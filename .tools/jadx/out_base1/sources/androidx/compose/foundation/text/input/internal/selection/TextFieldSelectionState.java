package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.MathUtilsKt;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: TextFieldSelectionState.kt */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001:\u0006«\u0001¬\u0001\u00ad\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u0006\u0010W\u001a\u00020\tJ\u0006\u0010X\u001a\u00020\tJ\u0006\u0010Y\u001a\u00020\tJ\u0006\u0010Z\u001a\u00020\tJ\u0006\u0010[\u001a\u00020\\J\u0010\u0010]\u001a\u00020\\2\b\b\u0002\u0010^\u001a\u00020\tJ\u0006\u0010_\u001a\u00020\\J\u0006\u0010`\u001a\u00020\\J\u0006\u0010a\u001a\u00020\\J\b\u0010b\u001a\u00020cH\u0002J\u0015\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020\tH\u0000¢\u0006\u0002\bgJ\u0006\u0010h\u001a\u00020cJ\u001d\u0010i\u001a\u00020\u00112\u0006\u0010j\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010lJ\u001d\u0010m\u001a\u00020e2\u0006\u0010j\u001a\u00020\t2\u0006\u0010f\u001a\u00020\tH\u0000¢\u0006\u0002\bnJ<\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u0002032\u0006\u0010r\u001a\u0002032\b\u0010s\u001a\u0004\u0018\u00010p2\u0006\u0010j\u001a\u00020\t2\u0006\u0010t\u001a\u00020uH\u0002ø\u0001\u0000¢\u0006\u0004\bv\u0010wJ\b\u0010x\u001a\u00020\\H\u0002J\b\u0010y\u001a\u00020\tH\u0002J\b\u0010z\u001a\u00020\\H\u0002J1\u0010{\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010<2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010|\u001a\u00020P2\u000e\b\u0004\u0010}\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0082\bJ\u000e\u0010~\u001a\u00020\\H\u0086@¢\u0006\u0002\u0010\u007fJ\u000f\u0010\u0080\u0001\u001a\u00020\\H\u0082@¢\u0006\u0002\u0010\u007fJ\u000f\u0010\u0081\u0001\u001a\u00020\\H\u0082@¢\u0006\u0002\u0010\u007fJ\u0007\u0010\u0082\u0001\u001a\u00020\\J\t\u0010\u0083\u0001\u001a\u00020\\H\u0002J\u001e\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0007\u0010\u0088\u0001\u001a\u00020\\J\u0012\u0010\u0089\u0001\u001a\u00020\\2\u0007\u0010\u008a\u0001\u001a\u00020cH\u0002J?\u0010\u008b\u0001\u001a\u00020\\2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020O2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ%\u0010\u008c\u0001\u001a\u00020\\2\u0007\u0010\u008d\u0001\u001a\u00020\u001d2\u0007\u0010\u008e\u0001\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001JZ\u0010\u0091\u0001\u001a\u00020p2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0007\u0010\u0094\u0001\u001a\u0002032\u0007\u0010\u0095\u0001\u001a\u0002032\u0006\u0010j\u001a\u00020\t2\u0006\u0010t\u001a\u00020u2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\t2\t\b\u0002\u0010\u0097\u0001\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u000f\u0010\u009a\u0001\u001a\u00020\\2\u0006\u0010Q\u001a\u00020PJ\u0015\u0010\u009b\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0086@¢\u0006\u0003\u0010\u009d\u0001J\u0015\u0010\u009e\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0082@¢\u0006\u0003\u0010\u009d\u0001J\u001d\u0010\u009f\u0001\u001a\u00020\\*\u00030\u009c\u00012\u0006\u0010j\u001a\u00020\tH\u0082@¢\u0006\u0003\u0010 \u0001J?\u0010¡\u0001\u001a\u00020\\*\u00030\u009c\u00012\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u00012\r\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<2\r\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0086@¢\u0006\u0003\u0010¦\u0001J\u0015\u0010§\u0001\u001a\u00020\\*\u00030\u009c\u0001H\u0086@¢\u0006\u0003\u0010\u009d\u0001J\u001d\u0010¨\u0001\u001a\u00020\\*\u00030\u009c\u00012\u0006\u0010j\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010 \u0001J$\u0010©\u0001\u001a\u00020\\*\u00030\u009c\u00012\r\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020\\0<H\u0086@¢\u0006\u0003\u0010ª\u0001R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001c\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\u00020\u00118Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b(\u0010\u0013R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010&\"\u0004\b+\u0010,R+\u0010-\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u001c\u001a\u0004\b-\u0010&\"\u0004\b.\u0010,R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R1\u00106\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b:\u0010\u001c\u001a\u0004\b7\u0010\u0013\"\u0004\b8\u00109R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010;\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010=\u0018\u00010<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR+\u0010B\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u001c\u001a\u0004\bC\u0010&\"\u0004\bD\u0010,R1\u0010F\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bI\u0010\u001c\u001a\u0004\bG\u0010\u0013\"\u0004\bH\u00109R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010J\u001a\u0004\u0018\u00010K8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010Q\u001a\u00020P2\u0006\u0010\u0014\u001a\u00020P8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010\u001c\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006®\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "isPassword", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZZ)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "currentTextLayoutPositionInWindow", "Landroidx/compose/ui/geometry/Offset;", "getCurrentTextLayoutPositionInWindow-F1C5BW0", "()J", "<set-?>", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "directDragGestureInitiator", "getDirectDragGestureInitiator", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "setDirectDragGestureInitiator", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;)V", "directDragGestureInitiator$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "editable", "getEditable", "()Z", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setFocused", "(Z)V", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "previousRawDragOffset", "", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "(J)V", "rawHandleDragPosition$delegate", "receiveContentConfiguration", "Lkotlin/Function0;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getReceiveContentConfiguration", "()Lkotlin/jvm/functions/Function0;", "setReceiveContentConfiguration", "(Lkotlin/jvm/functions/Function0;)V", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "startTextLayoutPositionInWindow", "getStartTextLayoutPositionInWindow-F1C5BW0", "setStartTextLayoutPositionInWindow-k-4lQ0M", "startTextLayoutPositionInWindow$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "canCopy", "canCut", "canPaste", "canSelectAll", "clearHandleDragging", "", "copy", "cancelSelection", "cut", "deselect", "dispose", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getCursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "includePosition", "getCursorHandleState$foundation_release", "getCursorRect", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ", "(Z)J", "getSelectionHandleState", "getSelectionHandleState$foundation_release", "getTextFieldSelection", "Landroidx/compose/ui/text/TextRange;", "rawStartOffset", "rawEndOffset", "previousSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "hideTextToolbar", "isCursorHandleInVisibleBounds", "markStartContentVisibleOffset", "menuItem", "desiredState", "operation", "observeChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTextChanges", "observeTextToolbarVisibility", "paste", "pasteAsPlainText", "placeCursorAtNearestOffset", "offset", "placeCursorAtNearestOffset-k-4lQ0M", "(J)Z", "selectAll", "showTextToolbar", "contentRect", "update", "updateHandleDragging", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "updateSelection", "textFieldCharSequence", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "startOffset", "endOffset", "allowPreviousSelectionCollapsed", "isStartOfSelection", "updateSelection-SsL-Rf8", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZZ)J", "updateTextToolbarState", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectCursorHandleDragGestures", "detectSelectionHandleDragGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTextFieldTapGestures", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTouchMode", "selectionHandleGestures", "textFieldSelectionGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "InputType", "TextFieldMouseSelectionObserver", "TextFieldTextDragObserver", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;
    private Density density;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;
    private boolean isPassword;
    private PressInteraction.Press pressInteraction;
    private SelectionLayout previousSelectionLayout;
    private boolean readOnly;
    private Function0<? extends ReceiveContentConfiguration> receiveContentConfiguration;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbar textToolbar;

    /* renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* renamed from: startTextLayoutPositionInWindow$delegate, reason: from kotlin metadata */
    private final MutableState startTextLayoutPositionInWindow = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3911boximpl(Offset.INSTANCE.m3937getUnspecifiedF1C5BW0()), null, 2, null);

    /* renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3911boximpl(Offset.INSTANCE.m3937getUnspecifiedF1C5BW0()), null, 2, null);

    /* renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* renamed from: directDragGestureInitiator$delegate, reason: from kotlin metadata */
    private final MutableState directDragGestureInitiator = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputType.None, null, 2, null);

    /* renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);
    private int previousRawDragOffset = -1;

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "", "(Ljava/lang/String;I)V", "None", "Touch", "Mouse", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum InputType {
        None,
        Touch,
        Mouse
    }

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, Density density, boolean enabled, boolean readOnly, boolean isFocused, boolean isPassword) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isFocused = isFocused;
        this.isPassword = isPassword;
    }

    /* renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    public final boolean isInTouchMode() {
        State $this$getValue$iv = this.isInTouchMode;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setInTouchMode(boolean z) {
        MutableState $this$setValue$iv = this.isInTouchMode;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final Function0<ReceiveContentConfiguration> getReceiveContentConfiguration() {
        return this.receiveContentConfiguration;
    }

    public final void setReceiveContentConfiguration(Function0<? extends ReceiveContentConfiguration> function0) {
        this.receiveContentConfiguration = function0;
    }

    /* renamed from: getStartTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1248getStartTextLayoutPositionInWindowF1C5BW0() {
        State $this$getValue$iv = this.startTextLayoutPositionInWindow;
        return ((Offset) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* renamed from: setStartTextLayoutPositionInWindow-k-4lQ0M, reason: not valid java name */
    private final void m1252setStartTextLayoutPositionInWindowk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.startTextLayoutPositionInWindow;
        $this$setValue$iv.setValue(Offset.m3911boximpl(j));
    }

    /* renamed from: getCurrentTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1245getCurrentTextLayoutPositionInWindowF1C5BW0() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return textLayoutCoordinates != null ? LayoutCoordinatesKt.positionInWindow(textLayoutCoordinates) : Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
    }

    /* renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1247getRawHandleDragPositionF1C5BW0() {
        State $this$getValue$iv = this.rawHandleDragPosition;
        return ((Offset) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1251setRawHandleDragPositionk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.rawHandleDragPosition;
        $this$setValue$iv.setValue(Offset.m3911boximpl(j));
    }

    /* renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1255getHandleDragPositionF1C5BW0() {
        if (OffsetKt.m3943isUnspecifiedk4lQ0M(m1247getRawHandleDragPositionF1C5BW0())) {
            return Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        }
        if (OffsetKt.m3943isUnspecifiedk4lQ0M(m1248getStartTextLayoutPositionInWindowF1C5BW0())) {
            return TextLayoutStateKt.m1207fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1247getRawHandleDragPositionF1C5BW0());
        }
        return Offset.m3927plusMKHz9U(m1247getRawHandleDragPositionF1C5BW0(), Offset.m3926minusMKHz9U(m1248getStartTextLayoutPositionInWindowF1C5BW0(), m1245getCurrentTextLayoutPositionInWindowF1C5BW0()));
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final InputType getDirectDragGestureInitiator() {
        State $this$getValue$iv = this.directDragGestureInitiator;
        return (InputType) $this$getValue$iv.getValue();
    }

    public final void setDirectDragGestureInitiator(InputType inputType) {
        MutableState $this$setValue$iv = this.directDragGestureInitiator;
        $this$setValue$iv.setValue(inputType);
    }

    private final boolean getShowCursorHandle() {
        State $this$getValue$iv = this.showCursorHandle;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowCursorHandle(boolean z) {
        MutableState $this$setValue$iv = this.showCursorHandle;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextToolbarState getTextToolbarState() {
        State $this$getValue$iv = this.textToolbarState;
        return (TextToolbarState) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        MutableState $this$setValue$iv = this.textToolbarState;
        $this$setValue$iv.setValue(textToolbarState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates it = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (it == null || !it.isAttached()) {
            return null;
        }
        return it;
    }

    private final boolean getEditable() {
        return this.enabled && !this.readOnly;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (isCursorHandleInVisibleBounds() != false) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState getCursorHandleState$foundation_release(boolean r14) {
        /*
            r13 = this;
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r0 = r13.textFieldState
            androidx.compose.foundation.text.input.TextFieldCharSequence r0 = r0.getVisualText()
            boolean r1 = r13.getShowCursorHandle()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r2 = r13.getDirectDragGestureInitiator()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.InputType.None
            r4 = 1
            r5 = 0
            if (r2 != r3) goto L16
            r2 = r4
            goto L17
        L16:
            r2 = r5
        L17:
            androidx.compose.foundation.text.Handle r3 = r13.getDraggingHandle()
            if (r1 == 0) goto L48
            if (r2 == 0) goto L48
            long r6 = r0.getSelection()
            boolean r6 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r6)
            if (r6 == 0) goto L48
            boolean r6 = r0.shouldShowSelection()
            if (r6 == 0) goto L48
            r6 = r0
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 <= 0) goto L3a
            r6 = r4
            goto L3b
        L3a:
            r6 = r5
        L3b:
            if (r6 == 0) goto L48
            androidx.compose.foundation.text.Handle r6 = androidx.compose.foundation.text.Handle.Cursor
            if (r3 == r6) goto L49
            boolean r6 = r13.isCursorHandleInVisibleBounds()
            if (r6 == 0) goto L48
            goto L49
        L48:
            r4 = r5
        L49:
            if (r4 != 0) goto L53
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r5 = r5.getHidden()
            return r5
        L53:
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r6 = new androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState
            if (r14 == 0) goto L61
            androidx.compose.ui.geometry.Rect r5 = r13.getCursorRect()
            long r7 = r5.m3949getBottomCenterF1C5BW0()
            goto L67
        L61:
            androidx.compose.ui.geometry.Offset$Companion r5 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r7 = r5.m3937getUnspecifiedF1C5BW0()
        L67:
            r8 = r7
            androidx.compose.ui.text.style.ResolvedTextDirection r10 = androidx.compose.ui.text.style.ResolvedTextDirection.Ltr
            r7 = 1
            r11 = 0
            r12 = 0
            r6.<init>(r7, r8, r10, r11, r12)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.getCursorHandleState$foundation_release(boolean):androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState");
    }

    private final boolean isCursorHandleInVisibleBounds() {
        Rect visibleBounds;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1 observer$iv = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            long position = getCursorRect().m3949getBottomCenterF1C5BW0();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (textLayoutCoordinates == null || (visibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) {
                return false;
            }
            return SelectionManagerKt.m1365containsInclusiveUv8p0NA(visibleBounds, position);
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
            throw th;
        }
    }

    public final Rect getCursorRect() {
        float cursorCenterX;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        if (!TextRange.m6086getCollapsedimpl(value.getSelection())) {
            return Rect.INSTANCE.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.m6092getStartimpl(value.getSelection()));
        Density $this$getCursorRect_u24lambda_u242 = this.density;
        float cursorWidth = $this$getCursorRect_u24lambda_u242.mo364toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
            cursorCenterX = cursorRect.getLeft() + (cursorWidth / 2.0f);
        } else {
            cursorCenterX = cursorRect.getRight() - (cursorWidth / 2.0f);
        }
        float coercedCursorCenterX = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(cursorCenterX, IntSize.m6796getWidthimpl(layoutResult.getSize()) - (cursorWidth / 2.0f)), cursorWidth / 2.0f);
        return new Rect(coercedCursorCenterX - (cursorWidth / 2.0f), cursorRect.getTop(), (cursorWidth / 2.0f) + coercedCursorCenterX, cursorRect.getBottom());
    }

    public final void update(HapticFeedback hapticFeedBack, ClipboardManager clipboardManager, TextToolbar textToolbar, Density density, boolean enabled, boolean readOnly, boolean isPassword) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboardManager = clipboardManager;
        this.textToolbar = textToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isPassword = isPassword;
    }

    public final Object cursorHandleGestures(PointerInputScope $this$cursorHandleGestures, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new TextFieldSelectionState$cursorHandleGestures$2(this, $this$cursorHandleGestures, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public final Object selectionHandleGestures(PointerInputScope $this$selectionHandleGestures, boolean isStartHandle, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new TextFieldSelectionState$selectionHandleGestures$2(this, $this$selectionHandleGestures, isStartHandle, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeChanges(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$1
            r0.<init>(r7, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2e:
            java.lang.Object r1 = r0.L$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L36
            goto L52
        L36:
            r2 = move-exception
            goto L68
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2 r4 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeChanges$2     // Catch: java.lang.Throwable -> L64
            r5 = 0
            r4.<init>(r2, r5)     // Catch: java.lang.Throwable -> L64
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch: java.lang.Throwable -> L64
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L64
            r5 = 1
            r0.label = r5     // Catch: java.lang.Throwable -> L64
            java.lang.Object r4 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r4, r0)     // Catch: java.lang.Throwable -> L64
            if (r4 != r1) goto L51
            return r1
        L51:
            r1 = r2
        L52:
            r1.setShowCursorHandle(r3)
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r2 = r1.getTextToolbarState()
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r3 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.None
            if (r2 == r3) goto L60
            r1.hideTextToolbar()
        L60:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L64:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L68:
            r1.setShowCursorHandle(r3)
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r3 = r1.getTextToolbarState()
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r4 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.None
            if (r3 == r4) goto L76
            r1.hideTextToolbar()
        L76:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeChanges(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }

    public final void dispose() {
        hideTextToolbar();
        this.textToolbar = null;
        this.clipboardManager = null;
        this.hapticFeedBack = null;
    }

    public final Object detectTouchMode(PointerInputScope $this$detectTouchMode, Continuation<? super Unit> continuation) {
        Object awaitPointerEventScope = $this$detectTouchMode.awaitPointerEventScope(new TextFieldSelectionState$detectTouchMode$2(this, null), continuation);
        return awaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitPointerEventScope : Unit.INSTANCE;
    }

    public final Object detectTextFieldTapGestures(PointerInputScope $this$detectTextFieldTapGestures, MutableInteractionSource interactionSource, final Function0<Unit> function0, final Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object detectTapAndPress = TapGestureDetectorKt.detectTapAndPress($this$detectTextFieldTapGestures, new TextFieldSelectionState$detectTextFieldTapGestures$2(interactionSource, this, null), new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                m1268invokek4lQ0M(offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m1268invokek4lQ0M(long offset) {
                boolean z;
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$3.1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onTapTextField";
                    }
                });
                function0.invoke();
                if (this.enabled && this.getIsFocused()) {
                    z = this.readOnly;
                    if (!z) {
                        function02.invoke();
                        if (this.textFieldState.getVisualText().length() > 0) {
                            this.setShowCursorHandle(true);
                        }
                    }
                    this.updateTextToolbarState(TextToolbarState.None);
                    long coercedOffset = this.textLayoutState.m1200coercedInVisibleBoundsOfInputTextMKHz9U$foundation_release(offset);
                    this.m1250placeCursorAtNearestOffsetk4lQ0M(TextLayoutStateKt.m1207fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, coercedOffset));
                }
            }
        }, continuation);
        return detectTapAndPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectTapAndPress : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeCursorAtNearestOffset-k-4lQ0M, reason: not valid java name */
    public final boolean m1250placeCursorAtNearestOffsetk4lQ0M(long offset) {
        int index;
        IndexTransformationType type$iv;
        boolean z;
        int untransformedCursor;
        SelectionWedgeAffinity selectionWedgeAffinity;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null || (index = layoutResult.m6064getOffsetForPositionk4lQ0M(offset)) == -1) {
            return false;
        }
        SelectionWedgeAffinity selectionWedgeAffinity2 = null;
        TransformedTextFieldState $this$getIndexTransformationType$iv = this.textFieldState;
        long untransformed$iv = $this$getIndexTransformationType$iv.m1214mapFromTransformedjx7JFs(index);
        long retransformed$iv = $this$getIndexTransformationType$iv.m1217mapToTransformedGEjPoXI(untransformed$iv);
        if (TextRange.m6086getCollapsedimpl(untransformed$iv) && TextRange.m6086getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Untransformed;
        } else if (!TextRange.m6086getCollapsedimpl(untransformed$iv) && !TextRange.m6086getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Replacement;
        } else if (TextRange.m6086getCollapsedimpl(untransformed$iv) && !TextRange.m6086getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Insertion;
        } else {
            type$iv = IndexTransformationType.Deletion;
        }
        IndexTransformationType type = type$iv;
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                z = false;
                untransformedCursor = TextRange.m6092getStartimpl(untransformed$iv);
                break;
            case 2:
                z = false;
                untransformedCursor = TextRange.m6092getStartimpl(untransformed$iv);
                break;
            case 3:
                z = false;
                Rect wedgeStartCursorRect = layoutResult.getCursorRect(TextRange.m6092getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect = layoutResult.getCursorRect(TextRange.m6087getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1162findClosestRect9KIMszo(offset, wedgeStartCursorRect, wedgeEndCursorRect) < 0) {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.Start);
                } else {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.End);
                }
                selectionWedgeAffinity2 = selectionWedgeAffinity;
                untransformedCursor = TextRange.m6092getStartimpl(untransformed$iv);
                break;
            case 4:
                z = false;
                Rect wedgeStartCursorRect2 = layoutResult.getCursorRect(TextRange.m6092getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect2 = layoutResult.getCursorRect(TextRange.m6087getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1162findClosestRect9KIMszo(offset, wedgeStartCursorRect2, wedgeEndCursorRect2) < 0) {
                    untransformedCursor = TextRange.m6092getStartimpl(untransformed$iv);
                    break;
                } else {
                    untransformedCursor = TextRange.m6087getEndimpl(untransformed$iv);
                    break;
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
        long untransformedCursorRange = TextRangeKt.TextRange(untransformedCursor);
        if (TextRange.m6085equalsimpl0(untransformedCursorRange, this.textFieldState.getUntransformedText().getSelection()) && (selectionWedgeAffinity2 == null || Intrinsics.areEqual(selectionWedgeAffinity2, this.textFieldState.getSelectionWedgeAffinity()))) {
            return z;
        }
        this.textFieldState.m1220selectUntransformedCharsIn5zctL8(untransformedCursorRange);
        if (selectionWedgeAffinity2 != null) {
            SelectionWedgeAffinity it = selectionWedgeAffinity2;
            this.textFieldState.setSelectionWedgeAffinity(it);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1
            r0.<init>(r10, r12)
        L19:
            r6 = r0
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L42;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2e:
            java.lang.Object r11 = r6.L$2
            kotlin.jvm.internal.Ref$LongRef r11 = (kotlin.jvm.internal.Ref.LongRef) r11
            java.lang.Object r0 = r6.L$1
            r1 = r0
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref.LongRef) r1
            java.lang.Object r0 = r6.L$0
            r2 = r0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r2 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState) r2
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L40
            goto L92
        L40:
            r0 = move-exception
            goto L9d
        L42:
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r10
            r1 = r11
            kotlin.jvm.internal.Ref$LongRef r11 = new kotlin.jvm.internal.Ref$LongRef
            r11.<init>()
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r2.m3937getUnspecifiedF1C5BW0()
            r11.element = r2
            kotlin.jvm.internal.Ref$LongRef r2 = new kotlin.jvm.internal.Ref$LongRef
            r2.<init>()
            r8 = r2
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r2.m3937getUnspecifiedF1C5BW0()
            r8.element = r2
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2 r2 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2     // Catch: java.lang.Throwable -> L99
            r2.<init>()     // Catch: java.lang.Throwable -> L99
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch: java.lang.Throwable -> L99
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3 r3 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3     // Catch: java.lang.Throwable -> L99
            r3.<init>()     // Catch: java.lang.Throwable -> L99
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3     // Catch: java.lang.Throwable -> L99
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4 r4 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4     // Catch: java.lang.Throwable -> L99
            r4.<init>()     // Catch: java.lang.Throwable -> L99
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4     // Catch: java.lang.Throwable -> L99
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5 r5 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5     // Catch: java.lang.Throwable -> L99
            r5.<init>()     // Catch: java.lang.Throwable -> L99
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch: java.lang.Throwable -> L99
            r6.L$0 = r7     // Catch: java.lang.Throwable -> L99
            r6.L$1 = r11     // Catch: java.lang.Throwable -> L99
            r6.L$2 = r8     // Catch: java.lang.Throwable -> L99
            r9 = 1
            r6.label = r9     // Catch: java.lang.Throwable -> L99
            java.lang.Object r2 = androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L99
            if (r2 != r0) goto L8f
            return r0
        L8f:
            r1 = r11
            r2 = r7
            r11 = r8
        L92:
            detectCursorHandleDragGestures$onDragStop(r1, r11, r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L99:
            r0 = move-exception
            r1 = r11
            r2 = r7
            r11 = r8
        L9d:
            detectCursorHandleDragGestures$onDragStop(r1, r11, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef cursorDragStart, Ref.LongRef cursorDragDelta, TextFieldSelectionState this$0) {
        if (OffsetKt.m3941isSpecifiedk4lQ0M(cursorDragStart.element)) {
            cursorDragStart.element = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
            cursorDragDelta.element = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
            this$0.clearHandleDragging();
        }
    }

    public final Object textFieldSelectionGestures(PointerInputScope $this$textFieldSelectionGestures, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object selectionGesturePointerInputBtf2 = SelectionGesturesKt.selectionGesturePointerInputBtf2($this$textFieldSelectionGestures, new TextFieldMouseSelectionObserver(function0), new TextFieldTextDragObserver(function0), continuation);
        return selectionGesturePointerInputBtf2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? selectionGesturePointerInputBtf2 : Unit.INSTANCE;
    }

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\u001a\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016J\"\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0011J*\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\tX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldMouseSelectionObserver;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "requestFocus", "Lkotlin/Function0;", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "onDrag", "", "dragPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "onDrag-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "onDragDone", "onExtend", "downPosition", "onExtend-k-4lQ0M", "(J)Z", "onExtendDrag", "onExtendDrag-k-4lQ0M", "onStart", "onStart-3MmeM6k", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "isStartOfSelection", "updateSelection-r1Wruf4", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class TextFieldMouseSelectionObserver implements MouseSelectionObserver {
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        private final Function0<Unit> requestFocus;

        public TextFieldMouseSelectionObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onStart-3MmeM6k, reason: not valid java name */
        public boolean mo1261onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
            if (TextFieldSelectionState.this.enabled) {
                if (!(TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0)) {
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onStart$1
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Mouse.onStart";
                        }
                    });
                    TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Mouse);
                    this.requestFocus.invoke();
                    TextFieldSelectionState.this.previousRawDragOffset = -1;
                    this.dragBeginOffsetInText = -1;
                    this.dragBeginPosition = downPosition;
                    long newSelection = m1257updateSelectionr1Wruf4(downPosition, adjustment, true);
                    this.dragBeginOffsetInText = TextRange.m6092getStartimpl(newSelection);
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onDrag-3MmeM6k, reason: not valid java name */
        public boolean mo1258onDrag3MmeM6k(final long dragPosition, SelectionAdjustment adjustment) {
            if (TextFieldSelectionState.this.enabled) {
                if (!(TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0)) {
                    TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onDrag$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Mouse.onDrag " + ((Object) Offset.m3930toStringimpl(dragPosition));
                        }
                    });
                    m1257updateSelectionr1Wruf4(dragPosition, adjustment, false);
                    return true;
                }
            }
            return false;
        }

        /* renamed from: updateSelection-r1Wruf4, reason: not valid java name */
        private final long m1257updateSelectionr1Wruf4(long dragPosition, SelectionAdjustment adjustment, boolean isStartOfSelection) {
            Integer valueOf = Integer.valueOf(this.dragBeginOffsetInText);
            int it = valueOf.intValue();
            if (!(it >= 0)) {
                valueOf = null;
            }
            int startOffset = valueOf != null ? valueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1202getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
            int endOffset = TextFieldSelectionState.this.textLayoutState.m1202getOffsetForPosition3MmeM6k(dragPosition, false);
            long newSelection = TextFieldSelectionState.this.m1253updateSelectionSsLRf8(TextFieldSelectionState.this.textFieldState.getVisualText(), startOffset, endOffset, false, adjustment, false, isStartOfSelection);
            if (this.dragBeginOffsetInText == -1 && !TextRange.m6086getCollapsedimpl(newSelection)) {
                this.dragBeginOffsetInText = TextRange.m6092getStartimpl(newSelection);
            }
            if (TextRange.m6091getReversedimpl(newSelection)) {
                newSelection = TextFieldSelectionStateKt.m1270reverse5zctL8(newSelection);
            }
            TextFieldSelectionState.this.textFieldState.m1219selectCharsIn5zctL8(newSelection);
            TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            return newSelection;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        public void onDragDone() {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onDragDone$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onDragDone";
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onExtend-k-4lQ0M, reason: not valid java name */
        public boolean mo1259onExtendk4lQ0M(long downPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onExtend$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onExtend";
                }
            });
            return true;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
        public boolean mo1260onExtendDragk4lQ0M(long dragPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$onExtendDrag$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Mouse.onExtendDrag";
                }
            });
            return true;
        }
    }

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0012J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\u001a\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0012J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u00020\u000bX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\u00020\u000bX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldTextDragObserver;", "Landroidx/compose/foundation/text/TextDragObserver;", "requestFocus", "Lkotlin/Function0;", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "actingHandle", "Landroidx/compose/foundation/text/Handle;", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "onCancel", "onDown", "point", "onDown-k-4lQ0M", "(J)V", "onDrag", "delta", "onDrag-k-4lQ0M", "onDragStop", "onStart", "startPoint", "onStart-k-4lQ0M", "onStop", "onUp", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class TextFieldTextDragObserver implements TextDragObserver {
        private final Function0<Unit> requestFocus;
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
        private long dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
        private Handle actingHandle = Handle.SelectionEnd;

        public TextFieldTextDragObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        private final void onDragStop() {
            if (OffsetKt.m3941isSpecifiedk4lQ0M(this.dragBeginPosition)) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onDragStop$1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDragStop";
                    }
                });
                TextFieldSelectionState.this.clearHandleDragging();
                this.dragBeginOffsetInText = -1;
                this.dragBeginPosition = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
                this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
                this.requestFocus.invoke();
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onDown-k-4lQ0M */
        public void mo1048onDownk4lQ0M(long point) {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onUp() {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onStop() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onCancel() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onStart-k-4lQ0M */
        public void mo1050onStartk4lQ0M(final long startPoint) {
            if (TextFieldSelectionState.this.enabled) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onStart$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDragStart after longPress at " + ((Object) Offset.m3930toStringimpl(startPoint));
                    }
                });
                TextFieldSelectionState.this.m1256updateHandleDraggingUv8p0NA(this.actingHandle, startPoint);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Touch);
                this.dragBeginPosition = startPoint;
                this.dragTotalDistance = Offset.INSTANCE.m3938getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                boolean m1203isPositionOnTextk4lQ0M = TextFieldSelectionState.this.textLayoutState.m1203isPositionOnTextk4lQ0M(startPoint);
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (!m1203isPositionOnTextk4lQ0M) {
                    int offset = TextLayoutState.m1199getOffsetForPosition3MmeM6k$default(textFieldSelectionState.textLayoutState, startPoint, false, 2, null);
                    HapticFeedback hapticFeedback = TextFieldSelectionState.this.hapticFeedBack;
                    if (hapticFeedback != null) {
                        hapticFeedback.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4892getTextHandleMove5zf0vsI());
                    }
                    TextFieldSelectionState.this.textFieldState.placeCursorBeforeCharAt(offset);
                    TextFieldSelectionState.this.setShowCursorHandle(true);
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Cursor);
                    return;
                }
                if (textFieldSelectionState.textFieldState.getVisualText().length() == 0) {
                    return;
                }
                int offset2 = TextLayoutState.m1199getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, startPoint, false, 2, null);
                long newSelection = TextFieldSelectionState.m1254updateSelectionSsLRf8$default(TextFieldSelectionState.this, new TextFieldCharSequence(TextFieldSelectionState.this.textFieldState.getVisualText(), TextRange.INSTANCE.m6097getZerod9O1mEE(), null, null, 12, null), offset2, offset2, false, SelectionAdjustment.INSTANCE.getWord(), false, false, 96, null);
                TextFieldSelectionState.this.textFieldState.m1219selectCharsIn5zctL8(newSelection);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                this.dragBeginOffsetInText = TextRange.m6092getStartimpl(newSelection);
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onDrag-k-4lQ0M */
        public void mo1049onDragk4lQ0M(long delta) {
            int startOffset;
            int endOffset;
            SelectionAdjustment adjustment;
            Handle handle;
            SelectionAdjustment word;
            if (TextFieldSelectionState.this.enabled) {
                if (TextFieldSelectionState.this.textFieldState.getVisualText().length() == 0) {
                    return;
                }
                this.dragTotalDistance = Offset.m3927plusMKHz9U(this.dragTotalDistance, delta);
                final long currentDragPosition = Offset.m3927plusMKHz9U(this.dragBeginPosition, this.dragTotalDistance);
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$onDrag$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Touch.onDrag at " + ((Object) Offset.m3930toStringimpl(currentDragPosition));
                    }
                });
                if (this.dragBeginOffsetInText < 0 && !TextFieldSelectionState.this.textLayoutState.m1203isPositionOnTextk4lQ0M(currentDragPosition)) {
                    int startOffset2 = TextLayoutState.m1199getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, this.dragBeginPosition, false, 2, null);
                    int endOffset2 = TextLayoutState.m1199getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, currentDragPosition, false, 2, null);
                    if (startOffset2 == endOffset2) {
                        word = SelectionAdjustment.INSTANCE.getNone();
                    } else {
                        word = SelectionAdjustment.INSTANCE.getWord();
                    }
                    startOffset = startOffset2;
                    endOffset = endOffset2;
                    adjustment = word;
                } else {
                    Integer valueOf = Integer.valueOf(this.dragBeginOffsetInText);
                    int it = valueOf.intValue();
                    if (!(it >= 0)) {
                        valueOf = null;
                    }
                    int startOffset3 = valueOf != null ? valueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1202getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                    int endOffset3 = TextFieldSelectionState.this.textLayoutState.m1202getOffsetForPosition3MmeM6k(currentDragPosition, false);
                    if (this.dragBeginOffsetInText < 0 && startOffset3 == endOffset3) {
                        return;
                    }
                    SelectionAdjustment adjustment2 = SelectionAdjustment.INSTANCE.getWord();
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                    startOffset = startOffset3;
                    endOffset = endOffset3;
                    adjustment = adjustment2;
                }
                long prevSelection = TextFieldSelectionState.this.textFieldState.getVisualText().getSelection();
                long newSelection = TextFieldSelectionState.m1254updateSelectionSsLRf8$default(TextFieldSelectionState.this, TextFieldSelectionState.this.textFieldState.getVisualText(), startOffset, endOffset, false, adjustment, false, false, 64, null);
                if (this.dragBeginOffsetInText == -1 && !TextRange.m6086getCollapsedimpl(newSelection)) {
                    this.dragBeginOffsetInText = TextRange.m6092getStartimpl(newSelection);
                }
                if (TextRange.m6091getReversedimpl(newSelection)) {
                    newSelection = TextFieldSelectionStateKt.m1270reverse5zctL8(newSelection);
                }
                if (!TextRange.m6085equalsimpl0(newSelection, prevSelection)) {
                    if (TextRange.m6092getStartimpl(newSelection) != TextRange.m6092getStartimpl(prevSelection) && TextRange.m6087getEndimpl(newSelection) == TextRange.m6087getEndimpl(prevSelection)) {
                        handle = Handle.SelectionStart;
                    } else if (TextRange.m6092getStartimpl(newSelection) != TextRange.m6092getStartimpl(prevSelection) || TextRange.m6087getEndimpl(newSelection) == TextRange.m6087getEndimpl(prevSelection)) {
                        float newMiddle = (TextRange.m6092getStartimpl(newSelection) + TextRange.m6087getEndimpl(newSelection)) / 2.0f;
                        float prevMiddle = (TextRange.m6092getStartimpl(prevSelection) + TextRange.m6087getEndimpl(prevSelection)) / 2.0f;
                        if (newMiddle > prevMiddle) {
                            handle = Handle.SelectionEnd;
                        } else {
                            handle = Handle.SelectionStart;
                        }
                    } else {
                        handle = Handle.SelectionEnd;
                    }
                    this.actingHandle = handle;
                }
                if (TextRange.m6086getCollapsedimpl(prevSelection) || !TextRange.m6086getCollapsedimpl(newSelection)) {
                    TextFieldSelectionState.this.textFieldState.m1219selectCharsIn5zctL8(newSelection);
                }
                TextFieldSelectionState.this.m1256updateHandleDraggingUv8p0NA(this.actingHandle, currentDragPosition);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r17, boolean r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectSelectionHandleDragGestures$onDragStop$5(Ref.LongRef dragBeginPosition, TextFieldSelectionState this$0, Ref.LongRef dragTotalDistance) {
        if (OffsetKt.m3941isSpecifiedk4lQ0M(dragBeginPosition.element)) {
            this$0.clearHandleDragging();
            dragBeginPosition.element = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
            dragTotalDistance.element = Offset.INSTANCE.m3938getZeroF1C5BW0();
            this$0.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object collect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0<TextFieldCharSequence>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextFieldCharSequence invoke() {
                return TextFieldSelectionState.this.textFieldState.getVisualText();
            }
        }), TextFieldSelectionState$observeTextChanges$3.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((TextFieldCharSequence) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(TextFieldCharSequence it, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Object collect = SnapshotStateKt.snapshotFlow(new Function0<Rect>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextToolbarVisibility$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x002a, code lost:
            
                if (r3 == androidx.compose.foundation.text.input.internal.selection.TextToolbarState.Selection) goto L9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:4:0x001e, code lost:
            
                if (r3 != androidx.compose.foundation.text.input.internal.selection.TextToolbarState.Cursor) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:5:0x002c, code lost:
            
                r3 = true;
             */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final androidx.compose.ui.geometry.Rect invoke() {
                /*
                    r10 = this;
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r0 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.foundation.text.input.internal.TransformedTextFieldState r0 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getTextFieldState$p(r0)
                    androidx.compose.foundation.text.input.TextFieldCharSequence r0 = r0.getVisualText()
                    long r0 = r0.getSelection()
                    boolean r0 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r0)
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L20
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.foundation.text.input.internal.selection.TextToolbarState r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getTextToolbarState(r3)
                    androidx.compose.foundation.text.input.internal.selection.TextToolbarState r4 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.Cursor
                    if (r3 == r4) goto L2c
                L20:
                    if (r0 != 0) goto L2e
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.foundation.text.input.internal.selection.TextToolbarState r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getTextToolbarState(r3)
                    androidx.compose.foundation.text.input.internal.selection.TextToolbarState r4 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.Selection
                    if (r3 != r4) goto L2e
                L2c:
                    r3 = r1
                    goto L2f
                L2e:
                    r3 = r2
                L2f:
                    if (r3 == 0) goto L43
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r4 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.foundation.text.Handle r4 = r4.getDraggingHandle()
                    if (r4 != 0) goto L43
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r4 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    boolean r4 = r4.isInTouchMode()
                    if (r4 == 0) goto L43
                    goto L44
                L43:
                    r1 = r2
                L44:
                    if (r1 != 0) goto L4f
                    androidx.compose.ui.geometry.Rect$Companion r2 = androidx.compose.ui.geometry.Rect.INSTANCE
                    androidx.compose.ui.geometry.Rect r2 = r2.getZero()
                    goto Lb0
                L4f:
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r2 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.ui.layout.LayoutCoordinates r2 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getTextLayoutCoordinates(r2)
                    r4 = 0
                    if (r2 == 0) goto L5d
                    androidx.compose.ui.geometry.Rect r2 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r2)
                    goto L5e
                L5d:
                    r2 = r4
                L5e:
                    if (r2 == 0) goto La9
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.ui.layout.LayoutCoordinates r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getTextLayoutCoordinates(r5)
                    if (r5 == 0) goto L75
                    long r6 = r2.m3957getTopLeftF1C5BW0()
                    long r5 = r5.mo5502localToRootMKHz9U(r6)
                    androidx.compose.ui.geometry.Offset r5 = androidx.compose.ui.geometry.Offset.m3911boximpl(r5)
                    goto L76
                L75:
                    r5 = r4
                L76:
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                    long r6 = r5.getPackedValue()
                    long r8 = r2.m3955getSizeNHjbRc()
                    androidx.compose.ui.geometry.Rect r6 = androidx.compose.ui.geometry.RectKt.m3962Recttz77jQw(r6, r8)
                    androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r7 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                    androidx.compose.ui.geometry.Rect r7 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getContentRect(r7)
                    r8 = r7
                    r9 = 0
                    boolean r8 = r6.overlaps(r8)
                    if (r8 == 0) goto L96
                    r4 = r7
                L96:
                    if (r4 == 0) goto La1
                L99:
                    androidx.compose.ui.geometry.Rect r4 = r4.intersect(r6)
                    if (r4 == 0) goto La1
                    r2 = r4
                    goto Lb0
                La1:
                    androidx.compose.ui.geometry.Rect$Companion r4 = androidx.compose.ui.geometry.Rect.INSTANCE
                    androidx.compose.ui.geometry.Rect r4 = r4.getZero()
                    r2 = r4
                    goto Lb0
                La9:
                    androidx.compose.ui.geometry.Rect$Companion r4 = androidx.compose.ui.geometry.Rect.INSTANCE
                    androidx.compose.ui.geometry.Rect r4 = r4.getZero()
                    r2 = r4
                Lb0:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextToolbarVisibility$2.invoke():androidx.compose.ui.geometry.Rect");
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextToolbarVisibility$3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((Rect) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                boolean areEqual = Intrinsics.areEqual(rect, Rect.INSTANCE.getZero());
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (areEqual) {
                    textFieldSelectionState.hideTextToolbar();
                } else {
                    textFieldSelectionState.showTextToolbar(rect);
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        float startTop;
        Rect cursorRect;
        Rect cursorRect2;
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6086getCollapsedimpl(text.getSelection())) {
            Rect cursorRect3 = getCursorRect();
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            long topLeft = textLayoutCoordinates != null ? textLayoutCoordinates.mo5502localToRootMKHz9U(cursorRect3.m3957getTopLeftF1C5BW0()) : Offset.INSTANCE.m3938getZeroF1C5BW0();
            return RectKt.m3962Recttz77jQw(topLeft, cursorRect3.m3955getSizeNHjbRc());
        }
        LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
        long startOffset = textLayoutCoordinates2 != null ? textLayoutCoordinates2.mo5502localToRootMKHz9U(m1246getHandlePositiontuRUvjQ(true)) : Offset.INSTANCE.m3938getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates3 = getTextLayoutCoordinates();
        long endOffset = textLayoutCoordinates3 != null ? textLayoutCoordinates3.mo5502localToRootMKHz9U(m1246getHandlePositiontuRUvjQ(false)) : Offset.INSTANCE.m3938getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates4 = getTextLayoutCoordinates();
        float endTop = 0.0f;
        if (textLayoutCoordinates4 != null) {
            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
            startTop = Offset.m3923getYimpl(textLayoutCoordinates4.mo5502localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (cursorRect2 = layoutResult.getCursorRect(TextRange.m6092getStartimpl(text.getSelection()))) == null) ? 0.0f : cursorRect2.getTop())));
        } else {
            startTop = 0.0f;
        }
        LayoutCoordinates textLayoutCoordinates5 = getTextLayoutCoordinates();
        if (textLayoutCoordinates5 != null) {
            TextLayoutResult layoutResult2 = this.textLayoutState.getLayoutResult();
            endTop = Offset.m3923getYimpl(textLayoutCoordinates5.mo5502localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (cursorRect = layoutResult2.getCursorRect(TextRange.m6087getEndimpl(text.getSelection()))) == null) ? 0.0f : cursorRect.getTop())));
        }
        return new Rect(Math.min(Offset.m3922getXimpl(startOffset), Offset.m3922getXimpl(endOffset)), Math.min(startTop, endTop), Math.max(Offset.m3922getXimpl(startOffset), Offset.m3922getXimpl(endOffset)), Math.max(Offset.m3923getYimpl(startOffset), Offset.m3923getYimpl(endOffset)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
    
        if (((r7 == null || (r7 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r7)) == null) ? false : androidx.compose.foundation.text.selection.SelectionManagerKt.m1365containsInclusiveUv8p0NA(r7, r5)) != false) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState getSelectionHandleState$foundation_release(boolean r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            if (r17 == 0) goto L7
            androidx.compose.foundation.text.Handle r1 = androidx.compose.foundation.text.Handle.SelectionStart
            goto L9
        L7:
            androidx.compose.foundation.text.Handle r1 = androidx.compose.foundation.text.Handle.SelectionEnd
        L9:
            androidx.compose.foundation.text.input.internal.TextLayoutState r2 = r0.textLayoutState
            androidx.compose.ui.text.TextLayoutResult r2 = r2.getLayoutResult()
            if (r2 != 0) goto L18
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r2 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r2 = r2.getHidden()
            return r2
        L18:
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r3 = r0.textFieldState
            androidx.compose.foundation.text.input.TextFieldCharSequence r3 = r3.getVisualText()
            long r3 = r3.getSelection()
            boolean r5 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r3)
            if (r5 == 0) goto L2f
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r5 = r5.getHidden()
            return r5
        L2f:
            long r5 = r16.m1246getHandlePositiontuRUvjQ(r17)
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r7 = r0.getDirectDragGestureInitiator()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r8 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.InputType.None
            r9 = 1
            r10 = 0
            if (r7 != r8) goto L59
            androidx.compose.foundation.text.Handle r7 = r0.getDraggingHandle()
            if (r7 == r1) goto L57
            androidx.compose.ui.layout.LayoutCoordinates r7 = r0.getTextLayoutCoordinates()
            if (r7 == 0) goto L54
            androidx.compose.ui.geometry.Rect r7 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r7)
            if (r7 == 0) goto L54
            boolean r7 = androidx.compose.foundation.text.selection.SelectionManagerKt.m1365containsInclusiveUv8p0NA(r7, r5)
            goto L55
        L54:
            r7 = r10
        L55:
            if (r7 == 0) goto L59
        L57:
            r7 = r9
            goto L5a
        L59:
            r7 = r10
        L5a:
            if (r7 != 0) goto L64
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r8 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r8 = r8.getHidden()
            return r8
        L64:
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r8 = r0.textFieldState
            androidx.compose.foundation.text.input.TextFieldCharSequence r8 = r8.getVisualText()
            boolean r8 = r8.shouldShowSelection()
            if (r8 != 0) goto L77
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r8 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r8 = r8.getHidden()
            return r8
        L77:
            if (r17 == 0) goto L7e
            int r8 = androidx.compose.ui.text.TextRange.m6092getStartimpl(r3)
            goto L87
        L7e:
            int r8 = androidx.compose.ui.text.TextRange.m6087getEndimpl(r3)
            int r8 = r8 - r9
            int r8 = java.lang.Math.max(r8, r10)
        L87:
            androidx.compose.ui.text.style.ResolvedTextDirection r13 = r2.getBidiRunDirection(r8)
            boolean r14 = androidx.compose.ui.text.TextRange.m6091getReversedimpl(r3)
            if (r18 == 0) goto La6
            androidx.compose.ui.layout.LayoutCoordinates r9 = r0.getTextLayoutCoordinates()
            if (r9 == 0) goto La4
            androidx.compose.ui.geometry.Rect r9 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r9)
            if (r9 == 0) goto La4
            r10 = 0
            long r9 = androidx.compose.foundation.text.input.internal.TextLayoutStateKt.m1206coerceIn3MmeM6k(r5, r9)
            r11 = r9
            goto Lad
        La4:
            r11 = r5
            goto Lad
        La6:
            androidx.compose.ui.geometry.Offset$Companion r9 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r9 = r9.m3937getUnspecifiedF1C5BW0()
            r11 = r9
        Lad:
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r9 = new androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState
            r10 = 1
            r15 = 0
            r9.<init>(r10, r11, r13, r14, r15)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.getSelectionHandleState$foundation_release(boolean, boolean):androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1246getHandlePositiontuRUvjQ(boolean isStartHandle) {
        int offset;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (isStartHandle) {
            offset = TextRange.m6092getStartimpl(selection);
        } else {
            offset = TextRange.m6087getEndimpl(selection);
        }
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, offset, isStartHandle, TextRange.m6091getReversedimpl(selection));
    }

    /* renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1256updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1251setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        m1252setStartTextLayoutPositionInWindowk4lQ0M(m1245getCurrentTextLayoutPositionInWindowF1C5BW0());
    }

    public final void clearHandleDragging() {
        setDraggingHandle(null);
        m1251setRawHandleDragPositionk4lQ0M(Offset.INSTANCE.m3937getUnspecifiedF1C5BW0());
        m1252setStartTextLayoutPositionInWindowk4lQ0M(Offset.INSTANCE.m3937getUnspecifiedF1C5BW0());
    }

    public final boolean canCut() {
        return (TextRange.m6086getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || !getEditable() || this.isPassword) ? false : true;
    }

    public final void cut() {
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6086getCollapsedimpl(text.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        this.textFieldState.deleteSelectedText();
    }

    public final boolean canCopy() {
        return (TextRange.m6086getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || this.isPassword) ? false : true;
    }

    public static /* synthetic */ void copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionState.copy(z);
    }

    public final void copy(boolean cancelSelection) {
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m6086getCollapsedimpl(text.getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
    }

    public final boolean canPaste() {
        if (!getEditable()) {
            return false;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null && clipboardManager.hasText()) {
            return true;
        }
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        if ((function0 != null ? function0.invoke() : null) == null) {
            return false;
        }
        ClipboardManager clipboardManager2 = this.clipboardManager;
        return (clipboardManager2 != null ? clipboardManager2.getClip() : null) != null;
    }

    public final void paste() {
        ReceiveContentConfiguration receiveContentConfiguration;
        ClipEntry clipEntry;
        ClipEntry clipEntry2;
        String clipboardText;
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        if (function0 == null || (receiveContentConfiguration = function0.invoke()) == null) {
            pasteAsPlainText();
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (clipEntry = clipboardManager.getClip()) == null) {
            pasteAsPlainText();
            return;
        }
        ClipMetadata clipMetadata = clipEntry.getClipMetadata();
        TransferableContent remaining = receiveContentConfiguration.getReceiveContentListener().onReceive(new TransferableContent(clipEntry, clipMetadata, TransferableContent.Source.INSTANCE.m327getClipboardkB6V9T0(), null, 8, null));
        if (remaining != null && (clipEntry2 = remaining.getClipEntry()) != null && (clipboardText = TransferableContent_androidKt.readPlainText(clipEntry2)) != null) {
            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, clipboardText, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
        }
    }

    private final void pasteAsPlainText() {
        AnnotatedString text;
        String clipboardText;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null || (clipboardText = text.getText()) == null) {
            return;
        }
        TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, clipboardText, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
    }

    public final boolean canSelectAll() {
        return TextRange.m6088getLengthimpl(this.textFieldState.getVisualText().getSelection()) != this.textFieldState.getVisualText().length();
    }

    public final void selectAll() {
        this.textFieldState.selectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTextToolbar(Rect contentRect) {
        Function0<Unit> function0;
        Function0<Unit> function02;
        Function0<Unit> function03;
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar != null) {
            boolean enabled$iv = canCopy();
            final TextToolbarState desiredState$iv = TextToolbarState.None;
            Function0<Unit> function04 = null;
            if (!enabled$iv) {
                function0 = null;
            } else {
                function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        TextFieldSelectionState.copy$default(this_$iv, false, 1, null);
                        TextFieldSelectionState.this.updateTextToolbarState(desiredState$iv);
                    }
                };
            }
            boolean enabled$iv2 = canPaste();
            final TextToolbarState desiredState$iv2 = TextToolbarState.None;
            if (!enabled$iv2) {
                function02 = null;
            } else {
                function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        this_$iv.paste();
                        TextFieldSelectionState.this.updateTextToolbarState(desiredState$iv2);
                    }
                };
            }
            boolean enabled$iv3 = canCut();
            final TextToolbarState desiredState$iv3 = TextToolbarState.None;
            if (!enabled$iv3) {
                function03 = null;
            } else {
                function03 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        this_$iv.cut();
                        TextFieldSelectionState.this.updateTextToolbarState(desiredState$iv3);
                    }
                };
            }
            boolean enabled$iv4 = canSelectAll();
            final TextToolbarState desiredState$iv4 = TextToolbarState.Selection;
            if (enabled$iv4) {
                function04 = new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$showTextToolbar$$inlined$menuItem$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        this_$iv.selectAll();
                        TextFieldSelectionState.this.updateTextToolbarState(desiredState$iv4);
                    }
                };
            }
            textToolbar.showMenu(contentRect, function0, function02, function03, function04);
        }
    }

    private final Function0<Unit> menuItem(boolean enabled, final TextToolbarState desiredState, final Function0<Unit> operation) {
        if (enabled) {
            return new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$menuItem$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    operation.invoke();
                    this.updateTextToolbarState(desiredState);
                }
            };
        }
        return null;
    }

    public final void deselect() {
        if (!TextRange.m6086getCollapsedimpl(this.textFieldState.getVisualText().getSelection())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* renamed from: updateSelection-SsL-Rf8$default, reason: not valid java name */
    static /* synthetic */ long m1254updateSelectionSsLRf8$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            z3 = false;
        }
        return textFieldSelectionState.m1253updateSelectionSsLRf8(textFieldCharSequence, i, i2, z, selectionAdjustment, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0060, code lost:
    
        if (androidx.compose.ui.text.TextRange.m6085equalsimpl0(r2, r13.getSelection()) != false) goto L21;
     */
    /* renamed from: updateSelection-SsL-Rf8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long m1253updateSelectionSsLRf8(androidx.compose.foundation.text.input.TextFieldCharSequence r13, int r14, int r15, boolean r16, androidx.compose.foundation.text.selection.SelectionAdjustment r17, boolean r18, boolean r19) {
        /*
            r12 = this;
            long r0 = r13.getSelection()
            androidx.compose.ui.text.TextRange r0 = androidx.compose.ui.text.TextRange.m6080boximpl(r0)
            long r1 = r0.getPackedValue()
            r3 = 0
            r4 = 1
            r5 = 0
            if (r19 != 0) goto L1e
            if (r18 != 0) goto L1c
            boolean r6 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r1)
            if (r6 != 0) goto L1e
        L1c:
            r1 = r4
            goto L1f
        L1e:
            r1 = r5
        L1f:
            if (r1 == 0) goto L22
            goto L23
        L22:
            r0 = 0
        L23:
            r9 = r0
            r6 = r12
            r7 = r14
            r8 = r15
            r10 = r16
            r11 = r17
            long r0 = r6.m1249getTextFieldSelectionqeG_v_k(r7, r8, r9, r10, r11)
            long r2 = r13.getSelection()
            boolean r2 = androidx.compose.ui.text.TextRange.m6085equalsimpl0(r0, r2)
            if (r2 == 0) goto L3c
            return r0
        L3c:
            boolean r2 = androidx.compose.ui.text.TextRange.m6091getReversedimpl(r0)
            long r7 = r13.getSelection()
            boolean r3 = androidx.compose.ui.text.TextRange.m6091getReversedimpl(r7)
            if (r2 == r3) goto L63
            r2 = r0
            r7 = 0
            int r8 = androidx.compose.ui.text.TextRange.m6087getEndimpl(r2)
            int r9 = androidx.compose.ui.text.TextRange.m6092getStartimpl(r2)
            long r2 = androidx.compose.ui.text.TextRangeKt.TextRange(r8, r9)
            long r7 = r13.getSelection()
            boolean r2 = androidx.compose.ui.text.TextRange.m6085equalsimpl0(r2, r7)
            if (r2 == 0) goto L63
            goto L64
        L63:
            r4 = r5
        L64:
            boolean r2 = r12.isInTouchMode()
            if (r2 == 0) goto L7a
            if (r4 != 0) goto L7a
            androidx.compose.ui.hapticfeedback.HapticFeedback r2 = r12.hapticFeedBack
            if (r2 == 0) goto L7a
            androidx.compose.ui.hapticfeedback.HapticFeedbackType$Companion r3 = androidx.compose.ui.hapticfeedback.HapticFeedbackType.INSTANCE
            int r3 = r3.m4892getTextHandleMove5zf0vsI()
            r2.mo4883performHapticFeedbackCdsT49E(r3)
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.m1253updateSelectionSsLRf8(androidx.compose.foundation.text.input.TextFieldCharSequence, int, int, boolean, androidx.compose.foundation.text.selection.SelectionAdjustment, boolean, boolean):long");
    }

    /* renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1249getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m6097getZerod9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayout = SelectionLayoutKt.m1331getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m6097getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayout.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long result = adjustment.adjust(selectionLayout).m1320toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayout;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return result;
    }
}
