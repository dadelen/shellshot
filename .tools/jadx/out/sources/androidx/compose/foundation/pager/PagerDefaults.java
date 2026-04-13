package androidx.compose.foundation.pager;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.PagerSnapLayoutInfoProviderKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.IntCompanionObject;

/* compiled from: Pager.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JI\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\b\b\u0003\u0010\u0010\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/pager/PagerDefaults;", "", "()V", "BeyondViewportPageCount", "", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "state", "Landroidx/compose/foundation/pager/PagerState;", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "snapPositionalThreshold", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/pager/PagerSnapDistance;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;FLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerDefaults {
    public static final int $stable = 0;
    public static final int BeyondViewportPageCount = 0;
    public static final PagerDefaults INSTANCE = new PagerDefaults();

    private PagerDefaults() {
    }

    public final TargetedFlingBehavior flingBehavior(final PagerState state, PagerSnapDistance pagerSnapDistance, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, float snapPositionalThreshold, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1559769181, "C(flingBehavior)P(4,1)295@16853L26,306@17415L7,307@17474L7,308@17497L1053:Pager.kt#g6yjnt");
        boolean z = true;
        PagerSnapDistance pagerSnapDistance2 = (i & 2) != 0 ? PagerSnapDistance.INSTANCE.atMost(1) : pagerSnapDistance;
        DecayAnimationSpec decayAnimationSpec2 = (i & 4) != 0 ? SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0) : decayAnimationSpec;
        AnimationSpec snapAnimationSpec = (i & 8) != 0 ? AnimationSpecKt.spring$default(0.0f, 400.0f, Float.valueOf(VisibilityThresholdsKt.getVisibilityThreshold(IntCompanionObject.INSTANCE)), 1, null) : animationSpec;
        final float snapPositionalThreshold2 = (i & 16) != 0 ? 0.5f : snapPositionalThreshold;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1559769181, $changed, -1, "androidx.compose.foundation.pager.PagerDefaults.flingBehavior (Pager.kt:301)");
        }
        if (!(0.0f <= snapPositionalThreshold2 && snapPositionalThreshold2 <= 1.0f)) {
            throw new IllegalArgumentException(("snapPositionalThreshold should be a number between 0 and 1. You've specified " + snapPositionalThreshold2).toString());
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density = (Density) consume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final LayoutDirection layoutDirection = (LayoutDirection) consume2;
        ComposerKt.sourceInformationMarkerStart($composer, -1107496563, "CC(remember):Pager.kt#9igjgp");
        boolean changed = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | $composer.changed(decayAnimationSpec2) | $composer.changed(snapAnimationSpec);
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(pagerSnapDistance2)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = z | changed | $composer.changed(density) | $composer.changed(layoutDirection);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            SnapLayoutInfoProvider snapLayoutInfoProvider = PagerSnapLayoutInfoProviderKt.SnapLayoutInfoProvider(state, pagerSnapDistance2, new Function3<Float, Float, Float, Float>() { // from class: androidx.compose.foundation.pager.PagerDefaults$flingBehavior$2$snapLayoutInfoProvider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Float invoke(Float f, Float f2, Float f3) {
                    return invoke(f.floatValue(), f2.floatValue(), f3.floatValue());
                }

                public final Float invoke(float flingVelocity, float lowerBound, float upperBound) {
                    return Float.valueOf(PagerSnapLayoutInfoProviderKt.calculateFinalSnappingBound(PagerState.this, layoutDirection, snapPositionalThreshold2, flingVelocity, lowerBound, upperBound));
                }
            });
            value$iv = SnapFlingBehaviorKt.snapFlingBehavior(snapLayoutInfoProvider, decayAnimationSpec2, snapAnimationSpec);
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

    public final NestedScrollConnection pageNestedScrollConnection(PagerState state, Orientation orientation, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 877583120, "C(pageNestedScrollConnection)P(1)351@19018L107:Pager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(877583120, $changed, -1, "androidx.compose.foundation.pager.PagerDefaults.pageNestedScrollConnection (Pager.kt:350)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1511248710, "CC(remember):Pager.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(orientation)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new DefaultPagerNestedScrollConnection(state, orientation);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        DefaultPagerNestedScrollConnection defaultPagerNestedScrollConnection = (DefaultPagerNestedScrollConnection) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultPagerNestedScrollConnection;
    }
}
