package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.tokens.SnackbarTokens;
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
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Snackbar.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001ae\u0010\n\u001a\u00020\u000b2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001ag\u0010\u0018\u001a\u00020\u000b2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000e2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0017\u001aj\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00142\b\b\u0002\u0010&\u001a\u00020\u00142\b\b\u0002\u0010'\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u0099\u0001\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020 2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u000e2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00142\b\b\u0002\u0010&\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"ContainerMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "HeightToFirstLine", "HorizontalSpacing", "HorizontalSpacingButtonSide", "LongButtonVerticalOffset", "SeparateButtonExtraY", "SnackbarVerticalPadding", "TextEndExtraSpacing", "NewLineButtonSnackbar", "", "text", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "action", "dismissAction", "actionTextStyle", "Landroidx/compose/ui/text/TextStyle;", "actionContentColor", "Landroidx/compose/ui/graphics/Color;", "dismissActionContentColor", "NewLineButtonSnackbar-kKq0p4A", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JJLandroidx/compose/runtime/Composer;I)V", "OneRowSnackbar", "actionTextColor", "dismissActionColor", "OneRowSnackbar-kKq0p4A", "Snackbar", "snackbarData", "Landroidx/compose/material3/SnackbarData;", "modifier", "Landroidx/compose/ui/Modifier;", "actionOnNewLine", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "contentColor", "actionColor", "Snackbar-sDKtq54", "(Landroidx/compose/material3/SnackbarData;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJJJJLandroidx/compose/runtime/Composer;II)V", "content", "Snackbar-eQBnUkQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/graphics/Shape;JJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SnackbarKt {
    private static final float ContainerMaxWidth = Dp.m6626constructorimpl(600);
    private static final float HeightToFirstLine = Dp.m6626constructorimpl(30);
    private static final float HorizontalSpacing = Dp.m6626constructorimpl(16);
    private static final float HorizontalSpacingButtonSide = Dp.m6626constructorimpl(8);
    private static final float SeparateButtonExtraY = Dp.m6626constructorimpl(2);
    private static final float SnackbarVerticalPadding = Dp.m6626constructorimpl(6);
    private static final float TextEndExtraSpacing = Dp.m6626constructorimpl(8);
    private static final float LongButtonVerticalOffset = Dp.m6626constructorimpl(12);

    /* renamed from: Snackbar-eQBnUkQ, reason: not valid java name */
    public static final void m2532SnackbareQBnUkQ(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, boolean actionOnNewLine, Shape shape, long containerColor, long contentColor, long actionContentColor, long dismissActionContentColor, final Function2<? super Composer, ? super Integer, Unit> function23, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2 function24;
        Function2 function25;
        boolean z;
        Shape shape2;
        long j;
        int $dirty;
        int i2;
        Modifier.Companion modifier3;
        Function2 action;
        Function2 dismissAction;
        boolean actionOnNewLine2;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        long actionContentColor2;
        long dismissActionContentColor2;
        int $dirty2;
        final Modifier modifier4;
        Composer $composer2;
        final Function2 action2;
        final Shape shape4;
        final boolean actionOnNewLine3;
        final long contentColor3;
        final long contentColor4;
        final Function2 dismissAction2;
        final long containerColor3;
        final long containerColor4;
        int i3;
        int i4;
        int $dirty3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-1235788955);
        ComposerKt.sourceInformation($composer3, "C(Snackbar)P(8!1,6,2,9,3:c#ui.graphics.Color,5:c#ui.graphics.Color,1:c#ui.graphics.Color,7:c#ui.graphics.Color)107@5066L5,108@5118L5,109@5168L12,110@5231L18,111@5307L25,120@5574L1123,114@5378L1319:Snackbar.kt#uh7d8r");
        int $dirty4 = $changed;
        int i8 = i & 1;
        if (i8 != 0) {
            $dirty4 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 2;
        if (i9 != 0) {
            $dirty4 |= 48;
            function24 = function2;
        } else if (($changed & 48) == 0) {
            function24 = function2;
            $dirty4 |= $composer3.changedInstance(function24) ? 32 : 16;
        } else {
            function24 = function2;
        }
        int i10 = i & 4;
        if (i10 != 0) {
            $dirty4 |= 384;
            function25 = function22;
        } else if (($changed & 384) == 0) {
            function25 = function22;
            $dirty4 |= $composer3.changedInstance(function25) ? 256 : 128;
        } else {
            function25 = function22;
        }
        int i11 = i & 8;
        if (i11 != 0) {
            $dirty4 |= 3072;
            z = actionOnNewLine;
        } else if (($changed & 3072) == 0) {
            z = actionOnNewLine;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = actionOnNewLine;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 16384;
                    $dirty4 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 8192;
            $dirty4 |= i7;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                j = containerColor;
                if ($composer3.changed(j)) {
                    i6 = 131072;
                    $dirty4 |= i6;
                }
            } else {
                j = containerColor;
            }
            i6 = 65536;
            $dirty4 |= i6;
        } else {
            j = containerColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                i2 = i8;
                if ($composer3.changed(contentColor)) {
                    i5 = 1048576;
                    $dirty = $dirty3 | i5;
                }
            } else {
                $dirty3 = $dirty4;
                i2 = i8;
            }
            i5 = 524288;
            $dirty = $dirty3 | i5;
        } else {
            $dirty = $dirty4;
            i2 = i8;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(actionContentColor)) {
                i4 = 8388608;
                $dirty |= i4;
            }
            i4 = 4194304;
            $dirty |= i4;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0 && $composer3.changed(dismissActionContentColor)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty |= i3;
            }
            i3 = 33554432;
            $dirty |= i3;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function23) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            action2 = function24;
            dismissAction2 = function25;
            actionOnNewLine3 = z;
            shape4 = shape2;
            containerColor3 = j;
            contentColor3 = contentColor;
            containerColor4 = actionContentColor;
            contentColor4 = dismissActionContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                action = i9 != 0 ? null : function24;
                dismissAction = i10 != 0 ? null : function25;
                actionOnNewLine2 = i11 != 0 ? false : z;
                if ((i & 16) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    containerColor2 = SnackbarDefaults.INSTANCE.getColor($composer3, 6);
                    $dirty &= -458753;
                } else {
                    containerColor2 = j;
                }
                if ((i & 64) != 0) {
                    contentColor2 = SnackbarDefaults.INSTANCE.getContentColor($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                if ((i & 128) != 0) {
                    actionContentColor2 = SnackbarDefaults.INSTANCE.getActionContentColor($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    actionContentColor2 = actionContentColor;
                }
                if ((i & 256) != 0) {
                    dismissActionContentColor2 = SnackbarDefaults.INSTANCE.getDismissActionContentColor($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                } else {
                    dismissActionContentColor2 = dismissActionContentColor;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    actionContentColor2 = actionContentColor;
                    dismissActionContentColor2 = dismissActionContentColor;
                    $dirty2 = $dirty & (-234881025);
                    modifier3 = modifier2;
                    action = function24;
                    dismissAction = function25;
                    actionOnNewLine2 = z;
                    shape3 = shape2;
                    containerColor2 = j;
                    contentColor2 = contentColor;
                } else {
                    actionContentColor2 = actionContentColor;
                    dismissActionContentColor2 = dismissActionContentColor;
                    modifier3 = modifier2;
                    action = function24;
                    dismissAction = function25;
                    actionOnNewLine2 = z;
                    shape3 = shape2;
                    containerColor2 = j;
                    $dirty2 = $dirty;
                    contentColor2 = contentColor;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1235788955, $dirty2, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:113)");
            }
            final Function2 action3 = action;
            final Function2 dismissAction3 = dismissAction;
            final boolean actionOnNewLine4 = actionOnNewLine2;
            final long actionContentColor3 = actionContentColor2;
            final long dismissActionContentColor3 = dismissActionContentColor2;
            Modifier modifier5 = modifier3;
            SurfaceKt.m2547SurfaceT9BRK9s(modifier5, shape3, containerColor2, contentColor2, 0.0f, SnackbarTokens.INSTANCE.m3505getContainerElevationD9Ej5fM(), null, ComposableLambdaKt.rememberComposableLambda(-1829663446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1
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
                    ComposerKt.sourceInformation($composer4, "C121@5634L5,122@5705L5,123@5779L912,123@5719L972:Snackbar.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1829663446, $changed2, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:121)");
                        }
                        TextStyle textStyle = TypographyKt.getValue(SnackbarTokens.INSTANCE.getSupportingTextFont(), $composer4, 6);
                        final TextStyle actionTextStyle = TypographyKt.getValue(SnackbarTokens.INSTANCE.getActionLabelTextFont(), $composer4, 6);
                        ProvidedValue<TextStyle> provides = TextKt.getLocalTextStyle().provides(textStyle);
                        final boolean z2 = actionOnNewLine4;
                        final Function2<Composer, Integer, Unit> function26 = action3;
                        final Function2<Composer, Integer, Unit> function27 = function23;
                        final Function2<Composer, Integer, Unit> function28 = dismissAction3;
                        final long j2 = actionContentColor3;
                        final long j3 = dismissActionContentColor3;
                        CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(835891690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$1.1
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
                                ComposerKt.sourceInformation($composer5, "C:Snackbar.kt#uh7d8r");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(835891690, $changed3, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:124)");
                                    }
                                    if (z2 && function26 != null) {
                                        $composer5.startReplaceGroup(-810715387);
                                        ComposerKt.sourceInformation($composer5, "126@5873L383");
                                        SnackbarKt.m2530NewLineButtonSnackbarkKq0p4A(function27, function26, function28, actionTextStyle, j2, j3, $composer5, 0);
                                        $composer5.endReplaceGroup();
                                    } else {
                                        $composer5.startReplaceGroup(-810701708);
                                        ComposerKt.sourceInformation($composer5, "135@6301L366");
                                        SnackbarKt.m2531OneRowSnackbarkKq0p4A(function27, function26, function28, actionTextStyle, j2, j3, $composer5, 0);
                                        $composer5.endReplaceGroup();
                                    }
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
            }, $composer3, 54), $composer3, ($dirty2 & 14) | 12779520 | (($dirty2 >> 9) & 112) | (($dirty2 >> 9) & 896) | (($dirty2 >> 9) & 7168), 80);
            modifier4 = modifier5;
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            action2 = action3;
            shape4 = shape3;
            actionOnNewLine3 = actionOnNewLine4;
            contentColor3 = contentColor2;
            contentColor4 = dismissActionContentColor2;
            dismissAction2 = dismissAction;
            containerColor3 = containerColor2;
            containerColor4 = actionContentColor2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$2
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

                public final void invoke(Composer composer, int i12) {
                    SnackbarKt.m2532SnackbareQBnUkQ(Modifier.this, action2, dismissAction2, actionOnNewLine3, shape4, containerColor3, contentColor3, containerColor4, contentColor4, function23, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Snackbar-sDKtq54, reason: not valid java name */
    public static final void m2533SnackbarsDKtq54(final SnackbarData snackbarData, Modifier modifier, boolean actionOnNewLine, Shape shape, long containerColor, long contentColor, long actionColor, long actionContentColor, long dismissActionContentColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        Shape shape2;
        long j;
        long j2;
        int $dirty;
        int i2;
        final Modifier.Companion modifier3;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        long actionColor2;
        long actionContentColor2;
        long dismissActionContentColor2;
        long contentColor3;
        final long actionColor3;
        long actionContentColor3;
        boolean actionOnNewLine2;
        Shape shape4;
        long containerColor3;
        Composer $composer2;
        final long actionColor4;
        final boolean actionOnNewLine3;
        final Shape shape5;
        final long containerColor4;
        final long contentColor4;
        final long actionContentColor4;
        final long dismissActionContentColor3;
        int i3;
        int i4;
        int $dirty2;
        int i5;
        int i6;
        int i7;
        int i8;
        Composer $composer3 = $composer.startRestartGroup(274621471);
        ComposerKt.sourceInformation($composer3, "C(Snackbar)P(8,6,2,7,3:c#ui.graphics.Color,4:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color)205@9602L5,206@9654L5,207@9704L12,208@9760L11,209@9822L18,210@9898L25,251@11371L38,241@10959L456:Snackbar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(snackbarData) ? 4 : 2;
        }
        int i9 = i & 2;
        if (i9 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 4;
        if (i10 != 0) {
            $dirty3 |= 384;
            z = actionOnNewLine;
        } else if (($changed & 384) == 0) {
            z = actionOnNewLine;
            $dirty3 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = actionOnNewLine;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i8 = 2048;
                    $dirty3 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 1024;
            $dirty3 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j = containerColor;
                if ($composer3.changed(j)) {
                    i7 = 16384;
                    $dirty3 |= i7;
                }
            } else {
                j = containerColor;
            }
            i7 = 8192;
            $dirty3 |= i7;
        } else {
            j = containerColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                j2 = contentColor;
                if ($composer3.changed(j2)) {
                    i6 = 131072;
                    $dirty3 |= i6;
                }
            } else {
                j2 = contentColor;
            }
            i6 = 65536;
            $dirty3 |= i6;
        } else {
            j2 = contentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                $dirty2 = $dirty3;
                i2 = i9;
                if ($composer3.changed(actionColor)) {
                    i5 = 1048576;
                    $dirty = $dirty2 | i5;
                }
            } else {
                $dirty2 = $dirty3;
                i2 = i9;
            }
            i5 = 524288;
            $dirty = $dirty2 | i5;
        } else {
            $dirty = $dirty3;
            i2 = i9;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(actionContentColor)) {
                i4 = 8388608;
                $dirty |= i4;
            }
            i4 = 4194304;
            $dirty |= i4;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0 && $composer3.changed(dismissActionContentColor)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty |= i3;
            }
            i3 = 33554432;
            $dirty |= i3;
        }
        if ((38347923 & $dirty) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
            actionOnNewLine3 = z;
            shape5 = shape2;
            containerColor4 = j;
            contentColor4 = j2;
            actionColor4 = actionColor;
            actionContentColor4 = actionContentColor;
            dismissActionContentColor3 = dismissActionContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean actionOnNewLine4 = i10 != 0 ? false : z;
                if ((i & 8) != 0) {
                    shape3 = SnackbarDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    containerColor2 = SnackbarDefaults.INSTANCE.getColor($composer3, 6);
                    $dirty &= -57345;
                } else {
                    containerColor2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = SnackbarDefaults.INSTANCE.getContentColor($composer3, 6);
                    $dirty &= -458753;
                } else {
                    contentColor2 = j2;
                }
                if ((i & 64) != 0) {
                    actionColor2 = SnackbarDefaults.INSTANCE.getActionColor($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    actionColor2 = actionColor;
                }
                if ((i & 128) != 0) {
                    actionContentColor2 = SnackbarDefaults.INSTANCE.getActionContentColor($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    actionContentColor2 = actionContentColor;
                }
                if ((i & 256) != 0) {
                    $dirty = (-234881025) & $dirty;
                    actionColor3 = actionColor2;
                    actionContentColor3 = actionContentColor2;
                    dismissActionContentColor2 = SnackbarDefaults.INSTANCE.getDismissActionContentColor($composer3, 6);
                    actionOnNewLine2 = actionOnNewLine4;
                    shape4 = shape3;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                } else {
                    dismissActionContentColor2 = dismissActionContentColor;
                    contentColor3 = contentColor2;
                    actionColor3 = actionColor2;
                    actionContentColor3 = actionContentColor2;
                    actionOnNewLine2 = actionOnNewLine4;
                    shape4 = shape3;
                    containerColor3 = containerColor2;
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
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    actionColor3 = actionColor;
                    actionContentColor3 = actionContentColor;
                    dismissActionContentColor2 = dismissActionContentColor;
                    $dirty &= -234881025;
                    modifier3 = modifier2;
                    actionOnNewLine2 = z;
                    shape4 = shape2;
                    containerColor3 = j;
                    contentColor3 = j2;
                } else {
                    actionColor3 = actionColor;
                    actionContentColor3 = actionContentColor;
                    dismissActionContentColor2 = dismissActionContentColor;
                    modifier3 = modifier2;
                    actionOnNewLine2 = z;
                    shape4 = shape2;
                    containerColor3 = j;
                    contentColor3 = j2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274621471, $dirty, -1, "androidx.compose.material3.Snackbar (Snackbar.kt:211)");
            }
            final String actionLabel = snackbarData.getVisuals().getActionLabel();
            $composer3.startReplaceGroup(1561344786);
            ComposerKt.sourceInformation($composer3, "215@10097L267");
            Function2 actionComposable = actionLabel != null ? ComposableLambdaKt.rememberComposableLambda(-1378313599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1
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
                    ComposerKt.sourceInformation($composer4, "C217@10171L44,218@10247L32,219@10311L21,216@10115L235:Snackbar.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1378313599, $changed2, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:216)");
                        }
                        ButtonColors m1824textButtonColorsro_MJ88 = ButtonDefaults.INSTANCE.m1824textButtonColorsro_MJ88(0L, actionColor3, 0L, 0L, $composer4, 24576, 13);
                        ComposerKt.sourceInformationMarkerStart($composer4, 642119911, "CC(remember):Snackbar.kt#9igjgp");
                        boolean invalid$iv = $composer4.changed(snackbarData);
                        final SnackbarData snackbarData2 = snackbarData;
                        Object it$iv = $composer4.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1$1$1
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
                        ButtonKt.TextButton((Function0) value$iv, null, false, null, m1824textButtonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(521110564, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$actionComposable$1.2
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
                                ComposerKt.sourceInformation($composer5, "C219@10313L17:Snackbar.kt#uh7d8r");
                                if (($changed3 & 17) == 16 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(521110564, $changed3, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:219)");
                                }
                                TextKt.m2697Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer4, 54), $composer4, 805306368, 494);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54) : null;
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(1561358724);
            ComposerKt.sourceInformation($composer3, "227@10548L362");
            Function2 dismissActionComposable = snackbarData.getVisuals().getWithDismissAction() ? ComposableLambdaKt.rememberComposableLambda(-1812633777, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$dismissActionComposable$1
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
                    ComposerKt.sourceInformation($composer4, "C229@10608L26,228@10566L330:Snackbar.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1812633777, $changed2, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:228)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer4, 642131457, "CC(remember):Snackbar.kt#9igjgp");
                        boolean invalid$iv = $composer4.changed(SnackbarData.this);
                        final SnackbarData snackbarData2 = SnackbarData.this;
                        Object it$iv = $composer4.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$dismissActionComposable$1$1$1
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
                                    SnackbarData.this.dismiss();
                                }
                            };
                            $composer4.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        IconButtonKt.IconButton((Function0) value$iv, null, false, null, null, ComposableSingletons$SnackbarKt.INSTANCE.m1986getLambda1$material3_release(), $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54) : null;
            $composer3.endReplaceGroup();
            $composer2 = $composer3;
            m2532SnackbareQBnUkQ(PaddingKt.m672padding3ABfNKs(modifier3, Dp.m6626constructorimpl(12)), actionComposable, dismissActionComposable, actionOnNewLine2, shape4, containerColor3, contentColor3, actionContentColor3, dismissActionContentColor2, ComposableLambdaKt.rememberComposableLambda(-1266389126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C251@11373L34:Snackbar.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1266389126, $changed2, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:251)");
                    }
                    TextKt.m2697Text4IGK_g(SnackbarData.this.getVisuals().getMessage(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty << 3) & 7168) | 805306368 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)) | (29360128 & $dirty) | (234881024 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            actionColor4 = actionColor3;
            actionOnNewLine3 = actionOnNewLine2;
            shape5 = shape4;
            containerColor4 = containerColor3;
            contentColor4 = contentColor3;
            actionContentColor4 = actionContentColor3;
            dismissActionContentColor3 = dismissActionContentColor2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarKt$Snackbar$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i11) {
                    SnackbarKt.m2533SnackbarsDKtq54(SnackbarData.this, modifier3, actionOnNewLine3, shape5, containerColor4, contentColor4, actionColor4, actionContentColor4, dismissActionContentColor3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x04f9  */
    /* renamed from: NewLineButtonSnackbar-kKq0p4A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2530NewLineButtonSnackbarkKq0p4A(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r73, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r74, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r75, final androidx.compose.ui.text.TextStyle r76, final long r77, final long r79, androidx.compose.runtime.Composer r81, final int r82) {
        /*
            Method dump skipped, instructions count: 1557
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnackbarKt.m2530NewLineButtonSnackbarkKq0p4A(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, long, long, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:116:0x05b7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0476  */
    /* renamed from: OneRowSnackbar-kKq0p4A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2531OneRowSnackbarkKq0p4A(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r57, final androidx.compose.ui.text.TextStyle r58, final long r59, final long r61, androidx.compose.runtime.Composer r63, final int r64) {
        /*
            Method dump skipped, instructions count: 1495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnackbarKt.m2531OneRowSnackbarkKq0p4A(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, long, long, androidx.compose.runtime.Composer, int):void");
    }
}
