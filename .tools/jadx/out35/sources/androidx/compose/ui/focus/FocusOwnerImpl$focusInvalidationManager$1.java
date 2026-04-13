package androidx.compose.ui.focus;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: FocusOwnerImpl.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
/* synthetic */ class FocusOwnerImpl$focusInvalidationManager$1 extends FunctionReferenceImpl implements Function0<Unit> {
    FocusOwnerImpl$focusInvalidationManager$1(Object obj) {
        super(0, obj, FocusOwnerImpl.class, "invalidateOwnerFocusState", "invalidateOwnerFocusState()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((FocusOwnerImpl) this.receiver).invalidateOwnerFocusState();
    }
}
