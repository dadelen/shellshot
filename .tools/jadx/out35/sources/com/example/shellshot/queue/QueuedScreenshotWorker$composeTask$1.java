package com.example.shellshot.queue;

import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19}, l = {118, 120, 121, 123, WorkQueueKt.MASK, 131, 132, 146, 166, 172, 179, 183, 199, ComposerKt.reuseKey, 221, 224, 226, 227, 232, 234}, m = "composeTask", n = {"task", "task", "task", "settings", "task", "settings", "resolution", "$this$composeTask_u24lambda_u242\\1", "$i$a$-run-QueuedScreenshotWorker$composeTask$candidate$1\\1\\122\\0", "task", "settings", "resolution", "candidate", "task", "settings", "resolution", "candidate", "task", "settings", "resolution", "candidate", "$this$composeTask_u24lambda_u243\\2", "$i$a$-run-QueuedScreenshotWorker$composeTask$template$1\\2\\131\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "$this$composeTask_u24lambda_u244\\3", "$i$a$-runCatching-QueuedScreenshotWorker$composeTask$2\\3\\165\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "throwable\\4", "$i$a$-getOrElse-QueuedScreenshotWorker$composeTask$3\\4\\171\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "$this$composeTask_u24lambda_u246\\5", "$i$a$-runCatching-QueuedScreenshotWorker$composeTask$4\\5\\178\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "it\\8", "$i$a$-getOrElse-QueuedScreenshotWorker$composeTask$6\\8\\182\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "$this$composeTask_u24lambda_u249\\9", "$i$a$-runCatching-QueuedScreenshotWorker$composeTask$output$1\\9\\198\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "it\\12", "$i$a$-getOrElse-QueuedScreenshotWorker$composeTask$output$3\\12\\206\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "pathKey\\13", "$i$a$-let-QueuedScreenshotWorker$composeTask$7\\13\\220\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "pathKey\\14", "$i$a$-let-QueuedScreenshotWorker$composeTask$8\\14\\223\\0", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "deleteResult", "task", "settings", "resolution", "candidate", "template", "invalidTemplateReason", "sourceBitmap", "composedBitmap", "output", "deleteResult"}, s = {"L$0", "L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$composeTask$1 extends ContinuationImpl {
    int I$0;
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
    QueuedScreenshotWorker$composeTask$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$composeTask$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object composeTask;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        composeTask = this.this$0.composeTask(null, this);
        return composeTask;
    }
}
