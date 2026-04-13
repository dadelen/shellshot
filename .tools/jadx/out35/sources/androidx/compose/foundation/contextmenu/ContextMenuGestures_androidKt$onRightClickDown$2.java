package androidx.compose.foundation.contextmenu;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ContextMenuGestures.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$onRightClickDown$2", f = "ContextMenuGestures.android.kt", i = {0}, l = {53, 56}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
/* loaded from: classes.dex */
final class ContextMenuGestures_androidKt$onRightClickDown$2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Offset, Unit> $onDown;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ContextMenuGestures_androidKt$onRightClickDown$2(Function1<? super Offset, Unit> function1, Continuation<? super ContextMenuGestures_androidKt$onRightClickDown$2> continuation) {
        super(2, continuation);
        this.$onDown = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ContextMenuGestures_androidKt$onRightClickDown$2 contextMenuGestures_androidKt$onRightClickDown$2 = new ContextMenuGestures_androidKt$onRightClickDown$2(this.$onDown, continuation);
        contextMenuGestures_androidKt$onRightClickDown$2.L$0 = obj;
        return contextMenuGestures_androidKt$onRightClickDown$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
        return ((ContextMenuGestures_androidKt$onRightClickDown$2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0067  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            switch(r1) {
                case 0: goto L25;
                case 1: goto L19;
                case 2: goto L13;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L13:
            r0 = r9
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r10
            goto L63
        L19:
            r1 = r9
            java.lang.Object r3 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            kotlin.ResultKt.throwOnFailure(r10)
            r4 = r3
            r3 = r1
            r1 = r10
            goto L40
        L25:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r9
            java.lang.Object r3 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.L$0 = r3
            r1.label = r2
            java.lang.Object r4 = androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.access$awaitFirstRightClickDown(r3, r4)
            if (r4 != r0) goto L3b
            return r0
        L3b:
            r8 = r1
            r1 = r10
            r10 = r4
            r4 = r3
            r3 = r8
        L40:
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            r10.consume()
            kotlin.jvm.functions.Function1<androidx.compose.ui.geometry.Offset, kotlin.Unit> r5 = r3.$onDown
            long r6 = r10.getPosition()
            androidx.compose.ui.geometry.Offset r6 = androidx.compose.ui.geometry.Offset.m3911boximpl(r6)
            r5.invoke(r6)
            r10 = r3
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r5 = 0
            r3.L$0 = r5
            r6 = 2
            r3.label = r6
            java.lang.Object r10 = androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation$default(r4, r5, r10, r2, r5)
            if (r10 != r0) goto L62
            return r0
        L62:
            r0 = r3
        L63:
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            if (r10 == 0) goto L6a
            r10.consume()
        L6a:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$onRightClickDown$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
