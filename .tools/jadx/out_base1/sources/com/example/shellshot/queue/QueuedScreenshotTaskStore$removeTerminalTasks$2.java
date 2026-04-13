package com.example.shellshot.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotTaskStore$removeTerminalTasks$2", f = "QueuedScreenshotTaskStore.kt", i = {0, 0}, l = {319}, m = "invokeSuspend", n = {"$this$withLock_u24default\\1", "$i$f$withLock\\1\\248"}, s = {"L$0", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotTaskStore$removeTerminalTasks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QueuedScreenshotTaskStore this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotTaskStore$removeTerminalTasks$2(QueuedScreenshotTaskStore queuedScreenshotTaskStore, Continuation<? super QueuedScreenshotTaskStore$removeTerminalTasks$2> continuation) {
        super(2, continuation);
        this.this$0 = queuedScreenshotTaskStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueuedScreenshotTaskStore$removeTerminalTasks$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((QueuedScreenshotTaskStore$removeTerminalTasks$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Mutex mutex;
        QueuedScreenshotTaskStore queuedScreenshotTaskStore;
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
        int i2 = 0;
        try {
            readSnapshotLocked = queuedScreenshotTaskStore.readSnapshotLocked();
            Iterable tasks = readSnapshotLocked.getTasks();
            Collection arrayList = new ArrayList();
            for (Object obj2 : tasks) {
                int i3 = i2;
                isTerminal = queuedScreenshotTaskStore.isTerminal(((QueuedScreenshotTask) obj2).getStatus());
                if (!isTerminal) {
                    arrayList.add(obj2);
                }
                i2 = i3;
            }
            queuedScreenshotTaskStore.writeSnapshotLocked(readSnapshotLocked.copy((List) arrayList));
            Unit unit = Unit.INSTANCE;
            mutex.unlock(obj);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(obj);
            throw th;
        }
    }
}
