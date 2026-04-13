package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
import androidx.compose.material3.tokens.ScrimTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: NavigationDrawer.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R \u0010\u000e\u001a\u00020\u000f8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u000f8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\u00020\u000f8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\u00020\u000f8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u001d\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006!"}, d2 = {"Landroidx/compose/material3/DrawerDefaults;", "", "()V", "DismissibleDrawerElevation", "Landroidx/compose/ui/unit/Dp;", "getDismissibleDrawerElevation-D9Ej5fM", "()F", "F", "MaximumDrawerWidth", "getMaximumDrawerWidth-D9Ej5fM", "ModalDrawerElevation", "getModalDrawerElevation-D9Ej5fM", "PermanentDrawerElevation", "getPermanentDrawerElevation-D9Ej5fM", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor$annotations", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "modalContainerColor", "getModalContainerColor", "scrimColor", "getScrimColor", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "standardContainerColor", "getStandardContainerColor", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class DrawerDefaults {
    public static final int $stable = 0;
    public static final DrawerDefaults INSTANCE = new DrawerDefaults();
    private static final float ModalDrawerElevation = ElevationTokens.INSTANCE.m3176getLevel0D9Ej5fM();
    private static final float PermanentDrawerElevation = NavigationDrawerTokens.INSTANCE.m3331getStandardContainerElevationD9Ej5fM();
    private static final float DismissibleDrawerElevation = NavigationDrawerTokens.INSTANCE.m3331getStandardContainerElevationD9Ej5fM();
    private static final float MaximumDrawerWidth = NavigationDrawerTokens.INSTANCE.m3328getContainerWidthD9Ej5fM();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use standardContainerColor or modalContainerColor instead.", replaceWith = @ReplaceWith(expression = "standardContainerColor", imports = {}))
    public static /* synthetic */ void getContainerColor$annotations() {
    }

    private DrawerDefaults() {
    }

    /* renamed from: getModalDrawerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2080getModalDrawerElevationD9Ej5fM() {
        return ModalDrawerElevation;
    }

    /* renamed from: getPermanentDrawerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2081getPermanentDrawerElevationD9Ej5fM() {
        return PermanentDrawerElevation;
    }

    /* renamed from: getDismissibleDrawerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2078getDismissibleDrawerElevationD9Ej5fM() {
        return DismissibleDrawerElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 928378975, "C859@35504L5:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(928378975, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-shape> (NavigationDrawer.kt:859)");
        }
        Shape value = ShapesKt.getValue(NavigationDrawerTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final long getScrimColor(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -1055074989, "C863@35676L5:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1055074989, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-scrimColor> (NavigationDrawer.kt:863)");
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r2) : 0.32f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(ScrimTokens.INSTANCE.getContainerColor(), $composer, 6)) : 0.0f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m4161copywmQWz5c;
    }

    public final long getContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1797317261, "C872@36094L5:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1797317261, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-containerColor> (NavigationDrawer.kt:872)");
        }
        long value = ColorSchemeKt.getValue(NavigationDrawerTokens.INSTANCE.getStandardContainerColor(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final long getStandardContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -125949421, "C878@36328L5:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-125949421, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-standardContainerColor> (NavigationDrawer.kt:878)");
        }
        long value = ColorSchemeKt.getValue(NavigationDrawerTokens.INSTANCE.getStandardContainerColor(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final long getModalContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 706424321, "C882@36506L5:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(706424321, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-modalContainerColor> (NavigationDrawer.kt:882)");
        }
        long value = ColorSchemeKt.getValue(NavigationDrawerTokens.INSTANCE.getModalContainerColor(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* renamed from: getMaximumDrawerWidth-D9Ej5fM, reason: not valid java name */
    public final float m2079getMaximumDrawerWidthD9Ej5fM() {
        return MaximumDrawerWidth;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -909973510, "C891@36788L29:NavigationDrawer.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-909973510, $changed, -1, "androidx.compose.material3.DrawerDefaults.<get-windowInsets> (NavigationDrawer.kt:891)");
        }
        WindowInsets m743onlybOOhFvg = WindowInsetsKt.m743onlybOOhFvg(SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, $composer, 6), WindowInsetsSides.m755plusgK_yJZ4(WindowInsetsSides.INSTANCE.m770getVerticalJoeWqyM(), WindowInsetsSides.INSTANCE.m768getStartJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m743onlybOOhFvg;
    }
}
