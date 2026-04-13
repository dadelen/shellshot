package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldBuffer.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\r\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001TB1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0014\u0010/\u001a\u00060\u0001j\u0002`\u00022\u0006\u00100\u001a\u000201H\u0016J\u0016\u0010/\u001a\u00060\u0001j\u0002`\u00022\b\u00102\u001a\u0004\u0018\u00010#H\u0016J&\u0010/\u001a\u00060\u0001j\u0002`\u00022\b\u00102\u001a\u0004\u0018\u00010#2\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001bH\u0016J\u0006\u00105\u001a\u00020#J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\b\u00108\u001a\u000209H\u0002J \u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\u001bH\u0002J\u000e\u0010>\u001a\u0002092\u0006\u00107\u001a\u00020\u001bJ\u000e\u0010?\u001a\u0002092\u0006\u00107\u001a\u00020\u001bJ\u001e\u0010@\u001a\u0002092\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u00020#J9\u0010@\u001a\u0002092\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u00020#2\b\b\u0002\u0010A\u001a\u00020\u001b2\b\b\u0002\u0010B\u001a\u00020\u001bH\u0000¢\u0006\u0002\bCJ \u0010D\u001a\u0002092\u0006\u00107\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020\u00182\u0006\u0010F\u001a\u00020\u0018H\u0002J\u001a\u0010G\u001a\u0002092\u0006\u0010H\u001a\u00020\u001fH\u0002ø\u0001\u0000¢\u0006\u0004\bI\u0010,J\u0006\u0010J\u001a\u000209J\u0015\u0010K\u001a\u0002092\u0006\u0010L\u001a\u00020#H\u0000¢\u0006\u0002\bMJ\b\u0010N\u001a\u00020OH\u0016J(\u0010P\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\u001f2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\bR\u0010SR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\u00020\u001f8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R*\u0010)\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u001f8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b*\u0010!\"\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u001fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010.\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006U"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "initialChanges", "Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "originalValue", "offsetMappingCalculator", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/ChangeTracker;Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)V", "backingChangeTracker", "buffer", "Landroidx/compose/foundation/text/input/internal/PartialGapBuffer;", "changeTracker", "getChangeTracker", "()Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "getChanges$annotations", "()V", "getChanges", "()Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "hasSelection", "", "()Z", "length", "", "getLength", "()I", "originalSelection", "Landroidx/compose/ui/text/TextRange;", "getOriginalSelection-d9O1mEE", "()J", "originalText", "", "getOriginalText", "()Ljava/lang/CharSequence;", "getOriginalValue$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "value", "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "selectionInChars", "J", "append", "char", "", "text", "start", "end", "asCharSequence", "charAt", "index", "clearChangeList", "", "onTextWillChange", "replaceStart", "replaceEnd", "newLength", "placeCursorAfterCharAt", "placeCursorBeforeCharAt", "replace", "textStart", "textEnd", "replace$foundation_release", "requireValidIndex", "startExclusive", "endExclusive", "requireValidRange", "range", "requireValidRange-5zc-tL8", "revertAllChanges", "setTextIfChanged", "newText", "setTextIfChanged$foundation_release", "toString", "", "toTextFieldCharSequence", "composition", "toTextFieldCharSequence-udt6zUU$foundation_release", "(JLandroidx/compose/ui/text/TextRange;)Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "ChangeList", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldBuffer implements Appendable {
    public static final int $stable = 8;
    private ChangeTracker backingChangeTracker;
    private final PartialGapBuffer buffer;
    private final OffsetMappingCalculator offsetMappingCalculator;
    private final TextFieldCharSequence originalValue;
    private long selectionInChars;

    /* compiled from: TextFieldBuffer.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "", "changeCount", "", "getChangeCount", "()I", "getOriginalRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getOriginalRange--jx7JFs", "(I)J", "getRange", "getRange--jx7JFs", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ChangeList {
        int getChangeCount();

        /* renamed from: getOriginalRange--jx7JFs, reason: not valid java name */
        long mo1095getOriginalRangejx7JFs(int changeIndex);

        /* renamed from: getRange--jx7JFs, reason: not valid java name */
        long mo1096getRangejx7JFs(int changeIndex);
    }

    public static /* synthetic */ void getChanges$annotations() {
    }

    public TextFieldBuffer(TextFieldCharSequence initialValue, ChangeTracker initialChanges, TextFieldCharSequence originalValue, OffsetMappingCalculator offsetMappingCalculator) {
        this.originalValue = originalValue;
        this.offsetMappingCalculator = offsetMappingCalculator;
        this.buffer = new PartialGapBuffer(initialValue);
        this.backingChangeTracker = initialChanges != null ? new ChangeTracker(initialChanges) : null;
        this.selectionInChars = initialValue.getSelection();
    }

    public /* synthetic */ TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldCharSequence, (i & 2) != 0 ? null : changeTracker, (i & 4) != 0 ? textFieldCharSequence : textFieldCharSequence2, (i & 8) != 0 ? null : offsetMappingCalculator);
    }

    /* renamed from: getOriginalValue$foundation_release, reason: from getter */
    public final TextFieldCharSequence getOriginalValue() {
        return this.originalValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ChangeTracker getChangeTracker() {
        ChangeTracker changeTracker = this.backingChangeTracker;
        if (changeTracker != null) {
            return changeTracker;
        }
        ChangeTracker changeTracker2 = new ChangeTracker(null, 1, 0 == true ? 1 : 0);
        this.backingChangeTracker = changeTracker2;
        return changeTracker2;
    }

    public final int getLength() {
        return this.buffer.length();
    }

    public final CharSequence getOriginalText() {
        return this.originalValue.getText();
    }

    /* renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name */
    public final long m1091getOriginalSelectiond9O1mEE() {
        return this.originalValue.getSelection();
    }

    public final ChangeList getChanges() {
        return getChangeTracker();
    }

    public final boolean hasSelection() {
        return !TextRange.m6086getCollapsedimpl(getSelectionInChars());
    }

    /* renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelectionInChars() {
        return this.selectionInChars;
    }

    /* renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1093setSelection5zctL8(long value) {
        m1089requireValidRange5zctL8(value);
        this.selectionInChars = value;
    }

    public final void replace(int start, int end, CharSequence text) {
        replace$foundation_release(start, end, text, 0, text.length());
    }

    public static /* synthetic */ void replace$foundation_release$default(TextFieldBuffer textFieldBuffer, int i, int i2, CharSequence charSequence, int i3, int i4, int i5, Object obj) {
        int i6;
        int i7;
        if ((i5 & 8) == 0) {
            i6 = i3;
        } else {
            i6 = 0;
        }
        if ((i5 & 16) == 0) {
            i7 = i4;
        } else {
            i7 = charSequence.length();
        }
        textFieldBuffer.replace$foundation_release(i, i2, charSequence, i6, i7);
    }

    public final void replace$foundation_release(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (start <= end) {
            if (!(textStart <= textEnd)) {
                throw new IllegalArgumentException(("Expected textStart=" + textStart + " <= textEnd=" + textEnd).toString());
            }
            onTextWillChange(start, end, textEnd - textStart);
            this.buffer.replace(start, end, text, textStart, textEnd);
            return;
        }
        throw new IllegalArgumentException(("Expected start=" + start + " <= end=" + end).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
    
        if (r4 < r5) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setTextIfChanged$foundation_release(java.lang.CharSequence r14) {
        /*
            r13 = this;
            androidx.compose.foundation.text.input.internal.PartialGapBuffer r0 = r13.buffer
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r1 = 0
            r2 = 0
            int r3 = r0.length()
            r4 = 0
            int r5 = r14.length()
            int r6 = r0.length()
            r7 = 0
            r8 = 1
            if (r6 <= 0) goto L19
            r6 = r8
            goto L1a
        L19:
            r6 = r7
        L1a:
            if (r6 == 0) goto L57
            int r6 = r14.length()
            if (r6 <= 0) goto L23
            r7 = r8
        L23:
            if (r7 == 0) goto L57
            r6 = 0
            r7 = 0
        L27:
            if (r6 != 0) goto L39
            char r8 = r0.charAt(r2)
            char r9 = r14.charAt(r4)
            if (r8 != r9) goto L38
            int r2 = r2 + 1
            int r4 = r4 + 1
            goto L39
        L38:
            r6 = 1
        L39:
            if (r7 != 0) goto L4f
            int r8 = r3 + (-1)
            char r8 = r0.charAt(r8)
            int r9 = r5 + (-1)
            char r9 = r14.charAt(r9)
            if (r8 != r9) goto L4e
            int r3 = r3 + (-1)
            int r5 = r5 + (-1)
            goto L4f
        L4e:
            r7 = 1
        L4f:
            if (r2 >= r3) goto L57
            if (r4 >= r5) goto L57
            if (r6 == 0) goto L27
            if (r7 == 0) goto L27
        L57:
            if (r2 < r3) goto L5d
            if (r4 < r5) goto L5d
            r10 = r14
            goto L6b
        L5d:
            r6 = r2
            r9 = r3
            r8 = r6
            r11 = r4
            r12 = r5
            r6 = 0
            r7 = r13
            r10 = r14
            r7.replace$foundation_release(r8, r9, r10, r11, r12)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.TextFieldBuffer.setTextIfChanged$foundation_release(java.lang.CharSequence):void");
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text) {
        TextFieldBuffer $this$append_u24lambda_u245 = this;
        if (text != null) {
            $this$append_u24lambda_u245.onTextWillChange($this$append_u24lambda_u245.getLength(), $this$append_u24lambda_u245.getLength(), text.length());
            PartialGapBuffer.replace$default($this$append_u24lambda_u245.buffer, $this$append_u24lambda_u245.buffer.length(), $this$append_u24lambda_u245.buffer.length(), text, 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text, int start, int end) {
        TextFieldBuffer $this$append_u24lambda_u246 = this;
        if (text != null) {
            $this$append_u24lambda_u246.onTextWillChange($this$append_u24lambda_u246.getLength(), $this$append_u24lambda_u246.getLength(), end - start);
            PartialGapBuffer.replace$default($this$append_u24lambda_u246.buffer, $this$append_u24lambda_u246.buffer.length(), $this$append_u24lambda_u246.buffer.length(), text.subSequence(start, end), 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char r14) {
        TextFieldBuffer $this$append_u24lambda_u247 = this;
        $this$append_u24lambda_u247.onTextWillChange($this$append_u24lambda_u247.getLength(), $this$append_u24lambda_u247.getLength(), 1);
        PartialGapBuffer.replace$default($this$append_u24lambda_u247.buffer, $this$append_u24lambda_u247.buffer.length(), $this$append_u24lambda_u247.buffer.length(), String.valueOf(r14), 0, 0, 24, null);
        return this;
    }

    private final void onTextWillChange(int replaceStart, int replaceEnd, int newLength) {
        int selEnd;
        getChangeTracker().trackChange(replaceStart, replaceEnd, newLength);
        OffsetMappingCalculator offsetMappingCalculator = this.offsetMappingCalculator;
        if (offsetMappingCalculator != null) {
            offsetMappingCalculator.recordEditOperation(replaceStart, replaceEnd, newLength);
        }
        int start = Math.min(replaceStart, replaceEnd);
        int end = Math.max(replaceStart, replaceEnd);
        int selStart = TextRange.m6090getMinimpl(getSelectionInChars());
        int selEnd2 = TextRange.m6089getMaximpl(getSelectionInChars());
        if (selEnd2 < start) {
            return;
        }
        if (selStart <= start && end <= selEnd2) {
            int diff = newLength - (end - start);
            if (selStart == selEnd2) {
                selStart += diff;
            }
            selEnd = selEnd2 + diff;
        } else if (selStart > start && selEnd2 < end) {
            selStart = start + newLength;
            selEnd = start + newLength;
        } else if (selStart >= end) {
            int diff2 = newLength - (end - start);
            selStart += diff2;
            selEnd = selEnd2 + diff2;
        } else if (start < selStart) {
            selStart = start + newLength;
            selEnd = selEnd2 + (newLength - (end - start));
        } else {
            selEnd = start;
        }
        this.selectionInChars = TextRangeKt.TextRange(selStart, selEnd);
    }

    public final char charAt(int index) {
        return this.buffer.charAt(index);
    }

    public String toString() {
        return this.buffer.toString();
    }

    public final CharSequence asCharSequence() {
        return this.buffer;
    }

    private final void clearChangeList() {
        getChangeTracker().clearChanges();
    }

    public final void revertAllChanges() {
        replace(0, getLength(), this.originalValue.toString());
        m1093setSelection5zctL8(this.originalValue.getSelection());
        clearChangeList();
    }

    public final void placeCursorBeforeCharAt(int index) {
        requireValidIndex(index, true, false);
        this.selectionInChars = TextRangeKt.TextRange(index);
    }

    public final void placeCursorAfterCharAt(int index) {
        requireValidIndex(index, false, true);
        this.selectionInChars = TextRangeKt.TextRange(RangesKt.coerceAtMost(index + 1, getLength()));
    }

    /* renamed from: toTextFieldCharSequence-udt6zUU$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1090toTextFieldCharSequenceudt6zUU$foundation_release$default(TextFieldBuffer textFieldBuffer, long j, TextRange textRange, int i, Object obj) {
        if ((i & 1) != 0) {
            j = textFieldBuffer.getSelectionInChars();
        }
        if ((i & 2) != 0) {
            textRange = null;
        }
        return textFieldBuffer.m1094toTextFieldCharSequenceudt6zUU$foundation_release(j, textRange);
    }

    /* renamed from: toTextFieldCharSequence-udt6zUU$foundation_release, reason: not valid java name */
    public final TextFieldCharSequence m1094toTextFieldCharSequenceudt6zUU$foundation_release(long selection, TextRange composition) {
        return new TextFieldCharSequence(this.buffer.toString(), selection, composition, null, 8, null);
    }

    private final void requireValidIndex(int index, boolean startExclusive, boolean endExclusive) {
        boolean z = false;
        int start = startExclusive ? 0 : -1;
        int end = getLength();
        if (!endExclusive) {
            end++;
        }
        if (start <= index && index < end) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Expected " + index + " to be in [" + start + ", " + end + ')').toString());
        }
    }

    /* renamed from: requireValidRange-5zc-tL8, reason: not valid java name */
    private final void m1089requireValidRange5zctL8(long range) {
        long validRange = TextRangeKt.TextRange(0, getLength());
        if (!TextRange.m6082contains5zctL8(validRange, range)) {
            throw new IllegalArgumentException(("Expected " + ((Object) TextRange.m6095toStringimpl(range)) + " to be in " + ((Object) TextRange.m6095toStringimpl(validRange))).toString());
        }
    }
}
