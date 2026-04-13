package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: TextFieldSelectionState.android.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionState_androidKt {
    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionState $this$contextMenuBuilder, final ContextMenuState state) {
        return new Function1<ContextMenuScope, Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$1
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
                TextContextMenuItems label$iv = TextContextMenuItems.Cut;
                boolean enabled$iv = $this$contextMenuBuilder.canCut();
                final TextFieldSelectionState textFieldSelectionState = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1
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
                        textFieldSelectionState.cut();
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
                final ContextMenuState state$iv2 = ContextMenuState.this;
                TextContextMenuItems label$iv2 = TextContextMenuItems.Copy;
                boolean enabled$iv2 = $this$contextMenuBuilder.canCopy();
                final TextFieldSelectionState textFieldSelectionState2 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv2), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv2, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2
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
                        textFieldSelectionState2.copy(false);
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
                final ContextMenuState state$iv3 = ContextMenuState.this;
                TextContextMenuItems label$iv3 = TextContextMenuItems.Paste;
                boolean enabled$iv3 = $this$contextMenuBuilder.canPaste();
                final TextFieldSelectionState textFieldSelectionState3 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv3), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv3, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$3
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
                        textFieldSelectionState3.paste();
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
                final ContextMenuState state$iv4 = ContextMenuState.this;
                TextContextMenuItems label$iv4 = TextContextMenuItems.SelectAll;
                boolean enabled$iv4 = $this$contextMenuBuilder.canSelectAll();
                final TextFieldSelectionState textFieldSelectionState4 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt$TextItem$1(label$iv4), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled$iv4, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$4
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
                        textFieldSelectionState4.selectAll();
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                });
            }
        };
    }
}
