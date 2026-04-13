package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: LazySemantics.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberLazyGridSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "reverseScrolling", "", "(Landroidx/compose/foundation/lazy/grid/LazyGridState;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazySemanticsKt {
    public static final LazyLayoutSemanticState rememberLazyGridSemanticState(final LazyGridState state, boolean reverseScrolling, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1247008005, "C(rememberLazyGridSemanticState)P(1)33@1324L1347:LazySemantics.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1247008005, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridSemanticState (LazySemantics.kt:33)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 488757183, "CC(remember):LazySemantics.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(reverseScrolling)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.lazy.grid.LazySemanticsKt$rememberLazyGridSemanticState$1$1
                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public float getScrollOffset() {
                    return LazyLayoutSemanticsKt.estimatedLazyScrollOffset(LazyGridState.this.getFirstVisibleItemIndex(), LazyGridState.this.getFirstVisibleItemScrollOffset());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public float getMaxScrollOffset() {
                    return LazyLayoutSemanticsKt.estimatedLazyMaxScrollOffset(LazyGridState.this.getFirstVisibleItemIndex(), LazyGridState.this.getFirstVisibleItemScrollOffset(), LazyGridState.this.getCanScrollForward());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public Object scrollToItem(int index, Continuation<? super Unit> continuation) {
                    Object scrollToItem$default = LazyGridState.scrollToItem$default(LazyGridState.this, index, 0, continuation, 2, null);
                    return scrollToItem$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollToItem$default : Unit.INSTANCE;
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public CollectionInfo collectionInfo() {
                    return new CollectionInfo(-1, -1);
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getViewport() {
                    Orientation orientation = LazyGridState.this.getLayoutInfo().getOrientation();
                    Orientation orientation2 = Orientation.Vertical;
                    LazyGridState lazyGridState = LazyGridState.this;
                    if (orientation == orientation2) {
                        return IntSize.m6795getHeightimpl(lazyGridState.getLayoutInfo().mo812getViewportSizeYbymL2g());
                    }
                    return IntSize.m6796getWidthimpl(lazyGridState.getLayoutInfo().mo812getViewportSizeYbymL2g());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getContentPadding() {
                    return LazyGridState.this.getLayoutInfo().getBeforeContentPadding() + LazyGridState.this.getLayoutInfo().getAfterContentPadding();
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazySemanticsKt$rememberLazyGridSemanticState$1$1 lazySemanticsKt$rememberLazyGridSemanticState$1$1 = (LazySemanticsKt$rememberLazyGridSemanticState$1$1) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazySemanticsKt$rememberLazyGridSemanticState$1$1;
    }
}
