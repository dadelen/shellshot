package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010p\u001a\u00020FH\u0000¢\u0006\u0002\bqJ\u0018\u0010r\u001a\u00020F2\u0006\u0010s\u001a\u00020\fø\u0001\u0000¢\u0006\u0004\bt\u0010uJ\u0017\u0010v\u001a\u00020F2\b\b\u0002\u0010w\u001a\u00020!H\u0000¢\u0006\u0002\bxJ\"\u0010y\u001a\u00020C2\u0006\u0010z\u001a\u00020_2\u0006\u0010{\u001a\u00020|H\u0002ø\u0001\u0000¢\u0006\u0004\b}\u0010~J\u000e\u0010\u007f\u001a\u00020[H\u0000¢\u0006\u0003\b\u0080\u0001J\u000f\u0010\u0081\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0082\u0001J\u001e\u0010\u0083\u0001\u001a\u00020F2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\fH\u0000ø\u0001\u0000¢\u0006\u0003\b\u0084\u0001J\u001a\u0010\u0085\u0001\u001a\u00020F2\t\b\u0002\u0010\u0086\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u0087\u0001J\u000f\u0010\u0088\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0089\u0001J\n\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\"\u0010\u008c\u0001\u001a\u00020\f2\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J!\u0010\u0091\u0001\u001a\u00020\f2\u0007\u0010\u0092\u0001\u001a\u00020!H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u0018\u0010\u0095\u0001\u001a\u00020[2\u0007\u0010\u0092\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u0096\u0001J\u000f\u0010\u0097\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u0098\u0001J\u000f\u0010\u0099\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b\u009a\u0001J\u000f\u0010\u009b\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u009c\u0001J\u000f\u0010\u009d\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b\u009e\u0001J\u001d\u0010\u009f\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020|H\u0000ø\u0001\u0000¢\u0006\u0005\b¡\u0001\u0010uJ\u0013\u0010¢\u0001\u001a\u00020F2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0002J\u001d\u0010¥\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020|H\u0000ø\u0001\u0000¢\u0006\u0005\b¦\u0001\u0010uJ\u000f\u0010§\u0001\u001a\u00020FH\u0000¢\u0006\u0003\b¨\u0001J\u0012\u0010©\u0001\u001a\u00020F2\u0007\u0010ª\u0001\u001a\u00020!H\u0002JK\u0010«\u0001\u001a\u00020|2\u0006\u0010d\u001a\u00020C2\u0007\u0010¬\u0001\u001a\u00020\f2\u0007\u0010\u00ad\u0001\u001a\u00020!2\u0007\u0010\u0092\u0001\u001a\u00020!2\b\u0010®\u0001\u001a\u00030¯\u00012\u0007\u0010°\u0001\u001a\u00020!H\u0002ø\u0001\u0000¢\u0006\u0006\b±\u0001\u0010²\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR5\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0018R/\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001a8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010\u0013\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR+\u0010\"\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0013\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010(\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u0013\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0014\u00108\u001a\u000209X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u00020CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010D\u001a\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F0EX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u000e\u0010K\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010N\u001a\u0004\u0018\u00010OX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001c\u0010T\u001a\u0004\u0018\u00010UX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020[X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0016\u0010^\u001a\u0004\u0018\u00010_8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR+\u0010d\u001a\u00020C2\u0006\u0010\u000b\u001a\u00020C8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010\u0013\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001a\u0010j\u001a\u00020kX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010o\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006³\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "(Landroidx/compose/foundation/text/UndoManager;)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager$foundation_release", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager$foundation_release", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "<set-?>", "Landroidx/compose/ui/geometry/Offset;", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setCurrentDragPosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "currentDragPosition$delegate", "Landroidx/compose/runtime/MutableState;", "dragBeginOffsetInText", "", "Ljava/lang/Integer;", "dragBeginPosition", "J", "dragTotalDistance", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "", "editable", "getEditable", "()Z", "setEditable", "(Z)V", "editable$delegate", "enabled", "getEnabled", "setEnabled", "enabled$delegate", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping$foundation_release", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping$foundation_release", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "oldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "", "getOnValueChange$foundation_release", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$foundation_release", "(Lkotlin/jvm/functions/Function1;)V", "previousRawDragOffset", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "getState$foundation_release", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "setState$foundation_release", "(Landroidx/compose/foundation/text/LegacyTextFieldState;)V", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "touchSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getTouchSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/TextDragObserver;", "transformedText", "Landroidx/compose/ui/text/AnnotatedString;", "getTransformedText$foundation_release", "()Landroidx/compose/ui/text/AnnotatedString;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "value", "getValue$foundation_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "value$delegate", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "getVisualTransformation$foundation_release", "()Landroidx/compose/ui/text/input/VisualTransformation;", "setVisualTransformation$foundation_release", "(Landroidx/compose/ui/text/input/VisualTransformation;)V", "clearPreviewHighlight", "clearPreviewHighlight$foundation_release", "contextMenuOpenAdjustment", "position", "contextMenuOpenAdjustment-k-4lQ0M", "(J)V", "copy", "cancelSelection", "copy$foundation_release", "createTextFieldValue", "annotatedString", "selection", "Landroidx/compose/ui/text/TextRange;", "createTextFieldValue-FDrldGo", "(Landroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/input/TextFieldValue;", "cursorDragObserver", "cursorDragObserver$foundation_release", "cut", "cut$foundation_release", "deselect", "deselect-_kEHs6E$foundation_release", "enterSelectionMode", "showFloatingToolbar", "enterSelectionMode$foundation_release", "exitSelectionMode", "exitSelectionMode$foundation_release", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getCursorPosition", "density", "Landroidx/compose/ui/unit/Density;", "getCursorPosition-tuRUvjQ$foundation_release", "(Landroidx/compose/ui/unit/Density;)J", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ$foundation_release", "(Z)J", "handleDragObserver", "handleDragObserver$foundation_release", "hideSelectionToolbar", "hideSelectionToolbar$foundation_release", "isTextChanged", "isTextChanged$foundation_release", "paste", "paste$foundation_release", "selectAll", "selectAll$foundation_release", "setDeletionPreviewHighlight", "range", "setDeletionPreviewHighlight-5zc-tL8$foundation_release", "setHandleState", "handleState", "Landroidx/compose/foundation/text/HandleState;", "setSelectionPreviewHighlight", "setSelectionPreviewHighlight-5zc-tL8$foundation_release", "showSelectionToolbar", "showSelectionToolbar$foundation_release", "updateFloatingToolbar", "show", "updateSelection", "currentPosition", "isStartOfSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "isTouchBasedSelection", "updateSelection-8UEBfa8", "(Landroidx/compose/ui/text/input/TextFieldValue;JZZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionManager {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;

    /* renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;
    private Integer dragBeginOffsetInText;
    private long dragBeginPosition;
    private long dragTotalDistance;

    /* renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* renamed from: editable$delegate, reason: from kotlin metadata */
    private final MutableState editable;

    /* renamed from: enabled$delegate, reason: from kotlin metadata */
    private final MutableState enabled;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;
    private final MouseSelectionObserver mouseSelectionObserver;
    private OffsetMapping offsetMapping;
    private TextFieldValue oldValue;
    private Function1<? super TextFieldValue, Unit> onValueChange;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;
    private LegacyTextFieldState state;
    private TextToolbar textToolbar;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;

    /* renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;
    private VisualTransformation visualTransformation;

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldSelectionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        this.offsetMapping = ValidatingOffsetMappingKt.getValidatingEmptyOffsetMappingIdentity();
        this.onValueChange = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$onValueChange$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                invoke2(textFieldValue);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldValue it) {
            }
        };
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
        this.visualTransformation = VisualTransformation.INSTANCE.getNone();
        this.editable = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.enabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.dragBeginPosition = Offset.INSTANCE.m3938getZeroF1C5BW0();
        this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDown-k-4lQ0M */
            public void mo1048onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo1050onStartk4lQ0M(long startPoint) {
                long adjustedStartSelection;
                long j;
                TextLayoutResultProxy layoutResult;
                TextFieldValue newValue;
                TextLayoutResultProxy layoutResult2;
                if (TextFieldSelectionManager.this.getEnabled() && TextFieldSelectionManager.this.getDraggingHandle() == null) {
                    TextFieldSelectionManager.this.setDraggingHandle(Handle.SelectionEnd);
                    TextFieldSelectionManager.this.previousRawDragOffset = -1;
                    TextFieldSelectionManager.this.hideSelectionToolbar$foundation_release();
                    LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                    boolean z = (state == null || (layoutResult2 = state.getLayoutResult()) == null || !layoutResult2.m1082isPositionOnTextk4lQ0M(startPoint)) ? false : true;
                    TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                    if (!z) {
                        LegacyTextFieldState state2 = textFieldSelectionManager.getState();
                        if (state2 != null && (layoutResult = state2.getLayoutResult()) != null) {
                            TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                            int transformedOffset = TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, startPoint, false, 2, null);
                            int offset = textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(transformedOffset);
                            newValue = textFieldSelectionManager2.m1379createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().getText(), TextRangeKt.TextRange(offset, offset));
                            textFieldSelectionManager2.enterSelectionMode$foundation_release(false);
                            HapticFeedback hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack();
                            if (hapticFeedBack != null) {
                                hapticFeedBack.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4892getTextHandleMove5zf0vsI());
                            }
                            textFieldSelectionManager2.getOnValueChange$foundation_release().invoke(newValue);
                        }
                    } else {
                        if (textFieldSelectionManager.getValue$foundation_release().getText().length() == 0) {
                            return;
                        }
                        TextFieldSelectionManager.this.enterSelectionMode$foundation_release(false);
                        adjustedStartSelection = TextFieldSelectionManager.this.m1382updateSelection8UEBfa8(TextFieldValue.m6333copy3r_uNRQ$default(TextFieldSelectionManager.this.getValue$foundation_release(), (AnnotatedString) null, TextRange.INSTANCE.m6097getZerod9O1mEE(), (TextRange) null, 5, (Object) null), startPoint, true, false, SelectionAdjustment.INSTANCE.getWord(), true);
                        TextFieldSelectionManager.this.dragBeginOffsetInText = Integer.valueOf(TextRange.m6092getStartimpl(adjustedStartSelection));
                    }
                    TextFieldSelectionManager.this.setHandleState(HandleState.None);
                    TextFieldSelectionManager.this.dragBeginPosition = startPoint;
                    TextFieldSelectionManager textFieldSelectionManager3 = TextFieldSelectionManager.this;
                    j = TextFieldSelectionManager.this.dragBeginPosition;
                    textFieldSelectionManager3.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(j));
                    TextFieldSelectionManager.this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo1049onDragk4lQ0M(long delta) {
                long j;
                TextLayoutResultProxy layoutResult;
                long j2;
                long j3;
                Integer num;
                TextLayoutResultProxy layoutResult2;
                Integer num2;
                long j4;
                int m1081getOffsetForPosition3MmeM6k;
                Integer num3;
                long m1382updateSelection8UEBfa8;
                long j5;
                SelectionAdjustment adjustment;
                if (TextFieldSelectionManager.this.getEnabled()) {
                    if (TextFieldSelectionManager.this.getValue$foundation_release().getText().length() == 0) {
                        return;
                    }
                    TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                    j = textFieldSelectionManager.dragTotalDistance;
                    textFieldSelectionManager.dragTotalDistance = Offset.m3927plusMKHz9U(j, delta);
                    LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                    if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                        TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                        j2 = textFieldSelectionManager2.dragBeginPosition;
                        j3 = textFieldSelectionManager2.dragTotalDistance;
                        textFieldSelectionManager2.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(Offset.m3927plusMKHz9U(j2, j3)));
                        num = textFieldSelectionManager2.dragBeginOffsetInText;
                        if (num != null) {
                            layoutResult2 = layoutResult;
                        } else {
                            Offset m1385getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E);
                            if (layoutResult.m1082isPositionOnTextk4lQ0M(m1385getCurrentDragPosition_m7T9E.getPackedValue())) {
                                layoutResult2 = layoutResult;
                            } else {
                                OffsetMapping offsetMapping = textFieldSelectionManager2.getOffsetMapping();
                                j5 = textFieldSelectionManager2.dragBeginPosition;
                                int startOffset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, j5, false, 2, null));
                                OffsetMapping offsetMapping2 = textFieldSelectionManager2.getOffsetMapping();
                                Offset m1385getCurrentDragPosition_m7T9E2 = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E2);
                                int endOffset = offsetMapping2.transformedToOriginal(TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, m1385getCurrentDragPosition_m7T9E2.getPackedValue(), false, 2, null));
                                if (startOffset == endOffset) {
                                    adjustment = SelectionAdjustment.INSTANCE.getNone();
                                } else {
                                    adjustment = SelectionAdjustment.INSTANCE.getWord();
                                }
                                TextFieldValue value$foundation_release = textFieldSelectionManager2.getValue$foundation_release();
                                Offset m1385getCurrentDragPosition_m7T9E3 = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E3);
                                m1382updateSelection8UEBfa8 = textFieldSelectionManager2.m1382updateSelection8UEBfa8(value$foundation_release, m1385getCurrentDragPosition_m7T9E3.getPackedValue(), false, false, adjustment, true);
                                TextRange.m6080boximpl(m1382updateSelection8UEBfa8);
                            }
                        }
                        num2 = textFieldSelectionManager2.dragBeginOffsetInText;
                        if (num2 != null) {
                            m1081getOffsetForPosition3MmeM6k = num2.intValue();
                        } else {
                            j4 = textFieldSelectionManager2.dragBeginPosition;
                            m1081getOffsetForPosition3MmeM6k = layoutResult2.m1081getOffsetForPosition3MmeM6k(j4, false);
                        }
                        int startOffset2 = m1081getOffsetForPosition3MmeM6k;
                        Offset m1385getCurrentDragPosition_m7T9E4 = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                        Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E4);
                        int endOffset2 = layoutResult2.m1081getOffsetForPosition3MmeM6k(m1385getCurrentDragPosition_m7T9E4.getPackedValue(), false);
                        num3 = textFieldSelectionManager2.dragBeginOffsetInText;
                        if (num3 == null && startOffset2 == endOffset2) {
                            return;
                        }
                        TextFieldValue value$foundation_release2 = textFieldSelectionManager2.getValue$foundation_release();
                        Offset m1385getCurrentDragPosition_m7T9E5 = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                        Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E5);
                        m1382updateSelection8UEBfa8 = textFieldSelectionManager2.m1382updateSelection8UEBfa8(value$foundation_release2, m1385getCurrentDragPosition_m7T9E5.getPackedValue(), false, false, SelectionAdjustment.INSTANCE.getWord(), true);
                        TextRange.m6080boximpl(m1382updateSelection8UEBfa8);
                    }
                    TextFieldSelectionManager.this.updateFloatingToolbar(false);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                onEnd();
            }

            private final void onEnd() {
                TextFieldSelectionManager.this.setDraggingHandle(null);
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(null);
                TextFieldSelectionManager.this.updateFloatingToolbar(true);
                TextFieldSelectionManager.this.dragBeginOffsetInText = null;
                boolean collapsed = TextRange.m6086getCollapsedimpl(TextFieldSelectionManager.this.getValue$foundation_release().getSelection());
                TextFieldSelectionManager.this.setHandleState(collapsed ? HandleState.Cursor : HandleState.Selection);
                LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null) {
                    state.setShowSelectionHandleStart(!collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(TextFieldSelectionManager.this, true));
                }
                LegacyTextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 != null) {
                    state2.setShowSelectionHandleEnd(!collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(TextFieldSelectionManager.this, false));
                }
                LegacyTextFieldState state3 = TextFieldSelectionManager.this.getState();
                if (state3 != null) {
                    state3.setShowCursorHandle(collapsed && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(TextFieldSelectionManager.this, true));
                }
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtend-k-4lQ0M */
            public boolean mo1259onExtendk4lQ0M(long downPosition) {
                LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                if (state == null || state.getLayoutResult() == null || !TextFieldSelectionManager.this.getEnabled()) {
                    return false;
                }
                TextFieldSelectionManager.this.previousRawDragOffset = -1;
                updateMouseSelection(TextFieldSelectionManager.this.getValue$foundation_release(), downPosition, false, SelectionAdjustment.INSTANCE.getNone());
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo1260onExtendDragk4lQ0M(long dragPosition) {
                LegacyTextFieldState state;
                if (TextFieldSelectionManager.this.getEnabled()) {
                    if (!(TextFieldSelectionManager.this.getValue$foundation_release().getText().length() == 0) && (state = TextFieldSelectionManager.this.getState()) != null && state.getLayoutResult() != null) {
                        updateMouseSelection(TextFieldSelectionManager.this.getValue$foundation_release(), dragPosition, false, SelectionAdjustment.INSTANCE.getNone());
                        return true;
                    }
                    return false;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onStart-3MmeM6k */
            public boolean mo1261onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                LegacyTextFieldState state;
                long j;
                if (TextFieldSelectionManager.this.getEnabled()) {
                    if (!(TextFieldSelectionManager.this.getValue$foundation_release().getText().length() == 0) && (state = TextFieldSelectionManager.this.getState()) != null && state.getLayoutResult() != null) {
                        FocusRequester focusRequester = TextFieldSelectionManager.this.getFocusRequester();
                        if (focusRequester != null) {
                            focusRequester.requestFocus();
                        }
                        TextFieldSelectionManager.this.dragBeginPosition = downPosition;
                        TextFieldSelectionManager.this.previousRawDragOffset = -1;
                        TextFieldSelectionManager.enterSelectionMode$foundation_release$default(TextFieldSelectionManager.this, false, 1, null);
                        TextFieldValue value$foundation_release = TextFieldSelectionManager.this.getValue$foundation_release();
                        j = TextFieldSelectionManager.this.dragBeginPosition;
                        updateMouseSelection(value$foundation_release, j, true, adjustment);
                        return true;
                    }
                    return false;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onDrag-3MmeM6k */
            public boolean mo1258onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LegacyTextFieldState state;
                if (TextFieldSelectionManager.this.getEnabled()) {
                    if (!(TextFieldSelectionManager.this.getValue$foundation_release().getText().length() == 0) && (state = TextFieldSelectionManager.this.getState()) != null && state.getLayoutResult() != null) {
                        updateMouseSelection(TextFieldSelectionManager.this.getValue$foundation_release(), dragPosition, false, adjustment);
                        return true;
                    }
                    return false;
                }
                return false;
            }

            public final void updateMouseSelection(TextFieldValue value, long currentPosition, boolean isStartOfSelection, SelectionAdjustment adjustment) {
                long newSelection;
                newSelection = TextFieldSelectionManager.this.m1382updateSelection8UEBfa8(value, currentPosition, isStartOfSelection, false, adjustment, false);
                TextFieldSelectionManager.this.setHandleState(TextRange.m6086getCollapsedimpl(newSelection) ? HandleState.Cursor : HandleState.Selection);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
            }
        };
    }

    public /* synthetic */ TextFieldSelectionManager(UndoManager undoManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : undoManager);
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* renamed from: getOffsetMapping$foundation_release, reason: from getter */
    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping$foundation_release(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange$foundation_release() {
        return this.onValueChange;
    }

    public final void setOnValueChange$foundation_release(Function1<? super TextFieldValue, Unit> function1) {
        this.onValueChange = function1;
    }

    /* renamed from: getState$foundation_release, reason: from getter */
    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final void setState$foundation_release(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final TextFieldValue getValue$foundation_release() {
        State $this$getValue$iv = this.value;
        return (TextFieldValue) $this$getValue$iv.getValue();
    }

    public final void setValue$foundation_release(TextFieldValue textFieldValue) {
        MutableState $this$setValue$iv = this.value;
        $this$setValue$iv.setValue(textFieldValue);
    }

    public final AnnotatedString getTransformedText$foundation_release() {
        TextDelegate textDelegate;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (textDelegate = legacyTextFieldState.getTextDelegate()) == null) {
            return null;
        }
        return textDelegate.getText();
    }

    /* renamed from: getVisualTransformation$foundation_release, reason: from getter */
    public final VisualTransformation getVisualTransformation() {
        return this.visualTransformation;
    }

    public final void setVisualTransformation$foundation_release(VisualTransformation visualTransformation) {
        this.visualTransformation = visualTransformation;
    }

    /* renamed from: getClipboardManager$foundation_release, reason: from getter */
    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final void setClipboardManager$foundation_release(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final boolean getEditable() {
        State $this$getValue$iv = this.editable;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEditable(boolean z) {
        MutableState $this$setValue$iv = this.editable;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getEnabled() {
        State $this$getValue$iv = this.enabled;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEnabled(boolean z) {
        MutableState $this$setValue$iv = this.enabled;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m1381setCurrentDragPosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.currentDragPosition;
        $this$setValue$iv.setValue(offset);
    }

    /* renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m1385getCurrentDragPosition_m7T9E() {
        State $this$getValue$iv = this.currentDragPosition;
        return (Offset) $this$getValue$iv.getValue();
    }

    /* renamed from: getTouchSelectionObserver$foundation_release, reason: from getter */
    public final TextDragObserver getTouchSelectionObserver() {
        return this.touchSelectionObserver;
    }

    /* renamed from: getMouseSelectionObserver$foundation_release, reason: from getter */
    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    public final TextDragObserver handleDragObserver$foundation_release(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDown-k-4lQ0M */
            public void mo1048onDownk4lQ0M(long point) {
                TextLayoutResultProxy layoutResult;
                TextFieldSelectionManager.this.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                long handleCoordinates = SelectionHandlesKt.m1328getAdjustedCoordinatesk4lQ0M(TextFieldSelectionManager.this.m1387getHandlePositiontuRUvjQ$foundation_release(isStartHandle));
                LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1084translateInnerToDecorationCoordinatesMKHz9U$foundation_release(handleCoordinates);
                TextFieldSelectionManager.this.dragBeginPosition = translatedPosition;
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(translatedPosition));
                TextFieldSelectionManager.this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
                TextFieldSelectionManager.this.previousRawDragOffset = -1;
                LegacyTextFieldState state2 = TextFieldSelectionManager.this.getState();
                if (state2 != null) {
                    state2.setInTouchMode(true);
                }
                TextFieldSelectionManager.this.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                TextFieldSelectionManager.this.setDraggingHandle(null);
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(null);
                TextFieldSelectionManager.this.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo1050onStartk4lQ0M(long startPoint) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo1049onDragk4lQ0M(long delta) {
                long j;
                long j2;
                long j3;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                j = textFieldSelectionManager.dragTotalDistance;
                textFieldSelectionManager.dragTotalDistance = Offset.m3927plusMKHz9U(j, delta);
                TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                j2 = TextFieldSelectionManager.this.dragBeginPosition;
                j3 = TextFieldSelectionManager.this.dragTotalDistance;
                textFieldSelectionManager2.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(Offset.m3927plusMKHz9U(j2, j3)));
                TextFieldSelectionManager textFieldSelectionManager3 = TextFieldSelectionManager.this;
                TextFieldValue value$foundation_release = TextFieldSelectionManager.this.getValue$foundation_release();
                Offset m1385getCurrentDragPosition_m7T9E = TextFieldSelectionManager.this.m1385getCurrentDragPosition_m7T9E();
                Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E);
                textFieldSelectionManager3.m1382updateSelection8UEBfa8(value$foundation_release, m1385getCurrentDragPosition_m7T9E.getPackedValue(), false, isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true);
                TextFieldSelectionManager.this.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                TextFieldSelectionManager.this.setDraggingHandle(null);
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(null);
                TextFieldSelectionManager.this.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public final TextDragObserver cursorDragObserver$foundation_release() {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDown-k-4lQ0M */
            public void mo1048onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                TextFieldSelectionManager.this.setDraggingHandle(null);
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo1050onStartk4lQ0M(long startPoint) {
                TextLayoutResultProxy layoutResult;
                long handleCoordinates = SelectionHandlesKt.m1328getAdjustedCoordinatesk4lQ0M(TextFieldSelectionManager.this.m1387getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1084translateInnerToDecorationCoordinatesMKHz9U$foundation_release(handleCoordinates);
                TextFieldSelectionManager.this.dragBeginPosition = translatedPosition;
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(translatedPosition));
                TextFieldSelectionManager.this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
                TextFieldSelectionManager.this.setDraggingHandle(Handle.Cursor);
                TextFieldSelectionManager.this.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo1049onDragk4lQ0M(long delta) {
                long j;
                TextLayoutResultProxy layoutResult;
                long j2;
                long j3;
                TextFieldValue m1379createTextFieldValueFDrldGo;
                HapticFeedback hapticFeedBack;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                j = textFieldSelectionManager.dragTotalDistance;
                textFieldSelectionManager.dragTotalDistance = Offset.m3927plusMKHz9U(j, delta);
                LegacyTextFieldState state = TextFieldSelectionManager.this.getState();
                if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                    j2 = textFieldSelectionManager2.dragBeginPosition;
                    j3 = textFieldSelectionManager2.dragTotalDistance;
                    textFieldSelectionManager2.m1381setCurrentDragPosition_kEHs6E(Offset.m3911boximpl(Offset.m3927plusMKHz9U(j2, j3)));
                    OffsetMapping offsetMapping = textFieldSelectionManager2.getOffsetMapping();
                    Offset m1385getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m1385getCurrentDragPosition_m7T9E();
                    Intrinsics.checkNotNull(m1385getCurrentDragPosition_m7T9E);
                    int offset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, m1385getCurrentDragPosition_m7T9E.getPackedValue(), false, 2, null));
                    long newSelection = TextRangeKt.TextRange(offset, offset);
                    if (TextRange.m6085equalsimpl0(newSelection, textFieldSelectionManager2.getValue$foundation_release().getSelection())) {
                        return;
                    }
                    LegacyTextFieldState state2 = textFieldSelectionManager2.getState();
                    boolean z = false;
                    if (state2 != null && !state2.isInTouchMode()) {
                        z = true;
                    }
                    if (!z && (hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack()) != null) {
                        hapticFeedBack.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4892getTextHandleMove5zf0vsI());
                    }
                    Function1<TextFieldValue, Unit> onValueChange$foundation_release = textFieldSelectionManager2.getOnValueChange$foundation_release();
                    m1379createTextFieldValueFDrldGo = textFieldSelectionManager2.m1379createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().getText(), newSelection);
                    onValueChange$foundation_release.invoke(m1379createTextFieldValueFDrldGo);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                TextFieldSelectionManager.this.setDraggingHandle(null);
                TextFieldSelectionManager.this.m1381setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public static /* synthetic */ void enterSelectionMode$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.enterSelectionMode$foundation_release(z);
    }

    public final void enterSelectionMode$foundation_release(boolean showFloatingToolbar) {
        FocusRequester focusRequester;
        LegacyTextFieldState legacyTextFieldState = this.state;
        boolean z = false;
        if (legacyTextFieldState != null && !legacyTextFieldState.getHasFocus()) {
            z = true;
        }
        if (z && (focusRequester = this.focusRequester) != null) {
            focusRequester.requestFocus();
        }
        this.oldValue = getValue$foundation_release();
        updateFloatingToolbar(showFloatingToolbar);
        setHandleState(HandleState.Selection);
    }

    public final void exitSelectionMode$foundation_release() {
        updateFloatingToolbar(false);
        setHandleState(HandleState.None);
    }

    /* renamed from: deselect-_kEHs6E$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ void m1380deselect_kEHs6E$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m1384deselect_kEHs6E$foundation_release(offset);
    }

    /* renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m1384deselect_kEHs6E$foundation_release(Offset position) {
        HandleState selectionMode;
        int newCursorOffset;
        if (!TextRange.m6086getCollapsedimpl(getValue$foundation_release().getSelection())) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
            if (position != null && layoutResult != null) {
                newCursorOffset = this.offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, position.getPackedValue(), false, 2, null));
            } else {
                newCursorOffset = TextRange.m6089getMaximpl(getValue$foundation_release().getSelection());
            }
            TextFieldValue newValue = TextFieldValue.m6333copy3r_uNRQ$default(getValue$foundation_release(), (AnnotatedString) null, TextRangeKt.TextRange(newCursorOffset), (TextRange) null, 5, (Object) null);
            this.onValueChange.invoke(newValue);
        }
        if (position != null) {
            if (getValue$foundation_release().getText().length() > 0) {
                selectionMode = HandleState.Cursor;
                setHandleState(selectionMode);
                updateFloatingToolbar(false);
            }
        }
        selectionMode = HandleState.None;
        setHandleState(selectionMode);
        updateFloatingToolbar(false);
    }

    /* renamed from: setSelectionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m1389setSelectionPreviewHighlight5zctL8$foundation_release(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1011setSelectionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1008setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6097getZerod9O1mEE());
        }
        if (!TextRange.m6086getCollapsedimpl(range)) {
            exitSelectionMode$foundation_release();
        }
    }

    /* renamed from: setDeletionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m1388setDeletionPreviewHighlight5zctL8$foundation_release(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1008setDeletionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1011setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6097getZerod9O1mEE());
        }
        if (!TextRange.m6086getCollapsedimpl(range)) {
            exitSelectionMode$foundation_release();
        }
    }

    public final void clearPreviewHighlight$foundation_release() {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1008setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6097getZerod9O1mEE());
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 == null) {
            return;
        }
        legacyTextFieldState2.m1011setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m6097getZerod9O1mEE());
    }

    public static /* synthetic */ void copy$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.copy$foundation_release(z);
    }

    public final void copy$foundation_release(boolean cancelSelection) {
        if (TextRange.m6086getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        if (cancelSelection) {
            int newCursorOffset = TextRange.m6089getMaximpl(getValue$foundation_release().getSelection());
            TextFieldValue newValue = m1379createTextFieldValueFDrldGo(getValue$foundation_release().getText(), TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
            this.onValueChange.invoke(newValue);
            setHandleState(HandleState.None);
        }
    }

    public final void paste$foundation_release() {
        AnnotatedString text;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null) {
            return;
        }
        AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(text).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int newCursorOffset = TextRange.m6090getMinimpl(getValue$foundation_release().getSelection()) + text.length();
        TextFieldValue newValue = m1379createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void cut$foundation_release() {
        if (TextRange.m6086getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int newCursorOffset = TextRange.m6090getMinimpl(getValue$foundation_release().getSelection());
        TextFieldValue newValue = m1379createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void selectAll$foundation_release() {
        TextFieldValue newValue = m1379createTextFieldValueFDrldGo(getValue$foundation_release().getText(), TextRangeKt.TextRange(0, getValue$foundation_release().getText().length()));
        this.onValueChange.invoke(newValue);
        this.oldValue = TextFieldValue.m6333copy3r_uNRQ$default(this.oldValue, (AnnotatedString) null, newValue.getSelection(), (TextRange) null, 5, (Object) null);
        enterSelectionMode$foundation_release(true);
    }

    /* renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1387getHandlePositiontuRUvjQ$foundation_release(boolean isStartHandle) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult = layoutResult.getValue()) == null) {
            return Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        }
        AnnotatedString transformedText = getTransformedText$foundation_release();
        if (transformedText == null) {
            return Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        }
        String layoutInputText = textLayoutResult.getLayoutInput().getText().getText();
        if (!Intrinsics.areEqual(transformedText.getText(), layoutInputText)) {
            return Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        }
        long selection = getValue$foundation_release().getSelection();
        int offset = isStartHandle ? TextRange.m6092getStartimpl(selection) : TextRange.m6087getEndimpl(selection);
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(textLayoutResult, this.offsetMapping.originalToTransformed(offset), isStartHandle, TextRange.m6091getReversedimpl(getValue$foundation_release().getSelection()));
    }

    /* renamed from: getCursorPosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1386getCursorPositiontuRUvjQ$foundation_release(Density density) {
        int offset = this.offsetMapping.originalToTransformed(TextRange.m6092getStartimpl(getValue$foundation_release().getSelection()));
        LegacyTextFieldState legacyTextFieldState = this.state;
        TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
        Intrinsics.checkNotNull(layoutResult);
        TextLayoutResult layoutResult2 = layoutResult.getValue();
        Rect cursorRect = layoutResult2.getCursorRect(RangesKt.coerceIn(offset, 0, layoutResult2.getLayoutInput().getText().length()));
        float x = cursorRect.getLeft() + (density.mo364toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness()) / 2.0f);
        return OffsetKt.Offset(x, cursorRect.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingToolbar(boolean show) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.setShowFloatingToolbar(show);
        }
        if (show) {
            showSelectionToolbar$foundation_release();
        } else {
            hideSelectionToolbar$foundation_release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showSelectionToolbar$foundation_release() {
        /*
            r11 = this;
            boolean r0 = r11.getEnabled()
            if (r0 == 0) goto Lae
            androidx.compose.foundation.text.LegacyTextFieldState r0 = r11.state
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L14
            boolean r0 = r0.isInTouchMode()
            if (r0 != 0) goto L14
            r0 = r1
            goto L15
        L14:
            r0 = r2
        L15:
            if (r0 == 0) goto L19
            goto Lae
        L19:
            androidx.compose.ui.text.input.VisualTransformation r0 = r11.visualTransformation
            boolean r0 = r0 instanceof androidx.compose.ui.text.input.PasswordVisualTransformation
            androidx.compose.ui.text.input.TextFieldValue r3 = r11.getValue$foundation_release()
            long r3 = r3.getSelection()
            boolean r3 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r3)
            r4 = 0
            if (r3 != 0) goto L36
            if (r0 != 0) goto L36
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1 r3 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1
            r3.<init>()
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            goto L37
        L36:
            r3 = r4
        L37:
            r7 = r3
            androidx.compose.ui.text.input.TextFieldValue r3 = r11.getValue$foundation_release()
            long r5 = r3.getSelection()
            boolean r3 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r5)
            if (r3 != 0) goto L57
            boolean r3 = r11.getEditable()
            if (r3 == 0) goto L57
            if (r0 != 0) goto L57
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1 r3 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1
            r3.<init>()
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            r9 = r3
            goto L58
        L57:
            r9 = r4
        L58:
            boolean r3 = r11.getEditable()
            if (r3 == 0) goto L76
            androidx.compose.ui.platform.ClipboardManager r3 = r11.clipboardManager
            if (r3 == 0) goto L6a
            boolean r3 = r3.hasText()
            if (r3 != r1) goto L6a
            goto L6b
        L6a:
            r1 = r2
        L6b:
            if (r1 == 0) goto L76
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1 r1 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1
            r1.<init>()
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r8 = r1
            goto L77
        L76:
            r8 = r4
        L77:
            androidx.compose.ui.text.input.TextFieldValue r1 = r11.getValue$foundation_release()
            long r1 = r1.getSelection()
            int r1 = androidx.compose.ui.text.TextRange.m6088getLengthimpl(r1)
            androidx.compose.ui.text.input.TextFieldValue r2 = r11.getValue$foundation_release()
            java.lang.String r2 = r2.getText()
            int r2 = r2.length()
            if (r1 == r2) goto L9c
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1 r1 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1
            r1.<init>()
            r4 = r1
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r10 = r4
            goto L9d
        L9c:
            r10 = r4
        L9d:
            androidx.compose.ui.platform.TextToolbar r5 = r11.textToolbar
            if (r5 == 0) goto Lad
            androidx.compose.ui.geometry.Rect r6 = r11.getContentRect()
            r5.showMenu(r6, r7, r8, r9, r10)
        Lad:
            return
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.showSelectionToolbar$foundation_release():void");
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* renamed from: contextMenuOpenAdjustment-k-4lQ0M, reason: not valid java name */
    public final void m1383contextMenuOpenAdjustmentk4lQ0M(long position) {
        TextLayoutResultProxy layoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null && (layoutResult = legacyTextFieldState.getLayoutResult()) != null) {
            int offset = TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(layoutResult, position, false, 2, null);
            if (!TextRange.m6083containsimpl(getValue$foundation_release().getSelection(), offset)) {
                this.previousRawDragOffset = -1;
                m1382updateSelection8UEBfa8(getValue$foundation_release(), position, true, false, SelectionAdjustment.INSTANCE.getWord(), false);
            }
        }
    }

    public final boolean isTextChanged$foundation_release() {
        return !Intrinsics.areEqual(this.oldValue.getText(), getValue$foundation_release().getText());
    }

    private final Rect getContentRect() {
        float startTop;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResult value;
        Rect cursorRect;
        LayoutCoordinates layoutCoordinates2;
        TextLayoutResult value2;
        Rect cursorRect2;
        LayoutCoordinates layoutCoordinates3;
        LayoutCoordinates layoutCoordinates4;
        LegacyTextFieldState it = this.state;
        if (it != null) {
            if (it.getIsLayoutResultStale()) {
                it = null;
            }
            if (it != null) {
                int transformedStart = this.offsetMapping.originalToTransformed(TextRange.m6092getStartimpl(getValue$foundation_release().getSelection()));
                int transformedEnd = this.offsetMapping.originalToTransformed(TextRange.m6087getEndimpl(getValue$foundation_release().getSelection()));
                LegacyTextFieldState legacyTextFieldState = this.state;
                long startOffset = (legacyTextFieldState == null || (layoutCoordinates4 = legacyTextFieldState.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m3938getZeroF1C5BW0() : layoutCoordinates4.mo5502localToRootMKHz9U(m1387getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState legacyTextFieldState2 = this.state;
                long endOffset = (legacyTextFieldState2 == null || (layoutCoordinates3 = legacyTextFieldState2.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m3938getZeroF1C5BW0() : layoutCoordinates3.mo5502localToRootMKHz9U(m1387getHandlePositiontuRUvjQ$foundation_release(false));
                LegacyTextFieldState legacyTextFieldState3 = this.state;
                float endTop = 0.0f;
                if (legacyTextFieldState3 == null || (layoutCoordinates2 = legacyTextFieldState3.getLayoutCoordinates()) == null) {
                    startTop = 0.0f;
                } else {
                    TextLayoutResultProxy layoutResult = it.getLayoutResult();
                    startTop = Offset.m3923getYimpl(layoutCoordinates2.mo5502localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (value2 = layoutResult.getValue()) == null || (cursorRect2 = value2.getCursorRect(transformedStart)) == null) ? 0.0f : cursorRect2.getTop())));
                }
                LegacyTextFieldState legacyTextFieldState4 = this.state;
                if (legacyTextFieldState4 != null && (layoutCoordinates = legacyTextFieldState4.getLayoutCoordinates()) != null) {
                    TextLayoutResultProxy layoutResult2 = it.getLayoutResult();
                    endTop = Offset.m3923getYimpl(layoutCoordinates.mo5502localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (value = layoutResult2.getValue()) == null || (cursorRect = value.getCursorRect(transformedEnd)) == null) ? 0.0f : cursorRect.getTop())));
                }
                float left = Math.min(Offset.m3922getXimpl(startOffset), Offset.m3922getXimpl(endOffset));
                float right = Math.max(Offset.m3922getXimpl(startOffset), Offset.m3922getXimpl(endOffset));
                float top = Math.min(startTop, endTop);
                float bottom = Math.max(Offset.m3923getYimpl(startOffset), Offset.m3923getYimpl(endOffset)) + (Dp.m6626constructorimpl(25) * it.getTextDelegate().getDensity().getDensity());
                return new Rect(left, top, right, bottom);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x019b, code lost:
    
        if (androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(r25, true) != false) goto L95;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0190  */
    /* renamed from: updateSelection-8UEBfa8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long m1382updateSelection8UEBfa8(androidx.compose.ui.text.input.TextFieldValue r26, long r27, boolean r29, boolean r30, androidx.compose.foundation.text.selection.SelectionAdjustment r31, boolean r32) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.m1382updateSelection8UEBfa8(androidx.compose.ui.text.input.TextFieldValue, long, boolean, boolean, androidx.compose.foundation.text.selection.SelectionAdjustment, boolean):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHandleState(HandleState handleState) {
        LegacyTextFieldState it = this.state;
        if (it != null) {
            if (it.getHandleState() == handleState) {
                it = null;
            }
            if (it != null) {
                it.setHandleState(handleState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m1379createTextFieldValueFDrldGo(AnnotatedString annotatedString, long selection) {
        return new TextFieldValue(annotatedString, selection, (TextRange) null, 4, (DefaultConstructorMarker) null);
    }
}
