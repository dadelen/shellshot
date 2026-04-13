package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: LazyStaggeredGridMeasureResult.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"EmptyArray", "", "EmptyLazyStaggeredGridLayoutInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "getEmptyLazyStaggeredGridLayoutInfo$annotations", "()V", "getEmptyLazyStaggeredGridLayoutInfo", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "findVisibleItem", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "itemIndex", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridMeasureResultKt {
    private static final int[] EmptyArray = new int[0];
    private static final LazyStaggeredGridMeasureResult EmptyLazyStaggeredGridLayoutInfo = new LazyStaggeredGridMeasureResult(EmptyArray, EmptyArray, 0.0f, new MeasureResult() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt$EmptyLazyStaggeredGridLayoutInfo$1
        private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
        private final int height;
        private final int width;

        public static /* synthetic */ void getAlignmentLines$annotations() {
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getWidth() {
            return this.width;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getHeight() {
            return this.height;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public Map<AlignmentLine, Integer> getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public void placeChildren() {
        }
    }, false, false, false, new LazyStaggeredGridSlots(EmptyArray, EmptyArray), new LazyStaggeredGridSpanProvider(new MutableIntervalList()), DensityKt.Density$default(1.0f, 0.0f, 2, null), 0, CollectionsKt.emptyList(), IntSize.INSTANCE.m6801getZeroYbymL2g(), 0, 0, 0, 0, 0, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), null);

    public static /* synthetic */ void getEmptyLazyStaggeredGridLayoutInfo$annotations() {
    }

    public static final LazyStaggeredGridItemInfo findVisibleItem(LazyStaggeredGridLayoutInfo $this$findVisibleItem, final int itemIndex) {
        if ($this$findVisibleItem.getVisibleItemsInfo().isEmpty()) {
            return null;
        }
        int index = ((LazyStaggeredGridItemInfo) CollectionsKt.first((List) $this$findVisibleItem.getVisibleItemsInfo())).getIndex();
        boolean z = false;
        if (itemIndex <= ((LazyStaggeredGridItemInfo) CollectionsKt.last((List) $this$findVisibleItem.getVisibleItemsInfo())).getIndex() && index <= itemIndex) {
            z = true;
        }
        if (!z) {
            return null;
        }
        int index2 = CollectionsKt.binarySearch$default($this$findVisibleItem.getVisibleItemsInfo(), 0, 0, new Function1<LazyStaggeredGridItemInfo, Integer>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt$findVisibleItem$index$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(LazyStaggeredGridItemInfo it) {
                return Integer.valueOf(it.getIndex() - itemIndex);
            }
        }, 3, (Object) null);
        return (LazyStaggeredGridItemInfo) CollectionsKt.getOrNull($this$findVisibleItem.getVisibleItemsInfo(), index2);
    }

    public static final LazyStaggeredGridMeasureResult getEmptyLazyStaggeredGridLayoutInfo() {
        return EmptyLazyStaggeredGridLayoutInfo;
    }
}
