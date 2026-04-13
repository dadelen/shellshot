package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Transition.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 0, 1, 1}, l = {542, 2186}, m = "waitForCompositionAfterTargetStateChange", n = {"this", "expectedState", "this", "expectedState"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
final class SeekableTransitionState$waitForCompositionAfterTargetStateChange$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SeekableTransitionState<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SeekableTransitionState$waitForCompositionAfterTargetStateChange$1(SeekableTransitionState<S> seekableTransitionState, Continuation<? super SeekableTransitionState$waitForCompositionAfterTargetStateChange$1> continuation) {
        super(continuation);
        this.this$0 = seekableTransitionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object waitForCompositionAfterTargetStateChange;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        waitForCompositionAfterTargetStateChange = this.this$0.waitForCompositionAfterTargetStateChange(this);
        return waitForCompositionAfterTargetStateChange;
    }
}
