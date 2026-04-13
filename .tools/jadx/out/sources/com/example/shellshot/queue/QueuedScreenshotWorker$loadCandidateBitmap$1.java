package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {389, 395}, m = "loadCandidateBitmap", n = {"candidate", "resolutionSource", "template", "targets", "lastThrowable", "$this$forEach\\2", "element\\2", "target\\3", "$this$loadCandidateBitmap_u24lambda_u2420_u24lambda_u2417\\4", "$i$f$forEach\\2\\386", "$i$a$-forEach-QueuedScreenshotWorker$loadCandidateBitmap$3\\3\\796\\0", "$i$a$-runCatching-QueuedScreenshotWorker$loadCandidateBitmap$3$1\\4\\387\\3", "candidate", "resolutionSource", "template", "targets", "lastThrowable", "$this$forEach\\2", "element\\2", "target\\3", "$this$loadCandidateBitmap_u24lambda_u2420_u24lambda_u2417\\4", "$i$f$forEach\\2\\386", "$i$a$-forEach-QueuedScreenshotWorker$loadCandidateBitmap$3\\3\\796\\0", "$i$a$-runCatching-QueuedScreenshotWorker$loadCandidateBitmap$3$1\\4\\387\\3"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "I$0", "I$1", "I$2"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$loadCandidateBitmap$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$loadCandidateBitmap$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$loadCandidateBitmap$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object loadCandidateBitmap;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        loadCandidateBitmap = this.this$0.loadCandidateBitmap(null, null, null, this);
        return loadCandidateBitmap;
    }
}
