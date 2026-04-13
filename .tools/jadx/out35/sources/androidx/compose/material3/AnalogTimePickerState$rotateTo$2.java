package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
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
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$rotateTo$2", f = "TimePicker.kt", i = {}, l = {803, 806}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class AnalogTimePickerState$rotateTo$2 extends SuspendLambda implements Function1<Continuation<? super Object>, Object> {
    final /* synthetic */ float $angle;
    final /* synthetic */ boolean $animate;
    int label;
    final /* synthetic */ AnalogTimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnalogTimePickerState$rotateTo$2(AnalogTimePickerState analogTimePickerState, float f, boolean z, Continuation<? super AnalogTimePickerState$rotateTo$2> continuation) {
        super(1, continuation);
        this.this$0 = analogTimePickerState;
        this.$angle = f;
        this.$animate = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AnalogTimePickerState$rotateTo$2(this.this$0, this.$angle, this.$animate, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Object> continuation) {
        return invoke2((Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<Object> continuation) {
        return ((AnalogTimePickerState$rotateTo$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int minute;
        float f;
        int minute2;
        float offsetAngle;
        float endAngle;
        Animatable animatable;
        Object animateTo;
        Animatable animatable2;
        float offsetAngle2;
        int hour;
        float f2;
        int hour2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                boolean m2751equalsimpl0 = TimePickerSelectionMode.m2751equalsimpl0(this.this$0.mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI());
                AnalogTimePickerState analogTimePickerState = this.this$0;
                if (m2751equalsimpl0) {
                    hour = this.this$0.toHour(this.$angle);
                    analogTimePickerState.hourAngle = (hour % 12) * 0.5235988f;
                    TimePickerState state = this.this$0.getState();
                    AnalogTimePickerState analogTimePickerState2 = this.this$0;
                    f2 = this.this$0.hourAngle;
                    hour2 = analogTimePickerState2.toHour(f2);
                    state.setHour((hour2 % 12) + (this.this$0.isAfternoon() ? 12 : 0));
                } else {
                    minute = this.this$0.toMinute(this.$angle);
                    analogTimePickerState.minuteAngle = minute * 0.10471976f;
                    TimePickerState state2 = this.this$0.getState();
                    AnalogTimePickerState analogTimePickerState3 = this.this$0;
                    f = this.this$0.minuteAngle;
                    minute2 = analogTimePickerState3.toMinute(f);
                    state2.setMinute(minute2);
                }
                boolean z = this.$animate;
                AnalogTimePickerState analogTimePickerState4 = this.this$0;
                if (!z) {
                    animatable2 = analogTimePickerState4.anim;
                    offsetAngle2 = this.this$0.offsetAngle(this.$angle);
                    this.label = 1;
                    if (animatable2.snapTo(Boxing.boxFloat(offsetAngle2), this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                } else {
                    offsetAngle = this.this$0.offsetAngle(this.$angle);
                    endAngle = analogTimePickerState4.endValueForAnimation(offsetAngle);
                    animatable = this.this$0.anim;
                    Float boxFloat = Boxing.boxFloat(endAngle);
                    SpringSpec spring$default = AnimationSpecKt.spring$default(1.0f, 700.0f, null, 4, null);
                    this.label = 2;
                    animateTo = animatable.animateTo(boxFloat, (r12 & 2) != 0 ? animatable.defaultSpringSpec : spring$default, (r12 & 4) != 0 ? animatable.getVelocity() : null, (r12 & 8) != 0 ? null : null, this);
                    return animateTo == coroutine_suspended ? coroutine_suspended : animateTo;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
