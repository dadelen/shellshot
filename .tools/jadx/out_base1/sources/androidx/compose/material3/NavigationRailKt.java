package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.tokens.NavigationRailTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: NavigationRail.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001az\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2 \b\u0002\u0010\u001d\u001a\u001a\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001e¢\u0006\u0002\b ¢\u0006\u0002\b!2\b\b\u0002\u0010\"\u001a\u00020#2\u001c\u0010$\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00170\u001e¢\u0006\u0002\b ¢\u0006\u0002\b!H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0081\u0001\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170+2\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010+¢\u0006\u0002\b 2\b\b\u0002\u0010/\u001a\u00020)2\b\b\u0002\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0007¢\u0006\u0002\u00104\u001aq\u00105\u001a\u00020\u00172\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0013\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010+¢\u0006\u0002\b 2\u0006\u0010/\u001a\u00020)2\f\u00108\u001a\b\u0012\u0004\u0012\u0002090+H\u0003¢\u0006\u0002\u0010:\u001a8\u0010;\u001a\u00020<*\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\b\u0010A\u001a\u0004\u0018\u00010?2\u0006\u0010B\u001a\u00020CH\u0002ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001aP\u0010F\u001a\u00020<*\u00020=2\u0006\u0010G\u001a\u00020?2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\b\u0010A\u001a\u0004\u0018\u00010?2\u0006\u0010B\u001a\u00020C2\u0006\u0010/\u001a\u00020)2\u0006\u00108\u001a\u000209H\u0002ø\u0001\u0000¢\u0006\u0004\bH\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u000e\u0010\u000f\"\u0016\u0010\u0010\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0011\u0010\u000f\"\u0016\u0010\u0012\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0013\u0010\u000f\"\u0016\u0010\u0014\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0015\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006J²\u0006\n\u0010K\u001a\u00020\u001bX\u008a\u0084\u0002²\u0006\n\u0010L\u001a\u00020\u001bX\u008a\u0084\u0002"}, d2 = {"IconLayoutIdTag", "", "IndicatorHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "IndicatorLayoutIdTag", "IndicatorRippleLayoutIdTag", "IndicatorVerticalPaddingNoLabel", "IndicatorVerticalPaddingWithLabel", "ItemAnimationDurationMillis", "", "LabelLayoutIdTag", "NavigationRailHeaderPadding", "NavigationRailItemHeight", "getNavigationRailItemHeight", "()F", "NavigationRailItemVerticalPadding", "getNavigationRailItemVerticalPadding", "NavigationRailItemWidth", "getNavigationRailItemWidth", "NavigationRailVerticalPadding", "getNavigationRailVerticalPadding", "NavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "NavigationRail-qi6gXK8", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationRailKt.IconLayoutIdTag, "enabled", NavigationRailKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationRailItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationRailItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItemLayout", NavigationRailKt.IndicatorRippleLayoutIdTag, NavigationRailKt.IndicatorLayoutIdTag, "animationProgress", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "material3_release", "iconColor", "textColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NavigationRailKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPaddingNoLabel;
    private static final float IndicatorVerticalPaddingWithLabel;
    private static final int ItemAnimationDurationMillis = 150;
    private static final String LabelLayoutIdTag = "label";
    private static final float NavigationRailVerticalPadding = Dp.m6626constructorimpl(4);
    private static final float NavigationRailHeaderPadding = Dp.m6626constructorimpl(8);
    private static final float NavigationRailItemWidth = NavigationRailTokens.INSTANCE.m3335getContainerWidthD9Ej5fM();
    private static final float NavigationRailItemHeight = NavigationRailTokens.INSTANCE.m3338getNoLabelActiveIndicatorHeightD9Ej5fM();
    private static final float NavigationRailItemVerticalPadding = Dp.m6626constructorimpl(4);

    /* renamed from: NavigationRail-qi6gXK8, reason: not valid java name */
    public static final void m2339NavigationRailqi6gXK8(Modifier modifier, long containerColor, long contentColor, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        Function3 header;
        WindowInsets windowInsets2;
        long contentColor3;
        final WindowInsets windowInsets3;
        Modifier modifier3;
        int $dirty;
        final Function3 header2;
        long containerColor3;
        Composer $composer2;
        final Function3 header3;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final long containerColor4;
        final long contentColor4;
        int i2;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(118552648);
        ComposerKt.sourceInformation($composer3, "C(NavigationRail)P(4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,5)113@5242L14,114@5284L31,116@5431L12,123@5614L618,119@5501L731:NavigationRail.kt#uh7d8r");
        int $dirty2 = $changed;
        int i5 = i & 1;
        if (i5 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i4 = 32;
                    $dirty2 |= i4;
                }
            } else {
                containerColor2 = containerColor;
            }
            i4 = 16;
            $dirty2 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i3 = 256;
                    $dirty2 |= i3;
                }
            } else {
                contentColor2 = contentColor;
            }
            i3 = 128;
            $dirty2 |= i3;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            header = function3;
        } else if (($changed & 3072) == 0) {
            header = function3;
            $dirty2 |= $composer3.changedInstance(header) ? 2048 : 1024;
        } else {
            header = function3;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function32) ? 131072 : 65536;
        }
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            windowInsets4 = windowInsets2;
            containerColor4 = containerColor2;
            contentColor4 = contentColor2;
            header3 = header;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    containerColor2 = NavigationRailDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                }
                if (i6 != 0) {
                    header = null;
                }
                if ((i & 16) != 0) {
                    windowInsets3 = NavigationRailDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    contentColor3 = contentColor2;
                    modifier3 = modifier5;
                    $dirty = $dirty2 & (-57345);
                    header2 = header;
                    containerColor3 = containerColor2;
                } else {
                    contentColor3 = contentColor2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier5;
                    $dirty = $dirty2;
                    header2 = header;
                    containerColor3 = containerColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty2 & (-57345);
                    contentColor3 = contentColor2;
                    header2 = header;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    containerColor3 = containerColor2;
                } else {
                    $dirty = $dirty2;
                    contentColor3 = contentColor2;
                    header2 = header;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    containerColor3 = containerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(118552648, $dirty, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:118)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2547SurfaceT9BRK9s(modifier3, null, containerColor3, contentColor3, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-2092683357, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRail$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0166  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x01a9  */
                /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r25, int r26) {
                    /*
                        Method dump skipped, instructions count: 429
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt$NavigationRail$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, ($dirty & 14) | 12582912 | (($dirty << 3) & 896) | (($dirty << 3) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            header3 = header2;
            windowInsets4 = windowInsets3;
            modifier4 = modifier3;
            containerColor4 = containerColor3;
            contentColor4 = contentColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRail$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    NavigationRailKt.m2339NavigationRailqi6gXK8(Modifier.this, containerColor4, contentColor4, header3, windowInsets4, function32, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x04c9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0421  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationRailItem(final boolean r48, final kotlin.jvm.functions.Function0<kotlin.Unit> r49, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, androidx.compose.ui.Modifier r51, boolean r52, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r53, boolean r54, androidx.compose.material3.NavigationRailItemColors r55, androidx.compose.foundation.interaction.MutableInteractionSource r56, androidx.compose.runtime.Composer r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 1331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.NavigationRailItem(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.NavigationRailItemColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:126:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0317  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationRailItemLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r57, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r58, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r59, final boolean r60, final kotlin.jvm.functions.Function0<java.lang.Float> r61, androidx.compose.runtime.Composer r62, final int r63) {
        /*
            Method dump skipped, instructions count: 1213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.NavigationRailItemLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m2342placeIconX9ElhV4(MeasureScope $this$placeIcon_u2dX9ElhV4, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints) {
        final int width = ConstraintsKt.m6596constrainWidthK40F9xA(constraints, Math.max(iconPlaceable.getWidth(), Math.max(indicatorRipplePlaceable.getWidth(), indicatorPlaceable != null ? indicatorPlaceable.getWidth() : 0)));
        final int height = ConstraintsKt.m6595constrainHeightK40F9xA(constraints, $this$placeIcon_u2dX9ElhV4.mo358roundToPx0680j_4(NavigationRailItemHeight));
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2dX9ElhV4, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$placeIcon$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                Placeable it = Placeable.this;
                if (it != null) {
                    int i = width;
                    int i2 = height;
                    int indicatorX = (i - it.getWidth()) / 2;
                    int indicatorY = (i2 - it.getHeight()) / 2;
                    Placeable.PlacementScope.placeRelative$default($this$layout, it, indicatorX, indicatorY, 0.0f, 4, null);
                }
                Placeable.PlacementScope.placeRelative$default($this$layout, iconPlaceable, iconX, iconY, 0.0f, 4, null);
                Placeable.PlacementScope.placeRelative$default($this$layout, indicatorRipplePlaceable, rippleX, rippleY, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m2343placeLabelAndIconzUg2_y0(final MeasureScope $this$placeLabelAndIcon_u2dzUg2_y0, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, final boolean alwaysShowLabel, final float animationProgress) {
        float contentHeight = iconPlaceable.getHeight() + $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(IndicatorVerticalPaddingWithLabel) + $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(NavigationRailItemVerticalPadding) + labelPlaceable.getHeight();
        final float contentVerticalPadding = RangesKt.coerceAtLeast((Constraints.m6580getMinHeightimpl(constraints) - contentHeight) / 2.0f, $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(IndicatorVerticalPaddingWithLabel));
        float height = contentHeight + (contentVerticalPadding * 2.0f);
        float unselectedIconY = alwaysShowLabel ? contentVerticalPadding : (height - iconPlaceable.getHeight()) / 2.0f;
        float iconDistance = unselectedIconY - contentVerticalPadding;
        final float offset = iconDistance * (1.0f - animationProgress);
        final float labelY = iconPlaceable.getHeight() + contentVerticalPadding + $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(IndicatorVerticalPaddingWithLabel) + $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(NavigationRailItemVerticalPadding);
        final int width = ConstraintsKt.m6596constrainWidthK40F9xA(constraints, Math.max(iconPlaceable.getWidth(), Math.max(labelPlaceable.getWidth(), indicatorPlaceable != null ? indicatorPlaceable.getWidth() : 0)));
        final int labelX = (width - labelPlaceable.getWidth()) / 2;
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final float rippleY = contentVerticalPadding - $this$placeLabelAndIcon_u2dzUg2_y0.mo364toPx0680j_4(IndicatorVerticalPaddingWithLabel);
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dzUg2_y0, width, MathKt.roundToInt(height), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$placeLabelAndIcon$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:9:0x003d, code lost:
            
                if ((r19 == 0.0f) == false) goto L12;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2(androidx.compose.ui.layout.Placeable.PlacementScope r21) {
                /*
                    r20 = this;
                    r0 = r20
                    androidx.compose.ui.layout.Placeable r1 = androidx.compose.ui.layout.Placeable.this
                    if (r1 == 0) goto L2f
                    int r2 = r30
                    float r3 = r26
                    androidx.compose.ui.layout.MeasureScope r4 = r31
                    float r5 = r23
                    r7 = r1
                    r1 = 0
                    int r6 = r7.getWidth()
                    int r2 = r2 - r6
                    int r8 = r2 / 2
                    float r2 = androidx.compose.material3.NavigationRailKt.access$getIndicatorVerticalPaddingWithLabel$p()
                    float r2 = r4.mo364toPx0680j_4(r2)
                    float r3 = r3 - r2
                    float r5 = r5 + r3
                    int r9 = kotlin.math.MathKt.roundToInt(r5)
                    r11 = 4
                    r12 = 0
                    r10 = 0
                    r6 = r21
                    androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r6, r7, r8, r9, r10, r11, r12)
                L2f:
                    boolean r1 = r18
                    if (r1 != 0) goto L3f
                    float r1 = r19
                    r2 = 0
                    int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                    if (r1 != 0) goto L3c
                    r1 = 1
                    goto L3d
                L3c:
                    r1 = 0
                L3d:
                    if (r1 != 0) goto L57
                L3f:
                    androidx.compose.ui.layout.Placeable r14 = r20
                    int r15 = r21
                    float r1 = r22
                    float r2 = r23
                    float r1 = r1 + r2
                    int r16 = kotlin.math.MathKt.roundToInt(r1)
                    r18 = 4
                    r19 = 0
                    r17 = 0
                    r13 = r21
                    androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r13, r14, r15, r16, r17, r18, r19)
                L57:
                    androidx.compose.ui.layout.Placeable r14 = r24
                    int r15 = r25
                    float r1 = r26
                    float r2 = r23
                    float r1 = r1 + r2
                    int r16 = kotlin.math.MathKt.roundToInt(r1)
                    r18 = 4
                    r19 = 0
                    r17 = 0
                    r13 = r21
                    androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r13, r14, r15, r16, r17, r18, r19)
                    androidx.compose.ui.layout.Placeable r14 = r27
                    int r15 = r28
                    float r1 = r29
                    float r2 = r23
                    float r1 = r1 + r2
                    int r16 = kotlin.math.MathKt.roundToInt(r1)
                    androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r13, r14, r15, r16, r17, r18, r19)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt$placeLabelAndIcon$1.invoke2(androidx.compose.ui.layout.Placeable$PlacementScope):void");
            }
        }, 4, null);
    }

    static {
        float arg0$iv = NavigationRailTokens.INSTANCE.m3333getActiveIndicatorWidthD9Ej5fM();
        float other$iv = NavigationRailTokens.INSTANCE.m3336getIconSizeD9Ej5fM();
        IndicatorHorizontalPadding = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = NavigationRailTokens.INSTANCE.m3332getActiveIndicatorHeightD9Ej5fM();
        float other$iv2 = NavigationRailTokens.INSTANCE.m3336getIconSizeD9Ej5fM();
        IndicatorVerticalPaddingWithLabel = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv2 - other$iv2) / 2);
        float arg0$iv3 = NavigationRailTokens.INSTANCE.m3338getNoLabelActiveIndicatorHeightD9Ej5fM();
        float other$iv3 = NavigationRailTokens.INSTANCE.m3336getIconSizeD9Ej5fM();
        IndicatorVerticalPaddingNoLabel = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv3 - other$iv3) / 2);
    }

    public static final float getNavigationRailVerticalPadding() {
        return NavigationRailVerticalPadding;
    }

    public static final float getNavigationRailItemWidth() {
        return NavigationRailItemWidth;
    }

    public static final float getNavigationRailItemHeight() {
        return NavigationRailItemHeight;
    }

    public static final float getNavigationRailItemVerticalPadding() {
        return NavigationRailItemVerticalPadding;
    }
}
