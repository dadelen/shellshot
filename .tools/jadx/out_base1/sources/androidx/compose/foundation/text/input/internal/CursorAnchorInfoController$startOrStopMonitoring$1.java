package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.runtime.SnapshotStateKt;
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
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: CursorAnchorInfoController.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.CursorAnchorInfoController$startOrStopMonitoring$1", f = "CursorAnchorInfoController.android.kt", i = {}, l = {155}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class CursorAnchorInfoController$startOrStopMonitoring$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CursorAnchorInfoController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CursorAnchorInfoController$startOrStopMonitoring$1(CursorAnchorInfoController cursorAnchorInfoController, Continuation<? super CursorAnchorInfoController$startOrStopMonitoring$1> continuation) {
        super(2, continuation);
        this.this$0 = cursorAnchorInfoController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CursorAnchorInfoController$startOrStopMonitoring$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CursorAnchorInfoController$startOrStopMonitoring$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final CursorAnchorInfoController cursorAnchorInfoController = this.this$0;
                Flow filterNotNull = FlowKt.filterNotNull(FlowKt.drop(SnapshotStateKt.snapshotFlow(new Function0<CursorAnchorInfo>() { // from class: androidx.compose.foundation.text.input.internal.CursorAnchorInfoController$startOrStopMonitoring$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final CursorAnchorInfo invoke() {
                        CursorAnchorInfo calculateCursorAnchorInfo;
                        calculateCursorAnchorInfo = CursorAnchorInfoController.this.calculateCursorAnchorInfo();
                        return calculateCursorAnchorInfo;
                    }
                }), 1));
                final CursorAnchorInfoController cursorAnchorInfoController2 = this.this$0;
                this.label = 1;
                if (filterNotNull.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.CursorAnchorInfoController$startOrStopMonitoring$1.2
                    public final Object emit(CursorAnchorInfo it, Continuation<? super Unit> continuation) {
                        ComposeInputMethodManager composeInputMethodManager;
                        composeInputMethodManager = CursorAnchorInfoController.this.composeImm;
                        composeInputMethodManager.updateCursorAnchorInfo(it);
                        return Unit.INSTANCE;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((CursorAnchorInfo) value, (Continuation<? super Unit>) $completion);
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
