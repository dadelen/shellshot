package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, l = {537, 553, 571, 581}, m = "handleFailure", n = {"task", "decision", "finalRetryCount", "task", "decision", "nextRetryCount", "task", "decision", "nextRetryCount", "delayMillis", "nextAttemptAtMillis", "task", "decision", "nextRetryCount", "delayMillis", "nextAttemptAtMillis"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "J$0", "J$1", "L$0", "L$1", "I$0", "J$0", "J$1"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$handleFailure$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$handleFailure$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$handleFailure$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object handleFailure;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        handleFailure = this.this$0.handleFailure(null, null, this);
        return handleFailure;
    }
}
