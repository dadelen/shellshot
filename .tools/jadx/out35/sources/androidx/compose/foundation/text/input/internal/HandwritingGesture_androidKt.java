package androidx.compose.foundation.text.input.internal;

import android.graphics.PointF;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.EditCommand;
import kotlin.Metadata;

/* compiled from: HandwritingGesture.android.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006\"\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0007\u001a\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\t*\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a(\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a&\u0010\u001b\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a(\u0010\u001b\u001a\u00020\u0001*\u00020 2\u0006\u0010\u001d\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010!\u001a2\u0010\u001b\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00162\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a<\u0010&\u001a\u00020\t*\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00162\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a.\u0010,\u001a\u00020\t*\u00020\u001c2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a.\u0010,\u001a\u00020\t*\u00020 2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b3\u00105\u001a:\u0010,\u001a\u00020\t*\u0004\u0018\u00010\u00142\u0006\u0010-\u001a\u00020.2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001a6\u00108\u001a\u00020\t*\u00020\u001c2\u0006\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a6\u00108\u001a\u00020\t*\u00020 2\u0006\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010=\u001a\u0014\u0010>\u001a\u00020?*\u00020'2\u0006\u0010@\u001a\u00020\u0001H\u0002\u001a\f\u0010A\u001a\u00020?*\u00020\u0001H\u0002\u001a\f\u0010B\u001a\u00020?*\u00020\u0001H\u0002\u001a\f\u0010C\u001a\u00020?*\u00020\u0001H\u0002\u001a\f\u0010D\u001a\u00020?*\u00020\u0001H\u0002\u001a\u0019\u0010E\u001a\u00020\t*\u00020\u00102\u0006\u0010@\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010F\u001a\u0011\u0010G\u001a\u00020\u0016*\u00020HH\u0002¢\u0006\u0002\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006J"}, d2 = {"LINE_FEED_CODE_POINT", "", "NBSP_CODE_POINT", "compoundEditCommand", "Landroidx/compose/ui/text/input/EditCommand;", "editCommands", "", "([Landroidx/compose/ui/text/input/EditCommand;)Landroidx/compose/ui/text/input/EditCommand;", "enclosure", "Landroidx/compose/ui/text/TextRange;", "a", "b", "enclosure-pWDy79M", "(JJ)J", "adjustHandwritingDeleteGestureRange", "text", "", "adjustHandwritingDeleteGestureRange-72CqOWE", "(JLjava/lang/CharSequence;)J", "getLineForHandwritingGesture", "Landroidx/compose/ui/text/MultiParagraph;", "localPoint", "Landroidx/compose/ui/geometry/Offset;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getLineForHandwritingGesture-d-4ec7I", "(Landroidx/compose/ui/text/MultiParagraph;JLandroidx/compose/ui/platform/ViewConfiguration;)I", "getOffsetForHandwritingGesture", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "pointInScreen", "getOffsetForHandwritingGesture-d-4ec7I", "(Landroidx/compose/foundation/text/LegacyTextFieldState;JLandroidx/compose/ui/platform/ViewConfiguration;)I", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;JLandroidx/compose/ui/platform/ViewConfiguration;)I", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getOffsetForHandwritingGesture-ubNVwUQ", "(Landroidx/compose/ui/text/MultiParagraph;JLandroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/platform/ViewConfiguration;)I", "getRangeForRemoveSpaceGesture", "Landroidx/compose/ui/text/TextLayoutResult;", "startPointInScreen", "endPointerInScreen", "getRangeForRemoveSpaceGesture-5iVPX68", "(Landroidx/compose/ui/text/TextLayoutResult;JJLandroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/platform/ViewConfiguration;)J", "getRangeForScreenRect", "rectInScreen", "Landroidx/compose/ui/geometry/Rect;", "granularity", "Landroidx/compose/ui/text/TextGranularity;", "inclusionStrategy", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getRangeForScreenRect-OH9lIzo", "(Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getRangeForScreenRect-O048IG0", "(Landroidx/compose/ui/text/MultiParagraph;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/layout/LayoutCoordinates;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getRangeForScreenRects", "startRectInScreen", "endRectInScreen", "getRangeForScreenRects-O048IG0", "(Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "isBiDiBoundary", "", "offset", "isNewline", "isPunctuation", "isWhitespace", "isWhitespaceExceptNewline", "rangeOfWhitespaces", "(Ljava/lang/CharSequence;I)J", "toOffset", "Landroid/graphics/PointF;", "(Landroid/graphics/PointF;)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HandwritingGesture_androidKt {
    private static final int LINE_FEED_CODE_POINT = 10;
    private static final int NBSP_CODE_POINT = 160;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: adjustHandwritingDeleteGestureRange-72CqOWE, reason: not valid java name */
    public static final long m1147adjustHandwritingDeleteGestureRange72CqOWE(long $this$adjustHandwritingDeleteGestureRange_u2d72CqOWE, CharSequence text) {
        int codePointBeforeStart;
        int start = TextRange.m6092getStartimpl($this$adjustHandwritingDeleteGestureRange_u2d72CqOWE);
        int end = TextRange.m6087getEndimpl($this$adjustHandwritingDeleteGestureRange_u2d72CqOWE);
        int codePointAtEnd = 10;
        if (start > 0) {
            codePointBeforeStart = Character.codePointBefore(text, start);
        } else {
            codePointBeforeStart = 10;
        }
        if (end < text.length()) {
            codePointAtEnd = Character.codePointAt(text, end);
        }
        if (isWhitespaceExceptNewline(codePointBeforeStart) && (isWhitespace(codePointAtEnd) || isPunctuation(codePointAtEnd))) {
            do {
                start -= Character.charCount(codePointBeforeStart);
                if (start == 0) {
                    break;
                }
                codePointBeforeStart = Character.codePointBefore(text, start);
            } while (isWhitespaceExceptNewline(codePointBeforeStart));
            return TextRangeKt.TextRange(start, end);
        }
        if (isWhitespaceExceptNewline(codePointAtEnd) && (isWhitespace(codePointBeforeStart) || isPunctuation(codePointBeforeStart))) {
            do {
                end += Character.charCount(codePointAtEnd);
                if (end == text.length()) {
                    break;
                }
                codePointAtEnd = Character.codePointAt(text, end);
            } while (isWhitespaceExceptNewline(codePointAtEnd));
            return TextRangeKt.TextRange(start, end);
        }
        return $this$adjustHandwritingDeleteGestureRange_u2d72CqOWE;
    }

    private static final boolean isNewline(int $this$isNewline) {
        int type = Character.getType($this$isNewline);
        return type == 14 || type == 13 || $this$isNewline == 10;
    }

    private static final boolean isWhitespace(int $this$isWhitespace) {
        return Character.isWhitespace($this$isWhitespace) || $this$isWhitespace == NBSP_CODE_POINT;
    }

    private static final boolean isWhitespaceExceptNewline(int $this$isWhitespaceExceptNewline) {
        return isWhitespace($this$isWhitespaceExceptNewline) && !isNewline($this$isWhitespaceExceptNewline);
    }

    private static final boolean isPunctuation(int $this$isPunctuation) {
        int type = Character.getType($this$isPunctuation);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long toOffset(PointF $this$toOffset) {
        return OffsetKt.Offset($this$toOffset.x, $this$toOffset.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRangeForScreenRect-OH9lIzo, reason: not valid java name */
    public static final long m1156getRangeForScreenRectOH9lIzo(TextLayoutState $this$getRangeForScreenRect_u2dOH9lIzo, Rect rectInScreen, int granularity, TextInclusionStrategy inclusionStrategy) {
        TextLayoutResult layoutResult = $this$getRangeForScreenRect_u2dOH9lIzo.getLayoutResult();
        return m1154getRangeForScreenRectO048IG0(layoutResult != null ? layoutResult.getMultiParagraph() : null, rectInScreen, $this$getRangeForScreenRect_u2dOH9lIzo.getTextLayoutNodeCoordinates(), granularity, inclusionStrategy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRangeForScreenRects-O048IG0, reason: not valid java name */
    public static final long m1158getRangeForScreenRectsO048IG0(TextLayoutState $this$getRangeForScreenRects_u2dO048IG0, Rect startRectInScreen, Rect endRectInScreen, int granularity, TextInclusionStrategy inclusionStrategy) {
        long startRange = m1156getRangeForScreenRectOH9lIzo($this$getRangeForScreenRects_u2dO048IG0, startRectInScreen, granularity, inclusionStrategy);
        if (TextRange.m6086getCollapsedimpl(startRange)) {
            return TextRange.INSTANCE.m6097getZerod9O1mEE();
        }
        long endRange = m1156getRangeForScreenRectOH9lIzo($this$getRangeForScreenRects_u2dO048IG0, endRectInScreen, granularity, inclusionStrategy);
        return TextRange.m6086getCollapsedimpl(endRange) ? TextRange.INSTANCE.m6097getZerod9O1mEE() : m1148enclosurepWDy79M(startRange, endRange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRangeForScreenRect-OH9lIzo, reason: not valid java name */
    public static final long m1155getRangeForScreenRectOH9lIzo(LegacyTextFieldState $this$getRangeForScreenRect_u2dOH9lIzo, Rect rectInScreen, int granularity, TextInclusionStrategy inclusionStrategy) {
        TextLayoutResult value;
        TextLayoutResultProxy layoutResult = $this$getRangeForScreenRect_u2dOH9lIzo.getLayoutResult();
        return m1154getRangeForScreenRectO048IG0((layoutResult == null || (value = layoutResult.getValue()) == null) ? null : value.getMultiParagraph(), rectInScreen, $this$getRangeForScreenRect_u2dOH9lIzo.getLayoutCoordinates(), granularity, inclusionStrategy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRangeForScreenRects-O048IG0, reason: not valid java name */
    public static final long m1157getRangeForScreenRectsO048IG0(LegacyTextFieldState $this$getRangeForScreenRects_u2dO048IG0, Rect startRectInScreen, Rect endRectInScreen, int granularity, TextInclusionStrategy inclusionStrategy) {
        long startRange = m1155getRangeForScreenRectOH9lIzo($this$getRangeForScreenRects_u2dO048IG0, startRectInScreen, granularity, inclusionStrategy);
        if (TextRange.m6086getCollapsedimpl(startRange)) {
            return TextRange.INSTANCE.m6097getZerod9O1mEE();
        }
        long endRange = m1155getRangeForScreenRectOH9lIzo($this$getRangeForScreenRects_u2dO048IG0, endRectInScreen, granularity, inclusionStrategy);
        return TextRange.m6086getCollapsedimpl(endRange) ? TextRange.INSTANCE.m6097getZerod9O1mEE() : m1148enclosurepWDy79M(startRange, endRange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rangeOfWhitespaces(CharSequence $this$rangeOfWhitespaces, int offset) {
        int startOffset = offset;
        int endOffset = offset;
        while (startOffset > 0) {
            int codePointBeforeStart = CodepointHelpers_jvmKt.codePointBefore($this$rangeOfWhitespaces, startOffset);
            if (!isWhitespace(codePointBeforeStart)) {
                break;
            }
            startOffset -= Character.charCount(codePointBeforeStart);
        }
        while (endOffset < $this$rangeOfWhitespaces.length()) {
            int codePointAtEnd = CodepointHelpers_jvmKt.codePointAt($this$rangeOfWhitespaces, endOffset);
            if (!isWhitespace(codePointAtEnd)) {
                break;
            }
            endOffset += CodepointHelpers_jvmKt.charCount(codePointAtEnd);
        }
        return TextRangeKt.TextRange(startOffset, endOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getOffsetForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m1151getOffsetForHandwritingGestured4ec7I(TextLayoutState $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I, long pointInScreen, ViewConfiguration viewConfiguration) {
        MultiParagraph multiParagraph;
        TextLayoutResult layoutResult = $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I.getLayoutResult();
        if (layoutResult != null && (multiParagraph = layoutResult.getMultiParagraph()) != null) {
            return m1152getOffsetForHandwritingGestureubNVwUQ(multiParagraph, pointInScreen, $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I.getTextLayoutNodeCoordinates(), viewConfiguration);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getOffsetForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m1150getOffsetForHandwritingGestured4ec7I(LegacyTextFieldState $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I, long pointInScreen, ViewConfiguration viewConfiguration) {
        TextLayoutResult value;
        MultiParagraph multiParagraph;
        TextLayoutResultProxy layoutResult = $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I.getLayoutResult();
        if (layoutResult != null && (value = layoutResult.getValue()) != null && (multiParagraph = value.getMultiParagraph()) != null) {
            return m1152getOffsetForHandwritingGestureubNVwUQ(multiParagraph, pointInScreen, $this$getOffsetForHandwritingGesture_u2dd_u2d4ec7I.getLayoutCoordinates(), viewConfiguration);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBiDiBoundary(TextLayoutResult $this$isBiDiBoundary, int offset) {
        int line = $this$isBiDiBoundary.getLineForOffset(offset);
        return (offset == $this$isBiDiBoundary.getLineStart(line) || offset == TextLayoutResult.getLineEnd$default($this$isBiDiBoundary, line, false, 2, null)) ? $this$isBiDiBoundary.getParagraphDirection(offset) != $this$isBiDiBoundary.getBidiRunDirection(offset) : $this$isBiDiBoundary.getBidiRunDirection(offset) != $this$isBiDiBoundary.getBidiRunDirection(offset + (-1));
    }

    /* renamed from: getRangeForScreenRect-O048IG0, reason: not valid java name */
    private static final long m1154getRangeForScreenRectO048IG0(MultiParagraph $this$getRangeForScreenRect_u2dO048IG0, Rect rectInScreen, LayoutCoordinates layoutCoordinates, int granularity, TextInclusionStrategy inclusionStrategy) {
        if ($this$getRangeForScreenRect_u2dO048IG0 == null || layoutCoordinates == null) {
            return TextRange.INSTANCE.m6097getZerod9O1mEE();
        }
        long screenOriginInLocal = layoutCoordinates.mo5505screenToLocalMKHz9U(Offset.INSTANCE.m3938getZeroF1C5BW0());
        Rect localRect = rectInScreen.m3959translatek4lQ0M(screenOriginInLocal);
        return $this$getRangeForScreenRect_u2dO048IG0.m5959getRangeForRect86BmAI(localRect, granularity, inclusionStrategy);
    }

    /* renamed from: getOffsetForHandwritingGesture-ubNVwUQ, reason: not valid java name */
    private static final int m1152getOffsetForHandwritingGestureubNVwUQ(MultiParagraph $this$getOffsetForHandwritingGesture_u2dubNVwUQ, long pointInScreen, LayoutCoordinates layoutCoordinates, ViewConfiguration viewConfiguration) {
        long localPoint;
        int line;
        if (layoutCoordinates == null || (line = m1149getLineForHandwritingGestured4ec7I($this$getOffsetForHandwritingGesture_u2dubNVwUQ, (localPoint = layoutCoordinates.mo5505screenToLocalMKHz9U(pointInScreen)), viewConfiguration)) == -1) {
            return -1;
        }
        long adjustedPoint = Offset.m3916copydBAh8RU$default(localPoint, 0.0f, ($this$getOffsetForHandwritingGesture_u2dubNVwUQ.getLineTop(line) + $this$getOffsetForHandwritingGesture_u2dubNVwUQ.getLineBottom(line)) / 2.0f, 1, null);
        return $this$getOffsetForHandwritingGesture_u2dubNVwUQ.m5958getOffsetForPositionk4lQ0M(adjustedPoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getRangeForRemoveSpaceGesture-5iVPX68, reason: not valid java name */
    public static final long m1153getRangeForRemoveSpaceGesture5iVPX68(TextLayoutResult $this$getRangeForRemoveSpaceGesture_u2d5iVPX68, long startPointInScreen, long endPointerInScreen, LayoutCoordinates layoutCoordinates, ViewConfiguration viewConfiguration) {
        int min;
        int line;
        if ($this$getRangeForRemoveSpaceGesture_u2d5iVPX68 != null && layoutCoordinates != null) {
            long localStartPoint = layoutCoordinates.mo5505screenToLocalMKHz9U(startPointInScreen);
            long localEndPoint = layoutCoordinates.mo5505screenToLocalMKHz9U(endPointerInScreen);
            int startLine = m1149getLineForHandwritingGestured4ec7I($this$getRangeForRemoveSpaceGesture_u2d5iVPX68.getMultiParagraph(), localStartPoint, viewConfiguration);
            int endLine = m1149getLineForHandwritingGestured4ec7I($this$getRangeForRemoveSpaceGesture_u2d5iVPX68.getMultiParagraph(), localEndPoint, viewConfiguration);
            if (startLine == -1) {
                if (endLine == -1) {
                    return TextRange.INSTANCE.m6097getZerod9O1mEE();
                }
                line = endLine;
            } else {
                if (endLine == -1) {
                    min = startLine;
                } else {
                    min = Math.min(startLine, endLine);
                }
                line = min;
            }
            float lineCenter = ($this$getRangeForRemoveSpaceGesture_u2d5iVPX68.getLineTop(line) + $this$getRangeForRemoveSpaceGesture_u2d5iVPX68.getLineBottom(line)) / 2.0f;
            Rect rect = new Rect(Math.min(Offset.m3922getXimpl(localStartPoint), Offset.m3922getXimpl(localEndPoint)), lineCenter - 0.1f, Math.max(Offset.m3922getXimpl(localStartPoint), Offset.m3922getXimpl(localEndPoint)), lineCenter + 0.1f);
            return $this$getRangeForRemoveSpaceGesture_u2d5iVPX68.getMultiParagraph().m5959getRangeForRect86BmAI(rect, TextGranularity.INSTANCE.m6056getCharacterDRrd7Zo(), TextInclusionStrategy.INSTANCE.getAnyOverlap());
        }
        return TextRange.INSTANCE.m6097getZerod9O1mEE();
    }

    /* renamed from: getLineForHandwritingGesture-d-4ec7I, reason: not valid java name */
    private static final int m1149getLineForHandwritingGestured4ec7I(MultiParagraph $this$getLineForHandwritingGesture_u2dd_u2d4ec7I, long localPoint, ViewConfiguration viewConfiguration) {
        float lineMargin = viewConfiguration != null ? viewConfiguration.getHandwritingGestureLineMargin() : 0.0f;
        int line = $this$getLineForHandwritingGesture_u2dd_u2d4ec7I.getLineForVerticalPosition(Offset.m3923getYimpl(localPoint));
        if (Offset.m3923getYimpl(localPoint) < $this$getLineForHandwritingGesture_u2dd_u2d4ec7I.getLineTop(line) - lineMargin || Offset.m3923getYimpl(localPoint) > $this$getLineForHandwritingGesture_u2dd_u2d4ec7I.getLineBottom(line) + lineMargin || Offset.m3922getXimpl(localPoint) < (-lineMargin) || Offset.m3922getXimpl(localPoint) > $this$getLineForHandwritingGesture_u2dd_u2d4ec7I.getWidth() + lineMargin) {
            return -1;
        }
        return line;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand compoundEditCommand(final EditCommand... editCommands) {
        return new EditCommand() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGesture_androidKt$compoundEditCommand$1
            @Override // androidx.compose.ui.text.input.EditCommand
            public void applyTo(androidx.compose.ui.text.input.EditingBuffer buffer) {
                for (EditCommand editCommand : editCommands) {
                    editCommand.applyTo(buffer);
                }
            }
        };
    }

    /* renamed from: enclosure-pWDy79M, reason: not valid java name */
    private static final long m1148enclosurepWDy79M(long a, long b) {
        return TextRangeKt.TextRange(Math.min(TextRange.m6092getStartimpl(a), TextRange.m6092getStartimpl(a)), Math.max(TextRange.m6087getEndimpl(b), TextRange.m6087getEndimpl(b)));
    }
}
