package androidx.compose.material;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: RadioButton.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006\u0018"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioButtonRippleRadius", "RadioButtonSize", "RadioRadius", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/RadioButtonColors;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/RadioButtonColors;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonDotSize;
    private static final float RadioRadius;
    private static final float RadioStrokeWidth;
    private static final float RadioButtonRippleRadius = Dp.m6626constructorimpl(24);
    private static final float RadioButtonPadding = Dp.m6626constructorimpl(2);
    private static final float RadioButtonSize = Dp.m6626constructorimpl(20);

    public static final void RadioButton(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, RadioButtonColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource mutableInteractionSource;
        RadioButtonColors radioButtonColors;
        Modifier modifier3;
        final boolean enabled2;
        MutableInteractionSource interactionSource2;
        int $dirty;
        RadioButtonColors colors2;
        float m6626constructorimpl;
        RadioButtonColors colors3;
        int i2;
        State dotRadius;
        final State radioColor;
        Object obj;
        boolean z2;
        int $dirty2;
        Modifier.Companion selectableModifier;
        Object value$iv;
        final MutableInteractionSource interactionSource3;
        final Modifier modifier4;
        final RadioButtonColors colors4;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(1314435585);
        ComposerKt.sourceInformation($composer2, "C(RadioButton)P(5,4,3,1,2)78@3689L8,80@3722L164,84@3915L29,114@4883L385,101@4476L792:RadioButton.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer2.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty3 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty3 |= 24576;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 24576) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer2.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                radioButtonColors = colors;
                if ($composer2.changed(radioButtonColors)) {
                    i3 = 131072;
                    $dirty3 |= i3;
                }
            } else {
                radioButtonColors = colors;
            }
            i3 = 65536;
            $dirty3 |= i3;
        } else {
            radioButtonColors = colors;
        }
        if ((74899 & $dirty3) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            enabled2 = z;
            interactionSource3 = mutableInteractionSource;
            modifier4 = modifier2;
            colors4 = radioButtonColors;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled3 = i5 != 0 ? true : z;
                MutableInteractionSource interactionSource4 = i6 != 0 ? null : mutableInteractionSource;
                if ((i & 32) != 0) {
                    RadioButtonColors colors5 = RadioButtonDefaults.INSTANCE.m1622colorsRGew2ao(0L, 0L, 0L, $composer2, 3072, 7);
                    $composer2 = $composer2;
                    modifier3 = modifier5;
                    enabled2 = enabled3;
                    interactionSource2 = interactionSource4;
                    $dirty = $dirty3 & (-458753);
                    colors2 = colors5;
                } else {
                    modifier3 = modifier5;
                    enabled2 = enabled3;
                    interactionSource2 = interactionSource4;
                    $dirty = $dirty3;
                    colors2 = colors;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                modifier3 = modifier2;
                enabled2 = z;
                interactionSource2 = mutableInteractionSource;
                $dirty = $dirty3;
                colors2 = radioButtonColors;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1314435585, $dirty, -1, "androidx.compose.material.RadioButton (RadioButton.kt:79)");
            }
            if (selected) {
                float arg0$iv = RadioButtonDotSize;
                m6626constructorimpl = Dp.m6626constructorimpl(arg0$iv / 2);
            } else {
                m6626constructorimpl = Dp.m6626constructorimpl(0);
            }
            State dotRadius2 = AnimateAsStateKt.m120animateDpAsStateAjpBEmI(m6626constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, $composer2, 48, 12);
            State radioColor2 = colors2.radioColor(enabled2, selected, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896));
            if (function0 != null) {
                $composer2.startReplaceGroup(1892955885);
                ComposerKt.sourceInformation($composer2, "93@4270L139");
                dotRadius = dotRadius2;
                radioColor = radioColor2;
                colors3 = colors2;
                i2 = 2;
                obj = null;
                z2 = false;
                $dirty2 = $dirty;
                selectableModifier = SelectableKt.m923selectableO2vRcR0(Modifier.INSTANCE, selected, interactionSource2, RippleKt.m1630rippleOrFallbackImplementation9IZ8Weo(false, RadioButtonRippleRadius, 0L, $composer2, 54, 4), enabled2, Role.m5895boximpl(Role.INSTANCE.m5906getRadioButtono7Vup1c()), function0);
                $composer2.endReplaceGroup();
            } else {
                colors3 = colors2;
                i2 = 2;
                dotRadius = dotRadius2;
                radioColor = radioColor2;
                obj = null;
                z2 = false;
                $dirty2 = $dirty;
                $composer2.startReplaceGroup(1893376059);
                $composer2.endReplaceGroup();
                selectableModifier = Modifier.INSTANCE;
            }
            Modifier m709requiredSize3ABfNKs = SizeKt.m709requiredSize3ABfNKs(PaddingKt.m672padding3ABfNKs(SizeKt.wrapContentSize$default(modifier3.then(function0 != null ? InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE) : Modifier.INSTANCE).then(selectableModifier), Alignment.INSTANCE.getCenter(), z2, i2, obj), RadioButtonPadding), RadioButtonSize);
            ComposerKt.sourceInformationMarkerStart($composer2, 61091208, "CC(remember):RadioButton.kt#9igjgp");
            final State dotRadius3 = dotRadius;
            boolean invalid$iv = $composer2.changed(radioColor) | $composer2.changed(dotRadius3);
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.RadioButtonKt$RadioButton$1$1
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
                        float f;
                        float f2;
                        f = RadioButtonKt.RadioStrokeWidth;
                        float strokeWidth = $this$Canvas.mo364toPx0680j_4(f);
                        long m4173unboximpl = radioColor.getValue().m4173unboximpl();
                        f2 = RadioButtonKt.RadioRadius;
                        DrawScope.m4694drawCircleVaOC9Bg$default($this$Canvas, m4173unboximpl, $this$Canvas.mo364toPx0680j_4(f2) - (strokeWidth / 2.0f), 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
                        if (Dp.m6625compareTo0680j_4(dotRadius3.getValue().m6640unboximpl(), Dp.m6626constructorimpl(0)) > 0) {
                            DrawScope.m4694drawCircleVaOC9Bg$default($this$Canvas, radioColor.getValue().m4173unboximpl(), $this$Canvas.mo364toPx0680j_4(dotRadius3.getValue().m6640unboximpl()) - (strokeWidth / 2.0f), 0L, 0.0f, Fill.INSTANCE, null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
                        }
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m709requiredSize3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource3 = interactionSource2;
            modifier4 = modifier3;
            colors4 = colors3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.RadioButtonKt$RadioButton$2
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
                    RadioButtonKt.RadioButton(selected, function0, modifier4, enabled2, interactionSource3, colors4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    static {
        float arg0$iv = RadioButtonSize;
        RadioRadius = Dp.m6626constructorimpl(arg0$iv / 2);
        RadioButtonDotSize = Dp.m6626constructorimpl(12);
        RadioStrokeWidth = Dp.m6626constructorimpl(2);
    }
}
