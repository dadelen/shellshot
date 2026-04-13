package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Snackbar.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a3\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010\u0011\u001a3\u0010\u0012\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010\u0011\u001a`\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001ax\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u00012\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a \u0010&\u001a\u00020\f2\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u000fH\u0003¢\u0006\u0002\u0010'\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"HeightToFirstLine", "Landroidx/compose/ui/unit/Dp;", "F", "HorizontalSpacing", "HorizontalSpacingButtonSide", "LongButtonVerticalOffset", "SeparateButtonExtraY", "SnackbarMinHeightOneLine", "SnackbarMinHeightTwoLines", "SnackbarVerticalPadding", "TextEndExtraSpacing", "NewLineButtonSnackbar", "", "text", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "action", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "OneRowSnackbar", "Snackbar", "snackbarData", "Landroidx/compose/material/SnackbarData;", "modifier", "Landroidx/compose/ui/Modifier;", "actionOnNewLine", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "actionColor", "elevation", "Snackbar-sPrSdHI", "(Landroidx/compose/material/SnackbarData;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJJFLandroidx/compose/runtime/Composer;II)V", "content", "Snackbar-7zSek6w", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TextOnlySnackbar", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SnackbarKt {
    private static final float HeightToFirstLine = Dp.m6626constructorimpl(30);
    private static final float HorizontalSpacing = Dp.m6626constructorimpl(16);
    private static final float HorizontalSpacingButtonSide = Dp.m6626constructorimpl(8);
    private static final float SeparateButtonExtraY = Dp.m6626constructorimpl(2);
    private static final float SnackbarVerticalPadding = Dp.m6626constructorimpl(6);
    private static final float TextEndExtraSpacing = Dp.m6626constructorimpl(8);
    private static final float LongButtonVerticalOffset = Dp.m6626constructorimpl(12);
    private static final float SnackbarMinHeightOneLine = Dp.m6626constructorimpl(48);
    private static final float SnackbarMinHeightTwoLines = Dp.m6626constructorimpl(68);

    /* renamed from: Snackbar-7zSek6w, reason: not valid java name */
    public static final void m1643Snackbar7zSek6w(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean actionOnNewLine, Shape shape, long backgroundColor, long contentColor, float elevation, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Function2 action;
        final boolean actionOnNewLine2;
        Shape shape2;
        long backgroundColor2;
        int $dirty;
        int i2;
        long contentColor2;
        int $dirty2;
        int $i$f$getDp;
        long contentColor3;
        int i3;
        float elevation2;
        long backgroundColor3;
        Modifier modifier3;
        Shape shape3;
        Composer $composer2;
        final Function2 action2;
        final boolean actionOnNewLine3;
        final Modifier modifier4;
        final Shape shape4;
        final long backgroundColor4;
        final long contentColor4;
        final float elevation3;
        int $dirty3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-558258760);
        ComposerKt.sourceInformation($composer3, "C(Snackbar)P(6!2,7,2:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.unit.Dp)87@4085L6,88@4145L15,89@4202L6,99@4455L464,93@4288L631:Snackbar.kt#jmzs0o");
        int $dirty4 = $changed;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty4 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty4 |= 48;
            action = function2;
        } else if (($changed & 48) == 0) {
            action = function2;
            $dirty4 |= $composer3.changedInstance(action) ? 32 : 16;
        } else {
            action = function2;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty4 |= 384;
            actionOnNewLine2 = actionOnNewLine;
        } else if (($changed & 384) == 0) {
            actionOnNewLine2 = actionOnNewLine;
            $dirty4 |= $composer3.changed(actionOnNewLine2) ? 256 : 128;
        } else {
            actionOnNewLine2 = actionOnNewLine;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 2048;
                    $dirty4 |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i5 = 16384;
                    $dirty4 |= i5;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i5 = 8192;
            $dirty4 |= i5;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                $dirty3 = $dirty4;
                i2 = i7;
                if ($composer3.changed(contentColor)) {
                    i4 = 131072;
                    $dirty = $dirty3 | i4;
                }
            } else {
                $dirty3 = $dirty4;
                i2 = i7;
            }
            i4 = 65536;
            $dirty = $dirty3 | i4;
        } else {
            $dirty = $dirty4;
            i2 = i7;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(elevation) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            action2 = action;
            actionOnNewLine3 = actionOnNewLine2;
            shape4 = shape2;
            backgroundColor4 = backgroundColor2;
            contentColor4 = contentColor;
            elevation3 = elevation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    action = null;
                }
                if (i9 != 0) {
                    actionOnNewLine2 = false;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall();
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = SnackbarDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    contentColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1482getSurface0d7_KjU();
                    $dirty &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i10 != 0) {
                    $i$f$getDp = 1572864;
                    contentColor3 = contentColor2;
                    i3 = -558258760;
                    elevation2 = Dp.m6626constructorimpl(6);
                    $dirty2 = $dirty;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                } else {
                    $dirty2 = $dirty;
                    $i$f$getDp = 1572864;
                    contentColor3 = contentColor2;
                    i3 = -558258760;
                    elevation2 = elevation;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 = $dirty & (-458753);
                    elevation2 = elevation;
                    backgroundColor3 = backgroundColor2;
                    $i$f$getDp = 1572864;
                    i3 = -558258760;
                    contentColor3 = contentColor;
                    modifier3 = modifier2;
                    shape3 = shape2;
                } else {
                    elevation2 = elevation;
                    $dirty2 = $dirty;
                    $i$f$getDp = 1572864;
                    i3 = -558258760;
                    contentColor3 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty2, -1, "androidx.compose.material.Snackbar (Snackbar.kt:92)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(modifier3, shape3, backgroundColor3, contentColor3, null, elevation2, ComposableLambdaKt.rememberComposableLambda(-2084221700, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$1
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C100@4530L4,100@4536L377,100@4465L448:Snackbar.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2084221700, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:100)");
                        }
                        ProvidedValue<Float> provides = ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer4, 6)));
                        final Function2<Composer, Integer, Unit> function23 = action;
                        final Function2<Composer, Integer, Unit> function24 = function22;
                        final boolean z = actionOnNewLine2;
                        CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(1939362236, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$1.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C101@4580L10,102@4645L258,102@4609L294:Snackbar.kt#jmzs0o");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1939362236, $changed3, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:101)");
                                    }
                                    TextStyle textStyle = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                    final Function2<Composer, Integer, Unit> function25 = function23;
                                    final Function2<Composer, Integer, Unit> function26 = function24;
                                    final boolean z2 = z;
                                    TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.rememberComposableLambda(225114541, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt.Snackbar.1.1.1
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

                                        public final void invoke(Composer $composer6, int $changed4) {
                                            ComposerKt.sourceInformation($composer6, "C:Snackbar.kt#jmzs0o");
                                            if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(225114541, $changed4, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous>.<anonymous> (Snackbar.kt:103)");
                                                }
                                                if (function25 == null) {
                                                    $composer6.startReplaceGroup(1850967489);
                                                    ComposerKt.sourceInformation($composer6, "104@4708L25");
                                                    SnackbarKt.TextOnlySnackbar(function26, $composer6, 0);
                                                    $composer6.endReplaceGroup();
                                                } else if (z2) {
                                                    $composer6.startReplaceGroup(1850969582);
                                                    ComposerKt.sourceInformation($composer6, "105@4773L38");
                                                    SnackbarKt.NewLineButtonSnackbar(function26, function25, $composer6, 0);
                                                    $composer6.endReplaceGroup();
                                                } else {
                                                    $composer6.startReplaceGroup(1850971719);
                                                    ComposerKt.sourceInformation($composer6, "106@4840L31");
                                                    SnackbarKt.OneRowSnackbar(function26, function25, $composer6, 0);
                                                    $composer6.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }, $composer5, 54), $composer5, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer4, 54), $composer4, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, $i$f$getDp | ($dirty2 & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 >> 3) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            action2 = action;
            actionOnNewLine3 = actionOnNewLine2;
            modifier4 = modifier3;
            shape4 = shape3;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
            elevation3 = elevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$2
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

                public final void invoke(Composer composer, int i11) {
                    SnackbarKt.m1643Snackbar7zSek6w(Modifier.this, action2, actionOnNewLine3, shape4, backgroundColor4, contentColor4, elevation3, function22, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Snackbar-sPrSdHI, reason: not valid java name */
    public static final void m1644SnackbarsPrSdHI(final SnackbarData snackbarData, Modifier modifier, boolean actionOnNewLine, Shape shape, long backgroundColor, long contentColor, long actionColor, float elevation, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean actionOnNewLine2;
        Shape shape2;
        long backgroundColor2;
        long contentColor2;
        long actionColor2;
        float elevation2;
        long contentColor3;
        int i2;
        final long actionColor3;
        boolean actionOnNewLine3;
        Shape shape3;
        long backgroundColor3;
        final SnackbarData snackbarData2;
        Function2 actionComposable;
        Composer $composer2;
        final long actionColor4;
        final Modifier modifier3;
        final boolean actionOnNewLine4;
        final Shape shape4;
        final long backgroundColor4;
        final long contentColor4;
        final float elevation3;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(258660814);
        ComposerKt.sourceInformation($composer3, "C(Snackbar)P(7,5,1,6,2:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,4:c#ui.unit.Dp)158@7262L6,159@7322L15,160@7379L6,161@7437L18,178@7986L30,176@7914L320:Snackbar.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(snackbarData) : $composer3.changedInstance(snackbarData) ? 4 : 2;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            actionOnNewLine2 = actionOnNewLine;
        } else if (($changed & 384) == 0) {
            actionOnNewLine2 = actionOnNewLine;
            $dirty |= $composer3.changed(actionOnNewLine2) ? 256 : 128;
        } else {
            actionOnNewLine2 = actionOnNewLine;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 2048;
                    $dirty |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i5 = 16384;
                    $dirty |= i5;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i5 = 8192;
            $dirty |= i5;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i4 = 131072;
                    $dirty |= i4;
                }
            } else {
                contentColor2 = contentColor;
            }
            i4 = 65536;
            $dirty |= i4;
        } else {
            contentColor2 = contentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0 && $composer3.changed(actionColor)) {
                i3 = 1048576;
                $dirty |= i3;
            }
            i3 = 524288;
            $dirty |= i3;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(elevation) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            elevation3 = elevation;
            $composer2 = $composer3;
            modifier3 = modifier2;
            shape4 = shape2;
            backgroundColor4 = backgroundColor2;
            actionColor4 = actionColor;
            actionOnNewLine4 = actionOnNewLine2;
            contentColor4 = contentColor2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    actionOnNewLine2 = false;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall();
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = SnackbarDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    contentColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1482getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    actionColor2 = SnackbarDefaults.INSTANCE.getPrimaryActionColor($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    actionColor2 = actionColor;
                }
                if (i9 != 0) {
                    elevation2 = Dp.m6626constructorimpl(6);
                    contentColor3 = contentColor2;
                    i2 = 12582912;
                    actionColor3 = actionColor2;
                    actionOnNewLine3 = actionOnNewLine2;
                    shape3 = shape2;
                    backgroundColor3 = backgroundColor2;
                } else {
                    elevation2 = elevation;
                    contentColor3 = contentColor2;
                    i2 = 12582912;
                    actionColor3 = actionColor2;
                    actionOnNewLine3 = actionOnNewLine2;
                    shape3 = shape2;
                    backgroundColor3 = backgroundColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    elevation2 = elevation;
                    $dirty &= -3670017;
                    shape3 = shape2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    i2 = 12582912;
                    actionColor3 = actionColor;
                    actionOnNewLine3 = actionOnNewLine2;
                } else {
                    actionColor3 = actionColor;
                    elevation2 = elevation;
                    shape3 = shape2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    i2 = 12582912;
                    actionOnNewLine3 = actionOnNewLine2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(258660814, $dirty, -1, "androidx.compose.material.Snackbar (Snackbar.kt:163)");
            }
            final String actionLabel = snackbarData.getActionLabel();
            if (actionLabel != null) {
                $composer3.startReplaceGroup(1609178760);
                ComposerKt.sourceInformation($composer3, "166@7634L243");
                snackbarData2 = snackbarData;
                Function2 rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1843479216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Object value$iv;
                        ComposerKt.sourceInformation($composer4, "C168@7700L44,169@7772L32,170@7832L21,167@7648L219:Snackbar.kt#jmzs0o");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1843479216, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:167)");
                            }
                            ButtonColors m1453textButtonColorsRGew2ao = ButtonDefaults.INSTANCE.m1453textButtonColorsRGew2ao(0L, actionColor3, 0L, $composer4, 3072, 5);
                            ComposerKt.sourceInformationMarkerStart($composer4, 750806346, "CC(remember):Snackbar.kt#9igjgp");
                            boolean invalid$iv = $composer4.changedInstance(snackbarData2);
                            final SnackbarData snackbarData3 = snackbarData2;
                            Object it$iv = $composer4.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1$1$1
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        SnackbarData.this.performAction();
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv);
                            } else {
                                value$iv = it$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            final String str = actionLabel;
                            ButtonKt.TextButton((Function0) value$iv, null, false, null, null, null, null, m1453textButtonColorsRGew2ao, null, ComposableLambdaKt.rememberComposableLambda(-929149933, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$actionComposable$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                    invoke(rowScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RowScope $this$TextButton, Composer $composer5, int $changed3) {
                                    ComposerKt.sourceInformation($composer5, "C170@7834L17:Snackbar.kt#jmzs0o");
                                    if (($changed3 & 17) == 16 && $composer5.getSkipping()) {
                                        $composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-929149933, $changed3, -1, "androidx.compose.material.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:170)");
                                    }
                                    TextKt.m1721Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer4, 54), $composer4, 805306368, 382);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                actionComposable = rememberComposableLambda;
            } else {
                snackbarData2 = snackbarData;
                $composer3.startReplaceGroup(1609445763);
                $composer3.endReplaceGroup();
                actionComposable = null;
            }
            $composer2 = $composer3;
            m1643Snackbar7zSek6w(PaddingKt.m672padding3ABfNKs(modifier2, Dp.m6626constructorimpl(12)), actionComposable, actionOnNewLine3, shape3, backgroundColor3, contentColor3, elevation2, ComposableLambdaKt.rememberComposableLambda(-261845785, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C178@7988L26:Snackbar.kt#jmzs0o");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-261845785, $changed2, -1, "androidx.compose.material.Snackbar.<anonymous> (Snackbar.kt:178)");
                    }
                    TextKt.m1721Text4IGK_g(SnackbarData.this.getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, i2 | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (($dirty >> 3) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            actionColor4 = actionColor3;
            modifier3 = modifier2;
            actionOnNewLine4 = actionOnNewLine3;
            shape4 = shape3;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
            elevation3 = elevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SnackbarKt$Snackbar$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    SnackbarKt.m1644SnackbarsPrSdHI(SnackbarData.this, modifier3, actionOnNewLine4, shape4, backgroundColor4, contentColor4, actionColor4, elevation3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextOnlySnackbar(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, androidx.compose.runtime.Composer r41, final int r42) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SnackbarKt.TextOnlySnackbar(kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0212  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NewLineButtonSnackbar(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r58, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r59, androidx.compose.runtime.Composer r60, final int r61) {
        /*
            Method dump skipped, instructions count: 1058
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SnackbarKt.NewLineButtonSnackbar(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OneRowSnackbar(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54) {
        /*
            Method dump skipped, instructions count: 1006
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SnackbarKt.OneRowSnackbar(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }
}
