package androidx.compose.material;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.GestureCancellationException;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$1", f = "Slider.kt", i = {}, l = {919}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SliderKt$sliderTapModifier$2$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DraggableState $draggableState;
    final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
    final /* synthetic */ boolean $isRtl;
    final /* synthetic */ float $maxPx;
    final /* synthetic */ MutableState<Float> $pressOffset;
    final /* synthetic */ State<Float> $rawOffset;
    final /* synthetic */ CoroutineScope $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SliderKt$sliderTapModifier$2$1$1(boolean z, float f, MutableState<Float> mutableState, State<Float> state, CoroutineScope coroutineScope, DraggableState draggableState, State<? extends Function1<? super Float, Unit>> state2, Continuation<? super SliderKt$sliderTapModifier$2$1$1> continuation) {
        super(2, continuation);
        this.$isRtl = z;
        this.$maxPx = f;
        this.$pressOffset = mutableState;
        this.$rawOffset = state;
        this.$scope = coroutineScope;
        this.$draggableState = draggableState;
        this.$gestureEndAction = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SliderKt$sliderTapModifier$2$1$1 sliderKt$sliderTapModifier$2$1$1 = new SliderKt$sliderTapModifier$2$1$1(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, this.$scope, this.$draggableState, this.$gestureEndAction, continuation);
        sliderKt$sliderTapModifier$2$1$1.L$0 = obj;
        return sliderKt$sliderTapModifier$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((SliderKt$sliderTapModifier$2$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, null);
                final CoroutineScope coroutineScope = this.$scope;
                final DraggableState draggableState = this.$draggableState;
                final State<Function1<Float, Unit>> state = this.$gestureEndAction;
                this.label = 1;
                if (TapGestureDetectorKt.detectTapGestures$default($this$pointerInput, null, null, anonymousClass1, new Function1<Offset, Unit>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$2$1$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1642invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$2$1", f = "Slider.kt", i = {}, l = {931}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$2$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DraggableState $draggableState;
                        final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        AnonymousClass1(DraggableState draggableState, State<? extends Function1<? super Float, Unit>> state, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$draggableState = draggableState;
                            this.$gestureEndAction = state;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$draggableState, this.$gestureEndAction, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* compiled from: Slider.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$2$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$2$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C00421 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
                            private /* synthetic */ Object L$0;
                            int label;

                            C00421(Continuation<? super C00421> continuation) {
                                super(2, continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                C00421 c00421 = new C00421(continuation);
                                c00421.L$0 = obj;
                                return c00421;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                                return ((C00421) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure(obj);
                                        DragScope $this$drag = (DragScope) this.L$0;
                                        $this$drag.dragBy(0.0f);
                                        return Unit.INSTANCE;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AnonymousClass1 anonymousClass1;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$draggableState.drag(MutatePriority.UserInput, new C00421(null), this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    anonymousClass1 = this;
                                    break;
                                case 1:
                                    anonymousClass1 = this;
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            anonymousClass1.$gestureEndAction.getValue().invoke(Boxing.boxFloat(0.0f));
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1642invokek4lQ0M(long it) {
                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(draggableState, state, null), 3, null);
                    }
                }, this, 3, null) != coroutine_suspended) {
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

    /* compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "pos", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$1", f = "Slider.kt", i = {}, l = {924}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ MutableState<Float> $pressOffset;
        final /* synthetic */ State<Float> $rawOffset;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, float f, MutableState<Float> mutableState, State<Float> state, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$isRtl = z;
            this.$maxPx = f;
            this.$pressOffset = mutableState;
            this.$rawOffset = state;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m1641invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m1641invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, continuation);
            anonymousClass1.L$0 = pressGestureScope;
            anonymousClass1.J$0 = j;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PressGestureScope $this$detectTapGestures = (PressGestureScope) this.L$0;
                    long pos = this.J$0;
                    float to = this.$isRtl ? this.$maxPx - Offset.m3922getXimpl(pos) : Offset.m3922getXimpl(pos);
                    this.$pressOffset.setValue(Boxing.boxFloat(to - this.$rawOffset.getValue().floatValue()));
                    try {
                        this.label = 1;
                    } catch (GestureCancellationException e) {
                        anonymousClass1 = this;
                        anonymousClass1.$pressOffset.setValue(Boxing.boxFloat(0.0f));
                        return Unit.INSTANCE;
                    }
                    if ($this$detectTapGestures.awaitRelease(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass1 = this;
                    try {
                        ResultKt.throwOnFailure($result);
                    } catch (GestureCancellationException e2) {
                        anonymousClass1.$pressOffset.setValue(Boxing.boxFloat(0.0f));
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
