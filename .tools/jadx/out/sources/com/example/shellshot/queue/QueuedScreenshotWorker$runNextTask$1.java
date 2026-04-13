package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {800, 43, 48, 50}, m = "runNextTask", n = {"$this$withLock_u24default\\1", "$i$f$withLock\\1\\42", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\42", "$i$a$-withLock$default-QueuedScreenshotWorker$runNextTask$2\\2\\802\\0", "$this$withLock_u24default\\1", "task\\2", "$i$f$withLock\\1\\42", "$i$a$-withLock$default-QueuedScreenshotWorker$runNextTask$2\\2\\802\\0", "$this$withLock_u24default\\1", "task\\2", "$i$f$withLock\\1\\42", "$i$a$-withLock$default-QueuedScreenshotWorker$runNextTask$2\\2\\802\\0"}, s = {"L$0", "I$0", "L$0", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$runNextTask$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$runNextTask$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$runNextTask$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.runNextTask(this);
    }
}
