package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyLayoutItemAnimation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateDisappearance$1", f = "LazyLayoutItemAnimation.kt", i = {}, l = {216}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class LazyLayoutItemAnimation$animateDisappearance$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ GraphicsLayer $layer;
    final /* synthetic */ FiniteAnimationSpec<Float> $spec;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animateDisappearance$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<Float> finiteAnimationSpec, GraphicsLayer graphicsLayer, Continuation<? super LazyLayoutItemAnimation$animateDisappearance$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$layer = graphicsLayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animateDisappearance$1(this.this$0, this.$spec, this.$layer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutItemAnimation$animateDisappearance$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateDisappearance$1] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Animatable animatable;
        Object animateTo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        LazyLayoutItemAnimation$animateDisappearance$1 lazyLayoutItemAnimation$animateDisappearance$1 = this.label;
        try {
            switch (lazyLayoutItemAnimation$animateDisappearance$1) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    LazyLayoutItemAnimation$animateDisappearance$1 lazyLayoutItemAnimation$animateDisappearance$12 = this;
                    animatable = lazyLayoutItemAnimation$animateDisappearance$12.this$0.visibilityAnimation;
                    Float boxFloat = Boxing.boxFloat(0.0f);
                    FiniteAnimationSpec<Float> finiteAnimationSpec = lazyLayoutItemAnimation$animateDisappearance$12.$spec;
                    final GraphicsLayer graphicsLayer = lazyLayoutItemAnimation$animateDisappearance$12.$layer;
                    final LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimation$animateDisappearance$12.this$0;
                    Function1<Animatable<Float, AnimationVector1D>, Unit> function1 = new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateDisappearance$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable2) {
                            invoke2(animatable2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<Float, AnimationVector1D> animatable2) {
                            Function0 function0;
                            GraphicsLayer.this.setAlpha(animatable2.getValue().floatValue());
                            function0 = lazyLayoutItemAnimation.onLayerPropertyChanged;
                            function0.invoke();
                        }
                    };
                    lazyLayoutItemAnimation$animateDisappearance$12.label = 1;
                    animateTo = animatable.animateTo(boxFloat, (r12 & 2) != 0 ? animatable.defaultSpringSpec : finiteAnimationSpec, (r12 & 4) != 0 ? animatable.getVelocity() : null, (r12 & 8) != 0 ? null : function1, lazyLayoutItemAnimation$animateDisappearance$12);
                    lazyLayoutItemAnimation$animateDisappearance$1 = lazyLayoutItemAnimation$animateDisappearance$12;
                    if (animateTo == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    lazyLayoutItemAnimation$animateDisappearance$1 = this;
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            lazyLayoutItemAnimation$animateDisappearance$1.this$0.setDisappearanceAnimationFinished(true);
            lazyLayoutItemAnimation$animateDisappearance$1.this$0.setDisappearanceAnimationInProgress(false);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            lazyLayoutItemAnimation$animateDisappearance$1.this$0.setDisappearanceAnimationInProgress(false);
            throw th;
        }
    }
}
