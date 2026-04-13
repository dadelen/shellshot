package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.PointerInputModifierNodeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HitPathTracker.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0017\u0010!\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001d0#H\u0082\bJ.\u0010$\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u001a\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\r2\u0006\u0010'\u001a\u00020\rH\u0002J\u0006\u0010(\u001a\u00020\u001dJ\u001e\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000-H\u0016J\b\u0010.\u001a\u00020/H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "modifierNode", "Landroidx/compose/ui/Modifier$Node;", "(Landroidx/compose/ui/Modifier$Node;)V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "hasExited", "", "isIn", "getModifierNode", "()Landroidx/compose/ui/Modifier$Node;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerIds", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "getPointerIds", "()Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "relevantChanges", "Landroidx/collection/LongSparseArray;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "wasIn", "buildCache", "changes", "parentCoordinates", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "isInBounds", "cleanUpHits", "", "clearCache", "dispatchCancel", "dispatchFinalEventPass", "dispatchIfNeeded", "block", "Lkotlin/Function0;", "dispatchMainEventPass", "hasPositionChanged", "oldEvent", "newEvent", "markIsIn", "removeInvalidPointerIdsAndChanges", "pointerIdValue", "", "hitNodes", "Landroidx/collection/MutableObjectList;", "toString", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class Node extends NodeParent {
    public static final int $stable = 8;
    private LayoutCoordinates coordinates;

    /* renamed from: modifierNode, reason: from kotlin metadata and from toString */
    private final Modifier.Node pointerInputFilter;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final LongSparseArray<PointerInputChange> relevantChanges = new LongSparseArray<>(2);
    private boolean isIn = true;
    private boolean hasExited = true;

    public Node(Modifier.Node modifierNode) {
        this.pointerInputFilter = modifierNode;
    }

    /* renamed from: getModifierNode, reason: from getter */
    public final Modifier.Node getPointerInputFilter() {
        return this.pointerInputFilter;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void removeInvalidPointerIdsAndChanges(long pointerIdValue, MutableObjectList<Node> hitNodes) {
        if (this.pointerIds.contains(pointerIdValue) && !hitNodes.contains(this)) {
            this.pointerIds.remove(pointerIdValue);
            this.relevantChanges.remove(pointerIdValue);
        }
        MutableVector this_$iv = getChildren();
        int size$iv = this_$iv.getSize();
        if (size$iv <= 0) {
            return;
        }
        int i$iv = 0;
        Object[] content$iv = this_$iv.getContent();
        do {
            Node it = (Node) content$iv[i$iv];
            it.removeInvalidPointerIdsAndChanges(pointerIdValue, hitNodes);
            i$iv++;
        } while (i$iv < size$iv);
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int kind$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        int kind$iv2;
        MutableVector mutableVector;
        MutableVector this_$iv;
        int size$iv;
        int i;
        Node this_$iv2;
        int $i$f$dispatchIfNeeded;
        Node this_$iv3;
        int $i$f$dispatchIfNeeded2;
        MutableVector mutableVector2;
        Node this_$iv4 = this;
        int $i$f$dispatchIfNeeded3 = 0;
        if (!this_$iv4.relevantChanges.isEmpty() && this_$iv4.pointerInputFilter.getIsAttached()) {
            PointerEvent event = this.pointerEvent;
            Intrinsics.checkNotNull(event);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long size = layoutCoordinates.mo5499getSizeYbymL2g();
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = this.pointerInputFilter;
            int count$iv = 16;
            int m5726constructorimpl = NodeKind.m5726constructorimpl(16);
            MutableVector mutableVector3 = null;
            Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv3;
            while (true) {
                int i2 = 1;
                if (node == null) {
                    break;
                }
                if (node instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) node;
                    i = count$iv;
                    it.mo200onPointerEventH0pRuoY(event, PointerEventPass.Initial, size);
                    this_$iv2 = this_$iv4;
                    $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                } else {
                    i = count$iv;
                    Modifier.Node this_$iv$iv = node;
                    Modifier.Node this_$iv$iv2 = (this_$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? 1 : null;
                    if (this_$iv$iv2 == null || !(node instanceof DelegatingNode)) {
                        this_$iv2 = this_$iv4;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                    } else {
                        int count$iv2 = 0;
                        DelegatingNode this_$iv$iv3 = (DelegatingNode) node;
                        Modifier.Node node$iv$iv = this_$iv$iv3.getDelegate();
                        while (node$iv$iv != null) {
                            Modifier.Node next$iv = node$iv$iv;
                            if (((next$iv.getKindSet() & m5726constructorimpl) != 0 ? i2 : 0) == 0) {
                                this_$iv3 = this_$iv4;
                                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                            } else {
                                count$iv2++;
                                if (count$iv2 == i2) {
                                    node = next$iv;
                                    this_$iv3 = this_$iv4;
                                    $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                } else {
                                    if (mutableVector3 != null) {
                                        this_$iv3 = this_$iv4;
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        mutableVector2 = mutableVector3;
                                    } else {
                                        this_$iv3 = this_$iv4;
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    mutableVector3 = mutableVector2;
                                    Modifier.Node theNode$iv = node;
                                    if (theNode$iv != null) {
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(theNode$iv);
                                        }
                                        node = null;
                                    }
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(next$iv);
                                    }
                                }
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            this_$iv4 = this_$iv3;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded2;
                            i2 = 1;
                        }
                        this_$iv2 = this_$iv4;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                        if (count$iv2 == 1) {
                            count$iv = i;
                            this_$iv4 = this_$iv2;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
                        }
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector3);
                count$iv = i;
                this_$iv4 = this_$iv2;
                $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
            }
            int i3 = count$iv;
            if (this.pointerInputFilter.getIsAttached() && (size$iv = (this_$iv = getChildren()).getSize()) > 0) {
                int i$iv = 0;
                Object[] content$iv = this_$iv.getContent();
                do {
                    Node it2 = (Node) content$iv[i$iv];
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    it2.dispatchMainEventPass(longSparseArray, layoutCoordinates2, internalPointerEvent, isInBounds);
                    i$iv++;
                } while (i$iv < size$iv);
            }
            if (!this.pointerInputFilter.getIsAttached()) {
                return true;
            }
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv4 = this.pointerInputFilter;
            int kind$iv3 = NodeKind.m5726constructorimpl(i3);
            MutableVector mutableVector4 = null;
            Modifier.Node node2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
            while (node2 != null) {
                if (node2 instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it3 = (PointerInputModifierNode) node2;
                    it3.mo200onPointerEventH0pRuoY(event, PointerEventPass.Main, size);
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                    kind$iv = kind$iv3;
                } else {
                    Modifier.Node this_$iv$iv4 = node2;
                    if (!((this_$iv$iv4.getKindSet() & kind$iv3) != 0) || !(node2 instanceof DelegatingNode)) {
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                        kind$iv = kind$iv3;
                    } else {
                        int count$iv3 = 0;
                        DelegatingNode this_$iv$iv5 = (DelegatingNode) node2;
                        Modifier.Node node$iv$iv2 = this_$iv$iv5.getDelegate();
                        while (node$iv$iv2 != null) {
                            Modifier.Node next$iv2 = node$iv$iv2;
                            if (!((next$iv2.getKindSet() & kind$iv3) != 0)) {
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                kind$iv2 = kind$iv3;
                            } else {
                                count$iv3++;
                                if (count$iv3 == 1) {
                                    node2 = next$iv2;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                    kind$iv2 = kind$iv3;
                                } else {
                                    if (mutableVector4 != null) {
                                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                        kind$iv2 = kind$iv3;
                                        mutableVector = mutableVector4;
                                    } else {
                                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                        kind$iv2 = kind$iv3;
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    mutableVector4 = mutableVector;
                                    Modifier.Node theNode$iv2 = node2;
                                    if (theNode$iv2 != null) {
                                        if (mutableVector4 != null) {
                                            mutableVector4.add(theNode$iv2);
                                        }
                                        node2 = null;
                                    }
                                    if (mutableVector4 != null) {
                                        mutableVector4.add(next$iv2);
                                    }
                                }
                            }
                            node$iv$iv2 = node$iv$iv2.getChild();
                            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                            kind$iv3 = kind$iv2;
                        }
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                        kind$iv = kind$iv3;
                        if (count$iv3 == 1) {
                            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
                            kind$iv3 = kind$iv;
                        }
                    }
                }
                node2 = DelegatableNodeKt.pop(mutableVector4);
                $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
                kind$iv3 = kind$iv;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        boolean z;
        Node this_$iv;
        int $i$f$dispatchIfNeeded;
        Node this_$iv2;
        int $i$f$dispatchIfNeeded2;
        MutableVector mutableVector;
        Node this_$iv3 = this;
        int $i$f$dispatchIfNeeded3 = 0;
        boolean result = false;
        if (!this_$iv3.relevantChanges.isEmpty() && this_$iv3.pointerInputFilter.getIsAttached()) {
            PointerEvent event = this.pointerEvent;
            Intrinsics.checkNotNull(event);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long size = layoutCoordinates.mo5499getSizeYbymL2g();
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = this.pointerInputFilter;
            int m5726constructorimpl = NodeKind.m5726constructorimpl(16);
            MutableVector mutableVector2 = null;
            Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv;
            while (true) {
                int i = 1;
                if (node == null) {
                    break;
                }
                if (node instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) node;
                    it.mo200onPointerEventH0pRuoY(event, PointerEventPass.Final, size);
                    this_$iv = this_$iv3;
                    $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                } else {
                    Modifier.Node this_$iv$iv = node;
                    Modifier.Node this_$iv$iv2 = (this_$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? 1 : null;
                    if (this_$iv$iv2 == null || !(node instanceof DelegatingNode)) {
                        this_$iv = this_$iv3;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                    } else {
                        int count$iv = 0;
                        DelegatingNode this_$iv$iv3 = (DelegatingNode) node;
                        Modifier.Node node$iv$iv = this_$iv$iv3.getDelegate();
                        while (node$iv$iv != null) {
                            Modifier.Node next$iv = node$iv$iv;
                            if (((next$iv.getKindSet() & m5726constructorimpl) != 0 ? i : 0) == 0) {
                                this_$iv2 = this_$iv3;
                                $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                            } else {
                                count$iv++;
                                if (count$iv == i) {
                                    node = next$iv;
                                    this_$iv2 = this_$iv3;
                                    $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                } else {
                                    if (mutableVector2 != null) {
                                        this_$iv2 = this_$iv3;
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        mutableVector = mutableVector2;
                                    } else {
                                        this_$iv2 = this_$iv3;
                                        $i$f$dispatchIfNeeded2 = $i$f$dispatchIfNeeded3;
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    mutableVector2 = mutableVector;
                                    Modifier.Node theNode$iv = node;
                                    if (theNode$iv != null) {
                                        if (mutableVector2 != null) {
                                            mutableVector2.add(theNode$iv);
                                        }
                                        node = null;
                                    }
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(next$iv);
                                    }
                                }
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            this_$iv3 = this_$iv2;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded2;
                            i = 1;
                        }
                        this_$iv = this_$iv3;
                        $i$f$dispatchIfNeeded = $i$f$dispatchIfNeeded3;
                        if (count$iv == 1) {
                            this_$iv3 = this_$iv;
                            $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
                        }
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector2);
                this_$iv3 = this_$iv;
                $i$f$dispatchIfNeeded3 = $i$f$dispatchIfNeeded;
            }
            if (this.pointerInputFilter.getIsAttached()) {
                MutableVector this_$iv4 = getChildren();
                int size$iv = this_$iv4.getSize();
                if (size$iv > 0) {
                    int i$iv = 0;
                    Object[] content$iv = this_$iv4.getContent();
                    do {
                        Node it2 = (Node) content$iv[i$iv];
                        it2.dispatchFinalEventPass(internalPointerEvent);
                        z = true;
                        i$iv++;
                    } while (i$iv < size$iv);
                } else {
                    z = true;
                }
            } else {
                z = true;
            }
            result = z;
        }
        cleanUpHits(internalPointerEvent);
        clearCache();
        return result;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean buildCache(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        Object it$iv;
        boolean z;
        boolean z2;
        int j;
        int i;
        int i2;
        boolean childChanged;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        boolean childChanged2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        MutableVector mutableVector;
        boolean childChanged3 = super.buildCache(changes, parentCoordinates, internalPointerEvent, isInBounds);
        int i3 = 1;
        if (!this.pointerInputFilter.getIsAttached()) {
            return true;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = this.pointerInputFilter;
        int m5726constructorimpl = NodeKind.m5726constructorimpl(16);
        MutableVector mutableVector2 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (node != null) {
            if (node instanceof PointerInputModifierNode) {
                this.coordinates = PointerInputModifierNodeKt.getLayoutCoordinates((PointerInputModifierNode) node);
                childChanged = childChanged3;
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
            } else {
                Modifier.Node this_$iv$iv = node;
                if (((this_$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? i3 : 0) == 0 || !(node instanceof DelegatingNode)) {
                    childChanged = childChanged3;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                } else {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & m5726constructorimpl) != 0 ? i3 : 0) != 0) {
                            count$iv++;
                            if (count$iv == i3) {
                                node = next$iv;
                                childChanged2 = childChanged3;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            } else {
                                if (mutableVector2 == null) {
                                    childChanged2 = childChanged3;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                } else {
                                    childChanged2 = childChanged3;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                    mutableVector = mutableVector2;
                                }
                                mutableVector2 = mutableVector;
                                Modifier.Node theNode$iv = node;
                                if (theNode$iv != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv);
                                    }
                                    node = null;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(next$iv);
                                }
                            }
                        } else {
                            childChanged2 = childChanged3;
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        childChanged3 = childChanged2;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        i3 = 1;
                    }
                    childChanged = childChanged3;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    if (count$iv == 1) {
                        childChanged3 = childChanged;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        i3 = 1;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector2);
            childChanged3 = childChanged;
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
            i3 = 1;
        }
        boolean childChanged4 = childChanged3;
        int j2 = 0;
        int size = changes.size();
        while (j2 < size) {
            long keyValue = changes.keyAt(j2);
            PointerInputChange change = changes.valueAt(j2);
            if (this.pointerIds.contains(keyValue)) {
                long prevPosition = change.getPreviousPosition();
                long currentPosition = change.getPosition();
                if (Offset.m3925isValidimpl(prevPosition) && Offset.m3925isValidimpl(currentPosition)) {
                    ArrayList historical = new ArrayList(change.getHistorical().size());
                    List $this$fastForEach$iv = change.getHistorical();
                    int size2 = $this$fastForEach$iv.size();
                    j = j2;
                    int j3 = 0;
                    while (j3 < size2) {
                        Object item$iv = $this$fastForEach$iv.get(j3);
                        HistoricalChange it = (HistoricalChange) item$iv;
                        int index$iv = j3;
                        int i4 = size;
                        long historicalPosition = it.getPosition();
                        if (Offset.m3925isValidimpl(historicalPosition)) {
                            long uptimeMillis = it.getUptimeMillis();
                            i2 = size2;
                            LayoutCoordinates layoutCoordinates = this.coordinates;
                            Intrinsics.checkNotNull(layoutCoordinates);
                            historical.add(new HistoricalChange(uptimeMillis, layoutCoordinates.mo5500localPositionOfR5De75A(parentCoordinates, historicalPosition), it.getOriginalEventPosition(), null));
                        } else {
                            i2 = size2;
                        }
                        j3 = index$iv + 1;
                        size2 = i2;
                        size = i4;
                    }
                    i = size;
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    long mo5500localPositionOfR5De75A = layoutCoordinates2.mo5500localPositionOfR5De75A(parentCoordinates, prevPosition);
                    LayoutCoordinates layoutCoordinates3 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates3);
                    longSparseArray.put(keyValue, PointerInputChange.m5351copyOHpmEuE$default(change, 0L, 0L, layoutCoordinates3.mo5500localPositionOfR5De75A(parentCoordinates, currentPosition), false, 0L, mo5500localPositionOfR5De75A, false, 0, historical, 0L, 731, null));
                } else {
                    j = j2;
                    i = size;
                }
            } else {
                j = j2;
                i = size;
            }
            j2 = j + 1;
            size = i;
        }
        if (this.relevantChanges.isEmpty()) {
            this.pointerIds.clear();
            getChildren().clear();
            return true;
        }
        PointerIdArray this_$iv = this.pointerIds;
        int i5 = this_$iv.getSize();
        while (true) {
            i5--;
            if (-1 >= i5) {
                break;
            }
            long pointerId = this.pointerIds.m5457get_I2yYro(i5);
            if (!changes.containsKey(pointerId)) {
                this.pointerIds.removeAt(i5);
            }
        }
        ArrayList changesList = new ArrayList(this.relevantChanges.size());
        int size3 = this.relevantChanges.size();
        for (int i6 = 0; i6 < size3; i6++) {
            changesList.add(this.relevantChanges.valueAt(i6));
        }
        PointerEvent event = new PointerEvent(changesList, internalPointerEvent);
        List $this$fastFirstOrNull$iv = event.getChanges();
        int index$iv$iv = 0;
        int size4 = $this$fastFirstOrNull$iv.size();
        while (true) {
            if (index$iv$iv >= size4) {
                it$iv = null;
                break;
            }
            Object item$iv$iv = $this$fastFirstOrNull$iv.get(index$iv$iv);
            it$iv = item$iv$iv;
            if (internalPointerEvent.m5291activeHoverEvent0FcD4WY(((PointerInputChange) it$iv).getId())) {
                break;
            }
            index$iv$iv++;
        }
        PointerInputChange activeHoverChange = (PointerInputChange) it$iv;
        if (activeHoverChange != null) {
            if (isInBounds) {
                z = false;
                if (this.isIn) {
                    z2 = true;
                } else if (activeHoverChange.getPressed() || activeHoverChange.getPreviousPressed()) {
                    LayoutCoordinates layoutCoordinates4 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates4);
                    long size5 = layoutCoordinates4.mo5499getSizeYbymL2g();
                    z2 = true;
                    this.isIn = !PointerEventKt.m5306isOutOfBoundsO0kMr_c(activeHoverChange, size5);
                } else {
                    z2 = true;
                }
            } else {
                z = false;
                this.isIn = false;
                z2 = true;
            }
            if (this.isIn != this.wasIn && (PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5317getMove7fucELk()) || PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5315getEnter7fucELk()) || PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5316getExit7fucELk()))) {
                event.m5305setTypeEhbLWgg$ui_release(this.isIn ? PointerEventType.INSTANCE.m5315getEnter7fucELk() : PointerEventType.INSTANCE.m5316getExit7fucELk());
            } else if (PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5315getEnter7fucELk()) && this.wasIn && !this.hasExited) {
                event.m5305setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5317getMove7fucELk());
            } else if (PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5316getExit7fucELk()) && this.isIn && activeHoverChange.getPressed()) {
                event.m5305setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5317getMove7fucELk());
            }
        } else {
            z = false;
            z2 = true;
        }
        boolean changed = (childChanged4 || !PointerEventType.m5311equalsimpl0(event.getType(), PointerEventType.INSTANCE.m5317getMove7fucELk()) || hasPositionChanged(this.pointerEvent, event)) ? z2 : z;
        this.pointerEvent = event;
        return changed;
    }

    private final boolean hasPositionChanged(PointerEvent oldEvent, PointerEvent newEvent) {
        if (oldEvent == null || oldEvent.getChanges().size() != newEvent.getChanges().size()) {
            return true;
        }
        int size = newEvent.getChanges().size();
        for (int i = 0; i < size; i++) {
            PointerInputChange old = oldEvent.getChanges().get(i);
            PointerInputChange current = newEvent.getChanges().get(i);
            if (!Offset.m3919equalsimpl0(old.getPosition(), current.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
    }

    private final boolean dispatchIfNeeded(Function0<Unit> block) {
        if (this.relevantChanges.isEmpty() || !this.pointerInputFilter.getIsAttached()) {
            return false;
        }
        block.invoke();
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        int i;
        MutableVector mutableVector;
        MutableVector this_$iv = getChildren();
        int size$iv = this_$iv.getSize();
        int i2 = 1;
        if (size$iv > 0) {
            int i$iv = 0;
            Object[] content$iv = this_$iv.getContent();
            do {
                Node it = (Node) content$iv[i$iv];
                it.dispatchCancel();
                i$iv++;
            } while (i$iv < size$iv);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = this.pointerInputFilter;
        int m5726constructorimpl = NodeKind.m5726constructorimpl(16);
        MutableVector mutableVector2 = null;
        Modifier.Node node = $this$dispatchForKind_u2d6rFNWt0$iv;
        while (node != null) {
            if (node instanceof PointerInputModifierNode) {
                PointerInputModifierNode it2 = (PointerInputModifierNode) node;
                it2.onCancelPointerInput();
                i = i2;
            } else {
                Modifier.Node this_$iv$iv = node;
                if (((this_$iv$iv.getKindSet() & m5726constructorimpl) != 0 ? i2 : 0) == 0 || !(node instanceof DelegatingNode)) {
                    i = i2;
                } else {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & m5726constructorimpl) != 0 ? i2 : 0) != 0) {
                            count$iv++;
                            if (count$iv == i2) {
                                node = next$iv;
                            } else {
                                if (mutableVector2 != null) {
                                    mutableVector = mutableVector2;
                                } else {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                mutableVector2 = mutableVector;
                                Modifier.Node theNode$iv = node;
                                if (theNode$iv != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode$iv);
                                    }
                                    node = null;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i2 = 1;
                    }
                    i = 1;
                    if (count$iv == 1) {
                        i2 = 1;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector2);
            i2 = i;
        }
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        PointerEvent event;
        List $this$fastForEach$iv;
        super.cleanUpHits(internalPointerEvent);
        PointerEvent event2 = this.pointerEvent;
        if (event2 == null) {
            return;
        }
        this.wasIn = this.isIn;
        List $this$fastForEach$iv2 = event2.getChanges();
        int index$iv = 0;
        int size = $this$fastForEach$iv2.size();
        while (true) {
            boolean removePointerId = false;
            if (index$iv >= size) {
                this.isIn = false;
                this.hasExited = PointerEventType.m5311equalsimpl0(event2.getType(), PointerEventType.INSTANCE.m5316getExit7fucELk());
                return;
            }
            Object item$iv = $this$fastForEach$iv2.get(index$iv);
            PointerInputChange change = (PointerInputChange) item$iv;
            boolean released = !change.getPressed();
            boolean nonHoverEventStream = !internalPointerEvent.m5291activeHoverEvent0FcD4WY(change.getId());
            boolean outsideArea = !this.isIn;
            if ((released && nonHoverEventStream) || (released && outsideArea)) {
                removePointerId = true;
            }
            if (!removePointerId) {
                event = event2;
                $this$fastForEach$iv = $this$fastForEach$iv2;
            } else {
                event = event2;
                $this$fastForEach$iv = $this$fastForEach$iv2;
                this.pointerIds.m5458remove0FcD4WY(change.getId());
            }
            index$iv++;
            event2 = event;
            $this$fastForEach$iv2 = $this$fastForEach$iv;
        }
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.pointerInputFilter + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }
}
