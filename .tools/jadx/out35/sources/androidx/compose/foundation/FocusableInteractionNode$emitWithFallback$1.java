package androidx.compose.foundation;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: Focusable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.FocusableInteractionNode$emitWithFallback$1", f = "Focusable.kt", i = {}, l = {309}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class FocusableInteractionNode$emitWithFallback$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DisposableHandle $handler;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ MutableInteractionSource $this_emitWithFallback;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FocusableInteractionNode$emitWithFallback$1(MutableInteractionSource mutableInteractionSource, Interaction interaction, DisposableHandle disposableHandle, Continuation<? super FocusableInteractionNode$emitWithFallback$1> continuation) {
        super(2, continuation);
        this.$this_emitWithFallback = mutableInteractionSource;
        this.$interaction = interaction;
        this.$handler = disposableHandle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FocusableInteractionNode$emitWithFallback$1(this.$this_emitWithFallback, this.$interaction, this.$handler, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FocusableInteractionNode$emitWithFallback$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        FocusableInteractionNode$emitWithFallback$1 focusableInteractionNode$emitWithFallback$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (this.$this_emitWithFallback.emit(this.$interaction, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                focusableInteractionNode$emitWithFallback$1 = this;
                break;
            case 1:
                focusableInteractionNode$emitWithFallback$1 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        DisposableHandle disposableHandle = focusableInteractionNode$emitWithFallback$1.$handler;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        return Unit.INSTANCE;
    }
}
