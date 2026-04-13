package androidx.compose.foundation.text.input.internal;

import android.os.CancellationSignal;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.platform.PlatformTextInputMethodRequest;
import androidx.compose.ui.platform.PlatformTextInputSession;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import kotlin.KotlinNothingValueException;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* compiled from: AndroidTextInputSession.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3", f = "AndroidTextInputSession.android.kt", i = {}, l = {129}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
    final /* synthetic */ ComposeInputMethodManager $composeImm;
    final /* synthetic */ ImeOptions $imeOptions;
    final /* synthetic */ TextLayoutState $layoutState;
    final /* synthetic */ Function1<ImeAction, Unit> $onImeAction;
    final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
    final /* synthetic */ TransformedTextFieldState $state;
    final /* synthetic */ MutableSharedFlow<Unit> $stylusHandwritingTrigger;
    final /* synthetic */ PlatformTextInputSession $this_platformSpecificTextInputSession;
    final /* synthetic */ ViewConfiguration $viewConfiguration;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3(MutableSharedFlow<Unit> mutableSharedFlow, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ComposeInputMethodManager composeInputMethodManager, PlatformTextInputSession platformTextInputSession, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1<? super ImeAction, Unit> function1, ViewConfiguration viewConfiguration, Continuation<? super AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3> continuation) {
        super(2, continuation);
        this.$stylusHandwritingTrigger = mutableSharedFlow;
        this.$state = transformedTextFieldState;
        this.$layoutState = textLayoutState;
        this.$composeImm = composeInputMethodManager;
        this.$this_platformSpecificTextInputSession = platformTextInputSession;
        this.$imeOptions = imeOptions;
        this.$receiveContentConfiguration = receiveContentConfiguration;
        this.$onImeAction = function1;
        this.$viewConfiguration = viewConfiguration;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3 androidTextInputSession_androidKt$platformSpecificTextInputSession$3 = new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3(this.$stylusHandwritingTrigger, this.$state, this.$layoutState, this.$composeImm, this.$this_platformSpecificTextInputSession, this.$imeOptions, this.$receiveContentConfiguration, this.$onImeAction, this.$viewConfiguration, continuation);
        androidTextInputSession_androidKt$platformSpecificTextInputSession$3.L$0 = obj;
        return androidTextInputSession_androidKt$platformSpecificTextInputSession$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
        return ((AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: AndroidTextInputSession.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1", f = "AndroidTextInputSession.android.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ComposeInputMethodManager $composeImm;
        final /* synthetic */ TransformedTextFieldState $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TransformedTextFieldState transformedTextFieldState, ComposeInputMethodManager composeInputMethodManager, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$state = transformedTextFieldState;
            this.$composeImm = composeInputMethodManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$state, this.$composeImm, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    TransformedTextFieldState transformedTextFieldState = this.$state;
                    final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                    this.label = 1;
                    if (transformedTextFieldState.collectImeNotifications(new TextFieldState.NotifyImeListener() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1$$ExternalSyntheticLambda0
                        @Override // androidx.compose.foundation.text.input.TextFieldState.NotifyImeListener
                        public final void onChange(TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, boolean z) {
                            AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3.AnonymousClass1.invokeSuspend$lambda$0(ComposeInputMethodManager.this, textFieldCharSequence, textFieldCharSequence2, z);
                        }
                    }, this) != coroutine_suspended) {
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
            throw new KotlinNothingValueException();
        }

        static final void invokeSuspend$lambda$0(ComposeInputMethodManager $composeImm, TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
            long oldSelection = oldValue.getSelection();
            long newSelection = newValue.getSelection();
            TextRange oldComposition = oldValue.getComposition();
            TextRange newComposition = newValue.getComposition();
            if (restartImeIfContentChanges && oldValue.getComposition() != null && !oldValue.contentEquals(newValue)) {
                $composeImm.restartInput();
            } else if (!TextRange.m6085equalsimpl0(oldSelection, newSelection) || !Intrinsics.areEqual(oldComposition, newComposition)) {
                $composeImm.updateSelection(TextRange.m6090getMinimpl(newSelection), TextRange.m6089getMaximpl(newSelection), newComposition != null ? TextRange.m6090getMinimpl(newComposition.getPackedValue()) : -1, newComposition != null ? TextRange.m6089getMaximpl(newComposition.getPackedValue()) : -1);
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this.$state, this.$composeImm, null), 1, null);
                MutableSharedFlow it = this.$stylusHandwritingTrigger;
                if (it != null) {
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1(it, this.$composeImm, null), 3, null);
                }
                final CursorAnchorInfoController cursorUpdatesController = new CursorAnchorInfoController(this.$state, this.$layoutState, this.$composeImm, $this$coroutineScope);
                PlatformTextInputSession platformTextInputSession = this.$this_platformSpecificTextInputSession;
                final TransformedTextFieldState transformedTextFieldState = this.$state;
                final ImeOptions imeOptions = this.$imeOptions;
                final ReceiveContentConfiguration receiveContentConfiguration = this.$receiveContentConfiguration;
                final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                final Function1<ImeAction, Unit> function1 = this.$onImeAction;
                final TextLayoutState textLayoutState = this.$layoutState;
                final ViewConfiguration viewConfiguration = this.$viewConfiguration;
                this.label = 1;
                if (platformTextInputSession.startInputMethod(new PlatformTextInputMethodRequest() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$$ExternalSyntheticLambda0
                    @Override // androidx.compose.ui.platform.PlatformTextInputMethodRequest
                    public final InputConnection createInputConnection(EditorInfo editorInfo) {
                        return AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3.invokeSuspend$lambda$2(TransformedTextFieldState.this, imeOptions, receiveContentConfiguration, composeInputMethodManager, function1, cursorUpdatesController, textLayoutState, viewConfiguration, editorInfo);
                    }
                }, this) != coroutine_suspended) {
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
        throw new KotlinNothingValueException();
    }

    static final InputConnection invokeSuspend$lambda$2(final TransformedTextFieldState $state, ImeOptions $imeOptions, final ReceiveContentConfiguration $receiveContentConfiguration, final ComposeInputMethodManager $composeImm, final Function1 $onImeAction, final CursorAnchorInfoController $cursorUpdatesController, final TextLayoutState $layoutState, final ViewConfiguration $viewConfiguration, EditorInfo outAttrs) {
        String[] strArr;
        String[] strArr2;
        AndroidTextInputSession_androidKt.logDebug$default(null, new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "createInputConnection(value=\"" + ((Object) TransformedTextFieldState.this.getVisualText()) + "\")";
            }
        }, 1, null);
        TextInputSession textInputSession = new TextInputSession() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$textInputSession$1
            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public TextFieldCharSequence getText() {
                return TransformedTextFieldState.this.getVisualText();
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public void requestEdit(Function1<? super EditingBuffer, Unit> block) {
                TransformedTextFieldState this_$iv = TransformedTextFieldState.this;
                TextFieldState $this$iv$iv = this_$iv.textFieldState;
                InputTransformation inputTransformation$iv$iv = this_$iv.inputTransformation;
                TextFieldEditUndoBehavior undoBehavior$iv$iv = TextFieldEditUndoBehavior.MergeIfPossible;
                $this$iv$iv.getMainBuffer().getChangeTracker().clearChanges();
                block.invoke($this$iv$iv.getMainBuffer());
                $this$iv$iv.commitEditAsUser(inputTransformation$iv$iv, false, undoBehavior$iv$iv);
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public void sendKeyEvent(KeyEvent keyEvent) {
                $composeImm.sendKeyEvent(keyEvent);
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            /* renamed from: onImeAction-KlQnJC8, reason: not valid java name */
            public void mo1125onImeActionKlQnJC8(int imeAction) {
                Function1<ImeAction, Unit> function1 = $onImeAction;
                if (function1 != null) {
                    function1.invoke(ImeAction.m6253boximpl(imeAction));
                }
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public boolean onCommitContent(TransferableContent transferableContent) {
                ReceiveContentConfiguration receiveContentConfiguration = $receiveContentConfiguration;
                if (receiveContentConfiguration != null) {
                    return receiveContentConfiguration.onCommitContent(transferableContent);
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public void requestCursorUpdates(int cursorUpdateMode) {
                $cursorUpdatesController.requestUpdates(cursorUpdateMode);
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public int performHandwritingGesture(HandwritingGesture gesture) {
                return HandwritingGestureApi34.INSTANCE.performHandwritingGesture$foundation_release(TransformedTextFieldState.this, gesture, $layoutState, $viewConfiguration);
            }

            @Override // androidx.compose.foundation.text.input.internal.TextInputSession
            public boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, CancellationSignal cancellationSignal) {
                return HandwritingGestureApi34.INSTANCE.previewHandwritingGesture$foundation_release(TransformedTextFieldState.this, gesture, $layoutState, cancellationSignal);
            }
        };
        TextFieldCharSequence visualText = $state.getVisualText();
        long selection = $state.getVisualText().getSelection();
        if ($receiveContentConfiguration != null) {
            strArr2 = AndroidTextInputSession_androidKt.ALL_MIME_TYPES;
            strArr = strArr2;
        } else {
            strArr = null;
        }
        EditorInfo_androidKt.m1132updatepLxbY9I(outAttrs, visualText, selection, $imeOptions, strArr);
        return new StatelessInputConnection(textInputSession, outAttrs);
    }
}
