package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: Switch.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001al\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001aR\u0010 \u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0003¢\u0006\u0002\u0010$\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\t\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\n\u0010\u000b\"\u0010\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\r\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000e\u0010\u000b¨\u0006%"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "SnapSpec", "Landroidx/compose/animation/core/SnapSpec;", "SwitchHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchWidth", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPadding", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "thumbContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "enabled", "colors", "Landroidx/compose/material3/SwitchColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/interaction/InteractionSource;", "thumbShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/SwitchColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final SnapSpec<Float> SnapSpec;
    private static final float ThumbPadding;
    private static final float ThumbDiameter = SwitchTokens.INSTANCE.m3524getSelectedHandleWidthD9Ej5fM();
    private static final float UncheckedThumbDiameter = SwitchTokens.INSTANCE.m3531getUnselectedHandleWidthD9Ej5fM();
    private static final float SwitchWidth = SwitchTokens.INSTANCE.m3529getTrackWidthD9Ej5fM();
    private static final float SwitchHeight = SwitchTokens.INSTANCE.m3527getTrackHeightD9Ej5fM();

    public static final void Switch(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean enabled, SwitchColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        boolean z;
        final Modifier modifier2;
        Function2 function22;
        boolean enabled2;
        SwitchColors colors2;
        MutableInteractionSource mutableInteractionSource;
        Modifier modifier3;
        int $dirty;
        boolean enabled3;
        SwitchColors colors3;
        MutableInteractionSource interactionSource2;
        Function2 thumbContent;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        final boolean enabled4;
        final SwitchColors colors4;
        final Function2 thumbContent2;
        final MutableInteractionSource interactionSource4;
        Object value$iv;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(1580463220);
        ComposerKt.sourceInformation($composer3, "C(Switch)P(!1,5,4,6,2)97@4514L8,129@5619L5,119@5244L424:Switch.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = checked;
        } else if (($changed & 6) == 0) {
            z = checked;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = checked;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function22 = function2;
        } else if (($changed & 3072) == 0) {
            function22 = function2;
            $dirty2 |= $composer3.changedInstance(function22) ? 2048 : 1024;
        } else {
            function22 = function2;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i2 = 131072;
                    $dirty2 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 65536;
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($dirty2 & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            thumbContent2 = function22;
            $composer2 = $composer3;
            enabled4 = enabled2;
            colors4 = colors2;
            interactionSource4 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                Function2 thumbContent3 = i4 != 0 ? null : function22;
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = SwitchDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i6 != 0) {
                    modifier3 = modifier4;
                    $dirty = $dirty2;
                    interactionSource2 = null;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    thumbContent = thumbContent3;
                } else {
                    modifier3 = modifier4;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    thumbContent = thumbContent3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                enabled3 = enabled2;
                colors3 = colors2;
                interactionSource2 = mutableInteractionSource;
                thumbContent = function22;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, $dirty, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
            }
            $composer3.startReplaceGroup(783532531);
            ComposerKt.sourceInformation($composer3, "101@4666L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 783533182, "CC(remember):Switch.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                interactionSource3 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
            }
            $composer3.endReplaceGroup();
            Modifier.Companion toggleableModifier = function1 != null ? ToggleableKt.m928toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, interactionSource3, null, enabled3, Role.m5895boximpl(Role.INSTANCE.m5907getSwitcho7Vup1c()), function1) : Modifier.INSTANCE;
            int $dirty3 = $dirty;
            $composer2 = $composer3;
            Modifier modifier5 = modifier3;
            SwitchImpl(SizeKt.m711requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(toggleableModifier), Alignment.INSTANCE.getCenter(), false, 2, null), SwitchWidth, SwitchHeight), checked, enabled3, colors3, thumbContent, interactionSource3, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), $composer3, 6), $composer2, (($dirty3 << 3) & 112) | (($dirty3 >> 6) & 896) | (($dirty3 >> 6) & 7168) | (57344 & ($dirty3 << 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled4 = enabled3;
            colors4 = colors3;
            thumbContent2 = thumbContent;
            interactionSource4 = interactionSource2;
            modifier2 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt$Switch$1
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
                    SwitchKt.Switch(checked, function1, modifier2, thumbContent2, enabled4, colors4, interactionSource4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:77:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SwitchImpl(final androidx.compose.ui.Modifier r58, final boolean r59, final boolean r60, final androidx.compose.material3.SwitchColors r61, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r62, final androidx.compose.foundation.interaction.InteractionSource r63, final androidx.compose.ui.graphics.Shape r64, androidx.compose.runtime.Composer r65, final int r66) {
        /*
            Method dump skipped, instructions count: 945
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SwitchKt.SwitchImpl(androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.material3.SwitchColors, kotlin.jvm.functions.Function2, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int):void");
    }

    static {
        float arg0$iv = SwitchHeight;
        float other$iv = ThumbDiameter;
        ThumbPadding = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv - other$iv) / 2);
        SnapSpec = new SnapSpec<>(0, 1, null);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
