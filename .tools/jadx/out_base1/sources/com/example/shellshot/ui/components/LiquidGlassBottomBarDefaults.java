package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/example/shellshot/ui/components/LiquidGlassBottomBarDefaults;", "", "<init>", "()V", "Standard", "Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;", "getStandard", "()Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;", "AppleEnhanced", "getAppleEnhanced", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class LiquidGlassBottomBarDefaults {
    public static final int $stable = 0;
    public static final LiquidGlassBottomBarDefaults INSTANCE = new LiquidGlassBottomBarDefaults();
    private static final LiquidGlassBottomBarSpec Standard = new LiquidGlassBottomBarSpec(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0, 16383, null);
    private static final LiquidGlassBottomBarSpec AppleEnhanced = new LiquidGlassBottomBarSpec(Dp.m6626constructorimpl(34), 0.74f, 10.0f, 0.96f, Dp.m6626constructorimpl(38), Dp.m6626constructorimpl(8), 0.46f, Dp.m6626constructorimpl(8), Dp.m6626constructorimpl(32), Dp.m6626constructorimpl(88), ColorKt.Color(4279771187L), ColorKt.Color(4284245630L), ColorKt.Color(4291555327L), ColorKt.Color(4294438888L), null);

    private LiquidGlassBottomBarDefaults() {
    }

    public final LiquidGlassBottomBarSpec getStandard() {
        return Standard;
    }

    public final LiquidGlassBottomBarSpec getAppleEnhanced() {
        return AppleEnhanced;
    }
}
