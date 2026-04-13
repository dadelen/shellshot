package androidx.compose.material3;

import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0098\u0001\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u0010'\u001aä\u0001\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\b\b\u0003\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00101\u001a\u007f\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007¢\u0006\u0002\u00102\u001a\u0080\u0001\u00103\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u00104\u001as\u00105\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u0002062\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u00109\u001a³\u0001\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\b\b\u0003\u0010/\u001a\u0002002\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)H\u0007¢\u0006\u0002\u0010:\u001a}\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 H\u0007¢\u0006\u0002\u0010;\u001a_\u0010<\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u00107\u001a\u00020 2\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*H\u0001¢\u0006\u0002\u0010A\u001a\u001b\u0010>\u001a\u00020\u00112\f\u0010B\u001a\b\u0012\u0004\u0012\u00020*0)H\u0001¢\u0006\u0002\u0010C\u001a \u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020*H\u0002\u001a:\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020\u00112\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a0\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010P\u001a\u00020*2\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002\u001a(\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020*H\u0002\u001a\u0010\u0010W\u001a\u00020T2\u0006\u0010/\u001a\u000200H\u0002\u001a5\u0010X\u001a\u0010\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020*\u0018\u00010Y*\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u0082@ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u001c\u0010b\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a,\u0010c\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\u001a\u001c\u0010d\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a\u001c\u0010e\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a$\u0010f\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u00107\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\t\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\r\u0010\u000b\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u001e\u0010\u000f\u001a\u00020\u0010*\u00020\u00118@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006g"}, d2 = {"SliderRangeTolerance", "", "ThumbHeight", "Landroidx/compose/ui/unit/Dp;", "F", "ThumbSize", "Landroidx/compose/ui/unit/DpSize;", "J", "ThumbTrackGapSize", "ThumbWidth", "getThumbWidth", "()F", "TrackHeight", "getTrackHeight", "TrackInsideCornerSize", "isSpecified", "", "Landroidx/compose/material3/SliderRange;", "isSpecified-If1S1O4$annotations", "(J)V", "isSpecified-If1S1O4", "(J)Z", "RangeSlider", "", "state", "Landroidx/compose/material3/RangeSliderState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/SliderColors;", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumb", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "endThumb", "track", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "value", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "onValueChange", "valueRange", "onValueChangeFinished", "Lkotlin/Function0;", "steps", "", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;ILandroidx/compose/runtime/Composer;III)V", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "Slider", "Landroidx/compose/material3/SliderState;", "interactionSource", "thumb", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;III)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "SliderRange", "start", "endInclusive", "(FF)J", "range", "(Lkotlin/ranges/ClosedFloatingPointRange;)J", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x", "a2", "b2", "scale-ziovWd0", "(FFJFF)J", "x1", "snapValueToTick", "current", "tickFractions", "", "minPx", "maxPx", "stepsToTickFractions", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderEndThumbSemantics", "rangeSliderPressDragModifier", "rangeSliderStartThumbSemantics", "sliderSemantics", "sliderTapModifier", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SliderKt {
    private static final double SliderRangeTolerance = 1.0E-4d;
    private static final float TrackHeight = SliderTokens.INSTANCE.m3500getInactiveTrackHeightD9Ej5fM();
    private static final float ThumbWidth = SliderTokens.INSTANCE.m3498getHandleWidthD9Ej5fM();
    private static final float ThumbHeight = SliderTokens.INSTANCE.m3497getHandleHeightD9Ej5fM();
    private static final long ThumbSize = DpKt.m6648DpSizeYgX7TsA(ThumbWidth, ThumbHeight);
    private static final float ThumbTrackGapSize = SliderTokens.INSTANCE.m3490getActiveHandleLeadingSpaceD9Ej5fM();
    private static final float TrackInsideCornerSize = Dp.m6626constructorimpl(2);

    /* renamed from: isSpecified-If1S1O4$annotations, reason: not valid java name */
    public static /* synthetic */ void m2510isSpecifiedIf1S1O4$annotations(long j) {
    }

    public static final void Slider(final float value, final Function1<? super Float, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, SliderColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        float f;
        Function1<? super Float, Unit> function12;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange valueRange;
        int steps2;
        Function0 onValueChangeFinished;
        int i2;
        int $dirty;
        final SliderColors colors2;
        final MutableInteractionSource interactionSource2;
        Modifier modifier3;
        ClosedFloatingPointRange valueRange2;
        int steps3;
        Function0 onValueChangeFinished2;
        int i3;
        final boolean enabled3;
        Object value$iv;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled4;
        final Function0 onValueChangeFinished3;
        final SliderColors colors3;
        final MutableInteractionSource interactionSource3;
        final int steps4;
        final ClosedFloatingPointRange valueRange3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-202044027);
        ComposerKt.sourceInformation($composer3, "C(Slider)P(7,4,3,1,8,6,5)167@8058L8,168@8118L39,179@8458L181,186@8657L122,170@8166L652:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            f = value;
        } else if (($changed & 6) == 0) {
            f = value;
            $dirty2 |= $composer3.changed(f) ? 4 : 2;
        } else {
            f = value;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty2 |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                valueRange = closedFloatingPointRange;
                if ($composer3.changed(valueRange)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                valueRange = closedFloatingPointRange;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            valueRange = closedFloatingPointRange;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty2 |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty2 |= 1572864;
            onValueChangeFinished = function0;
        } else if ((1572864 & $changed) == 0) {
            onValueChangeFinished = function0;
            $dirty2 |= $composer3.changedInstance(onValueChangeFinished) ? 1048576 : 524288;
        } else {
            onValueChangeFinished = function0;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(colors)) {
                i4 = 8388608;
                $dirty2 |= i4;
            }
            i4 = 4194304;
            $dirty2 |= i4;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty2 |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i10;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors3 = colors;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            valueRange3 = valueRange;
            steps4 = steps2;
            onValueChangeFinished3 = onValueChangeFinished;
            interactionSource3 = interactionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    ClosedFloatingPointRange valueRange4 = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty = $dirty3 & (-57345);
                    valueRange = valueRange4;
                } else {
                    $dirty = $dirty3;
                }
                if (i8 != 0) {
                    steps2 = 0;
                }
                if (i9 != 0) {
                    onValueChangeFinished = null;
                }
                if ((i & 128) != 0) {
                    colors2 = SliderDefaults.INSTANCE.colors($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i2 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -403669397, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    SliderColors colors4 = colors2;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    interactionSource2 = (MutableInteractionSource) value$iv;
                    modifier3 = modifier2;
                    valueRange2 = valueRange;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    i3 = -202044027;
                    colors2 = colors4;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    valueRange2 = valueRange;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    i3 = -202044027;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 16) != 0 ? $dirty3 & (-57345) : $dirty3;
                if ((i & 128) != 0) {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty4 & (-29360129);
                    modifier3 = modifier2;
                    valueRange2 = valueRange;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    i3 = -202044027;
                    colors2 = colors;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty4;
                    modifier3 = modifier2;
                    valueRange2 = valueRange;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    i3 = -202044027;
                    colors2 = colors;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.Slider (Slider.kt:169)");
            }
            SliderColors colors5 = colors2;
            $composer2 = $composer3;
            MutableInteractionSource interactionSource4 = interactionSource2;
            Slider(f, function12, modifier3, enabled3, onValueChangeFinished2, colors5, interactionSource4, steps3, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                    invoke(sliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C180@8487L142:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(308249025, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                    }
                    SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled3, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                    invoke(sliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState sliderState, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C187@8701L68:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1843234110, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                    }
                    SliderDefaults.INSTANCE.m2498Track4EFweAY(sliderState, (Modifier) null, enabled3, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), valueRange2, $composer2, ($dirty & 14) | 905969664 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty << 6) & 29360128), ($dirty >> 12) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled4 = enabled3;
            onValueChangeFinished3 = onValueChangeFinished2;
            colors3 = colors5;
            interactionSource3 = interactionSource4;
            steps4 = steps3;
            valueRange3 = valueRange2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$4
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

                public final void invoke(Composer composer, int i11) {
                    SliderKt.Slider(value, function1, modifier4, enabled4, valueRange3, steps4, onValueChangeFinished3, colors3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x0334  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(final float r25, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r26, androidx.compose.ui.Modifier r27, boolean r28, kotlin.jvm.functions.Function0<kotlin.Unit> r29, androidx.compose.material3.SliderColors r30, androidx.compose.foundation.interaction.MutableInteractionSource r31, int r32, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r35, androidx.compose.runtime.Composer r36, final int r37, final int r38, final int r39) {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, int, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.ranges.ClosedFloatingPointRange, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void Slider(final SliderState state, Modifier modifier, boolean enabled, SliderColors colors, MutableInteractionSource interactionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        SliderState sliderState;
        Modifier modifier2;
        boolean z;
        final SliderColors colors2;
        final MutableInteractionSource interactionSource2;
        Function3 thumb;
        Function3 track;
        Modifier modifier3;
        boolean z2;
        boolean enabled2;
        Function3 thumb2;
        MutableInteractionSource interactionSource3;
        Modifier modifier4;
        Object value$iv;
        final SliderColors colors3;
        final Modifier modifier5;
        final boolean enabled3;
        final MutableInteractionSource interactionSource4;
        final Function3 thumb3;
        final Function3 track2;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-1303883986);
        ComposerKt.sourceInformation($composer2, "C(Slider)P(4,3,1)339@15682L8,340@15742L39,341@15830L157,348@16036L114,354@16217L188:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            sliderState = state;
        } else if (($changed & 6) == 0) {
            sliderState = state;
            $dirty |= $composer2.changedInstance(sliderState) ? 4 : 2;
        } else {
            sliderState = state;
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
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            colors2 = colors;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer2.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            thumb = function3;
        } else if ((196608 & $changed) == 0) {
            thumb = function3;
            $dirty |= $composer2.changedInstance(thumb) ? 131072 : 65536;
        } else {
            thumb = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            track = function32;
        } else if ((1572864 & $changed) == 0) {
            track = function32;
            $dirty |= $composer2.changedInstance(track) ? 1048576 : 524288;
        } else {
            track = function32;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier5 = modifier2;
            enabled3 = z;
            colors3 = colors2;
            interactionSource4 = interactionSource2;
            thumb3 = thumb;
            track2 = track;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                final boolean enabled4 = i4 != 0 ? true : z;
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    colors2 = SliderDefaults.INSTANCE.colors($composer2, 6);
                }
                if (i5 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -403425429, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer2.rememberedValue();
                    modifier3 = modifier6;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    interactionSource2 = (MutableInteractionSource) value$iv;
                } else {
                    modifier3 = modifier6;
                }
                if (i6 != 0) {
                    z2 = true;
                    thumb = ComposableLambdaKt.rememberComposableLambda(1426271326, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$10
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState2, Composer composer, Integer num) {
                            invoke(sliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState it, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C342@15855L126:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1426271326, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:342)");
                            }
                            SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled4, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                } else {
                    z2 = true;
                }
                if (i7 != 0) {
                    track = ComposableLambdaKt.rememberComposableLambda(577038345, z2, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$11
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState2, Composer composer, Integer num) {
                            invoke(sliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState sliderState2, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C349@16076L68:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(577038345, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:349)");
                            }
                            SliderDefaults.INSTANCE.m2498Track4EFweAY(sliderState2, (Modifier) null, enabled4, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer3, ($changed2 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                    enabled2 = enabled4;
                    thumb2 = thumb;
                    interactionSource3 = interactionSource2;
                    modifier4 = modifier3;
                } else {
                    enabled2 = enabled4;
                    thumb2 = thumb;
                    interactionSource3 = interactionSource2;
                    modifier4 = modifier3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                enabled2 = z;
                thumb2 = thumb;
                z2 = true;
                interactionSource3 = interactionSource2;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1303883986, $dirty, -1, "androidx.compose.material3.Slider (Slider.kt:351)");
            }
            if (!(sliderState.getSteps() >= 0 ? z2 : false)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            SliderImpl(modifier4, sliderState, enabled2, interactionSource3, thumb2, track, $composer2, (($dirty >> 3) & 14) | (($dirty << 3) & 112) | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (458752 & ($dirty >> 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            modifier5 = modifier4;
            enabled3 = enabled2;
            interactionSource4 = interactionSource3;
            thumb3 = thumb2;
            track2 = track;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$13
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

                public final void invoke(Composer composer, int i8) {
                    SliderKt.Slider(SliderState.this, modifier5, enabled3, colors3, interactionSource4, thumb3, track2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int steps, Function0<Unit> function0, SliderColors colors, Composer $composer, final int $changed, final int i) {
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        Function1<? super ClosedFloatingPointRange<Float>, Unit> function12;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange valueRange;
        int steps2;
        Function0 onValueChangeFinished;
        int $dirty;
        final SliderColors colors2;
        int $dirty2;
        final boolean enabled3;
        int steps3;
        Function0 onValueChangeFinished2;
        Modifier modifier3;
        ClosedFloatingPointRange valueRange2;
        int $dirty3;
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        final SliderColors colors3;
        final Modifier modifier4;
        final boolean enabled4;
        final ClosedFloatingPointRange valueRange3;
        final Function0 onValueChangeFinished3;
        final int steps4;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-743091416);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)P(6,3,2,1,7,5,4)408@18812L8,410@18884L39,411@18981L39,423@19399L186,430@19606L184,437@19808L199,413@19026L987:Slider.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            closedFloatingPointRange3 = closedFloatingPointRange;
        } else if (($changed & 6) == 0) {
            closedFloatingPointRange3 = closedFloatingPointRange;
            $dirty4 |= $composer3.changed(closedFloatingPointRange3) ? 4 : 2;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty4 |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty4 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                valueRange = closedFloatingPointRange2;
                if ($composer3.changed(valueRange)) {
                    i3 = 16384;
                    $dirty4 |= i3;
                }
            } else {
                valueRange = closedFloatingPointRange2;
            }
            i3 = 8192;
            $dirty4 |= i3;
        } else {
            valueRange = closedFloatingPointRange2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty4 |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
            onValueChangeFinished = function0;
        } else if ((1572864 & $changed) == 0) {
            onValueChangeFinished = function0;
            $dirty4 |= $composer3.changedInstance(onValueChangeFinished) ? 1048576 : 524288;
        } else {
            onValueChangeFinished = function0;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(colors)) {
                i2 = 8388608;
                $dirty4 |= i2;
            }
            i2 = 4194304;
            $dirty4 |= i2;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors3 = colors;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            valueRange3 = valueRange;
            steps4 = steps2;
            onValueChangeFinished3 = onValueChangeFinished;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    ClosedFloatingPointRange valueRange4 = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty = $dirty5 & (-57345);
                    valueRange = valueRange4;
                } else {
                    $dirty = $dirty5;
                }
                if (i6 != 0) {
                    steps2 = 0;
                }
                if (i7 != 0) {
                    onValueChangeFinished = null;
                }
                if ((i & 128) != 0) {
                    colors2 = SliderDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 = (-29360129) & $dirty;
                    enabled3 = enabled2;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    modifier3 = modifier5;
                    valueRange2 = valueRange;
                    $dirty3 = -743091416;
                } else {
                    colors2 = colors;
                    $dirty2 = $dirty;
                    enabled3 = enabled2;
                    steps3 = steps2;
                    onValueChangeFinished2 = onValueChangeFinished;
                    modifier3 = modifier5;
                    valueRange2 = valueRange;
                    $dirty3 = -743091416;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 16) != 0 ? $dirty5 & (-57345) : $dirty5;
                if ((i & 128) != 0) {
                    $dirty6 &= -29360129;
                }
                colors2 = colors;
                $dirty2 = $dirty6;
                enabled3 = enabled2;
                steps3 = steps2;
                onValueChangeFinished2 = onValueChangeFinished;
                $dirty3 = -743091416;
                modifier3 = modifier2;
                valueRange2 = valueRange;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart($dirty3, $dirty2, -1, "androidx.compose.material3.RangeSlider (Slider.kt:409)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1661051042, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableInteractionSource startInteractionSource = (MutableInteractionSource) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1661054146, "CC(remember):Slider.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableInteractionSource endInteractionSource = (MutableInteractionSource) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            RangeSlider(closedFloatingPointRange3, function12, modifier3, enabled3, valueRange2, onValueChangeFinished2, null, startInteractionSource, endInteractionSource, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C424@19428L147:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-811582901, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                    }
                    SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled3, 0L, $composer4, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C431@19635L145:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1832060001, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                    }
                    SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled3, 0L, $composer4, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState rangeSliderState, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C438@19857L140:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(377064480, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                    }
                    SliderDefaults.INSTANCE.m2497Track4EFweAY(rangeSliderState, (Modifier) null, enabled3, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), steps3, $composer2, ($dirty2 & 14) | 918552576 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty2 >> 3) & 458752), (($dirty2 >> 9) & 896) | 54, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            modifier4 = modifier3;
            enabled4 = enabled3;
            valueRange3 = valueRange2;
            onValueChangeFinished3 = onValueChangeFinished2;
            steps4 = steps3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$4
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

                public final void invoke(Composer composer, int i8) {
                    SliderKt.RangeSlider(closedFloatingPointRange, function1, modifier4, enabled4, valueRange3, steps4, onValueChangeFinished3, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSlider(final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r30, final kotlin.jvm.functions.Function1<? super kotlin.ranges.ClosedFloatingPointRange<java.lang.Float>, kotlin.Unit> r31, androidx.compose.ui.Modifier r32, boolean r33, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r34, kotlin.jvm.functions.Function0<kotlin.Unit> r35, androidx.compose.material3.SliderColors r36, androidx.compose.foundation.interaction.MutableInteractionSource r37, androidx.compose.foundation.interaction.MutableInteractionSource r38, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, int r42, androidx.compose.runtime.Composer r43, final int r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 1186
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, int, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void RangeSlider(final RangeSliderState state, Modifier modifier, boolean enabled, SliderColors colors, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function32, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function33, Composer $composer, final int $changed, final int i) {
        RangeSliderState rangeSliderState;
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        final MutableInteractionSource startInteractionSource2;
        final MutableInteractionSource endInteractionSource2;
        int i2;
        int i3;
        int $dirty;
        boolean z;
        Function3 startThumb;
        Function3 track;
        Function3 startThumb2;
        Function3 endThumb;
        Modifier modifier3;
        MutableInteractionSource startInteractionSource3;
        MutableInteractionSource endInteractionSource3;
        int i4;
        boolean enabled3;
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        final Modifier modifier4;
        final SliderColors colors3;
        final boolean enabled4;
        final MutableInteractionSource startInteractionSource4;
        final MutableInteractionSource endInteractionSource4;
        final Function3 startThumb3;
        final Function3 endThumb2;
        final Function3 track2;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(511405654);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)P(7,4,1!1,5!1,6)616@28157L8,617@28222L39,618@28316L39,619@28414L162,626@28633L160,633@28847L175,643@29089L295:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            rangeSliderState = state;
        } else if (($changed & 6) == 0) {
            rangeSliderState = state;
            $dirty2 |= $composer3.changedInstance(rangeSliderState) ? 4 : 2;
        } else {
            rangeSliderState = state;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 2048;
                    $dirty2 |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 1024;
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty2 |= 24576;
            startInteractionSource2 = startInteractionSource;
        } else if (($changed & 24576) == 0) {
            startInteractionSource2 = startInteractionSource;
            $dirty2 |= $composer3.changed(startInteractionSource2) ? 16384 : 8192;
        } else {
            startInteractionSource2 = startInteractionSource;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            endInteractionSource2 = endInteractionSource;
        } else if ((196608 & $changed) == 0) {
            endInteractionSource2 = endInteractionSource;
            $dirty2 |= $composer3.changed(endInteractionSource2) ? 131072 : 65536;
        } else {
            endInteractionSource2 = endInteractionSource;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty2 |= $composer3.changedInstance(function32) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty2 |= 100663296;
            i3 = i12;
        } else if (($changed & 100663296) == 0) {
            i3 = i12;
            $dirty2 |= $composer3.changedInstance(function33) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i12;
        }
        if (($dirty2 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            endThumb2 = function32;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            colors3 = colors2;
            startInteractionSource4 = startInteractionSource2;
            endInteractionSource4 = endInteractionSource2;
            startThumb3 = function3;
            track2 = function33;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    colors2 = SliderDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1661349858, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    $dirty = $dirty2;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    startInteractionSource2 = (MutableInteractionSource) value$iv2;
                } else {
                    $dirty = $dirty2;
                }
                if (i9 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1661352866, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    endInteractionSource2 = (MutableInteractionSource) value$iv;
                }
                if (i10 != 0) {
                    z = true;
                    startThumb = ComposableLambdaKt.rememberComposableLambda(1884205643, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$14
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                            invoke(rangeSliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C620@28439L131:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1884205643, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:620)");
                            }
                            SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled2, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                } else {
                    z = true;
                    startThumb = function3;
                }
                Function3 endThumb3 = i2 != 0 ? ComposableLambdaKt.rememberComposableLambda(1016457138, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$15
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                        invoke(rangeSliderState2, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C627@28658L129:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1016457138, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:627)");
                        }
                        SliderDefaults.INSTANCE.m2496Thumb9LiSoMs(MutableInteractionSource.this, null, colors2, enabled2, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54) : function32;
                if (i3 != 0) {
                    track = ComposableLambdaKt.rememberComposableLambda(-1617375262, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$16
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                            invoke(rangeSliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState rangeSliderState2, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C634@28892L124:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1617375262, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:634)");
                            }
                            SliderDefaults.INSTANCE.m2497Track4EFweAY(rangeSliderState2, (Modifier) null, enabled2, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    startThumb2 = startThumb;
                    endThumb = endThumb3;
                    modifier3 = modifier2;
                    startInteractionSource3 = startInteractionSource2;
                    endInteractionSource3 = endInteractionSource2;
                    i4 = 511405654;
                    $dirty2 = $dirty;
                    enabled3 = enabled2;
                } else {
                    track = function33;
                    startThumb2 = startThumb;
                    endThumb = endThumb3;
                    modifier3 = modifier2;
                    startInteractionSource3 = startInteractionSource2;
                    endInteractionSource3 = endInteractionSource2;
                    i4 = 511405654;
                    $dirty2 = $dirty;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    startThumb2 = function3;
                    endThumb = function32;
                    track = function33;
                    $dirty2 &= -7169;
                    modifier3 = modifier2;
                    startInteractionSource3 = startInteractionSource2;
                    endInteractionSource3 = endInteractionSource2;
                    i4 = 511405654;
                    z = true;
                    enabled3 = enabled2;
                } else {
                    startThumb2 = function3;
                    endThumb = function32;
                    track = function33;
                    modifier3 = modifier2;
                    startInteractionSource3 = startInteractionSource2;
                    endInteractionSource3 = endInteractionSource2;
                    i4 = 511405654;
                    z = true;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty2, -1, "androidx.compose.material3.RangeSlider (Slider.kt:640)");
            }
            if (!(rangeSliderState.getSteps() >= 0 ? z : false)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            $composer2 = $composer3;
            RangeSliderImpl(modifier3, rangeSliderState, enabled3, startInteractionSource3, endInteractionSource3, startThumb2, endThumb, track, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 << 3) & 112) | ($dirty2 & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 3) & 57344) | (($dirty2 >> 3) & 458752) | (($dirty2 >> 3) & 3670016) | (29360128 & ($dirty2 >> 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            colors3 = colors2;
            enabled4 = enabled3;
            startInteractionSource4 = startInteractionSource3;
            endInteractionSource4 = endInteractionSource3;
            startThumb3 = startThumb2;
            endThumb2 = endThumb;
            track2 = track;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$18
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

                public final void invoke(Composer composer, int i13) {
                    SliderKt.RangeSlider(RangeSliderState.this, modifier4, enabled4, colors3, startInteractionSource4, endInteractionSource4, startThumb3, endThumb2, track2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0368 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x043a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SliderImpl(final androidx.compose.ui.Modifier r50, final androidx.compose.material3.SliderState r51, final boolean r52, final androidx.compose.foundation.interaction.MutableInteractionSource r53, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57) {
        /*
            Method dump skipped, instructions count: 1339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.SliderImpl(androidx.compose.ui.Modifier, androidx.compose.material3.SliderState, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0701  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0550 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x039b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0352  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSliderImpl(final androidx.compose.ui.Modifier r57, final androidx.compose.material3.RangeSliderState r58, final boolean r59, final androidx.compose.foundation.interaction.MutableInteractionSource r60, final androidx.compose.foundation.interaction.MutableInteractionSource r61, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r62, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r63, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r64, androidx.compose.runtime.Composer r65, final int r66) {
        /*
            Method dump skipped, instructions count: 1828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSliderImpl(androidx.compose.ui.Modifier, androidx.compose.material3.RangeSliderState, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.collections.IntIterator] */
    public static final float snapValueToTick(float current, float[] tickFractions, float minPx, float maxPx) {
        Float valueOf;
        if (tickFractions.length == 0) {
            valueOf = null;
        } else {
            float minElem$iv = tickFractions[0];
            int lastIndex$iv = ArraysKt.getLastIndex(tickFractions);
            if (lastIndex$iv == 0) {
                valueOf = Float.valueOf(minElem$iv);
            } else {
                float minValue$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, minElem$iv) - current);
                ?? it = new IntRange(1, lastIndex$iv).iterator();
                while (it.hasNext()) {
                    int i$iv = it.nextInt();
                    float e$iv = tickFractions[i$iv];
                    float v$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, e$iv) - current);
                    if (Float.compare(minValue$iv, v$iv) > 0) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                }
                valueOf = Float.valueOf(minElem$iv);
            }
        }
        if (valueOf == null) {
            return current;
        }
        float $this$snapValueToTick_u24lambda_u2430 = valueOf.floatValue();
        return MathHelpersKt.lerp(minPx, maxPx, $this$snapValueToTick_u24lambda_u2430);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0064 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m2508awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, long r8, int r10, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.material3.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = (androidx.compose.material3.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = new androidx.compose.material3.SliderKt$awaitSlop$1
            r0.<init>(r11)
        L19:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r6.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r11
            goto L55
        L36:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r7
            r2 = r8
            r4 = r10
            kotlin.jvm.internal.Ref$FloatRef r7 = new kotlin.jvm.internal.Ref$FloatRef
            r7.<init>()
            androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1 r8 = new androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1
            r8.<init>()
            r5 = r8
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6.L$0 = r7
            r8 = 1
            r6.label = r8
            java.lang.Object r8 = androidx.compose.material3.internal.DragGestureDetectorCopyKt.m2896awaitHorizontalPointerSlopOrCancellationgDDlDlE(r1, r2, r4, r5, r6)
            if (r8 != r0) goto L55
            return r0
        L55:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 == 0) goto L64
            float r9 = r7.element
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            kotlin.Pair r9 = kotlin.TuplesKt.to(r8, r9)
            goto L65
        L64:
            r9 = 0
        L65:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.m2508awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] stepsToTickFractions(int steps) {
        if (steps == 0) {
            return new float[0];
        }
        int i = steps + 2;
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = i2 / (steps + 1);
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float a1, float b1, float x1, float a2, float b2) {
        return MathHelpersKt.lerp(a2, b2, calcFraction(a1, b1, x1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: scale-ziovWd0, reason: not valid java name */
    public static final long m2511scaleziovWd0(float a1, float b1, long x, float a2, float b2) {
        return SliderRange(scale(a1, b1, SliderRange.m2523getStartimpl(x), a2, b2), scale(a1, b1, SliderRange.m2522getEndInclusiveimpl(x), a2, b2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float a, float b, float pos) {
        return RangesKt.coerceIn(((b - a) > 0.0f ? 1 : ((b - a) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (pos - a) / (b - a), 0.0f, 1.0f);
    }

    private static final Modifier sliderSemantics(Modifier $this$sliderSemantics, final SliderState state, final boolean enabled) {
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$sliderSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                final SliderState sliderState = state;
                SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float targetValue) {
                        float newValue = RangesKt.coerceIn(targetValue, SliderState.this.getValueRange().getStart().floatValue(), SliderState.this.getValueRange().getEndInclusive().floatValue());
                        boolean z = true;
                        if (SliderState.this.getSteps() > 0) {
                            float distance = newValue;
                            int i = 0;
                            int steps = SliderState.this.getSteps() + 1;
                            if (0 <= steps) {
                                while (true) {
                                    float stepValue = MathHelpersKt.lerp(SliderState.this.getValueRange().getStart().floatValue(), SliderState.this.getValueRange().getEndInclusive().floatValue(), i / (SliderState.this.getSteps() + 1));
                                    if (Math.abs(stepValue - newValue) <= distance) {
                                        distance = Math.abs(stepValue - newValue);
                                        newValue = stepValue;
                                    }
                                    if (i == steps) {
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                        float resolvedValue = newValue;
                        if (resolvedValue == SliderState.this.getValue()) {
                            z = false;
                        } else {
                            if (!(resolvedValue == SliderState.this.getValue())) {
                                Function1<Float, Unit> onValueChange$material3_release = SliderState.this.getOnValueChange$material3_release();
                                SliderState sliderState2 = SliderState.this;
                                if (onValueChange$material3_release != null) {
                                    Function1 it = sliderState2.getOnValueChange$material3_release();
                                    if (it != null) {
                                        it.invoke(Float.valueOf(resolvedValue));
                                    }
                                } else {
                                    sliderState2.setValue(resolvedValue);
                                }
                            }
                            Function0<Unit> onValueChangeFinished = SliderState.this.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), state.getValue(), RangesKt.rangeTo(state.getValueRange().getStart().floatValue(), state.getValueRange().getEndInclusive().floatValue()), state.getSteps());
    }

    private static final Modifier rangeSliderStartThumbSemantics(Modifier $this$rangeSliderStartThumbSemantics, final RangeSliderState state, final boolean enabled) {
        final ClosedFloatingPointRange valueRange = RangesKt.rangeTo(state.getValueRange().getStart().floatValue(), state.getActiveRangeEnd());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$rangeSliderStartThumbSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = valueRange;
                final RangeSliderState rangeSliderState = state;
                SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float targetValue) {
                        float newValue = RangesKt.coerceIn(targetValue, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z = true;
                        if (rangeSliderState.getStartSteps$material3_release() > 0) {
                            float distance = newValue;
                            int i = 0;
                            int startSteps$material3_release = rangeSliderState.getStartSteps$material3_release() + 1;
                            if (0 <= startSteps$material3_release) {
                                while (true) {
                                    float stepValue = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState.getStartSteps$material3_release() + 1));
                                    if (Math.abs(stepValue - newValue) <= distance) {
                                        distance = Math.abs(stepValue - newValue);
                                        newValue = stepValue;
                                    }
                                    if (i == startSteps$material3_release) {
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                        float resolvedValue = newValue;
                        if (resolvedValue == rangeSliderState.getActiveRangeStart()) {
                            z = false;
                        } else {
                            long resolvedRange = SliderKt.SliderRange(resolvedValue, rangeSliderState.getActiveRangeEnd());
                            long activeRange = SliderKt.SliderRange(rangeSliderState.getActiveRangeStart(), rangeSliderState.getActiveRangeEnd());
                            if (!SliderRange.m2521equalsimpl0(resolvedRange, activeRange)) {
                                Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState.getOnValueChange$material3_release();
                                RangeSliderState rangeSliderState2 = rangeSliderState;
                                if (onValueChange$material3_release != null) {
                                    Function1 it = rangeSliderState2.getOnValueChange$material3_release();
                                    if (it != null) {
                                        it.invoke(SliderRange.m2518boximpl(resolvedRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2523getStartimpl(resolvedRange));
                                    rangeSliderState.setActiveRangeEnd(SliderRange.m2522getEndInclusiveimpl(resolvedRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), state.getActiveRangeStart(), valueRange, state.getStartSteps$material3_release());
    }

    private static final Modifier rangeSliderEndThumbSemantics(Modifier $this$rangeSliderEndThumbSemantics, final RangeSliderState state, final boolean enabled) {
        final ClosedFloatingPointRange valueRange = RangesKt.rangeTo(state.getActiveRangeStart(), state.getValueRange().getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$rangeSliderEndThumbSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = valueRange;
                final RangeSliderState rangeSliderState = state;
                SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float targetValue) {
                        float newValue = RangesKt.coerceIn(targetValue, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z = true;
                        if (rangeSliderState.getEndSteps$material3_release() > 0) {
                            float distance = newValue;
                            int i = 0;
                            int endSteps$material3_release = rangeSliderState.getEndSteps$material3_release() + 1;
                            if (0 <= endSteps$material3_release) {
                                while (true) {
                                    float stepValue = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState.getEndSteps$material3_release() + 1));
                                    if (Math.abs(stepValue - newValue) <= distance) {
                                        distance = Math.abs(stepValue - newValue);
                                        newValue = stepValue;
                                    }
                                    if (i == endSteps$material3_release) {
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }
                        float resolvedValue = newValue;
                        if (resolvedValue == rangeSliderState.getActiveRangeEnd()) {
                            z = false;
                        } else {
                            long resolvedRange = SliderKt.SliderRange(rangeSliderState.getActiveRangeStart(), resolvedValue);
                            long activeRange = SliderKt.SliderRange(rangeSliderState.getActiveRangeStart(), rangeSliderState.getActiveRangeEnd());
                            if (!SliderRange.m2521equalsimpl0(resolvedRange, activeRange)) {
                                Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState.getOnValueChange$material3_release();
                                RangeSliderState rangeSliderState2 = rangeSliderState;
                                if (onValueChange$material3_release != null) {
                                    Function1 it = rangeSliderState2.getOnValueChange$material3_release();
                                    if (it != null) {
                                        it.invoke(SliderRange.m2518boximpl(resolvedRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2523getStartimpl(resolvedRange));
                                    rangeSliderState.setActiveRangeEnd(SliderRange.m2522getEndInclusiveimpl(resolvedRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), state.getActiveRangeEnd(), valueRange, state.getEndSteps$material3_release());
    }

    private static final Modifier sliderTapModifier(Modifier $this$sliderTapModifier, SliderState state, MutableInteractionSource interactionSource, boolean enabled) {
        if (enabled) {
            return SuspendingPointerInputFilterKt.pointerInput($this$sliderTapModifier, state, interactionSource, new SliderKt$sliderTapModifier$1(state, null));
        }
        return $this$sliderTapModifier;
    }

    private static final Modifier rangeSliderPressDragModifier(Modifier $this$rangeSliderPressDragModifier, RangeSliderState state, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, boolean enabled) {
        if (enabled) {
            return SuspendingPointerInputFilterKt.pointerInput($this$rangeSliderPressDragModifier, new Object[]{startInteractionSource, endInteractionSource, state}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new SliderKt$rangeSliderPressDragModifier$1(state, startInteractionSource, endInteractionSource, null));
        }
        return $this$rangeSliderPressDragModifier;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    public static final float getThumbWidth() {
        return ThumbWidth;
    }

    public static final long SliderRange(float start, float endInclusive) {
        boolean z = true;
        boolean isUnspecified = Float.isNaN(start) && Float.isNaN(endInclusive);
        if (!isUnspecified && start > endInclusive + SliderRangeTolerance) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("start(" + start + ") must be <= endInclusive(" + endInclusive + ')').toString());
        }
        long v1$iv = Float.floatToRawIntBits(start);
        long v2$iv = Float.floatToRawIntBits(endInclusive);
        return SliderRange.m2519constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    public static final long SliderRange(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        float start = closedFloatingPointRange.getStart().floatValue();
        float endInclusive = closedFloatingPointRange.getEndInclusive().floatValue();
        boolean z = true;
        boolean isUnspecified = Float.isNaN(start) && Float.isNaN(endInclusive);
        if (!isUnspecified && start > endInclusive + SliderRangeTolerance) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("ClosedFloatingPointRange<Float>.start(" + start + ") must be <= ClosedFloatingPoint.endInclusive(" + endInclusive + ')').toString());
        }
        long v1$iv = Float.floatToRawIntBits(start);
        long v2$iv = Float.floatToRawIntBits(endInclusive);
        return SliderRange.m2519constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: isSpecified-If1S1O4, reason: not valid java name */
    public static final boolean m2509isSpecifiedIf1S1O4(long $this$isSpecified) {
        return $this$isSpecified != SliderRange.INSTANCE.m2528getUnspecifiedFYbKRX4();
    }
}
