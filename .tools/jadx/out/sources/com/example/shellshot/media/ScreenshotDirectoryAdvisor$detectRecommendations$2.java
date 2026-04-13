package com.example.shellshot.media;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ScreenshotDirectoryAdvisor.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.ScreenshotDirectoryAdvisor$detectRecommendations$2", f = "ScreenshotDirectoryAdvisor.kt", i = {0, 0, 0, 0, 0}, l = {40}, m = "invokeSuspend", n = {"$this$withContext", "normalizedCurrent", "accumulators", "$this$invokeSuspend_u24lambda_u242\\5", "$i$a$-runCatching-ScreenshotDirectoryAdvisor$detectRecommendations$2$mediaStoreCandidates$1\\5\\39\\0"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
/* loaded from: classes11.dex */
final class ScreenshotDirectoryAdvisor$detectRecommendations$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ScreenshotDirectoryRecommendation>>, Object> {
    final /* synthetic */ String $currentRelativePath;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ ScreenshotDirectoryAdvisor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScreenshotDirectoryAdvisor$detectRecommendations$2(String str, int i, ScreenshotDirectoryAdvisor screenshotDirectoryAdvisor, Continuation<? super ScreenshotDirectoryAdvisor$detectRecommendations$2> continuation) {
        super(2, continuation);
        this.$currentRelativePath = str;
        this.$limit = i;
        this.this$0 = screenshotDirectoryAdvisor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScreenshotDirectoryAdvisor$detectRecommendations$2 screenshotDirectoryAdvisor$detectRecommendations$2 = new ScreenshotDirectoryAdvisor$detectRecommendations$2(this.$currentRelativePath, this.$limit, this.this$0, continuation);
        screenshotDirectoryAdvisor$detectRecommendations$2.L$0 = obj;
        return screenshotDirectoryAdvisor$detectRecommendations$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ScreenshotDirectoryRecommendation>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<ScreenshotDirectoryRecommendation>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<ScreenshotDirectoryRecommendation>> continuation) {
        return ((ScreenshotDirectoryAdvisor$detectRecommendations$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0257 A[LOOP:2: B:54:0x0251->B:56:0x0257, LOOP_END] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r30) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDirectoryAdvisor$detectRecommendations$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
