package androidx.compose.foundation.gestures;

import androidx.collection.MutableObjectFloatMap;
import androidx.collection.ObjectFloatMap;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u001d\u0010\t\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\n\u0012\u0004\u0012\u00020\u000b0\u0001¢\u0006\u0002\b\fH\u0007\u001a\u0017\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082\b\u001a\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0012\"\u0004\b\u0000\u0010\u0007H\u0002\u001aF\u0010\u0013\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u000f2\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0018\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0017H\u0082@¢\u0006\u0002\u0010\u0019\u001aT\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u0007*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0003H\u0007\u001a\\\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u0007*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0003H\u0007\u001a&\u0010'\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u0007H\u0087@¢\u0006\u0002\u0010)\u001aD\u0010'\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010/\u001a\u0002H\u0007H\u0082@¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020+\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u00072\u0006\u0010*\u001a\u00020+H\u0087@¢\u0006\u0002\u00102\u001a\u0014\u00103\u001a\u00020+*\u00020+2\u0006\u00104\u001a\u00020+H\u0002\u001a\u0018\u00105\u001a\u00020+\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607H\u0002\u001a\u0018\u00108\u001a\u00020+\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607H\u0002\u001a&\u00109\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u0007H\u0087@¢\u0006\u0002\u0010)\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"AlwaysDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "DEBUG", "DraggableAnchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "T", "", "builder", "Landroidx/compose/foundation/gestures/DraggableAnchorsConfig;", "", "Lkotlin/ExtensionFunctionType;", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "emptyDraggableAnchors", "Landroidx/compose/foundation/gestures/MapDraggableAnchors;", "restartable", "I", "inputs", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDraggable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "startDragImmediately", "reverseDirection", "animateTo", "targetValue", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "velocity", "", "anchoredDragScope", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "latestTarget", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;FLandroidx/compose/foundation/gestures/AnchoredDragScope;Landroidx/compose/foundation/gestures/DraggableAnchors;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToWithDecay", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceToTarget", "target", "maxValueOrNaN", "K", "Landroidx/collection/ObjectFloatMap;", "minValueOrNaN", "snapTo", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnchoredDraggableKt {
    private static final Function1<PointerInputChange, Boolean> AlwaysDrag = new Function1<PointerInputChange, Boolean>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$AlwaysDrag$1
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(PointerInputChange it) {
            return true;
        }
    };
    private static final boolean DEBUG = false;

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, boolean reverseDirection, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, Boolean.valueOf(reverseDirection), interactionSource, startDragImmediately, overscrollEffect));
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, null, interactionSource, startDragImmediately, overscrollEffect));
    }

    public static final <T> DraggableAnchors<T> DraggableAnchors(Function1<? super DraggableAnchorsConfig<T>, Unit> function1) {
        DraggableAnchorsConfig draggableAnchorsConfig = new DraggableAnchorsConfig();
        function1.invoke(draggableAnchorsConfig);
        return new MapDraggableAnchors(draggableAnchorsConfig.getAnchors$foundation_release());
    }

    public static final <T> Object snapTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object anchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnchoredDraggableKt$snapTo$2(null), continuation, 2, null);
        return anchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? anchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, float velocity, final AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
        float targetOffset = draggableAnchors.positionOf(t);
        final Ref.FloatRef prev = new Ref.FloatRef();
        prev.element = Float.isNaN(anchoredDraggableState.getOffset()) ? 0.0f : anchoredDraggableState.getOffset();
        if (!Float.isNaN(targetOffset)) {
            if (!(prev.element == targetOffset)) {
                Object animate = SuspendAnimationKt.animate(prev.element, targetOffset, velocity, anchoredDraggableState.getSnapAnimationSpec(), new Function2<Float, Float, Unit>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$2$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Float f, Float f2) {
                        invoke(f.floatValue(), f2.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float value, float velocity2) {
                        AnchoredDragScope.this.dragTo(value, velocity2);
                        prev.element = value;
                    }
                }, continuation);
                if (animate == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return animate;
                }
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object anchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnchoredDraggableKt$animateTo$4(anchoredDraggableState, null), continuation, 2, null);
        return anchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? anchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object animateToWithDecay(androidx.compose.foundation.gestures.AnchoredDraggableState<T> r8, T r9, float r10, kotlin.coroutines.Continuation<? super java.lang.Float> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1
            r0.<init>(r11)
        L19:
            r5 = r0
            java.lang.Object r11 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            float r8 = r5.F$0
            java.lang.Object r9 = r5.L$0
            kotlin.jvm.internal.Ref$FloatRef r9 = (kotlin.jvm.internal.Ref.FloatRef) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5e
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r8
            r8 = r10
            r2 = r9
            kotlin.jvm.internal.Ref$FloatRef r9 = new kotlin.jvm.internal.Ref$FloatRef
            r9.<init>()
            r9.element = r8
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2 r10 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2
            r3 = 0
            r10.<init>(r1, r8, r9, r3)
            r4 = r10
            kotlin.jvm.functions.Function4 r4 = (kotlin.jvm.functions.Function4) r4
            r5.L$0 = r9
            r5.F$0 = r8
            r10 = 1
            r5.label = r10
            r6 = 2
            r7 = 0
            java.lang.Object r10 = androidx.compose.foundation.gestures.AnchoredDraggableState.anchoredDrag$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L5e
            return r0
        L5e:
            float r10 = r9.element
            float r10 = r8 - r10
            java.lang.Float r10 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.animateToWithDecay(androidx.compose.foundation.gestures.AnchoredDraggableState, java.lang.Object, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float $this$coerceToTarget, float target) {
        if (target == 0.0f) {
            return 0.0f;
        }
        return target > 0.0f ? RangesKt.coerceAtMost($this$coerceToTarget, target) : RangesKt.coerceAtLeast($this$coerceToTarget, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|8|13|14|15))|22|6|7|8|13|14|15) */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[Catch: AnchoredDragFinishedSignal -> 0x0031, TRY_ENTER, TRY_LEAVE, TryCatch #0 {AnchoredDragFinishedSignal -> 0x0031, blocks: (B:12:0x002d, B:18:0x0037), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <I> java.lang.Object restartable(kotlin.jvm.functions.Function0<? extends I> r4, kotlin.jvm.functions.Function2<? super I, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            goto L49
        L31:
            r4 = move-exception
            goto L4a
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2 r2 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            r3 = 0
            r2.<init>(r4, r5, r3)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            r3 = 1
            r0.label = r3     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            java.lang.Object r2 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r2, r0)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            if (r2 != r1) goto L49
            return r1
        L49:
        L4a:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.restartable(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> MapDraggableAnchors<T> emptyDraggableAnchors() {
        return new MapDraggableAnchors<>(new MutableObjectFloatMap(0, 1, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K> float minValueOrNaN(ObjectFloatMap<K> objectFloatMap) {
        if (objectFloatMap.get_size() == 1) {
            return Float.NaN;
        }
        float minValue = Float.POSITIVE_INFINITY;
        ObjectFloatMap this_$iv = objectFloatMap;
        float[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                ObjectFloatMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            float value = v$iv[index$iv$iv];
                            if (value <= minValue) {
                                minValue = value;
                            }
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
            }
        }
        return minValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K> float maxValueOrNaN(ObjectFloatMap<K> objectFloatMap) {
        if (objectFloatMap.get_size() == 1) {
            return Float.NaN;
        }
        float maxValue = Float.NEGATIVE_INFINITY;
        ObjectFloatMap this_$iv = objectFloatMap;
        float[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                ObjectFloatMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            float value = v$iv[index$iv$iv];
                            if (value >= maxValue) {
                                maxValue = value;
                            }
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
            }
        }
        return maxValue;
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
