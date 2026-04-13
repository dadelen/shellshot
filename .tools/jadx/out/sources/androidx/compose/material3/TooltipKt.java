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
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.BasicTooltipDefaults;
import androidx.compose.material3.internal.BasicTooltip_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: Tooltip.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\u001al\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u001c\u0010$\u001a\u0018\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020!0%¢\u0006\u0002\b'¢\u0006\u0002\b(2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020.2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020!01¢\u0006\u0002\b'H\u0007¢\u0006\u0002\u00102\u001a&\u00103\u001a\u00020*2\b\b\u0002\u00104\u001a\u00020.2\b\b\u0002\u00105\u001a\u00020.2\b\b\u0002\u00106\u001a\u000207H\u0007\u001a+\u00108\u001a\u00020*2\b\b\u0002\u00104\u001a\u00020.2\b\b\u0002\u00105\u001a\u00020.2\b\b\u0002\u00106\u001a\u000207H\u0007¢\u0006\u0002\u00109\u001a\u001a\u0010:\u001a\u00020,*\u00020,2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020.0<H\u0000\u001a\u001c\u0010=\u001a\u00020,*\u00020,2\u0006\u0010>\u001a\u00020.2\u0006\u0010?\u001a\u00020.H\u0001\"\u0016\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0005\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\t\u0010\u0003\"\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000f\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\u0003\"\u0010\u0010\u0011\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u0012\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0013\u0010\u0003\"\u0016\u0010\u0014\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0015\u0010\u0003\"\u0016\u0010\u0016\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0017\u0010\u0003\"\u0010\u0010\u0018\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u001aX\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001c\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u001d\u0010\u0003\"\u0016\u0010\u001e\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u001f\u0010\u0003¨\u0006@²\u0006\n\u0010A\u001a\u00020BX\u008a\u0084\u0002²\u0006\n\u0010C\u001a\u00020BX\u008a\u0084\u0002"}, d2 = {"ActionLabelBottomPadding", "Landroidx/compose/ui/unit/Dp;", "getActionLabelBottomPadding", "()F", "F", "ActionLabelMinHeight", "getActionLabelMinHeight", "HeightFromSubheadToTextFirstLine", "HeightToSubheadFirstLine", "getHeightToSubheadFirstLine", "PlainTooltipContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getPlainTooltipContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "PlainTooltipHorizontalPadding", "PlainTooltipMaxWidth", "getPlainTooltipMaxWidth", "PlainTooltipVerticalPadding", "RichTooltipHorizontalPadding", "getRichTooltipHorizontalPadding", "RichTooltipMaxWidth", "getRichTooltipMaxWidth", "SpacingBetweenTooltipAndAnchor", "getSpacingBetweenTooltipAndAnchor", "TextBottomPadding", "TooltipFadeInDuration", "", "TooltipFadeOutDuration", "TooltipMinHeight", "getTooltipMinHeight", "TooltipMinWidth", "getTooltipMinWidth", "TooltipBox", "", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "tooltip", "Lkotlin/Function1;", "Landroidx/compose/material3/TooltipScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/material3/TooltipState;", "modifier", "Landroidx/compose/ui/Modifier;", "focusable", "", "enableUserInput", "content", "Lkotlin/Function0;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function3;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TooltipState", "initialIsVisible", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "rememberTooltipState", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TooltipState;", "animateTooltip", "transition", "Landroidx/compose/animation/core/Transition;", "textVerticalPadding", "subheadExists", "actionExists", "material3_release", "scale", "", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TooltipKt {
    public static final int TooltipFadeInDuration = 150;
    public static final int TooltipFadeOutDuration = 75;
    private static final float SpacingBetweenTooltipAndAnchor = Dp.m6626constructorimpl(4);
    private static final float TooltipMinHeight = Dp.m6626constructorimpl(24);
    private static final float TooltipMinWidth = Dp.m6626constructorimpl(40);
    private static final float PlainTooltipMaxWidth = Dp.m6626constructorimpl(200);
    private static final float PlainTooltipVerticalPadding = Dp.m6626constructorimpl(4);
    private static final float PlainTooltipHorizontalPadding = Dp.m6626constructorimpl(8);
    private static final PaddingValues PlainTooltipContentPadding = PaddingKt.m666PaddingValuesYgX7TsA(PlainTooltipHorizontalPadding, PlainTooltipVerticalPadding);
    private static final float RichTooltipMaxWidth = Dp.m6626constructorimpl(320);
    private static final float RichTooltipHorizontalPadding = Dp.m6626constructorimpl(16);
    private static final float HeightToSubheadFirstLine = Dp.m6626constructorimpl(28);
    private static final float HeightFromSubheadToTextFirstLine = Dp.m6626constructorimpl(24);
    private static final float TextBottomPadding = Dp.m6626constructorimpl(16);
    private static final float ActionLabelMinHeight = Dp.m6626constructorimpl(36);
    private static final float ActionLabelBottomPadding = Dp.m6626constructorimpl(8);

    public static final void TooltipBox(final PopupPositionProvider positionProvider, final Function3<? super TooltipScope, ? super Composer, ? super Integer, Unit> function3, final TooltipState state, Modifier modifier, boolean focusable, boolean enableUserInput, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        PopupPositionProvider popupPositionProvider;
        Modifier modifier2;
        boolean z;
        boolean z2;
        boolean focusable2;
        boolean enableUserInput2;
        Object value$iv;
        Object value$iv2;
        final Modifier modifier3;
        final boolean focusable3;
        final boolean enableUserInput3;
        Composer $composer2 = $composer.startRestartGroup(1836749106);
        ComposerKt.sourceInformation($composer2, "C(TooltipBox)P(4,6,5,3,2,1)133@5704L64,134@5826L33,135@5876L52,137@5979L103,143@6168L64,141@6088L310:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            popupPositionProvider = positionProvider;
        } else if (($changed & 6) == 0) {
            popupPositionProvider = positionProvider;
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        } else {
            popupPositionProvider = positionProvider;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            z = focusable;
        } else if (($changed & 24576) == 0) {
            z = focusable;
            $dirty |= $composer2.changed(z) ? 16384 : 8192;
        } else {
            z = focusable;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = enableUserInput;
        } else if ((196608 & $changed) == 0) {
            z2 = enableUserInput;
            $dirty |= $composer2.changed(z2) ? 131072 : 65536;
        } else {
            z2 = enableUserInput;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        int $dirty2 = $dirty;
        if ((599187 & $dirty2) != 599186 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i3 == 0) {
                focusable2 = z;
            } else {
                focusable2 = true;
            }
            if (i4 == 0) {
                enableUserInput2 = z2;
            } else {
                enableUserInput2 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1836749106, $dirty2, -1, "androidx.compose.material3.TooltipBox (Tooltip.kt:131)");
            }
            final Transition transition = TransitionKt.updateTransition((MutableTransitionState) state.getTransition(), "tooltip transition", $composer2, MutableTransitionState.$stable | 48, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1495108980, "CC(remember):Tooltip.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState anchorBounds = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1495107361, "CC(remember):Tooltip.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new TooltipScopeImpl(new Function0<LayoutCoordinates>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final LayoutCoordinates invoke() {
                        return anchorBounds.getValue();
                    }
                });
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final TooltipScopeImpl scope = (TooltipScopeImpl) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function2 wrappedContent = ComposableLambdaKt.rememberComposableLambda(-1130808188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$wrappedContent$1
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

                /* JADX WARN: Removed duplicated region for block: B:27:0x0181  */
                /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r27, int r28) {
                    /*
                        Method dump skipped, instructions count: 389
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt$TooltipBox$wrappedContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54);
            Modifier modifier4 = modifier2;
            BasicTooltip_androidKt.BasicTooltipBox(popupPositionProvider, ComposableLambdaKt.rememberComposableLambda(-149611544, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0156  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 346
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt$TooltipBox$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), state, modifier4, focusable2, enableUserInput2, wrappedContent, $composer2, (458752 & $dirty2) | ($dirty2 & 14) | 1572912 | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            focusable3 = focusable2;
            enableUserInput3 = enableUserInput2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            focusable3 = z;
            enableUserInput3 = z2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$2
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

                public final void invoke(Composer composer, int i5) {
                    TooltipKt.TooltipBox(PopupPositionProvider.this, function3, state, modifier3, focusable3, enableUserInput3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final TooltipState rememberTooltipState(boolean initialIsVisible, boolean isPersistent, MutatorMutex mutatorMutex, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1413230530, "C(rememberTooltipState)436@18949L210:Tooltip.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialIsVisible = false;
        }
        if ((i & 2) != 0) {
            isPersistent = false;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1413230530, $changed, -1, "androidx.compose.material3.rememberTooltipState (Tooltip.kt:436)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1281295952, "CC(remember):Tooltip.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(isPersistent)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(mutatorMutex)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new TooltipStateImpl(initialIsVisible, isPersistent, mutatorMutex);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        TooltipStateImpl tooltipStateImpl = (TooltipStateImpl) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return tooltipStateImpl;
    }

    public static /* synthetic */ TooltipState TooltipState$default(boolean z, boolean z2, MutatorMutex mutatorMutex, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return TooltipState(z, z2, mutatorMutex);
    }

    public static final TooltipState TooltipState(boolean initialIsVisible, boolean isPersistent, MutatorMutex mutatorMutex) {
        return new TooltipStateImpl(initialIsVisible, isPersistent, mutatorMutex);
    }

    public static final Modifier textVerticalPadding(Modifier $this$textVerticalPadding, boolean subheadExists, boolean actionExists) {
        if (!subheadExists && !actionExists) {
            return PaddingKt.m674paddingVpY3zN4$default($this$textVerticalPadding, 0.0f, PlainTooltipVerticalPadding, 1, null);
        }
        return PaddingKt.m676paddingqDBjuR0$default(AlignmentLineKt.m527paddingFromBaselineVpY3zN4$default($this$textVerticalPadding, HeightFromSubheadToTextFirstLine, 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TextBottomPadding, 7, null);
    }

    public static final Modifier animateTooltip(Modifier $this$animateTooltip, final Transition<Boolean> transition) {
        return ComposedModifierKt.composed($this$animateTooltip, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$$inlined$debugInspectorInfo$1
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
                $this$null.setName("animateTooltip");
                $this$null.getProperties().set("transition", Transition.this);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            private static final float invoke$lambda$1(State<Float> state) {
                return state.getValue().floatValue();
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Transition $this$animateValue$iv$iv;
                String str;
                Composer $composer2;
                Transition $this$animateValue$iv$iv2;
                boolean it;
                boolean it2;
                $composer.startReplaceGroup(-1498516085);
                ComposerKt.sourceInformation($composer, "C585@24638L747,607@25431L561:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498516085, $changed, -1, "androidx.compose.material3.animateTooltip.<anonymous> (Tooltip.kt:584)");
                }
                Transition $this$animateFloat$iv = transition;
                Function3 transitionSpec$iv = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$scale$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed2) {
                        TweenSpec tween$default;
                        $composer3.startReplaceGroup(386845748);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(386845748, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:587)");
                        }
                        if (segment.isTransitioningTo(false, true)) {
                            tween$default = AnimationSpecKt.tween$default(150, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                        } else {
                            tween$default = AnimationSpecKt.tween$default(75, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer3.endReplaceGroup();
                        return tween$default;
                    }
                };
                ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter typeConverter$iv$iv = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                int $changed2 = ($changed$iv$iv >> 9) & 112;
                boolean it3 = $this$animateFloat$iv.getCurrentState().booleanValue();
                $composer.startReplaceGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    $this$animateValue$iv$iv = $this$animateFloat$iv;
                    str = "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli";
                    ComposerKt.traceEventStart(-1553362193, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:603)");
                } else {
                    $this$animateValue$iv$iv = $this$animateFloat$iv;
                    str = "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli";
                }
                float f = it3 ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object initialValue$iv$iv = Float.valueOf(f);
                int $changed3 = ($changed$iv$iv >> 9) & 112;
                boolean it4 = $this$animateValue$iv$iv.getTargetState().booleanValue();
                $composer.startReplaceGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    $composer2 = $composer;
                    ComposerKt.traceEventStart(-1553362193, $changed3, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:603)");
                } else {
                    $composer2 = $composer;
                }
                float f2 = it4 ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceGroup();
                Object targetValue$iv$iv = Float.valueOf(f2);
                FiniteAnimationSpec<Float> animationSpec$iv$iv = transitionSpec$iv.invoke($this$animateValue$iv$iv.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112));
                State scale$delegate = TransitionKt.createTransitionAnimation($this$animateValue$iv$iv, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, typeConverter$iv$iv, "tooltip transition: scaling", $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Transition $this$animateFloat$iv2 = transition;
                Function3 transitionSpec$iv2 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$alpha$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed4) {
                        TweenSpec tween$default;
                        $composer3.startReplaceGroup(-281714272);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-281714272, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:609)");
                        }
                        if (segment.isTransitioningTo(false, true)) {
                            tween$default = AnimationSpecKt.tween$default(150, 0, EasingKt.getLinearEasing(), 2, null);
                        } else {
                            tween$default = AnimationSpecKt.tween$default(75, 0, EasingKt.getLinearEasing(), 2, null);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer3.endReplaceGroup();
                        return tween$default;
                    }
                };
                ComposerKt.sourceInformationMarkerStart($composer, -1338768149, str);
                TwoWayConverter typeConverter$iv$iv2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                int $changed4 = ($changed$iv$iv2 >> 9) & 112;
                boolean it5 = $this$animateFloat$iv2.getCurrentState().booleanValue();
                $composer.startReplaceGroup(2073045083);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    $this$animateValue$iv$iv2 = $this$animateFloat$iv2;
                    it = it5;
                    ComposerKt.traceEventStart(2073045083, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:619)");
                } else {
                    $this$animateValue$iv$iv2 = $this$animateFloat$iv2;
                    it = it5;
                }
                float f3 = it ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object initialValue$iv$iv2 = Float.valueOf(f3);
                int $changed5 = ($changed$iv$iv2 >> 9) & 112;
                boolean it6 = $this$animateValue$iv$iv2.getTargetState().booleanValue();
                $composer.startReplaceGroup(2073045083);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    it2 = it6;
                    ComposerKt.traceEventStart(2073045083, $changed5, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:619)");
                } else {
                    it2 = it6;
                }
                float f4 = it2 ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object targetValue$iv$iv2 = Float.valueOf(f4);
                FiniteAnimationSpec<Float> animationSpec$iv$iv2 = transitionSpec$iv2.invoke($this$animateValue$iv$iv2.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
                State alpha$delegate = TransitionKt.createTransitionAnimation($this$animateValue$iv$iv2, initialValue$iv$iv2, targetValue$iv$iv2, animationSpec$iv$iv2, typeConverter$iv$iv2, "tooltip transition: alpha", $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier m4323graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m4323graphicsLayerAp8cVGQ$default($this$composed, invoke$lambda$1(scale$delegate), invoke$lambda$1(scale$delegate), invoke$lambda$3(alpha$delegate), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131064, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return m4323graphicsLayerAp8cVGQ$default;
            }

            private static final float invoke$lambda$3(State<Float> state) {
                return state.getValue().floatValue();
            }
        });
    }

    public static final float getSpacingBetweenTooltipAndAnchor() {
        return SpacingBetweenTooltipAndAnchor;
    }

    public static final float getTooltipMinHeight() {
        return TooltipMinHeight;
    }

    public static final float getTooltipMinWidth() {
        return TooltipMinWidth;
    }

    public static final float getPlainTooltipMaxWidth() {
        return PlainTooltipMaxWidth;
    }

    public static final PaddingValues getPlainTooltipContentPadding() {
        return PlainTooltipContentPadding;
    }

    public static final float getRichTooltipMaxWidth() {
        return RichTooltipMaxWidth;
    }

    public static final float getRichTooltipHorizontalPadding() {
        return RichTooltipHorizontalPadding;
    }

    public static final float getHeightToSubheadFirstLine() {
        return HeightToSubheadFirstLine;
    }

    public static final float getActionLabelMinHeight() {
        return ActionLabelMinHeight;
    }

    public static final float getActionLabelBottomPadding() {
        return ActionLabelBottomPadding;
    }
}
