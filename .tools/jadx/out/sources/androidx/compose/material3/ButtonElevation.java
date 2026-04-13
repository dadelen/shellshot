package androidx.compose.material3;

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
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0016\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/ButtonElevation;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "disabledElevation", "(FFFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "animateElevation", "Landroidx/compose/runtime/State;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(ZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "equals", "other", "hashCode", "", "shadowElevation", "shadowElevation$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ButtonElevation {
    public static final int $stable = 0;
    private final float defaultElevation;
    private final float disabledElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ ButtonElevation(float f, float f2, float f3, float f4, float f5, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5);
    }

    private ButtonElevation(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float disabledElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.focusedElevation = focusedElevation;
        this.hoveredElevation = hoveredElevation;
        this.disabledElevation = disabledElevation;
    }

    public final State<Dp> shadowElevation$material3_release(boolean enabled, InteractionSource interactionSource, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2045116089, "C(shadowElevation)931@43193L74:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2045116089, $changed, -1, "androidx.compose.material3.ButtonElevation.shadowElevation (Button.kt:930)");
        }
        State<Dp> animateElevation = animateElevation(enabled, interactionSource, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateElevation;
    }

    private final State<Dp> animateElevation(boolean enabled, InteractionSource interactionSource, Composer $composer, int $changed) {
        Object value$iv;
        ButtonElevation$animateElevation$1$1 value$iv2;
        float target;
        Object value$iv3;
        Animatable animatable;
        ButtonElevation$animateElevation$2$1 value$iv4;
        ComposerKt.sourceInformationMarkerStart($composer, -1312510462, "C(animateElevation)939@43442L46,940@43531L1077,940@43497L1111,982@45105L51,984@45189L863,984@45166L886:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1312510462, $changed, -1, "androidx.compose.material3.ButtonElevation.animateElevation (Button.kt:938)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -842871664, "CC(remember):Button.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt.mutableStateListOf();
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SnapshotStateList interactions = (SnapshotStateList) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -842867785, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv = ((($changed & 112) ^ 48) > 32 && $composer.changed(interactionSource)) || ($changed & 48) == 32;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new ButtonElevation$animateElevation$1$1(interactionSource, interactions, null);
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
        } else {
            target = interaction instanceof HoverInteraction.Enter ? this.hoveredElevation : interaction instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
        }
        ComposerKt.sourceInformationMarkerStart($composer, -842818443, "CC(remember):Button.kt#9igjgp");
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
        ComposerKt.sourceInformationMarkerStart($composer, -842814943, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(animatable2) | $composer.changed(target) | (((($changed & 14) ^ 6) > 4 && $composer.changed(enabled)) || ($changed & 6) == 4) | (((($changed & 896) ^ 384) > 256 && $composer.changed(this)) || ($changed & 384) == 256) | $composer.changedInstance(interaction);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            animatable = animatable2;
            value$iv4 = new ButtonElevation$animateElevation$2$1(animatable, target, enabled, this, interaction, null);
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
        ComposerKt.sourceInformationMarkerEnd($composer);
        return asState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof ButtonElevation) && Dp.m6631equalsimpl0(this.defaultElevation, ((ButtonElevation) other).defaultElevation) && Dp.m6631equalsimpl0(this.pressedElevation, ((ButtonElevation) other).pressedElevation) && Dp.m6631equalsimpl0(this.focusedElevation, ((ButtonElevation) other).focusedElevation) && Dp.m6631equalsimpl0(this.hoveredElevation, ((ButtonElevation) other).hoveredElevation) && Dp.m6631equalsimpl0(this.disabledElevation, ((ButtonElevation) other).disabledElevation)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Dp.m6632hashCodeimpl(this.defaultElevation);
        return (((((((result * 31) + Dp.m6632hashCodeimpl(this.pressedElevation)) * 31) + Dp.m6632hashCodeimpl(this.focusedElevation)) * 31) + Dp.m6632hashCodeimpl(this.hoveredElevation)) * 31) + Dp.m6632hashCodeimpl(this.disabledElevation);
    }
}
