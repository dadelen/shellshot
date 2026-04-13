package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyLayoutItemAnimation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1", f = "LazyLayoutItemAnimation.kt", i = {0}, l = {151, 158}, m = "invokeSuspend", n = {"finalSpec"}, s = {"L$0"})
/* loaded from: classes.dex */
final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j, Continuation<? super LazyLayoutItemAnimation$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutItemAnimation$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00b9 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            switch(r1) {
                case 0: goto L24;
                case 1: goto L1b;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L12:
            r1 = r14
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.util.concurrent.CancellationException -> L18
            goto Lba
        L18:
            r0 = move-exception
            goto Lc5
        L1b:
            r1 = r14
            java.lang.Object r2 = r1.L$0
            androidx.compose.animation.core.FiniteAnimationSpec r2 = (androidx.compose.animation.core.FiniteAnimationSpec) r2
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.util.concurrent.CancellationException -> L18
            goto L71
        L24:
            kotlin.ResultKt.throwOnFailure(r15)
            r1 = r14
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r2 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.Animatable r2 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r2)     // Catch: java.util.concurrent.CancellationException -> L18
            boolean r2 = r2.isRunning()     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.FiniteAnimationSpec<androidx.compose.ui.unit.IntOffset> r3 = r1.$spec
            if (r2 == 0) goto L48
            boolean r2 = r3 instanceof androidx.compose.animation.core.SpringSpec     // Catch: java.util.concurrent.CancellationException -> L18
            if (r2 == 0) goto L40
            androidx.compose.animation.core.FiniteAnimationSpec<androidx.compose.ui.unit.IntOffset> r2 = r1.$spec     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.SpringSpec r2 = (androidx.compose.animation.core.SpringSpec) r2     // Catch: java.util.concurrent.CancellationException -> L18
            goto L44
        L40:
            androidx.compose.animation.core.SpringSpec r2 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimationKt.access$getInterruptionSpec$p()     // Catch: java.util.concurrent.CancellationException -> L18
        L44:
            r3 = r2
            androidx.compose.animation.core.FiniteAnimationSpec r3 = (androidx.compose.animation.core.FiniteAnimationSpec) r3     // Catch: java.util.concurrent.CancellationException -> L18
            goto L49
        L48:
        L49:
            r2 = r3
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.Animatable r3 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r3)     // Catch: java.util.concurrent.CancellationException -> L18
            boolean r3 = r3.isRunning()     // Catch: java.util.concurrent.CancellationException -> L18
            if (r3 != 0) goto L7a
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.Animatable r3 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r3)     // Catch: java.util.concurrent.CancellationException -> L18
            long r4 = r1.$totalDelta     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.ui.unit.IntOffset r4 = androidx.compose.ui.unit.IntOffset.m6745boximpl(r4)     // Catch: java.util.concurrent.CancellationException -> L18
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.util.concurrent.CancellationException -> L18
            r1.L$0 = r2     // Catch: java.util.concurrent.CancellationException -> L18
            r6 = 1
            r1.label = r6     // Catch: java.util.concurrent.CancellationException -> L18
            java.lang.Object r3 = r3.snapTo(r4, r5)     // Catch: java.util.concurrent.CancellationException -> L18
            if (r3 != r0) goto L71
            return r0
        L71:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            kotlin.jvm.functions.Function0 r3 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getOnLayerPropertyChanged$p(r3)     // Catch: java.util.concurrent.CancellationException -> L18
            r3.invoke()     // Catch: java.util.concurrent.CancellationException -> L18
        L7a:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r3 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.Animatable r3 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r3)     // Catch: java.util.concurrent.CancellationException -> L18
            java.lang.Object r3 = r3.getValue()     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.ui.unit.IntOffset r3 = (androidx.compose.ui.unit.IntOffset) r3     // Catch: java.util.concurrent.CancellationException -> L18
            long r3 = r3.getPackedValue()     // Catch: java.util.concurrent.CancellationException -> L18
            long r5 = r1.$totalDelta     // Catch: java.util.concurrent.CancellationException -> L18
            long r3 = androidx.compose.ui.unit.IntOffset.m6757minusqkQi6aY(r3, r5)     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r5 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.animation.core.Animatable r6 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r5)     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.ui.unit.IntOffset r7 = androidx.compose.ui.unit.IntOffset.m6745boximpl(r3)     // Catch: java.util.concurrent.CancellationException -> L18
            r8 = r2
            androidx.compose.animation.core.AnimationSpec r8 = (androidx.compose.animation.core.AnimationSpec) r8     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1 r5 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r9 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            r5.<init>()     // Catch: java.util.concurrent.CancellationException -> L18
            r10 = r5
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10     // Catch: java.util.concurrent.CancellationException -> L18
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11     // Catch: java.util.concurrent.CancellationException -> L18
            r5 = 0
            r1.L$0 = r5     // Catch: java.util.concurrent.CancellationException -> L18
            r5 = 2
            r1.label = r5     // Catch: java.util.concurrent.CancellationException -> L18
            r9 = 0
            r12 = 4
            r13 = 0
            java.lang.Object r5 = androidx.compose.animation.core.Animatable.animateTo$default(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch: java.util.concurrent.CancellationException -> L18
            if (r5 != r0) goto Lba
            return r0
        Lba:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r0 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            r2 = 0
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setPlacementAnimationInProgress(r0, r2)     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r0 = r1.this$0     // Catch: java.util.concurrent.CancellationException -> L18
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setRunningMovingAwayAnimation$p(r0, r2)     // Catch: java.util.concurrent.CancellationException -> L18
        Lc5:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
