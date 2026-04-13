package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: Draggable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1", f = "Draggable.kt", i = {}, l = {524}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DragGestureNode$initializePointerInputNode$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DragGestureNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragGestureNode$initializePointerInputNode$1(DragGestureNode dragGestureNode, Continuation<? super DragGestureNode$initializePointerInputNode$1> continuation) {
        super(2, continuation);
        this.this$0 = dragGestureNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureNode$initializePointerInputNode$1 dragGestureNode$initializePointerInputNode$1 = new DragGestureNode$initializePointerInputNode$1(this.this$0, continuation);
        dragGestureNode$initializePointerInputNode$1.L$0 = obj;
        return dragGestureNode$initializePointerInputNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((DragGestureNode$initializePointerInputNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$SuspendingPointerInputModifierNode = (PointerInputScope) this.L$0;
                final VelocityTracker velocityTracker = new VelocityTracker();
                final DragGestureNode dragGestureNode = this.this$0;
                Function3 onDragStart = new Function3<PointerInputChange, PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragStart$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
                        m424invoke0AR0LA0(pointerInputChange, pointerInputChange2, offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-0AR0LA0, reason: not valid java name */
                    public final void m424invoke0AR0LA0(PointerInputChange down, PointerInputChange slopTriggerChange, long postSlopOffset) {
                        boolean z;
                        Channel channel;
                        Channel channel2;
                        if (DragGestureNode.this.getCanDrag().invoke(down).booleanValue()) {
                            z = DragGestureNode.this.isListeningForEvents;
                            if (!z) {
                                channel2 = DragGestureNode.this.channel;
                                if (channel2 == null) {
                                    DragGestureNode.this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                                }
                                DragGestureNode.this.startListeningForEvents();
                            }
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, down);
                            long dragStartedOffset = Offset.m3926minusMKHz9U(slopTriggerChange.getPosition(), postSlopOffset);
                            channel = DragGestureNode.this.channel;
                            if (channel != null) {
                                ChannelResult.m8713boximpl(channel.mo8702trySendJP2dKIU(new DragEvent.DragStarted(dragStartedOffset, null)));
                            }
                        }
                    }
                };
                final DragGestureNode dragGestureNode2 = this.this$0;
                Function1 onDragEnd = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragEnd$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                        invoke2(pointerInputChange);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PointerInputChange upEvent) {
                        Channel channel;
                        long m439toValidVelocityTH1AsA0;
                        VelocityTrackerKt.addPointerInputChange(VelocityTracker.this, upEvent);
                        float maximumVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(dragGestureNode2, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
                        long velocity = VelocityTracker.this.m5462calculateVelocityAH228Gc(VelocityKt.Velocity(maximumVelocity, maximumVelocity));
                        VelocityTracker.this.resetTracking();
                        channel = dragGestureNode2.channel;
                        if (channel != null) {
                            m439toValidVelocityTH1AsA0 = DraggableKt.m439toValidVelocityTH1AsA0(velocity);
                            ChannelResult.m8713boximpl(channel.mo8702trySendJP2dKIU(new DragEvent.DragStopped(m439toValidVelocityTH1AsA0, null)));
                        }
                    }
                };
                final DragGestureNode dragGestureNode3 = this.this$0;
                Function0 onDragCancel = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragCancel$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Channel channel;
                        channel = DragGestureNode.this.channel;
                        if (channel != null) {
                            ChannelResult.m8713boximpl(channel.mo8702trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                        }
                    }
                };
                final DragGestureNode dragGestureNode4 = this.this$0;
                Function0 shouldAwaitTouchSlop = new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$shouldAwaitTouchSlop$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(!DragGestureNode.this.getStartDragImmediately());
                    }
                };
                final DragGestureNode dragGestureNode5 = this.this$0;
                Function2 onDrag = new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDrag$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                        m423invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
                    public final void m423invokeUv8p0NA(PointerInputChange change, long delta) {
                        Channel channel;
                        VelocityTrackerKt.addPointerInputChange(VelocityTracker.this, change);
                        channel = dragGestureNode5.channel;
                        if (channel != null) {
                            ChannelResult.m8713boximpl(channel.mo8702trySendJP2dKIU(new DragEvent.DragDelta(delta, null)));
                        }
                    }
                };
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, $this$SuspendingPointerInputModifierNode, onDragStart, onDragEnd, onDragCancel, shouldAwaitTouchSlop, onDrag, null), this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1", f = "Draggable.kt", i = {0}, l = {526}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
        final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
        final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DragGestureNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(DragGestureNode dragGestureNode, PointerInputScope pointerInputScope, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = dragGestureNode;
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
            this.$onDragStart = function3;
            this.$onDragEnd = function1;
            this.$onDragCancel = function0;
            this.$shouldAwaitTouchSlop = function02;
            this.$onDrag = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$$this$SuspendingPointerInputModifierNode, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, this.$onDrag, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v5 */
        /* JADX WARN: Type inference failed for: r1v6 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Channel channel;
            CoroutineScope coroutineScope;
            Orientation orientation;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            AnonymousClass1 anonymousClass1 = this.label;
            try {
                switch (anonymousClass1) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        AnonymousClass1 anonymousClass12 = this;
                        coroutineScope = (CoroutineScope) anonymousClass12.L$0;
                        orientation = anonymousClass12.this$0.orientationLock;
                        anonymousClass12.L$0 = coroutineScope;
                        anonymousClass12.label = 1;
                        Object detectDragGestures = DragGestureDetectorKt.detectDragGestures(anonymousClass12.$$this$SuspendingPointerInputModifierNode, anonymousClass12.$onDragStart, anonymousClass12.$onDragEnd, anonymousClass12.$onDragCancel, anonymousClass12.$shouldAwaitTouchSlop, orientation, anonymousClass12.$onDrag, anonymousClass12);
                        anonymousClass1 = anonymousClass12;
                        if (detectDragGestures == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        AnonymousClass1 anonymousClass13 = this;
                        coroutineScope = (CoroutineScope) anonymousClass13.L$0;
                        ResultKt.throwOnFailure(obj);
                        anonymousClass1 = anonymousClass13;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CancellationException e) {
                channel = anonymousClass1.this$0.channel;
                if (channel != null) {
                    ChannelResult.m8713boximpl(channel.mo8702trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    throw e;
                }
            }
            return Unit.INSTANCE;
        }
    }
}
