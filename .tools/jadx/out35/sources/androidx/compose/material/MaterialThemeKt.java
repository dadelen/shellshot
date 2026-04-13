package androidx.compose.material;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.ripple.RippleThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"MaterialTheme", "", "colors", "Landroidx/compose/material/Colors;", "typography", "Landroidx/compose/material/Typography;", "shapes", "Landroidx/compose/material/Shapes;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material/Colors;Landroidx/compose/material/Typography;Landroidx/compose/material/Shapes;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MaterialThemeKt {
    public static final void MaterialTheme(Colors colors, Typography typography, Shapes shapes, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Colors colors2;
        Typography typography2;
        Shapes shapes2;
        int $dirty;
        final Typography typography3;
        Colors colors3;
        Shapes shapes3;
        Object value$iv;
        final Shapes shapes4;
        final Typography typography4;
        final Colors colors4;
        int i2;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(-891417079);
        ComposerKt.sourceInformation($composer2, "C(MaterialTheme)P(!1,3,2)55@2741L6,56@2792L10,57@2839L6,*60@2914L184,65@3161L32,66@3220L45,70@3426L4,77@3778L113,68@3305L586:MaterialTheme.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i4 = 4;
                    $dirty2 |= i4;
                }
            } else {
                colors2 = colors;
            }
            i4 = 2;
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                typography2 = typography;
                if ($composer2.changed(typography2)) {
                    i3 = 32;
                    $dirty2 |= i3;
                }
            } else {
                typography2 = typography;
            }
            i3 = 16;
            $dirty2 |= i3;
        } else {
            typography2 = typography;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shapes2 = shapes;
                if ($composer2.changed(shapes2)) {
                    i2 = 256;
                    $dirty2 |= i2;
                }
            } else {
                shapes2 = shapes;
            }
            i2 = 128;
            $dirty2 |= i2;
        } else {
            shapes2 = shapes;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            colors4 = colors2;
            typography4 = typography2;
            shapes4 = shapes2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 1) != 0) {
                    colors2 = MaterialTheme.INSTANCE.getColors($composer2, 6);
                    $dirty2 &= -15;
                }
                if ((i & 2) != 0) {
                    typography2 = MaterialTheme.INSTANCE.getTypography($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    Typography typography5 = typography2;
                    $dirty = $dirty2 & (-897);
                    typography3 = typography5;
                    colors3 = colors2;
                    shapes3 = MaterialTheme.INSTANCE.getShapes($composer2, 6);
                } else {
                    Typography typography6 = typography2;
                    $dirty = $dirty2;
                    typography3 = typography6;
                    colors3 = colors2;
                    shapes3 = shapes2;
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
                    Typography typography7 = typography2;
                    $dirty = $dirty2 & (-897);
                    typography3 = typography7;
                    colors3 = colors2;
                    shapes3 = shapes2;
                } else {
                    Typography typography8 = typography2;
                    $dirty = $dirty2;
                    typography3 = typography8;
                    colors3 = colors2;
                    shapes3 = shapes2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-891417079, $dirty, -1, "androidx.compose.material.MaterialTheme (MaterialTheme.kt:59)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 586107060, "CC(remember):MaterialTheme.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = Colors.m1469copypvPzIIM$default(colors3, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false, 8191, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            Colors $this$MaterialTheme_u24lambda_u241 = (Colors) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ColorsKt.updateColorsFrom($this$MaterialTheme_u24lambda_u241, colors3);
            Indication rippleIndication = RippleKt.m1630rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, $composer2, 0, 7);
            SelectionColors selectionColors = MaterialTextSelectionColorsKt.rememberTextSelectionColors($this$MaterialTheme_u24lambda_u241, $composer2, 0);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ColorsKt.getLocalColors().provides($this$MaterialTheme_u24lambda_u241), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer2, 6))), IndicationKt.getLocalIndication().provides(rippleIndication), RippleThemeKt.getLocalRippleTheme().provides(CompatRippleTheme.INSTANCE), ShapesKt.getLocalShapes().provides(shapes3), TextSelectionColorsKt.getLocalTextSelectionColors().provides(selectionColors), TypographyKt.getLocalTypography().provides(typography3)}, ComposableLambdaKt.rememberComposableLambda(-1740102967, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt$MaterialTheme$1
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
                    ComposerKt.sourceInformation($composer3, "C78@3831L54,78@3788L97:MaterialTheme.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1740102967, $changed2, -1, "androidx.compose.material.MaterialTheme.<anonymous> (MaterialTheme.kt:78)");
                        }
                        TextStyle body1 = Typography.this.getBody1();
                        final Function2<Composer, Integer, Unit> function22 = function2;
                        TextKt.ProvideTextStyle(body1, ComposableLambdaKt.rememberComposableLambda(181426554, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt$MaterialTheme$1.1
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

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C79@3845L30:MaterialTheme.kt#jmzs0o");
                                if (($changed3 & 3) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(181426554, $changed3, -1, "androidx.compose.material.MaterialTheme.<anonymous>.<anonymous> (MaterialTheme.kt:79)");
                                }
                                MaterialTheme_androidKt.PlatformMaterialTheme(function22, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer3, 54), $composer3, 48);
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
            shapes4 = shapes3;
            typography4 = typography3;
            colors4 = colors3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt$MaterialTheme$2
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
                    MaterialThemeKt.MaterialTheme(Colors.this, typography4, shapes4, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
