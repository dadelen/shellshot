package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8}, l = {62, 66, 69, 71, 75, 79, 81, 96, 101}, m = "prepareTask", n = {"task", "task", "task", "settings", "screenshotRelativePath", "task", "settings", "screenshotRelativePath", "resolution", "$this$prepareTask_u24lambda_u241\\1", "$i$a$-run-QueuedScreenshotWorker$prepareTask$candidate$1\\1\\70\\0", "task", "settings", "screenshotRelativePath", "resolution", "candidate", "task", "settings", "screenshotRelativePath", "resolution", "candidate", "task", "settings", "screenshotRelativePath", "resolution", "candidate", "stableResult", "task", "settings", "screenshotRelativePath", "resolution", "candidate", "stableResult", "task", "settings", "screenshotRelativePath", "resolution", "candidate", "stableResult"}, s = {"L$0", "L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$prepareTask$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$prepareTask$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$prepareTask$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object prepareTask;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        prepareTask = this.this$0.prepareTask(null, this);
        return prepareTask;
    }
}
