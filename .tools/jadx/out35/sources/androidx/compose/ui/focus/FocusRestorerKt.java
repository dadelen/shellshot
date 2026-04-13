package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: FocusRestorer.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001e\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\tH\u0001\u001a\f\u0010\f\u001a\u00020\u000b*\u00020\tH\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"PrevFocusedChild", "", "focusRestorer", "Landroidx/compose/ui/Modifier;", "onRestoreFailed", "Lkotlin/Function0;", "Landroidx/compose/ui/focus/FocusRequester;", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "Landroidx/compose/ui/focus/FocusTargetNode;", "restoreFocusedChild", "", "saveFocusedChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusRestorerKt {
    private static final String PrevFocusedChild = "previouslyFocusedChildHash";

    public static final boolean saveFocusedChild(FocusTargetNode $this$saveFocusedChild) {
        DelegatableNode $this$visitChildren_u2d6rFNWt0$iv;
        int type$iv;
        boolean z;
        DelegatingNode this_$iv$iv$iv;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv;
        Modifier.Node node2;
        Modifier.Node node3;
        final FocusTargetNode focusTargetNode = $this$saveFocusedChild;
        if (!focusTargetNode.getFocusState().getHasFocus()) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2d6rFNWt0$iv2 = focusTargetNode;
        int type$iv2 = NodeKind.m5726constructorimpl(1024);
        if (!$this$visitChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2d6rFNWt0$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2d6rFNWt0$iv2.getNode());
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (branches$iv$iv.isNotEmpty()) {
            boolean z2 = true;
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        focusTargetNode = $this$saveFocusedChild;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            boolean z3 = z2;
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) this_$iv$iv$iv2;
                                if (it.getFocusState().getHasFocus()) {
                                    focusTargetNode.setPreviouslyFocusedChildHash(DelegatableNodeKt.requireLayoutNode(it).getCompositeKeyHash());
                                    SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf(focusTargetNode, SaveableStateRegistryKt.getLocalSaveableStateRegistry());
                                    if (saveableStateRegistry != null) {
                                        saveableStateRegistry.registerProvider(PrevFocusedChild, new Function0<Object>() { // from class: androidx.compose.ui.focus.FocusRestorerKt$saveFocusedChild$1$1
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                return Integer.valueOf(FocusTargetNode.this.getPreviouslyFocusedChildHash());
                                            }
                                        });
                                    }
                                    return z3;
                                }
                                $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                type$iv = type$iv2;
                                z = z3;
                            } else {
                                $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                type$iv = type$iv2;
                                if (((this_$iv$iv$iv2.getKindSet() & type$iv) != 0 ? z3 : false) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type$iv) != 0 ? z3 : false) {
                                            count$iv$iv2++;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            if (count$iv$iv2 == z3) {
                                                mutableVector = mutableVector2;
                                                node = next$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    node2 = this_$iv$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    node2 = this_$iv$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv = node2;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    node3 = null;
                                                } else {
                                                    node3 = node2;
                                                }
                                                if (mutableVector != null) {
                                                    node = node3;
                                                    mutableVector.add(next$iv$iv);
                                                } else {
                                                    node = node3;
                                                }
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            mutableVector = mutableVector2;
                                            node = this_$iv$iv$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv2 = node;
                                        z3 = true;
                                        mutableVector2 = mutableVector;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                    }
                                    Modifier.Node node4 = this_$iv$iv$iv2;
                                    z = true;
                                    if (count$iv$iv2 == 1) {
                                        focusTargetNode = $this$saveFocusedChild;
                                        z2 = true;
                                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                                        type$iv2 = type$iv;
                                        this_$iv$iv$iv2 = node4;
                                    }
                                } else {
                                    z = z3;
                                }
                            }
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                            focusTargetNode = $this$saveFocusedChild;
                            z2 = z;
                            $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                            type$iv2 = type$iv;
                        }
                        focusTargetNode = $this$saveFocusedChild;
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        focusTargetNode = $this$saveFocusedChild;
                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                    }
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
            }
        }
        return false;
    }

    public static final boolean restoreFocusedChild(FocusTargetNode $this$restoreFocusedChild) {
        DelegatableNode $this$visitChildren_u2d6rFNWt0$iv;
        boolean z;
        DelegatingNode this_$iv$iv$iv;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv;
        Modifier.Node node2;
        Modifier.Node node3;
        SaveableStateRegistry savableStateRegistry;
        Object it;
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == 0 && (savableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf($this$restoreFocusedChild, SaveableStateRegistryKt.getLocalSaveableStateRegistry())) != null && (it = savableStateRegistry.consumeRestored(PrevFocusedChild)) != null) {
            $this$restoreFocusedChild.setPreviouslyFocusedChildHash(((Integer) it).intValue());
        }
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == 0) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2d6rFNWt0$iv2 = $this$restoreFocusedChild;
        int m5726constructorimpl = NodeKind.m5726constructorimpl(1024);
        if (!$this$visitChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2d6rFNWt0$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2d6rFNWt0$iv2.getNode());
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (branches$iv$iv.isNotEmpty()) {
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & m5726constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
            } else {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & m5726constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it2 = (FocusTargetNode) this_$iv$iv$iv2;
                                if (it2.getIsAttached()) {
                                    $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                    if (DelegatableNodeKt.requireLayoutNode(it2).getCompositeKeyHash() == $this$restoreFocusedChild.getPreviouslyFocusedChildHash()) {
                                        return restoreFocusedChild(it2) || FocusTransactionsKt.requestFocus(it2);
                                    }
                                } else {
                                    $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                }
                                z = true;
                            } else {
                                $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                if (((this_$iv$iv$iv2.getKindSet() & m5726constructorimpl) != 0) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & m5726constructorimpl) != 0) {
                                            count$iv$iv2++;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            if (count$iv$iv2 == 1) {
                                                mutableVector = mutableVector2;
                                                node = next$iv$iv;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    node2 = this_$iv$iv$iv2;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    node2 = this_$iv$iv$iv2;
                                                    mutableVector = mutableVector2;
                                                }
                                                Modifier.Node theNode$iv$iv = node2;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    node3 = null;
                                                } else {
                                                    node3 = node2;
                                                }
                                                if (mutableVector != null) {
                                                    node = node3;
                                                    mutableVector.add(next$iv$iv);
                                                } else {
                                                    node = node3;
                                                }
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            mutableVector = mutableVector2;
                                            node = this_$iv$iv$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv2 = node;
                                        mutableVector2 = mutableVector;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                    }
                                    Modifier.Node node4 = this_$iv$iv$iv2;
                                    z = true;
                                    if (count$iv$iv2 == 1) {
                                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                                        this_$iv$iv$iv2 = node4;
                                    }
                                } else {
                                    z = true;
                                }
                            }
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                        }
                    } else {
                        node$iv$iv = node$iv$iv.getChild();
                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                    }
                }
            }
        }
        return false;
    }

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusTargetNode $this$pinFocusedChild) {
        PinnableContainer pinnableContainer;
        FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode($this$pinFocusedChild);
        if (findActiveFocusNode == null || (pinnableContainer = (PinnableContainer) CompositionLocalConsumerModifierNodeKt.currentValueOf(findActiveFocusNode, PinnableContainerKt.getLocalPinnableContainer())) == null) {
            return null;
        }
        return pinnableContainer.pin();
    }

    public static /* synthetic */ Modifier focusRestorer$default(Modifier modifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return focusRestorer(modifier, function0);
    }

    public static final Modifier focusRestorer(Modifier $this$focusRestorer, Function0<FocusRequester> function0) {
        return $this$focusRestorer.then(new FocusRestorerElement(function0));
    }
}
