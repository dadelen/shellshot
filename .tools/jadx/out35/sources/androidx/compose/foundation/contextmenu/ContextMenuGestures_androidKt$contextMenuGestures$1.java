package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ContextMenuGestures.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$contextMenuGestures$1", f = "ContextMenuGestures.android.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class ContextMenuGestures_androidKt$contextMenuGestures$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContextMenuState $state;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContextMenuGestures_androidKt$contextMenuGestures$1(ContextMenuState contextMenuState, Continuation<? super ContextMenuGestures_androidKt$contextMenuGestures$1> continuation) {
        super(2, continuation);
        this.$state = contextMenuState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ContextMenuGestures_androidKt$contextMenuGestures$1 contextMenuGestures_androidKt$contextMenuGestures$1 = new ContextMenuGestures_androidKt$contextMenuGestures$1(this.$state, continuation);
        contextMenuGestures_androidKt$contextMenuGestures$1.L$0 = obj;
        return contextMenuGestures_androidKt$contextMenuGestures$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((ContextMenuGestures_androidKt$contextMenuGestures$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                final ContextMenuState contextMenuState = this.$state;
                this.label = 1;
                if (ContextMenuGestures_androidKt.onRightClickDown($this$pointerInput, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$contextMenuGestures$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m335invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m335invokek4lQ0M(long it) {
                        ContextMenuState.this.setStatus(new ContextMenuState.Status.Open(it, null));
                    }
                }, this) != coroutine_suspended) {
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
