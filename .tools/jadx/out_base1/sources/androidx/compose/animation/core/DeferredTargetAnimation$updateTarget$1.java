package androidx.compose.animation.core;

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

/* compiled from: DeferredTargetAnimation.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.DeferredTargetAnimation$updateTarget$1", f = "DeferredTargetAnimation.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DeferredTargetAnimation$updateTarget$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<T, V> $anim;
    final /* synthetic */ FiniteAnimationSpec<T> $animationSpec;
    final /* synthetic */ T $target;
    int label;
    final /* synthetic */ DeferredTargetAnimation<T, V> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DeferredTargetAnimation$updateTarget$1(Animatable<T, V> animatable, DeferredTargetAnimation<T, V> deferredTargetAnimation, T t, FiniteAnimationSpec<T> finiteAnimationSpec, Continuation<? super DeferredTargetAnimation$updateTarget$1> continuation) {
        super(2, continuation);
        this.$anim = animatable;
        this.this$0 = deferredTargetAnimation;
        this.$target = t;
        this.$animationSpec = finiteAnimationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DeferredTargetAnimation$updateTarget$1(this.$anim, this.this$0, this.$target, this.$animationSpec, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeferredTargetAnimation$updateTarget$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object obj;
        Object animateTo;
        DeferredTargetAnimation$updateTarget$1 deferredTargetAnimation$updateTarget$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Object targetValue = this.$anim.getTargetValue();
                obj = this.this$0.get_pendingTarget();
                if (!Intrinsics.areEqual(targetValue, obj)) {
                    Animatable<T, V> animatable = this.$anim;
                    T t = this.$target;
                    AnimationSpec animationSpec = this.$animationSpec;
                    this.label = 1;
                    animateTo = animatable.animateTo(t, (r12 & 2) != 0 ? animatable.defaultSpringSpec : animationSpec, (r12 & 4) != 0 ? animatable.getVelocity() : null, (r12 & 8) != 0 ? null : null, this);
                    if (animateTo == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    deferredTargetAnimation$updateTarget$1 = this;
                }
                return Unit.INSTANCE;
            case 1:
                deferredTargetAnimation$updateTarget$1 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
