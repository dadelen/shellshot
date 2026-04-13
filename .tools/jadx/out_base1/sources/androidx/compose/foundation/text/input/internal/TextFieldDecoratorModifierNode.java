package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.compose.foundation.content.MediaType;
import androidx.compose.foundation.content.ReceiveContentListener;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.DragAndDropRequestPermission_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.content.internal.ReceiveContentConfigurationKt;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.handwriting.StylusHandwritingNode;
import androidx.compose.foundation.text.handwriting.StylusHandwriting_androidKt;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextToolbarState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNodeKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.PlatformTextInputModifierNode;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.core.app.NotificationCompat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: TextFieldDecoratorModifier.kt */
@Metadata(d1 = {"\u0000\u0081\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001>\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\fBY\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0016\u0012\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ\b\u0010d\u001a\u00020\"H\u0002J\b\u0010e\u001a\u00020\"H\u0002J\b\u0010f\u001a\u00020\"H\u0016J\b\u0010g\u001a\u00020\"H\u0016J\b\u0010h\u001a\u00020\"H\u0016J\b\u0010i\u001a\u00020\"H\u0002J\u0010\u0010j\u001a\u00020\"2\u0006\u0010k\u001a\u00020lH\u0016J\u0010\u0010m\u001a\u00020\"2\u0006\u0010n\u001a\u00020oH\u0016J\u001a\u0010p\u001a\u00020\"2\u0006\u0010q\u001a\u00020rH\u0002ø\u0001\u0000¢\u0006\u0004\bs\u0010tJ\u001a\u0010u\u001a\u00020\u00162\u0006\u0010v\u001a\u00020wH\u0016ø\u0001\u0000¢\u0006\u0004\bx\u0010yJ\b\u0010z\u001a\u00020\"H\u0016J.\u0010{\u001a\u00020\"2\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u007f2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J\u001c\u0010\u0084\u0001\u001a\u00020\u00162\u0006\u0010v\u001a\u00020wH\u0016ø\u0001\u0000¢\u0006\u0005\b\u0085\u0001\u0010yJ\n\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\"2\u0007\u0010\u0089\u0001\u001a\u00020\u0016H\u0002J[\u0010\u008a\u0001\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u008b\u0001\u001a\u00020\"*\u00030\u008c\u0001H\u0016R\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010)\"\u0004\b+\u0010,R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u00107\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u0010)R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0010\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?R\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0010\u0010C\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010)\"\u0004\bG\u0010,R\u0016\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010J0IX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010K\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010)R\u001a\u0010\u001c\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010)\"\u0004\bN\u0010,R\u000e\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010Q\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0010\u0010b\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u008d\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/platform/PlatformTextInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "filter", "Landroidx/compose/foundation/text/input/InputTransformation;", "enabled", "", "readOnly", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActionHandler", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "singleLine", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;)V", "backingStylusHandwritingTrigger", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "dragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "dragEnterEvent", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "editable", "getEditable", "()Z", "getEnabled", "setEnabled", "(Z)V", "getFilter", "()Landroidx/compose/foundation/text/input/InputTransformation;", "setFilter", "(Landroidx/compose/foundation/text/input/InputTransformation;)V", "inputSessionJob", "Lkotlinx/coroutines/Job;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "setInteractionSource", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "isElementFocused", "isFocused", "getKeyboardActionHandler", "()Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "setKeyboardActionHandler", "(Landroidx/compose/foundation/text/input/KeyboardActionHandler;)V", "keyboardActionScope", "androidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1", "Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1;", "<set-?>", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "observeChangesJob", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "getReadOnly", "setReadOnly", "receiveContentConfigurationProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "getSingleLine", "setSingleLine", "stylusHandwritingNode", "Landroidx/compose/foundation/text/handwriting/StylusHandwritingNode;", "stylusHandwritingTrigger", "getStylusHandwritingTrigger", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "textFieldKeyEventHandler", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "getTextFieldSelectionState", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "setTextFieldSelectionState", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;)V", "getTextFieldState", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "setTextFieldState", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;)V", "getTextLayoutState", "()Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "setTextLayoutState", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;)V", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "disposeInputSession", "emitDragExitEvent", "onAttach", "onCancelPointerInput", "onDetach", "onFocusChange", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "onGloballyPositioned", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "onImeActionPerformed", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed-KlQnJC8", "(I)V", "onKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onObservedReadsChanged", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "requireKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "startInputSession", "fromTap", "updateNode", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldDecoratorModifierNode extends DelegatingNode implements PlatformTextInputModifierNode, SemanticsModifierNode, FocusRequesterModifierNode, FocusEventModifierNode, GlobalPositionAwareModifierNode, PointerInputModifierNode, KeyInputModifierNode, CompositionLocalConsumerModifierNode, ModifierLocalModifierNode, ObserverModifierNode, LayoutAwareModifierNode {
    public static final int $stable = 8;
    private MutableSharedFlow<Unit> backingStylusHandwritingTrigger;
    private HoverInteraction.Enter dragEnterEvent;
    private boolean enabled;
    private InputTransformation filter;
    private Job inputSessionJob;
    private MutableInteractionSource interactionSource;
    private boolean isElementFocused;
    private KeyboardActionHandler keyboardActionHandler;
    private final TextFieldDecoratorModifierNode$keyboardActionScope$1 keyboardActionScope;
    private KeyboardOptions keyboardOptions;
    private Job observeChangesJob;
    private boolean readOnly;
    private final Function0<ReceiveContentConfiguration> receiveContentConfigurationProvider;
    private boolean singleLine;
    private final StylusHandwritingNode stylusHandwritingNode;
    private final TextFieldKeyEventHandler textFieldKeyEventHandler;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private WindowInfo windowInfo;
    private final SuspendingPointerInputModifierNode pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new TextFieldDecoratorModifierNode$pointerInputNode$1(this, null)));
    private final DragAndDropModifierNode dragAndDropNode = (DragAndDropModifierNode) delegate(TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode$default(new Function0<Set<? extends MediaType>>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Set<? extends MediaType> invoke() {
            Set<? extends MediaType> set;
            Set<? extends MediaType> set2;
            ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this);
            if (receiveContentConfiguration != null) {
                set2 = TextFieldDecoratorModifierKt.MediaTypesAll;
                return set2;
            }
            set = TextFieldDecoratorModifierKt.MediaTypesText;
            return set;
        }
    }, new Function2<ClipEntry, ClipMetadata, Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$2
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(ClipEntry clipEntry, ClipMetadata clipMetadata) {
            ClipEntry clipEntry2;
            TextFieldDecoratorModifierNode.this.emitDragExitEvent();
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().clearHandleDragging();
            String plainText = TransferableContent_androidKt.readPlainText(clipEntry);
            ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this);
            if (receiveContentConfiguration != null) {
                TransferableContent transferableContent = new TransferableContent(clipEntry, clipMetadata, TransferableContent.Source.INSTANCE.m328getDragAndDropkB6V9T0(), null, 8, null);
                TransferableContent remaining = receiveContentConfiguration.getReceiveContentListener().onReceive(transferableContent);
                plainText = (remaining == null || (clipEntry2 = remaining.getClipEntry()) == null) ? null : TransferableContent_androidKt.readPlainText(clipEntry2);
            }
            if (plainText != null) {
                String p0 = plainText;
                TransformedTextFieldState.replaceSelectedText$default(TextFieldDecoratorModifierNode.this.getTextFieldState(), p0, false, null, 6, null);
            }
            return true;
        }
    }, new Function1<DragAndDropEvent, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$3
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DragAndDropEvent dragAndDropEvent) {
            invoke2(dragAndDropEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DragAndDropEvent it) {
            if (ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this) != null) {
                DragAndDropRequestPermission_androidKt.dragAndDropRequestPermission(TextFieldDecoratorModifierNode.this, it);
            }
        }
    }, null, new Function1<DragAndDropEvent, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$4
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DragAndDropEvent dragAndDropEvent) {
            invoke2(dragAndDropEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DragAndDropEvent it) {
            ReceiveContentListener receiveContentListener;
            TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
            HoverInteraction.Enter it2 = new HoverInteraction.Enter();
            TextFieldDecoratorModifierNode.this.getInteractionSource().tryEmit(it2);
            textFieldDecoratorModifierNode.dragEnterEvent = it2;
            ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this);
            if (receiveContentConfiguration == null || (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) == null) {
                return;
            }
            receiveContentListener.onDragEnter();
        }
    }, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$5
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
            m1188invokek4lQ0M(offset.getPackedValue());
            return Unit.INSTANCE;
        }

        /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m1188invokek4lQ0M(long position) {
            long positionOnTextField = TextLayoutStateKt.m1209fromWindowToDecorationUv8p0NA(TextFieldDecoratorModifierNode.this.getTextLayoutState(), position);
            int cursorPosition = TextLayoutState.m1199getOffsetForPosition3MmeM6k$default(TextFieldDecoratorModifierNode.this.getTextLayoutState(), positionOnTextField, false, 2, null);
            TextFieldDecoratorModifierNode.this.getTextFieldState().m1219selectCharsIn5zctL8(TextRangeKt.TextRange(cursorPosition));
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().m1256updateHandleDraggingUv8p0NA(Handle.Cursor, positionOnTextField);
        }
    }, null, new Function1<DragAndDropEvent, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$6
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DragAndDropEvent dragAndDropEvent) {
            invoke2(dragAndDropEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DragAndDropEvent it) {
            ReceiveContentListener receiveContentListener;
            TextFieldDecoratorModifierNode.this.emitDragExitEvent();
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().clearHandleDragging();
            ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this);
            if (receiveContentConfiguration == null || (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) == null) {
                return;
            }
            receiveContentListener.onDragExit();
        }
    }, new Function1<DragAndDropEvent, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$dragAndDropNode$7
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DragAndDropEvent dragAndDropEvent) {
            invoke2(dragAndDropEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DragAndDropEvent it) {
            TextFieldDecoratorModifierNode.this.emitDragExitEvent();
        }
    }, 72, null));

    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    public final void setTextFieldState(TransformedTextFieldState transformedTextFieldState) {
        this.textFieldState = transformedTextFieldState;
    }

    public final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    public final void setTextLayoutState(TextLayoutState textLayoutState) {
        this.textLayoutState = textLayoutState;
    }

    public final TextFieldSelectionState getTextFieldSelectionState() {
        return this.textFieldSelectionState;
    }

    public final void setTextFieldSelectionState(TextFieldSelectionState textFieldSelectionState) {
        this.textFieldSelectionState = textFieldSelectionState;
    }

    public final InputTransformation getFilter() {
        return this.filter;
    }

    public final void setFilter(InputTransformation inputTransformation) {
        this.filter = inputTransformation;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public final KeyboardActionHandler getKeyboardActionHandler() {
        return this.keyboardActionHandler;
    }

    public final void setKeyboardActionHandler(KeyboardActionHandler keyboardActionHandler) {
        this.keyboardActionHandler = keyboardActionHandler;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final void setInteractionSource(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    public TextFieldDecoratorModifierNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, final KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean singleLine, MutableInteractionSource interactionSource) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = filter;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.keyboardActionHandler = keyboardActionHandler;
        this.singleLine = singleLine;
        this.interactionSource = interactionSource;
        this.stylusHandwritingNode = (StylusHandwritingNode) delegate(new StylusHandwritingNode(new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$stylusHandwritingNode$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
            
                r0 = r2.this$0.getStylusHandwritingTrigger();
             */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke() {
                /*
                    r2 = this;
                    androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r0 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.this
                    boolean r0 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.access$isFocused(r0)
                    if (r0 != 0) goto Lf
                    androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r0 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.this
                    androidx.compose.ui.focus.FocusRequesterModifierNode r0 = (androidx.compose.ui.focus.FocusRequesterModifierNode) r0
                    androidx.compose.ui.focus.FocusRequesterModifierNodeKt.requestFocus(r0)
                Lf:
                    androidx.compose.foundation.text.KeyboardOptions r0 = r3
                    int r0 = r0.getKeyboardType()
                    androidx.compose.ui.text.input.KeyboardType$Companion r1 = androidx.compose.ui.text.input.KeyboardType.INSTANCE
                    int r1 = r1.m6328getPasswordPjHm6EE()
                    boolean r0 = androidx.compose.ui.text.input.KeyboardType.m6309equalsimpl0(r0, r1)
                    if (r0 != 0) goto L40
                    androidx.compose.foundation.text.KeyboardOptions r0 = r3
                    int r0 = r0.getKeyboardType()
                    androidx.compose.ui.text.input.KeyboardType$Companion r1 = androidx.compose.ui.text.input.KeyboardType.INSTANCE
                    int r1 = r1.m6327getNumberPasswordPjHm6EE()
                    boolean r0 = androidx.compose.ui.text.input.KeyboardType.m6309equalsimpl0(r0, r1)
                    if (r0 != 0) goto L40
                    androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r0 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.this
                    kotlinx.coroutines.flow.MutableSharedFlow r0 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.access$getStylusHandwritingTrigger(r0)
                    if (r0 == 0) goto L40
                    kotlin.Unit r1 = kotlin.Unit.INSTANCE
                    r0.tryEmit(r1)
                L40:
                    r0 = 1
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$stylusHandwritingNode$1.invoke():java.lang.Boolean");
            }
        }));
        InputTransformation inputTransformation = this.filter;
        this.keyboardOptions = keyboardOptions.fillUnspecifiedValuesWith$foundation_release(inputTransformation != null ? inputTransformation.getKeyboardOptions() : null);
        this.textFieldKeyEventHandler = TextFieldKeyEventHandler_androidKt.createTextFieldKeyEventHandler();
        this.keyboardActionScope = new TextFieldDecoratorModifierNode$keyboardActionScope$1(this);
        this.receiveContentConfigurationProvider = new Function0<ReceiveContentConfiguration>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$receiveContentConfigurationProvider$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReceiveContentConfiguration invoke() {
                return ReceiveContentConfigurationKt.getReceiveContentConfiguration(TextFieldDecoratorModifierNode.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getEditable() {
        return this.enabled && !this.readOnly;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableSharedFlow<Unit> getStylusHandwritingTrigger() {
        MutableSharedFlow finalStylusHandwritingTrigger = this.backingStylusHandwritingTrigger;
        if (finalStylusHandwritingTrigger != null) {
            return finalStylusHandwritingTrigger;
        }
        if (!StylusHandwriting_androidKt.isStylusHandwritingSupported()) {
            return null;
        }
        MutableSharedFlow it = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_LATEST, 2, null);
        this.backingStylusHandwritingTrigger = it;
        return it;
    }

    public final KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFocused() {
        WindowInfo windowInfo = this.windowInfo;
        boolean isWindowFocused = windowInfo != null && windowInfo.isWindowFocused();
        return this.isElementFocused && isWindowFocused;
    }

    public final void updateNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean singleLine, MutableInteractionSource interactionSource) {
        boolean previousWriteable = this.enabled && !this.readOnly;
        boolean writeable = enabled && !readOnly;
        boolean previousEnabled = this.enabled;
        TransformedTextFieldState previousTextFieldState = this.textFieldState;
        KeyboardOptions previousKeyboardOptions = this.keyboardOptions;
        TextFieldSelectionState previousTextFieldSelectionState = this.textFieldSelectionState;
        MutableInteractionSource previousInteractionSource = this.interactionSource;
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = filter;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.keyboardOptions = keyboardOptions.fillUnspecifiedValuesWith$foundation_release(filter != null ? filter.getKeyboardOptions() : null);
        this.keyboardActionHandler = keyboardActionHandler;
        this.singleLine = singleLine;
        this.interactionSource = interactionSource;
        if (writeable != previousWriteable || !Intrinsics.areEqual(textFieldState, previousTextFieldState) || !Intrinsics.areEqual(this.keyboardOptions, previousKeyboardOptions)) {
            if (writeable && isFocused()) {
                startInputSession(false);
            } else if (!writeable) {
                disposeInputSession();
            }
        }
        if (previousEnabled != enabled) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (!Intrinsics.areEqual(textFieldSelectionState, previousTextFieldSelectionState)) {
            this.pointerInputNode.resetPointerInputHandler();
            this.stylusHandwritingNode.resetPointerInputHandler();
            if (getIsAttached()) {
                textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
            }
        }
        if (!Intrinsics.areEqual(interactionSource, previousInteractionSource)) {
            this.pointerInputNode.resetPointerInputHandler();
            this.stylusHandwritingNode.resetPointerInputHandler();
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        TextFieldCharSequence text = this.textFieldState.getOutputText();
        long selection = text.getSelection();
        SemanticsPropertiesKt.setEditableText($this$applySemantics, new AnnotatedString(text.toString(), null, null, 6, null));
        SemanticsPropertiesKt.m5922setTextSelectionRangeFDrldGo($this$applySemantics, selection);
        if (!this.enabled) {
            SemanticsPropertiesKt.disabled($this$applySemantics);
        }
        SemanticsPropertiesKt.setEditable($this$applySemantics, getEditable());
        SemanticsPropertiesKt.getTextLayoutResult$default($this$applySemantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<TextLayoutResult> list) {
                TextLayoutResult result = TextFieldDecoratorModifierNode.this.getTextLayoutState().getLayoutResult();
                return Boolean.valueOf(result != null ? list.add(result) : false);
            }
        }, 1, null);
        if (getEditable()) {
            SemanticsPropertiesKt.setText$default($this$applySemantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(AnnotatedString newText) {
                    boolean editable;
                    editable = TextFieldDecoratorModifierNode.this.getEditable();
                    if (!editable) {
                        return false;
                    }
                    TextFieldDecoratorModifierNode.this.getTextFieldState().replaceAll(newText);
                    return true;
                }
            }, 1, null);
            SemanticsPropertiesKt.insertTextAtCursor$default($this$applySemantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(AnnotatedString newText) {
                    boolean editable;
                    editable = TextFieldDecoratorModifierNode.this.getEditable();
                    if (!editable) {
                        return false;
                    }
                    TransformedTextFieldState.replaceSelectedText$default(TextFieldDecoratorModifierNode.this.getTextFieldState(), newText, true, null, 4, null);
                    return true;
                }
            }, 1, null);
        }
        SemanticsPropertiesKt.setSelection$default($this$applySemantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$4
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
            }

            public final Boolean invoke(int start, int end, boolean relativeToOriginal) {
                TextFieldCharSequence text2;
                TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
                if (relativeToOriginal) {
                    text2 = textFieldDecoratorModifierNode.getTextFieldState().getUntransformedText();
                } else {
                    text2 = textFieldDecoratorModifierNode.getTextFieldState().getVisualText();
                }
                long selection2 = text2.getSelection();
                if (!TextFieldDecoratorModifierNode.this.getEnabled() || Math.min(start, end) < 0 || Math.max(start, end) > text2.length()) {
                    return false;
                }
                if (start == TextRange.m6092getStartimpl(selection2) && end == TextRange.m6087getEndimpl(selection2)) {
                    return true;
                }
                long selectionRange = TextRangeKt.TextRange(start, end);
                if (relativeToOriginal || start == end) {
                    TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.None);
                } else {
                    TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
                }
                TextFieldDecoratorModifierNode textFieldDecoratorModifierNode2 = TextFieldDecoratorModifierNode.this;
                if (relativeToOriginal) {
                    textFieldDecoratorModifierNode2.getTextFieldState().m1220selectUntransformedCharsIn5zctL8(selectionRange);
                } else {
                    textFieldDecoratorModifierNode2.getTextFieldState().m1219selectCharsIn5zctL8(selectionRange);
                }
                return true;
            }
        }, 1, null);
        final int effectiveImeAction = this.keyboardOptions.m1002getImeActionOrDefaulteUduSuo$foundation_release();
        SemanticsPropertiesKt.m5917onImeAction9UiTYpY$default($this$applySemantics, effectiveImeAction, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                TextFieldDecoratorModifierNode.this.m1187onImeActionPerformedKlQnJC8(effectiveImeAction);
                return true;
            }
        }, 2, null);
        SemanticsPropertiesKt.onClick$default($this$applySemantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean isFocused;
                SoftwareKeyboardController requireKeyboardController;
                isFocused = TextFieldDecoratorModifierNode.this.isFocused();
                TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
                if (!isFocused) {
                    FocusRequesterModifierNodeKt.requestFocus(textFieldDecoratorModifierNode);
                } else if (!textFieldDecoratorModifierNode.getReadOnly()) {
                    requireKeyboardController = TextFieldDecoratorModifierNode.this.requireKeyboardController();
                    requireKeyboardController.show();
                }
                return true;
            }
        }, 1, null);
        SemanticsPropertiesKt.onLongClick$default($this$applySemantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$7
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean isFocused;
                isFocused = TextFieldDecoratorModifierNode.this.isFocused();
                if (!isFocused) {
                    FocusRequesterModifierNodeKt.requestFocus(TextFieldDecoratorModifierNode.this);
                }
                TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
                return true;
            }
        }, 1, null);
        if (!TextRange.m6086getCollapsedimpl(selection)) {
            SemanticsPropertiesKt.copyText$default($this$applySemantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$8
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    TextFieldSelectionState.copy$default(TextFieldDecoratorModifierNode.this.getTextFieldSelectionState(), false, 1, null);
                    return true;
                }
            }, 1, null);
            if (this.enabled && !this.readOnly) {
                SemanticsPropertiesKt.cutText$default($this$applySemantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$9
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().cut();
                        return true;
                    }
                }, 1, null);
            }
        }
        if (getEditable()) {
            SemanticsPropertiesKt.pasteText$default($this$applySemantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$applySemantics$10
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().paste();
                    return true;
                }
            }, 1, null);
        }
        InputTransformation it = this.filter;
        if (it != null) {
            it.applySemantics($this$applySemantics);
        }
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        if (this.isElementFocused == focusState.isFocused()) {
            return;
        }
        this.isElementFocused = focusState.isFocused();
        onFocusChange();
        if (focusState.isFocused()) {
            if (getEditable()) {
                startInputSession(false);
            }
        } else {
            disposeInputSession();
            TransformedTextFieldState $this$iv = this.textFieldState;
            TextFieldState $this$iv$iv = $this$iv.textFieldState;
            InputTransformation inputTransformation$iv$iv = $this$iv.inputTransformation;
            TextFieldEditUndoBehavior undoBehavior$iv$iv = TextFieldEditUndoBehavior.MergeIfPossible;
            $this$iv$iv.getMainBuffer().getChangeTracker().clearChanges();
            EditingBuffer $this$onFocusEvent_u24lambda_u243 = $this$iv$iv.getMainBuffer();
            EditCommandKt.finishComposingText($this$onFocusEvent_u24lambda_u243);
            $this$iv$iv.commitEditAsUser(inputTransformation$iv$iv, true, undoBehavior$iv$iv);
            this.textFieldState.collapseSelectionToMax();
        }
        this.stylusHandwritingNode.onFocusEvent(focusState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFocusChange() {
        Job launch$default;
        this.textFieldSelectionState.setFocused(isFocused());
        if (isFocused() && this.observeChangesJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$onFocusChange$1(this, null), 3, null);
            this.observeChangesJob = launch$default;
        } else if (!isFocused()) {
            Job job = this.observeChangesJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.observeChangesJob = null;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        onObservedReadsChanged();
        this.textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        disposeInputSession();
        this.textFieldSelectionState.setReceiveContentConfiguration(null);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setDecoratorNodeCoordinates(coordinates);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public void mo200onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.stylusHandwritingNode.mo200onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        this.pointerInputNode.mo200onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.stylusHandwritingNode.onCancelPointerInput();
        this.pointerInputNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onPreKeyEvent-ZmokQxo */
    public boolean mo201onPreKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.mo1124onPreKeyEventMyFupTE(event, this.textFieldState, this.textFieldSelectionState, (FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager()), requireKeyboardController());
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onKeyEvent-ZmokQxo */
    public boolean mo199onKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.mo1123onKeyEvent6ptp14s(event, this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.enabled && !this.readOnly, this.singleLine, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onKeyEvent$1
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
                TextFieldDecoratorModifierNode.this.m1187onImeActionPerformedKlQnJC8(TextFieldDecoratorModifierNode.this.getKeyboardOptions().m1002getImeActionOrDefaulteUduSuo$foundation_release());
            }
        });
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onObservedReadsChanged$1
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
                TextFieldDecoratorModifierNode.this.windowInfo = (WindowInfo) CompositionLocalConsumerModifierNodeKt.currentValueOf(TextFieldDecoratorModifierNode.this, CompositionLocalsKt.getLocalWindowInfo());
                TextFieldDecoratorModifierNode.this.onFocusChange();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startInputSession(boolean fromTap) {
        Job launch$default;
        if (fromTap || this.keyboardOptions.getShowKeyboardOnFocusOrDefault$foundation_release()) {
            ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this);
            launch$default = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$startInputSession$1(this, receiveContentConfiguration, null), 3, null);
            this.inputSessionJob = launch$default;
        }
    }

    private final void disposeInputSession() {
        Job job = this.inputSessionJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.inputSessionJob = null;
        MutableSharedFlow<Unit> stylusHandwritingTrigger = getStylusHandwritingTrigger();
        if (stylusHandwritingTrigger != null) {
            stylusHandwritingTrigger.resetReplayCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SoftwareKeyboardController requireKeyboardController() {
        SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalSoftwareKeyboardController());
        if (softwareKeyboardController != null) {
            return softwareKeyboardController;
        }
        throw new IllegalStateException("No software keyboard controller".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitDragExitEvent() {
        HoverInteraction.Enter it = this.dragEnterEvent;
        if (it != null) {
            this.interactionSource.tryEmit(new HoverInteraction.Exit(it));
            this.dragEnterEvent = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onImeActionPerformed-KlQnJC8, reason: not valid java name */
    public final void m1187onImeActionPerformedKlQnJC8(final int imeAction) {
        if (ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6273getNoneeUduSuo()) || ImeAction.m6256equalsimpl0(imeAction, ImeAction.INSTANCE.m6269getDefaulteUduSuo()) || this.keyboardActionHandler == null) {
            this.keyboardActionScope.mo988defaultKeyboardActionKlQnJC8(imeAction);
            return;
        }
        KeyboardActionHandler keyboardActionHandler = this.keyboardActionHandler;
        if (keyboardActionHandler != null) {
            keyboardActionHandler.onKeyboardAction(new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onImeActionPerformed$1
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
                    TextFieldDecoratorModifierNode$keyboardActionScope$1 textFieldDecoratorModifierNode$keyboardActionScope$1;
                    textFieldDecoratorModifierNode$keyboardActionScope$1 = TextFieldDecoratorModifierNode.this.keyboardActionScope;
                    textFieldDecoratorModifierNode$keyboardActionScope$1.mo988defaultKeyboardActionKlQnJC8(imeAction);
                }
            });
        }
    }
}
