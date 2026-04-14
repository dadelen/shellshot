package com.example.shellshot.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shellshot.ShellShotApplication
import com.example.shellshot.media.ScreenshotDirectories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "shellshot_prefs")
private val defaultGamePackageRules = listOf(
    "com.tencent.tmgp",
    "com.tencent.ig",
    "com.proximabeta",
    "com.miHoYo",
    "com.mihoyo",
    "com.hoyoverse",
    "com.hypergryph",
    "com.netease",
    "com.supercell",
    "com.riotgames",
    "com.lilithgames",
    "com.papegames",
    "com.perfectworld",
)

enum class OutputNamingStrategy {
    Prefix,
    Suffix;

    companion object {
        fun fromRaw(value: String?): OutputNamingStrategy {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: Prefix
        }
    }
}

data class AppSettings(
    val selectedTemplateId: String = "",
    val screenshotRelativePath: String = ScreenshotDirectories.DEFAULT_SCREENSHOT_RELATIVE_PATH,
    val screenshotDirectoryRecommendationsInitialized: Boolean = false,
    val autoDeleteOriginal: Boolean = true,
    val debugModeEnabled: Boolean = false,
    val monitoringEnabled: Boolean = false,
    val mediaStoreFallbackEnabled: Boolean = true,
    val batteryOptimizationEnabled: Boolean = false,
    val outputNamingStrategy: OutputNamingStrategy = OutputNamingStrategy.Prefix,
    val recentProcessedKeys: List<String> = emptyList(),
    val gamePackageRules: List<String> = defaultGamePackageRules,
)

class AppPrefs(
    private val context: Context,
) {
    val settingsFlow: Flow<AppSettings> = context.dataStore.data.map { preferences ->
        AppSettings(
            selectedTemplateId = preferences[Keys.SelectedTemplateId].orEmpty(),
            screenshotRelativePath = normalizeScreenshotRelativePath(preferences[Keys.ScreenshotRelativePath]),
            screenshotDirectoryRecommendationsInitialized =
                preferences[Keys.ScreenshotDirectoryRecommendationsInitialized] ?: false,
            autoDeleteOriginal = preferences[Keys.AutoDeleteOriginal] ?: true,
            debugModeEnabled = preferences[Keys.DebugModeEnabled] ?: false,
            monitoringEnabled = preferences[Keys.MonitoringEnabled] ?: false,
            mediaStoreFallbackEnabled = preferences[Keys.MediaStoreFallbackEnabled] ?: true,
            batteryOptimizationEnabled = preferences[Keys.BatteryOptimizationEnabled] ?: false,
            outputNamingStrategy = OutputNamingStrategy.fromRaw(preferences[Keys.OutputNamingStrategy]),
            recentProcessedKeys = decodeProcessedKeys(preferences[Keys.RecentProcessedKeys]),
            gamePackageRules = decodeGamePackageRules(preferences[Keys.GamePackageRules]),
        )
    }

    suspend fun currentSettings(): AppSettings = settingsFlow.first()

    suspend fun updateSelectedTemplate(templateId: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.SelectedTemplateId] = templateId
        }
    }

    suspend fun updateScreenshotRelativePath(relativePath: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.ScreenshotRelativePath] = normalizeScreenshotRelativePath(relativePath)
        }
    }

    suspend fun updateScreenshotDirectoryRecommendationsInitialized(initialized: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.ScreenshotDirectoryRecommendationsInitialized] = initialized
        }
    }

    suspend fun updateAutoDeleteOriginal(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.AutoDeleteOriginal] = enabled
        }
    }

    suspend fun updateDebugModeEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.DebugModeEnabled] = enabled
        }
    }

    suspend fun updateMonitoringEnabled(enabled: Boolean) {
        log("Persist monitoringEnabled=$enabled ${diagnosticTrace("updateMonitoringEnabled enabled=$enabled")}")
        context.dataStore.edit { preferences ->
            preferences[Keys.MonitoringEnabled] = enabled
        }
    }

    suspend fun updateMediaStoreFallbackEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.MediaStoreFallbackEnabled] = enabled
        }
    }

    suspend fun updateBatteryOptimizationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.BatteryOptimizationEnabled] = enabled
        }
    }

    suspend fun updateOutputNamingStrategy(strategy: OutputNamingStrategy) {
        context.dataStore.edit { preferences ->
            preferences[Keys.OutputNamingStrategy] = strategy.name
        }
    }

    suspend fun updateRecentProcessedKeys(keys: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[Keys.RecentProcessedKeys] = encodeProcessedKeys(keys)
        }
    }

    suspend fun addRecentProcessedKey(key: String) {
        if (key.isBlank()) {
            return
        }

        context.dataStore.edit { preferences ->
            val existing = decodeProcessedKeys(preferences[Keys.RecentProcessedKeys])
            val updated = (existing.filterNot { it == key } + key).takeLast(MAX_RECENT_PROCESSED_KEYS)
            preferences[Keys.RecentProcessedKeys] = encodeProcessedKeys(updated)
        }
    }

    suspend fun updateGamePackageRules(rules: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[Keys.GamePackageRules] = encodeGamePackageRules(rules)
        }
    }

    private fun decodeProcessedKeys(raw: String?): List<String> {
        if (raw.isNullOrBlank()) {
            return emptyList()
        }
        return raw.split('\n').filter { it.isNotBlank() }
    }

    private fun normalizeScreenshotRelativePath(relativePath: String?): String {
        return ScreenshotDirectories.resolveScreenshotRelativePath(relativePath)
    }

    private fun encodeProcessedKeys(keys: List<String>): String = keys.joinToString(separator = "\n")

    private fun decodeGamePackageRules(raw: String?): List<String> {
        if (raw.isNullOrBlank()) {
            return defaultGamePackageRules
        }

        return raw.split('\n')
            .map(String::trim)
            .filter { it.isNotBlank() }
            .ifEmpty { defaultGamePackageRules }
    }

    private fun encodeGamePackageRules(rules: List<String>): String {
        return rules
            .map(String::trim)
            .filter { it.isNotBlank() }
            .distinct()
            .joinToString(separator = "\n")
    }

    private fun log(message: String) {
        val logger = (context.applicationContext as? ShellShotApplication)?.appContainer?.logger
        if (logger != null) {
            logger.d(TAG, message)
        } else {
            Log.d(TAG, message)
        }
    }

    private fun diagnosticTrace(reason: String): String = buildString {
        append("reason=")
        append(reason)
        append(" thread=")
        append(Thread.currentThread().name)
        append('\n')
        append(Throwable().stackTraceToString())
    }

    private object Keys {
        val SelectedTemplateId: Preferences.Key<String> = stringPreferencesKey("selected_template_id")
        val ScreenshotRelativePath: Preferences.Key<String> = stringPreferencesKey("screenshot_relative_path")
        val ScreenshotDirectoryRecommendationsInitialized: Preferences.Key<Boolean> =
            booleanPreferencesKey("screenshot_directory_recommendations_initialized")
        val AutoDeleteOriginal: Preferences.Key<Boolean> = booleanPreferencesKey("auto_delete_original")
        val DebugModeEnabled: Preferences.Key<Boolean> = booleanPreferencesKey("debug_mode_enabled")
        val MonitoringEnabled: Preferences.Key<Boolean> = booleanPreferencesKey("monitoring_enabled")
        val MediaStoreFallbackEnabled: Preferences.Key<Boolean> =
            booleanPreferencesKey("media_store_fallback_enabled")
        val BatteryOptimizationEnabled: Preferences.Key<Boolean> =
            booleanPreferencesKey("battery_optimization_enabled")
        val OutputNamingStrategy: Preferences.Key<String> = stringPreferencesKey("output_naming_strategy")
        val RecentProcessedKeys: Preferences.Key<String> = stringPreferencesKey("recent_processed_keys")
        val GamePackageRules: Preferences.Key<String> = stringPreferencesKey("game_package_rules")
    }

    private companion object {
        const val TAG = "AppPrefs"
        const val MAX_RECENT_PROCESSED_KEYS = 240
    }
}
