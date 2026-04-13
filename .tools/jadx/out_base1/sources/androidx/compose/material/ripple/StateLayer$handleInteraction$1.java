package androidx.compose.material.ripple;

import androidx.compose.animation.core.AnimationSpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Ripple.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.ripple.StateLayer$handleInteraction$1", f = "Ripple.kt", i = {}, l = {497}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class StateLayer$handleInteraction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $incomingAnimationSpec;
    final /* synthetic */ float $targetAlpha;
    int label;
    final /* synthetic */ StateLayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StateLayer$handleInteraction$1(StateLayer stateLayer, float f, AnimationSpec<Float> animationSpec, Continuation<? super StateLayer$handleInteraction$1> continuation) {
        super(2, continuation);
        this.this$0 = stateLayer;
        this.$targetAlpha = f;
        this.$incomingAnimationSpec = animationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StateLayer$handleInteraction$1(this.this$0, this.$targetAlpha, this.$incomingAnimationSpec, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StateLayer$handleInteraction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.animation.core.Animatable.animateTo$default(androidx.compose.animation.core.Animatable, java.lang.Object, androidx.compose.animation.core.AnimationSpec, java.lang.Object, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation, int, java.lang.Object):java.lang.Object
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.util.NoSuchElementException
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1053)
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:162)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            switch(r1) {
                case 0: goto L17;
                case 1: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L12:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L3c
        L17:
            kotlin.ResultKt.throwOnFailure(r12)
            r1 = r11
            androidx.compose.material.ripple.StateLayer r2 = r1.this$0
            androidx.compose.animation.core.Animatable r3 = androidx.compose.material.ripple.StateLayer.access$getAnimatedAlpha$p(r2)
            float r2 = r1.$targetAlpha
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r2)
            androidx.compose.animation.core.AnimationSpec<java.lang.Float> r5 = r1.$incomingAnimationSpec
            r8 = r1
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r2 = 1
            r1.label = r2
            r6 = 0
            r7 = 0
            r9 = 12
            r10 = 0
            java.lang.Object r2 = androidx.compose.animation.core.Animatable.animateTo$default(r3, r4, r5, r6, r7, r8, r9, r10)
            if (r2 != r0) goto L3b
            return r0
        L3b:
            r0 = r1
        L3c:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.StateLayer$handleInteraction$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
