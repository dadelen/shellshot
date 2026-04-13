package androidx.compose.foundation.gestures;

import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: Scrollable.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "velocity"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1", f = "Scrollable.kt", i = {0, 1, 1, 2, 2}, l = {745, 748, 751}, m = "invokeSuspend", n = {"velocity", "velocity", "available", "velocity", "velocityLeft"}, s = {"J$0", "J$0", "J$1", "J$0", "J$1"})
/* loaded from: classes.dex */
final class ScrollingLogic$onDragStopped$performFling$1 extends SuspendLambda implements Function2<Velocity, Continuation<? super Velocity>, Object> {
    /* synthetic */ long J$0;
    long J$1;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$onDragStopped$performFling$1(ScrollingLogic scrollingLogic, Continuation<? super ScrollingLogic$onDragStopped$performFling$1> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(this.this$0, continuation);
        scrollingLogic$onDragStopped$performFling$1.J$0 = ((Velocity) obj).getPackedValue();
        return scrollingLogic$onDragStopped$performFling$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Velocity> continuation) {
        return m491invokesFctU(velocity.getPackedValue(), continuation);
    }

    /* renamed from: invoke-sF-c-tU, reason: not valid java name */
    public final Object m491invokesFctU(long j, Continuation<? super Velocity> continuation) {
        return ((ScrollingLogic$onDragStopped$performFling$1) create(Velocity.m6854boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r17
            int r2 = r1.label
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L33;
                case 2: goto L23;
                case 3: goto L14;
                default: goto Lb;
            }
        Lb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L14:
            r1 = r17
            r0 = r18
            long r2 = r1.J$1
            long r4 = r1.J$0
            kotlin.ResultKt.throwOnFailure(r0)
            r11 = r2
            r2 = r0
            goto La4
        L23:
            r1 = r17
            r2 = r18
            long r3 = r1.J$1
            long r5 = r1.J$0
            kotlin.ResultKt.throwOnFailure(r2)
            r15 = r5
            r6 = r3
            r4 = r15
            r3 = r2
            goto L82
        L33:
            r1 = r17
            r2 = r18
            long r3 = r1.J$0
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = r3
            r3 = r2
            goto L61
        L3f:
            kotlin.ResultKt.throwOnFailure(r18)
            r1 = r17
            r2 = r18
            long r3 = r1.J$0
            androidx.compose.foundation.gestures.ScrollingLogic r5 = r1.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r5 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r5)
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.J$0 = r3
            r7 = 1
            r1.label = r7
            java.lang.Object r5 = r5.m5240dispatchPreFlingQWom1Mo(r3, r6)
            if (r5 != r0) goto L5d
            return r0
        L5d:
            r15 = r3
            r3 = r2
            r2 = r5
            r4 = r15
        L61:
            androidx.compose.ui.unit.Velocity r2 = (androidx.compose.ui.unit.Velocity) r2
            long r6 = r2.getPackedValue()
            long r6 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r4, r6)
            androidx.compose.foundation.gestures.ScrollingLogic r2 = r1.this$0
            r8 = r1
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r1.J$0 = r4
            r1.J$1 = r6
            r9 = 2
            r1.label = r9
            java.lang.Object r2 = r2.m484doFlingAnimationQWom1Mo(r6, r8)
            if (r2 != r0) goto L7f
            return r0
        L7f:
            r15 = r3
            r3 = r2
            r2 = r15
        L82:
            androidx.compose.ui.unit.Velocity r3 = (androidx.compose.ui.unit.Velocity) r3
            long r11 = r3.getPackedValue()
            androidx.compose.foundation.gestures.ScrollingLogic r3 = r1.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r8 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r3)
            long r9 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r6, r11)
            r13 = r1
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r1.J$0 = r4
            r1.J$1 = r11
            r3 = 3
            r1.label = r3
            java.lang.Object r3 = r8.m5238dispatchPostFlingRZ2iAVY(r9, r11, r13)
            if (r3 != r0) goto La3
            return r0
        La3:
            r0 = r3
        La4:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r6 = r0.getPackedValue()
            long r8 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r11, r6)
            long r13 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r4, r8)
            androidx.compose.ui.unit.Velocity r0 = androidx.compose.ui.unit.Velocity.m6854boximpl(r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
