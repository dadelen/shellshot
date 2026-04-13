package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: TextFieldSelectionManager.android.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a%\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u000f²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u008e\u0002"}, d2 = {"isShiftPressed", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "contextMenuState", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "textFieldMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionManager_androidKt {
    public static final boolean isShiftPressed(PointerEvent $this$isShiftPressed) {
        return false;
    }

    public static final Modifier textFieldMagnifier(Modifier $this$textFieldMagnifier, TextFieldSelectionManager manager) {
        if (!Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return $this$textFieldMagnifier;
        }
        return ComposedModifierKt.composed$default($this$textFieldMagnifier, null, new TextFieldSelectionManager_androidKt$textFieldMagnifier$1(manager), 1, null);
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionManager $this$contextMenuBuilder, final ContextMenuState contextMenuState) {
        return new Function1<ContextMenuScope, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContextMenuScope contextMenuScope) {
                invoke2(contextMenuScope);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:22:0x00df  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(androidx.compose.foundation.contextmenu.ContextMenuScope r18) {
                /*
                    Method dump skipped, instructions count: 255
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1.invoke2(androidx.compose.foundation.contextmenu.ContextMenuScope):void");
            }
        };
    }
}
