package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeyondBoundsLayout.kt */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aA\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"searchBeyondBounds", "T", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class BeyondBoundsLayoutKt {
    /* renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m3830searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> function1) {
        T t;
        Modifier.Node node;
        int m5477getBeforehoxUOeE;
        FocusTargetNode focusTargetNode2;
        int i2;
        int i3;
        FocusTargetNode focusTargetNode3;
        Modifier.Node node2;
        NodeChain nodes;
        FocusTargetNode focusTargetNode4;
        int i4;
        int i5;
        FocusTargetNode focusTargetNode5;
        int i6;
        int i7;
        FocusTargetNode focusTargetNode6;
        int i8;
        int i9;
        FocusTargetNode focusTargetNode7;
        int i10;
        MutableVector mutableVector;
        FocusTargetNode focusTargetNode8 = focusTargetNode;
        int m5726constructorimpl = NodeKind.m5726constructorimpl(1024);
        int i11 = 0;
        FocusTargetNode focusTargetNode9 = focusTargetNode8;
        if (!focusTargetNode9.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = focusTargetNode9.getNode().getParent();
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode9);
        loop0: while (true) {
            if (requireLayoutNode == null) {
                t = null;
                node = null;
                break;
            }
            if ((requireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & m5726constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & m5726constructorimpl) != 0) {
                        MutableVector mutableVector2 = null;
                        Modifier.Node node3 = parent;
                        t = null;
                        while (node3 != null) {
                            FocusTargetNode focusTargetNode10 = focusTargetNode8;
                            if (node3 instanceof FocusTargetNode) {
                                node = node3;
                                break loop0;
                            }
                            if (((node3.getKindSet() & m5726constructorimpl) != 0 ? 1 : 0) == 0 || !(node3 instanceof DelegatingNode)) {
                                i6 = m5726constructorimpl;
                                i7 = i11;
                                focusTargetNode6 = focusTargetNode9;
                            } else {
                                int i12 = 0;
                                Modifier.Node delegate = ((DelegatingNode) node3).getDelegate();
                                while (delegate != null) {
                                    Modifier.Node node4 = delegate;
                                    if ((node4.getKindSet() & m5726constructorimpl) != 0) {
                                        i12++;
                                        i8 = m5726constructorimpl;
                                        if (i12 == 1) {
                                            node3 = node4;
                                            i9 = i11;
                                            focusTargetNode7 = focusTargetNode9;
                                        } else {
                                            if (mutableVector2 == null) {
                                                i10 = i12;
                                                i9 = i11;
                                                focusTargetNode7 = focusTargetNode9;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                i10 = i12;
                                                i9 = i11;
                                                focusTargetNode7 = focusTargetNode9;
                                                mutableVector = mutableVector2;
                                            }
                                            Modifier.Node node5 = node3;
                                            if (node5 != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(node5);
                                                }
                                                node3 = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(node4);
                                            }
                                            mutableVector2 = mutableVector;
                                            i12 = i10;
                                        }
                                    } else {
                                        i8 = m5726constructorimpl;
                                        i9 = i11;
                                        focusTargetNode7 = focusTargetNode9;
                                    }
                                    delegate = delegate.getChild();
                                    m5726constructorimpl = i8;
                                    i11 = i9;
                                    focusTargetNode9 = focusTargetNode7;
                                }
                                i6 = m5726constructorimpl;
                                i7 = i11;
                                focusTargetNode6 = focusTargetNode9;
                                if (i12 == 1) {
                                    focusTargetNode8 = focusTargetNode10;
                                    m5726constructorimpl = i6;
                                    i11 = i7;
                                    focusTargetNode9 = focusTargetNode6;
                                }
                            }
                            node3 = DelegatableNodeKt.pop(mutableVector2);
                            focusTargetNode8 = focusTargetNode10;
                            m5726constructorimpl = i6;
                            i11 = i7;
                            focusTargetNode9 = focusTargetNode6;
                        }
                        focusTargetNode4 = focusTargetNode8;
                        i4 = m5726constructorimpl;
                        i5 = i11;
                        focusTargetNode5 = focusTargetNode9;
                    } else {
                        focusTargetNode4 = focusTargetNode8;
                        i4 = m5726constructorimpl;
                        i5 = i11;
                        focusTargetNode5 = focusTargetNode9;
                    }
                    parent = parent.getParent();
                    focusTargetNode8 = focusTargetNode4;
                    m5726constructorimpl = i4;
                    i11 = i5;
                    focusTargetNode9 = focusTargetNode5;
                }
                focusTargetNode2 = focusTargetNode8;
                i2 = m5726constructorimpl;
                i3 = i11;
                focusTargetNode3 = focusTargetNode9;
                node2 = null;
            } else {
                focusTargetNode2 = focusTargetNode8;
                i2 = m5726constructorimpl;
                i3 = i11;
                focusTargetNode3 = focusTargetNode9;
                node2 = null;
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            parent = (requireLayoutNode == null || (nodes = requireLayoutNode.getNodes()) == null) ? node2 : nodes.getTail();
            focusTargetNode8 = focusTargetNode2;
            m5726constructorimpl = i2;
            i11 = i3;
            focusTargetNode9 = focusTargetNode3;
        }
        FocusTargetNode focusTargetNode11 = (FocusTargetNode) node;
        if (focusTargetNode11 != null && Intrinsics.areEqual(focusTargetNode11.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) {
            return t;
        }
        BeyondBoundsLayout beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent();
        if (beyondBoundsLayoutParent == null) {
            return t;
        }
        if (FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3847getUpdhqQ8s())) {
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5475getAbovehoxUOeE();
        } else if (FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3840getDowndhqQ8s())) {
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5478getBelowhoxUOeE();
        } else if (FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3843getLeftdhqQ8s())) {
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5479getLefthoxUOeE();
        } else if (FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3846getRightdhqQ8s())) {
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5480getRighthoxUOeE();
        } else if (FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3844getNextdhqQ8s())) {
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5476getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m3834equalsimpl0(i, FocusDirection.INSTANCE.m3845getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            m5477getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m5477getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo825layouto7g1Pn8(m5477getBeforehoxUOeE, function1);
    }
}
