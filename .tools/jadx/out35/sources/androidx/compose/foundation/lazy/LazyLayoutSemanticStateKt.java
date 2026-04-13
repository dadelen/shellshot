package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: LazyLayoutSemanticState.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"LazyLayoutSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "isVertical", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutSemanticStateKt {
    public static final LazyLayoutSemanticState LazyLayoutSemanticState(final LazyListState state, final boolean isVertical) {
        return new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.lazy.LazyLayoutSemanticStateKt$LazyLayoutSemanticState$1
            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public float getScrollOffset() {
                return LazyLayoutSemanticsKt.estimatedLazyScrollOffset(LazyListState.this.getFirstVisibleItemIndex(), LazyListState.this.getFirstVisibleItemScrollOffset());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public float getMaxScrollOffset() {
                return LazyLayoutSemanticsKt.estimatedLazyMaxScrollOffset(LazyListState.this.getFirstVisibleItemIndex(), LazyListState.this.getFirstVisibleItemScrollOffset(), LazyListState.this.getCanScrollForward());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public Object scrollToItem(int index, Continuation<? super Unit> continuation) {
                Object scrollToItem$default = LazyListState.scrollToItem$default(LazyListState.this, index, 0, continuation, 2, null);
                return scrollToItem$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollToItem$default : Unit.INSTANCE;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public CollectionInfo collectionInfo() {
                if (isVertical) {
                    return new CollectionInfo(-1, 1);
                }
                return new CollectionInfo(1, -1);
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getViewport() {
                Orientation orientation = LazyListState.this.getLayoutInfo().getOrientation();
                Orientation orientation2 = Orientation.Vertical;
                LazyListState lazyListState = LazyListState.this;
                if (orientation == orientation2) {
                    return IntSize.m6795getHeightimpl(lazyListState.getLayoutInfo().mo777getViewportSizeYbymL2g());
                }
                return IntSize.m6796getWidthimpl(lazyListState.getLayoutInfo().mo777getViewportSizeYbymL2g());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getContentPadding() {
                return LazyListState.this.getLayoutInfo().getBeforeContentPadding() + LazyListState.this.getLayoutInfo().getAfterContentPadding();
            }
        };
    }
}
