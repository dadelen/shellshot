package androidx.compose.ui.text.input;

import android.view.inputmethod.InputConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: NullableInputConnectionWrapper.android.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¨\u0006\u0007"}, d2 = {"NullableInputConnectionWrapper", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "delegate", "Landroid/view/inputmethod/InputConnection;", "onConnectionClosed", "Lkotlin/Function1;", "", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NullableInputConnectionWrapper_androidKt {
    public static final NullableInputConnectionWrapper NullableInputConnectionWrapper(InputConnection delegate, Function1<? super NullableInputConnectionWrapper, Unit> function1) {
        return new NullableInputConnectionWrapperApi34(delegate, function1);
    }
}
