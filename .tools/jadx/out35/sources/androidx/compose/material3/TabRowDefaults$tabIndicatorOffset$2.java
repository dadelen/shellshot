package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: TabRow.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class TabRowDefaults$tabIndicatorOffset$2 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ TabPosition $currentTabPosition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TabRowDefaults$tabIndicatorOffset$2(TabPosition tabPosition) {
        super(3);
        this.$currentTabPosition = tabPosition;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    private static final float invoke$lambda$0(State<Dp> state) {
        return state.getValue().m6640unboximpl();
    }

    public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
        AnimationSpec animationSpec;
        AnimationSpec animationSpec2;
        Object value$iv;
        $composer.startReplaceGroup(-1541271084);
        ComposerKt.sourceInformation($composer, "C1266@53909L151,1271@54112L150,1277@54370L53:TabRow.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1541271084, $changed, -1, "androidx.compose.material3.TabRowDefaults.tabIndicatorOffset.<anonymous> (TabRow.kt:1265)");
        }
        float width = this.$currentTabPosition.getWidth();
        animationSpec = TabRowKt.TabRowIndicatorSpec;
        State currentTabWidth$delegate = AnimateAsStateKt.m120animateDpAsStateAjpBEmI(width, animationSpec, null, null, $composer, 0, 12);
        float left = this.$currentTabPosition.getLeft();
        animationSpec2 = TabRowKt.TabRowIndicatorSpec;
        final State indicatorOffset$delegate = AnimateAsStateKt.m120animateDpAsStateAjpBEmI(left, animationSpec2, null, null, $composer, 0, 12);
        Modifier wrapContentSize$default = SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default($this$composed, 0.0f, 1, null), Alignment.INSTANCE.getBottomStart(), false, 2, null);
        ComposerKt.sourceInformationMarkerStart($composer, -1825077707, "CC(remember):TabRow.kt#9igjgp");
        boolean invalid$iv = $composer.changed(indicatorOffset$delegate);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$2$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                    return IntOffset.m6745boximpl(m2594invokeBjo55l4(density));
                }

                /* renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m2594invokeBjo55l4(Density $this$offset) {
                    float invoke$lambda$1;
                    invoke$lambda$1 = TabRowDefaults$tabIndicatorOffset$2.invoke$lambda$1(indicatorOffset$delegate);
                    return IntOffsetKt.IntOffset($this$offset.mo358roundToPx0680j_4(invoke$lambda$1), 0);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier m722width3ABfNKs = SizeKt.m722width3ABfNKs(OffsetKt.offset(wrapContentSize$default, (Function1) value$iv), invoke$lambda$0(currentTabWidth$delegate));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return m722width3ABfNKs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$1(State<Dp> state) {
        return state.getValue().m6640unboximpl();
    }
}
