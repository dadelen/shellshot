package androidx.compose.material3;

import androidx.collection.IntList;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: TimePicker.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u000e\u0010*\u001a\u00020+H\u0086@¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0006H\u0002J\b\u0010/\u001a\u00020\u0018H\u0002J\u0010\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006H\u0002J\u000e\u00102\u001a\u00020+H\u0086@¢\u0006\u0002\u0010,J \u00103\u001a\u00020+2\u0006\u00101\u001a\u00020\u00062\b\b\u0002\u00104\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u00105J\b\u00106\u001a\u00020+H\u0002J\f\u00107\u001a\u00020\u0006*\u00020\u0006H\u0002J\f\u00108\u001a\u00020\u0010*\u00020\u0006H\u0002J\f\u00109\u001a\u00020\u0010*\u00020\u0006H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\u00020\u0018X\u0096\u000f¢\u0006\f\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u00020\u0018X\u0096\u000f¢\u0006\f\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR$\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R\u000e\u0010!\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020%X\u0096\u000fø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006:"}, d2 = {"Landroidx/compose/material3/AnalogTimePickerState;", "Landroidx/compose/material3/TimePickerState;", "state", "(Landroidx/compose/material3/TimePickerState;)V", "anim", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "clockFaceValues", "Landroidx/collection/IntList;", "getClockFaceValues", "()Landroidx/collection/IntList;", "currentAngle", "getCurrentAngle", "()F", "value", "", "hour", "getHour", "()I", "setHour", "(I)V", "hourAngle", "is24hour", "", "()Z", "set24hour", "(Z)V", "isAfternoon", "setAfternoon", "minute", "getMinute", "setMinute", "minuteAngle", "mutex", "Landroidx/compose/foundation/MutatorMutex;", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "getSelection-yecRtBI", "setSelection-6_8s6DQ", "getState", "()Landroidx/compose/material3/TimePickerState;", "animateToCurrent", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endValueForAnimation", "new", "isUpdated", "offsetAngle", "angle", "onGestureEnd", "rotateTo", "animate", "(FZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBaseStateMinute", "normalize", "toHour", "toMinute", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AnalogTimePickerState implements TimePickerState {
    public static final int $stable = 8;
    private Animatable<Float, AnimationVector1D> anim;
    private float hourAngle;
    private float minuteAngle;
    private final MutatorMutex mutex = new MutatorMutex();
    private final TimePickerState state;

    @Override // androidx.compose.material3.TimePickerState
    /* renamed from: getSelection-yecRtBI, reason: not valid java name */
    public int mo1764getSelectionyecRtBI() {
        return this.state.mo1764getSelectionyecRtBI();
    }

    @Override // androidx.compose.material3.TimePickerState
    public boolean is24hour() {
        return this.state.is24hour();
    }

    @Override // androidx.compose.material3.TimePickerState
    public boolean isAfternoon() {
        return this.state.isAfternoon();
    }

    @Override // androidx.compose.material3.TimePickerState
    public void set24hour(boolean z) {
        this.state.set24hour(z);
    }

    @Override // androidx.compose.material3.TimePickerState
    public void setAfternoon(boolean z) {
        this.state.setAfternoon(z);
    }

    @Override // androidx.compose.material3.TimePickerState
    /* renamed from: setSelection-6_8s6DQ, reason: not valid java name */
    public void mo1765setSelection6_8s6DQ(int i) {
        this.state.mo1765setSelection6_8s6DQ(i);
    }

    public AnalogTimePickerState(TimePickerState state) {
        this.state = state;
        this.hourAngle = ((this.state.getHour() % 12) * 0.5235988f) - 1.5707964f;
        this.minuteAngle = (this.state.getMinute() * 0.10471976f) - 1.5707964f;
        this.anim = AnimatableKt.Animatable$default(this.hourAngle, 0.0f, 2, null);
    }

    public final TimePickerState getState() {
        return this.state;
    }

    public final float getCurrentAngle() {
        return this.anim.getValue().floatValue();
    }

    public final Object animateToCurrent(Continuation<? super Unit> continuation) {
        float end;
        if (!isUpdated()) {
            return Unit.INSTANCE;
        }
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) {
            end = endValueForAnimation(this.hourAngle);
        } else {
            end = endValueForAnimation(this.minuteAngle);
        }
        Object mutate = this.mutex.mutate(MutatePriority.PreventUserInput, new AnalogTimePickerState$animateToCurrent$2(this, end, null), continuation);
        return mutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate : Unit.INSTANCE;
    }

    private final boolean isUpdated() {
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) {
            if (normalize(this.anim.getTargetValue().floatValue()) == normalize(this.hourAngle)) {
                return false;
            }
        }
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI())) {
            if (normalize(this.anim.getTargetValue().floatValue()) == normalize(this.minuteAngle)) {
                return false;
            }
        }
        return true;
    }

    public final IntList getClockFaceValues() {
        IntList intList;
        IntList intList2;
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI())) {
            intList2 = TimePickerKt.Minutes;
            return intList2;
        }
        intList = TimePickerKt.Hours;
        return intList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float endValueForAnimation(float r4) {
        float diff = this.anim.getValue().floatValue() - r4;
        while (diff > 3.1415927f) {
            diff -= 6.2831855f;
        }
        while (diff <= -3.1415927f) {
            diff += 6.2831855f;
        }
        return this.anim.getValue().floatValue() - diff;
    }

    public final Object onGestureEnd(Continuation<? super Unit> continuation) {
        float f;
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) {
            f = this.hourAngle;
        } else {
            f = this.minuteAngle;
        }
        float end = endValueForAnimation(f);
        Object mutate = this.mutex.mutate(MutatePriority.PreventUserInput, new AnalogTimePickerState$onGestureEnd$2(this, end, null), continuation);
        return mutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object rotateTo$default(AnalogTimePickerState analogTimePickerState, float f, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return analogTimePickerState.rotateTo(f, z, continuation);
    }

    public final Object rotateTo(float angle, boolean animate, Continuation<? super Unit> continuation) {
        Object mutate = this.mutex.mutate(MutatePriority.UserInput, new AnalogTimePickerState$rotateTo$2(this, angle, animate, null), continuation);
        return mutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.TimePickerState
    public int getMinute() {
        return this.state.getMinute();
    }

    @Override // androidx.compose.material3.TimePickerState
    public void setMinute(int value) {
        this.minuteAngle = (value * 0.10471976f) - 1.5707964f;
        this.state.setMinute(value);
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI())) {
            this.anim = AnimatableKt.Animatable$default(this.minuteAngle, 0.0f, 2, null);
        }
        updateBaseStateMinute();
    }

    private final void updateBaseStateMinute() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1 observer$iv = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            this.state.setMinute(getMinute());
            Unit unit = Unit.INSTANCE;
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, observer$iv);
        }
    }

    @Override // androidx.compose.material3.TimePickerState
    public int getHour() {
        return this.state.getHour();
    }

    @Override // androidx.compose.material3.TimePickerState
    public void setHour(int value) {
        this.hourAngle = ((value % 12) * 0.5235988f) - 1.5707964f;
        this.state.setHour(value);
        if (TimePickerSelectionMode.m2751equalsimpl0(mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) {
            this.anim = AnimatableKt.Animatable$default(this.hourAngle, 0.0f, 2, null);
        }
    }

    private final float normalize(float $this$normalize) {
        double normalizedAngle = $this$normalize % 6.283185307179586d;
        if (normalizedAngle < 0.0d) {
            normalizedAngle += 6.283185307179586d;
        }
        return (float) normalizedAngle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toHour(float $this$toHour) {
        double totalOffset = 0.2617994f + 1.5707963267948966d;
        return ((int) (($this$toHour + totalOffset) / 0.5235987901687622d)) % 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toMinute(float $this$toMinute) {
        double totalOffset = 0.05235988f + 1.5707963267948966d;
        return ((int) (($this$toMinute + totalOffset) / 0.10471975803375244d)) % 60;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float offsetAngle(float angle) {
        float ret = 1.5707964f + angle;
        return ret < 0.0f ? 6.2831855f + ret : ret;
    }
}
