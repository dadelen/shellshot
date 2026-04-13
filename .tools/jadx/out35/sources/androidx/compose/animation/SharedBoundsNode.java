package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: SharedContentNode.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020)H\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J&\u0010-\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\b4\u00105J\f\u00106\u001a\u00020)*\u000207H\u0016J&\u00108\u001a\u00020.*\u0002092\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u0014\u0010<\u001a\u00020.*\u0002092\u0006\u0010=\u001a\u00020>H\u0002J\f\u0010?\u001a\u00020)*\u00020\u0016H\u0002R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\"\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006@"}, d2 = {"Landroidx/compose/animation/SharedBoundsNode;", "Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "state", "Landroidx/compose/animation/SharedElementInternalState;", "(Landroidx/compose/animation/SharedElementInternalState;)V", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "value", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "rootCoords", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getRootCoords", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "rootLookaheadCoords", "getRootLookaheadCoords", "sharedElement", "Landroidx/compose/animation/SharedElement;", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "getState", "()Landroidx/compose/animation/SharedElementInternalState;", "setState$animation_release", "isMeasurementApproachInProgress", "", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "onAttach", "", "onDetach", "onReset", "requireLookaheadLayoutCoordinates", "approachMeasure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "measure", "Landroidx/compose/ui/layout/MeasureScope;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "place", "placeable", "Landroidx/compose/ui/layout/Placeable;", "updateCurrentBounds", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SharedBoundsNode extends Modifier.Node implements ApproachLayoutModifierNode, DrawModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private GraphicsLayer layer;
    private final ModifierLocalMap providedValues;
    private SharedElementInternalState state;

    public SharedBoundsNode(SharedElementInternalState state) {
        this.state = state;
        this.layer = state.getLayer();
        this.providedValues = ModifierLocalModifierNodeKt.modifierLocalMapOf(TuplesKt.to(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), state));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getRootCoords() {
        return getSharedElement().getScope().getRoot$animation_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getRootLookaheadCoords() {
        return getSharedElement().getScope().getLookaheadRoot$animation_release();
    }

    public final SharedElementInternalState getState() {
        return this.state;
    }

    public final void setState$animation_release(SharedElementInternalState value) {
        if (!Intrinsics.areEqual(value, this.state)) {
            this.state = value;
            if (getIsAttached()) {
                provide(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), value);
                this.state.setParentState((SharedElementInternalState) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState()));
                this.state.setLayer(this.layer);
                this.state.setLookaheadCoords(new Function0<LayoutCoordinates>() { // from class: androidx.compose.animation.SharedBoundsNode$state$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final LayoutCoordinates invoke() {
                        LayoutCoordinates requireLookaheadLayoutCoordinates;
                        requireLookaheadLayoutCoordinates = SharedBoundsNode.this.requireLookaheadLayoutCoordinates();
                        return requireLookaheadLayoutCoordinates;
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates requireLookaheadLayoutCoordinates() {
        SharedTransitionScopeImpl $this$requireLookaheadLayoutCoordinates_u24lambda_u240 = this.state.getSharedElement().getScope();
        return $this$requireLookaheadLayoutCoordinates_u24lambda_u240.toLookaheadCoordinates(DelegatableNodeKt.requireLayoutCoordinates(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BoundsAnimation getBoundsAnimation() {
        return this.state.getBoundsAnimation();
    }

    private final void setLayer(GraphicsLayer value) {
        if (value == null) {
            GraphicsLayer it = this.layer;
            if (it != null) {
                DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it);
            }
        } else {
            this.state.setLayer(value);
        }
        this.layer = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement getSharedElement() {
        return this.state.getSharedElement();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public ModifierLocalMap getProvidedValues() {
        return this.providedValues;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        provide(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), this.state);
        this.state.setParentState((SharedElementInternalState) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState()));
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
        this.state.setLookaheadCoords(new Function0<LayoutCoordinates>() { // from class: androidx.compose.animation.SharedBoundsNode$onAttach$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LayoutCoordinates invoke() {
                LayoutCoordinates requireLookaheadLayoutCoordinates;
                requireLookaheadLayoutCoordinates = SharedBoundsNode.this.requireLookaheadLayoutCoordinates();
                return requireLookaheadLayoutCoordinates;
            }
        });
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        setLayer(null);
        this.state.setParentState(null);
        this.state.setLookaheadCoords(new Function0() { // from class: androidx.compose.animation.SharedBoundsNode$onDetach$1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        });
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        super.onReset();
        GraphicsLayer it = this.layer;
        if (it != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it);
        }
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode, androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo84measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo5491measureBRTryo0(constraints);
        final long lookaheadSize = SizeKt.Size(placeable.getWidth(), placeable.getHeight());
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode$measure$1
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
                Offset topLeft;
                SharedElement sharedElement;
                LayoutCoordinates rootLookaheadCoords;
                SharedElement sharedElement2;
                SharedElement sharedElement3;
                LayoutCoordinates it = $this$layout.getCoordinates();
                if (it != null) {
                    SharedBoundsNode sharedBoundsNode = this;
                    long j = lookaheadSize;
                    rootLookaheadCoords = sharedBoundsNode.getRootLookaheadCoords();
                    long topLeft2 = rootLookaheadCoords.mo5500localPositionOfR5De75A(it, Offset.INSTANCE.m3938getZeroF1C5BW0());
                    sharedElement2 = sharedBoundsNode.getSharedElement();
                    if (sharedElement2.getCurrentBounds() == null) {
                        sharedElement3 = sharedBoundsNode.getSharedElement();
                        sharedElement3.setCurrentBounds(RectKt.m3962Recttz77jQw(topLeft2, j));
                    }
                    topLeft = Offset.m3911boximpl(topLeft2);
                } else {
                    topLeft = null;
                }
                Placeable.PlacementScope.place$default($this$layout, Placeable.this, 0, 0, 0.0f, 4, null);
                if (topLeft != null) {
                    SharedBoundsNode sharedBoundsNode2 = this;
                    long j2 = lookaheadSize;
                    long it2 = topLeft.getPackedValue();
                    sharedElement = sharedBoundsNode2.getSharedElement();
                    sharedElement.m98onLookaheadResultv_w8tDc(sharedBoundsNode2.getState(), j2, it2);
                }
            }
        }, 4, null);
    }

    private final MeasureResult place(MeasureScope $this$place, final Placeable placeable) {
        long mo101calculateSizeJyjRU_E = this.state.getPlaceHolderSize().mo101calculateSizeJyjRU_E(requireLookaheadLayoutCoordinates().mo5499getSizeYbymL2g(), IntSizeKt.IntSize(placeable.getWidth(), placeable.getHeight()));
        int m6796getWidthimpl = IntSize.m6796getWidthimpl(mo101calculateSizeJyjRU_E);
        int h = IntSize.m6795getHeightimpl(mo101calculateSizeJyjRU_E);
        return MeasureScope.layout$default($this$place, m6796getWidthimpl, h, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode$place$1
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
                SharedElement sharedElement;
                SharedElement sharedElement2;
                BoundsAnimation boundsAnimation;
                Offset positionInScope;
                BoundsAnimation boundsAnimation2;
                SharedElement sharedElement3;
                long topLeft;
                LayoutCoordinates coordinates;
                BoundsAnimation boundsAnimation3;
                SharedElement sharedElement4;
                LayoutCoordinates rootCoords;
                BoundsAnimation boundsAnimation4;
                SharedElement sharedElement5;
                SharedElement sharedElement6;
                sharedElement = SharedBoundsNode.this.getSharedElement();
                if (!sharedElement.getFoundMatch()) {
                    LayoutCoordinates coordinates2 = $this$layout.getCoordinates();
                    if (coordinates2 != null) {
                        SharedBoundsNode.this.updateCurrentBounds(coordinates2);
                    }
                    Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                    return;
                }
                sharedElement2 = SharedBoundsNode.this.getSharedElement();
                if (sharedElement2.getTargetBounds() != null) {
                    boundsAnimation4 = SharedBoundsNode.this.getBoundsAnimation();
                    sharedElement5 = SharedBoundsNode.this.getSharedElement();
                    Rect currentBounds = sharedElement5.getCurrentBounds();
                    Intrinsics.checkNotNull(currentBounds);
                    sharedElement6 = SharedBoundsNode.this.getSharedElement();
                    Rect targetBounds = sharedElement6.getTargetBounds();
                    Intrinsics.checkNotNull(targetBounds);
                    boundsAnimation4.animate(currentBounds, targetBounds);
                }
                boundsAnimation = SharedBoundsNode.this.getBoundsAnimation();
                Rect animatedBounds = boundsAnimation.getValue();
                LayoutCoordinates it = $this$layout.getCoordinates();
                if (it != null) {
                    rootCoords = SharedBoundsNode.this.getRootCoords();
                    positionInScope = Offset.m3911boximpl(rootCoords.mo5500localPositionOfR5De75A(it, Offset.INSTANCE.m3938getZeroF1C5BW0()));
                } else {
                    positionInScope = null;
                }
                SharedBoundsNode sharedBoundsNode = SharedBoundsNode.this;
                if (animatedBounds != null) {
                    boundsAnimation3 = sharedBoundsNode.getBoundsAnimation();
                    if (boundsAnimation3.getTarget()) {
                        sharedElement4 = SharedBoundsNode.this.getSharedElement();
                        sharedElement4.setCurrentBounds(animatedBounds);
                    }
                    topLeft = animatedBounds.m3957getTopLeftF1C5BW0();
                } else {
                    boundsAnimation2 = sharedBoundsNode.getBoundsAnimation();
                    if (boundsAnimation2.getTarget() && (coordinates = $this$layout.getCoordinates()) != null) {
                        SharedBoundsNode.this.updateCurrentBounds(coordinates);
                    }
                    sharedElement3 = SharedBoundsNode.this.getSharedElement();
                    Rect currentBounds2 = sharedElement3.getCurrentBounds();
                    Intrinsics.checkNotNull(currentBounds2);
                    topLeft = currentBounds2.m3957getTopLeftF1C5BW0();
                }
                long it2 = positionInScope != null ? Offset.m3926minusMKHz9U(topLeft, positionInScope.getPackedValue()) : Offset.INSTANCE.m3938getZeroF1C5BW0();
                float x = Offset.m3922getXimpl(it2);
                float y = Offset.m3923getYimpl(it2);
                Placeable.PlacementScope.place$default($this$layout, placeable, Math.round(x), Math.round(y), 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: isMeasurementApproachInProgress-ozmzZPI, reason: not valid java name */
    public boolean mo97isMeasurementApproachInProgressozmzZPI(long lookaheadSize) {
        return getSharedElement().getFoundMatch() && this.state.getSharedElement().getScope().isTransitionActive();
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: approachMeasure-3p2s80s, reason: not valid java name */
    public MeasureResult mo96approachMeasure3p2s80s(ApproachMeasureScope $this$approachMeasure_u2d3p2s80s, Measurable measurable, long constraints) {
        long resolvedConstraints;
        if (getSharedElement().getFoundMatch()) {
            Rect it = getBoundsAnimation().getValue();
            if (it == null) {
                it = getSharedElement().getCurrentBounds();
            }
            if (it != null) {
                long m6804roundToIntSizeuvyYCjk = IntSizeKt.m6804roundToIntSizeuvyYCjk(it.m3955getSizeNHjbRc());
                int m6796getWidthimpl = IntSize.m6796getWidthimpl(m6804roundToIntSizeuvyYCjk);
                int height = IntSize.m6795getHeightimpl(m6804roundToIntSizeuvyYCjk);
                if (!((m6796getWidthimpl == Integer.MAX_VALUE || height == Integer.MAX_VALUE) ? false : true)) {
                    throw new IllegalArgumentException(("Error: Infinite width/height is invalid. animated bounds: " + getBoundsAnimation().getValue() + ", current bounds: " + getSharedElement().getCurrentBounds()).toString());
                }
                resolvedConstraints = Constraints.INSTANCE.m6589fixedJhjzzOo(RangesKt.coerceAtLeast(m6796getWidthimpl, 0), RangesKt.coerceAtLeast(height, 0));
                Placeable placeable = measurable.mo5491measureBRTryo0(resolvedConstraints);
                return place($this$approachMeasure_u2d3p2s80s, placeable);
            }
        }
        resolvedConstraints = constraints;
        Placeable placeable2 = measurable.mo5491measureBRTryo0(resolvedConstraints);
        return place($this$approachMeasure_u2d3p2s80s, placeable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCurrentBounds(LayoutCoordinates $this$updateCurrentBounds) {
        getSharedElement().setCurrentBounds(RectKt.m3962Recttz77jQw(getRootCoords().mo5500localPositionOfR5De75A($this$updateCurrentBounds, Offset.INSTANCE.m3938getZeroF1C5BW0()), SizeKt.Size(IntSize.m6796getWidthimpl($this$updateCurrentBounds.mo5499getSizeYbymL2g()), IntSize.m6795getHeightimpl($this$updateCurrentBounds.mo5499getSizeYbymL2g()))));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(final ContentDrawScope $this$draw) {
        SharedElementInternalState sharedElementInternalState = this.state;
        SharedTransitionScope.OverlayClip overlayClip = this.state.getOverlayClip();
        SharedTransitionScope.SharedContentState userState = this.state.getUserState();
        Rect currentBounds = getSharedElement().getCurrentBounds();
        Intrinsics.checkNotNull(currentBounds);
        sharedElementInternalState.setClipPathInOverlay$animation_release(overlayClip.getClipPath(userState, currentBounds, $this$draw.getLayoutDirection(), DelegatableNodeKt.requireDensity(this)));
        GraphicsLayer layer = this.state.getLayer();
        if (layer == null) {
            throw new IllegalArgumentException(("Error: Layer is null when accessed for shared bounds/element : " + getSharedElement().getKey() + ",target: " + this.state.getBoundsAnimation().getTarget() + ", is attached: " + getIsAttached()).toString());
        }
        DrawScope.m4711recordJVtK1S4$default($this$draw, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode$draw$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope $this$record) {
                ContentDrawScope.this.drawContent();
            }
        }, 1, null);
        if (this.state.getShouldRenderInPlace()) {
            GraphicsLayerKt.drawLayer($this$draw, layer);
        }
    }
}
