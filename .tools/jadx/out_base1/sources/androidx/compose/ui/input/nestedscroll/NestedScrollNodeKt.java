package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.node.DelegatableNode;
import kotlin.Metadata;

/* compiled from: NestedScrollNode.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"nestedScrollModifierNode", "Landroidx/compose/ui/node/DelegatableNode;", "connection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NestedScrollNodeKt {
    public static final DelegatableNode nestedScrollModifierNode(NestedScrollConnection connection, NestedScrollDispatcher dispatcher) {
        return new NestedScrollNode(connection, dispatcher);
    }
}
