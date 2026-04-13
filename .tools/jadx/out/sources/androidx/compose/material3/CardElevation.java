package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
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

/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0001¢\u0006\u0004\b\u0017\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/material3/CardElevation;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "(FFFFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "animateElevation", "Landroidx/compose/runtime/State;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(ZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "equals", "other", "hashCode", "", "shadowElevation", "shadowElevation$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CardElevation {
    public static final int $stable = 0;
    private final float defaultElevation;
    private final float disabledElevation;
    private final float draggedElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ CardElevation(float f, float f2, float f3, float f4, float f5, float f6, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6);
    }

    private CardElevation(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.focusedElevation = focusedElevation;
        this.hoveredElevation = hoveredElevation;
        this.draggedElevation = draggedElevation;
        this.disabledElevation = disabledElevation;
    }

    public final State<Dp> shadowElevation$material3_release(boolean enabled, InteractionSource interactionSource, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceGroup(-1763481333);
        ComposerKt.sourceInformation($composer, "C(shadowElevation)663@29753L74:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1763481333, $changed, -1, "androidx.compose.material3.CardElevation.shadowElevation (Card.kt:659)");
        }
        $composer.startReplaceGroup(-734838460);
        ComposerKt.sourceInformation($composer, "661@29682L45");
        if (interactionSource == null) {
            ComposerKt.sourceInformationMarkerStart($composer, -734836858, "CC(remember):Card.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Dp.m6624boximpl(this.defaultElevation), null, 2, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MutableState mutableState = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return mutableState;
        }
        $composer.endReplaceGroup();
        State<Dp> animateElevation = animateElevation(enabled, interactionSource, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return animateElevation;
    }

    private final State<Dp> animateElevation(boolean enabled, InteractionSource interactionSource, Composer $composer, int $changed) {
        Object value$iv;
        CardElevation$animateElevation$1$1 value$iv2;
        float target;
        Object value$iv3;
        Animatable animatable;
        CardElevation$animateElevation$2$1 value$iv4;
        ComposerKt.sourceInformationMarkerStart($composer, -1421890746, "C(animateElevation)671@30002L46,672@30091L1473,672@30057L1507,724@32126L51,726@32210L936,726@32187L959:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1421890746, $changed, -1, "androidx.compose.material3.CardElevation.animateElevation (Card.kt:670)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1719589842, "CC(remember):Card.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt.mutableStateListOf();
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SnapshotStateList interactions = (SnapshotStateList) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1719585567, "CC(remember):Card.kt#9igjgp");
        boolean invalid$iv = ((($changed & 112) ^ 48) > 32 && $composer.changed(interactionSource)) || ($changed & 48) == 32;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new CardElevation$animateElevation$1$1(interactionSource, interactions, null);
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
            target = interaction instanceof HoverInteraction.Enter ? this.hoveredElevation : interaction instanceof FocusInteraction.Focus ? this.focusedElevation : interaction instanceof DragInteraction.Start ? this.draggedElevation : this.defaultElevation;
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1719521869, "CC(remember):Card.kt#9igjgp");
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
        ComposerKt.sourceInformationMarkerStart($composer, -1719518296, "CC(remember):Card.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(animatable2) | $composer.changed(target) | (((($changed & 14) ^ 6) > 4 && $composer.changed(enabled)) || ($changed & 6) == 4) | (((($changed & 896) ^ 384) > 256 && $composer.changed(this)) || ($changed & 384) == 256) | $composer.changedInstance(interaction);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            animatable = animatable2;
            value$iv4 = new CardElevation$animateElevation$2$1(animatable, target, enabled, this, interaction, null);
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
        if (other != null && (other instanceof CardElevation) && Dp.m6631equalsimpl0(this.defaultElevation, ((CardElevation) other).defaultElevation) && Dp.m6631equalsimpl0(this.pressedElevation, ((CardElevation) other).pressedElevation) && Dp.m6631equalsimpl0(this.focusedElevation, ((CardElevation) other).focusedElevation) && Dp.m6631equalsimpl0(this.hoveredElevation, ((CardElevation) other).hoveredElevation) && Dp.m6631equalsimpl0(this.disabledElevation, ((CardElevation) other).disabledElevation)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Dp.m6632hashCodeimpl(this.defaultElevation);
        return (((((((result * 31) + Dp.m6632hashCodeimpl(this.pressedElevation)) * 31) + Dp.m6632hashCodeimpl(this.focusedElevation)) * 31) + Dp.m6632hashCodeimpl(this.hoveredElevation)) * 31) + Dp.m6632hashCodeimpl(this.disabledElevation);
    }
}
