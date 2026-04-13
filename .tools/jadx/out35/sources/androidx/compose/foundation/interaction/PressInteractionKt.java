package androidx.compose.foundation.interaction;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PressInteraction.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"collectIsPressedAsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PressInteractionKt {
    public static final State<Boolean> collectIsPressedAsState(InteractionSource $this$collectIsPressedAsState, Composer $composer, int $changed) {
        Object value$iv;
        PressInteractionKt$collectIsPressedAsState$1$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -1692965168, "C(collectIsPressedAsState)84@3016L34,85@3076L504,85@3055L525:PressInteraction.kt#ywyzhk");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1692965168, $changed, -1, "androidx.compose.foundation.interaction.collectIsPressedAsState (PressInteraction.kt:83)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1614367837, "CC(remember):PressInteraction.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState isPressed = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1614370227, "CC(remember):PressInteraction.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed($this$collectIsPressedAsState)) || ($changed & 6) == 4;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new PressInteractionKt$collectIsPressedAsState$1$1($this$collectIsPressedAsState, isPressed, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect($this$collectIsPressedAsState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return isPressed;
    }
}
