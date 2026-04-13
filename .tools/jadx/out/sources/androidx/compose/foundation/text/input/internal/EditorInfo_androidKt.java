package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.text.handwriting.StylusHandwriting_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.inputmethod.EditorInfoCompat;
import kotlin.Metadata;

/* compiled from: EditorInfo.android.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a@\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, d2 = {"hasFlag", "", "bits", "", "flag", "update", "", "Landroid/view/inputmethod/EditorInfo;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "contentMimeTypes", "", "", "update-pLxbY9I", "(Landroid/view/inputmethod/EditorInfo;Ljava/lang/CharSequence;JLandroidx/compose/ui/text/input/ImeOptions;[Ljava/lang/String;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditorInfo_androidKt {
    /* renamed from: update-pLxbY9I$default, reason: not valid java name */
    public static /* synthetic */ void m1133updatepLxbY9I$default(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr, int i, Object obj) {
        String[] strArr2;
        if ((i & 8) == 0) {
            strArr2 = strArr;
        } else {
            strArr2 = null;
        }
        m1132updatepLxbY9I(editorInfo, charSequence, j, imeOptions, strArr2);
    }

    /* renamed from: update-pLxbY9I, reason: not valid java name */
    public static final void m1132updatepLxbY9I(EditorInfo $this$update_u2dpLxbY9I, CharSequence text, long selection, ImeOptions imeOptions, String[] contentMimeTypes) {
        String it;
        int imeAction = imeOptions.getImeAction();
        int i = 3;
        int i2 = 6;
        if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6269getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i2 = 0;
            }
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6273getNoneeUduSuo())) {
            i2 = 1;
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6271getGoeUduSuo())) {
            i2 = 2;
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6272getNexteUduSuo())) {
            i2 = 5;
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6274getPreviouseUduSuo())) {
            i2 = 7;
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6275getSearcheUduSuo())) {
            i2 = 3;
        } else if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6276getSendeUduSuo())) {
            i2 = 4;
        } else if (!ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6270getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction".toString());
        }
        $this$update_u2dpLxbY9I.imeOptions = i2;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (it = platformImeOptions.getPrivateImeOptions()) != null) {
            $this$update_u2dpLxbY9I.privateImeOptions = it;
        }
        LocaleListHelper.INSTANCE.setHintLocales($this$update_u2dpLxbY9I, imeOptions.getHintLocales());
        int keyboardType = imeOptions.getKeyboardType();
        if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6330getTextPjHm6EE())) {
            i = 1;
        } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6323getAsciiPjHm6EE())) {
            $this$update_u2dpLxbY9I.imeOptions |= Integer.MIN_VALUE;
            i = 1;
        } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6326getNumberPjHm6EE())) {
            i = 2;
        } else if (!KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6329getPhonePjHm6EE())) {
            if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6332getUriPjHm6EE())) {
                i = 17;
            } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6325getEmailPjHm6EE())) {
                i = 33;
            } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6328getPasswordPjHm6EE())) {
                i = 129;
            } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6327getNumberPasswordPjHm6EE())) {
                i = 18;
            } else if (KeyboardType.m6309equalsimpl0(keyboardType, KeyboardType.INSTANCE.m6324getDecimalPjHm6EE())) {
                i = 8194;
            } else {
                throw new IllegalStateException("Invalid Keyboard Type".toString());
            }
        }
        $this$update_u2dpLxbY9I.inputType = i;
        if (!imeOptions.getSingleLine() && hasFlag($this$update_u2dpLxbY9I.inputType, 1)) {
            $this$update_u2dpLxbY9I.inputType |= 131072;
            if (ImeAction.m6256equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m6269getDefaulteUduSuo())) {
                $this$update_u2dpLxbY9I.imeOptions |= BasicMeasure.EXACTLY;
            }
        }
        if (hasFlag($this$update_u2dpLxbY9I.inputType, 1)) {
            int capitalization = imeOptions.getCapitalization();
            if (KeyboardCapitalization.m6292equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m6301getCharactersIUNYP9k())) {
                $this$update_u2dpLxbY9I.inputType |= 4096;
            } else if (KeyboardCapitalization.m6292equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m6305getWordsIUNYP9k())) {
                $this$update_u2dpLxbY9I.inputType |= 8192;
            } else if (KeyboardCapitalization.m6292equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m6303getSentencesIUNYP9k())) {
                $this$update_u2dpLxbY9I.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                $this$update_u2dpLxbY9I.inputType |= 32768;
            }
        }
        $this$update_u2dpLxbY9I.initialSelStart = TextRange.m6092getStartimpl(selection);
        $this$update_u2dpLxbY9I.initialSelEnd = TextRange.m6087getEndimpl(selection);
        EditorInfoCompat.setInitialSurroundingText($this$update_u2dpLxbY9I, text);
        if (contentMimeTypes != null) {
            EditorInfoCompat.setContentMimeTypes($this$update_u2dpLxbY9I, contentMimeTypes);
        }
        $this$update_u2dpLxbY9I.imeOptions |= 33554432;
        if (StylusHandwriting_androidKt.isStylusHandwritingSupported() && !KeyboardType.m6309equalsimpl0(imeOptions.getKeyboardType(), KeyboardType.INSTANCE.m6328getPasswordPjHm6EE()) && !KeyboardType.m6309equalsimpl0(imeOptions.getKeyboardType(), KeyboardType.INSTANCE.m6327getNumberPasswordPjHm6EE())) {
            EditorInfoCompat.setStylusHandwritingEnabled($this$update_u2dpLxbY9I, true);
            EditorInfoApi34.INSTANCE.setHandwritingGestures($this$update_u2dpLxbY9I);
        } else {
            EditorInfoCompat.setStylusHandwritingEnabled($this$update_u2dpLxbY9I, false);
        }
    }

    private static final boolean hasFlag(int bits, int flag) {
        return (bits & flag) == flag;
    }
}
