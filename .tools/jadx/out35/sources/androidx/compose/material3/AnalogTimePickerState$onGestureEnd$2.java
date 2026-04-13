package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationResult;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: TimePicker.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$onGestureEnd$2", f = "TimePicker.kt", i = {}, l = {789}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class AnalogTimePickerState$onGestureEnd$2 extends SuspendLambda implements Function1<Continuation<? super AnimationResult<Float, AnimationVector1D>>, Object> {
    final /* synthetic */ float $end;
    int label;
    final /* synthetic */ AnalogTimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnalogTimePickerState$onGestureEnd$2(AnalogTimePickerState analogTimePickerState, float f, Continuation<? super AnalogTimePickerState$onGestureEnd$2> continuation) {
        super(1, continuation);
        this.this$0 = analogTimePickerState;
        this.$end = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AnalogTimePickerState$onGestureEnd$2(this.this$0, this.$end, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        return ((AnalogTimePickerState$onGestureEnd$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Animatable animatable;
        Object animateTo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                animatable = this.this$0.anim;
                Float boxFloat = Boxing.boxFloat(this.$end);
                SpringSpec spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                this.label = 1;
                animateTo = animatable.animateTo(boxFloat, (r12 & 2) != 0 ? animatable.defaultSpringSpec : spring$default, (r12 & 4) != 0 ? animatable.getVelocity() : null, (r12 & 8) != 0 ? null : null, this);
                return animateTo == coroutine_suspended ? coroutine_suspended : animateTo;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
