package androidx.compose.material3;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.ripple.RippleThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.LayoutKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0011H\u0001¢\u0006\u0002\u0010\u0012\u001a>\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\u0010\u0016\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"LocalUsingExpressiveTheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalUsingExpressiveTheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "TextSelectionBackgroundOpacity", "", "MaterialExpressiveTheme", "", "colorScheme", "Landroidx/compose/material3/ColorScheme;", "shapes", "Landroidx/compose/material3/Shapes;", "typography", "Landroidx/compose/material3/Typography;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/Shapes;Landroidx/compose/material3/Typography;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "MaterialTheme", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class MaterialThemeKt {
    private static final ProvidableCompositionLocal<Boolean> LocalUsingExpressiveTheme = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.material3.MaterialThemeKt$LocalUsingExpressiveTheme$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    public static final float TextSelectionBackgroundOpacity = 0.4f;

    public static final void MaterialTheme(ColorScheme colorScheme, Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        ColorScheme colorScheme2;
        Shapes shapes2;
        Typography typography2;
        int $dirty;
        Shapes shapes3;
        final Typography typography3;
        final Shapes shapes4;
        final ColorScheme colorScheme3;
        final Typography typography4;
        int i2;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(-2127166334);
        ComposerKt.sourceInformation($composer2, "C(MaterialTheme)P(!1,2,3)51@2364L11,52@2412L6,53@2463L10,56@2542L32,57@2601L40,67@3101L81,59@2681L501:MaterialTheme.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                colorScheme2 = colorScheme;
                if ($composer2.changed(colorScheme2)) {
                    i4 = 4;
                    $dirty2 |= i4;
                }
            } else {
                colorScheme2 = colorScheme;
            }
            i4 = 2;
            $dirty2 |= i4;
        } else {
            colorScheme2 = colorScheme;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shapes2 = shapes;
                if ($composer2.changed(shapes2)) {
                    i3 = 32;
                    $dirty2 |= i3;
                }
            } else {
                shapes2 = shapes;
            }
            i3 = 16;
            $dirty2 |= i3;
        } else {
            shapes2 = shapes;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                typography2 = typography;
                if ($composer2.changed(typography2)) {
                    i2 = 256;
                    $dirty2 |= i2;
                }
            } else {
                typography2 = typography;
            }
            i2 = 128;
            $dirty2 |= i2;
        } else {
            typography2 = typography;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            typography4 = typography2;
            colorScheme3 = colorScheme2;
            shapes4 = shapes2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 1) != 0) {
                    colorScheme2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6);
                    $dirty2 &= -15;
                }
                if ((i & 2) != 0) {
                    shapes2 = MaterialTheme.INSTANCE.getShapes($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    Shapes shapes5 = shapes2;
                    $dirty = $dirty2 & (-897);
                    shapes3 = shapes5;
                    typography3 = MaterialTheme.INSTANCE.getTypography($composer2, 6);
                } else {
                    Shapes shapes6 = shapes2;
                    $dirty = $dirty2;
                    shapes3 = shapes6;
                    typography3 = typography2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty2 &= -15;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    Shapes shapes7 = shapes2;
                    $dirty = $dirty2 & (-897);
                    shapes3 = shapes7;
                    typography3 = typography2;
                } else {
                    Shapes shapes8 = shapes2;
                    $dirty = $dirty2;
                    shapes3 = shapes8;
                    typography3 = typography2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2127166334, $dirty, -1, "androidx.compose.material3.MaterialTheme (MaterialTheme.kt:55)");
            }
            int $dirty3 = $dirty;
            Indication rippleIndication = RippleKt.m2411rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, $composer2, 0, 7);
            SelectionColors selectionColors = rememberTextSelectionColors(colorScheme2, $composer2, $dirty3 & 14);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ColorSchemeKt.getLocalColorScheme().provides(colorScheme2), IndicationKt.getLocalIndication().provides(rippleIndication), RippleThemeKt.getLocalRippleTheme().provides(CompatRippleTheme.INSTANCE), ShapesKt.getLocalShapes().provides(shapes3), TextSelectionColorsKt.getLocalTextSelectionColors().provides(selectionColors), TypographyKt.getLocalTypography().provides(typography3)}, ComposableLambdaKt.rememberComposableLambda(-1066563262, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialTheme$1
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
                    ComposerKt.sourceInformation($composer3, "C68@3111L65:MaterialTheme.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1066563262, $changed2, -1, "androidx.compose.material3.MaterialTheme.<anonymous> (MaterialTheme.kt:68)");
                    }
                    TextKt.ProvideTextStyle(Typography.this.getBodyLarge(), function2, $composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ColorScheme colorScheme4 = colorScheme2;
            shapes4 = shapes3;
            colorScheme3 = colorScheme4;
            typography4 = typography3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialTheme$2
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

                public final void invoke(Composer composer, int i5) {
                    MaterialThemeKt.MaterialTheme(ColorScheme.this, shapes4, typography4, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void MaterialExpressiveTheme(final ColorScheme colorScheme, final Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        final Typography typography2;
        Composer $composer2;
        final Typography typography3;
        final ColorScheme colorScheme2;
        final Shapes shapes2;
        Composer $composer3 = $composer.startRestartGroup(-1399457222);
        ComposerKt.sourceInformation($composer3, "C(MaterialExpressiveTheme)P(!1,2,3)134@5791L7:MaterialTheme.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(colorScheme) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(shapes) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(typography) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer3.getSkipping()) {
            if (i2 != 0) {
                colorScheme = null;
            }
            if (i3 != 0) {
                shapes = null;
            }
            if (i4 == 0) {
                typography2 = typography;
            } else {
                typography2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1399457222, $dirty2, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:133)");
            }
            ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalUsingExpressiveTheme;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (((Boolean) consume).booleanValue()) {
                $composer3.startReplaceGroup(547059915);
                ComposerKt.sourceInformation($composer3, "135@5810L240");
                $composer3.startReplaceGroup(1126027167);
                ComposerKt.sourceInformation($composer3, "136@5880L11");
                ColorScheme colorScheme3 = colorScheme == null ? MaterialTheme.INSTANCE.getColorScheme($composer3, 6) : colorScheme;
                $composer3.endReplaceGroup();
                $composer3.startReplaceGroup(1126029309);
                ComposerKt.sourceInformation($composer3, "137@5946L10");
                Typography typography4 = typography2 == null ? MaterialTheme.INSTANCE.getTypography($composer3, 6) : typography2;
                $composer3.endReplaceGroup();
                $composer3.startReplaceGroup(1126031253);
                ComposerKt.sourceInformation($composer3, "138@6003L6");
                Shapes shapes3 = shapes == null ? MaterialTheme.INSTANCE.getShapes($composer3, 6) : shapes;
                $composer3.endReplaceGroup();
                MaterialTheme(colorScheme3, shapes3, typography4, function2, $composer3, $dirty2 & 7168, 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer2 = $composer3;
                $composer2.startReplaceGroup(547327197);
                ComposerKt.sourceInformation($composer2, "142@6138L412,142@6072L478");
                CompositionLocalKt.CompositionLocalProvider(LocalUsingExpressiveTheme.provides(true), ComposableLambdaKt.rememberComposableLambda(2050809758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialExpressiveTheme$1
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
                        ComposerKt.sourceInformation($composer4, "C143@6152L388:MaterialTheme.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2050809758, $changed2, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:143)");
                            }
                            ColorScheme colorScheme4 = ColorScheme.this;
                            if (colorScheme4 == null) {
                                colorScheme4 = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme5 = colorScheme4;
                            Shapes shapes4 = shapes;
                            Shapes shapes5 = shapes4 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes4;
                            Typography typography5 = typography2;
                            MaterialThemeKt.MaterialTheme(colorScheme5, shapes5, typography5 == null ? new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, LayoutKt.LargeDimension, null) : typography5, function2, $composer4, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            typography3 = typography2;
            colorScheme2 = colorScheme;
            shapes2 = shapes;
        } else {
            $composer3.skipToGroupEnd();
            typography3 = typography;
            $composer2 = $composer3;
            colorScheme2 = colorScheme;
            shapes2 = shapes;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt$MaterialExpressiveTheme$2
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

                public final void invoke(Composer composer, int i5) {
                    MaterialThemeKt.MaterialExpressiveTheme(ColorScheme.this, shapes2, typography3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalUsingExpressiveTheme() {
        return LocalUsingExpressiveTheme;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    public static final androidx.compose.foundation.text.selection.SelectionColors rememberTextSelectionColors(androidx.compose.material3.ColorScheme r19, androidx.compose.runtime.Composer r20, int r21) {
        /*
            r0 = r20
            r1 = 1866455512(0x6f3fd9d8, float:5.9374994E28)
            java.lang.String r2 = "C(rememberTextSelectionColors)161@6816L198:MaterialTheme.kt#uh7d8r"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r1, r2)
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L19
            r2 = -1
            java.lang.String r3 = "androidx.compose.material3.rememberTextSelectionColors (MaterialTheme.kt:159)"
            r4 = r21
            androidx.compose.runtime.ComposerKt.traceEventStart(r1, r4, r2, r3)
            goto L1b
        L19:
            r4 = r21
        L1b:
            long r5 = r19.getPrimary()
            r1 = -1160063291(0xffffffffbadad6c5, float:-0.0016696086)
            java.lang.String r2 = "CC(remember):MaterialTheme.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r1, r2)
            boolean r1 = r0.changed(r5)
            r2 = r20
            r3 = 0
            java.lang.Object r13 = r2.rememberedValue()
            r14 = 0
            if (r1 != 0) goto L40
            androidx.compose.runtime.Composer$Companion r7 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r7 = r7.getEmpty()
            if (r13 != r7) goto L3e
            goto L40
        L3e:
            r7 = r13
            goto L61
        L40:
            r15 = 0
            androidx.compose.foundation.text.selection.TextSelectionColors r16 = new androidx.compose.foundation.text.selection.TextSelectionColors
            r11 = 14
            r12 = 0
            r7 = 1053609165(0x3ecccccd, float:0.4)
            r8 = 0
            r9 = 0
            r10 = 0
            long r8 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r5, r7, r8, r9, r10, r11, r12)
            r10 = 0
            r6 = r5
            r5 = r16
            r5.<init>(r6, r8, r10)
            r17 = r6
            r7 = r5
            r5 = r17
            r2.updateRememberedValue(r7)
        L61:
            androidx.compose.foundation.text.selection.TextSelectionColors r7 = (androidx.compose.foundation.text.selection.SelectionColors) r7
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L71
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L71:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MaterialThemeKt.rememberTextSelectionColors(androidx.compose.material3.ColorScheme, androidx.compose.runtime.Composer, int):androidx.compose.foundation.text.selection.TextSelectionColors");
    }
}
