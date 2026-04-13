package com.example.shellshot.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: LatestScreenshotResolver.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.LatestScreenshotResolver", f = "LatestScreenshotResolver.kt", i = {0, 0, 0, 0}, l = {33}, m = "resolveQueuedTask", n = {"absolutePath", "displayName", "relativePath", "changedUri"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes11.dex */
final class LatestScreenshotResolver$resolveQueuedTask$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LatestScreenshotResolver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LatestScreenshotResolver$resolveQueuedTask$1(LatestScreenshotResolver latestScreenshotResolver, Continuation<? super LatestScreenshotResolver$resolveQueuedTask$1> continuation) {
        super(continuation);
        this.this$0 = latestScreenshotResolver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolveQueuedTask(null, null, null, null, this);
    }
}
