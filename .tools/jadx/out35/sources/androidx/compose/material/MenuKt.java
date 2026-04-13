package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: Menu.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001aY\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u001fH\u0001¢\u0006\u0002\u0010 \u001ac\u0010!\u001a\u00020\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100#2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020\u00132\b\b\u0002\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u001fH\u0001¢\u0006\u0002\u0010*\u001a\u001d\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0000¢\u0006\u0002\u0010/\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0007\u0010\b\"\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\f\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\r\u0010\b\"\u000e\u0010\u000e\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000¨\u00060²\u0006\n\u00101\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"DropdownMenuItemDefaultMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "DropdownMenuItemDefaultMinHeight", "DropdownMenuItemDefaultMinWidth", "DropdownMenuItemHorizontalPadding", "DropdownMenuVerticalPadding", "getDropdownMenuVerticalPadding", "()F", "InTransitionDuration", "", "MenuElevation", "MenuVerticalMargin", "getMenuVerticalMargin", "OutTransitionDuration", "DropdownMenuContent", "", "expandedStates", "Landroidx/compose/animation/core/MutableTransitionState;", "", "transformOriginState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/TransformOrigin;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/runtime/MutableState;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItemContent", "onClick", "Lkotlin/Function0;", "enabled", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "calculateTransformOrigin", "parentBounds", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)J", "material_release", "scale", "", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MenuKt {
    public static final int InTransitionDuration = 120;
    public static final int OutTransitionDuration = 75;
    private static final float MenuElevation = Dp.m6626constructorimpl(8);
    private static final float MenuVerticalMargin = Dp.m6626constructorimpl(48);
    private static final float DropdownMenuItemHorizontalPadding = Dp.m6626constructorimpl(16);
    private static final float DropdownMenuVerticalPadding = Dp.m6626constructorimpl(8);
    private static final float DropdownMenuItemDefaultMinWidth = Dp.m6626constructorimpl(112);
    private static final float DropdownMenuItemDefaultMaxWidth = Dp.m6626constructorimpl(280);
    private static final float DropdownMenuItemDefaultMinHeight = Dp.m6626constructorimpl(48);

    public static final void DropdownMenuContent(final MutableTransitionState<Boolean> mutableTransitionState, final MutableState<TransformOrigin> mutableState, final ScrollState scrollState, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        int $dirty;
        Object initialValue$iv$iv;
        boolean it;
        char c;
        boolean it2;
        boolean it3;
        Object value$iv;
        final Modifier modifier3;
        Composer $composer3 = $composer.startRestartGroup(435109845);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenuContent)P(1,4,3,2)66@2778L50,68@2858L666,94@3554L477,114@4084L153,121@4279L248,113@4036L491:Menu.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed(mutableTransitionState) : $composer3.changedInstance(mutableTransitionState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(mutableState) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(scrollState) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(435109845, $dirty2, -1, "androidx.compose.material.DropdownMenuContent (Menu.kt:64)");
            }
            Transition transition = TransitionKt.rememberTransition(mutableTransitionState, "DropDownMenu", $composer3, MutableTransitionState.$stable | 48 | ($dirty2 & 14), 0);
            Function3 transitionSpec$iv = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material.MenuKt$DropdownMenuContent$scale$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer4, int $changed2) {
                    TweenSpec tween$default;
                    $composer4.startReplaceGroup(-800950068);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-800950068, $changed2, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:70)");
                    }
                    if (segment.isTransitioningTo(false, true)) {
                        tween$default = AnimationSpecKt.tween$default(120, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                    } else {
                        tween$default = AnimationSpecKt.tween$default(1, 74, null, 4, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer4.endReplaceGroup();
                    return tween$default;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer3, -1338768149, "CC(animateFloat)P(2)1968@80576L78:Transition.kt#pdpnli");
            TwoWayConverter typeConverter$iv$iv = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer3, -142660079, "CC(animateValue)P(3,2)1885@77139L32,1886@77194L31,1887@77250L23,1889@77286L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            boolean it4 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer3.startReplaceGroup(1652594929);
            ComposerKt.sourceInformation($composer3, "C:Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                $composer2 = $composer3;
                $dirty = $dirty2;
                ComposerKt.traceEventStart(1652594929, $changed2, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:85)");
            } else {
                $composer2 = $composer3;
                $dirty = $dirty2;
            }
            float f = it4 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv2 = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            boolean it5 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer3.startReplaceGroup(1652594929);
            ComposerKt.sourceInformation($composer3, "C:Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                initialValue$iv$iv = initialValue$iv$iv2;
                it = it5;
                c = 65535;
                ComposerKt.traceEventStart(1652594929, $changed3, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:85)");
            } else {
                initialValue$iv$iv = initialValue$iv$iv2;
                it = it5;
                c = 65535;
            }
            float f2 = it ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            FiniteAnimationSpec<Float> animationSpec$iv$iv = transitionSpec$iv.invoke(transition.getSegment(), $composer3, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            final Modifier modifier4 = modifier2;
            final State scale$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, typeConverter$iv$iv, "FloatAnimation", $composer3, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function3 transitionSpec$iv2 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material.MenuKt$DropdownMenuContent$alpha$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer4, int $changed4) {
                    TweenSpec tween$default;
                    $composer4.startReplaceGroup(-2065494304);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2065494304, $changed4, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:96)");
                    }
                    if (segment.isTransitioningTo(false, true)) {
                        tween$default = AnimationSpecKt.tween$default(30, 0, null, 6, null);
                    } else {
                        tween$default = AnimationSpecKt.tween$default(75, 0, null, 6, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer4.endReplaceGroup();
                    return tween$default;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer3, -1338768149, "CC(animateFloat)P(2)1968@80576L78:Transition.kt#pdpnli");
            TwoWayConverter typeConverter$iv$iv2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer3, -142660079, "CC(animateValue)P(3,2)1885@77139L32,1886@77194L31,1887@77250L23,1889@77286L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            boolean it6 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer3.startReplaceGroup(388050693);
            ComposerKt.sourceInformation($composer3, "C:Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                it2 = it6;
                ComposerKt.traceEventStart(388050693, $changed4, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:105)");
            } else {
                it2 = it6;
            }
            float f3 = it2 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object initialValue$iv$iv3 = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            boolean it7 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer3.startReplaceGroup(388050693);
            ComposerKt.sourceInformation($composer3, "C:Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                it3 = it7;
                ComposerKt.traceEventStart(388050693, $changed5, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:105)");
            } else {
                it3 = it7;
            }
            float f4 = it3 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            FiniteAnimationSpec<Float> animationSpec$iv$iv2 = transitionSpec$iv2.invoke(transition.getSegment(), $composer3, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            final State alpha$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv3, targetValue$iv$iv2, animationSpec$iv$iv2, typeConverter$iv$iv2, "FloatAnimation", $composer3, (458752 & ($changed$iv$iv2 << 6)) | ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 668225020, "CC(remember):Menu.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(scale$delegate) | $composer3.changed(alpha$delegate) | (($dirty & 112) == 32);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material.MenuKt$DropdownMenuContent$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                        invoke2(graphicsLayerScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                        float DropdownMenuContent$lambda$1;
                        float DropdownMenuContent$lambda$12;
                        float DropdownMenuContent$lambda$3;
                        DropdownMenuContent$lambda$1 = MenuKt.DropdownMenuContent$lambda$1(scale$delegate);
                        $this$graphicsLayer.setScaleX(DropdownMenuContent$lambda$1);
                        DropdownMenuContent$lambda$12 = MenuKt.DropdownMenuContent$lambda$1(scale$delegate);
                        $this$graphicsLayer.setScaleY(DropdownMenuContent$lambda$12);
                        DropdownMenuContent$lambda$3 = MenuKt.DropdownMenuContent$lambda$3(alpha$delegate);
                        $this$graphicsLayer.setAlpha(DropdownMenuContent$lambda$3);
                        $this$graphicsLayer.mo4355setTransformOrigin__ExYCQ(mutableState.getValue().getPackedValue());
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CardKt.m1454CardFjzlyU(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv), null, 0L, 0L, null, MenuElevation, ComposableLambdaKt.rememberComposableLambda(895555282, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MenuKt$DropdownMenuContent$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x015a  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 350
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.MenuKt$DropdownMenuContent$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, 1769472, 30);
            $composer3 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MenuKt$DropdownMenuContent$3
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

                public final void invoke(Composer composer, int i3) {
                    MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState, modifier3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent$lambda$1(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent$lambda$3(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x02a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DropdownMenuItemContent(final kotlin.jvm.functions.Function0<kotlin.Unit> r31, androidx.compose.ui.Modifier r32, boolean r33, androidx.compose.foundation.layout.PaddingValues r34, androidx.compose.foundation.interaction.MutableInteractionSource r35, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, androidx.compose.runtime.Composer r37, final int r38, final int r39) {
        /*
            Method dump skipped, instructions count: 711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.MenuKt.DropdownMenuItemContent(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final float getMenuVerticalMargin() {
        return MenuVerticalMargin;
    }

    public static final float getDropdownMenuVerticalPadding() {
        return DropdownMenuVerticalPadding;
    }

    public static final long calculateTransformOrigin(IntRect parentBounds, IntRect menuBounds) {
        float pivotX;
        float pivotY = 1.0f;
        if (menuBounds.getLeft() >= parentBounds.getRight()) {
            pivotX = 0.0f;
        } else if (menuBounds.getRight() <= parentBounds.getLeft()) {
            pivotX = 1.0f;
        } else if (menuBounds.getWidth() == 0) {
            pivotX = 0.0f;
        } else {
            int intersectionCenter = (Math.max(parentBounds.getLeft(), menuBounds.getLeft()) + Math.min(parentBounds.getRight(), menuBounds.getRight())) / 2;
            pivotX = (intersectionCenter - menuBounds.getLeft()) / menuBounds.getWidth();
        }
        if (menuBounds.getTop() >= parentBounds.getBottom()) {
            pivotY = 0.0f;
        } else if (menuBounds.getBottom() > parentBounds.getTop()) {
            if (menuBounds.getHeight() == 0) {
                pivotY = 0.0f;
            } else {
                int intersectionCenter2 = (Math.max(parentBounds.getTop(), menuBounds.getTop()) + Math.min(parentBounds.getBottom(), menuBounds.getBottom())) / 2;
                pivotY = (intersectionCenter2 - menuBounds.getTop()) / menuBounds.getHeight();
            }
        }
        return TransformOriginKt.TransformOrigin(pivotX, pivotY);
    }
}
