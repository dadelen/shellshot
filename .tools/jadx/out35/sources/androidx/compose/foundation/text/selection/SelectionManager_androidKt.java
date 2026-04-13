package androidx.compose.foundation.text.selection;

import android.view.KeyEvent;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: SelectionManager.android.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002"}, d2 = {"isCopyKeyEvent", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "isCopyKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/selection/SelectionManager;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "selectionMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionManager_androidKt {
    /* renamed from: isCopyKeyEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m1367isCopyKeyEventZmokQxo(KeyEvent keyEvent) {
        return KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo987mapZmokQxo(keyEvent) == KeyCommand.COPY;
    }

    public static final Modifier selectionMagnifier(Modifier $this$selectionMagnifier, SelectionManager manager) {
        if (!Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return $this$selectionMagnifier;
        }
        return ComposedModifierKt.composed$default($this$selectionMagnifier, null, new SelectionManager_androidKt$selectionMagnifier$1(manager), 1, null);
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final SelectionManager $this$contextMenuBuilder, final ContextMenuState state) {
        return new Function1<ContextMenuScope, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$contextMenuBuilder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContextMenuScope contextMenuScope) {
                invoke2(contextMenuScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContextMenuScope $this$null) {
                final ContextMenuState state$iv = ContextMenuState.this;
                TextContextMenuItems label$iv = TextContextMenuItems.Copy;
                boolean enabled$iv = $this$contextMenuBuilder.isNonEmptySelection$foundation_release();
                final SelectionManager selectionManager = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        selectionManager.copy$foundation_release();
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
                final ContextMenuState state$iv2 = ContextMenuState.this;
                TextContextMenuItems label$iv2 = TextContextMenuItems.SelectAll;
                boolean enabled$iv2 = !$this$contextMenuBuilder.isEntireContainerSelected$foundation_release();
                final SelectionManager selectionManager2 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv2), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv2, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        selectionManager2.selectAll$foundation_release();
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
                CollectionsKt.listOf((Object[]) new Unit[]{Unit.INSTANCE, Unit.INSTANCE});
            }
        };
    }
}
