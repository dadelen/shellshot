package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0090\u0001\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000b0\u001d¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001fH\u0007¢\u0006\u0002\u0010 \u001aÆ\u0001\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020#2\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u0015\b\u0002\u0010%\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000b0\u001d¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001fH\u0007¢\u0006\u0002\u0010&\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006'²\u0006\n\u0010(\u001a\u00020)X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020)X\u008a\u0084\u0002"}, d2 = {"HorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "LeadingIconEndSpacing", "LeadingIconStartSpacing", "SelectedIconContainerSize", "SelectedOverlayOpacity", "", "SurfaceOverlayOpacity", "TrailingIconSpacing", "Chip", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "colors", "Landroidx/compose/material/ChipColors;", "leadingIcon", "Landroidx/compose/runtime/Composable;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FilterChip", "selected", "Landroidx/compose/material/SelectableChipColors;", "selectedIcon", "trailingIcon", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/SelectableChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "material_release", "contentColor", "Landroidx/compose/ui/graphics/Color;", "leadingIconContentColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ChipKt {
    private static final float SelectedOverlayOpacity = 0.16f;
    private static final float SurfaceOverlayOpacity = 0.12f;
    private static final float HorizontalPadding = Dp.m6626constructorimpl(12);
    private static final float LeadingIconStartSpacing = Dp.m6626constructorimpl(4);
    private static final float LeadingIconEndSpacing = Dp.m6626constructorimpl(8);
    private static final float TrailingIconSpacing = Dp.m6626constructorimpl(8);
    private static final float SelectedIconContainerSize = Dp.m6626constructorimpl(24);

    public static final void Chip(final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, Shape shape, BorderStroke border, ChipColors colors, Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        BorderStroke borderStroke;
        ChipColors chipColors;
        int i2;
        Function2 leadingIcon;
        CornerBasedShape shape3;
        int $dirty;
        Composer $composer2;
        ChipColors colors2;
        Shape shape4;
        int $dirty2;
        BorderStroke border2;
        boolean enabled3;
        MutableInteractionSource interactionSource3;
        long m4161copywmQWz5c;
        Composer $composer3;
        final Modifier modifier3;
        final Function2 leadingIcon2;
        final boolean enabled4;
        final Shape shape5;
        final MutableInteractionSource interactionSource4;
        final ChipColors colors3;
        final BorderStroke border3;
        int i3;
        int i4;
        Composer $composer4 = $composer.startRestartGroup(-368396408);
        ComposerKt.sourceInformation($composer4, "C(Chip)P(7,6,3,4,8!2,5)92@4243L6,94@4360L12,98@4505L21,104@4702L24,108@4860L1458,99@4531L1787:Chip.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer4.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            interactionSource2 = interactionSource;
        } else if (($changed & 3072) == 0) {
            interactionSource2 = interactionSource;
            $dirty3 |= $composer4.changed(interactionSource2) ? 2048 : 1024;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i4 = 16384;
                    $dirty3 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 8192;
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            borderStroke = border;
        } else if ((196608 & $changed) == 0) {
            borderStroke = border;
            $dirty3 |= $composer4.changed(borderStroke) ? 131072 : 65536;
        } else {
            borderStroke = border;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                chipColors = colors;
                if ($composer4.changed(chipColors)) {
                    i3 = 1048576;
                    $dirty3 |= i3;
                }
            } else {
                chipColors = colors;
            }
            i3 = 524288;
            $dirty3 |= i3;
        } else {
            chipColors = colors;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty3 |= 12582912;
            i2 = i8;
            leadingIcon = function2;
        } else if (($changed & 12582912) == 0) {
            i2 = i8;
            leadingIcon = function2;
            $dirty3 |= $composer4.changedInstance(leadingIcon) ? 8388608 : 4194304;
        } else {
            i2 = i8;
            leadingIcon = function2;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
            modifier3 = modifier2;
            enabled4 = enabled2;
            interactionSource4 = interactionSource2;
            shape5 = shape2;
            leadingIcon2 = leadingIcon;
            border3 = borderStroke;
            colors3 = chipColors;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if (i7 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 16) != 0) {
                    shape3 = MaterialTheme.INSTANCE.getShapes($composer4, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                    $dirty = $dirty4 & (-57345);
                } else {
                    shape3 = shape2;
                    $dirty = $dirty4;
                }
                BorderStroke border4 = i2 != 0 ? null : borderStroke;
                if ((i & 64) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1461chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer4, 1572864, 63);
                    $composer2 = $composer4;
                    $dirty &= -3670017;
                } else {
                    $composer2 = $composer4;
                    colors2 = colors;
                }
                if (i9 != 0) {
                    shape4 = shape3;
                    leadingIcon = null;
                    $dirty2 = $dirty;
                    border2 = border4;
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                } else {
                    leadingIcon = function2;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                    border2 = border4;
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty4 & (-3670017);
                    $composer2 = $composer4;
                    interactionSource3 = interactionSource2;
                    border2 = borderStroke;
                    colors2 = chipColors;
                    enabled3 = enabled2;
                    shape4 = shape2;
                } else {
                    $composer2 = $composer4;
                    interactionSource3 = interactionSource2;
                    border2 = borderStroke;
                    colors2 = chipColors;
                    $dirty2 = $dirty4;
                    enabled3 = enabled2;
                    shape4 = shape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368396408, $dirty2, -1, "androidx.compose.material.Chip (Chip.kt:97)");
            }
            final State contentColor$delegate = colors2.contentColor(enabled3, $composer2, (($dirty2 >> 6) & 14) | (($dirty2 >> 15) & 112));
            Modifier semantics$default = SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt$Chip$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null);
            long m4173unboximpl = colors2.backgroundColor(enabled3, $composer2, (($dirty2 >> 6) & 14) | (($dirty2 >> 15) & 112)).getValue().m4173unboximpl();
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r23, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r23) : 1.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r23) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r23) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Chip$lambda$0(contentColor$delegate)) : 0.0f);
            final ChipColors colors4 = colors2;
            final Function2 leadingIcon3 = leadingIcon;
            final boolean enabled5 = enabled3;
            $composer3 = $composer2;
            SurfaceKt.m1661SurfaceLPr_se0(function02, semantics$default, enabled3, shape4, m4173unboximpl, m4161copywmQWz5c, border2, 0.0f, interactionSource3, ComposableLambdaKt.rememberComposableLambda(139076687, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$Chip$2
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

                public final void invoke(Composer $composer5, int $changed2) {
                    long Chip$lambda$0;
                    ComposerKt.sourceInformation($composer5, "C109@4942L1370,109@4870L1442:Chip.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(139076687, $changed2, -1, "androidx.compose.material.Chip.<anonymous> (Chip.kt:109)");
                        }
                        ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
                        Chip$lambda$0 = ChipKt.Chip$lambda$0(contentColor$delegate);
                        ProvidedValue<Float> provides = localContentAlpha.provides(Float.valueOf(Color.m4165getAlphaimpl(Chip$lambda$0)));
                        final Function2<Composer, Integer, Unit> function22 = leadingIcon3;
                        final ChipColors chipColors2 = colors4;
                        final boolean z = enabled5;
                        final Function3<RowScope, Composer, Integer, Unit> function32 = function3;
                        CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(667535631, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$Chip$2.1
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

                            public final void invoke(Composer $composer6, int $changed3) {
                                ComposerKt.sourceInformation($composer6, "C111@5012L10,112@5043L1259,110@4956L1346:Chip.kt#jmzs0o");
                                if (($changed3 & 3) != 2 || !$composer6.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(667535631, $changed3, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous> (Chip.kt:110)");
                                    }
                                    TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer6, 6).getBody2();
                                    final Function2<Composer, Integer, Unit> function23 = function22;
                                    final ChipColors chipColors3 = chipColors2;
                                    final boolean z2 = z;
                                    final Function3<RowScope, Composer, Integer, Unit> function33 = function32;
                                    TextKt.ProvideTextStyle(body2, ComposableLambdaKt.rememberComposableLambda(-1131213696, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.2.1.1
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

                                        /* JADX WARN: Removed duplicated region for block: B:27:0x017a  */
                                        /* JADX WARN: Removed duplicated region for block: B:30:0x0211  */
                                        /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                                        /* JADX WARN: Removed duplicated region for block: B:33:0x01e2  */
                                        /*
                                            Code decompiled incorrectly, please refer to instructions dump.
                                            To view partially-correct add '--show-bad-code' argument
                                        */
                                        public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
                                            /*
                                                Method dump skipped, instructions count: 533
                                                To view this dump add '--comments-level debug' option
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ChipKt$Chip$2.AnonymousClass1.C00331.invoke(androidx.compose.runtime.Composer, int):void");
                                        }

                                        private static final long invoke$lambda$1$lambda$0(State<Color> state) {
                                            Object thisObj$iv = state.getValue();
                                            return ((Color) thisObj$iv).m4173unboximpl();
                                        }
                                    }, $composer6, 54), $composer6, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer6.skipToGroupEnd();
                            }
                        }, $composer5, 54), $composer5, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer5.skipToGroupEnd();
                }
            }, $composer2, 54), $composer3, ($dirty2 & 14) | 805306368 | ($dirty2 & 896) | (($dirty2 >> 3) & 7168) | (3670016 & ($dirty2 << 3)) | (234881024 & ($dirty2 << 15)), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            leadingIcon2 = leadingIcon3;
            enabled4 = enabled3;
            shape5 = shape4;
            interactionSource4 = interactionSource3;
            colors3 = colors4;
            border3 = border2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$Chip$3
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

                public final void invoke(Composer composer, int i10) {
                    ChipKt.Chip(function0, modifier3, enabled4, interactionSource4, shape5, border3, colors3, leadingIcon2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long Chip$lambda$0(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m4173unboximpl();
    }

    public static final void FilterChip(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, Shape shape, BorderStroke border, SelectableChipColors colors, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource mutableInteractionSource;
        Shape shape2;
        BorderStroke borderStroke;
        int $dirty1;
        int i2;
        int i3;
        int $dirty12;
        Shape shape3;
        int $dirty;
        Modifier modifier3;
        int $dirty13;
        int i4;
        int i5;
        SelectableChipColors colors2;
        Function2 trailingIcon;
        Function2 selectedIcon;
        MutableInteractionSource interactionSource2;
        int $dirty2;
        BorderStroke border2;
        Modifier modifier4;
        Function2 leadingIcon;
        Shape shape4;
        long m4161copywmQWz5c;
        Composer $composer2;
        final Shape shape5;
        final BorderStroke border3;
        final MutableInteractionSource interactionSource3;
        final SelectableChipColors colors3;
        final Function2 leadingIcon2;
        final Function2 selectedIcon2;
        final Function2 trailingIcon2;
        final boolean enabled3;
        final Modifier modifier5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-1259208246);
        ComposerKt.sourceInformation($composer3, "C(FilterChip)P(8,7,6,3,4,10!2,5,9,11)189@8769L6,191@8896L18,198@9227L31,205@9465L34,209@9639L3966,199@9263L4342:Chip.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty3 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty3 |= 24576;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 24576) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 131072;
                    $dirty3 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 65536;
            $dirty3 |= i7;
        } else {
            shape2 = shape;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty3 |= 1572864;
            borderStroke = border;
        } else if (($changed & 1572864) == 0) {
            borderStroke = border;
            $dirty3 |= $composer3.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                if ((16777216 & $changed) == 0 ? $composer3.changed(colors) : $composer3.changedInstance(colors)) {
                    i6 = 8388608;
                    $dirty3 |= i6;
                }
            }
            i6 = 4194304;
            $dirty3 |= i6;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty3 |= 100663296;
            $dirty1 = $changed1;
        } else if (($changed & 100663296) == 0) {
            $dirty1 = $changed1;
            $dirty3 |= $composer3.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            $dirty1 = $changed1;
        }
        int $dirty14 = i & 512;
        if ($dirty14 != 0) {
            $dirty3 |= 805306368;
            i2 = $dirty14;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty14;
            $dirty3 |= $composer3.changedInstance(function22) ? 536870912 : 268435456;
        } else {
            i2 = $dirty14;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty12 = $dirty1 | 6;
            i3 = i13;
        } else if (($changed1 & 6) == 0) {
            i3 = i13;
            $dirty12 = $dirty1 | ($composer3.changedInstance(function23) ? 4 : 2);
        } else {
            i3 = i13;
            $dirty12 = $dirty1;
        }
        if ((i & 2048) != 0) {
            $dirty12 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        int $dirty15 = $dirty12;
        if (($dirty3 & 306783379) == 306783378 && ($dirty15 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            enabled3 = enabled2;
            interactionSource3 = mutableInteractionSource;
            shape5 = shape2;
            border3 = borderStroke;
            $composer2 = $composer3;
            modifier5 = modifier2;
            colors3 = colors;
            leadingIcon2 = function2;
            selectedIcon2 = function22;
            trailingIcon2 = function23;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : enabled2;
                MutableInteractionSource interactionSource4 = i10 != 0 ? null : mutableInteractionSource;
                if ((i & 32) != 0) {
                    shape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                    $dirty = $dirty3 & (-458753);
                } else {
                    shape3 = shape2;
                    $dirty = $dirty3;
                }
                BorderStroke border4 = i11 != 0 ? null : borderStroke;
                if ((i & 128) != 0) {
                    i4 = i2;
                    i5 = i3;
                    modifier3 = modifier6;
                    $dirty13 = $dirty15;
                    colors2 = ChipDefaults.INSTANCE.m1462filterChipColorsJ08w3E(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 805306368, FrameMetricsAggregator.EVERY_DURATION);
                    $composer3 = $composer3;
                    $dirty &= -29360129;
                } else {
                    modifier3 = modifier6;
                    $dirty13 = $dirty15;
                    i4 = i2;
                    i5 = i3;
                    colors2 = colors;
                }
                Function2 leadingIcon3 = i12 != 0 ? null : function2;
                Function2 selectedIcon3 = i4 != 0 ? null : function22;
                if (i5 != 0) {
                    enabled2 = enabled4;
                    trailingIcon = null;
                    interactionSource2 = interactionSource4;
                    $dirty2 = $dirty;
                    border2 = border4;
                    modifier4 = modifier3;
                    selectedIcon = selectedIcon3;
                    leadingIcon = leadingIcon3;
                    shape4 = shape3;
                } else {
                    trailingIcon = function23;
                    enabled2 = enabled4;
                    selectedIcon = selectedIcon3;
                    interactionSource2 = interactionSource4;
                    $dirty2 = $dirty;
                    border2 = border4;
                    modifier4 = modifier3;
                    leadingIcon = leadingIcon3;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 128) != 0) {
                    int i14 = (-29360129) & $dirty3;
                    BorderStroke borderStroke2 = borderStroke;
                    interactionSource2 = mutableInteractionSource;
                    border2 = borderStroke2;
                    colors2 = colors;
                    selectedIcon = function22;
                    $dirty2 = i14;
                    $dirty13 = $dirty15;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    leadingIcon = function2;
                    trailingIcon = function23;
                } else {
                    BorderStroke borderStroke3 = borderStroke;
                    interactionSource2 = mutableInteractionSource;
                    border2 = borderStroke3;
                    selectedIcon = function22;
                    $dirty2 = $dirty3;
                    $dirty13 = $dirty15;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    colors2 = colors;
                    leadingIcon = function2;
                    trailingIcon = function23;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1259208246, $dirty2, $dirty13, "androidx.compose.material.FilterChip (Chip.kt:196)");
            }
            final State contentColor = colors2.contentColor(enabled2, selected, $composer3, (($dirty2 >> 9) & 14) | (($dirty2 << 3) & 112) | (($dirty2 >> 15) & 896));
            Shape shape6 = shape4;
            Modifier modifier7 = SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt$FilterChip$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5903getCheckboxo7Vup1c());
                }
            }, 1, null);
            long m4173unboximpl = colors2.backgroundColor(enabled2, selected, $composer3, (($dirty2 >> 9) & 14) | (($dirty2 << 3) & 112) | (($dirty2 >> 15) & 896)).getValue().m4173unboximpl();
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 1.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor.getValue().m4173unboximpl()) : 0.0f);
            final SelectableChipColors colors4 = colors2;
            final Function2 leadingIcon4 = leadingIcon;
            final Function2 selectedIcon4 = selectedIcon;
            final boolean enabled5 = enabled2;
            final Function2 trailingIcon3 = trailingIcon;
            Modifier modifier8 = modifier4;
            SurfaceKt.m1662SurfaceNy5ogXk(selected, function0, modifier7, enabled5, shape6, m4173unboximpl, m4161copywmQWz5c, border2, 0.0f, interactionSource2, ComposableLambdaKt.rememberComposableLambda(722126431, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$FilterChip$2
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
                    ComposerKt.sourceInformation($composer4, "C210@9727L3872,210@9649L3950:Chip.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(722126431, $changed2, -1, "androidx.compose.material.FilterChip.<anonymous> (Chip.kt:210)");
                        }
                        ProvidedValue<Float> provides = ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m4165getAlphaimpl(contentColor.getValue().m4173unboximpl())));
                        final Function2<Composer, Integer, Unit> function24 = leadingIcon4;
                        final boolean z = selected;
                        final Function2<Composer, Integer, Unit> function25 = selectedIcon4;
                        final Function2<Composer, Integer, Unit> function26 = trailingIcon3;
                        final Function3<RowScope, Composer, Integer, Unit> function32 = function3;
                        final SelectableChipColors selectableChipColors = colors4;
                        final boolean z2 = enabled5;
                        final State<Color> state = contentColor;
                        CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(1582291359, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$FilterChip$2.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C212@9797L10,213@9828L3761,211@9741L3848:Chip.kt#jmzs0o");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1582291359, $changed3, -1, "androidx.compose.material.FilterChip.<anonymous>.<anonymous> (Chip.kt:211)");
                                    }
                                    TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                    final Function2<Composer, Integer, Unit> function27 = function24;
                                    final boolean z3 = z;
                                    final Function2<Composer, Integer, Unit> function28 = function25;
                                    final Function2<Composer, Integer, Unit> function29 = function26;
                                    final Function3<RowScope, Composer, Integer, Unit> function33 = function32;
                                    final SelectableChipColors selectableChipColors2 = selectableChipColors;
                                    final boolean z4 = z2;
                                    final State<Color> state2 = state;
                                    TextKt.ProvideTextStyle(body2, ComposableLambdaKt.rememberComposableLambda(-1543702066, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.2.1.1
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

                                        /* JADX WARN: Removed duplicated region for block: B:100:0x0257  */
                                        /* JADX WARN: Removed duplicated region for block: B:39:0x0597  */
                                        /* JADX WARN: Removed duplicated region for block: B:42:0x05a3  */
                                        /* JADX WARN: Removed duplicated region for block: B:45:0x05d5  */
                                        /* JADX WARN: Removed duplicated region for block: B:50:0x0641  */
                                        /* JADX WARN: Removed duplicated region for block: B:53:0x06a0  */
                                        /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
                                        /* JADX WARN: Removed duplicated region for block: B:56:0x067a  */
                                        /* JADX WARN: Removed duplicated region for block: B:58:0x05eb A[ADDED_TO_REGION] */
                                        /* JADX WARN: Removed duplicated region for block: B:59:0x05a7  */
                                        /* JADX WARN: Removed duplicated region for block: B:62:0x0245  */
                                        /* JADX WARN: Removed duplicated region for block: B:65:0x0251  */
                                        /* JADX WARN: Removed duplicated region for block: B:73:0x02f2  */
                                        /* JADX WARN: Removed duplicated region for block: B:75:0x034f A[ADDED_TO_REGION] */
                                        /* JADX WARN: Removed duplicated region for block: B:97:0x0342  */
                                        /*
                                            Code decompiled incorrectly, please refer to instructions dump.
                                            To view partially-correct add '--show-bad-code' argument
                                        */
                                        public final void invoke(androidx.compose.runtime.Composer r81, int r82) {
                                            /*
                                                Method dump skipped, instructions count: 1700
                                                To view this dump add '--comments-level debug' option
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ChipKt$FilterChip$2.AnonymousClass1.C00341.invoke(androidx.compose.runtime.Composer, int):void");
                                        }
                                    }, $composer5, 54), $composer5, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer4, 54), $composer4, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (($dirty2 >> 3) & 57344) | (29360128 & ($dirty2 << 3)) | (1879048192 & ($dirty2 << 15)), 6, 256);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape5 = shape6;
            border3 = border2;
            interactionSource3 = interactionSource2;
            colors3 = colors4;
            leadingIcon2 = leadingIcon4;
            selectedIcon2 = selectedIcon4;
            trailingIcon2 = trailingIcon3;
            enabled3 = enabled5;
            modifier5 = modifier8;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt$FilterChip$3
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

                public final void invoke(Composer composer, int i15) {
                    ChipKt.FilterChip(selected, function0, modifier5, enabled3, interactionSource3, shape5, border3, colors3, leadingIcon2, selectedIcon2, trailingIcon2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
