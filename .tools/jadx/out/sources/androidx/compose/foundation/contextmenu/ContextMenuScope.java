package androidx.compose.foundation.contextmenu;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.StringsKt;

/* compiled from: ContextMenuUi.android.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0010Jl\u0010\u0011\u001a\u00020\n2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182*\b\u0002\u0010\u0019\u001a$\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005¢\u0006\u0002\b\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u0013¢\u0006\u0002\u0010\u001dR4\u0010\u0003\u001a(\u0012$\u0012\"\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "()V", "composables", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "colors", "", "Landroidx/compose/runtime/Composable;", "Content", "Content$foundation_release", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/runtime/Composer;I)V", "clear", "clear$foundation_release", "item", "label", "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "leadingIcon", "Landroidx/compose/ui/graphics/Color;", "iconColor", "onClick", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenuScope {
    public static final int $stable = 0;
    private final SnapshotStateList<Function3<ContextMenuColors, Composer, Integer, Unit>> composables = SnapshotStateKt.mutableStateListOf();

    public final void Content$foundation_release(final ContextMenuColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1320309496);
        ComposerKt.sourceInformation($composer2, "C(Content)*234@8533L18:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(colors) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(this) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1320309496, $dirty, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.Content (ContextMenuUi.android.kt:233)");
            }
            List $this$fastForEach$iv = this.composables;
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                Function3 composable = (Function3) item$iv;
                composable.invoke(colors, $composer2, Integer.valueOf($dirty & 14));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ContextMenuScope.this.Content$foundation_release(colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public final void clear$foundation_release() {
        this.composables.clear();
    }

    public final void item(final Function2<? super Composer, ? super Integer, String> label, final Modifier modifier, final boolean enabled, final Function3<? super Color, ? super Composer, ? super Integer, Unit> leadingIcon, final Function0<Unit> onClick) {
        this.composables.add(ComposableLambdaKt.composableLambdaInstance(262103052, true, new Function3<ContextMenuColors, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$item$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(ContextMenuColors contextMenuColors, Composer composer, Integer num) {
                invoke(contextMenuColors, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ContextMenuColors colors, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C275@10323L7,277@10419L251:ContextMenuUi.android.kt#3xeu6s");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed(colors) ? 4 : 2;
                }
                if (($dirty & 19) != 18 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(262103052, $dirty, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.item.<anonymous> (ContextMenuUi.android.kt:275)");
                    }
                    String resolvedLabel = label.invoke($composer, 0);
                    if (StringsKt.isBlank(resolvedLabel)) {
                        throw new IllegalStateException("Label must not be blank".toString());
                    }
                    ContextMenuUi_androidKt.ContextMenuItem(resolvedLabel, enabled, colors, modifier, leadingIcon, onClick, $composer, ($dirty << 6) & 896, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }));
    }
}
