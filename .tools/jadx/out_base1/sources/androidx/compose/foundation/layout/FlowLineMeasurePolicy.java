package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowLayout.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J=\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ2\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0014H\u0016Jg\u0010$\u001a\u00020%2\u000e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0'2\u0006\u0010(\u001a\u00020)2\u0006\u0010#\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00142\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u0014H\u0016¢\u0006\u0002\u00101J(\u00102\u001a\u0002032\u0006\u0010,\u001a\u00020\u00142\u0006\u00104\u001a\u00020+2\u0006\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020)H\u0016J\f\u00105\u001a\u00020\u0014*\u00020\u001dH\u0016J\f\u00106\u001a\u00020\u0014*\u00020\u001dH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u00067À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "isHorizontal", "", "()Z", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "isPrioritizing", "createConstraints-xF2OJ5Q", "(IIIIZ)J", "getCrossAxisPosition", "placeable", "Landroidx/compose/ui/layout/Placeable;", "rowColumnParentData", "Landroidx/compose/foundation/layout/RowColumnParentData;", "crossAxisLayoutSize", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "beforeCrossAxisAlignmentLine", "placeHelper", "Landroidx/compose/ui/layout/MeasureResult;", "placeables", "", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "mainAxisPositions", "", "mainAxisLayoutSize", "crossAxisOffset", "currentLineIndex", "startIndex", "endIndex", "([Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/MeasureScope;I[III[IIII)Landroidx/compose/ui/layout/MeasureResult;", "populateMainAxisPositions", "", "childrenMainAxisSize", "crossAxisSize", "mainAxisSize", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface FlowLineMeasurePolicy extends RowColumnMeasurePolicy {
    CrossAxisAlignment getCrossAxisAlignment();

    Arrangement.Horizontal getHorizontalArrangement();

    Arrangement.Vertical getVerticalArrangement();

    boolean isHorizontal();

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int mainAxisSize(Placeable $this$mainAxisSize) {
        return isHorizontal() ? $this$mainAxisSize.getMeasuredWidth() : $this$mainAxisSize.getMeasuredHeight();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int crossAxisSize(Placeable $this$crossAxisSize) {
        return isHorizontal() ? $this$crossAxisSize.getMeasuredHeight() : $this$crossAxisSize.getMeasuredWidth();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* renamed from: createConstraints-xF2OJ5Q */
    default long mo587createConstraintsxF2OJ5Q(int mainAxisMin, int crossAxisMin, int mainAxisMax, int crossAxisMax, boolean isPrioritizing) {
        if (isHorizontal()) {
            return RowKt.createRowConstraints(isPrioritizing, mainAxisMin, crossAxisMin, mainAxisMax, crossAxisMax);
        }
        return ColumnKt.createColumnConstraints(isPrioritizing, mainAxisMin, crossAxisMin, mainAxisMax, crossAxisMax);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default MeasureResult placeHelper(final Placeable[] placeables, final MeasureScope measureScope, final int beforeCrossAxisAlignmentLine, final int[] mainAxisPositions, int mainAxisLayoutSize, final int crossAxisLayoutSize, final int[] crossAxisOffset, final int currentLineIndex, final int startIndex, final int endIndex) {
        int width;
        int height;
        if (isHorizontal()) {
            width = mainAxisLayoutSize;
            height = crossAxisLayoutSize;
        } else {
            width = crossAxisLayoutSize;
            height = mainAxisLayoutSize;
        }
        return MeasureScope.layout$default(measureScope, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.FlowLineMeasurePolicy$placeHelper$1$1
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
                Placeable.PlacementScope $this$layout2;
                int[] iArr = crossAxisOffset;
                int crossAxisLineOffset = iArr != null ? iArr[currentLineIndex] : 0;
                int i = startIndex;
                while (i < endIndex) {
                    Placeable placeable = placeables[i];
                    Intrinsics.checkNotNull(placeable);
                    int crossAxisPosition = this.getCrossAxisPosition(placeable, RowColumnImplKt.getRowColumnParentData(placeable), crossAxisLayoutSize, measureScope.getLayoutDirection(), beforeCrossAxisAlignmentLine) + crossAxisLineOffset;
                    boolean isHorizontal = this.isHorizontal();
                    int[] iArr2 = mainAxisPositions;
                    if (isHorizontal) {
                        int crossAxisPosition2 = iArr2[i - startIndex];
                        $this$layout2 = $this$layout;
                        Placeable.PlacementScope.place$default($this$layout2, placeable, crossAxisPosition2, crossAxisPosition, 0.0f, 4, null);
                    } else {
                        int i2 = iArr2[i - startIndex];
                        $this$layout2 = $this$layout;
                        Placeable.PlacementScope.place$default($this$layout2, placeable, crossAxisPosition, i2, 0.0f, 4, null);
                    }
                    i++;
                    $this$layout = $this$layout2;
                }
            }
        }, 4, null);
    }

    default int getCrossAxisPosition(Placeable placeable, RowColumnParentData rowColumnParentData, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine) {
        CrossAxisAlignment childCrossAlignment;
        LayoutDirection layoutDirection2;
        if (rowColumnParentData == null || (childCrossAlignment = rowColumnParentData.getCrossAxisAlignment()) == null) {
            childCrossAlignment = getCrossAxisAlignment();
        }
        int crossAxisSize = crossAxisLayoutSize - crossAxisSize(placeable);
        if (isHorizontal()) {
            layoutDirection2 = LayoutDirection.Ltr;
        } else {
            layoutDirection2 = layoutDirection;
        }
        return childCrossAlignment.align$foundation_layout_release(crossAxisSize, layoutDirection2, placeable, beforeCrossAxisAlignmentLine);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default void populateMainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        if (isHorizontal()) {
            Arrangement.Horizontal $this$populateMainAxisPositions_u24lambda_u241 = getHorizontalArrangement();
            $this$populateMainAxisPositions_u24lambda_u241.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, measureScope.getLayoutDirection(), mainAxisPositions);
        } else {
            Arrangement.Vertical $this$populateMainAxisPositions_u24lambda_u242 = getVerticalArrangement();
            $this$populateMainAxisPositions_u24lambda_u242.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions);
        }
    }
}
