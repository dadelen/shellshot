package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.material3.internal.PredictiveBack;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SearchBar.android.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u008a@"}, d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Lkotlin/jvm/JvmSuppressWildcards;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.SearchBar_androidKt$SearchBar$2$1", f = "SearchBar.android.kt", i = {}, l = {ComposerKt.compositionLocalMapKey}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class SearchBar_androidKt$SearchBar$2$1 extends SuspendLambda implements Function2<Flow<BackEventCompat>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    final /* synthetic */ MutatorMutex $mutatorMutex;
    final /* synthetic */ Function1<Boolean, Unit> $onExpandedChange;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SearchBar_androidKt$SearchBar$2$1(MutatorMutex mutatorMutex, MutableFloatState mutableFloatState, Animatable<Float, AnimationVector1D> animatable, Function1<? super Boolean, Unit> function1, MutableState<BackEventCompat> mutableState, MutableState<BackEventCompat> mutableState2, Continuation<? super SearchBar_androidKt$SearchBar$2$1> continuation) {
        super(2, continuation);
        this.$mutatorMutex = mutatorMutex;
        this.$finalBackProgress = mutableFloatState;
        this.$animationProgress = animatable;
        this.$onExpandedChange = function1;
        this.$firstBackEvent = mutableState;
        this.$currentBackEvent = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SearchBar_androidKt$SearchBar$2$1 searchBar_androidKt$SearchBar$2$1 = new SearchBar_androidKt$SearchBar$2$1(this.$mutatorMutex, this.$finalBackProgress, this.$animationProgress, this.$onExpandedChange, this.$firstBackEvent, this.$currentBackEvent, continuation);
        searchBar_androidKt$SearchBar$2$1.L$0 = obj;
        return searchBar_androidKt$SearchBar$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Flow<BackEventCompat> flow, Continuation<? super Unit> continuation) {
        return ((SearchBar_androidKt$SearchBar$2$1) create(flow, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: SearchBar.android.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SearchBar_androidKt$SearchBar$2$1$1", f = "SearchBar.android.kt", i = {}, l = {205, 216}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material3.SearchBar_androidKt$SearchBar$2$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
        final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
        final /* synthetic */ MutableFloatState $finalBackProgress;
        final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
        final /* synthetic */ Function1<Boolean, Unit> $onExpandedChange;
        final /* synthetic */ Flow<BackEventCompat> $progress;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(MutableFloatState mutableFloatState, Flow<BackEventCompat> flow, Animatable<Float, AnimationVector1D> animatable, Function1<? super Boolean, Unit> function1, MutableState<BackEventCompat> mutableState, MutableState<BackEventCompat> mutableState2, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$finalBackProgress = mutableFloatState;
            this.$progress = flow;
            this.$animationProgress = animatable;
            this.$onExpandedChange = function1;
            this.$firstBackEvent = mutableState;
            this.$currentBackEvent = mutableState2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$finalBackProgress, this.$progress, this.$animationProgress, this.$onExpandedChange, this.$firstBackEvent, this.$currentBackEvent, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            FiniteAnimationSpec finiteAnimationSpec;
            Object animateTo;
            AnonymousClass1 anonymousClass12;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
            } catch (CancellationException e) {
                anonymousClass1 = anonymousClass12;
                Animatable<Float, AnimationVector1D> animatable = anonymousClass1.$animationProgress;
                Float boxFloat = Boxing.boxFloat(1.0f);
                finiteAnimationSpec = SearchBar_androidKt.AnimationPredictiveBackExitFloatSpec;
                anonymousClass1.label = 2;
                animateTo = animatable.animateTo(boxFloat, (r12 & 2) != 0 ? animatable.defaultSpringSpec : finiteAnimationSpec, (r12 & 4) != 0 ? animatable.getVelocity() : null, (r12 & 8) != 0 ? null : null, anonymousClass1);
                if (animateTo == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass12 = this;
                    anonymousClass12.$finalBackProgress.setFloatValue(Float.NaN);
                    Flow<BackEventCompat> flow = anonymousClass12.$progress;
                    final MutableState<BackEventCompat> mutableState = anonymousClass12.$firstBackEvent;
                    final MutableState<BackEventCompat> mutableState2 = anonymousClass12.$currentBackEvent;
                    final Animatable<Float, AnimationVector1D> animatable2 = anonymousClass12.$animationProgress;
                    anonymousClass12.label = 1;
                    if (flow.collect(new FlowCollector() { // from class: androidx.compose.material3.SearchBar_androidKt.SearchBar.2.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((BackEventCompat) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(BackEventCompat backEvent, Continuation<? super Unit> continuation) {
                            if (mutableState.getValue() == null) {
                                mutableState.setValue(backEvent);
                            }
                            mutableState2.setValue(backEvent);
                            float interpolatedProgress = PredictiveBack.INSTANCE.transform$material3_release(backEvent.getProgress());
                            Object snapTo = animatable2.snapTo(Boxing.boxFloat(1.0f - interpolatedProgress), continuation);
                            return snapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? snapTo : Unit.INSTANCE;
                        }
                    }, anonymousClass12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass12.$finalBackProgress.setFloatValue(anonymousClass12.$animationProgress.getValue().floatValue());
                    anonymousClass12.$onExpandedChange.invoke(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass12 = this;
                    ResultKt.throwOnFailure($result);
                    anonymousClass12.$finalBackProgress.setFloatValue(anonymousClass12.$animationProgress.getValue().floatValue());
                    anonymousClass12.$onExpandedChange.invoke(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass1 = this;
                    ResultKt.throwOnFailure($result);
                    anonymousClass1.$finalBackProgress.setFloatValue(Float.NaN);
                    anonymousClass1.$firstBackEvent.setValue(null);
                    anonymousClass1.$currentBackEvent.setValue(null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Flow progress = (Flow) this.L$0;
                this.label = 1;
                if (MutatorMutex.mutate$default(this.$mutatorMutex, null, new AnonymousClass1(this.$finalBackProgress, progress, this.$animationProgress, this.$onExpandedChange, this.$firstBackEvent, this.$currentBackEvent, null), this, 1, null) != coroutine_suspended) {
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
