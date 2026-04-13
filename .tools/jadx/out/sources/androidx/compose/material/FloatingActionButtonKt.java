package androidx.compose.material;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
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
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: FloatingActionButton.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0088\u0001\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t¢\u0006\u0002\b\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aq\u0010\u001a\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"ExtendedFabIconPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ExtendedFabSize", "ExtendedFabTextPadding", "FabSize", "ExtendedFloatingActionButton", "", "text", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "onClick", "modifier", "Landroidx/compose/ui/Modifier;", "icon", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/material/FloatingActionButtonElevation;", "ExtendedFloatingActionButton-wqdebIU", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material/FloatingActionButtonElevation;Landroidx/compose/runtime/Composer;II)V", "FloatingActionButton", "content", "FloatingActionButton-bogVsAg", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material/FloatingActionButtonElevation;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FloatingActionButtonKt {
    private static final float FabSize = Dp.m6626constructorimpl(56);
    private static final float ExtendedFabSize = Dp.m6626constructorimpl(48);
    private static final float ExtendedFabIconPadding = Dp.m6626constructorimpl(12);
    private static final float ExtendedFabTextPadding = Dp.m6626constructorimpl(20);

    /* renamed from: FloatingActionButton-bogVsAg, reason: not valid java name */
    public static final void m1568FloatingActionButtonbogVsAg(final Function0<Unit> function0, Modifier modifier, MutableInteractionSource interactionSource, Shape shape, long backgroundColor, long contentColor, FloatingActionButtonElevation elevation, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        long j;
        final long contentColor2;
        Shape shape3;
        long backgroundColor2;
        int $dirty;
        FloatingActionButtonElevation elevation2;
        Shape shape4;
        long backgroundColor3;
        int i2;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        final Modifier modifier3;
        final MutableInteractionSource interactionSource4;
        final Shape shape5;
        final long backgroundColor4;
        final long contentColor3;
        final FloatingActionButtonElevation elevation3;
        Object value$iv;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(1028985328);
        ComposerKt.sourceInformation($composer3, "C(FloatingActionButton)P(6,5,4,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3)83@3890L6,84@3978L6,85@4022L32,86@4132L11,97@4531L28,99@4619L408,91@4310L717:FloatingActionButton.kt#jmzs0o");
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
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty2 |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 2048;
                    $dirty2 |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty2 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j = backgroundColor;
                if ($composer3.changed(j)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                j = backgroundColor;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            j = backgroundColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i4 = 131072;
                    $dirty2 |= i4;
                }
            } else {
                contentColor2 = contentColor;
            }
            i4 = 65536;
            $dirty2 |= i4;
        } else {
            contentColor2 = contentColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(elevation)) {
                i3 = 1048576;
                $dirty2 |= i3;
            }
            i3 = 524288;
            $dirty2 |= i3;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            interactionSource4 = interactionSource2;
            $composer2 = $composer3;
            shape5 = shape2;
            backgroundColor4 = j;
            contentColor3 = contentColor2;
            elevation3 = elevation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                MutableInteractionSource interactionSource5 = i8 != 0 ? null : interactionSource2;
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1480getSecondary0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty = $dirty2 & (-458753);
                } else {
                    $dirty = $dirty2;
                }
                if ((i & 64) != 0) {
                    long backgroundColor5 = backgroundColor2;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1564elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    modifier2 = modifier4;
                    $dirty &= -3670017;
                    interactionSource2 = interactionSource5;
                    shape4 = shape3;
                    backgroundColor3 = backgroundColor5;
                    i2 = 1028985328;
                } else {
                    long backgroundColor6 = backgroundColor2;
                    elevation2 = elevation;
                    modifier2 = modifier4;
                    interactionSource2 = interactionSource5;
                    shape4 = shape3;
                    backgroundColor3 = backgroundColor6;
                    i2 = 1028985328;
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
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    int i9 = $dirty2 & (-3670017);
                    elevation2 = elevation;
                    shape4 = shape2;
                    backgroundColor3 = j;
                    $dirty = i9;
                    i2 = 1028985328;
                } else {
                    shape4 = shape2;
                    backgroundColor3 = j;
                    i2 = 1028985328;
                    $dirty = $dirty2;
                    elevation2 = elevation;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.FloatingActionButton (FloatingActionButton.kt:88)");
            }
            if (interactionSource2 == null) {
                $composer3.startReplaceGroup(-1991754265);
                ComposerKt.sourceInformation($composer3, "90@4266L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 628486523, "CC(remember):FloatingActionButton.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource3 = (MutableInteractionSource) value$iv;
            } else {
                $composer3.startReplaceGroup(628485872);
                $composer3.endReplaceGroup();
                interactionSource3 = interactionSource2;
            }
            long contentColor4 = contentColor2;
            $composer2 = $composer3;
            SurfaceKt.m1661SurfaceLPr_se0(function02, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$FloatingActionButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null), false, shape4, backgroundColor3, contentColor4, null, elevation2.elevation(interactionSource3, $composer3, ($dirty >> 15) & 112).getValue().m6640unboximpl(), interactionSource3, ComposableLambdaKt.rememberComposableLambda(1972871863, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$FloatingActionButton$2
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
                    ComposerKt.sourceInformation($composer4, "C100@4701L320,100@4629L392:FloatingActionButton.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1972871863, $changed2, -1, "androidx.compose.material.FloatingActionButton.<anonymous> (FloatingActionButton.kt:100)");
                        }
                        ProvidedValue<Float> provides = ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m4165getAlphaimpl(contentColor2)));
                        final Function2<Composer, Integer, Unit> function22 = function2;
                        CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(1867794295, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$FloatingActionButton$2.1
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
                                ComposerKt.sourceInformation($composer5, "C101@4746L10,101@4765L246,101@4715L296:FloatingActionButton.kt#jmzs0o");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1867794295, $changed3, -1, "androidx.compose.material.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:101)");
                                    }
                                    TextStyle button = MaterialTheme.INSTANCE.getTypography($composer5, 6).getButton();
                                    final Function2<Composer, Integer, Unit> function23 = function22;
                                    TextKt.ProvideTextStyle(button, ComposableLambdaKt.rememberComposableLambda(-1567914264, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt.FloatingActionButton.2.1.1
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

                                        /* JADX WARN: Removed duplicated region for block: B:24:0x0160  */
                                        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                                        /*
                                            Code decompiled incorrectly, please refer to instructions dump.
                                            To view partially-correct add '--show-bad-code' argument
                                        */
                                        public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                                            /*
                                                Method dump skipped, instructions count: 356
                                                To view this dump add '--comments-level debug' option
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.FloatingActionButtonKt$FloatingActionButton$2.AnonymousClass1.C00381.invoke(androidx.compose.runtime.Composer, int):void");
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
            }, $composer3, 54), $composer2, ($dirty & 14) | 805306368 | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 68);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            interactionSource4 = interactionSource2;
            shape5 = shape4;
            backgroundColor4 = backgroundColor3;
            contentColor3 = contentColor4;
            elevation3 = elevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$FloatingActionButton$3
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
                    FloatingActionButtonKt.m1568FloatingActionButtonbogVsAg(function0, modifier3, interactionSource4, shape5, backgroundColor4, contentColor3, elevation3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: ExtendedFloatingActionButton-wqdebIU, reason: not valid java name */
    public static final void m1567ExtendedFloatingActionButtonwqdebIU(Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, MutableInteractionSource interactionSource, Shape shape, long backgroundColor, long contentColor, FloatingActionButtonElevation elevation, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        final Function2 icon;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        final FloatingActionButtonElevation elevation2;
        int $dirty;
        long backgroundColor2;
        long contentColor2;
        Composer $composer2;
        MutableInteractionSource interactionSource3;
        FloatingActionButtonElevation elevation3;
        long contentColor3;
        long contentColor4;
        int $dirty2;
        Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        Composer $composer3;
        final Modifier modifier3;
        final Function2 icon2;
        final MutableInteractionSource interactionSource4;
        final Shape shape4;
        final long backgroundColor3;
        final long contentColor5;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer4 = $composer.startRestartGroup(-1555720195);
        ComposerKt.sourceInformation($composer4, "C(ExtendedFloatingActionButton)P(8,6,5,3,4,7,0:c#ui.graphics.Color,1:c#ui.graphics.Color)152@7254L6,153@7342L6,154@7386L32,155@7496L11,168@7878L487,157@7516L849:FloatingActionButton.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty3 |= $composer4.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            icon = function22;
        } else if (($changed & 3072) == 0) {
            icon = function22;
            $dirty3 |= $composer4.changedInstance(icon) ? 2048 : 1024;
        } else {
            icon = function22;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty3 |= $composer4.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i5 = 131072;
                    $dirty3 |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 65536;
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0 && $composer4.changed(backgroundColor)) {
                i4 = 1048576;
                $dirty3 |= i4;
            }
            i4 = 524288;
            $dirty3 |= i4;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer4.changed(contentColor)) {
                i3 = 8388608;
                $dirty3 |= i3;
            }
            i3 = 4194304;
            $dirty3 |= i3;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0) {
                elevation2 = elevation;
                if ($composer4.changed(elevation2)) {
                    i2 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    $dirty3 |= i2;
                }
            } else {
                elevation2 = elevation;
            }
            i2 = 33554432;
            $dirty3 |= i2;
        } else {
            elevation2 = elevation;
        }
        if (($dirty3 & 38347923) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
            modifier3 = modifier2;
            icon2 = icon;
            interactionSource4 = interactionSource2;
            shape4 = shape2;
            function23 = function2;
            backgroundColor3 = backgroundColor;
            contentColor5 = contentColor;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    icon = null;
                }
                if (i8 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer4, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty3 & (-3670017);
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer4, 6).m1480getSecondary0d7_KjU();
                } else {
                    $dirty = $dirty3;
                    backgroundColor2 = backgroundColor;
                }
                if ((i & 128) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer4, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    contentColor2 = contentColor;
                }
                if ((i & 256) != 0) {
                    $composer2 = $composer4;
                    elevation3 = FloatingActionButtonDefaults.INSTANCE.m1564elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer4, 24576, 15);
                    interactionSource3 = interactionSource2;
                    contentColor3 = contentColor2;
                    contentColor4 = backgroundColor2;
                    $dirty3 = $dirty & (-234881025);
                    $dirty2 = 12582912;
                    shape3 = shape2;
                } else {
                    $composer2 = $composer4;
                    interactionSource3 = interactionSource2;
                    elevation3 = elevation2;
                    contentColor3 = contentColor2;
                    contentColor4 = backgroundColor2;
                    $dirty3 = $dirty;
                    $dirty2 = 12582912;
                    shape3 = shape2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 256) != 0) {
                    contentColor4 = backgroundColor;
                    contentColor3 = contentColor;
                    $dirty3 &= -234881025;
                    $composer2 = $composer4;
                    interactionSource3 = interactionSource2;
                    elevation3 = elevation2;
                    $dirty2 = 12582912;
                    shape3 = shape2;
                } else {
                    contentColor4 = backgroundColor;
                    contentColor3 = contentColor;
                    $composer2 = $composer4;
                    interactionSource3 = interactionSource2;
                    elevation3 = elevation2;
                    $dirty2 = 12582912;
                    shape3 = shape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1555720195, $dirty3, -1, "androidx.compose.material.ExtendedFloatingActionButton (FloatingActionButton.kt:156)");
            }
            function23 = function2;
            $composer3 = $composer2;
            m1568FloatingActionButtonbogVsAg(function02, SizeKt.m721sizeInqDBjuR0$default(modifier2, ExtendedFabSize, ExtendedFabSize, 0.0f, 0.0f, 12, null), interactionSource3, shape3, contentColor4, contentColor3, elevation3, ComposableLambdaKt.rememberComposableLambda(1418981691, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$ExtendedFloatingActionButton$1
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

                /* JADX WARN: Removed duplicated region for block: B:27:0x0162  */
                /* JADX WARN: Removed duplicated region for block: B:30:0x01b6  */
                /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:33:0x0189  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r30, int r31) {
                    /*
                        Method dump skipped, instructions count: 442
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.FloatingActionButtonKt$ExtendedFloatingActionButton$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer3, $dirty2 | (($dirty3 >> 3) & 14) | (($dirty3 >> 6) & 896) | (($dirty3 >> 6) & 7168) | (($dirty3 >> 6) & 57344) | (($dirty3 >> 6) & 458752) | (($dirty3 >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            icon2 = icon;
            interactionSource4 = interactionSource3;
            shape4 = shape3;
            backgroundColor3 = contentColor4;
            contentColor5 = contentColor3;
            elevation2 = elevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function24 = function23;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.FloatingActionButtonKt$ExtendedFloatingActionButton$2
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

                public final void invoke(Composer composer, int i9) {
                    FloatingActionButtonKt.m1567ExtendedFloatingActionButtonwqdebIU(function24, function0, modifier3, icon2, interactionSource4, shape4, backgroundColor3, contentColor5, elevation2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
