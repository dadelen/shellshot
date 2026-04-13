package com.example.shellshot.ui.components;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.rounded.ArticleKt;
import androidx.compose.material.icons.rounded.DashboardCustomizeKt;
import androidx.compose.material.icons.rounded.HomeKt;
import androidx.compose.material.icons.rounded.SettingsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BottomDock.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ComposableSingletons$BottomDockKt {
    public static final ComposableSingletons$BottomDockKt INSTANCE = new ComposableSingletons$BottomDockKt();

    /* renamed from: lambda$-1687026242, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f87lambda$1687026242 = ComposableLambdaKt.composableLambdaInstance(-1687026242, false, new Function2() { // from class: com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$BottomDockKt.lambda__1687026242$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-1969472556, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f88lambda$1969472556 = ComposableLambdaKt.composableLambdaInstance(-1969472556, false, new Function2() { // from class: com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$BottomDockKt.lambda__1969472556$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: getLambda$-1687026242$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6941getLambda$1687026242$app_debug() {
        return f87lambda$1687026242;
    }

    /* renamed from: getLambda$-1969472556$app_debug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6942getLambda$1969472556$app_debug() {
        return f88lambda$1969472556;
    }

    static final Unit lambda__1687026242$lambda$2(Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C559@23475L2,550@22944L544:BottomDock.kt#e5eupx");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1687026242, $changed, -1, "com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt.lambda$-1687026242.<anonymous> (BottomDock.kt:550)");
            }
            List listOf = CollectionsKt.listOf((Object[]) new NavItem[]{new NavItem("home", "首页", HomeKt.getHome(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("templates", "模板", DashboardCustomizeKt.getDashboardCustomize(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("settings", "设置", SettingsKt.getSettings(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("logs", "日志", ArticleKt.getArticle(Icons.AutoMirrored.Rounded.INSTANCE), true)});
            ComposerKt.sourceInformationMarkerStart($composer, -2029530656, "CC(remember):BottomDock.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function1() { // from class: com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ComposableSingletons$BottomDockKt.lambda__1687026242$lambda$2$lambda$1$lambda$0((String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BottomDockKt.BottomDock(listOf, "settings", (Function1) obj, null, false, $composer, 25008, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1687026242$lambda$2$lambda$1$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit lambda__1969472556$lambda$5(Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C581@24244L2,572@23714L543:BottomDock.kt#e5eupx");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1969472556, $changed, -1, "com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt.lambda$-1969472556.<anonymous> (BottomDock.kt:572)");
            }
            List listOf = CollectionsKt.listOf((Object[]) new NavItem[]{new NavItem("home", "首页", HomeKt.getHome(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("templates", "模板", DashboardCustomizeKt.getDashboardCustomize(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("settings", "设置", SettingsKt.getSettings(Icons.Rounded.INSTANCE), false, 8, null), new NavItem("logs", "日志", ArticleKt.getArticle(Icons.AutoMirrored.Rounded.INSTANCE), true)});
            ComposerKt.sourceInformationMarkerStart($composer, 1417468502, "CC(remember):BottomDock.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function1() { // from class: com.example.shellshot.ui.components.ComposableSingletons$BottomDockKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ComposableSingletons$BottomDockKt.lambda__1969472556$lambda$5$lambda$4$lambda$3((String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BottomDockKt.BottomDock(listOf, "settings", (Function1) obj, null, true, $composer, 25008, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1969472556$lambda$5$lambda$4$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }
}
