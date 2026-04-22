package com.example.shellshot.ui

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shellshot.data.AppContainer
import com.example.shellshot.data.OutputNamingStrategy
import com.example.shellshot.data.PermissionSnapshot
import com.example.shellshot.data.ThemeOverride
import com.example.shellshot.template.CalibrationCornerId
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateImportDraft
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val appContainer: AppContainer,
) : ViewModel() {
    private val templatesState = MutableStateFlow<List<ShellTemplate>>(emptyList())
    private val permissionSnapshotState = MutableStateFlow(PermissionSnapshot())
    private val deviceCaptureProfileState = MutableStateFlow(appContainer.templateRepository.currentDeviceCaptureProfile())
    private val templateImportDraftState = MutableStateFlow<TemplateImportDraft?>(null)
    private val templateImportInProgressState = MutableStateFlow(false)
    private val templateImportAlertState = MutableStateFlow<TemplateImportAlert?>(null)
    private val templateSelectingIdState = MutableStateFlow<String?>(null)
    private val activeTabState = MutableStateFlow(AppTab.Home)
    private val systemDarkThemeState = MutableStateFlow(false)
    private val templateOverviewVisibleState = MutableStateFlow(false)
    private val templateOverviewDetailIdState = MutableStateFlow<String?>(null)
    private val templatePendingDeleteIdState = MutableStateFlow<String?>(null)
    private val templateImportPreparingState = MutableStateFlow(false)
    private val templateCarouselAnchorIdState = MutableStateFlow<String?>(null)
    private val templateConfettiTokenState = MutableStateFlow(0L)
    private val recommendedDirectoriesState =
        MutableStateFlow(emptyList<com.example.shellshot.media.ScreenshotDirectoryRecommendation>())
    private val detectingDirectoriesState = MutableStateFlow(false)
    private var refreshDirectoriesJob: Job? = null

    val uiState: StateFlow<MainUiState> = combine(
        appContainer.appPrefs.settingsFlow,
        appContainer.appStateStore.runtimeState,
        templatesState,
        permissionSnapshotState,
        templateImportDraftState,
    ) { settings, runtimeState, templates, permissionSnapshot, templateImportDraft ->
        MainUiState(
            settings = settings,
            permissionSnapshot = permissionSnapshot,
            monitoringActive = runtimeState.monitoringActive,
            monitoringPhase = runtimeState.monitoringPhase,
            mode = runtimeState.mode,
            modeReason = runtimeState.modeReason,
            screenOn = runtimeState.screenOn,
            userUnlocked = runtimeState.userUnlocked,
            currentForegroundPackage = runtimeState.currentForegroundPackage,
            gameForeground = runtimeState.gameForeground,
            fileWatcherActive = runtimeState.fileWatcherActive,
            mediaFallbackActive = runtimeState.mediaFallbackActive,
            degradedMode = runtimeState.degradedMode,
            watchedDirectories = runtimeState.watchedDirectories,
            templates = templates,
            templateImportDraft = templateImportDraft,
            lastProcessingResult = runtimeState.lastProcessingResult,
            logs = runtimeState.logs,
            themeOverride = settings.themeOverride,
        )
    }.combine(deviceCaptureProfileState) { state, deviceCaptureProfile ->
        state.copy(currentDeviceCaptureProfile = deviceCaptureProfile)
    }.combine(templateImportInProgressState) { state, templateImportInProgress ->
        state.copy(templateImportInProgress = templateImportInProgress)
    }.combine(templateImportAlertState) { state, templateImportAlert ->
        state.copy(templateImportAlert = templateImportAlert)
    }.combine(recommendedDirectoriesState) { state, recommendedDirectories ->
        state.copy(recommendedScreenshotDirectories = recommendedDirectories)
    }.combine(detectingDirectoriesState) { state, detectingDirectories ->
        state.copy(detectingScreenshotDirectories = detectingDirectories)
    }.combine(templateSelectingIdState) { state, templateSelectingId ->
        state.copy(templateSelectingId = templateSelectingId)
    }.combine(activeTabState) { state, activeTab ->
        state.copy(activeTab = activeTab)
    }.combine(systemDarkThemeState) { state, systemDarkTheme ->
        val resolvedDarkTheme = when (state.settings.themeOverride) {
            ThemeOverride.System -> systemDarkTheme
            ThemeOverride.Light -> false
            ThemeOverride.Dark -> true
        }
        state.copy(resolvedDarkTheme = resolvedDarkTheme)
    }.combine(templateOverviewVisibleState) { state, overviewVisible ->
        state.copy(templateOverviewVisible = overviewVisible)
    }.combine(templateOverviewDetailIdState) { state, detailId ->
        state.copy(templateOverviewDetailId = detailId)
    }.combine(templatePendingDeleteIdState) { state, deleteId ->
        state.copy(templatePendingDeleteId = deleteId)
    }.combine(templateImportPreparingState) { state, preparing ->
        state.copy(templateImportPreparing = preparing)
    }.combine(templateCarouselAnchorIdState) { state, anchorId ->
        state.copy(templateCarouselAnchorId = anchorId)
    }.combine(templateConfettiTokenState) { state, confettiToken ->
        state.copy(templateConfettiToken = confettiToken)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState(),
    )

    init {
        reloadTemplates()
        refreshPermissionSnapshot()
        ensureInitialScreenshotDirectoryRecommendations()
    }

    fun onAppVisible(context: Context) {
        deviceCaptureProfileState.value = appContainer.templateRepository.currentDeviceCaptureProfile()
        refreshPermissionSnapshot()
        restoreMonitoringIfNeeded(context)
    }

    fun updateSystemDarkTheme(isDark: Boolean) {
        systemDarkThemeState.value = isDark
    }

    fun selectTab(tab: AppTab) {
        val allowLogs = tab != AppTab.Logs || uiState.value.settings.debugModeEnabled
        activeTabState.value = if (allowLogs) tab else AppTab.Home
    }

    fun setThemeOverride(themeOverride: ThemeOverride) {
        viewModelScope.launch {
            appContainer.appPrefs.updateThemeOverride(themeOverride)
        }
    }

    fun toggleThemeQuick() {
        val next = if (uiState.value.resolvedDarkTheme) ThemeOverride.Light else ThemeOverride.Dark
        setThemeOverride(next)
    }

    fun openTemplateOverview() {
        templateOverviewVisibleState.value = true
    }

    fun closeTemplateOverview() {
        templateOverviewDetailIdState.value = null
        templateOverviewVisibleState.value = false
    }

    fun openTemplateOverviewDetail(id: String) {
        templateOverviewDetailIdState.value = id
    }

    fun closeTemplateOverviewDetail() {
        templateOverviewDetailIdState.value = null
    }

    fun requestDeleteTemplate(id: String) {
        templatePendingDeleteIdState.value = id
    }

    fun dismissDeleteTemplate() {
        templatePendingDeleteIdState.value = null
    }

    fun setTemplateCarouselAnchor(id: String?) {
        templateCarouselAnchorIdState.value = id
    }

    fun acknowledgeTemplateConfetti(token: Long) {
        if (templateConfettiTokenState.value == token) {
            templateConfettiTokenState.value = 0L
        }
    }

    fun startMonitoring(context: Context) {
        viewModelScope.launch {
            if (!appContainer.permissionManager.hasCoreMonitoringPermissions()) {
                refreshPermissionSnapshot()
                return@launch
            }

            appContainer.appPrefs.updateMonitoringEnabled(true)
            appContainer.serviceController.start(context)
        }
    }

    fun stopMonitoring(context: Context) {
        viewModelScope.launch {
            appContainer.appPrefs.updateMonitoringEnabled(false)
            appContainer.serviceController.stop(context)
            appContainer.appStateStore.setMonitoringActive(false)
        }
    }

    fun selectTemplate(templateId: String) {
        viewModelScope.launch {
            templateSelectingIdState.value = templateId
            appContainer.appPrefs.updateSelectedTemplate(templateId)
            templateCarouselAnchorIdState.value = templateId
            kotlinx.coroutines.delay(600)
            templateSelectingIdState.value = null
        }
    }

    fun prepareTemplateImport(imageUri: Uri) {
        if (templateImportInProgressState.value) {
            return
        }

        val defaultName = imageUri.lastPathSegment
            ?.substringAfterLast('/')
            ?.substringBeforeLast('.')
            ?.ifBlank { null }
            ?: "我的模板"

        templateImportInProgressState.value = true
        templateImportPreparingState.value = true
        viewModelScope.launch {
            try {
                val draft = appContainer.templateRepository.prepareTemplateImportDraft(
                    imageUri = imageUri,
                    templateNameOverride = defaultName,
                )
                val validationWarning = draft.validationWarning
                if (validationWarning != null) {
                    templateImportDraftState.value = null
                    templateImportAlertState.value = TemplateImportAlert(
                        title = "模板不符合要求",
                        message = mapTemplateImportMessage(validationWarning),
                    )
                } else {
                    templateImportDraftState.value = draft
                    templateImportAlertState.value = null
                }
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) throw throwable
                templateImportDraftState.value = null
                templateImportAlertState.value = TemplateImportAlert(
                    title = "无法导入模板",
                    message = mapTemplateImportMessage(throwable.message),
                )
            } finally {
                templateImportInProgressState.value = false
                templateImportPreparingState.value = false
            }
        }
    }

    fun updateTemplateImportName(name: String) {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(templateName = name)
    }

    fun startCalibrationCornerDrag(cornerId: CalibrationCornerId, touchX: Float, touchY: Float) {
        // 当前拖拽状态仅用于 UI，不持久化到草稿。该接口保留为后续手势遥测入口。
    }

    fun updateCalibrationCorner(cornerId: CalibrationCornerId, x: Float, y: Float) {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(
            corners = current.corners.map { corner ->
                if (corner.id == cornerId) {
                    corner.copy(
                        x = x.coerceIn(0f, current.outputWidth.toFloat()),
                        y = y.coerceIn(0f, current.outputHeight.toFloat()),
                    )
                } else {
                    corner
                }
            }
        )
    }

    fun finishCalibrationCornerDrag() {
        // 预留给将来的埋点/吸附统计，目前 UI 本地收口即可。
    }

    fun setCalibrationCornerRadius(value: Float) {
        val current = templateImportDraftState.value ?: return
        val fittedRect = current.fittedRect
        templateImportDraftState.value = current.copy(
            cornerRadiusPx = value.coerceIn(0f, minOf(fittedRect.width, fittedRect.height) / 2f),
        )
    }

    fun resetCalibrationToAutoInit() {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(
            corners = current.defaultCorners,
            cornerRadiusPx = current.defaultCornerRadiusPx,
        )
    }

    fun setTemplateCalibrationGuidesVisible(visible: Boolean) {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(showGuides = visible)
    }

    fun cancelTemplateImport() {
        templateImportDraftState.value = null
        templateImportPreparingState.value = false
    }

    fun confirmTemplateImport() {
        if (templateImportInProgressState.value) {
            return
        }
        val draft = templateImportDraftState.value ?: return
        if (draft.validationWarning != null) {
            templateImportDraftState.value = null
            templateImportAlertState.value = TemplateImportAlert(
                title = "模板不符合要求",
                message = mapTemplateImportMessage(draft.validationWarning),
            )
            return
        }

        val trimmedName = draft.templateName.trim()
        if (trimmedName.isBlank()) {
            return
        }

        templateImportDraftState.value = draft.copy(templateName = trimmedName)
        templateImportInProgressState.value = true
        viewModelScope.launch {
            try {
                val result = appContainer.templateRepository.importPreparedTemplateDraft(
                    draft.copy(templateName = trimmedName),
                )
                if (result.success && result.templateId != null) {
                    templateImportDraftState.value = null
                    templateImportAlertState.value = null
                    reloadTemplates(preferredTemplateId = result.templateId)
                    appContainer.appPrefs.updateSelectedTemplate(result.templateId)
                    templateCarouselAnchorIdState.value = result.templateId
                    templateConfettiTokenState.value = System.currentTimeMillis()
                } else {
                    templateImportAlertState.value = TemplateImportAlert(
                        title = "模板生成失败",
                        message = mapTemplateImportMessage(result.message),
                    )
                }
            } finally {
                templateImportInProgressState.value = false
            }
        }
    }

    fun deleteTemplate(templateId: String) {
        viewModelScope.launch {
            val templatesBeforeDelete = templatesState.value
            val deleteIndex = templatesBeforeDelete.indexOfFirst { it.id == templateId }.coerceAtLeast(0)
            val wasSelected = uiState.value.selectedTemplate?.id == templateId
            val result = appContainer.templateRepository.deleteUserTemplate(templateId)
            if (result.success) {
                dismissDeleteTemplate()
                reloadTemplates()
                val currentTemplates = appContainer.templateRepository.getTemplates()
                val fallbackTemplateId = currentTemplates.getOrNull(deleteIndex.coerceAtMost(currentTemplates.lastIndex))?.id
                    ?: currentTemplates.firstOrNull()?.id
                if (wasSelected && fallbackTemplateId != null) {
                    appContainer.appPrefs.updateSelectedTemplate(fallbackTemplateId)
                    templateCarouselAnchorIdState.value = fallbackTemplateId
                }
            }
        }
    }

    fun setAutoDeleteOriginal(enabled: Boolean) {
        viewModelScope.launch {
            appContainer.appPrefs.updateAutoDeleteOriginal(enabled)
        }
    }

    fun setDebugModeEnabled(enabled: Boolean) {
        viewModelScope.launch {
            appContainer.appPrefs.updateDebugModeEnabled(enabled)
            if (!enabled && activeTabState.value == AppTab.Logs) {
                activeTabState.value = AppTab.Home
            }
        }
    }

    fun updateScreenshotRelativePath(context: Context, relativePath: String) {
        viewModelScope.launch {
            appContainer.appPrefs.updateScreenshotRelativePath(relativePath)
            val settings = appContainer.appPrefs.currentSettings()
            if (settings.monitoringEnabled && appContainer.permissionManager.hasCoreMonitoringPermissions()) {
                appContainer.serviceController.start(context)
            }
        }
    }

    fun refreshScreenshotDirectoryRecommendations() {
        performScreenshotDirectoryRecommendationsRefresh(markInitialized = true)
    }

    fun setMediaStoreFallbackEnabled(enabled: Boolean) {
        viewModelScope.launch {
            appContainer.appPrefs.updateMediaStoreFallbackEnabled(enabled)
        }
    }

    fun setBatteryOptimizationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            appContainer.appPrefs.updateBatteryOptimizationEnabled(enabled)
        }
    }

    fun setOutputNamingStrategy(strategy: OutputNamingStrategy) {
        viewModelScope.launch {
            appContainer.appPrefs.updateOutputNamingStrategy(strategy)
        }
    }

    fun refreshPermissionSnapshot(context: Context? = null) {
        permissionSnapshotState.value = appContainer.permissionManager.readSnapshot()
    }

    fun dismissTemplateImportAlert() {
        templateImportAlertState.value = null
    }

    private fun ensureInitialScreenshotDirectoryRecommendations() {
        viewModelScope.launch {
            val settings = appContainer.appPrefs.currentSettings()
            if (!settings.screenshotDirectoryRecommendationsInitialized) {
                performScreenshotDirectoryRecommendationsRefresh(markInitialized = true)
            }
        }
    }

    private fun performScreenshotDirectoryRecommendationsRefresh(markInitialized: Boolean) {
        refreshDirectoriesJob?.cancel()
        refreshDirectoriesJob = viewModelScope.launch {
            detectingDirectoriesState.value = true
            try {
                val settings = appContainer.appPrefs.currentSettings()
                recommendedDirectoriesState.value = appContainer.screenshotDirectoryAdvisor.detectRecommendations(
                    currentRelativePath = settings.screenshotRelativePath,
                )
                if (markInitialized) {
                    appContainer.appPrefs.updateScreenshotDirectoryRecommendationsInitialized(true)
                }
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) throw throwable
                appContainer.logger.e("MainViewModel", "刷新截图目录推荐失败", throwable)
                recommendedDirectoriesState.value = emptyList()
            } finally {
                detectingDirectoriesState.value = false
            }
        }
    }

    private fun reloadTemplates(preferredTemplateId: String? = null) {
        viewModelScope.launch {
            val templates = appContainer.templateRepository.refreshTemplates()
            templatesState.value = templates

            val settings = appContainer.appPrefs.currentSettings()
            val templateToSelect = when {
                preferredTemplateId != null && templates.any { it.id == preferredTemplateId } -> preferredTemplateId
                settings.selectedTemplateId.isNotBlank() && templates.any { it.id == settings.selectedTemplateId } ->
                    settings.selectedTemplateId
                templates.isNotEmpty() -> templates.first().id
                else -> null
            }
            if (templateToSelect != null) {
                appContainer.appPrefs.updateSelectedTemplate(templateToSelect)
            }
        }
    }

    private fun restoreMonitoringIfNeeded(context: Context) {
        viewModelScope.launch {
            val settings = appContainer.appPrefs.currentSettings()
            val runtimeState = appContainer.appStateStore.runtimeState.value
            if (
                settings.monitoringEnabled &&
                !runtimeState.monitoringActive &&
                appContainer.permissionManager.hasCoreMonitoringPermissions()
            ) {
                appContainer.serviceController.start(context)
            }
        }
    }

    private fun mapTemplateImportMessage(message: String?): String {
        val source = message.orEmpty()
        return when {
            source.contains("未识别到透明屏幕区") ->
                "没有识别到透明屏幕区域。请上传带透明屏幕开口的手机壳图片后再试。"
            source.contains("透明区域过小") ->
                "识别到的透明屏幕区域太小。请换一张屏幕开口更完整的手机壳图片。"
            source.contains("保底") || source.contains("未识别到") ->
                "这张图片没有通过模板校验。请上传边框清晰、屏幕开口完整的手机壳图片。"
            source.contains("无法读取所选图片") ->
                "无法读取这张图片，请重新选择后再试。"
            source.contains("无法解码模板图片") ->
                "这张图片无法识别，请上传清晰的 PNG 或 JPG 手机壳图片。"
            source.contains("已失效") ->
                "所选图片已经失效，请重新上传。"
            source.contains("无法生成屏幕遮罩") ->
                "模板处理失败，请换一张带清晰透明屏幕区域的图片再试。"
            source.isNotBlank() -> source
            else -> "这张图片暂时无法生成模板，请更换符合要求的手机壳图片后重试。"
        }
    }
}

class MainViewModelFactory(
    private val appContainer: AppContainer,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(appContainer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
