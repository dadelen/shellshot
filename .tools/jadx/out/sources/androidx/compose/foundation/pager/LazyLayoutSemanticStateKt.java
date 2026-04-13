package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: LazyLayoutSemanticState.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"LazyLayoutSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/pager/PagerState;", "isVertical", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutSemanticStateKt {
    public static final LazyLayoutSemanticState LazyLayoutSemanticState(final PagerState state, final boolean isVertical) {
        return new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.pager.LazyLayoutSemanticStateKt$LazyLayoutSemanticState$1
            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public float getScrollOffset() {
                return PagerScrollPositionKt.currentAbsoluteScrollOffset(PagerState.this);
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public float getMaxScrollOffset() {
                return PagerStateKt.calculateNewMaxScrollOffset(PagerState.this.getLayoutInfo(), PagerState.this.getPageCount());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public Object scrollToItem(int index, Continuation<? super Unit> continuation) {
                Object scrollToPage$default = PagerState.scrollToPage$default(PagerState.this, index, 0.0f, continuation, 2, null);
                return scrollToPage$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollToPage$default : Unit.INSTANCE;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public CollectionInfo collectionInfo() {
                if (isVertical) {
                    return new CollectionInfo(PagerState.this.getPageCount(), 1);
                }
                return new CollectionInfo(1, PagerState.this.getPageCount());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getViewport() {
                Orientation orientation = PagerState.this.getLayoutInfo().getOrientation();
                Orientation orientation2 = Orientation.Vertical;
                PagerState pagerState = PagerState.this;
                if (orientation == orientation2) {
                    return IntSize.m6795getHeightimpl(pagerState.getLayoutInfo().mo911getViewportSizeYbymL2g());
                }
                return IntSize.m6796getWidthimpl(pagerState.getLayoutInfo().mo911getViewportSizeYbymL2g());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getContentPadding() {
                return PagerState.this.getLayoutInfo().getBeforeContentPadding() + PagerState.this.getLayoutInfo().getAfterContentPadding();
            }
        };
    }
}
