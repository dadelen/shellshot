package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002BI\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0013\u0010\u0013\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\f\u0010\u001b\u001a\u00020\u0019*\u00020\u001cH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableElement;", "T", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/gestures/AnchoredDraggableNode;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Landroidx/compose/foundation/gestures/Orientation;ZLjava/lang/Boolean;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLandroidx/compose/foundation/OverscrollEffect;)V", "Ljava/lang/Boolean;", "create", "equals", "other", "", "hashCode", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class AnchoredDraggableElement<T> extends ModifierNodeElement<AnchoredDraggableNode<T>> {
    private final boolean enabled;
    private final MutableInteractionSource interactionSource;
    private final Orientation orientation;
    private final OverscrollEffect overscrollEffect;
    private final Boolean reverseDirection;
    private final boolean startDragImmediately;
    private final AnchoredDraggableState<T> state;

    public AnchoredDraggableElement(AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, Boolean reverseDirection, MutableInteractionSource interactionSource, boolean startDragImmediately, OverscrollEffect overscrollEffect) {
        this.state = anchoredDraggableState;
        this.orientation = orientation;
        this.enabled = enabled;
        this.reverseDirection = reverseDirection;
        this.interactionSource = interactionSource;
        this.startDragImmediately = startDragImmediately;
        this.overscrollEffect = overscrollEffect;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* renamed from: create */
    public AnchoredDraggableNode<T> getNode() {
        return new AnchoredDraggableNode<>(this.state, this.orientation, this.enabled, this.reverseDirection, this.interactionSource, this.overscrollEffect, this.startDragImmediately);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(AnchoredDraggableNode<T> node) {
        node.update(this.state, this.orientation, this.enabled, this.reverseDirection, this.interactionSource, this.overscrollEffect, this.startDragImmediately);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int result = this.state.hashCode();
        int result2 = ((((result * 31) + this.orientation.hashCode()) * 31) + Boolean.hashCode(this.enabled)) * 31;
        Boolean bool = this.reverseDirection;
        int result3 = (result2 + (bool != null ? bool.hashCode() : 0)) * 31;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        int result4 = (((result3 + (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0)) * 31) + Boolean.hashCode(this.startDragImmediately)) * 31;
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        return result4 + (overscrollEffect != null ? overscrollEffect.hashCode() : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AnchoredDraggableElement) && Intrinsics.areEqual(this.state, ((AnchoredDraggableElement) other).state) && this.orientation == ((AnchoredDraggableElement) other).orientation && this.enabled == ((AnchoredDraggableElement) other).enabled && Intrinsics.areEqual(this.reverseDirection, ((AnchoredDraggableElement) other).reverseDirection) && Intrinsics.areEqual(this.interactionSource, ((AnchoredDraggableElement) other).interactionSource) && this.startDragImmediately == ((AnchoredDraggableElement) other).startDragImmediately && Intrinsics.areEqual(this.overscrollEffect, ((AnchoredDraggableElement) other).overscrollEffect);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("anchoredDraggable");
        $this$inspectableProperties.getProperties().set("state", this.state);
        $this$inspectableProperties.getProperties().set("orientation", this.orientation);
        $this$inspectableProperties.getProperties().set("enabled", Boolean.valueOf(this.enabled));
        $this$inspectableProperties.getProperties().set("reverseDirection", this.reverseDirection);
        $this$inspectableProperties.getProperties().set("interactionSource", this.interactionSource);
        $this$inspectableProperties.getProperties().set("startDragImmediately", Boolean.valueOf(this.startDragImmediately));
        $this$inspectableProperties.getProperties().set("overscrollEffect", this.overscrollEffect);
    }
}
