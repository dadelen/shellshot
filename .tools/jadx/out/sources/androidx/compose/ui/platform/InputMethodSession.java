package androidx.compose.ui.platform;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper_androidKt;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: AndroidPlatformTextInputSession.android.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0006R$\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/platform/InputMethodSession;", "", "request", "Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;", "onAllConnectionsClosed", "Lkotlin/Function0;", "", "(Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;Lkotlin/jvm/functions/Function0;)V", "connections", "Landroidx/compose/runtime/collection/MutableVector;", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "Landroidx/compose/ui/node/WeakReference;", "disposed", "", "isActive", "()Z", "lock", "createInputConnection", "Landroid/view/inputmethod/InputConnection;", "outAttrs", "Landroid/view/inputmethod/EditorInfo;", "dispose", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class InputMethodSession {
    private boolean disposed;
    private final Function0<Unit> onAllConnectionsClosed;
    private final PlatformTextInputMethodRequest request;
    private final Object lock = new Object();
    private MutableVector<WeakReference<NullableInputConnectionWrapper>> connections = new MutableVector<>(new WeakReference[16], 0);

    public InputMethodSession(PlatformTextInputMethodRequest request, Function0<Unit> function0) {
        this.request = request;
        this.onAllConnectionsClosed = function0;
    }

    public final boolean isActive() {
        return !this.disposed;
    }

    public final InputConnection createInputConnection(EditorInfo outAttrs) {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (this.disposed) {
                return null;
            }
            InputConnection connectionDelegate = this.request.createInputConnection(outAttrs);
            NullableInputConnectionWrapper it = NullableInputConnectionWrapper_androidKt.NullableInputConnectionWrapper(connectionDelegate, new Function1<NullableInputConnectionWrapper, Unit>() { // from class: androidx.compose.ui.platform.InputMethodSession$createInputConnection$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NullableInputConnectionWrapper nullableInputConnectionWrapper) {
                    invoke2(nullableInputConnectionWrapper);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect condition in loop: B:5:0x001f */
                /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
                /* JADX WARN: Removed duplicated region for block: B:15:0x003f  */
                /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke2(androidx.compose.ui.text.input.NullableInputConnectionWrapper r8) {
                    /*
                        r7 = this;
                        r8.disposeDelegate()
                        androidx.compose.ui.platform.InputMethodSession r0 = androidx.compose.ui.platform.InputMethodSession.this
                        androidx.compose.runtime.collection.MutableVector r0 = androidx.compose.ui.platform.InputMethodSession.access$getConnections$p(r0)
                        r1 = 0
                        int r2 = r0.getSize()
                        if (r2 <= 0) goto L26
                        r3 = 0
                        java.lang.Object[] r4 = r0.getContent()
                    L16:
                        r5 = r4[r3]
                        java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
                        r6 = 0
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)
                        if (r5 == 0) goto L22
                        goto L27
                    L22:
                        int r3 = r3 + 1
                        if (r3 < r2) goto L16
                    L26:
                        r3 = -1
                    L27:
                        if (r3 < 0) goto L33
                        androidx.compose.ui.platform.InputMethodSession r0 = androidx.compose.ui.platform.InputMethodSession.this
                        androidx.compose.runtime.collection.MutableVector r0 = androidx.compose.ui.platform.InputMethodSession.access$getConnections$p(r0)
                        r0.removeAt(r3)
                    L33:
                        androidx.compose.ui.platform.InputMethodSession r0 = androidx.compose.ui.platform.InputMethodSession.this
                        androidx.compose.runtime.collection.MutableVector r0 = androidx.compose.ui.platform.InputMethodSession.access$getConnections$p(r0)
                        boolean r0 = r0.isEmpty()
                        if (r0 == 0) goto L48
                        androidx.compose.ui.platform.InputMethodSession r0 = androidx.compose.ui.platform.InputMethodSession.this
                        kotlin.jvm.functions.Function0 r0 = androidx.compose.ui.platform.InputMethodSession.access$getOnAllConnectionsClosed$p(r0)
                        r0.invoke()
                    L48:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.InputMethodSession$createInputConnection$1$1.invoke2(androidx.compose.ui.text.input.NullableInputConnectionWrapper):void");
                }
            });
            this.connections.add(new WeakReference<>(it));
            return it;
        }
    }

    public final void dispose() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            this.disposed = true;
            MutableVector this_$iv = this.connections;
            int size$iv = this_$iv.getSize();
            if (size$iv > 0) {
                int i$iv = 0;
                Object[] content$iv = this_$iv.getContent();
                do {
                    WeakReference it = (WeakReference) content$iv[i$iv];
                    NullableInputConnectionWrapper nullableInputConnectionWrapper = it.get();
                    if (nullableInputConnectionWrapper != null) {
                        nullableInputConnectionWrapper.disposeDelegate();
                    }
                    i$iv++;
                } while (i$iv < size$iv);
            }
            this.connections.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
