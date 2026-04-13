package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: CoreTextField.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001fH\u0001¢\u0006\u0002\u0010#\u001a0\u0010$\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010(\u001a\u001d\u0010)\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010+\u001a\u0015\u0010,\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\u0010-\u001a\u0010\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u000200H\u0002\u001a \u00101\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00102\u001a\u000203H\u0002\u001a0\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u0002062\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0002\u001a \u00107\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0013H\u0002\u001a2\u0010;\u001a\u00020\u0001*\u00020<2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\r2\u0006\u00102\u001a\u000203H\u0080@¢\u0006\u0002\u0010@\u001a\u001c\u0010A\u001a\u00020\u0007*\u00020\u00072\u0006\u0010/\u001a\u0002002\u0006\u0010%\u001a\u00020&H\u0002¨\u0006B²\u0006\n\u0010C\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "endInputSession", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "notifyFocusedRect", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "startInputSession", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previewKeyEventToDeselectOnBack", "foundation_release", "writeable"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CoreTextFieldKt {
    /* JADX WARN: Code restructure failed: missing block: B:330:0x058e, code lost:
    
        if (r2 == null) goto L289;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06e3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0796  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x07e1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x080e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x081b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x086d  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x08cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0909  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0927  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x097e  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x09b8  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x09c2  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x09dc  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0a63  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0a78  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0ab8  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0ad4  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0aeb  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0b46  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0b53  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0b7d  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0b99  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0bfe A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0c43  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0c88  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0c95  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0cb1  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0cf3  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0d38  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0d5f  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0dc9  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0de3  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0e68  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0dec  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0d6f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0cf6  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0cbf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0c8a  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0c53  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0bb7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0b64  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0b48  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0ad6  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0aba  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0a86 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0a65  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x09f7  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x09c4  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x09ba  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x098c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0936  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0889  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x082e  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0810  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x07ec  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x06a8  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x05e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:329:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0537  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CoreTextField(final androidx.compose.ui.text.input.TextFieldValue r57, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r58, androidx.compose.ui.Modifier r59, androidx.compose.ui.text.TextStyle r60, androidx.compose.ui.text.input.VisualTransformation r61, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r62, androidx.compose.foundation.interaction.MutableInteractionSource r63, androidx.compose.ui.graphics.Brush r64, boolean r65, int r66, int r67, androidx.compose.ui.text.input.ImeOptions r68, androidx.compose.foundation.text.KeyboardActions r69, boolean r70, boolean r71, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 3753
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CoreTextField$lambda$11(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0195  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CoreTextFieldRootBox(final androidx.compose.ui.Modifier r28, final androidx.compose.foundation.text.selection.TextFieldSelectionManager r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextFieldRootBox(androidx.compose.ui.Modifier, androidx.compose.foundation.text.selection.TextFieldSelectionManager, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier $this$previewKeyEventToDeselectOnBack, final LegacyTextFieldState state, final TextFieldSelectionManager manager) {
        return KeyInputModifierKt.onPreviewKeyEvent($this$previewKeyEventToDeselectOnBack, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$previewKeyEventToDeselectOnBack$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m984invokeZmokQxo(keyEvent.m5209unboximpl());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m984invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                if (LegacyTextFieldState.this.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m986cancelsTextSelectionZmokQxo(keyEvent)) {
                    z = true;
                    TextFieldSelectionManager.m1380deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(LegacyTextFieldState state, FocusRequester focusRequester, boolean allowKeyboard) {
        SoftwareKeyboardController keyboardController;
        if (!state.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!allowKeyboard || (keyboardController = state.getKeyboardController()) == null) {
                return;
            }
            keyboardController.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState state, TextFieldValue value, ImeOptions imeOptions, OffsetMapping offsetMapping) {
        state.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, value, state.getProcessor(), imeOptions, state.getOnValueChange(), state.getOnImeActionPerformed()));
        notifyFocusedRect(state, value, offsetMapping);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endInputSession(LegacyTextFieldState state) {
        TextInputSession session = state.getInputSession();
        if (session != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation_release(session, state.getProcessor(), state.getOnValueChange());
        }
        state.setInputSession(null);
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester $this$bringSelectionEndIntoView, TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
        Rect selectionEndBounds;
        int selectionEndInTransformed = offsetMapping.originalToTransformed(TextRange.m6089getMaximpl(value.getSelection()));
        if (selectionEndInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed);
        } else if (selectionEndInTransformed != 0) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed - 1);
        } else {
            long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
            selectionEndBounds = new Rect(0.0f, 0.0f, 1.0f, IntSize.m6795getHeightimpl(defaultSize));
        }
        Object bringIntoView = $this$bringSelectionEndIntoView.bringIntoView(selectionEndBounds, continuation);
        return bringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? bringIntoView : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager manager, final boolean show, Composer $composer, final int $changed) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer $composer2 = $composer.startRestartGroup(626339208);
        ComposerKt.sourceInformation($composer2, "C(SelectionToolbarAndHandles):CoreTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(show) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(626339208, $dirty, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1146)");
            }
            if (show) {
                $composer2.startReplaceGroup(-1286242594);
                ComposerKt.sourceInformation($composer2, "");
                LegacyTextFieldState state = manager.getState();
                TextLayoutResult it = null;
                if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                    LegacyTextFieldState state2 = manager.getState();
                    if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                        it = value;
                    }
                }
                if (it == null) {
                    $composer2.startReplaceGroup(-1285984396);
                } else {
                    $composer2.startReplaceGroup(-1285984395);
                    ComposerKt.sourceInformation($composer2, "");
                    if (!TextRange.m6086getCollapsedimpl(manager.getValue$foundation_release().getSelection())) {
                        $composer2.startReplaceGroup(-1680616096);
                        ComposerKt.sourceInformation($composer2, "");
                        int startOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m6092getStartimpl(manager.getValue$foundation_release().getSelection()));
                        int endOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m6087getEndimpl(manager.getValue$foundation_release().getSelection()));
                        ResolvedTextDirection startDirection = it.getBidiRunDirection(startOffset);
                        ResolvedTextDirection endDirection = it.getBidiRunDirection(Math.max(endOffset - 1, 0));
                        LegacyTextFieldState state3 = manager.getState();
                        if (state3 != null && state3.getShowSelectionHandleStart()) {
                            $composer2.startReplaceGroup(-1680216289);
                            ComposerKt.sourceInformation($composer2, "1158@51449L203");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, startDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-1679975078);
                            $composer2.endReplaceGroup();
                        }
                        LegacyTextFieldState state4 = manager.getState();
                        if (state4 != null && state4.getShowSelectionHandleEnd()) {
                            $composer2.startReplaceGroup(-1679895904);
                            ComposerKt.sourceInformation($composer2, "1165@51772L202");
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, endDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-1679655654);
                            $composer2.endReplaceGroup();
                        }
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-1679637798);
                        $composer2.endReplaceGroup();
                    }
                    LegacyTextFieldState textFieldState = manager.getState();
                    if (textFieldState != null) {
                        if (manager.isTextChanged$foundation_release()) {
                            textFieldState.setShowFloatingToolbar(false);
                        }
                        if (textFieldState.getHasFocus()) {
                            if (textFieldState.getShowFloatingToolbar()) {
                                manager.showSelectionToolbar$foundation_release();
                            } else {
                                manager.hideSelectionToolbar$foundation_release();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                $composer2.endReplaceGroup();
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(651305535);
                $composer2.endReplaceGroup();
                manager.hideSelectionToolbar$foundation_release();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$SelectionToolbarAndHandles$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    CoreTextFieldKt.SelectionToolbarAndHandles(TextFieldSelectionManager.this, show, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0192  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextFieldCursorHandle(final androidx.compose.foundation.text.selection.TextFieldSelectionManager r18, androidx.compose.runtime.Composer r19, final int r20) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle(androidx.compose.foundation.text.selection.TextFieldSelectionManager, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) {
        TextInputSession inputSession;
        LayoutCoordinates layoutCoordinates;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1 observer$iv = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            TextLayoutResultProxy layoutResult = state.getLayoutResult();
            if (layoutResult != null && (inputSession = state.getInputSession()) != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
                TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(value, state.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, state.getHasFocus(), offsetMapping);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
        }
    }
}
