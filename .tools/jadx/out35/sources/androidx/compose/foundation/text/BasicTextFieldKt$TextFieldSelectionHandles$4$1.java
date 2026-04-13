package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: BasicTextField.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$4$1", f = "BasicTextField.kt", i = {}, l = {482}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class BasicTextFieldKt$TextFieldSelectionHandles$4$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextFieldSelectionState $selectionState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BasicTextFieldKt$TextFieldSelectionHandles$4$1(TextFieldSelectionState textFieldSelectionState, Continuation<? super BasicTextFieldKt$TextFieldSelectionHandles$4$1> continuation) {
        super(2, continuation);
        this.$selectionState = textFieldSelectionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BasicTextFieldKt$TextFieldSelectionHandles$4$1 basicTextFieldKt$TextFieldSelectionHandles$4$1 = new BasicTextFieldKt$TextFieldSelectionHandles$4$1(this.$selectionState, continuation);
        basicTextFieldKt$TextFieldSelectionHandles$4$1.L$0 = obj;
        return basicTextFieldKt$TextFieldSelectionHandles$4$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((BasicTextFieldKt$TextFieldSelectionHandles$4$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                TextFieldSelectionState $this$invokeSuspend_u24lambda_u240 = this.$selectionState;
                this.label = 1;
                if ($this$invokeSuspend_u24lambda_u240.selectionHandleGestures($this$pointerInput, false, this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
