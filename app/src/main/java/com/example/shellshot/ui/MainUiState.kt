package com.example.shellshot.ui

import com.example.shellshot.data.AppSettings
import com.example.shellshot.data.LogEntry
import com.example.shellshot.data.MediaAccessLevel
import com.example.shellshot.data.MonitoringPhase
import com.example.shellshot.data.PermissionSnapshot
import com.example.shellshot.data.ProcessingResult
import com.example.shellshot.data.ThemeOverride
import com.example.shellshot.media.ScreenshotDirectoryRecommendation
import com.example.shellshot.media.ScreenshotDirectories
import com.example.shellshot.service.AutoShellMode
import com.example.shellshot.template.DeviceCaptureProfile
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateImportDraft

enum class AppTab {
    Home,
    Templates,
    Settings,
    Logs,
}

data class TemplateImportAlert(
    val title: String,
    val message: String,
)

data class ScreenshotDirectoryOption(
    val relativePath: String,
    val absolutePath: String,
)

data class MainUiState(
    val settings: AppSettings = AppSettings(),
    val permissionSnapshot: PermissionSnapshot = PermissionSnapshot(),
    val monitoringActive: Boolean = false,
    val monitoringPhase: MonitoringPhase = MonitoringPhase.Disabled,
    val mode: AutoShellMode = AutoShellMode.USER_STOPPED,
    val modeReason: String = "",
    val screenOn: Boolean = true,
    val userUnlocked: Boolean = true,
    val currentForegroundPackage: String? = null,
    val gameForeground: Boolean = false,
    val fileWatcherActive: Boolean = false,
    val mediaFallbackActive: Boolean = false,
    val degradedMode: Boolean = false,
    val watchedDirectories: List<String> = emptyList(),
    val templates: List<ShellTemplate> = emptyList(),
    val currentDeviceCaptureProfile: DeviceCaptureProfile? = null,
    val templateImportDraft: TemplateImportDraft? = null,
    val templateImportInProgress: Boolean = false,
    val templateImportAlert: TemplateImportAlert? = null,
    val lastProcessingResult: ProcessingResult? = null,
    val recommendedScreenshotDirectories: List<ScreenshotDirectoryRecommendation> = emptyList(),
    val detectingScreenshotDirectories: Boolean = false,
    val logs: List<LogEntry> = emptyList(),
    val templateSelectingId: String? = null,
    val activeTab: AppTab = AppTab.Home,
    val themeOverride: ThemeOverride = ThemeOverride.System,
    val resolvedDarkTheme: Boolean = false,
    val templateOverviewVisible: Boolean = false,
    val templateOverviewDetailId: String? = null,
    val templatePendingDeleteId: String? = null,
    val templateImportPreparing: Boolean = false,
    val templateCarouselAnchorId: String? = null,
    val templateConfettiToken: Long = 0L,
) {
    val selectedTemplate: ShellTemplate?
        get() = templates.firstOrNull { it.id == settings.selectedTemplateId } ?: templates.firstOrNull()

    val overviewDetailTemplate: ShellTemplate?
        get() = templates.firstOrNull { it.id == templateOverviewDetailId }

    val pendingDeleteTemplate: ShellTemplate?
        get() = templates.firstOrNull { it.id == templatePendingDeleteId }

    val hasCoreStartPermissions: Boolean
        get() = permissionSnapshot.notificationsGranted && permissionSnapshot.allFilesGranted

    val canUseFallback: Boolean
        get() = settings.mediaStoreFallbackEnabled && permissionSnapshot.readImagesGranted

    val screenshotDirectoryRelativePath: String
        get() = ScreenshotDirectories.resolveScreenshotRelativePath(settings.screenshotRelativePath)

    val screenshotDirectoryAbsolutePath: String
        get() = ScreenshotDirectories.screenshotDirectory(screenshotDirectoryRelativePath).absolutePath

    val defaultScreenshotDirectoryRelativePath: String
        get() = ScreenshotDirectories.DEFAULT_SCREENSHOT_RELATIVE_PATH

    val presetScreenshotDirectories: List<ScreenshotDirectoryOption>
        get() = ScreenshotDirectories.presetScreenshotRelativePaths().map { relativePath ->
            ScreenshotDirectoryOption(
                relativePath = relativePath,
                absolutePath = ScreenshotDirectories.screenshotDirectory(relativePath).absolutePath,
            )
        }

    val mediaAccessLabel: String
        get() = when (permissionSnapshot.mediaAccessLevel) {
            MediaAccessLevel.Full -> "完整媒体访问"
            MediaAccessLevel.Partial -> "部分媒体访问"
            MediaAccessLevel.NotGranted -> "未授予媒体访问"
        }

    val phaseLabel: String
        get() = when (mode) {
            AutoShellMode.USER_STOPPED -> "未开启"
            AutoShellMode.SUSPEND_SCREEN_OFF -> "息屏暂停"
            AutoShellMode.SUSPEND_GAME -> "游戏暂停"
            AutoShellMode.ACTIVE -> when (monitoringPhase) {
                MonitoringPhase.Disabled -> "未开启"
                MonitoringPhase.Starting -> "启动中"
                MonitoringPhase.Monitoring -> if (degradedMode) "降级监听中" else "监听中"
                MonitoringPhase.CandidateDetected -> "已发现截图"
                MonitoringPhase.WaitingFileStable -> "等待文件稳定"
                MonitoringPhase.Composing -> "正在合成"
                MonitoringPhase.Saving -> "正在保存"
                MonitoringPhase.ObserverRegisterFailed -> "监听注册失败"
                MonitoringPhase.FileNotStable -> "文件未稳定"
                MonitoringPhase.ComposeFailed -> "合成失败"
                MonitoringPhase.SaveFailed -> "保存失败"
            }
        }
}
