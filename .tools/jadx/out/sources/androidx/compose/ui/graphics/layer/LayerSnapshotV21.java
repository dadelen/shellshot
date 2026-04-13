package androidx.compose.ui.graphics.layer;

import android.graphics.Bitmap;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: LayerSnapshot.android.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0096@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerSnapshotV21;", "Landroidx/compose/ui/graphics/layer/LayerSnapshotImpl;", "()V", "toBitmap", "Landroid/graphics/Bitmap;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayerSnapshotV21 implements LayerSnapshotImpl {
    public static final LayerSnapshotV21 INSTANCE = new LayerSnapshotV21();

    private LayerSnapshotV21() {
    }

    @Override // androidx.compose.ui.graphics.layer.LayerSnapshotImpl
    public Object toBitmap(GraphicsLayer graphicsLayer, Continuation<? super Bitmap> continuation) {
        long size = graphicsLayer.getSize();
        Bitmap bitmap = Bitmap.createBitmap(IntSize.m6796getWidthimpl(size), IntSize.m6795getHeightimpl(size), Bitmap.Config.ARGB_8888);
        Canvas canvas = AndroidCanvas_androidKt.Canvas(new android.graphics.Canvas(bitmap));
        graphicsLayer.draw$ui_graphics_release(canvas, null);
        return bitmap;
    }
}
