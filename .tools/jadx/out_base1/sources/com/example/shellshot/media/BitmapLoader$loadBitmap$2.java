package com.example.shellshot.media;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
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
@DebugMetadata(c = "com.example.shellshot.media.BitmapLoader$loadBitmap$2", f = "BitmapLoader.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class BitmapLoader$loadBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ int $targetHeight;
    final /* synthetic */ int $targetWidth;
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ BitmapLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BitmapLoader$loadBitmap$2(BitmapLoader bitmapLoader, Uri uri, int i, int i2, Continuation<? super BitmapLoader$loadBitmap$2> continuation) {
        super(2, continuation);
        this.this$0 = bitmapLoader;
        this.$uri = uri;
        this.$targetWidth = i;
        this.$targetHeight = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BitmapLoader$loadBitmap$2(this.this$0, this.$uri, this.$targetWidth, this.$targetHeight, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((BitmapLoader$loadBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ContentResolver contentResolver;
        Bitmap decodeBitmap;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                BitmapLoader bitmapLoader = this.this$0;
                contentResolver = this.this$0.contentResolver;
                ImageDecoder.Source createSource = ImageDecoder.createSource(contentResolver, this.$uri);
                Intrinsics.checkNotNullExpressionValue(createSource, "createSource(...)");
                String uri = this.$uri.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                decodeBitmap = bitmapLoader.decodeBitmap(createSource, uri, this.$targetWidth, this.$targetHeight);
                return decodeBitmap;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
