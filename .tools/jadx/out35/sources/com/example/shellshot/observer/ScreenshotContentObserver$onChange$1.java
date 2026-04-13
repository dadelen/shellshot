package com.example.shellshot.observer;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ScreenshotContentObserver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.observer.ScreenshotContentObserver$onChange$1", f = "ScreenshotContentObserver.kt", i = {}, l = {31, 32}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class ScreenshotContentObserver$onChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ ScreenshotContentObserver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScreenshotContentObserver$onChange$1(ScreenshotContentObserver screenshotContentObserver, Uri uri, Continuation<? super ScreenshotContentObserver$onChange$1> continuation) {
        super(2, continuation);
        this.this$0 = screenshotContentObserver;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ScreenshotContentObserver$onChange$1(this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ScreenshotContentObserver$onChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L19;
                case 1: goto L15;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L11:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L41
        L15:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L2f
        L19:
            kotlin.ResultKt.throwOnFailure(r6)
            com.example.shellshot.observer.ScreenshotContentObserver r1 = r5.this$0
            long r1 = com.example.shellshot.observer.ScreenshotContentObserver.access$getDebounceMillis$p(r1)
            r3 = r5
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r5.label = r4
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r1, r3)
            if (r1 != r0) goto L2f
            return r0
        L2f:
            com.example.shellshot.observer.ScreenshotContentObserver r1 = r5.this$0
            kotlin.jvm.functions.Function2 r1 = com.example.shellshot.observer.ScreenshotContentObserver.access$getOnContentChanged$p(r1)
            android.net.Uri r2 = r5.$uri
            r3 = 2
            r5.label = r3
            java.lang.Object r1 = r1.invoke(r2, r5)
            if (r1 != r0) goto L41
            return r0
        L41:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.observer.ScreenshotContentObserver$onChange$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
