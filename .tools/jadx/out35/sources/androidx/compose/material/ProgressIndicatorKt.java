package androidx.compose.material;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.data.AppPrefs;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* compiled from: ProgressIndicator.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a0\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001aD\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010(\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a8\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001aN\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010,\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010(\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a0\u00101\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a:\u00101\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010(\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a8\u00101\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001aD\u00101\u001a\u00020\u001f2\b\b\u0001\u0010,\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010'\u001a\u00020#2\b\b\u0002\u0010(\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a6\u0010:\u001a\u00020\u001f*\u00020;2\u0006\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a&\u0010B\u001a\u00020\u001f*\u00020;2\u0006\u0010\"\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a6\u0010E\u001a\u00020\u001f*\u00020;2\u0006\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010A\u001a>\u0010G\u001a\u00020\u001f*\u00020;2\u0006\u0010<\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0002ø\u0001\u0000¢\u0006\u0004\bH\u0010I\u001a>\u0010J\u001a\u00020\u001f*\u00020;2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00012\u0006\u0010(\u001a\u00020)H\u0002ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a.\u0010O\u001a\u00020\u001f*\u00020;2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00012\u0006\u0010(\u001a\u00020)H\u0002ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a\f\u0010R\u001a\u00020!*\u00020!H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0013\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006S²\u0006\n\u0010T\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010U\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010V\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010W\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010X\u001a\u00020\bX\u008a\u0084\u0002²\u0006\n\u0010Y\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010Z\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010<\u001a\u00020\u0001X\u008a\u0084\u0002"}, d2 = {"BaseRotationAngle", "", "CircularEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "CircularIndicatorDiameter", "Landroidx/compose/ui/unit/Dp;", "F", "FirstLineHeadDelay", "", "FirstLineHeadDuration", "FirstLineHeadEasing", "FirstLineTailDelay", "FirstLineTailDuration", "FirstLineTailEasing", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "JumpRotationAngle", "LinearAnimationDuration", "LinearIndicatorHeight", "LinearIndicatorWidth", "RotationAngleOffset", "RotationDuration", "RotationsPerCycle", "SecondLineHeadDelay", "SecondLineHeadDuration", "SecondLineHeadEasing", "SecondLineTailDelay", "SecondLineTailDuration", "SecondLineTailEasing", "StartAngleOffset", "CircularProgressIndicator", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "backgroundColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", NotificationCompat.CATEGORY_PROGRESS, "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-DUhRLBM", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-_5eSR-E", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorBackground", "drawCircularIndicatorBackground-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawLinearIndicator", "startFraction", "endFraction", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "drawLinearIndicatorBackground", "drawLinearIndicatorBackground-AZGd3zU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFI)V", "increaseSemanticsBounds", "material_release", "firstLineHead", "firstLineTail", "secondLineHead", "secondLineTail", "currentRotation", "baseRotation", "endAngle"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final float StartAngleOffset = -90.0f;
    private static final float LinearIndicatorHeight = ProgressIndicatorDefaults.INSTANCE.m1601getStrokeWidthD9Ej5fM();
    private static final float LinearIndicatorWidth = Dp.m6626constructorimpl(AppPrefs.MAX_RECENT_PROCESSED_KEYS);
    private static final float CircularIndicatorDiameter = Dp.m6626constructorimpl(40);
    private static final CubicBezierEasing FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
    private static final CubicBezierEasing FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
    private static final CubicBezierEasing SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
    private static final CubicBezierEasing CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);

    public static final Modifier increaseSemanticsBounds(Modifier $this$increaseSemanticsBounds) {
        final float padding = Dp.m6626constructorimpl(10);
        return PaddingKt.m674paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout($this$increaseSemanticsBounds, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material.ProgressIndicatorKt$increaseSemanticsBounds$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m1621invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m1621invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                final int paddingPx = $this$layout.mo358roundToPx0680j_4(padding);
                long newConstraint = ConstraintsKt.m6598offsetNN6EwU(constraints, 0, paddingPx * 2);
                final Placeable placeable = measurable.mo5491measureBRTryo0(newConstraint);
                int height = placeable.getHeight() - (paddingPx * 2);
                int width = placeable.getWidth();
                return MeasureScope.layout$default($this$layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$increaseSemanticsBounds$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        Placeable.PlacementScope.place$default($this$layout2, Placeable.this, 0, -paddingPx, 0.0f, 4, null);
                    }
                }, 4, null);
            }
        }), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$increaseSemanticsBounds$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
            }
        }), 0.0f, padding, 1, null);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m1608LinearProgressIndicator_5eSRE(float r24, androidx.compose.ui.Modifier r25, long r26, long r28, int r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1608LinearProgressIndicator_5eSRE(float, androidx.compose.ui.Modifier, long, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final void m1606LinearProgressIndicator2cYBFYY(androidx.compose.ui.Modifier r37, long r38, long r40, int r42, androidx.compose.runtime.Composer r43, int r44, int r45) {
        /*
            Method dump skipped, instructions count: 731
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1606LinearProgressIndicator2cYBFYY(androidx.compose.ui.Modifier, long, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float LinearProgressIndicator_2cYBFYY$lambda$1(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float LinearProgressIndicator_2cYBFYY$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float LinearProgressIndicator_2cYBFYY$lambda$3(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float LinearProgressIndicator_2cYBFYY$lambda$4(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m1609LinearProgressIndicatoreaDK9VM(float r24, androidx.compose.ui.Modifier r25, long r26, long r28, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1609LinearProgressIndicatoreaDK9VM(float, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m1607LinearProgressIndicatorRIQooxk(androidx.compose.ui.Modifier r24, long r25, long r27, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1607LinearProgressIndicatorRIQooxk(androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    public static final void m1619drawLinearIndicatorqYKTg0g(DrawScope $this$drawLinearIndicator_u2dqYKTg0g, float startFraction, float endFraction, long color, float strokeWidth, int strokeCap) {
        float width = Size.m3991getWidthimpl($this$drawLinearIndicator_u2dqYKTg0g.mo4713getSizeNHjbRc());
        float height = Size.m3988getHeightimpl($this$drawLinearIndicator_u2dqYKTg0g.mo4713getSizeNHjbRc());
        float yOffset = height / 2.0f;
        boolean isLtr = $this$drawLinearIndicator_u2dqYKTg0g.getLayoutDirection() == LayoutDirection.Ltr;
        float barStart = (isLtr ? startFraction : 1.0f - endFraction) * width;
        float barEnd = (isLtr ? endFraction : 1.0f - startFraction) * width;
        if (StrokeCap.m4511equalsimpl0(strokeCap, StrokeCap.INSTANCE.m4515getButtKaPHkGw()) || height > width) {
            DrawScope.m4699drawLineNGM6Ib0$default($this$drawLinearIndicator_u2dqYKTg0g, color, OffsetKt.Offset(barStart, yOffset), OffsetKt.Offset(barEnd, yOffset), strokeWidth, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float strokeCapOffset = strokeWidth / 2.0f;
        ClosedFloatingPointRange coerceRange = RangesKt.rangeTo(strokeCapOffset, width - strokeCapOffset);
        float adjustedBarStart = ((Number) RangesKt.coerceIn(Float.valueOf(barStart), (ClosedFloatingPointRange<Float>) coerceRange)).floatValue();
        float adjustedBarEnd = ((Number) RangesKt.coerceIn(Float.valueOf(barEnd), (ClosedFloatingPointRange<Float>) coerceRange)).floatValue();
        if (Math.abs(endFraction - startFraction) > 0.0f) {
            DrawScope.m4699drawLineNGM6Ib0$default($this$drawLinearIndicator_u2dqYKTg0g, color, OffsetKt.Offset(adjustedBarStart, yOffset), OffsetKt.Offset(adjustedBarEnd, yOffset), strokeWidth, strokeCap, null, 0.0f, null, 0, 480, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicatorBackground-AZGd3zU, reason: not valid java name */
    public static final void m1620drawLinearIndicatorBackgroundAZGd3zU(DrawScope $this$drawLinearIndicatorBackground_u2dAZGd3zU, long color, float strokeWidth, int strokeCap) {
        m1619drawLinearIndicatorqYKTg0g($this$drawLinearIndicatorBackground_u2dAZGd3zU, 0.0f, 1.0f, color, strokeWidth, strokeCap);
    }

    /* renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m1602CircularProgressIndicatorDUhRLBM(final float progress, Modifier modifier, long color, float strokeWidth, long backgroundColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float strokeWidth2;
        long backgroundColor2;
        int i2;
        Modifier.Companion modifier3;
        int strokeCap2;
        long backgroundColor3;
        Object value$iv;
        final Modifier modifier4;
        final long color3;
        final long backgroundColor4;
        final int strokeCap3;
        final float strokeWidth3;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(1746618448);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)P(3,2,1:c#ui.graphics.Color,5:c#ui.unit.Dp,0:c#ui.graphics.Color,4:c#ui.graphics.StrokeCap)322@13149L6,*328@13412L7,335@13617L251,331@13493L375:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(progress) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i4 = 256;
                    $dirty |= i4;
                }
            } else {
                color2 = color;
            }
            i4 = 128;
            $dirty |= i4;
        } else {
            color2 = color;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty |= $composer2.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            backgroundColor2 = backgroundColor;
        } else if (($changed & 24576) == 0) {
            backgroundColor2 = backgroundColor;
            $dirty |= $composer2.changed(backgroundColor2) ? 16384 : 8192;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                i2 = strokeCap;
                if ($composer2.changed(i2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                i2 = strokeCap;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            i2 = strokeCap;
        }
        if (($dirty & 74899) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            color3 = color2;
            strokeCap3 = i2;
            backgroundColor4 = backgroundColor2;
            strokeWidth3 = strokeWidth2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1478getPrimary0d7_KjU();
                }
                if (i6 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m1601getStrokeWidthD9Ej5fM();
                }
                if (i7 != 0) {
                    backgroundColor2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    strokeCap2 = StrokeCap.INSTANCE.m4515getButtKaPHkGw();
                } else {
                    strokeCap2 = i2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    modifier3 = modifier2;
                    strokeCap2 = i2;
                } else {
                    modifier3 = modifier2;
                    strokeCap2 = i2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1746618448, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:326)");
            }
            float $this$fastCoerceAtLeast$iv$iv = progress;
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv = 1.0f;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$CircularProgressIndicator_DUhRLBM_u24lambda_u246 = (Density) consume;
            final Stroke stroke = new Stroke($this$CircularProgressIndicator_DUhRLBM_u24lambda_u246.mo364toPx0680j_4(strokeWidth2), 0.0f, strokeCap2, 0, null, 26, null);
            Modifier modifier5 = modifier3;
            float coercedProgress = $this$fastCoerceAtLeast$iv$iv;
            Modifier m717size3ABfNKs = SizeKt.m717size3ABfNKs(ProgressSemanticsKt.progressSemantics$default(modifier5, coercedProgress, null, 0, 6, null), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart($composer2, -973402056, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean z = true;
            boolean changed = $composer2.changed($this$fastCoerceAtLeast$iv$iv) | ((57344 & $dirty) == 16384) | $composer2.changedInstance(stroke);
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = changed | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final long color4 = color2;
                final float coercedProgress2 = $this$fastCoerceAtLeast$iv$iv;
                final long backgroundColor5 = backgroundColor2;
                backgroundColor3 = backgroundColor5;
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$1$1
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
                        float sweep = 360.0f * coercedProgress2;
                        ProgressIndicatorKt.m1616drawCircularIndicatorBackgroundbw27NRU($this$Canvas, backgroundColor5, stroke);
                        ProgressIndicatorKt.m1617drawDeterminateCircularIndicator42QJj7c($this$Canvas, 270.0f, sweep, color4, stroke);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
                backgroundColor3 = backgroundColor2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m717size3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            color3 = color2;
            backgroundColor4 = backgroundColor3;
            strokeCap3 = strokeCap2;
            strokeWidth3 = strokeWidth2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    ProgressIndicatorKt.m1602CircularProgressIndicatorDUhRLBM(progress, modifier4, color3, strokeWidth3, backgroundColor4, strokeCap3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0340  */
    /* renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1603CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier r32, long r33, float r35, long r36, int r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1603CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier, long, float, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CircularProgressIndicator_LxG7B9w$lambda$9(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float CircularProgressIndicator_LxG7B9w$lambda$10(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float CircularProgressIndicator_LxG7B9w$lambda$11(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float CircularProgressIndicator_LxG7B9w$lambda$12(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m1604CircularProgressIndicatorMBs18nI(final float progress, Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j;
        float f2;
        long color2;
        Modifier modifier3;
        float strokeWidth2;
        long color3;
        final Modifier modifier4;
        final long color4;
        final float strokeWidth3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-409649739);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,3:c#ui.unit.Dp)441@17676L6,443@17756L157:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = progress;
        } else if (($changed & 6) == 0) {
            f = progress;
            $dirty |= $composer2.changed(f) ? 4 : 2;
        } else {
            f = progress;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                if ($composer2.changed(j)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                j = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            j = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            f2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            f2 = strokeWidth;
            $dirty |= $composer2.changed(f2) ? 2048 : 1024;
        } else {
            f2 = strokeWidth;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            color4 = j;
            strokeWidth3 = f2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1478getPrimary0d7_KjU();
                    $dirty &= -897;
                } else {
                    color2 = j;
                }
                if (i4 != 0) {
                    modifier3 = modifier5;
                    color3 = color2;
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m1601getStrokeWidthD9Ej5fM();
                } else {
                    modifier3 = modifier5;
                    strokeWidth2 = f2;
                    color3 = color2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                strokeWidth2 = f2;
                color3 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-409649739, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:443)");
            }
            m1602CircularProgressIndicatorDUhRLBM(f, modifier3, color3, strokeWidth2, Color.INSTANCE.m4198getTransparent0d7_KjU(), StrokeCap.INSTANCE.m4515getButtKaPHkGw(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            color4 = color3;
            strokeWidth3 = strokeWidth2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    ProgressIndicatorKt.m1604CircularProgressIndicatorMBs18nI(progress, modifier4, color4, strokeWidth3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m1605CircularProgressIndicatoraMcp0Q(Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float f;
        float strokeWidth2;
        long color3;
        Modifier modifier3;
        final long color4;
        final float strokeWidth3;
        final Modifier modifier4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-392089979);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.unit.Dp)456@18110L6,458@18190L145:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i2 = 32;
                    $dirty |= i2;
                }
            } else {
                color2 = color;
            }
            i2 = 16;
            $dirty |= i2;
        } else {
            color2 = color;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            color4 = color2;
            strokeWidth3 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1478getPrimary0d7_KjU();
                    $dirty &= -113;
                }
                if (i4 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m1601getStrokeWidthD9Ej5fM();
                    color3 = color2;
                    modifier3 = modifier5;
                } else {
                    strokeWidth2 = f;
                    color3 = color2;
                    modifier3 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                strokeWidth2 = f;
                color3 = color2;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-392089979, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:458)");
            }
            m1603CircularProgressIndicatorLxG7B9w(modifier3, color3, strokeWidth2, Color.INSTANCE.m4198getTransparent0d7_KjU(), StrokeCap.INSTANCE.m4517getSquareKaPHkGw(), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color4 = color3;
            strokeWidth3 = strokeWidth2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    ProgressIndicatorKt.m1605CircularProgressIndicatoraMcp0Q(Modifier.this, color4, strokeWidth3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m1615drawCircularIndicator42QJj7c(DrawScope $this$drawCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        float diameterOffset = stroke.getWidth() / 2.0f;
        float arcDimen = Size.m3991getWidthimpl($this$drawCircularIndicator_u2d42QJj7c.mo4713getSizeNHjbRc()) - (2.0f * diameterOffset);
        DrawScope.m4692drawArcyD3GUKo$default($this$drawCircularIndicator_u2d42QJj7c, color, startAngle, sweep, false, OffsetKt.Offset(diameterOffset, diameterOffset), androidx.compose.ui.geometry.SizeKt.Size(arcDimen, arcDimen), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawCircularIndicatorBackground-bw27NRU, reason: not valid java name */
    public static final void m1616drawCircularIndicatorBackgroundbw27NRU(DrawScope $this$drawCircularIndicatorBackground_u2dbw27NRU, long color, Stroke stroke) {
        m1615drawCircularIndicator42QJj7c($this$drawCircularIndicatorBackground_u2dbw27NRU, 0.0f, 360.0f, color, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m1617drawDeterminateCircularIndicator42QJj7c(DrawScope $this$drawDeterminateCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        m1615drawCircularIndicator42QJj7c($this$drawDeterminateCircularIndicator_u2d42QJj7c, startAngle, sweep, color, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m1618drawIndeterminateCircularIndicatorhrjfTZI(DrawScope $this$drawIndeterminateCircularIndicator_u2dhrjfTZI, float startAngle, float strokeWidth, float sweep, long color, Stroke stroke) {
        float strokeCapOffset;
        if (StrokeCap.m4511equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m4515getButtKaPHkGw())) {
            strokeCapOffset = 0.0f;
        } else {
            float arg0$iv = CircularIndicatorDiameter;
            float other$iv = strokeWidth / Dp.m6626constructorimpl(arg0$iv / 2);
            strokeCapOffset = (other$iv * 57.29578f) / 2.0f;
        }
        float adjustedStartAngle = startAngle + strokeCapOffset;
        float adjustedSweep = Math.max(sweep, 0.1f);
        m1615drawCircularIndicator42QJj7c($this$drawIndeterminateCircularIndicator_u2dhrjfTZI, adjustedStartAngle, adjustedSweep, color, stroke);
    }
}
