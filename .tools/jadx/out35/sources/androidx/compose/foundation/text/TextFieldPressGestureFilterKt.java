package androidx.compose.foundation.text;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextFieldPressGestureFilter.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0000¨\u0006\n"}, d2 = {"tapPressTextFieldModifier", "Landroidx/compose/ui/Modifier;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "enabled", "", "onTap", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldPressGestureFilterKt {
    public static /* synthetic */ Modifier tapPressTextFieldModifier$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return tapPressTextFieldModifier(modifier, mutableInteractionSource, z, function1);
    }

    public static final Modifier tapPressTextFieldModifier(Modifier $this$tapPressTextFieldModifier, final MutableInteractionSource interactionSource, boolean enabled, final Function1<? super Offset, Unit> function1) {
        return enabled ? ComposedModifierKt.composed$default($this$tapPressTextFieldModifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv$iv;
                Object value$iv;
                Object value$iv2;
                TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 value$iv3;
                $composer.startReplaceGroup(-102778667);
                ComposerKt.sourceInformation($composer, "C40@1611L24,41@1665L58,42@1745L27,43@1813L279,43@1777L315,52@2138L1321:TextFieldPressGestureFilter.kt#423gt5");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-102778667, $changed, -1, "androidx.compose.foundation.text.tapPressTextFieldModifier.<anonymous> (TextFieldPressGestureFilter.kt:40)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
                Object it$iv$iv = $composer.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                    $composer.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                CoroutineScope scope = wrapper$iv.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 1794542038, "CC(remember):TextFieldPressGestureFilter.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                final MutableState pressedInteraction = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                State onTapState = SnapshotStateKt.rememberUpdatedState(function1, $composer, 0);
                MutableInteractionSource mutableInteractionSource = interactionSource;
                ComposerKt.sourceInformationMarkerStart($composer, 1794546995, "CC(remember):TextFieldPressGestureFilter.kt#9igjgp");
                boolean invalid$iv = $composer.changed(interactionSource);
                final MutableInteractionSource mutableInteractionSource2 = interactionSource;
                Object it$iv2 = $composer.rememberedValue();
                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                            final MutableState<PressInteraction.Press> mutableState = pressedInteraction;
                            final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$1$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    PressInteraction.Press oldValue = (PressInteraction.Press) MutableState.this.getValue();
                                    if (oldValue == null) {
                                        return;
                                    }
                                    PressInteraction.Cancel interaction = new PressInteraction.Cancel(oldValue);
                                    if (mutableInteractionSource3 != null) {
                                        mutableInteractionSource3.tryEmit(interaction);
                                    }
                                    MutableState.this.setValue(null);
                                }
                            };
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EffectsKt.DisposableEffect(mutableInteractionSource, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv2, $composer, 0);
                Modifier.Companion companion = Modifier.INSTANCE;
                MutableInteractionSource mutableInteractionSource3 = interactionSource;
                ComposerKt.sourceInformationMarkerStart($composer, 1794558437, "CC(remember):TextFieldPressGestureFilter.kt#9igjgp");
                boolean invalid$iv2 = $composer.changedInstance(scope) | $composer.changed(interactionSource) | $composer.changed(onTapState);
                MutableInteractionSource mutableInteractionSource4 = interactionSource;
                Object it$iv3 = $composer.rememberedValue();
                if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1(scope, pressedInteraction, mutableInteractionSource4, onTapState, null);
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, mutableInteractionSource3, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return pointerInput;
            }
        }, 1, null) : $this$tapPressTextFieldModifier;
    }
}
