package androidx.compose.foundation.gestures;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Draggable.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0, 0}, l = {558}, m = "processDragStop", n = {"this", NotificationCompat.CATEGORY_EVENT}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
final class DragGestureNode$processDragStop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DragGestureNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragGestureNode$processDragStop$1(DragGestureNode dragGestureNode, Continuation<? super DragGestureNode$processDragStop$1> continuation) {
        super(continuation);
        this.this$0 = dragGestureNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object processDragStop;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        processDragStop = this.this$0.processDragStop(null, this);
        return processDragStop;
    }
}
