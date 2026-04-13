package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
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
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J,\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean singleLine, float animationProgress, PaddingValues paddingValues) {
        this.singleLine = singleLine;
        this.animationProgress = animationProgress;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        long looseConstraints;
        int occupiedSpaceHorizontally;
        Object it$iv;
        Object it$iv2;
        long looseConstraints2;
        final Placeable trailingPlaceable;
        Object it$iv3;
        Object it$iv4;
        long looseConstraints3;
        Placeable suffixPlaceable;
        Object it$iv5;
        Object it$iv6;
        long m6569copyZbe2FdA;
        long placeholderConstraints;
        Object it$iv7;
        final int width;
        long supportingConstraints;
        int totalHeight;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope = $this$measure_u2d3p2s80s;
        final int topPaddingValue = measureScope.mo358roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int bottomPaddingValue = measureScope.mo358roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        int occupiedSpaceHorizontally2 = 0;
        looseConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
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
        Placeable leadingPlaceable = measurable != null ? measurable.mo5491measureBRTryo0(looseConstraints) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(leadingPlaceable);
        int occupiedSpaceVertically = Math.max(0, TextFieldImplKt.heightOrZero(leadingPlaceable));
        List $this$fastFirstOrNull$iv = list;
        int $i$f$fastFirstOrNull = 0;
        int index$iv$iv2 = 0;
        int size2 = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = $this$fastFirstOrNull$iv.get(index$iv$iv2);
            Measurable it2 = (Measurable) it$iv2;
            List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
            int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Trailing")) {
                break;
            }
            index$iv$iv2++;
            $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
            $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        if (measurable2 != null) {
            looseConstraints2 = looseConstraints;
            trailingPlaceable = measurable2.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints2, -occupiedSpaceHorizontally3, 0, 2, null));
        } else {
            looseConstraints2 = looseConstraints;
            trailingPlaceable = null;
        }
        int occupiedSpaceHorizontally4 = occupiedSpaceHorizontally3 + TextFieldImplKt.widthOrZero(trailingPlaceable);
        int occupiedSpaceVertically2 = Math.max(occupiedSpaceVertically, TextFieldImplKt.heightOrZero(trailingPlaceable));
        List $this$fastFirstOrNull$iv3 = list;
        int $i$f$fastFirstOrNull3 = 0;
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
            int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), TextFieldImplKt.PrefixId)) {
                break;
            }
            index$iv$iv3++;
            $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
            $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
        }
        Measurable measurable3 = (Measurable) it$iv3;
        final Placeable prefixPlaceable = measurable3 != null ? measurable3.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints2, -occupiedSpaceHorizontally4, 0, 2, null)) : null;
        int occupiedSpaceHorizontally5 = occupiedSpaceHorizontally4 + TextFieldImplKt.widthOrZero(prefixPlaceable);
        int occupiedSpaceVertically3 = Math.max(occupiedSpaceVertically2, TextFieldImplKt.heightOrZero(prefixPlaceable));
        List $this$fastFirstOrNull$iv5 = list;
        int $i$f$fastFirstOrNull5 = 0;
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
            int $i$f$fastFirstOrNull6 = $i$f$fastFirstOrNull5;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), TextFieldImplKt.SuffixId)) {
                break;
            }
            index$iv$iv4++;
            $this$fastFirstOrNull$iv5 = $this$fastFirstOrNull$iv6;
            $i$f$fastFirstOrNull5 = $i$f$fastFirstOrNull6;
        }
        Measurable measurable4 = (Measurable) it$iv4;
        if (measurable4 != null) {
            looseConstraints3 = looseConstraints2;
            suffixPlaceable = measurable4.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints2, -occupiedSpaceHorizontally5, 0, 2, null));
        } else {
            looseConstraints3 = looseConstraints2;
            suffixPlaceable = null;
        }
        int occupiedSpaceHorizontally6 = occupiedSpaceHorizontally5 + TextFieldImplKt.widthOrZero(suffixPlaceable);
        int occupiedSpaceVertically4 = Math.max(occupiedSpaceVertically3, TextFieldImplKt.heightOrZero(suffixPlaceable));
        long labelConstraints = ConstraintsKt.m6598offsetNN6EwU(looseConstraints3, -occupiedSpaceHorizontally6, -bottomPaddingValue);
        List $this$fastForEach$iv$iv = list;
        final Placeable suffixPlaceable2 = suffixPlaceable;
        int size5 = $this$fastForEach$iv$iv.size();
        int index$iv$iv5 = 0;
        while (true) {
            if (index$iv$iv5 >= size5) {
                it$iv5 = null;
                break;
            }
            int i = size5;
            List $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
            it$iv5 = $this$fastForEach$iv$iv2.get(index$iv$iv5);
            Measurable it5 = (Measurable) it$iv5;
            int index$iv$iv6 = index$iv$iv5;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it5), "Label")) {
                break;
            }
            index$iv$iv5 = index$iv$iv6 + 1;
            size5 = i;
            $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
        }
        Measurable measurable5 = (Measurable) it$iv5;
        List $this$fastForEach$iv$iv3 = list;
        final Placeable labelPlaceable = measurable5 != null ? measurable5.mo5491measureBRTryo0(labelConstraints) : null;
        int size6 = $this$fastForEach$iv$iv3.size();
        int index$iv$iv7 = 0;
        while (true) {
            if (index$iv$iv7 >= size6) {
                it$iv6 = null;
                break;
            }
            List $this$fastForEach$iv$iv4 = $this$fastForEach$iv$iv3;
            it$iv6 = $this$fastForEach$iv$iv4.get(index$iv$iv7);
            Measurable it6 = (Measurable) it$iv6;
            int i2 = size6;
            int index$iv$iv8 = index$iv$iv7;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it6), TextFieldImplKt.SupportingId)) {
                break;
            }
            index$iv$iv7 = index$iv$iv8 + 1;
            $this$fastForEach$iv$iv3 = $this$fastForEach$iv$iv4;
            size6 = i2;
        }
        Measurable supportingMeasurable = (Measurable) it$iv6;
        int supportingIntrinsicHeight = supportingMeasurable != null ? supportingMeasurable.minIntrinsicHeight(Constraints.m6581getMinWidthimpl(constraints)) : 0;
        int effectiveTopOffset = TextFieldImplKt.heightOrZero(labelPlaceable) + topPaddingValue;
        final Placeable leadingPlaceable2 = leadingPlaceable;
        m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        long textFieldConstraints = ConstraintsKt.m6598offsetNN6EwU(m6569copyZbe2FdA, -occupiedSpaceHorizontally6, ((-effectiveTopOffset) - bottomPaddingValue) - supportingIntrinsicHeight);
        int $i$f$fastFirst = 0;
        List $this$fastForEach$iv$iv5 = list;
        int occupiedSpaceHorizontally7 = $this$fastForEach$iv$iv5.size();
        int index$iv$iv9 = 0;
        while (true) {
            int index$iv$iv10 = $i$f$fastFirst;
            if (index$iv$iv9 >= occupiedSpaceHorizontally7) {
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            int i3 = occupiedSpaceHorizontally7;
            List $this$fastForEach$iv$iv6 = $this$fastForEach$iv$iv5;
            Object item$iv$iv = $this$fastForEach$iv$iv6.get(index$iv$iv9);
            Measurable it7 = (Measurable) item$iv$iv;
            int index$iv$iv11 = index$iv$iv9;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it7), "TextField")) {
                final Placeable textFieldPlaceable = ((Measurable) item$iv$iv).mo5491measureBRTryo0(textFieldConstraints);
                placeholderConstraints = Constraints.m6569copyZbe2FdA(textFieldConstraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(textFieldConstraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(textFieldConstraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(textFieldConstraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(textFieldConstraints) : 0);
                List $this$fastForEach$iv$iv7 = list;
                int size7 = $this$fastForEach$iv$iv7.size();
                int index$iv$iv12 = 0;
                while (true) {
                    if (index$iv$iv12 >= size7) {
                        it$iv7 = null;
                        break;
                    }
                    int i4 = size7;
                    List $this$fastForEach$iv$iv8 = $this$fastForEach$iv$iv7;
                    it$iv7 = $this$fastForEach$iv$iv8.get(index$iv$iv12);
                    Measurable it8 = (Measurable) it$iv7;
                    int index$iv$iv13 = index$iv$iv12;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it8), "Hint")) {
                        break;
                    }
                    index$iv$iv12 = index$iv$iv13 + 1;
                    size7 = i4;
                    $this$fastForEach$iv$iv7 = $this$fastForEach$iv$iv8;
                }
                Measurable measurable6 = (Measurable) it$iv7;
                final Placeable placeholderPlaceable = measurable6 != null ? measurable6.mo5491measureBRTryo0(placeholderConstraints) : null;
                int occupiedSpaceVertically5 = Math.max(occupiedSpaceVertically4, Math.max(TextFieldImplKt.heightOrZero(textFieldPlaceable), TextFieldImplKt.heightOrZero(placeholderPlaceable)) + effectiveTopOffset + bottomPaddingValue);
                width = TextFieldKt.m2695calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(leadingPlaceable2), TextFieldImplKt.widthOrZero(trailingPlaceable), TextFieldImplKt.widthOrZero(prefixPlaceable), TextFieldImplKt.widthOrZero(suffixPlaceable2), textFieldPlaceable.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable), TextFieldImplKt.widthOrZero(placeholderPlaceable), constraints);
                supportingConstraints = Constraints.m6569copyZbe2FdA(r3, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(r3) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(r3) : width, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(r3) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints3, 0, -occupiedSpaceVertically5, 1, null)) : 0);
                Placeable supportingPlaceable = supportingMeasurable != null ? supportingMeasurable.mo5491measureBRTryo0(supportingConstraints) : null;
                int supportingHeight = TextFieldImplKt.heightOrZero(supportingPlaceable);
                totalHeight = TextFieldKt.m2694calculateHeightmKXJcVc(textFieldPlaceable.getHeight(), TextFieldImplKt.heightOrZero(labelPlaceable), TextFieldImplKt.heightOrZero(leadingPlaceable2), TextFieldImplKt.heightOrZero(trailingPlaceable), TextFieldImplKt.heightOrZero(prefixPlaceable), TextFieldImplKt.heightOrZero(suffixPlaceable2), TextFieldImplKt.heightOrZero(placeholderPlaceable), TextFieldImplKt.heightOrZero(supportingPlaceable), textFieldMeasurePolicy.animationProgress, constraints, measureScope.getDensity(), textFieldMeasurePolicy.paddingValues);
                int height = totalHeight - supportingHeight;
                List $this$fastForEach$iv$iv9 = list;
                int height2 = totalHeight;
                int totalHeight2 = $this$fastForEach$iv$iv9.size();
                int totalHeight3 = 0;
                while (totalHeight3 < totalHeight2) {
                    int i5 = totalHeight2;
                    List $this$fastForEach$iv$iv10 = $this$fastForEach$iv$iv9;
                    Object item$iv$iv2 = $this$fastForEach$iv$iv10.get(totalHeight3);
                    Measurable it9 = (Measurable) item$iv$iv2;
                    int index$iv$iv14 = totalHeight3;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it9), TextFieldImplKt.ContainerId)) {
                        final Placeable containerPlaceable = ((Measurable) item$iv$iv2).mo5491measureBRTryo0(ConstraintsKt.Constraints(width != Integer.MAX_VALUE ? width : 0, width, height != Integer.MAX_VALUE ? height : 0, height));
                        final Placeable leadingPlaceable3 = supportingPlaceable;
                        final int totalHeight4 = height2;
                        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, totalHeight4, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
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
                                boolean z;
                                PaddingValues paddingValues;
                                boolean z2;
                                float f;
                                Placeable placeable = Placeable.this;
                                int i6 = width;
                                if (placeable != null) {
                                    int i7 = totalHeight4;
                                    Placeable placeable2 = textFieldPlaceable;
                                    Placeable placeable3 = Placeable.this;
                                    Placeable placeable4 = placeholderPlaceable;
                                    Placeable placeable5 = leadingPlaceable2;
                                    Placeable placeable6 = trailingPlaceable;
                                    Placeable placeable7 = prefixPlaceable;
                                    Placeable placeable8 = suffixPlaceable2;
                                    Placeable placeable9 = containerPlaceable;
                                    Placeable placeable10 = leadingPlaceable3;
                                    z2 = textFieldMeasurePolicy.singleLine;
                                    int i8 = topPaddingValue;
                                    int height3 = topPaddingValue + Placeable.this.getHeight();
                                    f = textFieldMeasurePolicy.animationProgress;
                                    TextFieldKt.placeWithLabel($this$layout, i6, i7, placeable2, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, placeable9, placeable10, z2, i8, height3, f, measureScope.getDensity());
                                    return;
                                }
                                int i9 = totalHeight4;
                                Placeable placeable11 = textFieldPlaceable;
                                Placeable placeable12 = placeholderPlaceable;
                                Placeable placeable13 = leadingPlaceable2;
                                Placeable placeable14 = trailingPlaceable;
                                Placeable placeable15 = prefixPlaceable;
                                Placeable placeable16 = suffixPlaceable2;
                                Placeable placeable17 = containerPlaceable;
                                Placeable placeable18 = leadingPlaceable3;
                                z = textFieldMeasurePolicy.singleLine;
                                float density = measureScope.getDensity();
                                paddingValues = textFieldMeasurePolicy.paddingValues;
                                TextFieldKt.placeWithoutLabel($this$layout, i6, i9, placeable11, placeable12, placeable13, placeable14, placeable15, placeable16, placeable17, placeable18, z, density, paddingValues);
                            }
                        }, 4, null);
                    }
                    textFieldMeasurePolicy = this;
                    measureScope = $this$measure_u2d3p2s80s;
                    height = height;
                    $this$fastForEach$iv$iv9 = $this$fastForEach$iv$iv10;
                    height2 = height2;
                    totalHeight3 = index$iv$iv14 + 1;
                    totalHeight2 = i5;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            index$iv$iv9 = index$iv$iv11 + 1;
            textFieldMeasurePolicy = this;
            measureScope = $this$measure_u2d3p2s80s;
            $i$f$fastFirst = index$iv$iv10;
            occupiedSpaceHorizontally7 = i3;
            $this$fastForEach$iv$iv5 = $this$fastForEach$iv$iv6;
        }
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$maxIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicHeight$1
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
        return intrinsicHeight($this$minIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicHeight$1
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
        return intrinsicWidth(list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicWidth$1
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
        return intrinsicWidth(list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int h) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(h));
            }
        });
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        Object it$iv;
        Object it$iv2;
        Object it$iv3;
        Object it$iv4;
        Object it$iv5;
        int leadingWidth;
        Object obj;
        int m2695calculateWidthyeHjK3Y;
        int size = measurables.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = measurables.get(index$iv$iv);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldWidth = intrinsicMeasurer.invoke(item$iv$iv, Integer.valueOf(height)).intValue();
                int index$iv$iv2 = 0;
                int size2 = measurables.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv = null;
                        break;
                    }
                    it$iv = measurables.get(index$iv$iv2);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv), "Label")) {
                        break;
                    }
                    index$iv$iv2++;
                }
                IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
                int labelWidth = it != null ? intrinsicMeasurer.invoke(it, Integer.valueOf(height)).intValue() : 0;
                int index$iv$iv3 = 0;
                int size3 = measurables.size();
                while (true) {
                    if (index$iv$iv3 >= size3) {
                        it$iv2 = null;
                        break;
                    }
                    it$iv2 = measurables.get(index$iv$iv3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv2), "Trailing")) {
                        break;
                    }
                    index$iv$iv3++;
                }
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
                int trailingWidth = it2 != null ? intrinsicMeasurer.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv = measurables;
                int index$iv$iv4 = 0;
                int size4 = $this$fastFirstOrNull$iv.size();
                while (true) {
                    if (index$iv$iv4 >= size4) {
                        it$iv3 = null;
                        break;
                    }
                    it$iv3 = $this$fastFirstOrNull$iv.get(index$iv$iv4);
                    List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    index$iv$iv4++;
                    $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
                }
                IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
                int prefixWidth = it3 != null ? intrinsicMeasurer.invoke(it3, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv3 = measurables;
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv5 = 0;
                int size5 = $this$fastFirstOrNull$iv3.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = $this$fastFirstOrNull$iv3.get(index$iv$iv5);
                    List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
                    int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    index$iv$iv5++;
                    $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
                    $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
                int suffixWidth = it4 != null ? intrinsicMeasurer.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv5 = measurables;
                int $i$f$fastFirstOrNull3 = 0;
                int index$iv$iv6 = 0;
                int size6 = $this$fastFirstOrNull$iv5.size();
                while (true) {
                    if (index$iv$iv6 >= size6) {
                        it$iv5 = null;
                        break;
                    }
                    it$iv5 = $this$fastFirstOrNull$iv5.get(index$iv$iv6);
                    List $this$fastFirstOrNull$iv6 = $this$fastFirstOrNull$iv5;
                    int $i$f$fastFirstOrNull4 = $i$f$fastFirstOrNull3;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv5), "Leading")) {
                        break;
                    }
                    index$iv$iv6++;
                    $this$fastFirstOrNull$iv5 = $this$fastFirstOrNull$iv6;
                    $i$f$fastFirstOrNull3 = $i$f$fastFirstOrNull4;
                }
                IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
                int leadingWidth2 = it5 != null ? intrinsicMeasurer.invoke(it5, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv7 = measurables;
                int index$iv$iv7 = 0;
                int size7 = $this$fastFirstOrNull$iv7.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        leadingWidth = leadingWidth2;
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = $this$fastFirstOrNull$iv7.get(index$iv$iv7);
                    List $this$fastFirstOrNull$iv8 = $this$fastFirstOrNull$iv7;
                    leadingWidth = leadingWidth2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), "Hint")) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv7++;
                    $this$fastFirstOrNull$iv7 = $this$fastFirstOrNull$iv8;
                    leadingWidth2 = leadingWidth;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it6 != null ? intrinsicMeasurer.invoke(it6, Integer.valueOf(height)).intValue() : 0;
                m2695calculateWidthyeHjK3Y = TextFieldKt.m2695calculateWidthyeHjK3Y(leadingWidth, trailingWidth, prefixWidth, suffixWidth, textFieldWidth, labelWidth, placeholderWidth, TextFieldImplKt.getZeroConstraints());
                return m2695calculateWidthyeHjK3Y;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        int i;
        Object it$iv2;
        int trailingHeight;
        Object it$iv3;
        int labelHeight;
        Object it$iv4;
        int prefixHeight;
        Object it$iv5;
        int suffixHeight;
        int textFieldHeight;
        Object it$iv6;
        Object item$iv$iv;
        int m2694calculateHeightmKXJcVc;
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
            substractConstraintSafely4 = TextFieldKt.substractConstraintSafely(remainingWidth, it.maxIntrinsicWidth(Integer.MAX_VALUE));
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
            substractConstraintSafely3 = TextFieldKt.substractConstraintSafely(remainingWidth, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely3;
            trailingHeight = function2.invoke(it2, Integer.valueOf(width)).intValue();
        } else {
            trailingHeight = 0;
        }
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Label")) {
                break;
            }
            index$iv$iv3++;
        }
        IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
        int labelHeight2 = it3 != null ? function2.invoke(it3, Integer.valueOf(remainingWidth)).intValue() : 0;
        int index$iv$iv4 = 0;
        int size4 = list.size();
        while (true) {
            if (index$iv$iv4 >= size4) {
                labelHeight = labelHeight2;
                it$iv4 = null;
                break;
            }
            it$iv4 = list.get(index$iv$iv4);
            labelHeight = labelHeight2;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), TextFieldImplKt.PrefixId)) {
                break;
            }
            index$iv$iv4++;
            labelHeight2 = labelHeight;
        }
        IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
        if (it4 != null) {
            prefixHeight = function2.invoke(it4, Integer.valueOf(remainingWidth)).intValue();
            substractConstraintSafely2 = TextFieldKt.substractConstraintSafely(remainingWidth, it4.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely2;
        } else {
            prefixHeight = 0;
        }
        List $this$fastFirstOrNull$iv = list;
        int index$iv$iv5 = 0;
        int size5 = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv5 >= size5) {
                it$iv5 = null;
                break;
            }
            it$iv5 = $this$fastFirstOrNull$iv.get(index$iv$iv5);
            List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv5), TextFieldImplKt.SuffixId)) {
                break;
            }
            index$iv$iv5++;
            $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
        }
        IntrinsicMeasurable it5 = (IntrinsicMeasurable) it$iv5;
        if (it5 != null) {
            int intValue = function2.invoke(it5, Integer.valueOf(remainingWidth)).intValue();
            substractConstraintSafely = TextFieldKt.substractConstraintSafely(remainingWidth, it5.maxIntrinsicWidth(Integer.MAX_VALUE));
            remainingWidth = substractConstraintSafely;
            suffixHeight = intValue;
        } else {
            suffixHeight = 0;
        }
        int index$iv$iv6 = 0;
        int size6 = list.size();
        while (index$iv$iv6 < size6) {
            Object item$iv$iv2 = list.get(index$iv$iv6);
            int remainingWidth2 = remainingWidth;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), "TextField")) {
                int textFieldHeight2 = function2.invoke(item$iv$iv2, Integer.valueOf(remainingWidth2)).intValue();
                int index$iv$iv7 = 0;
                int size7 = list.size();
                while (true) {
                    if (index$iv$iv7 >= size7) {
                        textFieldHeight = textFieldHeight2;
                        it$iv6 = null;
                        break;
                    }
                    it$iv6 = list.get(index$iv$iv7);
                    textFieldHeight = textFieldHeight2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv6), "Hint")) {
                        break;
                    }
                    index$iv$iv7++;
                    textFieldHeight2 = textFieldHeight;
                }
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) it$iv6;
                int placeholderHeight = it6 != null ? function2.invoke(it6, Integer.valueOf(remainingWidth2)).intValue() : 0;
                int index$iv$iv8 = 0;
                int size8 = list.size();
                while (true) {
                    if (index$iv$iv8 >= size8) {
                        item$iv$iv = null;
                        break;
                    }
                    Object item$iv$iv3 = list.get(index$iv$iv8);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv3), TextFieldImplKt.SupportingId)) {
                        item$iv$iv = item$iv$iv3;
                        break;
                    }
                    index$iv$iv8++;
                }
                IntrinsicMeasurable it7 = (IntrinsicMeasurable) item$iv$iv;
                int supportingHeight = it7 != null ? function2.invoke(it7, Integer.valueOf(width)).intValue() : 0;
                int textFieldHeight3 = textFieldHeight;
                int textFieldHeight4 = labelHeight;
                int labelHeight3 = prefixHeight;
                m2694calculateHeightmKXJcVc = TextFieldKt.m2694calculateHeightmKXJcVc(textFieldHeight3, textFieldHeight4, leadingHeight, trailingHeight, labelHeight3, suffixHeight, placeholderHeight, supportingHeight, this.animationProgress, TextFieldImplKt.getZeroConstraints(), $this$intrinsicHeight.getDensity(), this.paddingValues);
                return m2694calculateHeightmKXJcVc;
            }
            index$iv$iv6++;
            remainingWidth = remainingWidth2;
            labelHeight = labelHeight;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
