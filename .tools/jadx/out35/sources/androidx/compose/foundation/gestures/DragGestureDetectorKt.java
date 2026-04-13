package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* compiled from: DragGestureDetector.kt */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a5\u0010\r\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000fH\u0082Hø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0013\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\f\u001aa\u0010\u0015\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u001726\u0010\u0018\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001aY\u0010!\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a!\u0010%\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b&\u0010\f\u001aM\u0010'\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u0019H\u0082Hø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001aY\u0010-\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b.\u0010$\u001a!\u0010/\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b0\u0010\f\u001aa\u00101\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u001726\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@ø\u0001\u0000¢\u0006\u0004\b2\u0010 \u001aY\u00103\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b4\u0010$\u001a\u0080\u0001\u00105\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001aà\u0001\u00105\u001a\u00020\u001e*\u0002062K\u00107\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(A\u0012\u0004\u0012\u00020\u001e0>2!\u00108\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001e0\u000f2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e092\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u0010092\b\u0010C\u001a\u0004\u0018\u00010)26\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@¢\u0006\u0002\u0010D\u001a\u0080\u0001\u0010E\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a\u0080\u0001\u0010F\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010G\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a\u0080\u0001\u0010H\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010I\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a3\u0010J\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bK\u0010\u0012\u001aS\u0010J\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000f2\b\u0010(\u001a\u0004\u0018\u00010)2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000fH\u0080Hø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a3\u0010O\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bP\u0010\u0012\u001a\u001e\u0010Q\u001a\u00020\u0010*\u00020R2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a\u001e\u0010U\u001a\u00020\u0005*\u00020V2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000ø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a3\u0010Y\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bZ\u0010\u0012\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006["}, d2 = {"defaultTouchSlop", "Landroidx/compose/ui/unit/Dp;", "F", "mouseSlop", "mouseToTouchSlopRatio", "", "awaitDragOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrUp", "hasDragged", "Lkotlin/Function1;", "", "awaitDragOrUp-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalDragOrCancellation", "awaitHorizontalDragOrCancellation-rnUCldI", "awaitHorizontalPointerSlopOrCancellation", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "onPointerSlopReached", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "change", "overSlop", "", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalTouchSlopOrCancellation", "onTouchSlopReached", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitLongPressOrCancellation", "awaitLongPressOrCancellation-rnUCldI", "awaitPointerSlopOrCancellation", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/ui/geometry/Offset;", "awaitPointerSlopOrCancellation-pn7EDYM", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitTouchSlopOrCancellation", "awaitTouchSlopOrCancellation-jO51t88", "awaitVerticalDragOrCancellation", "awaitVerticalDragOrCancellation-rnUCldI", "awaitVerticalPointerSlopOrCancellation", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "awaitVerticalTouchSlopOrCancellation", "awaitVerticalTouchSlopOrCancellation-jO51t88", "detectDragGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "onDragEnd", "Lkotlin/Function0;", "onDragCancel", "onDrag", "dragAmount", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function3;", "down", "slopTriggerChange", "overSlopOffset", "shouldAwaitTouchSlop", "orientationLock", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "detectHorizontalDragGestures", "onHorizontalDrag", "detectVerticalDragGestures", "onVerticalDrag", "drag", "drag-jO51t88", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "horizontalDrag", "horizontalDrag-jO51t88", "isPointerUp", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "pointerSlop", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "verticalDrag", "verticalDrag-jO51t88", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DragGestureDetectorKt {
    private static final float mouseToTouchSlopRatio;
    private static final float mouseSlop = Dp.m6626constructorimpl((float) 0.125d);
    private static final float defaultTouchSlop = Dp.m6626constructorimpl(18);

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01ae A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ba A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x01a1 -> B:12:0x01a8). Please report as a decompilation issue!!! */
    /* renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m407awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m407awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x004f -> B:12:0x0052). Please report as a decompilation issue!!! */
    /* renamed from: drag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m413dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, long r8, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r10, kotlin.coroutines.Continuation<? super java.lang.Boolean> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L3b;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r8 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r8
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r11
            goto L52
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            r4 = r8
            r8 = r7
            r7 = r10
        L41:
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r9 = m400awaitDragOrCancellationrnUCldI(r8, r4, r0)
            if (r9 != r1) goto L4f
            return r1
        L4f:
            r6 = r11
            r11 = r9
            r9 = r6
        L52:
            androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
            if (r11 != 0) goto L5c
            r10 = 0
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)
            return r10
        L5c:
            boolean r10 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r11)
            if (r10 == 0) goto L67
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r10
        L67:
            r7.invoke(r11)
            long r4 = r11.getId()
            r11 = r9
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m413dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0100, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.positionChangedIgnoreConsumed(r15) != false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0114 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0070 -> B:12:0x007a). Please report as a decompilation issue!!! */
    /* renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m400awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m400awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m418invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m418invokek4lQ0M(long it) {
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$4
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Function0 function03 = function02;
        return detectDragGestures(pointerInputScope, function1, function0, function03, function2, continuation);
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, final Function1<? super Offset, Unit> function1, final Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super Unit> continuation) {
        Object detectDragGestures = detectDragGestures($this$detectDragGestures, new Function3<PointerInputChange, PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
                m419invoke0AR0LA0(pointerInputChange, pointerInputChange2, offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-0AR0LA0, reason: not valid java name */
            public final void m419invoke0AR0LA0(PointerInputChange pointerInputChange, PointerInputChange slopTriggerChange, long j) {
                function1.invoke(Offset.m3911boximpl(slopTriggerChange.getPosition()));
            }
        }, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                invoke2(pointerInputChange);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PointerInputChange it) {
                function0.invoke();
            }
        }, function02, new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return true;
            }
        }, null, function2, continuation);
        return detectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectDragGestures : Unit.INSTANCE;
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function02, Orientation orientationLock, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super Unit> continuation) {
        Ref.LongRef overSlop = new Ref.LongRef();
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGestures, new DragGestureDetectorKt$detectDragGestures$9(function02, overSlop, orientationLock, function3, function2, function0, function1, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectDragGesturesAfterLongPress$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m420invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m420invokek4lQ0M(long it) {
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$4
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Function0 function03 = function02;
        return detectDragGesturesAfterLongPress(pointerInputScope, function1, function0, function03, function2, continuation);
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope $this$detectDragGesturesAfterLongPress, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super Unit> continuation) {
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGesturesAfterLongPress, new DragGestureDetectorKt$detectDragGesturesAfterLongPress$5(function1, function0, function02, function2, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01bf A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x01b2 -> B:12:0x01b9). Please report as a decompilation issue!!! */
    /* renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m410awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m410awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01bd A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x01ae -> B:12:0x01b7). Please report as a decompilation issue!!! */
    /* renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m409awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m409awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0155, code lost:
    
        if (r0 == false) goto L57;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0094 -> B:12:0x00a2). Please report as a decompilation issue!!! */
    /* renamed from: verticalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m417verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r24, long r25, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r27, kotlin.coroutines.Continuation<? super java.lang.Boolean> r28) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m417verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x010e, code lost:
    
        if (r1 == false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0121 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0070 -> B:12:0x007a). Please report as a decompilation issue!!! */
    /* renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m408awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m408awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectVerticalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m422invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m422invokek4lQ0M(long it) {
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$4
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Function0 function03 = function02;
        return detectVerticalDragGestures(pointerInputScope, function1, function0, function03, function2, continuation);
    }

    public static final Object detectVerticalDragGestures(PointerInputScope $this$detectVerticalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectVerticalDragGestures, new DragGestureDetectorKt$detectVerticalDragGestures$5(function1, function2, function0, function02, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01bf A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x01b2 -> B:12:0x01b9). Please report as a decompilation issue!!! */
    /* renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m404awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m404awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01bd A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x01ae -> B:12:0x01b7). Please report as a decompilation issue!!! */
    /* renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m403awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m403awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0155, code lost:
    
        if (r0 == false) goto L57;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0094 -> B:12:0x00a2). Please report as a decompilation issue!!! */
    /* renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m414horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r24, long r25, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r27, kotlin.coroutines.Continuation<? super java.lang.Boolean> r28) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m414horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x010e, code lost:
    
        if (r1 == false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0121 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0070 -> B:12:0x007a). Please report as a decompilation issue!!! */
    /* renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m402awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m402awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectHorizontalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m421invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m421invokek4lQ0M(long it) {
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$4
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Function0 function03 = function02;
        return detectHorizontalDragGestures(pointerInputScope, function1, function0, function03, function2, continuation);
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope $this$detectHorizontalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectHorizontalDragGestures, new DragGestureDetectorKt$detectHorizontalDragGestures$5(function1, function2, function0, function02, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0167, code lost:
    
        if (r0 == false) goto L57;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x016c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Type inference failed for: r19v2, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x009d -> B:12:0x00ac). Please report as a decompilation issue!!! */
    /* renamed from: drag-VnAYq1g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m411dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r24, long r25, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r27, androidx.compose.foundation.gestures.Orientation r28, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r29, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r30) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m411dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v2, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m412dragVnAYq1g$$forInline(AwaitPointerEventScope $this$drag_u2dVnAYq1g, long pointerId, Function1<? super PointerInputChange, Unit> function1, Orientation orientation, Function1<? super PointerInputChange, Boolean> function12, Continuation<? super PointerInputChange> continuation) {
        PointerEventPass pointerEventPass;
        int i;
        AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv;
        Object it$iv$iv;
        ?? r20;
        Object it$iv$iv2;
        int i2 = 0;
        PointerEventPass pointerEventPass2 = null;
        if (m415isPointerUpDmW0f2w($this$drag_u2dVnAYq1g.getCurrentEvent(), pointerId)) {
            return null;
        }
        long pointer = pointerId;
        while (true) {
            AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv2 = $this$drag_u2dVnAYq1g;
            Ref.LongRef pointer$iv = new Ref.LongRef();
            pointer$iv.element = pointer;
            while (true) {
                PointerEvent event$iv = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88$iv2, pointerEventPass2, continuation, 1, pointerEventPass2);
                List $this$fastFirstOrNull$iv$iv = event$iv.getChanges();
                pointerEventPass = pointerEventPass2;
                int size = $this$fastFirstOrNull$iv$iv.size();
                int index$iv$iv$iv = 0;
                while (true) {
                    if (index$iv$iv$iv >= size) {
                        i = i2;
                        $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                        it$iv$iv = pointerEventPass;
                        break;
                    }
                    Object item$iv$iv$iv = $this$fastFirstOrNull$iv$iv.get(index$iv$iv$iv);
                    it$iv$iv = item$iv$iv$iv;
                    PointerInputChange it$iv = (PointerInputChange) it$iv$iv;
                    i = i2;
                    $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                    if (Boolean.valueOf(PointerId.m5344equalsimpl0(it$iv.getId(), pointer$iv.element)).booleanValue()) {
                        break;
                    }
                    index$iv$iv$iv++;
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                }
                r20 = (PointerInputChange) it$iv$iv;
                if (r20 == 0) {
                    r20 = pointerEventPass;
                    break;
                }
                if (PointerEventKt.changedToUpIgnoreConsumed(r20)) {
                    List $this$fastFirstOrNull$iv$iv2 = event$iv.getChanges();
                    int index$iv$iv$iv2 = 0;
                    int size2 = $this$fastFirstOrNull$iv$iv2.size();
                    while (true) {
                        if (index$iv$iv$iv2 >= size2) {
                            it$iv$iv2 = pointerEventPass;
                            break;
                        }
                        Object item$iv$iv$iv2 = $this$fastFirstOrNull$iv$iv2.get(index$iv$iv$iv2);
                        it$iv$iv2 = item$iv$iv$iv2;
                        PointerInputChange it$iv2 = (PointerInputChange) it$iv$iv2;
                        if (Boolean.valueOf(it$iv2.getPressed()).booleanValue()) {
                            break;
                        }
                        index$iv$iv$iv2++;
                    }
                    PointerInputChange otherDown$iv = (PointerInputChange) it$iv$iv2;
                    if (otherDown$iv == null) {
                        break;
                    }
                    pointer$iv.element = otherDown$iv.getId();
                    pointerEventPass2 = pointerEventPass;
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                } else {
                    PointerInputChange it = (PointerInputChange) r20;
                    long positionChange = PointerEventKt.positionChangeIgnoreConsumed(it);
                    float motionChange = orientation == null ? Offset.m3920getDistanceimpl(positionChange) : orientation == Orientation.Vertical ? Offset.m3923getYimpl(positionChange) : Offset.m3922getXimpl(positionChange);
                    if (Boolean.valueOf(!(motionChange == 0.0f)).booleanValue()) {
                        break;
                    }
                    pointerEventPass2 = pointerEventPass;
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                }
            }
            if (r20 == 0) {
                return pointerEventPass;
            }
            PointerInputChange change = r20;
            if (function12.invoke(change).booleanValue()) {
                return pointerEventPass;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(change)) {
                return change;
            }
            function1.invoke(change);
            pointer = change.getId();
            pointerEventPass2 = pointerEventPass;
            i2 = i;
        }
    }

    /* renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m401awaitDragOrUpjO51t88(AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88, long pointerId, Function1<? super PointerInputChange, Boolean> function1, Continuation<? super PointerInputChange> continuation) {
        Object obj;
        PointerEvent event;
        Object it$iv;
        Object obj2;
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = pointerId;
        while (true) {
            Object obj3 = null;
            PointerEvent event2 = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88, null, continuation, 1, null);
            List $this$fastFirstOrNull$iv = event2.getChanges();
            int index$iv$iv = 0;
            int size = $this$fastFirstOrNull$iv.size();
            while (true) {
                if (index$iv$iv >= size) {
                    obj = obj3;
                    event = event2;
                    it$iv = obj;
                    break;
                }
                it$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) it$iv;
                obj = obj3;
                event = event2;
                if (Boolean.valueOf(PointerId.m5344equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv++;
                obj3 = obj;
                event2 = event;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return obj;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List $this$fastFirstOrNull$iv2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = $this$fastFirstOrNull$iv2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        obj2 = obj;
                        break;
                    }
                    Object item$iv$iv = $this$fastFirstOrNull$iv2.get(index$iv$iv2);
                    PointerInputChange it2 = (PointerInputChange) item$iv$iv;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        obj2 = item$iv$iv;
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) obj2;
                if (otherDown == null) {
                    return dragEvent;
                }
                pointer.element = otherDown.getId();
            } else if (function1.invoke(dragEvent).booleanValue()) {
                return dragEvent;
            }
        }
    }

    /* renamed from: awaitPointerSlopOrCancellation-pn7EDYM, reason: not valid java name */
    private static final Object m406awaitPointerSlopOrCancellationpn7EDYM(AwaitPointerEventScope $this$awaitPointerSlopOrCancellation_u2dpn7EDYM, long pointerId, int pointerType, Orientation orientation, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super PointerInputChange> continuation) {
        Object it$iv;
        Object it$iv2;
        PointerEventPass pointerEventPass = null;
        if (m415isPointerUpDmW0f2w($this$awaitPointerSlopOrCancellation_u2dpn7EDYM.getCurrentEvent(), pointerId)) {
            return null;
        }
        float touchSlop = m416pointerSlopE8SPZFQ($this$awaitPointerSlopOrCancellation_u2dpn7EDYM.getViewConfiguration(), pointerType);
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = pointerId;
        TouchSlopDetector touchSlopDetector = new TouchSlopDetector(orientation);
        while (true) {
            PointerEvent event = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitPointerSlopOrCancellation_u2dpn7EDYM, pointerEventPass, continuation, 1, pointerEventPass);
            List $this$fastFirstOrNull$iv = event.getChanges();
            PointerEventPass pointerEventPass2 = pointerEventPass;
            int size = $this$fastFirstOrNull$iv.size();
            int index$iv$iv = 0;
            while (true) {
                if (index$iv$iv >= size) {
                    it$iv = pointerEventPass2;
                    break;
                }
                Object item$iv$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                int index$iv$iv2 = index$iv$iv;
                int i = size;
                if (Boolean.valueOf(PointerId.m5344equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv = index$iv$iv2 + 1;
                size = i;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return pointerEventPass2;
            }
            if (dragEvent.isConsumed()) {
                return pointerEventPass2;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List $this$fastFirstOrNull$iv2 = event.getChanges();
                int index$iv$iv3 = 0;
                int size2 = $this$fastFirstOrNull$iv2.size();
                while (true) {
                    if (index$iv$iv3 >= size2) {
                        it$iv2 = pointerEventPass2;
                        break;
                    }
                    Object item$iv$iv2 = $this$fastFirstOrNull$iv2.get(index$iv$iv3);
                    it$iv2 = item$iv$iv2;
                    PointerInputChange it2 = (PointerInputChange) it$iv2;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        break;
                    }
                    index$iv$iv3++;
                }
                PointerInputChange otherDown = (PointerInputChange) it$iv2;
                if (otherDown == null) {
                    return pointerEventPass2;
                }
                pointer.element = otherDown.getId();
            } else {
                Offset postSlopOffset = touchSlopDetector.m495addPointerInputChangeGcwITfU(dragEvent, touchSlop);
                if (postSlopOffset != null) {
                    function2.invoke(dragEvent, postSlopOffset);
                    if (dragEvent.isConsumed()) {
                        return dragEvent;
                    }
                    touchSlopDetector.reset();
                } else {
                    $this$awaitPointerSlopOrCancellation_u2dpn7EDYM.awaitPointerEvent(PointerEventPass.Final, continuation);
                    if (dragEvent.isConsumed()) {
                        return pointerEventPass2;
                    }
                }
            }
            pointerEventPass = pointerEventPass2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m405awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r1 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r1 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r2 = r1.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            switch(r3) {
                case 0: goto L42;
                case 1: goto L30;
                default: goto L27;
            }
        L27:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            java.lang.Object r0 = r1.L$1
            r3 = r0
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r0 = r1.L$0
            r5 = r0
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
            kotlin.ResultKt.throwOnFailure(r2)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3f
            goto Lc1
        L3f:
            r0 = move-exception
            goto Lc5
        L42:
            kotlin.ResultKt.throwOnFailure(r2)
            r3 = r17
            r5 = r18
            androidx.compose.ui.input.pointer.PointerEvent r7 = r3.getCurrentEvent()
            boolean r7 = m415isPointerUpDmW0f2w(r7, r5)
            if (r7 == 0) goto L54
            return r4
        L54:
            androidx.compose.ui.input.pointer.PointerEvent r7 = r3.getCurrentEvent()
            java.util.List r7 = r7.getChanges()
            r8 = 0
            r9 = 0
            r10 = 0
            int r11 = r7.size()
        L66:
            if (r10 >= r11) goto L8a
            java.lang.Object r12 = r7.get(r10)
            r13 = r12
            r14 = 0
            r15 = r13
            androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
            r16 = 0
            r18 = r7
            r17 = r8
            long r7 = r15.getId()
            boolean r7 = androidx.compose.ui.input.pointer.PointerId.m5344equalsimpl0(r7, r5)
            if (r7 == 0) goto L82
            goto L90
        L82:
            int r10 = r10 + 1
            r8 = r17
            r7 = r18
            goto L66
        L8a:
            r18 = r7
            r17 = r8
            r13 = r4
        L90:
            androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
            if (r13 != 0) goto L95
            return r4
        L95:
            r5 = r13
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            r7.element = r5
            androidx.compose.ui.platform.ViewConfiguration r8 = r3.getViewConfiguration()
            long r8 = r8.getLongPressTimeoutMillis()
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2 r10 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            r10.<init>(r7, r6, r4)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            r1.L$0 = r5     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            r1.L$1 = r6     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            r11 = 1
            r1.label = r11     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            java.lang.Object r10 = r3.withTimeout(r8, r10, r1)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> Lc3
            if (r10 != r0) goto Lc0
            return r0
        Lc0:
            r3 = r6
        Lc1:
            goto Lce
        Lc3:
            r0 = move-exception
            r3 = r6
        Lc5:
            T r0 = r3.element
            androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
            if (r0 != 0) goto Lcd
            r4 = r5
            goto Lce
        Lcd:
            r4 = r0
        Lce:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m405awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m415isPointerUpDmW0f2w(PointerEvent $this$isPointerUp_u2dDmW0f2w, long pointerId) {
        Object it$iv;
        List $this$fastFirstOrNull$iv = $this$isPointerUp_u2dDmW0f2w.getChanges();
        int index$iv$iv = 0;
        int size = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                if (PointerId.m5344equalsimpl0(it.getId(), pointerId)) {
                    break;
                }
                index$iv$iv++;
            } else {
                it$iv = null;
                break;
            }
        }
        PointerInputChange pointerInputChange = (PointerInputChange) it$iv;
        boolean z = false;
        if (pointerInputChange != null && pointerInputChange.getPressed()) {
            z = true;
        }
        return !z;
    }

    static {
        float arg0$iv = mouseSlop;
        float other$iv = defaultTouchSlop;
        mouseToTouchSlopRatio = arg0$iv / other$iv;
    }

    /* renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m416pointerSlopE8SPZFQ(ViewConfiguration $this$pointerSlop_u2dE8SPZFQ, int pointerType) {
        return PointerType.m5434equalsimpl0(pointerType, PointerType.INSTANCE.m5439getMouseT8wyACA()) ? $this$pointerSlop_u2dE8SPZFQ.getTouchSlop() * mouseToTouchSlopRatio : $this$pointerSlop_u2dE8SPZFQ.getTouchSlop();
    }
}
