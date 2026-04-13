package androidx.compose.material3.carousel;

import androidx.compose.foundation.gestures.snapping.SnapPosition;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* compiled from: KeylineSnapPosition.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0000¨\u0006\n"}, d2 = {"KeylineSnapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageSize", "Landroidx/compose/material3/carousel/CarouselPageSize;", "getSnapPositionOffset", "", "strategy", "Landroidx/compose/material3/carousel/Strategy;", "itemIndex", "itemCount", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class KeylineSnapPositionKt {
    public static final int getSnapPositionOffset(Strategy strategy, int itemIndex, int itemCount) {
        if (!strategy.getIsValid()) {
            return 0;
        }
        int numOfFocalKeylines = strategy.getDefaultKeylines().getLastFocalIndex() - strategy.getDefaultKeylines().getFirstFocalIndex();
        int startStepsSize = strategy.getStartKeylineSteps().size() + numOfFocalKeylines;
        int endStepsSize = strategy.getEndKeylineSteps().size() + numOfFocalKeylines;
        int offset = MathKt.roundToInt(strategy.getDefaultKeylines().getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
        if (itemIndex < startStepsSize) {
            int startIndex = Math.max(0, (startStepsSize - 1) - itemIndex);
            KeylineList startKeylines = strategy.getStartKeylineSteps().get(Math.min(strategy.getStartKeylineSteps().size() - 1, startIndex));
            offset = MathKt.roundToInt(startKeylines.getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
        }
        int startIndex2 = numOfFocalKeylines + 1;
        if (itemCount > startIndex2 && itemIndex >= itemCount - endStepsSize) {
            int endIndex = Math.max(0, (itemIndex - itemCount) + endStepsSize);
            KeylineList endKeylines = strategy.getEndKeylineSteps().get(Math.min(strategy.getEndKeylineSteps().size() - 1, endIndex));
            int offset2 = MathKt.roundToInt(endKeylines.getFirstFocal().getUnadjustedOffset() - (strategy.getItemMainAxisSize() / 2.0f));
            return offset2;
        }
        return offset;
    }

    public static final SnapPosition KeylineSnapPosition(final CarouselPageSize pageSize) {
        return new SnapPosition() { // from class: androidx.compose.material3.carousel.KeylineSnapPositionKt$KeylineSnapPosition$1
            @Override // androidx.compose.foundation.gestures.snapping.SnapPosition
            public int position(int layoutSize, int itemSize, int beforeContentPadding, int afterContentPadding, int itemIndex, int itemCount) {
                return KeylineSnapPositionKt.getSnapPositionOffset(CarouselPageSize.this.getStrategy(), itemIndex, itemCount);
            }
        };
    }
}
