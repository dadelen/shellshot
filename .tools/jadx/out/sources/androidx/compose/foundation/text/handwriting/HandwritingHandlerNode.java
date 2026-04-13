package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.text.input.internal.ComposeInputMethodManager;
import androidx.compose.foundation.text.input.internal.ComposeInputMethodManager_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: HandwritingHandler.android.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/text/handwriting/HandwritingHandlerNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "()V", "composeImm", "Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "getComposeImm", "()Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "composeImm$delegate", "Lkotlin/Lazy;", "focusState", "Landroidx/compose/ui/focus/FocusState;", "onFocusEvent", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class HandwritingHandlerNode extends Modifier.Node implements FocusEventModifierNode {

    /* renamed from: composeImm$delegate, reason: from kotlin metadata */
    private final Lazy composeImm = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ComposeInputMethodManager>() { // from class: androidx.compose.foundation.text.handwriting.HandwritingHandlerNode$composeImm$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ComposeInputMethodManager invoke() {
            return ComposeInputMethodManager_androidKt.ComposeInputMethodManager(DelegatableNode_androidKt.requireView(HandwritingHandlerNode.this));
        }
    });
    private FocusState focusState;

    /* JADX INFO: Access modifiers changed from: private */
    public final ComposeInputMethodManager getComposeImm() {
        return (ComposeInputMethodManager) this.composeImm.getValue();
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        if (!Intrinsics.areEqual(this.focusState, focusState)) {
            this.focusState = focusState;
            if (focusState.getHasFocus()) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new HandwritingHandlerNode$onFocusEvent$1(this, null), 3, null);
            }
        }
    }
}
