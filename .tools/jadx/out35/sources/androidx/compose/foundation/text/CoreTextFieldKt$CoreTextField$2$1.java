package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.TextInputService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: CoreTextField.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2$1", f = "CoreTextField.kt", i = {}, l = {357}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class CoreTextFieldKt$CoreTextField$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ImeOptions $imeOptions;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ LegacyTextFieldState $state;
    final /* synthetic */ TextInputService $textInputService;
    final /* synthetic */ State<Boolean> $writeable$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoreTextFieldKt$CoreTextField$2$1(LegacyTextFieldState legacyTextFieldState, State<Boolean> state, TextInputService textInputService, TextFieldSelectionManager textFieldSelectionManager, ImeOptions imeOptions, Continuation<? super CoreTextFieldKt$CoreTextField$2$1> continuation) {
        super(2, continuation);
        this.$state = legacyTextFieldState;
        this.$writeable$delegate = state;
        this.$textInputService = textInputService;
        this.$manager = textFieldSelectionManager;
        this.$imeOptions = imeOptions;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoreTextFieldKt$CoreTextField$2$1(this.$state, this.$writeable$delegate, this.$textInputService, this.$manager, this.$imeOptions, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoreTextFieldKt$CoreTextField$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable th;
        CoreTextFieldKt$CoreTextField$2$1 coreTextFieldKt$CoreTextField$2$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    final State<Boolean> state = this.$writeable$delegate;
                    Flow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            boolean CoreTextField$lambda$11;
                            CoreTextField$lambda$11 = CoreTextFieldKt.CoreTextField$lambda$11(state);
                            return Boolean.valueOf(CoreTextField$lambda$11);
                        }
                    });
                    final LegacyTextFieldState legacyTextFieldState = this.$state;
                    final TextInputService textInputService = this.$textInputService;
                    final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                    final ImeOptions imeOptions = this.$imeOptions;
                    this.label = 1;
                    if (snapshotFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2$1.2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Boolean) value).booleanValue(), (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(boolean writeable, Continuation<? super Unit> continuation) {
                            if (!writeable || !LegacyTextFieldState.this.getHasFocus()) {
                                CoreTextFieldKt.endInputSession(LegacyTextFieldState.this);
                            } else {
                                CoreTextFieldKt.startInputSession(textInputService, LegacyTextFieldState.this, textFieldSelectionManager.getValue$foundation_release(), imeOptions, textFieldSelectionManager.getOffsetMapping());
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coreTextFieldKt$CoreTextField$2$1 = this;
                    CoreTextFieldKt.endInputSession(coreTextFieldKt$CoreTextField$2$1.$state);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    coreTextFieldKt$CoreTextField$2$1 = this;
                    CoreTextFieldKt.endInputSession(coreTextFieldKt$CoreTextField$2$1.$state);
                    throw th;
                }
            case 1:
                coreTextFieldKt$CoreTextField$2$1 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    CoreTextFieldKt.endInputSession(coreTextFieldKt$CoreTextField$2$1.$state);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    CoreTextFieldKt.endInputSession(coreTextFieldKt$CoreTextField$2$1.$state);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
