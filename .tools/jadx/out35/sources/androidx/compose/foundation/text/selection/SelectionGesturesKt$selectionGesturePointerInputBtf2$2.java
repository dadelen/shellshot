package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: SelectionGestures.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2", f = "SelectionGestures.kt", i = {0}, l = {209, 217, 220, 221}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
/* loaded from: classes.dex */
final class SelectionGesturesKt$selectionGesturePointerInputBtf2$2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ClicksCounter $clicksCounter;
    final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
    final /* synthetic */ TextDragObserver $textDragObserver;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionGesturesKt$selectionGesturePointerInputBtf2$2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super SelectionGesturesKt$selectionGesturePointerInputBtf2$2> continuation) {
        super(2, continuation);
        this.$clicksCounter = clicksCounter;
        this.$mouseSelectionObserver = mouseSelectionObserver;
        this.$textDragObserver = textDragObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionGesturesKt$selectionGesturePointerInputBtf2$2 selectionGesturesKt$selectionGesturePointerInputBtf2$2 = new SelectionGesturesKt$selectionGesturePointerInputBtf2$2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
        selectionGesturesKt$selectionGesturePointerInputBtf2$2.L$0 = obj;
        return selectionGesturesKt$selectionGesturePointerInputBtf2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
        return ((SelectionGesturesKt$selectionGesturePointerInputBtf2$2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b8  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
