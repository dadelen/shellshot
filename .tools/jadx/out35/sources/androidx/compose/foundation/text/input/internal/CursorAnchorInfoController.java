package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: CursorAnchorInfoController.android.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J8\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u001eH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\u00020\u0016X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/foundation/text/input/internal/CursorAnchorInfoController;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "composeImm", "Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "monitorScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;Lkotlinx/coroutines/CoroutineScope;)V", "androidMatrix", "Landroid/graphics/Matrix;", "builder", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "hasPendingImmediateRequest", "", "includeCharacterBounds", "includeEditorBounds", "includeInsertionMarker", "includeLineBounds", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "monitorEnabled", "monitorJob", "Lkotlinx/coroutines/Job;", "calculateCursorAnchorInfo", "Landroid/view/inputmethod/CursorAnchorInfo;", "requestUpdates", "", "immediate", "monitor", "cursorUpdateMode", "", "startOrStopMonitoring", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CursorAnchorInfoController {
    public static final int $stable = 8;
    private final ComposeInputMethodManager composeImm;
    private boolean hasPendingImmediateRequest;
    private boolean includeCharacterBounds;
    private boolean includeEditorBounds;
    private boolean includeInsertionMarker;
    private boolean includeLineBounds;
    private boolean monitorEnabled;
    private Job monitorJob;
    private final CoroutineScope monitorScope;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private final CursorAnchorInfo.Builder builder = new CursorAnchorInfo.Builder();
    private final float[] matrix = Matrix.m4393constructorimpl$default(null, 1, null);
    private final android.graphics.Matrix androidMatrix = new android.graphics.Matrix();

    public CursorAnchorInfoController(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, ComposeInputMethodManager composeImm, CoroutineScope monitorScope) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.composeImm = composeImm;
        this.monitorScope = monitorScope;
    }

    public final void requestUpdates(int cursorUpdateMode) {
        boolean includeCharacterBounds;
        boolean includeLineBounds;
        boolean includeInsertionMarker;
        boolean includeInsertionMarker2;
        boolean immediate = (cursorUpdateMode & 1) != 0;
        boolean monitor = (cursorUpdateMode & 2) != 0;
        boolean includeInsertionMarker3 = (cursorUpdateMode & 16) != 0;
        boolean includeCharacterBounds2 = (cursorUpdateMode & 8) != 0;
        boolean includeEditorBounds = (cursorUpdateMode & 4) != 0;
        boolean includeLineBounds2 = (cursorUpdateMode & 32) != 0;
        if (!includeInsertionMarker3 && !includeCharacterBounds2 && !includeEditorBounds && !includeLineBounds2) {
            includeCharacterBounds = true;
            includeLineBounds = true;
            includeInsertionMarker = true;
            includeInsertionMarker2 = true;
        } else {
            includeCharacterBounds = includeCharacterBounds2;
            includeLineBounds = includeLineBounds2;
            includeInsertionMarker = includeInsertionMarker3;
            includeInsertionMarker2 = includeEditorBounds;
        }
        requestUpdates(immediate, monitor, includeInsertionMarker, includeCharacterBounds, includeInsertionMarker2, includeLineBounds);
    }

    private final void requestUpdates(boolean immediate, boolean monitor, boolean includeInsertionMarker, boolean includeCharacterBounds, boolean includeEditorBounds, boolean includeLineBounds) {
        this.includeInsertionMarker = includeInsertionMarker;
        this.includeCharacterBounds = includeCharacterBounds;
        this.includeEditorBounds = includeEditorBounds;
        this.includeLineBounds = includeLineBounds;
        if (immediate) {
            this.hasPendingImmediateRequest = true;
            CursorAnchorInfo p0 = calculateCursorAnchorInfo();
            if (p0 != null) {
                this.composeImm.updateCursorAnchorInfo(p0);
            }
        }
        this.monitorEnabled = monitor;
        startOrStopMonitoring();
    }

    private final void startOrStopMonitoring() {
        Job launch$default;
        boolean z = this.monitorEnabled;
        Job job = this.monitorJob;
        boolean z2 = true;
        if (z) {
            if (job == null || !job.isActive()) {
                z2 = false;
            }
            if (!z2) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(this.monitorScope, null, CoroutineStart.UNDISPATCHED, new CursorAnchorInfoController$startOrStopMonitoring$1(this, null), 1, null);
                this.monitorJob = launch$default;
                return;
            }
            return;
        }
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.monitorJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CursorAnchorInfo calculateCursorAnchorInfo() {
        LayoutCoordinates coreCoordinates;
        LayoutCoordinates decorationBoxCoordinates;
        TextLayoutResult textLayoutResult;
        LayoutCoordinates textLayoutCoordinates = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutCoordinates != null) {
            if (!textLayoutCoordinates.isAttached()) {
                textLayoutCoordinates = null;
            }
            if (textLayoutCoordinates != null && (coreCoordinates = this.textLayoutState.getCoreNodeCoordinates()) != null) {
                if (!coreCoordinates.isAttached()) {
                    coreCoordinates = null;
                }
                if (coreCoordinates != null && (decorationBoxCoordinates = this.textLayoutState.getDecoratorNodeCoordinates()) != null) {
                    if (!decorationBoxCoordinates.isAttached()) {
                        decorationBoxCoordinates = null;
                    }
                    if (decorationBoxCoordinates == null || (textLayoutResult = this.textLayoutState.getLayoutResult()) == null) {
                        return null;
                    }
                    TextFieldCharSequence text = this.textFieldState.getVisualText();
                    Matrix.m4402resetimpl(this.matrix);
                    textLayoutCoordinates.mo5507transformToScreen58bKbWc(this.matrix);
                    AndroidMatrixConversions_androidKt.m4033setFromEL8BTi8(this.androidMatrix, this.matrix);
                    Rect innerTextFieldBounds = SelectionManagerKt.visibleBounds(coreCoordinates).m3959translatek4lQ0M(textLayoutCoordinates.mo5500localPositionOfR5De75A(coreCoordinates, Offset.INSTANCE.m3938getZeroF1C5BW0()));
                    Rect decorationBoxBounds = SelectionManagerKt.visibleBounds(decorationBoxCoordinates).m3959translatek4lQ0M(textLayoutCoordinates.mo5500localPositionOfR5De75A(decorationBoxCoordinates, Offset.INSTANCE.m3938getZeroF1C5BW0()));
                    return CursorAnchorInfoBuilder_androidKt.m1126buildvxqZcH0(this.builder, text, text.getSelection(), text.getComposition(), textLayoutResult, this.androidMatrix, innerTextFieldBounds, decorationBoxBounds, this.includeInsertionMarker, this.includeCharacterBounds, this.includeEditorBounds, this.includeLineBounds);
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
