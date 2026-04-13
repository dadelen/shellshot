package androidx.compose.material3;

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
import androidx.compose.ui.unit.Dp;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: NavigationItem.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\"\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J,\u0010\u001e\u001a\u00020\u001f*\u00020 2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\"\u001a\u00020#H\u0016ø\u0001\u0000¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\n\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u0019\u0010\u000b\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0016\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Landroidx/compose/material3/TopIconOrIconOnlyMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "hasLabel", "", "animationProgress", "Lkotlin/Function0;", "", "indicatorHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "indicatorVerticalPadding", "indicatorToLabelVerticalPadding", "topIconItemVerticalPadding", "(ZLkotlin/jvm/functions/Function0;FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnimationProgress", "()Lkotlin/jvm/functions/Function0;", "getHasLabel", "()Z", "getIndicatorHorizontalPadding-D9Ej5fM", "()F", "F", "getIndicatorToLabelVerticalPadding-D9Ej5fM", "getIndicatorVerticalPadding-D9Ej5fM", "getTopIconItemVerticalPadding-D9Ej5fM", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class TopIconOrIconOnlyMeasurePolicy implements MeasurePolicy {
    private final Function0<Float> animationProgress;
    private final boolean hasLabel;
    private final float indicatorHorizontalPadding;
    private final float indicatorToLabelVerticalPadding;
    private final float indicatorVerticalPadding;
    private final float topIconItemVerticalPadding;

    public /* synthetic */ TopIconOrIconOnlyMeasurePolicy(boolean z, Function0 function0, float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, f, f2, f3, f4);
    }

    private TopIconOrIconOnlyMeasurePolicy(boolean hasLabel, Function0<Float> function0, float indicatorHorizontalPadding, float indicatorVerticalPadding, float indicatorToLabelVerticalPadding, float topIconItemVerticalPadding) {
        this.hasLabel = hasLabel;
        this.animationProgress = function0;
        this.indicatorHorizontalPadding = indicatorHorizontalPadding;
        this.indicatorVerticalPadding = indicatorVerticalPadding;
        this.indicatorToLabelVerticalPadding = indicatorToLabelVerticalPadding;
        this.topIconItemVerticalPadding = topIconItemVerticalPadding;
    }

    public final boolean getHasLabel() {
        return this.hasLabel;
    }

    public final Function0<Float> getAnimationProgress() {
        return this.animationProgress;
    }

    /* renamed from: getIndicatorHorizontalPadding-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorHorizontalPadding() {
        return this.indicatorHorizontalPadding;
    }

    /* renamed from: getIndicatorVerticalPadding-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorVerticalPadding() {
        return this.indicatorVerticalPadding;
    }

    /* renamed from: getIndicatorToLabelVerticalPadding-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorToLabelVerticalPadding() {
        return this.indicatorToLabelVerticalPadding;
    }

    /* renamed from: getTopIconItemVerticalPadding-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTopIconItemVerticalPadding() {
        return this.topIconItemVerticalPadding;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        long looseConstraints;
        MeasureResult m2321placeIconX9ElhV4;
        MeasureResult m2323placeLabelAndTopIconqoqLrGI;
        MeasureScope measureScope = $this$measure_u2d3p2s80s;
        float animationProgress = this.animationProgress.invoke().floatValue();
        looseConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        List $this$fastFirst$iv = list;
        int $i$f$fastFirst = 0;
        int index$iv$iv = 0;
        int size = $this$fastFirst$iv.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = $this$fastFirst$iv.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            List $this$fastFirst$iv2 = $this$fastFirst$iv;
            int $i$f$fastFirst2 = $i$f$fastFirst;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "icon")) {
                float arg0$iv = this.indicatorHorizontalPadding;
                int i = -measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv));
                float arg0$iv2 = this.indicatorVerticalPadding;
                Placeable iconPlaceable = ((Measurable) item$iv$iv).mo5491measureBRTryo0(ConstraintsKt.m6598offsetNN6EwU(looseConstraints, i, -measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv2))));
                int width = iconPlaceable.getWidth();
                float arg0$iv3 = this.indicatorHorizontalPadding;
                int totalIndicatorWidth = width + measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv3));
                int height = iconPlaceable.getHeight();
                float arg0$iv4 = this.indicatorVerticalPadding;
                int indicatorHeight = height + measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv4));
                int animatedIndicatorWidth = MathKt.roundToInt(totalIndicatorWidth * animationProgress);
                List $this$fastFirst$iv3 = list;
                int index$iv$iv2 = 0;
                int size2 = $this$fastFirst$iv3.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = $this$fastFirst$iv3.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    Placeable iconPlaceable2 = iconPlaceable;
                    List $this$fastFirst$iv4 = $this$fastFirst$iv3;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "indicatorRipple")) {
                        Placeable indicatorRipplePlaceable = ((Measurable) item$iv$iv2).mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(looseConstraints, Constraints.INSTANCE.m6589fixedJhjzzOo(totalIndicatorWidth, indicatorHeight)));
                        int index$iv$iv3 = 0;
                        int size3 = list.size();
                        while (index$iv$iv3 < size3) {
                            Object item$iv$iv3 = list.get(index$iv$iv3);
                            Measurable it3 = (Measurable) item$iv$iv3;
                            Placeable indicatorRipplePlaceable2 = indicatorRipplePlaceable;
                            int totalIndicatorWidth2 = totalIndicatorWidth;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "indicator")) {
                                Placeable indicatorPlaceable = ((Measurable) item$iv$iv3).mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(looseConstraints, Constraints.INSTANCE.m6589fixedJhjzzOo(animatedIndicatorWidth, indicatorHeight)));
                                if (!this.hasLabel) {
                                    m2321placeIconX9ElhV4 = NavigationItemKt.m2321placeIconX9ElhV4($this$measure_u2d3p2s80s, iconPlaceable2, indicatorRipplePlaceable2, indicatorPlaceable, constraints);
                                    return m2321placeIconX9ElhV4;
                                }
                                List $this$fastFirst$iv5 = list;
                                int index$iv$iv4 = 0;
                                int size4 = $this$fastFirst$iv5.size();
                                while (index$iv$iv4 < size4) {
                                    Object item$iv$iv4 = $this$fastFirst$iv5.get(index$iv$iv4);
                                    Measurable it4 = (Measurable) item$iv$iv4;
                                    Placeable indicatorPlaceable2 = indicatorPlaceable;
                                    List $this$fastFirst$iv6 = $this$fastFirst$iv5;
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), "label")) {
                                        Placeable labelPlaceable = ((Measurable) item$iv$iv4).mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(looseConstraints, 0, -(indicatorPlaceable2.getHeight() + measureScope.mo358roundToPx0680j_4(this.indicatorToLabelVerticalPadding)), 1, null));
                                        m2323placeLabelAndTopIconqoqLrGI = NavigationItemKt.m2323placeLabelAndTopIconqoqLrGI(measureScope, labelPlaceable, iconPlaceable2, indicatorRipplePlaceable2, indicatorPlaceable2, constraints, this.indicatorToLabelVerticalPadding, this.indicatorVerticalPadding, this.topIconItemVerticalPadding);
                                        return m2323placeLabelAndTopIconqoqLrGI;
                                    }
                                    index$iv$iv4++;
                                    measureScope = $this$measure_u2d3p2s80s;
                                    indicatorPlaceable = indicatorPlaceable2;
                                    indicatorHeight = indicatorHeight;
                                    animatedIndicatorWidth = animatedIndicatorWidth;
                                    $this$fastFirst$iv5 = $this$fastFirst$iv6;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            index$iv$iv3++;
                            measureScope = $this$measure_u2d3p2s80s;
                            indicatorRipplePlaceable = indicatorRipplePlaceable2;
                            indicatorHeight = indicatorHeight;
                            totalIndicatorWidth = totalIndicatorWidth2;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                    index$iv$iv2++;
                    measureScope = $this$measure_u2d3p2s80s;
                    iconPlaceable = iconPlaceable2;
                    $this$fastFirst$iv3 = $this$fastFirst$iv4;
                    totalIndicatorWidth = totalIndicatorWidth;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            index$iv$iv++;
            measureScope = $this$measure_u2d3p2s80s;
            $this$fastFirst$iv = $this$fastFirst$iv2;
            $i$f$fastFirst = $i$f$fastFirst2;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Object it$iv;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable it = (IntrinsicMeasurable) item$iv$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it), "icon")) {
                int iconHeight = ((IntrinsicMeasurable) item$iv$iv).maxIntrinsicHeight(width);
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv = null;
                        break;
                    }
                    it$iv = list.get(index$iv$iv2);
                    IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it2), "label")) {
                        break;
                    }
                    index$iv$iv2++;
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) it$iv;
                int labelHeight = intrinsicMeasurable != null ? intrinsicMeasurable.maxIntrinsicHeight(width) : 0;
                float arg0$iv = this.topIconItemVerticalPadding;
                float arg0$iv2 = Dp.m6626constructorimpl(2 * arg0$iv);
                float arg0$iv3 = this.indicatorVerticalPadding;
                float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv2 + Dp.m6626constructorimpl(2 * arg0$iv3));
                float other$iv = this.indicatorToLabelVerticalPadding;
                int paddings = $this$maxIntrinsicHeight.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv4 + other$iv));
                return iconHeight + labelHeight + paddings;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
