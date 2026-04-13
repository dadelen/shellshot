package androidx.compose.ui.platform;

import androidx.compose.ui.focus.FocusDirection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: AndroidComposeView.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
/* synthetic */ class AndroidComposeView$focusOwner$3 extends FunctionReferenceImpl implements Function1<FocusDirection, Boolean> {
    AndroidComposeView$focusOwner$3(Object obj) {
        super(1, obj, AndroidComposeView.class, "onMoveFocusInChildren", "onMoveFocusInChildren-3ESFkO8(I)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(FocusDirection focusDirection) {
        return m5815invoke3ESFkO8(focusDirection.getValue());
    }

    /* renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final Boolean m5815invoke3ESFkO8(int p0) {
        boolean m5807onMoveFocusInChildren3ESFkO8;
        m5807onMoveFocusInChildren3ESFkO8 = ((AndroidComposeView) this.receiver).m5807onMoveFocusInChildren3ESFkO8(p0);
        return Boolean.valueOf(m5807onMoveFocusInChildren3ESFkO8);
    }
}
