package com.example.shellshot.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ScreenshotDeduplicator.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.ScreenshotDeduplicator", f = "ScreenshotDeduplicator.kt", i = {0, 0, 0}, l = {93}, m = "tryBegin", n = {"candidate", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\20"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes11.dex */
final class ScreenshotDeduplicator$tryBegin$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ScreenshotDeduplicator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScreenshotDeduplicator$tryBegin$1(ScreenshotDeduplicator screenshotDeduplicator, Continuation<? super ScreenshotDeduplicator$tryBegin$1> continuation) {
        super(continuation);
        this.this$0 = screenshotDeduplicator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.tryBegin(null, this);
    }
}
