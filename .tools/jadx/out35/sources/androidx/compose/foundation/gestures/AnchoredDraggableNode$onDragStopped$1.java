package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1", f = "AnchoredDraggable.kt", i = {}, l = {275, 277}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class AnchoredDraggableNode$onDragStopped$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $velocity;
    Object L$0;
    int label;
    final /* synthetic */ AnchoredDraggableNode<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnchoredDraggableNode$onDragStopped$1(AnchoredDraggableNode<T> anchoredDraggableNode, long j, Continuation<? super AnchoredDraggableNode$onDragStopped$1> continuation) {
        super(2, continuation);
        this.this$0 = anchoredDraggableNode;
        this.$velocity = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AnchoredDraggableNode$onDragStopped$1(this.this$0, this.$velocity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AnchoredDraggableNode$onDragStopped$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        OverscrollEffect overscrollEffect;
        AnchoredDraggableNode anchoredDraggableNode;
        OverscrollEffect overscrollEffect2;
        long m375reverseIfNeededAH228Gc;
        AnchoredDraggableNode$onDragStopped$1 anchoredDraggableNode$onDragStopped$1;
        AnchoredDraggableState anchoredDraggableState;
        long m375reverseIfNeededAH228Gc2;
        float m377toFloatTH1AsA0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                overscrollEffect = ((AnchoredDraggableNode) this.this$0).overscrollEffect;
                anchoredDraggableNode = this.this$0;
                if (overscrollEffect != null) {
                    overscrollEffect2 = anchoredDraggableNode.overscrollEffect;
                    Intrinsics.checkNotNull(overscrollEffect2);
                    m375reverseIfNeededAH228Gc = this.this$0.m375reverseIfNeededAH228Gc(this.$velocity);
                    this.label = 2;
                    if (overscrollEffect2.mo208applyToFlingBMRW4eQ(m375reverseIfNeededAH228Gc, new AnonymousClass1(this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anchoredDraggableNode$onDragStopped$1 = this;
                    return Unit.INSTANCE;
                }
                anchoredDraggableState = ((AnchoredDraggableNode) this.this$0).state;
                AnchoredDraggableNode<T> anchoredDraggableNode2 = this.this$0;
                m375reverseIfNeededAH228Gc2 = this.this$0.m375reverseIfNeededAH228Gc(this.$velocity);
                m377toFloatTH1AsA0 = anchoredDraggableNode2.m377toFloatTH1AsA0(m375reverseIfNeededAH228Gc2);
                this.L$0 = anchoredDraggableNode;
                this.label = 1;
                Object obj = anchoredDraggableState.settle(m377toFloatTH1AsA0, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = obj;
                anchoredDraggableNode.m380toVelocityadjELrA(((Number) $result).floatValue());
                return Unit.INSTANCE;
            case 1:
                AnchoredDraggableNode anchoredDraggableNode3 = (AnchoredDraggableNode) this.L$0;
                ResultKt.throwOnFailure($result);
                anchoredDraggableNode = anchoredDraggableNode3;
                anchoredDraggableNode.m380toVelocityadjELrA(((Number) $result).floatValue());
                return Unit.INSTANCE;
            case 2:
                anchoredDraggableNode$onDragStopped$1 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "T", "availableVelocity"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1$1", f = "AnchoredDraggable.kt", i = {0}, l = {280}, m = "invokeSuspend", n = {"availableVelocity"}, s = {"J$0"})
    /* renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Velocity, Continuation<? super Velocity>, Object> {
        /* synthetic */ long J$0;
        Object L$0;
        int label;
        final /* synthetic */ AnchoredDraggableNode<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AnchoredDraggableNode<T> anchoredDraggableNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = anchoredDraggableNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.J$0 = ((Velocity) obj).getPackedValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Velocity> continuation) {
            return m384invokesFctU(velocity.getPackedValue(), continuation);
        }

        /* renamed from: invoke-sF-c-tU, reason: not valid java name */
        public final Object m384invokesFctU(long j, Continuation<? super Velocity> continuation) {
            return ((AnonymousClass1) create(Velocity.m6854boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            long availableVelocity;
            AnchoredDraggableNode anchoredDraggableNode;
            AnchoredDraggableState anchoredDraggableState;
            float m377toFloatTH1AsA0;
            long consumed;
            AnchoredDraggableState anchoredDraggableState2;
            AnchoredDraggableState anchoredDraggableState3;
            AnchoredDraggableState anchoredDraggableState4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    availableVelocity = anonymousClass1.J$0;
                    anchoredDraggableNode = anonymousClass1.this$0;
                    anchoredDraggableState = ((AnchoredDraggableNode) anonymousClass1.this$0).state;
                    m377toFloatTH1AsA0 = anonymousClass1.this$0.m377toFloatTH1AsA0(availableVelocity);
                    anonymousClass1.L$0 = anchoredDraggableNode;
                    anonymousClass1.J$0 = availableVelocity;
                    anonymousClass1.label = 1;
                    Object obj = anchoredDraggableState.settle(m377toFloatTH1AsA0, anonymousClass1);
                    if (obj != coroutine_suspended) {
                        $result = obj;
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    long availableVelocity2 = this.J$0;
                    AnchoredDraggableNode anchoredDraggableNode2 = (AnchoredDraggableNode) this.L$0;
                    ResultKt.throwOnFailure($result);
                    anchoredDraggableNode = anchoredDraggableNode2;
                    availableVelocity = availableVelocity2;
                    anonymousClass1 = this;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            consumed = anchoredDraggableNode.m380toVelocityadjELrA(((Number) $result).floatValue());
            anchoredDraggableState2 = ((AnchoredDraggableNode) anonymousClass1.this$0).state;
            float currentOffset = anchoredDraggableState2.requireOffset();
            anchoredDraggableState3 = ((AnchoredDraggableNode) anonymousClass1.this$0).state;
            float minAnchor = anchoredDraggableState3.getAnchors().minAnchor();
            anchoredDraggableState4 = ((AnchoredDraggableNode) anonymousClass1.this$0).state;
            float maxAnchor = anchoredDraggableState4.getAnchors().maxAnchor();
            if (currentOffset >= maxAnchor || currentOffset <= minAnchor) {
                availableVelocity = consumed;
            }
            return Velocity.m6854boximpl(availableVelocity);
        }
    }
}
