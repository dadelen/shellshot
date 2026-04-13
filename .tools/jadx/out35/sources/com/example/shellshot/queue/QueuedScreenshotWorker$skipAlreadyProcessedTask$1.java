package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0, 0}, l = {659}, m = "skipAlreadyProcessedTask", n = {"task", "candidate", "reason"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$skipAlreadyProcessedTask$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$skipAlreadyProcessedTask$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$skipAlreadyProcessedTask$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object skipAlreadyProcessedTask;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        skipAlreadyProcessedTask = this.this$0.skipAlreadyProcessedTask(null, null, null, this);
        return skipAlreadyProcessedTask;
    }
}
