package androidx.compose.foundation.text.input.internal;

import android.view.InputDevice;
import android.view.KeyEvent;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: TextFieldKeyEventHandler.android.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JP\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J:\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/AndroidTextFieldKeyEventHandler;", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "()V", "onKeyEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "editable", "singleLine", "onSubmit", "Lkotlin/Function0;", "", "onKeyEvent-6ptp14s", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZZLkotlin/jvm/functions/Function0;)Z", "onPreKeyEvent", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "onPreKeyEvent-MyFupTE", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/focus/FocusManager;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidTextFieldKeyEventHandler extends TextFieldKeyEventHandler {
    public static final int $stable = 0;

    @Override // androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler
    /* renamed from: onPreKeyEvent-MyFupTE, reason: not valid java name */
    public boolean mo1124onPreKeyEventMyFupTE(KeyEvent event, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, FocusManager focusManager, SoftwareKeyboardController keyboardController) {
        boolean m1192isKeyCodeYhN2O0w;
        boolean m1192isKeyCodeYhN2O0w2;
        boolean m1192isKeyCodeYhN2O0w3;
        boolean m1192isKeyCodeYhN2O0w4;
        boolean m1192isKeyCodeYhN2O0w5;
        if (super.mo1124onPreKeyEventMyFupTE(event, textFieldState, textFieldSelectionState, focusManager, keyboardController)) {
            return true;
        }
        InputDevice device = event.getDevice();
        if (device != null && device.supportsSource(InputDeviceCompat.SOURCE_DPAD) && !device.isVirtual() && KeyEventType.m5213equalsimpl0(KeyEvent_androidKt.m5221getTypeZmokQxo(event), KeyEventType.INSTANCE.m5217getKeyDownCS__XNY()) && event.getSource() != 257) {
            m1192isKeyCodeYhN2O0w = TextFieldKeyEventHandler_androidKt.m1192isKeyCodeYhN2O0w(event, 19);
            if (m1192isKeyCodeYhN2O0w) {
                return focusManager.mo3849moveFocus3ESFkO8(FocusDirection.INSTANCE.m3847getUpdhqQ8s());
            }
            m1192isKeyCodeYhN2O0w2 = TextFieldKeyEventHandler_androidKt.m1192isKeyCodeYhN2O0w(event, 20);
            if (m1192isKeyCodeYhN2O0w2) {
                return focusManager.mo3849moveFocus3ESFkO8(FocusDirection.INSTANCE.m3840getDowndhqQ8s());
            }
            m1192isKeyCodeYhN2O0w3 = TextFieldKeyEventHandler_androidKt.m1192isKeyCodeYhN2O0w(event, 21);
            if (m1192isKeyCodeYhN2O0w3) {
                return focusManager.mo3849moveFocus3ESFkO8(FocusDirection.INSTANCE.m3843getLeftdhqQ8s());
            }
            m1192isKeyCodeYhN2O0w4 = TextFieldKeyEventHandler_androidKt.m1192isKeyCodeYhN2O0w(event, 22);
            if (m1192isKeyCodeYhN2O0w4) {
                return focusManager.mo3849moveFocus3ESFkO8(FocusDirection.INSTANCE.m3846getRightdhqQ8s());
            }
            m1192isKeyCodeYhN2O0w5 = TextFieldKeyEventHandler_androidKt.m1192isKeyCodeYhN2O0w(event, 23);
            if (!m1192isKeyCodeYhN2O0w5) {
                return false;
            }
            keyboardController.show();
            return true;
        }
        return false;
    }

    @Override // androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler
    /* renamed from: onKeyEvent-6ptp14s, reason: not valid java name */
    public boolean mo1123onKeyEvent6ptp14s(KeyEvent event, TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, boolean editable, boolean singleLine, Function0<Unit> onSubmit) {
        if (KeyEventType.m5213equalsimpl0(KeyEvent_androidKt.m5221getTypeZmokQxo(event), KeyEventType.INSTANCE.m5217getKeyDownCS__XNY()) && event.isFromSource(InputDeviceCompat.SOURCE_KEYBOARD) && !TextFieldKeyEventHandler_androidKt.m1191isFromSoftKeyboardZmokQxo(event)) {
            textFieldSelectionState.setInTouchMode(false);
        }
        return super.mo1123onKeyEvent6ptp14s(event, textFieldState, textLayoutState, textFieldSelectionState, editable, singleLine, onSubmit);
    }
}
