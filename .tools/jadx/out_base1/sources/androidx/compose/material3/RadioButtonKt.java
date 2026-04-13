package androidx.compose.material3;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.tokens.RadioButtonTokens;
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
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: RadioButton.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006\u0015"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonPadding = Dp.m6626constructorimpl(2);
    private static final float RadioButtonDotSize = Dp.m6626constructorimpl(12);
    private static final float RadioStrokeWidth = Dp.m6626constructorimpl(2);

    public static final void RadioButton(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, RadioButtonColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        RadioButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier3;
        int $dirty;
        boolean enabled3;
        RadioButtonColors colors3;
        MutableInteractionSource interactionSource2;
        float m6626constructorimpl;
        boolean z;
        Modifier modifier4;
        State dotRadius;
        State radioColor;
        Object obj;
        final boolean enabled4;
        RadioButtonColors colors4;
        int $dirty2;
        Modifier.Companion selectableModifier;
        Object value$iv;
        final MutableInteractionSource interactionSource3;
        final RadioButtonColors colors5;
        final Modifier modifier5;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(408580840);
        ComposerKt.sourceInformation($composer2, "C(RadioButton)P(5,4,3,1)80@3770L8,84@3868L176,88@4073L29,119@5097L415,106@4679L833:RadioButton.kt#uh7d8r");
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
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i2 = 16384;
                    $dirty3 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 8192;
            $dirty3 |= i2;
        } else {
            colors2 = colors;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource = interactionSource;
        } else if ((196608 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer2.changed(mutableInteractionSource) ? 131072 : 65536;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((74899 & $dirty3) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier5 = modifier2;
            colors5 = colors2;
            enabled4 = enabled2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                    colors2 = RadioButtonDefaults.INSTANCE.colors($composer2, 6);
                }
                if (i5 != 0) {
                    $dirty = $dirty3;
                    interactionSource2 = null;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    $dirty = $dirty3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                $dirty = $dirty3;
                modifier3 = modifier2;
                enabled3 = enabled2;
                colors3 = colors2;
                interactionSource2 = mutableInteractionSource;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, $dirty, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
            }
            if (selected) {
                float arg0$iv = RadioButtonDotSize;
                m6626constructorimpl = Dp.m6626constructorimpl(arg0$iv / 2);
            } else {
                m6626constructorimpl = Dp.m6626constructorimpl(0);
            }
            State dotRadius2 = AnimateAsStateKt.m120animateDpAsStateAjpBEmI(m6626constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, $composer2, 48, 12);
            State radioColor2 = colors3.radioColor$material3_release(enabled3, selected, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 896));
            $composer2.startReplaceGroup(1327106656);
            ComposerKt.sourceInformation($composer2, "98@4448L164");
            if (function0 != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                int m5906getRadioButtono7Vup1c = Role.INSTANCE.m5906getRadioButtono7Vup1c();
                float arg0$iv2 = RadioButtonTokens.INSTANCE.m3473getStateLayerSizeD9Ej5fM();
                float arg0$iv3 = Dp.m6626constructorimpl(arg0$iv2 / 2);
                dotRadius = dotRadius2;
                radioColor = radioColor2;
                colors4 = colors3;
                z = false;
                modifier4 = modifier3;
                obj = null;
                enabled4 = enabled3;
                $dirty2 = $dirty;
                selectableModifier = SelectableKt.m923selectableO2vRcR0(companion, selected, interactionSource2, RippleKt.m2411rippleOrFallbackImplementation9IZ8Weo(false, arg0$iv3, 0L, $composer2, 54, 4), enabled4, Role.m5895boximpl(m5906getRadioButtono7Vup1c), function0);
            } else {
                z = false;
                modifier4 = modifier3;
                dotRadius = dotRadius2;
                radioColor = radioColor2;
                obj = null;
                enabled4 = enabled3;
                colors4 = colors3;
                $dirty2 = $dirty;
                selectableModifier = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Modifier m709requiredSize3ABfNKs = SizeKt.m709requiredSize3ABfNKs(PaddingKt.m672padding3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(function0 != null ? InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE) : Modifier.INSTANCE).then(selectableModifier), Alignment.INSTANCE.getCenter(), z, 2, obj), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3472getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer2, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
            final State radioColor3 = radioColor;
            final State dotRadius3 = dotRadius;
            boolean invalid$iv = $composer2.changed(radioColor3) | $composer2.changed(dotRadius3);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                        f = RadioButtonKt.RadioStrokeWidth;
                        float strokeWidth = $this$Canvas.mo364toPx0680j_4(f);
                        long m4173unboximpl = radioColor3.getValue().m4173unboximpl();
                        float arg0$iv4 = RadioButtonTokens.INSTANCE.m3472getIconSizeD9Ej5fM();
                        DrawScope.m4694drawCircleVaOC9Bg$default($this$Canvas, m4173unboximpl, $this$Canvas.mo364toPx0680j_4(Dp.m6626constructorimpl(arg0$iv4 / 2)) - (strokeWidth / 2.0f), 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
                        if (Dp.m6625compareTo0680j_4(dotRadius3.getValue().m6640unboximpl(), Dp.m6626constructorimpl(0)) > 0) {
                            DrawScope.m4694drawCircleVaOC9Bg$default($this$Canvas, radioColor3.getValue().m4173unboximpl(), $this$Canvas.mo364toPx0680j_4(dotRadius3.getValue().m6640unboximpl()) - (strokeWidth / 2.0f), 0L, 0.0f, Fill.INSTANCE, null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m709requiredSize3ABfNKs, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource3 = interactionSource2;
            colors5 = colors4;
            modifier5 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    RadioButtonKt.RadioButton(selected, function0, modifier5, enabled4, colors5, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
