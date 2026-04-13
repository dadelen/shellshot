package androidx.compose.ui.platform;

import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: AndroidComposeView.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
/* synthetic */ class AndroidComposeView$focusOwner$2 extends FunctionReferenceImpl implements Function2<FocusDirection, Rect, Boolean> {
    AndroidComposeView$focusOwner$2(Object obj) {
        super(2, obj, AndroidComposeView.class, "onRequestFocusForOwner", "onRequestFocusForOwner-7o62pno(Landroidx/compose/ui/focus/FocusDirection;Landroidx/compose/ui/geometry/Rect;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke-7o62pno, reason: not valid java name and merged with bridge method [inline-methods] */
    public final Boolean invoke(FocusDirection p0, Rect p1) {
        boolean m5808onRequestFocusForOwner7o62pno;
        m5808onRequestFocusForOwner7o62pno = ((AndroidComposeView) this.receiver).m5808onRequestFocusForOwner7o62pno(p0, p1);
        return Boolean.valueOf(m5808onRequestFocusForOwner7o62pno);
    }
}
