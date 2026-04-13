package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FloatingActionButton.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0004\b\u0014\u0010\rJ\u0015\u0010\u0015\u001a\u00020\u0003H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/material3/FloatingActionButtonElevation;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "animateElevation", "Landroidx/compose/runtime/State;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "equals", "", "other", "hashCode", "", "shadowElevation", "shadowElevation$material3_release", "tonalElevation", "tonalElevation-D9Ej5fM$material3_release", "()F", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public class FloatingActionButtonElevation {
    public static final int $stable = 0;
    private final float defaultElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ FloatingActionButtonElevation(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private FloatingActionButtonElevation(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.focusedElevation = focusedElevation;
        this.hoveredElevation = hoveredElevation;
    }

    public final State<Dp> shadowElevation$material3_release(InteractionSource interactionSource, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -424810125, "C(shadowElevation)517@24051L55:FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-424810125, $changed, -1, "androidx.compose.material3.FloatingActionButtonElevation.shadowElevation (FloatingActionButton.kt:516)");
        }
        State<Dp> animateElevation = animateElevation(interactionSource, $composer, ($changed & 14) | ($changed & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return animateElevation;
    }

    /* renamed from: tonalElevation-D9Ej5fM$material3_release, reason: not valid java name and from getter */
    public final float getDefaultElevation() {
        return this.defaultElevation;
    }

    private final State<Dp> animateElevation(InteractionSource interactionSource, Composer $composer, int $changed) {
        Object value$iv;
        FloatingActionButtonElevation$animateElevation$1$1 value$iv2;
        FloatingActionButtonElevation$animateElevation$2$1 value$iv3;
        ComposerKt.sourceInformationMarkerStart($composer, -1845106002, "C(animateElevation)527@24330L345,536@24706L276,536@24685L297,545@25026L1282,545@24992L1316:FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1845106002, $changed, -1, "androidx.compose.material3.FloatingActionButtonElevation.animateElevation (FloatingActionButton.kt:525)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1492982391, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(interactionSource)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new FloatingActionButtonElevationAnimatable(this.defaultElevation, this.pressedElevation, this.hoveredElevation, this.focusedElevation, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        FloatingActionButtonElevationAnimatable animatable = (FloatingActionButtonElevationAnimatable) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1492994354, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(animatable) | (((($changed & 112) ^ 48) > 32 && $composer.changed(this)) || ($changed & 48) == 32);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new FloatingActionButtonElevation$animateElevation$1$1(animatable, this, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(this, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, ($changed >> 3) & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 1493005600, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv3 = $composer.changedInstance(animatable) | (((($changed & 14) ^ 6) > 4 && $composer.changed(interactionSource)) || ($changed & 6) == 4);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = new FloatingActionButtonElevation$animateElevation$2$1(interactionSource, animatable, null);
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3, $composer, $changed & 14);
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
        if (other == null || !(other instanceof FloatingActionButtonElevation) || !Dp.m6631equalsimpl0(this.defaultElevation, ((FloatingActionButtonElevation) other).defaultElevation) || !Dp.m6631equalsimpl0(this.pressedElevation, ((FloatingActionButtonElevation) other).pressedElevation) || !Dp.m6631equalsimpl0(this.focusedElevation, ((FloatingActionButtonElevation) other).focusedElevation)) {
            return false;
        }
        return Dp.m6631equalsimpl0(this.hoveredElevation, ((FloatingActionButtonElevation) other).hoveredElevation);
    }

    public int hashCode() {
        int result = Dp.m6632hashCodeimpl(this.defaultElevation);
        return (((((result * 31) + Dp.m6632hashCodeimpl(this.pressedElevation)) * 31) + Dp.m6632hashCodeimpl(this.focusedElevation)) * 31) + Dp.m6632hashCodeimpl(this.hoveredElevation);
    }
}
