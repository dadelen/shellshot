package com.example.shellshot.queue;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.example.shellshot.data.AppPrefs;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 C2\u00020\u0001:\u0001CB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J:\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0086@¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0019H\u0086@¢\u0006\u0002\u0010\u001aJ.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0086@¢\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'H\u0086@¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010*J0\u0010+\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u00100J(\u00101\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u00102J.\u00103\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0086@¢\u0006\u0002\u0010$J&\u00104\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u00105J\u000e\u00106\u001a\u00020\u001dH\u0086@¢\u0006\u0002\u0010\u001aJ*\u00107\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001909H\u0082@¢\u0006\u0002\u0010:J\u0010\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020?H\u0002J\f\u0010B\u001a\u00020.*\u00020=H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotTaskStore;", "", "context", "Landroid/content/Context;", "appPrefs", "Lcom/example/shellshot/data/AppPrefs;", "<init>", "(Landroid/content/Context;Lcom/example/shellshot/data/AppPrefs;)V", "storeFile", "Ljava/io/File;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "json", "Lkotlinx/serialization/json/Json;", "enqueueDetected", "Lcom/example/shellshot/queue/EnqueueTaskResult;", "absolutePath", "", "displayName", "relativePath", "changedUri", "source", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverAfterRestart", "", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peekNextProcessableTask", "markWaitStable", "", "taskId", "retryCount", "", "message", "nextAttemptAtMillis", "", "(Ljava/lang/String;ILjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markReady", "preparedCandidate", "Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "(Ljava/lang/String;Lcom/example/shellshot/queue/PreparedScreenshotCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markComposing", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markSaved", "outputPath", "deleteAttempted", "", "deleteMessage", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markDeleteDone", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markFailedRetryable", "markFailedFinal", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeTerminalTasks", "updateTask", "transform", "Lkotlin/Function1;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "statusPriority", NotificationCompat.CATEGORY_STATUS, "Lcom/example/shellshot/queue/ScreenshotTaskStatus;", "readSnapshotLocked", "Lcom/example/shellshot/queue/ScreenshotTaskSnapshot;", "writeSnapshotLocked", "snapshot", "isTerminal", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QueuedScreenshotTaskStore {

    @Deprecated
    public static final int MAX_TASKS = 120;
    private final AppPrefs appPrefs;
    private final Json json;
    private final Mutex mutex;
    private final File storeFile;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: QueuedScreenshotTaskStore.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenshotTaskStatus.values().length];
            try {
                iArr[ScreenshotTaskStatus.READY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScreenshotTaskStatus.DETECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ScreenshotTaskStatus.WAIT_STABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ScreenshotTaskStatus.FAILED_RETRYABLE.ordinal()] = 4;
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
        }
    }

    public QueuedScreenshotTaskStore(Context context, AppPrefs appPrefs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appPrefs, "appPrefs");
        this.appPrefs = appPrefs;
        this.storeFile = new File(context.getFilesDir(), "screenshot_task_queue.json");
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.json = JsonKt.Json$default(null, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
    }

    static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }

    public final Object enqueueDetected(String absolutePath, String displayName, String relativePath, String changedUri, String source, Continuation<? super EnqueueTaskResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new QueuedScreenshotTaskStore$enqueueDetected$2(this, absolutePath, displayName, relativePath, changedUri, source, null), continuation);
    }

    public final Object recoverAfterRestart(Continuation<? super List<QueuedScreenshotTask>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new QueuedScreenshotTaskStore$recoverAfterRestart$2(this, null), continuation);
    }

    public final Object peekNextProcessableTask(Continuation<? super QueuedScreenshotTask> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new QueuedScreenshotTaskStore$peekNextProcessableTask$2(this, null), continuation);
    }

    public final Object markWaitStable(String taskId, final int retryCount, final String message, final long nextAttemptAtMillis, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markWaitStable$lambda$1(nextAttemptAtMillis, retryCount, message, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markWaitStable$lambda$1(long $nextAttemptAtMillis, int $retryCount, String $message, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.WAIT_STABLE, 0L, System.currentTimeMillis(), $nextAttemptAtMillis, $retryCount, $message, null, null, false, false, null, 123071, null);
    }

    public final Object markReady(String taskId, final PreparedScreenshotCandidate preparedCandidate, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markReady$lambda$2(PreparedScreenshotCandidate.this, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markReady$lambda$2(PreparedScreenshotCandidate $preparedCandidate, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.READY, 0L, System.currentTimeMillis(), System.currentTimeMillis(), 0, null, $preparedCandidate, null, false, false, null, 124095, null);
    }

    public final Object markComposing(String taskId, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markComposing$lambda$3((QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markComposing$lambda$3(QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.COMPOSING, 0L, System.currentTimeMillis(), System.currentTimeMillis(), 0, null, null, null, false, false, null, 128191, null);
    }

    public final Object markSaved(String taskId, final String outputPath, final boolean deleteAttempted, final String deleteMessage, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markSaved$lambda$4(outputPath, deleteAttempted, deleteMessage, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markSaved$lambda$4(String $outputPath, boolean $deleteAttempted, String $deleteMessage, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.SAVED, 0L, System.currentTimeMillis(), Long.MAX_VALUE, 0, null, null, $outputPath, $deleteAttempted, false, $deleteMessage, 5311, null);
    }

    public final Object markDeleteDone(String taskId, final String outputPath, final String deleteMessage, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markDeleteDone$lambda$5(outputPath, deleteMessage, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markDeleteDone$lambda$5(String $outputPath, String $deleteMessage, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.DELETE_DONE, 0L, System.currentTimeMillis(), Long.MAX_VALUE, 0, null, null, $outputPath, true, true, $deleteMessage, 5311, null);
    }

    public final Object markFailedRetryable(String taskId, final int retryCount, final String message, final long nextAttemptAtMillis, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markFailedRetryable$lambda$6(nextAttemptAtMillis, retryCount, message, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markFailedRetryable$lambda$6(long $nextAttemptAtMillis, int $retryCount, String $message, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.FAILED_RETRYABLE, 0L, System.currentTimeMillis(), $nextAttemptAtMillis, $retryCount, $message, null, null, false, false, null, 123071, null);
    }

    public final Object markFailedFinal(String taskId, final int retryCount, final String message, Continuation<? super Unit> continuation) {
        Object updateTask = updateTask(taskId, new Function1() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QueuedScreenshotTaskStore.markFailedFinal$lambda$7(retryCount, message, (QueuedScreenshotTask) obj);
            }
        }, continuation);
        return updateTask == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateTask : Unit.INSTANCE;
    }

    static final QueuedScreenshotTask markFailedFinal$lambda$7(int $retryCount, String $message, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return QueuedScreenshotTask.copy$default(task, null, null, null, null, null, null, ScreenshotTaskStatus.FAILED_FINAL, 0L, System.currentTimeMillis(), Long.MAX_VALUE, $retryCount, $message, null, null, false, false, null, 123071, null);
    }

    public final Object removeTerminalTasks(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new QueuedScreenshotTaskStore$removeTerminalTasks$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object updateTask(String taskId, Function1<? super QueuedScreenshotTask, QueuedScreenshotTask> function1, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new QueuedScreenshotTaskStore$updateTask$2(this, taskId, function1, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int statusPriority(ScreenshotTaskStatus status) {
        switch (WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
            case 7:
            case 8:
                return 5;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final ScreenshotTaskSnapshot readSnapshotLocked() {
        Object m7212constructorimpl;
        int i = 1;
        List list = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (!this.storeFile.exists()) {
            return new ScreenshotTaskSnapshot(list, i, (DefaultConstructorMarker) (objArr3 == true ? 1 : 0));
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            QueuedScreenshotTaskStore queuedScreenshotTaskStore = this;
            Json json = queuedScreenshotTaskStore.json;
            String readText$default = FilesKt.readText$default(queuedScreenshotTaskStore.storeFile, null, 1, null);
            json.getSerializersModule();
            m7212constructorimpl = Result.m7212constructorimpl((ScreenshotTaskSnapshot) json.decodeFromString(ScreenshotTaskSnapshot.INSTANCE.serializer(), readText$default));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
        }
        ScreenshotTaskSnapshot screenshotTaskSnapshot = new ScreenshotTaskSnapshot((List) (objArr2 == true ? 1 : 0), i, (DefaultConstructorMarker) (objArr == true ? 1 : 0));
        if (Result.m7218isFailureimpl(m7212constructorimpl)) {
            m7212constructorimpl = screenshotTaskSnapshot;
        }
        return (ScreenshotTaskSnapshot) m7212constructorimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeSnapshotLocked(ScreenshotTaskSnapshot snapshot) {
        File parentFile = this.storeFile.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        File file = this.storeFile;
        StringFormat stringFormat = this.json;
        stringFormat.getSerializersModule();
        FilesKt.writeText$default(file, stringFormat.encodeToString(ScreenshotTaskSnapshot.INSTANCE.serializer(), snapshot), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTerminal(ScreenshotTaskStatus $this$isTerminal) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$isTerminal.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return false;
            case 6:
            case 7:
            case 8:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: QueuedScreenshotTaskStore.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotTaskStore$Companion;", "", "<init>", "()V", "MAX_TASKS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
