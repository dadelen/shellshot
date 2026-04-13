package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: SelectionGestures.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\rH\u0082@¢\u0006\u0002\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a*\u0010\u0017\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a\u001c\u0010\u0018\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a\"\u0010\u001d\u001a\u00020\u0010*\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0080@¢\u0006\u0002\u0010\u001f\u001a\"\u0010 \u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010\"\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010$\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a \u0010%\u001a\u00020\u0019*\u00020\u00192\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100'H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006("}, d2 = {"STATIC_KEY", "", "isPrecisePointer", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "awaitDown", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "", "observer", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelectionBtf2", "selectionGestureInput", "Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "selectionGesturePointerInputBtf2", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "downEvent", "touchSelectionSubsequentPress", "updateSelectionTouchMode", "updateTouchMode", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    public static final Modifier updateSelectionTouchMode(Modifier $this$updateSelectionTouchMode, Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput($this$updateSelectionTouchMode, Integer.valueOf(STATIC_KEY), new SelectionGesturesKt$updateSelectionTouchMode$1(function1, null));
    }

    public static final Modifier selectionGestureInput(Modifier $this$selectionGestureInput, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput($this$selectionGestureInput, mouseSelectionObserver, textDragObserver, new SelectionGesturesKt$selectionGestureInput$1(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b1 A[Catch: CancellationException -> 0x00e3, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:16:0x00a9, B:18:0x00b1, B:20:0x00c2, B:22:0x00d0, B:24:0x00d3, B:27:0x00d8, B:30:0x00dd, B:36:0x004c, B:38:0x0077, B:40:0x007b, B:42:0x0085, B:46:0x0055), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00dd A[Catch: CancellationException -> 0x00e3, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:16:0x00a9, B:18:0x00b1, B:20:0x00c2, B:22:0x00d0, B:24:0x00d3, B:27:0x00d8, B:30:0x00dd, B:36:0x004c, B:38:0x0077, B:40:0x007b, B:42:0x0085, B:46:0x0055), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.TextDragObserver r8, androidx.compose.ui.input.pointer.PointerEvent r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.selection.MouseSelectionObserver r8, androidx.compose.foundation.text.selection.ClicksCounter r9, androidx.compose.ui.input.pointer.PointerEvent r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object selectionGesturePointerInputBtf2(PointerInputScope $this$selectionGesturePointerInputBtf2, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        ClicksCounter clicksCounter = new ClicksCounter($this$selectionGesturePointerInputBtf2.getViewConfiguration());
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$selectionGesturePointerInputBtf2, new SelectionGesturesKt$selectionGesturePointerInputBtf2$2(clicksCounter, mouseSelectionObserver, textDragObserver, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00af A[Catch: CancellationException -> 0x00e1, TryCatch #0 {CancellationException -> 0x00e1, blocks: (B:16:0x00a7, B:18:0x00af, B:20:0x00c0, B:22:0x00ce, B:24:0x00d1, B:27:0x00d6, B:30:0x00db, B:36:0x004b, B:38:0x0076, B:40:0x007a, B:42:0x0084, B:46:0x0054), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00db A[Catch: CancellationException -> 0x00e1, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00e1, blocks: (B:16:0x00a7, B:18:0x00af, B:20:0x00c0, B:22:0x00ce, B:24:0x00d1, B:27:0x00d6, B:30:0x00db, B:36:0x004b, B:38:0x0076, B:40:0x007a, B:42:0x0084, B:46:0x0054), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object touchSelectionFirstPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.TextDragObserver r8, androidx.compose.ui.input.pointer.PointerEvent r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionFirstPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ec A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:15:0x00e4, B:17:0x00ec, B:19:0x00fd, B:21:0x010b, B:23:0x010e, B:26:0x0113, B:29:0x0118, B:34:0x00a3, B:36:0x00a7, B:37:0x00a9, B:39:0x00ae, B:41:0x00b1, B:43:0x00bc, B:45:0x00c2, B:47:0x00c6, B:48:0x00cb), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0118 A[Catch: CancellationException -> 0x003c, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:15:0x00e4, B:17:0x00ec, B:19:0x00fd, B:21:0x010b, B:23:0x010e, B:26:0x0113, B:29:0x0118, B:34:0x00a3, B:36:0x00a7, B:37:0x00a9, B:39:0x00ae, B:41:0x00b1, B:43:0x00bc, B:45:0x00c2, B:47:0x00c6, B:48:0x00cb), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7 A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:15:0x00e4, B:17:0x00ec, B:19:0x00fd, B:21:0x010b, B:23:0x010e, B:26:0x0113, B:29:0x0118, B:34:0x00a3, B:36:0x00a7, B:37:0x00a9, B:39:0x00ae, B:41:0x00b1, B:43:0x00bc, B:45:0x00c2, B:47:0x00c6, B:48:0x00cb), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:15:0x00e4, B:17:0x00ec, B:19:0x00fd, B:21:0x010b, B:23:0x010e, B:26:0x0113, B:29:0x0118, B:34:0x00a3, B:36:0x00a7, B:37:0x00a9, B:39:0x00ae, B:41:0x00b1, B:43:0x00bc, B:45:0x00c2, B:47:0x00c6, B:48:0x00cb), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b1 A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:15:0x00e4, B:17:0x00ec, B:19:0x00fd, B:21:0x010b, B:23:0x010e, B:26:0x0113, B:29:0x0118, B:34:0x00a3, B:36:0x00a7, B:37:0x00a9, B:39:0x00ae, B:41:0x00b1, B:43:0x00bc, B:45:0x00c2, B:47:0x00c6, B:48:0x00cb), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v1, types: [androidx.compose.foundation.text.TextDragObserver] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object touchSelectionSubsequentPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, androidx.compose.foundation.text.TextDragObserver r13, androidx.compose.ui.input.pointer.PointerEvent r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionSubsequentPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0117 A[Catch: all -> 0x003b, TryCatch #3 {all -> 0x003b, blocks: (B:13:0x0035, B:15:0x010f, B:17:0x0117, B:19:0x0128, B:21:0x0136), top: B:12:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094 A[Catch: all -> 0x004b, TryCatch #2 {all -> 0x004b, blocks: (B:35:0x0046, B:37:0x008c, B:39:0x0094, B:41:0x00a5, B:43:0x00b3), top: B:34:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object mouseSelectionBtf2(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.selection.MouseSelectionObserver r8, androidx.compose.foundation.text.selection.ClicksCounter r9, androidx.compose.ui.input.pointer.PointerEvent r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelectionBtf2(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x004f -> B:12:0x0057). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r18) {
        /*
            r0 = r18
            boolean r1 = r0 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = (androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
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
            goto L57
        L3c:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r17
        L42:
            androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r1.L$0 = r3
            r1.label = r4
            java.lang.Object r5 = r3.awaitPointerEvent(r5, r1)
            if (r5 != r2) goto L4f
            return r2
        L4f:
            r16 = r1
            r1 = r0
            r0 = r5
            r5 = r3
            r3 = r2
            r2 = r16
        L57:
            androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
            java.util.List r6 = r0.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            int r10 = r6.size()
        L67:
            if (r9 >= r10) goto L7f
            java.lang.Object r11 = r6.get(r9)
            r12 = r11
            r13 = 0
            r14 = r12
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            r15 = 0
            boolean r14 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r14)
            if (r14 != 0) goto L7b
            r10 = 0
            goto L81
        L7b:
            int r9 = r9 + 1
            goto L67
        L7f:
            r10 = r4
        L81:
            if (r10 == 0) goto L84
            return r0
        L84:
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r5
            goto L42
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange change1, PointerInputChange change2) {
        float slop = DragGestureDetectorKt.m416pointerSlopE8SPZFQ(viewConfiguration, change1.getType());
        return Offset.m3920getDistanceimpl(Offset.m3926minusMKHz9U(change1.getPosition(), change2.getPosition())) < slop;
    }

    public static final boolean isPrecisePointer(PointerEvent $this$isPrecisePointer) {
        List $this$fastAll$iv = $this$isPrecisePointer.getChanges();
        int size = $this$fastAll$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastAll$iv.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (!PointerType.m5434equalsimpl0(it.getType(), PointerType.INSTANCE.m5439getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }
}
