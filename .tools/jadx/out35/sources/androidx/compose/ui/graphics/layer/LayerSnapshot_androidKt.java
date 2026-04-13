package androidx.compose.ui.graphics.layer;

import android.graphics.Bitmap;
import android.media.Image;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayerSnapshot.android.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"toBitmap", "Landroid/graphics/Bitmap;", "Landroid/media/Image;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayerSnapshot_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap toBitmap(Image $this$toBitmap) {
        Image.Plane[] it = $this$toBitmap.getPlanes();
        Intrinsics.checkNotNull(it);
        Image.Plane plane = it[0];
        int pixelCount = $this$toBitmap.getWidth() * $this$toBitmap.getHeight();
        int[] colors = new int[pixelCount];
        plane.getBuffer().asIntBuffer().get(colors);
        for (int i = 0; i < pixelCount; i++) {
            int color = colors[i];
            int red = color & 255;
            int green = (color >> 8) & 255;
            int blue = (color >> 16) & 255;
            int alpha = (color >> 24) & 255;
            colors[i] = ColorKt.m4217toArgb8_81llA(ColorKt.Color(red, green, blue, alpha));
        }
        return Bitmap.createBitmap(colors, $this$toBitmap.getWidth(), $this$toBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }
}
