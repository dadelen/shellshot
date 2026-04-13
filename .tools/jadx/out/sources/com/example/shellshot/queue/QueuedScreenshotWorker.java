package com.example.shellshot.queue;

import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.example.shellshot.data.AppPrefs;
import com.example.shellshot.data.AppStateStore;
import com.example.shellshot.data.MonitoringPhase;
import com.example.shellshot.data.ProcessingResult;
import com.example.shellshot.data.ProcessingStatus;
import com.example.shellshot.media.BitmapLoader;
import com.example.shellshot.media.DeleteResult;
import com.example.shellshot.media.LatestScreenshotResolver;
import com.example.shellshot.media.OutputRepository;
import com.example.shellshot.media.ResolutionSource;
import com.example.shellshot.media.ResolveQueuedTaskResult;
import com.example.shellshot.media.ScreenshotCandidate;
import com.example.shellshot.media.ScreenshotDirectories;
import com.example.shellshot.media.ScreenshotStabilityChecker;
import com.example.shellshot.processor.ShellComposeEngine;
import com.example.shellshot.service.AutoShellMode;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateRepository;
import com.example.shellshot.utils.ShellLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0007\u0018\u0000 j2\u00020\u0001:\u0005fghijBW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000e\u0010\u001a\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010 J\u0016\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010 J\u0016\u0010$\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010 J \u0010%\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020)H\u0002J \u0010*\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J&\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020-2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0082@¢\u0006\u0002\u00106J\u001e\u00107\u001a\b\u0012\u0004\u0012\u000209082\u0006\u0010,\u001a\u00020-2\u0006\u00102\u001a\u000203H\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010)2\u0006\u00104\u001a\u000205H\u0002J\u001c\u0010;\u001a\u00020<*\u00020-2\u0006\u00102\u001a\u0002032\u0006\u0010=\u001a\u00020)H\u0002J\u000e\u0010>\u001a\u0004\u0018\u00010#*\u00020<H\u0002J\u001e\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020-H\u0082@¢\u0006\u0002\u0010BJ\u001e\u0010C\u001a\u00020D2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010E\u001a\u00020&H\u0082@¢\u0006\u0002\u0010FJ0\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020)2\u0006\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J(\u0010Q\u001a\u00020R2\u0006\u0010I\u001a\u00020)2\u0006\u0010J\u001a\u00020)2\u0006\u0010S\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J\u0010\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020WH\u0002J\b\u0010X\u001a\u00020\u001bH\u0002J&\u0010Y\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020)08H\u0002J&\u0010[\u001a\u00020D2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\u0006\u0010\\\u001a\u00020)H\u0082@¢\u0006\u0002\u0010]J\u0016\u0010^\u001a\u00020D2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010 J8\u0010_\u001a\u00020D2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\u0006\u0010`\u001a\u00020)2\u0006\u0010a\u001a\u00020)2\u0006\u0010b\u001a\u00020@2\u0006\u0010c\u001a\u00020)H\u0002J \u0010d\u001a\u00020D2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020P2\u0006\u0010c\u001a\u00020)H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006k"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker;", "", "appPrefs", "Lcom/example/shellshot/data/AppPrefs;", "appStateStore", "Lcom/example/shellshot/data/AppStateStore;", "taskStore", "Lcom/example/shellshot/queue/QueuedScreenshotTaskStore;", "templateRepository", "Lcom/example/shellshot/template/TemplateRepository;", "latestScreenshotResolver", "Lcom/example/shellshot/media/LatestScreenshotResolver;", "screenshotStabilityChecker", "Lcom/example/shellshot/media/ScreenshotStabilityChecker;", "bitmapLoader", "Lcom/example/shellshot/media/BitmapLoader;", "outputRepository", "Lcom/example/shellshot/media/OutputRepository;", "shellComposeEngine", "Lcom/example/shellshot/processor/ShellComposeEngine;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Lcom/example/shellshot/data/AppPrefs;Lcom/example/shellshot/data/AppStateStore;Lcom/example/shellshot/queue/QueuedScreenshotTaskStore;Lcom/example/shellshot/template/TemplateRepository;Lcom/example/shellshot/media/LatestScreenshotResolver;Lcom/example/shellshot/media/ScreenshotStabilityChecker;Lcom/example/shellshot/media/BitmapLoader;Lcom/example/shellshot/media/OutputRepository;Lcom/example/shellshot/processor/ShellComposeEngine;Lcom/example/shellshot/utils/ShellLogger;)V", "workerMutex", "Lkotlinx/coroutines/sync/Mutex;", "runNextTask", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareTask", "task", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "(Lcom/example/shellshot/queue/QueuedScreenshotTask;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "composeTask", "resolveTask", "Lcom/example/shellshot/media/ResolveQueuedTaskResult;", "resolveTaskForCompose", "classifyPrepareResolutionFailure", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision;", "resolution", "screenshotRelativePath", "", "classifyComposeResolutionFailure", "classifyDecodeFailure", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "throwable", "", "loadCandidateBitmap", "Landroid/graphics/Bitmap;", "resolutionSource", "Lcom/example/shellshot/media/ResolutionSource;", "template", "Lcom/example/shellshot/template/ShellTemplate;", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lcom/example/shellshot/media/ResolutionSource;Lcom/example/shellshot/template/ShellTemplate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildDecodeTargets", "", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget;", "validateTemplate", "toPreparedCandidate", "Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "resolutionReason", "toReusableResolutionOrNull", "deleteOriginalIfNeeded", "Lcom/example/shellshot/media/DeleteResult;", "autoDeleteOriginal", "(ZLcom/example/shellshot/media/ScreenshotCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleFailure", "", "decision", "(Lcom/example/shellshot/queue/QueuedScreenshotTask;Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retryableFailure", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Retryable;", "message", "basis", "queueState", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$RetryQueueState;", "exhaustedPhase", "Lcom/example/shellshot/data/MonitoringPhase;", "statusOnPublish", "Lcom/example/shellshot/data/ProcessingStatus;", "finalFailure", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Final;", "phase", "retryDelayMillis", "", "nextRetryCount", "", "canProcessCurrentTask", "isRecentlyProcessed", "recentProcessedKeys", "skipAlreadyProcessedTask", "reason", "(Lcom/example/shellshot/queue/QueuedScreenshotTask;Lcom/example/shellshot/media/ScreenshotCandidate;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deferTaskUntilMonitoringActive", "publishSuccessResult", "templateId", "outputPath", "deleteResult", "detailMessage", "publishResult", NotificationCompat.CATEGORY_STATUS, "RetryQueueState", "TaskFailureCategory", "TaskFailureDecision", "DecodeTarget", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QueuedScreenshotWorker {

    @Deprecated
    public static final int MAX_RETRYABLE_ATTEMPTS = 8;

    @Deprecated
    public static final long SUSPENDED_WAIT_DELAY_MILLIS = 200;

    @Deprecated
    public static final String TAG = "QueueWorker";
    private final AppPrefs appPrefs;
    private final AppStateStore appStateStore;
    private final BitmapLoader bitmapLoader;
    private final LatestScreenshotResolver latestScreenshotResolver;
    private final ShellLogger logger;
    private final OutputRepository outputRepository;
    private final ScreenshotStabilityChecker screenshotStabilityChecker;
    private final ShellComposeEngine shellComposeEngine;
    private final QueuedScreenshotTaskStore taskStore;
    private final TemplateRepository templateRepository;
    private final Mutex workerMutex;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final long[] RETRYABLE_BACKOFF_MILLIS = {200, 500, 1000, 2000, 4000, 4000, 4000};

    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$RetryQueueState;", "", "<init>", "(Ljava/lang/String;I)V", "WaitStable", "FailedRetryable", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum RetryQueueState {
        WaitStable,
        FailedRetryable;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<RetryQueueState> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureCategory;", "", "<init>", "(Ljava/lang/String;I)V", "RETRYABLE", "FINAL", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum TaskFailureCategory {
        RETRYABLE,
        FINAL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<TaskFailureCategory> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ScreenshotTaskStatus.values().length];
            try {
                iArr[ScreenshotTaskStatus.DETECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScreenshotTaskStatus.WAIT_STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ScreenshotTaskStatus.FAILED_RETRYABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ScreenshotTaskStatus.READY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ScreenshotTaskStatus.COMPOSING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ScreenshotTaskStatus.SAVED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ScreenshotTaskStatus.DELETE_DONE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ScreenshotTaskStatus.FAILED_FINAL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RetryQueueState.values().length];
            try {
                iArr2[RetryQueueState.WaitStable.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[RetryQueueState.FailedRetryable.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public QueuedScreenshotWorker(AppPrefs appPrefs, AppStateStore appStateStore, QueuedScreenshotTaskStore taskStore, TemplateRepository templateRepository, LatestScreenshotResolver latestScreenshotResolver, ScreenshotStabilityChecker screenshotStabilityChecker, BitmapLoader bitmapLoader, OutputRepository outputRepository, ShellComposeEngine shellComposeEngine, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(appPrefs, "appPrefs");
        Intrinsics.checkNotNullParameter(appStateStore, "appStateStore");
        Intrinsics.checkNotNullParameter(taskStore, "taskStore");
        Intrinsics.checkNotNullParameter(templateRepository, "templateRepository");
        Intrinsics.checkNotNullParameter(latestScreenshotResolver, "latestScreenshotResolver");
        Intrinsics.checkNotNullParameter(screenshotStabilityChecker, "screenshotStabilityChecker");
        Intrinsics.checkNotNullParameter(bitmapLoader, "bitmapLoader");
        Intrinsics.checkNotNullParameter(outputRepository, "outputRepository");
        Intrinsics.checkNotNullParameter(shellComposeEngine, "shellComposeEngine");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.appPrefs = appPrefs;
        this.appStateStore = appStateStore;
        this.taskStore = taskStore;
        this.templateRepository = templateRepository;
        this.latestScreenshotResolver = latestScreenshotResolver;
        this.screenshotStabilityChecker = screenshotStabilityChecker;
        this.bitmapLoader = bitmapLoader;
        this.outputRepository = outputRepository;
        this.shellComposeEngine = shellComposeEngine;
        this.logger = logger;
        this.workerMutex = MutexKt.Mutex$default(false, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ac A[Catch: all -> 0x0063, TryCatch #1 {all -> 0x0063, blocks: (B:28:0x005e, B:30:0x00a4, B:33:0x00ac, B:34:0x00b8, B:35:0x00bb, B:36:0x0112, B:37:0x0115, B:39:0x00bf, B:43:0x00e3), top: B:27:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runNextTask(kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.runNextTask(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object prepareTask(com.example.shellshot.queue.QueuedScreenshotTask r18, kotlin.coroutines.Continuation<? super java.lang.Boolean> r19) {
        /*
            Method dump skipped, instructions count: 894
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.prepareTask(com.example.shellshot.queue.QueuedScreenshotTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x01ac: MOVE (r6 I:??[OBJECT, ARRAY] A[D('invalidTemplateReason' java.lang.String)]) = (r13 I:??[OBJECT, ARRAY] A[D('template' com.example.shellshot.template.ShellTemplate)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x01ad: MOVE (r10 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY] A[D('candidate' com.example.shellshot.media.ScreenshotCandidate)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 19, insn: 0x01ae: MOVE (r14 I:??[OBJECT, ARRAY] A[D('candidate' com.example.shellshot.media.ScreenshotCandidate)]) = (r19 I:??[OBJECT, ARRAY] A[D('settings' com.example.shellshot.data.AppSettings)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x01b0: MOVE (r13 I:??[OBJECT, ARRAY] A[D('template' com.example.shellshot.template.ShellTemplate)]) = (r20 I:??[OBJECT, ARRAY] A[D('task' com.example.shellshot.queue.QueuedScreenshotTask)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x01a8: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY] A[D('composedBitmap' kotlin.jvm.internal.Ref$ObjectRef)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01a9: MOVE (r4 I:??[OBJECT, ARRAY] A[D('composedBitmap' kotlin.jvm.internal.Ref$ObjectRef)]) = (r5 I:??[OBJECT, ARRAY] A[D('sourceBitmap' kotlin.jvm.internal.Ref$ObjectRef)]), block:B:553:0x01a8 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x01aa: MOVE (r5 I:??[OBJECT, ARRAY] A[D('sourceBitmap' kotlin.jvm.internal.Ref$ObjectRef)]) = (r6 I:??[OBJECT, ARRAY] A[D('invalidTemplateReason' java.lang.String)]), block:B:553:0x01a8 */
    public final java.lang.Object composeTask(com.example.shellshot.queue.QueuedScreenshotTask r26, kotlin.coroutines.Continuation<? super java.lang.Boolean> r27) {
        /*
            Method dump skipped, instructions count: 3898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.composeTask(com.example.shellshot.queue.QueuedScreenshotTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTask(QueuedScreenshotTask task, Continuation<? super ResolveQueuedTaskResult> continuation) {
        LatestScreenshotResolver latestScreenshotResolver = this.latestScreenshotResolver;
        String absolutePath = task.getAbsolutePath();
        String displayName = task.getDisplayName();
        String relativePath = task.getRelativePath();
        String changedUri = task.getChangedUri();
        return latestScreenshotResolver.resolveQueuedTaskDetailed(absolutePath, displayName, relativePath, changedUri != null ? Uri.parse(changedUri) : null, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTaskForCompose(QueuedScreenshotTask task, Continuation<? super ResolveQueuedTaskResult> continuation) {
        PreparedScreenshotCandidate preparedCandidate = task.getPreparedCandidate();
        ResolveQueuedTaskResult preparedResolution = preparedCandidate != null ? toReusableResolutionOrNull(preparedCandidate) : null;
        if (preparedResolution != null) {
            ShellLogger shellLogger = this.logger;
            ScreenshotCandidate candidate = preparedResolution.getCandidate();
            shellLogger.d(TAG, "复用已确认候选 path=" + (candidate != null ? candidate.getAbsolutePath() : null) + " source=" + preparedResolution.getSource() + " reason=" + preparedResolution.getReason());
            return preparedResolution;
        }
        if (task.getPreparedCandidate() != null) {
            this.logger.d(TAG, "已确认候选失效，回退重新解析 path=" + task.getAbsolutePath());
        }
        return resolveTask(task, continuation);
    }

    private final TaskFailureDecision classifyPrepareResolutionFailure(QueuedScreenshotTask task, ResolveQueuedTaskResult resolution, String screenshotRelativePath) {
        if (StringsKt.isBlank(task.getAbsolutePath()) || StringsKt.isBlank(task.getDisplayName())) {
            return finalFailure("输入路径或文件名无效", LatestScreenshotResolver.REASON_INVALID_INPUT, MonitoringPhase.FileNotStable, ProcessingStatus.Failed);
        }
        if (ScreenshotDirectories.INSTANCE.isGeneratedByApp(task.getDisplayName()) || ScreenshotDirectories.isOutputLocation$default(ScreenshotDirectories.INSTANCE, task.getAbsolutePath(), task.getRelativePath(), null, 4, null) || !ScreenshotDirectories.INSTANCE.looksLikeImageFile(task.getDisplayName()) || !ScreenshotDirectories.INSTANCE.isScreenshotSource(task.getAbsolutePath(), task.getRelativePath(), screenshotRelativePath)) {
            return finalFailure("明确不是截图", "not_screenshot path=" + task.getAbsolutePath(), MonitoringPhase.FileNotStable, ProcessingStatus.Skipped);
        }
        return retryableFailure("候选截图尚未准备好", "resolver_none source=" + resolution.getSource() + " reason=" + resolution.getReason(), RetryQueueState.WaitStable, MonitoringPhase.FileNotStable, ProcessingStatus.Skipped);
    }

    private final TaskFailureDecision classifyComposeResolutionFailure(QueuedScreenshotTask task, ResolveQueuedTaskResult resolution, String screenshotRelativePath) {
        if (StringsKt.isBlank(task.getAbsolutePath()) || StringsKt.isBlank(task.getDisplayName())) {
            return finalFailure("输入路径或文件名无效", "compose_invalid_input", MonitoringPhase.ComposeFailed, ProcessingStatus.Failed);
        }
        if (!ScreenshotDirectories.INSTANCE.looksLikeImageFile(task.getDisplayName()) || !ScreenshotDirectories.INSTANCE.isScreenshotSource(task.getAbsolutePath(), task.getRelativePath(), screenshotRelativePath)) {
            return finalFailure("明确不是截图", "compose_not_screenshot path=" + task.getAbsolutePath(), MonitoringPhase.ComposeFailed, ProcessingStatus.Skipped);
        }
        return finalFailure("截图文件已不可用", "compose_candidate_missing source=" + resolution.getSource() + " reason=" + resolution.getReason(), MonitoringPhase.ComposeFailed, ProcessingStatus.Failed);
    }

    private final TaskFailureDecision classifyDecodeFailure(ScreenshotCandidate candidate, Throwable throwable) {
        this.logger.e(TAG, "截图解码失败 path=" + candidate.getAbsolutePath(), throwable);
        String simpleName = Reflection.getOrCreateKotlinClass(throwable.getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return retryableFailure("截图解码暂时失败", "decode_transient:" + simpleName, RetryQueueState.FailedRetryable, MonitoringPhase.ComposeFailed, ProcessingStatus.Failed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:29|30|31|32|33|34|(14:36|37|38|39|40|41|42|43|44|45|46|47|48|(1:50)(6:51|52|17|18|19|(2:21|22)(5:23|(1:25)(1:87)|26|27|(2:81|(2:83|84)(2:85|86))(0))))(3:65|66|(2:68|(1:70)(7:71|15|16|17|18|19|(0)(0)))(2:72|73))|53|54|55|19|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(14:36|37|38|39|40|41|42|43|44|45|46|47|48|(1:50)(6:51|52|17|18|19|(2:21|22)(5:23|(1:25)(1:87)|26|27|(2:81|(2:83|84)(2:85|86))(0)))) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:3|(4:5|6|7|8))|101|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x00b1, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x017d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x017e, code lost:
    
        r18 = r19;
        r2 = r20;
        r10 = r23;
        r5 = r8;
        r7 = r9;
        r9 = r15;
        r12 = r17;
        r17 = r3;
        r15 = r4;
        r3 = r6;
        r4 = r22;
        r6 = 0;
        r1 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0196, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0197, code lost:
    
        r18 = r19;
        r2 = r20;
        r10 = r23;
        r5 = r8;
        r7 = r9;
        r9 = r15;
        r17 = r3;
        r15 = r4;
        r3 = r6;
        r4 = r22;
        r6 = 0;
        r1 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01af, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01b0, code lost:
    
        r18 = r19;
        r2 = r20;
        r5 = r8;
        r7 = r9;
        r9 = r15;
        r17 = r3;
        r15 = r4;
        r3 = r6;
        r4 = r22;
        r6 = 0;
        r1 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0276, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0277, code lost:
    
        r22 = r7;
        r18 = r19;
        r2 = r20;
        r5 = r8;
        r7 = r9;
        r9 = r15;
        r17 = r3;
        r15 = r4;
        r3 = r6;
        r4 = r22;
        r6 = 0;
        r1 = r21;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /* JADX WARN: Type inference failed for: r4v15, types: [T, java.lang.Throwable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x02ab -> B:19:0x02c6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0225 -> B:15:0x023d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCandidateBitmap(com.example.shellshot.media.ScreenshotCandidate r20, com.example.shellshot.media.ResolutionSource r21, com.example.shellshot.template.ShellTemplate r22, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r23) {
        /*
            Method dump skipped, instructions count: 932
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.loadCandidateBitmap(com.example.shellshot.media.ScreenshotCandidate, com.example.shellshot.media.ResolutionSource, com.example.shellshot.template.ShellTemplate, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List<DecodeTarget> buildDecodeTargets(ScreenshotCandidate candidate, ResolutionSource resolutionSource) {
        Iterable targets = (List) new ArrayList();
        if (resolutionSource == ResolutionSource.MEDIASTORE_FALLBACK && candidate.getUri() != null) {
            ((Collection) targets).add(new DecodeTarget.UriTarget(candidate.getUri()));
        }
        if (!StringsKt.isBlank(candidate.getAbsolutePath())) {
            ((Collection) targets).add(new DecodeTarget.PathTarget(candidate.getAbsolutePath()));
        }
        if (resolutionSource != ResolutionSource.MEDIASTORE_FALLBACK && candidate.getUri() != null) {
            ((Collection) targets).add(new DecodeTarget.UriTarget(candidate.getUri()));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : targets) {
            if (hashSet.add(((DecodeTarget) obj).key())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final String validateTemplate(ShellTemplate template) {
        if (StringsKt.isBlank(template.getFrameAsset())) {
            return "frame_asset_blank";
        }
        if (template.getScreenRect().getWidth() <= 0 || template.getScreenRect().getHeight() <= 0) {
            return "screen_rect_invalid";
        }
        if (!template.getHasExplicitOutputSize() || (template.getOutputWidth() > 0 && template.getOutputHeight() > 0)) {
            return null;
        }
        return "output_size_invalid";
    }

    private final PreparedScreenshotCandidate toPreparedCandidate(ScreenshotCandidate $this$toPreparedCandidate, ResolutionSource resolutionSource, String resolutionReason) {
        String absolutePath = $this$toPreparedCandidate.getAbsolutePath();
        String displayName = $this$toPreparedCandidate.getDisplayName();
        String relativePath = $this$toPreparedCandidate.getRelativePath();
        Uri uri = $this$toPreparedCandidate.getUri();
        return new PreparedScreenshotCandidate(absolutePath, displayName, relativePath, uri != null ? uri.toString() : null, $this$toPreparedCandidate.getLastModifiedMillis(), $this$toPreparedCandidate.getSizeBytes(), $this$toPreparedCandidate.getMimeType(), $this$toPreparedCandidate.getWidth(), $this$toPreparedCandidate.getHeight(), resolutionSource.name(), resolutionReason);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.example.shellshot.media.ResolveQueuedTaskResult toReusableResolutionOrNull(com.example.shellshot.queue.PreparedScreenshotCandidate r21) {
        /*
            r20 = this;
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L12
            r0 = r21
            r1 = 0
            java.lang.String r2 = r0.getResolutionSource()     // Catch: java.lang.Throwable -> L12
            com.example.shellshot.media.ResolutionSource r2 = com.example.shellshot.media.ResolutionSource.valueOf(r2)     // Catch: java.lang.Throwable -> L12
            java.lang.Object r0 = kotlin.Result.m7216constructorimpl(r2)     // Catch: java.lang.Throwable -> L12
            goto L1d
        L12:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m7216constructorimpl(r0)
        L1d:
            boolean r1 = kotlin.Result.m7222isFailureimpl(r0)
            r2 = 0
            if (r1 == 0) goto L25
            r0 = r2
        L25:
            com.example.shellshot.media.ResolutionSource r0 = (com.example.shellshot.media.ResolutionSource) r0
            if (r0 != 0) goto L2a
            return r2
        L2a:
            java.lang.String r1 = r21.getUri()
            if (r1 == 0) goto L45
            r3 = r1
            r4 = 0
            r5 = r3
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)
            if (r5 != 0) goto L3c
            goto L3d
        L3c:
            r1 = r2
        L3d:
            if (r1 == 0) goto L45
            r3 = 0
            android.net.Uri r1 = android.net.Uri.parse(r1)
            goto L46
        L45:
            r1 = r2
        L46:
            r14 = r1
            java.lang.String r1 = r21.getAbsolutePath()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            r1 = r1 ^ 1
            if (r1 != 0) goto L58
            if (r14 != 0) goto L58
            return r2
        L58:
            if (r1 == 0) goto La8
            java.io.File r3 = new java.io.File
            java.lang.String r4 = r21.getAbsolutePath()
            r3.<init>(r4)
            boolean r4 = r3.exists()
            if (r4 == 0) goto La5
            boolean r4 = r3.canRead()
            if (r4 == 0) goto La4
            long r4 = r3.length()
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L7a
            goto La4
        L7a:
            long r4 = r21.getSizeBytes()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L8f
            long r4 = r3.length()
            long r8 = r21.getSizeBytes()
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 == 0) goto L8f
            return r2
        L8f:
            long r4 = r21.getLastModifiedMillis()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto La8
            long r4 = r3.lastModified()
            long r6 = r21.getLastModifiedMillis()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto La8
            return r2
        La4:
            return r2
        La5:
            if (r14 != 0) goto La8
            return r2
        La8:
            com.example.shellshot.media.ResolveQueuedTaskResult r2 = new com.example.shellshot.media.ResolveQueuedTaskResult
            com.example.shellshot.media.ScreenshotCandidate r3 = new com.example.shellshot.media.ScreenshotCandidate
            java.lang.String r4 = r21.getAbsolutePath()
            java.lang.String r5 = r21.getDisplayName()
            java.lang.String r6 = r21.getRelativePath()
            long r7 = r21.getLastModifiedMillis()
            long r9 = r21.getSizeBytes()
            java.lang.String r11 = r21.getMimeType()
            java.lang.Integer r12 = r21.getWidth()
            java.lang.Integer r13 = r21.getHeight()
            r18 = 1536(0x600, float:2.152E-42)
            r19 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r3.<init>(r4, r5, r6, r7, r9, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            java.lang.String r4 = r21.getResolutionReason()
            r2.<init>(r3, r0, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.toReusableResolutionOrNull(com.example.shellshot.queue.PreparedScreenshotCandidate):com.example.shellshot.media.ResolveQueuedTaskResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:0|1|(2:3|(12:5|6|7|8|15|16|(1:18)|19|(3:21|(1:23)|24)|25|(1:27)(1:29)|28))|41|6|7|8|15|16|(0)|19|(0)|25|(0)(0)|28) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x003f, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0076, code lost:
    
        r3 = kotlin.Result.INSTANCE;
        r2 = kotlin.Result.m7216constructorimpl(kotlin.ResultKt.createFailure(r2));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deleteOriginalIfNeeded(boolean r12, com.example.shellshot.media.ScreenshotCandidate r13, kotlin.coroutines.Continuation<? super com.example.shellshot.media.DeleteResult> r14) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.deleteOriginalIfNeeded(boolean, com.example.shellshot.media.ScreenshotCandidate, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object handleFailure(com.example.shellshot.queue.QueuedScreenshotTask r22, com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.handleFailure(com.example.shellshot.queue.QueuedScreenshotTask, com.example.shellshot.queue.QueuedScreenshotWorker$TaskFailureDecision, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final TaskFailureDecision.Retryable retryableFailure(String message, String basis, RetryQueueState queueState, MonitoringPhase exhaustedPhase, ProcessingStatus statusOnPublish) {
        return new TaskFailureDecision.Retryable(message, basis, queueState, exhaustedPhase, statusOnPublish);
    }

    private final TaskFailureDecision.Final finalFailure(String message, String basis, MonitoringPhase phase, ProcessingStatus statusOnPublish) {
        return new TaskFailureDecision.Final(message, basis, phase, statusOnPublish);
    }

    private final long retryDelayMillis(int nextRetryCount) {
        long[] jArr = RETRYABLE_BACKOFF_MILLIS;
        int i = nextRetryCount - 1;
        boolean z = false;
        if (i >= 0 && i < jArr.length) {
            z = true;
        }
        return z ? jArr[i] : ArraysKt.last(RETRYABLE_BACKOFF_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canProcessCurrentTask() {
        return this.appStateStore.getRuntimeState().getValue().getMode() == AutoShellMode.ACTIVE;
    }

    private final boolean isRecentlyProcessed(QueuedScreenshotTask task, ScreenshotCandidate candidate, List<String> recentProcessedKeys) {
        if (recentProcessedKeys.isEmpty()) {
            return false;
        }
        Iterable listOfNotNull = CollectionsKt.listOfNotNull((Object[]) new String[]{ScreenshotCandidate.INSTANCE.buildProcessedPathKey(task.getAbsolutePath()), ScreenshotCandidate.INSTANCE.buildProcessedPathKey(candidate.getAbsolutePath()), candidate.getDedupeKey()});
        if ((listOfNotNull instanceof Collection) && ((Collection) listOfNotNull).isEmpty()) {
            return false;
        }
        Iterator it = listOfNotNull.iterator();
        while (it.hasNext()) {
            if (recentProcessedKeys.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object skipAlreadyProcessedTask(com.example.shellshot.queue.QueuedScreenshotTask r10, com.example.shellshot.media.ScreenshotCandidate r11, java.lang.String r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof com.example.shellshot.queue.QueuedScreenshotWorker$skipAlreadyProcessedTask$1
            if (r0 == 0) goto L14
            r0 = r13
            com.example.shellshot.queue.QueuedScreenshotWorker$skipAlreadyProcessedTask$1 r0 = (com.example.shellshot.queue.QueuedScreenshotWorker$skipAlreadyProcessedTask$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.queue.QueuedScreenshotWorker$skipAlreadyProcessedTask$1 r0 = new com.example.shellshot.queue.QueuedScreenshotWorker$skipAlreadyProcessedTask$1
            r0.<init>(r9, r13)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            java.lang.String r4 = "already_processed"
            switch(r3) {
                case 0: goto L41;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2e:
            java.lang.Object r2 = r0.L$2
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r2 = r0.L$1
            r11 = r2
            com.example.shellshot.media.ScreenshotCandidate r11 = (com.example.shellshot.media.ScreenshotCandidate) r11
            java.lang.Object r2 = r0.L$0
            r10 = r2
            com.example.shellshot.queue.QueuedScreenshotTask r10 = (com.example.shellshot.queue.QueuedScreenshotTask) r10
            kotlin.ResultKt.throwOnFailure(r1)
            goto L5f
        L41:
            kotlin.ResultKt.throwOnFailure(r1)
            com.example.shellshot.queue.QueuedScreenshotTaskStore r3 = r9.taskStore
            java.lang.String r5 = r10.getId()
            int r6 = r10.getRetryCount()
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r7 = 1
            r0.label = r7
            java.lang.Object r3 = r3.markFailedFinal(r5, r6, r4, r0)
            if (r3 != r2) goto L5f
            return r2
        L5f:
            com.example.shellshot.utils.ShellLogger r2 = r9.logger
            com.example.shellshot.queue.QueuedScreenshotWorker$TaskFailureCategory r3 = com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureCategory.FINAL
            java.lang.String r5 = r10.getAbsolutePath()
            java.lang.String r6 = r11.getAbsolutePath()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "浠诲姟璺宠繃 category="
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r3 = r7.append(r3)
            java.lang.String r7 = " basis=already_processed reason="
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.StringBuilder r3 = r3.append(r12)
            java.lang.String r7 = " taskPath="
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = " candidatePath="
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "QueueWorker"
            r2.d(r5, r3)
            com.example.shellshot.data.AppStateStore r2 = r9.appStateStore
            com.example.shellshot.data.MonitoringPhase r3 = com.example.shellshot.data.MonitoringPhase.Monitoring
            r2.setMonitoringPhase(r3)
            com.example.shellshot.data.ProcessingStatus r2 = com.example.shellshot.data.ProcessingStatus.Skipped
            r9.publishResult(r10, r2, r4)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.skipAlreadyProcessedTask(com.example.shellshot.queue.QueuedScreenshotTask, com.example.shellshot.media.ScreenshotCandidate, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deferTaskUntilMonitoringActive(com.example.shellshot.queue.QueuedScreenshotTask r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.example.shellshot.queue.QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1
            if (r0 == 0) goto L14
            r0 = r13
            com.example.shellshot.queue.QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1 r0 = (com.example.shellshot.queue.QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.queue.QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1 r0 = new com.example.shellshot.queue.QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1
            r0.<init>(r11, r13)
        L19:
            r7 = r0
            java.lang.Object r0 = r7.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            long r1 = r7.J$0
            java.lang.Object r3 = r7.L$0
            r12 = r3
            com.example.shellshot.queue.QueuedScreenshotTask r12 = (com.example.shellshot.queue.QueuedScreenshotTask) r12
            kotlin.ResultKt.throwOnFailure(r0)
            goto L60
        L38:
            kotlin.ResultKt.throwOnFailure(r0)
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 200(0xc8, double:9.9E-322)
            long r5 = r1 + r3
            com.example.shellshot.queue.QueuedScreenshotTaskStore r1 = r11.taskStore
            java.lang.String r2 = r12.getId()
            int r3 = r12.getRetryCount()
            r7.L$0 = r12
            r7.J$0 = r5
            r4 = 1
            r7.label = r4
            java.lang.String r4 = "monitoring_suspended"
            java.lang.Object r1 = r1.markWaitStable(r2, r3, r4, r5, r7)
            if (r1 != r8) goto L5f
            return r8
        L5f:
            r1 = r5
        L60:
            com.example.shellshot.utils.ShellLogger r3 = r11.logger
            com.example.shellshot.queue.QueuedScreenshotWorker$TaskFailureCategory r4 = com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureCategory.RETRYABLE
            int r5 = r12.getRetryCount()
            java.lang.String r6 = r12.getAbsolutePath()
            com.example.shellshot.data.AppStateStore r8 = r11.appStateStore
            kotlinx.coroutines.flow.StateFlow r8 = r8.getRuntimeState()
            java.lang.Object r8 = r8.getValue()
            com.example.shellshot.data.AppRuntimeState r8 = (com.example.shellshot.data.AppRuntimeState) r8
            com.example.shellshot.service.AutoShellMode r8 = r8.getMode()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "任务延后 category="
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r4 = r9.append(r4)
            java.lang.String r9 = " basis=suspend_interrupted retry="
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " nextAttemptAt="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r5 = " path="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r5 = " mode="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "QueueWorker"
            r3.d(r5, r4)
            com.example.shellshot.data.AppStateStore r3 = r11.appStateStore
            com.example.shellshot.data.MonitoringPhase r4 = com.example.shellshot.data.MonitoringPhase.Monitoring
            r3.setMonitoringPhase(r4)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotWorker.deferTaskUntilMonitoringActive(com.example.shellshot.queue.QueuedScreenshotTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void publishSuccessResult(QueuedScreenshotTask task, ScreenshotCandidate candidate, String templateId, String outputPath, DeleteResult deleteResult, String detailMessage) {
        this.appStateStore.setLastProcessingResult(new ProcessingResult(System.currentTimeMillis(), candidate.getAbsolutePath(), outputPath, templateId, task.getSource(), deleteResult.getSuccess(), deleteResult.getMessage(), ProcessingStatus.Success, detailMessage));
    }

    private final void publishResult(QueuedScreenshotTask task, ProcessingStatus status, String detailMessage) {
        AppStateStore appStateStore = this.appStateStore;
        long currentTimeMillis = System.currentTimeMillis();
        String absolutePath = task.getAbsolutePath();
        String outputPath = task.getOutputPath();
        ProcessingResult lastProcessingResult = this.appStateStore.getRuntimeState().getValue().getLastProcessingResult();
        String templateId = lastProcessingResult != null ? lastProcessingResult.getTemplateId() : null;
        if (templateId == null) {
            templateId = "";
        }
        appStateStore.setLastProcessingResult(new ProcessingResult(currentTimeMillis, absolutePath, outputPath, templateId, task.getSource(), false, null, status, detailMessage));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B)\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0082\u0001\u0002\u0014\u0015¨\u0006\u0016"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision;", "", "category", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureCategory;", "message", "", "basis", "statusOnPublish", "Lcom/example/shellshot/data/ProcessingStatus;", "<init>", "(Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureCategory;Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/data/ProcessingStatus;)V", "getCategory", "()Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureCategory;", "getMessage", "()Ljava/lang/String;", "getBasis", "getStatusOnPublish", "()Lcom/example/shellshot/data/ProcessingStatus;", "Retryable", "Final", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Final;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Retryable;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static abstract class TaskFailureDecision {
        private final String basis;
        private final TaskFailureCategory category;
        private final String message;
        private final ProcessingStatus statusOnPublish;

        public /* synthetic */ TaskFailureDecision(TaskFailureCategory taskFailureCategory, String str, String str2, ProcessingStatus processingStatus, DefaultConstructorMarker defaultConstructorMarker) {
            this(taskFailureCategory, str, str2, processingStatus);
        }

        private TaskFailureDecision(TaskFailureCategory category, String message, String basis, ProcessingStatus statusOnPublish) {
            this.category = category;
            this.message = message;
            this.basis = basis;
            this.statusOnPublish = statusOnPublish;
        }

        public final TaskFailureCategory getCategory() {
            return this.category;
        }

        public String getMessage() {
            return this.message;
        }

        public String getBasis() {
            return this.basis;
        }

        public ProcessingStatus getStatusOnPublish() {
            return this.statusOnPublish;
        }

        /* compiled from: QueuedScreenshotWorker.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Retryable;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision;", "message", "", "basis", "queueState", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$RetryQueueState;", "exhaustedPhase", "Lcom/example/shellshot/data/MonitoringPhase;", "statusOnPublish", "Lcom/example/shellshot/data/ProcessingStatus;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/queue/QueuedScreenshotWorker$RetryQueueState;Lcom/example/shellshot/data/MonitoringPhase;Lcom/example/shellshot/data/ProcessingStatus;)V", "getMessage", "()Ljava/lang/String;", "getBasis", "getQueueState", "()Lcom/example/shellshot/queue/QueuedScreenshotWorker$RetryQueueState;", "getExhaustedPhase", "()Lcom/example/shellshot/data/MonitoringPhase;", "getStatusOnPublish", "()Lcom/example/shellshot/data/ProcessingStatus;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Retryable extends TaskFailureDecision {
            public static final int $stable = 0;
            private final String basis;
            private final MonitoringPhase exhaustedPhase;
            private final String message;
            private final RetryQueueState queueState;
            private final ProcessingStatus statusOnPublish;

            public static /* synthetic */ Retryable copy$default(Retryable retryable, String str, String str2, RetryQueueState retryQueueState, MonitoringPhase monitoringPhase, ProcessingStatus processingStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = retryable.message;
                }
                if ((i & 2) != 0) {
                    str2 = retryable.basis;
                }
                if ((i & 4) != 0) {
                    retryQueueState = retryable.queueState;
                }
                if ((i & 8) != 0) {
                    monitoringPhase = retryable.exhaustedPhase;
                }
                if ((i & 16) != 0) {
                    processingStatus = retryable.statusOnPublish;
                }
                ProcessingStatus processingStatus2 = processingStatus;
                RetryQueueState retryQueueState2 = retryQueueState;
                return retryable.copy(str, str2, retryQueueState2, monitoringPhase, processingStatus2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final String getBasis() {
                return this.basis;
            }

            /* renamed from: component3, reason: from getter */
            public final RetryQueueState getQueueState() {
                return this.queueState;
            }

            /* renamed from: component4, reason: from getter */
            public final MonitoringPhase getExhaustedPhase() {
                return this.exhaustedPhase;
            }

            /* renamed from: component5, reason: from getter */
            public final ProcessingStatus getStatusOnPublish() {
                return this.statusOnPublish;
            }

            public final Retryable copy(String message, String basis, RetryQueueState queueState, MonitoringPhase exhaustedPhase, ProcessingStatus statusOnPublish) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(basis, "basis");
                Intrinsics.checkNotNullParameter(queueState, "queueState");
                Intrinsics.checkNotNullParameter(exhaustedPhase, "exhaustedPhase");
                Intrinsics.checkNotNullParameter(statusOnPublish, "statusOnPublish");
                return new Retryable(message, basis, queueState, exhaustedPhase, statusOnPublish);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Retryable)) {
                    return false;
                }
                Retryable retryable = (Retryable) other;
                return Intrinsics.areEqual(this.message, retryable.message) && Intrinsics.areEqual(this.basis, retryable.basis) && this.queueState == retryable.queueState && this.exhaustedPhase == retryable.exhaustedPhase && this.statusOnPublish == retryable.statusOnPublish;
            }

            public int hashCode() {
                return (((((((this.message.hashCode() * 31) + this.basis.hashCode()) * 31) + this.queueState.hashCode()) * 31) + this.exhaustedPhase.hashCode()) * 31) + this.statusOnPublish.hashCode();
            }

            public String toString() {
                return "Retryable(message=" + this.message + ", basis=" + this.basis + ", queueState=" + this.queueState + ", exhaustedPhase=" + this.exhaustedPhase + ", statusOnPublish=" + this.statusOnPublish + ")";
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public String getMessage() {
                return this.message;
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public String getBasis() {
                return this.basis;
            }

            public final RetryQueueState getQueueState() {
                return this.queueState;
            }

            public final MonitoringPhase getExhaustedPhase() {
                return this.exhaustedPhase;
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public ProcessingStatus getStatusOnPublish() {
                return this.statusOnPublish;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Retryable(String message, String basis, RetryQueueState queueState, MonitoringPhase exhaustedPhase, ProcessingStatus statusOnPublish) {
                super(TaskFailureCategory.RETRYABLE, message, basis, statusOnPublish, null);
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(basis, "basis");
                Intrinsics.checkNotNullParameter(queueState, "queueState");
                Intrinsics.checkNotNullParameter(exhaustedPhase, "exhaustedPhase");
                Intrinsics.checkNotNullParameter(statusOnPublish, "statusOnPublish");
                this.message = message;
                this.basis = basis;
                this.queueState = queueState;
                this.exhaustedPhase = exhaustedPhase;
                this.statusOnPublish = statusOnPublish;
            }
        }

        /* compiled from: QueuedScreenshotWorker.kt */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision$Final;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$TaskFailureDecision;", "message", "", "basis", "phase", "Lcom/example/shellshot/data/MonitoringPhase;", "statusOnPublish", "Lcom/example/shellshot/data/ProcessingStatus;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/data/MonitoringPhase;Lcom/example/shellshot/data/ProcessingStatus;)V", "getMessage", "()Ljava/lang/String;", "getBasis", "getPhase", "()Lcom/example/shellshot/data/MonitoringPhase;", "getStatusOnPublish", "()Lcom/example/shellshot/data/ProcessingStatus;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Final extends TaskFailureDecision {
            public static final int $stable = 0;
            private final String basis;
            private final String message;
            private final MonitoringPhase phase;
            private final ProcessingStatus statusOnPublish;

            public static /* synthetic */ Final copy$default(Final r0, String str, String str2, MonitoringPhase monitoringPhase, ProcessingStatus processingStatus, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = r0.message;
                }
                if ((i & 2) != 0) {
                    str2 = r0.basis;
                }
                if ((i & 4) != 0) {
                    monitoringPhase = r0.phase;
                }
                if ((i & 8) != 0) {
                    processingStatus = r0.statusOnPublish;
                }
                return r0.copy(str, str2, monitoringPhase, processingStatus);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final String getBasis() {
                return this.basis;
            }

            /* renamed from: component3, reason: from getter */
            public final MonitoringPhase getPhase() {
                return this.phase;
            }

            /* renamed from: component4, reason: from getter */
            public final ProcessingStatus getStatusOnPublish() {
                return this.statusOnPublish;
            }

            public final Final copy(String message, String basis, MonitoringPhase phase, ProcessingStatus statusOnPublish) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(basis, "basis");
                Intrinsics.checkNotNullParameter(phase, "phase");
                Intrinsics.checkNotNullParameter(statusOnPublish, "statusOnPublish");
                return new Final(message, basis, phase, statusOnPublish);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Final)) {
                    return false;
                }
                Final r1 = (Final) other;
                return Intrinsics.areEqual(this.message, r1.message) && Intrinsics.areEqual(this.basis, r1.basis) && this.phase == r1.phase && this.statusOnPublish == r1.statusOnPublish;
            }

            public int hashCode() {
                return (((((this.message.hashCode() * 31) + this.basis.hashCode()) * 31) + this.phase.hashCode()) * 31) + this.statusOnPublish.hashCode();
            }

            public String toString() {
                return "Final(message=" + this.message + ", basis=" + this.basis + ", phase=" + this.phase + ", statusOnPublish=" + this.statusOnPublish + ")";
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public String getMessage() {
                return this.message;
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public String getBasis() {
                return this.basis;
            }

            public final MonitoringPhase getPhase() {
                return this.phase;
            }

            @Override // com.example.shellshot.queue.QueuedScreenshotWorker.TaskFailureDecision
            public ProcessingStatus getStatusOnPublish() {
                return this.statusOnPublish;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Final(String message, String basis, MonitoringPhase phase, ProcessingStatus statusOnPublish) {
                super(TaskFailureCategory.FINAL, message, basis, statusOnPublish, null);
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(basis, "basis");
                Intrinsics.checkNotNullParameter(phase, "phase");
                Intrinsics.checkNotNullParameter(statusOnPublish, "statusOnPublish");
                this.message = message;
                this.basis = basis;
                this.phase = phase;
                this.statusOnPublish = statusOnPublish;
            }
        }
    }

    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget;", "", "<init>", "()V", "key", "", "describe", "PathTarget", "UriTarget", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget$PathTarget;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget$UriTarget;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static abstract class DecodeTarget {
        public /* synthetic */ DecodeTarget(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: QueuedScreenshotWorker.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget$PathTarget;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget;", "absolutePath", "", "<init>", "(Ljava/lang/String;)V", "getAbsolutePath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class PathTarget extends DecodeTarget {
            public static final int $stable = 0;
            private final String absolutePath;

            public static /* synthetic */ PathTarget copy$default(PathTarget pathTarget, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = pathTarget.absolutePath;
                }
                return pathTarget.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getAbsolutePath() {
                return this.absolutePath;
            }

            public final PathTarget copy(String absolutePath) {
                Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
                return new PathTarget(absolutePath);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof PathTarget) && Intrinsics.areEqual(this.absolutePath, ((PathTarget) other).absolutePath);
            }

            public int hashCode() {
                return this.absolutePath.hashCode();
            }

            public String toString() {
                return "PathTarget(absolutePath=" + this.absolutePath + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PathTarget(String absolutePath) {
                super(null);
                Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
                this.absolutePath = absolutePath;
            }

            public final String getAbsolutePath() {
                return this.absolutePath;
            }
        }

        private DecodeTarget() {
        }

        /* compiled from: QueuedScreenshotWorker.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget$UriTarget;", "Lcom/example/shellshot/queue/QueuedScreenshotWorker$DecodeTarget;", "uri", "Landroid/net/Uri;", "<init>", "(Landroid/net/Uri;)V", "getUri", "()Landroid/net/Uri;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class UriTarget extends DecodeTarget {
            public static final int $stable = 8;
            private final Uri uri;

            public static /* synthetic */ UriTarget copy$default(UriTarget uriTarget, Uri uri, int i, Object obj) {
                if ((i & 1) != 0) {
                    uri = uriTarget.uri;
                }
                return uriTarget.copy(uri);
            }

            /* renamed from: component1, reason: from getter */
            public final Uri getUri() {
                return this.uri;
            }

            public final UriTarget copy(Uri uri) {
                Intrinsics.checkNotNullParameter(uri, "uri");
                return new UriTarget(uri);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof UriTarget) && Intrinsics.areEqual(this.uri, ((UriTarget) other).uri);
            }

            public int hashCode() {
                return this.uri.hashCode();
            }

            public String toString() {
                return "UriTarget(uri=" + this.uri + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UriTarget(Uri uri) {
                super(null);
                Intrinsics.checkNotNullParameter(uri, "uri");
                this.uri = uri;
            }

            public final Uri getUri() {
                return this.uri;
            }
        }

        public final String key() {
            if (this instanceof PathTarget) {
                return "path:" + ((PathTarget) this).getAbsolutePath();
            }
            if (!(this instanceof UriTarget)) {
                throw new NoWhenBranchMatchedException();
            }
            return "uri:" + ((UriTarget) this).getUri();
        }

        public final String describe() {
            if (this instanceof PathTarget) {
                return ((PathTarget) this).getAbsolutePath();
            }
            if (!(this instanceof UriTarget)) {
                throw new NoWhenBranchMatchedException();
            }
            String uri = ((UriTarget) this).getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            return uri;
        }
    }

    /* compiled from: QueuedScreenshotWorker.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotWorker$Companion;", "", "<init>", "()V", "TAG", "", "SUSPENDED_WAIT_DELAY_MILLIS", "", "MAX_RETRYABLE_ATTEMPTS", "", "RETRYABLE_BACKOFF_MILLIS", "", "getRETRYABLE_BACKOFF_MILLIS", "()[J", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long[] getRETRYABLE_BACKOFF_MILLIS() {
            return QueuedScreenshotWorker.RETRYABLE_BACKOFF_MILLIS;
        }
    }
}
