package com.example.shellshot.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ImageBitmap;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplatePreviewThumbnail.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/graphics/ImageBitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2", f = "TemplatePreviewThumbnail.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ImageBitmap>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $targetHeight;
    final /* synthetic */ int $targetWidth;
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2(Context context, Uri uri, int i, int i2, Continuation<? super TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$uri = uri;
        this.$targetWidth = i;
        this.$targetHeight = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2 templatePreviewThumbnailKt$loadUriPreviewBitmap$2 = new TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2(this.$context, this.$uri, this.$targetWidth, this.$targetHeight, continuation);
        templatePreviewThumbnailKt$loadUriPreviewBitmap$2.L$0 = obj;
        return templatePreviewThumbnailKt$loadUriPreviewBitmap$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ImageBitmap> continuation) {
        return ((TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7216constructorimpl;
        int calculateInSampleSize;
        ImageBitmap imageBitmap;
        InputStream inputStream;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Context context = this.$context;
                Uri uri = this.$uri;
                int i = this.$targetWidth;
                int i2 = this.$targetHeight;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                    if (openInputStream != null) {
                        inputStream = openInputStream;
                        try {
                            BitmapFactory.decodeStream(inputStream, null, options);
                            CloseableKt.closeFinally(inputStream, null);
                        } finally {
                        }
                    }
                    calculateInSampleSize = TemplatePreviewThumbnailKt.calculateInSampleSize(options.outWidth, options.outHeight, i, i2);
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options2.inSampleSize = calculateInSampleSize;
                    InputStream openInputStream2 = context.getContentResolver().openInputStream(uri);
                    if (openInputStream2 != null) {
                        inputStream = openInputStream2;
                        try {
                            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options2);
                            imageBitmap = decodeStream != null ? AndroidImageBitmap_androidKt.asImageBitmap(decodeStream) : null;
                            CloseableKt.closeFinally(inputStream, null);
                        } finally {
                        }
                    } else {
                        imageBitmap = null;
                    }
                    m7216constructorimpl = Result.m7216constructorimpl(imageBitmap);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7216constructorimpl = Result.m7216constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m7222isFailureimpl(m7216constructorimpl)) {
                    return null;
                }
                return m7216constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
