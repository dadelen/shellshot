package com.example.shellshot.service;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.MediaStore;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.LifecycleService;
import com.example.shellshot.MainActivity;
import com.example.shellshot.R;
import com.example.shellshot.ShellShotApplication;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.data.AppRuntimeState;
import com.example.shellshot.data.AppSettings;
import com.example.shellshot.data.AppStateStore;
import com.example.shellshot.data.MonitoringPhase;
import com.example.shellshot.media.ScreenshotDirectories;
import com.example.shellshot.observer.DirectoryWatchEvent;
import com.example.shellshot.observer.MultiDirectoryFileObserver;
import com.example.shellshot.observer.ScreenshotContentObserver;
import com.example.shellshot.receiver.AutoShellRestartReceiver;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\b\u0007\u0018\u0000 k2\u00020\u0001:\u0003ijkB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020+H\u0016J\"\u0010,\u001a\u00020-2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-H\u0016J\b\u00100\u001a\u00020+H\u0016J\u0012\u00101\u001a\u00020+2\b\u00102\u001a\u0004\u0018\u00010)H\u0016J\u0010\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020#H\u0002J\u0010\u00105\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\b\u00106\u001a\u00020+H\u0002J\b\u00107\u001a\u00020+H\u0002J\b\u00108\u001a\u00020+H\u0002J\b\u00109\u001a\u00020+H\u0002J\u0012\u0010:\u001a\u00020+2\b\u0010;\u001a\u0004\u0018\u00010<H\u0002J\b\u0010=\u001a\u00020+H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0016\u0010?\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0082@¢\u0006\u0002\u0010@J\u0010\u0010A\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0010\u0010B\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\b\u0010C\u001a\u00020+H\u0002J\b\u0010D\u001a\u00020+H\u0002J\u0010\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020GH\u0002J\b\u0010H\u001a\u00020+H\u0002J\u0010\u0010I\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0018\u0010J\u001a\u00020+2\u0006\u0010K\u001a\u00020L2\u0006\u00104\u001a\u00020#H\u0002J \u0010M\u001a\u00020+2\u0006\u00104\u001a\u00020#2\b\u0010N\u001a\u0004\u0018\u00010OH\u0082@¢\u0006\u0002\u0010PJ\u0010\u0010Q\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0010\u0010R\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0018\u0010S\u001a\u00020+2\u0006\u0010T\u001a\u00020\r2\u0006\u00104\u001a\u00020#H\u0002J\"\u0010U\u001a\u00020+2\b\u0010V\u001a\u0004\u0018\u00010\u001a2\u0006\u0010W\u001a\u00020#2\u0006\u00104\u001a\u00020#H\u0002J\u0010\u0010X\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\u0010\u0010Y\u001a\u00020+2\u0006\u00104\u001a\u00020#H\u0002J\b\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020#2\u0006\u0010]\u001a\u00020-H\u0002J\u0010\u0010^\u001a\u00020#2\u0006\u00104\u001a\u00020#H\u0002J\b\u0010_\u001a\u00020`H\u0002J\b\u0010a\u001a\u00020+H\u0002J\u0018\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020c2\u0006\u0010e\u001a\u00020cH\u0002J\u000e\u0010f\u001a\u00020#H\u0082@¢\u0006\u0002\u0010gJ\b\u0010h\u001a\u00020#H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010%¨\u0006l"}, d2 = {"Lcom/example/shellshot/service/AutoShellForegroundService;", "Landroidx/lifecycle/LifecycleService;", "<init>", "()V", "appContainer", "Lcom/example/shellshot/data/AppContainer;", "getAppContainer", "()Lcom/example/shellshot/data/AppContainer;", "appContainer$delegate", "Lkotlin/Lazy;", "modeMutex", "Lkotlinx/coroutines/sync/Mutex;", "explicitStopRequested", "", "foregroundStarted", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "observerThread", "Landroid/os/HandlerThread;", "observerHandler", "Landroid/os/Handler;", "directoryObserver", "Lcom/example/shellshot/observer/MultiDirectoryFileObserver;", "mediaStoreObserver", "Lcom/example/shellshot/observer/ScreenshotContentObserver;", "workerJob", "Lkotlinx/coroutines/Job;", "startupJob", "resumeRecoveryJob", "foregroundAppMonitorJob", "screenStateMonitor", "Lcom/example/shellshot/service/ScreenStateMonitor;", "startupRecoveryCompleted", "usageAccessWarningLogged", "lastForegroundPackage", "", "lastForegroundGameState", "Ljava/lang/Boolean;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onStartCommand", "", "flags", "startId", "onDestroy", "onTaskRemoved", "rootIntent", "ensureRuntime", "reason", "cleanupRuntime", "ensureForeground", "startEnvironmentMonitoring", "stopEnvironmentMonitoring", "startForegroundAppMonitorIfNeeded", "syncScreenStateFromPoll", "snapshot", "Lcom/example/shellshot/service/ScreenStateSnapshot;", "resetForegroundAppCache", "requestModeEvaluation", "evaluateModeAndApply", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreObservationChain", "stopObservationChain", "registerPrimaryDirectoryWatcher", "registerMediaStoreFallbackIfNeeded", "onDirectoryEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/example/shellshot/observer/DirectoryWatchEvent;", "startQueueWorkerIfNeeded", "scheduleStartupRecoveryIfNeeded", "scheduleResumeRecovery", "plan", "Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryPlan;", "enqueueRecentMediaStoreCandidate", "changedUri", "Landroid/net/Uri;", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopMonitoring", "maybeRestartSelf", "setExplicitStopRequested", "value", "cancelJob", "job", "label", "scheduleRestartAlarm", "cancelScheduledRestart", "restartPendingIntent", "Landroid/app/PendingIntent;", "eventMaskLabel", "mask", "diagnosticTrace", "buildNotification", "Landroid/app/Notification;", "createNotificationChannel", "remainingDelayMillis", "", "startedAtElapsedRealtime", "targetOffsetMillis", "currentScreenshotRelativePath", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentScreenshotRelativePathBlocking", "ResumeRecoveryTrigger", "ResumeRecoveryPlan", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AutoShellForegroundService extends LifecycleService {
    public static final String ACTION_START = "com.example.shellshot.action.START_AUTO_SHELL";
    public static final String ACTION_STOP = "com.example.shellshot.action.STOP_AUTO_SHELL";
    private static final String CHANNEL_ID = "shellshot_auto_shell";
    private static final long CONTENT_DEBOUNCE_MILLIS = 450;
    public static final String EXTRA_TRUSTED_STOP = "com.example.shellshot.extra.TRUSTED_STOP";
    private static final long FOREGROUND_APP_POLL_MILLIS = 3000;
    private static final int MEDIASTORE_FALLBACK_LIMIT = 3;
    private static final int NOTIFICATION_ID = 42001;
    private static final int RESTART_REQUEST_CODE = 1003;
    private static final long RESUME_EARLY_STOP_FRESHNESS_MILLIS = 1500;
    private static final long RESUME_EDGE_LOOKBACK_MILLIS = 3000;
    private static final int RESUME_SCAN_CANDIDATE_LIMIT = 3;
    private static final long RESUME_SCAN_WINDOW_MILLIS = 30000;
    private static final long STARTUP_SCAN_DELAY_MILLIS = 900;
    private static final int STARTUP_SCAN_LIMIT = 5;
    private static final long STARTUP_SCAN_WINDOW_MILLIS = 90000;
    private static final String TAG = "AutoShellService";
    private static final long WORKER_IDLE_DELAY_MILLIS = 350;
    private static final long WORKER_SUSPENDED_DELAY_MILLIS = 1000;
    private MultiDirectoryFileObserver directoryObserver;
    private boolean explicitStopRequested;
    private Job foregroundAppMonitorJob;
    private boolean foregroundStarted;
    private Boolean lastForegroundGameState;
    private String lastForegroundPackage;
    private ScreenshotContentObserver mediaStoreObserver;
    private Handler observerHandler;
    private HandlerThread observerThread;
    private Job resumeRecoveryJob;
    private ScreenStateMonitor screenStateMonitor;
    private CoroutineScope serviceScope;
    private Job startupJob;
    private boolean startupRecoveryCompleted;
    private boolean usageAccessWarningLogged;
    private Job workerJob;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final long SELF_RESTART_DELAY_MILLIS = 1200;
    private static final long[] RESUME_BURST_SCHEDULE_MILLIS = {0, 200, 600, SELF_RESTART_DELAY_MILLIS};

    /* renamed from: appContainer$delegate, reason: from kotlin metadata */
    private final Lazy appContainer = LazyKt.lazy(new Function0() { // from class: com.example.shellshot.service.AutoShellForegroundService$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AutoShellForegroundService.appContainer_delegate$lambda$0(AutoShellForegroundService.this);
        }
    });
    private final Mutex modeMutex = MutexKt.Mutex$default(false, 1, null);

    /* compiled from: AutoShellForegroundService.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutoShellMode.values().length];
            try {
                iArr[AutoShellMode.SUSPEND_SCREEN_OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AutoShellMode.SUSPEND_GAME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AutoShellMode.ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AutoShellMode.USER_STOPPED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AppContainer getAppContainer() {
        return (AppContainer) this.appContainer.getValue();
    }

    static final AppContainer appContainer_delegate$lambda$0(AutoShellForegroundService this$0) {
        Application application = this$0.getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.example.shellshot.ShellShotApplication");
        return ((ShellShotApplication) application).getAppContainer();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        return null;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        ensureRuntime("onCreate");
        getAppContainer().getLogger().d(TAG, "服务已创建 " + diagnosticTrace("onCreate"));
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Object runBlocking$default;
        String action = intent != null ? intent.getAction() : null;
        if (action == null) {
            action = "";
        }
        getAppContainer().getLogger().d(TAG, "服务收到启动指令 action=" + action + " startId=" + startId + " " + diagnosticTrace("onStartCommand action=" + action));
        cancelScheduledRestart("onStartCommand");
        if (!Intrinsics.areEqual(action, ACTION_STOP)) {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new AutoShellForegroundService$onStartCommand$settings$1(this, null), 1, null);
            AppSettings settings = (AppSettings) runBlocking$default;
            if (!settings.getMonitoringEnabled() || !getAppContainer().getPermissionManager().hasCoreMonitoringPermissions()) {
                getAppContainer().getLogger().d(TAG, "无法启动后台监听：缺少前置条件");
                getAppContainer().getAppStateStore().setMode(AutoShellMode.USER_STOPPED, "缺少前置条件");
                stopMonitoring("missing_prerequisites");
                return 2;
            }
            setExplicitStopRequested(false, "服务启动");
            ensureForeground();
            ensureRuntime("start_command");
            getAppContainer().getAppStateStore().setMonitoringActive(true);
            getAppContainer().getAppStateStore().setMonitoringPhase(MonitoringPhase.Starting);
            startEnvironmentMonitoring();
            startQueueWorkerIfNeeded();
            requestModeEvaluation("start_command");
            return 1;
        }
        boolean trustedStop = intent != null ? intent.getBooleanExtra(EXTRA_TRUSTED_STOP, false) : false;
        if (!trustedStop) {
            getAppContainer().getLogger().d(TAG, "忽略未标记的停止指令");
            return 1;
        }
        setExplicitStopRequested(true, "用户主动点击停止监听");
        cancelScheduledRestart("user_stop");
        BuildersKt__BuildersKt.runBlocking$default(null, new AutoShellForegroundService$onStartCommand$1(this, null), 1, null);
        getAppContainer().getAppStateStore().setMode(AutoShellMode.USER_STOPPED, "用户主动停止监听");
        stopMonitoring("user_stop");
        return 2;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        getAppContainer().getLogger().d(TAG, "服务已销毁 explicitStop=" + this.explicitStopRequested + " " + diagnosticTrace("onDestroy explicitStop=" + this.explicitStopRequested));
        cleanupRuntime("onDestroy");
        if (!this.explicitStopRequested) {
            maybeRestartSelf("onDestroy");
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        getAppContainer().getLogger().d(TAG, "服务任务被系统移除 explicitStop=" + this.explicitStopRequested + " " + diagnosticTrace("onTaskRemoved explicitStop=" + this.explicitStopRequested));
        if (!this.explicitStopRequested) {
            maybeRestartSelf("onTaskRemoved");
        }
        super.onTaskRemoved(rootIntent);
    }

    private final CoroutineScope ensureRuntime(String reason) {
        CoroutineScope currentScope = this.serviceScope;
        boolean z = false;
        if (currentScope != null && CoroutineScopeKt.isActive(currentScope)) {
            HandlerThread handlerThread = this.observerThread;
            if (handlerThread != null && handlerThread.isAlive()) {
                z = true;
            }
            if (z && this.observerHandler != null) {
                return currentScope;
            }
        }
        if (currentScope != null) {
            getAppContainer().getLogger().d(TAG, "重建服务运行时 reason=" + reason);
            CoroutineScopeKt.cancel$default(currentScope, null, 1, null);
        }
        HandlerThread handlerThread2 = this.observerThread;
        if (handlerThread2 != null) {
            handlerThread2.quitSafely();
        }
        HandlerThread handlerThread3 = new HandlerThread("AutoShellWorker");
        handlerThread3.start();
        this.observerThread = handlerThread3;
        HandlerThread handlerThread4 = this.observerThread;
        if (handlerThread4 != null) {
            this.observerHandler = new Handler(handlerThread4.getLooper());
            this.serviceScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
            CoroutineScope coroutineScope = this.serviceScope;
            if (coroutineScope != null) {
                return coroutineScope;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final void cleanupRuntime(String reason) {
        cancelJob(this.startupJob, "startupJob", reason);
        this.startupJob = null;
        cancelJob(this.resumeRecoveryJob, "resumeRecoveryJob", reason);
        this.resumeRecoveryJob = null;
        cancelJob(this.workerJob, "workerJob", reason);
        this.workerJob = null;
        cancelJob(this.foregroundAppMonitorJob, "foregroundAppMonitorJob", reason);
        this.foregroundAppMonitorJob = null;
        stopObservationChain("cleanup:" + reason);
        stopEnvironmentMonitoring();
        getAppContainer().getAppStateStore().setMonitoringActive(false);
        getAppContainer().getAppStateStore().setMonitoringPhase(MonitoringPhase.Disabled);
        if (this.explicitStopRequested) {
            getAppContainer().getAppStateStore().setMode(AutoShellMode.USER_STOPPED, "用户主动停止监听");
        }
        CoroutineScope coroutineScope = this.serviceScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.serviceScope = null;
        this.observerHandler = null;
        HandlerThread handlerThread = this.observerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.observerThread = null;
    }

    private final void ensureForeground() {
        if (this.foregroundStarted) {
            return;
        }
        startForeground(NOTIFICATION_ID, buildNotification());
        this.foregroundStarted = true;
    }

    private final void startEnvironmentMonitoring() {
        ScreenStateMonitor monitor = this.screenStateMonitor;
        if (monitor == null) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            monitor = new ScreenStateMonitor(applicationContext, getAppContainer().getLogger(), new Function2() { // from class: com.example.shellshot.service.AutoShellForegroundService$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AutoShellForegroundService.startEnvironmentMonitoring$lambda$2(AutoShellForegroundService.this, (String) obj, (ScreenStateSnapshot) obj2);
                }
            });
            this.screenStateMonitor = monitor;
        }
        monitor.start();
        startForegroundAppMonitorIfNeeded();
    }

    static final Unit startEnvironmentMonitoring$lambda$2(AutoShellForegroundService this$0, String reason, ScreenStateSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this$0.getAppContainer().getAppStateStore().setScreenState(snapshot.getScreenOn(), snapshot.getUserUnlocked());
        this$0.requestModeEvaluation("screen:" + reason);
        return Unit.INSTANCE;
    }

    private final void stopEnvironmentMonitoring() {
        ScreenStateMonitor screenStateMonitor = this.screenStateMonitor;
        if (screenStateMonitor != null) {
            screenStateMonitor.stop();
        }
        this.screenStateMonitor = null;
    }

    private final void startForegroundAppMonitorIfNeeded() {
        Job launch$default;
        Job job = this.foregroundAppMonitorJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (!z) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(ensureRuntime("foreground_app_monitor"), null, null, new AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1(this, null), 3, null);
            this.foregroundAppMonitorJob = launch$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncScreenStateFromPoll(ScreenStateSnapshot snapshot) {
        if (snapshot == null) {
            return;
        }
        AppRuntimeState runtimeState = getAppContainer().getAppStateStore().getRuntimeState().getValue();
        if (runtimeState.getScreenOn() == snapshot.getScreenOn() && runtimeState.getUserUnlocked() == snapshot.getUserUnlocked()) {
            return;
        }
        getAppContainer().getLogger().d(TAG, "轮询同步屏幕状态 screenOn=" + snapshot.getScreenOn() + " unlocked=" + snapshot.getUserUnlocked());
        getAppContainer().getAppStateStore().setScreenState(snapshot.getScreenOn(), snapshot.getUserUnlocked());
        requestModeEvaluation("screen:poll_sync");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetForegroundAppCache() {
        this.lastForegroundPackage = null;
        this.lastForegroundGameState = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestModeEvaluation(String reason) {
        BuildersKt__Builders_commonKt.launch$default(ensureRuntime("mode_evaluation"), null, null, new AutoShellForegroundService$requestModeEvaluation$1(this, reason, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b9 A[Catch: all -> 0x0044, TRY_ENTER, TryCatch #1 {all -> 0x0044, blocks: (B:13:0x003f, B:18:0x00b9, B:21:0x00da, B:62:0x00e3, B:65:0x00ea, B:67:0x00f0, B:68:0x00f3, B:70:0x00c5), top: B:12:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00da A[Catch: all -> 0x0044, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0044, blocks: (B:13:0x003f, B:18:0x00b9, B:21:0x00da, B:62:0x00e3, B:65:0x00ea, B:67:0x00f0, B:68:0x00f3, B:70:0x00c5), top: B:12:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ff A[Catch: all -> 0x0240, TRY_LEAVE, TryCatch #0 {all -> 0x0240, blocks: (B:16:0x0095, B:19:0x00d4, B:22:0x00f8, B:24:0x00ff, B:60:0x00dd, B:69:0x00f6), top: B:15:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01a6 A[Catch: all -> 0x023c, TryCatch #4 {all -> 0x023c, blocks: (B:33:0x0125, B:34:0x0190, B:35:0x01a3, B:36:0x01a6, B:37:0x0238, B:38:0x023b, B:39:0x01aa, B:40:0x022c, B:43:0x01da, B:45:0x01eb, B:46:0x01ef, B:47:0x01f3), top: B:32:0x0125 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01aa A[Catch: all -> 0x023c, TryCatch #4 {all -> 0x023c, blocks: (B:33:0x0125, B:34:0x0190, B:35:0x01a3, B:36:0x01a6, B:37:0x0238, B:38:0x023b, B:39:0x01aa, B:40:0x022c, B:43:0x01da, B:45:0x01eb, B:46:0x01ef, B:47:0x01f3), top: B:32:0x0125 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01da A[Catch: all -> 0x023c, TryCatch #4 {all -> 0x023c, blocks: (B:33:0x0125, B:34:0x0190, B:35:0x01a3, B:36:0x01a6, B:37:0x0238, B:38:0x023b, B:39:0x01aa, B:40:0x022c, B:43:0x01da, B:45:0x01eb, B:46:0x01ef, B:47:0x01f3), top: B:32:0x0125 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01f3 A[Catch: all -> 0x023c, TryCatch #4 {all -> 0x023c, blocks: (B:33:0x0125, B:34:0x0190, B:35:0x01a3, B:36:0x01a6, B:37:0x0238, B:38:0x023b, B:39:0x01aa, B:40:0x022c, B:43:0x01da, B:45:0x01eb, B:46:0x01ef, B:47:0x01f3), top: B:32:0x0125 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00dd A[Catch: all -> 0x0240, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0240, blocks: (B:16:0x0095, B:19:0x00d4, B:22:0x00f8, B:24:0x00ff, B:60:0x00dd, B:69:0x00f6), top: B:15:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c5 A[Catch: all -> 0x0044, TRY_LEAVE, TryCatch #1 {all -> 0x0044, blocks: (B:13:0x003f, B:18:0x00b9, B:21:0x00da, B:62:0x00e3, B:65:0x00ea, B:67:0x00f0, B:68:0x00f3, B:70:0x00c5), top: B:12:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object evaluateModeAndApply(java.lang.String r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService.evaluateModeAndApply(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void restoreObservationChain(String reason) {
        MonitoringPhase phase;
        registerPrimaryDirectoryWatcher();
        registerMediaStoreFallbackIfNeeded();
        AppRuntimeState runtimeState = getAppContainer().getAppStateStore().getRuntimeState().getValue();
        if (runtimeState.getFileWatcherActive() || runtimeState.getMediaFallbackActive()) {
            phase = MonitoringPhase.Monitoring;
        } else {
            phase = MonitoringPhase.ObserverRegisterFailed;
        }
        getAppContainer().getAppStateStore().setMonitoringPhase(phase);
        getAppContainer().getLogger().d(TAG, "监听链已恢复 reason=" + reason + " watcher=" + runtimeState.getFileWatcherActive() + " mediaStore=" + runtimeState.getMediaFallbackActive());
    }

    private final void stopObservationChain(String reason) {
        ScreenshotContentObserver screenshotContentObserver = this.mediaStoreObserver;
        if (screenshotContentObserver != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                getContentResolver().unregisterContentObserver(screenshotContentObserver);
                Result.m7216constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7216constructorimpl(ResultKt.createFailure(th));
            }
            screenshotContentObserver.release();
        }
        this.mediaStoreObserver = null;
        MultiDirectoryFileObserver multiDirectoryFileObserver = this.directoryObserver;
        if (multiDirectoryFileObserver != null) {
            multiDirectoryFileObserver.stopAll();
        }
        this.directoryObserver = null;
        getAppContainer().getAppStateStore().setFileWatcherState(false, CollectionsKt.emptyList());
        getAppContainer().getAppStateStore().setMediaFallbackActive(false);
        getAppContainer().getLogger().d(TAG, "监听链已挂起 reason=" + reason);
    }

    private final void registerPrimaryDirectoryWatcher() {
        String screenshotRelativePath = currentScreenshotRelativePathBlocking();
        File screenshotDirectory = ScreenshotDirectories.INSTANCE.screenshotDirectory(screenshotRelativePath);
        MultiDirectoryFileObserver observer = this.directoryObserver;
        if (observer == null) {
            observer = new MultiDirectoryFileObserver(getAppContainer().getLogger(), new AutoShellForegroundService$registerPrimaryDirectoryWatcher$observer$1(this));
            this.directoryObserver = observer;
        }
        List activeDirectories = observer.refreshDirectories(CollectionsKt.listOf(screenshotDirectory));
        AppStateStore appStateStore = getAppContainer().getAppStateStore();
        boolean z = !activeDirectories.isEmpty();
        List list = activeDirectories;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((File) it.next()).getAbsolutePath());
        }
        appStateStore.setFileWatcherState(z, (List) arrayList);
        if (activeDirectories.isEmpty()) {
            getAppContainer().getAppStateStore().setMonitoringPhase(MonitoringPhase.ObserverRegisterFailed);
            ShellLogger.e$default(getAppContainer().getLogger(), TAG, "固定截图目录不可用 path=" + screenshotDirectory.getAbsolutePath(), null, 4, null);
            return;
        }
        getAppContainer().getLogger().d(TAG, "固定截图目录监听已注册 path=" + ((File) CollectionsKt.first(activeDirectories)).getAbsolutePath());
    }

    private final void registerMediaStoreFallbackIfNeeded() {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$settings$1(this, null), 1, null);
        AppSettings settings = (AppSettings) runBlocking$default;
        boolean shouldEnableFallback = settings.getMediaStoreFallbackEnabled() && getAppContainer().getPermissionManager().canUseMediaStoreFallback();
        if (!shouldEnableFallback) {
            getAppContainer().getAppStateStore().setMediaFallbackActive(false);
            ScreenshotContentObserver screenshotContentObserver = this.mediaStoreObserver;
            if (screenshotContentObserver != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    getContentResolver().unregisterContentObserver(screenshotContentObserver);
                    Result.m7216constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m7216constructorimpl(ResultKt.createFailure(th));
                }
                screenshotContentObserver.release();
            }
            this.mediaStoreObserver = null;
            return;
        }
        if (this.mediaStoreObserver != null) {
            getAppContainer().getAppStateStore().setMediaFallbackActive(true);
            return;
        }
        Handler handler = this.observerHandler;
        if (handler == null) {
            return;
        }
        this.mediaStoreObserver = new ScreenshotContentObserver(handler, CONTENT_DEBOUNCE_MILLIS, new AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2(this, null), null, 8, null);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ScreenshotContentObserver screenshotContentObserver2 = this.mediaStoreObserver;
        if (screenshotContentObserver2 != null) {
            contentResolver.registerContentObserver(uri, true, screenshotContentObserver2);
            getAppContainer().getAppStateStore().setMediaFallbackActive(true);
            getAppContainer().getLogger().d(TAG, "媒体库兜底监听已注册");
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDirectoryEvent(DirectoryWatchEvent event) {
        if (getAppContainer().getAppStateStore().getRuntimeState().getValue().getMode() != AutoShellMode.ACTIVE) {
            getAppContainer().getLogger().d(TAG, "跳过文件事件 reason=mode_not_active mode=" + getAppContainer().getAppStateStore().getRuntimeState().getValue().getMode() + " path=" + event.getAbsolutePath());
            return;
        }
        if (ScreenshotDirectories.INSTANCE.isGeneratedByApp(event.getFileName())) {
            getAppContainer().getLogger().d(TAG, "跳过文件事件 reason=generated_by_app path=" + event.getAbsolutePath());
        } else {
            getAppContainer().getAppStateStore().setMonitoringPhase(MonitoringPhase.CandidateDetected);
            getAppContainer().getLogger().d(TAG, "文件事件 type=" + eventMaskLabel(event.getEventMask()) + " path=" + event.getAbsolutePath());
            BuildersKt__Builders_commonKt.launch$default(ensureRuntime("directory_event"), null, null, new AutoShellForegroundService$onDirectoryEvent$1(this, event, null), 3, null);
        }
    }

    private final void startQueueWorkerIfNeeded() {
        Job launch$default;
        Job job = this.workerJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (!z) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(ensureRuntime("queue_worker"), null, null, new AutoShellForegroundService$startQueueWorkerIfNeeded$1(this, null), 3, null);
            this.workerJob = launch$default;
        }
    }

    private final void scheduleStartupRecoveryIfNeeded(String reason) {
        Job launch$default;
        if (this.startupRecoveryCompleted) {
            return;
        }
        Job job = this.startupJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (!z) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(ensureRuntime("startup_recovery"), null, null, new AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1(this, reason, null), 3, null);
            this.startupJob = launch$default;
        }
    }

    private final void scheduleResumeRecovery(ResumeRecoveryPlan plan, String reason) {
        Job launch$default;
        ResumeRecoveryTrigger trigger = plan.getTrigger();
        Job job = this.resumeRecoveryJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            getAppContainer().getLogger().d(TAG, "重启恢复补扫 trigger=" + trigger.getSourceTag() + " reason=" + reason + " suspendedSince=" + plan.getSuspendedSinceMillis());
            Job job2 = this.resumeRecoveryJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ensureRuntime("resume_recovery"), null, null, new AutoShellForegroundService$scheduleResumeRecovery$1(plan, this, trigger, reason, null), 3, null);
        this.resumeRecoveryJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enqueueRecentMediaStoreCandidate(java.lang.String r27, android.net.Uri r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            Method dump skipped, instructions count: 492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService.enqueueRecentMediaStoreCandidate(java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void stopMonitoring(String reason) {
        getAppContainer().getLogger().d(TAG, "停止监听 reason=" + reason + " " + diagnosticTrace("stopMonitoring reason=" + reason));
        cleanupRuntime("stop:" + reason);
        if (this.foregroundStarted) {
            stopForeground(1);
            this.foregroundStarted = false;
        }
        stopSelf();
    }

    private final void maybeRestartSelf(String reason) {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new AutoShellForegroundService$maybeRestartSelf$settings$1(this, null), 1, null);
        AppSettings settings = (AppSettings) runBlocking$default;
        if (!settings.getMonitoringEnabled() || !getAppContainer().getPermissionManager().hasCoreMonitoringPermissions()) {
            getAppContainer().getLogger().d(TAG, "跳过自恢复 reason=" + reason);
        } else {
            scheduleRestartAlarm(reason);
        }
    }

    private final void setExplicitStopRequested(boolean value, String reason) {
        if (this.explicitStopRequested == value) {
            return;
        }
        this.explicitStopRequested = value;
        getAppContainer().getLogger().d(TAG, "explicitStop=" + value + " reason=" + reason + " " + diagnosticTrace("explicitStop=" + value + " reason=" + reason));
    }

    private final void cancelJob(Job job, String label, String reason) {
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            getAppContainer().getLogger().d(TAG, "取消任务 label=" + label + " reason=" + reason + " " + diagnosticTrace("cancel " + label + " reason=" + reason));
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void scheduleRestartAlarm(String reason) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(AlarmManager.class);
        alarmManager.setAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + SELF_RESTART_DELAY_MILLIS, restartPendingIntent());
        getAppContainer().getLogger().d(TAG, "已安排服务自恢复 reason=" + reason + " delay=1200ms");
    }

    private final void cancelScheduledRestart(String reason) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(AlarmManager.class);
        alarmManager.cancel(restartPendingIntent());
        getAppContainer().getLogger().d(TAG, "已取消待执行自恢复 reason=" + reason);
    }

    private final PendingIntent restartPendingIntent() {
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 1003, new Intent(this, (Class<?>) AutoShellRestartReceiver.class), 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final String eventMaskLabel(int mask) {
        List createListBuilder = CollectionsKt.createListBuilder();
        if ((mask & 256) != 0) {
            createListBuilder.add("CREATE");
        }
        if ((mask & 8) != 0) {
            createListBuilder.add("CLOSE_WRITE");
        }
        if ((mask & 128) != 0) {
            createListBuilder.add("MOVED_TO");
        }
        String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.build(createListBuilder), "|", null, null, 0, null, null, 62, null);
        if (StringsKt.isBlank(joinToString$default)) {
            joinToString$default = String.valueOf(mask);
        }
        return joinToString$default;
    }

    private final String diagnosticTrace(String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("reason=");
        sb.append(reason);
        sb.append(" thread=");
        sb.append(Thread.currentThread().getName());
        sb.append('\n');
        sb.append(ExceptionsKt.stackTraceToString(new Throwable()));
        return sb.toString();
    }

    private final Notification buildNotification() {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_notification_shellshot).setContentTitle(getString(R.string.notification_title)).setContentText(getString(R.string.notification_text)).setOnlyAlertOnce(true).setOngoing(true).setContentIntent(PendingIntent.getActivity(this, PointerIconCompat.TYPE_CONTEXT_MENU, new Intent(this, (Class<?>) MainActivity.class), 201326592));
        String string = getString(R.string.notification_action_stop);
        Intent intent = new Intent(this, (Class<?>) AutoShellForegroundService.class);
        intent.setAction(ACTION_STOP);
        intent.putExtra(EXTRA_TRUSTED_STOP, true);
        Unit unit = Unit.INSTANCE;
        Notification build = contentIntent.addAction(0, string, PendingIntent.getService(this, PointerIconCompat.TYPE_HAND, intent, 201326592)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager.getNotificationChannel(CHANNEL_ID) != null) {
            return;
        }
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, getString(R.string.notification_channel_name), 2);
        channel.setDescription(getString(R.string.notification_channel_description));
        notificationManager.createNotificationChannel(channel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AutoShellForegroundService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryTrigger;", "", "sourceTag", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getSourceTag", "()Ljava/lang/String;", "ScreenOff", "Game", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    enum ResumeRecoveryTrigger {
        ScreenOff("screen_off"),
        Game("game");

        private final String sourceTag;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ResumeRecoveryTrigger> getEntries() {
            return $ENTRIES;
        }

        ResumeRecoveryTrigger(String sourceTag) {
            this.sourceTag = sourceTag;
        }

        public final String getSourceTag() {
            return this.sourceTag;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AutoShellForegroundService.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryPlan;", "", "trigger", "Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryTrigger;", "suspendedSinceMillis", "", "<init>", "(Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryTrigger;J)V", "getTrigger", "()Lcom/example/shellshot/service/AutoShellForegroundService$ResumeRecoveryTrigger;", "getSuspendedSinceMillis", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class ResumeRecoveryPlan {
        private final long suspendedSinceMillis;
        private final ResumeRecoveryTrigger trigger;

        public static /* synthetic */ ResumeRecoveryPlan copy$default(ResumeRecoveryPlan resumeRecoveryPlan, ResumeRecoveryTrigger resumeRecoveryTrigger, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                resumeRecoveryTrigger = resumeRecoveryPlan.trigger;
            }
            if ((i & 2) != 0) {
                j = resumeRecoveryPlan.suspendedSinceMillis;
            }
            return resumeRecoveryPlan.copy(resumeRecoveryTrigger, j);
        }

        /* renamed from: component1, reason: from getter */
        public final ResumeRecoveryTrigger getTrigger() {
            return this.trigger;
        }

        /* renamed from: component2, reason: from getter */
        public final long getSuspendedSinceMillis() {
            return this.suspendedSinceMillis;
        }

        public final ResumeRecoveryPlan copy(ResumeRecoveryTrigger trigger, long suspendedSinceMillis) {
            Intrinsics.checkNotNullParameter(trigger, "trigger");
            return new ResumeRecoveryPlan(trigger, suspendedSinceMillis);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResumeRecoveryPlan)) {
                return false;
            }
            ResumeRecoveryPlan resumeRecoveryPlan = (ResumeRecoveryPlan) other;
            return this.trigger == resumeRecoveryPlan.trigger && this.suspendedSinceMillis == resumeRecoveryPlan.suspendedSinceMillis;
        }

        public int hashCode() {
            return (this.trigger.hashCode() * 31) + Long.hashCode(this.suspendedSinceMillis);
        }

        public String toString() {
            return "ResumeRecoveryPlan(trigger=" + this.trigger + ", suspendedSinceMillis=" + this.suspendedSinceMillis + ")";
        }

        public ResumeRecoveryPlan(ResumeRecoveryTrigger trigger, long suspendedSinceMillis) {
            Intrinsics.checkNotNullParameter(trigger, "trigger");
            this.trigger = trigger;
            this.suspendedSinceMillis = suspendedSinceMillis;
        }

        public final ResumeRecoveryTrigger getTrigger() {
            return this.trigger;
        }

        public final long getSuspendedSinceMillis() {
            return this.suspendedSinceMillis;
        }
    }

    /* compiled from: AutoShellForegroundService.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0016\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/example/shellshot/service/AutoShellForegroundService$Companion;", "", "<init>", "()V", "ACTION_START", "", "ACTION_STOP", "EXTRA_TRUSTED_STOP", "CHANNEL_ID", "NOTIFICATION_ID", "", "CONTENT_DEBOUNCE_MILLIS", "", "STARTUP_SCAN_DELAY_MILLIS", "STARTUP_SCAN_WINDOW_MILLIS", "STARTUP_SCAN_LIMIT", "RESUME_SCAN_WINDOW_MILLIS", "RESUME_SCAN_CANDIDATE_LIMIT", "RESUME_EDGE_LOOKBACK_MILLIS", "RESUME_EARLY_STOP_FRESHNESS_MILLIS", "RESUME_BURST_SCHEDULE_MILLIS", "", "getRESUME_BURST_SCHEDULE_MILLIS", "()[J", "MEDIASTORE_FALLBACK_LIMIT", "FOREGROUND_APP_POLL_MILLIS", "WORKER_IDLE_DELAY_MILLIS", "WORKER_SUSPENDED_DELAY_MILLIS", "SELF_RESTART_DELAY_MILLIS", "RESTART_REQUEST_CODE", "TAG", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long[] getRESUME_BURST_SCHEDULE_MILLIS() {
            return AutoShellForegroundService.RESUME_BURST_SCHEDULE_MILLIS;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long remainingDelayMillis(long startedAtElapsedRealtime, long targetOffsetMillis) {
        long elapsedMillis = SystemClock.elapsedRealtime() - startedAtElapsedRealtime;
        return RangesKt.coerceAtLeast(targetOffsetMillis - elapsedMillis, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object currentScreenshotRelativePath(kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.example.shellshot.service.AutoShellForegroundService$currentScreenshotRelativePath$1
            if (r0 == 0) goto L14
            r0 = r6
            com.example.shellshot.service.AutoShellForegroundService$currentScreenshotRelativePath$1 r0 = (com.example.shellshot.service.AutoShellForegroundService$currentScreenshotRelativePath$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.service.AutoShellForegroundService$currentScreenshotRelativePath$1 r0 = new com.example.shellshot.service.AutoShellForegroundService$currentScreenshotRelativePath$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L46
        L31:
            kotlin.ResultKt.throwOnFailure(r1)
            com.example.shellshot.data.AppContainer r3 = r5.getAppContainer()
            com.example.shellshot.data.AppPrefs r3 = r3.getAppPrefs()
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.currentSettings(r0)
            if (r3 != r2) goto L46
            return r2
        L46:
            com.example.shellshot.data.AppSettings r3 = (com.example.shellshot.data.AppSettings) r3
            java.lang.String r2 = r3.getScreenshotRelativePath()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService.currentScreenshotRelativePath(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String currentScreenshotRelativePathBlocking() {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new AutoShellForegroundService$currentScreenshotRelativePathBlocking$1(this, null), 1, null);
        return (String) runBlocking$default;
    }
}
