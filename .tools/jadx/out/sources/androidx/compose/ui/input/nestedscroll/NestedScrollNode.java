package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NestedScrollNode.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J#\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J*\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020$2\u0006\u0010 \u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\"\u0010,\u001a\u00020$2\u0006\u0010 \u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016ø\u0001\u0000¢\u0006\u0004\b-\u0010.J\b\u0010/\u001a\u00020\u001bH\u0002J\u0012\u00100\u001a\u00020\u001b2\b\u00101\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u00102\u001a\u00020\u001bH\u0002J\u001f\u00103\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b4R\u001a\u0010\u0004\u001a\u00020\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/ui/Modifier$Node;", "connection", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "getConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setConnection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "nestedCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getNestedCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "parentConnection", "getParentConnection", "parentNestedScrollNode", "getParentNestedScrollNode$ui_release", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "resolvedDispatcher", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "onAttach", "", "onDetach", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "resetDispatcherFields", "updateDispatcher", "newDispatcher", "updateDispatcherFields", "updateNode", "updateNode$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NestedScrollNode extends Modifier.Node implements TraversableNode, NestedScrollConnection {
    public static final int $stable = 8;
    private NestedScrollConnection connection;
    private NestedScrollDispatcher resolvedDispatcher;
    private final Object traverseKey;

    public final NestedScrollConnection getConnection() {
        return this.connection;
    }

    public final void setConnection(NestedScrollConnection nestedScrollConnection) {
        this.connection = nestedScrollConnection;
    }

    public NestedScrollNode(NestedScrollConnection connection, NestedScrollDispatcher dispatcher) {
        this.connection = connection;
        this.resolvedDispatcher = dispatcher == null ? new NestedScrollDispatcher() : dispatcher;
        this.traverseKey = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";
    }

    public final NestedScrollNode getParentNestedScrollNode$ui_release() {
        if (getIsAttached()) {
            return (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this);
        }
        return null;
    }

    private final NestedScrollConnection getParentConnection() {
        if (getIsAttached()) {
            return getParentNestedScrollNode$ui_release();
        }
        return null;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineScope getNestedCoroutineScope() {
        CoroutineScope scope;
        NestedScrollNode parentNestedScrollNode$ui_release = getParentNestedScrollNode$ui_release();
        if ((parentNestedScrollNode$ui_release == null || (scope = parentNestedScrollNode$ui_release.getNestedCoroutineScope()) == null) && (scope = this.resolvedDispatcher.getScope()) == null) {
            throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        }
        return scope;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo748onPreScrollOzD1aCk(long available, int source) {
        NestedScrollConnection parentConnection = getParentConnection();
        long parentPreConsumed = parentConnection != null ? parentConnection.mo748onPreScrollOzD1aCk(available, source) : Offset.INSTANCE.m3938getZeroF1C5BW0();
        long selfPreConsumed = this.connection.mo748onPreScrollOzD1aCk(Offset.m3926minusMKHz9U(available, parentPreConsumed), source);
        return Offset.m3927plusMKHz9U(parentPreConsumed, selfPreConsumed);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo474onPostScrollDzOQY0M(long consumed, long available, int source) {
        long selfConsumed = this.connection.mo474onPostScrollDzOQY0M(consumed, available, source);
        NestedScrollConnection parentConnection = getParentConnection();
        long parentConsumed = parentConnection != null ? parentConnection.mo474onPostScrollDzOQY0M(Offset.m3927plusMKHz9U(consumed, selfConsumed), Offset.m3926minusMKHz9U(available, selfConsumed), source) : Offset.INSTANCE.m3938getZeroF1C5BW0();
        return Offset.m3927plusMKHz9U(selfConsumed, parentConsumed);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo747onPreFlingQWom1Mo(long r10, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            r0.<init>(r9, r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L34;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            long r10 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r12)
            r3 = r10
            r10 = r12
            goto L79
        L34:
            long r10 = r0.J$0
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r3 = r12
            goto L57
        L3f:
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r9
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r2.getParentConnection()
            if (r3 == 0) goto L5e
            r0.L$0 = r2
            r0.J$0 = r10
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.mo747onPreFlingQWom1Mo(r10, r0)
            if (r3 != r1) goto L57
            return r1
        L57:
            androidx.compose.ui.unit.Velocity r3 = (androidx.compose.ui.unit.Velocity) r3
            long r3 = r3.getPackedValue()
            goto L64
        L5e:
            androidx.compose.ui.unit.Velocity$Companion r3 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r3 = r3.m6874getZero9UxMQ8M()
        L64:
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r5 = r2.connection
            long r6 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r10, r3)
            r8 = 0
            r0.L$0 = r8
            r0.J$0 = r3
            r8 = 2
            r0.label = r8
            java.lang.Object r10 = r5.mo747onPreFlingQWom1Mo(r6, r0)
            if (r10 != r1) goto L79
            return r1
        L79:
            androidx.compose.ui.unit.Velocity r10 = (androidx.compose.ui.unit.Velocity) r10
            long r10 = r10.getPackedValue()
            long r1 = androidx.compose.ui.unit.Velocity.m6867plusAH228Gc(r3, r10)
            androidx.compose.ui.unit.Velocity r1 = androidx.compose.ui.unit.Velocity.m6854boximpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo747onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo473onPostFlingRZ2iAVY(long r10, long r12, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            r0.<init>(r9, r14)
        L19:
            r14 = r0
            java.lang.Object r0 = r14.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r14.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L35;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            long r10 = r14.J$0
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r10
            r10 = r0
            goto L81
        L35:
            long r10 = r14.J$1
            long r12 = r14.J$0
            java.lang.Object r2 = r14.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r2
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r0
            goto L5c
        L42:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r9
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r2.connection
            r14.L$0 = r2
            r14.J$0 = r10
            r14.J$1 = r12
            r4 = 1
            r14.label = r4
            r9 = r3
            java.lang.Object r3 = r9.mo473onPostFlingRZ2iAVY(r10, r12, r14)
            if (r3 != r1) goto L59
            return r1
        L59:
            r7 = r12
            r12 = r10
            r10 = r7
        L5c:
            androidx.compose.ui.unit.Velocity r3 = (androidx.compose.ui.unit.Velocity) r3
            long r3 = r3.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r2 = r2.getParentConnection()
            if (r2 == 0) goto L88
            r5 = r10
            long r10 = androidx.compose.ui.unit.Velocity.m6867plusAH228Gc(r12, r3)
            long r12 = androidx.compose.ui.unit.Velocity.m6866minusAH228Gc(r5, r3)
            r5 = 0
            r14.L$0 = r5
            r14.J$0 = r3
            r5 = 2
            r14.label = r5
            r9 = r2
            java.lang.Object r10 = r9.mo473onPostFlingRZ2iAVY(r10, r12, r14)
            if (r10 != r1) goto L81
            return r1
        L81:
            androidx.compose.ui.unit.Velocity r10 = (androidx.compose.ui.unit.Velocity) r10
            long r10 = r10.getPackedValue()
            goto L8f
        L88:
            r5 = r10
            androidx.compose.ui.unit.Velocity$Companion r10 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r10 = r10.m6874getZero9UxMQ8M()
        L8f:
            long r12 = androidx.compose.ui.unit.Velocity.m6867plusAH228Gc(r3, r10)
            androidx.compose.ui.unit.Velocity r12 = androidx.compose.ui.unit.Velocity.m6854boximpl(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo473onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void updateDispatcher(NestedScrollDispatcher newDispatcher) {
        resetDispatcherFields();
        if (newDispatcher == null) {
            this.resolvedDispatcher = new NestedScrollDispatcher();
        } else if (!Intrinsics.areEqual(newDispatcher, this.resolvedDispatcher)) {
            this.resolvedDispatcher = newDispatcher;
        }
        if (getIsAttached()) {
            updateDispatcherFields();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDispatcherFields();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        resetDispatcherFields();
    }

    private final void updateDispatcherFields() {
        this.resolvedDispatcher.setNestedScrollNode$ui_release(this);
        this.resolvedDispatcher.setCalculateNestedScrollScope$ui_release(new Function0<CoroutineScope>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollNode$updateDispatcherFields$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                CoroutineScope nestedCoroutineScope;
                nestedCoroutineScope = NestedScrollNode.this.getNestedCoroutineScope();
                return nestedCoroutineScope;
            }
        });
        this.resolvedDispatcher.setScope$ui_release(getCoroutineScope());
    }

    private final void resetDispatcherFields() {
        if (this.resolvedDispatcher.getNestedScrollNode() == this) {
            this.resolvedDispatcher.setNestedScrollNode$ui_release(null);
        }
    }

    public final void updateNode$ui_release(NestedScrollConnection connection, NestedScrollDispatcher dispatcher) {
        this.connection = connection;
        updateDispatcher(dispatcher);
    }
}
