package com.example.shellshot.ui.theme;

import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Shapes;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"LightColors", "Landroidx/compose/material3/ColorScheme;", "DarkColors", "AppShapes", "Landroidx/compose/material3/Shapes;", "ShellShotTheme", "", "darkTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ThemeKt {
    private static final Shapes AppShapes;
    private static final ColorScheme DarkColors;
    private static final ColorScheme LightColors = ColorSchemeKt.m1940lightColorSchemeCXl9yA$default(ColorKt.getAccentBlue(), ColorKt.getTextPrimary(), ColorKt.getAccentBlueSoft(), 0, 0, ColorKt.getAccentGreen(), ColorKt.getTextPrimary(), ColorKt.getAccentGreenSoft(), 0, ColorKt.getAccentAmber(), 0, 0, 0, ColorKt.getBackgroundPrimary(), ColorKt.getTextPrimary(), ColorKt.getCardBackgroundStrong(), ColorKt.getTextPrimary(), ColorKt.getCardBackground(), ColorKt.getTextSecondary(), 0, 0, 0, ColorKt.getAccentRed(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4711144, 15, null);

    static final Unit ShellShotTheme$lambda$1(boolean z, Function2 function2, int i, int i2, Composer composer, int i3) {
        ShellShotTheme(z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long darkAccentBlue = ColorKt.getDarkAccentBlue();
        long darkTextPrimary = ColorKt.getDarkTextPrimary();
        long darkAccentGreen = ColorKt.getDarkAccentGreen();
        long darkTextPrimary2 = ColorKt.getDarkTextPrimary();
        long darkAccentAmber = ColorKt.getDarkAccentAmber();
        long darkBackgroundPrimary = ColorKt.getDarkBackgroundPrimary();
        long darkCardBackgroundStrong = ColorKt.getDarkCardBackgroundStrong();
        long darkCardBackground = ColorKt.getDarkCardBackground();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r37, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r37) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r37) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r37) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentBlue()) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r37, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r37) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r37) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r37) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentGreen()) : 0.0f);
        DarkColors = ColorSchemeKt.m1936darkColorSchemeCXl9yA$default(darkAccentBlue, darkTextPrimary, m4161copywmQWz5c, 0L, 0L, darkAccentGreen, darkTextPrimary2, m4161copywmQWz5c2, 0L, darkAccentAmber, 0L, 0L, 0L, darkBackgroundPrimary, ColorKt.getDarkTextPrimary(), darkCardBackgroundStrong, ColorKt.getDarkTextPrimary(), darkCardBackground, ColorKt.getDarkTextSecondary(), 0L, 0L, 0L, ColorKt.getDarkAccentRed(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -4711144, 15, null);
        AppShapes = new Shapes(null, RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(20)), RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(22)), RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(28)), null, 17, null);
    }

    public static final void ShellShotTheme(final boolean darkTheme, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1837933827);
        ComposerKt.sourceInformation($composer2, "C(ShellShotTheme)P(1)59@1991L213,59@1928L276:Theme.kt#fj5fpg");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ((i & 1) == 0 && $composer2.changed(darkTheme)) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "54@1781L21");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
            } else if ((i & 1) != 0) {
                darkTheme = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
                $dirty &= -15;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1837933827, $dirty, -1, "com.example.shellshot.ui.theme.ShellShotTheme (Theme.kt:56)");
            }
            ShellShotTokens tokens = darkTheme ? DesignTokensKt.getDarkShellShotTokens() : DesignTokensKt.getLightShellShotTokens();
            CompositionLocalKt.CompositionLocalProvider(DesignTokensKt.getLocalShellShotTokens().provides(tokens), ComposableLambdaKt.rememberComposableLambda(-833341373, true, new Function2() { // from class: com.example.shellshot.ui.theme.ThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.ShellShotTheme$lambda$0(darkTheme, content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.theme.ThemeKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.ShellShotTheme$lambda$1(darkTheme, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ShellShotTheme$lambda$0(boolean $darkTheme, Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C60@2001L197:Theme.kt#fj5fpg");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-833341373, $changed, -1, "com.example.shellshot.ui.theme.ShellShotTheme.<anonymous> (Theme.kt:60)");
            }
            MaterialThemeKt.MaterialTheme($darkTheme ? DarkColors : LightColors, AppShapes, TypeKt.getAppTypography(), $content, $composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
