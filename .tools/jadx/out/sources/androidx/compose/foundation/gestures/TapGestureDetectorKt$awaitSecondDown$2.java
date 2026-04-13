package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2", f = "TapGestureDetector.kt", i = {0, 0}, l = {212}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull", "minUptime"}, s = {"L$0", "J$0"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$awaitSecondDown$2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
    final /* synthetic */ PointerInputChange $firstUp;
    long J$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TapGestureDetectorKt$awaitSecondDown$2(PointerInputChange pointerInputChange, Continuation<? super TapGestureDetectorKt$awaitSecondDown$2> continuation) {
        super(2, continuation);
        this.$firstUp = pointerInputChange;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TapGestureDetectorKt$awaitSecondDown$2 tapGestureDetectorKt$awaitSecondDown$2 = new TapGestureDetectorKt$awaitSecondDown$2(this.$firstUp, continuation);
        tapGestureDetectorKt$awaitSecondDown$2.L$0 = obj;
        return tapGestureDetectorKt$awaitSecondDown$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
        return ((TapGestureDetectorKt$awaitSecondDown$2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x005d A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x004e -> B:7:0x0053). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            switch(r1) {
                case 0: goto L21;
                case 1: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L12:
            r1 = r10
            long r2 = r1.J$0
            java.lang.Object r4 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            goto L53
        L21:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r10
            java.lang.Object r2 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
            androidx.compose.ui.input.pointer.PointerInputChange r3 = r1.$firstUp
            long r3 = r3.getUptimeMillis()
            androidx.compose.ui.platform.ViewConfiguration r5 = r2.getViewConfiguration()
            long r5 = r5.getDoubleTapMinTimeMillis()
            long r3 = r3 + r5
            r8 = r3
        L39:
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r2
            r1.J$0 = r8
            r3 = 1
            r1.label = r3
            r3 = 0
            r4 = 0
            r6 = 3
            r7 = 0
            java.lang.Object r3 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r2, r3, r4, r5, r6, r7)
            if (r3 != r0) goto L4e
            return r0
        L4e:
            r4 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r3
        L53:
            androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
            long r5 = r11.getUptimeMillis()
            int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r3 < 0) goto L5e
            return r11
        L5e:
            r11 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
