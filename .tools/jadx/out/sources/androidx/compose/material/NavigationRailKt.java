package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.ColumnScope;
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
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;

/* compiled from: NavigationRail.kt */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0082\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00012 \b\u0002\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000e0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001az\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00012 \b\u0002\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000e0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0090\u0001\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0$2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u000e0$¢\u0006\u0002\b\u00192\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020\"2\u0015\b\u0002\u0010'\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010$¢\u0006\u0002\b\u00192\b\b\u0002\u0010(\u001a\u00020\"2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020\u00132\b\b\u0002\u0010,\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a?\u0010/\u001a\u00020\u000e2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u000e0$¢\u0006\u0002\b\u00192\u0013\u0010'\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010$¢\u0006\u0002\b\u00192\b\b\u0001\u00100\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00101\u001aR\u00102\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2&\u0010\u001b\u001a\"\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u000e0\u0017¢\u0006\u0002\b\u0019H\u0003ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a&\u0010:\u001a\u00020;*\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0002ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001a8\u0010C\u001a\u00020;*\u00020<2\u0006\u0010D\u001a\u00020>2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\b\b\u0001\u00100\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G²\u0006\n\u00107\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"HeaderPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ItemIconTopOffset", "ItemLabelBaselineBottomOffset", "NavigationRailAnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "NavigationRailItemCompactSize", "NavigationRailItemSize", "NavigationRailPadding", "ZeroInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "NavigationRail", "", "windowInsets", "modifier", "Landroidx/compose/ui/Modifier;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "content", "NavigationRail-afqeVBk", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRail-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", "icon", "enabled", "label", "alwaysShowLabel", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "unselectedContentColor", "NavigationRailItem-0S3VyRs", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", "NavigationRailItemBaselineLayout", "iconPositionAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;I)V", "NavigationRailTransition", "activeColor", "inactiveColor", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animationProgress", "NavigationRailTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-DIyivk0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NavigationRailKt {
    private static final TweenSpec<Float> NavigationRailAnimationSpec = new TweenSpec<>(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final float NavigationRailItemSize = Dp.m6626constructorimpl(72);
    private static final float NavigationRailItemCompactSize = Dp.m6626constructorimpl(56);
    private static final float NavigationRailPadding = Dp.m6626constructorimpl(8);
    private static final float HeaderPadding = Dp.m6626constructorimpl(8);
    private static final float ItemLabelBaselineBottomOffset = Dp.m6626constructorimpl(16);
    private static final float ItemIconTopOffset = Dp.m6626constructorimpl(14);
    private static final WindowInsets ZeroInsets = WindowInsetsKt.m742WindowInsetsa9UjIt4$default(Dp.m6626constructorimpl(0), 0.0f, 0.0f, 0.0f, 14, null);

    /* renamed from: NavigationRail-afqeVBk, reason: not valid java name */
    public static final void m1588NavigationRailafqeVBk(final WindowInsets windowInsets, Modifier modifier, long backgroundColor, long contentColor, float elevation, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        long contentColor2;
        float elevation2;
        Function3 function33;
        float elevation3;
        final Function3 header;
        int i2;
        long backgroundColor3;
        long contentColor3;
        Modifier modifier3;
        Composer $composer2;
        final Function3 header2;
        final Modifier modifier4;
        final long backgroundColor4;
        final long contentColor4;
        final float elevation4;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1389243291);
        ComposerKt.sourceInformation($composer3, "C(NavigationRail)P(6,5,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.unit.Dp,4)103@4797L6,104@4839L32,114@5185L459,109@5041L603:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
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
                    i4 = 256;
                    $dirty |= i4;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i4 = 128;
            $dirty |= i4;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i3 = 2048;
                    $dirty |= i3;
                }
            } else {
                contentColor2 = contentColor;
            }
            i3 = 1024;
            $dirty |= i3;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            elevation2 = elevation;
        } else if (($changed & 24576) == 0) {
            elevation2 = elevation;
            $dirty |= $composer3.changed(elevation2) ? 16384 : 8192;
        } else {
            elevation2 = elevation;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function33 = function3;
        } else if ((196608 & $changed) == 0) {
            function33 = function3;
            $dirty |= $composer3.changedInstance(function33) ? 131072 : 65536;
        } else {
            function33 = function3;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function32) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            header2 = function33;
            backgroundColor4 = backgroundColor2;
            contentColor4 = contentColor2;
            elevation4 = elevation2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1482getSurface0d7_KjU();
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    elevation2 = NavigationRailDefaults.INSTANCE.m1586getElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    header = null;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    i2 = -1389243291;
                    modifier3 = modifier2;
                    backgroundColor3 = backgroundColor2;
                } else {
                    elevation3 = elevation2;
                    header = function33;
                    i2 = -1389243291;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    modifier3 = modifier2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    elevation3 = elevation2;
                    header = function33;
                    i2 = -1389243291;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    modifier3 = modifier2;
                } else {
                    elevation3 = elevation2;
                    header = function33;
                    i2 = -1389243291;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.NavigationRail (NavigationRail.kt:108)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(modifier3, null, backgroundColor3, contentColor3, null, elevation3, ComposableLambdaKt.rememberComposableLambda(-245908831, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.NavigationRailKt$NavigationRail$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0159  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x01b1  */
                /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:30:0x0182  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 437
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.NavigationRailKt$NavigationRail$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168) | (458752 & ($dirty << 3)), 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            header2 = header;
            modifier4 = modifier3;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
            elevation4 = elevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.NavigationRailKt$NavigationRail$2
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

                public final void invoke(Composer composer, int i8) {
                    NavigationRailKt.m1588NavigationRailafqeVBk(WindowInsets.this, modifier4, backgroundColor4, contentColor4, elevation4, header2, function32, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: NavigationRail-HsRjFd4, reason: not valid java name */
    public static final void m1587NavigationRailHsRjFd4(Modifier modifier, long backgroundColor, long contentColor, float elevation, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        long contentColor2;
        float elevation2;
        Function3 function33;
        float elevation3;
        Function3 header;
        int i2;
        Modifier modifier3;
        long backgroundColor3;
        int i3;
        long contentColor3;
        Composer $composer2;
        final Modifier modifier4;
        final long contentColor4;
        final float elevation4;
        final Function3 header2;
        final long backgroundColor4;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1790971523);
        ComposerKt.sourceInformation($composer3, "C(NavigationRail)P(5,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.unit.Dp,4)168@7492L6,169@7534L32,174@7736L95:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        int i6 = i & 1;
        if (i6 != 0) {
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
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i5 = 32;
                    $dirty |= i5;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i5 = 16;
            $dirty |= i5;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i4 = 256;
                    $dirty |= i4;
                }
            } else {
                contentColor2 = contentColor;
            }
            i4 = 128;
            $dirty |= i4;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            elevation2 = elevation;
        } else if (($changed & 3072) == 0) {
            elevation2 = elevation;
            $dirty |= $composer3.changed(elevation2) ? 2048 : 1024;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            function33 = function3;
        } else if (($changed & 24576) == 0) {
            function33 = function3;
            $dirty |= $composer3.changedInstance(function33) ? 16384 : 8192;
        } else {
            function33 = function3;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function32) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            backgroundColor4 = backgroundColor2;
            elevation4 = elevation2;
            $composer2 = $composer3;
            modifier4 = modifier2;
            contentColor4 = contentColor2;
            header2 = function33;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1482getSurface0d7_KjU();
                }
                if ((i & 4) != 0) {
                    long contentColor5 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                    contentColor2 = contentColor5;
                }
                if (i7 != 0) {
                    elevation2 = NavigationRailDefaults.INSTANCE.m1586getElevationD9Ej5fM();
                }
                if (i8 != 0) {
                    header = null;
                    backgroundColor3 = backgroundColor2;
                    elevation3 = elevation2;
                    i2 = 1790971523;
                    modifier3 = modifier5;
                    i3 = 6;
                    contentColor3 = contentColor2;
                } else {
                    elevation3 = elevation2;
                    header = function33;
                    i2 = 1790971523;
                    modifier3 = modifier5;
                    backgroundColor3 = backgroundColor2;
                    i3 = 6;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    elevation3 = elevation2;
                    header = function33;
                    i3 = 6;
                    i2 = 1790971523;
                    modifier3 = modifier2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                } else {
                    elevation3 = elevation2;
                    header = function33;
                    i3 = 6;
                    i2 = 1790971523;
                    modifier3 = modifier2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.NavigationRail (NavigationRail.kt:173)");
            }
            $composer2 = $composer3;
            m1588NavigationRailafqeVBk(ZeroInsets, modifier3, backgroundColor3, contentColor3, elevation3, header, function32, $composer2, i3 | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentColor4 = contentColor3;
            elevation4 = elevation3;
            header2 = header;
            backgroundColor4 = backgroundColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.NavigationRailKt$NavigationRail$3
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

                public final void invoke(Composer composer, int i9) {
                    NavigationRailKt.m1587NavigationRailHsRjFd4(Modifier.this, backgroundColor4, contentColor4, elevation4, header2, function32, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x03ff  */
    /* renamed from: NavigationRailItem-0S3VyRs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1589NavigationRailItem0S3VyRs(final boolean r37, final kotlin.jvm.functions.Function0<kotlin.Unit> r38, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.ui.Modifier r40, boolean r41, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, boolean r43, androidx.compose.foundation.interaction.MutableInteractionSource r44, long r45, long r47, androidx.compose.runtime.Composer r49, final int r50, final int r51) {
        /*
            Method dump skipped, instructions count: 1057
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.NavigationRailKt.m1589NavigationRailItem0S3VyRs(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: NavigationRailTransition-Klgx-Pg, reason: not valid java name */
    public static final void m1590NavigationRailTransitionKlgxPg(final long activeColor, final long inactiveColor, final boolean selected, final Function3<? super Float, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        long m4161copywmQWz5c;
        Composer $composer2 = $composer.startRestartGroup(-207161906);
        ComposerKt.sourceInformation($composer2, "C(NavigationRailTransition)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color,3)296@12823L126,306@13163L42,303@13024L181:NavigationRail.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-207161906, $dirty, -1, "androidx.compose.material.NavigationRailTransition (NavigationRail.kt:295)");
            }
            final State animationProgress$delegate = AnimateAsStateKt.animateFloatAsState(selected ? 1065353216 : 0, NavigationRailAnimationSpec, 0.0f, null, null, $composer2, 48, 28);
            long color = ColorKt.m4214lerpjxsXWHM(inactiveColor, activeColor, NavigationRailTransition_Klgx_Pg$lambda$2(animationProgress$delegate));
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            m4161copywmQWz5c = Color.m4161copywmQWz5c(color, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(color) : 1.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(color) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(color) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(color) : 0.0f);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{localContentColor.provides(Color.m4153boximpl(m4161copywmQWz5c)), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m4165getAlphaimpl(color)))}, ComposableLambdaKt.rememberComposableLambda(-1688205042, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.NavigationRailKt$NavigationRailTransition$1
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
                    float NavigationRailTransition_Klgx_Pg$lambda$2;
                    ComposerKt.sourceInformation($composer3, "C307@13173L26:NavigationRail.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1688205042, $changed2, -1, "androidx.compose.material.NavigationRailTransition.<anonymous> (NavigationRail.kt:307)");
                        }
                        Function3<Float, Composer, Integer, Unit> function32 = function3;
                        NavigationRailTransition_Klgx_Pg$lambda$2 = NavigationRailKt.NavigationRailTransition_Klgx_Pg$lambda$2(animationProgress$delegate);
                        function32.invoke(Float.valueOf(NavigationRailTransition_Klgx_Pg$lambda$2), $composer3, 0);
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.NavigationRailKt$NavigationRailTransition$2
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
                    NavigationRailKt.m1590NavigationRailTransitionKlgxPg(activeColor, inactiveColor, selected, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float NavigationRailTransition_Klgx_Pg$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationRailItemBaselineLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final float r52, androidx.compose.runtime.Composer r53, final int r54) {
        /*
            Method dump skipped, instructions count: 1046
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.NavigationRailKt.NavigationRailItemBaselineLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, float, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeIcon-3p2s80s, reason: not valid java name */
    public static final MeasureResult m1594placeIcon3p2s80s(MeasureScope $this$placeIcon_u2d3p2s80s, final Placeable iconPlaceable, long constraints) {
        final int iconX = Math.max(0, (Constraints.m6579getMaxWidthimpl(constraints) - iconPlaceable.getWidth()) / 2);
        final int iconY = Math.max(0, (Constraints.m6578getMaxHeightimpl(constraints) - iconPlaceable.getHeight()) / 2);
        return MeasureScope.layout$default($this$placeIcon_u2d3p2s80s, Constraints.m6579getMaxWidthimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.NavigationRailKt$placeIcon$1
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
                Placeable.PlacementScope.placeRelative$default($this$layout, Placeable.this, iconX, iconY, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeLabelAndIcon-DIyivk0, reason: not valid java name */
    public static final MeasureResult m1595placeLabelAndIconDIyivk0(MeasureScope $this$placeLabelAndIcon_u2dDIyivk0, final Placeable labelPlaceable, final Placeable iconPlaceable, long constraints, final float iconPositionAnimationProgress) {
        int baseline = labelPlaceable.get(AlignmentLineKt.getLastBaseline());
        int labelBaselineOffset = $this$placeLabelAndIcon_u2dDIyivk0.mo358roundToPx0680j_4(ItemLabelBaselineBottomOffset);
        final int labelY = (Constraints.m6578getMaxHeightimpl(constraints) - baseline) - labelBaselineOffset;
        final int labelX = (Constraints.m6579getMaxWidthimpl(constraints) - labelPlaceable.getWidth()) / 2;
        final int selectedIconY = $this$placeLabelAndIcon_u2dDIyivk0.mo358roundToPx0680j_4(ItemIconTopOffset);
        int unselectedIconY = (Constraints.m6578getMaxHeightimpl(constraints) - iconPlaceable.getHeight()) / 2;
        final int iconX = (Constraints.m6579getMaxWidthimpl(constraints) - iconPlaceable.getWidth()) / 2;
        int iconDistance = unselectedIconY - selectedIconY;
        final int offset = MathKt.roundToInt(iconDistance * (1.0f - iconPositionAnimationProgress));
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dDIyivk0, Constraints.m6579getMaxWidthimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.NavigationRailKt$placeLabelAndIcon$1
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
                Placeable.PlacementScope.placeRelative$default($this$layout, iconPlaceable, iconX, selectedIconY + offset, 0.0f, 4, null);
            }
        }, 4, null);
    }
}
