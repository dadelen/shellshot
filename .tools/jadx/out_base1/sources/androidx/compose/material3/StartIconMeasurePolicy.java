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
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\"\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\"\u0010\u0018\u001a\u00020\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J,\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00152\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b \u0010!R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\b\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\""}, d2 = {"Landroidx/compose/material3/StartIconMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "animationProgress", "Lkotlin/Function0;", "", "indicatorHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "indicatorVerticalPadding", "startIconToLabelHorizontalPadding", "(Lkotlin/jvm/functions/Function0;FFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnimationProgress", "()Lkotlin/jvm/functions/Function0;", "getIndicatorHorizontalPadding-D9Ej5fM", "()F", "F", "getIndicatorVerticalPadding-D9Ej5fM", "getStartIconToLabelHorizontalPadding-D9Ej5fM", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class StartIconMeasurePolicy implements MeasurePolicy {
    private final Function0<Float> animationProgress;
    private final float indicatorHorizontalPadding;
    private final float indicatorVerticalPadding;
    private final float startIconToLabelHorizontalPadding;

    public /* synthetic */ StartIconMeasurePolicy(Function0 function0, float f, float f2, float f3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, f, f2, f3);
    }

    private StartIconMeasurePolicy(Function0<Float> function0, float indicatorHorizontalPadding, float indicatorVerticalPadding, float startIconToLabelHorizontalPadding) {
        this.animationProgress = function0;
        this.indicatorHorizontalPadding = indicatorHorizontalPadding;
        this.indicatorVerticalPadding = indicatorVerticalPadding;
        this.startIconToLabelHorizontalPadding = startIconToLabelHorizontalPadding;
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

    /* renamed from: getStartIconToLabelHorizontalPadding-D9Ej5fM, reason: not valid java name and from getter */
    public final float getStartIconToLabelHorizontalPadding() {
        return this.startIconToLabelHorizontalPadding;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        long looseConstraints;
        MeasureResult m2322placeLabelAndStartIconnru01g4;
        MeasureScope measureScope = $this$measure_u2d3p2s80s;
        float animationProgress = this.animationProgress.invoke().floatValue();
        looseConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        float arg0$iv = this.indicatorHorizontalPadding;
        int i = -measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv));
        float arg0$iv2 = this.indicatorVerticalPadding;
        long iconConstraints = ConstraintsKt.m6598offsetNN6EwU(looseConstraints, i, -measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv2)));
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
                Placeable iconPlaceable = ((Measurable) item$iv$iv).mo5491measureBRTryo0(iconConstraints);
                List $this$fastFirst$iv3 = list;
                int index$iv$iv2 = 0;
                int size2 = $this$fastFirst$iv3.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = $this$fastFirst$iv3.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    List $this$fastFirst$iv4 = $this$fastFirst$iv3;
                    Placeable iconPlaceable2 = iconPlaceable;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "label")) {
                        Placeable labelPlaceable = ((Measurable) item$iv$iv2).mo5491measureBRTryo0(ConstraintsKt.m6599offsetNN6EwU$default(iconConstraints, -(iconPlaceable2.getWidth() + measureScope.mo358roundToPx0680j_4(this.startIconToLabelHorizontalPadding)), 0, 2, null));
                        int width = iconPlaceable2.getWidth() + labelPlaceable.getWidth();
                        float arg0$iv3 = this.startIconToLabelHorizontalPadding;
                        float arg0$iv4 = this.indicatorHorizontalPadding;
                        int totalIndicatorWidth = width + measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv3 + Dp.m6626constructorimpl(2 * arg0$iv4)));
                        int max = Math.max(iconPlaceable2.getHeight(), labelPlaceable.getHeight());
                        float arg0$iv5 = this.indicatorVerticalPadding;
                        int indicatorHeight = max + measureScope.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv5));
                        int animatedIndicatorWidth = MathKt.roundToInt(totalIndicatorWidth * animationProgress);
                        int size3 = list.size();
                        Placeable labelPlaceable2 = labelPlaceable;
                        int index$iv$iv3 = 0;
                        while (index$iv$iv3 < size3) {
                            Object item$iv$iv3 = list.get(index$iv$iv3);
                            Measurable it3 = (Measurable) item$iv$iv3;
                            int i2 = size3;
                            int index$iv$iv4 = index$iv$iv3;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "indicatorRipple")) {
                                Placeable indicatorRipplePlaceable = ((Measurable) item$iv$iv3).mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(looseConstraints, Constraints.INSTANCE.m6589fixedJhjzzOo(totalIndicatorWidth, indicatorHeight)));
                                int $i$f$fastFirst3 = 0;
                                int index$iv$iv5 = 0;
                                int size4 = list.size();
                                while (index$iv$iv5 < size4) {
                                    Object item$iv$iv4 = list.get(index$iv$iv5);
                                    Measurable it4 = (Measurable) item$iv$iv4;
                                    int i3 = size4;
                                    int $i$f$fastFirst4 = $i$f$fastFirst3;
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), "indicator")) {
                                        Placeable indicatorPlaceable = ((Measurable) item$iv$iv4).mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(looseConstraints, Constraints.INSTANCE.m6589fixedJhjzzOo(animatedIndicatorWidth, indicatorHeight)));
                                        m2322placeLabelAndStartIconnru01g4 = NavigationItemKt.m2322placeLabelAndStartIconnru01g4($this$measure_u2d3p2s80s, labelPlaceable2, iconPlaceable2, indicatorRipplePlaceable, indicatorPlaceable, constraints, this.startIconToLabelHorizontalPadding);
                                        return m2322placeLabelAndStartIconnru01g4;
                                    }
                                    index$iv$iv5++;
                                    animatedIndicatorWidth = animatedIndicatorWidth;
                                    size4 = i3;
                                    labelPlaceable2 = labelPlaceable2;
                                    $i$f$fastFirst3 = $i$f$fastFirst4;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            animatedIndicatorWidth = animatedIndicatorWidth;
                            labelPlaceable2 = labelPlaceable2;
                            index$iv$iv3 = index$iv$iv4 + 1;
                            size3 = i2;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                    iconPlaceable = iconPlaceable2;
                    index$iv$iv2++;
                    measureScope = $this$measure_u2d3p2s80s;
                    $this$fastFirst$iv3 = $this$fastFirst$iv4;
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
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable it = (IntrinsicMeasurable) item$iv$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it), "icon")) {
                int iconWidth = ((IntrinsicMeasurable) item$iv$iv).maxIntrinsicWidth(height);
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    IntrinsicMeasurable it2 = (IntrinsicMeasurable) item$iv$iv2;
                    int iconWidth2 = iconWidth;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it2), "label")) {
                        int labelWidth = ((IntrinsicMeasurable) item$iv$iv2).maxIntrinsicWidth(height);
                        float arg0$iv = this.indicatorHorizontalPadding;
                        float arg0$iv2 = Dp.m6626constructorimpl(2 * arg0$iv);
                        float other$iv = this.startIconToLabelHorizontalPadding;
                        int paddings = $this$maxIntrinsicWidth.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv2 + other$iv));
                        return iconWidth2 + labelWidth + paddings;
                    }
                    index$iv$iv2++;
                    iconWidth = iconWidth2;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            IntrinsicMeasurable it = (IntrinsicMeasurable) item$iv$iv;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it), "icon")) {
                int iconHeight = ((IntrinsicMeasurable) item$iv$iv).maxIntrinsicHeight(width);
                int size2 = list.size();
                for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    IntrinsicMeasurable it2 = (IntrinsicMeasurable) item$iv$iv2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(it2), "label")) {
                        int labelHeight = ((IntrinsicMeasurable) item$iv$iv2).maxIntrinsicHeight(width);
                        float arg0$iv = this.indicatorVerticalPadding;
                        int paddings = $this$maxIntrinsicHeight.mo358roundToPx0680j_4(Dp.m6626constructorimpl(2 * arg0$iv));
                        return Math.max(iconHeight, labelHeight) + paddings;
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
