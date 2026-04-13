package com.example.shellshot.queue;

import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotWorker", f = "QueuedScreenshotWorker.kt", i = {0, 0, 0, 0}, l = {InputDeviceCompat.SOURCE_DPAD}, m = "deleteOriginalIfNeeded", n = {"candidate", "$this$deleteOriginalIfNeeded_u24lambda_u2425\\1", "autoDeleteOriginal", "$i$a$-runCatching-QueuedScreenshotWorker$deleteOriginalIfNeeded$2\\1\\512\\0"}, s = {"L$0", "L$1", "Z$0", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotWorker$deleteOriginalIfNeeded$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ QueuedScreenshotWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotWorker$deleteOriginalIfNeeded$1(QueuedScreenshotWorker queuedScreenshotWorker, Continuation<? super QueuedScreenshotWorker$deleteOriginalIfNeeded$1> continuation) {
        super(continuation);
        this.this$0 = queuedScreenshotWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object deleteOriginalIfNeeded;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        deleteOriginalIfNeeded = this.this$0.deleteOriginalIfNeeded(false, null, this);
        return deleteOriginalIfNeeded;
    }
}
