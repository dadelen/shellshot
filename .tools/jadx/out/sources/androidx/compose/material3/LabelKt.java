package androidx.compose.material3;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.internal.BasicTooltipKt;
import androidx.compose.material3.internal.BasicTooltip_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Label.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010\b\u001a^\u0010\t\u001a\u00020\u00012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00032\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"HandleInteractions", "", "enabled", "", "state", "Landroidx/compose/material3/TooltipState;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLandroidx/compose/material3/TooltipState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "Label", "label", "Lkotlin/Function1;", "Landroidx/compose/material3/TooltipScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "isPersistent", "content", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LabelKt {
    /* JADX WARN: Type inference failed for: r2v19, types: [T, androidx.compose.runtime.MutableState] */
    public static final void Label(final Function3<? super TooltipScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, MutableInteractionSource interactionSource, boolean isPersistent, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        MutableInteractionSource mutableInteractionSource;
        boolean z;
        MutableInteractionSource interactionSource2;
        int $dirty;
        Modifier modifier3;
        SnapshotMutationPolicy snapshotMutationPolicy;
        TooltipState state;
        Object value$iv;
        Object value$iv2;
        final MutableInteractionSource interactionSource3;
        final boolean isPersistent2;
        final Modifier modifier4;
        Object value$iv3;
        Object value$iv4;
        Composer $composer2 = $composer.startRestartGroup(-544399326);
        ComposerKt.sourceInformation($composer2, "C(Label)P(3,4,1,2)74@3254L38,79@3493L33,80@3543L52,82@3646L103,88@3835L17,86@3755L249,95@4009L127:Label.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 384) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 256 : 128;
        } else {
            mutableInteractionSource = interactionSource;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = isPersistent;
        } else if (($changed & 3072) == 0) {
            z = isPersistent;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = isPersistent;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            interactionSource3 = mutableInteractionSource;
            isPersistent2 = z;
        } else {
            Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            MutableInteractionSource interactionSource4 = i3 != 0 ? null : mutableInteractionSource;
            boolean isPersistent3 = i4 != 0 ? false : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-544399326, $dirty2, -1, "androidx.compose.material3.Label (Label.kt:70)");
            }
            $composer2.startReplaceGroup(519104973);
            ComposerKt.sourceInformation($composer2, "72@3116L39");
            if (interactionSource4 == null) {
                ComposerKt.sourceInformationMarkerStart($composer2, 519105624, "CC(remember):Label.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv;
                }
                interactionSource2 = (MutableInteractionSource) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                interactionSource2 = interactionSource4;
            }
            $composer2.endReplaceGroup();
            PopupPositionProvider positionProvider = TooltipDefaults.INSTANCE.m2845rememberPlainTooltipPositionProviderkHDZbjc(0.0f, $composer2, 48, 1);
            if (isPersistent3) {
                $composer2.startReplaceGroup(-1087377900);
                ComposerKt.sourceInformation($composer2, "76@3335L29");
                ComposerKt.sourceInformationMarkerStart($composer2, 519112622, "CC(remember):Label.kt#9igjgp");
                Object it$iv2 = $composer2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    value$iv3 = new LabelStateImpl(false, false, 3, null);
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    value$iv3 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                state = (LabelStateImpl) value$iv3;
                snapshotMutationPolicy = null;
            } else {
                $dirty = $dirty2;
                modifier3 = modifier5;
                $composer2.startReplaceGroup(519114025);
                ComposerKt.sourceInformation($composer2, "77@3378L56");
                snapshotMutationPolicy = null;
                TooltipState rememberBasicTooltipState = BasicTooltipKt.rememberBasicTooltipState(false, false, new MutatorMutex(), $composer2, 0, 3);
                $composer2.endReplaceGroup();
                state = rememberBasicTooltipState;
            }
            final Ref.ObjectRef anchorBounds = new Ref.ObjectRef();
            ComposerKt.sourceInformationMarkerStart($composer2, 519117682, "CC(remember):Label.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(snapshotMutationPolicy, snapshotMutationPolicy, 2, snapshotMutationPolicy);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            anchorBounds.element = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerStart($composer2, 519119301, "CC(remember):Label.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new TooltipScopeImpl(new Function0<LayoutCoordinates>() { // from class: androidx.compose.material3.LabelKt$Label$scope$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final LayoutCoordinates invoke() {
                        return anchorBounds.element.getValue();
                    }
                });
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv4;
            }
            final TooltipScopeImpl scope = (TooltipScopeImpl) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function2 wrappedContent = ComposableLambdaKt.rememberComposableLambda(1950723216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt$Label$wrappedContent$1
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
                public final void invoke(androidx.compose.runtime.Composer r27, int r28) {
                    /*
                        Method dump skipped, instructions count: 349
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.LabelKt$Label$wrappedContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54);
            BasicTooltip_androidKt.BasicTooltipBox(positionProvider, ComposableLambdaKt.rememberComposableLambda(784196780, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt$Label$1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C88@3843L7:Label.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(784196780, $changed2, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:88)");
                    }
                    function3.invoke(scope, $composer3, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), state, modifier3, false, false, wrappedContent, $composer2, (($dirty << 6) & 7168) | 1794096, 0);
            $composer2 = $composer2;
            HandleInteractions(!isPersistent3, state, interactionSource2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource3 = interactionSource4;
            isPersistent2 = isPersistent3;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt$Label$2
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
                    LabelKt.Label(function3, modifier4, interactionSource3, isPersistent2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HandleInteractions(final boolean enabled, final TooltipState state, final MutableInteractionSource interactionSource, Composer $composer, final int $changed) {
        LabelKt$HandleInteractions$1$1 value$iv;
        Composer $composer2 = $composer.startRestartGroup(-627258109);
        ComposerKt.sourceInformation($composer2, "C(HandleInteractions)P(!1,2)110@4384L587,110@4350L621:Label.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(enabled) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-627258109, $dirty, -1, "androidx.compose.material3.HandleInteractions (Label.kt:108)");
            }
            if (enabled) {
                ComposerKt.sourceInformationMarkerStart($composer2, 354570583, "CC(remember):Label.kt#9igjgp");
                boolean z = true;
                boolean z2 = ($dirty & 896) == 256;
                if (($dirty & 112) != 32 && (($dirty & 64) == 0 || !$composer2.changedInstance(state))) {
                    z = false;
                }
                boolean invalid$iv = z2 | z;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new LabelKt$HandleInteractions$1$1(interactionSource, state, null);
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv, $composer2, ($dirty >> 6) & 14);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.LabelKt$HandleInteractions$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    LabelKt.HandleInteractions(enabled, state, interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
