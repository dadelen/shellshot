package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Column.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\b\u001a\u00020\u0004HÂ\u0003J\t\u0010\t\u001a\u00020\u0006HÂ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J=\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J2\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0002J\t\u0010\"\u001a\u00020\u000eHÖ\u0001Jg\u0010#\u001a\u00020$2\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0&2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000eH\u0016¢\u0006\u0002\u00100J(\u00101\u001a\u0002022\u0006\u0010+\u001a\u00020\u000e2\u0006\u00103\u001a\u00020*2\u0006\u0010)\u001a\u00020*2\u0006\u0010'\u001a\u00020(H\u0016J\t\u00104\u001a\u000205HÖ\u0001J\f\u00106\u001a\u00020\u000e*\u00020\u001bH\u0016J\f\u00107\u001a\u00020\u000e*\u00020\u001bH\u0016J\"\u00108\u001a\u00020\u000e*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;2\u0006\u0010=\u001a\u00020\u000eH\u0016J\"\u0010>\u001a\u00020\u000e*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;2\u0006\u0010?\u001a\u00020\u000eH\u0016J,\u0010@\u001a\u00020$*\u00020(2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020A0;2\u0006\u0010B\u001a\u00020\fH\u0016ø\u0001\u0001¢\u0006\u0004\bC\u0010DJ\"\u0010E\u001a\u00020\u000e*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;2\u0006\u0010=\u001a\u00020\u000eH\u0016J\"\u0010F\u001a\u00020\u000e*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;2\u0006\u0010?\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"Landroidx/compose/foundation/layout/ColumnMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;)V", "component1", "component2", "copy", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "isPrioritizing", "", "createConstraints-xF2OJ5Q", "(IIIIZ)J", "equals", "other", "", "getCrossAxisPosition", "placeable", "Landroidx/compose/ui/layout/Placeable;", "parentData", "Landroidx/compose/foundation/layout/RowColumnParentData;", "crossAxisLayoutSize", "beforeCrossAxisAlignmentLine", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "hashCode", "placeHelper", "Landroidx/compose/ui/layout/MeasureResult;", "placeables", "", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "mainAxisPositions", "", "mainAxisLayoutSize", "crossAxisOffset", "currentLineIndex", "startIndex", "endIndex", "([Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/MeasureScope;I[III[IIII)Landroidx/compose/ui/layout/MeasureResult;", "populateMainAxisPositions", "", "childrenMainAxisSize", "toString", "", "crossAxisSize", "mainAxisSize", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class ColumnMeasurePolicy implements MeasurePolicy, RowColumnMeasurePolicy {
    public static final int $stable = 0;
    private final Alignment.Horizontal horizontalAlignment;
    private final Arrangement.Vertical verticalArrangement;

    /* renamed from: component1, reason: from getter */
    private final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* renamed from: component2, reason: from getter */
    private final Alignment.Horizontal getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public static /* synthetic */ ColumnMeasurePolicy copy$default(ColumnMeasurePolicy columnMeasurePolicy, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, int i, Object obj) {
        if ((i & 1) != 0) {
            vertical = columnMeasurePolicy.verticalArrangement;
        }
        if ((i & 2) != 0) {
            horizontal = columnMeasurePolicy.horizontalAlignment;
        }
        return columnMeasurePolicy.copy(vertical, horizontal);
    }

    public final ColumnMeasurePolicy copy(Arrangement.Vertical verticalArrangement, Alignment.Horizontal horizontalAlignment) {
        return new ColumnMeasurePolicy(verticalArrangement, horizontalAlignment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ColumnMeasurePolicy)) {
            return false;
        }
        ColumnMeasurePolicy columnMeasurePolicy = (ColumnMeasurePolicy) other;
        return Intrinsics.areEqual(this.verticalArrangement, columnMeasurePolicy.verticalArrangement) && Intrinsics.areEqual(this.horizontalAlignment, columnMeasurePolicy.horizontalAlignment);
    }

    public int hashCode() {
        return (this.verticalArrangement.hashCode() * 31) + this.horizontalAlignment.hashCode();
    }

    public String toString() {
        return "ColumnMeasurePolicy(verticalArrangement=" + this.verticalArrangement + ", horizontalAlignment=" + this.horizontalAlignment + ')';
    }

    public ColumnMeasurePolicy(Arrangement.Vertical verticalArrangement, Alignment.Horizontal horizontalAlignment) {
        this.verticalArrangement = verticalArrangement;
        this.horizontalAlignment = horizontalAlignment;
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public int mainAxisSize(Placeable $this$mainAxisSize) {
        return $this$mainAxisSize.getHeight();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public int crossAxisSize(Placeable $this$crossAxisSize) {
        return $this$crossAxisSize.getWidth();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public void populateMainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        Arrangement.Vertical $this$populateMainAxisPositions_u24lambda_u240 = this.verticalArrangement;
        $this$populateMainAxisPositions_u24lambda_u240.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public MeasureResult placeHelper(final Placeable[] placeables, final MeasureScope measureScope, final int beforeCrossAxisAlignmentLine, final int[] mainAxisPositions, int mainAxisLayoutSize, final int crossAxisLayoutSize, int[] crossAxisOffset, int currentLineIndex, int startIndex, int endIndex) {
        return MeasureScope.layout$default(measureScope, crossAxisLayoutSize, mainAxisLayoutSize, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.ColumnMeasurePolicy$placeHelper$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                int crossAxisPosition;
                Placeable[] placeableArr = placeables;
                ColumnMeasurePolicy columnMeasurePolicy = this;
                int i = crossAxisLayoutSize;
                int i2 = beforeCrossAxisAlignmentLine;
                MeasureScope measureScope2 = measureScope;
                int[] iArr = mainAxisPositions;
                int index$iv = 0;
                int length = placeableArr.length;
                int i3 = 0;
                while (i3 < length) {
                    Placeable placeable = placeableArr[i3];
                    int i4 = index$iv;
                    Intrinsics.checkNotNull(placeable);
                    crossAxisPosition = columnMeasurePolicy.getCrossAxisPosition(placeable, RowColumnImplKt.getRowColumnParentData(placeable), i, i2, measureScope2.getLayoutDirection());
                    Placeable.PlacementScope.place$default($this$layout, placeable, crossAxisPosition, iArr[i4], 0.0f, 4, null);
                    i3++;
                    index$iv++;
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisPosition(Placeable placeable, RowColumnParentData parentData, int crossAxisLayoutSize, int beforeCrossAxisAlignmentLine, LayoutDirection layoutDirection) {
        CrossAxisAlignment childCrossAlignment = parentData != null ? parentData.getCrossAxisAlignment() : null;
        if (childCrossAlignment != null) {
            return childCrossAlignment.align$foundation_layout_release(crossAxisLayoutSize - placeable.getWidth(), layoutDirection, placeable, beforeCrossAxisAlignmentLine);
        }
        return this.horizontalAlignment.align(0, crossAxisLayoutSize - placeable.getWidth(), layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* renamed from: createConstraints-xF2OJ5Q, reason: not valid java name */
    public long mo587createConstraintsxF2OJ5Q(int mainAxisMin, int crossAxisMin, int mainAxisMax, int crossAxisMax, boolean isPrioritizing) {
        return ColumnKt.createColumnConstraints(isPrioritizing, mainAxisMin, crossAxisMin, mainAxisMax, crossAxisMax);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        return RowColumnMeasurePolicyKt.measure$default(this, Constraints.m6580getMinHeightimpl(constraints), Constraints.m6581getMinWidthimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints), Constraints.m6579getMaxWidthimpl(constraints), $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(this.verticalArrangement.getSpacing()), $this$measure_u2d3p2s80s, list, new Placeable[list.size()], 0, list.size(), null, 0, 3072, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMinWidth(list, height, $this$minIntrinsicWidth.mo358roundToPx0680j_4(this.verticalArrangement.getSpacing()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMinHeight(list, width, $this$minIntrinsicHeight.mo358roundToPx0680j_4(this.verticalArrangement.getSpacing()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMaxWidth(list, height, $this$maxIntrinsicWidth.mo358roundToPx0680j_4(this.verticalArrangement.getSpacing()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMaxHeight(list, width, $this$maxIntrinsicHeight.mo358roundToPx0680j_4(this.verticalArrangement.getSpacing()));
    }
}
