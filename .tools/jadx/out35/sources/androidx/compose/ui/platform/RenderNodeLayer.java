package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Fields;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.GraphicLayerInfo;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: RenderNodeLayer.android.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0002UVBU\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00128\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\u0010\u0010J\u0010\u0010/\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\b\u00100\u001a\u00020\rH\u0016J\u001a\u00101\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u00102\u001a\u00020\rH\u0016J\u001a\u00103\u001a\u00020\r2\u0006\u00104\u001a\u000205H\u0016ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001a\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020:H\u0016ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u0018\u0010=\u001a\u00020\r2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u0014H\u0016J\"\u0010A\u001a\u00020:2\u0006\u0010B\u001a\u00020:2\u0006\u0010@\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ\u001a\u0010E\u001a\u00020\r2\u0006\u00109\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\u001a\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020KH\u0016ø\u0001\u0000¢\u0006\u0004\bL\u0010HJP\u0010M\u001a\u00020\r28\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0016J\u001a\u0010N\u001a\u00020\r2\u0006\u00104\u001a\u000205H\u0016ø\u0001\u0000¢\u0006\u0004\bO\u00107J\b\u0010P\u001a\u00020\rH\u0002J\b\u0010Q\u001a\u00020\rH\u0016J\u0010\u0010R\u001a\u00020\r2\u0006\u0010S\u001a\u00020TH\u0016R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u0005\u001a6\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u001dR\u000e\u0010)\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\u00020-X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010.\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006W"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer;", "Landroidx/compose/ui/node/OwnedLayer;", "Landroidx/compose/ui/layout/GraphicLayerInfo;", "ownerView", "Landroidx/compose/ui/platform/AndroidComposeView;", "drawBlock", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/Canvas;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "canvas", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentLayer", "", "invalidateParentLayer", "Lkotlin/Function0;", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)V", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "drawnWithZ", "", "isDestroyed", "value", "isDirty", "setDirty", "(Z)V", "layerId", "", "getLayerId", "()J", "matrixCache", "Landroidx/compose/ui/platform/LayerMatrixCache;", "Landroidx/compose/ui/platform/DeviceRenderNode;", "mutatedFields", "", "outlineResolver", "Landroidx/compose/ui/platform/OutlineResolver;", "getOwnerView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "ownerViewId", "getOwnerViewId", "renderNode", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "J", "clipRenderNode", "destroy", "drawLayer", "invalidate", "inverseTransform", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "inverseTransform-58bKbWc", "([F)V", "isInLayer", "position", "Landroidx/compose/ui/geometry/Offset;", "isInLayer-k-4lQ0M", "(J)Z", "mapBounds", "rect", "Landroidx/compose/ui/geometry/MutableRect;", "inverse", "mapOffset", "point", "mapOffset-8S9VItk", "(JZ)J", "move", "Landroidx/compose/ui/unit/IntOffset;", "move--gyyYBs", "(J)V", "resize", "size", "Landroidx/compose/ui/unit/IntSize;", "resize-ozmzZPI", "reuseLayer", "transform", "transform-58bKbWc", "triggerRepaint", "updateDisplayList", "updateLayerProperties", "scope", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "Companion", "UniqueDrawingIdApi29", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class RenderNodeLayer implements OwnedLayer, GraphicLayerInfo {
    private Function2<? super Canvas, ? super GraphicsLayer, Unit> drawBlock;
    private boolean drawnWithZ;
    private Function0<Unit> invalidateParentLayer;
    private boolean isDestroyed;
    private boolean isDirty;
    private int mutatedFields;
    private final AndroidComposeView ownerView;
    private final DeviceRenderNode renderNode;
    private Paint softwareLayerPaint;
    public static final int $stable = 8;
    private static final Function2<DeviceRenderNode, Matrix, Unit> getMatrix = new Function2<DeviceRenderNode, Matrix, Unit>() { // from class: androidx.compose.ui.platform.RenderNodeLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(DeviceRenderNode deviceRenderNode, Matrix matrix) {
            invoke2(deviceRenderNode, matrix);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DeviceRenderNode rn, Matrix matrix) {
            rn.getMatrix(matrix);
        }
    };
    private final OutlineResolver outlineResolver = new OutlineResolver();
    private final LayerMatrixCache<DeviceRenderNode> matrixCache = new LayerMatrixCache<>(getMatrix);
    private final CanvasHolder canvasHolder = new CanvasHolder();
    private long transformOrigin = TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ();

    public RenderNodeLayer(AndroidComposeView ownerView, Function2<? super Canvas, ? super GraphicsLayer, Unit> function2, Function0<Unit> function0) {
        this.ownerView = ownerView;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        DeviceRenderNode $this$renderNode_u24lambda_u240 = new RenderNodeApi29(this.ownerView);
        $this$renderNode_u24lambda_u240.setHasOverlappingRendering(true);
        $this$renderNode_u24lambda_u240.setClipToBounds(false);
        this.renderNode = $this$renderNode_u24lambda_u240;
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    private final void setDirty(boolean value) {
        if (value != this.isDirty) {
            this.isDirty = value;
            this.ownerView.notifyLayerIsDirty$ui_release(this, value);
        }
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getLayerId() {
        return this.renderNode.getUniqueId();
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getOwnerViewId() {
        return UniqueDrawingIdApi29.getUniqueDrawingId(this.ownerView);
    }

    /* compiled from: RenderNodeLayer.android.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer$UniqueDrawingIdApi29;", "", "()V", "getUniqueDrawingId", "", "view", "Landroid/view/View;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class UniqueDrawingIdApi29 {
        public static final UniqueDrawingIdApi29 INSTANCE = new UniqueDrawingIdApi29();

        private UniqueDrawingIdApi29() {
        }

        @JvmStatic
        public static final long getUniqueDrawingId(View view) {
            return view.getUniqueDrawingId();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope scope) {
        Function0<Unit> function0;
        int maybeChangedFields = scope.getMutatedFields() | this.mutatedFields;
        if ((maybeChangedFields & 4096) != 0) {
            this.transformOrigin = scope.getTransformOrigin();
        }
        boolean isClippingManually = false;
        boolean wasClippingManually = this.renderNode.getClipToOutline() && !this.outlineResolver.getOutlineClipSupported();
        if ((maybeChangedFields & 1) != 0) {
            this.renderNode.setScaleX(scope.getScaleX());
        }
        if ((maybeChangedFields & 2) != 0) {
            this.renderNode.setScaleY(scope.getScaleY());
        }
        if ((maybeChangedFields & 4) != 0) {
            this.renderNode.setAlpha(scope.getAlpha());
        }
        if ((maybeChangedFields & 8) != 0) {
            this.renderNode.setTranslationX(scope.getTranslationX());
        }
        if ((maybeChangedFields & 16) != 0) {
            this.renderNode.setTranslationY(scope.getTranslationY());
        }
        if ((maybeChangedFields & 32) != 0) {
            this.renderNode.setElevation(scope.getShadowElevation());
        }
        if ((maybeChangedFields & 64) != 0) {
            this.renderNode.setAmbientShadowColor(ColorKt.m4217toArgb8_81llA(scope.getAmbientShadowColor()));
        }
        if ((maybeChangedFields & 128) != 0) {
            this.renderNode.setSpotShadowColor(ColorKt.m4217toArgb8_81llA(scope.getSpotShadowColor()));
        }
        if ((maybeChangedFields & 1024) != 0) {
            this.renderNode.setRotationZ(scope.getRotationZ());
        }
        if ((maybeChangedFields & 256) != 0) {
            this.renderNode.setRotationX(scope.getRotationX());
        }
        if ((maybeChangedFields & 512) != 0) {
            this.renderNode.setRotationY(scope.getRotationY());
        }
        if ((maybeChangedFields & 2048) != 0) {
            this.renderNode.setCameraDistance(scope.getCameraDistance());
        }
        if ((maybeChangedFields & 4096) != 0) {
            this.renderNode.setPivotX(TransformOrigin.m4548getPivotFractionXimpl(this.transformOrigin) * this.renderNode.getWidth());
            this.renderNode.setPivotY(TransformOrigin.m4549getPivotFractionYimpl(this.transformOrigin) * this.renderNode.getHeight());
        }
        boolean clipToOutline = scope.getClip() && scope.getShape() != RectangleShapeKt.getRectangleShape();
        if ((maybeChangedFields & 24576) != 0) {
            this.renderNode.setClipToOutline(clipToOutline);
            this.renderNode.setClipToBounds(scope.getClip() && scope.getShape() == RectangleShapeKt.getRectangleShape());
        }
        if ((131072 & maybeChangedFields) != 0) {
            this.renderNode.setRenderEffect(scope.getRenderEffect());
        }
        if ((32768 & maybeChangedFields) != 0) {
            this.renderNode.mo5836setCompositingStrategyaDBOjCE(scope.getCompositingStrategy());
        }
        boolean shapeChanged = this.outlineResolver.m5876updateS_szKao(scope.getOutline(), scope.getAlpha(), clipToOutline, scope.getShadowElevation(), scope.getSize());
        if (this.outlineResolver.getCacheIsDirty()) {
            this.renderNode.setOutline(this.outlineResolver.getAndroidOutline());
        }
        if (clipToOutline && !this.outlineResolver.getOutlineClipSupported()) {
            isClippingManually = true;
        }
        if (wasClippingManually != isClippingManually || (isClippingManually && shapeChanged)) {
            invalidate();
        } else {
            triggerRepaint();
        }
        if (!this.drawnWithZ && this.renderNode.getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        if ((maybeChangedFields & Fields.MatrixAffectingFields) != 0) {
            this.matrixCache.invalidate();
        }
        this.mutatedFields = scope.getMutatedFields();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public boolean mo5774isInLayerk4lQ0M(long position) {
        float x = Offset.m3922getXimpl(position);
        float y = Offset.m3923getYimpl(position);
        if (this.renderNode.getClipToBounds()) {
            return 0.0f <= x && x < ((float) this.renderNode.getWidth()) && 0.0f <= y && y < ((float) this.renderNode.getHeight());
        }
        if (this.renderNode.getClipToOutline()) {
            return this.outlineResolver.m5875isInOutlinek4lQ0M(position);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public void mo5777resizeozmzZPI(long size) {
        int width = IntSize.m6796getWidthimpl(size);
        int height = IntSize.m6795getHeightimpl(size);
        this.renderNode.setPivotX(TransformOrigin.m4548getPivotFractionXimpl(this.transformOrigin) * width);
        this.renderNode.setPivotY(TransformOrigin.m4549getPivotFractionYimpl(this.transformOrigin) * height);
        if (this.renderNode.setPosition(this.renderNode.getLeft(), this.renderNode.getTop(), this.renderNode.getLeft() + width, this.renderNode.getTop() + height)) {
            this.renderNode.setOutline(this.outlineResolver.getAndroidOutline());
            invalidate();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public void mo5776movegyyYBs(long position) {
        int oldLeft = this.renderNode.getLeft();
        int oldTop = this.renderNode.getTop();
        int newLeft = IntOffset.m6754getXimpl(position);
        int newTop = IntOffset.m6755getYimpl(position);
        if (oldLeft != newLeft || oldTop != newTop) {
            if (oldLeft != newLeft) {
                this.renderNode.offsetLeftAndRight(newLeft - oldLeft);
            }
            if (oldTop != newTop) {
                this.renderNode.offsetTopAndBottom(newTop - oldTop);
            }
            triggerRepaint();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (!this.isDirty && !this.isDestroyed) {
            this.ownerView.invalidate();
            setDirty(true);
        }
    }

    private final void triggerRepaint() {
        WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas, GraphicsLayer parentLayer) {
        android.graphics.Canvas androidCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (androidCanvas.isHardwareAccelerated()) {
            updateDisplayList();
            this.drawnWithZ = this.renderNode.getElevation() > 0.0f;
            if (this.drawnWithZ) {
                canvas.enableZ();
            }
            this.renderNode.drawInto(androidCanvas);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = this.renderNode.getLeft();
        float top = this.renderNode.getTop();
        float right = this.renderNode.getRight();
        float bottom = this.renderNode.getBottom();
        if (this.renderNode.getAlpha() < 1.0f) {
            Paint it = this.softwareLayerPaint;
            if (it == null) {
                it = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = it;
            }
            Paint it2 = it;
            it2.setAlpha(this.renderNode.getAlpha());
            androidCanvas.saveLayer(left, top, right, bottom, it2.getInternalPaint());
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo4018concat58bKbWc(this.matrixCache.m5853calculateMatrixGrdbGEg(this.renderNode));
        clipRenderNode(canvas);
        Function2<? super Canvas, ? super GraphicsLayer, Unit> function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(canvas, null);
        }
        canvas.restore();
        setDirty(false);
    }

    private final void clipRenderNode(Canvas canvas) {
        if (this.renderNode.getClipToOutline() || this.renderNode.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        Path clipPath;
        if (this.isDirty || !this.renderNode.getHasDisplayList()) {
            if (this.renderNode.getClipToOutline() && !this.outlineResolver.getOutlineClipSupported()) {
                clipPath = this.outlineResolver.getClipPath();
            } else {
                clipPath = null;
            }
            final Function2 drawBlock = this.drawBlock;
            if (drawBlock != null) {
                this.renderNode.record(this.canvasHolder, clipPath, new Function1<Canvas, Unit>() { // from class: androidx.compose.ui.platform.RenderNodeLayer$updateDisplayList$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
                        invoke2(canvas);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Canvas it) {
                        drawBlock.invoke(it, null);
                    }
                });
            }
            setDirty(false);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        if (this.renderNode.getHasDisplayList()) {
            this.renderNode.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        this.ownerView.requestClearInvalidObservations();
        this.ownerView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public long mo5775mapOffset8S9VItk(long point, boolean inverse) {
        LayerMatrixCache<DeviceRenderNode> layerMatrixCache = this.matrixCache;
        if (inverse) {
            float[] m5852calculateInverseMatrixbWbORWo = layerMatrixCache.m5852calculateInverseMatrixbWbORWo(this.renderNode);
            return m5852calculateInverseMatrixbWbORWo != null ? androidx.compose.ui.graphics.Matrix.m4399mapMKHz9U(m5852calculateInverseMatrixbWbORWo, point) : Offset.INSTANCE.m3936getInfiniteF1C5BW0();
        }
        return androidx.compose.ui.graphics.Matrix.m4399mapMKHz9U(layerMatrixCache.m5853calculateMatrixGrdbGEg(this.renderNode), point);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect rect, boolean inverse) {
        LayerMatrixCache<DeviceRenderNode> layerMatrixCache = this.matrixCache;
        if (inverse) {
            float[] matrix = layerMatrixCache.m5852calculateInverseMatrixbWbORWo(this.renderNode);
            if (matrix == null) {
                rect.set(0.0f, 0.0f, 0.0f, 0.0f);
                return;
            } else {
                androidx.compose.ui.graphics.Matrix.m4401mapimpl(matrix, rect);
                return;
            }
        }
        androidx.compose.ui.graphics.Matrix.m4401mapimpl(layerMatrixCache.m5853calculateMatrixGrdbGEg(this.renderNode), rect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(Function2<? super Canvas, ? super GraphicsLayer, Unit> drawBlock, Function0<Unit> invalidateParentLayer) {
        setDirty(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        this.transformOrigin = TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ();
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: transform-58bKbWc */
    public void mo5778transform58bKbWc(float[] matrix) {
        androidx.compose.ui.graphics.Matrix.m4410timesAssign58bKbWc(matrix, this.matrixCache.m5853calculateMatrixGrdbGEg(this.renderNode));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: inverseTransform-58bKbWc */
    public void mo5773inverseTransform58bKbWc(float[] matrix) {
        float[] inverse = this.matrixCache.m5852calculateInverseMatrixbWbORWo(this.renderNode);
        if (inverse != null) {
            androidx.compose.ui.graphics.Matrix.m4410timesAssign58bKbWc(matrix, inverse);
        }
    }
}
