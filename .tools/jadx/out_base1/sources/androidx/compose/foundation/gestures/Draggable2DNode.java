package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Draggable2D.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u008d\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012>\b\u0002\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017\u0012#\b\u0002\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00150\u0005\u0012>\b\u0002\u0010\u0019\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017\u0012#\b\u0002\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\u0002\u0010\u001dJM\u0010\u001f\u001a\u00020\u00152=\u0010 \u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00150\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160!H\u0096@¢\u0006\u0002\u0010$J\u001a\u0010\r\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u001a\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b'\u0010&J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0093\u0002\u0010(\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072>\b\u0002\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u00172>\b\u0002\u0010\u0019\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u00172#\b\u0002\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00150\u00052#\b\u0002\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020\u0010*\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u0016\u0010*\u001a\u00020\u001a*\u00020\u001aH\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010,R)\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00150\u0005X\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR)\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00150\u0005X\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010\u0019\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000e¢\u0006\u0002\b\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Landroidx/compose/foundation/gestures/Draggable2DNode;", "Landroidx/compose/foundation/gestures/DragGestureNode;", "state", "Landroidx/compose/foundation/gestures/Draggable2DState;", "canDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "reverseDirection", "onDragStarted", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startedPosition", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "onDragStart", "onDragStopped", "Landroidx/compose/ui/unit/Velocity;", "velocity", "onDragStop", "(Landroidx/compose/foundation/gestures/Draggable2DState;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function3;", "drag", "forEachDelta", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped-TH1AsA0", "update", "(Landroidx/compose/foundation/gestures/Draggable2DState;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "reverseIfNeeded", "reverseIfNeeded-MK-Hz9U", "(J)J", "reverseIfNeeded-AH228Gc", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Draggable2DNode extends DragGestureNode {
    public static final int $stable = 8;
    private Function1<? super Offset, Unit> onDragStart;
    private Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> onDragStarted;
    private Function1<? super Velocity, Unit> onDragStop;
    private Function3<? super CoroutineScope, ? super Velocity, ? super Continuation<? super Unit>, ? extends Object> onDragStopped;
    private boolean reverseDirection;
    private boolean startDragImmediately;
    private Draggable2DState state;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Draggable2DNode(androidx.compose.foundation.gestures.Draggable2DState r14, kotlin.jvm.functions.Function1 r15, boolean r16, androidx.compose.foundation.interaction.MutableInteractionSource r17, boolean r18, boolean r19, kotlin.jvm.functions.Function3 r20, kotlin.jvm.functions.Function1 r21, kotlin.jvm.functions.Function3 r22, kotlin.jvm.functions.Function1 r23, int r24, kotlin.jvm.internal.DefaultConstructorMarker r25) {
        /*
            r13 = this;
            r0 = r24
            r1 = r0 & 64
            if (r1 == 0) goto Lc
            kotlin.jvm.functions.Function3 r1 = androidx.compose.foundation.gestures.Draggable2DKt.access$getNoOpOnDragStarted$p()
            r9 = r1
            goto Le
        Lc:
            r9 = r20
        Le:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L18
            kotlin.jvm.functions.Function1 r1 = androidx.compose.foundation.gestures.Draggable2DKt.access$getNoOpOnDragStart$p()
            r10 = r1
            goto L1a
        L18:
            r10 = r21
        L1a:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L24
            kotlin.jvm.functions.Function3 r1 = androidx.compose.foundation.gestures.Draggable2DKt.access$getNoOpOnDragStopped$p()
            r11 = r1
            goto L26
        L24:
            r11 = r22
        L26:
            r0 = r0 & 512(0x200, float:7.17E-43)
            if (r0 == 0) goto L30
            kotlin.jvm.functions.Function1 r0 = androidx.compose.foundation.gestures.Draggable2DKt.access$getNoOpOnDragStop$p()
            r12 = r0
            goto L32
        L30:
            r12 = r23
        L32:
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.Draggable2DNode.<init>(androidx.compose.foundation.gestures.Draggable2DState, kotlin.jvm.functions.Function1, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, boolean, boolean, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public Draggable2DNode(Draggable2DState state, Function1<? super PointerInputChange, Boolean> function1, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, boolean reverseDirection, Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function12, Function3<? super CoroutineScope, ? super Velocity, ? super Continuation<? super Unit>, ? extends Object> function32, Function1<? super Velocity, Unit> function13) {
        super(function1, enabled, interactionSource, null);
        this.state = state;
        this.startDragImmediately = startDragImmediately;
        this.reverseDirection = reverseDirection;
        this.onDragStarted = function3;
        this.onDragStart = function12;
        this.onDragStopped = function32;
        this.onDragStop = function13;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object drag = this.state.drag(MutatePriority.UserInput, new Draggable2DNode$drag$2(function2, this, null), continuation);
        return drag == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? drag : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStarted-k-4lQ0M */
    public void mo381onDragStartedk4lQ0M(long startedPosition) {
        Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3;
        this.onDragStart.invoke(Offset.m3911boximpl(startedPosition));
        if (getIsAttached()) {
            Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function32 = this.onDragStarted;
            function3 = Draggable2DKt.NoOpOnDragStarted;
            if (function32 != function3) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new Draggable2DNode$onDragStarted$1(this, startedPosition, null), 3, null);
            }
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStopped-TH1AsA0 */
    public void mo382onDragStoppedTH1AsA0(long velocity) {
        Function3<? super CoroutineScope, ? super Velocity, ? super Continuation<? super Unit>, ? extends Object> function3;
        this.onDragStop.invoke(Velocity.m6854boximpl(velocity));
        if (getIsAttached()) {
            Function3<? super CoroutineScope, ? super Velocity, ? super Continuation<? super Unit>, ? extends Object> function32 = this.onDragStopped;
            function3 = Draggable2DKt.NoOpOnDragStopped;
            if (function32 != function3) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new Draggable2DNode$onDragStopped$1(this, velocity, null), 3, null);
            }
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: startDragImmediately, reason: from getter */
    public boolean getStartDragImmediately() {
        return this.startDragImmediately;
    }

    public final void update(Draggable2DState state, Function1<? super PointerInputChange, Boolean> canDrag, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, boolean reverseDirection, Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> onDragStarted, Function3<? super CoroutineScope, ? super Velocity, ? super Continuation<? super Unit>, ? extends Object> onDragStopped, Function1<? super Offset, Unit> onDragStart, Function1<? super Velocity, Unit> onDragStop) {
        boolean resetPointerInputHandling;
        boolean resetPointerInputHandling2 = false;
        if (!Intrinsics.areEqual(this.state, state)) {
            this.state = state;
            resetPointerInputHandling2 = true;
        }
        if (this.reverseDirection == reverseDirection) {
            resetPointerInputHandling = resetPointerInputHandling2;
        } else {
            this.reverseDirection = reverseDirection;
            resetPointerInputHandling = true;
        }
        this.onDragStarted = onDragStarted;
        this.onDragStopped = onDragStopped;
        this.onDragStart = onDragStart;
        this.onDragStop = onDragStop;
        this.startDragImmediately = startDragImmediately;
        update(canDrag, enabled, interactionSource, null, resetPointerInputHandling);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseIfNeeded-AH228Gc, reason: not valid java name */
    public final long m432reverseIfNeededAH228Gc(long $this$reverseIfNeeded_u2dAH228Gc) {
        return Velocity.m6869timesadjELrA($this$reverseIfNeeded_u2dAH228Gc, this.reverseDirection ? -1.0f : 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m433reverseIfNeededMKHz9U(long $this$reverseIfNeeded_u2dMK_u2dHz9U) {
        return Offset.m3929timestuRUvjQ($this$reverseIfNeeded_u2dMK_u2dHz9U, this.reverseDirection ? -1.0f : 1.0f);
    }
}
