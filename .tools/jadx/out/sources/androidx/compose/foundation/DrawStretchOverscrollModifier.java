package androidx.compose.foundation;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.widget.EdgeEffect;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: AndroidOverscroll.android.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B.\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\fJ\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J\u001c\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J\u001c\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J\u001c\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J$\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002J\f\u0010%\u001a\u00020\n*\u00020&H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006'"}, d2 = {"Landroidx/compose/foundation/DrawStretchOverscrollModifier;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "overscrollEffect", "Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "edgeEffectWrapper", "Landroidx/compose/foundation/EdgeEffectWrapper;", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;Landroidx/compose/foundation/EdgeEffectWrapper;Lkotlin/jvm/functions/Function1;)V", "_renderNode", "Landroid/graphics/RenderNode;", "renderNode", "getRenderNode", "()Landroid/graphics/RenderNode;", "drawBottomStretch", "", "bottom", "Landroid/widget/EdgeEffect;", "canvas", "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "drawLeftStretch", "left", "drawRightStretch", "right", "drawTopStretch", "top", "drawWithRotation", "rotationDegrees", "", "edgeEffect", "shouldDrawHorizontalStretch", "shouldDrawVerticalStretch", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class DrawStretchOverscrollModifier extends InspectorValueInfo implements DrawModifier {
    private RenderNode _renderNode;
    private final EdgeEffectWrapper edgeEffectWrapper;
    private final AndroidEdgeEffectOverscrollEffect overscrollEffect;

    public DrawStretchOverscrollModifier(AndroidEdgeEffectOverscrollEffect overscrollEffect, EdgeEffectWrapper edgeEffectWrapper, Function1<? super InspectorInfo, Unit> function1) {
        super(function1);
        this.overscrollEffect = overscrollEffect;
        this.edgeEffectWrapper = edgeEffectWrapper;
    }

    private final RenderNode getRenderNode() {
        RenderNode renderNode = this._renderNode;
        if (renderNode != null) {
            return renderNode;
        }
        RenderNode it = new RenderNode("AndroidEdgeEffectOverscrollEffect");
        this._renderNode = it;
        return it;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope $this$draw) {
        float f;
        boolean needsInvalidate;
        this.overscrollEffect.m211updateSizeuvyYCjk$foundation_release($this$draw.mo4713getSizeNHjbRc());
        if (Size.m3993isEmptyimpl($this$draw.mo4713getSizeNHjbRc())) {
            $this$draw.drawContent();
            return;
        }
        this.overscrollEffect.getRedrawSignal$foundation_release().getValue();
        float maxElevation = $this$draw.mo364toPx0680j_4(ClipScrollableContainerKt.getMaxSupportedElevation());
        Canvas canvas = AndroidCanvas_androidKt.getNativeCanvas($this$draw.getDrawContext().getCanvas());
        boolean needsInvalidate2 = false;
        EdgeEffectWrapper $this$draw_u24lambda_u244 = this.edgeEffectWrapper;
        boolean shouldDrawVerticalStretch = shouldDrawVerticalStretch();
        boolean shouldDrawHorizontalStretch = shouldDrawHorizontalStretch();
        if (shouldDrawVerticalStretch && shouldDrawHorizontalStretch) {
            getRenderNode().setPosition(0, 0, canvas.getWidth(), canvas.getHeight());
        } else if (shouldDrawVerticalStretch) {
            getRenderNode().setPosition(0, 0, canvas.getWidth() + (MathKt.roundToInt(maxElevation) * 2), canvas.getHeight());
        } else {
            if (!shouldDrawHorizontalStretch) {
                $this$draw.drawContent();
                return;
            }
            getRenderNode().setPosition(0, 0, canvas.getWidth(), canvas.getHeight() + (MathKt.roundToInt(maxElevation) * 2));
        }
        RecordingCanvas recordingCanvas = getRenderNode().beginRecording();
        if ($this$draw_u24lambda_u244.isLeftNegationStretched()) {
            EdgeEffect leftEffectNegation = $this$draw_u24lambda_u244.getOrCreateLeftEffectNegation();
            drawRightStretch(leftEffectNegation, recordingCanvas);
            leftEffectNegation.finish();
        }
        if ($this$draw_u24lambda_u244.isLeftAnimating()) {
            EdgeEffect leftEffect = $this$draw_u24lambda_u244.getOrCreateLeftEffect();
            needsInvalidate2 = drawLeftStretch(leftEffect, recordingCanvas);
            if ($this$draw_u24lambda_u244.isLeftStretched()) {
                float displacementY = Offset.m3923getYimpl(this.overscrollEffect.m210displacementF1C5BW0$foundation_release());
                f = 1.0f;
                EdgeEffectCompat.INSTANCE.onPullDistanceCompat($this$draw_u24lambda_u244.getOrCreateLeftEffectNegation(), EdgeEffectCompat.INSTANCE.getDistanceCompat(leftEffect), 1.0f - displacementY);
            } else {
                f = 1.0f;
            }
        } else {
            f = 1.0f;
        }
        if ($this$draw_u24lambda_u244.isTopNegationStretched()) {
            EdgeEffect topEffectNegation = $this$draw_u24lambda_u244.getOrCreateTopEffectNegation();
            drawBottomStretch(topEffectNegation, recordingCanvas);
            topEffectNegation.finish();
        }
        if ($this$draw_u24lambda_u244.isTopAnimating()) {
            EdgeEffect topEffect = $this$draw_u24lambda_u244.getOrCreateTopEffect();
            needsInvalidate2 = drawTopStretch(topEffect, recordingCanvas) || needsInvalidate2;
            if ($this$draw_u24lambda_u244.isTopStretched()) {
                float displacementX = Offset.m3922getXimpl(this.overscrollEffect.m210displacementF1C5BW0$foundation_release());
                EdgeEffectCompat.INSTANCE.onPullDistanceCompat($this$draw_u24lambda_u244.getOrCreateTopEffectNegation(), EdgeEffectCompat.INSTANCE.getDistanceCompat(topEffect), displacementX);
            }
        }
        if ($this$draw_u24lambda_u244.isRightNegationStretched()) {
            EdgeEffect rightEffectNegation = $this$draw_u24lambda_u244.getOrCreateRightEffectNegation();
            drawLeftStretch(rightEffectNegation, recordingCanvas);
            rightEffectNegation.finish();
        }
        if ($this$draw_u24lambda_u244.isRightAnimating()) {
            EdgeEffect rightEffect = $this$draw_u24lambda_u244.getOrCreateRightEffect();
            needsInvalidate2 = drawRightStretch(rightEffect, recordingCanvas) || needsInvalidate2;
            if ($this$draw_u24lambda_u244.isRightStretched()) {
                float displacementY2 = Offset.m3923getYimpl(this.overscrollEffect.m210displacementF1C5BW0$foundation_release());
                EdgeEffectCompat.INSTANCE.onPullDistanceCompat($this$draw_u24lambda_u244.getOrCreateRightEffectNegation(), EdgeEffectCompat.INSTANCE.getDistanceCompat(rightEffect), displacementY2);
            }
        }
        if ($this$draw_u24lambda_u244.isBottomNegationStretched()) {
            EdgeEffect bottomEffectNegation = $this$draw_u24lambda_u244.getOrCreateBottomEffectNegation();
            drawTopStretch(bottomEffectNegation, recordingCanvas);
            bottomEffectNegation.finish();
        }
        if ($this$draw_u24lambda_u244.isBottomAnimating()) {
            EdgeEffect bottomEffect = $this$draw_u24lambda_u244.getOrCreateBottomEffect();
            boolean needsInvalidate3 = drawBottomStretch(bottomEffect, recordingCanvas) || needsInvalidate2;
            if ($this$draw_u24lambda_u244.isBottomStretched()) {
                float displacementX2 = Offset.m3922getXimpl(this.overscrollEffect.m210displacementF1C5BW0$foundation_release());
                EdgeEffectCompat.INSTANCE.onPullDistanceCompat($this$draw_u24lambda_u244.getOrCreateBottomEffectNegation(), EdgeEffectCompat.INSTANCE.getDistanceCompat(bottomEffect), f - displacementX2);
            }
            needsInvalidate = needsInvalidate3;
        } else {
            needsInvalidate = needsInvalidate2;
        }
        if (needsInvalidate) {
            this.overscrollEffect.invalidateOverscroll$foundation_release();
        }
        float left = shouldDrawHorizontalStretch ? 0.0f : maxElevation;
        float top = shouldDrawVerticalStretch ? 0.0f : maxElevation;
        ContentDrawScope $this$draw_u2dymL40Pk_u24default$iv = $this$draw;
        LayoutDirection layoutDirection$iv = $this$draw.getLayoutDirection();
        androidx.compose.ui.graphics.Canvas canvas$iv = AndroidCanvas_androidKt.Canvas(recordingCanvas);
        long size$iv = $this$draw.mo4713getSizeNHjbRc();
        Density prevDensity$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getDensity();
        LayoutDirection prevLayoutDirection$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getLayoutDirection();
        androidx.compose.ui.graphics.Canvas prevCanvas$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getCanvas();
        long prevSize$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().mo4634getSizeNHjbRc();
        GraphicsLayer prevLayer$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getGraphicsLayer();
        DrawContext $this$draw_ymL40Pk_u24lambda_u247$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext();
        $this$draw_ymL40Pk_u24lambda_u247$iv.setDensity($this$draw);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setLayoutDirection(layoutDirection$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setCanvas(canvas$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.mo4635setSizeuvyYCjk(size$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setGraphicsLayer(null);
        canvas$iv.save();
        try {
            $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getTransform().translate(left, top);
            try {
                try {
                    $this$draw.drawContent();
                    $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getTransform().translate(-left, -top);
                    canvas$iv.restore();
                    DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext();
                    $this$draw_ymL40Pk_u24lambda_u248$iv.setDensity(prevDensity$iv);
                    $this$draw_ymL40Pk_u24lambda_u248$iv.setLayoutDirection(prevLayoutDirection$iv);
                    $this$draw_ymL40Pk_u24lambda_u248$iv.setCanvas(prevCanvas$iv);
                    $this$draw_ymL40Pk_u24lambda_u248$iv.mo4635setSizeuvyYCjk(prevSize$iv);
                    $this$draw_ymL40Pk_u24lambda_u248$iv.setGraphicsLayer(prevLayer$iv);
                    getRenderNode().endRecording();
                    int restore = canvas.save();
                    canvas.translate(-left, -top);
                    canvas.drawRenderNode(getRenderNode());
                    canvas.restoreToCount(restore);
                } catch (Throwable th) {
                    $this$draw_u2dymL40Pk_u24default$iv.getDrawContext().getTransform().translate(-left, -top);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                canvas$iv.restore();
                DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv2 = $this$draw_u2dymL40Pk_u24default$iv.getDrawContext();
                $this$draw_ymL40Pk_u24lambda_u248$iv2.setDensity(prevDensity$iv);
                $this$draw_ymL40Pk_u24lambda_u248$iv2.setLayoutDirection(prevLayoutDirection$iv);
                $this$draw_ymL40Pk_u24lambda_u248$iv2.setCanvas(prevCanvas$iv);
                $this$draw_ymL40Pk_u24lambda_u248$iv2.mo4635setSizeuvyYCjk(prevSize$iv);
                $this$draw_ymL40Pk_u24lambda_u248$iv2.setGraphicsLayer(prevLayer$iv);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private final boolean shouldDrawVerticalStretch() {
        EdgeEffectWrapper $this$shouldDrawVerticalStretch_u24lambda_u245 = this.edgeEffectWrapper;
        return $this$shouldDrawVerticalStretch_u24lambda_u245.isTopAnimating() || $this$shouldDrawVerticalStretch_u24lambda_u245.isTopNegationStretched() || $this$shouldDrawVerticalStretch_u24lambda_u245.isBottomAnimating() || $this$shouldDrawVerticalStretch_u24lambda_u245.isBottomNegationStretched();
    }

    private final boolean shouldDrawHorizontalStretch() {
        EdgeEffectWrapper $this$shouldDrawHorizontalStretch_u24lambda_u246 = this.edgeEffectWrapper;
        return $this$shouldDrawHorizontalStretch_u24lambda_u246.isLeftAnimating() || $this$shouldDrawHorizontalStretch_u24lambda_u246.isLeftNegationStretched() || $this$shouldDrawHorizontalStretch_u24lambda_u246.isRightAnimating() || $this$shouldDrawHorizontalStretch_u24lambda_u246.isRightNegationStretched();
    }

    private final boolean drawLeftStretch(EdgeEffect left, Canvas canvas) {
        return drawWithRotation(270.0f, left, canvas);
    }

    private final boolean drawTopStretch(EdgeEffect top, Canvas canvas) {
        return drawWithRotation(0.0f, top, canvas);
    }

    private final boolean drawRightStretch(EdgeEffect right, Canvas canvas) {
        return drawWithRotation(90.0f, right, canvas);
    }

    private final boolean drawBottomStretch(EdgeEffect bottom, Canvas canvas) {
        return drawWithRotation(180.0f, bottom, canvas);
    }

    private final boolean drawWithRotation(float rotationDegrees, EdgeEffect edgeEffect, Canvas canvas) {
        if (rotationDegrees == 0.0f) {
            boolean needsInvalidate = edgeEffect.draw(canvas);
            return needsInvalidate;
        }
        int restore = canvas.save();
        canvas.rotate(rotationDegrees);
        boolean needsInvalidate2 = edgeEffect.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate2;
    }
}
