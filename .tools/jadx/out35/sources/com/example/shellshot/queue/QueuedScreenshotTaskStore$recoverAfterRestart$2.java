package com.example.shellshot.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotTaskStore$recoverAfterRestart$2", f = "QueuedScreenshotTaskStore.kt", i = {0, 0}, l = {319}, m = "invokeSuspend", n = {"$this$withLock_u24default\\1", "$i$f$withLock\\1\\119"}, s = {"L$0", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotTaskStore$recoverAfterRestart$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends QueuedScreenshotTask>>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QueuedScreenshotTaskStore this$0;

    /* compiled from: QueuedScreenshotTaskStore.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenshotTaskStatus.values().length];
            try {
                iArr[ScreenshotTaskStatus.COMPOSING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotTaskStore$recoverAfterRestart$2(QueuedScreenshotTaskStore queuedScreenshotTaskStore, Continuation<? super QueuedScreenshotTaskStore$recoverAfterRestart$2> continuation) {
        super(2, continuation);
        this.this$0 = queuedScreenshotTaskStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueuedScreenshotTaskStore$recoverAfterRestart$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends QueuedScreenshotTask>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<QueuedScreenshotTask>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<QueuedScreenshotTask>> continuation) {
        return ((QueuedScreenshotTaskStore$recoverAfterRestart$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Mutex mutex;
        QueuedScreenshotTaskStore queuedScreenshotTaskStore;
        Object obj;
        ScreenshotTaskSnapshot readSnapshotLocked;
        boolean isTerminal;
        Collection collection;
        QueuedScreenshotTask queuedScreenshotTask;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = 1;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutex = this.this$0.mutex;
                queuedScreenshotTaskStore = this.this$0;
                obj = null;
                this.L$0 = mutex;
                this.L$1 = queuedScreenshotTaskStore;
                this.I$0 = 0;
                this.label = 1;
                if (mutex.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                int i2 = this.I$0;
                queuedScreenshotTaskStore = (QueuedScreenshotTaskStore) this.L$1;
                obj = null;
                mutex = (Mutex) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i3 = 0;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            readSnapshotLocked = queuedScreenshotTaskStore.readSnapshotLocked();
            Iterable<QueuedScreenshotTask> tasks = readSnapshotLocked.getTasks();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(tasks, 10));
            for (QueuedScreenshotTask queuedScreenshotTask2 : tasks) {
                if (WhenMappings.$EnumSwitchMapping$0[queuedScreenshotTask2.getStatus().ordinal()] == i) {
                    collection = arrayList;
                    queuedScreenshotTask = QueuedScreenshotTask.copy$default(queuedScreenshotTask2, null, null, null, null, null, null, ScreenshotTaskStatus.READY, 0L, currentTimeMillis, currentTimeMillis, 0, "recovered_after_restart", null, null, false, false, null, 128191, null);
                } else {
                    collection = arrayList;
                    queuedScreenshotTask = queuedScreenshotTask2;
                }
                collection.add(queuedScreenshotTask);
                arrayList = collection;
                i = 1;
            }
            List list = (List) arrayList;
            queuedScreenshotTaskStore.writeSnapshotLocked(readSnapshotLocked.copy(CollectionsKt.takeLast(list, 120)));
            Collection arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                int i4 = i3;
                isTerminal = queuedScreenshotTaskStore.isTerminal(((QueuedScreenshotTask) obj2).getStatus());
                if (!isTerminal) {
                    arrayList2.add(obj2);
                }
                i3 = i4;
            }
            return (List) arrayList2;
        } finally {
            mutex.unlock(obj);
        }
    }
}
