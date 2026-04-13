package androidx.compose.material3.carousel;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.pager.PagerDefaults;
import androidx.compose.foundation.pager.PagerSnapDistance;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: Carousel.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015H\u0007¢\u0006\u0002\u0010\u0016J\r\u0010\u0017\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0018J%\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015H\u0007¢\u0006\u0002\u0010\u001aR\u001c\u0010\u0003\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/carousel/CarouselDefaults;", "", "()V", "AnchorSize", "Landroidx/compose/ui/unit/Dp;", "getAnchorSize-D9Ej5fM$material3_release", "()F", "F", "MaxSmallItemSize", "getMaxSmallItemSize-D9Ej5fM$material3_release", "MediumLargeItemDiffThreshold", "", "MinSmallItemSize", "getMinSmallItemSize-D9Ej5fM$material3_release", "multiBrowseFlingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "state", "Landroidx/compose/material3/carousel/CarouselState;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "noSnapFlingBehavior", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "singleAdvanceFlingBehavior", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CarouselDefaults {
    public static final int $stable = 0;
    public static final float MediumLargeItemDiffThreshold = 0.85f;
    public static final CarouselDefaults INSTANCE = new CarouselDefaults();
    private static final float MinSmallItemSize = Dp.m6626constructorimpl(40);
    private static final float MaxSmallItemSize = Dp.m6626constructorimpl(56);
    private static final float AnchorSize = Dp.m6626constructorimpl(10);

    private CarouselDefaults() {
    }

    public final TargetedFlingBehavior singleAdvanceFlingBehavior(CarouselState state, AnimationSpec<Float> animationSpec, Composer $composer, int $changed, int i) {
        AnimationSpec snapAnimationSpec;
        ComposerKt.sourceInformationMarkerStart($composer, 1701587199, "C(singleAdvanceFlingBehavior)P(1)612@27409L174:Carousel.kt#dcf9yb");
        if ((i & 2) != 0) {
            AnimationSpec snapAnimationSpec2 = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
            snapAnimationSpec = snapAnimationSpec2;
        } else {
            snapAnimationSpec = animationSpec;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1701587199, $changed, -1, "androidx.compose.material3.carousel.CarouselDefaults.singleAdvanceFlingBehavior (Carousel.kt:611)");
        }
        TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(state.getPagerState(), PagerSnapDistance.INSTANCE.atMost(1), null, snapAnimationSpec, 0.0f, $composer, (($changed << 6) & 7168) | (PagerDefaults.$stable << 15), 20);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return flingBehavior;
    }

    public final TargetedFlingBehavior multiBrowseFlingBehavior(CarouselState state, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Composer $composer, int $changed, int i) {
        DecayAnimationSpec decayAnimationSpec2;
        AnimationSpec snapAnimationSpec;
        ComposerKt.sourceInformationMarkerStart($composer, -1105043293, "C(multiBrowseFlingBehavior)P(2)642@29119L26,657@29722L217:Carousel.kt#dcf9yb");
        if ((i & 2) != 0) {
            DecayAnimationSpec decayAnimationSpec3 = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0);
            decayAnimationSpec2 = decayAnimationSpec3;
        } else {
            decayAnimationSpec2 = decayAnimationSpec;
        }
        if ((i & 4) != 0) {
            AnimationSpec snapAnimationSpec2 = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
            snapAnimationSpec = snapAnimationSpec2;
        } else {
            snapAnimationSpec = animationSpec;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1105043293, $changed, -1, "androidx.compose.material3.carousel.CarouselDefaults.multiBrowseFlingBehavior (Carousel.kt:644)");
        }
        TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(state.getPagerState(), new PagerSnapDistance() { // from class: androidx.compose.material3.carousel.CarouselDefaults$multiBrowseFlingBehavior$pagerSnapDistance$1
            @Override // androidx.compose.foundation.pager.PagerSnapDistance
            public int calculateTargetPage(int startPage, int suggestedTargetPage, float velocity, int pageSize, int pageSpacing) {
                return suggestedTargetPage;
            }
        }, decayAnimationSpec2, snapAnimationSpec, 0.0f, $composer, (($changed << 3) & 896) | (($changed << 3) & 7168) | (PagerDefaults.$stable << 15), 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return flingBehavior;
    }

    public final TargetedFlingBehavior noSnapFlingBehavior(Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -418415756, "C(noSnapFlingBehavior)674@30353L158,680@30528L75:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-418415756, $changed, -1, "androidx.compose.material3.carousel.CarouselDefaults.noSnapFlingBehavior (Carousel.kt:673)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1269150096, "CC(remember):Carousel.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new SnapLayoutInfoProvider() { // from class: androidx.compose.material3.carousel.CarouselDefaults$noSnapFlingBehavior$decayLayoutInfoProvider$1$1
                @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
                public float calculateSnapOffset(float velocity) {
                    return 0.0f;
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        CarouselDefaults$noSnapFlingBehavior$decayLayoutInfoProvider$1$1 decayLayoutInfoProvider = (CarouselDefaults$noSnapFlingBehavior$decayLayoutInfoProvider$1$1) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        TargetedFlingBehavior rememberSnapFlingBehavior = SnapFlingBehaviorKt.rememberSnapFlingBehavior(decayLayoutInfoProvider, $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return rememberSnapFlingBehavior;
    }

    /* renamed from: getMinSmallItemSize-D9Ej5fM$material3_release, reason: not valid java name */
    public final float m2885getMinSmallItemSizeD9Ej5fM$material3_release() {
        return MinSmallItemSize;
    }

    /* renamed from: getMaxSmallItemSize-D9Ej5fM$material3_release, reason: not valid java name */
    public final float m2884getMaxSmallItemSizeD9Ej5fM$material3_release() {
        return MaxSmallItemSize;
    }

    /* renamed from: getAnchorSize-D9Ej5fM$material3_release, reason: not valid java name */
    public final float m2883getAnchorSizeD9Ej5fM$material3_release() {
        return AnchorSize;
    }
}
