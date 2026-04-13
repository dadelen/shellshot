package com.example.shellshot.ui.components;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ImageBitmap;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TemplatePreviewThumbnail.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001a=\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a0\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010\u001a\u001a0\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010\u001d\u001a\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0002\u001a(\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¨\u0006#²\u0006\f\u0010$\u001a\u0004\u0018\u00010\u0013X\u008a\u0084\u0002²\u0006\f\u0010$\u001a\u0004\u0018\u00010\u0013X\u008a\u0084\u0002"}, d2 = {"TemplatePreviewThumbnail", "", "previewPath", "", "contentDescription", "modifier", "Landroidx/compose/ui/Modifier;", "cornerRadius", "Landroidx/compose/ui/unit/Dp;", "imagePadding", "selected", "", "TemplatePreviewThumbnail-nSlTg7c", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;FFZLandroidx/compose/runtime/Composer;II)V", "previewUri", "Landroid/net/Uri;", "TemplatePreviewThumbnail-6PoWaU8", "(Landroid/net/Uri;Ljava/lang/String;Landroidx/compose/ui/Modifier;FFLandroidx/compose/runtime/Composer;II)V", "loadTemplatePreviewBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "context", "Landroid/content/Context;", "path", "targetWidth", "", "targetHeight", "(Landroid/content/Context;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadUriPreviewBitmap", "uri", "(Landroid/content/Context;Landroid/net/Uri;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openTemplatePreviewStream", "Ljava/io/InputStream;", "calculateInSampleSize", "sourceWidth", "sourceHeight", "app_debug", "previewBitmap"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class TemplatePreviewThumbnailKt {
    static final Unit TemplatePreviewThumbnail_6PoWaU8$lambda$13(Uri uri, String str, Modifier modifier, float f, float f2, int i, int i2, Composer composer, int i3) {
        m7019TemplatePreviewThumbnail6PoWaU8(uri, str, modifier, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TemplatePreviewThumbnail_nSlTg7c$lambda$6(String str, String str2, Modifier modifier, float f, float f2, boolean z, int i, int i2, Composer composer, int i3) {
        m7020TemplatePreviewThumbnailnSlTg7c(str, str2, modifier, f, f2, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ed  */
    /* renamed from: TemplatePreviewThumbnail-nSlTg7c, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7020TemplatePreviewThumbnailnSlTg7c(final java.lang.String r47, final java.lang.String r48, androidx.compose.ui.Modifier r49, float r50, float r51, boolean r52, androidx.compose.runtime.Composer r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 1250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.TemplatePreviewThumbnailKt.m7020TemplatePreviewThumbnailnSlTg7c(java.lang.String, java.lang.String, androidx.compose.ui.Modifier, float, float, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final ImageBitmap TemplatePreviewThumbnail_nSlTg7c$lambda$4(State<? extends ImageBitmap> state) {
        return (ImageBitmap) state.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b2  */
    /* renamed from: TemplatePreviewThumbnail-6PoWaU8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7019TemplatePreviewThumbnail6PoWaU8(final android.net.Uri r46, final java.lang.String r47, androidx.compose.ui.Modifier r48, float r49, float r50, androidx.compose.runtime.Composer r51, final int r52, final int r53) {
        /*
            Method dump skipped, instructions count: 1135
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.TemplatePreviewThumbnailKt.m7019TemplatePreviewThumbnail6PoWaU8(android.net.Uri, java.lang.String, androidx.compose.ui.Modifier, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final ImageBitmap TemplatePreviewThumbnail_6PoWaU8$lambda$11(State<? extends ImageBitmap> state) {
        return (ImageBitmap) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object loadTemplatePreviewBitmap(Context context, String path, int targetWidth, int targetHeight, Continuation<? super ImageBitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TemplatePreviewThumbnailKt$loadTemplatePreviewBitmap$2(context, path, targetWidth, targetHeight, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object loadUriPreviewBitmap(Context context, Uri uri, int targetWidth, int targetHeight, Continuation<? super ImageBitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TemplatePreviewThumbnailKt$loadUriPreviewBitmap$2(context, uri, targetWidth, targetHeight, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InputStream openTemplatePreviewStream(Context context, String path) {
        Object m7212constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            File file = new File(path);
            m7212constructorimpl = Result.m7212constructorimpl((file.isAbsolute() && file.exists()) ? new FileInputStream(file) : context.getAssets().open(path));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7218isFailureimpl(m7212constructorimpl)) {
            m7212constructorimpl = null;
        }
        return (InputStream) m7212constructorimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateInSampleSize(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
        if (sourceWidth <= 0 || sourceHeight <= 0 || targetWidth <= 0 || targetHeight <= 0) {
            return 1;
        }
        int sampleSize = 1;
        int currentWidth = sourceWidth;
        int currentHeight = sourceHeight;
        while (currentWidth / 2 >= targetWidth && currentHeight / 2 >= targetHeight) {
            currentWidth /= 2;
            currentHeight /= 2;
            sampleSize *= 2;
        }
        return RangesKt.coerceAtLeast(sampleSize, 1);
    }
}
