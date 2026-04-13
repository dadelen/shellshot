package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003Be\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012H\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u0010!\u001a\u00020\"H\u0016J&\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\r\u001a\u00020\fH\u0016ø\u0001\u0000¢\u0006\u0004\b(\u0010)R\\\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Landroidx/compose/material/DraggableAnchorsNode;", "T", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "anchors", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "size", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Pair;", "Landroidx/compose/material/DraggableAnchors;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(Landroidx/compose/material/AnchoredDraggableState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/gestures/Orientation;)V", "getAnchors", "()Lkotlin/jvm/functions/Function2;", "setAnchors", "(Lkotlin/jvm/functions/Function2;)V", "didLookahead", "", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "getState", "()Landroidx/compose/material/AnchoredDraggableState;", "setState", "(Landroidx/compose/material/AnchoredDraggableState;)V", "onDetach", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class DraggableAnchorsNode<T> extends Modifier.Node implements LayoutModifierNode {
    private Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> anchors;
    private boolean didLookahead;
    private Orientation orientation;
    private AnchoredDraggableState<T> state;

    public final AnchoredDraggableState<T> getState() {
        return this.state;
    }

    public final void setState(AnchoredDraggableState<T> anchoredDraggableState) {
        this.state = anchoredDraggableState;
    }

    public final Function2<IntSize, Constraints, Pair<DraggableAnchors<T>, T>> getAnchors() {
        return this.anchors;
    }

    public final void setAnchors(Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> function2) {
        this.anchors = function2;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public DraggableAnchorsNode(AnchoredDraggableState<T> anchoredDraggableState, Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> function2, Orientation orientation) {
        this.state = anchoredDraggableState;
        this.anchors = function2;
        this.orientation = orientation;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.didLookahead = false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo84measure3p2s80s(final MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo5491measureBRTryo0(constraints);
        if (!$this$measure_u2d3p2s80s.isLookingAhead() || !this.didLookahead) {
            long size = IntSizeKt.IntSize(placeable.getWidth(), placeable.getHeight());
            Pair newAnchorResult = this.anchors.invoke(IntSize.m6788boximpl(size), Constraints.m6567boximpl(constraints));
            this.state.updateAnchors(newAnchorResult.getFirst(), newAnchorResult.getSecond());
        }
        this.didLookahead = $this$measure_u2d3p2s80s.isLookingAhead() || this.didLookahead;
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.DraggableAnchorsNode$measure$1
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
                float offset;
                boolean isLookingAhead = MeasureScope.this.isLookingAhead();
                DraggableAnchorsNode<T> draggableAnchorsNode = this;
                if (isLookingAhead) {
                    offset = draggableAnchorsNode.getState().getAnchors().positionOf(this.getState().getTargetValue());
                } else {
                    offset = draggableAnchorsNode.getState().requireOffset();
                }
                float xOffset = this.getOrientation() == Orientation.Horizontal ? offset : 0.0f;
                float yOffset = this.getOrientation() == Orientation.Vertical ? offset : 0.0f;
                Placeable.PlacementScope.place$default($this$layout, placeable, MathKt.roundToInt(xOffset), MathKt.roundToInt(yOffset), 0.0f, 4, null);
            }
        }, 4, null);
    }
}
