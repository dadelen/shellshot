package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* compiled from: PagerBeyondBoundsModifier.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberPagerBeyondBoundsState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "state", "Landroidx/compose/foundation/pager/PagerState;", "beyondViewportPageCount", "", "(Landroidx/compose/foundation/pager/PagerState;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerBeyondBoundsModifierKt {
    public static final LazyLayoutBeyondBoundsState rememberPagerBeyondBoundsState(PagerState state, int beyondViewportPageCount, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 373558254, "C(rememberPagerBeyondBoundsState)P(1)26@976L111:PagerBeyondBoundsModifier.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(373558254, $changed, -1, "androidx.compose.foundation.pager.rememberPagerBeyondBoundsState (PagerBeyondBoundsModifier.kt:25)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1074814635, "CC(remember):PagerBeyondBoundsModifier.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(beyondViewportPageCount)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new PagerBeyondBoundsState(state, beyondViewportPageCount);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        PagerBeyondBoundsState pagerBeyondBoundsState = (PagerBeyondBoundsState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return pagerBeyondBoundsState;
    }
}
