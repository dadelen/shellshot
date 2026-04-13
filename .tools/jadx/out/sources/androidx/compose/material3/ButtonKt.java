package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001e"}, d2 = {"Button", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/ButtonColors;", "elevation", "Landroidx/compose/material3/ButtonElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ButtonColors;Landroidx/compose/material3/ButtonElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedButton", "FilledTonalButton", "OutlinedButton", "TextButton", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ButtonKt {
    public static final void Button(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z;
        Shape shape2;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke borderStroke;
        int i2;
        int i3;
        Shape shape3;
        ButtonColors colors3;
        int $dirty;
        final PaddingValues contentPadding2;
        ButtonElevation elevation3;
        boolean enabled2;
        Shape shape4;
        int $dirty2;
        int i4;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        Composer $composer2;
        final MutableInteractionSource interactionSource5;
        final ButtonElevation elevation4;
        final ButtonColors colors4;
        final Shape shape5;
        final BorderStroke border3;
        final PaddingValues contentPadding3;
        final Modifier modifier3;
        final boolean enabled3;
        Object value$iv;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(650121315);
        ComposerKt.sourceInformation($composer3, "C(Button)P(8,7,5,9,1,4!1,3,6)111@5461L5,112@5510L14,113@5575L17,134@6467L587,124@6130L924:Button.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty3 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty3 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 2048;
                    $dirty3 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 1024;
            $dirty3 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i6 = 16384;
                    $dirty3 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 8192;
            $dirty3 |= i6;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i5 = 131072;
                    $dirty3 |= i5;
                }
            } else {
                elevation2 = elevation;
            }
            i5 = 65536;
            $dirty3 |= i5;
        } else {
            elevation2 = elevation;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty3 |= 1572864;
            borderStroke = border;
        } else if ((1572864 & $changed) == 0) {
            borderStroke = border;
            $dirty3 |= $composer3.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty3 |= 12582912;
            i2 = i8;
        } else if (($changed & 12582912) == 0) {
            i2 = i8;
            $dirty3 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        } else {
            i2 = i8;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty3 |= 100663296;
            i3 = i12;
        } else if (($changed & 100663296) == 0) {
            i3 = i12;
            $dirty3 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i12;
        }
        if ((i & 512) != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if ((306783379 & $dirty3) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = z;
            shape5 = shape2;
            $composer2 = $composer3;
            colors4 = colors2;
            border3 = borderStroke;
            elevation4 = elevation2;
            contentPadding3 = contentPadding;
            interactionSource5 = interactionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z;
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors3 = ButtonDefaults.INSTANCE.buttonColors($composer3, 6);
                    $dirty = $dirty3 & (-57345);
                } else {
                    colors3 = colors2;
                    $dirty = $dirty3;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = ButtonDefaults.INSTANCE.m1814buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                }
                BorderStroke border4 = i10 != 0 ? null : border;
                contentPadding2 = i11 != 0 ? ButtonDefaults.INSTANCE.getContentPadding() : contentPadding;
                if (i3 != 0) {
                    enabled2 = enabled4;
                    shape4 = shape3;
                    colors2 = colors3;
                    $dirty2 = $dirty;
                    i4 = 650121315;
                    border2 = border4;
                    interactionSource2 = null;
                    modifier2 = modifier4;
                    elevation3 = elevation2;
                } else {
                    modifier2 = modifier4;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                    colors2 = colors3;
                    $dirty2 = $dirty;
                    i4 = 650121315;
                    border2 = border4;
                    interactionSource2 = interactionSource;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    int i13 = $dirty3 & (-458753);
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty2 = i13;
                    enabled2 = z;
                    shape4 = shape2;
                    border2 = borderStroke;
                    elevation3 = elevation2;
                    i4 = 650121315;
                } else {
                    contentPadding2 = contentPadding;
                    $dirty2 = $dirty3;
                    enabled2 = z;
                    shape4 = shape2;
                    border2 = borderStroke;
                    elevation3 = elevation2;
                    i4 = 650121315;
                    interactionSource2 = interactionSource;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty2, -1, "androidx.compose.material3.Button (Button.kt:118)");
            }
            $composer3.startReplaceGroup(-239156623);
            ComposerKt.sourceInformation($composer3, "120@5882L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, -239155972, "CC(remember):Button.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                interactionSource3 = interactionSource2;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                interactionSource4 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
                interactionSource4 = interactionSource3;
            }
            $composer3.endReplaceGroup();
            long containerColor = colors2.m1806containerColorvNxB06k$material3_release(enabled2);
            final long contentColor = colors2.m1807contentColorvNxB06k$material3_release(enabled2);
            $composer3.startReplaceGroup(-239150048);
            ComposerKt.sourceInformation($composer3, "123@6067L43");
            State<Dp> shadowElevation$material3_release = elevation3 == null ? null : elevation3.shadowElevation$material3_release(enabled2, interactionSource4, $composer3, (($dirty2 >> 9) & 896) | (($dirty2 >> 6) & 14));
            $composer3.endReplaceGroup();
            float shadowElevation = shadowElevation$material3_release != null ? shadowElevation$material3_release.getValue().m6640unboximpl() : Dp.m6626constructorimpl(0);
            ButtonElevation elevation5 = elevation3;
            $composer2 = $composer3;
            SurfaceKt.m2550Surfaceo_FOJdg(function02, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ButtonKt$Button$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null), enabled2, shape4, containerColor, contentColor, 0.0f, shadowElevation, border2, interactionSource4, ComposableLambdaKt.rememberComposableLambda(956488494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$Button$2
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
                    ComposerKt.sourceInformation($composer4, "C137@6586L10,138@6618L430,135@6477L571:Button.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(956488494, $changed2, -1, "androidx.compose.material3.Button.<anonymous> (Button.kt:135)");
                        }
                        long j = contentColor;
                        TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer4, 6).getLabelLarge();
                        final PaddingValues paddingValues = contentPadding2;
                        final Function3<RowScope, Composer, Integer, Unit> function32 = function3;
                        ProvideContentColorTextStyleKt.m2906ProvideContentColorTextStyle3JVO9M(j, labelLarge, ComposableLambdaKt.rememberComposableLambda(1327513942, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$Button$2.1
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

                            /* JADX WARN: Removed duplicated region for block: B:24:0x0157  */
                            /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                                /*
                                    Method dump skipped, instructions count: 347
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ButtonKt$Button$2.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                            }
                        }, $composer4, 54), $composer4, 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 << 6) & 234881024), 6, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource5 = interactionSource3;
            elevation4 = elevation5;
            colors4 = colors2;
            shape5 = shape4;
            border3 = border2;
            contentPadding3 = contentPadding2;
            modifier3 = modifier2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$Button$3
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

                public final void invoke(Composer composer, int i14) {
                    ButtonKt.Button(function0, modifier3, enabled3, shape5, colors4, elevation4, border3, contentPadding3, interactionSource5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ElevatedButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z;
        Shape shape2;
        ButtonColors buttonColors;
        ButtonElevation elevation2;
        BorderStroke border2;
        int i2;
        Shape shape3;
        ButtonColors colors2;
        int $dirty;
        int i3;
        BorderStroke border3;
        boolean enabled2;
        Modifier modifier3;
        ButtonElevation elevation3;
        Shape shape4;
        ButtonColors colors3;
        int i4;
        MutableInteractionSource interactionSource2;
        PaddingValues contentPadding2;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled3;
        final Shape shape5;
        final ButtonColors colors4;
        final ButtonElevation elevation4;
        final BorderStroke border4;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1466887385);
        ComposerKt.sourceInformation($composer3, "C(ElevatedButton)P(8,7,5,9,1,4!1,3,6)204@9975L13,205@10032L22,206@10105L25,212@10343L314:Button.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 2048;
                    $dirty2 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 1024;
            $dirty2 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                if ($composer3.changed(buttonColors)) {
                    i6 = 16384;
                    $dirty2 |= i6;
                }
            } else {
                buttonColors = colors;
            }
            i6 = 8192;
            $dirty2 |= i6;
        } else {
            buttonColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i5 = 131072;
                    $dirty2 |= i5;
                }
            } else {
                elevation2 = elevation;
            }
            i5 = 65536;
            $dirty2 |= i5;
        } else {
            elevation2 = elevation;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty2 |= 1572864;
            border2 = border;
        } else if ((1572864 & $changed) == 0) {
            border2 = border;
            $dirty2 |= $composer3.changed(border2) ? 1048576 : 524288;
        } else {
            border2 = border;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty2 |= 100663296;
            i2 = i12;
        } else if (($changed & 100663296) == 0) {
            i2 = i12;
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i12;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if (($dirty2 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            modifier4 = modifier2;
            enabled3 = z;
            shape5 = shape2;
            $composer2 = $composer3;
            colors4 = buttonColors;
            elevation4 = elevation2;
            border4 = border2;
            contentPadding3 = contentPadding;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z;
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.elevatedButtonColors($composer3, 6);
                    $dirty = $dirty2 & (-57345);
                } else {
                    colors2 = buttonColors;
                    $dirty = $dirty2;
                }
                if ((i & 32) != 0) {
                    i3 = i11;
                    $dirty &= -458753;
                    elevation2 = ButtonDefaults.INSTANCE.m1816elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                } else {
                    i3 = i11;
                }
                if (i10 != 0) {
                    border2 = null;
                }
                PaddingValues contentPadding4 = i3 != 0 ? ButtonDefaults.INSTANCE.getContentPadding() : contentPadding;
                if (i2 != 0) {
                    boolean z2 = enabled4;
                    border3 = border2;
                    enabled2 = z2;
                    modifier3 = modifier5;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    colors3 = colors2;
                    i4 = 1466887385;
                    contentPadding2 = contentPadding4;
                    interactionSource2 = null;
                    $dirty2 = $dirty;
                } else {
                    boolean z3 = enabled4;
                    border3 = border2;
                    enabled2 = z3;
                    modifier3 = modifier5;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    colors3 = colors2;
                    i4 = 1466887385;
                    interactionSource2 = interactionSource;
                    contentPadding2 = contentPadding4;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty2 &= -458753;
                    modifier3 = modifier2;
                    shape4 = shape2;
                    colors3 = buttonColors;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = 1466887385;
                    enabled2 = z;
                } else {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    shape4 = shape2;
                    colors3 = buttonColors;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = 1466887385;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty2, -1, "androidx.compose.material3.ElevatedButton (Button.kt:212)");
            }
            $composer2 = $composer3;
            Button(function02, modifier3, enabled2, shape4, colors3, elevation3, border3, contentPadding2, interactionSource2, function3, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled3 = enabled2;
            shape5 = shape4;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            contentPadding3 = contentPadding2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$ElevatedButton$1
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

                public final void invoke(Composer composer, int i13) {
                    ButtonKt.ElevatedButton(function0, modifier4, enabled3, shape5, colors4, elevation4, border4, contentPadding3, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void FilledTonalButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z;
        Shape shape2;
        ButtonColors buttonColors;
        ButtonElevation elevation2;
        BorderStroke border2;
        int i2;
        Shape shape3;
        ButtonColors colors2;
        int $dirty;
        int i3;
        BorderStroke border3;
        boolean enabled2;
        Modifier modifier3;
        ButtonElevation elevation3;
        Shape shape4;
        ButtonColors colors3;
        int i4;
        MutableInteractionSource interactionSource2;
        PaddingValues contentPadding2;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled3;
        final Shape shape5;
        final ButtonColors colors4;
        final ButtonElevation elevation4;
        final BorderStroke border4;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-1717924381);
        ComposerKt.sourceInformation($composer3, "C(FilledTonalButton)P(8,7,5,9,1,4!1,3,6)277@13636L16,278@13696L25,279@13772L28,285@14013L314:Button.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 2048;
                    $dirty2 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 1024;
            $dirty2 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                if ($composer3.changed(buttonColors)) {
                    i6 = 16384;
                    $dirty2 |= i6;
                }
            } else {
                buttonColors = colors;
            }
            i6 = 8192;
            $dirty2 |= i6;
        } else {
            buttonColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i5 = 131072;
                    $dirty2 |= i5;
                }
            } else {
                elevation2 = elevation;
            }
            i5 = 65536;
            $dirty2 |= i5;
        } else {
            elevation2 = elevation;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty2 |= 1572864;
            border2 = border;
        } else if ((1572864 & $changed) == 0) {
            border2 = border;
            $dirty2 |= $composer3.changed(border2) ? 1048576 : 524288;
        } else {
            border2 = border;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty2 |= 100663296;
            i2 = i12;
        } else if (($changed & 100663296) == 0) {
            i2 = i12;
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i12;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if (($dirty2 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            modifier4 = modifier2;
            enabled3 = z;
            shape5 = shape2;
            $composer2 = $composer3;
            colors4 = buttonColors;
            elevation4 = elevation2;
            border4 = border2;
            contentPadding3 = contentPadding;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z;
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.filledTonalButtonColors($composer3, 6);
                    $dirty = $dirty2 & (-57345);
                } else {
                    colors2 = buttonColors;
                    $dirty = $dirty2;
                }
                if ((i & 32) != 0) {
                    i3 = i11;
                    $dirty &= -458753;
                    elevation2 = ButtonDefaults.INSTANCE.m1818filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                } else {
                    i3 = i11;
                }
                if (i10 != 0) {
                    border2 = null;
                }
                PaddingValues contentPadding4 = i3 != 0 ? ButtonDefaults.INSTANCE.getContentPadding() : contentPadding;
                if (i2 != 0) {
                    boolean z2 = enabled4;
                    border3 = border2;
                    enabled2 = z2;
                    modifier3 = modifier5;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    colors3 = colors2;
                    i4 = -1717924381;
                    contentPadding2 = contentPadding4;
                    interactionSource2 = null;
                    $dirty2 = $dirty;
                } else {
                    boolean z3 = enabled4;
                    border3 = border2;
                    enabled2 = z3;
                    modifier3 = modifier5;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    colors3 = colors2;
                    i4 = -1717924381;
                    interactionSource2 = interactionSource;
                    contentPadding2 = contentPadding4;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty2 &= -458753;
                    modifier3 = modifier2;
                    shape4 = shape2;
                    colors3 = buttonColors;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -1717924381;
                    enabled2 = z;
                } else {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    shape4 = shape2;
                    colors3 = buttonColors;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -1717924381;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty2, -1, "androidx.compose.material3.FilledTonalButton (Button.kt:285)");
            }
            $composer2 = $composer3;
            Button(function02, modifier3, enabled2, shape4, colors3, elevation3, border3, contentPadding2, interactionSource2, function3, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled3 = enabled2;
            shape5 = shape4;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            contentPadding3 = contentPadding2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$FilledTonalButton$1
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

                public final void invoke(Composer composer, int i13) {
                    ButtonKt.FilledTonalButton(function0, modifier4, enabled3, shape5, colors4, elevation4, border4, contentPadding3, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void OutlinedButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke border2;
        int i2;
        MutableInteractionSource interactionSource2;
        PaddingValues contentPadding2;
        boolean enabled3;
        Shape shape3;
        ButtonColors colors3;
        ButtonElevation elevation3;
        BorderStroke border3;
        int i3;
        Modifier modifier3;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final ButtonColors colors4;
        final ButtonElevation elevation4;
        final BorderStroke border4;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-1694808287);
        ComposerKt.sourceInformation($composer3, "C(OutlinedButton)P(8,7,5,9,1,4!1,3,6)349@17203L13,350@17260L22,352@17367L29,357@17575L314:Button.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 2048;
                    $dirty |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 16384;
                    $dirty |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 8192;
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            elevation2 = elevation;
        } else if ((196608 & $changed) == 0) {
            elevation2 = elevation;
            $dirty |= $composer3.changed(elevation2) ? 131072 : 65536;
        } else {
            elevation2 = elevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                if ($composer3.changed(border2)) {
                    i4 = 1048576;
                    $dirty |= i4;
                }
            } else {
                border2 = border;
            }
            i4 = 524288;
            $dirty |= i4;
        } else {
            border2 = border;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i11;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            shape4 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border4 = border2;
            contentPadding3 = contentPadding;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    colors2 = ButtonDefaults.INSTANCE.outlinedButtonColors($composer3, 6);
                }
                if (i9 != 0) {
                    elevation2 = null;
                }
                if ((i & 64) != 0) {
                    BorderStroke border5 = ButtonDefaults.INSTANCE.outlinedButtonBorder(enabled2, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                    border2 = border5;
                }
                PaddingValues contentPadding4 = i10 != 0 ? ButtonDefaults.INSTANCE.getContentPadding() : contentPadding;
                if (i2 != 0) {
                    contentPadding2 = contentPadding4;
                    interactionSource2 = null;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i3 = -1694808287;
                    modifier3 = modifier2;
                } else {
                    interactionSource2 = interactionSource;
                    contentPadding2 = contentPadding4;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i3 = -1694808287;
                    modifier3 = modifier2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty &= -3670017;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i3 = -1694808287;
                    modifier3 = modifier2;
                } else {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i3 = -1694808287;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.OutlinedButton (Button.kt:357)");
            }
            $composer2 = $composer3;
            Button(function02, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding2, interactionSource2, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            contentPadding3 = contentPadding2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$OutlinedButton$1
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
                    ButtonKt.OutlinedButton(function0, modifier4, enabled4, shape4, colors4, elevation4, border4, contentPadding3, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void TextButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke border2;
        int i2;
        int i3;
        MutableInteractionSource interactionSource2;
        PaddingValues contentPadding2;
        Modifier modifier3;
        Shape shape3;
        ButtonColors colors3;
        ButtonElevation elevation3;
        BorderStroke border3;
        int i4;
        boolean enabled3;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final ButtonColors colors4;
        final ButtonElevation elevation4;
        final BorderStroke border4;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource3;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-2106428362);
        ComposerKt.sourceInformation($composer3, "C(TextButton)P(8,7,5,9,1,4!1,3,6)422@20900L9,423@20953L18,430@21234L314:Button.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 2048;
                    $dirty |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 16384;
                    $dirty |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 8192;
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            elevation2 = elevation;
        } else if ((196608 & $changed) == 0) {
            elevation2 = elevation;
            $dirty |= $composer3.changed(elevation2) ? 131072 : 65536;
        } else {
            elevation2 = elevation;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
            border2 = border;
        } else if ((1572864 & $changed) == 0) {
            border2 = border;
            $dirty |= $composer3.changed(border2) ? 1048576 : 524288;
        } else {
            border2 = border;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
            i3 = i12;
        } else if (($changed & 100663296) == 0) {
            i3 = i12;
            $dirty |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i12;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            shape4 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border4 = border2;
            contentPadding3 = contentPadding;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    colors2 = ButtonDefaults.INSTANCE.textButtonColors($composer3, 6);
                }
                if (i9 != 0) {
                    elevation2 = null;
                }
                if (i10 != 0) {
                    border2 = null;
                }
                PaddingValues contentPadding4 = i2 != 0 ? ButtonDefaults.INSTANCE.getTextButtonContentPadding() : contentPadding;
                if (i3 != 0) {
                    contentPadding2 = contentPadding4;
                    interactionSource2 = null;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -2106428362;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource;
                    contentPadding2 = contentPadding4;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -2106428362;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty &= -57345;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -2106428362;
                    enabled3 = enabled2;
                } else {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border3 = border2;
                    i4 = -2106428362;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty, -1, "androidx.compose.material3.TextButton (Button.kt:430)");
            }
            $composer2 = $composer3;
            Button(function02, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding2, interactionSource2, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            contentPadding3 = contentPadding2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt$TextButton$1
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

                public final void invoke(Composer composer, int i13) {
                    ButtonKt.TextButton(function0, modifier4, enabled4, shape4, colors4, elevation4, border4, contentPadding3, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
