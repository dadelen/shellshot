package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: Divider.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a0\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a0\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\t\u001a0\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "Divider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "HorizontalDivider", "HorizontalDivider-9IZ8Weo", "VerticalDivider", "VerticalDivider-9IZ8Weo", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class DividerKt {
    /* renamed from: HorizontalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m2076HorizontalDivider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final long color2;
        Modifier.Companion modifier3;
        final float thickness2;
        Object value$iv;
        final Modifier modifier4;
        final float thickness3;
        final long color3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(75144485);
        ComposerKt.sourceInformation($composer2, "C(HorizontalDivider)P(1,2:c#ui.unit.Dp,0:c#ui.graphics.Color)52@2069L5,54@2134L220,54@2084L270:Divider.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                color2 = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            color2 = color;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            thickness3 = f;
            modifier4 = modifier2;
            color3 = color2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                thickness2 = i4 != 0 ? DividerDefaults.INSTANCE.m2074getThicknessD9Ej5fM() : f;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color2 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                thickness2 = f;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(75144485, $dirty, -1, "androidx.compose.material3.HorizontalDivider (Divider.kt:54)");
            }
            boolean z = true;
            Modifier m703height3ABfNKs = SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), thickness2);
            ComposerKt.sourceInformationMarkerStart($composer2, -873178642, "CC(remember):Divider.kt#9igjgp");
            boolean z2 = ($dirty & 112) == 32;
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.DividerKt$HorizontalDivider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        float f2 = $this$Canvas.mo364toPx0680j_4(thickness2);
                        DrawScope.m4699drawLineNGM6Ib0$default($this$Canvas, color2, OffsetKt.Offset(0.0f, $this$Canvas.mo364toPx0680j_4(thickness2) / 2.0f), OffsetKt.Offset(Size.m3991getWidthimpl($this$Canvas.mo4713getSizeNHjbRc()), $this$Canvas.mo364toPx0680j_4(thickness2) / 2.0f), f2, 0, null, 0.0f, null, 0, 496, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m703height3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            thickness3 = thickness2;
            color3 = color2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DividerKt$HorizontalDivider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    DividerKt.m2076HorizontalDivider9IZ8Weo(Modifier.this, thickness3, color3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: VerticalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m2077VerticalDivider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final long color2;
        Modifier.Companion modifier3;
        final float thickness2;
        Object value$iv;
        final Modifier modifier4;
        final float thickness3;
        final long color3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-1534852205);
        ComposerKt.sourceInformation($composer2, "C(VerticalDivider)P(1,2:c#ui.unit.Dp,0:c#ui.graphics.Color)81@3089L5,83@3154L221,83@3104L271:Divider.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                color2 = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            color2 = color;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            thickness3 = f;
            modifier4 = modifier2;
            color3 = color2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                thickness2 = i4 != 0 ? DividerDefaults.INSTANCE.m2074getThicknessD9Ej5fM() : f;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color2 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                thickness2 = f;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1534852205, $dirty, -1, "androidx.compose.material3.VerticalDivider (Divider.kt:83)");
            }
            boolean z = true;
            Modifier m722width3ABfNKs = SizeKt.m722width3ABfNKs(SizeKt.fillMaxHeight$default(modifier3, 0.0f, 1, null), thickness2);
            ComposerKt.sourceInformationMarkerStart($composer2, -662977123, "CC(remember):Divider.kt#9igjgp");
            boolean z2 = ($dirty & 112) == 32;
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.DividerKt$VerticalDivider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        float f2 = $this$Canvas.mo364toPx0680j_4(thickness2);
                        DrawScope.m4699drawLineNGM6Ib0$default($this$Canvas, color2, OffsetKt.Offset($this$Canvas.mo364toPx0680j_4(thickness2) / 2.0f, 0.0f), OffsetKt.Offset($this$Canvas.mo364toPx0680j_4(thickness2) / 2.0f, Size.m3988getHeightimpl($this$Canvas.mo4713getSizeNHjbRc())), f2, 0, null, 0.0f, null, 0, 496, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m722width3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            thickness3 = thickness2;
            color3 = color2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DividerKt$VerticalDivider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    DividerKt.m2077VerticalDivider9IZ8Weo(Modifier.this, thickness3, color3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(message = "Renamed to HorizontalDivider", replaceWith = @ReplaceWith(expression = "HorizontalDivider(modifier, thickness, color)", imports = {}))
    /* renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public static final void m2075Divider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        long color2;
        Modifier.Companion modifier3;
        float thickness2;
        long color3;
        float targetThickness;
        final Modifier modifier4;
        final float thickness3;
        final long color4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(1562471785);
        ComposerKt.sourceInformation($composer2, "C(Divider)P(1,2:c#ui.unit.Dp,0:c#ui.graphics.Color)100@3659L5,108@3840L78:Divider.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                color2 = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            color2 = color;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            color4 = color2;
            thickness3 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                thickness2 = i4 != 0 ? DividerDefaults.INSTANCE.m2074getThicknessD9Ej5fM() : f;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color3 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                } else {
                    color3 = color2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    modifier3 = modifier2;
                    thickness2 = f;
                    color3 = color2;
                } else {
                    modifier3 = modifier2;
                    thickness2 = f;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1562471785, $dirty, -1, "androidx.compose.material3.Divider (Divider.kt:101)");
            }
            $composer2.startReplaceGroup(-433645095);
            ComposerKt.sourceInformation($composer2, "*104@3767L7");
            if (Dp.m6631equalsimpl0(thickness2, Dp.INSTANCE.m6644getHairlineD9Ej5fM())) {
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float $this$dp$iv = 1.0f / ((Density) consume).getDensity();
                targetThickness = Dp.m6626constructorimpl($this$dp$iv);
            } else {
                targetThickness = thickness2;
            }
            $composer2.endReplaceGroup();
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), targetThickness), color3, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            thickness3 = thickness2;
            color4 = color3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DividerKt$Divider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    DividerKt.m2075Divider9IZ8Weo(Modifier.this, thickness3, color4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
