package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0017H\u0096\u0002J\t\u0010\u0018\u001a\u00020\u0002H\u0096\u0002R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/SourceInformationGroupIterator;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "table", "Landroidx/compose/runtime/SlotTable;", "parent", "", "group", "Landroidx/compose/runtime/GroupSourceInformation;", "path", "Landroidx/compose/runtime/SourceInformationGroupPath;", "(Landroidx/compose/runtime/SlotTable;ILandroidx/compose/runtime/GroupSourceInformation;Landroidx/compose/runtime/SourceInformationGroupPath;)V", "getGroup", "()Landroidx/compose/runtime/GroupSourceInformation;", "index", "getParent", "()I", "getPath", "()Landroidx/compose/runtime/SourceInformationGroupPath;", "getTable", "()Landroidx/compose/runtime/SlotTable;", "version", "hasNext", "", "next", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class SourceInformationGroupIterator implements Iterator<CompositionGroup>, KMappedMarker {
    private final GroupSourceInformation group;
    private int index;
    private final int parent;
    private final SourceInformationGroupPath path;
    private final SlotTable table;
    private final int version;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public SourceInformationGroupIterator(SlotTable table, int parent, GroupSourceInformation group, SourceInformationGroupPath path) {
        this.table = table;
        this.parent = parent;
        this.group = group;
        this.path = path;
        this.version = this.table.getVersion();
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getParent() {
        return this.parent;
    }

    public final GroupSourceInformation getGroup() {
        return this.group;
    }

    public final SourceInformationGroupPath getPath() {
        return this.path;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        ArrayList it = this.group.getGroups();
        return it != null && this.index < it.size();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public CompositionGroup next() {
        Object group;
        ArrayList<Object> groups = this.group.getGroups();
        if (groups != null) {
            int i = this.index;
            this.index = i + 1;
            group = groups.get(i);
        } else {
            group = null;
        }
        if (group instanceof Anchor) {
            return new SlotTableGroup(this.table, ((Anchor) group).getLocation(), this.version);
        }
        if (group instanceof GroupSourceInformation) {
            return new SourceInformationSlotTableGroup(this.table, this.parent, (GroupSourceInformation) group, new RelativeGroupPath(this.path, this.index - 1));
        }
        ComposerKt.composeRuntimeError("Unexpected group information structure");
        throw new KotlinNothingValueException();
    }
}
