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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotTaskStore$updateTask$2", f = "QueuedScreenshotTaskStore.kt", i = {0, 0}, l = {319}, m = "invokeSuspend", n = {"$this$withLock_u24default\\1", "$i$f$withLock\\1\\258"}, s = {"L$0", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotTaskStore$updateTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $taskId;
    final /* synthetic */ Function1<QueuedScreenshotTask, QueuedScreenshotTask> $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ QueuedScreenshotTaskStore this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    QueuedScreenshotTaskStore$updateTask$2(QueuedScreenshotTaskStore queuedScreenshotTaskStore, String str, Function1<? super QueuedScreenshotTask, QueuedScreenshotTask> function1, Continuation<? super QueuedScreenshotTaskStore$updateTask$2> continuation) {
        super(2, continuation);
        this.this$0 = queuedScreenshotTaskStore;
        this.$taskId = str;
        this.$transform = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueuedScreenshotTaskStore$updateTask$2(this.this$0, this.$taskId, this.$transform, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((QueuedScreenshotTaskStore$updateTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Mutex mutex;
        QueuedScreenshotTaskStore queuedScreenshotTaskStore;
        String str;
        Function1<QueuedScreenshotTask, QueuedScreenshotTask> function1;
        Object obj;
        ScreenshotTaskSnapshot readSnapshotLocked;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutex = this.this$0.mutex;
                queuedScreenshotTaskStore = this.this$0;
                str = this.$taskId;
                function1 = this.$transform;
                obj = null;
                this.L$0 = mutex;
                this.L$1 = queuedScreenshotTaskStore;
                this.L$2 = str;
                this.L$3 = function1;
                this.I$0 = 0;
                this.label = 1;
                if (mutex.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                int i = this.I$0;
                function1 = (Function1) this.L$3;
                str = (String) this.L$2;
                queuedScreenshotTaskStore = (QueuedScreenshotTaskStore) this.L$1;
                obj = null;
                mutex = (Mutex) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = 0;
        try {
            readSnapshotLocked = queuedScreenshotTaskStore.readSnapshotLocked();
            Iterable<QueuedScreenshotTask> tasks = readSnapshotLocked.getTasks();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(tasks, 10));
            for (QueuedScreenshotTask queuedScreenshotTask : tasks) {
                int i3 = i2;
                arrayList.add(Intrinsics.areEqual(queuedScreenshotTask.getId(), str) ? function1.invoke(queuedScreenshotTask) : queuedScreenshotTask);
                i2 = i3;
            }
            queuedScreenshotTaskStore.writeSnapshotLocked(readSnapshotLocked.copy(CollectionsKt.takeLast((List) arrayList, 120)));
            Unit unit = Unit.INSTANCE;
            mutex.unlock(obj);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(obj);
            throw th;
        }
    }
}
