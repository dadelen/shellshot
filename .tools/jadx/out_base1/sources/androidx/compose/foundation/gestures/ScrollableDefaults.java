package androidx.compose.foundation.gestures;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.AndroidOverscroll_androidKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: Scrollable.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableDefaults;", "", "()V", "bringIntoViewSpec", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/FlingBehavior;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/OverscrollEffect;", "reverseDirection", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseScrolling", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScrollableDefaults {
    public static final int $stable = 0;
    public static final ScrollableDefaults INSTANCE = new ScrollableDefaults();

    private ScrollableDefaults() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FlingBehavior flingBehavior(Composer composer, int i) {
        Object defaultFlingBehavior;
        ComposerKt.sourceInformationMarkerStart(composer, 1107739818, "C(flingBehavior)571@22168L33,572@22217L75:Scrollable.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1107739818, i, -1, "androidx.compose.foundation.gestures.ScrollableDefaults.flingBehavior (Scrollable.kt:570)");
        }
        DecayAnimationSpec rememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        ComposerKt.sourceInformationMarkerStart(composer, -480380690, "CC(remember):Scrollable.kt#9igjgp");
        boolean changed = composer.changed(rememberSplineBasedDecay);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            defaultFlingBehavior = new DefaultFlingBehavior(rememberSplineBasedDecay, null, 2, 0 == true ? 1 : 0);
            composer.updateRememberedValue(defaultFlingBehavior);
        } else {
            defaultFlingBehavior = rememberedValue;
        }
        DefaultFlingBehavior defaultFlingBehavior2 = (DefaultFlingBehavior) defaultFlingBehavior;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultFlingBehavior2;
    }

    public final OverscrollEffect overscrollEffect(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1809802212, "C(overscrollEffect)584@22537L26:Scrollable.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1809802212, $changed, -1, "androidx.compose.foundation.gestures.ScrollableDefaults.overscrollEffect (Scrollable.kt:583)");
        }
        OverscrollEffect rememberOverscrollEffect = AndroidOverscroll_androidKt.rememberOverscrollEffect($composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return rememberOverscrollEffect;
    }

    public final boolean reverseDirection(LayoutDirection layoutDirection, Orientation orientation, boolean reverseScrolling) {
        boolean reverseDirection = !reverseScrolling;
        boolean isRtl = layoutDirection == LayoutDirection.Rtl;
        if (isRtl && orientation != Orientation.Vertical) {
            return reverseDirection ? false : true;
        }
        return reverseDirection;
    }

    @Deprecated(message = "This has been replaced by composition locals LocalBringIntoViewSpec", replaceWith = @ReplaceWith(expression = "LocalBringIntoView.current", imports = {"androidx.compose.foundation.gestures.LocalBringIntoViewSpec"}))
    public final BringIntoViewSpec bringIntoViewSpec() {
        return BringIntoViewSpec.INSTANCE.getDefaultBringIntoViewSpec$foundation_release();
    }
}
