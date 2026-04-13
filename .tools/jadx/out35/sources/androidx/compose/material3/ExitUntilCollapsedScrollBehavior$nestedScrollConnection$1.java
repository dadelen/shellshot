package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"androidx/compose/material3/ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitUntilCollapsedScrollBehavior this$0;

    ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1(ExitUntilCollapsedScrollBehavior $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo748onPreScrollOzD1aCk(long available, int source) {
        if (this.this$0.getCanScroll().invoke().booleanValue() && Offset.m3923getYimpl(available) <= 0.0f) {
            float prevHeightOffset = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m3923getYimpl(available));
            return !((prevHeightOffset > this.this$0.getState().getHeightOffset() ? 1 : (prevHeightOffset == this.this$0.getState().getHeightOffset() ? 0 : -1)) == 0) ? Offset.m3916copydBAh8RU$default(available, 0.0f, 0.0f, 2, null) : Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m3938getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo474onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        TopAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m3923getYimpl(consumed));
        if (Offset.m3923getYimpl(available) < 0.0f || Offset.m3923getYimpl(consumed) < 0.0f) {
            float oldHeightOffset = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m3923getYimpl(consumed));
            return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - oldHeightOffset);
        }
        if ((Offset.m3923getYimpl(consumed) == 0.0f) && Offset.m3923getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        if (Offset.m3923getYimpl(available) > 0.0f) {
            float oldHeightOffset2 = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m3923getYimpl(available));
            return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - oldHeightOffset2);
        }
        return Offset.INSTANCE.m3938getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo473onPostFlingRZ2iAVY(long r11, long r13, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r15
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = (androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = new androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1
            r0.<init>(r10, r15)
        L19:
            r15 = r0
            java.lang.Object r0 = r15.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r15.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L35;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2d:
            long r11 = r15.J$0
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r11
            r11 = r0
            goto L82
        L35:
            long r11 = r15.J$0
            java.lang.Object r13 = r15.L$0
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 r13 = (androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r11
            r12 = r13
            r13 = r8
            r11 = r0
            goto L56
        L43:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r10
            r15.L$0 = r2
            r15.J$0 = r13
            r3 = 1
            r15.label = r3
            java.lang.Object r11 = super.mo473onPostFlingRZ2iAVY(r11, r13, r15)
            r12 = r10
            if (r11 != r1) goto L56
            return r1
        L56:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r2 = r11.getPackedValue()
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r11 = r12.this$0
            androidx.compose.material3.TopAppBarState r11 = r11.getState()
            float r4 = androidx.compose.ui.unit.Velocity.m6864getYimpl(r13)
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r5 = r12.this$0
            androidx.compose.animation.core.DecayAnimationSpec r5 = r5.getFlingAnimationSpec()
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r6 = r12.this$0
            androidx.compose.animation.core.AnimationSpec r6 = r6.getSnapAnimationSpec()
            r7 = 0
            r15.L$0 = r7
            r15.J$0 = r2
            r7 = 2
            r15.label = r7
            java.lang.Object r11 = androidx.compose.material3.AppBarKt.access$settleAppBar(r11, r4, r5, r6, r15)
            if (r11 != r1) goto L82
            return r1
        L82:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r11 = r11.getPackedValue()
            long r11 = androidx.compose.ui.unit.Velocity.m6867plusAH228Gc(r2, r11)
            androidx.compose.ui.unit.Velocity r11 = androidx.compose.ui.unit.Velocity.m6854boximpl(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1.mo473onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
