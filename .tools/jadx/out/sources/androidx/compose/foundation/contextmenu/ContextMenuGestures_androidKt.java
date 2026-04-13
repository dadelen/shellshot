package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: ContextMenuGestures.android.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0082@¢\u0006\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a&\u0010\b\u001a\u00020\t*\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\fH\u0081@¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"awaitFirstRightClickDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contextMenuGestures", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "onRightClickDown", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDown", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenuGestures_androidKt {
    public static final Modifier contextMenuGestures(Modifier $this$contextMenuGestures, ContextMenuState state) {
        return SuspendingPointerInputFilterKt.pointerInput($this$contextMenuGestures, ContextMenuKey.INSTANCE, new ContextMenuGestures_androidKt$contextMenuGestures$1(state, null));
    }

    public static final Object onRightClickDown(PointerInputScope $this$onRightClickDown, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$onRightClickDown, new ContextMenuGestures_androidKt$onRightClickDown$2(function1, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x004e -> B:12:0x0056). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1 r1 = (androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1 r1 = new androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt$awaitFirstRightClickDown$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            switch(r3) {
                case 0: goto L3c;
                case 1: goto L30;
                default: goto L27;
            }
        L27:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            java.lang.Object r3 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            goto L56
        L3c:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r18
        L41:
            r1.L$0 = r3
            r1.label = r4
            r5 = 0
            java.lang.Object r5 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r3, r5, r1, r4, r5)
            if (r5 != r2) goto L4e
            return r2
        L4e:
            r17 = r1
            r1 = r0
            r0 = r5
            r5 = r3
            r3 = r2
            r2 = r17
        L56:
            androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
            int r6 = r0.getButtons()
            boolean r6 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m5337isSecondaryPressedaHzCxE(r6)
            if (r6 == 0) goto L97
            java.util.List r6 = r0.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            int r10 = r6.size()
        L70:
            r11 = 0
            if (r9 >= r10) goto L8a
            java.lang.Object r12 = r6.get(r9)
            r13 = r12
            r14 = 0
            r15 = r13
            androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
            r16 = 0
            boolean r15 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r15)
            if (r15 != 0) goto L86
            r6 = r11
            goto L8c
        L86:
            int r9 = r9 + 1
            goto L70
        L8a:
            r6 = r4
        L8c:
            if (r6 == 0) goto L97
            java.util.List r3 = r0.getChanges()
            java.lang.Object r3 = r3.get(r11)
            return r3
        L97:
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r5
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuGestures_androidKt.awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
