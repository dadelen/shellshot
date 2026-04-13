package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TextPreparedSelection.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b*\b\u0000\u0018\u0000 P2\u00020\u0001:\u0001PB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ,\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u001b\u001a\u00020\u00072\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\u0002\b\u001fH\u0082\bJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u001f\u0010#\u001a\u00020\u00002\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\u0002\b\u001fJ\u001f\u0010%\u001a\u00020\u00002\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\u0002\b\u001fJ\u0019\u0010&\u001a\u00020\u001e2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120'H\u0086\bJ\u0006\u0010(\u001a\u00020\u0000J\u0006\u0010)\u001a\u00020!J\u0006\u0010*\u001a\u00020!J\u0006\u0010+\u001a\u00020!J\u0006\u0010,\u001a\u00020!J\u0006\u0010-\u001a\u00020!J\u0006\u0010.\u001a\u00020!J\b\u0010/\u001a\u00020\u0007H\u0002J\u0010\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020!H\u0002J\u0006\u00102\u001a\u00020\u0000J\u0006\u00103\u001a\u00020\u0000J\u0006\u00104\u001a\u00020\u0000J\u0006\u00105\u001a\u00020\u0000J\b\u00106\u001a\u00020\u0000H\u0002J\u0006\u00107\u001a\u00020\u0000J\b\u00108\u001a\u00020\u0000H\u0002J\b\u00109\u001a\u00020\u0000H\u0002J\u0006\u0010:\u001a\u00020\u0000J\b\u0010;\u001a\u00020\u0000H\u0002J\u0006\u0010<\u001a\u00020\u0000J\u0006\u0010=\u001a\u00020\u0000J\u0006\u0010>\u001a\u00020\u0000J\u0006\u0010?\u001a\u00020\u0000J\u0006\u0010@\u001a\u00020\u0000J\u0006\u0010A\u001a\u00020\u0000J\u0006\u0010B\u001a\u00020\u0000J\u0006\u0010C\u001a\u00020\u0000J\u0006\u0010D\u001a\u00020\u0000J\u0006\u0010E\u001a\u00020\u0000J\u0006\u0010F\u001a\u00020\u0000J\u0006\u0010G\u001a\u00020\u0000J\u0010\u0010H\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!H\u0002J\u0016\u0010I\u001a\u00020!*\u00020\u00052\b\b\u0002\u0010J\u001a\u00020!H\u0002J\u0016\u0010K\u001a\u00020!*\u00020\u00052\b\b\u0002\u0010J\u001a\u00020!H\u0002J\u0017\u0010L\u001a\u00020!*\u00020\u00052\b\b\u0002\u0010J\u001a\u00020!H\u0082\u0010J\u0017\u0010M\u001a\u00020!*\u00020\u00052\b\b\u0002\u0010J\u001a\u00020!H\u0082\u0010J\u0014\u0010N\u001a\u00020!*\u00020\u00052\u0006\u0010O\u001a\u00020!H\u0002R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u00020\u0012X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Q"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelection;", "", "state", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isFromSoftKeyboard", "", "visibleTextLayoutHeight", "", "textPreparedSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextLayoutResult;ZFLandroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;)V", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getInitialValue", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "selection", "Landroidx/compose/ui/text/TextRange;", "getSelection-d9O1mEE", "()J", "setSelection-5zc-tL8", "(J)V", "J", "text", "", "applyIfNotEmpty", "resetCachedX", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "charOffset", "", "offset", "collapseLeftOr", "or", "collapseRightOr", "deleteIfSelectedOr", "Lkotlin/Function0;", "deselect", "getLineEndByOffset", "getLineStartByOffset", "getNextCharacterIndex", "getNextWordOffset", "getPrecedingCharacterIndex", "getPreviousWordOffset", "isLtr", "jumpByPagesOffset", "pagesAmount", "moveCursorDownByLine", "moveCursorDownByPage", "moveCursorLeft", "moveCursorLeftByWord", "moveCursorNext", "moveCursorNextByParagraph", "moveCursorNextByWord", "moveCursorPrev", "moveCursorPrevByParagraph", "moveCursorPrevByWord", "moveCursorRight", "moveCursorRightByWord", "moveCursorToEnd", "moveCursorToHome", "moveCursorToLineEnd", "moveCursorToLineLeftSide", "moveCursorToLineRightSide", "moveCursorToLineStart", "moveCursorUpByLine", "moveCursorUpByPage", "selectAll", "selectMovement", "setCursor", "getLineEndByOffsetForLayout", "currentOffset", "getLineStartByOffsetForLayout", "getNextWordOffsetForLayout", "getPrevWordOffsetForLayout", "jumpByLinesOffset", "linesAmount", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldPreparedSelection {
    public static final int NoCharacterFound = -1;
    private final TextFieldCharSequence initialValue;
    private final boolean isFromSoftKeyboard;
    private long selection;
    private final TransformedTextFieldState state;
    private final String text;
    private final TextLayoutResult textLayoutResult;
    private final TextFieldPreparedSelectionState textPreparedSelectionState;
    private final float visibleTextLayoutHeight;
    public static final int $stable = 8;

    public TextFieldPreparedSelection(TransformedTextFieldState state, TextLayoutResult textLayoutResult, boolean isFromSoftKeyboard, float visibleTextLayoutHeight, TextFieldPreparedSelectionState textPreparedSelectionState) {
        this.state = state;
        this.textLayoutResult = textLayoutResult;
        this.isFromSoftKeyboard = isFromSoftKeyboard;
        this.visibleTextLayoutHeight = visibleTextLayoutHeight;
        this.textPreparedSelectionState = textPreparedSelectionState;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1 observer$iv = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            TextFieldCharSequence visualText = this.state.getVisualText();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
            this.initialValue = visualText;
            this.selection = this.initialValue.getSelection();
            this.text = this.initialValue.toString();
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
            throw th;
        }
    }

    public final TextFieldCharSequence getInitialValue() {
        return this.initialValue;
    }

    /* renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    /* renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1241setSelection5zctL8(long j) {
        this.selection = j;
    }

    public final void deleteIfSelectedOr(Function0<TextRange> block) {
        if (!TextRange.m6086getCollapsedimpl(getSelection())) {
            TransformedTextFieldState.m1212replaceTextM8tDOmk$default(this.state, "", getSelection(), null, !this.isFromSoftKeyboard, 4, null);
            return;
        }
        TextRange invoke = block.invoke();
        if (invoke != null) {
            long it = invoke.getPackedValue();
            TransformedTextFieldState.m1212replaceTextM8tDOmk$default(this.state, "", it, null, !this.isFromSoftKeyboard, 4, null);
        }
    }

    public final TextFieldPreparedSelection moveCursorUpByPage() {
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorUpByPage_u24lambda_u242 = this;
            $this$moveCursorUpByPage_u24lambda_u242.setCursor($this$moveCursorUpByPage_u24lambda_u242.jumpByPagesOffset(-1));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorDownByPage() {
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorDownByPage_u24lambda_u243 = this;
            $this$moveCursorDownByPage_u24lambda_u243.setCursor($this$moveCursorDownByPage_u24lambda_u243.jumpByPagesOffset(1));
        }
        return this;
    }

    private final int jumpByPagesOffset(int pagesAmount) {
        int currentOffset = TextRange.m6087getEndimpl(this.initialValue.getSelection());
        if (this.textLayoutResult == null || Float.isNaN(this.visibleTextLayoutHeight)) {
            return currentOffset;
        }
        Rect currentPos = this.textLayoutResult.getCursorRect(currentOffset);
        Rect newPos = currentPos.translate(0.0f, this.visibleTextLayoutHeight * pagesAmount);
        int topLine = this.textLayoutResult.getLineForVerticalPosition(newPos.getTop());
        float lineSeparator = this.textLayoutResult.getLineBottom(topLine);
        float abs = Math.abs(newPos.getTop() - lineSeparator);
        float abs2 = Math.abs(newPos.getBottom() - lineSeparator);
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        if (abs > abs2) {
            return textLayoutResult.m6064getOffsetForPositionk4lQ0M(newPos.m3957getTopLeftF1C5BW0());
        }
        return textLayoutResult.m6064getOffsetForPositionk4lQ0M(newPos.m3950getBottomLeftF1C5BW0());
    }

    static /* synthetic */ TextFieldPreparedSelection applyIfNotEmpty$default(TextFieldPreparedSelection $this, boolean resetCachedX, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            resetCachedX = true;
        }
        if (resetCachedX) {
            $this.textPreparedSelectionState.resetCachedX();
        }
        if ($this.text.length() > 0) {
            block.invoke($this);
        }
        return $this;
    }

    private final TextFieldPreparedSelection applyIfNotEmpty(boolean resetCachedX, Function1<? super TextFieldPreparedSelection, Unit> block) {
        if (resetCachedX) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            block.invoke(this);
        }
        return this;
    }

    private final void setCursor(int offset) {
        this.selection = TextRangeKt.TextRange(offset, offset);
    }

    public final TextFieldPreparedSelection selectAll() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$selectAll_u24lambda_u244 = this;
            $this$selectAll_u24lambda_u244.selection = TextRangeKt.TextRange(0, $this$selectAll_u24lambda_u244.text.length());
        }
        return this;
    }

    public final TextFieldPreparedSelection deselect() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$deselect_u24lambda_u245 = this;
            $this$deselect_u24lambda_u245.setCursor(TextRange.m6087getEndimpl($this$deselect_u24lambda_u245.selection));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorLeft() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorLeft_u24lambda_u246 = this;
            if ($this$moveCursorLeft_u24lambda_u246.isLtr()) {
                $this$moveCursorLeft_u24lambda_u246.moveCursorPrev();
            } else {
                $this$moveCursorLeft_u24lambda_u246.moveCursorNext();
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorRight() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorRight_u24lambda_u247 = this;
            if ($this$moveCursorRight_u24lambda_u247.isLtr()) {
                $this$moveCursorRight_u24lambda_u247.moveCursorNext();
            } else {
                $this$moveCursorRight_u24lambda_u247.moveCursorPrev();
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection collapseLeftOr(Function1<? super TextFieldPreparedSelection, Unit> or) {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$collapseLeftOr_u24lambda_u248 = this;
            if (TextRange.m6086getCollapsedimpl($this$collapseLeftOr_u24lambda_u248.selection)) {
                or.invoke($this$collapseLeftOr_u24lambda_u248);
            } else {
                boolean isLtr = $this$collapseLeftOr_u24lambda_u248.isLtr();
                long j = $this$collapseLeftOr_u24lambda_u248.selection;
                if (isLtr) {
                    $this$collapseLeftOr_u24lambda_u248.setCursor(TextRange.m6090getMinimpl(j));
                } else {
                    $this$collapseLeftOr_u24lambda_u248.setCursor(TextRange.m6089getMaximpl(j));
                }
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection collapseRightOr(Function1<? super TextFieldPreparedSelection, Unit> or) {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$collapseRightOr_u24lambda_u249 = this;
            if (TextRange.m6086getCollapsedimpl($this$collapseRightOr_u24lambda_u249.selection)) {
                or.invoke($this$collapseRightOr_u24lambda_u249);
            } else {
                boolean isLtr = $this$collapseRightOr_u24lambda_u249.isLtr();
                long j = $this$collapseRightOr_u24lambda_u249.selection;
                if (isLtr) {
                    $this$collapseRightOr_u24lambda_u249.setCursor(TextRange.m6089getMaximpl(j));
                } else {
                    $this$collapseRightOr_u24lambda_u249.setCursor(TextRange.m6090getMinimpl(j));
                }
            }
        }
        return this;
    }

    public final int getPrecedingCharacterIndex() {
        return StringHelpers_androidKt.findPrecedingBreak(this.text, TextRange.m6087getEndimpl(this.selection));
    }

    public final int getNextCharacterIndex() {
        return StringHelpers_androidKt.findFollowingBreak(this.text, TextRange.m6087getEndimpl(this.selection));
    }

    private final TextFieldPreparedSelection moveCursorPrev() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorPrev_u24lambda_u2410 = this;
            int oldCursor = TextRange.m6087getEndimpl($this$moveCursorPrev_u24lambda_u2410.selection);
            int newCursor = TextPreparedSelectionKt.calculateAdjacentCursorPosition($this$moveCursorPrev_u24lambda_u2410.text, oldCursor, false, $this$moveCursorPrev_u24lambda_u2410.state);
            if (newCursor != oldCursor) {
                $this$moveCursorPrev_u24lambda_u2410.setCursor(newCursor);
            }
        }
        return this;
    }

    private final TextFieldPreparedSelection moveCursorNext() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorNext_u24lambda_u2411 = this;
            int oldCursor = TextRange.m6087getEndimpl($this$moveCursorNext_u24lambda_u2411.selection);
            int newCursor = TextPreparedSelectionKt.calculateAdjacentCursorPosition($this$moveCursorNext_u24lambda_u2411.text, oldCursor, true, $this$moveCursorNext_u24lambda_u2411.state);
            if (newCursor != oldCursor) {
                $this$moveCursorNext_u24lambda_u2411.setCursor(newCursor);
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToHome() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToHome_u24lambda_u2412 = this;
            $this$moveCursorToHome_u24lambda_u2412.setCursor(0);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToEnd() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToEnd_u24lambda_u2413 = this;
            $this$moveCursorToEnd_u24lambda_u2413.setCursor($this$moveCursorToEnd_u24lambda_u2413.text.length());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorLeftByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorLeftByWord_u24lambda_u2414 = this;
            if ($this$moveCursorLeftByWord_u24lambda_u2414.isLtr()) {
                $this$moveCursorLeftByWord_u24lambda_u2414.moveCursorPrevByWord();
            } else {
                $this$moveCursorLeftByWord_u24lambda_u2414.moveCursorNextByWord();
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorRightByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorRightByWord_u24lambda_u2415 = this;
            if ($this$moveCursorRightByWord_u24lambda_u2415.isLtr()) {
                $this$moveCursorRightByWord_u24lambda_u2415.moveCursorNextByWord();
            } else {
                $this$moveCursorRightByWord_u24lambda_u2415.moveCursorPrevByWord();
            }
        }
        return this;
    }

    public final int getNextWordOffset() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        return textLayoutResult != null ? getNextWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : this.text.length();
    }

    private final TextFieldPreparedSelection moveCursorNextByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorNextByWord_u24lambda_u2416 = this;
            $this$moveCursorNextByWord_u24lambda_u2416.setCursor($this$moveCursorNextByWord_u24lambda_u2416.getNextWordOffset());
        }
        return this;
    }

    public final int getPreviousWordOffset() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        if (textLayoutResult != null) {
            return getPrevWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null);
        }
        return 0;
    }

    private final TextFieldPreparedSelection moveCursorPrevByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorPrevByWord_u24lambda_u2417 = this;
            $this$moveCursorPrevByWord_u24lambda_u2417.setCursor($this$moveCursorPrevByWord_u24lambda_u2417.getPreviousWordOffset());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorPrevByParagraph() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorPrevByParagraph_u24lambda_u2418 = this;
            int paragraphStart = StringHelpersKt.findParagraphStart($this$moveCursorPrevByParagraph_u24lambda_u2418.text, TextRange.m6090getMinimpl($this$moveCursorPrevByParagraph_u24lambda_u2418.selection));
            if (paragraphStart == TextRange.m6090getMinimpl($this$moveCursorPrevByParagraph_u24lambda_u2418.selection) && paragraphStart != 0) {
                paragraphStart = StringHelpersKt.findParagraphStart($this$moveCursorPrevByParagraph_u24lambda_u2418.text, paragraphStart - 1);
            }
            $this$moveCursorPrevByParagraph_u24lambda_u2418.setCursor(paragraphStart);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorNextByParagraph() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorNextByParagraph_u24lambda_u2419 = this;
            int paragraphEnd = StringHelpersKt.findParagraphEnd($this$moveCursorNextByParagraph_u24lambda_u2419.text, TextRange.m6089getMaximpl($this$moveCursorNextByParagraph_u24lambda_u2419.selection));
            if (paragraphEnd == TextRange.m6089getMaximpl($this$moveCursorNextByParagraph_u24lambda_u2419.selection) && paragraphEnd != $this$moveCursorNextByParagraph_u24lambda_u2419.text.length()) {
                paragraphEnd = StringHelpersKt.findParagraphEnd($this$moveCursorNextByParagraph_u24lambda_u2419.text, paragraphEnd + 1);
            }
            $this$moveCursorNextByParagraph_u24lambda_u2419.setCursor(paragraphEnd);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorUpByLine() {
        if (this.textLayoutResult == null) {
            return this;
        }
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorUpByLine_u24lambda_u2420 = this;
            TextLayoutResult textLayoutResult = $this$moveCursorUpByLine_u24lambda_u2420.textLayoutResult;
            Intrinsics.checkNotNull(textLayoutResult);
            $this$moveCursorUpByLine_u24lambda_u2420.setCursor($this$moveCursorUpByLine_u24lambda_u2420.jumpByLinesOffset(textLayoutResult, -1));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorDownByLine() {
        if (this.textLayoutResult == null) {
            return this;
        }
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorDownByLine_u24lambda_u2421 = this;
            TextLayoutResult textLayoutResult = $this$moveCursorDownByLine_u24lambda_u2421.textLayoutResult;
            Intrinsics.checkNotNull(textLayoutResult);
            $this$moveCursorDownByLine_u24lambda_u2421.setCursor($this$moveCursorDownByLine_u24lambda_u2421.jumpByLinesOffset(textLayoutResult, 1));
        }
        return this;
    }

    public final int getLineStartByOffset() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        if (textLayoutResult != null) {
            return getLineStartByOffsetForLayout$default(this, textLayoutResult, 0, 1, null);
        }
        return 0;
    }

    public final TextFieldPreparedSelection moveCursorToLineStart() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToLineStart_u24lambda_u2422 = this;
            $this$moveCursorToLineStart_u24lambda_u2422.setCursor($this$moveCursorToLineStart_u24lambda_u2422.getLineStartByOffset());
        }
        return this;
    }

    public final int getLineEndByOffset() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        return textLayoutResult != null ? getLineEndByOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : this.text.length();
    }

    public final TextFieldPreparedSelection moveCursorToLineEnd() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToLineEnd_u24lambda_u2423 = this;
            $this$moveCursorToLineEnd_u24lambda_u2423.setCursor($this$moveCursorToLineEnd_u24lambda_u2423.getLineEndByOffset());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineLeftSide() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToLineLeftSide_u24lambda_u2424 = this;
            if ($this$moveCursorToLineLeftSide_u24lambda_u2424.isLtr()) {
                $this$moveCursorToLineLeftSide_u24lambda_u2424.moveCursorToLineStart();
            } else {
                $this$moveCursorToLineLeftSide_u24lambda_u2424.moveCursorToLineEnd();
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineRightSide() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$moveCursorToLineRightSide_u24lambda_u2425 = this;
            if ($this$moveCursorToLineRightSide_u24lambda_u2425.isLtr()) {
                $this$moveCursorToLineRightSide_u24lambda_u2425.moveCursorToLineEnd();
            } else {
                $this$moveCursorToLineRightSide_u24lambda_u2425.moveCursorToLineStart();
            }
        }
        return this;
    }

    public final TextFieldPreparedSelection selectMovement() {
        if (this.text.length() > 0) {
            TextFieldPreparedSelection $this$selectMovement_u24lambda_u2426 = this;
            $this$selectMovement_u24lambda_u2426.selection = TextRangeKt.TextRange(TextRange.m6092getStartimpl($this$selectMovement_u24lambda_u2426.initialValue.getSelection()), TextRange.m6087getEndimpl($this$selectMovement_u24lambda_u2426.selection));
        }
        return this;
    }

    private final boolean isLtr() {
        ResolvedTextDirection direction;
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        return textLayoutResult == null || (direction = textLayoutResult.getParagraphDirection(TextRange.m6087getEndimpl(this.selection))) == null || direction == ResolvedTextDirection.Ltr;
    }

    static /* synthetic */ int getNextWordOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.m6087getEndimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getNextWordOffsetForLayout(textLayoutResult, i);
    }

    private final int getNextWordOffsetForLayout(TextLayoutResult $this$getNextWordOffsetForLayout, int currentOffset) {
        while (currentOffset < this.initialValue.length()) {
            long currentWord = $this$getNextWordOffsetForLayout.m6066getWordBoundaryjx7JFs(charOffset(currentOffset));
            if (TextRange.m6087getEndimpl(currentWord) <= currentOffset) {
                currentOffset++;
            } else {
                return TextRange.m6087getEndimpl(currentWord);
            }
        }
        return this.initialValue.length();
    }

    static /* synthetic */ int getPrevWordOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.m6087getEndimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getPrevWordOffsetForLayout(textLayoutResult, i);
    }

    private final int getPrevWordOffsetForLayout(TextLayoutResult $this$getPrevWordOffsetForLayout, int currentOffset) {
        while (currentOffset > 0) {
            long currentWord = $this$getPrevWordOffsetForLayout.m6066getWordBoundaryjx7JFs(charOffset(currentOffset));
            if (TextRange.m6092getStartimpl(currentWord) >= currentOffset) {
                currentOffset--;
            } else {
                return TextRange.m6092getStartimpl(currentWord);
            }
        }
        return 0;
    }

    static /* synthetic */ int getLineStartByOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.m6090getMinimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getLineStartByOffsetForLayout(textLayoutResult, i);
    }

    private final int getLineStartByOffsetForLayout(TextLayoutResult $this$getLineStartByOffsetForLayout, int currentOffset) {
        int currentLine = $this$getLineStartByOffsetForLayout.getLineForOffset(currentOffset);
        return $this$getLineStartByOffsetForLayout.getLineStart(currentLine);
    }

    static /* synthetic */ int getLineEndByOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.m6089getMaximpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getLineEndByOffsetForLayout(textLayoutResult, i);
    }

    private final int getLineEndByOffsetForLayout(TextLayoutResult $this$getLineEndByOffsetForLayout, int currentOffset) {
        int currentLine = $this$getLineEndByOffsetForLayout.getLineForOffset(currentOffset);
        return $this$getLineEndByOffsetForLayout.getLineEnd(currentLine, true);
    }

    private final int jumpByLinesOffset(TextLayoutResult $this$jumpByLinesOffset, int linesAmount) {
        int currentOffset = TextRange.m6087getEndimpl(this.selection);
        if (Float.isNaN(this.textPreparedSelectionState.getCachedX())) {
            this.textPreparedSelectionState.setCachedX($this$jumpByLinesOffset.getCursorRect(currentOffset).getLeft());
        }
        int targetLine = $this$jumpByLinesOffset.getLineForOffset(currentOffset) + linesAmount;
        if (targetLine < 0) {
            return 0;
        }
        if (targetLine >= $this$jumpByLinesOffset.getLineCount()) {
            return this.text.length();
        }
        float y = $this$jumpByLinesOffset.getLineBottom(targetLine) - 1.0f;
        float x = this.textPreparedSelectionState.getCachedX();
        if ((isLtr() && x >= $this$jumpByLinesOffset.getLineRight(targetLine)) || (!isLtr() && x <= $this$jumpByLinesOffset.getLineLeft(targetLine))) {
            return $this$jumpByLinesOffset.getLineEnd(targetLine, true);
        }
        return $this$jumpByLinesOffset.m6064getOffsetForPositionk4lQ0M(OffsetKt.Offset(x, y));
    }

    private final int charOffset(int offset) {
        return RangesKt.coerceAtMost(offset, this.text.length() - 1);
    }
}
