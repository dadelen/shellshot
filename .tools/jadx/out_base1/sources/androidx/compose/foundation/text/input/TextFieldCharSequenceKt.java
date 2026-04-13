package androidx.compose.foundation.text.input;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* compiled from: TextFieldCharSequence.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0007"}, d2 = {"getSelectedText", "", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getTextAfterSelection", "maxChars", "", "getTextBeforeSelection", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldCharSequenceKt {
    public static final CharSequence getTextBeforeSelection(TextFieldCharSequence $this$getTextBeforeSelection, int maxChars) {
        return $this$getTextBeforeSelection.subSequence(Math.max(0, TextRange.m6090getMinimpl($this$getTextBeforeSelection.getSelection()) - maxChars), TextRange.m6090getMinimpl($this$getTextBeforeSelection.getSelection()));
    }

    public static final CharSequence getTextAfterSelection(TextFieldCharSequence $this$getTextAfterSelection, int maxChars) {
        return $this$getTextAfterSelection.subSequence(TextRange.m6089getMaximpl($this$getTextAfterSelection.getSelection()), Math.min(TextRange.m6089getMaximpl($this$getTextAfterSelection.getSelection()) + maxChars, $this$getTextAfterSelection.length()));
    }

    public static final CharSequence getSelectedText(TextFieldCharSequence $this$getSelectedText) {
        return $this$getSelectedText.subSequence(TextRange.m6090getMinimpl($this$getSelectedText.getSelection()), TextRange.m6089getMaximpl($this$getSelectedText.getSelection()));
    }
}
