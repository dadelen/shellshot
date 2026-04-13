package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BI\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0002\u0010\u000fJM\u0010\u0013\u001a\u00020\u00142=\u0010\u0015\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00140\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u0016H\u0096@¢\u0006\u0002\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001a\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&H\u0016ø\u0001\u0000¢\u0006\u0004\b'\u0010#J\b\u0010\u000e\u001a\u00020\bH\u0016JO\u0010(\u001a\u00020\u00142\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0002\u0010\u000fJ\u0016\u0010)\u001a\u00020!*\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0016\u0010)\u001a\u00020&*\u00020&H\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010+J\u0016\u0010-\u001a\u00020.*\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u0016\u0010-\u001a\u00020.*\u00020&H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00100J\u0019\u00102\u001a\u00020!*\u00020.H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u00020&*\u00020.H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b6\u00104R\u0014\u0010\u0010\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00067"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableNode;", "T", "Landroidx/compose/foundation/gestures/DragGestureNode;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "startDragImmediately", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Landroidx/compose/foundation/gestures/Orientation;ZLjava/lang/Boolean;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/OverscrollEffect;Z)V", "isReverseDirection", "()Z", "Ljava/lang/Boolean;", "drag", "", "forEachDelta", "Lkotlin/Function2;", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "Landroidx/compose/ui/geometry/Offset;", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped", "velocity", "Landroidx/compose/ui/unit/Velocity;", "onDragStopped-TH1AsA0", "update", "reverseIfNeeded", "reverseIfNeeded-MK-Hz9U", "(J)J", "reverseIfNeeded-AH228Gc", "toFloat", "", "toFloat-k-4lQ0M", "(J)F", "toFloat-TH1AsA0", "toOffset", "toOffset-tuRUvjQ", "(F)J", "toVelocity", "toVelocity-adjELrA", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class AnchoredDraggableNode<T> extends DragGestureNode {
    private Orientation orientation;
    private OverscrollEffect overscrollEffect;
    private Boolean reverseDirection;
    private boolean startDragImmediately;
    private AnchoredDraggableState<T> state;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AnchoredDraggableNode(androidx.compose.foundation.gestures.AnchoredDraggableState<T> r2, androidx.compose.foundation.gestures.Orientation r3, boolean r4, java.lang.Boolean r5, androidx.compose.foundation.interaction.MutableInteractionSource r6, androidx.compose.foundation.OverscrollEffect r7, boolean r8) {
        /*
            r1 = this;
            kotlin.jvm.functions.Function1 r0 = androidx.compose.foundation.gestures.AnchoredDraggableKt.access$getAlwaysDrag$p()
            r1.<init>(r0, r4, r6, r3)
            r1.state = r2
            r1.orientation = r3
            r1.reverseDirection = r5
            r1.overscrollEffect = r7
            r1.startDragImmediately = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableNode.<init>(androidx.compose.foundation.gestures.AnchoredDraggableState, androidx.compose.foundation.gestures.Orientation, boolean, java.lang.Boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.OverscrollEffect, boolean):void");
    }

    private final boolean isReverseDirection() {
        if (this.reverseDirection == null) {
            return DelegatableNodeKt.requireLayoutDirection(this) == LayoutDirection.Rtl && this.orientation == Orientation.Horizontal;
        }
        Boolean bool = this.reverseDirection;
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object anchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.state, null, new AnchoredDraggableNode$drag$2(function2, this, null), continuation, 1, null);
        return anchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? anchoredDrag$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStarted-k-4lQ0M, reason: not valid java name */
    public void mo381onDragStartedk4lQ0M(long startedPosition) {
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStopped-TH1AsA0, reason: not valid java name */
    public void mo382onDragStoppedTH1AsA0(long velocity) {
        if (getIsAttached()) {
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnchoredDraggableNode$onDragStopped$1(this, velocity, null), 3, null);
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: startDragImmediately, reason: from getter */
    public boolean getStartDragImmediately() {
        return this.startDragImmediately;
    }

    public final void update(AnchoredDraggableState<T> state, Orientation orientation, boolean enabled, Boolean reverseDirection, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately) {
        boolean resetPointerInputHandling;
        boolean resetPointerInputHandling2 = false;
        if (!Intrinsics.areEqual(this.state, state)) {
            this.state = state;
            resetPointerInputHandling2 = true;
        }
        if (this.orientation != orientation) {
            this.orientation = orientation;
            resetPointerInputHandling2 = true;
        }
        if (Intrinsics.areEqual(this.reverseDirection, reverseDirection)) {
            resetPointerInputHandling = resetPointerInputHandling2;
        } else {
            this.reverseDirection = reverseDirection;
            resetPointerInputHandling = true;
        }
        this.startDragImmediately = startDragImmediately;
        this.overscrollEffect = overscrollEffect;
        DragGestureNode.update$default(this, null, enabled, interactionSource, orientation, resetPointerInputHandling, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m379toOffsettuRUvjQ(float $this$toOffset_u2dtuRUvjQ) {
        return OffsetKt.Offset(this.orientation == Orientation.Horizontal ? $this$toOffset_u2dtuRUvjQ : 0.0f, this.orientation == Orientation.Vertical ? $this$toOffset_u2dtuRUvjQ : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toVelocity-adjELrA, reason: not valid java name */
    public final long m380toVelocityadjELrA(float $this$toVelocity_u2dadjELrA) {
        return VelocityKt.Velocity(this.orientation == Orientation.Horizontal ? $this$toVelocity_u2dadjELrA : 0.0f, this.orientation == Orientation.Vertical ? $this$toVelocity_u2dadjELrA : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float m377toFloatTH1AsA0(long $this$toFloat_u2dTH1AsA0) {
        return this.orientation == Orientation.Vertical ? Velocity.m6864getYimpl($this$toFloat_u2dTH1AsA0) : Velocity.m6863getXimpl($this$toFloat_u2dTH1AsA0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m378toFloatk4lQ0M(long $this$toFloat_u2dk_u2d4lQ0M) {
        return this.orientation == Orientation.Vertical ? Offset.m3923getYimpl($this$toFloat_u2dk_u2d4lQ0M) : Offset.m3922getXimpl($this$toFloat_u2dk_u2d4lQ0M);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseIfNeeded-AH228Gc, reason: not valid java name */
    public final long m375reverseIfNeededAH228Gc(long $this$reverseIfNeeded_u2dAH228Gc) {
        return Velocity.m6869timesadjELrA($this$reverseIfNeeded_u2dAH228Gc, isReverseDirection() ? -1.0f : 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m376reverseIfNeededMKHz9U(long $this$reverseIfNeeded_u2dMK_u2dHz9U) {
        return Offset.m3929timestuRUvjQ($this$reverseIfNeeded_u2dMK_u2dHz9U, isReverseDirection() ? -1.0f : 1.0f);
    }
}
