package com.example.shellshot.media;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LatestScreenshotResolver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/media/ResolveQueuedTaskResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTaskDetailed$2", f = "LatestScreenshotResolver.kt", i = {1, 1}, l = {47, 89}, m = "invokeSuspend", n = {"screenshotRelativePath", "configuredPathEvaluation"}, s = {"L$0", "L$1"})
/* loaded from: classes11.dex */
final class LatestScreenshotResolver$resolveQueuedTaskDetailed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ResolveQueuedTaskResult>, Object> {
    final /* synthetic */ String $absolutePath;
    final /* synthetic */ Uri $changedUri;
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $relativePath;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ LatestScreenshotResolver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LatestScreenshotResolver$resolveQueuedTaskDetailed$2(LatestScreenshotResolver latestScreenshotResolver, String str, String str2, String str3, Uri uri, Continuation<? super LatestScreenshotResolver$resolveQueuedTaskDetailed$2> continuation) {
        super(2, continuation);
        this.this$0 = latestScreenshotResolver;
        this.$absolutePath = str;
        this.$displayName = str2;
        this.$relativePath = str3;
        this.$changedUri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LatestScreenshotResolver$resolveQueuedTaskDetailed$2(this.this$0, this.$absolutePath, this.$displayName, this.$relativePath, this.$changedUri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ResolveQueuedTaskResult> continuation) {
        return ((LatestScreenshotResolver$resolveQueuedTaskDetailed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c6  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r25) {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTaskDetailed$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
