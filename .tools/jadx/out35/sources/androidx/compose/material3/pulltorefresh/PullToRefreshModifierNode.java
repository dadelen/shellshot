package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: PullToRefresh.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u00106\u001a\u00020\bH\u0082@¢\u0006\u0002\u00107J\u000e\u00108\u001a\u00020\bH\u0082@¢\u0006\u0002\u00107J\b\u00109\u001a\u00020\u0010H\u0002J\u001a\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\b\u0010?\u001a\u00020\bH\u0016J*\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010B\u001a\u00020CH\u0016ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020G2\u0006\u0010<\u001a\u00020GH\u0096@ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\"\u0010J\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010B\u001a\u00020CH\u0016ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ\u0016\u0010M\u001a\u00020\u00102\u0006\u0010N\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010OJ\u0006\u0010P\u001a\u00020\bR\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R+\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u001b\"\u0004\b\u001e\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010\f\u001a\u00020\rX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0017R\u0014\u0010.\u001a\u00020/8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R+\u00102\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u0019\u001a\u0004\b3\u0010\u0012\"\u0004\b4\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Q"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "isRefreshing", "", "onRefresh", "Lkotlin/Function0;", "", "enabled", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "threshold", "Landroidx/compose/ui/unit/Dp;", "(ZLkotlin/jvm/functions/Function0;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "adjustedDistancePulled", "", "getAdjustedDistancePulled", "()F", "<set-?>", "distancePulled", "getDistancePulled", "setDistancePulled", "(F)V", "distancePulled$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getEnabled", "()Z", "setEnabled", "(Z)V", "setRefreshing", "nestedScrollNode", "Landroidx/compose/ui/node/DelegatableNode;", "getOnRefresh", "()Lkotlin/jvm/functions/Function0;", "setOnRefresh", "(Lkotlin/jvm/functions/Function0;)V", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "getState", "()Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "setState", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;)V", "getThreshold-D9Ej5fM", "setThreshold-0680j_4", "F", "thresholdPx", "", "getThresholdPx", "()I", "verticalOffset", "getVerticalOffset", "setVerticalOffset", "verticalOffset$delegate", "animateToHidden", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToThreshold", "calculateVerticalOffset", "consumeAvailableOffset", "Landroidx/compose/ui/geometry/Offset;", "available", "consumeAvailableOffset-MK-Hz9U", "(J)J", "onAttach", "onPostScroll", "consumed", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "onRelease", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PullToRefreshModifierNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, NestedScrollConnection {
    public static final int $stable = 8;

    /* renamed from: distancePulled$delegate, reason: from kotlin metadata */
    private final MutableFloatState distancePulled;
    private boolean enabled;
    private boolean isRefreshing;
    private DelegatableNode nestedScrollNode;
    private Function0<Unit> onRefresh;
    private PullToRefreshState state;
    private float threshold;

    /* renamed from: verticalOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState verticalOffset;

    public /* synthetic */ PullToRefreshModifierNode(boolean z, Function0 function0, boolean z2, PullToRefreshState pullToRefreshState, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, z2, pullToRefreshState, f);
    }

    /* renamed from: isRefreshing, reason: from getter */
    public final boolean getIsRefreshing() {
        return this.isRefreshing;
    }

    public final void setRefreshing(boolean z) {
        this.isRefreshing = z;
    }

    public final Function0<Unit> getOnRefresh() {
        return this.onRefresh;
    }

    public final void setOnRefresh(Function0<Unit> function0) {
        this.onRefresh = function0;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final PullToRefreshState getState() {
        return this.state;
    }

    public final void setState(PullToRefreshState pullToRefreshState) {
        this.state = pullToRefreshState;
    }

    /* renamed from: getThreshold-D9Ej5fM, reason: not valid java name and from getter */
    public final float getThreshold() {
        return this.threshold;
    }

    /* renamed from: setThreshold-0680j_4, reason: not valid java name */
    public final void m3006setThreshold0680j_4(float f) {
        this.threshold = f;
    }

    private PullToRefreshModifierNode(boolean isRefreshing, Function0<Unit> function0, boolean enabled, PullToRefreshState state, float threshold) {
        this.isRefreshing = isRefreshing;
        this.onRefresh = function0;
        this.enabled = enabled;
        this.state = state;
        this.threshold = threshold;
        this.nestedScrollNode = NestedScrollNodeKt.nestedScrollModifierNode(this, null);
        this.verticalOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.distancePulled = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getVerticalOffset() {
        FloatState $this$getValue$iv = this.verticalOffset;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setVerticalOffset(float f) {
        MutableFloatState $this$setValue$iv = this.verticalOffset;
        $this$setValue$iv.setFloatValue(f);
    }

    private final float getDistancePulled() {
        FloatState $this$getValue$iv = this.distancePulled;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setDistancePulled(float f) {
        MutableFloatState $this$setValue$iv = this.distancePulled;
        $this$setValue$iv.setFloatValue(f);
    }

    private final float getAdjustedDistancePulled() {
        return getDistancePulled() * 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getThresholdPx() {
        Density $this$_get_thresholdPx__u24lambda_u240 = (Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalDensity());
        return $this$_get_thresholdPx__u24lambda_u240.mo358roundToPx0680j_4(this.threshold);
    }

    private final float getProgress() {
        return getAdjustedDistancePulled() / getThresholdPx();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        delegate(this.nestedScrollNode);
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$onAttach$1(this, null), 3, null);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo748onPreScrollOzD1aCk(long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m5245equalsimpl0(source, NestedScrollSource.INSTANCE.m5257getUserInputWNlRxjI()) && Offset.m3923getYimpl(available) < 0.0f) {
                return m3004consumeAvailableOffsetMKHz9U(available);
            }
            return Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m3938getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo474onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m5245equalsimpl0(source, NestedScrollSource.INSTANCE.m5257getUserInputWNlRxjI())) {
                long m3004consumeAvailableOffsetMKHz9U = m3004consumeAvailableOffsetMKHz9U(available);
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$onPostScroll$1(this, null), 3, null);
                return m3004consumeAvailableOffsetMKHz9U;
            }
            return Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m3938getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo747onPreFlingQWom1Mo(long r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onPreFling$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onPreFling$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onPreFling$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onPreFling$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            float r7 = r0.F$0
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = r7
            r7 = r9
            goto L49
        L34:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r6
            float r3 = androidx.compose.ui.unit.Velocity.m6864getYimpl(r7)
            r4 = 0
            r0.F$0 = r4
            r5 = 1
            r0.label = r5
            java.lang.Object r7 = r2.onRelease(r3, r0)
            if (r7 != r1) goto L49
            return r1
        L49:
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            long r7 = androidx.compose.ui.unit.VelocityKt.Velocity(r4, r7)
            androidx.compose.ui.unit.Velocity r7 = androidx.compose.ui.unit.Velocity.m6854boximpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.mo747onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void update() {
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$update$1(this, null), 3, null);
    }

    /* renamed from: consumeAvailableOffset-MK-Hz9U, reason: not valid java name */
    private final long m3004consumeAvailableOffsetMKHz9U(long available) {
        float dragConsumed;
        if (this.isRefreshing) {
            dragConsumed = 0.0f;
        } else {
            float newOffset = RangesKt.coerceAtLeast(getDistancePulled() + Offset.m3923getYimpl(available), 0.0f);
            dragConsumed = newOffset - getDistancePulled();
            setDistancePulled(newOffset);
            setVerticalOffset(calculateVerticalOffset());
        }
        return OffsetKt.Offset(0.0f, dragConsumed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onRelease(float r8, kotlin.coroutines.Continuation<? super java.lang.Float> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L42;
                case 1: goto L38;
                case 2: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            float r8 = r0.F$0
            java.lang.Object r1 = r0.L$0
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7f
        L38:
            float r8 = r0.F$0
            java.lang.Object r1 = r0.L$0
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6a
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            boolean r5 = r2.isRefreshing
            if (r5 == 0) goto L4f
            java.lang.Float r1 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r4)
            return r1
        L4f:
            float r5 = r2.getAdjustedDistancePulled()
            int r6 = r2.getThresholdPx()
            float r6 = (float) r6
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L70
            r0.L$0 = r2
            r0.F$0 = r8
            r0.label = r3
            java.lang.Object r5 = r2.animateToThreshold(r0)
            if (r5 != r1) goto L69
            return r1
        L69:
            r1 = r2
        L6a:
            kotlin.jvm.functions.Function0<kotlin.Unit> r2 = r1.onRefresh
            r2.invoke()
            goto L80
        L70:
            r0.L$0 = r2
            r0.F$0 = r8
            r5 = 2
            r0.label = r5
            java.lang.Object r5 = r2.animateToHidden(r0)
            if (r5 != r1) goto L7e
            return r1
        L7e:
            r1 = r2
        L7f:
        L80:
            float r2 = r1.getDistancePulled()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L89
            goto L8a
        L89:
            r3 = 0
        L8a:
            if (r3 == 0) goto L8e
            r8 = r4
            goto L95
        L8e:
            int r2 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r2 >= 0) goto L94
            r8 = r4
            goto L95
        L94:
        L95:
            r1.setDistancePulled(r4)
            java.lang.Float r2 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.onRelease(float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final float calculateVerticalOffset() {
        if (getAdjustedDistancePulled() > getThresholdPx()) {
            float overshootPercent = Math.abs(getProgress()) - 1.0f;
            float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
            float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2.0d)) / 4.0f);
            float extraOffset = getThresholdPx() * tensionPercent;
            float overshootPercent2 = getThresholdPx() + extraOffset;
            return overshootPercent2;
        }
        float overshootPercent3 = getAdjustedDistancePulled();
        return overshootPercent3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateToThreshold(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2c:
            java.lang.Object r1 = r0.L$0
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L47
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            androidx.compose.material3.pulltorefresh.PullToRefreshState r3 = r2.state
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.animateToThreshold(r0)
            if (r3 != r1) goto L46
            return r1
        L46:
            r1 = r2
        L47:
            int r2 = r1.getThresholdPx()
            float r2 = (float) r2
            r1.setDistancePulled(r2)
            int r2 = r1.getThresholdPx()
            float r2 = (float) r2
            r1.setVerticalOffset(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.animateToThreshold(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateToHidden(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2c:
            java.lang.Object r1 = r0.L$0
            androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = (androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L47
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            androidx.compose.material3.pulltorefresh.PullToRefreshState r3 = r2.state
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.animateToHidden(r0)
            if (r3 != r1) goto L46
            return r1
        L46:
            r1 = r2
        L47:
            r2 = 0
            r1.setDistancePulled(r2)
            r1.setVerticalOffset(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.animateToHidden(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
