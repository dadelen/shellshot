package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* compiled from: LazyGridBeyondBoundsModifier.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"rememberLazyGridBeyondBoundsState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "(Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridBeyondBoundsModifierKt {
    public static final LazyLayoutBeyondBoundsState rememberLazyGridBeyondBoundsState(LazyGridState state, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2004349821, "C(rememberLazyGridBeyondBoundsState)24@947L64:LazyGridBeyondBoundsModifier.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2004349821, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridBeyondBoundsState (LazyGridBeyondBoundsModifier.kt:23)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -242332290, "CC(remember):LazyGridBeyondBoundsModifier.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyGridBeyondBoundsState(state);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazyGridBeyondBoundsState lazyGridBeyondBoundsState = (LazyGridBeyondBoundsState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyGridBeyondBoundsState;
    }
}
