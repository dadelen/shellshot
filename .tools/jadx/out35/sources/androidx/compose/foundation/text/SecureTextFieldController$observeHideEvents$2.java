package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;

/* compiled from: BasicSecureTextField.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.SecureTextFieldController$observeHideEvents$2", f = "BasicSecureTextField.kt", i = {}, l = {242}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SecureTextFieldController$observeHideEvents$2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SecureTextFieldController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SecureTextFieldController$observeHideEvents$2(SecureTextFieldController secureTextFieldController, Continuation<? super SecureTextFieldController$observeHideEvents$2> continuation) {
        super(2, continuation);
        this.this$0 = secureTextFieldController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SecureTextFieldController$observeHideEvents$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((SecureTextFieldController$observeHideEvents$2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SecureTextFieldController$observeHideEvents$2 secureTextFieldController$observeHideEvents$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                secureTextFieldController$observeHideEvents$2 = this;
                break;
            case 1:
                secureTextFieldController$observeHideEvents$2 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        secureTextFieldController$observeHideEvents$2.this$0.getPasswordInputTransformation().hide();
        return Unit.INSTANCE;
    }
}
