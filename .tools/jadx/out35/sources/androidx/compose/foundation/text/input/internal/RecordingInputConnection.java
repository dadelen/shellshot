package androidx.compose.foundation.text.input.internal;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextInCodePointsCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.SetComposingRegionCommand;
import androidx.compose.ui.text.input.SetComposingTextCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: RecordingInputConnection.android.kt */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0016H\u0002J\b\u0010*\u001a\u00020\u0007H\u0016J\b\u0010+\u001a\u00020\u0007H\u0002J\u0010\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u0012H\u0016J\b\u0010.\u001a\u00020(H\u0016J\u0012\u0010/\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u000101H\u0016J\"\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00122\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0012\u00108\u001a\u00020\u00072\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u001a\u0010;\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u0012H\u0016J\u0018\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u0012H\u0016J\u0018\u0010A\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u0012H\u0016J\b\u0010B\u001a\u00020\u0007H\u0016J\b\u0010C\u001a\u00020\u0007H\u0002J\u0017\u0010D\u001a\u00020\u00072\f\u0010E\u001a\b\u0012\u0004\u0012\u00020(0FH\u0082\bJ\b\u0010G\u001a\u00020\u0007H\u0016J\u0010\u0010H\u001a\u00020\u00122\u0006\u0010I\u001a\u00020\u0012H\u0016J\u001a\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u00105\u001a\u00020\u0012H\u0016J\n\u0010N\u001a\u0004\u0018\u00010OH\u0016J\u0012\u0010P\u001a\u0004\u0018\u00010<2\u0006\u00105\u001a\u00020\u0012H\u0016J\u0018\u0010Q\u001a\u00020<2\u0006\u0010R\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u0012H\u0016J\u0018\u0010S\u001a\u00020<2\u0006\u0010R\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u0012H\u0016J\u0010\u0010T\u001a\u00020(2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010W\u001a\u00020\u00072\u0006\u0010X\u001a\u00020\u0012H\u0016J\u0010\u0010Y\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u0012H\u0016J$\u0010[\u001a\u00020(2\u0006\u0010\\\u001a\u00020]2\b\u0010^\u001a\u0004\u0018\u00010_2\b\u0010`\u001a\u0004\u0018\u00010aH\u0016J\u001c\u0010b\u001a\u00020\u00072\b\u0010c\u001a\u0004\u0018\u00010V2\b\u0010d\u001a\u0004\u0018\u000107H\u0016J\u001a\u0010e\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010hH\u0016J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u0007H\u0016J\u0010\u0010k\u001a\u00020\u00072\u0006\u0010l\u001a\u00020\u0012H\u0016J\u0010\u0010m\u001a\u00020\u00072\u0006\u0010n\u001a\u00020oH\u0016J\u0010\u0010p\u001a\u00020(2\u0006\u0010q\u001a\u00020\u0012H\u0002J\u0018\u0010r\u001a\u00020\u00072\u0006\u0010s\u001a\u00020\u00122\u0006\u0010t\u001a\u00020\u0012H\u0016J\u001a\u0010u\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u0012H\u0016J\u0018\u0010v\u001a\u00020\u00072\u0006\u0010s\u001a\u00020\u00122\u0006\u0010t\u001a\u00020\u0012H\u0016J\u0016\u0010w\u001a\u00020(2\u0006\u0010x\u001a\u00020\u00032\u0006\u0010y\u001a\u00020zR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006{"}, d2 = {"Landroidx/compose/foundation/text/input/internal/RecordingInputConnection;", "Landroid/view/inputmethod/InputConnection;", "initState", "Landroidx/compose/ui/text/input/TextFieldValue;", "eventCallback", "Landroidx/compose/foundation/text/input/internal/InputEventCallback2;", "autoCorrect", "", "legacyTextFieldState", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "textFieldSelectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/input/internal/InputEventCallback2;ZLandroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/platform/ViewConfiguration;)V", "getAutoCorrect", "()Z", "batchDepth", "", "currentExtractedTextRequestToken", "editCommands", "", "Landroidx/compose/ui/text/input/EditCommand;", "getEventCallback", "()Landroidx/compose/foundation/text/input/internal/InputEventCallback2;", "extractedTextMonitorMode", "isActive", "getLegacyTextFieldState", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "getTextFieldSelectionManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "textFieldValue", "getTextFieldValue$foundation_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setTextFieldValue$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "addEditCommandWithBatch", "", "editCommand", "beginBatchEdit", "beginBatchEditInternal", "clearMetaKeyStates", "states", "closeConnection", "commitCompletion", "text", "Landroid/view/inputmethod/CompletionInfo;", "commitContent", "inputContentInfo", "Landroid/view/inputmethod/InputContentInfo;", "flags", "opts", "Landroid/os/Bundle;", "commitCorrection", "correctionInfo", "Landroid/view/inputmethod/CorrectionInfo;", "commitText", "", "newCursorPosition", "deleteSurroundingText", "beforeLength", "afterLength", "deleteSurroundingTextInCodePoints", "endBatchEdit", "endBatchEditInternal", "ensureActive", "block", "Lkotlin/Function0;", "finishComposingText", "getCursorCapsMode", "reqModes", "getExtractedText", "Landroid/view/inputmethod/ExtractedText;", "request", "Landroid/view/inputmethod/ExtractedTextRequest;", "getHandler", "Landroid/os/Handler;", "getSelectedText", "getTextAfterCursor", "maxChars", "getTextBeforeCursor", "logDebug", "message", "", "performContextMenuAction", "id", "performEditorAction", "editorAction", "performHandwritingGesture", "gesture", "Landroid/view/inputmethod/HandwritingGesture;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "Ljava/util/function/IntConsumer;", "performPrivateCommand", "action", "data", "previewHandwritingGesture", "Landroid/view/inputmethod/PreviewableHandwritingGesture;", "cancellationSignal", "Landroid/os/CancellationSignal;", "reportFullscreenMode", "enabled", "requestCursorUpdates", "cursorUpdateMode", "sendKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "sendSynthesizedKeyEvent", "code", "setComposingRegion", "start", "end", "setComposingText", "setSelection", "updateInputState", "state", "inputMethodManager", "Landroidx/compose/foundation/text/input/internal/InputMethodManager;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RecordingInputConnection implements InputConnection {
    public static final int $stable = 8;
    private final boolean autoCorrect;
    private int batchDepth;
    private int currentExtractedTextRequestToken;
    private final List<EditCommand> editCommands;
    private final InputEventCallback2 eventCallback;
    private boolean extractedTextMonitorMode;
    private boolean isActive;
    private final LegacyTextFieldState legacyTextFieldState;
    private final TextFieldSelectionManager textFieldSelectionManager;
    private TextFieldValue textFieldValue;
    private final ViewConfiguration viewConfiguration;

    public RecordingInputConnection(TextFieldValue initState, InputEventCallback2 eventCallback, boolean autoCorrect, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration) {
        this.eventCallback = eventCallback;
        this.autoCorrect = autoCorrect;
        this.legacyTextFieldState = legacyTextFieldState;
        this.textFieldSelectionManager = textFieldSelectionManager;
        this.viewConfiguration = viewConfiguration;
        this.textFieldValue = initState;
        this.editCommands = new ArrayList();
        this.isActive = true;
    }

    public /* synthetic */ RecordingInputConnection(TextFieldValue textFieldValue, InputEventCallback2 inputEventCallback2, boolean z, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldValue, inputEventCallback2, z, (i & 8) != 0 ? null : legacyTextFieldState, (i & 16) != 0 ? null : textFieldSelectionManager, (i & 32) != 0 ? null : viewConfiguration);
    }

    public final InputEventCallback2 getEventCallback() {
        return this.eventCallback;
    }

    public final boolean getAutoCorrect() {
        return this.autoCorrect;
    }

    public final LegacyTextFieldState getLegacyTextFieldState() {
        return this.legacyTextFieldState;
    }

    public final TextFieldSelectionManager getTextFieldSelectionManager() {
        return this.textFieldSelectionManager;
    }

    public final ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    /* renamed from: getTextFieldValue$foundation_release, reason: from getter */
    public final TextFieldValue getTextFieldValue() {
        return this.textFieldValue;
    }

    public final void setTextFieldValue$foundation_release(TextFieldValue value) {
        this.textFieldValue = value;
    }

    private final boolean ensureActive(Function0<Unit> block) {
        boolean applying = this.isActive;
        if (applying) {
            block.invoke();
        }
        return applying;
    }

    public final void updateInputState(TextFieldValue state, InputMethodManager inputMethodManager) {
        ExtractedText extractedText;
        if (this.isActive) {
            setTextFieldValue$foundation_release(state);
            if (this.extractedTextMonitorMode) {
                int i = this.currentExtractedTextRequestToken;
                extractedText = RecordingInputConnection_androidKt.toExtractedText(state);
                inputMethodManager.updateExtractedText(i, extractedText);
            }
            TextRange composition = state.getComposition();
            int compositionStart = composition != null ? TextRange.m6090getMinimpl(composition.getPackedValue()) : -1;
            TextRange composition2 = state.getComposition();
            int compositionEnd = composition2 != null ? TextRange.m6089getMaximpl(composition2.getPackedValue()) : -1;
            inputMethodManager.updateSelection(TextRange.m6090getMinimpl(state.getSelection()), TextRange.m6089getMaximpl(state.getSelection()), compositionStart, compositionEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addEditCommandWithBatch(EditCommand editCommand) {
        beginBatchEditInternal();
        try {
            this.editCommands.add(editCommand);
        } finally {
            endBatchEditInternal();
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return beginBatchEditInternal();
        }
        return applying$iv;
    }

    private final boolean beginBatchEditInternal() {
        this.batchDepth++;
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        return endBatchEditInternal();
    }

    private final boolean endBatchEditInternal() {
        this.batchDepth--;
        if (this.batchDepth == 0 && !this.editCommands.isEmpty()) {
            this.eventCallback.onEditCommands(CollectionsKt.toMutableList((Collection) this.editCommands));
            this.editCommands.clear();
        }
        return this.batchDepth > 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
        this.editCommands.clear();
        this.batchDepth = 0;
        this.isActive = false;
        this.eventCallback.onConnectionClosed(this);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new CommitTextCommand(String.valueOf(text), newCursorPosition));
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int start, int end) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new SetComposingRegionCommand(start, end));
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new SetComposingTextCommand(String.valueOf(text), newCursorPosition));
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new DeleteSurroundingTextInCodePointsCommand(beforeLength, afterLength));
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new DeleteSurroundingTextCommand(beforeLength, afterLength));
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int start, int end) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new SetSelectionCommand(start, end));
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            addEditCommandWithBatch(new FinishComposingTextCommand());
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            this.eventCallback.onKeyEvent(event);
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int maxChars, int flags) {
        String result = TextFieldValueKt.getTextBeforeSelection(this.textFieldValue, maxChars).toString();
        return result;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int maxChars, int flags) {
        String result = TextFieldValueKt.getTextAfterSelection(this.textFieldValue, maxChars).toString();
        return result;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int flags) {
        if (TextRange.m6086getCollapsedimpl(this.textFieldValue.getSelection())) {
            return null;
        }
        CharSequence result = TextFieldValueKt.getSelectedText(this.textFieldValue).toString();
        return result;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        boolean includeCharacterBounds;
        boolean includeLineBounds;
        boolean includeInsertionMarker;
        boolean includeInsertionMarker2;
        boolean applying$iv = this.isActive;
        if (applying$iv) {
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
            this.eventCallback.onRequestCursorAnchorInfo(immediate, monitor, includeInsertionMarker, includeCharacterBounds, includeInsertionMarker2, includeLineBounds);
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        ExtractedText extractedText;
        this.extractedTextMonitorMode = (flags & 1) != 0;
        if (this.extractedTextMonitorMode) {
            this.currentExtractedTextRequestToken = request != null ? request.token : 0;
        }
        extractedText = RecordingInputConnection_androidKt.toExtractedText(this.textFieldValue);
        return extractedText;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
    
        return false;
     */
    @Override // android.view.inputmethod.InputConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean performContextMenuAction(int r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = 0
            boolean r2 = r0.isActive
            r3 = r2
            r4 = 0
            if (r3 == 0) goto L36
            r2 = 0
            r5 = 0
            switch(r9) {
                case 16908319: goto L21;
                case 16908320: goto L1b;
                case 16908321: goto L15;
                case 16908322: goto Lf;
                default: goto Le;
            }
        Le:
            goto L35
        Lf:
            r6 = 279(0x117, float:3.91E-43)
            r8.sendSynthesizedKeyEvent(r6)
            goto L35
        L15:
            r6 = 278(0x116, float:3.9E-43)
            r8.sendSynthesizedKeyEvent(r6)
            goto L35
        L1b:
            r6 = 277(0x115, float:3.88E-43)
            r8.sendSynthesizedKeyEvent(r6)
            goto L35
        L21:
            androidx.compose.ui.text.input.SetSelectionCommand r6 = new androidx.compose.ui.text.input.SetSelectionCommand
            androidx.compose.ui.text.input.TextFieldValue r7 = r8.textFieldValue
            java.lang.String r7 = r7.getText()
            int r7 = r7.length()
            r6.<init>(r5, r7)
            androidx.compose.ui.text.input.EditCommand r6 = (androidx.compose.ui.text.input.EditCommand) r6
            r8.addEditCommandWithBatch(r6)
        L35:
            return r5
        L36:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.RecordingInputConnection.performContextMenuAction(int):boolean");
    }

    private final void sendSynthesizedKeyEvent(int code) {
        sendKeyEvent(new KeyEvent(0, code));
        sendKeyEvent(new KeyEvent(1, code));
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int editorAction) {
        int imeAction;
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            switch (editorAction) {
                case 0:
                    imeAction = ImeAction.INSTANCE.m6269getDefaulteUduSuo();
                    break;
                case 1:
                default:
                    Log.w("RecordingIC", "IME sends unsupported Editor Action: " + editorAction);
                    imeAction = ImeAction.INSTANCE.m6269getDefaulteUduSuo();
                    break;
                case 2:
                    imeAction = ImeAction.INSTANCE.m6271getGoeUduSuo();
                    break;
                case 3:
                    imeAction = ImeAction.INSTANCE.m6275getSearcheUduSuo();
                    break;
                case 4:
                    imeAction = ImeAction.INSTANCE.m6276getSendeUduSuo();
                    break;
                case 5:
                    imeAction = ImeAction.INSTANCE.m6272getNexteUduSuo();
                    break;
                case 6:
                    imeAction = ImeAction.INSTANCE.m6270getDoneeUduSuo();
                    break;
                case 7:
                    imeAction = ImeAction.INSTANCE.m6274getPreviouseUduSuo();
                    break;
            }
            this.eventCallback.mo1159onImeActionKlQnJC8(imeAction);
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public void performHandwritingGesture(HandwritingGesture gesture, Executor executor, IntConsumer consumer) {
        Api34LegacyPerformHandwritingGestureImpl.INSTANCE.performHandwritingGesture(this.legacyTextFieldState, this.textFieldSelectionManager, gesture, this.viewConfiguration, executor, consumer, new Function1<EditCommand, Unit>() { // from class: androidx.compose.foundation.text.input.internal.RecordingInputConnection$performHandwritingGesture$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(EditCommand editCommand) {
                invoke2(editCommand);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EditCommand it) {
                RecordingInputConnection.this.addEditCommandWithBatch(it);
            }
        });
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, CancellationSignal cancellationSignal) {
        return Api34LegacyPerformHandwritingGestureImpl.INSTANCE.previewHandwritingGesture(this.legacyTextFieldState, this.textFieldSelectionManager, gesture, cancellationSignal);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo text) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return false;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return this.autoCorrect;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int states) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return false;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean enabled) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int reqModes) {
        return TextUtils.getCapsMode(this.textFieldValue.getText(), TextRange.m6090getMinimpl(this.textFieldValue.getSelection()), reqModes);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String action, Bundle data) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return true;
        }
        return applying$iv;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
        boolean applying$iv = this.isActive;
        if (applying$iv) {
            return false;
        }
        return applying$iv;
    }

    private final void logDebug(String message) {
    }
}
