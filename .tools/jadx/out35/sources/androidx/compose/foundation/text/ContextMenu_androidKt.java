package androidx.compose.foundation.text;

import androidx.compose.foundation.contextmenu.ContextMenuArea_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt;
import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.foundation.text.selection.SelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: ContextMenu.android.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010\t\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010\f\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\r2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010\u000e\u001a5\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0004\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0080\b¨\u0006\u0016"}, d2 = {"ContextMenuArea", "", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "enabled", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextItem", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "label", "Landroidx/compose/foundation/text/TextContextMenuItems;", "operation", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenu_androidKt {
    public static final void ContextMenuArea(final TextFieldSelectionManager manager, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        final Function2 content;
        Composer $composer2 = $composer.startRestartGroup(-1985516685);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuArea)P(1)36@1552L31,39@1688L17,37@1628L215:ContextMenu.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1985516685, $dirty, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:35)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 181395603, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new ContextMenuState(null, 1, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final ContextMenuState state = (ContextMenuState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 181399941, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$1$1
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
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            content = function2;
            ContextMenuArea_androidKt.ContextMenuArea(state, (Function0) value$iv2, TextFieldSelectionManager_androidKt.contextMenuBuilder(manager, state), null, manager.getEnabled(), content, $composer2, (($dirty << 12) & 458752) | 54, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            content = function2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ContextMenu_androidKt.ContextMenuArea(TextFieldSelectionManager.this, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final void ContextMenuArea(final TextFieldSelectionState selectionState, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        final boolean enabled2;
        final Function2 content;
        Composer $composer2 = $composer.startRestartGroup(2103477555);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuArea)P(2,1)52@2019L31,55@2155L17,53@2095L214:ContextMenu.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(selectionState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(enabled) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2103477555, $dirty, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:51)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 181410547, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new ContextMenuState(null, 1, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final ContextMenuState state = (ContextMenuState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 181414885, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$3$1
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
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            enabled2 = enabled;
            content = function2;
            ContextMenuArea_androidKt.ContextMenuArea(state, (Function0) value$iv2, TextFieldSelectionState_androidKt.contextMenuBuilder(selectionState, state), null, enabled2, content, $composer2, (($dirty << 9) & 57344) | 54 | (458752 & ($dirty << 9)), 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            enabled2 = enabled;
            content = function2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ContextMenu_androidKt.ContextMenuArea(TextFieldSelectionState.this, enabled2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final void ContextMenuArea(final SelectionManager manager, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        final Function2 content;
        Composer $composer2 = $composer.startRestartGroup(605522716);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuArea)P(1)67@2449L31,70@2585L17,68@2525L180:ContextMenu.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(605522716, $dirty, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:66)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 181424307, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new ContextMenuState(null, 1, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final ContextMenuState state = (ContextMenuState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 181428645, "CC(remember):ContextMenu.android.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$5$1
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
                        ContextMenuState_androidKt.close(ContextMenuState.this);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            content = function2;
            ContextMenuArea_androidKt.ContextMenuArea(state, (Function0) value$iv2, SelectionManager_androidKt.contextMenuBuilder(manager, state), null, false, content, $composer2, (($dirty << 12) & 458752) | 54, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            content = function2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$ContextMenuArea$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ContextMenu_androidKt.ContextMenuArea(SelectionManager.this, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final void TextItem(ContextMenuScope $this$TextItem, final ContextMenuState state, TextContextMenuItems label, boolean enabled, final Function0<Unit> function0) {
        $this$TextItem.item(new ContextMenu_androidKt$TextItem$1(label), (r12 & 2) != 0 ? Modifier.INSTANCE : null, (r12 & 4) != 0 ? true : enabled, (r12 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$2
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
                function0.invoke();
                ContextMenuState_androidKt.close(state);
            }
        });
    }
}
