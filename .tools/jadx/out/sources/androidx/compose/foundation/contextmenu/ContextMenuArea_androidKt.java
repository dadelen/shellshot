package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ContextMenuArea.android.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aF\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0002\u0010\f\u001ac\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0011H\u0001¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"ContextMenu", "", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "onDismiss", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "contextMenuBuilderBlock", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/contextmenu/ContextMenuState;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ContextMenuArea", "enabled", "", "content", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/contextmenu/ContextMenuState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenuArea_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:52:0x0238  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextMenuArea(final androidx.compose.foundation.contextmenu.ContextMenuState r33, final kotlin.jvm.functions.Function0<kotlin.Unit> r34, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.contextmenu.ContextMenuScope, kotlin.Unit> r35, androidx.compose.ui.Modifier r36, boolean r37, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuArea_androidKt.ContextMenuArea(androidx.compose.foundation.contextmenu.ContextMenuState, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void ContextMenu(final ContextMenuState state, final Function0<Unit> function0, Modifier modifier, final Function1<? super ContextMenuScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final Function1<? super ContextMenuScope, Unit> function12;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(645832757);
        ComposerKt.sourceInformation($composer2, "C(ContextMenu)P(3,2,1)67@2412L88,71@2506L197:ContextMenuArea.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            function12 = function1;
        } else if (($changed & 3072) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        } else {
            function12 = function1;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(645832757, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenu (ContextMenuArea.android.kt:63)");
            }
            ContextMenuState.Status status = state.getStatus();
            if (!(status instanceof ContextMenuState.Status.Open)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuArea_androidKt$ContextMenu$1
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

                        public final void invoke(Composer composer, int i3) {
                            ContextMenuArea_androidKt.ContextMenu(ContextMenuState.this, function0, modifier2, function12, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                        }
                    });
                    return;
                }
                return;
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1573318964, "CC(remember):ContextMenuArea.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(status);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new ContextMenuPopupPositionProvider(IntOffsetKt.m6770roundk4lQ0M(((ContextMenuState.Status.Open) status).getOffset()), null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ContextMenuPopupPositionProvider popupPositionProvider = (ContextMenuPopupPositionProvider) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ContextMenuUi_androidKt.ContextMenuPopup(popupPositionProvider, function0, modifier2, function1, $composer2, ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168), 0);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuArea_androidKt$ContextMenu$2
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

                public final void invoke(Composer composer, int i3) {
                    ContextMenuArea_androidKt.ContextMenu(ContextMenuState.this, function0, modifier2, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
