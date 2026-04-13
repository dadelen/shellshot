package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* compiled from: LazyStaggeredGridBeyondBoundsModifier.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"rememberLazyStaggeredGridBeyondBoundsState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridBeyondBoundsModifierKt {
    public static final LazyLayoutBeyondBoundsState rememberLazyStaggeredGridBeyondBoundsState(LazyStaggeredGridState state, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -363070453, "C(rememberLazyStaggeredGridBeyondBoundsState)26@980L73:LazyStaggeredGridBeyondBoundsModifier.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-363070453, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridBeyondBoundsState (LazyStaggeredGridBeyondBoundsModifier.kt:25)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1392793503, "CC(remember):LazyStaggeredGridBeyondBoundsModifier.kt#9igjgp");
        boolean invalid$iv = $composer.changed(state);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyStaggeredGridBeyondBoundsState(state);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazyStaggeredGridBeyondBoundsState lazyStaggeredGridBeyondBoundsState = (LazyStaggeredGridBeyondBoundsState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyStaggeredGridBeyondBoundsState;
    }
}
