package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* compiled from: SuspendingPointerInputFilter.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0013H¦@¢\u0006\u0002\u0010\u0014JE\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182'\u0010\u0019\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001a¢\u0006\u0002\b\u001dH\u0096@¢\u0006\u0002\u0010\u001eJG\u0010\u001f\u001a\u0004\u0018\u0001H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182'\u0010\u0019\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001a¢\u0006\u0002\b\u001dH\u0096@¢\u0006\u0002\u0010\u001eR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00020\u000bX¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006 À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/unit/Density;", "currentEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "getCurrentEvent", "()Landroidx/compose/ui/input/pointer/PointerEvent;", "extendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "()J", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "awaitPointerEvent", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeout", "T", "timeMillis", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeoutOrNull", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public interface AwaitPointerEventScope extends Density {
    Object awaitPointerEvent(PointerEventPass pointerEventPass, Continuation<? super PointerEvent> continuation);

    PointerEvent getCurrentEvent();

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    long mo5273getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    default <T> Object withTimeout(long j, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return withTimeout$suspendImpl(this, j, function2, continuation);
    }

    default <T> Object withTimeoutOrNull(long j, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return withTimeoutOrNull$suspendImpl(this, j, function2, continuation);
    }

    /* compiled from: SuspendingPointerInputFilter.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5275roundToPxR2X_6o(AwaitPointerEventScope $this, long $receiver) {
            return AwaitPointerEventScope.super.mo357roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5276roundToPx0680j_4(AwaitPointerEventScope $this, float $receiver) {
            return AwaitPointerEventScope.super.mo358roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5277toDpGaN1DYA(AwaitPointerEventScope $this, long $receiver) {
            return AwaitPointerEventScope.super.mo359toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5278toDpu2uoSUM(AwaitPointerEventScope $this, float $receiver) {
            return AwaitPointerEventScope.super.mo360toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5279toDpu2uoSUM(AwaitPointerEventScope $this, int $receiver) {
            return AwaitPointerEventScope.super.mo361toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5280toDpSizekrfVVM(AwaitPointerEventScope $this, long $receiver) {
            return AwaitPointerEventScope.super.mo362toDpSizekrfVVM($receiver);
        }

        @Deprecated
        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5281toPxR2X_6o(AwaitPointerEventScope $this, long $receiver) {
            return AwaitPointerEventScope.super.mo363toPxR2X_6o($receiver);
        }

        @Deprecated
        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5282toPx0680j_4(AwaitPointerEventScope $this, float $receiver) {
            return AwaitPointerEventScope.super.mo364toPx0680j_4($receiver);
        }

        @Deprecated
        public static Rect toRect(AwaitPointerEventScope $this, DpRect $receiver) {
            return AwaitPointerEventScope.super.toRect($receiver);
        }

        @Deprecated
        /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5283toSizeXkaWNTQ(AwaitPointerEventScope $this, long $receiver) {
            return AwaitPointerEventScope.super.mo365toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5284toSp0xMU5do(AwaitPointerEventScope $this, float $receiver) {
            return AwaitPointerEventScope.super.mo366toSp0xMU5do($receiver);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5285toSpkPz2Gy4(AwaitPointerEventScope $this, float $receiver) {
            return AwaitPointerEventScope.super.mo367toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5286toSpkPz2Gy4(AwaitPointerEventScope $this, int $receiver) {
            return AwaitPointerEventScope.super.mo368toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m5274getExtendedTouchPaddingNHjbRc(AwaitPointerEventScope $this) {
            return AwaitPointerEventScope.super.mo5272getExtendedTouchPaddingNHjbRc();
        }

        @Deprecated
        public static <T> Object withTimeoutOrNull(AwaitPointerEventScope $this, long timeMillis, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
            return AwaitPointerEventScope.super.withTimeoutOrNull(timeMillis, function2, continuation);
        }

        @Deprecated
        public static <T> Object withTimeout(AwaitPointerEventScope $this, long timeMillis, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
            return AwaitPointerEventScope.super.withTimeout(timeMillis, function2, continuation);
        }
    }

    /* renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
    default long mo5272getExtendedTouchPaddingNHjbRc() {
        return Size.INSTANCE.m4000getZeroNHjbRc();
    }

    static /* synthetic */ Object awaitPointerEvent$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: awaitPointerEvent");
        }
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitPointerEventScope.awaitPointerEvent(pointerEventPass, continuation);
    }

    static /* synthetic */ <T> Object withTimeoutOrNull$suspendImpl(AwaitPointerEventScope $this, long timeMillis, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return function2.invoke($this, continuation);
    }

    static /* synthetic */ <T> Object withTimeout$suspendImpl(AwaitPointerEventScope $this, long timeMillis, Function2<? super AwaitPointerEventScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return function2.invoke($this, continuation);
    }
}
