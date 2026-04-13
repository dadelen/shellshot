package androidx.compose.ui.unit;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: InlineClassHelper.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a*\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0000\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"DualFloatSignBit", "", "checkPrecondition", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "requirePrecondition", "throwIllegalArgumentException", "message", "throwIllegalStateException", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class InlineClassHelperKt {
    public static final long DualFloatSignBit = -9223372034707292160L;

    public static final void throwIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static final void requirePrecondition(boolean value, Function0<String> function0) {
        if (!value) {
            throwIllegalArgumentException(function0.invoke());
        }
    }

    public static final void throwIllegalStateException(String message) {
        throw new IllegalStateException(message);
    }

    public static final void checkPrecondition(boolean value, Function0<String> function0) {
        if (!value) {
            throwIllegalStateException(function0.invoke());
        }
    }
}
