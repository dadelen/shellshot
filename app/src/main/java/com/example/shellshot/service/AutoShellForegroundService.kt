package com.example.shellshot.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.FileObserver
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.SystemClock
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.shellshot.MainActivity
import com.example.shellshot.R
import com.example.shellshot.ShellShotApplication
import com.example.shellshot.data.MonitoringPhase
import com.example.shellshot.media.ScreenshotDirectories
import com.example.shellshot.media.ScreenshotRules
import com.example.shellshot.observer.DirectoryWatchEvent
import com.example.shellshot.observer.MultiDirectoryFileObserver
import com.example.shellshot.observer.ScreenshotContentObserver
import com.example.shellshot.receiver.AutoShellRestartReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AutoShellForegroundService : LifecycleService() {
    private val appContainer by lazy {
        (application as ShellShotApplication).appContainer
    }

    private val modeMutex = Mutex()

    private var explicitStopRequested = false
    private var foregroundStarted = false
    private var serviceScope: CoroutineScope? = null
    private var observerThread: HandlerThread? = null
    private var observerHandler: Handler? = null
    private var directoryObserver: MultiDirectoryFileObserver? = null
    private var mediaStoreObserver: ScreenshotContentObserver? = null
    private var workerJob: Job? = null
    private var startupJob: Job? = null
    private var resumeRecoveryJob: Job? = null
    private var observationWatchdogJob: Job? = null
    private var foregroundAppMonitorJob: Job? = null
    private var screenStateMonitor: ScreenStateMonitor? = null
    private var startupRecoveryCompleted = false
    private var usageAccessWarningLogged = false
    private var lastForegroundPackage: String? = null
    private var lastForegroundGameState: Boolean? = null
    private var lastObservationRestoreElapsedRealtime = 0L
    private var lastDirectoryEventElapsedRealtime = 0L

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        ensureRuntime("onCreate")
        appContainer.logger.d(TAG, "服务已创建 ${diagnosticTrace("onCreate")}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action.orEmpty()
        appContainer.logger.d(
            TAG,
            "服务收到启动指令 action=$action startId=$startId ${diagnosticTrace("onStartCommand action=$action")}",
        )
        cancelScheduledRestart("onStartCommand")

        if (action == ACTION_STOP) {
            val trustedStop = intent?.getBooleanExtra(EXTRA_TRUSTED_STOP, false) ?: false
            if (!trustedStop) {
                appContainer.logger.d(TAG, "忽略未标记的停止指令")
                return START_STICKY
            }

            setExplicitStopRequested(true, "用户主动点击停止监听")
            cancelScheduledRestart("user_stop")
            runBlocking {
                appContainer.appPrefs.updateMonitoringEnabled(false)
            }
            appContainer.appStateStore.setMode(AutoShellMode.USER_STOPPED, "用户主动停止监听")
            stopMonitoring("user_stop")
            return START_NOT_STICKY
        }

        val settings = runBlocking { appContainer.appPrefs.currentSettings() }
        if (!settings.monitoringEnabled || !appContainer.permissionManager.hasCoreMonitoringPermissions()) {
            appContainer.logger.d(TAG, "无法启动后台监听：缺少前置条件")
            appContainer.appStateStore.setMode(AutoShellMode.USER_STOPPED, "缺少前置条件")
            stopMonitoring("missing_prerequisites")
            return START_NOT_STICKY
        }

        setExplicitStopRequested(false, "服务启动")
        ensureForeground()
        ensureRuntime("start_command")
        appContainer.appStateStore.setMonitoringActive(true)
        appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.Starting)
        startEnvironmentMonitoring()
        startObservationWatchdogIfNeeded()
        startQueueWorkerIfNeeded()
        requestModeEvaluation("start_command")
        return START_STICKY
    }

    override fun onDestroy() {
        appContainer.logger.d(
            TAG,
            "服务已销毁 explicitStop=$explicitStopRequested ${diagnosticTrace("onDestroy explicitStop=$explicitStopRequested")}",
        )
        cleanupRuntime("onDestroy")
        if (!explicitStopRequested) {
            maybeRestartSelf("onDestroy")
        }
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        appContainer.logger.d(
            TAG,
            "服务任务被系统移除 explicitStop=$explicitStopRequested ${diagnosticTrace("onTaskRemoved explicitStop=$explicitStopRequested")}",
        )
        if (!explicitStopRequested) {
            maybeRestartSelf("onTaskRemoved")
        }
        super.onTaskRemoved(rootIntent)
    }

    private fun ensureRuntime(reason: String): CoroutineScope {
        val currentScope = serviceScope
        if (currentScope?.isActive == true && observerThread?.isAlive == true && observerHandler != null) {
            return currentScope
        }

        if (currentScope != null) {
            appContainer.logger.d(TAG, "重建服务运行时 reason=$reason")
            currentScope.cancel()
        }
        observerThread?.quitSafely()

        observerThread = HandlerThread("AutoShellWorker").also { it.start() }
        observerHandler = Handler(checkNotNull(observerThread).looper)
        serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        return checkNotNull(serviceScope)
    }

    private fun cleanupRuntime(reason: String) {
        cancelJob(startupJob, "startupJob", reason)
        startupJob = null
        cancelJob(resumeRecoveryJob, "resumeRecoveryJob", reason)
        resumeRecoveryJob = null
        cancelJob(observationWatchdogJob, "observationWatchdogJob", reason)
        observationWatchdogJob = null
        cancelJob(workerJob, "workerJob", reason)
        workerJob = null
        cancelJob(foregroundAppMonitorJob, "foregroundAppMonitorJob", reason)
        foregroundAppMonitorJob = null

        stopObservationChain("cleanup:$reason")
        stopEnvironmentMonitoring()

        appContainer.appStateStore.setMonitoringActive(false)
        appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.Disabled)
        if (explicitStopRequested) {
            appContainer.appStateStore.setMode(AutoShellMode.USER_STOPPED, "用户主动停止监听")
        }

        serviceScope?.cancel()
        serviceScope = null
        observerHandler = null
        observerThread?.quitSafely()
        observerThread = null
    }

    private fun ensureForeground() {
        if (foregroundStarted) {
            return
        }
        startForeground(NOTIFICATION_ID, buildNotification())
        foregroundStarted = true
    }

    private fun startEnvironmentMonitoring() {
        val monitor = screenStateMonitor ?: ScreenStateMonitor(
            context = applicationContext,
            logger = appContainer.logger,
            onChanged = { reason, snapshot ->
                appContainer.appStateStore.setScreenState(
                    screenOn = snapshot.screenOn,
                    userUnlocked = snapshot.userUnlocked,
                )
                requestModeEvaluation("screen:$reason")
            },
        ).also { screenStateMonitor = it }
        monitor.start()
        startForegroundAppMonitorIfNeeded()
    }

    private fun stopEnvironmentMonitoring() {
        screenStateMonitor?.stop()
        screenStateMonitor = null
    }

    private fun startForegroundAppMonitorIfNeeded() {
        if (foregroundAppMonitorJob?.isActive == true) {
            return
        }

        foregroundAppMonitorJob = ensureRuntime("foreground_app_monitor").launch {
            while (isActive) {
                val snapshot = screenStateMonitor?.currentSnapshot()
                syncScreenStateFromPoll(snapshot)
                if (snapshot == null || !snapshot.screenOn || !snapshot.userUnlocked) {
                    appContainer.appStateStore.setForegroundApp(null, false)
                    resetForegroundAppCache()
                    delay(FOREGROUND_APP_POLL_MILLIS)
                    continue
                }

                if (!appContainer.permissionManager.hasUsageStatsAccess()) {
                    appContainer.appStateStore.setForegroundApp(null, false)
                    if (!usageAccessWarningLogged) {
                        usageAccessWarningLogged = true
                        appContainer.logger.d(TAG, "")
                    }
                    requestModeEvaluation("usage_access_missing")
                    delay(FOREGROUND_APP_POLL_MILLIS)
                    continue
                }

                usageAccessWarningLogged = false
                val settings = appContainer.appPrefs.currentSettings()
                val foregroundSnapshot = appContainer.foregroundAppResolver.resolve(settings.gamePackageRules)
                appContainer.appStateStore.setForegroundApp(
                    packageName = foregroundSnapshot.packageName,
                    isGame = foregroundSnapshot.isGame,
                )

                if (
                    foregroundSnapshot.packageName != lastForegroundPackage ||
                    foregroundSnapshot.isGame != lastForegroundGameState
                ) {
                    lastForegroundPackage = foregroundSnapshot.packageName
                    lastForegroundGameState = foregroundSnapshot.isGame
                    appContainer.logger.d(
                        TAG,
                        "前台应用变化 package=${foregroundSnapshot.packageName} hitGame=${foregroundSnapshot.isGame}",
                    )
                    requestModeEvaluation("foreground_app_changed")
                }

                delay(FOREGROUND_APP_POLL_MILLIS)
            }
        }
    }

    private fun startObservationWatchdogIfNeeded() {
        if (observationWatchdogJob?.isActive == true) {
            return
        }

        observationWatchdogJob = ensureRuntime("observation_watchdog").launch {
            while (isActive) {
                delay(OBSERVATION_WATCHDOG_INTERVAL_MILLIS)
                runCatching {
                    verifyObservationChain("watchdog_tick")
                }.onFailure { throwable ->
                    appContainer.logger.e(TAG, "watchdog 自检失败，监听链未完成校验", throwable)
                }
            }
        }
    }

    private fun syncScreenStateFromPoll(snapshot: ScreenStateSnapshot?) {
        if (snapshot == null) {
            return
        }

        val runtimeState = appContainer.appStateStore.runtimeState.value
        if (
            runtimeState.screenOn == snapshot.screenOn &&
            runtimeState.userUnlocked == snapshot.userUnlocked
        ) {
            return
        }

        appContainer.logger.d(
            TAG,
            "轮询同步屏幕状态 screenOn=${snapshot.screenOn} unlocked=${snapshot.userUnlocked}",
        )
        appContainer.appStateStore.setScreenState(
            screenOn = snapshot.screenOn,
            userUnlocked = snapshot.userUnlocked,
        )
        requestModeEvaluation("screen:poll_sync")
    }

    private fun resetForegroundAppCache() {
        lastForegroundPackage = null
        lastForegroundGameState = null
    }

    private fun requestModeEvaluation(reason: String) {
        ensureRuntime("mode_evaluation").launch {
            evaluateModeAndApply(reason)
        }
    }

    private suspend fun evaluateModeAndApply(reason: String) {
        modeMutex.withLock {
            val settings = appContainer.appPrefs.currentSettings()
            val runtimeState = appContainer.appStateStore.runtimeState.value
            val resumeRecoveryPlan = when (runtimeState.mode) {
                AutoShellMode.SUSPEND_SCREEN_OFF -> ResumeRecoveryPlan(
                    trigger = ResumeRecoveryTrigger.ScreenOff,
                    suspendedSinceMillis = runtimeState.modeChangedAtMillis,
                )

                AutoShellMode.SUSPEND_GAME -> ResumeRecoveryPlan(
                    trigger = ResumeRecoveryTrigger.Game,
                    suspendedSinceMillis = runtimeState.modeChangedAtMillis,
                )

                else -> null
            }
            val targetMode = when {
                !settings.monitoringEnabled -> AutoShellMode.USER_STOPPED
                !runtimeState.screenOn || !runtimeState.userUnlocked -> AutoShellMode.SUSPEND_SCREEN_OFF
                runtimeState.gameForeground -> AutoShellMode.SUSPEND_GAME
                else -> AutoShellMode.ACTIVE
            }

            if (runtimeState.mode != targetMode) {
                appContainer.logger.d(
                    TAG,
                    "模式切换 from=${runtimeState.mode} to=$targetMode reason=$reason screenOn=${runtimeState.screenOn} unlocked=${runtimeState.userUnlocked} package=${runtimeState.currentForegroundPackage} game=${runtimeState.gameForeground}",
                )
            }

            appContainer.appStateStore.setMode(targetMode, reason)
            when (targetMode) {
                AutoShellMode.ACTIVE -> {
                    appContainer.appStateStore.setMonitoringActive(true)
                    restoreObservationChain(reason)
                    if (resumeRecoveryPlan != null) {
                        scheduleResumeRecovery(plan = resumeRecoveryPlan, reason = reason)
                    } else {
                        scheduleStartupRecoveryIfNeeded(reason)
                    }
                }

                AutoShellMode.SUSPEND_SCREEN_OFF,
                AutoShellMode.SUSPEND_GAME,
                -> {
                    appContainer.appStateStore.setMonitoringActive(true)
                    stopObservationChain("suspend:$targetMode:$reason")
                    appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
                }

                AutoShellMode.USER_STOPPED -> {
                    appContainer.appStateStore.setMonitoringActive(false)
                    stopObservationChain("user_stopped:$reason")
                    appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.Disabled)
                }
            }
        }
    }

    private fun restoreObservationChain(reason: String) {
        registerPrimaryDirectoryWatcher(
            forceRebind = false,
            reason = reason,
        )
        registerMediaStoreFallbackIfNeeded()
        lastObservationRestoreElapsedRealtime = SystemClock.elapsedRealtime()
        val runtimeState = appContainer.appStateStore.runtimeState.value
        val phase = when {
            runtimeState.fileWatcherActive || runtimeState.mediaFallbackActive -> MonitoringPhase.Monitoring
            else -> MonitoringPhase.ObserverRegisterFailed
        }
        appContainer.appStateStore.setMonitoringPhase(phase)
        appContainer.logger.d(
            TAG,
            "监听链已恢复 reason=$reason watcher=${runtimeState.fileWatcherActive} mediaStore=${runtimeState.mediaFallbackActive}",
        )
    }

    private fun stopObservationChain(reason: String) {
        mediaStoreObserver?.let { observer ->
            runCatching { contentResolver.unregisterContentObserver(observer) }
            observer.release()
        }
        mediaStoreObserver = null

        directoryObserver?.stopAll()
        directoryObserver = null

        appContainer.appStateStore.setFileWatcherState(active = false, watchedDirectories = emptyList())
        appContainer.appStateStore.setMediaFallbackActive(false)
        appContainer.logger.d(TAG, "监听链已挂起 reason=$reason")
    }

    private fun verifyObservationChain(reason: String) {
        val runtimeState = appContainer.appStateStore.runtimeState.value
        if (runtimeState.mode != AutoShellMode.ACTIVE) {
            return
        }

        val screenshotRelativePath = currentScreenshotRelativePathBlocking()
        val screenshotDirectory = ScreenshotDirectories.screenshotDirectory(screenshotRelativePath)
        if (!screenshotDirectory.exists() || !screenshotDirectory.isDirectory) {
            appContainer.logger.d(
                TAG,
                "watchdog 跳过自检：截图目录当前不可用 path=${screenshotDirectory.absolutePath}",
            )
            registerPrimaryDirectoryWatcher(
                forceRebind = false,
                reason = "$reason:directory_unavailable",
            )
            return
        }

        val now = SystemClock.elapsedRealtime()
        val watcherMissing = directoryObserver == null ||
            observerThread?.isAlive != true ||
            observerHandler == null ||
            !runtimeState.fileWatcherActive
        val idleSinceRestore = now - lastObservationRestoreElapsedRealtime
        val idleSinceEvent = if (lastDirectoryEventElapsedRealtime > 0L) {
            now - lastDirectoryEventElapsedRealtime
        } else {
            Long.MAX_VALUE
        }
        val shouldForceRebind = watcherMissing ||
            idleSinceRestore >= OBSERVATION_FORCE_REBIND_INTERVAL_MILLIS ||
            idleSinceEvent >= OBSERVATION_FORCE_REBIND_IDLE_INTERVAL_MILLIS

        if (!shouldForceRebind) {
            return
        }

        appContainer.logger.d(
            TAG,
            "watchdog 触发监听重绑 reason=$reason watcherMissing=$watcherMissing idleSinceRestoreMs=$idleSinceRestore idleSinceEventMs=$idleSinceEvent",
        )
        registerPrimaryDirectoryWatcher(
            forceRebind = true,
            reason = "$reason:watchdog_rebind",
        )
        registerMediaStoreFallbackIfNeeded()
        lastObservationRestoreElapsedRealtime = now

        ensureRuntime("watchdog_probe").launch {
            val recentFiles = ScreenshotDirectories.recentScreenshotFiles(
                screenshotRelativePath = screenshotRelativePath,
                limit = WATCHDOG_PROBE_CANDIDATE_LIMIT,
                recentWindowMillis = WATCHDOG_PROBE_WINDOW_MILLIS,
            )
            recentFiles.forEach { file ->
                val result = appContainer.queuedTaskStore.enqueueDetected(
                    absolutePath = file.absolutePath,
                    displayName = file.name,
                    relativePath = ScreenshotDirectories.relativePathFromAbsolutePath(file.absolutePath),
                    changedUri = null,
                    source = "watchdog_probe",
                )
                if (result.accepted) {
                    appContainer.logger.d(TAG, "watchdog 探测命中并入队 path=${file.absolutePath}")
                } else {
                    appContainer.logger.d(TAG, "watchdog 探测跳过 reason=${result.reason} path=${file.absolutePath}")
                }
            }
        }
    }

    private fun registerPrimaryDirectoryWatcher(
        forceRebind: Boolean,
        reason: String,
    ) {
        val screenshotRelativePath = currentScreenshotRelativePathBlocking()
        val screenshotDirectory = ScreenshotDirectories.screenshotDirectory(screenshotRelativePath)
        val observer = directoryObserver ?: MultiDirectoryFileObserver(
            logger = appContainer.logger,
            onEvent = ::onDirectoryEvent,
        ).also { directoryObserver = it }

        val activeDirectories = observer.refreshDirectories(
            listOf(screenshotDirectory),
            forceRebind = forceRebind,
        )
        appContainer.appStateStore.setFileWatcherState(
            active = activeDirectories.isNotEmpty(),
            watchedDirectories = activeDirectories.map { it.absolutePath },
        )

        if (activeDirectories.isEmpty()) {
            appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.ObserverRegisterFailed)
            appContainer.logger.e(
                TAG,
                "固定截图目录不可用 path=${screenshotDirectory.absolutePath}",
            )
        } else {
            lastObservationRestoreElapsedRealtime = SystemClock.elapsedRealtime()
            appContainer.logger.d(
                TAG,
                "固定截图目录监听已注册 path=${activeDirectories.first().absolutePath}",
            )
        }
    }

    private fun registerMediaStoreFallbackIfNeeded() {
        val settings = runBlocking { appContainer.appPrefs.currentSettings() }
        val shouldEnableFallback = settings.mediaStoreFallbackEnabled &&
            appContainer.permissionManager.canUseMediaStoreFallback()

        if (!shouldEnableFallback) {
            appContainer.appStateStore.setMediaFallbackActive(false)
            mediaStoreObserver?.let { observer ->
                runCatching { contentResolver.unregisterContentObserver(observer) }
                observer.release()
            }
            mediaStoreObserver = null
            return
        }

        if (mediaStoreObserver != null) {
            appContainer.appStateStore.setMediaFallbackActive(true)
            return
        }

        val handler = observerHandler ?: return
        mediaStoreObserver = ScreenshotContentObserver(
            handler = handler,
            debounceMillis = CONTENT_DEBOUNCE_MILLIS,
            onContentChanged = { changedUri ->
                if (appContainer.appStateStore.runtimeState.value.mode != AutoShellMode.ACTIVE) {
                    appContainer.logger.d(TAG, "跳过媒体库兜底 reason=mode_not_active")
                    return@ScreenshotContentObserver
                }
                enqueueRecentMediaStoreCandidate("media_store_fallback", changedUri)
            },
        )

        contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            checkNotNull(mediaStoreObserver),
        )
        appContainer.appStateStore.setMediaFallbackActive(true)
        appContainer.logger.d(TAG, "媒体库兜底监听已注册")
    }

    private fun onDirectoryEvent(event: DirectoryWatchEvent) {
        if (appContainer.appStateStore.runtimeState.value.mode != AutoShellMode.ACTIVE) {
            appContainer.logger.d(
                TAG,
                "跳过文件事件 reason=mode_not_active mode=${appContainer.appStateStore.runtimeState.value.mode} path=${event.absolutePath}",
            )
            return
        }
        if (ScreenshotDirectories.isGeneratedByApp(event.fileName)) {
            appContainer.logger.d(TAG, "跳过文件事件 reason=generated_by_app path=${event.absolutePath}")
            return
        }

        appContainer.appStateStore.setMonitoringPhase(MonitoringPhase.CandidateDetected)
        lastDirectoryEventElapsedRealtime = SystemClock.elapsedRealtime()
        appContainer.logger.d(
            TAG,
            "文件事件 type=${eventMaskLabel(event.eventMask)} path=${event.absolutePath}",
        )
        ensureRuntime("directory_event").launch {
            val result = appContainer.queuedTaskStore.enqueueDetected(
                absolutePath = event.absolutePath,
                displayName = event.fileName,
                relativePath = event.relativePath,
                changedUri = null,
                source = "file_observer",
            )
            if (result.accepted) {
                appContainer.logger.d(TAG, "候选任务入队 source=file_observer path=${event.absolutePath}")
            } else {
                appContainer.logger.d(TAG, "跳过入队 reason=${result.reason} path=${event.absolutePath}")
            }
        }
    }

    private fun startQueueWorkerIfNeeded() {
        if (workerJob?.isActive == true) {
            return
        }

        workerJob = ensureRuntime("queue_worker").launch {
            while (isActive) {
                if (appContainer.appStateStore.runtimeState.value.mode != AutoShellMode.ACTIVE) {
                    delay(WORKER_SUSPENDED_DELAY_MILLIS)
                    continue
                }

                val didWork = appContainer.queuedScreenshotWorker.runNextTask()
                if (!didWork) {
                    delay(WORKER_IDLE_DELAY_MILLIS)
                }
            }
        }
    }

    private fun scheduleStartupRecoveryIfNeeded(reason: String) {
        if (startupRecoveryCompleted || startupJob?.isActive == true) {
            return
        }

        startupJob = ensureRuntime("startup_recovery").launch {
            val recovered = appContainer.queuedTaskStore.recoverAfterRestart()
            appContainer.queuedTaskStore.removeTerminalTasks()
            if (recovered.isNotEmpty()) {
                appContainer.logger.d(TAG, "队列恢复完成 count=${recovered.size} reason=$reason")
            }

            delay(STARTUP_SCAN_DELAY_MILLIS)
            if (appContainer.appStateStore.runtimeState.value.mode != AutoShellMode.ACTIVE) {
                appContainer.logger.d(TAG, "启动恢复延后 reason=mode_not_active")
                return@launch
            }

            val screenshotRelativePath = currentScreenshotRelativePath()
            val recentFiles = ScreenshotDirectories.recentScreenshotFiles(
                screenshotRelativePath = screenshotRelativePath,
                limit = STARTUP_SCAN_LIMIT,
                recentWindowMillis = STARTUP_SCAN_WINDOW_MILLIS,
            )
            if (recentFiles.isNotEmpty()) {
                appContainer.logger.d(TAG, "启动恢复命中 count=${recentFiles.size}")
            }

            recentFiles.forEach { file ->
                val result = appContainer.queuedTaskStore.enqueueDetected(
                    absolutePath = file.absolutePath,
                    displayName = file.name,
                    relativePath = ScreenshotDirectories.relativePathFromAbsolutePath(file.absolutePath),
                    changedUri = null,
                    source = "startup_scan",
                )
                if (result.accepted) {
                    appContainer.logger.d(TAG, "候选任务入队 source=startup_scan path=${file.absolutePath}")
                }
            }

            startupRecoveryCompleted = true
        }
    }

    private fun scheduleResumeRecovery(plan: ResumeRecoveryPlan, reason: String) {
        val trigger = plan.trigger
        if (resumeRecoveryJob?.isActive == true) {
            appContainer.logger.d(
                TAG,
                "重启恢复补扫 trigger=${trigger.sourceTag} reason=$reason suspendedSince=${plan.suspendedSinceMillis}",
            )
            resumeRecoveryJob?.cancel()
        }

        resumeRecoveryJob = ensureRuntime("resume_recovery").launch {
            val burstStartedAtElapsedRealtime = SystemClock.elapsedRealtime()
            val resumeStartedAtWallClockMillis = System.currentTimeMillis()
            val minLastModifiedMillis = (plan.suspendedSinceMillis - RESUME_EDGE_LOOKBACK_MILLIS).coerceAtLeast(0L)

            appContainer.logger.d(
                TAG,
                "触发 ACTIVE 恢复补扫 trigger=${trigger.sourceTag} reason=$reason suspendedSince=${plan.suspendedSinceMillis} minLastModified=$minLastModifiedMillis",
            )

            for ((index, targetOffsetMillis) in RESUME_BURST_SCHEDULE_MILLIS.withIndex()) {
                val sleepMillis = remainingDelayMillis(
                    startedAtElapsedRealtime = burstStartedAtElapsedRealtime,
                    targetOffsetMillis = targetOffsetMillis,
                )
                if (sleepMillis > 0L) {
                    delay(sleepMillis)
                }

                val runtimeState = appContainer.appStateStore.runtimeState.value
                val stopReason = when {
                    runtimeState.mode != AutoShellMode.ACTIVE -> "mode_not_active"
                    !runtimeState.screenOn -> "screen_off"
                    !runtimeState.userUnlocked -> "user_locked"
                    runtimeState.gameForeground -> "game_foreground"
                    else -> null
                }
                if (stopReason != null) {
                    appContainer.logger.d(
                        TAG,
                        "恢复补扫停止 trigger=${trigger.sourceTag} round=${index + 1} reason=$stopReason mode=${runtimeState.mode}",
                    )
                    return@launch
                }

                val roundNowMillis = System.currentTimeMillis()
                val screenshotRelativePath = currentScreenshotRelativePath()
                val recentFiles = ScreenshotDirectories.recentScreenshotFiles(
                    screenshotRelativePath = screenshotRelativePath,
                    limit = RESUME_SCAN_CANDIDATE_LIMIT,
                    recentWindowMillis = RESUME_SCAN_WINDOW_MILLIS,
                    minLastModifiedMillis = minLastModifiedMillis,
                    nowMillis = roundNowMillis,
                )
                appContainer.logger.d(
                    TAG,
                    "恢复补扫 round=${index + 1} targetOffsetMs=$targetOffsetMillis sleepMs=$sleepMillis trigger=${trigger.sourceTag} candidates=${recentFiles.size} minLastModified=$minLastModifiedMillis",
                )

                var acceptedAny = false
                var acceptedFreshEnough = false
                recentFiles.forEach { file ->
                    val result = appContainer.queuedTaskStore.enqueueDetected(
                        absolutePath = file.absolutePath,
                        displayName = file.name,
                        relativePath = ScreenshotDirectories.relativePathFromAbsolutePath(file.absolutePath),
                        changedUri = null,
                        source = "resume_scan_${trigger.sourceTag}",
                    )
                    if (result.accepted) {
                        acceptedAny = true
                        if (file.lastModified() >= resumeStartedAtWallClockMillis - RESUME_EARLY_STOP_FRESHNESS_MILLIS) {
                            acceptedFreshEnough = true
                        }
                        appContainer.logger.d(
                            TAG,
                            "恢复补扫命中 round=${index + 1} trigger=${trigger.sourceTag} path=${file.absolutePath} lastModified=${file.lastModified()} freshEnough=$acceptedFreshEnough",
                        )
                    } else {
                        appContainer.logger.d(
                            TAG,
                            "恢复补扫跳过 round=${index + 1} trigger=${trigger.sourceTag} path=${file.absolutePath} lastModified=${file.lastModified()} reason=${result.reason}",
                        )
                    }
                }

                if (acceptedAny && acceptedFreshEnough) {
                    appContainer.logger.d(
                        TAG,
                        "恢复补扫提前结束 trigger=${trigger.sourceTag} round=${index + 1} reason=fresh_candidate_enqueued",
                    )
                    return@launch
                }
            }

            appContainer.logger.d(
                TAG,
                "恢复补扫结束 trigger=${trigger.sourceTag} reason=no_candidate_enqueued",
            )
        }
    }

    private suspend fun enqueueRecentMediaStoreCandidate(reason: String, changedUri: Uri?) {
        val screenshotRelativePath = currentScreenshotRelativePath()
        val candidates = appContainer.mediaStoreRepository.queryRecentImageCandidates(
            limit = MEDIASTORE_FALLBACK_LIMIT,
            changedUri = changedUri,
        )
        val candidate = candidates.firstOrNull { screenshot ->
            ScreenshotRules.isEligibleScreenshotCandidate(
                candidate = screenshot,
                screenshotRelativePath = screenshotRelativePath,
            ) &&
                ScreenshotDirectories.isScreenshotSource(
                    screenshot.absolutePath,
                    screenshot.relativePath,
                    screenshotRelativePath,
                )
        } ?: run {
            appContainer.logger.d(TAG, "媒体库兜底未发现截图 reason=$reason uri=$changedUri")
            return
        }

        val result = appContainer.queuedTaskStore.enqueueDetected(
            absolutePath = candidate.absolutePath,
            displayName = candidate.displayName,
            relativePath = candidate.relativePath,
            changedUri = candidate.uri?.toString(),
            source = reason,
        )
        if (result.accepted) {
            appContainer.logger.d(TAG, "候选任务入队 source=$reason path=${candidate.absolutePath}")
        } else {
            appContainer.logger.d(TAG, "跳过入队 reason=${result.reason} path=${candidate.absolutePath}")
        }
    }

    private fun stopMonitoring(reason: String) {
        appContainer.logger.d(TAG, "停止监听 reason=$reason ${diagnosticTrace("stopMonitoring reason=$reason")}")
        cleanupRuntime("stop:$reason")
        if (foregroundStarted) {
            stopForeground(STOP_FOREGROUND_REMOVE)
            foregroundStarted = false
        }
        stopSelf()
    }

    private fun maybeRestartSelf(reason: String) {
        val settings = runBlocking { appContainer.appPrefs.currentSettings() }
        if (!settings.monitoringEnabled || !appContainer.permissionManager.hasCoreMonitoringPermissions()) {
            appContainer.logger.d(TAG, "跳过自恢复 reason=$reason")
            return
        }
        scheduleRestartAlarm(reason)
    }

    private fun setExplicitStopRequested(value: Boolean, reason: String) {
        if (explicitStopRequested == value) {
            return
        }
        explicitStopRequested = value
        appContainer.logger.d(
            TAG,
            "explicitStop=$value reason=$reason ${diagnosticTrace("explicitStop=$value reason=$reason")}",
        )
    }

    private fun cancelJob(job: Job?, label: String, reason: String) {
        if (job?.isActive == true) {
            appContainer.logger.d(
                TAG,
                "取消任务 label=$label reason=$reason ${diagnosticTrace("cancel $label reason=$reason")}",
            )
            job.cancel()
        }
    }

    private fun scheduleRestartAlarm(reason: String) {
        val alarmManager = getSystemService(AlarmManager::class.java)
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + SELF_RESTART_DELAY_MILLIS,
            restartPendingIntent(),
        )
        appContainer.logger.d(TAG, "已安排服务自恢复 reason=$reason delay=${SELF_RESTART_DELAY_MILLIS}ms")
    }

    private fun cancelScheduledRestart(reason: String) {
        val alarmManager = getSystemService(AlarmManager::class.java)
        alarmManager.cancel(restartPendingIntent())
        appContainer.logger.d(TAG, "已取消待执行自恢复 reason=$reason")
    }

    private fun restartPendingIntent(): PendingIntent {
        return PendingIntent.getBroadcast(
            this,
            RESTART_REQUEST_CODE,
            Intent(this, AutoShellRestartReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
    }

    private fun eventMaskLabel(mask: Int): String = buildList {
        if (mask and FileObserver.CREATE != 0) add("CREATE")
        if (mask and FileObserver.CLOSE_WRITE != 0) add("CLOSE_WRITE")
        if (mask and FileObserver.MOVED_TO != 0) add("MOVED_TO")
    }.joinToString(separator = "|").ifBlank { mask.toString() }

    private fun diagnosticTrace(reason: String): String = buildString {
        append("reason=")
        append(reason)
        append(" thread=")
        append(Thread.currentThread().name)
        append('\n')
        append(Throwable().stackTraceToString())
    }

    private fun buildNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification_shellshot)
        .setContentTitle(getString(R.string.notification_title))
        .setContentText(getString(R.string.notification_text))
        .setOnlyAlertOnce(true)
        .setOngoing(true)
        .setContentIntent(
            PendingIntent.getActivity(
                this,
                1001,
                Intent(this, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            ),
        )
        .addAction(
            0,
            getString(R.string.notification_action_stop),
            PendingIntent.getService(
                this,
                1002,
                Intent(this, AutoShellForegroundService::class.java).apply {
                    action = ACTION_STOP
                    putExtra(EXTRA_TRUSTED_STOP, true)
                },
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            ),
        )
        .build()

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        if (notificationManager.getNotificationChannel(CHANNEL_ID) != null) {
            return
        }

        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_LOW,
        ).apply {
            description = getString(R.string.notification_channel_description)
        }
        notificationManager.createNotificationChannel(channel)
    }

    private enum class ResumeRecoveryTrigger(val sourceTag: String) {
        ScreenOff("screen_off"),
        Game("game"),
    }

    private data class ResumeRecoveryPlan(
        val trigger: ResumeRecoveryTrigger,
        val suspendedSinceMillis: Long,
    )

    companion object {
        const val ACTION_START = "com.example.shellshot.action.START_AUTO_SHELL"
        const val ACTION_STOP = "com.example.shellshot.action.STOP_AUTO_SHELL"
        const val EXTRA_TRUSTED_STOP = "com.example.shellshot.extra.TRUSTED_STOP"

        private const val CHANNEL_ID = "shellshot_auto_shell"
        private const val NOTIFICATION_ID = 42001
        private const val CONTENT_DEBOUNCE_MILLIS = 450L
        private const val STARTUP_SCAN_DELAY_MILLIS = 900L
        private const val STARTUP_SCAN_WINDOW_MILLIS = 90_000L
        private const val STARTUP_SCAN_LIMIT = 5
        private const val RESUME_SCAN_WINDOW_MILLIS = 30_000L
        private const val RESUME_SCAN_CANDIDATE_LIMIT = 3
        private const val RESUME_EDGE_LOOKBACK_MILLIS = 3_000L
        private const val RESUME_EARLY_STOP_FRESHNESS_MILLIS = 1_500L
        val RESUME_BURST_SCHEDULE_MILLIS = longArrayOf(0L, 200L, 600L, 1_200L)
        private const val MEDIASTORE_FALLBACK_LIMIT = 3
        private const val OBSERVATION_WATCHDOG_INTERVAL_MILLIS = 45_000L
        private const val OBSERVATION_FORCE_REBIND_INTERVAL_MILLIS = 10 * 60_000L
        private const val OBSERVATION_FORCE_REBIND_IDLE_INTERVAL_MILLIS = 5 * 60_000L
        private const val WATCHDOG_PROBE_WINDOW_MILLIS = 90_000L
        private const val WATCHDOG_PROBE_CANDIDATE_LIMIT = 2
        private const val FOREGROUND_APP_POLL_MILLIS = 3_000L
        private const val WORKER_IDLE_DELAY_MILLIS = 350L
        private const val WORKER_SUSPENDED_DELAY_MILLIS = 1_000L
        private const val SELF_RESTART_DELAY_MILLIS = 1_200L
        private const val RESTART_REQUEST_CODE = 1003
        private const val TAG = "AutoShellService"
    }

    private fun remainingDelayMillis(
        startedAtElapsedRealtime: Long,
        targetOffsetMillis: Long,
    ): Long {
        val elapsedMillis = SystemClock.elapsedRealtime() - startedAtElapsedRealtime
        return (targetOffsetMillis - elapsedMillis).coerceAtLeast(0L)
    }

    private suspend fun currentScreenshotRelativePath(): String {
        return appContainer.appPrefs.currentSettings().screenshotRelativePath
    }

    private fun currentScreenshotRelativePathBlocking(): String {
        return runBlocking { currentScreenshotRelativePath() }
    }
}
