package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.ListItemType;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002JV\u0010\u000f\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002J(\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u0012\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ(\u0010\u001b\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u001c\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "()V", "calculateIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo620measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        long looseConstraints;
        int intrinsicSupportingWidthConstraint;
        boolean intrinsicIsSupportingMultiline;
        int currentTotalWidth;
        boolean z;
        float arg0$iv;
        Placeable trailingPlaceable;
        int currentTotalHeight;
        long paddedLooseConstraints;
        Placeable headlinePlaceable;
        Placeable headlinePlaceable2;
        int currentTotalHeight2;
        Placeable trailingPlaceable2;
        Placeable supportingPlaceable;
        Placeable supportingPlaceable2;
        float topPadding;
        int width;
        int height;
        MeasureResult place;
        List headlineMeasurable = list.get(0);
        List overlineMeasurable = list.get(1);
        List supportingMeasurable = list.get(2);
        List leadingMeasurable = list.get(3);
        List trailingMeasurable = list.get(4);
        looseConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        float startPadding = ListItemKt.getListItemStartPadding();
        float endPadding = ListItemKt.getListItemEndPadding();
        int horizontalPadding = $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(Dp.m6626constructorimpl(startPadding + endPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull(leadingMeasurable);
        int intrinsicLeadingWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m6578getMaxHeightimpl(constraints)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull(trailingMeasurable);
        int intrinsicTrailingWidth = measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m6578getMaxHeightimpl(constraints)) : 0;
        intrinsicSupportingWidthConstraint = ListItemKt.subtractConstraintSafely(Constraints.m6579getMaxWidthimpl(looseConstraints), intrinsicLeadingWidth + intrinsicTrailingWidth + horizontalPadding);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull(supportingMeasurable);
        int intrinsicSupportingHeight = measurable3 != null ? measurable3.minIntrinsicHeight(intrinsicSupportingWidthConstraint) : 0;
        intrinsicIsSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$measure_u2d3p2s80s, intrinsicSupportingHeight);
        ListItemType.Companion companion = ListItemType.INSTANCE;
        boolean z2 = CollectionsKt.firstOrNull(overlineMeasurable) != null;
        if (CollectionsKt.firstOrNull(supportingMeasurable) != null) {
            currentTotalWidth = 0;
            z = true;
        } else {
            currentTotalWidth = 0;
            z = false;
        }
        int intrinsicListItemType = companion.m2220invokeZLSjz4$material3_release(z2, z, intrinsicIsSupportingMultiline);
        arg0$iv = ListItemKt.m2207verticalPaddingyh95HIg(intrinsicListItemType);
        float arg0$iv2 = 2;
        int intrinsicVerticalPadding = $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv2 * arg0$iv));
        long paddedLooseConstraints2 = ConstraintsKt.m6598offsetNN6EwU(looseConstraints, -horizontalPadding, -intrinsicVerticalPadding);
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull(leadingMeasurable);
        Placeable placeable = null;
        Placeable leadingPlaceable = measurable4 != null ? measurable4.mo5491measureBRTryo0(paddedLooseConstraints2) : null;
        Placeable leadingPlaceable2 = leadingPlaceable;
        int currentTotalWidth2 = currentTotalWidth + TextFieldImplKt.widthOrZero(leadingPlaceable);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull(trailingMeasurable);
        Placeable trailingPlaceable3 = measurable5 != null ? measurable5.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(paddedLooseConstraints2, -currentTotalWidth2, 0, 2, null)) : null;
        int currentTotalWidth3 = currentTotalWidth2 + TextFieldImplKt.widthOrZero(trailingPlaceable3);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull(headlineMeasurable);
        if (measurable6 != null) {
            currentTotalHeight = 0;
            trailingPlaceable = trailingPlaceable3;
            paddedLooseConstraints = paddedLooseConstraints2;
            headlinePlaceable = measurable6.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(paddedLooseConstraints2, -currentTotalWidth3, 0, 2, null));
        } else {
            trailingPlaceable = trailingPlaceable3;
            currentTotalHeight = 0;
            paddedLooseConstraints = paddedLooseConstraints2;
            headlinePlaceable = null;
        }
        int currentTotalHeight3 = currentTotalHeight + TextFieldImplKt.heightOrZero(headlinePlaceable);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull(supportingMeasurable);
        if (measurable7 != null) {
            headlinePlaceable2 = headlinePlaceable;
            trailingPlaceable2 = trailingPlaceable;
            currentTotalHeight2 = currentTotalHeight3;
            supportingPlaceable = measurable7.mo5491measureBRTryo0(ConstraintsKt.m6598offsetNN6EwU(paddedLooseConstraints, -currentTotalWidth3, -currentTotalHeight3));
        } else {
            headlinePlaceable2 = headlinePlaceable;
            currentTotalHeight2 = currentTotalHeight3;
            trailingPlaceable2 = trailingPlaceable;
            supportingPlaceable = null;
        }
        int currentTotalHeight4 = currentTotalHeight2 + TextFieldImplKt.heightOrZero(supportingPlaceable);
        boolean isSupportingMultiline = (supportingPlaceable == null || supportingPlaceable.get(AlignmentLineKt.getFirstBaseline()) == supportingPlaceable.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull(overlineMeasurable);
        if (measurable8 != null) {
            supportingPlaceable2 = supportingPlaceable;
            int i = -currentTotalWidth3;
            int currentTotalWidth4 = -currentTotalHeight4;
            placeable = measurable8.mo5491measureBRTryo0(ConstraintsKt.m6598offsetNN6EwU(paddedLooseConstraints, i, currentTotalWidth4));
        } else {
            supportingPlaceable2 = supportingPlaceable;
        }
        Placeable overlinePlaceable = placeable;
        int listItemType = ListItemType.INSTANCE.m2220invokeZLSjz4$material3_release(overlinePlaceable != null, supportingPlaceable2 != null, isSupportingMultiline);
        topPadding = ListItemKt.m2207verticalPaddingyh95HIg(listItemType);
        float verticalPadding = Dp.m6626constructorimpl(2 * topPadding);
        width = ListItemKt.m2206calculateWidthyeHjK3Y($this$measure_u2d3p2s80s, TextFieldImplKt.widthOrZero(leadingPlaceable2), TextFieldImplKt.widthOrZero(trailingPlaceable2), TextFieldImplKt.widthOrZero(headlinePlaceable2), TextFieldImplKt.widthOrZero(overlinePlaceable), TextFieldImplKt.widthOrZero(supportingPlaceable2), horizontalPadding, constraints);
        height = ListItemKt.m2205calculateHeightN4Jib3Y($this$measure_u2d3p2s80s, TextFieldImplKt.heightOrZero(leadingPlaceable2), TextFieldImplKt.heightOrZero(trailingPlaceable2), TextFieldImplKt.heightOrZero(headlinePlaceable2), TextFieldImplKt.heightOrZero(overlinePlaceable), TextFieldImplKt.heightOrZero(supportingPlaceable2), listItemType, $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(verticalPadding), constraints);
        boolean m2212equalsimpl0 = ListItemType.m2212equalsimpl0(listItemType, ListItemType.INSTANCE.m2218getThreeLineAlXitO8());
        int intrinsicSupportingWidthConstraint2 = $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(startPadding);
        int intrinsicSupportingHeight2 = $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(endPadding);
        place = ListItemKt.place($this$measure_u2d3p2s80s, width, height, leadingPlaceable2, trailingPlaceable2, headlinePlaceable2, overlinePlaceable, supportingPlaceable2, m2212equalsimpl0, intrinsicSupportingWidthConstraint2, intrinsicSupportingHeight2, $this$measure_u2d3p2s80s.mo358roundToPx0680j_4(topPadding));
        return place;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$maxIntrinsicHeight, list, width, ListItemMeasurePolicy$maxIntrinsicHeight$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$maxIntrinsicWidth, list, height, ListItemMeasurePolicy$maxIntrinsicWidth$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
        return calculateIntrinsicHeight($this$minIntrinsicHeight, list, width, ListItemMeasurePolicy$minIntrinsicHeight$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
        return calculateIntrinsicWidth($this$minIntrinsicWidth, list, height, ListItemMeasurePolicy$minIntrinsicWidth$1.INSTANCE);
    }

    private final int calculateIntrinsicWidth(IntrinsicMeasureScope $this$calculateIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int m2206calculateWidthyeHjK3Y;
        List headlineMeasurable = list.get(0);
        List overlineMeasurable = list.get(1);
        List supportingMeasurable = list.get(2);
        List leadingMeasurable = list.get(3);
        List trailingMeasurable = list.get(4);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull(leadingMeasurable);
        int intValue = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(trailingMeasurable);
        int intValue2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(headlineMeasurable);
        int intValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(overlineMeasurable);
        int intValue4 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(supportingMeasurable);
        int intValue5 = intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(height)).intValue() : 0;
        float arg0$iv = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        m2206calculateWidthyeHjK3Y = ListItemKt.m2206calculateWidthyeHjK3Y($this$calculateIntrinsicWidth, intValue, intValue2, intValue3, intValue4, intValue5, $this$calculateIntrinsicWidth.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv + other$iv)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        return m2206calculateWidthyeHjK3Y;
    }

    private final int calculateIntrinsicHeight(IntrinsicMeasureScope $this$calculateIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int remainingWidth;
        int leadingHeight;
        int trailingHeight;
        int remainingWidth2;
        boolean isSupportingMultiline;
        float arg0$iv;
        int supportingHeight;
        int remainingWidth3;
        int subtractConstraintSafely;
        List headlineMeasurable = list.get(0);
        List overlineMeasurable = list.get(1);
        List supportingMeasurable = list.get(2);
        List leadingMeasurable = list.get(3);
        List trailingMeasurable = list.get(4);
        float arg0$iv2 = ListItemKt.getListItemStartPadding();
        float other$iv = ListItemKt.getListItemEndPadding();
        remainingWidth = ListItemKt.subtractConstraintSafely(width, $this$calculateIntrinsicHeight.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv2 + other$iv)));
        IntrinsicMeasurable it = (IntrinsicMeasurable) CollectionsKt.firstOrNull(leadingMeasurable);
        if (it != null) {
            leadingHeight = function2.invoke(it, Integer.valueOf(remainingWidth)).intValue();
            subtractConstraintSafely = ListItemKt.subtractConstraintSafely(remainingWidth, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = subtractConstraintSafely;
        } else {
            leadingHeight = 0;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(trailingMeasurable);
        if (it2 != null) {
            trailingHeight = function2.invoke(it2, Integer.valueOf(remainingWidth)).intValue();
            remainingWidth3 = ListItemKt.subtractConstraintSafely(remainingWidth, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth2 = remainingWidth3;
        } else {
            trailingHeight = 0;
            remainingWidth2 = remainingWidth;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull(overlineMeasurable);
        int overlineHeight = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(remainingWidth2)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(supportingMeasurable);
        int supportingHeight2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(remainingWidth2)).intValue() : 0;
        isSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$calculateIntrinsicHeight, supportingHeight2);
        int listItemType = ListItemType.INSTANCE.m2220invokeZLSjz4$material3_release(overlineHeight > 0, supportingHeight2 > 0, isSupportingMultiline);
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull(headlineMeasurable);
        int supportingHeight3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(width)).intValue() : 0;
        arg0$iv = ListItemKt.m2207verticalPaddingyh95HIg(listItemType);
        int other$iv2 = $this$calculateIntrinsicHeight.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv));
        supportingHeight = ListItemKt.m2205calculateHeightN4Jib3Y($this$calculateIntrinsicHeight, leadingHeight, trailingHeight, supportingHeight3, overlineHeight, supportingHeight2, listItemType, other$iv2, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        return supportingHeight;
    }
}
