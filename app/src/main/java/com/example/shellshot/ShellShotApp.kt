package com.example.shellshot

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shellshot.permissions.SpecialAccessNavigator
import com.example.shellshot.ui.MainViewModel
import com.example.shellshot.ui.components.AppBackdrop
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.BottomDock
import com.example.shellshot.ui.components.NavItem
import com.example.shellshot.ui.screen.HomeScreen
import com.example.shellshot.ui.screen.LogScreen
import com.example.shellshot.ui.screen.SettingsScreen
import com.example.shellshot.ui.screen.TemplateScreen

private enum class AppTab(val navItem: NavItem) {
    Home(NavItem(id = "home", label = "首页", icon = AppIconId.Home)),
    Templates(NavItem(id = "templates", label = "模板", icon = AppIconId.Template)),
    Settings(NavItem(id = "settings", label = "设置", icon = AppIconId.Settings)),
    Logs(NavItem(id = "logs", label = "日志", icon = AppIconId.Terminal)),
}

@Composable
fun ShellShotApp(
    viewModel: MainViewModel,
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var currentTab by rememberSaveable { mutableStateOf(AppTab.Home) }
    var templateDetailOpen by rememberSaveable { mutableStateOf(false) }

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
            currentTab = AppTab.Templates
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onAppVisible(context)
    }

    LaunchedEffect(uiState.settings.debugModeEnabled, currentTab) {
        if (!uiState.settings.debugModeEnabled && currentTab == AppTab.Logs) {
            currentTab = AppTab.Home
        }
    }

    LaunchedEffect(currentTab) {
        if (currentTab != AppTab.Templates) {
            templateDetailOpen = false
        }
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
        AppTab.Home.navItem,
        AppTab.Templates.navItem,
        AppTab.Settings.navItem,
        AppTab.Logs.navItem.copy(visible = uiState.settings.debugModeEnabled),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) Color.Black else Color(0xFFEBEDF0)),
    ) {
        AppBackdrop(modifier = Modifier.fillMaxSize())

        AnimatedContent(
            targetState = currentTab,
            label = "zip_frontend_crossfade",
            transitionSpec = {
                (
                    fadeIn(
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                    ) + slideInVertically(
                        initialOffsetY = { it / 8 },
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                    ) + scaleIn(
                        initialScale = 0.98f,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                    )
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
                    ) + slideOutVertically(
                        targetOffsetY = { -it / 8 },
                        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
                    ) + scaleOut(
                        targetScale = 0.98f,
                        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
                    )
                ).using(SizeTransform(clip = false))
            },
            modifier = Modifier.fillMaxSize(),
        ) { tab ->
            when (tab) {
                AppTab.Home -> HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    darkTheme = darkTheme,
                    onToggleDarkTheme = onToggleDarkTheme,
                    onToggleMonitoring = onToggleMonitoring,
                    onSelectTemplate = {
                        currentTab = AppTab.Templates
                        viewModel.selectTemplate(it)
                    },
                    onOpenTemplates = { currentTab = AppTab.Templates },
                    onOpenSettings = { currentTab = AppTab.Settings },
                    onOpenLogs = {
                        currentTab = if (uiState.settings.debugModeEnabled) AppTab.Logs else AppTab.Settings
                    },
                )

                AppTab.Templates -> TemplateScreen(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    onSelectTemplate = viewModel::selectTemplate,
                    onUploadTemplateImage = { uploadTemplateImageLauncher.launch(arrayOf("image/*")) },
                    onDeleteTemplate = viewModel::deleteTemplate,
                    onUpdateImportName = viewModel::updateTemplateImportName,
                    onConfirmImport = viewModel::confirmTemplateImport,
                    onCancelImport = viewModel::cancelTemplateImport,
                    onDismissImportAlert = viewModel::dismissTemplateImportAlert,
                    onCancelPage = { currentTab = AppTab.Home },
                    onDetailToggle = { templateDetailOpen = it },
                )

                AppTab.Settings -> SettingsScreen(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
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
                    onOpenNotificationSettings = { SpecialAccessNavigator.openNotificationSettings(context) },
                    onOpenManageAllFilesSettings = { SpecialAccessNavigator.openManageAllFilesSettings(context) },
                    onToggleDebugMode = viewModel::setDebugModeEnabled,
                    onRefreshScreenshotDirectories = viewModel::refreshScreenshotDirectoryRecommendations,
                    onUpdateScreenshotDirectory = { relativePath ->
                        viewModel.updateScreenshotRelativePath(context, relativePath)
                    },
                    onToggleAutoDelete = viewModel::setAutoDeleteOriginal,
                    onToggleMediaStoreFallback = viewModel::setMediaStoreFallbackEnabled,
                    onOpenBatteryOptimizationSettings = { SpecialAccessNavigator.openBatteryOptimizationSettings(context) },
                )

                AppTab.Logs -> LogScreen(
                    modifier = Modifier.fillMaxSize(),
                    logs = uiState.logs,
                )
            }
        }

        BottomDock(
            items = dockItems,
            selectedItemId = currentTab.navItem.id,
            darkTheme = darkTheme,
            hidden = templateDetailOpen,
            modifier = Modifier.align(Alignment.BottomCenter),
            onItemSelected = { itemId ->
                currentTab = AppTab.entries.firstOrNull { it.navItem.id == itemId } ?: AppTab.Home
            },
        )
    }
}
