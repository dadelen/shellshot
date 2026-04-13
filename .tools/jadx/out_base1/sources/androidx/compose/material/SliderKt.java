package androidx.compose.material;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aS\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u001b\u001a\u007f\u0010\u001c\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0016\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0003\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u0010'\u001ak\u0010(\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00103\u001a\u007f\u00104\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0003\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\n\b\u0002\u00105\u001a\u0004\u0018\u00010/2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u00106\u001aK\u00107\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u00108\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u00105\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00109\u001aS\u0010:\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\bH\u0003¢\u0006\u0002\u0010=\u001a.\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010D\u001a \u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0002\u001a0\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a<\u0010I\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a.\u0010P\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010R\u001a\u00020\bH\u0002\u001a\u0016\u0010S\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010!\u001a\u00020\"H\u0002\u001aF\u0010T\u001a\u00020\u0012*\u00020U2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010V\u001a\u00020\u00032\u0006\u00105\u001a\u00020/2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010W\u001a\u00020\u0003H\u0003ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a5\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\b\u0018\u00010[*\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0082@ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a\u0098\u0001\u0010d\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\b0f2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010h\u001a\u00020 2\u0006\u0010R\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u00140f2\u001e\u0010j\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120k0fH\u0002\u001a\\\u0010l\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"H\u0002\u001aj\u0010m\u001a\u00020\u0001*\u00020\u00012\u0006\u0010?\u001a\u00020@2\u0006\u00105\u001a\u00020/2\u0006\u0010R\u001a\u00020\b2\u0006\u0010h\u001a\u00020 2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00140f2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001f\u001a\u00020 H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000b\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006p"}, d2 = {"DefaultSliderConstraints", "Landroidx/compose/ui/Modifier;", "SliderHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SliderMinWidth", "SliderToTickAnimation", "Landroidx/compose/animation/core/TweenSpec;", "", "ThumbDefaultElevation", "ThumbPressedElevation", "ThumbRadius", "getThumbRadius", "()F", "ThumbRippleRadius", "TrackHeight", "getTrackHeight", "CorrectValueSideEffect", "", "scaleToOffset", "Lkotlin/Function1;", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "trackRange", "valueState", "Landroidx/compose/runtime/MutableState;", "value", "(Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/MutableState;FLandroidx/compose/runtime/Composer;I)V", "RangeSlider", "onValueChange", "modifier", "enabled", "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "colors", "Landroidx/compose/material/SliderColors;", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "positionFractionStart", "positionFractionEnd", "tickFractions", "", "width", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumbSemantics", "endThumbSemantics", "(ZFFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Slider", "interactionSource", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "positionFraction", "(ZFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "Track", "thumbPx", "trackStrokeWidth", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SliderColors;ZFFLjava/util/List;FFLandroidx/compose/runtime/Composer;I)V", "animateToTarget", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "current", "target", "velocity", "(Landroidx/compose/foundation/gestures/DraggableState;FFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x1", "a2", "b2", "x", "snapValueToTick", "minPx", "maxPx", "stepsToTickFractions", "SliderThumb", "Landroidx/compose/foundation/layout/BoxScope;", "offset", "thumbSize", "SliderThumb-PcYyNuk", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;ZFLandroidx/compose/runtime/Composer;I)V", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderPressDragModifier", "rawOffsetStart", "Landroidx/compose/runtime/State;", "rawOffsetEnd", "isRtl", "gestureEndAction", "onDrag", "Lkotlin/Function2;", "sliderSemantics", "sliderTapModifier", "rawOffset", "pressOffset", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SliderKt {
    private static final float ThumbRadius = Dp.m6626constructorimpl(10);
    private static final float ThumbRippleRadius = Dp.m6626constructorimpl(24);
    private static final float ThumbDefaultElevation = Dp.m6626constructorimpl(1);
    private static final float ThumbPressedElevation = Dp.m6626constructorimpl(6);
    private static final float TrackHeight = Dp.m6626constructorimpl(4);
    private static final float SliderHeight = Dp.m6626constructorimpl(48);
    private static final float SliderMinWidth = Dp.m6626constructorimpl(144);
    private static final Modifier DefaultSliderConstraints = SizeKt.m705heightInVpY3zN4$default(SizeKt.m724widthInVpY3zN4$default(Modifier.INSTANCE, SliderMinWidth, 0.0f, 2, null), 0.0f, SliderHeight, 1, null);
    private static final TweenSpec<Float> SliderToTickAnimation = new TweenSpec<>(100, 0, null, 6, null);

    public static final void Slider(final float value, final Function1<? super Float, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, MutableInteractionSource interactionSource, SliderColors colors, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange valueRange;
        int steps2;
        Function0 function02;
        int i2;
        MutableInteractionSource interactionSource2;
        Composer $composer2;
        SliderColors colors2;
        Function0 onValueChangeFinished;
        boolean enabled3;
        MutableInteractionSource mutableInteractionSource;
        Object value$iv;
        final Modifier modifier3;
        final boolean enabled4;
        final ClosedFloatingPointRange valueRange2;
        final SliderColors colors3;
        final int steps3;
        final MutableInteractionSource interactionSource3;
        final Function0 onValueChangeFinished2;
        Object value$iv2;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1962335196);
        ComposerKt.sourceInformation($composer3, "C(Slider)P(7,4,3,1,8,6,5,2)158@7608L8,163@7822L35,164@7882L59,180@8384L3006,167@7946L3444:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = value;
        } else if (($changed & 6) == 0) {
            f = value;
            $dirty |= $composer3.changed(f) ? 4 : 2;
        } else {
            f = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                valueRange = closedFloatingPointRange;
                if ($composer3.changed(valueRange)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                valueRange = closedFloatingPointRange;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            valueRange = closedFloatingPointRange;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            function02 = function0;
        } else if (($changed & 1572864) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 1048576 : 524288;
        } else {
            function02 = function0;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(colors)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty |= i3;
            }
            i3 = 33554432;
            $dirty |= i3;
        }
        Composer $composer4 = $composer3;
        if (($dirty & 38347923) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            modifier3 = modifier2;
            enabled4 = enabled2;
            valueRange2 = valueRange;
            onValueChangeFinished2 = function02;
            steps3 = steps2;
            interactionSource3 = interactionSource;
            colors3 = colors;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    ClosedFloatingPointRange valueRange3 = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty &= -57345;
                    valueRange = valueRange3;
                }
                int steps4 = i7 != 0 ? 0 : steps2;
                Function0 onValueChangeFinished3 = i8 != 0 ? null : function02;
                interactionSource2 = i2 != 0 ? null : interactionSource;
                if ((i & 256) != 0) {
                    $composer2 = $composer4;
                    $dirty &= -234881025;
                    steps2 = steps4;
                    onValueChangeFinished = onValueChangeFinished3;
                    enabled3 = enabled2;
                    colors2 = SliderDefaults.INSTANCE.m1636colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 6, 1023);
                } else {
                    $composer2 = $composer4;
                    colors2 = colors;
                    steps2 = steps4;
                    onValueChangeFinished = onValueChangeFinished3;
                    enabled3 = enabled2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 256) != 0) {
                    interactionSource2 = interactionSource;
                    colors2 = colors;
                    $dirty &= -234881025;
                    enabled3 = enabled2;
                    onValueChangeFinished = function02;
                    $composer2 = $composer4;
                } else {
                    interactionSource2 = interactionSource;
                    colors2 = colors;
                    enabled3 = enabled2;
                    onValueChangeFinished = function02;
                    $composer2 = $composer4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1962335196, $dirty, -1, "androidx.compose.material.Slider (Slider.kt:159)");
            }
            if (interactionSource2 == null) {
                $composer2.startReplaceGroup(246071380);
                ComposerKt.sourceInformation($composer2, "161@7702L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -407704210, "CC(remember):Slider.kt#9igjgp");
                Composer $this$cache$iv = $composer2;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                mutableInteractionSource = (MutableInteractionSource) value$iv2;
            } else {
                $composer2.startReplaceGroup(-407704861);
                $composer2.endReplaceGroup();
                mutableInteractionSource = interactionSource2;
            }
            MutableInteractionSource interactionSource4 = mutableInteractionSource;
            if (!(steps2 >= 0)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            State onValueChangeState = SnapshotStateKt.rememberUpdatedState(function1, $composer2, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer2, -407698430, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = (458752 & $dirty) == 131072;
            Composer $this$cache$iv2 = $composer2;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = stepsToTickFractions(steps2);
                $this$cache$iv2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            List tickFractions = (List) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier minimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
            float arg0$iv = ThumbRadius;
            float m6626constructorimpl = Dp.m6626constructorimpl(2 * arg0$iv);
            float arg0$iv2 = ThumbRadius;
            ClosedFloatingPointRange valueRange4 = valueRange;
            int steps5 = steps2;
            boolean enabled5 = enabled3;
            Composer $composer5 = $composer2;
            BoxWithConstraintsKt.BoxWithConstraints(FocusableKt.focusable(sliderSemantics(SizeKt.m713requiredSizeInqDBjuR0$default(minimumInteractiveComponentSize, m6626constructorimpl, Dp.m6626constructorimpl(2 * arg0$iv2), 0.0f, 0.0f, 12, null), f, enabled5, function1, onValueChangeFinished, valueRange4, steps5), enabled5, interactionSource4), null, false, ComposableLambdaKt.rememberComposableLambda(2085116814, true, new SliderKt$Slider$2(valueRange4, value, tickFractions, onValueChangeFinished, interactionSource4, enabled5, colors2, onValueChangeState), $composer5, 54), $composer5, 3072, 6);
            $composer4 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            enabled4 = enabled5;
            valueRange2 = valueRange4;
            colors3 = colors2;
            steps3 = steps5;
            interactionSource3 = interactionSource2;
            onValueChangeFinished2 = onValueChangeFinished;
        }
        ScopeUpdateScope endRestartGroup = $composer4.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    SliderKt.Slider(value, function1, modifier3, enabled4, valueRange2, steps3, onValueChangeFinished2, interactionSource3, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int steps, Function0<Unit> function0, SliderColors colors, Composer $composer, final int $changed, final int i) {
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange valueRange;
        int i2;
        Function0 function02;
        ClosedFloatingPointRange valueRange2;
        Composer $composer2;
        SliderColors colors2;
        int steps2;
        Function0 onValueChangeFinished;
        boolean enabled3;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        final Modifier modifier3;
        final ClosedFloatingPointRange valueRange3;
        final Function0 onValueChangeFinished2;
        final boolean enabled4;
        final int steps3;
        final SliderColors colors3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1556183027);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)P(6,3,2,1,7,5,4)304@13749L8,306@13821L39,307@13918L39,310@14039L35,311@14099L59,319@14350L4956,315@14164L5142:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            closedFloatingPointRange3 = closedFloatingPointRange;
        } else if (($changed & 6) == 0) {
            closedFloatingPointRange3 = closedFloatingPointRange;
            $dirty |= $composer3.changed(closedFloatingPointRange3) ? 4 : 2;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                valueRange = closedFloatingPointRange2;
                if ($composer3.changed(valueRange)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                valueRange = closedFloatingPointRange2;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            valueRange = closedFloatingPointRange2;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = steps;
        } else if ((196608 & $changed) == 0) {
            i2 = steps;
            $dirty |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = steps;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            function02 = function0;
        } else if (($changed & 1572864) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 1048576 : 524288;
        } else {
            function02 = function0;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(colors)) {
                i3 = 8388608;
                $dirty |= i3;
            }
            i3 = 4194304;
            $dirty |= i3;
        }
        Composer $composer4 = $composer3;
        if (($dirty & 4793491) == 4793490 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            enabled4 = enabled2;
            valueRange3 = valueRange;
            onValueChangeFinished2 = function02;
            colors3 = colors;
            modifier3 = modifier2;
            steps3 = i2;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    valueRange2 = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty &= -57345;
                } else {
                    valueRange2 = valueRange;
                }
                int steps4 = i7 != 0 ? 0 : i2;
                Function0 onValueChangeFinished3 = i8 != 0 ? null : function02;
                if ((i & 128) != 0) {
                    $composer2 = $composer4;
                    $dirty &= -29360129;
                    steps2 = steps4;
                    onValueChangeFinished = onValueChangeFinished3;
                    enabled3 = enabled2;
                    colors2 = SliderDefaults.INSTANCE.m1636colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 6, 1023);
                    valueRange = valueRange2;
                } else {
                    $composer2 = $composer4;
                    colors2 = colors;
                    valueRange = valueRange2;
                    steps2 = steps4;
                    onValueChangeFinished = onValueChangeFinished3;
                    enabled3 = enabled2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    colors2 = colors;
                    $dirty &= -29360129;
                    enabled3 = enabled2;
                    onValueChangeFinished = function02;
                    steps2 = i2;
                    $composer2 = $composer4;
                } else {
                    colors2 = colors;
                    enabled3 = enabled2;
                    onValueChangeFinished = function02;
                    steps2 = i2;
                    $composer2 = $composer4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1556183027, $dirty, -1, "androidx.compose.material.RangeSlider (Slider.kt:305)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 394849951, "CC(remember):Slider.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = InteractionSourceKt.MutableInteractionSource();
                $this$cache$iv.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MutableInteractionSource startInteractionSource = (MutableInteractionSource) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 394853055, "CC(remember):Slider.kt#9igjgp");
            Composer $this$cache$iv2 = $composer2;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = InteractionSourceKt.MutableInteractionSource();
                $this$cache$iv2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            MutableInteractionSource endInteractionSource = (MutableInteractionSource) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (!(steps2 >= 0)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            State onValueChangeState = SnapshotStateKt.rememberUpdatedState(function1, $composer2, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer2, 394858867, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = (458752 & $dirty) == 131072;
            Composer $this$cache$iv3 = $composer2;
            Object it$iv3 = $this$cache$iv3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = stepsToTickFractions(steps2);
                $this$cache$iv3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            List tickFractions = (List) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier minimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
            float arg0$iv = ThumbRadius;
            float arg0$iv2 = Dp.m6626constructorimpl(4 * arg0$iv);
            float arg0$iv3 = ThumbRadius;
            Composer $composer5 = $composer2;
            ClosedFloatingPointRange valueRange4 = valueRange;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.m713requiredSizeInqDBjuR0$default(minimumInteractiveComponentSize, arg0$iv2, Dp.m6626constructorimpl(2 * arg0$iv3), 0.0f, 0.0f, 12, null), null, false, ComposableLambdaKt.rememberComposableLambda(652589923, true, new SliderKt$RangeSlider$2(valueRange4, closedFloatingPointRange3, tickFractions, onValueChangeFinished, onValueChangeState, startInteractionSource, endInteractionSource, enabled3, steps2, colors2), $composer5, 54), $composer5, 3072, 6);
            $composer4 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            valueRange3 = valueRange4;
            onValueChangeFinished2 = onValueChangeFinished;
            enabled4 = enabled3;
            steps3 = steps2;
            colors3 = colors2;
        }
        ScopeUpdateScope endRestartGroup = $composer4.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    SliderKt.RangeSlider(closedFloatingPointRange, function1, modifier3, enabled4, valueRange3, steps3, onValueChangeFinished2, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SliderImpl(final boolean r45, final float r46, final java.util.List<java.lang.Float> r47, final androidx.compose.material.SliderColors r48, final float r49, final androidx.compose.foundation.interaction.MutableInteractionSource r50, final androidx.compose.ui.Modifier r51, androidx.compose.runtime.Composer r52, final int r53) {
        /*
            Method dump skipped, instructions count: 711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.SliderImpl(boolean, float, java.util.List, androidx.compose.material.SliderColors, float, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0402  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSliderImpl(final boolean r48, final float r49, final float r50, final java.util.List<java.lang.Float> r51, final androidx.compose.material.SliderColors r52, final float r53, final androidx.compose.foundation.interaction.MutableInteractionSource r54, final androidx.compose.foundation.interaction.MutableInteractionSource r55, final androidx.compose.ui.Modifier r56, final androidx.compose.ui.Modifier r57, final androidx.compose.ui.Modifier r58, androidx.compose.runtime.Composer r59, final int r60, final int r61) {
        /*
            Method dump skipped, instructions count: 1072
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.RangeSliderImpl(boolean, float, float, java.util.List, androidx.compose.material.SliderColors, float, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.Modifier, androidx.compose.ui.Modifier, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0209  */
    /* renamed from: SliderThumb-PcYyNuk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1637SliderThumbPcYyNuk(final androidx.compose.foundation.layout.BoxScope r51, final androidx.compose.ui.Modifier r52, final float r53, final androidx.compose.foundation.interaction.MutableInteractionSource r54, final androidx.compose.material.SliderColors r55, final boolean r56, final float r57, androidx.compose.runtime.Composer r58, final int r59) {
        /*
            Method dump skipped, instructions count: 794
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.m1637SliderThumbPcYyNuk(androidx.compose.foundation.layout.BoxScope, androidx.compose.ui.Modifier, float, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SliderColors, boolean, float, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Track(final Modifier modifier, final SliderColors colors, final boolean enabled, final float positionFractionStart, final float positionFractionEnd, final List<Float> list, final float thumbPx, final float trackStrokeWidth, Composer $composer, final int $changed) {
        final float f;
        float f2;
        float f3;
        float f4;
        int $dirty;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1833126050);
        ComposerKt.sourceInformation($composer2, "C(Track)P(2!2,4!1,6)748@30055L35,749@30125L34,750@30195L34,751@30263L33,752@30318L1535,752@30301L1552:Slider.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            f = positionFractionStart;
            $dirty2 |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = positionFractionStart;
        }
        if (($changed & 24576) == 0) {
            f2 = positionFractionEnd;
            $dirty2 |= $composer2.changed(f2) ? 16384 : 8192;
        } else {
            f2 = positionFractionEnd;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(list) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            f3 = thumbPx;
            $dirty2 |= $composer2.changed(f3) ? 1048576 : 524288;
        } else {
            f3 = thumbPx;
        }
        if ((12582912 & $changed) == 0) {
            f4 = trackStrokeWidth;
            $dirty2 |= $composer2.changed(f4) ? 8388608 : 4194304;
        } else {
            f4 = trackStrokeWidth;
        }
        if (($dirty2 & 4793491) != 4793490 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1833126050, $dirty2, -1, "androidx.compose.material.Track (Slider.kt:747)");
            }
            final State inactiveTrackColor = colors.trackColor(enabled, false, $composer2, (($dirty2 >> 6) & 14) | 48 | (($dirty2 << 3) & 896));
            final State activeTrackColor = colors.trackColor(enabled, true, $composer2, (($dirty2 >> 6) & 14) | 48 | (($dirty2 << 3) & 896));
            final State inactiveTickColor = colors.tickColor(enabled, false, $composer2, (($dirty2 >> 6) & 14) | 48 | (($dirty2 << 3) & 896));
            final State activeTickColor = colors.tickColor(enabled, true, $composer2, (($dirty2 >> 6) & 14) | 48 | (($dirty2 << 3) & 896));
            ComposerKt.sourceInformationMarkerStart($composer2, -1731271772, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ((3670016 & $dirty2) == 1048576) | $composer2.changed(inactiveTrackColor) | ((29360128 & $dirty2) == 8388608) | ((57344 & $dirty2) == 16384) | (($dirty2 & 7168) == 2048) | $composer2.changed(activeTrackColor) | $composer2.changedInstance(list) | $composer2.changed(inactiveTickColor) | $composer2.changed(activeTickColor);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float f5 = f2;
                final float f6 = f4;
                $dirty = $dirty2;
                final float f7 = f3;
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.SliderKt$Track$1$1
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
                        float f8;
                        Object answer$iv$iv$iv;
                        boolean isRtl = $this$Canvas.getLayoutDirection() == LayoutDirection.Rtl;
                        long sliderLeft = OffsetKt.Offset(f7, Offset.m3923getYimpl($this$Canvas.mo4712getCenterF1C5BW0()));
                        long sliderRight = OffsetKt.Offset(Size.m3991getWidthimpl($this$Canvas.mo4713getSizeNHjbRc()) - f7, Offset.m3923getYimpl($this$Canvas.mo4712getCenterF1C5BW0()));
                        long sliderStart = isRtl ? sliderRight : sliderLeft;
                        long sliderEnd = isRtl ? sliderLeft : sliderRight;
                        DrawScope.m4699drawLineNGM6Ib0$default($this$Canvas, inactiveTrackColor.getValue().m4173unboximpl(), sliderStart, sliderEnd, f6, StrokeCap.INSTANCE.m4516getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        long sliderEnd2 = sliderEnd;
                        long sliderValueEnd = OffsetKt.Offset(Offset.m3922getXimpl(sliderStart) + ((Offset.m3922getXimpl(sliderEnd2) - Offset.m3922getXimpl(sliderStart)) * f5), Offset.m3923getYimpl($this$Canvas.mo4712getCenterF1C5BW0()));
                        long sliderValueStart = OffsetKt.Offset(Offset.m3922getXimpl(sliderStart) + ((Offset.m3922getXimpl(sliderEnd2) - Offset.m3922getXimpl(sliderStart)) * f), Offset.m3923getYimpl($this$Canvas.mo4712getCenterF1C5BW0()));
                        DrawScope.m4699drawLineNGM6Ib0$default($this$Canvas, activeTrackColor.getValue().m4173unboximpl(), sliderValueStart, sliderValueEnd, f6, StrokeCap.INSTANCE.m4516getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        Iterable $this$groupBy$iv = list;
                        float f9 = f5;
                        float f10 = f;
                        Map $this$forEach$iv = new LinkedHashMap();
                        for (Object element$iv$iv : $this$groupBy$iv) {
                            float it = ((Number) element$iv$iv).floatValue();
                            Boolean valueOf = Boolean.valueOf(it > f9 || it < f10);
                            Iterable $this$groupBy$iv2 = $this$groupBy$iv;
                            boolean isRtl2 = isRtl;
                            Object value$iv$iv$iv = $this$forEach$iv.get(valueOf);
                            if (value$iv$iv$iv == null) {
                                answer$iv$iv$iv = new ArrayList();
                                f8 = f10;
                                $this$forEach$iv.put(valueOf, answer$iv$iv$iv);
                            } else {
                                f8 = f10;
                                answer$iv$iv$iv = value$iv$iv$iv;
                            }
                            List list$iv$iv = (List) answer$iv$iv$iv;
                            list$iv$iv.add(element$iv$iv);
                            $this$groupBy$iv = $this$groupBy$iv2;
                            f10 = f8;
                            isRtl = isRtl2;
                        }
                        State<Color> state = inactiveTickColor;
                        State<Color> state2 = activeTickColor;
                        float f11 = f6;
                        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                            boolean outsideFraction = ((Boolean) element$iv.getKey()).booleanValue();
                            List list2 = (List) element$iv.getValue();
                            State<Color> state3 = state;
                            ArrayList target$iv = new ArrayList(list2.size());
                            List $this$fastForEach$iv$iv = list2;
                            int size = $this$fastForEach$iv$iv.size();
                            State<Color> state4 = state2;
                            int index$iv$iv = 0;
                            while (index$iv$iv < size) {
                                Object item$iv$iv = $this$fastForEach$iv$iv.get(index$iv$iv);
                                target$iv.add(Offset.m3911boximpl(OffsetKt.Offset(Offset.m3922getXimpl(OffsetKt.m3945lerpWko1d7g(sliderStart, sliderEnd2, ((Number) item$iv$iv).floatValue())), Offset.m3923getYimpl($this$Canvas.mo4712getCenterF1C5BW0()))));
                                index$iv$iv++;
                                size = size;
                                $this$fastForEach$iv$iv = $this$fastForEach$iv$iv;
                            }
                            DrawScope.m4704drawPointsF8ZwMP8$default($this$Canvas, target$iv, PointMode.INSTANCE.m4468getPointsr_lszbg(), (outsideFraction ? state3 : state4).getValue().m4173unboximpl(), f11, StrokeCap.INSTANCE.m4516getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                            state = state3;
                            state2 = state4;
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                $dirty = $dirty2;
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) value$iv, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$Track$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    SliderKt.Track(Modifier.this, colors, enabled, positionFractionStart, positionFractionEnd, list, thumbPx, trackStrokeWidth, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float current, List<Float> list, float minPx, float maxPx) {
        Object minElem$iv;
        if (list.isEmpty()) {
            minElem$iv = null;
        } else {
            minElem$iv = list.get(0);
            float it = ((Number) minElem$iv).floatValue();
            float minValue$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it) - current);
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = list.get(i$iv);
                    float it2 = ((Number) e$iv).floatValue();
                    float v$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it2) - current);
                    if (Float.compare(minValue$iv, v$iv) > 0) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Object minElem$iv2 = (Float) minElem$iv;
        if (minElem$iv2 == null) {
            return current;
        }
        float $this$snapValueToTick_u24lambda_u2418 = ((Number) minElem$iv2).floatValue();
        return MathHelpersKt.lerp(minPx, maxPx, $this$snapValueToTick_u24lambda_u2418);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0065 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m1640awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, long r8, int r10, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.material.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = (androidx.compose.material.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = new androidx.compose.material.SliderKt$awaitSlop$1
            r0.<init>(r11)
        L19:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L37;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            java.lang.Object r7 = r6.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r11
            goto L56
        L37:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r7
            r2 = r8
            r4 = r10
            kotlin.jvm.internal.Ref$FloatRef r7 = new kotlin.jvm.internal.Ref$FloatRef
            r7.<init>()
            androidx.compose.material.SliderKt$awaitSlop$postPointerSlop$1 r8 = new androidx.compose.material.SliderKt$awaitSlop$postPointerSlop$1
            r8.<init>()
            r5 = r8
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6.L$0 = r7
            r8 = 1
            r6.label = r8
            java.lang.Object r8 = androidx.compose.material.DragGestureDetectorCopyKt.m1523awaitHorizontalPointerSlopOrCancellationgDDlDlE(r1, r2, r4, r5, r6)
            if (r8 != r0) goto L56
            return r0
        L56:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 == 0) goto L65
            float r9 = r7.element
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            kotlin.Pair r9 = kotlin.TuplesKt.to(r8, r9)
            goto L66
        L65:
            r9 = 0
        L66:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.m1640awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final List<Float> stepsToTickFractions(int steps) {
        if (steps == 0) {
            return CollectionsKt.emptyList();
        }
        int i = steps + 2;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            int it = i2;
            arrayList.add(Float.valueOf(it / (steps + 1)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float a1, float b1, float x1, float a2, float b2) {
        return MathHelpersKt.lerp(a2, b2, calcFraction(a1, b1, x1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Float> scale(float a1, float b1, ClosedFloatingPointRange<Float> closedFloatingPointRange, float a2, float b2) {
        return RangesKt.rangeTo(scale(a1, b1, closedFloatingPointRange.getStart().floatValue(), a2, b2), scale(a1, b1, closedFloatingPointRange.getEndInclusive().floatValue(), a2, b2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float a, float b, float pos) {
        float $this$fastCoerceIn$iv = ((b - a) > 0.0f ? 1 : ((b - a) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (pos - a) / (b - a);
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CorrectValueSideEffect(final Function1<? super Float, Float> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final ClosedFloatingPointRange<Float> closedFloatingPointRange2, final MutableState<Float> mutableState, final float value, Composer $composer, final int $changed) {
        final Function1<? super Float, Float> function12;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        final MutableState<Float> mutableState2;
        float f;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-743965752);
        ComposerKt.sourceInformation($composer2, "C(CorrectValueSideEffect)P(!1,3!1,4)848@33715L300,848@33704L311:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 4 : 2;
        } else {
            function12 = function1;
        }
        if (($changed & 48) == 0) {
            closedFloatingPointRange3 = closedFloatingPointRange;
            $dirty |= $composer2.changed(closedFloatingPointRange3) ? 32 : 16;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange;
        }
        if (($changed & 384) == 0) {
            closedFloatingPointRange4 = closedFloatingPointRange2;
            $dirty |= $composer2.changed(closedFloatingPointRange4) ? 256 : 128;
        } else {
            closedFloatingPointRange4 = closedFloatingPointRange2;
        }
        if (($changed & 3072) == 0) {
            mutableState2 = mutableState;
            $dirty |= $composer2.changed(mutableState2) ? 2048 : 1024;
        } else {
            mutableState2 = mutableState;
        }
        if (($changed & 24576) == 0) {
            f = value;
            $dirty |= $composer2.changed(f) ? 16384 : 8192;
        } else {
            f = value;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) != 9362 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-743965752, $dirty2, -1, "androidx.compose.material.CorrectValueSideEffect (Slider.kt:847)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1313906175, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | (($dirty2 & 14) == 4) | ((57344 & $dirty2) == 16384) | (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float f2 = f;
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SliderKt$CorrectValueSideEffect$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        float error = (closedFloatingPointRange3.getEndInclusive().floatValue() - closedFloatingPointRange3.getStart().floatValue()) / 1000.0f;
                        float newOffset = function12.invoke(Float.valueOf(f2)).floatValue();
                        if (Math.abs(newOffset - mutableState2.getValue().floatValue()) > error && closedFloatingPointRange4.contains(mutableState2.getValue())) {
                            mutableState2.setValue(Float.valueOf(newOffset));
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$CorrectValueSideEffect$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    SliderKt.CorrectValueSideEffect(function1, closedFloatingPointRange, closedFloatingPointRange2, mutableState, value, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderSemantics(Modifier $this$sliderSemantics, float value, final boolean enabled, final Function1<? super Float, Unit> function1, final Function0<Unit> function0, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int steps) {
        final float coerced = RangesKt.coerceIn(value, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$sliderSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt$sliderSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                if (!enabled) {
                    SemanticsPropertiesKt.disabled($this$semantics);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = closedFloatingPointRange;
                final int i = steps;
                final float f = coerced;
                final Function1<Float, Unit> function12 = function1;
                final Function0<Unit> function02 = function0;
                SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material.SliderKt$sliderSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f2) {
                        return invoke(f2.floatValue());
                    }

                    public final Boolean invoke(float targetValue) {
                        float newValue = RangesKt.coerceIn(targetValue, closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue());
                        boolean z = true;
                        if (i > 0) {
                            float distance = newValue;
                            int i2 = 0;
                            int i3 = i + 1;
                            if (0 <= i3) {
                                while (true) {
                                    float stepValue = MathHelpersKt.lerp(closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue(), i2 / (i + 1));
                                    if (Math.abs(stepValue - newValue) <= distance) {
                                        distance = Math.abs(stepValue - newValue);
                                        newValue = stepValue;
                                    }
                                    if (i2 == i3) {
                                        break;
                                    }
                                    i2++;
                                }
                            }
                        }
                        float resolvedValue = newValue;
                        if (resolvedValue == f) {
                            z = false;
                        } else {
                            function12.invoke(Float.valueOf(resolvedValue));
                            Function0<Unit> function03 = function02;
                            if (function03 != null) {
                                function03.invoke();
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null), value, closedFloatingPointRange, steps);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderTapModifier(Modifier $this$sliderTapModifier, final DraggableState draggableState, final MutableInteractionSource interactionSource, final float maxPx, final boolean isRtl, final State<Float> state, final State<? extends Function1<? super Float, Unit>> state2, final MutableState<Float> mutableState, final boolean enabled) {
        return ComposedModifierKt.composed($this$sliderTapModifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("sliderTapModifier");
                $this$null.getProperties().set("draggableState", DraggableState.this);
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("maxPx", Float.valueOf(maxPx));
                $this$null.getProperties().set("isRtl", Boolean.valueOf(isRtl));
                $this$null.getProperties().set("rawOffset", state);
                $this$null.getProperties().set("gestureEndAction", state2);
                $this$null.getProperties().set("pressOffset", mutableState);
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Modifier modifier;
                Object value$iv$iv;
                SliderKt$sliderTapModifier$2$1$1 value$iv;
                $composer.startReplaceGroup(1945228890);
                ComposerKt.sourceInformation($composer, "C:Slider.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1945228890, $changed, -1, "androidx.compose.material.sliderTapModifier.<anonymous> (Slider.kt:915)");
                }
                if (enabled) {
                    $composer.startReplaceGroup(-398958937);
                    ComposerKt.sourceInformation($composer, "916@36114L24,917@36213L898");
                    ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
                    Object it$iv$iv = $composer.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                        $composer.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    CoroutineScope scope = wrapper$iv.getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Object[] objArr = {draggableState, interactionSource, Float.valueOf(maxPx), Boolean.valueOf(isRtl)};
                    ComposerKt.sourceInformationMarkerStart($composer, -1952528428, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = $composer.changed(isRtl) | $composer.changed(maxPx) | $composer.changed(mutableState) | $composer.changed(state) | $composer.changedInstance(scope) | $composer.changedInstance(draggableState) | $composer.changed(state2);
                    boolean z = isRtl;
                    float f = maxPx;
                    MutableState<Float> mutableState2 = mutableState;
                    State<Float> state3 = state;
                    DraggableState draggableState2 = draggableState;
                    State<Function1<Float, Unit>> state4 = state2;
                    Object it$iv = $composer.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new SliderKt$sliderTapModifier$2$1$1(z, f, mutableState2, state3, scope, draggableState2, state4, null);
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    modifier = SuspendingPointerInputFilterKt.pointerInput($this$composed, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-397959404);
                    $composer.endReplaceGroup();
                    modifier = $this$composed;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifier;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateToTarget(DraggableState draggableState, float current, float target, float velocity, Continuation<? super Unit> continuation) {
        Object drag$default = DraggableState.drag$default(draggableState, null, new SliderKt$animateToTarget$2(current, target, velocity, null), continuation, 1, null);
        return drag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? drag$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier rangeSliderPressDragModifier(Modifier $this$rangeSliderPressDragModifier, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, State<Float> state, State<Float> state2, boolean enabled, boolean isRtl, float maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, State<? extends Function1<? super Boolean, Unit>> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4) {
        return enabled ? SuspendingPointerInputFilterKt.pointerInput($this$rangeSliderPressDragModifier, new Object[]{startInteractionSource, endInteractionSource, Float.valueOf(maxPx), Boolean.valueOf(isRtl), closedFloatingPointRange}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new SliderKt$rangeSliderPressDragModifier$1(startInteractionSource, endInteractionSource, state, state2, state4, isRtl, maxPx, state3, null)) : $this$rangeSliderPressDragModifier;
    }

    public static final float getThumbRadius() {
        return ThumbRadius;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }
}
