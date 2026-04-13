package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
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

/* compiled from: BottomNavigation.kt */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a`\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00042\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aX\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00042\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a?\u0010\u001c\u001a\u00020\u000b2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u000b0\u001e¢\u0006\u0002\b\u00162\u0013\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001e¢\u0006\u0002\b\u00162\b\b\u0001\u0010 \u001a\u00020\u0002H\u0003¢\u0006\u0002\u0010!\u001aR\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&2&\u0010\u0013\u001a\"\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\u0016H\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a\u0094\u0001\u0010,\u001a\u00020\u000b*\u00020\u00152\u0006\u0010%\u001a\u00020&2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001e2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u000b0\u001e¢\u0006\u0002\b\u00162\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010.\u001a\u00020&2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001e¢\u0006\u0002\b\u00162\b\b\u0002\u0010/\u001a\u00020&2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\b\b\u0002\u00102\u001a\u00020\u00102\b\b\u0002\u00103\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a&\u00106\u001a\u000207*\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a8\u0010?\u001a\u000207*\u0002082\u0006\u0010@\u001a\u00020:2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\b\b\u0001\u0010 \u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\bA\u0010B\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C²\u0006\n\u0010)\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"BottomNavigationAnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "BottomNavigationHeight", "Landroidx/compose/ui/unit/Dp;", "F", "BottomNavigationItemHorizontalPadding", "CombinedItemTextBaseline", "ZeroInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "BottomNavigation", "", "windowInsets", "modifier", "Landroidx/compose/ui/Modifier;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "BottomNavigation-_UMDTes", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomNavigation-PEIptTM", "(Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomNavigationItemBaselineLayout", "icon", "Lkotlin/Function0;", "label", "iconPositionAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;I)V", "BottomNavigationTransition", "activeColor", "inactiveColor", "selected", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animationProgress", "BottomNavigationTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "BottomNavigationItem", "onClick", "enabled", "alwaysShowLabel", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "unselectedContentColor", "BottomNavigationItem-jY6E1Zs", "(Landroidx/compose/foundation/layout/RowScope;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;III)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-DIyivk0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BottomNavigationKt {
    private static final TweenSpec<Float> BottomNavigationAnimationSpec = new TweenSpec<>(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final float BottomNavigationHeight = Dp.m6626constructorimpl(56);
    private static final float BottomNavigationItemHorizontalPadding = Dp.m6626constructorimpl(12);
    private static final float CombinedItemTextBaseline = Dp.m6626constructorimpl(12);
    private static final WindowInsets ZeroInsets = WindowInsetsKt.m741WindowInsetsa9UjIt4(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0));

    /* renamed from: BottomNavigation-_UMDTes, reason: not valid java name */
    public static final void m1428BottomNavigation_UMDTes(final WindowInsets windowInsets, Modifier modifier, long backgroundColor, long contentColor, float elevation, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        long contentColor2;
        float f;
        long contentColor3;
        float elevation2;
        Modifier modifier3;
        long backgroundColor3;
        Composer $composer2;
        final Modifier modifier4;
        final long backgroundColor4;
        final long contentColor4;
        final float elevation3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-1278406810);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigation)P(5,4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.unit.Dp)101@4623L6,102@4672L32,111@4959L330,106@4815L474:BottomNavigation.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i3 = 128;
            $dirty |= i3;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                contentColor2 = contentColor;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            contentColor2 = contentColor;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            f = elevation;
        } else if (($changed & 24576) == 0) {
            f = elevation;
            $dirty |= $composer3.changed(f) ? 16384 : 8192;
        } else {
            f = elevation;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            backgroundColor4 = backgroundColor2;
            contentColor4 = contentColor2;
            elevation3 = f;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i5 != 0) {
                    elevation2 = BottomNavigationDefaults.INSTANCE.m1426getElevationD9Ej5fM();
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    modifier3 = modifier2;
                } else {
                    contentColor3 = contentColor2;
                    elevation2 = f;
                    modifier3 = modifier2;
                    backgroundColor3 = backgroundColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                contentColor3 = contentColor2;
                elevation2 = f;
                modifier3 = modifier2;
                backgroundColor3 = backgroundColor2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1278406810, $dirty, -1, "androidx.compose.material.BottomNavigation (BottomNavigation.kt:105)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(modifier3, null, backgroundColor3, contentColor3, null, elevation2, ComposableLambdaKt.rememberComposableLambda(-352628062, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigation$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x015b  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 351
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomNavigationKt$BottomNavigation$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168) | (458752 & ($dirty << 3)), 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
            elevation3 = elevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigation$2
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

                public final void invoke(Composer composer, int i6) {
                    BottomNavigationKt.m1428BottomNavigation_UMDTes(WindowInsets.this, modifier4, backgroundColor4, contentColor4, elevation3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: BottomNavigation-PEIptTM, reason: not valid java name */
    public static final void m1427BottomNavigationPEIptTM(Modifier modifier, long backgroundColor, long contentColor, float elevation, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long contentColor2;
        float f;
        long backgroundColor2;
        Modifier modifier3;
        int i2;
        long contentColor3;
        float elevation2;
        long backgroundColor3;
        Composer $composer2;
        final Modifier modifier4;
        final long contentColor4;
        final float elevation3;
        final long backgroundColor4;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(456489494);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigation)P(4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.unit.Dp)156@6823L6,157@6872L32,161@7015L89:BottomNavigation.kt#jmzs0o");
        int $dirty = $changed;
        int i5 = i & 1;
        if (i5 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = backgroundColor;
                if ($composer3.changed(j)) {
                    i4 = 32;
                    $dirty |= i4;
                }
            } else {
                j = backgroundColor;
            }
            i4 = 16;
            $dirty |= i4;
        } else {
            j = backgroundColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            } else {
                contentColor2 = contentColor;
            }
            i3 = 128;
            $dirty |= i3;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            f = elevation;
        } else if (($changed & 3072) == 0) {
            f = elevation;
            $dirty |= $composer3.changed(f) ? 2048 : 1024;
        } else {
            f = elevation;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            backgroundColor4 = j;
            contentColor4 = contentColor2;
            elevation3 = f;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -113;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    modifier3 = modifier5;
                    i2 = 6;
                    contentColor3 = contentColor2;
                    backgroundColor3 = backgroundColor2;
                    elevation2 = BottomNavigationDefaults.INSTANCE.m1426getElevationD9Ej5fM();
                } else {
                    modifier3 = modifier5;
                    i2 = 6;
                    contentColor3 = contentColor2;
                    elevation2 = f;
                    backgroundColor3 = backgroundColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                elevation2 = f;
                i2 = 6;
                backgroundColor3 = j;
                contentColor3 = contentColor2;
                modifier3 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(456489494, $dirty, -1, "androidx.compose.material.BottomNavigation (BottomNavigation.kt:160)");
            }
            $composer2 = $composer3;
            m1428BottomNavigation_UMDTes(ZeroInsets, modifier3, backgroundColor3, contentColor3, elevation2, function3, $composer2, (($dirty << 3) & 112) | i2 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentColor4 = contentColor3;
            elevation3 = elevation2;
            backgroundColor4 = backgroundColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigation$3
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
                    BottomNavigationKt.m1427BottomNavigationPEIptTM(Modifier.this, backgroundColor4, contentColor4, elevation3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x043b  */
    /* renamed from: BottomNavigationItem-jY6E1Zs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1429BottomNavigationItemjY6E1Zs(final androidx.compose.foundation.layout.RowScope r39, final boolean r40, final kotlin.jvm.functions.Function0<kotlin.Unit> r41, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, androidx.compose.ui.Modifier r43, boolean r44, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r45, boolean r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, long r48, long r50, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 1134
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomNavigationKt.m1429BottomNavigationItemjY6E1Zs(androidx.compose.foundation.layout.RowScope, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, long, long, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: BottomNavigationTransition-Klgx-Pg, reason: not valid java name */
    public static final void m1430BottomNavigationTransitionKlgxPg(final long activeColor, final long inactiveColor, final boolean selected, final Function3<? super Float, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        long m4161copywmQWz5c;
        Composer $composer2 = $composer.startRestartGroup(-985175058);
        ComposerKt.sourceInformation($composer2, "C(BottomNavigationTransition)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color,3)286@12327L128,296@12669L42,293@12530L181:BottomNavigation.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(activeColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(inactiveColor) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(selected) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        if (($dirty & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-985175058, $dirty, -1, "androidx.compose.material.BottomNavigationTransition (BottomNavigation.kt:285)");
            }
            final State animationProgress$delegate = AnimateAsStateKt.animateFloatAsState(selected ? 1065353216 : 0, BottomNavigationAnimationSpec, 0.0f, null, null, $composer2, 48, 28);
            long color = ColorKt.m4214lerpjxsXWHM(inactiveColor, activeColor, BottomNavigationTransition_Klgx_Pg$lambda$2(animationProgress$delegate));
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            m4161copywmQWz5c = Color.m4161copywmQWz5c(color, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(color) : 1.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(color) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(color) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(color) : 0.0f);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{localContentColor.provides(Color.m4153boximpl(m4161copywmQWz5c)), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m4165getAlphaimpl(color)))}, ComposableLambdaKt.rememberComposableLambda(-138092754, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigationTransition$1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    float BottomNavigationTransition_Klgx_Pg$lambda$2;
                    ComposerKt.sourceInformation($composer3, "C297@12679L26:BottomNavigation.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-138092754, $changed2, -1, "androidx.compose.material.BottomNavigationTransition.<anonymous> (BottomNavigation.kt:297)");
                        }
                        Function3<Float, Composer, Integer, Unit> function32 = function3;
                        BottomNavigationTransition_Klgx_Pg$lambda$2 = BottomNavigationKt.BottomNavigationTransition_Klgx_Pg$lambda$2(animationProgress$delegate);
                        function32.invoke(Float.valueOf(BottomNavigationTransition_Klgx_Pg$lambda$2), $composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigationTransition$2
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

                public final void invoke(Composer composer, int i) {
                    BottomNavigationKt.m1430BottomNavigationTransitionKlgxPg(activeColor, inactiveColor, selected, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float BottomNavigationTransition_Klgx_Pg$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BottomNavigationItemBaselineLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, final float r53, androidx.compose.runtime.Composer r54, final int r55) {
        /*
            Method dump skipped, instructions count: 1059
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomNavigationKt.BottomNavigationItemBaselineLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, float, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeIcon-3p2s80s, reason: not valid java name */
    public static final MeasureResult m1434placeIcon3p2s80s(MeasureScope $this$placeIcon_u2d3p2s80s, final Placeable iconPlaceable, long constraints) {
        int height = ConstraintsKt.m6595constrainHeightK40F9xA(constraints, $this$placeIcon_u2d3p2s80s.mo358roundToPx0680j_4(BottomNavigationHeight));
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2d3p2s80s, iconPlaceable.getWidth(), height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$placeIcon$1
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
                Placeable.PlacementScope.placeRelative$default($this$layout, Placeable.this, 0, iconY, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeLabelAndIcon-DIyivk0, reason: not valid java name */
    public static final MeasureResult m1435placeLabelAndIconDIyivk0(MeasureScope $this$placeLabelAndIcon_u2dDIyivk0, final Placeable labelPlaceable, final Placeable iconPlaceable, long constraints, final float iconPositionAnimationProgress) {
        int firstBaseline = labelPlaceable.get(AlignmentLineKt.getFirstBaseline());
        int baselineOffset = $this$placeLabelAndIcon_u2dDIyivk0.mo358roundToPx0680j_4(CombinedItemTextBaseline);
        int netBaselineAdjustment = baselineOffset - firstBaseline;
        int contentHeight = iconPlaceable.getHeight() + labelPlaceable.getHeight() + netBaselineAdjustment;
        int height = ConstraintsKt.m6595constrainHeightK40F9xA(constraints, Math.max(contentHeight, $this$placeLabelAndIcon_u2dDIyivk0.mo358roundToPx0680j_4(BottomNavigationHeight)));
        final int contentVerticalPadding = RangesKt.coerceAtLeast((height - contentHeight) / 2, 0);
        int unselectedIconY = (height - iconPlaceable.getHeight()) / 2;
        final int labelY = iconPlaceable.getHeight() + contentVerticalPadding + netBaselineAdjustment;
        int containerWidth = Math.max(labelPlaceable.getWidth(), iconPlaceable.getWidth());
        final int labelX = (containerWidth - labelPlaceable.getWidth()) / 2;
        final int iconX = (containerWidth - iconPlaceable.getWidth()) / 2;
        int iconDistance = unselectedIconY - contentVerticalPadding;
        final int offset = MathKt.roundToInt(iconDistance * (1.0f - iconPositionAnimationProgress));
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dDIyivk0, containerWidth, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomNavigationKt$placeLabelAndIcon$1
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
                if (!(iconPositionAnimationProgress == 0.0f)) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, labelPlaceable, labelX, labelY + offset, 0.0f, 4, null);
                }
                Placeable.PlacementScope.placeRelative$default($this$layout, iconPlaceable, iconX, contentVerticalPadding + offset, 0.0f, 4, null);
            }
        }, 4, null);
    }
}
