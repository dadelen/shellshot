package com.example.shellshot.ui.components;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.AppleLiquidNavBarKt$AppleLiquidNavBar$2$1", f = "AppleLiquidNavBar.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class AppleLiquidNavBarKt$AppleLiquidNavBar$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $currentIndex;
    final /* synthetic */ HapticFeedback $hapticFeedback;
    final /* synthetic */ MutableIntState $lastHapticIndex$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppleLiquidNavBarKt$AppleLiquidNavBar$2$1(int i, HapticFeedback hapticFeedback, MutableIntState mutableIntState, Continuation<? super AppleLiquidNavBarKt$AppleLiquidNavBar$2$1> continuation) {
        super(2, continuation);
        this.$currentIndex = i;
        this.$hapticFeedback = hapticFeedback;
        this.$lastHapticIndex$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppleLiquidNavBarKt$AppleLiquidNavBar$2$1(this.$currentIndex, this.$hapticFeedback, this.$lastHapticIndex$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppleLiquidNavBarKt$AppleLiquidNavBar$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int AppleLiquidNavBar$lambda$2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AppleLiquidNavBar$lambda$2 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$2(this.$lastHapticIndex$delegate);
                if (AppleLiquidNavBar$lambda$2 != this.$currentIndex) {
                    this.$hapticFeedback.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4891getLongPress5zf0vsI());
                    this.$lastHapticIndex$delegate.setIntValue(this.$currentIndex);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
