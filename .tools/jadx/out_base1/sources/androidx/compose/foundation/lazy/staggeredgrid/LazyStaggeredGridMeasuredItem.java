package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: LazyStaggeredGridMeasure.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002Bs\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0004J\u001d\u0010C\u001a\u0002032\u0006\u0010\u0003\u001a\u00020\u0004H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u0012\u0010F\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0016\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KJ\u001e\u0010L\u001a\u00020A2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004J(\u0010L\u001a\u00020A2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u0004H\u0016J\b\u0010Q\u001a\u00020RH\u0016J\u000e\u0010S\u001a\u00020A2\u0006\u0010&\u001a\u00020\u0004J+\u0010T\u001a\u000203*\u0002032\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040VH\u0082\bø\u0001\u0000¢\u0006\u0004\bW\u0010XR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u00020\u0015X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010 R\u001a\u0010!\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010 \"\u0004\b\"\u0010#R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0014\u0010\r\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u000e\u0010&\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b(\u0010\u001eR\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0014\u0010+\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u000e\u0010-\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010 \"\u0004\b1\u0010#R&\u00104\u001a\u0002032\u0006\u00102\u001a\u000203@RX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b5\u0010\u0018R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u001eR\u001c\u00108\u001a\u000209X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b:\u0010\u0018R\u0014\u0010\u000e\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u001eR\u0018\u0010<\u001a\u00020\u0004*\u0002038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0019\u0010)\u001a\u00020\u0004*\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010?\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "index", "", "key", "", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "isVertical", "", "spacing", "lane", "span", "beforeContentPadding", "afterContentPadding", "contentType", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "(ILjava/lang/Object;Ljava/util/List;ZIIIIILjava/lang/Object;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConstraints-msEJaDk", "()J", "J", "getContentType", "()Ljava/lang/Object;", "crossAxisSize", "getCrossAxisSize", "()I", "getIndex", "()Z", "isVisible", "setVisible", "(Z)V", "getKey", "getLane", "mainAxisLayoutSize", "mainAxisOffset", "getMainAxisOffset", "mainAxisSize", "getMainAxisSize", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "maxMainAxisOffset", "minMainAxisOffset", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "<set-?>", "Landroidx/compose/ui/unit/IntOffset;", "offset", "getOffset-nOcc-ac", "placeablesCount", "getPlaceablesCount", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "getSpan", "mainAxis", "getMainAxis--gyyYBs", "(J)I", "(Landroidx/compose/ui/layout/Placeable;)I", "applyScrollDelta", "", "delta", "getOffset", "getOffset-Bjo55l4", "(I)J", "getParentData", "place", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "context", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "position", "crossAxis", "crossAxisOffset", "layoutWidth", "layoutHeight", "toString", "", "updateMainAxisLayoutSize", "copy", "mainAxisMap", "Lkotlin/Function1;", "copy-4Tuh3kE", "(JLkotlin/jvm/functions/Function1;)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridMeasuredItem implements LazyStaggeredGridItemInfo, LazyLayoutMeasuredItem {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyLayoutItemAnimator<LazyStaggeredGridMeasuredItem> animator;
    private final int beforeContentPadding;
    private final long constraints;
    private final Object contentType;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private boolean isVisible;
    private final Object key;
    private final int lane;
    private int mainAxisLayoutSize;
    private final int mainAxisSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private long offset;
    private final List<Placeable> placeables;
    private final long size;
    private final int span;

    public /* synthetic */ LazyStaggeredGridMeasuredItem(int i, Object obj, List list, boolean z, int i2, int i3, int i4, int i5, int i6, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, obj, list, z, i2, i3, i4, i5, i6, obj2, lazyLayoutItemAnimator, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyStaggeredGridMeasuredItem(int index, Object key, List<? extends Placeable> list, boolean isVertical, int spacing, int lane, int span, int beforeContentPadding, int afterContentPadding, Object contentType, LazyLayoutItemAnimator<LazyStaggeredGridMeasuredItem> lazyLayoutItemAnimator, long constraints) {
        Integer valueOf;
        this.index = index;
        this.key = key;
        this.placeables = list;
        this.isVertical = isVertical;
        this.lane = lane;
        this.span = span;
        this.beforeContentPadding = beforeContentPadding;
        this.afterContentPadding = afterContentPadding;
        this.contentType = contentType;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = constraints;
        this.isVisible = true;
        List $this$fastMaxOfOrNull$iv = this.placeables;
        Comparable maxValue$iv = null;
        if ($this$fastMaxOfOrNull$iv.isEmpty()) {
            valueOf = null;
        } else {
            Placeable placeable = $this$fastMaxOfOrNull$iv.get(0);
            valueOf = Integer.valueOf(getIsVertical() ? placeable.getHeight() : placeable.getWidth());
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable placeable2 = $this$fastMaxOfOrNull$iv.get(i$iv);
                    Integer valueOf2 = Integer.valueOf(getIsVertical() ? placeable2.getHeight() : placeable2.getWidth());
                    valueOf = valueOf2.compareTo(valueOf) > 0 ? valueOf2 : valueOf;
                    if (i$iv == lastIndex) {
                        break;
                    } else {
                        i$iv++;
                    }
                }
            }
        }
        Integer num = valueOf;
        this.mainAxisSize = num != null ? num.intValue() : 0;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(this.mainAxisSize + spacing, 0);
        List $this$fastMaxOfOrNull$iv2 = this.placeables;
        if (!$this$fastMaxOfOrNull$iv2.isEmpty()) {
            Placeable it = $this$fastMaxOfOrNull$iv2.get(0);
            Integer valueOf3 = Integer.valueOf(getIsVertical() ? it.getWidth() : it.getHeight());
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv2);
            if (1 <= lastIndex2) {
                while (true) {
                    Placeable it2 = $this$fastMaxOfOrNull$iv2.get(i$iv2);
                    Integer valueOf4 = Integer.valueOf(getIsVertical() ? it2.getWidth() : it2.getHeight());
                    valueOf3 = valueOf4.compareTo(valueOf3) > 0 ? valueOf4 : valueOf3;
                    if (i$iv2 == lastIndex2) {
                        break;
                    } else {
                        i$iv2++;
                    }
                }
            }
            maxValue$iv = valueOf3;
        }
        Integer num2 = (Integer) maxValue$iv;
        this.crossAxisSize = num2 != null ? num2.intValue() : 0;
        this.mainAxisLayoutSize = -1;
        this.size = getIsVertical() ? IntSizeKt.IntSize(this.crossAxisSize, this.mainAxisSize) : IntSizeKt.IntSize(this.mainAxisSize, this.crossAxisSize);
        this.offset = IntOffset.INSTANCE.m6764getZeronOccac();
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: isVertical, reason: from getter */
    public boolean getIsVertical() {
        return this.isVertical;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getLane() {
        return this.lane;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getConstraints-msEJaDk, reason: from getter */
    public long getConstraints() {
        return this.constraints;
    }

    /* renamed from: isVisible, reason: from getter */
    public final boolean getIsVisible() {
        return this.isVisible;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getOffset-Bjo55l4 */
    public long mo784getOffsetBjo55l4(int index) {
        return getOffset();
    }

    public final void position(int mainAxis, int crossAxis, int mainAxisLayoutSize) {
        long IntOffset;
        this.mainAxisLayoutSize = mainAxisLayoutSize;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.afterContentPadding + mainAxisLayoutSize;
        if (getIsVertical()) {
            IntOffset = IntOffsetKt.IntOffset(crossAxis, mainAxis);
        } else {
            IntOffset = IntOffsetKt.IntOffset(mainAxis, crossAxis);
        }
        this.offset = IntOffset;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight) {
        position(mainAxisOffset, crossAxisOffset, getIsVertical() ? layoutHeight : layoutWidth);
    }

    public final int getMainAxisOffset() {
        return !getIsVertical() ? IntOffset.m6754getXimpl(getOffset()) : IntOffset.m6755getYimpl(getOffset());
    }

    public final void place(Placeable.PlacementScope scope, LazyStaggeredGridMeasureContext context) {
        int i;
        int i2;
        Placeable.PlacementScope $this$place_u24lambda_u246_u24lambda_u245;
        GraphicsLayer layer;
        GraphicsLayer layer2;
        int height;
        int m6755getYimpl;
        LazyStaggeredGridMeasureContext $this$place_u24lambda_u246 = context;
        int i3 = 0;
        if (!(this.mainAxisLayoutSize != -1)) {
            throw new IllegalArgumentException("position() should be called first".toString());
        }
        Placeable.PlacementScope $this$place_u24lambda_u246_u24lambda_u2452 = scope;
        int i4 = 0;
        List $this$fastForEachIndexed$iv = this.placeables;
        int size = $this$fastForEachIndexed$iv.size();
        int index$iv = 0;
        while (index$iv < size) {
            Object item$iv = $this$fastForEachIndexed$iv.get(index$iv);
            Placeable placeable = (Placeable) item$iv;
            int index = index$iv;
            int minOffset = this.minMainAxisOffset - (getIsVertical() ? placeable.getHeight() : placeable.getWidth());
            int maxOffset = this.maxMainAxisOffset;
            long offset = getOffset();
            LazyStaggeredGridMeasureContext $this$place_u24lambda_u2462 = $this$place_u24lambda_u246;
            LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), index);
            if (animation != null) {
                i = i3;
                i2 = i4;
                long animatedOffset = IntOffset.m6758plusqkQi6aY(offset, animation.m831getPlacementDeltanOccac());
                $this$place_u24lambda_u246_u24lambda_u245 = $this$place_u24lambda_u246_u24lambda_u2452;
                if ((m889getMainAxisgyyYBs(offset) <= minOffset && m889getMainAxisgyyYBs(animatedOffset) <= minOffset) || (m889getMainAxisgyyYBs(offset) >= maxOffset && m889getMainAxisgyyYBs(animatedOffset) >= maxOffset)) {
                    animation.cancelPlacementAnimation();
                }
                offset = animatedOffset;
                layer = animation.getLayer();
            } else {
                i = i3;
                i2 = i4;
                $this$place_u24lambda_u246_u24lambda_u245 = $this$place_u24lambda_u246_u24lambda_u2452;
                layer = null;
            }
            if ($this$place_u24lambda_u2462.getReverseLayout()) {
                long $this$copy_u2d4Tuh3kE$iv = offset;
                if (getIsVertical()) {
                    layer2 = layer;
                    height = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv);
                } else {
                    int mainAxisOffset = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv);
                    layer2 = layer;
                    height = (this.mainAxisLayoutSize - mainAxisOffset) - (getIsVertical() ? placeable.getHeight() : placeable.getWidth());
                }
                if (getIsVertical()) {
                    int mainAxisOffset2 = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv);
                    m6755getYimpl = (this.mainAxisLayoutSize - mainAxisOffset2) - (getIsVertical() ? placeable.getHeight() : placeable.getWidth());
                } else {
                    m6755getYimpl = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv);
                }
                offset = IntOffsetKt.IntOffset(height, m6755getYimpl);
            } else {
                layer2 = layer;
            }
            long offset2 = IntOffset.m6758plusqkQi6aY(offset, $this$place_u24lambda_u2462.getContentOffset());
            if (animation != null) {
                animation.m833setFinalOffsetgyyYBs(offset2);
            }
            if (layer2 != null) {
                $this$place_u24lambda_u246_u24lambda_u2452 = $this$place_u24lambda_u246_u24lambda_u245;
                Placeable.PlacementScope.m5558placeRelativeWithLayeraW9wM$default($this$place_u24lambda_u246_u24lambda_u2452, placeable, offset2, layer2, 0.0f, 4, (Object) null);
            } else {
                $this$place_u24lambda_u246_u24lambda_u2452 = $this$place_u24lambda_u246_u24lambda_u245;
                Placeable.PlacementScope.m5557placeRelativeWithLayeraW9wM$default($this$place_u24lambda_u246_u24lambda_u2452, placeable, offset2, 0.0f, (Function1) null, 6, (Object) null);
            }
            index$iv++;
            $this$place_u24lambda_u246 = $this$place_u24lambda_u2462;
            i3 = i;
            i4 = i2;
        }
    }

    public final void updateMainAxisLayoutSize(int mainAxisLayoutSize) {
        this.mainAxisLayoutSize = mainAxisLayoutSize;
        this.maxMainAxisOffset = this.afterContentPadding + mainAxisLayoutSize;
    }

    public final void applyScrollDelta(int delta) {
        int it;
        int it2;
        int intValue;
        int mainAxis;
        if (getNonScrollableItem()) {
            return;
        }
        long $this$copy_u2d4Tuh3kE$iv = getOffset();
        if (getIsVertical()) {
            it = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv);
        } else {
            int it3 = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv);
            it = it3 + delta;
        }
        if (getIsVertical()) {
            int it4 = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv);
            it2 = it4 + delta;
        } else {
            it2 = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv);
        }
        this.offset = IntOffsetKt.IntOffset(it, it2);
        int placeablesCount = getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), index);
            if (animation != null) {
                long $this$copy_u2d4Tuh3kE$iv2 = animation.getRawOffset();
                if (getIsVertical()) {
                    intValue = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv2);
                } else {
                    int mainAxis2 = IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE$iv2);
                    intValue = Integer.valueOf(mainAxis2 + delta).intValue();
                }
                if (getIsVertical()) {
                    int mainAxis3 = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv2);
                    mainAxis = mainAxis3 + delta;
                } else {
                    mainAxis = IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE$iv2);
                }
                animation.m835setRawOffsetgyyYBs(IntOffsetKt.IntOffset(intValue, mainAxis));
            }
        }
    }

    /* renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m889getMainAxisgyyYBs(long $this$mainAxis) {
        return getIsVertical() ? IntOffset.m6755getYimpl($this$mainAxis) : IntOffset.m6754getXimpl($this$mainAxis);
    }

    private final int getMainAxisSize(Placeable $this$mainAxisSize) {
        return getIsVertical() ? $this$mainAxisSize.getHeight() : $this$mainAxisSize.getWidth();
    }

    /* renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m888copy4Tuh3kE(long $this$copy_u2d4Tuh3kE, Function1<? super Integer, Integer> function1) {
        return IntOffsetKt.IntOffset(getIsVertical() ? IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE) : function1.invoke(Integer.valueOf(IntOffset.m6754getXimpl($this$copy_u2d4Tuh3kE))).intValue(), getIsVertical() ? function1.invoke(Integer.valueOf(IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE))).intValue() : IntOffset.m6755getYimpl($this$copy_u2d4Tuh3kE));
    }

    public String toString() {
        return super.toString();
    }
}
