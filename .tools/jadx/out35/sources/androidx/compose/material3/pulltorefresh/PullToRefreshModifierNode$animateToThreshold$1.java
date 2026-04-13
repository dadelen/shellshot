package androidx.compose.material3.pulltorefresh;

import com.example.shellshot.data.LogRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PullToRefresh.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode", f = "PullToRefresh.kt", i = {0}, l = {LogRepository.MAX_PERSISTED_COUNT}, m = "animateToThreshold", n = {"this"}, s = {"L$0"})
/* loaded from: classes13.dex */
final class PullToRefreshModifierNode$animateToThreshold$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PullToRefreshModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PullToRefreshModifierNode$animateToThreshold$1(PullToRefreshModifierNode pullToRefreshModifierNode, Continuation<? super PullToRefreshModifierNode$animateToThreshold$1> continuation) {
        super(continuation);
        this.this$0 = pullToRefreshModifierNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object animateToThreshold;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        animateToThreshold = this.this$0.animateToThreshold(this);
        return animateToThreshold;
    }
}
