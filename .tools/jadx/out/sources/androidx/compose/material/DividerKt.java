package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: Divider.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a:\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"DividerAlpha", "", "Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "thickness", "Landroidx/compose/ui/unit/Dp;", "startIndent", "Divider-oMI9zvI", "(Landroidx/compose/ui/Modifier;JFFLandroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DividerKt {
    private static final float DividerAlpha = 0.12f;

    /* renamed from: Divider-oMI9zvI, reason: not valid java name */
    public static final void m1522DivideroMI9zvI(Modifier modifier, long color, float thickness, float startIndent, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float thickness2;
        float startIndent2;
        Modifier.Companion modifier3;
        long color2;
        int i2;
        Modifier.Companion indentMod;
        float targetThickness;
        final Modifier modifier4;
        final long color3;
        final float thickness3;
        final float startIndent3;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(-1249392198);
        ComposerKt.sourceInformation($composer2, "C(Divider)P(1,0:c#ui.graphics.Color,3:c#ui.unit.Dp,2:c#ui.unit.Dp)45@1819L6,59@2200L147:Divider.kt#jmzs0o");
        int $dirty = $changed;
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = color;
                if ($composer2.changed(j)) {
                    i3 = 32;
                    $dirty |= i3;
                }
            } else {
                j = color;
            }
            i3 = 16;
            $dirty |= i3;
        } else {
            j = color;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            thickness2 = thickness;
        } else if (($changed & 384) == 0) {
            thickness2 = thickness;
            $dirty |= $composer2.changed(thickness2) ? 256 : 128;
        } else {
            thickness2 = thickness;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            startIndent2 = startIndent;
        } else if (($changed & 3072) == 0) {
            startIndent2 = startIndent;
            $dirty |= $composer2.changed(startIndent2) ? 2048 : 1024;
        } else {
            startIndent2 = startIndent;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            color3 = j;
            thickness3 = thickness2;
            startIndent3 = startIndent2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer2, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
                    $dirty &= -113;
                } else {
                    color2 = j;
                }
                if (i5 != 0) {
                    thickness2 = Dp.m6626constructorimpl(1);
                }
                if (i6 != 0) {
                    startIndent2 = Dp.m6626constructorimpl(0);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                modifier3 = modifier2;
                color2 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1249392198, $dirty, -1, "androidx.compose.material.Divider (Divider.kt:48)");
            }
            if (startIndent2 == 0.0f) {
                i2 = 1;
                indentMod = Modifier.INSTANCE;
            } else {
                i2 = 1;
                indentMod = PaddingKt.m676paddingqDBjuR0$default(Modifier.INSTANCE, startIndent2, 0.0f, 0.0f, 0.0f, 14, null);
            }
            if (Dp.m6631equalsimpl0(thickness2, Dp.INSTANCE.m6644getHairlineD9Ej5fM())) {
                $composer2.startReplaceGroup(-129374855);
                ComposerKt.sourceInformation($composer2, "*55@2139L7");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float $this$dp$iv = 1.0f / ((Density) consume).getDensity();
                targetThickness = Dp.m6626constructorimpl($this$dp$iv);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-129316234);
                $composer2.endReplaceGroup();
                targetThickness = thickness2;
            }
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3.then(indentMod), 0.0f, i2, null), targetThickness), color2, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            color3 = color2;
            thickness3 = thickness2;
            startIndent3 = startIndent2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DividerKt$Divider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    DividerKt.m1522DivideroMI9zvI(Modifier.this, color3, thickness3, startIndent3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
