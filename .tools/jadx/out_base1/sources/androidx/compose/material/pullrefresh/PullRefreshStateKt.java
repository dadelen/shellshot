package androidx.compose.material.pullrefresh;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PullRefreshState.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"DragMultiplier", "", "rememberPullRefreshState", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "refreshing", "", "onRefresh", "Lkotlin/Function0;", "", "refreshThreshold", "Landroidx/compose/ui/unit/Dp;", "refreshingOffset", "rememberPullRefreshState-UuyPYSY", "(ZLkotlin/jvm/functions/Function0;FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/pullrefresh/PullRefreshState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PullRefreshStateKt {
    private static final float DragMultiplier = 0.5f;

    /* renamed from: rememberPullRefreshState-UuyPYSY, reason: not valid java name */
    public static final PullRefreshState m1740rememberPullRefreshStateUuyPYSY(final boolean refreshing, Function0<Unit> function0, float refreshThreshold, float refreshingOffset, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -174977512, "C(rememberPullRefreshState)P(2!1,1:c#ui.unit.Dp,3:c#ui.unit.Dp)65@2636L24,66@2686L31,*70@2802L7,75@2935L104,79@3056L141,79@3045L152:PullRefreshState.kt#t44y28");
        float refreshThreshold2 = (i & 4) != 0 ? PullRefreshDefaults.INSTANCE.m1733getRefreshThresholdD9Ej5fM() : refreshThreshold;
        float refreshingOffset2 = (i & 8) != 0 ? PullRefreshDefaults.INSTANCE.m1734getRefreshingOffsetD9Ej5fM() : refreshingOffset;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-174977512, $changed, -1, "androidx.compose.material.pullrefresh.rememberPullRefreshState (PullRefreshState.kt:62)");
        }
        int $this$dp$iv = Dp.m6625compareTo0680j_4(refreshThreshold2, Dp.m6626constructorimpl(0));
        if (!($this$dp$iv > 0)) {
            throw new IllegalArgumentException("The refresh trigger must be greater than zero!".toString());
        }
        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object it$iv$iv = $composer.rememberedValue();
        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        CoroutineScope scope = wrapper$iv.getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd($composer);
        State onRefreshState = SnapshotStateKt.rememberUpdatedState(function0, $composer, ($changed >> 3) & 14);
        final Ref.FloatRef thresholdPx = new Ref.FloatRef();
        final Ref.FloatRef refreshingOffsetPx = new Ref.FloatRef();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241 = (Density) consume;
        thresholdPx.element = $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241.mo364toPx0680j_4(refreshThreshold2);
        refreshingOffsetPx.element = $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241.mo364toPx0680j_4(refreshingOffset2);
        ComposerKt.sourceInformationMarkerStart($composer, 975868558, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean invalid$iv = $composer.changed(scope);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            float refreshThreshold3 = refreshingOffsetPx.element;
            float refreshingOffset3 = thresholdPx.element;
            value$iv = new PullRefreshState(scope, onRefreshState, refreshThreshold3, refreshingOffset3);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        final PullRefreshState state = (PullRefreshState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 975872467, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(state) | (((($changed & 14) ^ 6) > 4 && $composer.changed(refreshing)) || ($changed & 6) == 4) | $composer.changed(thresholdPx.element) | $composer.changed(refreshingOffsetPx.element);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshStateKt$rememberPullRefreshState$3$1
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
                    PullRefreshState.this.setRefreshing$material_release(refreshing);
                    PullRefreshState.this.setThreshold$material_release(thresholdPx.element);
                    PullRefreshState.this.setRefreshingOffset$material_release(refreshingOffsetPx.element);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.SideEffect((Function0) value$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }
}
