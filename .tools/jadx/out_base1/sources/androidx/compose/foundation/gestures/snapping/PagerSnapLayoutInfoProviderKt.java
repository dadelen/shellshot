package androidx.compose.foundation.gestures.snapping;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.pager.PageInfo;
import androidx.compose.foundation.pager.PagerLayoutInfo;
import androidx.compose.foundation.pager.PagerLayoutInfoKt;
import androidx.compose.foundation.pager.PagerSnapDistance;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: PagerSnapLayoutInfoProvider.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0000\u001a8\u0010\u0006\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0000\u001a\u0017\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0082\b\u001a\f\u0010\u0014\u001a\u00020\b*\u00020\u0003H\u0002\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0002\u001a\f\u0010\u0017\u001a\u00020\u0016*\u00020\u0003H\u0002¨\u0006\u0018"}, d2 = {"SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "calculateFinalSnappingBound", "Lkotlin/Function3;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "snapPositionalThreshold", "flingVelocity", "lowerBoundOffset", "upperBoundOffset", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "dragGestureDelta", "isLtrDragging", "", "isScrollingForward", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerSnapLayoutInfoProviderKt {
    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final PagerState pagerState, final PagerSnapDistance pagerSnapDistance, final Function3<? super Float, ? super Float, ? super Float, Float> function3) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.snapping.PagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1
            public final PagerLayoutInfo getLayoutInfo() {
                return PagerState.this.getLayoutInfo();
            }

            public final boolean isValidDistance(float $this$isValidDistance) {
                if (!($this$isValidDistance == Float.POSITIVE_INFINITY)) {
                    if (!($this$isValidDistance == Float.NEGATIVE_INFINITY)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapOffset(float velocity) {
                SnapPosition snapPosition = PagerState.this.getLayoutInfo().getSnapPosition();
                Pair<Float, Float> searchForSnappingBounds = searchForSnappingBounds(snapPosition);
                float lowerBoundOffset = searchForSnappingBounds.component1().floatValue();
                float upperBoundOffset = searchForSnappingBounds.component2().floatValue();
                float finalDistance = function3.invoke(Float.valueOf(velocity), Float.valueOf(lowerBoundOffset), Float.valueOf(upperBoundOffset)).floatValue();
                boolean z = true;
                if (!(finalDistance == lowerBoundOffset)) {
                    if (!(finalDistance == upperBoundOffset)) {
                        if (!(finalDistance == 0.0f)) {
                            z = false;
                        }
                    }
                }
                if (!z) {
                    throw new IllegalStateException(("Final Snapping Offset Should Be one of " + lowerBoundOffset + ", " + upperBoundOffset + " or 0.0").toString());
                }
                if (isValidDistance(finalDistance)) {
                    return finalDistance;
                }
                return 0.0f;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float velocity, float decayOffset) {
                int firstVisiblePage;
                float signum;
                int effectivePageSizePx = PagerState.this.getPageSize$foundation_release() + PagerState.this.getPageSpacing$foundation_release();
                if (effectivePageSizePx == 0) {
                    return 0.0f;
                }
                PagerState pagerState2 = PagerState.this;
                if (velocity < 0.0f) {
                    firstVisiblePage = pagerState2.getFirstVisiblePage() + 1;
                } else {
                    firstVisiblePage = pagerState2.getFirstVisiblePage();
                }
                int startPage = firstVisiblePage;
                float pagesInAnimationOffset = decayOffset / effectivePageSizePx;
                int $i$f$debugLog = (int) pagesInAnimationOffset;
                int targetPage = RangesKt.coerceIn($i$f$debugLog + startPage, 0, PagerState.this.getPageCount());
                int correctedTargetPage = RangesKt.coerceIn(pagerSnapDistance.calculateTargetPage(startPage, targetPage, velocity, PagerState.this.getPageSize$foundation_release(), PagerState.this.getPageSpacing$foundation_release()), 0, PagerState.this.getPageCount());
                int $i$f$debugLog2 = correctedTargetPage - startPage;
                int proposedFlingOffset = $i$f$debugLog2 * effectivePageSizePx;
                int $i$f$debugLog3 = Math.abs(proposedFlingOffset);
                int flingApproachOffsetPx = RangesKt.coerceAtLeast($i$f$debugLog3 - effectivePageSizePx, 0);
                if (flingApproachOffsetPx != 0) {
                    signum = flingApproachOffsetPx * Math.signum(velocity);
                } else {
                    signum = flingApproachOffsetPx;
                }
                return signum;
            }

            private final Pair<Float, Float> searchForSnappingBounds(SnapPosition snapPosition) {
                float dragGestureDelta;
                boolean isScrollingForward;
                boolean isScrollingForward2;
                float lowerBoundOffset = Float.NEGATIVE_INFINITY;
                float upperBoundOffset = Float.POSITIVE_INFINITY;
                List $this$fastForEach$iv = getLayoutInfo().getVisiblePagesInfo();
                PagerState pagerState2 = PagerState.this;
                int size = $this$fastForEach$iv.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    PageInfo page = (PageInfo) item$iv;
                    float offset = SnapPositionKt.calculateDistanceToDesiredSnapPosition(PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), getLayoutInfo().getPageSize(), page.getOffset(), page.getIndex(), snapPosition, pagerState2.getPageCount());
                    if (offset <= 0.0f && offset > lowerBoundOffset) {
                        lowerBoundOffset = offset;
                    }
                    if (offset >= 0.0f && offset < upperBoundOffset) {
                        upperBoundOffset = offset;
                    }
                }
                if (lowerBoundOffset == Float.NEGATIVE_INFINITY) {
                    lowerBoundOffset = upperBoundOffset;
                }
                if (upperBoundOffset == Float.POSITIVE_INFINITY) {
                    upperBoundOffset = lowerBoundOffset;
                }
                dragGestureDelta = PagerSnapLayoutInfoProviderKt.dragGestureDelta(PagerState.this);
                boolean isDragging = !(dragGestureDelta == 0.0f);
                if (!PagerState.this.getCanScrollForward()) {
                    upperBoundOffset = 0.0f;
                    if (isDragging) {
                        isScrollingForward2 = PagerSnapLayoutInfoProviderKt.isScrollingForward(PagerState.this);
                        if (isScrollingForward2) {
                            lowerBoundOffset = 0.0f;
                        }
                    }
                }
                if (!PagerState.this.getCanScrollBackward()) {
                    lowerBoundOffset = 0.0f;
                    if (isDragging) {
                        isScrollingForward = PagerSnapLayoutInfoProviderKt.isScrollingForward(PagerState.this);
                        if (!isScrollingForward) {
                            upperBoundOffset = 0.0f;
                        }
                    }
                }
                return TuplesKt.to(Float.valueOf(lowerBoundOffset), Float.valueOf(upperBoundOffset));
            }
        };
    }

    private static final boolean isLtrDragging(PagerState $this$isLtrDragging) {
        return dragGestureDelta($this$isLtrDragging) > 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isScrollingForward(PagerState $this$isScrollingForward) {
        boolean reverseScrollDirection = $this$isScrollingForward.getLayoutInfo().getReverseLayout();
        return (isLtrDragging($this$isScrollingForward) && reverseScrollDirection) || !(isLtrDragging($this$isScrollingForward) || reverseScrollDirection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float dragGestureDelta(PagerState $this$dragGestureDelta) {
        if ($this$dragGestureDelta.getLayoutInfo().getOrientation() == Orientation.Horizontal) {
            return Offset.m3922getXimpl($this$dragGestureDelta.m920getUpDownDifferenceF1C5BW0$foundation_release());
        }
        return Offset.m3923getYimpl($this$dragGestureDelta.m920getUpDownDifferenceF1C5BW0$foundation_release());
    }

    private static final void debugLog(Function0<String> function0) {
    }

    public static final float calculateFinalSnappingBound(PagerState pagerState, LayoutDirection layoutDirection, float snapPositionalThreshold, float flingVelocity, float lowerBoundOffset, float upperBoundOffset) {
        boolean isForward = pagerState.getLayoutInfo().getOrientation() == Orientation.Vertical ? isScrollingForward(pagerState) : layoutDirection == LayoutDirection.Ltr ? isScrollingForward(pagerState) : !isScrollingForward(pagerState);
        int pageSize = pagerState.getLayoutInfo().getPageSize();
        float offsetFromSnappedPosition = pageSize == 0 ? 0.0f : dragGestureDelta(pagerState) / pageSize;
        float offsetFromSnappedPositionOverflow = offsetFromSnappedPosition - ((int) offsetFromSnappedPosition);
        Density $this$calculateFinalSnappingBound_u24lambda_u241 = pagerState.getDensity();
        int finalSnappingItem = LazyListSnapLayoutInfoProviderKt.calculateFinalSnappingItem($this$calculateFinalSnappingBound_u24lambda_u241, flingVelocity);
        if (FinalSnappingItem.m511equalsimpl0(finalSnappingItem, FinalSnappingItem.INSTANCE.m515getClosestItembbeMdSM())) {
            return Math.abs(offsetFromSnappedPositionOverflow) <= snapPositionalThreshold ? upperBoundOffset : upperBoundOffset;
        }
        if (!FinalSnappingItem.m511equalsimpl0(finalSnappingItem, FinalSnappingItem.INSTANCE.m516getNextItembbeMdSM())) {
            if (!FinalSnappingItem.m511equalsimpl0(finalSnappingItem, FinalSnappingItem.INSTANCE.m517getPreviousItembbeMdSM())) {
                return 0.0f;
            }
            return lowerBoundOffset;
        }
    }
}
