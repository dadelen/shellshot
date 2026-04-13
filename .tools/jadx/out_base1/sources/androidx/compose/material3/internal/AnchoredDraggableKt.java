package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0000\u001a\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002H\u0002\u001aF\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000e2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0010H\u0082@¢\u0006\u0002\u0010\u0012\u001aH\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0002*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000\u001a0\u0010\u001e\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u001f\u001a\u0002H\u00022\b\b\u0002\u0010 \u001a\u00020!H\u0080@¢\u0006\u0002\u0010\"\u001ar\u0010#\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0002*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182H\u0010$\u001aD\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110)¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(*\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u0002H\u00020+0\u0010H\u0000\u001a&\u0010,\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u001f\u001a\u0002H\u0002H\u0080@¢\u0006\u0002\u0010-¨\u0006."}, d2 = {"DraggableAnchors", "Landroidx/compose/material3/internal/DraggableAnchors;", "T", "", "builder", "Lkotlin/Function1;", "Landroidx/compose/material3/internal/DraggableAnchorsConfig;", "", "Lkotlin/ExtensionFunctionType;", "emptyDraggableAnchors", "Landroidx/compose/material3/internal/MapDraggableAnchors;", "restartable", "I", "inputs", "Lkotlin/Function0;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDraggable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "animateTo", "targetValue", "velocity", "", "(Landroidx/compose/material3/internal/AnchoredDraggableState;Ljava/lang/Object;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "draggableAnchors", "anchors", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "size", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Pair;", "snapTo", "(Landroidx/compose/material3/internal/AnchoredDraggableState;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AnchoredDraggableKt {
    public static final <T> DraggableAnchors<T> DraggableAnchors(Function1<? super DraggableAnchorsConfig<T>, Unit> function1) {
        DraggableAnchorsConfig draggableAnchorsConfig = new DraggableAnchorsConfig();
        function1.invoke(draggableAnchorsConfig);
        return new MapDraggableAnchors(draggableAnchorsConfig.getAnchors$material3_release());
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, boolean reverseDirection, MutableInteractionSource interactionSource) {
        Modifier draggable;
        draggable = DraggableKt.draggable($this$anchoredDraggable, anchoredDraggableState.getDraggableState(), orientation, (r18 & 4) != 0 ? true : enabled, (r18 & 8) != 0 ? null : interactionSource, (r18 & 16) != 0 ? false : anchoredDraggableState.isAnimationRunning(), (r18 & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (r18 & 64) != 0 ? DraggableKt.NoOpOnDragStopped : new AnchoredDraggableKt$anchoredDraggable$1(anchoredDraggableState, null), (r18 & 128) != 0 ? false : reverseDirection);
        return draggable;
    }

    public static final <T> Object snapTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object anchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnchoredDraggableKt$snapTo$2(null), continuation, 2, null);
        return anchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? anchoredDrag$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnchoredDraggableState anchoredDraggableState, Object obj, float f, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            f = anchoredDraggableState.getLastVelocity();
        }
        return animateTo(anchoredDraggableState, obj, f, continuation);
    }

    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, T t, float velocity, Continuation<? super Unit> continuation) {
        Object anchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnchoredDraggableKt$animateTo$2(anchoredDraggableState, velocity, null), continuation, 2, null);
        return anchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? anchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|8|13|14|15))|22|6|7|8|13|14|15) */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[Catch: AnchoredDragFinishedSignal -> 0x0030, TRY_ENTER, TRY_LEAVE, TryCatch #0 {AnchoredDragFinishedSignal -> 0x0030, blocks: (B:12:0x002c, B:18:0x0036), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <I> java.lang.Object restartable(kotlin.jvm.functions.Function0<? extends I> r4, kotlin.jvm.functions.Function2<? super I, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.material3.internal.AnchoredDraggableKt$restartable$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.material3.internal.AnchoredDraggableKt$restartable$1 r0 = (androidx.compose.material3.internal.AnchoredDraggableKt$restartable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.material3.internal.AnchoredDraggableKt$restartable$1 r0 = new androidx.compose.material3.internal.AnchoredDraggableKt$restartable$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L32;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            goto L48
        L30:
            r4 = move-exception
            goto L49
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.material3.internal.AnchoredDraggableKt$restartable$2 r2 = new androidx.compose.material3.internal.AnchoredDraggableKt$restartable$2     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            r3 = 0
            r2.<init>(r4, r5, r3)     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            r3 = 1
            r0.label = r3     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            java.lang.Object r2 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r2, r0)     // Catch: androidx.compose.material3.internal.AnchoredDragFinishedSignal -> L30
            if (r2 != r1) goto L48
            return r1
        L48:
        L49:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.AnchoredDraggableKt.restartable(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> MapDraggableAnchors<T> emptyDraggableAnchors() {
        return new MapDraggableAnchors<>(MapsKt.emptyMap());
    }

    public static final <T> Modifier draggableAnchors(Modifier $this$draggableAnchors, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends DraggableAnchors<T>, ? extends T>> function2) {
        return $this$draggableAnchors.then(new DraggableAnchorsElement(anchoredDraggableState, function2, orientation));
    }
}
