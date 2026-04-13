package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: FocusRequesterModifierNode.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0000\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\t"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "freeFocus", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "requestFocus", "restoreFocusedChild", "saveFocusedChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusRequesterModifierNodeKt {
    public static final boolean requestFocus(FocusRequesterModifierNode $this$requestFocus) {
        int i;
        DelegatingNode this_$iv$iv$iv;
        int i2;
        int count$iv$iv;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$requestFocus;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i4 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int count$iv$iv2 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & count$iv$iv2) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & count$iv$iv2) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node this_$iv$iv$iv2 = it$iv;
                                while (this_$iv$iv$iv2 != null) {
                                    if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                        FocusTargetNode focusTarget = (FocusTargetNode) this_$iv$iv$iv2;
                                        return focusTarget.fetchFocusProperties$ui_release().getCanFocus() ? FocusTransactionsKt.requestFocus(focusTarget) : TwoDimensionalFocusSearchKt.m3883findChildCorrespondingToFocusEnterOMvw8(focusTarget, FocusDirection.INSTANCE.m3841getEnterdhqQ8s(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Boolean invoke(FocusTargetNode it) {
                                                return Boolean.valueOf(FocusTransactionsKt.requestFocus(it));
                                            }
                                        });
                                    }
                                    int mask$iv$iv = count$iv$iv2;
                                    DelegatableNode $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv;
                                    if (((this_$iv$iv$iv2.getKindSet() & type$iv4) != 0 ? true : z) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                        int count$iv$iv3 = 0;
                                        DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) {
                                                count$iv$iv3++;
                                                if (count$iv$iv3 == 1) {
                                                    this_$iv$iv$iv2 = next$iv$iv;
                                                    this_$iv$iv$iv = this_$iv$iv$iv3;
                                                    i2 = i3;
                                                } else {
                                                    if (mutableVector4 == null) {
                                                        count$iv$iv = count$iv$iv3;
                                                        this_$iv$iv$iv = this_$iv$iv$iv3;
                                                        i2 = i3;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv3;
                                                        this_$iv$iv$iv = this_$iv$iv$iv3;
                                                        i2 = i3;
                                                        mutableVector = mutableVector4;
                                                    }
                                                    mutableVector4 = mutableVector;
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv2 = null;
                                                    }
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(next$iv$iv);
                                                    }
                                                    count$iv$iv3 = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                i2 = i3;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            i3 = i2;
                                            z = false;
                                        }
                                        i = i3;
                                        if (count$iv$iv3 == 1) {
                                            count$iv$iv2 = mask$iv$iv;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv2;
                                            i3 = i;
                                            z = false;
                                        }
                                    } else {
                                        i = i3;
                                    }
                                    this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector4);
                                    count$iv$iv2 = mask$iv$iv;
                                    $this$visitChildren$iv$iv = $this$visitChildren$iv$iv2;
                                    i3 = i;
                                    z = false;
                                }
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                z = false;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (node instanceof FocusTargetNode) {
                FocusTargetNode focusTarget2 = (FocusTargetNode) node;
                return focusTarget2.fetchFocusProperties$ui_release().getCanFocus() ? FocusTransactionsKt.requestFocus(focusTarget2) : TwoDimensionalFocusSearchKt.m3883findChildCorrespondingToFocusEnterOMvw8(focusTarget2, FocusDirection.INSTANCE.m3841getEnterdhqQ8s(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(FocusTargetNode it) {
                        return Boolean.valueOf(FocusTransactionsKt.requestFocus(it));
                    }
                });
            }
            Modifier.Node this_$iv$iv$iv4 = node;
            Modifier.Node this_$iv$iv$iv5 = (this_$iv$iv$iv4.getKindSet() & type$iv3) != 0 ? 1 : null;
            if (this_$iv$iv$iv5 == null || !(node instanceof DelegatingNode)) {
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                int count$iv$iv4 = 0;
                DelegatingNode this_$iv$iv$iv6 = (DelegatingNode) node;
                Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv6.getDelegate();
                while (node$iv$iv$iv2 != null) {
                    Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                    if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i4 : 0) != 0) {
                        count$iv$iv4++;
                        if (count$iv$iv4 == i4) {
                            node = next$iv$iv2;
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        } else {
                            if (mutableVector3 == null) {
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                            } else {
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                                mutableVector2 = mutableVector3;
                            }
                            mutableVector3 = mutableVector2;
                            Modifier.Node theNode$iv$iv2 = node;
                            if (theNode$iv$iv2 != null) {
                                if (mutableVector3 != null) {
                                    mutableVector3.add(theNode$iv$iv2);
                                }
                                node = null;
                            }
                            if (mutableVector3 != null) {
                                mutableVector3.add(next$iv$iv2);
                            }
                        }
                    } else {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                        type$iv2 = type$iv3;
                    }
                    node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                    type$iv3 = type$iv2;
                    i4 = 1;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
                if (count$iv$iv4 == 1) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                    type$iv3 = type$iv;
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }

    public static final boolean captureFocus(FocusRequesterModifierNode $this$captureFocus) {
        int mask$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        int i;
        int mask$iv$iv2;
        DelegatableNode $this$visitChildren$iv$iv2;
        int i2;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$captureFocus;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i4 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int mask$iv$iv3 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node node2 = it$iv;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) node2;
                                        if (FocusTransactionsKt.captureFocus(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                        i = i3;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = node2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) && (node2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        node2 = next$iv$iv;
                                                        mask$iv$iv2 = mask$iv$iv3;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        i2 = i3;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        Modifier.Node theNode$iv$iv = node2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector4 != null) {
                                                                mutableVector4.add(theNode$iv$iv);
                                                            }
                                                            node2 = null;
                                                        }
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    i2 = i3;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                i3 = i2;
                                                z = false;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                i3 = i;
                                                z = false;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                        }
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector4);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                    i3 = i;
                                    z = false;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (node instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) node;
                if (FocusTransactionsKt.captureFocus(it2)) {
                    return true;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                Modifier.Node this_$iv$iv$iv3 = node;
                Modifier.Node this_$iv$iv$iv4 = (this_$iv$iv$iv3.getKindSet() & type$iv3) != 0 ? 1 : null;
                if (this_$iv$iv$iv4 == null || !(node instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i4 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i4) {
                                node = next$iv$iv2;
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                            } else {
                                if (mutableVector3 == null) {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv2 = node;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv2);
                                    }
                                    node = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                        type$iv3 = type$iv2;
                        i4 = 1;
                    }
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        type$iv3 = type$iv;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }

    public static final boolean freeFocus(FocusRequesterModifierNode $this$freeFocus) {
        int mask$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        int i;
        int mask$iv$iv2;
        DelegatableNode $this$visitChildren$iv$iv2;
        int i2;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$freeFocus;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i4 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int mask$iv$iv3 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node node2 = it$iv;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) node2;
                                        if (FocusTransactionsKt.freeFocus(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                        i = i3;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = node2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) && (node2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        node2 = next$iv$iv;
                                                        mask$iv$iv2 = mask$iv$iv3;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        i2 = i3;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        Modifier.Node theNode$iv$iv = node2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector4 != null) {
                                                                mutableVector4.add(theNode$iv$iv);
                                                            }
                                                            node2 = null;
                                                        }
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    i2 = i3;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                i3 = i2;
                                                z = false;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                i3 = i;
                                                z = false;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                        }
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector4);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                    i3 = i;
                                    z = false;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (node instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) node;
                if (FocusTransactionsKt.freeFocus(it2)) {
                    return true;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                Modifier.Node this_$iv$iv$iv3 = node;
                Modifier.Node this_$iv$iv$iv4 = (this_$iv$iv$iv3.getKindSet() & type$iv3) != 0 ? 1 : null;
                if (this_$iv$iv$iv4 == null || !(node instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i4 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i4) {
                                node = next$iv$iv2;
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                            } else {
                                if (mutableVector3 == null) {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv2 = node;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv2);
                                    }
                                    node = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                        type$iv3 = type$iv2;
                        i4 = 1;
                    }
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        type$iv3 = type$iv;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }

    public static final boolean saveFocusedChild(FocusRequesterModifierNode $this$saveFocusedChild) {
        int mask$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        int i;
        int mask$iv$iv2;
        DelegatableNode $this$visitChildren$iv$iv2;
        int i2;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$saveFocusedChild;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i4 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int mask$iv$iv3 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node node2 = it$iv;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) node2;
                                        if (FocusRestorerKt.saveFocusedChild(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                        i = i3;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = node2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) && (node2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        node2 = next$iv$iv;
                                                        mask$iv$iv2 = mask$iv$iv3;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        i2 = i3;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        Modifier.Node theNode$iv$iv = node2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector4 != null) {
                                                                mutableVector4.add(theNode$iv$iv);
                                                            }
                                                            node2 = null;
                                                        }
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    i2 = i3;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                i3 = i2;
                                                z = false;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                i3 = i;
                                                z = false;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                        }
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector4);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                    i3 = i;
                                    z = false;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (node instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) node;
                if (FocusRestorerKt.saveFocusedChild(it2)) {
                    return true;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                Modifier.Node this_$iv$iv$iv3 = node;
                Modifier.Node this_$iv$iv$iv4 = (this_$iv$iv$iv3.getKindSet() & type$iv3) != 0 ? 1 : null;
                if (this_$iv$iv$iv4 == null || !(node instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i4 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i4) {
                                node = next$iv$iv2;
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                            } else {
                                if (mutableVector3 == null) {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv2 = node;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv2);
                                    }
                                    node = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                        type$iv3 = type$iv2;
                        i4 = 1;
                    }
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        type$iv3 = type$iv;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }

    public static final boolean restoreFocusedChild(FocusRequesterModifierNode $this$restoreFocusedChild) {
        int mask$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        int i;
        int mask$iv$iv2;
        DelegatableNode $this$visitChildren$iv$iv2;
        int i2;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$restoreFocusedChild;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i4 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int mask$iv$iv3 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node node2 = it$iv;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) node2;
                                        if (FocusRestorerKt.restoreFocusedChild(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                        i = i3;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = node2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) && (node2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv4) != 0 ? true : z) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        node2 = next$iv$iv;
                                                        mask$iv$iv2 = mask$iv$iv3;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        i2 = i3;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            mask$iv$iv2 = mask$iv$iv3;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        Modifier.Node theNode$iv$iv = node2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector4 != null) {
                                                                mutableVector4.add(theNode$iv$iv);
                                                            }
                                                            node2 = null;
                                                        }
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    i2 = i3;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                i3 = i2;
                                                z = false;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                i3 = i;
                                                z = false;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                        }
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector4);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                    i3 = i;
                                    z = false;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            } else {
                                node$iv$iv = node$iv$iv.getChild();
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (node instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) node;
                if (FocusRestorerKt.restoreFocusedChild(it2)) {
                    return true;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                Modifier.Node this_$iv$iv$iv3 = node;
                Modifier.Node this_$iv$iv$iv4 = (this_$iv$iv$iv3.getKindSet() & type$iv3) != 0 ? 1 : null;
                if (this_$iv$iv$iv4 == null || !(node instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i4 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i4) {
                                node = next$iv$iv2;
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                            } else {
                                if (mutableVector3 == null) {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv2 = node;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv2);
                                    }
                                    node = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                        type$iv3 = type$iv2;
                        i4 = 1;
                    }
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        type$iv3 = type$iv;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusRequesterModifierNode $this$pinFocusedChild) {
        int mask$iv$iv;
        DelegatableNode $this$visitChildren$iv$iv;
        int i;
        int mask$iv$iv2;
        int mask$iv$iv3;
        DelegatableNode $this$visitChildren$iv$iv2;
        int i2;
        int mask$iv$iv4;
        MutableVector mutableVector;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
        int type$iv2;
        MutableVector mutableVector2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$pinFocusedChild;
        int type$iv3 = NodeKind.m5726constructorimpl(1024);
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3.getNode();
        MutableVector mutableVector3 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i4 = 0;
            int i5 = 1;
            if (node == null) {
                DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv4 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                int type$iv4 = type$iv3;
                int mask$iv$iv5 = type$iv4;
                DelegatableNode $this$visitChildren$iv$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv4;
                if (!$this$visitChildren$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren$iv$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren$iv$iv3.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv5) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                int mask$iv$iv6 = mask$iv$iv5;
                                int mask$iv$iv7 = i4;
                                i4 = mask$iv$iv7;
                                mask$iv$iv5 = mask$iv$iv6;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv5) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector4 = null;
                                Modifier.Node node2 = it$iv;
                                while (node2 != null) {
                                    if (node2 instanceof FocusTargetNode) {
                                        PinnableContainer.PinnedHandle it = FocusRestorerKt.pinFocusedChild((FocusTargetNode) node2);
                                        if (it != null) {
                                            return it;
                                        }
                                        mask$iv$iv = mask$iv$iv5;
                                        $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                        i = i3;
                                        mask$iv$iv2 = i4;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = node2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : i4) == 0 || !(node2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv5;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            mask$iv$iv2 = i4;
                                        } else {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if (((next$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : i4) != 0) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        node2 = next$iv$iv;
                                                        mask$iv$iv3 = mask$iv$iv5;
                                                        $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                        i2 = i3;
                                                        mask$iv$iv4 = 0;
                                                    } else {
                                                        if (mutableVector4 == null) {
                                                            mask$iv$iv3 = mask$iv$iv5;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mask$iv$iv4 = 0;
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        } else {
                                                            mask$iv$iv3 = mask$iv$iv5;
                                                            $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                            i2 = i3;
                                                            mask$iv$iv4 = 0;
                                                            mutableVector = mutableVector4;
                                                        }
                                                        mutableVector4 = mutableVector;
                                                        Modifier.Node theNode$iv$iv = node2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector4 != null) {
                                                                mutableVector4.add(theNode$iv$iv);
                                                            }
                                                            node2 = null;
                                                        }
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv3 = mask$iv$iv5;
                                                    $this$visitChildren$iv$iv2 = $this$visitChildren$iv$iv3;
                                                    i2 = i3;
                                                    mask$iv$iv4 = i4;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                i4 = mask$iv$iv4;
                                                mask$iv$iv5 = mask$iv$iv3;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv2;
                                                i3 = i2;
                                            }
                                            mask$iv$iv = mask$iv$iv5;
                                            $this$visitChildren$iv$iv = $this$visitChildren$iv$iv3;
                                            i = i3;
                                            mask$iv$iv2 = i4;
                                            if (count$iv$iv == 1) {
                                                i4 = mask$iv$iv2;
                                                mask$iv$iv5 = mask$iv$iv;
                                                $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                                i3 = i;
                                            }
                                        }
                                    }
                                    node2 = DelegatableNodeKt.pop(mutableVector4);
                                    i4 = mask$iv$iv2;
                                    mask$iv$iv5 = mask$iv$iv;
                                    $this$visitChildren$iv$iv3 = $this$visitChildren$iv$iv;
                                    i3 = i;
                                }
                                int mask$iv$iv8 = mask$iv$iv5;
                                int mask$iv$iv9 = i4;
                                i4 = mask$iv$iv9;
                                mask$iv$iv5 = mask$iv$iv8;
                            } else {
                                int mask$iv$iv10 = mask$iv$iv5;
                                int mask$iv$iv11 = i4;
                                node$iv$iv = node$iv$iv.getChild();
                                i4 = mask$iv$iv11;
                                mask$iv$iv5 = mask$iv$iv10;
                            }
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return null;
            }
            if (node instanceof FocusTargetNode) {
                PinnableContainer.PinnedHandle it2 = FocusRestorerKt.pinFocusedChild((FocusTargetNode) node);
                if (it2 != null) {
                    return it2;
                }
                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                type$iv = type$iv3;
            } else {
                Modifier.Node this_$iv$iv$iv3 = node;
                Modifier.Node this_$iv$iv$iv4 = (this_$iv$iv$iv3.getKindSet() & type$iv3) != 0 ? 1 : null;
                if (this_$iv$iv$iv4 == null || !(node instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & type$iv3) != 0 ? i5 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i5) {
                                node = next$iv$iv2;
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                type$iv2 = type$iv3;
                            } else {
                                if (mutableVector3 == null) {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                                    type$iv2 = type$iv3;
                                    mutableVector2 = mutableVector3;
                                }
                                mutableVector3 = mutableVector2;
                                Modifier.Node theNode$iv$iv2 = node;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv$iv2);
                                    }
                                    node = null;
                                }
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                            type$iv2 = type$iv3;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                        type$iv3 = type$iv2;
                        i5 = 1;
                    }
                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv3;
                    type$iv = type$iv3;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        type$iv3 = type$iv;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector3);
            $this$visitSelfAndChildren_u2d6rFNWt0$iv3 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
            type$iv3 = type$iv;
        }
    }
}
