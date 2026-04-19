package com.example.shellshot.ui

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shellshot.data.AppContainer
import com.example.shellshot.data.OutputNamingStrategy
import com.example.shellshot.data.PermissionSnapshot
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
            appContainer.appPrefs.updateSelectedTemplate(templateId)
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
            }
        }
    }

    fun updateTemplateImportName(name: String) {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(templateName = name)
    }

    fun updateTemplateCalibration(
        centerX: Float,
        centerY: Float,
        width: Float,
        cornerRadius: Float,
    ) {
        val current = templateImportDraftState.value ?: return
        val safeWidth = width.coerceIn(24f, current.outputWidth.toFloat())
        val safeHeight = (safeWidth / current.overlayAspectRatio)
            .coerceIn(24f, current.outputHeight.toFloat())
        val clampedWidth = if (safeHeight * current.overlayAspectRatio != safeWidth) {
            safeHeight * current.overlayAspectRatio
        } else {
            safeWidth
        }
        val halfW = clampedWidth / 2f
        val halfH = safeHeight / 2f
        templateImportDraftState.value = current.copy(
            overlayCenterX = centerX.coerceIn(halfW, current.outputWidth - halfW),
            overlayCenterY = centerY.coerceIn(halfH, current.outputHeight - halfH),
            overlayWidth = clampedWidth,
            overlayHeight = safeHeight,
            overlayCornerRadius = cornerRadius.coerceIn(0f, minOf(clampedWidth, safeHeight) / 2f),
        )
    }

    fun resetTemplateCalibration() {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(
            overlayCenterX = current.defaultOverlayCenterX,
            overlayCenterY = current.defaultOverlayCenterY,
            overlayWidth = current.defaultOverlayWidth,
            overlayHeight = current.defaultOverlayHeight,
            overlayCornerRadius = current.defaultOverlayCornerRadius,
        )
    }

    fun setTemplateCalibrationGuidesVisible(visible: Boolean) {
        val current = templateImportDraftState.value ?: return
        templateImportDraftState.value = current.copy(showGuides = visible)
    }

    fun cancelTemplateImport() {
        templateImportDraftState.value = null
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
            val result = appContainer.templateRepository.deleteUserTemplate(templateId)
            if (result.success) {
                reloadTemplates()
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
