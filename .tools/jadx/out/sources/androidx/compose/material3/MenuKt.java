package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: Menu.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0086\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010&2\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00140(¢\u0006\u0002\b*¢\u0006\u0002\b+H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a\u0082\u0001\u0010.\u001a\u00020\u00142\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u001400¢\u0006\u0002\b*2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u0014002\u0006\u0010\u0015\u001a\u00020\u00162\u0013\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u000100¢\u0006\u0002\b*2\u0013\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u000100¢\u0006\u0002\b*2\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:H\u0001¢\u0006\u0002\u0010;\u001a\u001d\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>H\u0000¢\u0006\u0002\u0010@\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0016\u0010\b\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0016\u0010\u0010\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0011\u0010\n\"\u000e\u0010\u0012\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006A²\u0006\n\u0010B\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010C\u001a\u00020\u0001X\u008a\u0084\u0002"}, d2 = {"ClosedAlphaTarget", "", "ClosedScaleTarget", "DropdownMenuItemDefaultMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "DropdownMenuItemDefaultMinWidth", "DropdownMenuItemHorizontalPadding", "DropdownMenuVerticalPadding", "getDropdownMenuVerticalPadding", "()F", "ExpandedAlphaTarget", "ExpandedScaleTarget", "InTransitionDuration", "", "MenuListItemContainerHeight", "MenuVerticalMargin", "getMenuVerticalMargin", "OutTransitionDuration", "DropdownMenuContent", "", "modifier", "Landroidx/compose/ui/Modifier;", "expandedState", "Landroidx/compose/animation/core/MutableTransitionState;", "", "transformOriginState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/TransformOrigin;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "shadowElevation", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenuContent-Qj0Zi0g", "(Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/runtime/MutableState;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "DropdownMenuItemContent", "text", "Lkotlin/Function0;", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "calculateTransformOrigin", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)J", "material3_release", "scale", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class MenuKt {
    public static final float ClosedAlphaTarget = 0.0f;
    public static final float ClosedScaleTarget = 0.8f;
    public static final float ExpandedAlphaTarget = 1.0f;
    public static final float ExpandedScaleTarget = 1.0f;
    public static final int InTransitionDuration = 120;
    public static final int OutTransitionDuration = 75;
    private static final float MenuVerticalMargin = Dp.m6626constructorimpl(48);
    private static final float MenuListItemContainerHeight = Dp.m6626constructorimpl(48);
    private static final float DropdownMenuItemHorizontalPadding = Dp.m6626constructorimpl(12);
    private static final float DropdownMenuVerticalPadding = Dp.m6626constructorimpl(8);
    private static final float DropdownMenuItemDefaultMinWidth = Dp.m6626constructorimpl(112);
    private static final float DropdownMenuItemDefaultMaxWidth = Dp.m6626constructorimpl(280);

    /* renamed from: DropdownMenuContent-Qj0Zi0g, reason: not valid java name */
    public static final void m2245DropdownMenuContentQj0Zi0g(final Modifier modifier, final MutableTransitionState<Boolean> mutableTransitionState, final MutableState<TransformOrigin> mutableState, final ScrollState scrollState, final Shape shape, final long containerColor, final float tonalElevation, final float shadowElevation, final BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3;
        Composer $composer4;
        boolean expanded;
        int $dirty;
        Object value$iv;
        Composer $composer5;
        Composer $composer6 = $composer.startRestartGroup(-151448888);
        ComposerKt.sourceInformation($composer6, "C(DropdownMenuContent)P(4,3,9,5,7,1:c#ui.graphics.Color,8:c#ui.unit.Dp,6:c#ui.unit.Dp)376@17481L47,379@17566L531,394@18135L460,408@18640L7,411@18715L587,428@19474L276,409@18652L1098:Menu.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer6.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= ($changed & 64) == 0 ? $composer6.changed(mutableTransitionState) : $composer6.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer6.changed(mutableState) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer6.changed(scrollState) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer6.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer6.changed(containerColor) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer6.changed(tonalElevation) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer6.changed(shadowElevation) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer6.changed(border) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer6.changedInstance(function3) ? 536870912 : 268435456;
        }
        if (($dirty2 & 306783379) == 306783378 && $composer6.getSkipping()) {
            $composer6.skipToGroupEnd();
            $composer5 = $composer6;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-151448888, $dirty2, -1, "androidx.compose.material3.DropdownMenuContent (Menu.kt:374)");
            }
            Transition transition = TransitionKt.updateTransition((MutableTransitionState) mutableTransitionState, "DropDownMenu", $composer6, MutableTransitionState.$stable | 48 | (($dirty2 >> 3) & 14), 0);
            Function3 transitionSpec$iv = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuContent$scale$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer7, int $changed2) {
                    TweenSpec tween$default;
                    $composer7.startReplaceGroup(1033023423);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1033023423, $changed2, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:381)");
                    }
                    if (segment.isTransitioningTo(false, true)) {
                        tween$default = AnimationSpecKt.tween$default(120, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                    } else {
                        tween$default = AnimationSpecKt.tween$default(1, 74, null, 4, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer7.endReplaceGroup();
                    return tween$default;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer6, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter typeConverter$iv$iv = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer6, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            boolean expanded2 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer6.startReplaceGroup(2139028452);
            ComposerKt.sourceInformation($composer6, "C:Menu.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                $composer2 = $composer6;
                ComposerKt.traceEventStart(2139028452, $changed2, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:390)");
            } else {
                $composer2 = $composer6;
            }
            float f = expanded2 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            boolean expanded3 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer6.startReplaceGroup(2139028452);
            ComposerKt.sourceInformation($composer6, "C:Menu.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                $composer3 = $composer6;
                ComposerKt.traceEventStart(2139028452, $changed3, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:390)");
            } else {
                $composer3 = $composer6;
            }
            float f2 = expanded3 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            FiniteAnimationSpec<Float> animationSpec$iv$iv = transitionSpec$iv.invoke(transition.getSegment(), $composer6, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            final State scale$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, typeConverter$iv$iv, "FloatAnimation", $composer6, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            Function3 transitionSpec$iv2 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuContent$alpha$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer7, int $changed4) {
                    TweenSpec tween$default;
                    $composer7.startReplaceGroup(-1355418157);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1355418157, $changed4, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:396)");
                    }
                    if (segment.isTransitioningTo(false, true)) {
                        tween$default = AnimationSpecKt.tween$default(30, 0, null, 6, null);
                    } else {
                        tween$default = AnimationSpecKt.tween$default(75, 0, null, 6, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer7.endReplaceGroup();
                    return tween$default;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer6, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter typeConverter$iv$iv2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer6, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            boolean expanded4 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer6.startReplaceGroup(-249413128);
            ComposerKt.sourceInformation($composer6, "C:Menu.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                $composer4 = $composer6;
                ComposerKt.traceEventStart(-249413128, $changed4, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:405)");
            } else {
                $composer4 = $composer6;
            }
            float f3 = expanded4 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer4.endReplaceGroup();
            Object initialValue$iv$iv2 = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            boolean expanded5 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer6.startReplaceGroup(-249413128);
            ComposerKt.sourceInformation($composer6, "C:Menu.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                expanded = expanded5;
                ComposerKt.traceEventStart(-249413128, $changed5, -1, "androidx.compose.material3.DropdownMenuContent.<anonymous> (Menu.kt:405)");
            } else {
                expanded = expanded5;
            }
            float f4 = expanded ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer6.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            FiniteAnimationSpec<Float> animationSpec$iv$iv2 = transitionSpec$iv2.invoke(transition.getSegment(), $composer6, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            final State alpha$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv2, animationSpec$iv$iv2, typeConverter$iv$iv2, "FloatAnimation", $composer6, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ProvidableCompositionLocal<Boolean> localInspectionMode = InspectionModeKt.getLocalInspectionMode();
            ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer6.consume(localInspectionMode);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            final boolean isInspecting = ((Boolean) consume).booleanValue();
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer6, 526637745, "CC(remember):Menu.kt#9igjgp");
            boolean invalid$iv = $composer6.changed(isInspecting) | $composer6.changed(scale$delegate) | (($dirty2 & 112) == 32 || (($dirty2 & 64) != 0 && $composer6.changedInstance(mutableTransitionState))) | $composer6.changed(alpha$delegate) | (($dirty2 & 896) == 256);
            Object it$iv = $composer6.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                value$iv = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuContent$1$1
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
                        float f5;
                        float f6 = 0.8f;
                        float f7 = 1.0f;
                        if (!isInspecting) {
                            f5 = MenuKt.DropdownMenuContent_Qj0Zi0g$lambda$1(scale$delegate);
                        } else {
                            f5 = mutableTransitionState.getTargetState().booleanValue() ? 1.0f : 0.8f;
                        }
                        $this$graphicsLayer.setScaleX(f5);
                        if (!isInspecting) {
                            f6 = MenuKt.DropdownMenuContent_Qj0Zi0g$lambda$1(scale$delegate);
                        } else if (mutableTransitionState.getTargetState().booleanValue()) {
                            f6 = 1.0f;
                        }
                        $this$graphicsLayer.setScaleY(f6);
                        if (!isInspecting) {
                            f7 = MenuKt.DropdownMenuContent_Qj0Zi0g$lambda$3(alpha$delegate);
                        } else if (!mutableTransitionState.getTargetState().booleanValue()) {
                            f7 = 0.0f;
                        }
                        $this$graphicsLayer.setAlpha(f7);
                        $this$graphicsLayer.mo4355setTransformOrigin__ExYCQ(mutableState.getValue().getPackedValue());
                    }
                };
                $composer6.updateRememberedValue(value$iv);
            } else {
                $dirty = $dirty2;
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer6);
            SurfaceKt.m2547SurfaceT9BRK9s(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv), shape, containerColor, 0L, tonalElevation, shadowElevation, border, ComposableLambdaKt.rememberComposableLambda(1573559053, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuContent$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0159  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 349
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MenuKt$DropdownMenuContent$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer6, 54), $composer6, (($dirty >> 9) & 112) | 12582912 | (($dirty >> 9) & 896) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752) | (3670016 & ($dirty >> 6)), 8);
            $composer5 = $composer6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer5.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuContent$3
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

                public final void invoke(Composer composer, int i) {
                    MenuKt.m2245DropdownMenuContentQj0Zi0g(Modifier.this, mutableTransitionState, mutableState, scrollState, shape, containerColor, tonalElevation, shadowElevation, border, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent_Qj0Zi0g$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent_Qj0Zi0g$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0286  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DropdownMenuItemContent(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, final kotlin.jvm.functions.Function0<kotlin.Unit> r33, final androidx.compose.ui.Modifier r34, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, final boolean r37, final androidx.compose.material3.MenuItemColors r38, final androidx.compose.foundation.layout.PaddingValues r39, final androidx.compose.foundation.interaction.MutableInteractionSource r40, androidx.compose.runtime.Composer r41, final int r42) {
        /*
            Method dump skipped, instructions count: 682
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MenuKt.DropdownMenuItemContent(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.MenuItemColors, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int):void");
    }

    public static final long calculateTransformOrigin(IntRect anchorBounds, IntRect menuBounds) {
        float pivotX;
        float pivotY = 1.0f;
        if (menuBounds.getLeft() >= anchorBounds.getRight()) {
            pivotX = 0.0f;
        } else if (menuBounds.getRight() <= anchorBounds.getLeft()) {
            pivotX = 1.0f;
        } else if (menuBounds.getWidth() == 0) {
            pivotX = 0.0f;
        } else {
            int intersectionCenter = (Math.max(anchorBounds.getLeft(), menuBounds.getLeft()) + Math.min(anchorBounds.getRight(), menuBounds.getRight())) / 2;
            pivotX = (intersectionCenter - menuBounds.getLeft()) / menuBounds.getWidth();
        }
        if (menuBounds.getTop() >= anchorBounds.getBottom()) {
            pivotY = 0.0f;
        } else if (menuBounds.getBottom() > anchorBounds.getTop()) {
            if (menuBounds.getHeight() == 0) {
                pivotY = 0.0f;
            } else {
                int intersectionCenter2 = (Math.max(anchorBounds.getTop(), menuBounds.getTop()) + Math.min(anchorBounds.getBottom(), menuBounds.getBottom())) / 2;
                pivotY = (intersectionCenter2 - menuBounds.getTop()) / menuBounds.getHeight();
            }
        }
        return TransformOriginKt.TransformOrigin(pivotX, pivotY);
    }

    public static final float getMenuVerticalMargin() {
        return MenuVerticalMargin;
    }

    public static final float getDropdownMenuVerticalPadding() {
        return DropdownMenuVerticalPadding;
    }
}
