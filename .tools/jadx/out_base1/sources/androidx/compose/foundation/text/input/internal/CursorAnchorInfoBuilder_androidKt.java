package androidx.compose.foundation.text.input.internal;

import android.graphics.Matrix;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: CursorAnchorInfoBuilder.android.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001ax\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0014H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a$\u0010\u001a\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"addCharacterBounds", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "startOffset", "", "endOffset", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "innerTextFieldBounds", "Landroidx/compose/ui/geometry/Rect;", "build", "Landroid/view/inputmethod/CursorAnchorInfo;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "composition", "matrix", "Landroid/graphics/Matrix;", "decorationBoxBounds", "includeInsertionMarker", "", "includeCharacterBounds", "includeEditorBounds", "includeLineBounds", "build-vxqZcH0", "(Landroid/view/inputmethod/CursorAnchorInfo$Builder;Ljava/lang/CharSequence;JLandroidx/compose/ui/text/TextRange;Landroidx/compose/ui/text/TextLayoutResult;Landroid/graphics/Matrix;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;ZZZZ)Landroid/view/inputmethod/CursorAnchorInfo;", "setInsertionMarker", "selectionStart", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CursorAnchorInfoBuilder_androidKt {
    /* renamed from: build-vxqZcH0$default, reason: not valid java name */
    public static /* synthetic */ CursorAnchorInfo m1127buildvxqZcH0$default(CursorAnchorInfo.Builder builder, CharSequence charSequence, long j, TextRange textRange, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 128) != 0) {
            z = true;
        }
        if ((i & 256) != 0) {
            z2 = true;
        }
        if ((i & 512) != 0) {
            z3 = true;
        }
        if ((i & 1024) != 0) {
            z4 = true;
        }
        return m1126buildvxqZcH0(builder, charSequence, j, textRange, textLayoutResult, matrix, rect, rect2, z, z2, z3, z4);
    }

    /* renamed from: build-vxqZcH0, reason: not valid java name */
    public static final CursorAnchorInfo m1126buildvxqZcH0(CursorAnchorInfo.Builder $this$build_u2dvxqZcH0, CharSequence text, long selection, TextRange composition, TextLayoutResult textLayoutResult, Matrix matrix, Rect innerTextFieldBounds, Rect decorationBoxBounds, boolean includeInsertionMarker, boolean includeCharacterBounds, boolean includeEditorBounds, boolean includeLineBounds) {
        $this$build_u2dvxqZcH0.reset();
        $this$build_u2dvxqZcH0.setMatrix(matrix);
        int selectionStart = TextRange.m6090getMinimpl(selection);
        int selectionEnd = TextRange.m6089getMaximpl(selection);
        $this$build_u2dvxqZcH0.setSelectionRange(selectionStart, selectionEnd);
        if (includeInsertionMarker) {
            setInsertionMarker($this$build_u2dvxqZcH0, selectionStart, textLayoutResult, innerTextFieldBounds);
        }
        if (includeCharacterBounds) {
            int compositionStart = composition != null ? TextRange.m6090getMinimpl(composition.getPackedValue()) : -1;
            int compositionEnd = composition != null ? TextRange.m6089getMaximpl(composition.getPackedValue()) : -1;
            boolean z = false;
            if (compositionStart >= 0 && compositionStart < compositionEnd) {
                z = true;
            }
            if (z) {
                $this$build_u2dvxqZcH0.setComposingText(compositionStart, text.subSequence(compositionStart, compositionEnd));
                addCharacterBounds($this$build_u2dvxqZcH0, compositionStart, compositionEnd, textLayoutResult, innerTextFieldBounds);
            }
        }
        if (includeEditorBounds) {
            CursorAnchorInfoApi33Helper.setEditorBoundsInfo($this$build_u2dvxqZcH0, decorationBoxBounds);
        }
        if (includeLineBounds) {
            CursorAnchorInfoApi34Helper.addVisibleLineBounds($this$build_u2dvxqZcH0, textLayoutResult, innerTextFieldBounds);
        }
        return $this$build_u2dvxqZcH0.build();
    }

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder $this$setInsertionMarker, int selectionStart, TextLayoutResult textLayoutResult, Rect innerTextFieldBounds) {
        if (selectionStart < 0) {
            return $this$setInsertionMarker;
        }
        Rect cursorRect = textLayoutResult.getCursorRect(selectionStart);
        float x = RangesKt.coerceIn(cursorRect.getLeft(), 0.0f, IntSize.m6796getWidthimpl(textLayoutResult.getSize()));
        boolean isTopVisible = LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(innerTextFieldBounds, x, cursorRect.getTop());
        boolean isBottomVisible = LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(innerTextFieldBounds, x, cursorRect.getBottom());
        boolean isRtl = textLayoutResult.getBidiRunDirection(selectionStart) == ResolvedTextDirection.Rtl;
        int flags = (isTopVisible || isBottomVisible) ? 0 | 1 : 0;
        if (!isTopVisible || !isBottomVisible) {
            flags |= 2;
        }
        if (isRtl) {
            flags |= 4;
        }
        $this$setInsertionMarker.setInsertionMarkerLocation(x, cursorRect.getTop(), cursorRect.getBottom(), cursorRect.getBottom(), flags);
        return $this$setInsertionMarker;
    }

    private static final CursorAnchorInfo.Builder addCharacterBounds(CursorAnchorInfo.Builder $this$addCharacterBounds, int startOffset, int endOffset, TextLayoutResult textLayoutResult, Rect innerTextFieldBounds) {
        float[] array = new float[(endOffset - startOffset) * 4];
        textLayoutResult.getMultiParagraph().m5957fillBoundingBoxes8ffj60Q(TextRangeKt.TextRange(startOffset, endOffset), array, 0);
        for (int offset = startOffset; offset < endOffset; offset++) {
            int arrayIndex = (offset - startOffset) * 4;
            Rect rect = new Rect(array[arrayIndex], array[arrayIndex + 1], array[arrayIndex + 2], array[arrayIndex + 3]);
            int flags = 0;
            if (innerTextFieldBounds.overlaps(rect)) {
                flags = 0 | 1;
            }
            if (!LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(innerTextFieldBounds, rect.getLeft(), rect.getTop()) || !LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(innerTextFieldBounds, rect.getRight(), rect.getBottom())) {
                flags |= 2;
            }
            if (textLayoutResult.getBidiRunDirection(offset) == ResolvedTextDirection.Rtl) {
                flags |= 4;
            }
            $this$addCharacterBounds.addCharacterBounds(offset, rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), flags);
        }
        return $this$addCharacterBounds;
    }
}
