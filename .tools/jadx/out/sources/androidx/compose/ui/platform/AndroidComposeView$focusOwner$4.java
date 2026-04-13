package androidx.compose.ui.platform;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: AndroidComposeView.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
/* synthetic */ class AndroidComposeView$focusOwner$4 extends FunctionReferenceImpl implements Function0<Unit> {
    AndroidComposeView$focusOwner$4(Object obj) {
        super(0, obj, AndroidComposeView.class, "onClearFocusForOwner", "onClearFocusForOwner()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((AndroidComposeView) this.receiver).onClearFocusForOwner();
    }
}
