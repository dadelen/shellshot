package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
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
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GraphicsLayerV29.android.kt */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010h\u001a\u00020iH\u0002J\b\u0010j\u001a\u00020<H\u0016J\b\u0010k\u001a\u00020iH\u0016J\u0010\u0010l\u001a\u00020i2\u0006\u0010m\u001a\u00020nH\u0016J\b\u0010o\u001a\u00020:H\u0002J9\u0010p\u001a\u00020i2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020t2\u0006\u0010u\u001a\u00020v2\u0017\u0010w\u001a\u0013\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020i0x¢\u0006\u0002\bzH\u0016J\b\u0010{\u001a\u00020!H\u0002J\b\u0010|\u001a\u00020!H\u0002J\u0012\u0010}\u001a\u00020i2\b\u0010~\u001a\u0004\u0018\u00010\u007fH\u0016J2\u0010\u0080\u0001\u001a\u00020i2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00012\u0007\u0010]\u001a\u00030\u0084\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\t\u0010\u0087\u0001\u001a\u00020iH\u0002J!\u0010\u0088\u0001\u001a\u00020i*\u00020J2\u0006\u00100\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR,\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R,\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\u0017@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u00020!2\u0006\u0010\t\u001a\u00020!@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\t\u001a\u0004\u0018\u00010)@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R,\u00100\u001a\u00020/2\u0006\u0010\t\u001a\u00020/@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b1\u0010\u001a\"\u0004\b2\u0010\u001cR\u0014\u00103\u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010$R\u001a\u00105\u001a\u00020!X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010$\"\u0004\b6\u0010&R\u0014\u00107\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u0010\u0013R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0013R,\u0010@\u001a\u00020?2\u0006\u0010\t\u001a\u00020?@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\bA\u0010\u0013\"\u0004\bB\u0010\u0015R(\u0010D\u001a\u0004\u0018\u00010C2\b\u0010\t\u001a\u0004\u0018\u00010C@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010K\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\r\"\u0004\bM\u0010\u000fR$\u0010N\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\r\"\u0004\bP\u0010\u000fR$\u0010Q\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\r\"\u0004\bS\u0010\u000fR$\u0010T\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\r\"\u0004\bV\u0010\u000fR$\u0010W\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\r\"\u0004\bY\u0010\u000fR$\u0010Z\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\r\"\u0004\b\\\u0010\u000fR\u0016\u0010]\u001a\u00020^X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0016R,\u0010_\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b`\u0010\u0013\"\u0004\ba\u0010\u0015R$\u0010b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\r\"\u0004\bd\u0010\u000fR$\u0010e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\r\"\u0004\bg\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u008b\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayerV29;", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "ownerId", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "(JLandroidx/compose/ui/graphics/CanvasHolder;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "J", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "I", "cameraDistance", "getCameraDistance", "setCameraDistance", "", "clip", "getClip", "()Z", "setClip", "(Z)V", "clipToBounds", "clipToOutline", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "setCompositingStrategy-Wpw9cng", "hasDisplayList", "getHasDisplayList", "isInvalidated", "setInvalidated", "layerId", "getLayerId", "layerPaint", "Landroid/graphics/Paint;", "matrix", "Landroid/graphics/Matrix;", "outlineIsProvided", "getOwnerId", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "renderNode", "Landroid/graphics/RenderNode;", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "size", "Landroidx/compose/ui/geometry/Size;", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "applyClip", "", "calculateMatrix", "discardDisplayList", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "obtainLayerPaint", "record", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "requiresCompositingLayer", "requiresLayerPaint", "setOutline", "outline", "Landroid/graphics/Outline;", "setPosition", "x", "", "y", "Landroidx/compose/ui/unit/IntSize;", "setPosition-H0pRuoY", "(IIJ)V", "updateLayerProperties", "applyCompositingStrategy", "applyCompositingStrategy-Z1X6vPc", "(Landroid/graphics/RenderNode;I)V", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GraphicsLayerV29 implements GraphicsLayerImpl {
    private float alpha;
    private long ambientShadowColor;
    private int blendMode;
    private float cameraDistance;
    private final CanvasDrawScope canvasDrawScope;
    private final CanvasHolder canvasHolder;
    private boolean clip;
    private boolean clipToBounds;
    private boolean clipToOutline;
    private ColorFilter colorFilter;
    private int compositingStrategy;
    private boolean isInvalidated;
    private Paint layerPaint;
    private Matrix matrix;
    private boolean outlineIsProvided;
    private final long ownerId;
    private long pivotOffset;
    private RenderEffect renderEffect;
    private final RenderNode renderNode;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private long size;
    private long spotShadowColor;
    private float translationX;
    private float translationY;

    public GraphicsLayerV29(long ownerId, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope) {
        this.ownerId = ownerId;
        this.canvasHolder = canvasHolder;
        this.canvasDrawScope = canvasDrawScope;
        this.renderNode = new RenderNode("graphicsLayer");
        this.size = Size.INSTANCE.m4000getZeroNHjbRc();
        this.renderNode.setClipToBounds(false);
        m4828applyCompositingStrategyZ1X6vPc(this.renderNode, CompositingStrategy.INSTANCE.m4793getAutoke2Ky5w());
        this.alpha = 1.0f;
        this.blendMode = BlendMode.INSTANCE.m4105getSrcOver0nO6VwU();
        this.pivotOffset = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.ambientShadowColor = Color.INSTANCE.m4189getBlack0d7_KjU();
        this.spotShadowColor = Color.INSTANCE.m4189getBlack0d7_KjU();
        this.cameraDistance = 8.0f;
        this.compositingStrategy = CompositingStrategy.INSTANCE.m4793getAutoke2Ky5w();
        this.isInvalidated = true;
    }

    public /* synthetic */ GraphicsLayerV29(long j, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? new CanvasHolder() : canvasHolder, (i & 4) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getOwnerId() {
        return this.ownerId;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setAlpha(float value) {
        this.alpha = value;
        this.renderNode.setAlpha(value);
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
        $this$setBlendMode_s9anfk8_u24lambda_u240.setBlendMode(AndroidBlendMode_androidKt.m4014toAndroidBlendModes9anfk8(value));
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
    /* renamed from: getPivotOffset-F1C5BW0, reason: from getter */
    public long getPivotOffset() {
        return this.pivotOffset;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setPivotOffset-k-4lQ0M */
    public void mo4824setPivotOffsetk4lQ0M(long value) {
        this.pivotOffset = value;
        boolean m3943isUnspecifiedk4lQ0M = OffsetKt.m3943isUnspecifiedk4lQ0M(value);
        RenderNode renderNode = this.renderNode;
        if (m3943isUnspecifiedk4lQ0M) {
            renderNode.resetPivot();
        } else {
            renderNode.setPivotX(Offset.m3922getXimpl(value));
            this.renderNode.setPivotY(Offset.m3923getYimpl(value));
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleX(float value) {
        this.scaleX = value;
        this.renderNode.setScaleX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleY(float value) {
        this.scaleY = value;
        this.renderNode.setScaleY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationX(float value) {
        this.translationX = value;
        this.renderNode.setTranslationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationY(float value) {
        this.translationY = value;
        this.renderNode.setTranslationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setShadowElevation(float value) {
        this.shadowElevation = value;
        this.renderNode.setElevation(value);
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
        this.renderNode.setAmbientShadowColor(ColorKt.m4217toArgb8_81llA(value));
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
        this.renderNode.setSpotShadowColor(ColorKt.m4217toArgb8_81llA(value));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationX(float value) {
        this.rotationX = value;
        this.renderNode.setRotationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationY(float value) {
        this.rotationY = value;
        this.renderNode.setRotationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationZ(float value) {
        this.rotationZ = value;
        this.renderNode.setRotationZ(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getCameraDistance() {
        return this.cameraDistance;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setCameraDistance(float value) {
        this.cameraDistance = value;
        this.renderNode.setCameraDistance(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getClip() {
        return this.clip;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setClip(boolean value) {
        this.clip = value;
        applyClip();
    }

    private final void applyClip() {
        boolean newClipToBounds = getClip() && !this.outlineIsProvided;
        boolean newClipToOutline = getClip() && this.outlineIsProvided;
        if (newClipToBounds != this.clipToBounds) {
            this.clipToBounds = newClipToBounds;
            this.renderNode.setClipToBounds(this.clipToBounds);
        }
        if (newClipToOutline != this.clipToOutline) {
            this.clipToOutline = newClipToOutline;
            this.renderNode.setClipToOutline(newClipToOutline);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRenderEffect(RenderEffect value) {
        this.renderEffect = value;
        RenderNodeVerificationHelper.INSTANCE.setRenderEffect(this.renderNode, value);
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

    /* renamed from: applyCompositingStrategy-Z1X6vPc, reason: not valid java name */
    private final void m4828applyCompositingStrategyZ1X6vPc(RenderNode $this$applyCompositingStrategy_u2dZ1X6vPc, int compositingStrategy) {
        if (CompositingStrategy.m4789equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w())) {
            $this$applyCompositingStrategy_u2dZ1X6vPc.setUseCompositingLayer(true, this.layerPaint);
            $this$applyCompositingStrategy_u2dZ1X6vPc.setHasOverlappingRendering(true);
            return;
        }
        boolean m4789equalsimpl0 = CompositingStrategy.m4789equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4794getModulateAlphake2Ky5w());
        Paint paint = this.layerPaint;
        if (m4789equalsimpl0) {
            $this$applyCompositingStrategy_u2dZ1X6vPc.setUseCompositingLayer(false, paint);
            $this$applyCompositingStrategy_u2dZ1X6vPc.setHasOverlappingRendering(false);
        } else {
            $this$applyCompositingStrategy_u2dZ1X6vPc.setUseCompositingLayer(false, paint);
            $this$applyCompositingStrategy_u2dZ1X6vPc.setHasOverlappingRendering(true);
        }
    }

    private final void updateLayerProperties() {
        boolean requiresCompositingLayer = requiresCompositingLayer();
        RenderNode renderNode = this.renderNode;
        if (requiresCompositingLayer) {
            m4828applyCompositingStrategyZ1X6vPc(renderNode, CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w());
        } else {
            m4828applyCompositingStrategyZ1X6vPc(renderNode, getCompositingStrategy());
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* renamed from: setPosition-H0pRuoY */
    public void mo4825setPositionH0pRuoY(int x, int y, long size) {
        this.renderNode.setPosition(x, y, IntSize.m6796getWidthimpl(size) + x, IntSize.m6795getHeightimpl(size) + y);
        this.size = IntSizeKt.m6808toSizeozmzZPI(size);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setOutline(Outline outline) {
        this.renderNode.setOutline(outline);
        this.outlineIsProvided = outline != null;
        applyClip();
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
    public void record(Density density, LayoutDirection layoutDirection, GraphicsLayer layer, Function1<? super DrawScope, Unit> block) {
        RecordingCanvas recordingCanvas = this.renderNode.beginRecording();
        try {
            CanvasHolder this_$iv = this.canvasHolder;
            Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
            this_$iv.getAndroidCanvas().setInternalCanvas(recordingCanvas);
            androidx.compose.ui.graphics.Canvas $this$record_u24lambda_u243 = this_$iv.getAndroidCanvas();
            DrawContext it = this.canvasDrawScope.getDrawContext();
            it.setDensity(density);
            it.setLayoutDirection(layoutDirection);
            it.setGraphicsLayer(layer);
            it.mo4635setSizeuvyYCjk(this.size);
            it.setCanvas($this$record_u24lambda_u243);
            block.invoke(this.canvasDrawScope);
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
            this.renderNode.endRecording();
            setInvalidated(false);
        } catch (Throwable th) {
            this.renderNode.endRecording();
            throw th;
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void draw(androidx.compose.ui.graphics.Canvas canvas) {
        AndroidCanvas_androidKt.getNativeCanvas(canvas).drawRenderNode(this.renderNode);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public Matrix calculateMatrix() {
        Matrix it = this.matrix;
        if (it == null) {
            it = new Matrix();
            this.matrix = it;
        }
        this.renderNode.getMatrix(it);
        return it;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getHasDisplayList() {
        return this.renderNode.hasDisplayList();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void discardDisplayList() {
        this.renderNode.discardDisplayList();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getLayerId() {
        return this.renderNode.getUniqueId();
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
        return CompositingStrategy.m4789equalsimpl0(getCompositingStrategy(), CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w()) || requiresLayerPaint() || getRenderEffect() != null;
    }

    private final boolean requiresLayerPaint() {
        return (BlendMode.m4074equalsimpl0(getBlendMode(), BlendMode.INSTANCE.m4105getSrcOver0nO6VwU()) && getColorFilter() == null) ? false : true;
    }
}
