package androidx.compose.foundation.contextmenu;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ContextMenuGestures.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt", f = "ContextMenuGestures.android.kt", i = {0}, l = {66}, m = "awaitFirstRightClickDown", n = {"$this$awaitFirstRightClickDown"}, s = {"L$0"})
/* loaded from: classes.dex */
final class ContextMenuGestures_androidKt$awaitFirstRightClickDown$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ContextMenuGestures_androidKt$awaitFirstRightClickDown$1(Continuation<? super ContextMenuGestures_androidKt$awaitFirstRightClickDown$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitFirstRightClickDown;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitFirstRightClickDown = ContextMenuGestures_androidKt.awaitFirstRightClickDown(null, this);
        return awaitFirstRightClickDown;
    }
}
