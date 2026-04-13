package com.example.shellshot.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1", f = "MainViewModel.kt", i = {1, 2}, l = {277, 278, 282}, m = "invokeSuspend", n = {"settings", "settings"}, s = {"L$0", "L$0"})
/* loaded from: classes7.dex */
final class MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $markInitialized;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1(MainViewModel mainViewModel, boolean z, Continuation<? super MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$markInitialized = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1(this.this$0, this.$markInitialized, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0090 A[Catch: all -> 0x0031, TRY_LEAVE, TryCatch #0 {all -> 0x0031, blocks: (B:8:0x0017, B:13:0x0024, B:14:0x0089, B:16:0x0090, B:20:0x002c, B:22:0x005b, B:26:0x0045), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0088 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
