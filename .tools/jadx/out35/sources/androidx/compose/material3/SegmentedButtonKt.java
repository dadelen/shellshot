package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SegmentedButton.kt */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aD\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a3\u0010\u0011\u001a\u00020\u00062\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0003¢\u0006\u0002\u0010\u0014\u001aD\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0010\u001a\u0091\u0001\u0010\u0018\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u001a2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0013\b\u0002\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010&\u001a\u008b\u0001\u0010\u0018\u001a\u00020\u0006*\u00020\u00162\u0006\u0010'\u001a\u00020\u001a2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u001a2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0013\b\u0002\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010)\u001a\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+*\u00020-H\u0003¢\u0006\u0002\u0010.\u001a\"\u0010/\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"CheckedZIndexFactor", "", "IconSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "MultiChoiceSegmentedButtonRow", "", "modifier", "Landroidx/compose/ui/Modifier;", "space", "content", "Lkotlin/Function1;", "Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "MultiChoiceSegmentedButtonRow-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SegmentedButtonContent", "icon", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SingleChoiceSegmentedButtonRow", "Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;", "SingleChoiceSegmentedButtonRow-uFdPcIQ", "SegmentedButton", "checked", "", "onCheckedChange", "shape", "Landroidx/compose/ui/graphics/Shape;", "enabled", "colors", "Landroidx/compose/material3/SegmentedButtonColors;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "label", "(Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "selected", "onClick", "(Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "interactionCountAsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "interactionZIndex", "interactionCount", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SegmentedButtonKt {
    private static final float CheckedZIndexFactor = 5.0f;
    private static final float IconSpacing = Dp.m6626constructorimpl(8);

    public static final void SegmentedButton(final MultiChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean checked, final Function1<? super Boolean, Unit> function1, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        SegmentedButtonColors colors2;
        BorderStroke border2;
        int i2;
        int i3;
        Modifier modifier3;
        SegmentedButtonColors colors3;
        boolean enabled3;
        MutableInteractionSource interactionSource2;
        int $dirty;
        BorderStroke border3;
        final Function2 icon;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        int $dirty2;
        final boolean enabled4;
        final BorderStroke border4;
        final Modifier modifier4;
        final SegmentedButtonColors colors4;
        final Function2 icon2;
        final MutableInteractionSource interactionSource4;
        Object value$iv;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-1596038053);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)P(1,8,9,7,3,2!1,5)133@6692L8,137@6905L41,144@7279L25,163@7880L51,146@7310L621:SegmentedButton.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(checked) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(shape) ? 2048 : 1024;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 1048576;
                    $dirty3 |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 524288;
            $dirty3 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                if ($composer3.changed(border2)) {
                    i4 = 8388608;
                    $dirty3 |= i4;
                }
            } else {
                border2 = border;
            }
            i4 = 4194304;
            $dirty3 |= i4;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 100663296;
            i2 = i8;
        } else if (($changed & 100663296) == 0) {
            i2 = i8;
            $dirty3 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i8;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 805306368;
            i3 = i9;
        } else if (($changed & 805306368) == 0) {
            i3 = i9;
            $dirty3 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        } else {
            i3 = i9;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function22) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource4 = interactionSource;
            $composer2 = $composer3;
            $dirty2 = $dirty3;
            enabled4 = enabled2;
            border4 = border2;
            icon2 = function2;
            colors4 = colors2;
            modifier4 = modifier2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if (i7 != 0) {
                    enabled2 = true;
                }
                if ((i & 32) != 0) {
                    colors2 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                    $dirty3 &= -3670017;
                }
                if ((i & 64) != 0) {
                    border2 = SegmentedButtonDefaults.m2454borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors2.m2438borderColorWaAFU9c$material3_release(enabled2, checked), 0.0f, 2, null);
                    $dirty3 &= -29360129;
                }
                MutableInteractionSource interactionSource5 = i2 != 0 ? null : interactionSource;
                if (i3 != 0) {
                    Modifier modifier6 = modifier5;
                    int i10 = $dirty3;
                    icon = ComposableLambdaKt.rememberComposableLambda(970447394, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$1
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
                            ComposerKt.sourceInformation($composer4, "C137@6931L13:SegmentedButton.kt#uh7d8r");
                            if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(970447394, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:137)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(checked, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors3 = colors2;
                    enabled3 = enabled2;
                    $dirty = i10;
                    modifier3 = modifier6;
                    interactionSource2 = interactionSource5;
                    border3 = border2;
                } else {
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource5;
                    $dirty = $dirty3;
                    border3 = border2;
                    icon = function2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 64) != 0) {
                    int i11 = $dirty3 & (-29360129);
                    boolean z = enabled2;
                    $dirty = i11;
                    colors3 = colors2;
                    enabled3 = z;
                    interactionSource2 = interactionSource;
                    icon = function2;
                    border3 = border2;
                    modifier3 = modifier2;
                } else {
                    interactionSource2 = interactionSource;
                    colors3 = colors2;
                    enabled3 = enabled2;
                    border3 = border2;
                    modifier3 = modifier2;
                    $dirty = $dirty3;
                    icon = function2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1596038053, $dirty, $dirty12, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:139)");
            }
            $composer3.startReplaceGroup(1788099965);
            ComposerKt.sourceInformation($composer3, "141@7068L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 1788100616, "CC(remember):SegmentedButton.kt#9igjgp");
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
            long containerColor = colors3.m2439containerColorWaAFU9c$material3_release(enabled3, checked);
            int $dirty4 = $dirty;
            long contentColor = colors3.m2440contentColorWaAFU9c$material3_release(enabled3, checked);
            State interactionCount = interactionCountAsState(interactionSource3, $composer3, 0);
            Modifier modifier7 = modifier3;
            SegmentedButtonColors colors5 = colors3;
            $composer2 = $composer3;
            Function2 icon3 = icon;
            SurfaceKt.m2549Surfaced85dljk(checked, function1, SizeKt.m701defaultMinSizeVpY3zN4(interactionZIndex(RowScope.weight$default($this$SegmentedButton, modifier3, 1.0f, false, 2, null), checked, interactionCount), ButtonDefaults.INSTANCE.m1822getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1821getMinHeightD9Ej5fM()), enabled3, shape, containerColor, contentColor, 0.0f, 0.0f, border3, interactionSource3, ComposableLambdaKt.rememberComposableLambda(1635710341, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$2
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
                    ComposerKt.sourceInformation($composer4, "C164@7890L35:SegmentedButton.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1635710341, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:164)");
                        }
                        SegmentedButtonKt.SegmentedButtonContent(icon, function22, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty4 >> 3) & 14) | (($dirty4 >> 3) & 112) | (($dirty4 >> 6) & 7168) | (57344 & ($dirty4 << 3)) | (($dirty4 << 6) & 1879048192), 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty2 = $dirty4;
            enabled4 = enabled3;
            border4 = border3;
            modifier4 = modifier7;
            colors4 = colors5;
            icon2 = icon3;
            interactionSource4 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$3
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
                    SegmentedButtonKt.SegmentedButton(MultiChoiceSegmentedButtonRowScope.this, checked, function1, shape, modifier4, enabled4, colors4, border4, interactionSource4, icon2, function22, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void SegmentedButton(final SingleChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean selected, final Function0<Unit> function0, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        SegmentedButtonColors colors2;
        BorderStroke border2;
        int i2;
        int i3;
        Modifier modifier3;
        SegmentedButtonColors colors3;
        boolean enabled3;
        MutableInteractionSource interactionSource2;
        int $dirty;
        BorderStroke border3;
        final Function2 icon;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        int $dirty2;
        final boolean enabled4;
        final BorderStroke border4;
        final Modifier modifier4;
        final SegmentedButtonColors colors4;
        final MutableInteractionSource interactionSource4;
        final Function2 icon2;
        Object value$iv;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-1016574361);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)P(8,7,9,6,2,1!1,4)211@10255L8,215@10469L42,222@10846L25,242@11489L51,224@10877L663:SegmentedButton.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(shape) ? 2048 : 1024;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 1048576;
                    $dirty3 |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 524288;
            $dirty3 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                if ($composer3.changed(border2)) {
                    i4 = 8388608;
                    $dirty3 |= i4;
                }
            } else {
                border2 = border;
            }
            i4 = 4194304;
            $dirty3 |= i4;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 100663296;
            i2 = i8;
        } else if (($changed & 100663296) == 0) {
            i2 = i8;
            $dirty3 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i8;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 805306368;
            i3 = i9;
        } else if (($changed & 805306368) == 0) {
            i3 = i9;
            $dirty3 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        } else {
            i3 = i9;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function22) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource4 = interactionSource;
            $composer2 = $composer3;
            $dirty2 = $dirty3;
            enabled4 = enabled2;
            border4 = border2;
            icon2 = function2;
            colors4 = colors2;
            modifier4 = modifier2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if (i7 != 0) {
                    enabled2 = true;
                }
                if ((i & 32) != 0) {
                    colors2 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                    $dirty3 &= -3670017;
                }
                if ((i & 64) != 0) {
                    border2 = SegmentedButtonDefaults.m2454borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors2.m2438borderColorWaAFU9c$material3_release(enabled2, selected), 0.0f, 2, null);
                    $dirty3 &= -29360129;
                }
                MutableInteractionSource interactionSource5 = i2 != 0 ? null : interactionSource;
                if (i3 != 0) {
                    Modifier modifier6 = modifier5;
                    int i10 = $dirty3;
                    icon = ComposableLambdaKt.rememberComposableLambda(1235063168, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$4
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
                            ComposerKt.sourceInformation($composer4, "C215@10495L14:SegmentedButton.kt#uh7d8r");
                            if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1235063168, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:215)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(selected, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors3 = colors2;
                    enabled3 = enabled2;
                    $dirty = i10;
                    modifier3 = modifier6;
                    interactionSource2 = interactionSource5;
                    border3 = border2;
                } else {
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource5;
                    $dirty = $dirty3;
                    border3 = border2;
                    icon = function2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 64) != 0) {
                    int i11 = $dirty3 & (-29360129);
                    boolean z = enabled2;
                    $dirty = i11;
                    colors3 = colors2;
                    enabled3 = z;
                    interactionSource2 = interactionSource;
                    icon = function2;
                    border3 = border2;
                    modifier3 = modifier2;
                } else {
                    interactionSource2 = interactionSource;
                    colors3 = colors2;
                    enabled3 = enabled2;
                    border3 = border2;
                    modifier3 = modifier2;
                    $dirty = $dirty3;
                    icon = function2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1016574361, $dirty, $dirty12, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:217)");
            }
            $composer3.startReplaceGroup(1788214045);
            ComposerKt.sourceInformation($composer3, "219@10633L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 1788214696, "CC(remember):SegmentedButton.kt#9igjgp");
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
            long containerColor = colors3.m2439containerColorWaAFU9c$material3_release(enabled3, selected);
            int $dirty4 = $dirty;
            long contentColor = colors3.m2440contentColorWaAFU9c$material3_release(enabled3, selected);
            State interactionCount = interactionCountAsState(interactionSource3, $composer3, 0);
            Modifier modifier7 = modifier3;
            SegmentedButtonColors colors5 = colors3;
            $composer2 = $composer3;
            Function2 icon3 = icon;
            SurfaceKt.m2548Surfaced85dljk(selected, function0, SemanticsModifierKt.semantics$default(SizeKt.m701defaultMinSizeVpY3zN4(interactionZIndex(RowScope.weight$default($this$SegmentedButton, modifier3, 1.0f, false, 2, null), selected, interactionCount), ButtonDefaults.INSTANCE.m1822getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1821getMinHeightD9Ej5fM()), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5906getRadioButtono7Vup1c());
                }
            }, 1, null), enabled3, shape, containerColor, contentColor, 0.0f, 0.0f, border3, interactionSource3, ComposableLambdaKt.rememberComposableLambda(383378045, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$6
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
                    ComposerKt.sourceInformation($composer4, "C243@11499L35:SegmentedButton.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(383378045, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:243)");
                        }
                        SegmentedButtonKt.SegmentedButtonContent(icon, function22, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty4 >> 3) & 14) | (($dirty4 >> 3) & 112) | (($dirty4 >> 6) & 7168) | (57344 & ($dirty4 << 3)) | (($dirty4 << 6) & 1879048192), 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty2 = $dirty4;
            enabled4 = enabled3;
            border4 = border3;
            modifier4 = modifier7;
            colors4 = colors5;
            interactionSource4 = interactionSource2;
            icon2 = icon3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$7
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
                    SegmentedButtonKt.SegmentedButton(SingleChoiceSegmentedButtonRowScope.this, selected, function0, shape, modifier4, enabled4, colors4, border4, interactionSource4, icon2, function22, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01f2  */
    /* renamed from: SingleChoiceSegmentedButtonRow-uFdPcIQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2460SingleChoiceSegmentedButtonRowuFdPcIQ(androidx.compose.ui.Modifier r30, float r31, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.SingleChoiceSegmentedButtonRowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 577
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonKt.m2460SingleChoiceSegmentedButtonRowuFdPcIQ(androidx.compose.ui.Modifier, float, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01ee  */
    /* renamed from: MultiChoiceSegmentedButtonRow-uFdPcIQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2459MultiChoiceSegmentedButtonRowuFdPcIQ(androidx.compose.ui.Modifier r30, float r31, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.MultiChoiceSegmentedButtonRowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonKt.m2459MultiChoiceSegmentedButtonRowuFdPcIQ(androidx.compose.ui.Modifier, float, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SegmentedButtonContent(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32) {
        /*
            Method dump skipped, instructions count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonKt.SegmentedButtonContent(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    private static final State<Integer> interactionCountAsState(InteractionSource $this$interactionCountAsState, Composer $composer, int $changed) {
        Object value$iv;
        SegmentedButtonKt$interactionCountAsState$1$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 281890131, "C(interactionCountAsState)397@17381L33,398@17440L499,398@17419L520:SegmentedButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(281890131, $changed, -1, "androidx.compose.material3.interactionCountAsState (SegmentedButton.kt:396)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 408875648, "CC(remember):SegmentedButton.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotIntStateKt.mutableIntStateOf(0);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableIntState interactionCount = (MutableIntState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 408878002, "CC(remember):SegmentedButton.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed($this$interactionCountAsState)) || ($changed & 6) == 4;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SegmentedButtonKt$interactionCountAsState$1$1($this$interactionCountAsState, interactionCount, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect($this$interactionCountAsState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return interactionCount;
    }

    private static final Modifier interactionZIndex(Modifier $this$interactionZIndex, final boolean checked, final State<Integer> state) {
        return LayoutModifierKt.layout($this$interactionZIndex, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.SegmentedButtonKt$interactionZIndex$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m2461invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m2461invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                final Placeable placeable = measurable.mo5491measureBRTryo0(constraints);
                int width = placeable.getWidth();
                int height = placeable.getHeight();
                final State<Integer> state2 = state;
                final boolean z = checked;
                return MeasureScope.layout$default($this$layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$interactionZIndex$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        float zIndex = state2.getValue().floatValue() + (z ? 5.0f : 0.0f);
                        $this$layout2.place(placeable, 0, 0, zIndex);
                    }
                }, 4, null);
            }
        });
    }
}
