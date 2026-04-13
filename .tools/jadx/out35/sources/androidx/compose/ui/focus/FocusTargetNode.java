package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: FocusTargetNode.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u00015B\u0005¢\u0006\u0002\u0010\u0006J\r\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J/\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020!0'H\u0080\bø\u0001\u0000¢\u0006\u0004\b)\u0010*J/\u0010+\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020!0'H\u0080\bø\u0001\u0000¢\u0006\u0004\b,\u0010*J\r\u0010-\u001a\u00020.H\u0000¢\u0006\u0002\b/J\b\u00100\u001a\u00020!H\u0002J\r\u00101\u001a\u00020!H\u0000¢\u0006\u0002\b2J\b\u00103\u001a\u00020!H\u0016J\b\u00104\u001a\u00020!H\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f8V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u0015X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00066"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "()V", "beyondBoundsLayoutParent", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "getBeyondBoundsLayoutParent", "()Landroidx/compose/ui/layout/BeyondBoundsLayout;", "committedFocusState", "Landroidx/compose/ui/focus/FocusStateImpl;", "value", "focusState", "getFocusState$annotations", "getFocusState", "()Landroidx/compose/ui/focus/FocusStateImpl;", "setFocusState", "(Landroidx/compose/ui/focus/FocusStateImpl;)V", "isProcessingCustomEnter", "", "isProcessingCustomExit", "previouslyFocusedChildHash", "", "getPreviouslyFocusedChildHash", "()I", "setPreviouslyFocusedChildHash", "(I)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "commitFocusState", "", "commitFocusState$ui_release", "fetchCustomEnter", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusRequester;", "fetchCustomEnter-aToIllA$ui_release", "(ILkotlin/jvm/functions/Function1;)V", "fetchCustomExit", "fetchCustomExit-aToIllA$ui_release", "fetchFocusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "fetchFocusProperties$ui_release", "initializeFocusState", "invalidateFocus", "invalidateFocus$ui_release", "onDetach", "onObservedReadsChanged", "FocusTargetElement", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusTargetNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, FocusTargetModifierNode, ObserverModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private FocusStateImpl committedFocusState;
    private boolean isProcessingCustomEnter;
    private boolean isProcessingCustomExit;
    private int previouslyFocusedChildHash;
    private final boolean shouldAutoInvalidate;

    /* compiled from: FocusTargetNode.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getFocusState$annotations() {
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    public FocusStateImpl getFocusState() {
        FocusTransactionManager $this$_get_focusState__u24lambda_u240;
        FocusStateImpl uncommittedFocusState;
        $this$_get_focusState__u24lambda_u240 = FocusTargetNodeKt.getFocusTransactionManager(this);
        if ($this$_get_focusState__u24lambda_u240 != null && (uncommittedFocusState = $this$_get_focusState__u24lambda_u240.getUncommittedFocusState(this)) != null) {
            return uncommittedFocusState;
        }
        FocusStateImpl focusStateImpl = this.committedFocusState;
        return focusStateImpl == null ? FocusStateImpl.Inactive : focusStateImpl;
    }

    public void setFocusState(FocusStateImpl value) {
        FocusTransactionManager $this$_set_focusState__u24lambda_u241 = FocusTargetNodeKt.requireTransactionManager(this);
        $this$_set_focusState__u24lambda_u241.setUncommittedFocusState(this, value);
    }

    public final int getPreviouslyFocusedChildHash() {
        return this.previouslyFocusedChildHash;
    }

    public final void setPreviouslyFocusedChildHash(int i) {
        this.previouslyFocusedChildHash = i;
    }

    public final BeyondBoundsLayout getBeyondBoundsLayoutParent() {
        return (BeyondBoundsLayout) getCurrent(androidx.compose.ui.layout.BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout());
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        FocusStateImpl previousFocusState = getFocusState();
        invalidateFocus$ui_release();
        if (previousFocusState != getFocusState()) {
            FocusEventModifierNodeKt.refreshFocusEventNodes(this);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        switch (WhenMappings.$EnumSwitchMapping$0[getFocusState().ordinal()]) {
            case 1:
            case 2:
                DelegatableNodeKt.requireOwner(this).getFocusOwner().mo3851clearFocusI7lrPNg(true, true, false, FocusDirection.INSTANCE.m3842getExitdhqQ8s());
                FocusTargetNodeKt.invalidateFocusTarget(this);
                break;
            case 3:
                FocusTransactionManager $this$iv = FocusTargetNodeKt.requireTransactionManager(this);
                try {
                    if ($this$iv.ongoingTransaction) {
                        $this$iv.cancelTransaction();
                    }
                    $this$iv.beginTransaction();
                    setFocusState(FocusStateImpl.Inactive);
                    Unit unit = Unit.INSTANCE;
                    break;
                } finally {
                    $this$iv.commitTransaction();
                }
        }
        this.committedFocusState = null;
    }

    public final FocusProperties fetchFocusProperties$ui_release() {
        FocusPropertiesImpl properties;
        FocusPropertiesImpl properties2;
        DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv;
        int type$iv;
        int untilType$iv;
        NodeChain nodes;
        DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
        int type$iv2;
        int untilType$iv2;
        DelegatingNode this_$iv$iv$iv;
        int type$iv3;
        int untilType$iv3;
        int count$iv$iv;
        MutableVector mutableVector;
        FocusPropertiesImpl properties3 = new FocusPropertiesImpl();
        FocusTargetNode $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = this;
        int type$iv4 = NodeKind.m5726constructorimpl(2048);
        int untilType$iv4 = NodeKind.m5726constructorimpl(1024);
        Modifier.Node self$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode();
        int mask$iv$iv = type$iv4 | untilType$iv4;
        if (!$this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3.getNode();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitSelfAndAncestors_u2d5BbP62I$iv3);
        loop0: while (true) {
            if (layout$iv$iv == null) {
                properties = properties3;
                break;
            }
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & mask$iv$iv) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & mask$iv$iv) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        if (it$iv != self$iv) {
                            if ((it$iv.getKindSet() & untilType$iv4) != 0) {
                                properties = properties3;
                                break loop0;
                            }
                        }
                        if ((it$iv.getKindSet() & type$iv4) != 0) {
                            MutableVector mutableVector2 = null;
                            Modifier.Node this_$iv$iv$iv2 = it$iv;
                            while (this_$iv$iv$iv2 != null) {
                                FocusPropertiesImpl properties4 = properties3;
                                if (this_$iv$iv$iv2 instanceof FocusPropertiesModifierNode) {
                                    FocusPropertiesModifierNode it = (FocusPropertiesModifierNode) this_$iv$iv$iv2;
                                    $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                    it.applyFocusProperties(properties4);
                                    type$iv2 = type$iv4;
                                    untilType$iv2 = untilType$iv4;
                                } else {
                                    $this$visitSelfAndAncestors_u2d5BbP62I$iv2 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                                    if (((this_$iv$iv$iv2.getKindSet() & type$iv4) != 0) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & type$iv4) != 0) {
                                                count$iv$iv2++;
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                if (count$iv$iv2 == 1) {
                                                    this_$iv$iv$iv2 = next$iv$iv;
                                                    type$iv3 = type$iv4;
                                                    untilType$iv3 = untilType$iv4;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        untilType$iv3 = untilType$iv4;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        count$iv$iv = count$iv$iv2;
                                                        type$iv3 = type$iv4;
                                                        untilType$iv3 = untilType$iv4;
                                                        mutableVector = mutableVector2;
                                                    }
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv2 = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    count$iv$iv2 = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                type$iv3 = type$iv4;
                                                untilType$iv3 = untilType$iv4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            type$iv4 = type$iv3;
                                            untilType$iv4 = untilType$iv3;
                                        }
                                        type$iv2 = type$iv4;
                                        untilType$iv2 = untilType$iv4;
                                        if (count$iv$iv2 == 1) {
                                            properties3 = properties4;
                                            $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                            type$iv4 = type$iv2;
                                            untilType$iv4 = untilType$iv2;
                                        }
                                    } else {
                                        type$iv2 = type$iv4;
                                        untilType$iv2 = untilType$iv4;
                                    }
                                }
                                this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                properties3 = properties4;
                                $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv2;
                                type$iv4 = type$iv2;
                                untilType$iv4 = untilType$iv2;
                            }
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    properties3 = properties3;
                    $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                    type$iv4 = type$iv4;
                    untilType$iv4 = untilType$iv4;
                }
                properties2 = properties3;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                type$iv = type$iv4;
                untilType$iv = untilType$iv4;
            } else {
                properties2 = properties3;
                $this$visitSelfAndAncestors_u2d5BbP62I$iv = $this$visitSelfAndAncestors_u2d5BbP62I$iv3;
                type$iv = type$iv4;
                untilType$iv = untilType$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui_release();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            properties3 = properties2;
            $this$visitSelfAndAncestors_u2d5BbP62I$iv3 = $this$visitSelfAndAncestors_u2d5BbP62I$iv;
            type$iv4 = type$iv;
            untilType$iv4 = untilType$iv;
        }
        return properties;
    }

    /* renamed from: fetchCustomEnter-aToIllA$ui_release, reason: not valid java name */
    public final void m3867fetchCustomEnteraToIllA$ui_release(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (!this.isProcessingCustomEnter) {
            this.isProcessingCustomEnter = true;
            try {
                FocusRequester it = fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m3831boximpl(focusDirection));
                if (it != FocusRequester.INSTANCE.getDefault()) {
                    block.invoke(it);
                }
            } finally {
                this.isProcessingCustomEnter = false;
            }
        }
    }

    /* renamed from: fetchCustomExit-aToIllA$ui_release, reason: not valid java name */
    public final void m3868fetchCustomExitaToIllA$ui_release(int focusDirection, Function1<? super FocusRequester, Unit> block) {
        if (!this.isProcessingCustomExit) {
            this.isProcessingCustomExit = true;
            try {
                FocusRequester it = fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m3831boximpl(focusDirection));
                if (it != FocusRequester.INSTANCE.getDefault()) {
                    block.invoke(it);
                }
            } finally {
                this.isProcessingCustomExit = false;
            }
        }
    }

    public final void commitFocusState$ui_release() {
        FocusTransactionManager $this$commitFocusState_u24lambda_u247 = FocusTargetNodeKt.requireTransactionManager(this);
        FocusStateImpl uncommittedFocusState = $this$commitFocusState_u24lambda_u247.getUncommittedFocusState(this);
        if (uncommittedFocusState != null) {
            this.committedFocusState = uncommittedFocusState;
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("committing a node that was not updated in the current transaction");
            throw new KotlinNothingValueException();
        }
    }

    public final void invalidateFocus$ui_release() {
        FocusProperties focusProperties;
        if (this.committedFocusState == null) {
            initializeFocusState();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[getFocusState().ordinal()]) {
            case 1:
            case 2:
                final Ref.ObjectRef focusProperties2 = new Ref.ObjectRef();
                ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTargetNode$invalidateFocus$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.compose.ui.focus.FocusProperties] */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        focusProperties2.element = this.fetchFocusProperties$ui_release();
                    }
                });
                if (focusProperties2.element == 0) {
                    Intrinsics.throwUninitializedPropertyAccessException("focusProperties");
                    focusProperties = null;
                } else {
                    focusProperties = (FocusProperties) focusProperties2.element;
                }
                if (!focusProperties.getCanFocus()) {
                    DelegatableNodeKt.requireOwner(this).getFocusOwner().clearFocus(true);
                    break;
                }
                break;
        }
    }

    /* compiled from: FocusTargetNode.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\f\u0010\u000e\u001a\u00020\f*\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode$FocusTargetElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusTargetNode;", "()V", "create", "equals", "", "other", "", "hashCode", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FocusTargetElement extends ModifierNodeElement<FocusTargetNode> {
        public static final int $stable = 0;
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* renamed from: create, reason: avoid collision after fix types in other method */
        public FocusTargetNode getNode() {
            return new FocusTargetNode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("focusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return "focusTarget".hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }
    }

    private static final boolean initializeFocusState$isInitialized(FocusTargetNode $this$initializeFocusState_u24isInitialized) {
        return $this$initializeFocusState_u24isInitialized.committedFocusState != null;
    }

    private static final boolean initializeFocusState$isInActiveSubTree(FocusTargetNode this$0) {
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean includeSelf$iv;
        NodeChain nodes;
        int type$iv2;
        boolean includeSelf$iv2;
        int type$iv3;
        boolean includeSelf$iv3;
        int count$iv$iv;
        MutableVector mutableVector;
        FocusTargetNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = this$0;
        int type$iv4 = NodeKind.m5726constructorimpl(1024);
        boolean includeSelf$iv4 = false;
        if (!$this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node node = it$iv;
                        while (node != null) {
                            DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                            if (node instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) node;
                                if (initializeFocusState$isInitialized(it)) {
                                    switch (WhenMappings.$EnumSwitchMapping$0[it.getFocusState().ordinal()]) {
                                        case 1:
                                        case 2:
                                        case 4:
                                            return false;
                                        case 3:
                                            return true;
                                        default:
                                            throw new NoWhenBranchMatchedException();
                                    }
                                }
                                type$iv2 = type$iv4;
                                includeSelf$iv2 = includeSelf$iv4;
                            } else {
                                Modifier.Node this_$iv$iv$iv = node;
                                if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0) && (node instanceof DelegatingNode)) {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type$iv4) != 0) {
                                            count$iv$iv2++;
                                            type$iv3 = type$iv4;
                                            if (count$iv$iv2 == 1) {
                                                node = next$iv$iv;
                                                includeSelf$iv3 = includeSelf$iv4;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    count$iv$iv = count$iv$iv2;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    count$iv$iv = count$iv$iv2;
                                                    includeSelf$iv3 = includeSelf$iv4;
                                                    mutableVector = mutableVector2;
                                                }
                                                mutableVector2 = mutableVector;
                                                Modifier.Node theNode$iv$iv = node;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                count$iv$iv2 = count$iv$iv;
                                            }
                                        } else {
                                            type$iv3 = type$iv4;
                                            includeSelf$iv3 = includeSelf$iv4;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        type$iv4 = type$iv3;
                                        includeSelf$iv4 = includeSelf$iv3;
                                    }
                                    type$iv2 = type$iv4;
                                    includeSelf$iv2 = includeSelf$iv4;
                                    if (count$iv$iv2 == 1) {
                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                                        type$iv4 = type$iv2;
                                        includeSelf$iv4 = includeSelf$iv2;
                                    }
                                } else {
                                    type$iv2 = type$iv4;
                                    includeSelf$iv2 = includeSelf$iv4;
                                }
                            }
                            node = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                            type$iv4 = type$iv2;
                            includeSelf$iv4 = includeSelf$iv2;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    type$iv4 = type$iv4;
                    includeSelf$iv4 = includeSelf$iv4;
                }
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                type$iv = type$iv4;
                includeSelf$iv = includeSelf$iv4;
            } else {
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                type$iv = type$iv4;
                includeSelf$iv = includeSelf$iv4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui_release();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            type$iv4 = type$iv;
            includeSelf$iv4 = includeSelf$iv;
        }
        return false;
    }

    private static final boolean initializeFocusState$hasActiveChild(FocusTargetNode this$0) {
        DelegatableNode $this$visitSubtreeIf_u2d6rFNWt0$iv;
        int type$iv;
        int i;
        boolean z;
        DelegatableNode $this$visitSubtreeIf_u2d6rFNWt0$iv2;
        int type$iv2;
        int i2;
        DelegatableNode $this$visitSubtreeIf_u2d6rFNWt0$iv3;
        int type$iv3;
        int i3;
        MutableVector mutableVector;
        FocusTargetNode $this$visitSubtreeIf_u2d6rFNWt0$iv4 = this$0;
        int type$iv4 = NodeKind.m5726constructorimpl(1024);
        int i4 = 0;
        boolean value$iv$iv$iv = $this$visitSubtreeIf_u2d6rFNWt0$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtreeIf_u2d6rFNWt0$iv4.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtreeIf_u2d6rFNWt0$iv4.getNode());
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (branches$iv$iv.isNotEmpty()) {
            boolean z2 = true;
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node node$iv = node$iv$iv;
                        MutableVector mutableVector2 = null;
                        Modifier.Node node = node$iv;
                        while (node != null) {
                            if (node instanceof FocusTargetNode) {
                                Object it$iv = node;
                                FocusTargetNode it = (FocusTargetNode) it$iv;
                                if (initializeFocusState$isInitialized(it)) {
                                    switch (WhenMappings.$EnumSwitchMapping$0[it.getFocusState().ordinal()]) {
                                        case 1:
                                        case 2:
                                        case 3:
                                            return true;
                                        case 4:
                                            return false;
                                        default:
                                            throw new NoWhenBranchMatchedException();
                                    }
                                }
                                $this$visitSubtreeIf_u2d6rFNWt0$iv2 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                                type$iv2 = type$iv4;
                                i2 = i4;
                            } else {
                                Modifier.Node this_$iv$iv$iv = node;
                                if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0) && (node instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type$iv4) != 0) {
                                            count$iv$iv++;
                                            $this$visitSubtreeIf_u2d6rFNWt0$iv3 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                                            if (count$iv$iv == 1) {
                                                node = next$iv$iv;
                                                type$iv3 = type$iv4;
                                                i3 = i4;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    type$iv3 = type$iv4;
                                                    i3 = i4;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    type$iv3 = type$iv4;
                                                    i3 = i4;
                                                    mutableVector = mutableVector2;
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
                                            }
                                        } else {
                                            $this$visitSubtreeIf_u2d6rFNWt0$iv3 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                                            type$iv3 = type$iv4;
                                            i3 = i4;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv3;
                                        type$iv4 = type$iv3;
                                        i4 = i3;
                                    }
                                    $this$visitSubtreeIf_u2d6rFNWt0$iv2 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                                    type$iv2 = type$iv4;
                                    i2 = i4;
                                    if (count$iv$iv == 1) {
                                        $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv2;
                                        type$iv4 = type$iv2;
                                        i4 = i2;
                                    }
                                } else {
                                    $this$visitSubtreeIf_u2d6rFNWt0$iv2 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                                    type$iv2 = type$iv4;
                                    i2 = i4;
                                }
                            }
                            node = DelegatableNodeKt.pop(mutableVector2);
                            $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv2;
                            type$iv4 = type$iv2;
                            i4 = i2;
                        }
                        $this$visitSubtreeIf_u2d6rFNWt0$iv = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                        type$iv = type$iv4;
                        i = i4;
                        z = true;
                        if (1 == 0) {
                            $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv;
                            type$iv4 = type$iv;
                            i4 = i;
                            break;
                        }
                    } else {
                        $this$visitSubtreeIf_u2d6rFNWt0$iv = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
                        type$iv = type$iv4;
                        i = i4;
                        z = z2;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    z2 = z;
                    $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv;
                    type$iv4 = type$iv;
                    i4 = i;
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
            $this$visitSubtreeIf_u2d6rFNWt0$iv4 = $this$visitSubtreeIf_u2d6rFNWt0$iv4;
            type$iv4 = type$iv4;
            i4 = i4;
        }
        return false;
    }

    private final void initializeFocusState() {
        if (initializeFocusState$isInitialized(this)) {
            throw new IllegalStateException("Re-initializing focus target node.".toString());
        }
        FocusTransactionManager $this$iv = FocusTargetNodeKt.requireTransactionManager(this);
        try {
            if ($this$iv.ongoingTransaction) {
                $this$iv.cancelTransaction();
            }
            $this$iv.beginTransaction();
            setFocusState((initializeFocusState$isInActiveSubTree(this) && initializeFocusState$hasActiveChild(this)) ? FocusStateImpl.ActiveParent : FocusStateImpl.Inactive);
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$iv.commitTransaction();
        }
    }
}
