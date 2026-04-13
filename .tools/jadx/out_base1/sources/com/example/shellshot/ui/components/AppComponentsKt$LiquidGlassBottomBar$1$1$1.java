package com.example.shellshot.ui.components;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableIntState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppComponents.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.AppComponentsKt$LiquidGlassBottomBar$1$1$1", f = "AppComponents.kt", i = {0, 1}, l = {1181, 1182}, m = "invokeSuspend", n = {"hopDistance", "hopDistance"}, s = {"I$0", "I$0"})
/* loaded from: classes11.dex */
final class AppComponentsKt$LiquidGlassBottomBar$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableIntState $previousSelectedIndex$delegate;
    final /* synthetic */ int $selectedIndex;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $stretchProgress;
    final /* synthetic */ LiquidGlassBottomBarStyle $style;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppComponentsKt$LiquidGlassBottomBar$1$1$1(int i, Animatable<Float, AnimationVector1D> animatable, LiquidGlassBottomBarStyle liquidGlassBottomBarStyle, MutableIntState mutableIntState, Continuation<? super AppComponentsKt$LiquidGlassBottomBar$1$1$1> continuation) {
        super(2, continuation);
        this.$selectedIndex = i;
        this.$stretchProgress = animatable;
        this.$style = liquidGlassBottomBarStyle;
        this.$previousSelectedIndex$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppComponentsKt$LiquidGlassBottomBar$1$1$1(this.$selectedIndex, this.$stretchProgress, this.$style, this.$previousSelectedIndex$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppComponentsKt$LiquidGlassBottomBar$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0099  */
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
            r3 = 0
            switch(r1) {
                case 0: goto L20;
                case 1: goto L1a;
                case 2: goto L13;
                default: goto Lb;
            }
        Lb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L13:
            int r0 = r12.I$0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L9a
        L1a:
            int r1 = r12.I$0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L6c
        L20:
            kotlin.ResultKt.throwOnFailure(r13)
            androidx.compose.runtime.MutableIntState r1 = r12.$previousSelectedIndex$delegate
            int r1 = com.example.shellshot.ui.components.AppComponentsKt.access$LiquidGlassBottomBar$lambda$111$lambda$80(r1)
            int r4 = r12.$selectedIndex
            r5 = 1
            if (r1 != r4) goto L46
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r1 = r12.$stretchProgress
            java.lang.Object r1 = r1.getValue()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L40
            r1 = r5
            goto L41
        L40:
            r1 = r2
        L41:
            if (r1 == 0) goto L46
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L46:
            int r1 = r12.$selectedIndex
            androidx.compose.runtime.MutableIntState r4 = r12.$previousSelectedIndex$delegate
            int r4 = com.example.shellshot.ui.components.AppComponentsKt.access$LiquidGlassBottomBar$lambda$111$lambda$80(r4)
            int r1 = r1 - r4
            int r1 = java.lang.Math.abs(r1)
            int r1 = kotlin.ranges.RangesKt.coerceAtLeast(r1, r5)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r4 = r12.$stretchProgress
            float r6 = (float) r1
            java.lang.Float r6 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            r7 = r12
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r12.I$0 = r1
            r12.label = r5
            java.lang.Object r4 = r4.snapTo(r6, r7)
            if (r4 != r0) goto L6c
            return r0
        L6c:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r4 = r12.$stretchProgress
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r3)
            com.example.shellshot.ui.components.LiquidGlassBottomBarStyle r3 = r12.$style
            int r3 = r3.getMotionDurationMillis()
            androidx.compose.animation.core.CubicBezierEasing r6 = com.example.shellshot.ui.components.AppComponentsKt.access$getPremiumMotionEasing$p()
            androidx.compose.animation.core.Easing r6 = (androidx.compose.animation.core.Easing) r6
            r7 = 0
            r8 = 2
            androidx.compose.animation.core.TweenSpec r2 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r3, r2, r6, r8, r7)
            r6 = r2
            androidx.compose.animation.core.AnimationSpec r6 = (androidx.compose.animation.core.AnimationSpec) r6
            r9 = r12
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r12.I$0 = r1
            r12.label = r8
            r8 = 0
            r10 = 12
            r11 = 0
            java.lang.Object r2 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r2 != r0) goto L99
            return r0
        L99:
            r0 = r1
        L9a:
            androidx.compose.runtime.MutableIntState r1 = r12.$previousSelectedIndex$delegate
            int r2 = r12.$selectedIndex
            com.example.shellshot.ui.components.AppComponentsKt.access$LiquidGlassBottomBar$lambda$111$lambda$81(r1, r2)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt$LiquidGlassBottomBar$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
