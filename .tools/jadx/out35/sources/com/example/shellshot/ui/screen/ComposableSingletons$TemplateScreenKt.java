package com.example.shellshot.ui.screen;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ComposableSingletons$TemplateScreenKt {
    public static final ComposableSingletons$TemplateScreenKt INSTANCE = new ComposableSingletons$TemplateScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$857340603 = ComposableLambdaKt.composableLambdaInstance(857340603, false, new Function2() { // from class: com.example.shellshot.ui.screen.ComposableSingletons$TemplateScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TemplateScreenKt.lambda_857340603$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-107356450, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f89lambda$107356450 = ComposableLambdaKt.composableLambdaInstance(-107356450, false, new Function3() { // from class: com.example.shellshot.ui.screen.ComposableSingletons$TemplateScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TemplateScreenKt.lambda__107356450$lambda$1((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: getLambda$-107356450$app_debug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7028getLambda$107356450$app_debug() {
        return f89lambda$107356450;
    }

    public final Function2<Composer, Integer, Unit> getLambda$857340603$app_debug() {
        return lambda$857340603;
    }

    static final Unit lambda_857340603$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C444@17670L12:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(857340603, $changed, -1, "com.example.shellshot.ui.screen.ComposableSingletons$TemplateScreenKt.lambda$857340603.<anonymous> (TemplateScreen.kt:444)");
            }
            TextKt.m2697Text4IGK_g("模板名称", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__107356450$lambda$1(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C551@21015L150:TemplateScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-107356450, $changed, -1, "com.example.shellshot.ui.screen.ComposableSingletons$TemplateScreenKt.lambda$-107356450.<anonymous> (TemplateScreen.kt:551)");
            }
            TextKt.m2697Text4IGK_g("确认删除", (Modifier) null, 0L, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
