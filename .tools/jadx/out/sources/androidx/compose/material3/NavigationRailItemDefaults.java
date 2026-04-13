package androidx.compose.material3;

import androidx.compose.material3.tokens.NavigationRailTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* compiled from: NavigationRail.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\tJD\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011JX\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/NavigationRailItemDefaults;", "", "()V", "defaultNavigationRailItemColors", "Landroidx/compose/material3/NavigationRailItemColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultNavigationRailItemColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationRailItemColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationRailItemColors;", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "indicatorColor", "unselectedIconColor", "unselectedTextColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationRailItemColors;", "disabledIconColor", "disabledTextColor", "colors-69fazGs", "(JJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationRailItemColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NavigationRailItemDefaults {
    public static final int $stable = 0;
    public static final NavigationRailItemDefaults INSTANCE = new NavigationRailItemDefaults();

    private NavigationRailItemDefaults() {
    }

    public final NavigationRailItemColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2014332261, "C(colors)308@13112L11:NavigationRail.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2014332261, $changed, -1, "androidx.compose.material3.NavigationRailItemDefaults.colors (NavigationRail.kt:308)");
        }
        NavigationRailItemColors defaultNavigationRailItemColors$material3_release = getDefaultNavigationRailItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultNavigationRailItemColors$material3_release;
    }

    /* renamed from: colors-69fazGs, reason: not valid java name */
    public final NavigationRailItemColors m2337colors69fazGs(long selectedIconColor, long selectedTextColor, long indicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor, Composer $composer, int $changed, int i) {
        long disabledIconColor2;
        long disabledTextColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, -2104358508, "C(colors)P(3:c#ui.graphics.Color,4:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)325@14138L5,326@14222L5,327@14303L5,328@14386L5,329@14474L5,333@14705L11:NavigationRail.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveIconColor(), $composer, 6) : selectedIconColor;
        long selectedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveLabelTextColor(), $composer, 6) : selectedTextColor;
        long indicatorColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6) : indicatorColor;
        long unselectedIconColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getInactiveIconColor(), $composer, 6) : unselectedIconColor;
        long unselectedTextColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getInactiveLabelTextColor(), $composer, 6) : unselectedTextColor;
        if ((i & 32) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(unselectedIconColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unselectedIconColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(unselectedIconColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unselectedIconColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unselectedIconColor2) : 0.0f);
            disabledIconColor2 = m4161copywmQWz5c2;
        } else {
            disabledIconColor2 = disabledIconColor;
        }
        if ((i & 64) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(unselectedTextColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unselectedTextColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(unselectedTextColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unselectedTextColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unselectedTextColor2) : 0.0f);
            disabledTextColor2 = m4161copywmQWz5c;
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2104358508, $changed, -1, "androidx.compose.material3.NavigationRailItemDefaults.colors (NavigationRail.kt:333)");
        }
        NavigationRailItemColors m2326copy4JmcsL4 = getDefaultNavigationRailItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2326copy4JmcsL4(selectedIconColor2, selectedTextColor2, indicatorColor2, unselectedIconColor2, unselectedTextColor2, disabledIconColor2, disabledTextColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2326copy4JmcsL4;
    }

    public final NavigationRailItemColors getDefaultNavigationRailItemColors$material3_release(ColorScheme $this$defaultNavigationRailItemColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        NavigationRailItemColors it = $this$defaultNavigationRailItemColors.getDefaultNavigationRailItemColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getActiveIconColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getActiveLabelTextColor());
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getActiveIndicatorColor());
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getInactiveIconColor());
            long fromToken5 = ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getInactiveLabelTextColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getInactiveIconColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r15) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultNavigationRailItemColors, NavigationRailTokens.INSTANCE.getInactiveLabelTextColor())) : 0.0f);
            NavigationRailItemColors it2 = new NavigationRailItemColors(fromToken, fromToken2, fromToken3, fromToken4, fromToken5, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultNavigationRailItemColors.setDefaultNavigationRailItemColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with disabledIconColor and disabledTextColor")
    /* renamed from: colors-zjMxDiM, reason: not valid java name */
    public final /* synthetic */ NavigationRailItemColors m2338colorszjMxDiM(long selectedIconColor, long selectedTextColor, long indicatorColor, long unselectedIconColor, long unselectedTextColor, Composer $composer, int $changed, int i) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, 1621601574, "C(colors)P(1:c#ui.graphics.Color,2:c#ui.graphics.Color,0:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)370@16602L5,371@16686L5,372@16767L5,373@16850L5,374@16938L5:NavigationRail.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveIconColor(), $composer, 6) : selectedIconColor;
        long selectedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveLabelTextColor(), $composer, 6) : selectedTextColor;
        long indicatorColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6) : indicatorColor;
        long unselectedIconColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getInactiveIconColor(), $composer, 6) : unselectedIconColor;
        long unselectedTextColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(NavigationRailTokens.INSTANCE.getInactiveLabelTextColor(), $composer, 6) : unselectedTextColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1621601574, $changed, -1, "androidx.compose.material3.NavigationRailItemDefaults.colors (NavigationRail.kt:376)");
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(unselectedIconColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unselectedIconColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(unselectedIconColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unselectedIconColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unselectedIconColor2) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(unselectedTextColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unselectedTextColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(unselectedTextColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unselectedTextColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unselectedTextColor2) : 0.0f);
        NavigationRailItemColors navigationRailItemColors = new NavigationRailItemColors(selectedIconColor2, selectedTextColor2, indicatorColor2, unselectedIconColor2, unselectedTextColor2, m4161copywmQWz5c, m4161copywmQWz5c2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return navigationRailItemColors;
    }
}
