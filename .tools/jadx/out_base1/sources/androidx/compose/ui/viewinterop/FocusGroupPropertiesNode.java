package androidx.compose.ui.viewinterop;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusGroupNode.android.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0019J\u001c\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0007H\u0016J\u0010\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0007H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/compose/ui/viewinterop/FocusGroupPropertiesNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "Landroid/view/View$OnAttachStateChangeListener;", "()V", "focusedChild", "Landroid/view/View;", "getFocusedChild", "()Landroid/view/View;", "setFocusedChild", "(Landroid/view/View;)V", "applyFocusProperties", "", "focusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "getFocusTargetOfEmbeddedViewWrapper", "Landroidx/compose/ui/focus/FocusTargetNode;", "onAttach", "onDetach", "onEnter", "Landroidx/compose/ui/focus/FocusRequester;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "onEnter-3ESFkO8", "(I)Landroidx/compose/ui/focus/FocusRequester;", "onExit", "onExit-3ESFkO8", "onGlobalFocusChanged", "oldFocus", "newFocus", "onViewAttachedToWindow", "v", "onViewDetachedFromWindow", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class FocusGroupPropertiesNode extends Modifier.Node implements FocusPropertiesModifierNode, ViewTreeObserver.OnGlobalFocusChangeListener, View.OnAttachStateChangeListener {
    private View focusedChild;

    public final View getFocusedChild() {
        return this.focusedChild;
    }

    public final void setFocusedChild(View view) {
        this.focusedChild = view;
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        focusProperties.setEnter(new FocusGroupPropertiesNode$applyFocusProperties$1(this));
        focusProperties.setExit(new FocusGroupPropertiesNode$applyFocusProperties$2(this));
    }

    /* renamed from: onEnter-3ESFkO8, reason: not valid java name */
    public final FocusRequester m6876onEnter3ESFkO8(int focusDirection) {
        View embeddedView;
        Rect currentlyFocusedRect;
        embeddedView = FocusGroupNode_androidKt.getView(this);
        if (embeddedView.isFocused() || embeddedView.hasFocus()) {
            return FocusRequester.INSTANCE.getDefault();
        }
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        Object requireOwner = DelegatableNodeKt.requireOwner(this);
        Intrinsics.checkNotNull(requireOwner, "null cannot be cast to non-null type android.view.View");
        View hostView = (View) requireOwner;
        Integer m3848toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m3848toAndroidFocusDirection3ESFkO8(focusDirection);
        currentlyFocusedRect = FocusGroupNode_androidKt.getCurrentlyFocusedRect(focusOwner, hostView, embeddedView);
        boolean targetViewFocused = FocusInteropUtils_androidKt.requestInteropFocus(embeddedView, m3848toAndroidFocusDirection3ESFkO8, currentlyFocusedRect);
        FocusRequester.Companion companion = FocusRequester.INSTANCE;
        return targetViewFocused ? companion.getDefault() : companion.getCancel();
    }

    /* renamed from: onExit-3ESFkO8, reason: not valid java name */
    public final FocusRequester m6877onExit3ESFkO8(int focusDirection) {
        View embeddedView;
        Rect focusedRect;
        View nextView;
        boolean containsDescendant;
        embeddedView = FocusGroupNode_androidKt.getView(this);
        if (!embeddedView.hasFocus()) {
            return FocusRequester.INSTANCE.getDefault();
        }
        FocusOwner focusOwner = DelegatableNodeKt.requireOwner(this).getFocusOwner();
        Object requireOwner = DelegatableNodeKt.requireOwner(this);
        Intrinsics.checkNotNull(requireOwner, "null cannot be cast to non-null type android.view.View");
        View hostView = (View) requireOwner;
        if (embeddedView instanceof ViewGroup) {
            focusedRect = FocusGroupNode_androidKt.getCurrentlyFocusedRect(focusOwner, hostView, embeddedView);
            Integer m3848toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m3848toAndroidFocusDirection3ESFkO8(focusDirection);
            int androidFocusDirection = m3848toAndroidFocusDirection3ESFkO8 != null ? m3848toAndroidFocusDirection3ESFkO8.intValue() : 130;
            FocusFinder $this$onExit_3ESFkO8_u24lambda_u241 = FocusFinder.getInstance();
            if (this.focusedChild != null) {
                nextView = $this$onExit_3ESFkO8_u24lambda_u241.findNextFocus((ViewGroup) hostView, this.focusedChild, androidFocusDirection);
            } else {
                nextView = $this$onExit_3ESFkO8_u24lambda_u241.findNextFocusFromRect((ViewGroup) hostView, focusedRect, androidFocusDirection);
            }
            if (nextView != null) {
                containsDescendant = FocusGroupNode_androidKt.containsDescendant(embeddedView, nextView);
                if (containsDescendant) {
                    nextView.requestFocus(androidFocusDirection, focusedRect);
                    return FocusRequester.INSTANCE.getCancel();
                }
            }
            if (hostView.requestFocus()) {
                return FocusRequester.INSTANCE.getDefault();
            }
            throw new IllegalStateException("host view did not take focus".toString());
        }
        if (hostView.requestFocus()) {
            return FocusRequester.INSTANCE.getDefault();
        }
        throw new IllegalStateException("host view did not take focus".toString());
    }

    private final FocusTargetNode getFocusTargetOfEmbeddedViewWrapper() {
        DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0$iv;
        int type$iv;
        int i;
        int i2;
        DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0$iv2;
        int type$iv2;
        int i3;
        DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0$iv3;
        int type$iv3;
        int i4;
        int count$iv$iv;
        MutableVector mutableVector;
        boolean foundFocusTargetOfFocusGroup = false;
        FocusGroupPropertiesNode $this$visitLocalDescendants_u2d6rFNWt0$iv4 = this;
        int type$iv4 = NodeKind.m5726constructorimpl(1024);
        int i5 = 0;
        int i6 = 0;
        boolean value$iv$iv$iv$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv4.getNode();
        if ((self$iv$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
            Modifier.Node next$iv$iv$iv = self$iv$iv$iv.getChild();
            while (next$iv$iv$iv != null) {
                if ((next$iv$iv$iv.getKindSet() & type$iv4) == 0) {
                    $this$visitLocalDescendants_u2d6rFNWt0$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                    type$iv = type$iv4;
                    i = i5;
                    i2 = i6;
                } else {
                    Modifier.Node it$iv = next$iv$iv$iv;
                    MutableVector mutableVector2 = null;
                    Modifier.Node node = it$iv;
                    while (node != null) {
                        boolean foundFocusTargetOfFocusGroup2 = foundFocusTargetOfFocusGroup;
                        boolean foundFocusTargetOfFocusGroup3 = node instanceof FocusTargetNode;
                        if (foundFocusTargetOfFocusGroup3) {
                            FocusTargetNode it = (FocusTargetNode) node;
                            if (foundFocusTargetOfFocusGroup2) {
                                return it;
                            }
                            $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                            type$iv2 = type$iv4;
                            i3 = i5;
                            foundFocusTargetOfFocusGroup = true;
                        } else {
                            Modifier.Node this_$iv$iv$iv = node;
                            if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0) == 0 || !(node instanceof DelegatingNode)) {
                                $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                                type$iv2 = type$iv4;
                                i3 = i5;
                                foundFocusTargetOfFocusGroup = foundFocusTargetOfFocusGroup2;
                            } else {
                                int count$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node;
                                Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv = node$iv$iv$iv;
                                    if (!((next$iv$iv.getKindSet() & type$iv4) != 0)) {
                                        $this$visitLocalDescendants_u2d6rFNWt0$iv3 = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                                        type$iv3 = type$iv4;
                                        i4 = i5;
                                    } else {
                                        count$iv$iv2++;
                                        $this$visitLocalDescendants_u2d6rFNWt0$iv3 = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                                        if (count$iv$iv2 == 1) {
                                            node = next$iv$iv;
                                            type$iv3 = type$iv4;
                                            i4 = i5;
                                        } else {
                                            if (mutableVector2 != null) {
                                                count$iv$iv = count$iv$iv2;
                                                type$iv3 = type$iv4;
                                                i4 = i5;
                                                mutableVector = mutableVector2;
                                            } else {
                                                count$iv$iv = count$iv$iv2;
                                                type$iv3 = type$iv4;
                                                i4 = i5;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            Modifier.Node theNode$iv$iv = node;
                                            if (theNode$iv$iv != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(theNode$iv$iv);
                                                }
                                                node = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv);
                                            }
                                            mutableVector2 = mutableVector;
                                            count$iv$iv2 = count$iv$iv;
                                        }
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                                    $this$visitLocalDescendants_u2d6rFNWt0$iv4 = $this$visitLocalDescendants_u2d6rFNWt0$iv3;
                                    type$iv4 = type$iv3;
                                    i5 = i4;
                                }
                                $this$visitLocalDescendants_u2d6rFNWt0$iv2 = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                                type$iv2 = type$iv4;
                                i3 = i5;
                                if (count$iv$iv2 != 1) {
                                    foundFocusTargetOfFocusGroup = foundFocusTargetOfFocusGroup2;
                                } else {
                                    foundFocusTargetOfFocusGroup = foundFocusTargetOfFocusGroup2;
                                    $this$visitLocalDescendants_u2d6rFNWt0$iv4 = $this$visitLocalDescendants_u2d6rFNWt0$iv2;
                                    type$iv4 = type$iv2;
                                    i5 = i3;
                                }
                            }
                        }
                        node = DelegatableNodeKt.pop(mutableVector2);
                        $this$visitLocalDescendants_u2d6rFNWt0$iv4 = $this$visitLocalDescendants_u2d6rFNWt0$iv2;
                        type$iv4 = type$iv2;
                        i5 = i3;
                    }
                    $this$visitLocalDescendants_u2d6rFNWt0$iv = $this$visitLocalDescendants_u2d6rFNWt0$iv4;
                    type$iv = type$iv4;
                    i = i5;
                    i2 = 0;
                }
                next$iv$iv$iv = next$iv$iv$iv.getChild();
                i6 = i2;
                $this$visitLocalDescendants_u2d6rFNWt0$iv4 = $this$visitLocalDescendants_u2d6rFNWt0$iv;
                type$iv4 = type$iv;
                i5 = i;
            }
        }
        throw new IllegalStateException("Could not find focus target of embedded view wrapper".toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008b  */
    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onGlobalFocusChanged(android.view.View r11, android.view.View r12) {
        /*
            r10 = this;
            r0 = r10
            androidx.compose.ui.node.DelegatableNode r0 = (androidx.compose.ui.node.DelegatableNode) r0
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r0)
            androidx.compose.ui.node.Owner r0 = r0.getOwner()
            if (r0 != 0) goto Le
            return
        Le:
            r0 = r10
            androidx.compose.ui.Modifier$Node r0 = (androidx.compose.ui.Modifier.Node) r0
            android.view.View r0 = androidx.compose.ui.viewinterop.FocusGroupNode_androidKt.access$getView(r0)
            r1 = r10
            androidx.compose.ui.node.DelegatableNode r1 = (androidx.compose.ui.node.DelegatableNode) r1
            androidx.compose.ui.node.Owner r1 = androidx.compose.ui.node.DelegatableNodeKt.requireOwner(r1)
            androidx.compose.ui.focus.FocusOwner r1 = r1.getFocusOwner()
            r2 = r10
            androidx.compose.ui.node.DelegatableNode r2 = (androidx.compose.ui.node.DelegatableNode) r2
            androidx.compose.ui.node.Owner r2 = androidx.compose.ui.node.DelegatableNodeKt.requireOwner(r2)
            r3 = 1
            r4 = 0
            if (r11 == 0) goto L39
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r2)
            if (r5 != 0) goto L39
            boolean r5 = androidx.compose.ui.viewinterop.FocusGroupNode_androidKt.access$containsDescendant(r0, r11)
            if (r5 == 0) goto L39
            r5 = r3
            goto L3a
        L39:
            r5 = r4
        L3a:
            if (r12 == 0) goto L4b
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r2)
            if (r6 != 0) goto L4b
            boolean r6 = androidx.compose.ui.viewinterop.FocusGroupNode_androidKt.access$containsDescendant(r0, r12)
            if (r6 == 0) goto L4b
            r6 = r3
            goto L4c
        L4b:
            r6 = r4
        L4c:
            if (r5 == 0) goto L55
            if (r6 == 0) goto L55
            r10.focusedChild = r12
            goto Lae
        L55:
            if (r6 == 0) goto L8b
            r10.focusedChild = r12
            androidx.compose.ui.focus.FocusTargetNode r3 = r10.getFocusTargetOfEmbeddedViewWrapper()
            androidx.compose.ui.focus.FocusStateImpl r4 = r3.getFocusState()
            boolean r4 = r4.getHasFocus()
            if (r4 != 0) goto Lae
            androidx.compose.ui.focus.FocusTransactionManager r4 = r1.getFocusTransactionManager()
            r7 = 0
            r8 = 0
            boolean r9 = androidx.compose.ui.focus.FocusTransactionManager.access$getOngoingTransaction$p(r4)     // Catch: java.lang.Throwable -> L86
            if (r9 == 0) goto L78
            androidx.compose.ui.focus.FocusTransactionManager.access$cancelTransaction(r4)     // Catch: java.lang.Throwable -> L86
        L78:
            androidx.compose.ui.focus.FocusTransactionManager.access$beginTransaction(r4)     // Catch: java.lang.Throwable -> L86
            r9 = 0
            androidx.compose.ui.focus.FocusTransactionsKt.performRequestFocus(r3)     // Catch: java.lang.Throwable -> L86
            androidx.compose.ui.focus.FocusTransactionManager.access$commitTransaction(r4)
            goto Lae
        L86:
            r9 = move-exception
            androidx.compose.ui.focus.FocusTransactionManager.access$commitTransaction(r4)
            throw r9
        L8b:
            r7 = 0
            if (r5 == 0) goto Lac
            r10.focusedChild = r7
            androidx.compose.ui.focus.FocusTargetNode r7 = r10.getFocusTargetOfEmbeddedViewWrapper()
            androidx.compose.ui.focus.FocusStateImpl r8 = r7.getFocusState()
            boolean r8 = r8.isFocused()
            if (r8 == 0) goto Lae
        La2:
            androidx.compose.ui.focus.FocusDirection$Companion r8 = androidx.compose.ui.focus.FocusDirection.INSTANCE
            int r8 = r8.m3842getExitdhqQ8s()
            r1.mo3851clearFocusI7lrPNg(r4, r3, r4, r8)
            goto Lae
        Lac:
            r10.focusedChild = r7
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.FocusGroupPropertiesNode.onGlobalFocusChanged(android.view.View, android.view.View):void");
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        View view;
        super.onAttach();
        view = FocusGroupNode_androidKt.getView(this);
        view.addOnAttachStateChangeListener(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        View view;
        view = FocusGroupNode_androidKt.getView(this);
        view.removeOnAttachStateChangeListener(this);
        this.focusedChild = null;
        super.onDetach();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
        v.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        v.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
    }
}
