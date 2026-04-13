package androidx.compose.material3.internal;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;

/* compiled from: AccessibilityServiceStateProvider.android.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0002\u0010\u0013\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\"B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0019J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0019R+\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u00020\u0003*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006#"}, d2 = {"Landroidx/compose/material3/internal/Listener;", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroidx/compose/runtime/State;", "", "listenToTouchExplorationState", "listenToSwitchAccessState", "(ZZ)V", "<set-?>", "accessibilityEnabled", "getAccessibilityEnabled", "()Z", "setAccessibilityEnabled", "(Z)V", "accessibilityEnabled$delegate", "Landroidx/compose/runtime/MutableState;", "switchAccessListener", "androidx/compose/material3/internal/Listener$switchAccessListener$1", "Landroidx/compose/material3/internal/Listener$switchAccessListener$1;", "touchExplorationListener", "androidx/compose/material3/internal/Listener$touchExplorationListener$1", "Landroidx/compose/material3/internal/Listener$touchExplorationListener$1;", "value", "getValue", "()Ljava/lang/Boolean;", "switchAccessEnabled", "Landroid/view/accessibility/AccessibilityManager;", "getSwitchAccessEnabled", "(Landroid/view/accessibility/AccessibilityManager;)Z", "onAccessibilityStateChanged", "", "enabled", "register", "am", "unregister", "Api33Impl", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class Listener implements AccessibilityManager.AccessibilityStateChangeListener, State<Boolean> {

    /* renamed from: accessibilityEnabled$delegate, reason: from kotlin metadata */
    private final MutableState accessibilityEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private final Listener$switchAccessListener$1 switchAccessListener;
    private final Listener$touchExplorationListener$1 touchExplorationListener;

    public Listener(boolean listenToTouchExplorationState, boolean listenToSwitchAccessState) {
        Listener$touchExplorationListener$1 listener$touchExplorationListener$1;
        if (listenToTouchExplorationState) {
            listener$touchExplorationListener$1 = new Listener$touchExplorationListener$1();
        } else {
            listener$touchExplorationListener$1 = null;
        }
        this.touchExplorationListener = listener$touchExplorationListener$1;
        this.switchAccessListener = listenToSwitchAccessState ? new Listener$switchAccessListener$1(this) : null;
    }

    private final boolean getAccessibilityEnabled() {
        State $this$getValue$iv = this.accessibilityEnabled;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setAccessibilityEnabled(boolean z) {
        MutableState $this$setValue$iv = this.accessibilityEnabled;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSwitchAccessEnabled(AccessibilityManager $this$switchAccessEnabled) {
        List $this$fastAny$iv;
        boolean z;
        List $this$fastAny$iv2 = $this$switchAccessEnabled.getEnabledAccessibilityServiceList(16);
        int index$iv$iv = 0;
        int size = $this$fastAny$iv2.size();
        while (true) {
            boolean z2 = false;
            if (index$iv$iv >= size) {
                return false;
            }
            Object item$iv$iv = $this$fastAny$iv2.get(index$iv$iv);
            AccessibilityServiceInfo it = (AccessibilityServiceInfo) item$iv$iv;
            String settingsActivityName = it.getSettingsActivityName();
            if (settingsActivityName != null) {
                $this$fastAny$iv = $this$fastAny$iv2;
                boolean contains$default = StringsKt.contains$default((CharSequence) settingsActivityName, (CharSequence) "SwitchAccess", false, 2, (Object) null);
                z = true;
                if (contains$default) {
                    z2 = true;
                }
            } else {
                $this$fastAny$iv = $this$fastAny$iv2;
                z = true;
            }
            if (z2) {
                return z;
            }
            index$iv$iv++;
            $this$fastAny$iv2 = $this$fastAny$iv;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
    
        if ((r0 != null ? r0.getEnabled() : false) != false) goto L14;
     */
    @Override // androidx.compose.runtime.State
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Boolean getValue() {
        /*
            r2 = this;
            boolean r0 = r2.getAccessibilityEnabled()
            r1 = 0
            if (r0 == 0) goto L21
            androidx.compose.material3.internal.Listener$touchExplorationListener$1 r0 = r2.touchExplorationListener
            if (r0 == 0) goto L10
            boolean r0 = r0.getEnabled()
            goto L11
        L10:
            r0 = r1
        L11:
            if (r0 != 0) goto L1f
            androidx.compose.material3.internal.Listener$switchAccessListener$1 r0 = r2.switchAccessListener
            if (r0 == 0) goto L1c
            boolean r0 = r0.getEnabled()
            goto L1d
        L1c:
            r0 = r1
        L1d:
            if (r0 == 0) goto L21
        L1f:
            r1 = 1
            goto L22
        L21:
        L22:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.Listener.getValue():java.lang.Boolean");
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        setAccessibilityEnabled(enabled);
    }

    public final void register(AccessibilityManager am) {
        setAccessibilityEnabled(am.isEnabled());
        am.addAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 it = this.touchExplorationListener;
        if (it != null) {
            it.setEnabled(am.isTouchExplorationEnabled());
            am.addTouchExplorationStateChangeListener(it);
        }
        Listener$switchAccessListener$1 it2 = this.switchAccessListener;
        if (it2 != null) {
            it2.setEnabled(getSwitchAccessEnabled(am));
            Api33Impl.addAccessibilityServicesStateChangeListener(am, it2);
        }
    }

    public final void unregister(AccessibilityManager am) {
        am.removeAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 it = this.touchExplorationListener;
        if (it != null) {
            am.removeTouchExplorationStateChangeListener(it);
        }
        Listener$switchAccessListener$1 it2 = this.switchAccessListener;
        if (it2 != null) {
            Api33Impl.removeAccessibilityServicesStateChangeListener(am, it2);
        }
    }

    /* compiled from: AccessibilityServiceStateProvider.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/material3/internal/Listener$Api33Impl;", "", "()V", "addAccessibilityServicesStateChangeListener", "", "am", "Landroid/view/accessibility/AccessibilityManager;", "listener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityServicesStateChangeListener;", "removeAccessibilityServicesStateChangeListener", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        @JvmStatic
        public static final void addAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager.AccessibilityServicesStateChangeListener listener) {
            am.addAccessibilityServicesStateChangeListener(listener);
        }

        @JvmStatic
        public static final void removeAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager.AccessibilityServicesStateChangeListener listener) {
            am.removeAccessibilityServicesStateChangeListener(listener);
        }
    }
}
