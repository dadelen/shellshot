package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0001H\u0002J\u001e\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003J\u000e\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020!2\u0006\u0010(\u001a\u00020\u0003J\u0010\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020\u0000H\u0002J\u000e\u0010.\u001a\u00020\t2\u0006\u0010+\u001a\u00020,J\u0016\u0010/\u001a\u00020!2\u0006\u00100\u001a\u0002012\u0006\u0010\"\u001a\u00020\u0003J\u0016\u0010/\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u0003J\u001e\u00102\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0003R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R.\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u00063"}, d2 = {"Landroidx/compose/runtime/GroupSourceInformation;", "", "key", "", "sourceInformation", "", "dataStartOffset", "(ILjava/lang/String;I)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "dataEndOffset", "getDataEndOffset", "()I", "setDataEndOffset", "(I)V", "getDataStartOffset", "groups", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getGroups", "()Ljava/util/ArrayList;", "setGroups", "(Ljava/util/ArrayList;)V", "getKey", "getSourceInformation", "()Ljava/lang/String;", "setSourceInformation", "(Ljava/lang/String;)V", "add", "", "group", "addGroupAfter", "writer", "Landroidx/compose/runtime/SlotWriter;", "predecessor", "close", "dataOffset", "endGrouplessCall", "hasAnchor", "anchor", "Landroidx/compose/runtime/Anchor;", "openInformation", "removeAnchor", "reportGroup", "table", "Landroidx/compose/runtime/SlotTable;", "startGrouplessCall", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GroupSourceInformation {
    public static final int $stable = 8;
    private boolean closed;
    private int dataEndOffset;
    private final int dataStartOffset;
    private ArrayList<Object> groups;
    private final int key;
    private String sourceInformation;

    public GroupSourceInformation(int key, String sourceInformation, int dataStartOffset) {
        this.key = key;
        this.sourceInformation = sourceInformation;
        this.dataStartOffset = dataStartOffset;
    }

    public final int getKey() {
        return this.key;
    }

    public final String getSourceInformation() {
        return this.sourceInformation;
    }

    public final void setSourceInformation(String str) {
        this.sourceInformation = str;
    }

    public final int getDataStartOffset() {
        return this.dataStartOffset;
    }

    public final ArrayList<Object> getGroups() {
        return this.groups;
    }

    public final void setGroups(ArrayList<Object> arrayList) {
        this.groups = arrayList;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void setClosed(boolean z) {
        this.closed = z;
    }

    public final int getDataEndOffset() {
        return this.dataEndOffset;
    }

    public final void setDataEndOffset(int i) {
        this.dataEndOffset = i;
    }

    public final void startGrouplessCall(int key, String sourceInformation, int dataOffset) {
        openInformation().add(new GroupSourceInformation(key, sourceInformation, dataOffset));
    }

    public final void endGrouplessCall(int dataOffset) {
        openInformation().close(dataOffset);
    }

    public final void reportGroup(SlotWriter writer, int group) {
        openInformation().add(writer.anchor(group));
    }

    public final void reportGroup(SlotTable table, int group) {
        openInformation().add(table.anchor(group));
    }

    public final void addGroupAfter(SlotWriter writer, int predecessor, int group) {
        Anchor anchor;
        ArrayList it = this.groups;
        if (it == null) {
            it = new ArrayList();
            this.groups = it;
        }
        int index = 0;
        if (predecessor >= 0 && (anchor = writer.tryAnchor$runtime_release(predecessor)) != null) {
            ArrayList $this$fastIndexOf$iv = it;
            int index$iv = 0;
            int size$iv = $this$fastIndexOf$iv.size();
            while (true) {
                if (index$iv >= size$iv) {
                    index = -1;
                    break;
                }
                Object value$iv = $this$fastIndexOf$iv.get(index$iv);
                if (Intrinsics.areEqual(value$iv, anchor) || ((value$iv instanceof GroupSourceInformation) && ((GroupSourceInformation) value$iv).hasAnchor(anchor))) {
                    index = index$iv;
                    break;
                }
                index$iv++;
            }
        }
        it.add(index, writer.anchor(group));
    }

    public final void close(int dataOffset) {
        this.closed = true;
        this.dataEndOffset = dataOffset;
    }

    private final GroupSourceInformation openInformation() {
        Object value$iv;
        GroupSourceInformation openInformation;
        ArrayList groups = this.groups;
        if (groups != null) {
            int index$iv = groups.size() - 1;
            while (true) {
                if (index$iv < 0) {
                    value$iv = null;
                    break;
                }
                value$iv = groups.get(index$iv);
                if ((value$iv instanceof GroupSourceInformation) && !((GroupSourceInformation) value$iv).closed) {
                    break;
                }
                index$iv--;
            }
        } else {
            value$iv = null;
        }
        GroupSourceInformation groupSourceInformation = value$iv instanceof GroupSourceInformation ? (GroupSourceInformation) value$iv : null;
        return (groupSourceInformation == null || (openInformation = groupSourceInformation.openInformation()) == null) ? this : openInformation;
    }

    private final void add(Object group) {
        ArrayList groups = this.groups;
        if (groups == null) {
            groups = new ArrayList();
        }
        this.groups = groups;
        groups.add(group);
    }

    private final boolean hasAnchor(Anchor anchor) {
        boolean z;
        List list = this.groups;
        if (list == null) {
            return false;
        }
        List $this$fastAny$iv = list;
        int index$iv$iv = 0;
        int size = $this$fastAny$iv.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
                Object it = (Intrinsics.areEqual(item$iv$iv, anchor) || ((item$iv$iv instanceof GroupSourceInformation) && ((GroupSourceInformation) item$iv$iv).hasAnchor(anchor))) ? 1 : null;
                if (it != null) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    public final boolean removeAnchor(Anchor anchor) {
        ArrayList groups = this.groups;
        if (groups != null) {
            for (int index = groups.size() - 1; index >= 0; index--) {
                Object item = groups.get(index);
                if (item instanceof Anchor) {
                    if (Intrinsics.areEqual(item, anchor)) {
                        groups.remove(index);
                    }
                } else if ((item instanceof GroupSourceInformation) && !((GroupSourceInformation) item).removeAnchor(anchor)) {
                    groups.remove(index);
                }
            }
            if (!groups.isEmpty()) {
                return true;
            }
            this.groups = null;
            return false;
        }
        return true;
    }
}
