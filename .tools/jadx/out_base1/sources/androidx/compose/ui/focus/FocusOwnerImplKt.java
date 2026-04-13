package androidx.compose.ui.focus;

import kotlin.Metadata;

/* compiled from: FocusOwnerImpl.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0005"}, d2 = {"is1dFocusSearch", "", "Landroidx/compose/ui/focus/FocusDirection;", "is1dFocusSearch-3ESFkO8", "(I)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusOwnerImplKt {
    /* renamed from: is1dFocusSearch-3ESFkO8, reason: not valid java name */
    public static final boolean m3860is1dFocusSearch3ESFkO8(int $this$is1dFocusSearch_u2d3ESFkO8) {
        return FocusDirection.m3834equalsimpl0($this$is1dFocusSearch_u2d3ESFkO8, FocusDirection.INSTANCE.m3844getNextdhqQ8s()) ? true : FocusDirection.m3834equalsimpl0($this$is1dFocusSearch_u2d3ESFkO8, FocusDirection.INSTANCE.m3845getPreviousdhqQ8s());
    }
}
