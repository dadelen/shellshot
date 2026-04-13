package com.example.shellshot.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
@DebugMetadata(c = "com.example.shellshot.ui.components.TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2", f = "TemplatePreviewThumbnail.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ImageBitmap>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $path;
    final /* synthetic */ int $targetHeight;
    final /* synthetic */ int $targetWidth;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2(Context context, String str, int i, int i2, Continuation<? super TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$path = str;
        this.$targetWidth = i;
        this.$targetHeight = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2 templatePreviewThumbnailKt$loadTemplatePreviewBitmap$2 = new TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2(this.$context, this.$path, this.$targetWidth, this.$targetHeight, continuation);
        templatePreviewThumbnailKt$loadTemplatePreviewBitmap$2.L$0 = obj;
        return templatePreviewThumbnailKt$loadTemplatePreviewBitmap$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ImageBitmap> continuation) {
        return ((TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7212constructorimpl;
        InputStream openTemplatePreviewStream;
        int calculateInSampleSize;
        InputStream openTemplatePreviewStream2;
        ImageBitmap imageBitmap;
        InputStream inputStream;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Context context = this.$context;
                String str = this.$path;
                int i = this.$targetWidth;
                int i2 = this.$targetHeight;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    openTemplatePreviewStream = TemplatePreviewThumbnailKt.openTemplatePreviewStream(context, str);
                    if (openTemplatePreviewStream != null) {
                        inputStream = openTemplatePreviewStream;
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
                    openTemplatePreviewStream2 = TemplatePreviewThumbnailKt.openTemplatePreviewStream(context, str);
                    if (openTemplatePreviewStream2 != null) {
                        inputStream = openTemplatePreviewStream2;
                        try {
                            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options2);
                            imageBitmap = decodeStream != null ? AndroidImageBitmap_androidKt.asImageBitmap(decodeStream) : null;
                            CloseableKt.closeFinally(inputStream, null);
                        } finally {
                            try {
                                throw th;
                            } finally {
                            }
                        }
                    } else {
                        imageBitmap = null;
                    }
                    m7212constructorimpl = Result.m7212constructorimpl(imageBitmap);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m7218isFailureimpl(m7212constructorimpl)) {
                    return null;
                }
                return m7212constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
