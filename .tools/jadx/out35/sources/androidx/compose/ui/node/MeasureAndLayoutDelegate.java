package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MeasureAndLayoutDelegate.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0000\u0018\u00002\u00020\u0001:\u0001SB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020)2\b\b\u0002\u0010+\u001a\u00020\bJ\"\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u001eH\u0002ø\u0001\u0000¢\u0006\u0002\b/J\"\u00100\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u001eH\u0002ø\u0001\u0000¢\u0006\u0002\b1J\b\u00102\u001a\u00020)H\u0002J\u0010\u00103\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0003H\u0002J\u0016\u00104\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00032\u0006\u00105\u001a\u00020\bJ\u0018\u00106\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00032\u0006\u00105\u001a\u00020\bH\u0002J\u0018\u00107\u001a\u00020\b2\u0010\b\u0002\u00108\u001a\n\u0012\u0004\u0012\u00020)\u0018\u000109J \u00107\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u001eø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u0006\u0010<\u001a\u00020)J\u000e\u0010=\u001a\u00020)2\u0006\u0010>\u001a\u00020\u0003J\u0018\u0010?\u001a\u00020)2\u0006\u0010>\u001a\u00020\u00032\u0006\u00105\u001a\u00020\bH\u0002J\u001f\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020\b2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020)09H\u0082\bJ\u000e\u0010C\u001a\u00020)2\u0006\u0010D\u001a\u00020\u0016J$\u0010E\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\b2\b\b\u0002\u0010F\u001a\u00020\bH\u0002J\u0010\u0010G\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0003H\u0002J\u0018\u0010H\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00032\u0006\u00105\u001a\u00020\bH\u0002J\u0018\u0010I\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\bJ\u0018\u0010K\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\bJ\u000e\u0010L\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0003J\u0018\u0010M\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\bJ\u0018\u0010N\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\bJ\u0018\u0010O\u001a\u00020)2\u0006\u0010.\u001a\u00020\u001eø\u0001\u0000¢\u0006\u0004\bP\u0010QJ\u0014\u0010R\u001a\u00020\b*\u00020\u00032\u0006\u00105\u001a\u00020\bH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u0018\u0010\u001f\u001a\u00020\b*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\"\u001a\u00020\b*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0018\u0010$\u001a\u00020\b*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010!R\u0018\u0010&\u001a\u00020\b*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006T"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "consistencyChecker", "Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "duringFullMeasureLayoutPass", "", "duringMeasureLayout", "hasPendingMeasureOrLayout", "getHasPendingMeasureOrLayout", "()Z", "hasPendingOnPositionedCallbacks", "getHasPendingOnPositionedCallbacks", "<set-?>", "", "measureIteration", "getMeasureIteration", "()J", "onLayoutCompletedListeners", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "onPositionedDispatcher", "Landroidx/compose/ui/node/OnPositionedDispatcher;", "postponedMeasureRequests", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "rootConstraints", "Landroidx/compose/ui/unit/Constraints;", "canAffectParent", "getCanAffectParent", "(Landroidx/compose/ui/node/LayoutNode;)Z", "canAffectParentInLookahead", "getCanAffectParentInLookahead", "measureAffectsParent", "getMeasureAffectsParent", "measureAffectsParentLookahead", "getMeasureAffectsParentLookahead", "callOnLayoutCompletedListeners", "", "dispatchOnPositionedCallbacks", "forceDispatch", "doLookaheadRemeasure", "layoutNode", "constraints", "doLookaheadRemeasure-sdFAvZA", "doRemeasure", "doRemeasure-sdFAvZA", "drainPostponedMeasureRequests", "ensureSubtreeLookaheadReplaced", "forceMeasureTheSubtree", "affectsLookahead", "forceMeasureTheSubtreeInternal", "measureAndLayout", "onLayout", "Lkotlin/Function0;", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "measureOnly", "onNodeDetached", "node", "onlyRemeasureIfScheduled", "performMeasureAndLayout", "fullPass", "block", "registerOnLayoutCompletedListener", "listener", "remeasureAndRelayoutIfNeeded", "relayoutNeeded", "remeasureLookaheadRootsInSubtree", "remeasureOnly", "requestLookaheadRelayout", "forced", "requestLookaheadRemeasure", "requestOnPositionedCallback", "requestRelayout", "requestRemeasure", "updateRootConstraints", "updateRootConstraints-BRTryo0", "(J)V", "measurePending", "PostponedRequest", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class MeasureAndLayoutDelegate {
    public static final int $stable = 8;
    private final LayoutTreeConsistencyChecker consistencyChecker;
    private boolean duringFullMeasureLayoutPass;
    private boolean duringMeasureLayout;
    private final LayoutNode root;
    private Constraints rootConstraints;
    private final DepthSortedSetsForDifferentPasses relayoutNodes = new DepthSortedSetsForDifferentPasses(Owner.INSTANCE.getEnableExtraAssertions());
    private final OnPositionedDispatcher onPositionedDispatcher = new OnPositionedDispatcher();
    private final MutableVector<Owner.OnLayoutCompletedListener> onLayoutCompletedListeners = new MutableVector<>(new Owner.OnLayoutCompletedListener[16], 0);
    private long measureIteration = 1;
    private final MutableVector<PostponedRequest> postponedMeasureRequests = new MutableVector<>(new PostponedRequest[16], 0);

    /* compiled from: MeasureAndLayoutDelegate.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutNode.LayoutState.values().length];
            try {
                iArr[LayoutNode.LayoutState.LookaheadMeasuring.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[LayoutNode.LayoutState.Idle.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode root) {
        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker;
        this.root = root;
        if (Owner.INSTANCE.getEnableExtraAssertions()) {
            layoutTreeConsistencyChecker = new LayoutTreeConsistencyChecker(this.root, this.relayoutNodes, this.postponedMeasureRequests.asMutableList());
        } else {
            layoutTreeConsistencyChecker = null;
        }
        this.consistencyChecker = layoutTreeConsistencyChecker;
    }

    public final boolean getHasPendingMeasureOrLayout() {
        return this.relayoutNodes.isNotEmpty();
    }

    public final boolean getHasPendingOnPositionedCallbacks() {
        return this.onPositionedDispatcher.isNotEmpty();
    }

    public final long getMeasureIteration() {
        boolean value$iv = this.duringMeasureLayout;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("measureIteration should be only used during the measure/layout pass");
        }
        return this.measureIteration;
    }

    /* renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m5681updateRootConstraintsBRTryo0(long constraints) {
        Constraints constraints2 = this.rootConstraints;
        if (!(constraints2 == null ? false : Constraints.m6572equalsimpl0(constraints2.getValue(), constraints))) {
            boolean value$iv = !this.duringMeasureLayout;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("updateRootConstraints called while measuring");
            }
            this.rootConstraints = Constraints.m6567boximpl(constraints);
            if (this.root.getLookaheadRoot() != null) {
                this.root.markLookaheadMeasurePending$ui_release();
            }
            this.root.markMeasurePending$ui_release();
            this.relayoutNodes.add(this.root, this.root.getLookaheadRoot() != null);
        }
    }

    public static /* synthetic */ boolean requestLookaheadRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean requestLookaheadRemeasure(androidx.compose.ui.node.LayoutNode r6, boolean r7) {
        /*
            r5 = this;
            androidx.compose.ui.node.LayoutNode r0 = r6.getLookaheadRoot()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto La
            r0 = r1
            goto Lb
        La:
            r0 = r2
        Lb:
            r3 = 0
            if (r0 != 0) goto L16
            r4 = 0
            java.lang.String r4 = "Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r4)
        L16:
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r6.getLayoutState$ui_release()
            int[] r3 = androidx.compose.ui.node.MeasureAndLayoutDelegate.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r3[r0]
            switch(r0) {
                case 1: goto Lad;
                case 2: goto L9a;
                case 3: goto L9a;
                case 4: goto L9a;
                case 5: goto L2c;
                default: goto L26;
            }
        L26:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L2c:
            boolean r0 = r6.getLookaheadMeasurePending$ui_release()
            if (r0 == 0) goto L37
            if (r7 != 0) goto L37
            r1 = r2
            goto Lae
        L37:
            r6.markLookaheadMeasurePending$ui_release()
            r6.markMeasurePending$ui_release()
            boolean r0 = r6.getIsDeactivated()
            if (r0 == 0) goto L46
            r1 = r2
            goto Lae
        L46:
            java.lang.Boolean r0 = r6.isPlacedInLookahead()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r0 != 0) goto L5a
            boolean r0 = r5.getCanAffectParentInLookahead(r6)
            if (r0 == 0) goto L71
        L5a:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui_release()
            if (r0 == 0) goto L68
            boolean r0 = r0.getLookaheadMeasurePending$ui_release()
            if (r0 != r1) goto L68
            r0 = r1
            goto L69
        L68:
            r0 = r2
        L69:
            if (r0 != 0) goto L71
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r0 = r5.relayoutNodes
            r0.add(r6, r1)
            goto L93
        L71:
            boolean r0 = r6.isPlaced()
            if (r0 != 0) goto L7d
            boolean r0 = r5.getCanAffectParent(r6)
            if (r0 == 0) goto L93
        L7d:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui_release()
            if (r0 == 0) goto L8b
            boolean r0 = r0.getMeasurePending$ui_release()
            if (r0 != r1) goto L8b
            r0 = r1
            goto L8c
        L8b:
            r0 = r2
        L8c:
            if (r0 != 0) goto L93
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r0 = r5.relayoutNodes
            r0.add(r6, r2)
        L93:
            boolean r0 = r5.duringFullMeasureLayoutPass
            if (r0 != 0) goto L98
            goto Lae
        L98:
            r1 = r2
            goto Lae
        L9a:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest> r0 = r5.postponedMeasureRequests
            androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest r3 = new androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest
            r3.<init>(r6, r1, r7)
            r0.add(r3)
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto Lab
            r0.assertConsistent()
        Lab:
            r1 = r2
            goto Lae
        Lad:
            r1 = r2
        Lae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.requestLookaheadRemeasure(androidx.compose.ui.node.LayoutNode, boolean):boolean");
    }

    public static /* synthetic */ boolean requestRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRemeasure(layoutNode, z);
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean forced) {
        switch (WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()]) {
            case 1:
            case 2:
                return false;
            case 3:
            case 4:
                this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, forced));
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker == null) {
                    return false;
                }
                layoutTreeConsistencyChecker.assertConsistent();
                return false;
            case 5:
                if (layoutNode.getMeasurePending$ui_release() && !forced) {
                    return false;
                }
                layoutNode.markMeasurePending$ui_release();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if (!layoutNode.isPlaced() && !getCanAffectParent(layoutNode)) {
                    return false;
                }
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if (!(parent$ui_release != null && parent$ui_release.getMeasurePending$ui_release())) {
                    this.relayoutNodes.add(layoutNode, false);
                }
                return !this.duringFullMeasureLayoutPass;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean requestLookaheadRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean requestLookaheadRelayout(androidx.compose.ui.node.LayoutNode r6, boolean r7) {
        /*
            r5 = this;
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r6.getLayoutState$ui_release()
            int[] r1 = androidx.compose.ui.node.MeasureAndLayoutDelegate.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 1: goto L9a;
                case 2: goto L16;
                case 3: goto L9a;
                case 4: goto L16;
                case 5: goto L16;
                default: goto L10;
            }
        L10:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L16:
            boolean r0 = r6.getLookaheadMeasurePending$ui_release()
            if (r0 != 0) goto L22
            boolean r0 = r6.getLookaheadLayoutPending$ui_release()
            if (r0 == 0) goto L2d
        L22:
            if (r7 != 0) goto L2d
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto L2b
            r0.assertConsistent()
        L2b:
            goto La2
        L2d:
            r6.markLookaheadLayoutPending$ui_release()
            r6.markLayoutPending$ui_release()
            boolean r0 = r6.getIsDeactivated()
            if (r0 == 0) goto L3b
            goto La2
        L3b:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui_release()
            java.lang.Boolean r2 = r6.isPlacedInLookahead()
            r3 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
            if (r2 == 0) goto L6e
            if (r0 == 0) goto L58
            boolean r2 = r0.getLookaheadMeasurePending$ui_release()
            if (r2 != r3) goto L58
            r2 = r3
            goto L59
        L58:
            r2 = r1
        L59:
            if (r2 != 0) goto L6e
            if (r0 == 0) goto L65
            boolean r2 = r0.getLookaheadLayoutPending$ui_release()
            if (r2 != r3) goto L65
            r2 = r3
            goto L66
        L65:
            r2 = r1
        L66:
            if (r2 != 0) goto L6e
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r2 = r5.relayoutNodes
            r2.add(r6, r3)
            goto L93
        L6e:
            boolean r2 = r6.isPlaced()
            if (r2 == 0) goto L93
            if (r0 == 0) goto L7e
            boolean r2 = r0.getLayoutPending$ui_release()
            if (r2 != r3) goto L7e
            r2 = r3
            goto L7f
        L7e:
            r2 = r1
        L7f:
            if (r2 != 0) goto L93
            if (r0 == 0) goto L8b
            boolean r2 = r0.getMeasurePending$ui_release()
            if (r2 != r3) goto L8b
            r2 = r3
            goto L8c
        L8b:
            r2 = r1
        L8c:
            if (r2 != 0) goto L93
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r2 = r5.relayoutNodes
            r2.add(r6, r1)
        L93:
            boolean r2 = r5.duringFullMeasureLayoutPass
            if (r2 != 0) goto L99
            r1 = r3
            goto La2
        L99:
            goto La2
        L9a:
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto La1
            r0.assertConsistent()
        La1:
        La2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.requestLookaheadRelayout(androidx.compose.ui.node.LayoutNode, boolean):boolean");
    }

    public static /* synthetic */ boolean requestRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRelayout(layoutNode, z);
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean forced) {
        switch (WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker == null) {
                    return false;
                }
                layoutTreeConsistencyChecker.assertConsistent();
                return false;
            case 5:
                if (!forced && layoutNode.isPlaced() == layoutNode.isPlacedByParent() && (layoutNode.getMeasurePending$ui_release() || layoutNode.getLayoutPending$ui_release())) {
                    LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
                    if (layoutTreeConsistencyChecker2 == null) {
                        return false;
                    }
                    layoutTreeConsistencyChecker2.assertConsistent();
                    return false;
                }
                layoutNode.markLayoutPending$ui_release();
                if (layoutNode.getIsDeactivated() || !layoutNode.isPlacedByParent()) {
                    return false;
                }
                LayoutNode parent = layoutNode.getParent$ui_release();
                if (!(parent != null && parent.getLayoutPending$ui_release())) {
                    if (!(parent != null && parent.getMeasurePending$ui_release())) {
                        this.relayoutNodes.add(layoutNode, false);
                    }
                }
                return !this.duringFullMeasureLayoutPass;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.onPositionedDispatcher.onNodePositioned(layoutNode);
    }

    /* renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m5678doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean lookaheadSizeChanged;
        if (layoutNode.getLookaheadRoot() == null) {
            return false;
        }
        if (constraints != null) {
            lookaheadSizeChanged = layoutNode.m5645lookaheadRemeasure_Sx5XlM$ui_release(constraints);
        } else {
            lookaheadSizeChanged = LayoutNode.m5641lookaheadRemeasure_Sx5XlM$ui_release$default(layoutNode, null, 1, null);
        }
        LayoutNode parent = layoutNode.getParent$ui_release();
        if (lookaheadSizeChanged && parent != null) {
            if (parent.getLookaheadRoot() == null) {
                LayoutNode.requestRemeasure$ui_release$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestLookaheadRelayout$ui_release$default(parent, false, 1, null);
            }
        }
        return lookaheadSizeChanged;
    }

    /* renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m5679doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean sizeChanged;
        if (constraints != null) {
            sizeChanged = layoutNode.m5646remeasure_Sx5XlM$ui_release(constraints);
        } else {
            sizeChanged = LayoutNode.m5642remeasure_Sx5XlM$ui_release$default(layoutNode, null, 1, null);
        }
        LayoutNode parent = layoutNode.getParent$ui_release();
        if (sizeChanged && parent != null) {
            if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestRemeasure$ui_release$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestRelayout$ui_release$default(parent, false, 1, null);
            }
        }
        return sizeChanged;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean measureAndLayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return measureAndLayoutDelegate.measureAndLayout(function0);
    }

    public final boolean measureAndLayout(Function0<Unit> onLayout) {
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this;
        boolean rootNodeResized = false;
        boolean value$iv$iv = this.root.isAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv$iv2 = this.root.isPlaced();
        if (!value$iv$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv$iv3 = this.duringMeasureLayout;
        if (!(!value$iv$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = true;
            try {
                if (measureAndLayoutDelegate.relayoutNodes.isNotEmpty()) {
                    DepthSortedSetsForDifferentPasses this_$iv = measureAndLayoutDelegate.relayoutNodes;
                    boolean rootNodeResized2 = false;
                    while (this_$iv.isNotEmpty()) {
                        try {
                            DepthSortedSet this_$iv$iv = this_$iv.lookaheadSet;
                            boolean affectsLookahead$iv = !this_$iv$iv.isEmpty();
                            LayoutNode layoutNode = (affectsLookahead$iv ? this_$iv.lookaheadSet : this_$iv.set).pop();
                            boolean sizeChanged = remeasureAndRelayoutIfNeeded$default(measureAndLayoutDelegate, layoutNode, affectsLookahead$iv, false, 4, null);
                            if (layoutNode == this.root && sizeChanged) {
                                rootNodeResized2 = true;
                            }
                            measureAndLayoutDelegate = this;
                        } catch (Throwable th) {
                            th = th;
                            this.duringMeasureLayout = false;
                            this.duringFullMeasureLayoutPass = false;
                            throw th;
                        }
                    }
                    if (onLayout != null) {
                        onLayout.invoke();
                    }
                    rootNodeResized = rootNodeResized2;
                }
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        callOnLayoutCompletedListeners();
        return rootNodeResized;
    }

    public final void measureOnly() {
        if (this.relayoutNodes.isNotEmpty()) {
            boolean value$iv$iv = this.root.isAttached();
            if (!value$iv$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
            }
            boolean value$iv$iv2 = this.root.isPlaced();
            if (!value$iv$iv2) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
            }
            boolean value$iv$iv3 = this.duringMeasureLayout;
            if (!(!value$iv$iv3)) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
            }
            if (this.rootConstraints != null) {
                this.duringMeasureLayout = true;
                this.duringFullMeasureLayoutPass = false;
                try {
                    if (!this.relayoutNodes.isEmpty(true)) {
                        LayoutNode lookaheadRoot = this.root.getLookaheadRoot();
                        LayoutNode layoutNode = this.root;
                        if (lookaheadRoot != null) {
                            remeasureOnly(layoutNode, true);
                        } else {
                            remeasureLookaheadRootsInSubtree(layoutNode);
                        }
                    }
                    remeasureOnly(this.root, false);
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                    if (layoutTreeConsistencyChecker != null) {
                        layoutTreeConsistencyChecker.assertConsistent();
                    }
                } catch (Throwable th) {
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    throw th;
                }
            }
        }
    }

    private final void remeasureLookaheadRootsInSubtree(LayoutNode layoutNode) {
        MutableVector this_$iv$iv = layoutNode.get_children$ui_release();
        int size$iv$iv = this_$iv$iv.getSize();
        if (size$iv$iv <= 0) {
            return;
        }
        int i$iv$iv = 0;
        Object[] content$iv$iv = this_$iv$iv.getContent();
        do {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (getMeasureAffectsParent(it)) {
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(it)) {
                    remeasureOnly(it, true);
                } else {
                    remeasureLookaheadRootsInSubtree(it);
                }
            }
            i$iv$iv++;
        } while (i$iv$iv < size$iv$iv);
    }

    /* renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m5680measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        boolean value$iv = !Intrinsics.areEqual(layoutNode, this.root);
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("measureAndLayout called on root");
        }
        boolean value$iv$iv = this.root.isAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv$iv2 = this.root.isPlaced();
        if (!value$iv$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv$iv3 = this.duringMeasureLayout;
        if (!(!value$iv$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = false;
            try {
                this.relayoutNodes.remove(layoutNode);
                boolean lookaheadSizeChanged = m5678doLookaheadRemeasuresdFAvZA(layoutNode, Constraints.m6567boximpl(constraints));
                if ((lookaheadSizeChanged || layoutNode.getLookaheadLayoutPending$ui_release()) && Intrinsics.areEqual((Object) layoutNode.isPlacedInLookahead(), (Object) true)) {
                    layoutNode.lookaheadReplace$ui_release();
                }
                ensureSubtreeLookaheadReplaced(layoutNode);
                m5679doRemeasuresdFAvZA(layoutNode, Constraints.m6567boximpl(constraints));
                if (layoutNode.getLayoutPending$ui_release() && layoutNode.isPlaced()) {
                    layoutNode.replace$ui_release();
                    this.onPositionedDispatcher.onNodePositioned(layoutNode);
                }
                drainPostponedMeasureRequests();
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            } catch (Throwable th) {
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                throw th;
            }
        }
        callOnLayoutCompletedListeners();
    }

    private final void ensureSubtreeLookaheadReplaced(LayoutNode layoutNode) {
        MutableVector this_$iv$iv = layoutNode.get_children$ui_release();
        int size$iv$iv = this_$iv$iv.getSize();
        if (size$iv$iv <= 0) {
            return;
        }
        int i$iv$iv = 0;
        Object[] content$iv$iv = this_$iv$iv.getContent();
        do {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (Intrinsics.areEqual((Object) it.isPlacedInLookahead(), (Object) true) && !it.getIsDeactivated()) {
                if (this.relayoutNodes.contains(it, true)) {
                    it.lookaheadReplace$ui_release();
                }
                ensureSubtreeLookaheadReplaced(it);
            }
            i$iv$iv++;
        } while (i$iv$iv < size$iv$iv);
    }

    private final void performMeasureAndLayout(boolean fullPass, Function0<Unit> block) {
        boolean value$iv = this.root.isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv2 = this.root.isPlaced();
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv3 = this.duringMeasureLayout;
        if (!(!value$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = fullPass;
            try {
                block.invoke();
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            } catch (Throwable th) {
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                throw th;
            }
        }
    }

    public final void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        MutableVector this_$iv = this.onLayoutCompletedListeners;
        this_$iv.add(listener);
    }

    private final void callOnLayoutCompletedListeners() {
        MutableVector this_$iv = this.onLayoutCompletedListeners;
        int size$iv = this_$iv.getSize();
        if (size$iv > 0) {
            int i$iv = 0;
            Object[] content$iv = this_$iv.getContent();
            do {
                Owner.OnLayoutCompletedListener it = (Owner.OnLayoutCompletedListener) content$iv[i$iv];
                it.onLayoutComplete();
                i$iv++;
            } while (i$iv < size$iv);
        }
        this.onLayoutCompletedListeners.clear();
    }

    static /* synthetic */ boolean remeasureAndRelayoutIfNeeded$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return measureAndLayoutDelegate.remeasureAndRelayoutIfNeeded(layoutNode, z, z2);
    }

    private final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean affectsLookahead, boolean relayoutNeeded) {
        Constraints constraints;
        boolean sizeChanged = false;
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        boolean isPlacedByPlacedParent = true;
        if (layoutNode.isPlaced() || layoutNode.isPlacedByParent() || getCanAffectParent(layoutNode) || Intrinsics.areEqual((Object) layoutNode.isPlacedInLookahead(), (Object) true) || getCanAffectParentInLookahead(layoutNode) || layoutNode.getAlignmentLinesRequired$ui_release()) {
            if (layoutNode == this.root) {
                constraints = this.rootConstraints;
                Intrinsics.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            if (affectsLookahead) {
                if (layoutNode.getLookaheadMeasurePending$ui_release()) {
                    sizeChanged = m5678doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
                }
                if (relayoutNeeded && ((sizeChanged || layoutNode.getLookaheadLayoutPending$ui_release()) && Intrinsics.areEqual((Object) layoutNode.isPlacedInLookahead(), (Object) true))) {
                    layoutNode.lookaheadReplace$ui_release();
                }
            } else {
                if (layoutNode.getMeasurePending$ui_release()) {
                    sizeChanged = m5679doRemeasuresdFAvZA(layoutNode, constraints);
                }
                if (relayoutNeeded && layoutNode.getLayoutPending$ui_release()) {
                    if (layoutNode != this.root) {
                        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                        if (!(parent$ui_release != null && parent$ui_release.isPlaced()) || !layoutNode.isPlacedByParent()) {
                            isPlacedByPlacedParent = false;
                        }
                    }
                    if (isPlacedByPlacedParent) {
                        if (layoutNode == this.root) {
                            layoutNode.place$ui_release(0, 0);
                        } else {
                            layoutNode.replace$ui_release();
                        }
                        this.onPositionedDispatcher.onNodePositioned(layoutNode);
                        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                        if (layoutTreeConsistencyChecker != null) {
                            layoutTreeConsistencyChecker.assertConsistent();
                        }
                    }
                }
            }
            drainPostponedMeasureRequests();
        }
        return sizeChanged;
    }

    private final void drainPostponedMeasureRequests() {
        if (this.postponedMeasureRequests.isNotEmpty()) {
            MutableVector this_$iv = this.postponedMeasureRequests;
            int size$iv = this_$iv.getSize();
            if (size$iv > 0) {
                int i$iv = 0;
                Object[] content$iv = this_$iv.getContent();
                do {
                    PostponedRequest request = (PostponedRequest) content$iv[i$iv];
                    if (request.getNode().isAttached()) {
                        if (!request.getIsLookahead()) {
                            LayoutNode.requestRemeasure$ui_release$default(request.getNode(), request.getIsForced(), false, false, 2, null);
                        } else {
                            LayoutNode.requestLookaheadRemeasure$ui_release$default(request.getNode(), request.getIsForced(), false, false, 2, null);
                        }
                    }
                    i$iv++;
                } while (i$iv < size$iv);
            }
            this.postponedMeasureRequests.clear();
        }
    }

    private final void remeasureOnly(LayoutNode layoutNode, boolean affectsLookahead) {
        Constraints constraints;
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            Intrinsics.checkNotNull(constraints);
        } else {
            constraints = null;
        }
        if (affectsLookahead) {
            m5678doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m5679doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        if (this.relayoutNodes.isEmpty(affectsLookahead)) {
            return;
        }
        boolean value$iv = this.duringMeasureLayout;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
        }
        boolean value$iv2 = measurePending(layoutNode, affectsLookahead);
        if (!(!value$iv2)) {
            InlineClassHelperKt.throwIllegalArgumentException("node not yet measured");
        }
        forceMeasureTheSubtreeInternal(layoutNode, affectsLookahead);
    }

    private final void onlyRemeasureIfScheduled(LayoutNode node, boolean affectsLookahead) {
        if (measurePending(node, affectsLookahead) && this.relayoutNodes.contains(node, affectsLookahead)) {
            remeasureAndRelayoutIfNeeded(node, affectsLookahead, false);
        }
    }

    private final void forceMeasureTheSubtreeInternal(LayoutNode layoutNode, boolean affectsLookahead) {
        MutableVector this_$iv$iv = layoutNode.get_children$ui_release();
        int size$iv$iv = this_$iv$iv.getSize();
        if (size$iv$iv > 0) {
            int i$iv$iv = 0;
            Object[] content$iv$iv = this_$iv$iv.getContent();
            do {
                LayoutNode child = (LayoutNode) content$iv$iv[i$iv$iv];
                if ((!affectsLookahead && getMeasureAffectsParent(child)) || (affectsLookahead && getMeasureAffectsParentLookahead(child))) {
                    if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(child) && !affectsLookahead) {
                        if (child.getLookaheadMeasurePending$ui_release() && this.relayoutNodes.contains(child, true)) {
                            remeasureAndRelayoutIfNeeded(child, true, false);
                        } else {
                            forceMeasureTheSubtree(child, true);
                        }
                    }
                    onlyRemeasureIfScheduled(child, affectsLookahead);
                    if (!measurePending(child, affectsLookahead)) {
                        forceMeasureTheSubtreeInternal(child, affectsLookahead);
                    }
                }
                i$iv$iv++;
            } while (i$iv$iv < size$iv$iv);
        }
        onlyRemeasureIfScheduled(layoutNode, affectsLookahead);
    }

    public static /* synthetic */ void dispatchOnPositionedCallbacks$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    public final void dispatchOnPositionedCallbacks(boolean forceDispatch) {
        if (forceDispatch) {
            this.onPositionedDispatcher.onRootNodePositioned(this.root);
        }
        this.onPositionedDispatcher.dispatch();
    }

    public final void onNodeDetached(LayoutNode node) {
        this.relayoutNodes.remove(node);
        this.onPositionedDispatcher.remove(node);
    }

    private final boolean getMeasureAffectsParent(LayoutNode $this$measureAffectsParent) {
        return $this$measureAffectsParent.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || $this$measureAffectsParent.getLayoutDelegate().getAlignmentLinesOwner$ui_release().getAlignmentLines().getRequired$ui_release();
    }

    private final boolean getCanAffectParent(LayoutNode $this$canAffectParent) {
        return $this$canAffectParent.getMeasurePending$ui_release() && getMeasureAffectsParent($this$canAffectParent);
    }

    private final boolean getCanAffectParentInLookahead(LayoutNode $this$canAffectParentInLookahead) {
        return $this$canAffectParentInLookahead.getLookaheadMeasurePending$ui_release() && getMeasureAffectsParentLookahead($this$canAffectParentInLookahead);
    }

    private final boolean getMeasureAffectsParentLookahead(LayoutNode $this$measureAffectsParentLookahead) {
        AlignmentLines alignmentLines;
        if ($this$measureAffectsParentLookahead.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
            return true;
        }
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui_release = $this$measureAffectsParentLookahead.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui_release();
        return lookaheadAlignmentLinesOwner$ui_release != null && (alignmentLines = lookaheadAlignmentLinesOwner$ui_release.getAlignmentLines()) != null && alignmentLines.getRequired$ui_release();
    }

    private final boolean measurePending(LayoutNode $this$measurePending, boolean affectsLookahead) {
        return affectsLookahead ? $this$measurePending.getLookaheadMeasurePending$ui_release() : $this$measurePending.getMeasurePending$ui_release();
    }

    /* compiled from: MeasureAndLayoutDelegate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "", "node", "Landroidx/compose/ui/node/LayoutNode;", "isLookahead", "", "isForced", "(Landroidx/compose/ui/node/LayoutNode;ZZ)V", "()Z", "getNode", "()Landroidx/compose/ui/node/LayoutNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PostponedRequest {
        public static final int $stable = 8;
        private final boolean isForced;
        private final boolean isLookahead;
        private final LayoutNode node;

        public PostponedRequest(LayoutNode node, boolean isLookahead, boolean isForced) {
            this.node = node;
            this.isLookahead = isLookahead;
            this.isForced = isForced;
        }

        public final LayoutNode getNode() {
            return this.node;
        }

        /* renamed from: isForced, reason: from getter */
        public final boolean getIsForced() {
            return this.isForced;
        }

        /* renamed from: isLookahead, reason: from getter */
        public final boolean getIsLookahead() {
            return this.isLookahead;
        }
    }
}
