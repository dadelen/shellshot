package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0017¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Landroidx/compose/material/DefaultButtonElevation;", "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "disabledElevation", "hoveredElevation", "focusedElevation", "(FFFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "elevation", "Landroidx/compose/runtime/State;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(ZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class DefaultButtonElevation implements ButtonElevation {
    private final float defaultElevation;
    private final float disabledElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ DefaultButtonElevation(float f, float f2, float f3, float f4, float f5, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5);
    }

    private DefaultButtonElevation(float defaultElevation, float pressedElevation, float disabledElevation, float hoveredElevation, float focusedElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.disabledElevation = disabledElevation;
        this.hoveredElevation = hoveredElevation;
        this.focusedElevation = focusedElevation;
    }

    @Override // androidx.compose.material.ButtonElevation
    public State<Dp> elevation(boolean enabled, InteractionSource interactionSource, Composer $composer, int $changed) {
        Object value$iv;
        DefaultButtonElevation$elevation$1$1 value$iv2;
        float target;
        Object value$iv3;
        Animatable animatable;
        DefaultButtonElevation$elevation$2$1 value$iv4;
        $composer.startReplaceGroup(-1588756907);
        ComposerKt.sourceInformation($composer, "C(elevation)507@20573L46,508@20662L1077,508@20628L1111,549@22188L51,551@22272L819,551@22249L842:Button.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1588756907, $changed, -1, "androidx.compose.material.DefaultButtonElevation.elevation (Button.kt:506)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1976547373, "CC(remember):Button.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt.mutableStateListOf();
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SnapshotStateList interactions = (SnapshotStateList) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1976551252, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv = ((($changed & 112) ^ 48) > 32 && $composer.changed(interactionSource)) || ($changed & 48) == 32;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new DefaultButtonElevation$elevation$1$1(interactionSource, interactions, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, ($changed >> 3) & 14);
        Interaction interaction = (Interaction) CollectionsKt.lastOrNull((List) interactions);
        if (!enabled) {
            target = this.disabledElevation;
        } else if (interaction instanceof PressInteraction.Press) {
            target = this.pressedElevation;
        } else if (interaction instanceof HoverInteraction.Enter) {
            target = this.hoveredElevation;
        } else {
            target = interaction instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1976599058, "CC(remember):Button.kt#9igjgp");
        Object it$iv3 = $composer.rememberedValue();
        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = new Animatable(Dp.m6624boximpl(target), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        Animatable animatable2 = (Animatable) value$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Dp m6624boximpl = Dp.m6624boximpl(target);
        ComposerKt.sourceInformationMarkerStart($composer, 1976602514, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(animatable2) | $composer.changed(target) | (((($changed & 14) ^ 6) > 4 && $composer.changed(enabled)) || ($changed & 6) == 4) | (((($changed & 896) ^ 384) > 256 && $composer.changed(this)) || ($changed & 384) == 256) | $composer.changedInstance(interaction);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            animatable = animatable2;
            value$iv4 = new DefaultButtonElevation$elevation$2$1(animatable, target, enabled, this, interaction, null);
            $composer.updateRememberedValue(value$iv4);
        } else {
            animatable = animatable2;
            value$iv4 = it$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(m6624boximpl, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer, 0);
        State<Dp> asState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return asState;
    }
}
