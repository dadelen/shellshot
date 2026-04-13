package com.example.shellshot.ui.theme;

import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DesignTokens.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"LightShellShotTokens", "Lcom/example/shellshot/ui/theme/ShellShotTokens;", "getLightShellShotTokens", "()Lcom/example/shellshot/ui/theme/ShellShotTokens;", "DarkShellShotTokens", "getDarkShellShotTokens", "LocalShellShotTokens", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalShellShotTokens", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "shellShotTokens", "Landroidx/compose/material3/MaterialTheme;", "getShellShotTokens", "(Landroidx/compose/material3/MaterialTheme;Landroidx/compose/runtime/Composer;I)Lcom/example/shellshot/ui/theme/ShellShotTokens;", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DesignTokensKt {
    private static final ShellShotTokens DarkShellShotTokens;
    private static final ShellShotTokens LightShellShotTokens = new ShellShotTokens(new ShellShotColorTokens(ColorKt.getBackgroundPrimary(), ColorKt.getBackgroundSecondary(), ColorKt.getCardBackground(), ColorKt.getCardBackgroundStrong(), ColorKt.getCardStroke(), ColorKt.getTextPrimary(), ColorKt.getTextSecondary(), ColorKt.getTextTertiary(), ColorKt.getAccentBlue(), ColorKt.getAccentBlueSoft(), ColorKt.getAccentGreen(), ColorKt.getAccentGreenSoft(), ColorKt.getAccentAmber(), ColorKt.getAccentAmberSoft(), ColorKt.getAccentRed(), ColorKt.getAccentRedSoft(), ColorKt.getDividerColor(), ColorKt.getNavBarBackground(), ColorKt.getSelectedItemBackground(), null), null, new ShellShotShadowTokens(new ShellShotShadowStyle(Dp.m6626constructorimpl(2), ColorKt.getShadowAmbient(), ColorKt.getShadowSpot(), null), new ShellShotShadowStyle(Dp.m6626constructorimpl(5), ColorKt.getShadowAmbient(), ColorKt.getShadowSpot(), null), new ShellShotShadowStyle(Dp.m6626constructorimpl(10), ColorKt.getShadowAmbient(), ColorKt.getShadowSpot(), null)), null, 10, null);
    private static final ProvidableCompositionLocal<ShellShotTokens> LocalShellShotTokens;

    static {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long darkBackgroundPrimary = ColorKt.getDarkBackgroundPrimary();
        long darkBackgroundSecondary = ColorKt.getDarkBackgroundSecondary();
        long darkCardBackground = ColorKt.getDarkCardBackground();
        long darkCardBackgroundStrong = ColorKt.getDarkCardBackgroundStrong();
        long darkCardStroke = ColorKt.getDarkCardStroke();
        long darkTextPrimary = ColorKt.getDarkTextPrimary();
        long darkTextSecondary = ColorKt.getDarkTextSecondary();
        long darkTextTertiary = ColorKt.getDarkTextTertiary();
        long darkAccentBlue = ColorKt.getDarkAccentBlue();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r21) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentBlue()) : 0.0f);
        long darkAccentGreen = ColorKt.getDarkAccentGreen();
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r25, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r25) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r25) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r25) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentGreen()) : 0.0f);
        long darkAccentAmber = ColorKt.getDarkAccentAmber();
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentAmber()) : 0.0f);
        long darkAccentRed = ColorKt.getDarkAccentRed();
        m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r33, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r33) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r33) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r33) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.getDarkAccentRed()) : 0.0f);
        DarkShellShotTokens = new ShellShotTokens(new ShellShotColorTokens(darkBackgroundPrimary, darkBackgroundSecondary, darkCardBackground, darkCardBackgroundStrong, darkCardStroke, darkTextPrimary, darkTextSecondary, darkTextTertiary, darkAccentBlue, m4161copywmQWz5c, darkAccentGreen, m4161copywmQWz5c2, darkAccentAmber, m4161copywmQWz5c3, darkAccentRed, m4161copywmQWz5c4, ColorKt.getDarkDividerColor(), ColorKt.getDarkNavBarBackground(), ColorKt.getDarkSelectedItemBackground(), null), null, new ShellShotShadowTokens(new ShellShotShadowStyle(Dp.m6626constructorimpl(2), ColorKt.getDarkShadowAmbient(), ColorKt.getDarkShadowSpot(), null), new ShellShotShadowStyle(Dp.m6626constructorimpl(5), ColorKt.getDarkShadowAmbient(), ColorKt.getDarkShadowSpot(), null), new ShellShotShadowStyle(Dp.m6626constructorimpl(10), ColorKt.getDarkShadowAmbient(), ColorKt.getDarkShadowSpot(), null)), null, 10, null);
        LocalShellShotTokens = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: com.example.shellshot.ui.theme.DesignTokensKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ShellShotTokens shellShotTokens;
                shellShotTokens = DesignTokensKt.LightShellShotTokens;
                return shellShotTokens;
            }
        });
    }

    public static final ShellShotTokens getLightShellShotTokens() {
        return LightShellShotTokens;
    }

    public static final ShellShotTokens getDarkShellShotTokens() {
        return DarkShellShotTokens;
    }

    public static final ProvidableCompositionLocal<ShellShotTokens> getLocalShellShotTokens() {
        return LocalShellShotTokens;
    }

    public static final ShellShotTokens getShellShotTokens(MaterialTheme $this$shellShotTokens, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$shellShotTokens, "<this>");
        ComposerKt.sourceInformationMarkerStart($composer, 1889900315, "C(<get-shellShotTokens>)164@5123L7:DesignTokens.kt#fj5fpg");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1889900315, $changed, -1, "com.example.shellshot.ui.theme.<get-shellShotTokens> (DesignTokens.kt:164)");
        }
        ProvidableCompositionLocal<ShellShotTokens> providableCompositionLocal = LocalShellShotTokens;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ShellShotTokens shellShotTokens = (ShellShotTokens) consume;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shellShotTokens;
    }
}
