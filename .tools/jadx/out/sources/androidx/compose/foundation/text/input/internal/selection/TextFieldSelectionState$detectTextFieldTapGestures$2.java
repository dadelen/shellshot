package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: TextFieldSelectionState.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {491}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldSelectionState$detectTextFieldTapGestures$2 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    /* synthetic */ long J$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionState$detectTextFieldTapGestures$2(MutableInteractionSource mutableInteractionSource, TextFieldSelectionState textFieldSelectionState, Continuation<? super TextFieldSelectionState$detectTextFieldTapGestures$2> continuation) {
        super(3, continuation);
        this.$interactionSource = mutableInteractionSource;
        this.this$0 = textFieldSelectionState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
        return m1267invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
    }

    /* renamed from: invoke-d-4ec7I, reason: not valid java name */
    public final Object m1267invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
        TextFieldSelectionState$detectTextFieldTapGestures$2 textFieldSelectionState$detectTextFieldTapGestures$2 = new TextFieldSelectionState$detectTextFieldTapGestures$2(this.$interactionSource, this.this$0, continuation);
        textFieldSelectionState$detectTextFieldTapGestures$2.L$0 = pressGestureScope;
        textFieldSelectionState$detectTextFieldTapGestures$2.J$0 = j;
        return textFieldSelectionState$detectTextFieldTapGestures$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        TextFieldSelectionState$detectTextFieldTapGestures$2 textFieldSelectionState$detectTextFieldTapGestures$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PressGestureScope $this$detectTapAndPress = (PressGestureScope) this.L$0;
                long offset = this.J$0;
                MutableInteractionSource interactionSource = this.$interactionSource;
                if (interactionSource != null) {
                    TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = new TextFieldSelectionState$detectTextFieldTapGestures$2$1$1($this$detectTapAndPress, this.this$0, offset, interactionSource, null);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(textFieldSelectionState$detectTextFieldTapGestures$2$1$1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    textFieldSelectionState$detectTextFieldTapGestures$2 = this;
                }
                return Unit.INSTANCE;
            case 1:
                textFieldSelectionState$detectTextFieldTapGestures$2 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
