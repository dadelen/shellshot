package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TextFieldState.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a&\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0001\u001a\u0012\u0010\r\u001a\u00020\f*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0006\u001a\u0012\u0010\u000f\u001a\u00020\f*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"TextFieldState", "Landroidx/compose/foundation/text/input/TextFieldState;", "initialValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "rememberTextFieldState", "initialText", "", "initialSelection", "Landroidx/compose/ui/text/TextRange;", "rememberTextFieldState-Le-punE", "(Ljava/lang/String;JLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/text/input/TextFieldState;", "clearText", "", "setTextAndPlaceCursorAtEnd", "text", "setTextAndSelectAll", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldStateKt {
    public static final TextFieldState TextFieldState(TextFieldValue initialValue) {
        return new TextFieldState(initialValue.getText(), initialValue.getSelection(), (DefaultConstructorMarker) null);
    }

    /* renamed from: rememberTextFieldState-Le-punE, reason: not valid java name */
    public static final TextFieldState m1102rememberTextFieldStateLepunE(final String initialText, final long initialSelection, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1125389485, "C(rememberTextFieldState)P(1,0:c#ui.text.TextRange)620@26335L53,620@26288L100:TextFieldState.kt#hp9ohv");
        if ((i & 1) != 0) {
            initialText = "";
        }
        if ((i & 2) != 0) {
            initialSelection = TextRangeKt.TextRange(initialText.length());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1125389485, $changed, -1, "androidx.compose.foundation.text.input.rememberTextFieldState (TextFieldState.kt:620)");
        }
        Object[] objArr = new Object[0];
        TextFieldState.Saver saver = TextFieldState.Saver.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 975793873, "CC(remember):TextFieldState.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(initialSelection)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialText)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<TextFieldState>() { // from class: androidx.compose.foundation.text.input.TextFieldStateKt$rememberTextFieldState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextFieldState invoke() {
                    return new TextFieldState(initialText, initialSelection, (DefaultConstructorMarker) null);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextFieldState textFieldState = (TextFieldState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 48, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldState;
    }

    public static final void setTextAndPlaceCursorAtEnd(TextFieldState $this$setTextAndPlaceCursorAtEnd, String text) {
        TextFieldBuffer mutableValue$iv = $this$setTextAndPlaceCursorAtEnd.startEdit();
        try {
            mutableValue$iv.replace(0, mutableValue$iv.getLength(), text);
            TextFieldBufferKt.placeCursorAtEnd(mutableValue$iv);
            $this$setTextAndPlaceCursorAtEnd.commitEdit(mutableValue$iv);
        } finally {
            $this$setTextAndPlaceCursorAtEnd.finishEditing();
        }
    }

    public static final void setTextAndSelectAll(TextFieldState $this$setTextAndSelectAll, String text) {
        TextFieldBuffer mutableValue$iv = $this$setTextAndSelectAll.startEdit();
        try {
            mutableValue$iv.replace(0, mutableValue$iv.getLength(), text);
            TextFieldBufferKt.selectAll(mutableValue$iv);
            $this$setTextAndSelectAll.commitEdit(mutableValue$iv);
        } finally {
            $this$setTextAndSelectAll.finishEditing();
        }
    }

    public static final void clearText(TextFieldState $this$clearText) {
        TextFieldBuffer mutableValue$iv = $this$clearText.startEdit();
        try {
            TextFieldBufferKt.delete(mutableValue$iv, 0, mutableValue$iv.getLength());
            TextFieldBufferKt.placeCursorAtEnd(mutableValue$iv);
            $this$clearText.commitEdit(mutableValue$iv);
        } finally {
            $this$clearText.finishEditing();
        }
    }
}
