package com.example.shellshot.ui.components;

import android.content.Context;
import androidx.compose.runtime.ProduceStateScope;
import androidx.compose.ui.graphics.ImageBitmap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: TemplatePreviewThumbnail.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/ProduceStateScope;", "Landroidx/compose/ui/graphics/ImageBitmap;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1", f = "TemplatePreviewThumbnail.kt", i = {0}, l = {62}, m = "invokeSuspend", n = {"$this$produceState"}, s = {"L$0"})
/* loaded from: classes11.dex */
final class TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1 extends SuspendLambda implements Function2<ProduceStateScope<ImageBitmap>, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $previewPath;
    final /* synthetic */ int $targetHeightPx;
    final /* synthetic */ int $targetWidthPx;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1(Context context, String str, int i, int i2, String str2, Continuation<? super TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$previewPath = str;
        this.$targetWidthPx = i;
        this.$targetHeightPx = i2;
        this.$cacheKey = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1 templatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1 = new TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1(this.$context, this.$previewPath, this.$targetWidthPx, this.$targetHeightPx, this.$cacheKey, continuation);
        templatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1.L$0 = obj;
        return templatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProduceStateScope<ImageBitmap> produceStateScope, Continuation<? super Unit> continuation) {
        return ((TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1) create(produceStateScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            androidx.compose.runtime.ProduceStateScope r0 = (androidx.compose.runtime.ProduceStateScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L1a;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r9
            goto L3a
        L1a:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r2 = r0.getValue()
            if (r2 != 0) goto L49
            android.content.Context r2 = r8.$context
            java.lang.String r3 = r8.$previewPath
            int r4 = r8.$targetWidthPx
            int r5 = r8.$targetHeightPx
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r0
            r7 = 1
            r8.label = r7
            java.lang.Object r2 = com.example.shellshot.ui.components.TemplatePreviewThumbnailKt.access$loadTemplatePreviewBitmap(r2, r3, r4, r5, r6)
            if (r2 != r1) goto L3a
            return r1
        L3a:
            r1 = r2
            androidx.compose.ui.graphics.ImageBitmap r1 = (androidx.compose.ui.graphics.ImageBitmap) r1
            if (r1 == 0) goto L46
            com.example.shellshot.ui.components.TemplatePreviewBitmapCache r2 = com.example.shellshot.ui.components.TemplatePreviewBitmapCache.INSTANCE
            java.lang.String r3 = r8.$cacheKey
            r2.put(r3, r1)
        L46:
            r0.setValue(r1)
        L49:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.TemplatePreviewThumbnailKt$TemplatePreviewThumbnail$previewBitmap$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
