package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutlinedTextField.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ<\u0010\r\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J<\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J\"\u0010\u0018\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010\u0019\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J,\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b \u0010!J\"\u0010\"\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010#\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Lkotlin/jvm/functions/Function1;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final Function1<Size, Unit> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX WARN: Multi-variable type inference failed */
    public OutlinedTextFieldMeasurePolicy(Function1<? super Size, Unit> function1, boolean singleLine, float animationProgress, PaddingValues paddingValues) {
        this.onLabelMeasured = function1;
        this.singleLine = singleLine;
        this.animationProgress = animationProgress;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        long relaxedConstraints;
        int occupiedSpaceHorizontally;
        Object it$iv;
        Placeable leadingPlaceable;
        Object it$iv2;
        long relaxedConstraints2;
        Placeable trailingPlaceable;
        Object it$iv3;
        Object it$iv4;
        long relaxedConstraints3;
        Placeable suffixPlaceable;
        Object it$iv5;
        long m4000getZeroNHjbRc;
        Object it$iv6;
        long textConstraints;
        long placeholderConstraints;
        Object it$iv7;
        final int width;
        long supportingConstraints;
        final int totalHeight;
        final OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        final MeasureScope measureScope = $this$measure_u2d3p2s80s;
        int occupiedSpaceHorizontally2 = 0;
        int bottomPadding = measureScope.mo358roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getBottom());
        relaxedConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                occupiedSpaceHorizontally = occupiedSpaceHorizontally2;
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) it$iv;
            occupiedSpaceHorizontally = occupiedSpaceHorizontally2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Leading")) {
                break;
            }
            index$iv$iv++;
            occupiedSpaceHorizontally2 = occupiedSpaceHorizontally;
        }
        Measurable measurable = (Measurable) it$iv;
        Placeable leadingPlaceable2 = measurable != null ? measurable.mo5491measureBRTryo0(relaxedConstraints) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(leadingPlaceable2);
        int occupiedSpaceVertically = Math.max(0, TextFieldImplKt.heightOrZero(leadingPlaceable2));
        List $this$fastFirstOrNull$iv = list;
        int index$iv$iv2 = 0;
        int size2 = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                leadingPlaceable = leadingPlaceable2;
                it$iv2 = null;
                break;
            }
            it$iv2 = $this$fastFirstOrNull$iv.get(index$iv$iv2);
            Measurable it2 = (Measurable) it$iv2;
            List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
            leadingPlaceable = leadingPlaceable2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Trailing")) {
                break;
            }
            index$iv$iv2++;
            $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
            leadingPlaceable2 = leadingPlaceable;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        if (measurable2 != null) {
            relaxedConstraints2 = relaxedConstraints;
            trailingPlaceable = measurable2.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally3, 0, 2, null));
        } else {
            relaxedConstraints2 = relaxedConstraints;
            trailingPlaceable = null;
        }
        int occupiedSpaceHorizontally4 = occupiedSpaceHorizontally3 + TextFieldImplKt.widthOrZero(trailingPlaceable);
        int occupiedSpaceVertically2 = Math.max(occupiedSpaceVertically, TextFieldImplKt.heightOrZero(trailingPlaceable));
        List $this$fastFirstOrNull$iv3 = list;
        int $i$f$fastFirstOrNull = 0;
        int index$iv$iv3 = 0;
        int size3 = $this$fastFirstOrNull$iv3.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                it$iv3 = null;
                break;
            }
            it$iv3 = $this$fastFirstOrNull$iv3.get(index$iv$iv3);
            Measurable it3 = (Measurable) it$iv3;
            List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
            int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), TextFieldImplKt.PrefixId)) {
                break;
            }
            index$iv$iv3++;
            $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
            $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
        }
        Measurable measurable3 = (Measurable) it$iv3;
        final Placeable prefixPlaceable = measurable3 != null ? measurable3.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally4, 0, 2, null)) : null;
        int occupiedSpaceHorizontally5 = occupiedSpaceHorizontally4 + TextFieldImplKt.widthOrZero(prefixPlaceable);
        int occupiedSpaceVertically3 = Math.max(occupiedSpaceVertically2, TextFieldImplKt.heightOrZero(prefixPlaceable));
        List $this$fastFirstOrNull$iv5 = list;
        int $i$f$fastFirstOrNull3 = 0;
        int index$iv$iv4 = 0;
        int size4 = $this$fastFirstOrNull$iv5.size();
        while (true) {
            if (index$iv$iv4 >= size4) {
                it$iv4 = null;
                break;
            }
            it$iv4 = $this$fastFirstOrNull$iv5.get(index$iv$iv4);
            Measurable it4 = (Measurable) it$iv4;
            List $this$fastFirstOrNull$iv6 = $this$fastFirstOrNull$iv5;
            int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), TextFieldImplKt.SuffixId)) {
                break;
            }
            index$iv$iv4++;
            $this$fastFirstOrNull$iv5 = $this$fastFirstOrNull$iv6;
            $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
        }
        Measurable measurable4 = (Measurable) it$iv4;
        if (measurable4 != null) {
            relaxedConstraints3 = relaxedConstraints2;
            suffixPlaceable = measurable4.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally5, 0, 2, null));
        } else {
            relaxedConstraints3 = relaxedConstraints2;
            suffixPlaceable = null;
        }
        int occupiedSpaceHorizontally6 = occupiedSpaceHorizontally5 + TextFieldImplKt.widthOrZero(suffixPlaceable);
        int occupiedSpaceVertically4 = Math.max(occupiedSpaceVertically3, TextFieldImplKt.heightOrZero(suffixPlaceable));
        int labelHorizontalPaddingOffset = measureScope.mo358roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo623calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo358roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo624calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection()));
        long labelConstraints = ConstraintsKt.m6598offsetNN6EwU(relaxedConstraints3, MathHelpersKt.lerp((-occupiedSpaceHorizontally6) - labelHorizontalPaddingOffset, -labelHorizontalPaddingOffset, outlinedTextFieldMeasurePolicy.animationProgress), -bottomPadding);
        Placeable suffixPlaceable2 = suffixPlaceable;
        int size5 = list.size();
        int labelHorizontalPaddingOffset2 = 0;
        while (true) {
            if (labelHorizontalPaddingOffset2 >= size5) {
                it$iv5 = null;
                break;
            }
            it$iv5 = list.get(labelHorizontalPaddingOffset2);
            Measurable it5 = (Measurable) it$iv5;
            int i = size5;
            int index$iv$iv5 = labelHorizontalPaddingOffset2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it5), "Label")) {
                break;
            }
            labelHorizontalPaddingOffset2 = index$iv$iv5 + 1;
            size5 = i;
        }
        Measurable measurable5 = (Measurable) it$iv5;
        Placeable labelPlaceable = measurable5 != null ? measurable5.mo5491measureBRTryo0(labelConstraints) : null;
        if (labelPlaceable != null) {
            Placeable it6 = labelPlaceable;
            m4000getZeroNHjbRc = SizeKt.Size(it6.getWidth(), it6.getHeight());
        } else {
            m4000getZeroNHjbRc = Size.INSTANCE.m4000getZeroNHjbRc();
        }
        long labelSize = m4000getZeroNHjbRc;
        outlinedTextFieldMeasurePolicy.onLabelMeasured.invoke(Size.m3979boximpl(labelSize));
        final Placeable labelPlaceable2 = labelPlaceable;
        int size6 = list.size();
        int index$iv$iv6 = 0;
        while (true) {
            if (index$iv$iv6 >= size6) {
                it$iv6 = null;
                break;
            }
            it$iv6 = list.get(index$iv$iv6);
            Measurable it7 = (Measurable) it$iv6;
            int i2 = size6;
            int index$iv$iv7 = index$iv$iv6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it7), TextFieldImplKt.SupportingId)) {
                break;
            }
            index$iv$iv6 = index$iv$iv7 + 1;
            size6 = i2;
        }
        Measurable supportingMeasurable = (Measurable) it$iv6;
        int supportingIntrinsicHeight = supportingMeasurable != null ? supportingMeasurable.minIntrinsicHeight(Constraints.m6581getMinWidthimpl(constraints)) : 0;
        int topPadding = Math.max(TextFieldImplKt.heightOrZero(labelPlaceable2) / 2, measureScope.mo358roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getTop()));
        final Placeable trailingPlaceable2 = trailingPlaceable;
        textConstraints = Constraints.m6569copyZbe2FdA(r15, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(r15) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(r15) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(r15) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(ConstraintsKt.m6598offsetNN6EwU(constraints, -occupiedSpaceHorizontally6, ((-bottomPadding) - topPadding) - supportingIntrinsicHeight)) : 0);
        List $this$fastForEach$iv$iv = list;
        int occupiedSpaceHorizontally7 = $this$fastForEach$iv$iv.size();
        int index$iv$iv8 = 0;
        while (index$iv$iv8 < occupiedSpaceHorizontally7) {
            int i3 = occupiedSpaceHorizontally7;
            List $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
            Object item$iv$iv = $this$fastForEach$iv$iv2.get(index$iv$iv8);
            Measurable it8 = (Measurable) item$iv$iv;
            int index$iv$iv9 = index$iv$iv8;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it8), "TextField")) {
                Placeable textFieldPlaceable = ((Measurable) item$iv$iv).mo5491measureBRTryo0(textConstraints);
                placeholderConstraints = Constraints.m6569copyZbe2FdA(textConstraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(textConstraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(textConstraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(textConstraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(textConstraints) : 0);
                List $this$fastForEach$iv$iv3 = list;
                Placeable textFieldPlaceable2 = textFieldPlaceable;
                int size7 = $this$fastForEach$iv$iv3.size();
                int index$iv$iv10 = 0;
                while (true) {
                    if (index$iv$iv10 >= size7) {
                        it$iv7 = null;
                        break;
                    }
                    int i4 = size7;
                    List $this$fastForEach$iv$iv4 = $this$fastForEach$iv$iv3;
                    it$iv7 = $this$fastForEach$iv$iv4.get(index$iv$iv10);
                    Measurable it9 = (Measurable) it$iv7;
                    int index$iv$iv11 = index$iv$iv10;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it9), "Hint")) {
                        break;
                    }
                    index$iv$iv10 = index$iv$iv11 + 1;
                    size7 = i4;
                    $this$fastForEach$iv$iv3 = $this$fastForEach$iv$iv4;
                }
                Measurable measurable6 = (Measurable) it$iv7;
                Placeable placeholderPlaceable = measurable6 != null ? measurable6.mo5491measureBRTryo0(placeholderConstraints) : null;
                int occupiedSpaceVertically5 = Math.max(occupiedSpaceVertically4, Math.max(TextFieldImplKt.heightOrZero(textFieldPlaceable2), TextFieldImplKt.heightOrZero(placeholderPlaceable)) + topPadding + bottomPadding);
                Placeable placeholderPlaceable2 = placeholderPlaceable;
                width = OutlinedTextFieldKt.m2356calculateWidthDHJA7U0(TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(trailingPlaceable2), TextFieldImplKt.widthOrZero(prefixPlaceable), TextFieldImplKt.widthOrZero(suffixPlaceable2), textFieldPlaceable2.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable2), TextFieldImplKt.widthOrZero(placeholderPlaceable), outlinedTextFieldMeasurePolicy.animationProgress, constraints, measureScope.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                supportingConstraints = Constraints.m6569copyZbe2FdA(relaxedConstraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(relaxedConstraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(relaxedConstraints) : width, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(relaxedConstraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(ConstraintsKt.m6599offsetNN6EwU$default(relaxedConstraints3, 0, -occupiedSpaceVertically5, 1, null)) : 0);
                final Placeable supportingPlaceable = supportingMeasurable != null ? supportingMeasurable.mo5491measureBRTryo0(supportingConstraints) : null;
                int supportingHeight = TextFieldImplKt.heightOrZero(supportingPlaceable);
                totalHeight = OutlinedTextFieldKt.m2355calculateHeightmKXJcVc(TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(trailingPlaceable2), TextFieldImplKt.heightOrZero(prefixPlaceable), TextFieldImplKt.heightOrZero(suffixPlaceable2), textFieldPlaceable2.getHeight(), TextFieldImplKt.heightOrZero(labelPlaceable2), TextFieldImplKt.heightOrZero(placeholderPlaceable2), TextFieldImplKt.heightOrZero(supportingPlaceable), outlinedTextFieldMeasurePolicy.animationProgress, constraints, measureScope.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int height = totalHeight - supportingHeight;
                List $this$fastForEach$iv$iv5 = list;
                int totalHeight2 = $this$fastForEach$iv$iv5.size();
                int index$iv$iv12 = 0;
                while (index$iv$iv12 < totalHeight2) {
                    int i5 = totalHeight2;
                    List $this$fastForEach$iv$iv6 = $this$fastForEach$iv$iv5;
                    Object item$iv$iv2 = $this$fastForEach$iv$iv6.get(index$iv$iv12);
                    Measurable it10 = (Measurable) item$iv$iv2;
                    int index$iv$iv13 = index$iv$iv12;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it10), TextFieldImplKt.ContainerId)) {
                        final Placeable containerPlaceable = ((Measurable) item$iv$iv2).mo5491measureBRTryo0(ConstraintsKt.Constraints(width != Integer.MAX_VALUE ? width : 0, width, height != Integer.MAX_VALUE ? height : 0, height));
                        final Placeable leadingPlaceable3 = leadingPlaceable;
                        final Placeable suffixPlaceable3 = suffixPlaceable2;
                        final Placeable textFieldPlaceable3 = textFieldPlaceable2;
                        final Placeable placeholderPlaceable3 = placeholderPlaceable2;
                        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, totalHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$measure$1
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
                                float f;
                                boolean z;
                                PaddingValues paddingValues;
                                int i6 = totalHeight;
                                int i7 = width;
                                Placeable placeable = leadingPlaceable3;
                                Placeable placeable2 = trailingPlaceable2;
                                Placeable placeable3 = prefixPlaceable;
                                Placeable placeable4 = suffixPlaceable3;
                                Placeable placeable5 = textFieldPlaceable3;
                                Placeable placeable6 = labelPlaceable2;
                                Placeable placeable7 = placeholderPlaceable3;
                                Placeable placeable8 = containerPlaceable;
                                Placeable placeable9 = supportingPlaceable;
                                f = outlinedTextFieldMeasurePolicy.animationProgress;
                                z = outlinedTextFieldMeasurePolicy.singleLine;
                                float density = measureScope.getDensity();
                                LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                                paddingValues = outlinedTextFieldMeasurePolicy.paddingValues;
                                OutlinedTextFieldKt.place($this$layout, i6, i7, placeable, placeable2, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, placeable9, f, z, density, layoutDirection, paddingValues);
                            }
                        }, 4, null);
                    }
                    index$iv$iv12 = index$iv$iv13 + 1;
                    measureScope = $this$measure_u2d3p2s80s;
                    totalHeight2 = i5;
                    $this$fastForEach$iv$iv5 = $this$fastForEach$iv$iv6;
                    outlinedTextFieldMeasurePolicy = this;
                    leadingPlaceable = leadingPlaceable;
                    suffixPlaceable2 = suffixPlaceable2;
                    labelConstraints = labelConstraints;
                    supportingConstraints = supportingConstraints;
                    textFieldPlaceable2 = textFieldPlaceable2;
                    placeholderPlaceable2 = placeholderPlaceable2;
                    placeholderConstraints = placeholderConstraints;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            index$iv$iv8 = index$iv$iv9 + 1;
            outlinedTextFieldMeasurePolicy = this;
            measureScope = $this$measure_u2d3p2s80s;
            occupiedSpaceHorizontally7 = i3;
            $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
            leadingPlaceable = leadingPlaceable;
            suffixPlaceable2 = suffixPlaceable2;
            labelConstraints = labelConstraints;
            relaxedConstraints3 = relaxedConstraints3;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$maxIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int w) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(w));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$minIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int w) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(w));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth($this$maxIntrinsicWidth, list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int h) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(h));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth($this$minIntrinsicWidth, list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int h) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(h));
            }
        });
    }

    private final int intrinsicWidth(IntrinsicMeasureScope $this$intrinsicWidth, List<? extends IntrinsicMeasurable> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        Object it$iv2;
        Object it$iv3;
        int leadingWidth;
        Object it$iv4;
        Object it$iv5;
        Object obj;
        int m2356calculateWidthDHJA7U0;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldWidth = function2.invoke(item$iv$iv, Integer.valueOf(height)).intValue();
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv = null;
                        break;
                    }
                    it$iv = list.get(index$iv$iv2);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv), "Label")) {
                        break;
                    }
                    index$iv$iv2++;
                }
                IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
                int labelWidth = it != null ? function2.invoke(it, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv = list;
                int index$iv$iv3 = 0;
                int size3 = $this$fastFirstOrNull$iv.size();
                while (true) {
                    if (index$iv$iv3 >= size3) {
                        it$iv2 = null;
                        break;
                    }
                    it$iv2 = $this$fastFirstOrNull$iv.get(index$iv$iv3);
                    List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv2), "Trailing")) {
                        break;
                    }
                    index$iv$iv3++;
                    $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
                }
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
                int trailingWidth = it2 != null ? function2.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv3 = list;
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv4 = 0;
                int size4 = $this$fastFirstOrNull$iv3.size();
                while (true) {
                    if (index$iv$iv4 >= size4) {
                        it$iv3 = null;
                        break;
                    }
                    it$iv3 = $this$fastFirstOrNull$iv3.get(index$iv$iv4);
                    List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
                    int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Leading")) {
                        break;
                    }
                    index$iv$iv4++;
                    $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
                    $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                }
                IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
                int leadingWidth2 = it3 != null ? function2.invoke(it3, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv5 = list;
                int index$iv$iv5 = 0;
                int size5 = $this$fastFirstOrNull$iv5.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        leadingWidth = leadingWidth2;
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = $this$fastFirstOrNull$iv5.get(index$iv$iv5);
                    List $this$fastFirstOrNull$iv6 = $this$fastFirstOrNull$iv5;
                    leadingWidth = leadingWidth2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    index$iv$iv5++;
                    $this$fastFirstOrNull$iv5 = $this$fastFirstOrNull$iv6;
                    leadingWidth2 = leadingWidth;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
                int prefixWidth = it4 != null ? function2.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv7 = list;
                int $i$f$fastFirstOrNull3 = 0;
                int index$iv$iv6 = 0;
                int size6 = $this$fastFirstOrNull$iv7.size();
                while (true) {
                    if (index$iv$iv6 >= size6) {
                        it$iv5 = null;
                        break;
                    }
                    it$iv5 = $this$fastFirstOrNull$iv7.get(index$iv$iv6);
                    List $this$fastFirstOrNull$iv8 = $this$fastFirstOrNull$iv7;
                    int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    index$iv$iv6++;
                    $this$fastFirstOrNull$iv7 = $this$fastFirstOrNull$iv8;
                    $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
                }
                IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
                int suffixWidth = it5 != null ? function2.invoke(it5, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv9 = list;
                int $i$f$fastFirstOrNull5 = 0;
                int index$iv$iv7 = 0;
                int size7 = $this$fastFirstOrNull$iv9.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = $this$fastFirstOrNull$iv9.get(index$iv$iv7);
                    List $this$fastFirstOrNull$iv10 = $this$fastFirstOrNull$iv9;
                    int $i$f$fastFirstOrNull6 = $i$f$fastFirstOrNull5;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), "Hint")) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv7++;
                    $this$fastFirstOrNull$iv9 = $this$fastFirstOrNull$iv10;
                    $i$f$fastFirstOrNull5 = $i$f$fastFirstOrNull6;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it6 != null ? function2.invoke(it6, Integer.valueOf(height)).intValue() : 0;
                m2356calculateWidthDHJA7U0 = OutlinedTextFieldKt.m2356calculateWidthDHJA7U0(leadingWidth, trailingWidth, prefixWidth, suffixWidth, textFieldWidth, labelWidth, placeholderWidth, this.animationProgress, TextFieldImplKt.getZeroConstraints(), $this$intrinsicWidth.getDensity(), this.paddingValues);
                return m2356calculateWidthDHJA7U0;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        int i;
        Object it$iv2;
        int trailingHeight;
        int trailingHeight2;
        Object it$iv3;
        int i2;
        int labelHeight;
        Object it$iv4;
        int prefixHeight;
        int prefixHeight2;
        Object it$iv5;
        int suffixHeight;
        Object it$iv6;
        int placeholderHeight;
        Object obj;
        int m2355calculateHeightmKXJcVc;
        int substractConstraintSafely;
        int substractConstraintSafely2;
        int substractConstraintSafely3;
        int substractConstraintSafely4;
        int remainingWidth = width;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv), "Leading")) {
                break;
            }
            index$iv$iv++;
        }
        IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
        if (it != null) {
            substractConstraintSafely4 = OutlinedTextFieldKt.substractConstraintSafely(remainingWidth, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely4;
            i = function2.invoke(it, Integer.valueOf(width)).intValue();
        } else {
            i = 0;
        }
        int leadingHeight = i;
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv2), "Trailing")) {
                break;
            }
            index$iv$iv2++;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
        if (it2 != null) {
            substractConstraintSafely3 = OutlinedTextFieldKt.substractConstraintSafely(remainingWidth, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely3;
            trailingHeight = function2.invoke(it2, Integer.valueOf(width)).intValue();
        } else {
            trailingHeight = 0;
        }
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                trailingHeight2 = trailingHeight;
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            trailingHeight2 = trailingHeight;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Label")) {
                break;
            }
            index$iv$iv3++;
            trailingHeight = trailingHeight2;
        }
        IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
        if (it3 != null) {
            i2 = width;
            labelHeight = function2.invoke(it3, Integer.valueOf(MathHelpersKt.lerp(remainingWidth, i2, this.animationProgress))).intValue();
        } else {
            i2 = width;
            labelHeight = 0;
        }
        List $this$fastFirstOrNull$iv = list;
        int index$iv$iv4 = 0;
        int size4 = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv4 >= size4) {
                it$iv4 = null;
                break;
            }
            it$iv4 = $this$fastFirstOrNull$iv.get(index$iv$iv4);
            List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.PrefixId)) {
                break;
            }
            index$iv$iv4++;
            $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
        }
        IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
        if (it4 != null) {
            prefixHeight = function2.invoke(it4, Integer.valueOf(remainingWidth)).intValue();
            substractConstraintSafely2 = OutlinedTextFieldKt.substractConstraintSafely(remainingWidth, it4.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely2;
        } else {
            prefixHeight = 0;
        }
        List $this$fastFirstOrNull$iv3 = list;
        int index$iv$iv5 = 0;
        int size5 = $this$fastFirstOrNull$iv3.size();
        while (true) {
            if (index$iv$iv5 >= size5) {
                prefixHeight2 = prefixHeight;
                it$iv5 = null;
                break;
            }
            it$iv5 = $this$fastFirstOrNull$iv3.get(index$iv$iv5);
            List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
            prefixHeight2 = prefixHeight;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv5), TextFieldImplKt.SuffixId)) {
                break;
            }
            index$iv$iv5++;
            $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
            prefixHeight = prefixHeight2;
        }
        IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
        if (it5 != null) {
            suffixHeight = function2.invoke(it5, Integer.valueOf(remainingWidth)).intValue();
            substractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(remainingWidth, it5.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely;
        } else {
            suffixHeight = 0;
        }
        List $this$fastFirst$iv = list;
        int index$iv$iv6 = 0;
        int size6 = $this$fastFirst$iv.size();
        while (index$iv$iv6 < size6) {
            Object item$iv$iv = $this$fastFirst$iv.get(index$iv$iv6);
            int remainingWidth2 = remainingWidth;
            List $this$fastFirst$iv2 = $this$fastFirst$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldHeight = function2.invoke(item$iv$iv, Integer.valueOf(remainingWidth2)).intValue();
                List $this$fastFirstOrNull$iv5 = list;
                int index$iv$iv7 = 0;
                int size7 = $this$fastFirstOrNull$iv5.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        it$iv6 = null;
                        break;
                    }
                    it$iv6 = $this$fastFirstOrNull$iv5.get(index$iv$iv7);
                    List $this$fastFirstOrNull$iv6 = $this$fastFirstOrNull$iv5;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv6), "Hint")) {
                        break;
                    }
                    index$iv$iv7++;
                    $this$fastFirstOrNull$iv5 = $this$fastFirstOrNull$iv6;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) it$iv6;
                int placeholderHeight2 = it6 != null ? function2.invoke(it6, Integer.valueOf(remainingWidth2)).intValue() : 0;
                List $this$fastFirstOrNull$iv7 = list;
                int index$iv$iv8 = 0;
                int size8 = $this$fastFirstOrNull$iv7.size();
                while (true) {
                    if (index$iv$iv8 >= size8) {
                        placeholderHeight = placeholderHeight2;
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = $this$fastFirstOrNull$iv7.get(index$iv$iv8);
                    placeholderHeight = placeholderHeight2;
                    List $this$fastFirstOrNull$iv8 = $this$fastFirstOrNull$iv7;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), TextFieldImplKt.SupportingId)) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv8++;
                    placeholderHeight2 = placeholderHeight;
                    $this$fastFirstOrNull$iv7 = $this$fastFirstOrNull$iv8;
                }
                IntrinsicMeasurable it7 = (IntrinsicMeasurable) obj;
                int supportingHeight = it7 != null ? function2.invoke(it7, Integer.valueOf(i2)).intValue() : 0;
                int prefixHeight3 = prefixHeight2;
                int prefixHeight4 = placeholderHeight;
                int trailingHeight3 = trailingHeight2;
                int trailingHeight4 = suffixHeight;
                m2355calculateHeightmKXJcVc = OutlinedTextFieldKt.m2355calculateHeightmKXJcVc(leadingHeight, trailingHeight3, prefixHeight3, trailingHeight4, textFieldHeight, labelHeight, prefixHeight4, supportingHeight, this.animationProgress, TextFieldImplKt.getZeroConstraints(), $this$intrinsicHeight.getDensity(), this.paddingValues);
                return m2355calculateHeightmKXJcVc;
            }
            index$iv$iv6++;
            $this$fastFirst$iv = $this$fastFirst$iv2;
            remainingWidth = remainingWidth2;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
