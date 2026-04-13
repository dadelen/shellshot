package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import com.example.shellshot.data.AppPrefs;
import kotlin.Metadata;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchDefaults;", "", "<init>", "()V", "HighPerformance", "Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;", "getHighPerformance", "()Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;", "HighFidelity", "getHighFidelity", "Standard", "getStandard", "AppleEnhanced", "getAppleEnhanced", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class LegacyLiquidGlassSwitchDefaults {
    public static final int $stable = 0;
    public static final LegacyLiquidGlassSwitchDefaults INSTANCE = new LegacyLiquidGlassSwitchDefaults();
    private static final LegacyLiquidGlassSwitchSpec HighPerformance = new LegacyLiquidGlassSwitchSpec(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0), 0.96f, 14.0f, 0.88f, Dp.m6626constructorimpl(999), Dp.m6626constructorimpl(3), Dp.m6626constructorimpl(82), Dp.m6626constructorimpl(48), Dp.m6626constructorimpl(34), Dp.m6626constructorimpl((float) 1.5d), 0.988f, 220, 0.5f, ColorKt.Color(4282569306L), ColorKt.Color(4283947888L), ColorKt.Color(4293323761L), null);
    private static final LegacyLiquidGlassSwitchSpec HighFidelity = new LegacyLiquidGlassSwitchSpec(Dp.m6626constructorimpl(14), Dp.m6626constructorimpl(10), 0.93f, 12.0f, 0.94f, Dp.m6626constructorimpl(999), Dp.m6626constructorimpl(4), Dp.m6626constructorimpl(84), Dp.m6626constructorimpl(50), Dp.m6626constructorimpl(36), Dp.m6626constructorimpl(2), 0.984f, AppPrefs.MAX_RECENT_PROCESSED_KEYS, 0.46f, ColorKt.Color(4282635354L), ColorKt.Color(4284079474L), ColorKt.Color(4293389554L), null);
    private static final LegacyLiquidGlassSwitchSpec Standard = HighPerformance;
    private static final LegacyLiquidGlassSwitchSpec AppleEnhanced = HighFidelity;

    private LegacyLiquidGlassSwitchDefaults() {
    }

    public final LegacyLiquidGlassSwitchSpec getHighPerformance() {
        return HighPerformance;
    }

    public final LegacyLiquidGlassSwitchSpec getHighFidelity() {
        return HighFidelity;
    }

    public final LegacyLiquidGlassSwitchSpec getStandard() {
        return Standard;
    }

    public final LegacyLiquidGlassSwitchSpec getAppleEnhanced() {
        return AppleEnhanced;
    }
}
