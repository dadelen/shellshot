package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusTransactions.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0003\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u001e\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u000f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u001e\u0010\u0011\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000e\u001a\u001e\u0010\u0013\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000e\u001a\f\u0010\u0015\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0016\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002\u001a\f\u0010\u001b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u001c\u001a\u00020\u0002*\u00020\u0002H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "clearChildFocus", "forced", "refreshFocusEvents", "clearFocus", "freeFocus", "grantFocus", "performCustomClearFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "performCustomClearFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "performCustomRequestFocus", "performCustomRequestFocus-Mxy_nc0", "performRequestFocus", "requestFocus", "requestFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Ljava/lang/Boolean;", "requestFocusForChild", "childNode", "requestFocusForOwner", "requireActiveChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusTransactionsKt {

    /* compiled from: FocusTransactions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final boolean requestFocus(FocusTargetNode $this$requestFocus) {
        Boolean m3873requestFocusMxy_nc0 = m3873requestFocusMxy_nc0($this$requestFocus, FocusDirection.INSTANCE.m3841getEnterdhqQ8s());
        if (m3873requestFocusMxy_nc0 != null) {
            return m3873requestFocusMxy_nc0.booleanValue();
        }
        return false;
    }

    /* renamed from: requestFocus-Mxy_nc0, reason: not valid java name */
    public static final Boolean m3873requestFocusMxy_nc0(final FocusTargetNode $this$requestFocus_u2dMxy_nc0, int focusDirection) {
        Boolean valueOf;
        FocusTransactionManager this_$iv = FocusTargetNodeKt.requireTransactionManager($this$requestFocus_u2dMxy_nc0);
        Function0 onCancelled$iv = new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt$requestFocus$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (FocusTargetNode.this.getNode().getIsAttached()) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(FocusTargetNode.this);
                }
            }
        };
        try {
            if (this_$iv.ongoingTransaction) {
                this_$iv.cancelTransaction();
            }
            this_$iv.beginTransaction();
            MutableVector this_$iv$iv = this_$iv.cancellationListener;
            this_$iv$iv.add(onCancelled$iv);
            switch (WhenMappings.$EnumSwitchMapping$0[m3872performCustomRequestFocusMxy_nc0($this$requestFocus_u2dMxy_nc0, focusDirection).ordinal()]) {
                case 1:
                    valueOf = Boolean.valueOf(performRequestFocus($this$requestFocus_u2dMxy_nc0));
                    break;
                case 2:
                    valueOf = true;
                    break;
                case 3:
                case 4:
                    valueOf = null;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return valueOf;
        } finally {
            this_$iv.commitTransaction();
        }
    }

    public static final boolean performRequestFocus(FocusTargetNode $this$performRequestFocus) {
        boolean success;
        boolean z;
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        boolean z2;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        boolean z3;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv3;
        Modifier.Node node2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        boolean z4 = true;
        switch (WhenMappings.$EnumSwitchMapping$1[$this$performRequestFocus.getFocusState().ordinal()]) {
            case 1:
            case 2:
                success = true;
                break;
            case 3:
                if (!clearChildFocus$default($this$performRequestFocus, false, false, 3, null) || !grantFocus($this$performRequestFocus)) {
                    success = false;
                    break;
                } else {
                    success = true;
                    break;
                }
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv4 = $this$performRequestFocus;
                int m5726constructorimpl = NodeKind.m5726constructorimpl(1024);
                if (!$this$nearestAncestor_u2d64DMado$iv4.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv4.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv4);
                while (true) {
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & m5726constructorimpl) != 0) {
                            while (node$iv$iv$iv != null) {
                                if ((node$iv$iv$iv.getKindSet() & m5726constructorimpl) != 0) {
                                    Modifier.Node it$iv$iv = node$iv$iv$iv;
                                    MutableVector mutableVector2 = null;
                                    node = it$iv$iv;
                                    while (node != null) {
                                        if (node instanceof FocusTargetNode) {
                                            z = z4;
                                        } else {
                                            Modifier.Node this_$iv$iv$iv$iv = node;
                                            if (((this_$iv$iv$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? z4 : false) && (node instanceof DelegatingNode)) {
                                                int count$iv$iv$iv2 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) node;
                                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                while (node$iv$iv$iv$iv != null) {
                                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                    if ((next$iv$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? z4 : false) {
                                                        count$iv$iv$iv2++;
                                                        if (count$iv$iv$iv2 == z4) {
                                                            node = next$iv$iv$iv;
                                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                        } else {
                                                            if (mutableVector2 == null) {
                                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                                node2 = node;
                                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                                node2 = node;
                                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                                mutableVector = mutableVector2;
                                                            }
                                                            Modifier.Node theNode$iv$iv$iv = node2;
                                                            if (theNode$iv$iv$iv != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(theNode$iv$iv$iv);
                                                                }
                                                                node2 = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv);
                                                            }
                                                            mutableVector2 = mutableVector;
                                                            node = node2;
                                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                    }
                                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                                    z4 = true;
                                                }
                                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                Modifier.Node node3 = node;
                                                z3 = true;
                                                if (count$iv$iv$iv2 == 1) {
                                                    z4 = true;
                                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                                    node = node3;
                                                }
                                            } else {
                                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                z3 = z4;
                                            }
                                            node = DelegatableNodeKt.pop(mutableVector2);
                                            z4 = z3;
                                            $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                        }
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getParent();
                                z4 = z4;
                                $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                            }
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                            z2 = z4;
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                            z2 = z4;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        z4 = z2;
                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv;
                    } else {
                        z = z4;
                        node = null;
                    }
                }
                FocusTargetNode parent = (FocusTargetNode) node;
                if (parent == null) {
                    if (!requestFocusForOwner($this$performRequestFocus) || !grantFocus($this$performRequestFocus)) {
                        success = false;
                        break;
                    } else {
                        success = z;
                        break;
                    }
                } else {
                    FocusStateImpl prevState = parent.getFocusState();
                    success = requestFocusForChild(parent, $this$performRequestFocus);
                    if (success && prevState != parent.getFocusState()) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(parent);
                        break;
                    }
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (success) {
            FocusEventModifierNodeKt.refreshFocusEventNodes($this$performRequestFocus);
        }
        return success;
    }

    public static final boolean captureFocus(FocusTargetNode $this$captureFocus) {
        FocusTransactionManager $this$iv = FocusTargetNodeKt.requireTransactionManager($this$captureFocus);
        try {
            if ($this$iv.ongoingTransaction) {
                $this$iv.cancelTransaction();
            }
            $this$iv.beginTransaction();
            boolean z = true;
            switch (WhenMappings.$EnumSwitchMapping$1[$this$captureFocus.getFocusState().ordinal()]) {
                case 1:
                    $this$captureFocus.setFocusState(FocusStateImpl.Captured);
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$captureFocus);
                    break;
                case 2:
                    break;
                case 3:
                case 4:
                    z = false;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return z;
        } finally {
            $this$iv.commitTransaction();
        }
    }

    public static final boolean freeFocus(FocusTargetNode $this$freeFocus) {
        FocusTransactionManager $this$iv = FocusTargetNodeKt.requireTransactionManager($this$freeFocus);
        try {
            if ($this$iv.ongoingTransaction) {
                $this$iv.cancelTransaction();
            }
            $this$iv.beginTransaction();
            boolean z = true;
            switch (WhenMappings.$EnumSwitchMapping$1[$this$freeFocus.getFocusState().ordinal()]) {
                case 1:
                    break;
                case 2:
                    $this$freeFocus.setFocusState(FocusStateImpl.Active);
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$freeFocus);
                    break;
                case 3:
                case 4:
                    z = false;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return z;
        } finally {
            $this$iv.commitTransaction();
        }
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode $this$clearFocus, boolean forced, boolean refreshFocusEvents) {
        switch (WhenMappings.$EnumSwitchMapping$1[$this$clearFocus.getFocusState().ordinal()]) {
            case 1:
                $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                if (!refreshFocusEvents) {
                    return true;
                }
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                return true;
            case 2:
                if (forced) {
                    $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                    if (refreshFocusEvents) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                    }
                }
                return forced;
            case 3:
                if (clearChildFocus($this$clearFocus, forced, refreshFocusEvents)) {
                    $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                    if (!refreshFocusEvents) {
                        return true;
                    }
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                    return true;
                }
                return false;
            case 4:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean grantFocus(final FocusTargetNode $this$grantFocus) {
        ObserverModifierNodeKt.observeReads($this$grantFocus, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt$grantFocus$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                FocusTargetNode.this.fetchFocusProperties$ui_release();
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$1[$this$grantFocus.getFocusState().ordinal()]) {
            case 3:
            case 4:
                $this$grantFocus.setFocusState(FocusStateImpl.Active);
                break;
        }
        return true;
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode $this$clearChildFocus, boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$clearChildFocus);
        if (activeChild != null) {
            return clearFocus(activeChild, forced, refreshFocusEvents);
        }
        return true;
    }

    private static final boolean requestFocusForChild(FocusTargetNode $this$requestFocusForChild, FocusTargetNode childNode) {
        int i;
        Modifier.Node node;
        int i2;
        Object obj;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv;
        int i3;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int i4;
        int i5;
        Modifier.Node node2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv3;
        int type$iv2;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv4;
        int type$iv3;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv5;
        int type$iv4;
        int count$iv$iv$iv2;
        MutableVector mutableVector2;
        FocusTargetNode $this$nearestAncestor_u2d64DMado$iv6 = childNode;
        int i6 = 1024;
        int type$iv5 = NodeKind.m5726constructorimpl(1024);
        if (!$this$nearestAncestor_u2d64DMado$iv6.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv6.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv6);
        loop0: while (true) {
            int i7 = 1;
            i = i6;
            if (layout$iv$iv$iv == null) {
                node = null;
                break;
            }
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv5) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & type$iv5) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        MutableVector mutableVector3 = null;
                        Modifier.Node node3 = it$iv$iv;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                node = node3;
                                break loop0;
                            }
                            Modifier.Node this_$iv$iv$iv$iv = node3;
                            if (((this_$iv$iv$iv$iv.getKindSet() & type$iv5) != 0 ? i7 : 0) == 0 || !(node3 instanceof DelegatingNode)) {
                                $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv6;
                                type$iv3 = type$iv5;
                            } else {
                                int count$iv$iv$iv3 = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) node3;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    if (((next$iv$iv$iv.getKindSet() & type$iv5) != 0 ? i7 : 0) != 0) {
                                        count$iv$iv$iv3++;
                                        if (count$iv$iv$iv3 == i7) {
                                            node3 = next$iv$iv$iv;
                                            $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv6;
                                            type$iv4 = type$iv5;
                                        } else {
                                            if (mutableVector3 == null) {
                                                $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv6;
                                                type$iv4 = type$iv5;
                                                count$iv$iv$iv2 = count$iv$iv$iv3;
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv6;
                                                type$iv4 = type$iv5;
                                                count$iv$iv$iv2 = count$iv$iv$iv3;
                                                mutableVector2 = mutableVector3;
                                            }
                                            Modifier.Node theNode$iv$iv$iv = node3;
                                            if (theNode$iv$iv$iv != null) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv$iv$iv);
                                                }
                                                node3 = null;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(next$iv$iv$iv);
                                            }
                                            mutableVector3 = mutableVector2;
                                            count$iv$iv$iv3 = count$iv$iv$iv2;
                                        }
                                    } else {
                                        $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv6;
                                        type$iv4 = type$iv5;
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    $this$nearestAncestor_u2d64DMado$iv6 = $this$nearestAncestor_u2d64DMado$iv5;
                                    type$iv5 = type$iv4;
                                    i7 = 1;
                                }
                                $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv6;
                                type$iv3 = type$iv5;
                                if (count$iv$iv$iv3 == 1) {
                                    $this$nearestAncestor_u2d64DMado$iv6 = $this$nearestAncestor_u2d64DMado$iv4;
                                    type$iv5 = type$iv3;
                                    i7 = 1;
                                }
                            }
                            node3 = DelegatableNodeKt.pop(mutableVector3);
                            $this$nearestAncestor_u2d64DMado$iv6 = $this$nearestAncestor_u2d64DMado$iv4;
                            type$iv5 = type$iv3;
                            i7 = 1;
                        }
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    $this$nearestAncestor_u2d64DMado$iv6 = $this$nearestAncestor_u2d64DMado$iv6;
                    type$iv5 = type$iv5;
                    i7 = 1;
                }
                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv6;
                type$iv2 = type$iv5;
            } else {
                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv6;
                type$iv2 = type$iv5;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
            i6 = i;
            $this$nearestAncestor_u2d64DMado$iv6 = $this$nearestAncestor_u2d64DMado$iv3;
            type$iv5 = type$iv2;
        }
        if (!Intrinsics.areEqual(node, $this$requestFocusForChild)) {
            throw new IllegalStateException("Non child node cannot request focus.".toString());
        }
        switch (WhenMappings.$EnumSwitchMapping$1[$this$requestFocusForChild.getFocusState().ordinal()]) {
            case 1:
                boolean success = grantFocus(childNode);
                if (!success) {
                    return success;
                }
                $this$requestFocusForChild.setFocusState(FocusStateImpl.ActiveParent);
                return success;
            case 2:
                return false;
            case 3:
                requireActiveChild($this$requestFocusForChild);
                return clearChildFocus$default($this$requestFocusForChild, false, false, 3, null) && grantFocus(childNode);
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv7 = $this$requestFocusForChild;
                int count$iv$iv$iv4 = NodeKind.m5726constructorimpl(i);
                int i8 = 0;
                if (!$this$nearestAncestor_u2d64DMado$iv7.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv2 = $this$nearestAncestor_u2d64DMado$iv7.getNode().getParent();
                LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv7);
                while (true) {
                    if (layout$iv$iv$iv2 != null) {
                        Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                        if ((head$iv$iv$iv2.getAggregateChildKindSet() & count$iv$iv$iv4) != 0) {
                            while (node$iv$iv$iv2 != null) {
                                if ((node$iv$iv$iv2.getKindSet() & count$iv$iv$iv4) != 0) {
                                    Modifier.Node it$iv$iv2 = node$iv$iv$iv2;
                                    MutableVector mutableVector4 = null;
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv7;
                                    Modifier.Node node4 = it$iv$iv2;
                                    while (node4 != null) {
                                        int type$iv6 = count$iv$iv$iv4;
                                        if (node4 instanceof FocusTargetNode) {
                                            Object it$iv = node4;
                                            obj = it$iv;
                                            i2 = 1;
                                        } else {
                                            Modifier.Node this_$iv$iv$iv$iv3 = node4;
                                            if (((this_$iv$iv$iv$iv3.getKindSet() & type$iv6) != 0) && (node4 instanceof DelegatingNode)) {
                                                int count$iv$iv$iv5 = 0;
                                                DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) node4;
                                                Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv$iv2 != null) {
                                                    Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                    if ((next$iv$iv$iv2.getKindSet() & type$iv6) != 0) {
                                                        count$iv$iv$iv5++;
                                                        Modifier.Node node5 = node4;
                                                        if (count$iv$iv$iv5 == 1) {
                                                            i5 = i8;
                                                            node2 = next$iv$iv$iv2;
                                                        } else {
                                                            if (mutableVector4 == null) {
                                                                count$iv$iv$iv = count$iv$iv$iv5;
                                                                i5 = i8;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                count$iv$iv$iv = count$iv$iv$iv5;
                                                                i5 = i8;
                                                                mutableVector = mutableVector4;
                                                            }
                                                            if (node5 != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(node5);
                                                                }
                                                                node2 = null;
                                                            } else {
                                                                node2 = node5;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv2);
                                                            }
                                                            mutableVector4 = mutableVector;
                                                            count$iv$iv$iv5 = count$iv$iv$iv;
                                                        }
                                                    } else {
                                                        i5 = i8;
                                                        node2 = node4;
                                                    }
                                                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                    node4 = node2;
                                                    i8 = i5;
                                                }
                                                Modifier.Node node6 = node4;
                                                i4 = i8;
                                                if (count$iv$iv$iv5 == 1) {
                                                    count$iv$iv$iv4 = type$iv6;
                                                    node4 = node6;
                                                    i8 = i4;
                                                }
                                            } else {
                                                i4 = i8;
                                            }
                                            node4 = DelegatableNodeKt.pop(mutableVector4);
                                            count$iv$iv$iv4 = type$iv6;
                                            i8 = i4;
                                        }
                                    }
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv7;
                                }
                                node$iv$iv$iv2 = node$iv$iv$iv2.getParent();
                                $this$nearestAncestor_u2d64DMado$iv7 = $this$nearestAncestor_u2d64DMado$iv2;
                                count$iv$iv$iv4 = count$iv$iv$iv4;
                                i8 = i8;
                            }
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv7;
                            type$iv = count$iv$iv$iv4;
                            i3 = i8;
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv7;
                            type$iv = count$iv$iv$iv4;
                            i3 = i8;
                        }
                        layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui_release();
                        node$iv$iv$iv2 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv7 = $this$nearestAncestor_u2d64DMado$iv;
                        count$iv$iv$iv4 = type$iv;
                        i8 = i3;
                    } else {
                        i2 = 1;
                        obj = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) obj;
                if (focusParent == null && requestFocusForOwner($this$requestFocusForChild)) {
                    boolean success2 = grantFocus(childNode);
                    if (!success2) {
                        return success2;
                    }
                    $this$requestFocusForChild.setFocusState(FocusStateImpl.ActiveParent);
                    return success2;
                }
                if (focusParent == null || !requestFocusForChild(focusParent, $this$requestFocusForChild)) {
                    return false;
                }
                boolean success3 = requestFocusForChild($this$requestFocusForChild, childNode);
                if (($this$requestFocusForChild.getFocusState() == FocusStateImpl.ActiveParent ? i2 : 0) == 0) {
                    throw new IllegalStateException("Deactivated node is focused".toString());
                }
                if (success3) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusParent);
                }
                return success3;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean requestFocusForOwner(FocusTargetNode $this$requestFocusForOwner) {
        return DelegatableNodeKt.requireOwner($this$requestFocusForOwner).getFocusOwner().mo3855requestFocusForOwner7o62pno(null, null);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode $this$requireActiveChild) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$requireActiveChild);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child".toString());
    }

    /* renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m3872performCustomRequestFocusMxy_nc0(FocusTargetNode $this$performCustomRequestFocus_u2dMxy_nc0, int focusDirection) {
        int i;
        int i2;
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv;
        int i3;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int type$iv2;
        int i4;
        int i5;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv3;
        int type$iv3;
        int i6;
        MutableVector mutableVector;
        switch (WhenMappings.$EnumSwitchMapping$1[$this$performCustomRequestFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return CustomDestinationResult.None;
            case 3:
                return m3869performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomRequestFocus_u2dMxy_nc0), focusDirection);
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv4 = $this$performCustomRequestFocus_u2dMxy_nc0;
                int type$iv4 = NodeKind.m5726constructorimpl(1024);
                int i7 = 0;
                if (!$this$nearestAncestor_u2d64DMado$iv4.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv4.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv4);
                while (true) {
                    int i8 = 1;
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                            while (node$iv$iv$iv != null) {
                                if ((node$iv$iv$iv.getKindSet() & type$iv4) != 0) {
                                    Modifier.Node it$iv$iv = node$iv$iv$iv;
                                    MutableVector mutableVector2 = null;
                                    Modifier.Node node2 = it$iv$iv;
                                    while (node2 != null) {
                                        if (node2 instanceof FocusTargetNode) {
                                            node = node2;
                                            i = i8;
                                            i2 = 0;
                                        } else {
                                            Modifier.Node this_$iv$iv$iv$iv = node2;
                                            if (((this_$iv$iv$iv$iv.getKindSet() & type$iv4) != 0 ? i8 : 0) == 0 || !(node2 instanceof DelegatingNode)) {
                                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                type$iv2 = type$iv4;
                                                i4 = i7;
                                                i5 = i8;
                                            } else {
                                                int count$iv$iv$iv = 0;
                                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) node2;
                                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                while (node$iv$iv$iv$iv != null) {
                                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                    if (((next$iv$iv$iv.getKindSet() & type$iv4) != 0 ? i8 : 0) != 0) {
                                                        count$iv$iv$iv++;
                                                        if (count$iv$iv$iv == i8) {
                                                            node2 = next$iv$iv$iv;
                                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                            type$iv3 = type$iv4;
                                                            i6 = i7;
                                                        } else {
                                                            if (mutableVector2 == null) {
                                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                                type$iv3 = type$iv4;
                                                                i6 = i7;
                                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                            } else {
                                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                                type$iv3 = type$iv4;
                                                                i6 = i7;
                                                                mutableVector = mutableVector2;
                                                            }
                                                            Modifier.Node theNode$iv$iv$iv = node2;
                                                            if (theNode$iv$iv$iv != null) {
                                                                if (mutableVector != null) {
                                                                    mutableVector.add(theNode$iv$iv$iv);
                                                                }
                                                                node2 = null;
                                                            }
                                                            if (mutableVector != null) {
                                                                mutableVector.add(next$iv$iv$iv);
                                                            }
                                                            mutableVector2 = mutableVector;
                                                        }
                                                    } else {
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                        type$iv3 = type$iv4;
                                                        i6 = i7;
                                                    }
                                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                                    type$iv4 = type$iv3;
                                                    i7 = i6;
                                                    i8 = 1;
                                                }
                                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                type$iv2 = type$iv4;
                                                i4 = i7;
                                                i5 = 1;
                                                if (count$iv$iv$iv == 1) {
                                                    i8 = 1;
                                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                                    type$iv4 = type$iv2;
                                                    i7 = i4;
                                                }
                                            }
                                            node2 = DelegatableNodeKt.pop(mutableVector2);
                                            i8 = i5;
                                            $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                            type$iv4 = type$iv2;
                                            i7 = i4;
                                        }
                                    }
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getParent();
                                i8 = i8;
                                $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                                type$iv4 = type$iv4;
                                i7 = i7;
                            }
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                            type$iv = type$iv4;
                            i3 = i7;
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                            type$iv = type$iv4;
                            i3 = i7;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv;
                        type$iv4 = type$iv;
                        i7 = i3;
                    } else {
                        i = 1;
                        i2 = 0;
                        node = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) node;
                if (focusParent == null) {
                    return CustomDestinationResult.None;
                }
                switch (WhenMappings.$EnumSwitchMapping$1[focusParent.getFocusState().ordinal()]) {
                    case 1:
                        return m3870performCustomEnterMxy_nc0(focusParent, focusDirection);
                    case 2:
                        return CustomDestinationResult.Cancelled;
                    case 3:
                        return m3872performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                    case 4:
                        CustomDestinationResult it = m3872performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                        if (it != CustomDestinationResult.None) {
                            i = i2;
                        }
                        CustomDestinationResult customDestinationResult = i == 0 ? it : null;
                        return customDestinationResult == null ? m3870performCustomEnterMxy_nc0(focusParent, focusDirection) : customDestinationResult;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m3869performCustomClearFocusMxy_nc0(FocusTargetNode $this$performCustomClearFocus_u2dMxy_nc0, int focusDirection) {
        switch (WhenMappings.$EnumSwitchMapping$1[$this$performCustomClearFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 4:
                return CustomDestinationResult.None;
            case 2:
                return CustomDestinationResult.Cancelled;
            case 3:
                CustomDestinationResult it = m3869performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomClearFocus_u2dMxy_nc0), focusDirection);
                if (it == CustomDestinationResult.None) {
                    it = null;
                }
                if (it == null) {
                    return m3871performCustomExitMxy_nc0($this$performCustomClearFocus_u2dMxy_nc0, focusDirection);
                }
                return it;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m3870performCustomEnterMxy_nc0(FocusTargetNode $this$performCustomEnter_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter) {
            $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = true;
            try {
                FocusRequester it$iv = $this$performCustomEnter_u2dMxy_nc0.fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m3831boximpl(focusDirection));
                if (it$iv != FocusRequester.INSTANCE.getDefault()) {
                    if (it$iv == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else {
                        customDestinationResult = it$iv.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                    return customDestinationResult;
                }
            } finally {
                $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m3871performCustomExitMxy_nc0(FocusTargetNode $this$performCustomExit_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit) {
            $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = true;
            try {
                FocusRequester it$iv = $this$performCustomExit_u2dMxy_nc0.fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m3831boximpl(focusDirection));
                if (it$iv != FocusRequester.INSTANCE.getDefault()) {
                    if (it$iv == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else {
                        customDestinationResult = it$iv.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                    return customDestinationResult;
                }
            } finally {
                $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }
}
