package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u001cJ\u000e\u0010<\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\u0007J=\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2&\u0010,\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u0001`/H\u0000¢\u0006\u0002\bAJ\u008f\u0001\u0010=\u001a\u00020>2\u0006\u00108\u001a\u00020B2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u000e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$2\u0006\u0010*\u001a\u00020\u001c2\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2&\u0010,\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u0001`/2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0000¢\u0006\u0004\bA\u0010CJ\u0006\u0010D\u001a\u00020>J\u0006\u0010E\u001a\u00020>J\u0006\u0010F\u001a\u00020!J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001c0HH\u0002J\u0012\u0010I\u001a\u0004\u0018\u00010\u00032\u0006\u0010J\u001a\u00020%H\u0016J\u0012\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020\u001cH\u0002J\u0016\u0010N\u001a\u00020!2\u0006\u0010O\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\u0007J\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020\u001c0HH\u0002J\u001d\u0010Q\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010H2\u0006\u0010R\u001a\u00020\u001cH\u0000¢\u0006\u0002\bSJ\u000f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00030UH\u0096\u0002J\u000e\u0010V\u001a\b\u0012\u0004\u0012\u00020\u001c0HH\u0002J\u000e\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001c0HH\u0002J\u0006\u0010X\u001a\u00020@J\u0006\u0010Y\u001a\u00020BJ\u000e\u0010Z\u001a\u00020!2\u0006\u0010:\u001a\u00020\u0007J\u000e\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001c0HH\u0002J7\u0010\\\u001a\u0002H]\"\u0004\b\u0000\u0010]2!\u0010^\u001a\u001d\u0012\u0013\u0012\u00110@¢\u0006\f\b`\u0012\b\ba\u0012\u0004\b\b(?\u0012\u0004\u0012\u0002H]0_H\u0086\b¢\u0006\u0002\u0010bJ\u0087\u0001\u0010c\u001a\u00020>2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u000e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$2\u0006\u0010*\u001a\u00020\u001c2\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2&\u0010,\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u0001`/2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0000¢\u0006\u0004\bd\u0010eJ\u001f\u0010f\u001a\u0004\u0018\u00010%2\u0006\u0010M\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020\u001cH\u0000¢\u0006\u0002\bhJ\u001d\u0010i\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0H2\u0006\u0010M\u001a\u00020\u001cH\u0000¢\u0006\u0002\bjJ\u0010\u0010k\u001a\u0004\u0018\u00010.2\u0006\u0010M\u001a\u00020\u001cJ\u0006\u0010l\u001a\u00020mJ\u0012\u0010n\u001a\u0004\u0018\u00010\u00072\u0006\u0010;\u001a\u00020\u001cH\u0002J\u0006\u0010o\u001a\u00020>J7\u0010p\u001a\u0002H]\"\u0004\b\u0000\u0010]2!\u0010^\u001a\u001d\u0012\u0013\u0012\u00110B¢\u0006\f\b`\u0012\b\ba\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H]0_H\u0086\b¢\u0006\u0002\u0010bJ \u0010q\u001a\u00020\u001c*\u00060rj\u0002`s2\u0006\u0010;\u001a\u00020\u001c2\u0006\u0010t\u001a\u00020\u001cH\u0002R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\"R\u000e\u0010#\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$2\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$@BX\u0086\u000e¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u001e\u0010*\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR:\u0010,\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020.\u0018\u0001`/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001f\"\u0004\b6\u00107R\u001e\u00108\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020!@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"¨\u0006u"}, d2 = {"Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "()V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "getAnchors$runtime_release", "()Ljava/util/ArrayList;", "setAnchors$runtime_release", "(Ljava/util/ArrayList;)V", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "getCalledByMap$runtime_release", "()Landroidx/collection/MutableIntObjectMap;", "setCalledByMap$runtime_release", "(Landroidx/collection/MutableIntObjectMap;)V", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "<set-?>", "", "groups", "getGroups", "()[I", "", "groupsSize", "getGroupsSize", "()I", "isEmpty", "", "()Z", "readers", "", "", "slots", "getSlots", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "slotsSize", "getSlotsSize", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "getSourceInformationMap$runtime_release", "()Ljava/util/HashMap;", "setSourceInformationMap$runtime_release", "(Ljava/util/HashMap;)V", "version", "getVersion$runtime_release", "setVersion$runtime_release", "(I)V", "writer", "getWriter$runtime_release", "anchor", "index", "anchorIndex", "close", "", "reader", "Landroidx/compose/runtime/SlotReader;", "close$runtime_release", "Landroidx/compose/runtime/SlotWriter;", "(Landroidx/compose/runtime/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "collectCalledByInformation", "collectSourceInformation", "containsMark", "dataIndexes", "", "find", "identityToFind", "findEffectiveRecomposeScope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "group", "groupContainsAnchor", "groupIndex", "groupSizes", "invalidateGroupsWithKey", "target", "invalidateGroupsWithKey$runtime_release", "iterator", "", "keys", "nodes", "openReader", "openWriter", "ownsAnchor", "parentIndexes", "read", "T", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "setTo", "setTo$runtime_release", "([II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "slot", "slotIndex", "slot$runtime_release", "slotsOf", "slotsOf$runtime_release", "sourceInformationOf", "toDebugString", "", "tryAnchor", "verifyWellFormed", "write", "emitGroup", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "level", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SlotTable implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    public static final int $stable = 8;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private ArrayList<Anchor> anchors = new ArrayList<>();

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    /* renamed from: getWriter$runtime_release, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    /* renamed from: getVersion$runtime_release, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final void setVersion$runtime_release(int i) {
        this.version = i;
    }

    public final ArrayList<Anchor> getAnchors$runtime_release() {
        return this.anchors;
    }

    public final void setAnchors$runtime_release(ArrayList<Anchor> arrayList) {
        this.anchors = arrayList;
    }

    public final HashMap<Anchor, GroupSourceInformation> getSourceInformationMap$runtime_release() {
        return this.sourceInformationMap;
    }

    public final void setSourceInformationMap$runtime_release(HashMap<Anchor, GroupSourceInformation> hashMap) {
        this.sourceInformationMap = hashMap;
    }

    public final MutableIntObjectMap<MutableIntSet> getCalledByMap$runtime_release() {
        return this.calledByMap;
    }

    public final void setCalledByMap$runtime_release(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap) {
        this.calledByMap = mutableIntObjectMap;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    public final <T> T read(Function1<? super SlotReader, ? extends T> block) {
        SlotReader reader = openReader();
        try {
            return block.invoke(reader);
        } finally {
            reader.close();
        }
    }

    public final <T> T write(Function1<? super SlotWriter, ? extends T> block) {
        SlotWriter writer = openWriter();
        boolean normalClose = false;
        try {
            normalClose = true;
            return block.invoke(writer);
        } finally {
            writer.close(normalClose);
        }
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when another writer is pending");
        }
        boolean value$iv2 = this.readers <= 0;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when a reader is pending");
        }
        this.writer = true;
        this.version++;
        return new SlotWriter(this);
    }

    public final Anchor anchor(int index) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to create an anchor location instead");
        }
        boolean value$iv2 = index >= 0 && index < this.groupsSize;
        if (!value$iv2) {
            PreconditionsKt.throwIllegalArgumentException("Parameter index is out of range");
        }
        ArrayList $this$getOrAdd$iv = this.anchors;
        int effectiveSize$iv = this.groupsSize;
        int location$iv = SlotTableKt.search($this$getOrAdd$iv, index, effectiveSize$iv);
        if (location$iv < 0) {
            Anchor anchor$iv = new Anchor(index);
            $this$getOrAdd$iv.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        return $this$getOrAdd$iv.get(location$iv);
    }

    private final Anchor tryAnchor(int index) {
        Anchor find;
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to crate an anchor for location instead");
        }
        if (!(index >= 0 && index < this.groupsSize)) {
            return null;
        }
        find = SlotTableKt.find(this.anchors, index, this.groupsSize);
        return find;
    }

    public final int anchorIndex(Anchor anchor) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Use active SlotWriter to determine anchor location instead");
        }
        boolean value$iv2 = anchor.getValid();
        if (!value$iv2) {
            PreconditionsKt.throwIllegalArgumentException("Anchor refers to a group that was removed");
        }
        return anchor.getLocation();
    }

    public final boolean ownsAnchor(Anchor anchor) {
        if (!anchor.getValid()) {
            return false;
        }
        int it = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize);
        return ((it < 0 || !Intrinsics.areEqual(this.anchors.get(it), anchor)) ? 0 : 1) != 0;
    }

    public final boolean groupContainsAnchor(int groupIndex, Anchor anchor) {
        int groupSize;
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Writer is active");
        }
        boolean value$iv2 = groupIndex >= 0 && groupIndex < this.groupsSize;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Invalid group index");
        }
        boolean value$iv3 = ownsAnchor(anchor);
        if (value$iv3) {
            groupSize = SlotTableKt.groupSize(this.groups, groupIndex);
            int i = groupSize + groupIndex;
            int location = anchor.getLocation();
            if (groupIndex <= location && location < i) {
                return true;
            }
        }
        return false;
    }

    public final void close$runtime_release(SlotReader reader, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        boolean value$iv = reader.getTable() == this && this.readers > 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        this.readers--;
        if (sourceInformationMap == null) {
            return;
        }
        synchronized (this) {
            HashMap thisMap = this.sourceInformationMap;
            if (thisMap != null) {
                thisMap.putAll(sourceInformationMap);
            } else {
                this.sourceInformationMap = sourceInformationMap;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void close$runtime_release(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        boolean value$iv = writer.getTable() == this && this.writer;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Unexpected writer close()");
        }
        this.writer = false;
        setTo$runtime_release(groups, groupsSize, slots, slotsSize, anchors, sourceInformationMap, calledByMap);
    }

    public final void setTo$runtime_release(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
        this.sourceInformationMap = sourceInformationMap;
        this.calledByMap = calledByMap;
    }

    public final List<RecomposeScopeImpl> invalidateGroupsWithKey$runtime_release(int target) {
        MutableIntSet set;
        MutableIntSet it;
        List anchors = new ArrayList();
        List scopes = new ArrayList();
        Ref.BooleanRef allScopesFound = new Ref.BooleanRef();
        allScopesFound.element = true;
        MutableIntSet set2 = new MutableIntSet(0, 1, null);
        set2.add(target);
        set2.add(-3);
        MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
        if (mutableIntObjectMap != null && (it = mutableIntObjectMap.get(target)) != null) {
            set2.addAll(it);
        }
        SlotReader reader$iv = openReader();
        try {
            invalidateGroupsWithKey$lambda$20$scanGroup(reader$iv, set2, anchors, allScopesFound, this, scopes);
            Unit unit = Unit.INSTANCE;
            reader$iv.close();
            SlotTable this_$iv = this;
            SlotWriter writer$iv = this_$iv.openWriter();
            try {
                writer$iv.startGroup();
                int index$iv = 0;
                int size = anchors.size();
                while (index$iv < size) {
                    Object item$iv = anchors.get(index$iv);
                    Anchor anchor = (Anchor) item$iv;
                    SlotTable this_$iv2 = this_$iv;
                    try {
                        set = set2;
                    } catch (Throwable th) {
                        th = th;
                        writer$iv.close(false);
                        throw th;
                    }
                    try {
                        if (anchor.toIndexFor(writer$iv) >= writer$iv.getCurrentGroup()) {
                            writer$iv.seek(anchor);
                            writer$iv.bashCurrentGroup();
                        }
                        index$iv++;
                        this_$iv = this_$iv2;
                        set2 = set;
                    } catch (Throwable th2) {
                        th = th2;
                        writer$iv.close(false);
                        throw th;
                    }
                }
                writer$iv.skipToGroupEnd();
                writer$iv.endGroup();
                writer$iv.close(true);
                if (allScopesFound.element) {
                    return scopes;
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            reader$iv.close();
            throw th4;
        }
    }

    private static final void invalidateGroupsWithKey$lambda$20$scanGroup(SlotReader $reader, MutableIntSet set, List<Anchor> list, Ref.BooleanRef allScopesFound, SlotTable this$0, List<RecomposeScopeImpl> list2) {
        RecomposeScopeImpl parentScope;
        int key = $reader.getGroupKey();
        if (set.contains(key)) {
            if (key != -3) {
                list.add(SlotReader.anchor$default($reader, 0, 1, null));
            }
            if (allScopesFound.element) {
                RecomposeScopeImpl nearestScope = this$0.findEffectiveRecomposeScope($reader.getCurrent());
                if (nearestScope != null) {
                    list2.add(nearestScope);
                    Anchor anchor = nearestScope.getAnchor();
                    if ((anchor != null && anchor.getLocation() == $reader.getCurrent()) && (parentScope = this$0.findEffectiveRecomposeScope($reader.getParent())) != null) {
                        list2.add(parentScope);
                    }
                } else {
                    allScopesFound.element = false;
                    list2.clear();
                }
            }
            $reader.skipGroup();
            return;
        }
        $reader.startGroup();
        while (!$reader.isGroupEnd()) {
            invalidateGroupsWithKey$lambda$20$scanGroup($reader, set, list, allScopesFound, this$0, list2);
        }
        $reader.endGroup();
    }

    public final boolean containsMark() {
        boolean containsMark;
        if (this.groupsSize <= 0) {
            return false;
        }
        containsMark = SlotTableKt.containsMark(this.groups, 0);
        return containsMark;
    }

    public final GroupSourceInformation sourceInformationOf(int group) {
        Anchor anchor;
        HashMap map = this.sourceInformationMap;
        if (map == null || (anchor = tryAnchor(group)) == null) {
            return null;
        }
        return map.get(anchor);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        int current = group;
        while (current > 0) {
            Iterator<Object> it = new DataIterator(this, current).iterator();
            while (it.hasNext()) {
                Object data = it.next();
                if (data instanceof RecomposeScopeImpl) {
                    if (((RecomposeScopeImpl) data).getUsed() && current != group) {
                        return (RecomposeScopeImpl) data;
                    }
                    ((RecomposeScopeImpl) data).setForcedRecompose(true);
                }
            }
            current = SlotTableKt.parentAnchor(this.groups, current);
        }
        return null;
    }

    public final void verifyWellFormed() {
        int groupSize;
        Ref.IntRef current = new Ref.IntRef();
        if (this.groupsSize > 0) {
            while (current.element < this.groupsSize) {
                int i = current.element;
                groupSize = SlotTableKt.groupSize(this.groups, current.element);
                verifyWellFormed$validateGroup(current, this, -1, i + groupSize);
            }
            boolean value$iv = current.element == this.groupsSize;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Incomplete group at root " + current.element + " expected to be " + this.groupsSize);
            }
        }
        int length = this.slots.length;
        for (int index = this.slotsSize; index < length; index++) {
            boolean value$iv2 = this.slots[index] == null;
            if (!value$iv2) {
                PreconditionsKt.throwIllegalStateException("Non null value in the slot gap at index " + index);
            }
        }
        int lastLocation = -1;
        List $this$fastForEach$iv = this.anchors;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            int location = ((Anchor) item$iv).toIndexFor(this);
            boolean value$iv3 = location >= 0 && location <= this.groupsSize;
            if (!value$iv3) {
                PreconditionsKt.throwIllegalArgumentException("Invalid anchor, location out of bound");
            }
            boolean value$iv4 = lastLocation < location;
            if (!value$iv4) {
                PreconditionsKt.throwIllegalArgumentException("Anchor is out of order");
            }
            lastLocation = location;
        }
        HashMap sourceInformationMap = this.sourceInformationMap;
        if (sourceInformationMap != null) {
            for (Map.Entry<Anchor, GroupSourceInformation> entry : sourceInformationMap.entrySet()) {
                Anchor anchor = entry.getKey();
                GroupSourceInformation sourceGroup = entry.getValue();
                boolean value$iv5 = anchor.getValid();
                if (!value$iv5) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                boolean value$iv6 = ownsAnchor(anchor);
                if (!value$iv6) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
                verifyWellFormed$verifySourceGroup(this, sourceGroup);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0193 A[LOOP:0: B:54:0x018f->B:56:0x0193, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0265 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x018b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int verifyWellFormed$validateGroup(kotlin.jvm.internal.Ref.IntRef r21, androidx.compose.runtime.SlotTable r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotTable.verifyWellFormed$validateGroup(kotlin.jvm.internal.Ref$IntRef, androidx.compose.runtime.SlotTable, int, int):int");
    }

    private static final void verifyWellFormed$verifySourceGroup(SlotTable this$0, GroupSourceInformation group) {
        List groups = group.getGroups();
        if (groups == null) {
            return;
        }
        List $this$fastForEach$iv = groups;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            if (item$iv instanceof Anchor) {
                boolean value$iv = ((Anchor) item$iv).getValid();
                if (!value$iv) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                boolean value$iv2 = this$0.ownsAnchor((Anchor) item$iv);
                if (!value$iv2) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
            } else if (item$iv instanceof GroupSourceInformation) {
                verifyWellFormed$verifySourceGroup(this$0, (GroupSourceInformation) item$iv);
            }
        }
    }

    public final void collectCalledByInformation() {
        this.calledByMap = new MutableIntObjectMap<>(0, 1, null);
    }

    public final void collectSourceInformation() {
        this.sourceInformationMap = new HashMap<>();
    }

    public final String toDebugString() {
        if (this.writer) {
            return super.toString();
        }
        StringBuilder $this$toDebugString_u24lambda_u2447 = new StringBuilder();
        $this$toDebugString_u24lambda_u2447.append(super.toString());
        $this$toDebugString_u24lambda_u2447.append('\n');
        int groupsSize = this.groupsSize;
        if (groupsSize > 0) {
            int current = 0;
            while (current < groupsSize) {
                current += emitGroup($this$toDebugString_u24lambda_u2447, current, 0);
            }
        } else {
            $this$toDebugString_u24lambda_u2447.append("<EMPTY>");
        }
        String sb = $this$toDebugString_u24lambda_u2447.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    private final int emitGroup(StringBuilder $this$emitGroup, int index, int level) {
        int key;
        int groupSize;
        int nodeCount;
        boolean hasMark;
        boolean containsMark;
        boolean hasObjectKey;
        boolean isNode;
        boolean hasAux;
        int slotStart;
        String summarize;
        int auxIndex;
        String summarize2;
        int nodeIndex;
        String summarize3;
        int objectKeyIndex;
        String summarize4;
        String it;
        boolean z = false;
        for (int i = 0; i < level; i++) {
            $this$emitGroup.append(' ');
        }
        $this$emitGroup.append("Group(");
        $this$emitGroup.append(index);
        $this$emitGroup.append(")");
        GroupSourceInformation sourceInformationOf = sourceInformationOf(index);
        if (sourceInformationOf != null && (it = sourceInformationOf.getSourceInformation()) != null && (StringsKt.startsWith$default(it, "C(", false, 2, (Object) null) || StringsKt.startsWith$default(it, "CC(", false, 2, (Object) null))) {
            int start = StringsKt.indexOf$default((CharSequence) it, "(", 0, false, 6, (Object) null) + 1;
            int endParen = StringsKt.indexOf$default((CharSequence) it, ')', 0, false, 6, (Object) null);
            $this$emitGroup.append(" ");
            String substring = it.substring(start, endParen);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            $this$emitGroup.append(substring);
            $this$emitGroup.append("()");
        }
        $this$emitGroup.append(" key=");
        key = SlotTableKt.key(this.groups, index);
        $this$emitGroup.append(key);
        groupSize = SlotTableKt.groupSize(this.groups, index);
        $this$emitGroup.append(", nodes=");
        nodeCount = SlotTableKt.nodeCount(this.groups, index);
        $this$emitGroup.append(nodeCount);
        $this$emitGroup.append(", size=");
        $this$emitGroup.append(groupSize);
        hasMark = SlotTableKt.hasMark(this.groups, index);
        if (hasMark) {
            $this$emitGroup.append(", mark");
        }
        containsMark = SlotTableKt.containsMark(this.groups, index);
        if (containsMark) {
            $this$emitGroup.append(", contains mark");
        }
        int dataStart = emitGroup$dataIndex(this, index);
        int dataEnd = emitGroup$dataIndex(this, index + 1);
        if (dataStart >= 0 && dataStart <= dataEnd) {
            z = true;
        }
        if (z && dataEnd <= this.slotsSize) {
            hasObjectKey = SlotTableKt.hasObjectKey(this.groups, index);
            if (hasObjectKey) {
                StringBuilder append = new StringBuilder().append(" objectKey=");
                Object[] objArr = this.slots;
                objectKeyIndex = SlotTableKt.objectKeyIndex(this.groups, index);
                summarize4 = SlotTableKt.summarize(String.valueOf(objArr[objectKeyIndex]), 10);
                $this$emitGroup.append(append.append(summarize4).toString());
            }
            isNode = SlotTableKt.isNode(this.groups, index);
            if (isNode) {
                StringBuilder append2 = new StringBuilder().append(" node=");
                Object[] objArr2 = this.slots;
                nodeIndex = SlotTableKt.nodeIndex(this.groups, index);
                summarize3 = SlotTableKt.summarize(String.valueOf(objArr2[nodeIndex]), 10);
                $this$emitGroup.append(append2.append(summarize3).toString());
            }
            hasAux = SlotTableKt.hasAux(this.groups, index);
            if (hasAux) {
                StringBuilder append3 = new StringBuilder().append(" aux=");
                Object[] objArr3 = this.slots;
                auxIndex = SlotTableKt.auxIndex(this.groups, index);
                summarize2 = SlotTableKt.summarize(String.valueOf(objArr3[auxIndex]), 10);
                $this$emitGroup.append(append3.append(summarize2).toString());
            }
            slotStart = SlotTableKt.slotAnchor(this.groups, index);
            if (slotStart < dataEnd) {
                $this$emitGroup.append(", slots=[");
                $this$emitGroup.append(slotStart);
                $this$emitGroup.append(": ");
                for (int dataIndex = slotStart; dataIndex < dataEnd; dataIndex++) {
                    if (dataIndex != slotStart) {
                        $this$emitGroup.append(", ");
                    }
                    summarize = SlotTableKt.summarize(String.valueOf(this.slots[dataIndex]), 10);
                    $this$emitGroup.append(summarize);
                }
                $this$emitGroup.append("]");
            }
        } else {
            $this$emitGroup.append(", *invalid data offsets " + dataStart + '-' + dataEnd + '*');
        }
        $this$emitGroup.append('\n');
        int current = index + 1;
        int end = index + groupSize;
        while (current < end) {
            current += emitGroup($this$emitGroup, current, level + 1);
        }
        return groupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable this$0, int index) {
        int dataAnchor;
        if (index >= this$0.groupsSize) {
            return this$0.slotsSize;
        }
        dataAnchor = SlotTableKt.dataAnchor(this$0.groups, index);
        return dataAnchor;
    }

    private final List<Integer> keys() {
        List<Integer> keys;
        keys = SlotTableKt.keys(this.groups, this.groupsSize * 5);
        return keys;
    }

    private final List<Integer> nodes() {
        List<Integer> nodeCounts;
        nodeCounts = SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
        return nodeCounts;
    }

    private final List<Integer> parentIndexes() {
        List<Integer> parentAnchors;
        parentAnchors = SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
        return parentAnchors;
    }

    private final List<Integer> dataIndexes() {
        List<Integer> dataAnchors;
        dataAnchors = SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
        return dataAnchors;
    }

    private final List<Integer> groupSizes() {
        List<Integer> groupSizes;
        groupSizes = SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
        return groupSizes;
    }

    public final List<Object> slotsOf$runtime_release(int group) {
        int start;
        start = SlotTableKt.dataAnchor(this.groups, group);
        int end = group + 1 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, group + 1) : this.slots.length;
        return ArraysKt.toList(this.slots).subList(start, end);
    }

    public final Object slot$runtime_release(int group, int slotIndex) {
        int start;
        start = SlotTableKt.slotAnchor(this.groups, group);
        int end = group + 1 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, group + 1) : this.slots.length;
        int len = end - start;
        boolean z = false;
        if (slotIndex >= 0 && slotIndex < len) {
            z = true;
        }
        return z ? this.slots[start + slotIndex] : Composer.INSTANCE.getEmpty();
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        return new SlotTableGroup(this, 0, 0, 4, null).find(identityToFind);
    }
}
