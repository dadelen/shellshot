package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: ExpressiveNavigationBar.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material3/EqualWeightContentMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class EqualWeightContentMeasurePolicy implements MeasurePolicy {
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        int width;
        final List itemsPlaceables;
        int width2 = Constraints.m6579getMaxWidthimpl(constraints);
        int itemHeight = Constraints.m6580getMinHeightimpl(constraints);
        int itemsCount = list.size();
        if (itemsCount < 1) {
            return MeasureScope.layout$default($this$measure_u2d3p2s80s, width2, itemHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.EqualWeightContentMeasurePolicy$measure$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                }
            }, 4, null);
        }
        List itemsPlaceables2 = null;
        if (!Constraints.m6575getHasBoundedWidthimpl(constraints)) {
            List $this$fastMap$iv = list;
            int $i$f$fastMap = 0;
            ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
            int index$iv$iv = 0;
            int size = $this$fastMap$iv.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                int width3 = width2;
                Measurable it = (Measurable) item$iv$iv;
                target$iv.add(it.mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(constraints, Constraints.INSTANCE.m6590fixedHeightOenEA2s(itemHeight))));
                index$iv$iv++;
                itemsPlaceables2 = itemsPlaceables2;
                width2 = width3;
                $this$fastMap$iv = $this$fastMap$iv;
                $i$f$fastMap = $i$f$fastMap;
            }
            width = width2;
            itemsPlaceables = target$iv;
        } else {
            width = width2;
            int width4 = width / itemsCount;
            int size2 = list.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = list.get(index$iv);
                Measurable it2 = (Measurable) item$iv;
                int measurableHeight = it2.maxIntrinsicHeight(width4);
                if (itemHeight < measurableHeight) {
                    itemHeight = RangesKt.coerceAtMost(measurableHeight, Constraints.m6578getMaxHeightimpl(constraints));
                }
            }
            List $this$fastMap$iv2 = list;
            int $i$f$fastMap2 = 0;
            ArrayList target$iv2 = new ArrayList($this$fastMap$iv2.size());
            int index$iv$iv2 = 0;
            int size3 = $this$fastMap$iv2.size();
            while (index$iv$iv2 < size3) {
                Object item$iv$iv2 = $this$fastMap$iv2.get(index$iv$iv2);
                List $this$fastMap$iv3 = $this$fastMap$iv2;
                Measurable it3 = (Measurable) item$iv$iv2;
                target$iv2.add(it3.mo5491measureBRTryo0(ConstraintsKt.m6594constrainN9IONVI(constraints, Constraints.INSTANCE.m6589fixedJhjzzOo(width4, itemHeight))));
                index$iv$iv2++;
                $this$fastMap$iv2 = $this$fastMap$iv3;
                $i$f$fastMap2 = $i$f$fastMap2;
                target$iv2 = target$iv2;
            }
            itemsPlaceables = target$iv2;
        }
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, itemHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.EqualWeightContentMeasurePolicy$measure$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                List $this$fastForEach$iv = itemsPlaceables;
                int size4 = $this$fastForEach$iv.size();
                int x = 0;
                int x2 = 0;
                while (x2 < size4) {
                    Object item$iv2 = $this$fastForEach$iv.get(x2);
                    Placeable item = (Placeable) item$iv2;
                    Placeable.PlacementScope $this$layout2 = $this$layout;
                    Placeable.PlacementScope.placeRelative$default($this$layout2, item, x, 0, 0.0f, 4, null);
                    x += item.getWidth();
                    x2++;
                    $this$layout = $this$layout2;
                }
            }
        }, 4, null);
    }
}
