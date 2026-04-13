package com.example.shellshot.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.shellshot.data.ProcessingResult;
import com.example.shellshot.data.ProcessingStatus;
import com.example.shellshot.service.AutoShellMode;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.ui.MainUiState;
import com.example.shellshot.ui.components.ZipFrontendComponentsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a]\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u000bH\u0007¢\u0006\u0002\u0010\u000e\u001a%\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u0012\u001a\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\u0016\u001a\u001d\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0018\u001a\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u001a"}, d2 = {"HomeScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "uiState", "Lcom/example/shellshot/ui/MainUiState;", "darkTheme", "", "onToggleDarkTheme", "Lkotlin/Function0;", "onToggleMonitoring", "Lkotlin/Function1;", "onSelectTemplate", "", "(Landroidx/compose/ui/Modifier;Lcom/example/shellshot/ui/MainUiState;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "HomeDetailRow", "label", "value", "(ZLjava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "processingStatusLabel", "processingStatusColor", "Landroidx/compose/ui/graphics/Color;", "(Lcom/example/shellshot/ui/MainUiState;)J", "processingStatusBackground", "(Lcom/example/shellshot/ui/MainUiState;Z)J", "homeStatusSubtitle", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class HomeScreenKt {

    /* compiled from: HomeScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProcessingStatus.values().length];
            try {
                iArr[ProcessingStatus.Success.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProcessingStatus.Failed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProcessingStatus.Skipped.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit HomeDetailRow$lambda$42(boolean z, String str, String str2, int i, Composer composer, int i2) {
        HomeDetailRow(z, str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$40(Modifier modifier, MainUiState mainUiState, boolean z, Function0 function0, Function1 function1, Function1 function12, int i, int i2, Composer composer, int i3) {
        HomeScreen(modifier, mainUiState, z, function0, function1, function12, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void HomeScreen(Modifier modifier, final MainUiState uiState, final boolean darkTheme, final Function0<Unit> onToggleDarkTheme, final Function1<? super Boolean, Unit> onToggleMonitoring, final Function1<? super String, Unit> onSelectTemplate, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier.Companion modifier3;
        final long insetColor;
        final long insetBorder;
        long statusDot;
        Modifier modifier4;
        Composer $composer2;
        Composer $composer3;
        final Modifier modifier5;
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(onToggleDarkTheme, "onToggleDarkTheme");
        Intrinsics.checkNotNullParameter(onToggleMonitoring, "onToggleMonitoring");
        Intrinsics.checkNotNullParameter(onSelectTemplate, "onSelectTemplate");
        Composer $composer4 = $composer.startRestartGroup(-1943317316);
        ComposerKt.sourceInformation($composer4, "C(HomeScreen)P(1,5!1,3,4)76@3531L15406,70@3287L15650:HomeScreen.kt#g9oban");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(uiState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changed(darkTheme) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changedInstance(onToggleDarkTheme) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(onToggleMonitoring) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(onSelectTemplate) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
            modifier5 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1943317316, $dirty, -1, "com.example.shellshot.ui.screen.HomeScreen (HomeScreen.kt:55)");
            }
            final ShellTemplate selectedTemplate = uiState.getSelectedTemplate();
            long titleColor = darkTheme ? Color.INSTANCE.m4200getWhite0d7_KjU() : ColorKt.Color(4279310375L);
            final long secondaryTextColor = ColorKt.Color(darkTheme ? 4288782762L : 4285231744L);
            insetColor = Color.m4161copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r21) : darkTheme ? 0.06f : 0.4f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            insetBorder = Color.m4161copywmQWz5c(r23, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r23) : darkTheme ? 0.06f : 0.6f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r23) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r23) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            final boolean monitoringEnabled = uiState.getSettings().getMonitoringEnabled();
            final String statusTitle = monitoringEnabled ? "自动监听已开启" : "自动监听已关闭";
            if (uiState.getMode() == AutoShellMode.ACTIVE && uiState.getMonitoringActive()) {
                statusDot = ColorKt.Color(4281648985L);
            } else if (monitoringEnabled) {
                statusDot = ColorKt.Color(4294286859L);
            } else {
                statusDot = darkTheme ? ColorKt.Color(4284703590L) : ColorKt.Color(4291940822L);
            }
            Modifier m674paddingVpY3zN4$default = PaddingKt.m674paddingVpY3zN4$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), Dp.m6626constructorimpl(24), 0.0f, 2, null);
            PaddingValues m669PaddingValuesa9UjIt4$default = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, Dp.m6626constructorimpl(56), 0.0f, Dp.m6626constructorimpl(140), 5, null);
            Arrangement.HorizontalOrVertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(24));
            ComposerKt.sourceInformationMarkerStart($composer4, 782889482, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = $composer4.changed(titleColor) | (($dirty & 896) == 256) | (($dirty & 7168) == 2048) | $composer4.changed(statusDot) | $composer4.changed(statusTitle) | $composer4.changed(monitoringEnabled) | ((57344 & $dirty) == 16384) | $composer4.changedInstance(uiState) | $composer4.changed(secondaryTextColor) | $composer4.changed(insetColor) | $composer4.changed(insetBorder) | $composer4.changed(selectedTemplate) | ((458752 & $dirty) == 131072);
            Object rememberedValue = $composer4.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                modifier4 = modifier3;
                final long titleColor2 = titleColor;
                final long statusDot2 = statusDot;
                $composer2 = $composer4;
                rememberedValue = new Function1() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return HomeScreenKt.HomeScreen$lambda$39$lambda$38(titleColor2, darkTheme, onToggleDarkTheme, uiState, secondaryTextColor, monitoringEnabled, onToggleMonitoring, statusDot2, statusTitle, insetColor, insetBorder, selectedTemplate, onSelectTemplate, (LazyListScope) obj);
                    }
                };
                $composer4.updateRememberedValue(rememberedValue);
            } else {
                modifier4 = modifier3;
                $composer2 = $composer4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Composer $composer5 = $composer2;
            LazyDslKt.LazyColumn(m674paddingVpY3zN4$default, null, m669PaddingValuesa9UjIt4$default, false, m552spacedBy0680j_4, null, null, false, (Function1) rememberedValue, $composer5, 24960, 234);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$40(Modifier.this, uiState, darkTheme, onToggleDarkTheme, onToggleMonitoring, onSelectTemplate, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit HomeScreen$lambda$39$lambda$38(final long $titleColor, final boolean $darkTheme, final Function0 $onToggleDarkTheme, final MainUiState $uiState, final long $secondaryTextColor, final boolean $monitoringEnabled, final Function1 $onToggleMonitoring, final long $statusDot, final String $statusTitle, final long $insetColor, final long $insetBorder, final ShellTemplate $selectedTemplate, final Function1 $onSelectTemplate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-523153584, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$2($titleColor, $darkTheme, $onToggleDarkTheme, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-737000825, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$9($darkTheme, $uiState, $secondaryTextColor, $monitoringEnabled, $onToggleMonitoring, $statusDot, $statusTitle, $titleColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1777292664, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$17($darkTheme, $insetColor, $insetBorder, $titleColor, $uiState, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1477382793, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$32($darkTheme, $insetColor, $insetBorder, $selectedTemplate, $titleColor, $secondaryTextColor, $onSelectTemplate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(437090954, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$37($darkTheme, $uiState, $insetColor, $insetBorder, $secondaryTextColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$2(final long $titleColor, final boolean $darkTheme, final Function0 $onToggleDarkTheme, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C78@3590L805,78@3560L835:HomeScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-523153584, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:78)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(0, null, ComposableLambdaKt.rememberComposableLambda(1161318135, true, new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$2$lambda$1($titleColor, $darkTheme, $onToggleDarkTheme, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0180  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit HomeScreen$lambda$39$lambda$38$lambda$2$lambda$1(long r49, boolean r51, kotlin.jvm.functions.Function0 r52, androidx.compose.runtime.Composer r53, int r54) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$2$lambda$1(long, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$9(final boolean $darkTheme, final MainUiState $uiState, final long $secondaryTextColor, final boolean $monitoringEnabled, final Function1 $onToggleMonitoring, final long $statusDot, final String $statusTitle, final long $titleColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C101@4464L2932,101@4434L2962:HomeScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-737000825, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:101)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(1, null, ComposableLambdaKt.rememberComposableLambda(-1162349458, true, new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$9$lambda$8($darkTheme, $uiState, $secondaryTextColor, $monitoringEnabled, $onToggleMonitoring, $statusDot, $statusTitle, $titleColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$9$lambda$8(final boolean $darkTheme, final MainUiState $uiState, final long $secondaryTextColor, final boolean $monitoringEnabled, final Function1 $onToggleMonitoring, final long $statusDot, final String $statusTitle, final long $titleColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C107@4714L2668,102@4482L2900:HomeScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162349458, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:102)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24)), ComposableLambdaKt.rememberComposableLambda(-990518246, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$9$lambda$8$lambda$7($darkTheme, $uiState, $secondaryTextColor, $monitoringEnabled, $onToggleMonitoring, $statusDot, $statusTitle, $titleColor, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x04f7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x017c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit HomeScreen$lambda$39$lambda$38$lambda$9$lambda$8$lambda$7(boolean r117, com.example.shellshot.ui.MainUiState r118, long r119, boolean r121, kotlin.jvm.functions.Function1 r122, long r123, java.lang.String r125, long r126, androidx.compose.foundation.layout.ColumnScope r128, androidx.compose.runtime.Composer r129, int r130) {
        /*
            Method dump skipped, instructions count: 1612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$9$lambda$8$lambda$7(boolean, com.example.shellshot.ui.MainUiState, long, boolean, kotlin.jvm.functions.Function1, long, java.lang.String, long, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$17(final boolean $darkTheme, final long $insetColor, final long $insetBorder, final long $titleColor, final MainUiState $uiState, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C166@7465L2758,166@7435L2788:HomeScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1777292664, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:166)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(2, null, ComposableLambdaKt.rememberComposableLambda(2092325999, true, new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$17$lambda$16($darkTheme, $insetColor, $insetBorder, $titleColor, $uiState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$17$lambda$16(final boolean $darkTheme, final long $insetColor, final long $insetBorder, final long $titleColor, final MainUiState $uiState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C172@7715L2494,167@7483L2726:HomeScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2092325999, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:167)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24)), ComposableLambdaKt.rememberComposableLambda(-2030810085, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$17$lambda$16$lambda$15($insetColor, $insetBorder, $darkTheme, $titleColor, $uiState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x063f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0761  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x053a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x040e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit HomeScreen$lambda$39$lambda$38$lambda$17$lambda$16$lambda$15(long r115, long r117, boolean r119, long r120, com.example.shellshot.ui.MainUiState r122, androidx.compose.foundation.layout.ColumnScope r123, androidx.compose.runtime.Composer r124, int r125) {
        /*
            Method dump skipped, instructions count: 1895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$17$lambda$16$lambda$15(long, long, boolean, long, com.example.shellshot.ui.MainUiState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$32(final boolean $darkTheme, final long $insetColor, final long $insetBorder, final ShellTemplate $selectedTemplate, final long $titleColor, final long $secondaryTextColor, final Function1 $onSelectTemplate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C227@10292L5509,227@10262L5539:HomeScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1477382793, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:227)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(3, null, ComposableLambdaKt.rememberComposableLambda(1052034160, true, new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31($darkTheme, $insetColor, $insetBorder, $selectedTemplate, $titleColor, $secondaryTextColor, $onSelectTemplate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31(final boolean $darkTheme, final long $insetColor, final long $insetBorder, final ShellTemplate $selectedTemplate, final long $titleColor, final long $secondaryTextColor, final Function1 $onSelectTemplate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C233@10542L5245,228@10310L5477:HomeScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1052034160, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:228)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24)), ComposableLambdaKt.rememberComposableLambda(1223865372, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31$lambda$30($insetColor, $insetBorder, $darkTheme, $selectedTemplate, $titleColor, $secondaryTextColor, $onSelectTemplate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0bc6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x091c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x09f9  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x07ea A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x07a1  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0699  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x04f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0379 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0499  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0695  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x078f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x07d4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x090a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0916  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31$lambda$30(long r122, long r124, boolean r126, final com.example.shellshot.template.ShellTemplate r127, long r128, long r130, final kotlin.jvm.functions.Function1 r132, androidx.compose.foundation.layout.ColumnScope r133, androidx.compose.runtime.Composer r134, int r135) {
        /*
            Method dump skipped, instructions count: 3020
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31$lambda$30(long, long, boolean, com.example.shellshot.template.ShellTemplate, long, long, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$32$lambda$31$lambda$30$lambda$29$lambda$28$lambda$25$lambda$24(Function1 $onSelectTemplate, ShellTemplate $selectedTemplate) {
        $onSelectTemplate.invoke($selectedTemplate.getId());
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$37(final boolean $darkTheme, final MainUiState $uiState, final long $insetColor, final long $insetBorder, final long $secondaryTextColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C327@15870L3051,327@15840L3081:HomeScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(437090954, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:327)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(4, null, ComposableLambdaKt.rememberComposableLambda(11742321, true, new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$37$lambda$36($darkTheme, $uiState, $insetColor, $insetBorder, $secondaryTextColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$39$lambda$38$lambda$37$lambda$36(final boolean $darkTheme, final MainUiState $uiState, final long $insetColor, final long $insetBorder, final long $secondaryTextColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C333@16120L2787,328@15888L3019:HomeScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(11742321, $changed, -1, "com.example.shellshot.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:328)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24)), ComposableLambdaKt.rememberComposableLambda(183573533, true, new Function3() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$37$lambda$36$lambda$35(MainUiState.this, $darkTheme, $insetColor, $insetBorder, $secondaryTextColor, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x03c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit HomeScreen$lambda$39$lambda$38$lambda$37$lambda$36$lambda$35(com.example.shellshot.ui.MainUiState r63, boolean r64, long r65, long r67, long r69, androidx.compose.foundation.layout.ColumnScope r71, androidx.compose.runtime.Composer r72, int r73) {
        /*
            Method dump skipped, instructions count: 1037
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.HomeScreenKt.HomeScreen$lambda$39$lambda$38$lambda$37$lambda$36$lambda$35(com.example.shellshot.ui.MainUiState, boolean, long, long, long, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final void HomeDetailRow(final boolean darkTheme, final String label, String value, Composer $composer, final int $changed) {
        Composer $composer2;
        final String str;
        Composer $composer3 = $composer.startRestartGroup(-11714568);
        ComposerKt.sourceInformation($composer3, "C(HomeDetailRow)404@19050L528:HomeScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(darkTheme) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(label) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(value) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            str = value;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-11714568, $dirty, -1, "com.example.shellshot.ui.screen.HomeDetailRow (HomeScreen.kt:403)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer m3656constructorimpl = Updater.m3656constructorimpl($composer3);
            Updater.m3663setimpl(m3656constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl(m3656constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3656constructorimpl.getInserting() || !Intrinsics.areEqual(m3656constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3656constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3656constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3663setimpl(m3656constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1434638480, "C409@19229L148,414@19386L186:HomeScreen.kt#g9oban");
            int $dirty2 = $dirty;
            $composer2 = $composer3;
            TextKt.m2697Text4IGK_g(label, (Modifier) null, ColorKt.Color(darkTheme ? 4288782762L : 4285231744L), TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 0, 131058);
            str = value;
            TextKt.m2697Text4IGK_g(str, (Modifier) null, darkTheme ? Color.INSTANCE.m4200getWhite0d7_KjU() : ColorKt.Color(4279310375L), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 6) & 14) | 199680, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.HomeScreenKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeDetailRow$lambda$42(darkTheme, label, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String processingStatusLabel(MainUiState uiState) {
        ProcessingStatus status;
        ProcessingResult lastProcessingResult = uiState.getLastProcessingResult();
        if (lastProcessingResult == null || (status = lastProcessingResult.getStatus()) == null) {
            return "暂无记录";
        }
        switch (WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1:
                return "处理成功";
            case 2:
                return "处理失败";
            case 3:
                return "已跳过";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final long processingStatusColor(MainUiState uiState) {
        ProcessingStatus status;
        ProcessingResult lastProcessingResult = uiState.getLastProcessingResult();
        if (lastProcessingResult == null || (status = lastProcessingResult.getStatus()) == null) {
            return ColorKt.Color(4285231744L);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1:
                return ColorKt.Color(4279286145L);
            case 2:
                return ColorKt.Color(4293870660L);
            case 3:
                return ColorKt.Color(4294286859L);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final long processingStatusBackground(MainUiState uiState, boolean darkTheme) {
        long m4161copywmQWz5c;
        long color = processingStatusColor(uiState);
        m4161copywmQWz5c = Color.m4161copywmQWz5c(color, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(color) : darkTheme ? 0.18f : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(color) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(color) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(color) : 0.0f);
        return m4161copywmQWz5c;
    }

    private static final String homeStatusSubtitle(MainUiState uiState) {
        if (!uiState.getHasCoreStartPermissions()) {
            return "核心权限还没有准备好。打开监听时会先引导你完成授权。";
        }
        if (uiState.getMode() == AutoShellMode.ACTIVE && uiState.getMonitoringActive()) {
            return "AutoShellService 正在稳定运行，当前会按照设置里的截图目录自动监听。";
        }
        if (uiState.getMode() == AutoShellMode.SUSPEND_GAME) {
            return "检测到游戏在前台，当前已自动暂停处理，离开游戏后会自动恢复。";
        }
        if (uiState.getMode() == AutoShellMode.SUSPEND_SCREEN_OFF) {
            return "当前处于息屏状态，监听暂时挂起，解锁后会自动恢复补扫。";
        }
        if (uiState.getSettings().getMonitoringEnabled()) {
            return "自动监听已启用，服务会在满足条件时恢复到活动状态。";
        }
        return "准备就绪，打开上面的开关后就会开始监听当前截图目录。";
    }
}
