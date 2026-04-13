package androidx.compose.ui.scrollcapture;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: ComposeScrollCaptureCallback.android.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010\u0013R\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR,\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/scrollcapture/RelativeScroller;", "", "viewportSize", "", "scrollBy", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "(ILkotlin/jvm/functions/Function2;)V", "<set-?>", "scrollAmount", "getScrollAmount", "()F", "Lkotlin/jvm/functions/Function2;", "mapOffsetToViewport", "offset", "reset", "", "delta", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollRangeIntoView", "min", "max", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollTo", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class RelativeScroller {
    private float scrollAmount;
    private final Function2<Float, Continuation<? super Float>, Object> scrollBy;
    private final int viewportSize;

    /* JADX WARN: Multi-variable type inference failed */
    public RelativeScroller(int viewportSize, Function2<? super Float, ? super Continuation<? super Float>, ? extends Object> function2) {
        this.viewportSize = viewportSize;
        this.scrollBy = function2;
    }

    public final float getScrollAmount() {
        return this.scrollAmount;
    }

    public final void reset() {
        this.scrollAmount = 0.0f;
    }

    public final Object scrollRangeIntoView(int min, int max, Continuation<? super Unit> continuation) {
        if (!(min <= max)) {
            throw new IllegalArgumentException(("Expected min=" + min + " ≤ max=" + max).toString());
        }
        if (!(max - min <= this.viewportSize)) {
            throw new IllegalArgumentException(("Expected range (" + (max - min) + ") to be ≤ viewportSize=" + this.viewportSize).toString());
        }
        if (min >= this.scrollAmount && max <= this.scrollAmount + this.viewportSize) {
            return Unit.INSTANCE;
        }
        int target = ((float) min) < this.scrollAmount ? min : max - this.viewportSize;
        Object scrollTo = scrollTo(target, continuation);
        return scrollTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollTo : Unit.INSTANCE;
    }

    public final int mapOffsetToViewport(int offset) {
        return RangesKt.coerceIn(offset - MathKt.roundToInt(this.scrollAmount), 0, this.viewportSize);
    }

    public final Object scrollTo(float offset, Continuation<? super Unit> continuation) {
        Object scrollBy = scrollBy(offset - this.scrollAmount, continuation);
        return scrollBy == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scrollBy : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scrollBy(float r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.compose.ui.scrollcapture.RelativeScroller$scrollBy$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.ui.scrollcapture.RelativeScroller$scrollBy$1 r0 = (androidx.compose.ui.scrollcapture.RelativeScroller$scrollBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.ui.scrollcapture.RelativeScroller$scrollBy$1 r0 = new androidx.compose.ui.scrollcapture.RelativeScroller$scrollBy$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.scrollcapture.RelativeScroller r7 = (androidx.compose.ui.scrollcapture.RelativeScroller) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            r7 = r8
            goto L4c
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            kotlin.jvm.functions.Function2<java.lang.Float, kotlin.coroutines.Continuation<? super java.lang.Float>, java.lang.Object> r3 = r2.scrollBy
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            r0.L$0 = r2
            r5 = 1
            r0.label = r5
            java.lang.Object r7 = r3.invoke(r4, r0)
            if (r7 != r1) goto L4c
            return r1
        L4c:
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            float r1 = r2.scrollAmount
            float r1 = r1 + r7
            r2.scrollAmount = r1
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.scrollcapture.RelativeScroller.scrollBy(float, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
