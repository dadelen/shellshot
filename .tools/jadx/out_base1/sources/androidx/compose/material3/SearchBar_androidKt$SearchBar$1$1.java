package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SearchBar.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.SearchBar_androidKt$SearchBar$1$1", f = "SearchBar.android.kt", i = {}, l = {191}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class SearchBar_androidKt$SearchBar$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchBar_androidKt$SearchBar$1$1(Animatable<Float, AnimationVector1D> animatable, boolean z, MutableFloatState mutableFloatState, MutableState<BackEventCompat> mutableState, MutableState<BackEventCompat> mutableState2, Continuation<? super SearchBar_androidKt$SearchBar$1$1> continuation) {
        super(2, continuation);
        this.$animationProgress = animatable;
        this.$expanded = z;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState;
        this.$currentBackEvent = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBar_androidKt$SearchBar$1$1(this.$animationProgress, this.$expanded, this.$finalBackProgress, this.$firstBackEvent, this.$currentBackEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchBar_androidKt$SearchBar$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x009a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r17
            int r2 = r1.label
            switch(r2) {
                case 0: goto L1c;
                case 1: goto L13;
                default: goto Lb;
            }
        Lb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L13:
            r1 = r17
            r0 = r18
            kotlin.ResultKt.throwOnFailure(r0)
            goto L95
        L1c:
            kotlin.ResultKt.throwOnFailure(r18)
            r1 = r17
            r2 = r18
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r3 = r1.$animationProgress
            java.lang.Object r3 = r3.getValue()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            r7 = 1
            if (r3 <= 0) goto L4a
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r3 = r1.$animationProgress
            java.lang.Object r3 = r3.getValue()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L4a
            r3 = r7
            goto L4b
        L4a:
            r3 = r6
        L4b:
            if (r3 == 0) goto L52
            androidx.compose.animation.core.FiniteAnimationSpec r8 = androidx.compose.material3.SearchBar_androidKt.access$getAnimationPredictiveBackExitFloatSpec$p()
            goto L5f
        L52:
            boolean r3 = r1.$expanded
            if (r3 == 0) goto L5b
            androidx.compose.animation.core.FiniteAnimationSpec r8 = androidx.compose.material3.SearchBar_androidKt.access$getAnimationEnterFloatSpec$p()
            goto L5f
        L5b:
            androidx.compose.animation.core.FiniteAnimationSpec r8 = androidx.compose.material3.SearchBar_androidKt.access$getAnimationExitFloatSpec$p()
        L5f:
            boolean r3 = r1.$expanded
            if (r3 == 0) goto L66
            r4 = r5
        L66:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r3 = r1.$animationProgress
            java.lang.Object r3 = r3.getValue()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L77
            r6 = r7
        L77:
            if (r6 != 0) goto L96
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r9 = r1.$animationProgress
            java.lang.Float r10 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r4)
            r11 = r8
            androidx.compose.animation.core.AnimationSpec r11 = (androidx.compose.animation.core.AnimationSpec) r11
            r14 = r1
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r1.label = r7
            r12 = 0
            r13 = 0
            r15 = 12
            r16 = 0
            java.lang.Object r3 = androidx.compose.animation.core.Animatable.animateTo$default(r9, r10, r11, r12, r13, r14, r15, r16)
            if (r3 != r0) goto L94
            return r0
        L94:
            r0 = r2
        L95:
            r2 = r0
        L96:
            boolean r0 = r1.$expanded
            if (r0 != 0) goto Lac
            androidx.compose.runtime.MutableFloatState r0 = r1.$finalBackProgress
            r3 = 2143289344(0x7fc00000, float:NaN)
            r0.setFloatValue(r3)
            androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r0 = r1.$firstBackEvent
            r3 = 0
            r0.setValue(r3)
            androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r0 = r1.$currentBackEvent
            r0.setValue(r3)
        Lac:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt$SearchBar$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
