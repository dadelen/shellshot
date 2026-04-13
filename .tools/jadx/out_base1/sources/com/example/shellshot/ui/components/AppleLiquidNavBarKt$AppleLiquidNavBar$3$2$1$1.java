package com.example.shellshot.ui.components;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1", f = "AppleLiquidNavBar.kt", i = {}, l = {171}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableFloatState $motionDeltaPx$delegate;
    final /* synthetic */ MutableFloatState $motionVelocityPxPerSecond$delegate;
    final /* synthetic */ State<Float> $pillOffsetPx$delegate;
    final /* synthetic */ MutableFloatState $previousFrameOffsetPx$delegate;
    final /* synthetic */ MutableLongState $previousFrameTimeNanos$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1(State<Float> state, MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, MutableLongState mutableLongState, MutableFloatState mutableFloatState3, Continuation<? super AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1> continuation) {
        super(2, continuation);
        this.$pillOffsetPx$delegate = state;
        this.$previousFrameOffsetPx$delegate = mutableFloatState;
        this.$motionDeltaPx$delegate = mutableFloatState2;
        this.$previousFrameTimeNanos$delegate = mutableLongState;
        this.$motionVelocityPxPerSecond$delegate = mutableFloatState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1(this.$pillOffsetPx$delegate, this.$previousFrameOffsetPx$delegate, this.$motionDeltaPx$delegate, this.$previousFrameTimeNanos$delegate, this.$motionVelocityPxPerSecond$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final State<Float> state = this.$pillOffsetPx$delegate;
                Flow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        float AppleLiquidNavBar$lambda$64$lambda$63$lambda$9;
                        AppleLiquidNavBar$lambda$64$lambda$63$lambda$9 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$9(State.this);
                        return Float.valueOf(AppleLiquidNavBar$lambda$64$lambda$63$lambda$9);
                    }
                });
                final MutableFloatState mutableFloatState = this.$previousFrameOffsetPx$delegate;
                final MutableFloatState mutableFloatState2 = this.$motionDeltaPx$delegate;
                final MutableLongState mutableLongState = this.$previousFrameTimeNanos$delegate;
                final MutableFloatState mutableFloatState3 = this.$motionVelocityPxPerSecond$delegate;
                this.label = 1;
                if (snapshotFlow.collect(new FlowCollector() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$AppleLiquidNavBar$3$2$1$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit(((Number) value).floatValue(), (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(float latestOffset, Continuation<? super Unit> continuation) {
                        float AppleLiquidNavBar$lambda$64$lambda$63$lambda$11;
                        long AppleLiquidNavBar$lambda$64$lambda$63$lambda$14;
                        long AppleLiquidNavBar$lambda$64$lambda$63$lambda$142;
                        float AppleLiquidNavBar$lambda$64$lambda$63$lambda$17;
                        float AppleLiquidNavBar$lambda$64$lambda$63$lambda$20;
                        long frameTimeNanos = System.nanoTime();
                        MutableFloatState mutableFloatState4 = mutableFloatState2;
                        AppleLiquidNavBar$lambda$64$lambda$63$lambda$11 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$11(MutableFloatState.this);
                        mutableFloatState4.setFloatValue(latestOffset - AppleLiquidNavBar$lambda$64$lambda$63$lambda$11);
                        AppleLiquidNavBar$lambda$64$lambda$63$lambda$14 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$14(mutableLongState);
                        if (AppleLiquidNavBar$lambda$64$lambda$63$lambda$14 != 0) {
                            AppleLiquidNavBar$lambda$64$lambda$63$lambda$142 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$14(mutableLongState);
                            float deltaSeconds = (frameTimeNanos - AppleLiquidNavBar$lambda$64$lambda$63$lambda$142) / 1.0E9f;
                            if (deltaSeconds > 0.0f) {
                                AppleLiquidNavBar$lambda$64$lambda$63$lambda$17 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$17(mutableFloatState2);
                                float instantaneousVelocity = AppleLiquidNavBar$lambda$64$lambda$63$lambda$17 / deltaSeconds;
                                MutableFloatState mutableFloatState5 = mutableFloatState3;
                                AppleLiquidNavBar$lambda$64$lambda$63$lambda$20 = AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$20(mutableFloatState3);
                                mutableFloatState5.setFloatValue((AppleLiquidNavBar$lambda$64$lambda$63$lambda$20 * 0.72f) + (0.28f * instantaneousVelocity));
                            }
                        }
                        MutableFloatState.this.setFloatValue(latestOffset);
                        mutableLongState.setLongValue(frameTimeNanos);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
