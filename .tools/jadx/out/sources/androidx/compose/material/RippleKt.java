package androidx.compose.material;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Ripple.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a0\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a0\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\u001bH\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b8GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b8GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"DarkThemeRippleAlpha", "Landroidx/compose/material/ripple/RippleAlpha;", "DefaultBoundedRipple", "Landroidx/compose/material/RippleNodeFactory;", "DefaultUnboundedRipple", "LightThemeHighContrastRippleAlpha", "LightThemeLowContrastRippleAlpha", "LocalRippleConfiguration", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/RippleConfiguration;", "getLocalRippleConfiguration$annotations", "()V", "getLocalRippleConfiguration", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalUseFallbackRippleImplementation", "", "getLocalUseFallbackRippleImplementation$annotations", "getLocalUseFallbackRippleImplementation", "ripple", "Landroidx/compose/foundation/IndicationNodeFactory;", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "bounded", "radius", "Landroidx/compose/ui/unit/Dp;", "ripple-wH6b6FI", "(Landroidx/compose/ui/graphics/ColorProducer;ZF)Landroidx/compose/foundation/IndicationNodeFactory;", "Landroidx/compose/ui/graphics/Color;", "ripple-H2RKhps", "(ZFJ)Landroidx/compose/foundation/IndicationNodeFactory;", "rippleOrFallbackImplementation", "Landroidx/compose/foundation/Indication;", "rippleOrFallbackImplementation-9IZ8Weo", "(ZFJLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/Indication;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RippleKt {
    private static final ProvidableCompositionLocal<Boolean> LocalUseFallbackRippleImplementation = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.material.RippleKt$LocalUseFallbackRippleImplementation$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    private static final ProvidableCompositionLocal<RippleConfiguration> LocalRippleConfiguration = CompositionLocalKt.compositionLocalOf$default(null, new Function0<RippleConfiguration>() { // from class: androidx.compose.material.RippleKt$LocalRippleConfiguration$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RippleConfiguration invoke() {
            return new RippleConfiguration(0L, null, 3, null);
        }
    }, 1, null);
    private static final RippleNodeFactory DefaultBoundedRipple = new RippleNodeFactory(true, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), (DefaultConstructorMarker) null);
    private static final RippleNodeFactory DefaultUnboundedRipple = new RippleNodeFactory(false, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), (DefaultConstructorMarker) null);
    private static final RippleAlpha LightThemeHighContrastRippleAlpha = new RippleAlpha(0.16f, 0.24f, 0.08f, 0.24f);
    private static final RippleAlpha LightThemeLowContrastRippleAlpha = new RippleAlpha(0.08f, 0.12f, 0.04f, 0.12f);
    private static final RippleAlpha DarkThemeRippleAlpha = new RippleAlpha(0.08f, 0.12f, 0.04f, 0.1f);

    public static /* synthetic */ void getLocalRippleConfiguration$annotations() {
    }

    public static /* synthetic */ void getLocalUseFallbackRippleImplementation$annotations() {
    }

    /* renamed from: ripple-H2RKhps$default, reason: not valid java name */
    public static /* synthetic */ IndicationNodeFactory m1627rippleH2RKhps$default(boolean z, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            f = Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM();
        }
        if ((i & 4) != 0) {
            j = Color.INSTANCE.m4199getUnspecified0d7_KjU();
        }
        return m1626rippleH2RKhps(z, f, j);
    }

    /* renamed from: ripple-H2RKhps, reason: not valid java name */
    public static final IndicationNodeFactory m1626rippleH2RKhps(boolean bounded, float radius, long color) {
        if (Dp.m6631equalsimpl0(radius, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) && Color.m4164equalsimpl0(color, Color.INSTANCE.m4199getUnspecified0d7_KjU())) {
            return bounded ? DefaultBoundedRipple : DefaultUnboundedRipple;
        }
        return new RippleNodeFactory(bounded, radius, color, (DefaultConstructorMarker) null);
    }

    /* renamed from: ripple-wH6b6FI$default, reason: not valid java name */
    public static /* synthetic */ IndicationNodeFactory m1629ripplewH6b6FI$default(ColorProducer colorProducer, boolean z, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            f = Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM();
        }
        return m1628ripplewH6b6FI(colorProducer, z, f);
    }

    /* renamed from: ripple-wH6b6FI, reason: not valid java name */
    public static final IndicationNodeFactory m1628ripplewH6b6FI(ColorProducer color, boolean bounded, float radius) {
        return new RippleNodeFactory(bounded, radius, color, (DefaultConstructorMarker) null);
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalUseFallbackRippleImplementation() {
        return LocalUseFallbackRippleImplementation;
    }

    public static final ProvidableCompositionLocal<RippleConfiguration> getLocalRippleConfiguration() {
        return LocalRippleConfiguration;
    }

    /* renamed from: rippleOrFallbackImplementation-9IZ8Weo, reason: not valid java name */
    public static final Indication m1630rippleOrFallbackImplementation9IZ8Weo(boolean bounded, float radius, long color, Composer $composer, int $changed, int i) {
        float radius2;
        long color2;
        Composer $composer2;
        IndicationNodeFactory m1626rippleH2RKhps;
        ComposerKt.sourceInformationMarkerStart($composer, -58830494, "C(rippleOrFallbackImplementation)P(!1,2:c#ui.unit.Dp,1:c#ui.graphics.Color)265@11741L7:Ripple.kt#jmzs0o");
        boolean bounded2 = (i & 1) != 0 ? true : bounded;
        if ((i & 2) != 0) {
            float radius3 = Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM();
            radius2 = radius3;
        } else {
            radius2 = radius;
        }
        if ((i & 4) != 0) {
            long color3 = Color.INSTANCE.m4199getUnspecified0d7_KjU();
            color2 = color3;
        } else {
            color2 = color;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-58830494, $changed, -1, "androidx.compose.material.rippleOrFallbackImplementation (Ripple.kt:264)");
        }
        ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalUseFallbackRippleImplementation;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (((Boolean) consume).booleanValue()) {
            $composer.startReplaceGroup(96412190);
            ComposerKt.sourceInformation($composer, "266@11793L38");
            $composer2 = $composer;
            m1626rippleH2RKhps = androidx.compose.material.ripple.RippleKt.m1751rememberRipple9IZ8Weo(bounded2, radius2, color2, $composer2, ($changed & 14) | ($changed & 112) | ($changed & 896), 0);
            $composer2.endReplaceGroup();
        } else {
            $composer2 = $composer;
            $composer2.startReplaceGroup(96503175);
            $composer2.endReplaceGroup();
            m1626rippleH2RKhps = m1626rippleH2RKhps(bounded2, radius2, color2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return m1626rippleH2RKhps;
    }
}
