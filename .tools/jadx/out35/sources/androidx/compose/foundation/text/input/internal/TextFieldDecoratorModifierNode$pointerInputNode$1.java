package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.focus.FocusRequesterModifierNodeKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: TextFieldDecoratorModifier.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {ComposerKt.providerKey}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldDecoratorModifierNode$pointerInputNode$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldDecoratorModifierNode$pointerInputNode$1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, Continuation<? super TextFieldDecoratorModifierNode$pointerInputNode$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldDecoratorModifierNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldDecoratorModifierNode$pointerInputNode$1 textFieldDecoratorModifierNode$pointerInputNode$1 = new TextFieldDecoratorModifierNode$pointerInputNode$1(this.this$0, continuation);
        textFieldDecoratorModifierNode$pointerInputNode$1.L$0 = obj;
        return textFieldDecoratorModifierNode$pointerInputNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TextFieldDecoratorModifierNode$pointerInputNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: TextFieldDecoratorModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TextFieldDecoratorModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = textFieldDecoratorModifierNode;
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$$this$SuspendingPointerInputModifierNode, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    final TextFieldSelectionState $this$invokeSuspend_u24lambda_u240 = this.this$0.getTextFieldSelectionState();
                    final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
                    PointerInputScope pointerInputScope = this.$$this$SuspendingPointerInputModifierNode;
                    Function0 requestFocus = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$requestFocus$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            if (!TextFieldSelectionState.this.getIsFocused()) {
                                FocusRequesterModifierNodeKt.requestFocus(textFieldDecoratorModifierNode);
                            }
                        }
                    };
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$1($this$invokeSuspend_u24lambda_u240, pointerInputScope, null), 1, null);
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$2(textFieldDecoratorModifierNode, $this$invokeSuspend_u24lambda_u240, pointerInputScope, requestFocus, null), 1, null);
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$3($this$invokeSuspend_u24lambda_u240, pointerInputScope, requestFocus, null), 1, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$SuspendingPointerInputModifierNode = (PointerInputScope) this.L$0;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, $this$SuspendingPointerInputModifierNode, null), this) != coroutine_suspended) {
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
