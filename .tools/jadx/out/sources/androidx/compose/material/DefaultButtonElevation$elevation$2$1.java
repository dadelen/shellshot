package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.DefaultButtonElevation$elevation$2$1", f = "Button.kt", i = {}, l = {556, 564}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DefaultButtonElevation$elevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ DefaultButtonElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultButtonElevation$elevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, DefaultButtonElevation defaultButtonElevation, Interaction interaction, Continuation<? super DefaultButtonElevation$elevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = defaultButtonElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultButtonElevation$elevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultButtonElevation$elevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        float f;
        float f2;
        float f3;
        DefaultButtonElevation$elevation$2$1 defaultButtonElevation$elevation$2$1;
        DefaultButtonElevation$elevation$2$1 defaultButtonElevation$elevation$2$12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!Dp.m6631equalsimpl0(this.$animatable.getTargetValue().m6640unboximpl(), this.$target)) {
                    boolean z = this.$enabled;
                    Animatable<Dp, AnimationVector1D> animatable = this.$animatable;
                    if (!z) {
                        this.label = 1;
                        if (animatable.snapTo(Dp.m6624boximpl(this.$target), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultButtonElevation$elevation$2$12 = this;
                    } else {
                        float m6640unboximpl = animatable.getTargetValue().m6640unboximpl();
                        f = this.this$0.pressedElevation;
                        FocusInteraction.Focus focus = null;
                        if (Dp.m6631equalsimpl0(m6640unboximpl, f)) {
                            focus = new PressInteraction.Press(Offset.INSTANCE.m3938getZeroF1C5BW0(), null);
                        } else {
                            f2 = this.this$0.hoveredElevation;
                            if (Dp.m6631equalsimpl0(m6640unboximpl, f2)) {
                                focus = new HoverInteraction.Enter();
                            } else {
                                f3 = this.this$0.focusedElevation;
                                if (Dp.m6631equalsimpl0(m6640unboximpl, f3)) {
                                    focus = new FocusInteraction.Focus();
                                }
                            }
                        }
                        this.label = 2;
                        if (ElevationKt.m1543animateElevationrAjV9yQ(this.$animatable, this.$target, focus, this.$interaction, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultButtonElevation$elevation$2$1 = this;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                defaultButtonElevation$elevation$2$12 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                defaultButtonElevation$elevation$2$1 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
