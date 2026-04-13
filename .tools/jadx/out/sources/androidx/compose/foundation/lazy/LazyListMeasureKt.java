package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: LazyListMeasure.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\\\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u001a4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001a£\u0002\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0002012\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u00102\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010!2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092/\u0010:\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<¢\u0006\u0002\b?\u0012\u0004\u0012\u00020@0;H\u0000ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "items", "", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "consumedScroll", "", "isLookingAhead", "lastPostLookaheadLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "createItemsBeforeList", "currentFirstItemIndex", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "hasLookaheadPassOccurred", "postLookaheadLayoutInfo", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-x0Ok8Vo", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListMeasureKt {
    /* JADX WARN: Removed duplicated region for block: B:127:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0490  */
    /* renamed from: measureLazyList-x0Ok8Vo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.lazy.LazyListMeasureResult m779measureLazyListx0Ok8Vo(int r51, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider r52, int r53, int r54, int r55, int r56, int r57, int r58, float r59, long r60, boolean r62, java.util.List<java.lang.Integer> r63, androidx.compose.foundation.layout.Arrangement.Vertical r64, androidx.compose.foundation.layout.Arrangement.Horizontal r65, boolean r66, androidx.compose.ui.unit.Density r67, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator<androidx.compose.foundation.lazy.LazyListMeasuredItem> r68, int r69, java.util.List<java.lang.Integer> r70, boolean r71, final boolean r72, androidx.compose.foundation.lazy.LazyListLayoutInfo r73, kotlinx.coroutines.CoroutineScope r74, final androidx.compose.runtime.MutableState<kotlin.Unit> r75, androidx.compose.ui.graphics.GraphicsContext r76, kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Integer, ? super kotlin.jvm.functions.Function1<? super androidx.compose.ui.layout.Placeable.PlacementScope, kotlin.Unit>, ? extends androidx.compose.ui.layout.MeasureResult> r77) {
        /*
            Method dump skipped, instructions count: 1482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListMeasureKt.m779measureLazyListx0Ok8Vo(int, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, java.util.List, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator, int, java.util.List, boolean, boolean, androidx.compose.foundation.lazy.LazyListLayoutInfo, kotlinx.coroutines.CoroutineScope, androidx.compose.runtime.MutableState, androidx.compose.ui.graphics.GraphicsContext, kotlin.jvm.functions.Function3):androidx.compose.foundation.lazy.LazyListMeasureResult");
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider measuredItemProvider, int itemsCount, int beyondBoundsItemCount, List<Integer> list2, float consumedScroll, boolean isLookingAhead, LazyListLayoutInfo lastPostLookaheadLayoutInfo) {
        boolean z;
        LazyListMeasuredItem item;
        Object it$iv;
        Object it$iv2;
        LazyListMeasuredItem lazyListMeasuredItem;
        Object it$iv3;
        List list3 = null;
        int end = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + beyondBoundsItemCount, itemsCount - 1);
        boolean z2 = true;
        int i = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (i <= end) {
            int i2 = i;
            while (true) {
                if (list3 == null) {
                    Object list4 = new ArrayList();
                    list3 = (List) list4;
                }
                list3.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, i2, 0L, 2, null));
                if (i2 == end) {
                    break;
                }
                i2++;
            }
        }
        if (isLookingAhead && lastPostLookaheadLayoutInfo != null && !lastPostLookaheadLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List $this$createItemsAfterList_u24lambda_u247 = lastPostLookaheadLayoutInfo.getVisibleItemsInfo();
            LazyListItemInfo found = null;
            for (int i3 = $this$createItemsAfterList_u24lambda_u247.size() - 1; -1 < i3; i3--) {
                if ($this$createItemsAfterList_u24lambda_u247.get(i3).getIndex() > end && (i3 == 0 || $this$createItemsAfterList_u24lambda_u247.get(i3 - 1).getIndex() <= end)) {
                    LazyListItemInfo found2 = $this$createItemsAfterList_u24lambda_u247.get(i3);
                    found = found2;
                    break;
                }
            }
            LazyListItemInfo lastVisibleItem = (LazyListItemInfo) CollectionsKt.last((List) lastPostLookaheadLayoutInfo.getVisibleItemsInfo());
            if (found == null) {
                z = true;
            } else {
                int i4 = found.getIndex();
                int min = Math.min(lastVisibleItem.getIndex(), itemsCount - 1);
                if (i4 <= min) {
                    int i5 = i4;
                    while (true) {
                        if (list3 != null) {
                            List $this$fastFirstOrNull$iv = list3;
                            int index$iv$iv = 0;
                            int size = $this$fastFirstOrNull$iv.size();
                            while (true) {
                                if (index$iv$iv < size) {
                                    Object item$iv$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
                                    it$iv3 = item$iv$iv;
                                    z = z2;
                                    if (((LazyListMeasuredItem) it$iv3).getIndex() == i5 ? z : false) {
                                        break;
                                    }
                                    index$iv$iv++;
                                    z2 = z;
                                } else {
                                    z = z2;
                                    it$iv3 = null;
                                    break;
                                }
                            }
                            lazyListMeasuredItem = (LazyListMeasuredItem) it$iv3;
                        } else {
                            z = z2;
                            lazyListMeasuredItem = null;
                        }
                        if (lazyListMeasuredItem == null) {
                            if (list3 == null) {
                                Object list5 = new ArrayList();
                                list3 = (List) list5;
                            }
                            list3.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, i5, 0L, 2, null));
                        }
                        if (i5 == min) {
                            break;
                        }
                        i5++;
                        z2 = z;
                    }
                } else {
                    z = true;
                }
            }
            float additionalOffset = ((lastPostLookaheadLayoutInfo.getViewportEndOffset() - lastVisibleItem.getOffset()) - lastVisibleItem.getSize()) - consumedScroll;
            if (additionalOffset > 0.0f) {
                int index = lastVisibleItem.getIndex() + 1;
                int totalOffset = 0;
                while (index < itemsCount && totalOffset < additionalOffset) {
                    if (index > end) {
                        if (list3 == null) {
                            item = null;
                        } else {
                            List $this$fastFirstOrNull$iv2 = list3;
                            int index$iv$iv2 = 0;
                            int size2 = $this$fastFirstOrNull$iv2.size();
                            while (true) {
                                if (index$iv$iv2 < size2) {
                                    Object item$iv$iv2 = $this$fastFirstOrNull$iv2.get(index$iv$iv2);
                                    it$iv = item$iv$iv2;
                                    if (((LazyListMeasuredItem) it$iv).getIndex() == index ? z : false) {
                                        break;
                                    }
                                    index$iv$iv2++;
                                } else {
                                    it$iv = null;
                                    break;
                                }
                            }
                            item = (LazyListMeasuredItem) it$iv;
                        }
                    } else {
                        int index$iv$iv3 = 0;
                        int size3 = list.size();
                        while (true) {
                            if (index$iv$iv3 < size3) {
                                Object item$iv$iv3 = list.get(index$iv$iv3);
                                it$iv2 = item$iv$iv3;
                                if (((LazyListMeasuredItem) it$iv2).getIndex() == index ? z : false) {
                                    break;
                                }
                                index$iv$iv3++;
                            } else {
                                it$iv2 = null;
                                break;
                            }
                        }
                        item = (LazyListMeasuredItem) it$iv2;
                    }
                    if (item != null) {
                        index++;
                        totalOffset += item.getMainAxisSizeWithSpacings();
                    } else {
                        if (list3 == null) {
                            Object list6 = new ArrayList();
                            list3 = (List) list6;
                        }
                        list3.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, index, 0L, 2, null));
                        index++;
                        totalOffset += ((LazyListMeasuredItem) CollectionsKt.last(list3)).getMainAxisSizeWithSpacings();
                    }
                }
            }
        }
        if (list3 != null) {
            List it = list3;
            if (((LazyListMeasuredItem) CollectionsKt.last(it)).getIndex() > end) {
                end = ((LazyListMeasuredItem) CollectionsKt.last(it)).getIndex();
            }
        }
        int size4 = list2.size();
        for (int index$iv = 0; index$iv < size4; index$iv++) {
            Object item$iv = list2.get(index$iv);
            int index2 = ((Number) item$iv).intValue();
            if (index2 > end) {
                if (list3 == null) {
                    List list7 = new ArrayList();
                    list3 = list7;
                }
                list3.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, index2, 0L, 2, null));
            }
        }
        return list3 == null ? CollectionsKt.emptyList() : list3;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int currentFirstItemIndex, LazyListMeasuredItemProvider measuredItemProvider, int beyondBoundsItemCount, List<Integer> list) {
        ArrayList arrayList = null;
        int start = Math.max(0, currentFirstItemIndex - beyondBoundsItemCount);
        int i = currentFirstItemIndex - 1;
        if (start <= i) {
            int i2 = i;
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, i2, 0L, 2, null));
                if (i2 == start) {
                    break;
                }
                i2--;
            }
        }
        int size = list.size() - 1;
        if (size >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = list.get(index$iv);
                int index = ((Number) item$iv).intValue();
                if (index < start) {
                    if (arrayList == null) {
                        Object list3 = new ArrayList();
                        arrayList = (List) list3;
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m785getAndMeasure0kLqBqw$default(measuredItemProvider, index, 0L, 2, null));
                }
            } while (size >= 0);
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int itemsScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
        int i;
        List<LazyListMeasuredItem> list4 = list;
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(itemsScrollOffset == 0)) {
                throw new IllegalStateException("non-zero itemsScrollOffset".toString());
            }
        }
        ArrayList positionedItems = new ArrayList(list4.size() + list2.size() + list3.size());
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("no extra items".toString());
            }
            int itemsCount = list4.size();
            int[] sizes = new int[itemsCount];
            for (int i2 = 0; i2 < itemsCount; i2++) {
                sizes[i2] = list4.get(calculateItemsOffsets$reverseAware(i2, reverseLayout, itemsCount)).getSize();
            }
            int[] offsets = new int[itemsCount];
            for (int i3 = 0; i3 < itemsCount; i3++) {
                offsets[i3] = 0;
            }
            if (isVertical) {
                if (verticalArrangement != null) {
                    verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets);
                } else {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                }
            } else if (horizontalArrangement != null) {
                horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            } else {
                throw new IllegalArgumentException("null horizontalArrangement when isVertical == false".toString());
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
            if (reverseLayout) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    LazyListMeasuredItem item = list4.get(calculateItemsOffsets$reverseAware(index, reverseLayout, itemsCount));
                    if (reverseLayout) {
                        i = (mainAxisLayoutSize - absoluteOffset) - item.getSize();
                    } else {
                        i = absoluteOffset;
                    }
                    int relativeOffset = i;
                    item.position(relativeOffset, layoutWidth, layoutHeight);
                    positionedItems.add(item);
                    if (index == last) {
                        break;
                    }
                    index += step;
                    list4 = list;
                }
            }
        } else {
            int currentMainAxis = itemsScrollOffset;
            int size = list2.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = list2.get(index$iv);
                LazyListMeasuredItem it = (LazyListMeasuredItem) item$iv;
                currentMainAxis -= it.getMainAxisSizeWithSpacings();
                it.position(currentMainAxis, layoutWidth, layoutHeight);
                positionedItems.add(it);
            }
            int currentMainAxis2 = itemsScrollOffset;
            int size2 = list.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv2;
                it2.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it2);
                currentMainAxis2 += it2.getMainAxisSizeWithSpacings();
            }
            int size3 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                LazyListMeasuredItem it3 = (LazyListMeasuredItem) item$iv3;
                it3.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it3);
                currentMainAxis2 += it3.getMainAxisSizeWithSpacings();
            }
        }
        return positionedItems;
    }

    private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int itemsCount) {
        return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (itemsCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
    }
}
