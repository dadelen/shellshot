package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: TabRow.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J0\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0016J\u0012\u0010\u001a\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\bR\u0019\u0010\f\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\r\u0010\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d²\u0006\n\u0010\u001e\u001a\u00020\u0006X\u008a\u0084\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0006X\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material/TabRowDefaults;", "", "()V", "DividerOpacity", "", "DividerThickness", "Landroidx/compose/ui/unit/Dp;", "getDividerThickness-D9Ej5fM", "()F", "F", "IndicatorHeight", "getIndicatorHeight-D9Ej5fM", "ScrollableTabRowPadding", "getScrollableTabRowPadding-D9Ej5fM", "Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "color", "Landroidx/compose/ui/graphics/Color;", "Divider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "Indicator", "height", "Indicator-9IZ8Weo", "tabIndicatorOffset", "currentTabPosition", "Landroidx/compose/material/TabPosition;", "material_release", "currentTabWidth", "indicatorOffset"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TabRowDefaults {
    public static final int $stable = 0;
    public static final float DividerOpacity = 0.12f;
    public static final TabRowDefaults INSTANCE = new TabRowDefaults();
    private static final float DividerThickness = Dp.m6626constructorimpl(1);
    private static final float IndicatorHeight = Dp.m6626constructorimpl(2);
    private static final float ScrollableTabRowPadding = Dp.m6626constructorimpl(52);

    private TabRowDefaults() {
    }

    /* renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public final void m1684Divider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        long j;
        float thickness2;
        float thickness3;
        long color2;
        Modifier modifier3;
        long color3;
        final Modifier modifier4;
        final long color4;
        final float thickness4;
        int i2;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(910934799);
        ComposerKt.sourceInformation($composer2, "C(Divider)P(1,2:c#ui.unit.Dp,0:c#ui.graphics.Color)371@16495L7,373@16574L66:TabRow.kt#jmzs0o");
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
                f = thickness;
                if ($composer2.changed(f)) {
                    i3 = 32;
                    $dirty |= i3;
                }
            } else {
                f = thickness;
            }
            i3 = 16;
            $dirty |= i3;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                if ($composer2.changed(j)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                j = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            j = color;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(this) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            thickness4 = f;
            color4 = j;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    thickness2 = DividerThickness;
                    $dirty &= -113;
                } else {
                    thickness2 = f;
                }
                if ((i & 4) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    color3 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(((Color) consume).m4173unboximpl()) : 0.0f);
                    $dirty &= -897;
                    thickness3 = thickness2;
                    color2 = color3;
                    modifier3 = modifier5;
                } else {
                    thickness3 = thickness2;
                    color2 = j;
                    modifier3 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                thickness3 = f;
                color2 = j;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(910934799, $dirty, -1, "androidx.compose.material.TabRowDefaults.Divider (TabRow.kt:372)");
            }
            DividerKt.m1522DivideroMI9zvI(modifier3, color2, thickness3, 0.0f, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty << 3) & 896), 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            color4 = color2;
            thickness4 = thickness3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowDefaults$Divider$1
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
                    TabRowDefaults.this.m1684Divider9IZ8Weo(modifier4, thickness4, color4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Indicator-9IZ8Weo, reason: not valid java name */
    public final void m1685Indicator9IZ8Weo(Modifier modifier, float height, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        long j;
        final Modifier.Companion modifier3;
        final float height2;
        long color2;
        final long color3;
        int i2;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(1499002201);
        ComposerKt.sourceInformation($composer2, "C(Indicator)P(2,1:c#ui.unit.Dp,0:c#ui.graphics.Color)388@17083L7,390@17107L142:TabRow.kt#jmzs0o");
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
                f = height;
                if ($composer2.changed(f)) {
                    i3 = 32;
                    $dirty |= i3;
                }
            } else {
                f = height;
            }
            i3 = 16;
            $dirty |= i3;
        } else {
            f = height;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                if ($composer2.changed(j)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                j = color;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            j = color;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(this) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            height2 = f;
            color3 = j;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    height2 = IndicatorHeight;
                    $dirty &= -113;
                } else {
                    height2 = f;
                }
                if ((i & 4) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -897;
                    color2 = ((Color) consume).m4173unboximpl();
                } else {
                    color2 = j;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                height2 = f;
                color2 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1499002201, $dirty, -1, "androidx.compose.material.TabRowDefaults.Indicator (TabRow.kt:389)");
            }
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), height2), color2, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color3 = color2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowDefaults$Indicator$1
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
                    TabRowDefaults.this.m1685Indicator9IZ8Weo(modifier3, height2, color3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public final Modifier tabIndicatorOffset(Modifier $this$tabIndicatorOffset, final TabPosition currentTabPosition) {
        return ComposedModifierKt.composed($this$tabIndicatorOffset, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TabRowDefaults$tabIndicatorOffset$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("tabIndicatorOffset");
                $this$null.setValue(TabPosition.this);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new TabRowDefaults$tabIndicatorOffset$2(currentTabPosition));
    }

    /* renamed from: getDividerThickness-D9Ej5fM, reason: not valid java name */
    public final float m1686getDividerThicknessD9Ej5fM() {
        return DividerThickness;
    }

    /* renamed from: getIndicatorHeight-D9Ej5fM, reason: not valid java name */
    public final float m1687getIndicatorHeightD9Ej5fM() {
        return IndicatorHeight;
    }

    /* renamed from: getScrollableTabRowPadding-D9Ej5fM, reason: not valid java name */
    public final float m1688getScrollableTabRowPaddingD9Ej5fM() {
        return ScrollableTabRowPadding;
    }
}
