package com.example.shellshot.media;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BitmapLoader.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.BitmapLoader$loadBitmap$4", f = "BitmapLoader.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class BitmapLoader$loadBitmap$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ String $absolutePath;
    final /* synthetic */ int $targetHeight;
    final /* synthetic */ int $targetWidth;
    int label;
    final /* synthetic */ BitmapLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BitmapLoader$loadBitmap$4(BitmapLoader bitmapLoader, String str, int i, int i2, Continuation<? super BitmapLoader$loadBitmap$4> continuation) {
        super(2, continuation);
        this.this$0 = bitmapLoader;
        this.$absolutePath = str;
        this.$targetWidth = i;
        this.$targetHeight = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BitmapLoader$loadBitmap$4(this.this$0, this.$absolutePath, this.$targetWidth, this.$targetHeight, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((BitmapLoader$loadBitmap$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Bitmap decodeBitmap;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                BitmapLoader bitmapLoader = this.this$0;
                ImageDecoder.Source createSource = ImageDecoder.createSource(new File(this.$absolutePath));
                Intrinsics.checkNotNullExpressionValue(createSource, "createSource(...)");
                decodeBitmap = bitmapLoader.decodeBitmap(createSource, this.$absolutePath, this.$targetWidth, this.$targetHeight);
                return decodeBitmap;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
