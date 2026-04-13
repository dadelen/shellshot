package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FlowLayoutBuildingBlocks.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001:\u0002\"#B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ8\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003JZ\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012ø\u0001\u0000¢\u0006\u0004\b \u0010!R\u0016\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks;", "", "maxItemsInMainAxis", "", "overflow", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "maxLines", "mainAxisSpacing", "crossAxisSpacing", "(ILandroidx/compose/foundation/layout/FlowLayoutOverflowState;JIIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "getWrapEllipsisInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "wrapInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "hasNext", "", "lastContentLineIndex", "totalCrossAxisSize", "leftOverMainAxis", "nextIndexInLine", "getWrapInfo", "nextItemHasNext", "leftOver", "Landroidx/collection/IntIntPair;", "nextSize", "lineIndex", "currentLineCrossAxisSize", "isWrappingRound", "isEllipsisWrap", "getWrapInfo-OpUlnko", "(ZIJLandroidx/collection/IntIntPair;IIIZZ)Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "WrapEllipsisInfo", "WrapInfo", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FlowLayoutBuildingBlocks {
    public static final int $stable = 8;
    private final long constraints;
    private final int crossAxisSpacing;
    private final int mainAxisSpacing;
    private final int maxItemsInMainAxis;
    private final int maxLines;
    private final FlowLayoutOverflowState overflow;

    public /* synthetic */ FlowLayoutBuildingBlocks(int i, FlowLayoutOverflowState flowLayoutOverflowState, long j, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, flowLayoutOverflowState, j, i2, i3, i4);
    }

    private FlowLayoutBuildingBlocks(int maxItemsInMainAxis, FlowLayoutOverflowState overflow, long constraints, int maxLines, int mainAxisSpacing, int crossAxisSpacing) {
        this.maxItemsInMainAxis = maxItemsInMainAxis;
        this.overflow = overflow;
        this.constraints = constraints;
        this.maxLines = maxLines;
        this.mainAxisSpacing = mainAxisSpacing;
        this.crossAxisSpacing = crossAxisSpacing;
    }

    /* compiled from: FlowLayoutBuildingBlocks.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapInfo;", "", "isLastItemInLine", "", "isLastItemInContainer", "(ZZ)V", "()Z", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WrapInfo {
        public static final int $stable = 0;
        private final boolean isLastItemInContainer;
        private final boolean isLastItemInLine;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public WrapInfo() {
            /*
                r3 = this;
                r0 = 3
                r1 = 0
                r2 = 0
                r3.<init>(r2, r2, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutBuildingBlocks.WrapInfo.<init>():void");
        }

        public WrapInfo(boolean isLastItemInLine, boolean isLastItemInContainer) {
            this.isLastItemInLine = isLastItemInLine;
            this.isLastItemInContainer = isLastItemInContainer;
        }

        public /* synthetic */ WrapInfo(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
        }

        /* renamed from: isLastItemInLine, reason: from getter */
        public final boolean getIsLastItemInLine() {
            return this.isLastItemInLine;
        }

        /* renamed from: isLastItemInContainer, reason: from getter */
        public final boolean getIsLastItemInContainer() {
            return this.isLastItemInContainer;
        }
    }

    /* compiled from: FlowLayoutBuildingBlocks.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "", "ellipsis", "Landroidx/compose/ui/layout/Measurable;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "ellipsisSize", "Landroidx/collection/IntIntPair;", "placeEllipsisOnLastContentLine", "", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/ui/layout/Placeable;JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEllipsis", "()Landroidx/compose/ui/layout/Measurable;", "getEllipsisSize-OO21N7I", "()J", "J", "getPlaceEllipsisOnLastContentLine", "()Z", "setPlaceEllipsisOnLastContentLine", "(Z)V", "getPlaceable", "()Landroidx/compose/ui/layout/Placeable;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WrapEllipsisInfo {
        public static final int $stable = 8;
        private final Measurable ellipsis;
        private final long ellipsisSize;
        private boolean placeEllipsisOnLastContentLine;
        private final Placeable placeable;

        public /* synthetic */ WrapEllipsisInfo(Measurable measurable, Placeable placeable, long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(measurable, placeable, j, z);
        }

        private WrapEllipsisInfo(Measurable ellipsis, Placeable placeable, long ellipsisSize, boolean placeEllipsisOnLastContentLine) {
            this.ellipsis = ellipsis;
            this.placeable = placeable;
            this.ellipsisSize = ellipsisSize;
            this.placeEllipsisOnLastContentLine = placeEllipsisOnLastContentLine;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ WrapEllipsisInfo(androidx.compose.ui.layout.Measurable r8, androidx.compose.ui.layout.Placeable r9, long r10, boolean r12, int r13, kotlin.jvm.internal.DefaultConstructorMarker r14) {
            /*
                r7 = this;
                r13 = r13 & 8
                if (r13 == 0) goto L7
                r12 = 1
                r5 = r12
                goto L8
            L7:
                r5 = r12
            L8:
                r6 = 0
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r0.<init>(r1, r2, r3, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutBuildingBlocks.WrapEllipsisInfo.<init>(androidx.compose.ui.layout.Measurable, androidx.compose.ui.layout.Placeable, long, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final Measurable getEllipsis() {
            return this.ellipsis;
        }

        public final Placeable getPlaceable() {
            return this.placeable;
        }

        /* renamed from: getEllipsisSize-OO21N7I, reason: not valid java name and from getter */
        public final long getEllipsisSize() {
            return this.ellipsisSize;
        }

        public final boolean getPlaceEllipsisOnLastContentLine() {
            return this.placeEllipsisOnLastContentLine;
        }

        public final void setPlaceEllipsisOnLastContentLine(boolean z) {
            this.placeEllipsisOnLastContentLine = z;
        }
    }

    public final WrapEllipsisInfo getWrapEllipsisInfo(WrapInfo wrapInfo, boolean hasNext, int lastContentLineIndex, int totalCrossAxisSize, int leftOverMainAxis, int nextIndexInLine) {
        WrapEllipsisInfo ellipsisInfo;
        if (!wrapInfo.getIsLastItemInContainer() || (ellipsisInfo = this.overflow.ellipsisInfo$foundation_layout_release(hasNext, lastContentLineIndex, totalCrossAxisSize)) == null) {
            return null;
        }
        boolean canFitLine = lastContentLineIndex >= 0 && (nextIndexInLine == 0 || (leftOverMainAxis - IntIntPair.m22getFirstimpl(ellipsisInfo.getEllipsisSize()) >= 0 && nextIndexInLine < this.maxItemsInMainAxis));
        ellipsisInfo.setPlaceEllipsisOnLastContentLine(canFitLine);
        return ellipsisInfo;
    }

    /* renamed from: getWrapInfo-OpUlnko, reason: not valid java name */
    public final WrapInfo m595getWrapInfoOpUlnko(boolean nextItemHasNext, int nextIndexInLine, long leftOver, IntIntPair nextSize, int lineIndex, int totalCrossAxisSize, int currentLineCrossAxisSize, boolean isWrappingRound, boolean isEllipsisWrap) {
        boolean z;
        boolean z2;
        IntIntPair ellipsis;
        boolean z3;
        int totalContainerCrossAxisSize = totalCrossAxisSize + currentLineCrossAxisSize;
        if (nextSize == null) {
            return new WrapInfo(true, true);
        }
        if (this.overflow.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.Visible) {
            z = false;
        } else if (lineIndex >= this.maxLines) {
            z = true;
        } else {
            z = IntIntPair.m23getSecondimpl(leftOver) - IntIntPair.m23getSecondimpl(nextSize.getPackedValue()) < 0;
        }
        boolean willOverflowCrossAxis = z;
        if (willOverflowCrossAxis) {
            return new WrapInfo(true, true);
        }
        if (nextIndexInLine == 0) {
            z2 = false;
        } else if (nextIndexInLine >= this.maxItemsInMainAxis) {
            z2 = true;
        } else {
            z2 = IntIntPair.m22getFirstimpl(leftOver) - IntIntPair.m22getFirstimpl(nextSize.getPackedValue()) < 0;
        }
        boolean shouldWrapItem = z2;
        if (shouldWrapItem) {
            if (isWrappingRound) {
                return new WrapInfo(true, true);
            }
            long arg0$iv = this.constraints;
            return new WrapInfo(true, m595getWrapInfoOpUlnko(nextItemHasNext, 0, IntIntPair.m18constructorimpl(Constraints.m6579getMaxWidthimpl(arg0$iv), (IntIntPair.m23getSecondimpl(leftOver) - this.crossAxisSpacing) - currentLineCrossAxisSize), IntIntPair.m15boximpl(IntIntPair.m18constructorimpl(IntIntPair.m22getFirstimpl(nextSize.getPackedValue()) - this.mainAxisSpacing, IntIntPair.m23getSecondimpl(nextSize.getPackedValue()))), lineIndex + 1, totalContainerCrossAxisSize, 0, true, false).getIsLastItemInContainer());
        }
        int totalContainerCrossAxisSize2 = totalCrossAxisSize + Math.max(currentLineCrossAxisSize, IntIntPair.m23getSecondimpl(nextSize.getPackedValue()));
        if (isEllipsisWrap) {
            ellipsis = null;
        } else {
            ellipsis = this.overflow.m600ellipsisSizeF35zmw$foundation_layout_release(nextItemHasNext, lineIndex, totalContainerCrossAxisSize2);
        }
        if (ellipsis != null) {
            ellipsis.getPackedValue();
            if (nextIndexInLine + 1 >= this.maxItemsInMainAxis) {
                z3 = true;
            } else {
                z3 = ((IntIntPair.m22getFirstimpl(leftOver) - IntIntPair.m22getFirstimpl(nextSize.getPackedValue())) - this.mainAxisSpacing) - IntIntPair.m22getFirstimpl(ellipsis.getPackedValue()) < 0;
            }
        } else {
            z3 = false;
        }
        boolean shouldWrapEllipsis = z3;
        if (shouldWrapEllipsis) {
            if (isEllipsisWrap) {
                return new WrapInfo(true, true);
            }
            long arg0$iv2 = this.constraints;
            WrapInfo wrapInfo = m595getWrapInfoOpUlnko(false, 0, IntIntPair.m18constructorimpl(Constraints.m6579getMaxWidthimpl(arg0$iv2), (IntIntPair.m23getSecondimpl(leftOver) - this.crossAxisSpacing) - Math.max(currentLineCrossAxisSize, IntIntPair.m23getSecondimpl(nextSize.getPackedValue()))), ellipsis, lineIndex + 1, totalContainerCrossAxisSize2, 0, true, true);
            return new WrapInfo(wrapInfo.getIsLastItemInContainer(), wrapInfo.getIsLastItemInContainer());
        }
        return new WrapInfo(false, false);
    }
}
