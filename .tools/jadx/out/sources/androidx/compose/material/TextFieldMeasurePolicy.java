package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.layout.AlignmentLineKt;
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
import kotlin.ranges.RangesKt;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J,\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/material/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
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
        Placeable leadingPlaceable;
        long looseConstraints2;
        Object it$iv2;
        long looseConstraints3;
        final Placeable leadingPlaceable2;
        Object it$iv3;
        int it;
        long m6569copyZbe2FdA;
        long placeholderConstraints;
        Object it$iv4;
        final int width;
        final int height;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope = $this$measure_u2d3p2s80s;
        final int topPaddingValue = measureScope.mo358roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int bottomPaddingValue = measureScope.mo358roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        final int topPadding = measureScope.mo358roundToPx0680j_4(TextFieldKt.getTextFieldTopPadding());
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
            Measurable it2 = (Measurable) it$iv;
            occupiedSpaceHorizontally = occupiedSpaceHorizontally2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Leading")) {
                break;
            }
            index$iv$iv++;
            occupiedSpaceHorizontally2 = occupiedSpaceHorizontally;
        }
        Measurable measurable = (Measurable) it$iv;
        Placeable leadingPlaceable3 = measurable != null ? measurable.mo5491measureBRTryo0(looseConstraints) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(leadingPlaceable3);
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                leadingPlaceable = leadingPlaceable3;
                looseConstraints2 = looseConstraints;
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            Measurable it3 = (Measurable) it$iv2;
            leadingPlaceable = leadingPlaceable3;
            looseConstraints2 = looseConstraints;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "Trailing")) {
                break;
            }
            index$iv$iv2++;
            looseConstraints = looseConstraints2;
            leadingPlaceable3 = leadingPlaceable;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        if (measurable2 != null) {
            looseConstraints3 = looseConstraints2;
            leadingPlaceable2 = measurable2.mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints3, -occupiedSpaceHorizontally3, 0, 2, null));
        } else {
            looseConstraints3 = looseConstraints2;
            leadingPlaceable2 = null;
        }
        int occupiedSpaceHorizontally4 = TextFieldImplKt.widthOrZero(leadingPlaceable2) + occupiedSpaceHorizontally3;
        int occupiedSpaceHorizontally5 = -bottomPaddingValue;
        long labelConstraints = ConstraintsKt.m6598offsetNN6EwU(looseConstraints3, -occupiedSpaceHorizontally4, occupiedSpaceHorizontally5);
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            Measurable it4 = (Measurable) it$iv3;
            int i = size3;
            long looseConstraints4 = looseConstraints3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), "Label")) {
                break;
            }
            index$iv$iv3++;
            size3 = i;
            looseConstraints3 = looseConstraints4;
        }
        Measurable measurable3 = (Measurable) it$iv3;
        Placeable labelPlaceable = measurable3 != null ? measurable3.mo5491measureBRTryo0(labelConstraints) : null;
        if (labelPlaceable != null) {
            it = labelPlaceable.get(AlignmentLineKt.getLastBaseline());
            if (it == Integer.MIN_VALUE) {
                it = labelPlaceable.getHeight();
            }
        } else {
            it = 0;
        }
        final int effectiveLabelBaseline = Math.max(it, topPaddingValue);
        int topPaddingValue2 = labelPlaceable != null ? ((-bottomPaddingValue) - topPadding) - effectiveLabelBaseline : (-topPaddingValue) - bottomPaddingValue;
        final int lastBaseline = it;
        m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        long textFieldConstraints = ConstraintsKt.m6598offsetNN6EwU(m6569copyZbe2FdA, -occupiedSpaceHorizontally4, topPaddingValue2);
        List $this$fastForEach$iv$iv = list;
        int size4 = $this$fastForEach$iv$iv.size();
        int occupiedSpaceHorizontally6 = 0;
        while (occupiedSpaceHorizontally6 < size4) {
            final Placeable labelPlaceable2 = labelPlaceable;
            List $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
            Object item$iv$iv = $this$fastForEach$iv$iv2.get(occupiedSpaceHorizontally6);
            Measurable it5 = (Measurable) item$iv$iv;
            int index$iv$iv4 = occupiedSpaceHorizontally6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it5), "TextField")) {
                final Placeable textFieldPlaceable = ((Measurable) item$iv$iv).mo5491measureBRTryo0(textFieldConstraints);
                placeholderConstraints = Constraints.m6569copyZbe2FdA(textFieldConstraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(textFieldConstraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(textFieldConstraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(textFieldConstraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(textFieldConstraints) : 0);
                int size5 = list.size();
                int index$iv$iv5 = 0;
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = list.get(index$iv$iv5);
                    Measurable it6 = (Measurable) it$iv4;
                    int i2 = size5;
                    int index$iv$iv6 = index$iv$iv5;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it6), "Hint")) {
                        break;
                    }
                    index$iv$iv5 = index$iv$iv6 + 1;
                    size5 = i2;
                }
                Measurable measurable4 = (Measurable) it$iv4;
                Placeable placeholderPlaceable = measurable4 != null ? measurable4.mo5491measureBRTryo0(placeholderConstraints) : null;
                width = TextFieldKt.m1718calculateWidthVsPV1Ek(TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(leadingPlaceable2), textFieldPlaceable.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable2), TextFieldImplKt.widthOrZero(placeholderPlaceable), constraints);
                height = TextFieldKt.m1717calculateHeightO3s9Psw(textFieldPlaceable.getHeight(), labelPlaceable2 != null, effectiveLabelBaseline, TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(leadingPlaceable2), TextFieldImplKt.heightOrZero(placeholderPlaceable), constraints, measureScope.getDensity(), textFieldMeasurePolicy.paddingValues);
                final Placeable placeholderPlaceable2 = placeholderPlaceable;
                final Placeable leadingPlaceable4 = leadingPlaceable;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$measure$1
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
                        if (Placeable.this != null) {
                            int labelEndPosition = RangesKt.coerceAtLeast(topPaddingValue - lastBaseline, 0);
                            int i3 = width;
                            int i4 = height;
                            Placeable placeable = textFieldPlaceable;
                            Placeable placeable2 = Placeable.this;
                            Placeable placeable3 = placeholderPlaceable2;
                            Placeable placeable4 = leadingPlaceable4;
                            Placeable placeable5 = leadingPlaceable2;
                            z2 = textFieldMeasurePolicy.singleLine;
                            int i5 = effectiveLabelBaseline + topPadding;
                            f = textFieldMeasurePolicy.animationProgress;
                            TextFieldKt.placeWithLabel($this$layout, i3, i4, placeable, placeable2, placeable3, placeable4, placeable5, z2, labelEndPosition, i5, f, measureScope.getDensity());
                            return;
                        }
                        int i6 = width;
                        int i7 = height;
                        Placeable placeable6 = textFieldPlaceable;
                        Placeable placeable7 = placeholderPlaceable2;
                        Placeable placeable8 = leadingPlaceable4;
                        Placeable placeable9 = leadingPlaceable2;
                        z = textFieldMeasurePolicy.singleLine;
                        float density = measureScope.getDensity();
                        paddingValues = textFieldMeasurePolicy.paddingValues;
                        TextFieldKt.placeWithoutLabel($this$layout, i6, i7, placeable6, placeable7, placeable8, placeable9, z, density, paddingValues);
                    }
                }, 4, null);
            }
            Placeable trailingPlaceable = leadingPlaceable2;
            Placeable trailingPlaceable2 = leadingPlaceable;
            int verticalConstraintOffset = topPaddingValue2;
            occupiedSpaceHorizontally6 = index$iv$iv4 + 1;
            measureScope = $this$measure_u2d3p2s80s;
            topPaddingValue2 = verticalConstraintOffset;
            labelPlaceable = labelPlaceable2;
            $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
            leadingPlaceable = trailingPlaceable2;
            leadingPlaceable2 = trailingPlaceable;
            textFieldMeasurePolicy = this;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$maxIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$maxIntrinsicHeight$1
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
        return intrinsicHeight($this$minIntrinsicHeight, list, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$minIntrinsicHeight$1
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
        return intrinsicWidth(list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$maxIntrinsicWidth$1
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
        return intrinsicWidth(list, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$minIntrinsicWidth$1
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
        int leadingWidth;
        Object obj;
        int m1718calculateWidthVsPV1Ek;
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
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Leading")) {
                        break;
                    }
                    index$iv$iv4++;
                    $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
                }
                IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
                int leadingWidth2 = it3 != null ? intrinsicMeasurer.invoke(it3, Integer.valueOf(height)).intValue() : 0;
                List $this$fastFirstOrNull$iv3 = measurables;
                int index$iv$iv5 = 0;
                int size5 = $this$fastFirstOrNull$iv3.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        leadingWidth = leadingWidth2;
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = $this$fastFirstOrNull$iv3.get(index$iv$iv5);
                    List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
                    leadingWidth = leadingWidth2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), "Hint")) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv5++;
                    $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
                    leadingWidth2 = leadingWidth;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it4 != null ? intrinsicMeasurer.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                m1718calculateWidthVsPV1Ek = TextFieldKt.m1718calculateWidthVsPV1Ek(leadingWidth, trailingWidth, textFieldWidth, labelWidth, placeholderWidth, TextFieldImplKt.getZeroConstraints());
                return m1718calculateWidthVsPV1Ek;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        int i;
        Object it$iv2;
        int trailingHeight;
        int remainingWidth;
        Object it$iv3;
        Object it$iv4;
        int m1717calculateHeightO3s9Psw;
        int remainingWidth2 = width;
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
            remainingWidth2 = TextFieldKt.substractConstraintSafely(remainingWidth2, it.maxIntrinsicWidth(Integer.MAX_VALUE));
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
            remainingWidth2 = TextFieldKt.substractConstraintSafely(remainingWidth2, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            trailingHeight = function2.invoke(it2, Integer.valueOf(width)).intValue();
        } else {
            trailingHeight = 0;
        }
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                remainingWidth = remainingWidth2;
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            remainingWidth = remainingWidth2;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Label")) {
                break;
            }
            index$iv$iv3++;
            remainingWidth2 = remainingWidth;
        }
        IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
        int labelHeight = it3 != null ? function2.invoke(it3, Integer.valueOf(remainingWidth)).intValue() : 0;
        List $this$fastFirst$iv = list;
        int index$iv$iv4 = 0;
        int size4 = $this$fastFirst$iv.size();
        while (index$iv$iv4 < size4) {
            Object item$iv$iv = $this$fastFirst$iv.get(index$iv$iv4);
            List $this$fastFirst$iv2 = $this$fastFirst$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldHeight = function2.invoke(item$iv$iv, Integer.valueOf(remainingWidth)).intValue();
                List $this$fastFirstOrNull$iv = list;
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv5 = 0;
                int size5 = $this$fastFirstOrNull$iv.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = $this$fastFirstOrNull$iv.get(index$iv$iv5);
                    List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
                    int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), "Hint")) {
                        break;
                    }
                    index$iv$iv5++;
                    $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
                    $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
                int placeholderHeight = it4 != null ? function2.invoke(it4, Integer.valueOf(remainingWidth)).intValue() : 0;
                m1717calculateHeightO3s9Psw = TextFieldKt.m1717calculateHeightO3s9Psw(textFieldHeight, labelHeight > 0, labelHeight, leadingHeight, trailingHeight, placeholderHeight, TextFieldImplKt.getZeroConstraints(), $this$intrinsicHeight.getDensity(), this.paddingValues);
                return m1717calculateHeightO3s9Psw;
            }
            index$iv$iv4++;
            $this$fastFirst$iv = $this$fastFirst$iv2;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
