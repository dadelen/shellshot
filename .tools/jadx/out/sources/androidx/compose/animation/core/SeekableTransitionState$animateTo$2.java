package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "S"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2", f = "Transition.kt", i = {}, l = {623}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SeekableTransitionState$animateTo$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
    final /* synthetic */ S $targetState;
    final /* synthetic */ Transition<S> $transition;
    int label;
    final /* synthetic */ SeekableTransitionState<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SeekableTransitionState$animateTo$2(Transition<S> transition, SeekableTransitionState<S> seekableTransitionState, S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super SeekableTransitionState$animateTo$2> continuation) {
        super(1, continuation);
        this.$transition = transition;
        this.this$0 = seekableTransitionState;
        this.$targetState = s;
        this.$animationSpec = finiteAnimationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SeekableTransitionState$animateTo$2(this.$transition, this.this$0, this.$targetState, this.$animationSpec, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((SeekableTransitionState$animateTo$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "S", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1", f = "Transition.kt", i = {0}, l = {2191, 636, 638, 690, 692}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"})
    /* renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$transition = transition;
            this.$animationSpec = finiteAnimationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0228 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0229  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00e7  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x00d7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x00b6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instructions count: 584
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SeekableTransitionState$animateTo$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SeekableTransitionState$animateTo$2 seekableTransitionState$animateTo$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seekableTransitionState$animateTo$2 = this;
                break;
            case 1:
                seekableTransitionState$animateTo$2 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        seekableTransitionState$animateTo$2.$transition.onTransitionEnd$animation_core_release();
        return Unit.INSTANCE;
    }
}
