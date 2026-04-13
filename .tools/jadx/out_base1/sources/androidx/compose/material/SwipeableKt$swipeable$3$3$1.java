package androidx.compose.material;

import androidx.compose.ui.unit.Density;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Swipeable.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.SwipeableKt$swipeable$3$3$1", f = "Swipeable.kt", i = {}, l = {617}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SwipeableKt$swipeable$3$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<Float, T> $anchors;
    final /* synthetic */ Density $density;
    final /* synthetic */ ResistanceConfig $resistance;
    final /* synthetic */ SwipeableState<T> $state;
    final /* synthetic */ Function2<T, T, ThresholdConfig> $thresholds;
    final /* synthetic */ float $velocityThreshold;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SwipeableKt$swipeable$3$3$1(SwipeableState<T> swipeableState, Map<Float, ? extends T> map, ResistanceConfig resistanceConfig, Density density, Function2<? super T, ? super T, ? extends ThresholdConfig> function2, float f, Continuation<? super SwipeableKt$swipeable$3$3$1> continuation) {
        super(2, continuation);
        this.$state = swipeableState;
        this.$anchors = map;
        this.$resistance = resistanceConfig;
        this.$density = density;
        this.$thresholds = function2;
        this.$velocityThreshold = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SwipeableKt$swipeable$3$3$1(this.$state, this.$anchors, this.$resistance, this.$density, this.$thresholds, this.$velocityThreshold, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SwipeableKt$swipeable$3$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Map oldAnchors = this.$state.getAnchors$material_release();
                this.$state.setAnchors$material_release(this.$anchors);
                this.$state.setResistance$material_release(this.$resistance);
                SwipeableState<T> swipeableState = this.$state;
                final Map<Float, T> map = this.$anchors;
                final Function2<T, T, ThresholdConfig> function2 = this.$thresholds;
                final Density density = this.$density;
                swipeableState.setThresholds$material_release(new Function2<Float, Float, Float>() { // from class: androidx.compose.material.SwipeableKt$swipeable$3$3$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Float invoke(Float f, Float f2) {
                        return invoke(f.floatValue(), f2.floatValue());
                    }

                    public final Float invoke(float a, float b) {
                        Object from = MapsKt.getValue(map, Float.valueOf(a));
                        Object to = MapsKt.getValue(map, Float.valueOf(b));
                        ThresholdConfig $this$invoke_u24lambda_u240 = function2.invoke(from, to);
                        return Float.valueOf($this$invoke_u24lambda_u240.computeThreshold(density, a, b));
                    }
                });
                Density $this$invokeSuspend_u24lambda_u240 = this.$density;
                this.$state.setVelocityThreshold$material_release($this$invokeSuspend_u24lambda_u240.mo364toPx0680j_4(this.$velocityThreshold));
                this.label = 1;
                if (this.$state.processNewAnchors$material_release(oldAnchors, this.$anchors, this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
