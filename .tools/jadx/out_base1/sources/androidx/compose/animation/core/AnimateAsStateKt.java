package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: AnimateAsState.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aH\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aM\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u00022\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010!\u001aW\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010\"\u001aC\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010$\u001aM\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010%\u001aH\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0013\u001a\u00020\b2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001aR\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0013\u001a\u00020\b2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001aH\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0013\u001a\u00020\n2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010(\u001aR\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0013\u001a\u00020\n2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010*\u001aH\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u00122\u0006\u0010\u0013\u001a\u00020\f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u0010(\u001aR\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u00122\u0006\u0010\u0013\u001a\u00020\f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010*\u001aC\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u00102\u001aM\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u00103\u001aH\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u0010(\u001aR\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u0010*\u001as\u00107\u001a\b\u0012\u0004\u0012\u0002H80\u0012\"\u0004\b\u0000\u00108\"\b\b\u0001\u00109*\u00020:2\u0006\u0010\u0013\u001a\u0002H82\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H90<2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H80\u00152\n\b\u0002\u0010 \u001a\u0004\u0018\u0001H82\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010=\u001a}\u00107\u001a\b\u0012\u0004\u0012\u0002H80\u0012\"\u0004\b\u0000\u00108\"\b\b\u0001\u00109*\u00020:2\u0006\u0010\u0013\u001a\u0002H82\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u0002H90<2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H80\u00152\n\b\u0002\u0010 \u001a\u0004\u0018\u0001H82\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010>\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006?²\u0006(\u0010@\u001a\u0010\u0012\u0004\u0012\u0002H8\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\"\u0004\b\u0000\u00108\"\b\b\u0001\u00109*\u00020:X\u008a\u0084\u0002²\u0006 \u0010A\u001a\b\u0012\u0004\u0012\u0002H80\u0015\"\u0004\b\u0000\u00108\"\b\b\u0001\u00109*\u00020:X\u008a\u0084\u0002"}, d2 = {"defaultAnimation", "Landroidx/compose/animation/core/SpringSpec;", "", "dpDefaultSpring", "Landroidx/compose/ui/unit/Dp;", "intDefaultSpring", "", "intOffsetDefaultSpring", "Landroidx/compose/ui/unit/IntOffset;", "intSizeDefaultSpring", "Landroidx/compose/ui/unit/IntSize;", "offsetDefaultSpring", "Landroidx/compose/ui/geometry/Offset;", "rectDefaultSpring", "Landroidx/compose/ui/geometry/Rect;", "sizeDefaultSpring", "Landroidx/compose/ui/geometry/Size;", "animateDpAsState", "Landroidx/compose/runtime/State;", "targetValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "finishedListener", "Lkotlin/Function1;", "", "animateDpAsState-Kz89ssw", "(FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "label", "", "animateDpAsState-AjpBEmI", "(FLandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateFloatAsState", "visibilityThreshold", "(FLandroidx/compose/animation/core/AnimationSpec;FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(FLandroidx/compose/animation/core/AnimationSpec;FLjava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntAsState", "(ILandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(ILandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntOffsetAsState", "animateIntOffsetAsState-8f6pmRE", "(JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntOffsetAsState-HyPO7BM", "(JLandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntSizeAsState", "animateIntSizeAsState-zTRF_AQ", "animateIntSizeAsState-4goxYXU", "animateOffsetAsState", "animateOffsetAsState-N6fFfp4", "animateOffsetAsState-7362WCg", "animateRectAsState", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateSizeAsState", "animateSizeAsState-LjSzlW0", "animateSizeAsState-YLp_XPw", "animateValueAsState", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animation-core_release", "listener", "animSpec"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimateAsStateKt {
    private static final SpringSpec<Float> defaultAnimation = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
    private static final SpringSpec<Dp> dpDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m6624boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
    private static final SpringSpec<Size> sizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m3979boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
    private static final SpringSpec<Offset> offsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m3911boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
    private static final SpringSpec<Rect> rectDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
    private static final SpringSpec<Integer> intDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Integer.valueOf(VisibilityThresholdsKt.getVisibilityThreshold(IntCompanionObject.INSTANCE)), 3, null);
    private static final SpringSpec<IntOffset> intOffsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m6745boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
    private static final SpringSpec<IntSize> intSizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m6788boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);

    public static final State<Float> animateFloatAsState(float targetValue, AnimationSpec<Float> animationSpec, float visibilityThreshold, String label, Function1<? super Float, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec resolvedAnimSpec;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 668842840, "C(animateFloatAsState)P(3!1,4,2)75@3368L173:AnimateAsState.kt#pdpnli");
        AnimationSpec animationSpec2 = (i & 2) != 0 ? defaultAnimation : animationSpec;
        float visibilityThreshold2 = (i & 4) != 0 ? 0.01f : visibilityThreshold;
        String label2 = (i & 8) != 0 ? "FloatAnimation" : label;
        Function1 finishedListener = (i & 16) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(668842840, $changed, -1, "androidx.compose.animation.core.animateFloatAsState (AnimateAsState.kt:68)");
        }
        if (animationSpec2 == defaultAnimation) {
            $composer.startReplaceGroup(1125598679);
            ComposerKt.sourceInformation($composer, "71@3220L83");
            ComposerKt.sourceInformationMarkerStart($composer, 313404723, "CC(remember):AnimateAsState.kt#9igjgp");
            boolean invalid$iv = ((($changed & 896) ^ 384) > 256 && $composer.changed(visibilityThreshold2)) || ($changed & 384) == 256;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = AnimationSpecKt.spring$default(0.0f, 0.0f, Float.valueOf(visibilityThreshold2), 3, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            resolvedAnimSpec = (SpringSpec) value$iv;
        } else {
            $composer.startReplaceGroup(1125708605);
            $composer.endReplaceGroup();
            resolvedAnimSpec = animationSpec2;
        }
        State<Float> animateValueAsState = animateValueAsState(Float.valueOf(targetValue), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), resolvedAnimSpec, Float.valueOf(visibilityThreshold2), label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    /* renamed from: animateDpAsState-AjpBEmI, reason: not valid java name */
    public static final State<Dp> m120animateDpAsStateAjpBEmI(float targetValue, AnimationSpec<Dp> animationSpec, String label, Function1<? super Dp, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -1407150062, "C(animateDpAsState)P(3:c#ui.unit.Dp!1,2)114@5081L165:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = dpDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "DpAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1407150062, $changed, -1, "androidx.compose.animation.core.animateDpAsState (AnimateAsState.kt:113)");
        }
        State<Dp> animateValueAsState = animateValueAsState(Dp.m6624boximpl(targetValue), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    /* renamed from: animateSizeAsState-YLp_XPw, reason: not valid java name */
    public static final State<Size> m129animateSizeAsStateYLp_XPw(long targetValue, AnimationSpec<Size> animationSpec, String label, Function1<? super Size, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 1374633148, "C(animateSizeAsState)P(3:c#ui.geometry.Size!1,2)155@6928L167:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = sizeDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "SizeAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1374633148, $changed, -1, "androidx.compose.animation.core.animateSizeAsState (AnimateAsState.kt:154)");
        }
        State<Size> animateValueAsState = animateValueAsState(Size.m3979boximpl(targetValue), VectorConvertersKt.getVectorConverter(Size.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    /* renamed from: animateOffsetAsState-7362WCg, reason: not valid java name */
    public static final State<Offset> m126animateOffsetAsState7362WCg(long targetValue, AnimationSpec<Offset> animationSpec, String label, Function1<? super Offset, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 357896800, "C(animateOffsetAsState)P(3:c#ui.geometry.Offset!1,2)195@8761L169:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = offsetDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "OffsetAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(357896800, $changed, -1, "androidx.compose.animation.core.animateOffsetAsState (AnimateAsState.kt:194)");
        }
        State<Offset> animateValueAsState = animateValueAsState(Offset.m3911boximpl(targetValue), VectorConvertersKt.getVectorConverter(Offset.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    public static final State<Rect> animateRectAsState(Rect targetValue, AnimationSpec<Rect> animationSpec, String label, Function1<? super Rect, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 536062978, "C(animateRectAsState)P(3!1,2)236@10633L167:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = rectDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "RectAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(536062978, $changed, -1, "androidx.compose.animation.core.animateRectAsState (AnimateAsState.kt:235)");
        }
        State<Rect> animateValueAsState = animateValueAsState(targetValue, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    public static final State<Integer> animateIntAsState(int targetValue, AnimationSpec<Integer> animationSpec, String label, Function1<? super Integer, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 428074472, "C(animateIntAsState)P(3!1,2)274@12363L166:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "IntAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(428074472, $changed, -1, "androidx.compose.animation.core.animateIntAsState (AnimateAsState.kt:273)");
        }
        State<Integer> animateValueAsState = animateValueAsState(Integer.valueOf(targetValue), VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    /* renamed from: animateIntOffsetAsState-HyPO7BM, reason: not valid java name */
    public static final State<IntOffset> m123animateIntOffsetAsStateHyPO7BM(long targetValue, AnimationSpec<IntOffset> animationSpec, String label, Function1<? super IntOffset, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -696782904, "C(animateIntOffsetAsState)P(3:c#ui.unit.IntOffset!1,2)314@14224L172:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intOffsetDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "IntOffsetAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-696782904, $changed, -1, "androidx.compose.animation.core.animateIntOffsetAsState (AnimateAsState.kt:313)");
        }
        State<IntOffset> animateValueAsState = animateValueAsState(IntOffset.m6745boximpl(targetValue), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    /* renamed from: animateIntSizeAsState-4goxYXU, reason: not valid java name */
    public static final State<IntSize> m124animateIntSizeAsState4goxYXU(long targetValue, AnimationSpec<IntSize> animationSpec, String label, Function1<? super IntSize, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 582576328, "C(animateIntSizeAsState)P(3:c#ui.unit.IntSize!1,2)352@16009L170:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intSizeDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        String label2 = (i & 4) != 0 ? "IntSizeAnimation" : label;
        Function1 finishedListener = (i & 8) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(582576328, $changed, -1, "androidx.compose.animation.core.animateIntSizeAsState (AnimateAsState.kt:351)");
        }
        State<IntSize> animateValueAsState = animateValueAsState(IntSize.m6788boximpl(targetValue), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), animationSpec2, null, label2, finishedListener, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 6) & 57344) | (458752 & ($changed << 6)), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    public static final <T, V extends AnimationVector> State<T> animateValueAsState(final T t, TwoWayConverter<T, V> twoWayConverter, AnimationSpec<T> animationSpec, T t2, String label, Function1<? super T, Unit> function1, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Animatable animatable;
        Object value$iv5;
        Object value$iv6;
        ComposerKt.sourceInformationMarkerStart($composer, -1994373980, "C(animateValueAsState)P(3,4!1,5,2)393@18031L21,399@18213L44,400@18279L79,401@18379L38,402@18456L339,413@18814L42,414@18872L44,414@18861L55,417@18945L697,417@18921L721:AnimateAsState.kt#pdpnli");
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 728506592, "CC(remember):AnimateAsState.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv6 = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                $composer.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            animationSpec2 = (SpringSpec) value$iv6;
        } else {
            animationSpec2 = animationSpec;
        }
        Object visibilityThreshold = (i & 8) != 0 ? null : t2;
        String label2 = (i & 16) != 0 ? "ValueAnimation" : label;
        Function1 finishedListener = (i & 32) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1994373980, $changed, -1, "androidx.compose.animation.core.animateValueAsState (AnimateAsState.kt:397)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 728512439, "CC(remember):AnimateAsState.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        MutableState toolingOverride = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 728514586, "CC(remember):AnimateAsState.kt#9igjgp");
        Object it$iv3 = $composer.rememberedValue();
        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Animatable(t, twoWayConverter, visibilityThreshold, label2);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv3;
        }
        Animatable animatable2 = (Animatable) value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        State listener$delegate = SnapshotStateKt.rememberUpdatedState(finishedListener, $composer, ($changed >> 15) & 14);
        AnimationSpec $this$animateValueAsState_u24lambda_u245 = animationSpec2;
        State animSpec$delegate = SnapshotStateKt.rememberUpdatedState((visibilityThreshold == null || !($this$animateValueAsState_u24lambda_u245 instanceof SpringSpec) || Intrinsics.areEqual(((SpringSpec) $this$animateValueAsState_u24lambda_u245).getVisibilityThreshold(), visibilityThreshold)) ? $this$animateValueAsState_u24lambda_u245 : AnimationSpecKt.spring(((SpringSpec) $this$animateValueAsState_u24lambda_u245).getDampingRatio(), ((SpringSpec) $this$animateValueAsState_u24lambda_u245).getStiffness(), visibilityThreshold), $composer, 0);
        ComposerKt.sourceInformationMarkerStart($composer, 728531669, "CC(remember):AnimateAsState.kt#9igjgp");
        Object it$iv4 = $composer.rememberedValue();
        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = ChannelKt.Channel$default(-1, null, null, 6, null);
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv4;
        }
        final Channel channel = (Channel) value$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 728533527, "CC(remember):AnimateAsState.kt#9igjgp");
        boolean invalid$iv = (((6 ^ ($changed & 14)) > 4 && $composer.changedInstance(t)) || ($changed & 6) == 4) | $composer.changedInstance(channel);
        Object it$iv5 = $composer.rememberedValue();
        if (invalid$iv || it$iv5 == Composer.INSTANCE.getEmpty()) {
            value$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    channel.mo8706trySendJP2dKIU(t);
                }
            };
            $composer.updateRememberedValue(value$iv4);
        } else {
            value$iv4 = it$iv5;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.SideEffect((Function0) value$iv4, $composer, 0);
        ComposerKt.sourceInformationMarkerStart($composer, 728536516, "CC(remember):AnimateAsState.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(channel) | $composer.changedInstance(animatable2) | $composer.changed(animSpec$delegate) | $composer.changed(listener$delegate);
        Object it$iv6 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
            animatable = animatable2;
            value$iv5 = new AnimateAsStateKt$animateValueAsState$3$1(channel, animatable, animSpec$delegate, listener$delegate, null);
            $composer.updateRememberedValue(value$iv5);
        } else {
            animatable = animatable2;
            value$iv5 = it$iv6;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(channel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv5, $composer, 0);
        State<T> state = (State) toolingOverride.getValue();
        if (state == null) {
            state = animatable.asState();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Function1<T, Unit> animateValueAsState$lambda$4(State<? extends Function1<? super T, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function1) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> AnimationSpec<T> animateValueAsState$lambda$6(State<? extends AnimationSpec<T>> state) {
        Object thisObj$iv = state.getValue();
        return (AnimationSpec) thisObj$iv;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    public static final /* synthetic */ State animateFloatAsState(float targetValue, AnimationSpec animationSpec, float visibilityThreshold, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 1091643291, "C(animateFloatAsState)P(2!1,3)446@20047L143:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = defaultAnimation;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        float visibilityThreshold2 = (i & 4) != 0 ? 0.01f : visibilityThreshold;
        Function1 finishedListener2 = (i & 8) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1091643291, $changed, -1, "androidx.compose.animation.core.animateFloatAsState (AnimateAsState.kt:446)");
        }
        State<Float> animateFloatAsState = animateFloatAsState(targetValue, animationSpec2, visibilityThreshold2, null, finishedListener2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | (($changed << 3) & 57344), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateFloatAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    /* renamed from: animateDpAsState-Kz89ssw, reason: not valid java name */
    public static final /* synthetic */ State m121animateDpAsStateKz89ssw(float targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 704104481, "C(animateDpAsState)P(2:c#ui.unit.Dp)463@20491L142:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = dpDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(704104481, $changed, -1, "androidx.compose.animation.core.animateDpAsState (AnimateAsState.kt:462)");
        }
        State animateValueAsState = animateValueAsState(Dp.m6624boximpl(targetValue), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    /* renamed from: animateSizeAsState-LjSzlW0, reason: not valid java name */
    public static final /* synthetic */ State m128animateSizeAsStateLjSzlW0(long targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 875212471, "C(animateSizeAsState)P(2:c#ui.geometry.Size)481@20948L144:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = sizeDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(875212471, $changed, -1, "androidx.compose.animation.core.animateSizeAsState (AnimateAsState.kt:480)");
        }
        State animateValueAsState = animateValueAsState(Size.m3979boximpl(targetValue), VectorConvertersKt.getVectorConverter(Size.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    /* renamed from: animateOffsetAsState-N6fFfp4, reason: not valid java name */
    public static final /* synthetic */ State m127animateOffsetAsStateN6fFfp4(long targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -456513133, "C(animateOffsetAsState)P(2:c#ui.geometry.Offset)499@21419L122:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = offsetDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-456513133, $changed, -1, "androidx.compose.animation.core.animateOffsetAsState (AnimateAsState.kt:498)");
        }
        State animateValueAsState = animateValueAsState(Offset.m3911boximpl(targetValue), VectorConvertersKt.getVectorConverter(Offset.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    public static final /* synthetic */ State animateRectAsState(Rect targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -782613967, "C(animateRectAsState)P(2)514@21856L120:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = rectDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-782613967, $changed, -1, "androidx.compose.animation.core.animateRectAsState (AnimateAsState.kt:513)");
        }
        State animateValueAsState = animateValueAsState(targetValue, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    public static final /* synthetic */ State animateIntAsState(int targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -842612981, "C(animateIntAsState)P(2)529@22285L119:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-842612981, $changed, -1, "androidx.compose.animation.core.animateIntAsState (AnimateAsState.kt:528)");
        }
        State animateValueAsState = animateValueAsState(Integer.valueOf(targetValue), VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    /* renamed from: animateIntOffsetAsState-8f6pmRE, reason: not valid java name */
    public static final /* synthetic */ State m122animateIntOffsetAsState8f6pmRE(long targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, 1010307371, "C(animateIntOffsetAsState)P(2:c#ui.unit.IntOffset)544@22749L125:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intOffsetDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1010307371, $changed, -1, "androidx.compose.animation.core.animateIntOffsetAsState (AnimateAsState.kt:543)");
        }
        State animateValueAsState = animateValueAsState(IntOffset.m6745boximpl(targetValue), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    /* renamed from: animateIntSizeAsState-zTRF_AQ, reason: not valid java name */
    public static final /* synthetic */ State m125animateIntSizeAsStatezTRF_AQ(long targetValue, AnimationSpec animationSpec, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        ComposerKt.sourceInformationMarkerStart($composer, -1749239765, "C(animateIntSizeAsState)P(2:c#ui.unit.IntSize)559@23207L123:AnimateAsState.kt#pdpnli");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec3 = intSizeDefaultSpring;
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Function1 finishedListener2 = (i & 4) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1749239765, $changed, -1, "androidx.compose.animation.core.animateIntSizeAsState (AnimateAsState.kt:558)");
        }
        State animateValueAsState = animateValueAsState(IntSize.m6788boximpl(targetValue), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), animationSpec2, null, null, finishedListener2, $composer, ($changed & 14) | (($changed << 3) & 896) | (($changed << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "animate*AsState APIs now have a new label parameter added.")
    public static final /* synthetic */ State animateValueAsState(Object targetValue, TwoWayConverter typeConverter, AnimationSpec animationSpec, Object visibilityThreshold, Function1 finishedListener, Composer $composer, int $changed, int i) {
        AnimationSpec animationSpec2;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -846382129, "C(animateValueAsState)P(2,3!1,4)572@23613L21,575@23730L240:AnimateAsState.kt#pdpnli");
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 728685216, "CC(remember):AnimateAsState.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            AnimationSpec animationSpec3 = (SpringSpec) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            animationSpec2 = animationSpec3;
        } else {
            animationSpec2 = animationSpec;
        }
        Object visibilityThreshold2 = (i & 8) != 0 ? null : visibilityThreshold;
        Function1 finishedListener2 = (i & 16) != 0 ? null : finishedListener;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-846382129, $changed, -1, "androidx.compose.animation.core.animateValueAsState (AnimateAsState.kt:575)");
        }
        State animateValueAsState = animateValueAsState(targetValue, typeConverter, animationSpec2, visibilityThreshold2, "ValueAnimation", finishedListener2, $composer, ($changed & 8) | 24576 | ($changed & 14) | ($changed & 112) | ($changed & 896) | (($changed & 8) << 9) | ($changed & 7168) | (($changed << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateValueAsState;
    }
}
