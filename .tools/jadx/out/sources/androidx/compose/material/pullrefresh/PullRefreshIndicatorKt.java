package androidx.compose.material.pullrefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PullRefreshIndicator.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0002\u001a*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aJ\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u001a2\b\b\u0002\u0010#\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020!H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a>\u0010'\u001a\u00020\u0016*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0013H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u00100\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\f\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\n\u00102\u001a\u00020!X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "", "ArcRadius", "Landroidx/compose/ui/unit/Dp;", "F", "ArrowHeight", "ArrowWidth", "CrossfadeDurationMs", "", "Elevation", "IndicatorSize", "MaxAlpha", "MaxProgressArc", "MinAlpha", "SpinnerShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "StrokeWidth", "ArrowValues", "Landroidx/compose/material/pullrefresh/ArrowValues;", NotificationCompat.CATEGORY_PROGRESS, "CircularArrowIndicator", "", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "color", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "CircularArrowIndicator-iJQMabo", "(Landroidx/compose/material/pullrefresh/PullRefreshState;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "PullRefreshIndicator", "refreshing", "", "backgroundColor", "contentColor", "scale", "PullRefreshIndicator-jB83MbM", "(ZLandroidx/compose/material/pullrefresh/PullRefreshState;Landroidx/compose/ui/Modifier;JJZLandroidx/compose/runtime/Composer;II)V", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "drawArrow-Bx497Mc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material/pullrefresh/ArrowValues;)V", "material_release", "showElevation", "targetAlpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PullRefreshIndicatorKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float IndicatorSize = Dp.m6626constructorimpl(40);
    private static final RoundedCornerShape SpinnerShape = RoundedCornerShapeKt.getCircleShape();
    private static final float ArcRadius = Dp.m6626constructorimpl((float) 7.5d);
    private static final float StrokeWidth = Dp.m6626constructorimpl((float) 2.5d);
    private static final float ArrowWidth = Dp.m6626constructorimpl(10);
    private static final float ArrowHeight = Dp.m6626constructorimpl(5);
    private static final float Elevation = Dp.m6626constructorimpl(6);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX WARN: Removed duplicated region for block: B:64:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01aa  */
    /* renamed from: PullRefreshIndicator-jB83MbM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1736PullRefreshIndicatorjB83MbM(final boolean r42, final androidx.compose.material.pullrefresh.PullRefreshState r43, androidx.compose.ui.Modifier r44, long r45, long r47, boolean r49, androidx.compose.runtime.Composer r50, final int r51, final int r52) {
        /*
            Method dump skipped, instructions count: 922
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt.m1736PullRefreshIndicatorjB83MbM(boolean, androidx.compose.material.pullrefresh.PullRefreshState, androidx.compose.ui.Modifier, long, long, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean PullRefreshIndicator_jB83MbM$lambda$1(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularArrowIndicator-iJQMabo, reason: not valid java name */
    public static final void m1735CircularArrowIndicatoriJQMabo(final PullRefreshState pullRefreshState, final long j, final Modifier modifier, Composer composer, final int i) {
        Object obj;
        Object derivedStateOf;
        Object obj2;
        Composer startRestartGroup = composer.startRestartGroup(-486016981);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularArrowIndicator)P(2,0:c#ui.graphics.Color)135@5348L61,137@5434L119,143@5576L74,146@5719L970,146@5689L1000:PullRefreshIndicator.kt#t44y28");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changedInstance(pullRefreshState) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-486016981, i2, -1, "androidx.compose.material.pullrefresh.CircularArrowIndicator (PullRefreshIndicator.kt:134)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -659480864, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Path Path = AndroidPath_androidKt.Path();
                Path.mo4056setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m4444getEvenOddRgk1Os());
                startRestartGroup.updateRememberedValue(Path);
                obj = Path;
            } else {
                obj = rememberedValue;
            }
            final Path path = (Path) obj;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -659478054, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            boolean changed = startRestartGroup.changed(pullRefreshState);
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                derivedStateOf = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$targetAlpha$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(PullRefreshState.this.getProgress() < 1.0f ? 0.3f : 1.0f);
                    }
                });
                startRestartGroup.updateRememberedValue(derivedStateOf);
            } else {
                derivedStateOf = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final State<Float> animateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowIndicator_iJQMabo$lambda$6((State) derivedStateOf), AlphaTween, 0.0f, null, null, startRestartGroup, 48, 28);
            Modifier semantics$default = SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                }
            }, 1, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -659468083, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(pullRefreshState) | startRestartGroup.changed(animateFloatAsState) | ((i2 & 112) == 32) | startRestartGroup.changedInstance(path);
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        ArrowValues values;
                        long previousSize$iv$iv;
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
                        float f;
                        float f2;
                        float f3;
                        values = PullRefreshIndicatorKt.ArrowValues(PullRefreshState.this.getProgress());
                        float alpha = animateFloatAsState.getValue().floatValue();
                        float degrees$iv = values.getRotation();
                        long previousSize$iv$iv2 = j;
                        Path path2 = path;
                        long pivot$iv = $this$Canvas.mo4712getCenterF1C5BW0();
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
                        long previousSize$iv$iv3 = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
                        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
                        try {
                            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
                            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo4640rotateUv8p0NA(degrees$iv, pivot$iv);
                            try {
                                f = PullRefreshIndicatorKt.ArcRadius;
                                float f4 = $this$Canvas.mo364toPx0680j_4(f);
                                f2 = PullRefreshIndicatorKt.StrokeWidth;
                                float arcRadius = f4 + ($this$Canvas.mo364toPx0680j_4(f2) / 2.0f);
                                Rect arcBounds = new Rect(Offset.m3922getXimpl(SizeKt.m4001getCenteruvyYCjk($this$Canvas.mo4713getSizeNHjbRc())) - arcRadius, Offset.m3923getYimpl(SizeKt.m4001getCenteruvyYCjk($this$Canvas.mo4713getSizeNHjbRc())) - arcRadius, Offset.m3922getXimpl(SizeKt.m4001getCenteruvyYCjk($this$Canvas.mo4713getSizeNHjbRc())) + arcRadius, Offset.m3923getYimpl(SizeKt.m4001getCenteruvyYCjk($this$Canvas.mo4713getSizeNHjbRc())) + arcRadius);
                                previousSize$iv$iv = previousSize$iv$iv3;
                                try {
                                    float startAngle = values.getStartAngle();
                                    float endAngle = values.getEndAngle() - values.getStartAngle();
                                    long m3957getTopLeftF1C5BW0 = arcBounds.m3957getTopLeftF1C5BW0();
                                    long m3955getSizeNHjbRc = arcBounds.m3955getSizeNHjbRc();
                                    f3 = PullRefreshIndicatorKt.StrokeWidth;
                                    try {
                                        DrawScope.m4692drawArcyD3GUKo$default($this$Canvas, previousSize$iv$iv2, startAngle, endAngle, false, m3957getTopLeftF1C5BW0, m3955getSizeNHjbRc, alpha, new Stroke($this$Canvas.mo364toPx0680j_4(f3), 0.0f, StrokeCap.INSTANCE.m4517getSquareKaPHkGw(), 0, null, 26, null), null, 0, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
                                        previousSize$iv$iv = previousSize$iv$iv;
                                        $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                        try {
                                            PullRefreshIndicatorKt.m1739drawArrowBx497Mc($this$Canvas, path2, arcBounds, previousSize$iv$iv2, alpha, values);
                                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                        } catch (Throwable th) {
                                            th = th;
                                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                        previousSize$iv$iv = previousSize$iv$iv;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                previousSize$iv$iv = previousSize$iv$iv3;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            previousSize$iv$iv = previousSize$iv$iv3;
                            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                        }
                    }
                };
                startRestartGroup.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(semantics$default, (Function1) obj2, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    PullRefreshIndicatorKt.m1735CircularArrowIndicatoriJQMabo(PullRefreshState.this, j, modifier, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final float CircularArrowIndicator_iJQMabo$lambda$6(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5.0f) / 3.0f;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float $this$fastCoerceAtLeast$iv$iv = overshootPercent;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv = 2.0f;
        }
        float tensionPercent = $this$fastCoerceAtLeast$iv$iv - (((float) Math.pow($this$fastCoerceAtLeast$iv$iv, 2.0d)) / 4.0f);
        float endTrim = 0.8f * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float startAngle = rotation * 360.0f;
        float endAngle = (rotation + endTrim) * 360.0f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawArrow-Bx497Mc, reason: not valid java name */
    public static final void m1739drawArrowBx497Mc(DrawScope $this$drawArrow_u2dBx497Mc, Path arrow, Rect bounds, long color, float alpha, ArrowValues values) {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        arrow.lineTo($this$drawArrow_u2dBx497Mc.mo364toPx0680j_4(ArrowWidth) * values.getScale(), 0.0f);
        arrow.lineTo(($this$drawArrow_u2dBx497Mc.mo364toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f, $this$drawArrow_u2dBx497Mc.mo364toPx0680j_4(ArrowHeight) * values.getScale());
        float radius = Math.min(bounds.getWidth(), bounds.getHeight()) / 2.0f;
        float inset = ($this$drawArrow_u2dBx497Mc.mo364toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f;
        arrow.mo4058translatek4lQ0M(OffsetKt.Offset((Offset.m3922getXimpl(bounds.m3952getCenterF1C5BW0()) + radius) - inset, Offset.m3923getYimpl(bounds.m3952getCenterF1C5BW0()) + ($this$drawArrow_u2dBx497Mc.mo364toPx0680j_4(StrokeWidth) / 2.0f)));
        arrow.close();
        float degrees$iv = values.getEndAngle();
        long pivot$iv = $this$drawArrow_u2dBx497Mc.mo4712getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawArrow_u2dBx497Mc.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo4640rotateUv8p0NA(degrees$iv, pivot$iv);
            previousSize$iv$iv = previousSize$iv$iv2;
            try {
                DrawScope.m4703drawPathLG529CI$default($this$drawArrow_u2dBx497Mc, arrow, color, alpha, null, null, 0, 56, null);
                $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv2.mo4635setSizeuvyYCjk(previousSize$iv$iv);
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            previousSize$iv$iv = previousSize$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
    }
}
