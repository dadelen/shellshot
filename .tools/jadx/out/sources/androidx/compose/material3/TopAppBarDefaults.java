package androidx.compose.material3;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallCenteredTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u001f\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010 JD\u0010\u001f\u001a\u00020\u00152\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(JK\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\u000e\b\u0002\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u0001012\u0010\b\u0002\u00103\u001a\n\u0012\u0004\u0012\u000202\u0018\u000104H\u0007¢\u0006\u0002\u00105JK\u00106\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\u000e\b\u0002\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u0001012\u0010\b\u0002\u00103\u001a\n\u0012\u0004\u0012\u000202\u0018\u000104H\u0007¢\u0006\u0002\u00105J\r\u00107\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010 JD\u00107\u001a\u00020\u00152\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b8\u0010(J\r\u00109\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010 JD\u00109\u001a\u00020\u00152\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b:\u0010(J'\u0010;\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\u000e\b\u0002\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0007¢\u0006\u0002\u0010<J\r\u0010=\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010 JD\u0010=\u001a\u00020\u00152\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b>\u0010(R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u0015*\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0018\u0010\u001b\u001a\u00020\u0015*\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0018\u0010\u001d\u001a\u00020\u0015*\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006?"}, d2 = {"Landroidx/compose/material3/TopAppBarDefaults;", "", "()V", "LargeAppBarCollapsedHeight", "Landroidx/compose/ui/unit/Dp;", "getLargeAppBarCollapsedHeight-D9Ej5fM", "()F", "F", "LargeAppBarExpandedHeight", "getLargeAppBarExpandedHeight-D9Ej5fM", "MediumAppBarCollapsedHeight", "getMediumAppBarCollapsedHeight-D9Ej5fM", "MediumAppBarExpandedHeight", "getMediumAppBarExpandedHeight-D9Ej5fM", "TopAppBarExpandedHeight", "getTopAppBarExpandedHeight-D9Ej5fM", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "defaultCenterAlignedTopAppBarColors", "Landroidx/compose/material3/TopAppBarColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultCenterAlignedTopAppBarColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TopAppBarColors;", "defaultLargeTopAppBarColors", "getDefaultLargeTopAppBarColors$material3_release", "defaultMediumTopAppBarColors", "getDefaultMediumTopAppBarColors$material3_release", "defaultTopAppBarColors", "getDefaultTopAppBarColors$material3_release", "centerAlignedTopAppBarColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TopAppBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "scrolledContainerColor", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "centerAlignedTopAppBarColors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarColors;", "enterAlwaysScrollBehavior", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "state", "Landroidx/compose/material3/TopAppBarState;", "canScroll", "Lkotlin/Function0;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "exitUntilCollapsedScrollBehavior", "largeTopAppBarColors", "largeTopAppBarColors-zjMxDiM", "mediumTopAppBarColors", "mediumTopAppBarColors-zjMxDiM", "pinnedScrollBehavior", "(Landroidx/compose/material3/TopAppBarState;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarScrollBehavior;", "topAppBarColors", "topAppBarColors-zjMxDiM", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TopAppBarDefaults {
    public static final int $stable = 0;
    public static final TopAppBarDefaults INSTANCE = new TopAppBarDefaults();
    private static final float TopAppBarExpandedHeight = TopAppBarSmallTokens.INSTANCE.m3570getContainerHeightD9Ej5fM();
    private static final float MediumAppBarCollapsedHeight = TopAppBarSmallTokens.INSTANCE.m3570getContainerHeightD9Ej5fM();
    private static final float MediumAppBarExpandedHeight = TopAppBarMediumTokens.INSTANCE.m3560getContainerHeightD9Ej5fM();
    private static final float LargeAppBarCollapsedHeight = TopAppBarSmallTokens.INSTANCE.m3570getContainerHeightD9Ej5fM();
    private static final float LargeAppBarExpandedHeight = TopAppBarLargeTokens.INSTANCE.m3556getContainerHeightD9Ej5fM();

    private TopAppBarDefaults() {
    }

    public final TopAppBarColors topAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1388520854, "C(topAppBarColors)977@47193L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1388520854, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:977)");
        }
        TopAppBarColors defaultTopAppBarColors$material3_release = getDefaultTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTopAppBarColors$material3_release;
    }

    /* renamed from: topAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2868topAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 2142919275, "C(topAppBarColors)P(1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color)998@48245L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2142919275, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:998)");
        }
        TopAppBarColors m2854copyt635Npw = getDefaultTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2854copyt635Npw(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2854copyt635Npw;
    }

    public final TopAppBarColors getDefaultTopAppBarColors$material3_release(ColorScheme $this$defaultTopAppBarColors) {
        TopAppBarColors it = $this$defaultTopAppBarColors.getDefaultTopAppBarColorsCached();
        if (it != null) {
            return it;
        }
        TopAppBarColors it2 = new TopAppBarColors(ColorSchemeKt.fromToken($this$defaultTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getContainerColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getOnScrollContainerColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getHeadlineColor()), ColorSchemeKt.fromToken($this$defaultTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getTrailingIconColor()), null);
        $this$defaultTopAppBarColors.setDefaultTopAppBarColorsCached$material3_release(it2);
        return it2;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2143182847, "C1025@49443L29:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143182847, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.<get-windowInsets> (AppBar.kt:1025)");
        }
        WindowInsets m743onlybOOhFvg = WindowInsetsKt.m743onlybOOhFvg(SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, $composer, 6), WindowInsetsSides.m755plusgK_yJZ4(WindowInsetsSides.INSTANCE.m765getHorizontalJoeWqyM(), WindowInsetsSides.INSTANCE.m769getTopJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m743onlybOOhFvg;
    }

    public final TopAppBarColors centerAlignedTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 513940029, "C(centerAlignedTopAppBarColors)1035@49843L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(513940029, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1035)");
        }
        TopAppBarColors defaultCenterAlignedTopAppBarColors$material3_release = getDefaultCenterAlignedTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCenterAlignedTopAppBarColors$material3_release;
    }

    /* renamed from: centerAlignedTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2860centerAlignedTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1896017784, "C(centerAlignedTopAppBarColors)P(1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color)1056@50929L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896017784, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors (AppBar.kt:1056)");
        }
        TopAppBarColors m2854copyt635Npw = getDefaultCenterAlignedTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2854copyt635Npw(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2854copyt635Npw;
    }

    public final TopAppBarColors getDefaultCenterAlignedTopAppBarColors$material3_release(ColorScheme $this$defaultCenterAlignedTopAppBarColors) {
        TopAppBarColors it = $this$defaultCenterAlignedTopAppBarColors.getDefaultCenterAlignedTopAppBarColorsCached();
        if (it != null) {
            return it;
        }
        TopAppBarColors it2 = new TopAppBarColors(ColorSchemeKt.fromToken($this$defaultCenterAlignedTopAppBarColors, TopAppBarSmallCenteredTokens.INSTANCE.getContainerColor()), ColorSchemeKt.fromToken($this$defaultCenterAlignedTopAppBarColors, TopAppBarSmallCenteredTokens.INSTANCE.getOnScrollContainerColor()), ColorSchemeKt.fromToken($this$defaultCenterAlignedTopAppBarColors, TopAppBarSmallCenteredTokens.INSTANCE.getLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultCenterAlignedTopAppBarColors, TopAppBarSmallCenteredTokens.INSTANCE.getHeadlineColor()), ColorSchemeKt.fromToken($this$defaultCenterAlignedTopAppBarColors, TopAppBarSmallCenteredTokens.INSTANCE.getTrailingIconColor()), null);
        $this$defaultCenterAlignedTopAppBarColors.setDefaultCenterAlignedTopAppBarColorsCached$material3_release(it2);
        return it2;
    }

    public final TopAppBarColors mediumTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1268886463, "C(mediumTopAppBarColors)1085@52373L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1268886463, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1085)");
        }
        TopAppBarColors defaultMediumTopAppBarColors$material3_release = getDefaultMediumTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultMediumTopAppBarColors$material3_release;
    }

    /* renamed from: mediumTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2867mediumTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -582474442, "C(mediumTopAppBarColors)P(1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color)1107@53476L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-582474442, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors (AppBar.kt:1107)");
        }
        TopAppBarColors m2854copyt635Npw = getDefaultMediumTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2854copyt635Npw(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2854copyt635Npw;
    }

    public final TopAppBarColors getDefaultMediumTopAppBarColors$material3_release(ColorScheme $this$defaultMediumTopAppBarColors) {
        TopAppBarColors it = $this$defaultMediumTopAppBarColors.getDefaultMediumTopAppBarColorsCached();
        if (it != null) {
            return it;
        }
        TopAppBarColors it2 = new TopAppBarColors(ColorSchemeKt.fromToken($this$defaultMediumTopAppBarColors, TopAppBarMediumTokens.INSTANCE.getContainerColor()), ColorSchemeKt.fromToken($this$defaultMediumTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getOnScrollContainerColor()), ColorSchemeKt.fromToken($this$defaultMediumTopAppBarColors, TopAppBarMediumTokens.INSTANCE.getLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultMediumTopAppBarColors, TopAppBarMediumTokens.INSTANCE.getHeadlineColor()), ColorSchemeKt.fromToken($this$defaultMediumTopAppBarColors, TopAppBarMediumTokens.INSTANCE.getTrailingIconColor()), null);
        $this$defaultMediumTopAppBarColors.setDefaultMediumTopAppBarColorsCached$material3_release(it2);
        return it2;
    }

    public final TopAppBarColors largeTopAppBarColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1744932393, "C(largeTopAppBarColors)1135@54826L11:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1744932393, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1135)");
        }
        TopAppBarColors defaultLargeTopAppBarColors$material3_release = getDefaultLargeTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultLargeTopAppBarColors$material3_release;
    }

    /* renamed from: largeTopAppBarColors-zjMxDiM, reason: not valid java name */
    public final TopAppBarColors m2866largeTopAppBarColorszjMxDiM(long containerColor, long scrolledContainerColor, long navigationIconContentColor, long titleContentColor, long actionIconContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1471507700, "C(largeTopAppBarColors)P(1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color)1157@55926L11:AppBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long scrolledContainerColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : scrolledContainerColor;
        long navigationIconContentColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : navigationIconContentColor;
        long titleContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : titleContentColor;
        long actionIconContentColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : actionIconContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1471507700, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors (AppBar.kt:1157)");
        }
        TopAppBarColors m2854copyt635Npw = getDefaultLargeTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2854copyt635Npw(containerColor2, scrolledContainerColor2, navigationIconContentColor2, titleContentColor2, actionIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2854copyt635Npw;
    }

    public final TopAppBarColors getDefaultLargeTopAppBarColors$material3_release(ColorScheme $this$defaultLargeTopAppBarColors) {
        TopAppBarColors it = $this$defaultLargeTopAppBarColors.getDefaultLargeTopAppBarColorsCached();
        if (it != null) {
            return it;
        }
        TopAppBarColors it2 = new TopAppBarColors(ColorSchemeKt.fromToken($this$defaultLargeTopAppBarColors, TopAppBarLargeTokens.INSTANCE.getContainerColor()), ColorSchemeKt.fromToken($this$defaultLargeTopAppBarColors, TopAppBarSmallTokens.INSTANCE.getOnScrollContainerColor()), ColorSchemeKt.fromToken($this$defaultLargeTopAppBarColors, TopAppBarLargeTokens.INSTANCE.getLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultLargeTopAppBarColors, TopAppBarLargeTokens.INSTANCE.getHeadlineColor()), ColorSchemeKt.fromToken($this$defaultLargeTopAppBarColors, TopAppBarLargeTokens.INSTANCE.getTrailingIconColor()), null);
        $this$defaultLargeTopAppBarColors.setDefaultLargeTopAppBarColorsCached$material3_release(it2);
        return it2;
    }

    public final TopAppBarScrollBehavior pinnedScrollBehavior(TopAppBarState state, Function0<Boolean> function0, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, 286497075, "C(pinnedScrollBehavior)P(1)1192@57597L24:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            state = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            Function0 canScroll = new Function0<Boolean>() { // from class: androidx.compose.material3.TopAppBarDefaults$pinnedScrollBehavior$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return true;
                }
            };
            function0 = canScroll;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(286497075, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior (AppBar.kt:1194)");
        }
        PinnedScrollBehavior pinnedScrollBehavior = new PinnedScrollBehavior(state, function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return pinnedScrollBehavior;
    }

    public final TopAppBarScrollBehavior enterAlwaysScrollBehavior(TopAppBarState state, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, 959086674, "C(enterAlwaysScrollBehavior)P(3!1,2)1214@58881L24,1217@59107L26:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            state = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            Function0 canScroll = new Function0<Boolean>() { // from class: androidx.compose.material3.TopAppBarDefaults$enterAlwaysScrollBehavior$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return true;
                }
            };
            function0 = canScroll;
        }
        if ((i & 4) != 0) {
            AnimationSpec snapAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
            animationSpec = snapAnimationSpec;
        }
        if ((i & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(959086674, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior (AppBar.kt:1219)");
        }
        EnterAlwaysScrollBehavior enterAlwaysScrollBehavior = new EnterAlwaysScrollBehavior(state, animationSpec, decayAnimationSpec, function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return enterAlwaysScrollBehavior;
    }

    public final TopAppBarScrollBehavior exitUntilCollapsedScrollBehavior(TopAppBarState state, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, -1757023234, "C(exitUntilCollapsedScrollBehavior)P(3!1,2)1247@60644L24,1250@60870L26:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            state = AppBarKt.rememberTopAppBarState(0.0f, 0.0f, 0.0f, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            Function0 canScroll = new Function0<Boolean>() { // from class: androidx.compose.material3.TopAppBarDefaults$exitUntilCollapsedScrollBehavior$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return true;
                }
            };
            function0 = canScroll;
        }
        if ((i & 4) != 0) {
            AnimationSpec snapAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
            animationSpec = snapAnimationSpec;
        }
        if ((i & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer2, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1757023234, $changed, -1, "androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior (AppBar.kt:1252)");
        }
        ExitUntilCollapsedScrollBehavior exitUntilCollapsedScrollBehavior = new ExitUntilCollapsedScrollBehavior(state, animationSpec, decayAnimationSpec, function0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return exitUntilCollapsedScrollBehavior;
    }

    /* renamed from: getTopAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m2865getTopAppBarExpandedHeightD9Ej5fM() {
        return TopAppBarExpandedHeight;
    }

    /* renamed from: getMediumAppBarCollapsedHeight-D9Ej5fM, reason: not valid java name */
    public final float m2863getMediumAppBarCollapsedHeightD9Ej5fM() {
        return MediumAppBarCollapsedHeight;
    }

    /* renamed from: getMediumAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m2864getMediumAppBarExpandedHeightD9Ej5fM() {
        return MediumAppBarExpandedHeight;
    }

    /* renamed from: getLargeAppBarCollapsedHeight-D9Ej5fM, reason: not valid java name */
    public final float m2861getLargeAppBarCollapsedHeightD9Ej5fM() {
        return LargeAppBarCollapsedHeight;
    }

    /* renamed from: getLargeAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m2862getLargeAppBarExpandedHeightD9Ej5fM() {
        return LargeAppBarExpandedHeight;
    }
}
