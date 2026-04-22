package com.example.shellshot

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shellshot.permissions.SpecialAccessNavigator
import com.example.shellshot.ui.AppTab
import com.example.shellshot.ui.MainViewModel
import com.example.shellshot.ui.components.DockItem
import com.example.shellshot.ui.components.FloatingDock
import com.example.shellshot.ui.components.ShellShotBackdrop
import com.example.shellshot.ui.screen.HomeTabScreen
import com.example.shellshot.ui.screen.LogsTabScreen
import com.example.shellshot.ui.screen.SettingsTabScreen
import com.example.shellshot.ui.screen.TemplatesTabScreen
import dev.chrisbanes.haze.rememberHazeState
import dev.chrisbanes.haze.hazeSource
import com.example.shellshot.ui.components.AppIconId

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ShellShotApp(
    viewModel: MainViewModel,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val systemDarkTheme = isSystemInDarkTheme()
    val hazeState = rememberHazeState()

    val notificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        viewModel.refreshPermissionSnapshot(context)
    }

    val mediaAccessLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) {
        viewModel.refreshPermissionSnapshot(context)
    }

    val uploadTemplateImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
    ) { uri ->
        if (uri != null) {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION,
                )
            }
            viewModel.prepareTemplateImport(uri)
            viewModel.selectTab(AppTab.Templates)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onAppVisible(context)
    }

    LaunchedEffect(systemDarkTheme) {
        viewModel.updateSystemDarkTheme(systemDarkTheme)
    }

    DisposableEffect(lifecycleOwner, context) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onAppVisible(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val onToggleMonitoring: (Boolean) -> Unit = { enabled ->
        if (!enabled) {
            viewModel.stopMonitoring(context)
        } else if (!uiState.permissionSnapshot.notificationsGranted) {
            notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else if (!uiState.permissionSnapshot.allFilesGranted) {
            SpecialAccessNavigator.openManageAllFilesSettings(context)
        } else {
            viewModel.startMonitoring(context)
        }
    }

    val dockItems = listOf(
        DockItem(tab = AppTab.Home, label = "首页", icon = AppIconId.Home),
        DockItem(tab = AppTab.Templates, label = "模板", icon = AppIconId.Template),
        DockItem(tab = AppTab.Settings, label = "设置", icon = AppIconId.Settings),
        DockItem(tab = AppTab.Logs, label = "日志", icon = AppIconId.Terminal, visible = uiState.settings.debugModeEnabled),
    )
    val detailMode = uiState.templateOverviewVisible ||
        uiState.templateOverviewDetailId != null ||
        uiState.templatePendingDeleteId != null ||
        uiState.templateImportPreparing ||
        uiState.templateImportDraft != null

    SharedTransitionLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
        ) {
            ShellShotBackdrop(
                isDark = uiState.resolvedDarkTheme,
                modifier = Modifier.fillMaxSize(),
            )

            AnimatedContent(
                targetState = uiState.activeTab,
                modifier = Modifier.fillMaxSize(),
                transitionSpec = {
                    (fadeIn(animationSpec = tween(400)) +
                        slideInVertically(initialOffsetY = { 15 }) +
                        scaleIn(initialScale = 0.98f, animationSpec = tween(400))) togetherWith
                        (fadeOut(animationSpec = tween(220)) +
                            slideOutVertically(targetOffsetY = { -15 }) +
                            scaleOut(targetScale = 0.98f, animationSpec = tween(220)))
                },
                label = "app-tabs",
            ) { tab ->
                AppTabContent(
                    tab = tab,
                    uiState = uiState,
                    hazeState = hazeState,
                    animatedVisibilityScope = this,
                    onToggleMonitoring = onToggleMonitoring,
                    onOpenTemplates = {
                        viewModel.setTemplateCarouselAnchor(uiState.selectedTemplate?.id)
                        viewModel.selectTab(AppTab.Templates)
                    },
                    onSelectTemplateAndOpen = {
                        viewModel.selectTemplate(it)
                        viewModel.selectTab(AppTab.Templates)
                    },
                    onToggleThemeQuick = viewModel::toggleThemeQuick,
                    onRequestNotifications = {
                        if (uiState.permissionSnapshot.notificationsGranted) {
                            SpecialAccessNavigator.openNotificationSettings(context)
                        } else {
                            notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    },
                    onRequestMediaAccess = {
                        mediaAccessLauncher.launch(
                            arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                            ),
                        )
                    },
                    onOpenManageAllFilesSettings = { SpecialAccessNavigator.openManageAllFilesSettings(context) },
                    onOpenBatteryOptimizationSettings = { SpecialAccessNavigator.openBatteryOptimizationSettings(context) },
                    onUploadTemplateImage = { uploadTemplateImageLauncher.launch(arrayOf("image/*")) },
                    viewModel = viewModel,
                )
            }

            FloatingDock(
                items = dockItems,
                activeTab = uiState.activeTab,
                isDark = uiState.resolvedDarkTheme,
                hazeState = hazeState,
                detailMode = detailMode,
                modifier = Modifier.fillMaxSize(),
                onTabSelected = viewModel::selectTab,
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun androidx.compose.animation.SharedTransitionScope.AppTabContent(
    tab: AppTab,
    uiState: com.example.shellshot.ui.MainUiState,
    hazeState: dev.chrisbanes.haze.HazeState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenTemplates: () -> Unit,
    onSelectTemplateAndOpen: (String) -> Unit,
    onToggleThemeQuick: () -> Unit,
    onRequestNotifications: () -> Unit,
    onRequestMediaAccess: () -> Unit,
    onOpenManageAllFilesSettings: () -> Unit,
    onOpenBatteryOptimizationSettings: () -> Unit,
    onUploadTemplateImage: () -> Unit,
    viewModel: MainViewModel,
) {
    when (tab) {
        AppTab.Home -> HomeTabScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            isDark = uiState.resolvedDarkTheme,
            hazeState = hazeState,
            onToggleThemeQuick = onToggleThemeQuick,
            onToggleMonitoring = onToggleMonitoring,
            onOpenTemplates = onOpenTemplates,
            onSelectTemplateAndOpen = onSelectTemplateAndOpen,
        )

        AppTab.Templates -> TemplatesTabScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            isDark = uiState.resolvedDarkTheme,
            hazeState = hazeState,
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = this,
            onUploadTemplateImage = onUploadTemplateImage,
            onSelectTemplate = viewModel::selectTemplate,
            onRequestDeleteTemplate = viewModel::requestDeleteTemplate,
            onDismissDeleteTemplate = viewModel::dismissDeleteTemplate,
            onConfirmDeleteTemplate = viewModel::deleteTemplate,
            onOpenOverview = viewModel::openTemplateOverview,
            onCloseOverview = viewModel::closeTemplateOverview,
            onOpenOverviewDetail = viewModel::openTemplateOverviewDetail,
            onCloseOverviewDetail = viewModel::closeTemplateOverviewDetail,
            onSetCarouselAnchor = viewModel::setTemplateCarouselAnchor,
            onUpdateImportName = viewModel::updateTemplateImportName,
            onStartCornerDrag = viewModel::startCalibrationCornerDrag,
            onUpdateCorner = viewModel::updateCalibrationCorner,
            onFinishCornerDrag = viewModel::finishCalibrationCornerDrag,
            onUpdateCornerRadius = viewModel::setCalibrationCornerRadius,
            onResetCalibration = viewModel::resetCalibrationToAutoInit,
            onConfirmImport = viewModel::confirmTemplateImport,
            onCancelImport = viewModel::cancelTemplateImport,
            onDismissImportAlert = viewModel::dismissTemplateImportAlert,
            onAcknowledgeConfetti = viewModel::acknowledgeTemplateConfetti,
        )

        AppTab.Settings -> SettingsTabScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            isDark = uiState.resolvedDarkTheme,
            hazeState = hazeState,
            onRequestNotifications = onRequestNotifications,
            onRequestMediaAccess = onRequestMediaAccess,
            onOpenManageAllFilesSettings = onOpenManageAllFilesSettings,
            onOpenBatteryOptimizationSettings = onOpenBatteryOptimizationSettings,
            onRefreshScreenshotDirectories = viewModel::refreshScreenshotDirectoryRecommendations,
            onToggleAutoDelete = viewModel::setAutoDeleteOriginal,
            onToggleMediaStoreFallback = viewModel::setMediaStoreFallbackEnabled,
            onToggleDebugMode = viewModel::setDebugModeEnabled,
            onSetThemeOverride = viewModel::setThemeOverride,
        )

        AppTab.Logs -> LogsTabScreen(
            modifier = Modifier.fillMaxSize(),
            logs = uiState.logs,
            isDark = uiState.resolvedDarkTheme,
            hazeState = hazeState,
        )
    }
}
