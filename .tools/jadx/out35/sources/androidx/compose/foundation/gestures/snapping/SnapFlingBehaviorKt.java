package androidx.compose.foundation.gestures.snapping;

import androidx.autofill.HintConstants;
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import com.example.shellshot.data.LogRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* compiled from: SnapFlingBehavior.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0017\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0082\b\u001a\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001f\u001ak\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u0010$\u001a\u00020\b2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"0&2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001d2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00120(H\u0082@¢\u0006\u0002\u0010,\u001as\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u0010$\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"0&2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u001f2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00120(H\u0082@¢\u0006\u0002\u00100\u001ae\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\"052!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00120(H\u0082@¢\u0006\u0002\u00106\u001a\u0014\u00107\u001a\u00020\b*\u00020\b2\u0006\u00108\u001a\u00020\bH\u0002\u001a(\u00109\u001a\u0002H:\"\u000e\b\u0000\u0010:*\b\u0012\u0004\u0012\u0002H:0;*\b\u0012\u0004\u0012\u0002H:0<H\u0082\u0002¢\u0006\u0002\u0010=\u001a(\u0010>\u001a\u0002H:\"\u000e\b\u0000\u0010:*\b\u0012\u0004\u0012\u0002H:0;*\b\u0012\u0004\u0012\u0002H:0<H\u0082\u0002¢\u0006\u0002\u0010=\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006?"}, d2 = {"DEBUG", "", "MinFlingVelocityDp", "Landroidx/compose/ui/unit/Dp;", "getMinFlingVelocityDp", "()F", "F", "NoDistance", "", "NoVelocity", "calculateFinalOffset", "snappingOffset", "Landroidx/compose/foundation/gestures/snapping/FinalSnappingItem;", "lowerBound", "upperBound", "calculateFinalOffset-Fhqu1e0", "(IFF)F", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "rememberSnapFlingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "snapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "snapFlingBehavior", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "animateDecay", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/foundation/gestures/ScrollScope;", "targetOffset", "animationState", "Landroidx/compose/animation/core/AnimationState;", "onAnimationStep", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "delta", "(Landroidx/compose/foundation/gestures/ScrollScope;FLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateWithTarget", "cancelOffset", "animationSpec", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "approach", "initialTargetOffset", "initialVelocity", "animation", "Landroidx/compose/foundation/gestures/snapping/ApproachAnimation;", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/foundation/gestures/snapping/ApproachAnimation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceToTarget", "target", "component1", "T", "", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "component2", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SnapFlingBehaviorKt {
    private static final boolean DEBUG = false;
    private static final float MinFlingVelocityDp = Dp.m6626constructorimpl(LogRepository.MAX_PERSISTED_COUNT);
    public static final float NoDistance = 0.0f;
    public static final float NoVelocity = 0.0f;

    public static final TargetedFlingBehavior snapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec) {
        return new SnapFlingBehavior(snapLayoutInfoProvider, decayAnimationSpec, animationSpec);
    }

    public static final TargetedFlingBehavior rememberSnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1921733134, "C(rememberSnapFlingBehavior)257@10415L7,258@10485L26,259@10523L340:SnapFlingBehavior.kt#ppz6w6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1921733134, $changed, -1, "androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior (SnapFlingBehavior.kt:256)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density = (Density) consume;
        DecayAnimationSpec highVelocityApproachSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0);
        ComposerKt.sourceInformationMarkerStart($composer, 962717891, "CC(remember):SnapFlingBehavior.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(snapLayoutInfoProvider)) || ($changed & 6) == 4) | $composer.changed(highVelocityApproachSpec) | $composer.changed(density);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = snapFlingBehavior(snapLayoutInfoProvider, highVelocityApproachSpec, AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null));
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        TargetedFlingBehavior targetedFlingBehavior = (TargetedFlingBehavior) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return targetedFlingBehavior;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object approach(ScrollScope $this$approach, float initialTargetOffset, float initialVelocity, ApproachAnimation<Float, AnimationVector1D> approachAnimation, Function1<? super Float, Unit> function1, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        return approachAnimation.approachAnimation($this$approach, Boxing.boxFloat(initialTargetOffset), Boxing.boxFloat(initialVelocity), function1, continuation);
    }

    private static final <T extends Comparable<? super T>> T component1(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        return closedFloatingPointRange.getStart();
    }

    private static final <T extends Comparable<? super T>> T component2(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        return closedFloatingPointRange.getEndInclusive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateDecay(final androidx.compose.foundation.gestures.ScrollScope r6, final float r7, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r8, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r9, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r10, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3b;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            float r6 = r0.F$0
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            java.lang.Object r8 = r0.L$0
            androidx.compose.animation.core.AnimationState r8 = (androidx.compose.animation.core.AnimationState) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L71
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.jvm.internal.Ref$FloatRef r2 = new kotlin.jvm.internal.Ref$FloatRef
            r2.<init>()
            java.lang.Object r3 = r8.getVelocity()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            r4 = 1
            if (r3 != 0) goto L57
            r3 = r4
            goto L58
        L57:
            r3 = 0
        L58:
            r3 = r3 ^ r4
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2 r5 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2
            r5.<init>()
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            r0.L$0 = r8
            r0.L$1 = r2
            r0.F$0 = r7
            r0.label = r4
            java.lang.Object r6 = androidx.compose.animation.core.SuspendAnimationKt.animateDecay(r8, r9, r3, r5, r0)
            if (r6 != r1) goto L6f
            return r1
        L6f:
            r6 = r7
            r7 = r2
        L71:
            r9 = 0
            androidx.compose.foundation.gestures.snapping.AnimationResult r9 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r10 = r7.element
            float r10 = r6 - r10
            java.lang.Float r10 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r10)
            r9.<init>(r10, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateDecay(androidx.compose.foundation.gestures.ScrollScope, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.DecayAnimationSpec, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateDecay$consumeDelta(AnimationScope<Float, AnimationVector1D> animationScope, ScrollScope $this_animateDecay, Function1<? super Float, Unit> function1, float delta) {
        float consumed = $this_animateDecay.scrollBy(delta);
        function1.invoke(Float.valueOf(consumed));
        if (Math.abs(delta - consumed) > 0.5f) {
            animationScope.cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateWithTarget(final androidx.compose.foundation.gestures.ScrollScope r17, float r18, final float r19, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r20, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r21, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r22, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1 r1 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1 r1 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            switch(r3) {
                case 0: goto L44;
                case 1: goto L30;
                default: goto L26;
            }
        L26:
            r3 = r1
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            float r2 = r1.F$1
            float r3 = r1.F$0
            java.lang.Object r4 = r1.L$1
            kotlin.jvm.internal.Ref$FloatRef r4 = (kotlin.jvm.internal.Ref.FloatRef) r4
            java.lang.Object r5 = r1.L$0
            androidx.compose.animation.core.AnimationState r5 = (androidx.compose.animation.core.AnimationState) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r3
            r3 = r1
            r7 = r5
            goto La7
        L44:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r17
            r4 = r19
            r5 = r21
            r6 = r18
            r7 = r20
            r8 = r22
            kotlin.jvm.internal.Ref$FloatRef r9 = new kotlin.jvm.internal.Ref$FloatRef
            r9.<init>()
            java.lang.Object r10 = r7.getVelocity()
            java.lang.Number r10 = (java.lang.Number) r10
            float r10 = r10.floatValue()
            java.lang.Float r11 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            java.lang.Object r12 = r7.getVelocity()
            java.lang.Number r12 = (java.lang.Number) r12
            float r12 = r12.floatValue()
            r13 = 0
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            r13 = 1
            if (r12 != 0) goto L7a
            r12 = r13
            goto L7b
        L7a:
            r12 = 0
        L7b:
            r12 = r12 ^ r13
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$2 r14 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateWithTarget$2
            r14.<init>()
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14
            r1.L$0 = r7
            r1.L$1 = r9
            r1.F$0 = r6
            r1.F$1 = r10
            r1.label = r13
            r22 = r1
            r19 = r5
            r17 = r7
            r18 = r11
            r20 = r12
            r21 = r14
            java.lang.Object r1 = androidx.compose.animation.core.SuspendAnimationKt.animateTo(r17, r18, r19, r20, r21, r22)
            r5 = r17
            r3 = r22
            if (r1 != r2) goto La4
            return r2
        La4:
            r4 = r9
            r2 = r10
            r7 = r5
        La7:
            r1 = 0
            java.lang.Object r1 = r7.getVelocity()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            float r9 = coerceToTarget(r1, r2)
            androidx.compose.foundation.gestures.snapping.AnimationResult r1 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r5 = r4.element
            float r5 = r6 - r5
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r5)
            r15 = 29
            r16 = 0
            r8 = 0
            r10 = 0
            r12 = 0
            r14 = 0
            androidx.compose.animation.core.AnimationState r8 = androidx.compose.animation.core.AnimationStateKt.copy$default(r7, r8, r9, r10, r12, r14, r15, r16)
            r1.<init>(r5, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateWithTarget(androidx.compose.foundation.gestures.ScrollScope, float, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.AnimationSpec, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float $this$coerceToTarget, float target) {
        if (target == 0.0f) {
            return 0.0f;
        }
        return target > 0.0f ? RangesKt.coerceAtMost($this$coerceToTarget, target) : RangesKt.coerceAtLeast($this$coerceToTarget, target);
    }

    public static final float getMinFlingVelocityDp() {
        return MinFlingVelocityDp;
    }

    private static final boolean calculateFinalOffset_Fhqu1e0$isValidDistance(float $this$calculateFinalOffset_Fhqu1e0_u24isValidDistance) {
        if (!($this$calculateFinalOffset_Fhqu1e0_u24isValidDistance == Float.POSITIVE_INFINITY)) {
            if (!($this$calculateFinalOffset_Fhqu1e0_u24isValidDistance == Float.NEGATIVE_INFINITY)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x001b, code lost:
    
        if (java.lang.Math.abs(r5) <= java.lang.Math.abs(r4)) goto L10;
     */
    /* renamed from: calculateFinalOffset-Fhqu1e0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final float m518calculateFinalOffsetFhqu1e0(int r3, float r4, float r5) {
        /*
            r0 = 0
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r0 = r0.m515getClosestItembbeMdSM()
            boolean r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m511equalsimpl0(r3, r0)
            r1 = 0
            if (r0 == 0) goto L1f
            float r0 = java.lang.Math.abs(r5)
            float r2 = java.lang.Math.abs(r4)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L1e
            goto L2b
        L1e:
            goto L39
        L1f:
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r0 = r0.m516getNextItembbeMdSM()
            boolean r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m511equalsimpl0(r3, r0)
            if (r0 == 0) goto L2d
        L2b:
            r0 = r5
            goto L3c
        L2d:
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r0 = r0.m517getPreviousItembbeMdSM()
            boolean r0 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m511equalsimpl0(r3, r0)
            if (r0 == 0) goto L3b
        L39:
            r0 = r4
            goto L3c
        L3b:
            r0 = r1
        L3c:
            boolean r2 = calculateFinalOffset_Fhqu1e0$isValidDistance(r0)
            if (r2 == 0) goto L45
            r1 = r0
            goto L46
        L45:
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.m518calculateFinalOffsetFhqu1e0(int, float, float):float");
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
