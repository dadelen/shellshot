package androidx.compose.foundation.lazy.staggeredgrid;

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

/* compiled from: LazyStaggeredGridSemantics.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberLazyStaggeredGridSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "reverseScrolling", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridSemanticsKt {
    public static final LazyLayoutSemanticState rememberLazyStaggeredGridSemanticState(final LazyStaggeredGridState state, boolean reverseScrolling, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1629354903, "C(rememberLazyStaggeredGridSemanticState)P(1)33@1351L1223:LazyStaggeredGridSemantics.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1629354903, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridSemanticState (LazyStaggeredGridSemantics.kt:33)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 518757981, "CC(remember):LazyStaggeredGridSemantics.kt#9igjgp");
        boolean invalid$iv = $composer.changed(state) | (((($changed & 112) ^ 48) > 32 && $composer.changed(reverseScrolling)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1
                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public float getScrollOffset() {
                    return LazyLayoutSemanticsKt.estimatedLazyScrollOffset(LazyStaggeredGridState.this.getFirstVisibleItemIndex(), LazyStaggeredGridState.this.getFirstVisibleItemScrollOffset());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public float getMaxScrollOffset() {
                    return LazyLayoutSemanticsKt.estimatedLazyMaxScrollOffset(LazyStaggeredGridState.this.getFirstVisibleItemIndex(), LazyStaggeredGridState.this.getFirstVisibleItemScrollOffset(), LazyStaggeredGridState.this.getCanScrollForward());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public Object scrollToItem(int index, Continuation<? super Unit> continuation) {
                    Object scrollToItem$default = LazyStaggeredGridState.scrollToItem$default(LazyStaggeredGridState.this, index, 0, continuation, 2, null);
                    return scrollToItem$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollToItem$default : Unit.INSTANCE;
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public CollectionInfo collectionInfo() {
                    return new CollectionInfo(-1, -1);
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getViewport() {
                    Orientation orientation = LazyStaggeredGridState.this.getLayoutInfo().getOrientation();
                    Orientation orientation2 = Orientation.Vertical;
                    LazyStaggeredGridState lazyStaggeredGridState = LazyStaggeredGridState.this;
                    if (orientation == orientation2) {
                        return IntSize.m6795getHeightimpl(lazyStaggeredGridState.getLayoutInfo().getViewportSize());
                    }
                    return IntSize.m6796getWidthimpl(lazyStaggeredGridState.getLayoutInfo().getViewportSize());
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getContentPadding() {
                    return LazyStaggeredGridState.this.getLayoutInfo().getBeforeContentPadding() + LazyStaggeredGridState.this.getLayoutInfo().getAfterContentPadding();
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1 lazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1 = (LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1;
    }
}
