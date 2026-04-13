package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.reflect.KFunction;

/* compiled from: TextFieldKeyInput.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001al\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"textFieldKeyInput", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "", "editable", "", "singleLine", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "textFieldKeyInput-2WJ9YEU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;ZZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;I)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldKeyInputKt {
    /* renamed from: textFieldKeyInput-2WJ9YEU$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1067textFieldKeyInput2WJ9YEU$default(Modifier modifier, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, Function1 function1, boolean z, boolean z2, OffsetMapping offsetMapping, UndoManager undoManager, int i, int i2, Object obj) {
        Function1 function12;
        if ((i2 & 8) == 0) {
            function12 = function1;
        } else {
            function12 = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInputKt$textFieldKeyInput$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue2) {
                    invoke2(textFieldValue2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextFieldValue it) {
                }
            };
        }
        return m1066textFieldKeyInput2WJ9YEU(modifier, legacyTextFieldState, textFieldSelectionManager, textFieldValue, function12, z, z2, offsetMapping, undoManager, i);
    }

    /* renamed from: textFieldKeyInput-2WJ9YEU, reason: not valid java name */
    public static final Modifier m1066textFieldKeyInput2WJ9YEU(Modifier $this$textFieldKeyInput_u2d2WJ9YEU, final LegacyTextFieldState state, final TextFieldSelectionManager manager, final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, final boolean editable, final boolean singleLine, final OffsetMapping offsetMapping, final UndoManager undoManager, final int imeAction) {
        return ComposedModifierKt.composed$default($this$textFieldKeyInput_u2d2WJ9YEU, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldKeyInputKt$textFieldKeyInput$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                Object value$iv2;
                TextFieldKeyInputKt$textFieldKeyInput$2$1$1 value$iv3;
                $composer.startReplaceGroup(851809892);
                ComposerKt.sourceInformation($composer, "C245@11270L41,246@11334L30,260@11814L18:TextFieldKeyInput.kt#423gt5");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(851809892, $changed, -1, "androidx.compose.foundation.text.textFieldKeyInput.<anonymous> (TextFieldKeyInput.kt:245)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1297760441, "CC(remember):TextFieldKeyInput.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new TextPreparedSelectionState();
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                TextPreparedSelectionState preparedSelectionState = (TextPreparedSelectionState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 1297762478, "CC(remember):TextFieldKeyInput.kt#9igjgp");
                Object it$iv2 = $composer.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new DeadKeyCombiner();
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                DeadKeyCombiner keyCombiner = (DeadKeyCombiner) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextFieldKeyInput processor = new TextFieldKeyInput(LegacyTextFieldState.this, manager, value, editable, singleLine, preparedSelectionState, offsetMapping, undoManager, keyCombiner, null, function1, imeAction, 512, null);
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1297777826, "CC(remember):TextFieldKeyInput.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(processor);
                Object it$iv3 = $composer.rememberedValue();
                if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new TextFieldKeyInputKt$textFieldKeyInput$2$1$1(processor);
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier onKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) ((KFunction) value$iv3));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return onKeyEvent;
            }
        }, 1, null);
    }
}
