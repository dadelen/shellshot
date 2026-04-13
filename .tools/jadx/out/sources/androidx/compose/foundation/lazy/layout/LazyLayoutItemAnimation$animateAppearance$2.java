package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyLayoutItemAnimation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2", f = "LazyLayoutItemAnimation.kt", i = {}, l = {195, 197}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class LazyLayoutItemAnimation$animateAppearance$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ GraphicsLayer $layer;
    final /* synthetic */ boolean $shouldResetValue;
    final /* synthetic */ FiniteAnimationSpec<Float> $spec;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animateAppearance$2(boolean z, LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<Float> finiteAnimationSpec, GraphicsLayer graphicsLayer, Continuation<? super LazyLayoutItemAnimation$animateAppearance$2> continuation) {
        super(2, continuation);
        this.$shouldResetValue = z;
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$layer = graphicsLayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animateAppearance$2(this.$shouldResetValue, this.this$0, this.$spec, this.$layer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutItemAnimation$animateAppearance$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006d A[RETURN] */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2, int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            switch(r1) {
                case 0: goto L1f;
                case 1: goto L18;
                case 2: goto L13;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L13:
            r1 = r12
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L1d
            goto L6e
        L18:
            r1 = r12
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L1d
            goto L40
        L1d:
            r0 = move-exception
            goto L77
        L1f:
            kotlin.ResultKt.throwOnFailure(r13)
            r1 = r12
            boolean r3 = r1.$shouldResetValue     // Catch: java.lang.Throwable -> L1d
            if (r3 == 0) goto L41
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.lang.Throwable -> L1d
            androidx.compose.animation.core.Animatable r3 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getVisibilityAnimation$p(r3)     // Catch: java.lang.Throwable -> L1d
            r4 = 0
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r4)     // Catch: java.lang.Throwable -> L1d
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> L1d
            r6 = 1
            r1.label = r6     // Catch: java.lang.Throwable -> L1d
            java.lang.Object r3 = r3.snapTo(r4, r5)     // Catch: java.lang.Throwable -> L1d
            if (r3 != r0) goto L40
            return r0
        L40:
        L41:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.lang.Throwable -> L1d
            androidx.compose.animation.core.Animatable r4 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getVisibilityAnimation$p(r3)     // Catch: java.lang.Throwable -> L1d
            r3 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r3)     // Catch: java.lang.Throwable -> L1d
            androidx.compose.animation.core.FiniteAnimationSpec<java.lang.Float> r3 = r1.$spec     // Catch: java.lang.Throwable -> L1d
            r6 = r3
            androidx.compose.animation.core.AnimationSpec r6 = (androidx.compose.animation.core.AnimationSpec) r6     // Catch: java.lang.Throwable -> L1d
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2$1 r3 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2$1     // Catch: java.lang.Throwable -> L1d
            androidx.compose.ui.graphics.layer.GraphicsLayer r7 = r1.$layer     // Catch: java.lang.Throwable -> L1d
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r8 = r1.this$0     // Catch: java.lang.Throwable -> L1d
            r3.<init>()     // Catch: java.lang.Throwable -> L1d
            r8 = r3
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8     // Catch: java.lang.Throwable -> L1d
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Throwable -> L1d
            r3 = 2
            r1.label = r3     // Catch: java.lang.Throwable -> L1d
            r7 = 0
            r10 = 4
            r11 = 0
            java.lang.Object r3 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L1d
            if (r3 != r0) goto L6e
            return r0
        L6e:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r0 = r1.this$0
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setAppearanceAnimationInProgress(r0, r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L77:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setAppearanceAnimationInProgress(r3, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
