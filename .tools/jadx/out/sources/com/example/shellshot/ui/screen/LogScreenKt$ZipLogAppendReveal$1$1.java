package com.example.shellshot.ui.screen;

import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LogScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.screen.LogScreenKt$ZipLogAppendReveal$1$1", f = "LogScreen.kt", i = {}, l = {260}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes10.dex */
final class LogScreenKt$ZipLogAppendReveal$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $animationHandled$delegate;
    final /* synthetic */ MutableState<Boolean> $entered$delegate;
    final /* synthetic */ Function0<Unit> $onAnimationHandled;
    final /* synthetic */ boolean $shouldAnimate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LogScreenKt$ZipLogAppendReveal$1$1(boolean z, Function0<Unit> function0, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, Continuation<? super LogScreenKt$ZipLogAppendReveal$1$1> continuation) {
        super(2, continuation);
        this.$shouldAnimate = z;
        this.$onAnimationHandled = function0;
        this.$animationHandled$delegate = mutableState;
        this.$entered$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogScreenKt$ZipLogAppendReveal$1$1(this.$shouldAnimate, this.$onAnimationHandled, this.$animationHandled$delegate, this.$entered$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LogScreenKt$ZipLogAppendReveal$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        boolean ZipLogAppendReveal$lambda$31;
        boolean ZipLogAppendReveal$lambda$34;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$shouldAnimate) {
                    ZipLogAppendReveal$lambda$34 = LogScreenKt.ZipLogAppendReveal$lambda$34(this.$animationHandled$delegate);
                    if (!ZipLogAppendReveal$lambda$34) {
                        this.label = 1;
                        if (MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$ZipLogAppendReveal$1$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit unit;
                                ((Long) obj).longValue();
                                unit = Unit.INSTANCE;
                                return unit;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        LogScreenKt.ZipLogAppendReveal$lambda$32(this.$entered$delegate, true);
                        LogScreenKt.ZipLogAppendReveal$lambda$35(this.$animationHandled$delegate, true);
                        this.$onAnimationHandled.invoke();
                        return Unit.INSTANCE;
                    }
                }
                ZipLogAppendReveal$lambda$31 = LogScreenKt.ZipLogAppendReveal$lambda$31(this.$entered$delegate);
                if (!ZipLogAppendReveal$lambda$31) {
                    LogScreenKt.ZipLogAppendReveal$lambda$32(this.$entered$delegate, true);
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                LogScreenKt.ZipLogAppendReveal$lambda$32(this.$entered$delegate, true);
                LogScreenKt.ZipLogAppendReveal$lambda$35(this.$animationHandled$delegate, true);
                this.$onAnimationHandled.invoke();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
