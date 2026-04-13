package com.example.shellshot.media;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import com.example.shellshot.utils.ShellLogger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BitmapLoader.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000fJ&\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0012J(\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J(\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/example/shellshot/media/BitmapLoader;", "", "contentResolver", "Landroid/content/ContentResolver;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Landroid/content/ContentResolver;Lcom/example/shellshot/utils/ShellLogger;)V", "loadBitmap", "Landroid/graphics/Bitmap;", "uri", "Landroid/net/Uri;", "targetWidth", "", "targetHeight", "(Landroid/net/Uri;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "absolutePath", "", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeBitmap", "source", "Landroid/graphics/ImageDecoder$Source;", "sourceDescription", "computeSampleSize", "sourceWidth", "sourceHeight", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class BitmapLoader {

    @Deprecated
    public static final String TAG = "BitmapLoader";
    private final ContentResolver contentResolver;
    private final ShellLogger logger;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public BitmapLoader(ContentResolver contentResolver, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.contentResolver = contentResolver;
        this.logger = logger;
    }

    public final Object loadBitmap(Uri uri, int targetWidth, int targetHeight, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new BitmapLoader$loadBitmap$2(this, uri, targetWidth, targetHeight, null), continuation);
    }

    public final Object loadBitmap(String absolutePath, int targetWidth, int targetHeight, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new BitmapLoader$loadBitmap$4(this, absolutePath, targetWidth, targetHeight, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap decodeBitmap(ImageDecoder.Source source, String sourceDescription, int targetWidth, int targetHeight) {
        final int safeTargetWidth = Math.max(1, targetWidth);
        final int safeTargetHeight = Math.max(1, targetHeight);
        try {
            Bitmap decodeBitmap = ImageDecoder.decodeBitmap(source, new ImageDecoder.OnHeaderDecodedListener() { // from class: com.example.shellshot.media.BitmapLoader$$ExternalSyntheticLambda0
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source2) {
                    BitmapLoader.decodeBitmap$lambda$0(BitmapLoader.this, safeTargetWidth, safeTargetHeight, imageDecoder, imageInfo, source2);
                }
            });
            this.logger.d(TAG, "解码成功 source=" + sourceDescription + " size=" + decodeBitmap.getWidth() + "x" + decodeBitmap.getHeight());
            Intrinsics.checkNotNull(decodeBitmap);
            return decodeBitmap;
        } catch (Throwable throwable) {
            this.logger.e(TAG, "解码失败 source=" + sourceDescription, throwable);
            throw throwable;
        }
    }

    static final void decodeBitmap$lambda$0(BitmapLoader this$0, int $safeTargetWidth, int $safeTargetHeight, ImageDecoder decoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(imageInfo, "imageInfo");
        Intrinsics.checkNotNullParameter(source, "<unused var>");
        int sampleSize = this$0.computeSampleSize(imageInfo.getSize().getWidth(), imageInfo.getSize().getHeight(), $safeTargetWidth, $safeTargetHeight);
        decoder.setAllocator(1);
        decoder.setMutableRequired(false);
        decoder.setTargetSampleSize(sampleSize);
    }

    private final int computeSampleSize(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
        int sampleSize = 1;
        int currentWidth = sourceWidth;
        int currentHeight = sourceHeight;
        while (currentWidth / 2 >= targetWidth && currentHeight / 2 >= targetHeight) {
            currentWidth /= 2;
            currentHeight /= 2;
            sampleSize *= 2;
        }
        return Math.max(1, sampleSize);
    }

    /* compiled from: BitmapLoader.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/media/BitmapLoader$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
