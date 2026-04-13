package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Draggable2D.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0001H\u0007\u001a!\u0010\u0015\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0001H\u0007¢\u0006\u0002\u0010\u0016\u001a\u0088\u0001\u0010\u0017\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001b2#\b\u0002\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u00012#\b\u0002\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00060\u00012\b\b\u0002\u0010!\u001a\u00020\u001bH\u0007\u001aÃ\u0001\u0010\u0017\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001b2>\b\u0002\u0010\u001f\u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\f2>\b\u0002\u0010 \u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\f2\b\b\u0002\u0010!\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010\"\")\u0010\u0000\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"F\u0010\u0007\u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r\")\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"F\u0010\u0011\u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006#"}, d2 = {"NoOpOnDragStart", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startedPosition", "", "NoOpOnDragStarted", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "NoOpOnDragStop", "Landroidx/compose/ui/unit/Velocity;", "velocity", "NoOpOnDragStopped", "Draggable2DState", "Landroidx/compose/foundation/gestures/Draggable2DState;", "onDelta", "rememberDraggable2DState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/Draggable2DState;", "draggable2D", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "onDragStarted", "onDragStopped", "reverseDirection", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/Draggable2DState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Z)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Draggable2DKt {
    private static final Function3<CoroutineScope, Offset, Continuation<? super Unit>, Object> NoOpOnDragStarted = new Draggable2DKt$NoOpOnDragStarted$1(null);
    private static final Function1<Offset, Unit> NoOpOnDragStart = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$NoOpOnDragStart$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
            m425invokek4lQ0M(offset.getPackedValue());
            return Unit.INSTANCE;
        }

        /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m425invokek4lQ0M(long it) {
        }
    };
    private static final Function3<CoroutineScope, Velocity, Continuation<? super Unit>, Object> NoOpOnDragStopped = new Draggable2DKt$NoOpOnDragStopped$1(null);
    private static final Function1<Velocity, Unit> NoOpOnDragStop = new Function1<Velocity, Unit>() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$NoOpOnDragStop$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Velocity velocity) {
            m427invokeTH1AsA0(velocity.getPackedValue());
            return Unit.INSTANCE;
        }

        /* renamed from: invoke-TH1AsA0, reason: not valid java name */
        public final void m427invokeTH1AsA0(long it) {
        }
    };

    public static final Draggable2DState Draggable2DState(Function1<? super Offset, Unit> function1) {
        return new DefaultDraggable2DState(function1);
    }

    public static final Draggable2DState rememberDraggable2DState(Function1<? super Offset, Unit> function1, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1150277615, "C(rememberDraggable2DState)121@4913L29,122@4954L63:Draggable2D.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1150277615, $changed, -1, "androidx.compose.foundation.gestures.rememberDraggable2DState (Draggable2D.kt:120)");
        }
        final State onDeltaState = SnapshotStateKt.rememberUpdatedState(function1, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -428493944, "CC(remember):Draggable2D.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = Draggable2DState(new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.Draggable2DKt$rememberDraggable2DState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m429invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m429invokek4lQ0M(long it) {
                    onDeltaState.getValue().invoke(Offset.m3911boximpl(it));
                }
            });
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        Draggable2DState draggable2DState = (Draggable2DState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return draggable2DState;
    }

    public static final Modifier draggable2D(Modifier $this$draggable2D, Draggable2DState state, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, Function1<? super Offset, Unit> function1, Function1<? super Velocity, Unit> function12, boolean reverseDirection) {
        return $this$draggable2D.then(new Draggable2DElement(state, enabled, interactionSource, startDragImmediately, function1, function12, reverseDirection));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use overload without the suspend onDragStarted onDragStopped and callbacks")
    public static final /* synthetic */ Modifier draggable2D(Modifier $this$draggable2D, Draggable2DState state, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, Function3 onDragStarted, Function3 onDragStopped, boolean reverseDirection) {
        return $this$draggable2D.then(new Draggable2DCompatElement(state, enabled, interactionSource, startDragImmediately, onDragStarted, onDragStopped, reverseDirection));
    }
}
