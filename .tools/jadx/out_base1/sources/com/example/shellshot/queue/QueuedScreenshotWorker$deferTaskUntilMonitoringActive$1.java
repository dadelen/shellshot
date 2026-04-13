package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0}, l = {674}, m = "deferTaskUntilMonitoringActive", n = {"task", "nextAttemptAtMillis"}, s = {"L$0", "J$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$deferTaskUntilMonitoringActive$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object deferTaskUntilMonitoringActive;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        deferTaskUntilMonitoringActive = this.this$0.deferTaskUntilMonitoringActive(null, this);
        return deferTaskUntilMonitoringActive;
    }
}
