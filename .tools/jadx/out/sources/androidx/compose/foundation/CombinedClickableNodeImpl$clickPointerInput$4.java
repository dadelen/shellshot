package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: Clickable.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNodeImpl$clickPointerInput$4", f = "Clickable.kt", i = {}, l = {787}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class CombinedClickableNodeImpl$clickPointerInput$4 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CombinedClickableNodeImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CombinedClickableNodeImpl$clickPointerInput$4(CombinedClickableNodeImpl combinedClickableNodeImpl, Continuation<? super CombinedClickableNodeImpl$clickPointerInput$4> continuation) {
        super(3, continuation);
        this.this$0 = combinedClickableNodeImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
        return m279invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
    }

    /* renamed from: invoke-d-4ec7I, reason: not valid java name */
    public final Object m279invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
        CombinedClickableNodeImpl$clickPointerInput$4 combinedClickableNodeImpl$clickPointerInput$4 = new CombinedClickableNodeImpl$clickPointerInput$4(this.this$0, continuation);
        combinedClickableNodeImpl$clickPointerInput$4.L$0 = pressGestureScope;
        combinedClickableNodeImpl$clickPointerInput$4.J$0 = j;
        return combinedClickableNodeImpl$clickPointerInput$4.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CombinedClickableNodeImpl$clickPointerInput$4 combinedClickableNodeImpl$clickPointerInput$4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PressGestureScope $this$detectTapGestures = (PressGestureScope) this.L$0;
                long offset = this.J$0;
                if (this.this$0.getEnabled()) {
                    this.label = 1;
                    if (this.this$0.m198handlePressInteractiond4ec7I($this$detectTapGestures, offset, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    combinedClickableNodeImpl$clickPointerInput$4 = this;
                }
                return Unit.INSTANCE;
            case 1:
                combinedClickableNodeImpl$clickPointerInput$4 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
