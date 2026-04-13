package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* compiled from: DragGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4}, l = {247, 253, 972, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, InputDeviceCompat.SOURCE_GAMEPAD}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "initialDown", "awaitTouchSlop", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$drag_u2dVnAYq1g$iv", "orientation$iv", "$this$awaitDragOrUp_u2djO51t88$iv$iv", "pointer$iv$iv"}, s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$detectDragGestures$9 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
    final /* synthetic */ Function0<Unit> $onDragCancel;
    final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
    final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
    final /* synthetic */ Orientation $orientationLock;
    final /* synthetic */ Ref.LongRef $overSlop;
    final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
    float F$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DragGestureDetectorKt$detectDragGestures$9(Function0<Boolean> function0, Ref.LongRef longRef, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function02, Function1<? super PointerInputChange, Unit> function1, Continuation<? super DragGestureDetectorKt$detectDragGestures$9> continuation) {
        super(2, continuation);
        this.$shouldAwaitTouchSlop = function0;
        this.$overSlop = longRef;
        this.$orientationLock = orientation;
        this.$onDragStart = function3;
        this.$onDrag = function2;
        this.$onDragCancel = function02;
        this.$onDragEnd = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureDetectorKt$detectDragGestures$9 dragGestureDetectorKt$detectDragGestures$9 = new DragGestureDetectorKt$detectDragGestures$9(this.$shouldAwaitTouchSlop, this.$overSlop, this.$orientationLock, this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
        dragGestureDetectorKt$detectDragGestures$9.L$0 = obj;
        return dragGestureDetectorKt$detectDragGestures$9;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
        return ((DragGestureDetectorKt$detectDragGestures$9) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x039d, code lost:
    
        if (r0 == false) goto L125;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:106:0x020c, B:93:0x01cf], limit reached: 142 */
    /* JADX WARN: Path cross not found for [B:37:0x0343, B:49:0x0379], limit reached: 142 */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0103 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x02e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x032b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0166 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x02fc  */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v12, types: [androidx.compose.ui.input.pointer.PointerEventPass, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x0228 -> B:63:0x0265). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0255 -> B:60:0x025b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x02e2 -> B:7:0x02e7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x0131 -> B:63:0x0265). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x01b7 -> B:63:0x0265). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:85:0x01c4 -> B:63:0x0265). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x0201 -> B:63:0x0265). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instructions count: 1042
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
