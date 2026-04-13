package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0002\b\u0016\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010H\u001a\u00020C2\b\b\u0002\u0010I\u001a\u00020\nJ\u0006\u0010J\u001a\u00020KJ\u0006\u0010L\u001a\u00020KJ\u000e\u0010M\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\nJ\u0006\u0010N\u001a\u00020KJ\u0006\u0010O\u001a\u00020KJ\f\u0010P\u001a\b\u0012\u0004\u0012\u00020R0QJ\u0010\u0010S\u001a\u0004\u0018\u00010\u00012\u0006\u0010I\u001a\u00020\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010I\u001a\u00020\nJ\u000e\u0010\u0018\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u0010\u0010T\u001a\u0004\u0018\u00010\u00012\u0006\u0010I\u001a\u00020\nJ\u0018\u0010T\u001a\u0004\u0018\u00010\u00012\u0006\u0010U\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010H\u001a\u00020CJ\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u0006\u0010I\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u000e\u0010V\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\nJ\u000e\u0010+\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\nJ\u000e\u00100\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\nJ\b\u0010W\u001a\u0004\u0018\u00010\u0001J\u0010\u0010X\u001a\u0004\u0018\u00010\u00012\u0006\u0010I\u001a\u00020\nJ\u000e\u00101\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u000e\u00103\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u000e\u0010Y\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nJ\u000e\u0010Z\u001a\u00020K2\u0006\u0010I\u001a\u00020\nJ\u000e\u0010[\u001a\u00020K2\u0006\u0010I\u001a\u00020\nJ\u0006\u0010\\\u001a\u00020\nJ\u0006\u0010]\u001a\u00020KJ\u0006\u0010^\u001a\u00020KJ\u0006\u0010_\u001a\u00020KJ\b\u0010`\u001a\u00020aH\u0016J\u0016\u0010b\u001a\u0004\u0018\u00010\u0001*\u00020'2\u0006\u0010I\u001a\u00020\nH\u0002J\u0016\u0010X\u001a\u0004\u0018\u00010\u0001*\u00020'2\u0006\u0010I\u001a\u00020\nH\u0002J\u0016\u0010c\u001a\u0004\u0018\u00010\u0001*\u00020'2\u0006\u0010I\u001a\u00020\nH\u0002R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\rR\u0011\u0010\u001a\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\rR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010 \u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b!\u0010\rR\u0011\u0010\"\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b#\u0010\rR\u0011\u0010$\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b%\u0010\rR\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\tR\u0011\u0010+\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b,\u0010\tR\u0011\u0010-\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b.\u0010\tR\u0011\u0010/\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b/\u0010\tR\u0011\u00100\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b0\u0010\tR\u0011\u00101\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b2\u0010\rR\u001e\u00103\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\rR\u0011\u00105\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b6\u0010\rR\u0011\u00107\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b8\u0010\rR\u0011\u00109\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b:\u0010\rR\u0011\u0010;\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b<\u0010\rR\u0018\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010>X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?R\u000e\u0010@\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010A\u001a\"\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020D\u0018\u00010Bj\u0010\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020D\u0018\u0001`EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010G¨\u0006d"}, d2 = {"Landroidx/compose/runtime/SlotReader;", "", "table", "Landroidx/compose/runtime/SlotTable;", "(Landroidx/compose/runtime/SlotTable;)V", "<set-?>", "", "closed", "getClosed", "()Z", "", "currentEnd", "getCurrentEnd", "()I", "currentGroup", "getCurrentGroup", "currentSlot", "currentSlotEnd", "currentSlotStack", "Landroidx/compose/runtime/IntStack;", "emptyCount", "groupAux", "getGroupAux", "()Ljava/lang/Object;", "groupEnd", "getGroupEnd", "groupKey", "getGroupKey", "groupNode", "getGroupNode", "groupObjectKey", "getGroupObjectKey", "groupSize", "getGroupSize", "groupSlotCount", "getGroupSlotCount", "groupSlotIndex", "getGroupSlotIndex", "groups", "", "groupsSize", "hadNext", "getHadNext", "hasObjectKey", "getHasObjectKey", "inEmpty", "getInEmpty", "isGroupEnd", "isNode", "nodeCount", "getNodeCount", "parent", "getParent", "parentNodes", "getParentNodes", "remainingSlots", "getRemainingSlots", "size", "getSize", "slot", "getSlot", "slots", "", "[Ljava/lang/Object;", "slotsSize", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/Anchor;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "anchor", "index", "beginEmpty", "", "close", "containsMark", "endEmpty", "endGroup", "extractKeys", "", "Landroidx/compose/runtime/KeyInfo;", "get", "groupGet", "group", "hasMark", "next", "node", "parentOf", "reposition", "restoreParent", "skipGroup", "skipToGroupEnd", "startGroup", "startNode", "toString", "", "aux", "objectKey", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SlotReader {
    public static final int $stable = 8;
    private boolean closed;

    /* renamed from: currentEnd, reason: from kotlin metadata and from toString */
    private int end;

    /* renamed from: currentGroup, reason: from kotlin metadata and from toString */
    private int current;
    private int currentSlot;
    private int currentSlotEnd;
    private int emptyCount;
    private final int[] groups;
    private final int groupsSize;
    private boolean hadNext;
    private final Object[] slots;
    private final int slotsSize;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private final SlotTable table;
    private int parent = -1;
    private final IntStack currentSlotStack = new IntStack();

    public SlotReader(SlotTable table) {
        this.table = table;
        this.groups = this.table.getGroups();
        this.groupsSize = this.table.getGroupsSize();
        this.slots = this.table.getSlots();
        this.slotsSize = this.table.getSlotsSize();
        this.end = this.groupsSize;
    }

    /* renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    /* renamed from: getCurrentGroup, reason: from getter */
    public final int getCurrent() {
        return this.current;
    }

    /* renamed from: getCurrentEnd, reason: from getter */
    public final int getEnd() {
        return this.end;
    }

    public final int getParent() {
        return this.parent;
    }

    /* renamed from: getSize, reason: from getter */
    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final int getSlot() {
        int slotAnchor;
        int i = this.currentSlot;
        slotAnchor = SlotTableKt.slotAnchor(this.groups, this.parent);
        return i - slotAnchor;
    }

    public final int parent(int index) {
        int parentAnchor;
        parentAnchor = SlotTableKt.parentAnchor(this.groups, index);
        return parentAnchor;
    }

    public final boolean isNode() {
        boolean isNode;
        isNode = SlotTableKt.isNode(this.groups, this.current);
        return isNode;
    }

    public final boolean isNode(int index) {
        boolean isNode;
        isNode = SlotTableKt.isNode(this.groups, index);
        return isNode;
    }

    public final int getNodeCount() {
        int nodeCount;
        nodeCount = SlotTableKt.nodeCount(this.groups, this.current);
        return nodeCount;
    }

    public final int nodeCount(int index) {
        int nodeCount;
        nodeCount = SlotTableKt.nodeCount(this.groups, index);
        return nodeCount;
    }

    public final Object node(int index) {
        boolean isNode;
        isNode = SlotTableKt.isNode(this.groups, index);
        if (isNode) {
            return node(this.groups, index);
        }
        return null;
    }

    public final boolean isGroupEnd() {
        return getInEmpty() || this.current == this.end;
    }

    public final boolean getInEmpty() {
        return this.emptyCount > 0;
    }

    public final int getGroupSize() {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, this.current);
        return groupSize;
    }

    public final int groupSize(int index) {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, index);
        return groupSize;
    }

    public final int getGroupEnd() {
        return this.end;
    }

    public final int groupEnd(int index) {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, index);
        return groupSize + index;
    }

    public final int getGroupKey() {
        int key;
        if (this.current < this.end) {
            key = SlotTableKt.key(this.groups, this.current);
            return key;
        }
        return 0;
    }

    public final int groupKey(int index) {
        int key;
        key = SlotTableKt.key(this.groups, index);
        return key;
    }

    public final int getGroupSlotIndex() {
        int slotAnchor;
        int i = this.currentSlot;
        slotAnchor = SlotTableKt.slotAnchor(this.groups, this.parent);
        return i - slotAnchor;
    }

    public final boolean hasObjectKey(int index) {
        boolean hasObjectKey;
        hasObjectKey = SlotTableKt.hasObjectKey(this.groups, index);
        return hasObjectKey;
    }

    public final boolean getHasObjectKey() {
        boolean hasObjectKey;
        if (this.current < this.end) {
            hasObjectKey = SlotTableKt.hasObjectKey(this.groups, this.current);
            if (hasObjectKey) {
                return true;
            }
        }
        return false;
    }

    public final Object getGroupObjectKey() {
        if (this.current < this.end) {
            return objectKey(this.groups, this.current);
        }
        return null;
    }

    public final Object groupObjectKey(int index) {
        return objectKey(this.groups, index);
    }

    public final Object getGroupAux() {
        if (this.current < this.end) {
            return aux(this.groups, this.current);
        }
        return 0;
    }

    public final Object groupAux(int index) {
        return aux(this.groups, index);
    }

    public final Object getGroupNode() {
        if (this.current < this.end) {
            return node(this.groups, this.current);
        }
        return null;
    }

    public final int groupKey(Anchor anchor) {
        int key;
        if (!anchor.getValid()) {
            return 0;
        }
        key = SlotTableKt.key(this.groups, this.table.anchorIndex(anchor));
        return key;
    }

    public final boolean hasMark(int index) {
        boolean hasMark;
        hasMark = SlotTableKt.hasMark(this.groups, index);
        return hasMark;
    }

    public final boolean containsMark(int index) {
        boolean containsMark;
        containsMark = SlotTableKt.containsMark(this.groups, index);
        return containsMark;
    }

    public final int getParentNodes() {
        int nodeCount;
        if (this.parent < 0) {
            return 0;
        }
        nodeCount = SlotTableKt.nodeCount(this.groups, this.parent);
        return nodeCount;
    }

    public final int getRemainingSlots() {
        return this.currentSlotEnd - this.currentSlot;
    }

    public final int parentOf(int index) {
        int parentAnchor;
        boolean value$iv = index >= 0 && index < this.groupsSize;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Invalid group index " + index);
        }
        parentAnchor = SlotTableKt.parentAnchor(this.groups, index);
        return parentAnchor;
    }

    public final int getGroupSlotCount() {
        int start;
        int current = this.current;
        start = SlotTableKt.slotAnchor(this.groups, current);
        int next = current + 1;
        int end = next < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, next) : this.slotsSize;
        return end - start;
    }

    public final Object get(int index) {
        int slotIndex = this.currentSlot + index;
        return slotIndex < this.currentSlotEnd ? this.slots[slotIndex] : Composer.INSTANCE.getEmpty();
    }

    public final Object groupGet(int index) {
        return groupGet(this.current, index);
    }

    public final Object groupGet(int group, int index) {
        int start;
        start = SlotTableKt.slotAnchor(this.groups, group);
        int next = group + 1;
        int end = next < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, next) : this.slotsSize;
        int address = start + index;
        return address < end ? this.slots[address] : Composer.INSTANCE.getEmpty();
    }

    public final Object next() {
        if (this.emptyCount > 0 || this.currentSlot >= this.currentSlotEnd) {
            this.hadNext = false;
            return Composer.INSTANCE.getEmpty();
        }
        this.hadNext = true;
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[i];
    }

    public final boolean getHadNext() {
        return this.hadNext;
    }

    public final void beginEmpty() {
        this.emptyCount++;
    }

    public final void endEmpty() {
        boolean value$iv = this.emptyCount > 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Unbalanced begin/end empty");
        }
        this.emptyCount--;
    }

    public final void close() {
        this.closed = true;
        this.table.close$runtime_release(this, this.sourceInformationMap);
    }

    public final void startGroup() {
        int parentAnchor;
        int groupSize;
        int slotAnchor;
        int dataAnchor;
        GroupSourceInformation groupSourceInformation;
        if (this.emptyCount <= 0) {
            int parent = this.parent;
            int currentGroup = this.current;
            parentAnchor = SlotTableKt.parentAnchor(this.groups, currentGroup);
            boolean value$iv = parentAnchor == parent;
            if (!value$iv) {
                PreconditionsKt.throwIllegalArgumentException("Invalid slot table detected");
            }
            HashMap<Anchor, GroupSourceInformation> hashMap = this.sourceInformationMap;
            if (hashMap != null && (groupSourceInformation = hashMap.get(anchor(parent))) != null) {
                groupSourceInformation.reportGroup(this.table, currentGroup);
            }
            IntStack currentSlotStack = this.currentSlotStack;
            int currentSlot = this.currentSlot;
            int currentEndSlot = this.currentSlotEnd;
            if (currentSlot == 0 && currentEndSlot == 0) {
                currentSlotStack.push(-1);
            } else {
                currentSlotStack.push(currentSlot);
            }
            this.parent = currentGroup;
            groupSize = SlotTableKt.groupSize(this.groups, currentGroup);
            this.end = groupSize + currentGroup;
            this.current = currentGroup + 1;
            slotAnchor = SlotTableKt.slotAnchor(this.groups, currentGroup);
            this.currentSlot = slotAnchor;
            if (currentGroup < this.groupsSize - 1) {
                dataAnchor = SlotTableKt.dataAnchor(this.groups, currentGroup + 1);
            } else {
                dataAnchor = this.slotsSize;
            }
            this.currentSlotEnd = dataAnchor;
        }
    }

    public final void startNode() {
        boolean value$iv;
        if (this.emptyCount <= 0) {
            value$iv = SlotTableKt.isNode(this.groups, this.current);
            if (!value$iv) {
                PreconditionsKt.throwIllegalArgumentException("Expected a node group");
            }
            startGroup();
        }
    }

    public final int skipGroup() {
        boolean isNode;
        int groupSize;
        boolean value$iv = this.emptyCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip while in an empty region");
        }
        isNode = SlotTableKt.isNode(this.groups, this.current);
        int count = isNode ? 1 : SlotTableKt.nodeCount(this.groups, this.current);
        int i = this.current;
        groupSize = SlotTableKt.groupSize(this.groups, this.current);
        this.current = i + groupSize;
        return count;
    }

    public final void skipToGroupEnd() {
        boolean value$iv = this.emptyCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip the enclosing group while in an empty region");
        }
        this.current = this.end;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void reposition(int index) {
        int groupSize;
        boolean value$iv = this.emptyCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot reposition while in an empty region");
        }
        this.current = index;
        int parent = index < this.groupsSize ? SlotTableKt.parentAnchor(this.groups, index) : -1;
        this.parent = parent;
        if (parent >= 0) {
            groupSize = SlotTableKt.groupSize(this.groups, parent);
            this.end = groupSize + parent;
        } else {
            this.end = this.groupsSize;
        }
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void restoreParent(int index) {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, index);
        int newCurrentEnd = groupSize + index;
        int current = this.current;
        boolean value$iv = current >= index && current <= newCurrentEnd;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Index " + index + " is not a parent of " + current);
        }
        this.parent = index;
        this.end = newCurrentEnd;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void endGroup() {
        int parent;
        int groupSize;
        int i;
        int dataAnchor;
        if (this.emptyCount == 0) {
            boolean value$iv = this.current == this.end;
            if (!value$iv) {
                ComposerKt.composeImmediateRuntimeError("endGroup() not called at the end of a group");
            }
            parent = SlotTableKt.parentAnchor(this.groups, this.parent);
            this.parent = parent;
            if (parent >= 0) {
                groupSize = SlotTableKt.groupSize(this.groups, parent);
                i = groupSize + parent;
            } else {
                i = this.groupsSize;
            }
            this.end = i;
            IntStack currentSlotStack = this.currentSlotStack;
            int newCurrentSlot = currentSlotStack.pop();
            if (newCurrentSlot < 0) {
                this.currentSlot = 0;
                this.currentSlotEnd = 0;
                return;
            }
            this.currentSlot = newCurrentSlot;
            if (parent < this.groupsSize - 1) {
                dataAnchor = SlotTableKt.dataAnchor(this.groups, parent + 1);
            } else {
                dataAnchor = this.slotsSize;
            }
            this.currentSlotEnd = dataAnchor;
        }
    }

    public final List<KeyInfo> extractKeys() {
        int key;
        boolean isNode;
        int groupSize;
        List result = new ArrayList();
        if (this.emptyCount > 0) {
            return result;
        }
        int index = 0;
        int childIndex = this.current;
        while (true) {
            int index2 = index;
            int index3 = this.end;
            if (childIndex < index3) {
                key = SlotTableKt.key(this.groups, childIndex);
                Object objectKey = objectKey(this.groups, childIndex);
                isNode = SlotTableKt.isNode(this.groups, childIndex);
                int nodeCount = isNode ? 1 : SlotTableKt.nodeCount(this.groups, childIndex);
                index = index2 + 1;
                result.add(new KeyInfo(key, objectKey, childIndex, nodeCount, index2));
                groupSize = SlotTableKt.groupSize(this.groups, childIndex);
                childIndex += groupSize;
            } else {
                return result;
            }
        }
    }

    public String toString() {
        return "SlotReader(current=" + this.current + ", key=" + getGroupKey() + ", parent=" + this.parent + ", end=" + this.end + ')';
    }

    public static /* synthetic */ Anchor anchor$default(SlotReader slotReader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotReader.current;
        }
        return slotReader.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList $this$getOrAdd$iv = this.table.getAnchors$runtime_release();
        int effectiveSize$iv = this.groupsSize;
        int location$iv = SlotTableKt.search($this$getOrAdd$iv, index, effectiveSize$iv);
        if (location$iv < 0) {
            Anchor anchor$iv = new Anchor(index);
            $this$getOrAdd$iv.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        return $this$getOrAdd$iv.get(location$iv);
    }

    private final Object node(int[] $this$node, int index) {
        boolean isNode;
        int nodeIndex;
        isNode = SlotTableKt.isNode($this$node, index);
        if (isNode) {
            Object[] objArr = this.slots;
            nodeIndex = SlotTableKt.nodeIndex($this$node, index);
            return objArr[nodeIndex];
        }
        return Composer.INSTANCE.getEmpty();
    }

    private final Object aux(int[] $this$aux, int index) {
        boolean hasAux;
        int auxIndex;
        hasAux = SlotTableKt.hasAux($this$aux, index);
        if (hasAux) {
            Object[] objArr = this.slots;
            auxIndex = SlotTableKt.auxIndex($this$aux, index);
            return objArr[auxIndex];
        }
        return Composer.INSTANCE.getEmpty();
    }

    private final Object objectKey(int[] $this$objectKey, int index) {
        boolean hasObjectKey;
        int objectKeyIndex;
        hasObjectKey = SlotTableKt.hasObjectKey($this$objectKey, index);
        if (hasObjectKey) {
            Object[] objArr = this.slots;
            objectKeyIndex = SlotTableKt.objectKeyIndex($this$objectKey, index);
            return objArr[objectKeyIndex];
        }
        return null;
    }
}
