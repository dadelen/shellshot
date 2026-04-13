package androidx.compose.foundation;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: AndroidOverscroll.android.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B6\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\u0010\u000eJ6\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\f\u0010\u001c\u001a\u00020\f*\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u0010*\u00020\u001f2\u0006\u0010 \u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002J \u0010!\u001a\u00020\u0010*\u00020\u001f2\u0006\u0010\"\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002J \u0010#\u001a\u00020\u0010*\u00020\u001f2\u0006\u0010$\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002J \u0010%\u001a\u00020\u0010*\u00020\u001f2\u0006\u0010&\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/foundation/DrawGlowOverscrollModifier;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "overscrollEffect", "Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "edgeEffectWrapper", "Landroidx/compose/foundation/EdgeEffectWrapper;", "overscrollConfig", "Landroidx/compose/foundation/OverscrollConfiguration;", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;Landroidx/compose/foundation/EdgeEffectWrapper;Landroidx/compose/foundation/OverscrollConfiguration;Lkotlin/jvm/functions/Function1;)V", "drawWithRotationAndOffset", "", "rotationDegrees", "", "offset", "Landroidx/compose/ui/geometry/Offset;", "edgeEffect", "Landroid/widget/EdgeEffect;", "canvas", "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "drawWithRotationAndOffset-ubNVwUQ", "(FJLandroid/widget/EdgeEffect;Landroid/graphics/Canvas;)Z", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawBottomGlow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "bottom", "drawLeftGlow", "left", "drawRightGlow", "right", "drawTopGlow", "top", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class DrawGlowOverscrollModifier extends InspectorValueInfo implements DrawModifier {
    private final EdgeEffectWrapper edgeEffectWrapper;
    private final OverscrollConfiguration overscrollConfig;
    private final AndroidEdgeEffectOverscrollEffect overscrollEffect;

    public DrawGlowOverscrollModifier(AndroidEdgeEffectOverscrollEffect overscrollEffect, EdgeEffectWrapper edgeEffectWrapper, OverscrollConfiguration overscrollConfig, Function1<? super InspectorInfo, Unit> function1) {
        super(function1);
        this.overscrollEffect = overscrollEffect;
        this.edgeEffectWrapper = edgeEffectWrapper;
        this.overscrollConfig = overscrollConfig;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope $this$draw) {
        this.overscrollEffect.m211updateSizeuvyYCjk$foundation_release($this$draw.mo4713getSizeNHjbRc());
        if (Size.m3993isEmptyimpl($this$draw.mo4713getSizeNHjbRc())) {
            $this$draw.drawContent();
            return;
        }
        $this$draw.drawContent();
        this.overscrollEffect.getRedrawSignal$foundation_release().getValue();
        Canvas canvas = AndroidCanvas_androidKt.getNativeCanvas($this$draw.getDrawContext().getCanvas());
        boolean needsInvalidate = false;
        EdgeEffectWrapper $this$draw_u24lambda_u240 = this.edgeEffectWrapper;
        if ($this$draw_u24lambda_u240.isLeftAnimating()) {
            EdgeEffect leftEffect = $this$draw_u24lambda_u240.getOrCreateLeftEffect();
            needsInvalidate = drawLeftGlow($this$draw, leftEffect, canvas);
        }
        if ($this$draw_u24lambda_u240.isTopAnimating()) {
            EdgeEffect topEffect = $this$draw_u24lambda_u240.getOrCreateTopEffect();
            needsInvalidate = drawTopGlow($this$draw, topEffect, canvas) || needsInvalidate;
        }
        if ($this$draw_u24lambda_u240.isRightAnimating()) {
            EdgeEffect rightEffect = $this$draw_u24lambda_u240.getOrCreateRightEffect();
            needsInvalidate = drawRightGlow($this$draw, rightEffect, canvas) || needsInvalidate;
        }
        if ($this$draw_u24lambda_u240.isBottomAnimating()) {
            EdgeEffect bottomEffect = $this$draw_u24lambda_u240.getOrCreateBottomEffect();
            needsInvalidate = drawBottomGlow($this$draw, bottomEffect, canvas) || needsInvalidate;
        }
        if (needsInvalidate) {
            this.overscrollEffect.invalidateOverscroll$foundation_release();
        }
    }

    private final boolean drawLeftGlow(DrawScope $this$drawLeftGlow, EdgeEffect left, Canvas canvas) {
        long offset = OffsetKt.Offset(-Size.m3988getHeightimpl($this$drawLeftGlow.mo4713getSizeNHjbRc()), $this$drawLeftGlow.mo364toPx0680j_4(this.overscrollConfig.getDrawPadding().mo623calculateLeftPaddingu2uoSUM($this$drawLeftGlow.getLayoutDirection())));
        return m281drawWithRotationAndOffsetubNVwUQ(270.0f, offset, left, canvas);
    }

    private final boolean drawTopGlow(DrawScope $this$drawTopGlow, EdgeEffect top, Canvas canvas) {
        long offset = OffsetKt.Offset(0.0f, $this$drawTopGlow.mo364toPx0680j_4(this.overscrollConfig.getDrawPadding().getTop()));
        return m281drawWithRotationAndOffsetubNVwUQ(0.0f, offset, top, canvas);
    }

    private final boolean drawRightGlow(DrawScope $this$drawRightGlow, EdgeEffect right, Canvas canvas) {
        int width = MathKt.roundToInt(Size.m3991getWidthimpl($this$drawRightGlow.mo4713getSizeNHjbRc()));
        float rightPadding = this.overscrollConfig.getDrawPadding().mo624calculateRightPaddingu2uoSUM($this$drawRightGlow.getLayoutDirection());
        long offset = OffsetKt.Offset(0.0f, (-width) + $this$drawRightGlow.mo364toPx0680j_4(rightPadding));
        return m281drawWithRotationAndOffsetubNVwUQ(90.0f, offset, right, canvas);
    }

    private final boolean drawBottomGlow(DrawScope $this$drawBottomGlow, EdgeEffect bottom, Canvas canvas) {
        float bottomPadding = $this$drawBottomGlow.mo364toPx0680j_4(this.overscrollConfig.getDrawPadding().getBottom());
        long offset = OffsetKt.Offset(-Size.m3991getWidthimpl($this$drawBottomGlow.mo4713getSizeNHjbRc()), (-Size.m3988getHeightimpl($this$drawBottomGlow.mo4713getSizeNHjbRc())) + bottomPadding);
        return m281drawWithRotationAndOffsetubNVwUQ(180.0f, offset, bottom, canvas);
    }

    /* renamed from: drawWithRotationAndOffset-ubNVwUQ, reason: not valid java name */
    private final boolean m281drawWithRotationAndOffsetubNVwUQ(float rotationDegrees, long offset, EdgeEffect edgeEffect, Canvas canvas) {
        int restore = canvas.save();
        canvas.rotate(rotationDegrees);
        canvas.translate(Offset.m3922getXimpl(offset), Offset.m3923getYimpl(offset));
        boolean needsInvalidate = edgeEffect.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate;
    }
}
