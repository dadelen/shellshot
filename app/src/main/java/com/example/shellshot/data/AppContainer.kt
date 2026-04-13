package com.example.shellshot.data

import android.app.Application
import com.example.shellshot.media.BitmapLoader
import com.example.shellshot.media.LatestScreenshotResolver
import com.example.shellshot.media.MediaStoreRepository
import com.example.shellshot.media.OutputRepository
import com.example.shellshot.media.ScreenshotDirectoryAdvisor
import com.example.shellshot.media.ScreenshotStabilityChecker
import com.example.shellshot.permissions.PermissionManager
import com.example.shellshot.processor.ShellComposeEngine
import com.example.shellshot.processor.ShellComposer
import com.example.shellshot.queue.QueuedScreenshotTaskStore
import com.example.shellshot.queue.QueuedScreenshotWorker
import com.example.shellshot.service.ForegroundAppResolver
import com.example.shellshot.template.TemplateRepository
import com.example.shellshot.utils.ServiceController
import com.example.shellshot.utils.ShellLogger

class AppContainer(
    application: Application,
) {
    private val appContext = application.applicationContext

    val appStateStore = AppStateStore()
    val appPrefs = AppPrefs(appContext)
    val logRepository = LogRepository(appContext, appStateStore)
    val logger = ShellLogger(logRepository)
    val permissionManager = PermissionManager(appContext)
    val templateRepository = TemplateRepository(appContext, logger)
    val mediaStoreRepository = MediaStoreRepository(appContext.contentResolver, logger)
    val screenshotDirectoryAdvisor = ScreenshotDirectoryAdvisor(
        mediaStoreRepository = mediaStoreRepository,
        logger = logger,
    )
    val latestScreenshotResolver = LatestScreenshotResolver(
        appPrefs = appPrefs,
        mediaStoreRepository = mediaStoreRepository,
        logger = logger,
    )
    val screenshotStabilityChecker = ScreenshotStabilityChecker(
        logger = logger,
    )
    val bitmapLoader = BitmapLoader(appContext.contentResolver, logger)
    val shellComposer = ShellComposer(
        templateRepository = templateRepository,
        logger = logger,
    )
    val shellComposeEngine = ShellComposeEngine(shellComposer)
    val outputRepository = OutputRepository(
        context = appContext,
        contentResolver = appContext.contentResolver,
        logger = logger,
    )
    val queuedTaskStore = QueuedScreenshotTaskStore(
        context = appContext,
        appPrefs = appPrefs,
    )
    val queuedScreenshotWorker = QueuedScreenshotWorker(
        appPrefs = appPrefs,
        appStateStore = appStateStore,
        taskStore = queuedTaskStore,
        templateRepository = templateRepository,
        latestScreenshotResolver = latestScreenshotResolver,
        screenshotStabilityChecker = screenshotStabilityChecker,
        bitmapLoader = bitmapLoader,
        outputRepository = outputRepository,
        shellComposeEngine = shellComposeEngine,
        logger = logger,
    )
    val foregroundAppResolver = ForegroundAppResolver(
        context = appContext,
        logger = logger,
    )
    val serviceController = ServiceController
}
