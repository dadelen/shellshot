package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.core.os.HandlerCompat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayerManager.android.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\rJ\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u001b\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerManager;", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "(Landroidx/compose/ui/graphics/CanvasHolder;)V", "getCanvasHolder", "()Landroidx/compose/ui/graphics/CanvasHolder;", "handler", "Landroid/os/Handler;", "imageReader", "Landroid/media/ImageReader;", "layerSet", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "persistenceIterationInProgress", "", "postponedReleaseRequests", "Landroidx/collection/MutableObjectList;", "destroy", "", "hasImageReader", "persist", "layer", "persistLayers", "layers", "Landroidx/collection/ScatterSet;", "release", "updateLayerPersistence", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayerManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean isRobolectric;
    private final CanvasHolder canvasHolder;
    private ImageReader imageReader;
    private boolean persistenceIterationInProgress;
    private MutableObjectList<GraphicsLayer> postponedReleaseRequests;
    private final MutableScatterSet<GraphicsLayer> layerSet = ScatterSetKt.mutableScatterSetOf();
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper(), new Handler.Callback() { // from class: androidx.compose.ui.graphics.layer.LayerManager$$ExternalSyntheticLambda1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return LayerManager.handler$lambda$0(LayerManager.this, message);
        }
    });

    public LayerManager(CanvasHolder canvasHolder) {
        this.canvasHolder = canvasHolder;
    }

    public final CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }

    static final boolean handler$lambda$0(LayerManager this$0, Message it) {
        this$0.persistLayers(this$0.layerSet);
        return true;
    }

    public final void persist(GraphicsLayer layer) {
        this.layerSet.add(layer);
        if (!this.handler.hasMessages(0)) {
            this.handler.sendMessageAtFrontOfQueue(Message.obtain());
        }
    }

    public final void release(GraphicsLayer layer) {
        if (!this.persistenceIterationInProgress) {
            if (this.layerSet.remove(layer)) {
                layer.discardDisplayList$ui_graphics_release();
            }
        } else {
            MutableObjectList it = this.postponedReleaseRequests;
            if (it == null) {
                it = new MutableObjectList(0, 1, null);
                this.postponedReleaseRequests = it;
            }
            it.add(layer);
        }
    }

    private final void persistLayers(ScatterSet<GraphicsLayer> layers) {
        CanvasHolder this_$iv;
        int i;
        boolean shouldPersistLayers = (1 == 0 || !layers.isNotEmpty() || isRobolectric) ? false : true;
        if (shouldPersistLayers) {
            ImageReader $this$persistLayers_u24lambda_u243 = this.imageReader;
            if ($this$persistLayers_u24lambda_u243 == null) {
                $this$persistLayers_u24lambda_u243 = ImageReader.newInstance(1, 1, 1, 3);
                $this$persistLayers_u24lambda_u243.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.compose.ui.graphics.layer.LayerManager$$ExternalSyntheticLambda0
                    @Override // android.media.ImageReader.OnImageAvailableListener
                    public final void onImageAvailable(ImageReader imageReader) {
                        LayerManager.persistLayers$lambda$3$lambda$2(imageReader);
                    }
                }, this.handler);
                this.imageReader = $this$persistLayers_u24lambda_u243;
            }
            Surface surface = $this$persistLayers_u24lambda_u243.getSurface();
            Canvas canvas = LockHardwareCanvasHelper.INSTANCE.lockHardwareCanvas(surface);
            this.persistenceIterationInProgress = true;
            CanvasHolder this_$iv2 = this.canvasHolder;
            int $i$f$drawInto = 0;
            Canvas previousCanvas$iv = this_$iv2.getAndroidCanvas().getInternalCanvas();
            this_$iv2.getAndroidCanvas().setInternalCanvas(canvas);
            androidx.compose.ui.graphics.Canvas $this$persistLayers_u24lambda_u246 = this_$iv2.getAndroidCanvas();
            canvas.save();
            canvas.clipRect(0, 0, 1, 1);
            Object[] k$iv = layers.elements;
            long[] m$iv$iv = layers.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    long[] m$iv$iv2 = m$iv$iv;
                    boolean shouldPersistLayers2 = shouldPersistLayers;
                    this_$iv = this_$iv2;
                    int $i$f$drawInto2 = $i$f$drawInto;
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull == 0) {
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i2;
                                GraphicsLayer layer = (GraphicsLayer) k$iv[index$iv$iv];
                                layer.drawForPersistence$ui_graphics_release($this$persistLayers_u24lambda_u246);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                        }
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv2 = this_$iv;
                    $i$f$drawInto = $i$f$drawInto2;
                    shouldPersistLayers = shouldPersistLayers2;
                    m$iv$iv = m$iv$iv2;
                }
            } else {
                this_$iv = this_$iv2;
            }
            canvas.restore();
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
            this.persistenceIterationInProgress = false;
            MutableObjectList requests = this.postponedReleaseRequests;
            if (requests != null && requests.isNotEmpty()) {
                MutableObjectList this_$iv3 = requests;
                Object[] content$iv = this_$iv3.content;
                int i3 = this_$iv3._size;
                for (int i$iv = 0; i$iv < i3; i$iv++) {
                    GraphicsLayer it = (GraphicsLayer) content$iv[i$iv];
                    release(it);
                }
                requests.clear();
            }
            surface.unlockCanvasAndPost(canvas);
        }
    }

    static final void persistLayers$lambda$3$lambda$2(ImageReader it) {
        Image acquireLatestImage;
        if (it == null || (acquireLatestImage = it.acquireLatestImage()) == null) {
            return;
        }
        acquireLatestImage.close();
    }

    public final void destroy() {
        ImageReader imageReader = this.imageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        this.imageReader = null;
    }

    public final boolean hasImageReader() {
        return this.imageReader != null;
    }

    public final void updateLayerPersistence() {
        destroy();
        persistLayers(this.layerSet);
    }

    /* compiled from: LayerManager.android.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerManager$Companion;", "", "()V", "isRobolectric", "", "()Z", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRobolectric() {
            return LayerManager.isRobolectric;
        }
    }

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        isRobolectric = Intrinsics.areEqual(lowerCase, "robolectric");
    }
}
