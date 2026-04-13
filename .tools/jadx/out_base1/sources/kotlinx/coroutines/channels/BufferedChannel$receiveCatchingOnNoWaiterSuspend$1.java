package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: BufferedChannel.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.BufferedChannel", f = "BufferedChannel.kt", i = {0, 0, 0, 0}, l = {3087}, m = "receiveCatchingOnNoWaiterSuspend-GKJJFZk", n = {"this", "segment", "index", "r"}, s = {"L$0", "L$1", "I$0", "J$0"})
/* loaded from: classes14.dex */
final class BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatchingOnNoWaiterSuspend$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m8709receiveCatchingOnNoWaiterSuspendGKJJFZk;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m8709receiveCatchingOnNoWaiterSuspendGKJJFZk = this.this$0.m8709receiveCatchingOnNoWaiterSuspendGKJJFZk(null, 0, 0L, this);
        return m8709receiveCatchingOnNoWaiterSuspendGKJJFZk == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m8709receiveCatchingOnNoWaiterSuspendGKJJFZk : ChannelResult.m8717boximpl(m8709receiveCatchingOnNoWaiterSuspendGKJJFZk);
    }
}
