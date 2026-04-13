package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;

/* compiled from: ForEachGesture.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080@¢\u0006\u0002\u0010\u0005\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u0006H\u0080@¢\u0006\u0002\u0010\u0007\u001a;\u0010\b\u001a\u00020\u0004*\u00020\u00062'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\rH\u0086@¢\u0006\u0002\u0010\u000e\u001a;\u0010\u000f\u001a\u00020\u0004*\u00020\u00062'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\rH\u0087@¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"allPointersUp", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "awaitAllPointersUp", "", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitEachGesture", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forEachGesture", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ForEachGestureKt {
    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:3|(4:5|6|7|8))|44|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a4, code lost:
    
        r0.L$0 = r2;
        r0.L$1 = r7;
        r0.L$2 = r6;
        r0.label = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b1, code lost:
    
        if (awaitAllPointersUp((androidx.compose.ui.input.pointer.PointerInputScope) r2, r0) == r1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b3, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
    
        r3 = r6;
        r6 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b7, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0062, code lost:
    
        r3 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.compose.ui.input.pointer.PointerInputScope] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object, kotlin.coroutines.CoroutineContext] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0096 -> B:13:0x006d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00b4 -> B:13:0x006d). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(message = "Use awaitEachGesture instead. forEachGesture() can drop events between gestures.", replaceWith = @kotlin.ReplaceWith(expression = "awaitEachGesture(block)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object forEachGesture(androidx.compose.ui.input.pointer.PointerInputScope r6, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L64;
                case 1: goto L52;
                case 2: goto L40;
                case 3: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            java.lang.Object r6 = r0.L$2
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r2 = (androidx.compose.ui.input.pointer.PointerInputScope) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r6
            r6 = r2
            goto Lb6
        L40:
            java.lang.Object r6 = r0.L$2
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r2 = (androidx.compose.ui.input.pointer.PointerInputScope) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L62
            r3 = r6
            r6 = r2
            goto L98
        L52:
            java.lang.Object r6 = r0.L$2
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r2 = (androidx.compose.ui.input.pointer.PointerInputScope) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L62
            goto L86
        L62:
            r3 = move-exception
            goto L9e
        L64:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            kotlin.coroutines.CoroutineContext r3 = r0.getContext()
        L6d:
            boolean r2 = kotlinx.coroutines.JobKt.isActive(r3)
            if (r2 == 0) goto Lb8
        L74:
            r0.L$0 = r6     // Catch: java.util.concurrent.CancellationException -> L99
            r0.L$1 = r7     // Catch: java.util.concurrent.CancellationException -> L99
            r0.L$2 = r3     // Catch: java.util.concurrent.CancellationException -> L99
            r2 = 1
            r0.label = r2     // Catch: java.util.concurrent.CancellationException -> L99
            java.lang.Object r2 = r7.invoke(r6, r0)     // Catch: java.util.concurrent.CancellationException -> L99
            if (r2 != r1) goto L84
            return r1
        L84:
            r2 = r6
            r6 = r3
        L86:
            r0.L$0 = r2     // Catch: java.util.concurrent.CancellationException -> L62
            r0.L$1 = r7     // Catch: java.util.concurrent.CancellationException -> L62
            r0.L$2 = r6     // Catch: java.util.concurrent.CancellationException -> L62
            r3 = 2
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L62
            java.lang.Object r3 = awaitAllPointersUp(r2, r0)     // Catch: java.util.concurrent.CancellationException -> L62
            if (r3 != r1) goto L96
            return r1
        L96:
            r3 = r6
            r6 = r2
        L98:
            goto L6d
        L99:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r3
            r3 = r5
        L9e:
            boolean r4 = kotlinx.coroutines.JobKt.isActive(r6)
            if (r4 == 0) goto Lb7
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r6
            r3 = 3
            r0.label = r3
            java.lang.Object r3 = awaitAllPointersUp(r2, r0)
            if (r3 != r1) goto Lb4
            return r1
        Lb4:
            r3 = r6
            r6 = r2
        Lb6:
            goto L6d
        Lb7:
            throw r3
        Lb8:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.forEachGesture(androidx.compose.ui.input.pointer.PointerInputScope, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final boolean allPointersUp(AwaitPointerEventScope $this$allPointersUp) {
        boolean z;
        List $this$fastAny$iv = $this$allPointersUp.getCurrentEvent().getChanges();
        int index$iv$iv = 0;
        int size = $this$fastAny$iv.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) item$iv$iv;
                if (it.getPressed()) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            } else {
                z = false;
                break;
            }
        }
        return !z;
    }

    public static final Object awaitAllPointersUp(PointerInputScope $this$awaitAllPointersUp, Continuation<? super Unit> continuation) {
        Object awaitPointerEventScope = $this$awaitAllPointersUp.awaitPointerEventScope(new ForEachGestureKt$awaitAllPointersUp$2(null), continuation);
        return awaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitPointerEventScope : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0040, code lost:
    
        if (allPointersUp(r14) == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x004f -> B:12:0x0054). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            boolean r0 = r15 instanceof androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            if (r0 == 0) goto L14
            r0 = r15
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            r0.<init>(r15)
        L19:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L39;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L2e:
            java.lang.Object r14 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r14 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r14
            kotlin.ResultKt.throwOnFailure(r15)
            r2 = r1
            r1 = r0
            r0 = r15
            goto L54
        L39:
            kotlin.ResultKt.throwOnFailure(r15)
            boolean r2 = allPointersUp(r14)
            if (r2 != 0) goto L87
        L42:
            androidx.compose.ui.input.pointer.PointerEventPass r2 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            r0.L$0 = r14
            r0.label = r3
            java.lang.Object r2 = r14.awaitPointerEvent(r2, r0)
            if (r2 != r1) goto L4f
            return r1
        L4f:
            r13 = r0
            r0 = r15
            r15 = r2
            r2 = r1
            r1 = r13
        L54:
            androidx.compose.ui.input.pointer.PointerEvent r15 = (androidx.compose.ui.input.pointer.PointerEvent) r15
            java.util.List r15 = r15.getChanges()
            r4 = 0
            r5 = 0
            r6 = 0
            int r7 = r15.size()
        L64:
            if (r6 >= r7) goto L7c
            java.lang.Object r8 = r15.get(r6)
            r9 = r8
            r10 = 0
            r11 = r9
            androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
            r12 = 0
            boolean r11 = r11.getPressed()
            if (r11 == 0) goto L78
            r15 = r3
            goto L7e
        L78:
            int r6 = r6 + 1
            goto L64
        L7c:
            r15 = 0
        L7e:
            if (r15 != 0) goto L83
            r15 = r0
            r0 = r1
            goto L87
        L83:
            r15 = r0
            r0 = r1
            r1 = r2
            goto L42
        L87:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object awaitEachGesture(PointerInputScope $this$awaitEachGesture, Function2<? super AwaitPointerEventScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        CoroutineContext currentContext = continuation.getContext();
        Object awaitPointerEventScope = $this$awaitEachGesture.awaitPointerEventScope(new ForEachGestureKt$awaitEachGesture$2(currentContext, function2, null), continuation);
        return awaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitPointerEventScope : Unit.INSTANCE;
    }
}
