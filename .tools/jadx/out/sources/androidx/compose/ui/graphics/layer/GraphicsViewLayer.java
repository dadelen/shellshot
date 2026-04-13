package androidx.compose.ui.graphics.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.AndroidBlendMode_androidKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidColorFilter_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.view.DrawChildContainer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GraphicsViewLayer.android.kt */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u0095\u00012\u00020\u0001:\u0002\u0095\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010u\u001a\u00020v2\u0006\u00106\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0004\bw\u0010\u001eJ\b\u0010x\u001a\u00020yH\u0016J\b\u0010z\u001a\u00020vH\u0016J\u0010\u0010{\u001a\u00020v2\u0006\u0010|\u001a\u00020}H\u0016J\b\u0010~\u001a\u00020>H\u0002JC\u0010\u007f\u001a\u00020v2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u001b\u0010\u0086\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0004\u0012\u00020v0\u0087\u0001¢\u0006\u0003\b\u0089\u0001H\u0016J\t\u0010\u008a\u0001\u001a\u00020vH\u0002J\t\u0010\u008b\u0001\u001a\u00020%H\u0002J\t\u0010\u008c\u0001\u001a\u00020%H\u0002J\u0015\u0010\u008d\u0001\u001a\u00020v2\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001H\u0016J-\u0010\u0090\u0001\u001a\u00020v2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020s2\u0006\u0010e\u001a\u00020fH\u0016ø\u0001\u0000¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\t\u0010\u0093\u0001\u001a\u00020vH\u0002J\t\u0010\u0094\u0001\u001a\u00020vH\u0002R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R,\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R,\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u0019@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R$\u0010&\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020%8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R(\u00100\u001a\u0004\u0018\u00010/2\b\u0010\u000b\u001a\u0004\u0018\u00010/@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R,\u00106\u001a\u0002052\u0006\u0010\u000b\u001a\u000205@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010\u001eR\u001a\u00109\u001a\u00020%X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010(\"\u0004\b:\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010;\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0015R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0015R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010F\u001a\u00020E2\u0006\u0010\u000b\u001a\u00020E@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\bG\u0010\u0015\"\u0004\bH\u0010\u0017R(\u0010J\u001a\u0004\u0018\u00010I2\b\u0010\u000b\u001a\u0004\u0018\u00010I@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u0016\u0010O\u001a\n Q*\u0004\u0018\u00010P0PX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010R\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u000f\"\u0004\bT\u0010\u0011R$\u0010U\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u000f\"\u0004\bW\u0010\u0011R$\u0010X\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u000f\"\u0004\bZ\u0010\u0011R$\u0010[\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u000f\"\u0004\b]\u0010\u0011R$\u0010^\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u000f\"\u0004\b`\u0010\u0011R$\u0010a\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u000f\"\u0004\bc\u0010\u0011R\u000e\u0010d\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010e\u001a\u00020fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0018R,\u0010g\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0012@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\bh\u0010\u0015\"\u0004\bi\u0010\u0017R$\u0010j\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u000f\"\u0004\bl\u0010\u0011R$\u0010m\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\u000f\"\u0004\bo\u0010\u0011R\u000e\u0010p\u001a\u00020qX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020sX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020sX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0096\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsViewLayer;", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "layerContainer", "Landroidx/compose/ui/graphics/layer/view/DrawChildContainer;", "ownerId", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "(Landroidx/compose/ui/graphics/layer/view/DrawChildContainer;JLandroidx/compose/ui/graphics/CanvasHolder;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "J", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "I", "cameraDistance", "getCameraDistance", "setCameraDistance", "getCanvasHolder", "()Landroidx/compose/ui/graphics/CanvasHolder;", "", "clip", "getClip", "()Z", "setClip", "(Z)V", "clipBoundsInvalidated", "clipRect", "Landroid/graphics/Rect;", "clipToBounds", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "setCompositingStrategy-Wpw9cng", "isInvalidated", "setInvalidated", "layerId", "getLayerId", "layerPaint", "Landroid/graphics/Paint;", "outlineIsProvided", "getOwnerId", "picture", "Landroid/graphics/Picture;", "pictureCanvasHolder", "pictureDrawScope", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "resources", "Landroid/content/res/Resources;", "kotlin.jvm.PlatformType", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "shouldManuallySetCenterPivot", "size", "Landroidx/compose/ui/unit/IntSize;", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "viewLayer", "Landroidx/compose/ui/graphics/layer/ViewLayer;", "x", "", "y", "applyCompositingLayer", "", "applyCompositingLayer-Wpw9cng", "calculateMatrix", "Landroid/graphics/Matrix;", "discardDisplayList", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "obtainLayerPaint", "record", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "recordDrawingOperations", "requiresCompositingLayer", "requiresLayerPaint", "setOutline", "outline", "Landroid/graphics/Outline;", "setPosition", "setPosition-H0pRuoY", "(IIJ)V", "updateClipBounds", "updateLayerProperties", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GraphicsViewLayer implements GraphicsLayerImpl {
    private float alpha;
    private long ambientShadowColor;
    private int blendMode;
    private final CanvasHolder canvasHolder;
    private boolean clipBoundsInvalidated;
    private final Rect clipRect;
    private boolean clipToBounds;
    private ColorFilter colorFilter;
    private int compositingStrategy;
    private boolean isInvalidated;
    private final DrawChildContainer layerContainer;
    private final long layerId;
    private Paint layerPaint;
    private boolean outlineIsProvided;
    private final long ownerId;
    private final Picture picture;
    private final CanvasHolder pictureCanvasHolder;
    private final CanvasDrawScope pictureDrawScope;
    private long pivotOffset;
    private RenderEffect renderEffect;
    private final Resources resources;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private boolean shouldManuallySetCenterPivot;
    private long size;
    private long spotShadowColor;
    private float translationX;
    private float translationY;
    private final ViewLayer viewLayer;
    private int x;
    private int y;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean mayRenderInSoftware = !SurfaceUtils.INSTANCE.isLockHardwareCanvasAvailable();
    private static final Canvas PlaceholderCanvas = new Canvas() { // from class: androidx.compose.ui.graphics.layer.GraphicsViewLayer$Companion$PlaceholderCanvas$1
        @Override // android.graphics.Canvas
        public boolean isHardwareAccelerated() {
            return true;
        }
    };

    public GraphicsViewLayer(DrawChildContainer layerContainer, long ownerId, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope) {
        Picture picture;
        CanvasDrawScope canvasDrawScope2;
        CanvasHolder canvasHolder2;
        this.layerContainer = layerContainer;
        this.ownerId = ownerId;
        this.canvasHolder = canvasHolder;
        this.viewLayer = new ViewLayer(this.layerContainer, this.canvasHolder, canvasDrawScope);
        this.resources = this.layerContainer.getResources();
        this.clipRect = new Rect();
        if (mayRenderInSoftware) {
            picture = new Picture();
        } else {
            picture = null;
        }
        this.picture = picture;
        if (mayRenderInSoftware) {
            canvasDrawScope2 = new CanvasDrawScope();
        } else {
            canvasDrawScope2 = null;
        }
        this.pictureDrawScope = canvasDrawScope2;
        if (mayRenderInSoftware) {
            canvasHolder2 = new CanvasHolder();
        } else {
            canvasHolder2 = null;
        }
        this.pictureCanvasHolder = canvasHolder2;
        this.layerContainer.addView(this.viewLayer);
        this.viewLayer.setClipBounds(null);
        this.size = IntSize.INSTANCE.m6801getZeroYbymL2g();
        this.isInvalidated = true;
        this.layerId = View.generateViewId();
        this.blendMode = BlendMode.INSTANCE.m4105getSrcOver0nO6VwU();
        this.compositingStrategy = CompositingStrategy.INSTANCE.m4793getAutoke2Ky5w();
        this.alpha = 1.0f;
        this.pivotOffset = Offset.INSTANCE.m3938getZeroF1C5BW0();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.ambientShadowColor = Color.INSTANCE.m4189getBlack0d7_KjU();
        this.spotShadowColor = Color.INSTANCE.m4189getBlack0d7_KjU();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ GraphicsViewLayer(androidx.compose.ui.graphics.layer.view.DrawChildContainer r7, long r8, androidx.compose.ui.graphics.CanvasHolder r10, androidx.compose.ui.graphics.drawscope.CanvasDrawScope r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto Lb
            androidx.compose.ui.graphics.CanvasHolder r10 = new androidx.compose.ui.graphics.CanvasHolder
            r10.<init>()
            r4 = r10
            goto Lc
        Lb:
            r4 = r10
        Lc:
            r10 = r12 & 8
            if (r10 == 0) goto L17
            androidx.compose.ui.graphics.drawscope.CanvasDrawScope r11 = new androidx.compose.ui.graphics.drawscope.CanvasDrawScope
            r11.<init>()
            r5 = r11
            goto L18
        L17:
            r5 = r11
        L18:
            r0 = r6
            r1 = r7
            r2 = r8
            r0.<init>(r1, r2, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsViewLayer.<init>(androidx.compose.ui.graphics.layer.view.DrawChildContainer, long, androidx.compose.ui.graphics.CanvasHolder, androidx.compose.ui.graphics.drawscope.CanvasDrawScope, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getOwnerId() {
        return this.ownerId;
    }

    public final CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: isInvalidated, reason: from getter */
    public boolean getIsInvalidated() {
        return this.isInvalidated;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setInvalidated(boolean z) {
        this.isInvalidated = z;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getLayerId() {
        return this.layerId;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: getBlendMode-0nO6VwU, reason: from getter */
    public int getBlendMode() {
        return this.blendMode;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setBlendMode-s9anfk8 */
    public void mo4822setBlendModes9anfk8(int value) {
        this.blendMode = value;
        Paint $this$setBlendMode_s9anfk8_u24lambda_u240 = obtainLayerPaint();
        $this$setBlendMode_s9anfk8_u24lambda_u240.setXfermode(new PorterDuffXfermode(AndroidBlendMode_androidKt.m4015toPorterDuffModes9anfk8(value)));
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setColorFilter(ColorFilter value) {
        this.colorFilter = value;
        Paint $this$_set_colorFilter__u24lambda_u241 = obtainLayerPaint();
        $this$_set_colorFilter__u24lambda_u241.setColorFilter(value != null ? AndroidColorFilter_androidKt.asAndroidColorFilter(value) : null);
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: getCompositingStrategy-ke2Ky5w, reason: from getter */
    public int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setCompositingStrategy-Wpw9cng */
    public void mo4823setCompositingStrategyWpw9cng(int value) {
        this.compositingStrategy = value;
        updateLayerProperties();
    }

    /* renamed from: applyCompositingLayer-Wpw9cng, reason: not valid java name */
    private final void m4829applyCompositingLayerWpw9cng(int compositingStrategy) {
        ViewLayer viewLayer = this.viewLayer;
        boolean z = true;
        if (CompositingStrategy.m4789equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w())) {
            this.viewLayer.setLayerType(2, this.layerPaint);
        } else {
            boolean m4789equalsimpl0 = CompositingStrategy.m4789equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4794getModulateAlphake2Ky5w());
            ViewLayer viewLayer2 = this.viewLayer;
            if (m4789equalsimpl0) {
                viewLayer2.setLayerType(0, this.layerPaint);
                z = false;
            } else {
                viewLayer2.setLayerType(0, this.layerPaint);
            }
        }
        viewLayer.setCanUseCompositingLayer$ui_graphics_release(z);
    }

    private final void updateLayerProperties() {
        if (requiresCompositingLayer()) {
            m4829applyCompositingLayerWpw9cng(CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w());
        } else {
            m4829applyCompositingLayerWpw9cng(getCompositingStrategy());
        }
    }

    private final Paint obtainLayerPaint() {
        Paint paint = this.layerPaint;
        if (paint != null) {
            return paint;
        }
        Paint it = new Paint();
        this.layerPaint = it;
        return it;
    }

    private final boolean requiresCompositingLayer() {
        return CompositingStrategy.m4789equalsimpl0(getCompositingStrategy(), CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w()) || requiresLayerPaint();
    }

    private final boolean requiresLayerPaint() {
        return (BlendMode.m4074equalsimpl0(getBlendMode(), BlendMode.INSTANCE.m4105getSrcOver0nO6VwU()) && getColorFilter() == null) ? false : true;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setAlpha(float value) {
        this.alpha = value;
        this.viewLayer.setAlpha(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: getPivotOffset-F1C5BW0, reason: from getter */
    public long getPivotOffset() {
        return this.pivotOffset;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setPivotOffset-k-4lQ0M */
    public void mo4824setPivotOffsetk4lQ0M(long value) {
        this.pivotOffset = value;
        if (OffsetKt.m3943isUnspecifiedk4lQ0M(value)) {
            ViewLayerVerificationHelper28.INSTANCE.resetPivot(this.viewLayer);
            return;
        }
        this.shouldManuallySetCenterPivot = false;
        this.viewLayer.setPivotX(Offset.m3922getXimpl(value));
        this.viewLayer.setPivotY(Offset.m3923getYimpl(value));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleX(float value) {
        this.scaleX = value;
        this.viewLayer.setScaleX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleY(float value) {
        this.scaleY = value;
        this.viewLayer.setScaleY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationX(float value) {
        this.translationX = value;
        this.viewLayer.setTranslationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationY(float value) {
        this.translationY = value;
        this.viewLayer.setTranslationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setShadowElevation(float value) {
        this.shadowElevation = value;
        this.viewLayer.setElevation(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: getAmbientShadowColor-0d7_KjU, reason: from getter */
    public long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setAmbientShadowColor-8_81llA */
    public void mo4821setAmbientShadowColor8_81llA(long value) {
        this.ambientShadowColor = value;
        ViewLayerVerificationHelper28.INSTANCE.setOutlineAmbientShadowColor(this.viewLayer, ColorKt.m4217toArgb8_81llA(value));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: getSpotShadowColor-0d7_KjU, reason: from getter */
    public long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setSpotShadowColor-8_81llA */
    public void mo4826setSpotShadowColor8_81llA(long value) {
        this.spotShadowColor = value;
        ViewLayerVerificationHelper28.INSTANCE.setOutlineSpotShadowColor(this.viewLayer, ColorKt.m4217toArgb8_81llA(value));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationX(float value) {
        this.rotationX = value;
        this.viewLayer.setRotationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationY(float value) {
        this.rotationY = value;
        this.viewLayer.setRotationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationZ(float value) {
        this.rotationZ = value;
        this.viewLayer.setRotation(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getCameraDistance() {
        return this.viewLayer.getCameraDistance() / this.resources.getDisplayMetrics().densityDpi;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setCameraDistance(float value) {
        this.viewLayer.setCameraDistance(this.resources.getDisplayMetrics().densityDpi * value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getClip() {
        return this.clipToBounds || this.viewLayer.getClipToOutline();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setClip(boolean value) {
        boolean z = false;
        this.clipToBounds = value && !this.outlineIsProvided;
        this.clipBoundsInvalidated = true;
        ViewLayer viewLayer = this.viewLayer;
        if (value && this.outlineIsProvided) {
            z = true;
        }
        viewLayer.setClipToOutline(z);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRenderEffect(RenderEffect value) {
        this.renderEffect = value;
        ViewLayerVerificationHelper31.INSTANCE.setRenderEffect(this.viewLayer, value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setPosition-H0pRuoY */
    public void mo4825setPositionH0pRuoY(int x, int y, long size) {
        if (!IntSize.m6794equalsimpl0(this.size, size)) {
            if (getClip()) {
                this.clipBoundsInvalidated = true;
            }
            this.viewLayer.layout(x, y, IntSize.m6796getWidthimpl(size) + x, IntSize.m6795getHeightimpl(size) + y);
            this.size = size;
            if (this.shouldManuallySetCenterPivot) {
                this.viewLayer.setPivotX(IntSize.m6796getWidthimpl(size) / 2.0f);
                this.viewLayer.setPivotY(IntSize.m6795getHeightimpl(size) / 2.0f);
            }
        } else {
            if (this.x != x) {
                this.viewLayer.offsetLeftAndRight(x - this.x);
            }
            if (this.y != y) {
                this.viewLayer.offsetTopAndBottom(y - this.y);
            }
        }
        this.x = x;
        this.y = y;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setOutline(Outline outline) {
        boolean requiresRedraw = !this.viewLayer.setLayerOutline(outline);
        if (getClip() && outline != null) {
            this.viewLayer.setClipToOutline(true);
            if (this.clipToBounds) {
                this.clipToBounds = false;
                this.clipBoundsInvalidated = true;
            }
        }
        this.outlineIsProvided = outline != null;
        if (requiresRedraw) {
            this.viewLayer.invalidate();
            recordDrawingOperations();
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void record(Density density, LayoutDirection layoutDirection, GraphicsLayer layer, Function1<? super DrawScope, Unit> block) {
        CanvasHolder this_$iv;
        if (this.viewLayer.getParent() == null) {
            this.layerContainer.addView(this.viewLayer);
        }
        this.viewLayer.setDrawParams(density, layoutDirection, layer, block);
        if (this.viewLayer.isAttachedToWindow()) {
            this.viewLayer.setVisibility(4);
            this.viewLayer.setVisibility(0);
            recordDrawingOperations();
            Picture p = this.picture;
            if (p != null) {
                Canvas pictureCanvas = p.beginRecording(IntSize.m6796getWidthimpl(this.size), IntSize.m6795getHeightimpl(this.size));
                try {
                    CanvasHolder this_$iv2 = this.pictureCanvasHolder;
                    if (this_$iv2 != null) {
                        Canvas previousCanvas$iv = this_$iv2.getAndroidCanvas().getInternalCanvas();
                        this_$iv2.getAndroidCanvas().setInternalCanvas(pictureCanvas);
                        androidx.compose.ui.graphics.Canvas $this$record_u24lambda_u244_u24lambda_u243 = this_$iv2.getAndroidCanvas();
                        CanvasDrawScope this_$iv3 = this.pictureDrawScope;
                        if (this_$iv3 != null) {
                            long size$iv = IntSizeKt.m6808toSizeozmzZPI(this.size);
                            CanvasDrawScope.DrawParams drawParams = this_$iv3.getDrawParams();
                            Density prevDensity$iv = drawParams.getDensity();
                            LayoutDirection prevLayoutDirection$iv = drawParams.getLayoutDirection();
                            androidx.compose.ui.graphics.Canvas prevCanvas$iv = drawParams.getCanvas();
                            long prevSize$iv = drawParams.getSize();
                            CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u240$iv = this_$iv3.getDrawParams();
                            this_$iv = this_$iv2;
                            $this$draw_yzxVdVo_u24lambda_u240$iv.setDensity(density);
                            $this$draw_yzxVdVo_u24lambda_u240$iv.setLayoutDirection(layoutDirection);
                            $this$draw_yzxVdVo_u24lambda_u240$iv.setCanvas($this$record_u24lambda_u244_u24lambda_u243);
                            $this$draw_yzxVdVo_u24lambda_u240$iv.m4633setSizeuvyYCjk(size$iv);
                            $this$record_u24lambda_u244_u24lambda_u243.save();
                            block.invoke(this_$iv3);
                            $this$record_u24lambda_u244_u24lambda_u243.restore();
                            CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u241$iv = this_$iv3.getDrawParams();
                            $this$draw_yzxVdVo_u24lambda_u241$iv.setDensity(prevDensity$iv);
                            $this$draw_yzxVdVo_u24lambda_u241$iv.setLayoutDirection(prevLayoutDirection$iv);
                            $this$draw_yzxVdVo_u24lambda_u241$iv.setCanvas(prevCanvas$iv);
                            $this$draw_yzxVdVo_u24lambda_u241$iv.m4633setSizeuvyYCjk(prevSize$iv);
                        } else {
                            this_$iv = this_$iv2;
                        }
                        this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
                        Unit unit = Unit.INSTANCE;
                    }
                } finally {
                    p.endRecording();
                }
            }
        }
    }

    private final void recordDrawingOperations() {
        try {
            CanvasHolder this_$iv = this.canvasHolder;
            Canvas targetCanvas$iv = PlaceholderCanvas;
            Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
            this_$iv.getAndroidCanvas().setInternalCanvas(targetCanvas$iv);
            androidx.compose.ui.graphics.Canvas $this$recordDrawingOperations_u24lambda_u245 = this_$iv.getAndroidCanvas();
            this.layerContainer.drawChild$ui_graphics_release($this$recordDrawingOperations_u24lambda_u245, this.viewLayer, this.viewLayer.getDrawingTime());
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
        } catch (Throwable th) {
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void draw(androidx.compose.ui.graphics.Canvas canvas) {
        updateClipBounds();
        Canvas androidCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (androidCanvas.isHardwareAccelerated()) {
            this.layerContainer.drawChild$ui_graphics_release(canvas, this.viewLayer, this.viewLayer.getDrawingTime());
            return;
        }
        Picture it = this.picture;
        if (it != null) {
            androidCanvas.drawPicture(it);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public Matrix calculateMatrix() {
        return this.viewLayer.getMatrix();
    }

    private final void updateClipBounds() {
        Rect $this$updateClipBounds_u24lambda_u247;
        if (this.clipBoundsInvalidated) {
            ViewLayer viewLayer = this.viewLayer;
            if (getClip() && !this.outlineIsProvided) {
                $this$updateClipBounds_u24lambda_u247 = this.clipRect;
                $this$updateClipBounds_u24lambda_u247.left = 0;
                $this$updateClipBounds_u24lambda_u247.top = 0;
                $this$updateClipBounds_u24lambda_u247.right = this.viewLayer.getWidth();
                $this$updateClipBounds_u24lambda_u247.bottom = this.viewLayer.getHeight();
            } else {
                $this$updateClipBounds_u24lambda_u247 = null;
            }
            viewLayer.setClipBounds($this$updateClipBounds_u24lambda_u247);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void discardDisplayList() {
        this.layerContainer.removeViewInLayout(this.viewLayer);
    }

    /* compiled from: GraphicsViewLayer.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsViewLayer$Companion;", "", "()V", "PlaceholderCanvas", "Landroid/graphics/Canvas;", "getPlaceholderCanvas", "()Landroid/graphics/Canvas;", "mayRenderInSoftware", "", "getMayRenderInSoftware", "()Z", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getMayRenderInSoftware() {
            return GraphicsViewLayer.mayRenderInSoftware;
        }

        public final Canvas getPlaceholderCanvas() {
            return GraphicsViewLayer.PlaceholderCanvas;
        }
    }
}
