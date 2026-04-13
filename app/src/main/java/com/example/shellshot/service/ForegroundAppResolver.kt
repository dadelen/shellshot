package com.example.shellshot.service

import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import com.example.shellshot.utils.ShellLogger

data class ForegroundAppSnapshot(
    val packageName: String?,
    val isGame: Boolean,
)

class ForegroundAppResolver(
    private val context: Context,
    private val logger: ShellLogger,
) {
    private val usageStatsManager by lazy { context.getSystemService(UsageStatsManager::class.java) }

    fun resolve(gamePackageRules: List<String>): ForegroundAppSnapshot {
        val now = System.currentTimeMillis()
        val events = usageStatsManager.queryEvents(now - QUERY_WINDOW_MILLIS, now)
        var latestPackage: String? = null
        var latestTimestamp = Long.MIN_VALUE
        val event = UsageEvents.Event()
        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            val isForegroundEvent = event.eventType == UsageEvents.Event.ACTIVITY_RESUMED
            if (isForegroundEvent && event.timeStamp >= latestTimestamp && !event.packageName.isNullOrBlank()) {
                latestPackage = event.packageName
                latestTimestamp = event.timeStamp
            }
        }

        val normalizedRules = gamePackageRules
            .map(String::trim)
            .filter { it.isNotBlank() }
        val isGame = latestPackage != null && (
            normalizedRules.any { latestPackage.startsWith(it, ignoreCase = true) } ||
                isCategoryGame(latestPackage)
            )

        logger.d(
            TAG,
            "前台应用评估 package=$latestPackage isGame=$isGame",
        )
        return ForegroundAppSnapshot(
            packageName = latestPackage,
            isGame = isGame,
        )
    }

    private fun isCategoryGame(packageName: String): Boolean {
        return runCatching {
            context.packageManager.getApplicationInfo(packageName, 0).category == ApplicationInfo.CATEGORY_GAME
        }.onFailure {
            logger.d(TAG, "前台应用分类判断失败 package=$packageName")
        }.getOrDefault(false)
    }

    private companion object {
        const val TAG = "ForegroundApp"
        const val QUERY_WINDOW_MILLIS = 120_000L
    }
}
