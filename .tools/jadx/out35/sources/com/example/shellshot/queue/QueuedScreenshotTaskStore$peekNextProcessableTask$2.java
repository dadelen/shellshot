package com.example.shellshot.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotTaskStore$peekNextProcessableTask$2", f = "QueuedScreenshotTaskStore.kt", i = {0, 0}, l = {319}, m = "invokeSuspend", n = {"$this$withLock_u24default\\1", "$i$f$withLock\\1\\140"}, s = {"L$0", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotTaskStore$peekNextProcessableTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QueuedScreenshotTask>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QueuedScreenshotTaskStore this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotTaskStore$peekNextProcessableTask$2(QueuedScreenshotTaskStore queuedScreenshotTaskStore, Continuation<? super QueuedScreenshotTaskStore$peekNextProcessableTask$2> continuation) {
        super(2, continuation);
        this.this$0 = queuedScreenshotTaskStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueuedScreenshotTaskStore$peekNextProcessableTask$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QueuedScreenshotTask> continuation) {
        return ((QueuedScreenshotTaskStore$peekNextProcessableTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Mutex mutex;
        final QueuedScreenshotTaskStore queuedScreenshotTaskStore;
        Object obj;
        ScreenshotTaskSnapshot readSnapshotLocked;
        boolean isTerminal;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
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
                int i = this.I$0;
                queuedScreenshotTaskStore = (QueuedScreenshotTaskStore) this.L$1;
                obj = null;
                mutex = (Mutex) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            readSnapshotLocked = queuedScreenshotTaskStore.readSnapshotLocked();
            Iterable tasks = readSnapshotLocked.getTasks();
            Collection arrayList = new ArrayList();
            for (Object obj2 : tasks) {
                QueuedScreenshotTask queuedScreenshotTask = (QueuedScreenshotTask) obj2;
                isTerminal = queuedScreenshotTaskStore.isTerminal(queuedScreenshotTask.getStatus());
                if (!isTerminal && queuedScreenshotTask.getNextAttemptAtMillis() <= currentTimeMillis) {
                    arrayList.add(obj2);
                }
            }
            final Comparator comparator = new Comparator() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$peekNextProcessableTask$2$invokeSuspend$lambda$4$$inlined$compareBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int statusPriority;
                    int statusPriority2;
                    statusPriority = QueuedScreenshotTaskStore.this.statusPriority(((QueuedScreenshotTask) t).getStatus());
                    Integer valueOf = Integer.valueOf(statusPriority);
                    statusPriority2 = QueuedScreenshotTaskStore.this.statusPriority(((QueuedScreenshotTask) t2).getStatus());
                    return ComparisonsKt.compareValues(valueOf, Integer.valueOf(statusPriority2));
                }
            };
            final Comparator comparator2 = new Comparator() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$peekNextProcessableTask$2$invokeSuspend$lambda$4$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator.compare(t, t2);
                    return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Long.valueOf(((QueuedScreenshotTask) t).getNextAttemptAtMillis()), Long.valueOf(((QueuedScreenshotTask) t2).getNextAttemptAtMillis()));
                }
            };
            return (QueuedScreenshotTask) CollectionsKt.firstOrNull(CollectionsKt.sortedWith((List) arrayList, new Comparator() { // from class: com.example.shellshot.queue.QueuedScreenshotTaskStore$peekNextProcessableTask$2$invokeSuspend$lambda$4$$inlined$thenBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator2.compare(t, t2);
                    return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Long.valueOf(((QueuedScreenshotTask) t).getDetectedAtMillis()), Long.valueOf(((QueuedScreenshotTask) t2).getDetectedAtMillis()));
                }
            }));
        } finally {
            mutex.unlock(obj);
        }
    }
}
