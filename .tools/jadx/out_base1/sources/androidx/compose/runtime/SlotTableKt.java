package androidx.compose.runtime;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0010\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u001b\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u001e\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u001f\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010!\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\"\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0002\u001a7\u0010&\u001a\u00020\u0001\"\u0004\b\u0000\u0010'*\u0012\u0012\u0004\u0012\u0002H'0(j\b\u0012\u0004\u0012\u0002H'`)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020 0+H\u0082\b\u001a>\u0010,\u001a\u0004\u0018\u0001H'\"\u0004\b\u0000\u0010'*\u0012\u0012\u0004\u0012\u0002H'0(j\b\u0012\u0004\u0012\u0002H'`)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020 0+H\u0082\b¢\u0006\u0002\u0010-\u001a.\u0010.\u001a\u0004\u0018\u00010/*\u0012\u0012\u0004\u0012\u00020/0(j\b\u0012\u0004\u0012\u00020/`)2\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0002\u001a;\u00102\u001a\u00020/*\u0012\u0012\u0004\u0012\u00020/0(j\b\u0012\u0004\u0012\u00020/`)2\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\f\u00103\u001a\b\u0012\u0004\u0012\u00020/04H\u0082\b\u001a\u0014\u00105\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u00106\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u00107\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0002\u001a\u0014\u00108\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u00109\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010:\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001aD\u0010;\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0002\u001a\u0014\u0010<\u001a\u00020 *\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u001a\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0002\u001a,\u0010A\u001a\u00020\u0001*\u0012\u0012\u0004\u0012\u00020/0(j\b\u0012\u0004\u0012\u00020/`)2\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0002\u001a\u0014\u0010B\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0002\u001a\u0014\u0010D\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010E\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010?\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\b\b\u0002\u0010%\u001a\u00020\u0001H\u0002\u001a,\u0010G\u001a\u00020\u0001*\u0012\u0012\u0004\u0012\u00020/0(j\b\u0012\u0004\u0012\u00020/`)2\u0006\u0010H\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0002\u001a \u0010I\u001a\b\u0012\u0004\u0012\u00020\u00010$*\u00020\u001c2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00010KH\u0002\u001a\u0014\u0010L\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u0014\u0010M\u001a\u00020N*\u00020N2\u0006\u0010O\u001a\u00020\u0001H\u0002\u001a\u001c\u0010P\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020 H\u0002\u001a\u001c\u0010Q\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010R\u001a\u00020\u0001H\u0002\u001a\u001c\u0010S\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002\u001a\u001c\u0010T\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a\u001c\u0010U\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020 H\u0002\u001a\u001c\u0010V\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a\u001c\u0010W\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Aux_Mask", "", "Aux_Shift", "ContainsMark_Mask", "DataAnchor_Offset", "GroupInfo_Offset", "Group_Fields_Size", "Key_Offset", "LIVE_EDIT_INVALID_KEY", "Mark_Mask", "MinGroupGrowthSize", "MinSlotsGrowthSize", "NodeBit_Mask", "NodeCount_Mask", "ObjectKey_Mask", "ObjectKey_Shift", "ParentAnchor_Offset", "Size_Offset", "Slots_Shift", "parentAnchorPivot", "countOneBits", "value", "add", "", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "key", "addAux", "", "address", "auxIndex", "containsAnyMark", "", "containsMark", "dataAnchor", "dataAnchors", "", "len", "fastIndexOf", "T", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "predicate", "Lkotlin/Function1;", "fastLastOrNull", "(Ljava/util/ArrayList;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "find", "Landroidx/compose/runtime/Anchor;", "index", "effectiveSize", "getOrAdd", "block", "Lkotlin/Function0;", "groupInfo", "groupSize", "groupSizes", "hasAux", "hasMark", "hasObjectKey", "initGroup", "isNode", "hasDataKey", "hasData", "parentAnchor", "keys", "locationOf", "nodeCount", "nodeCounts", "nodeIndex", "objectKeyIndex", "parentAnchors", "search", "location", "slice", "indices", "", "slotAnchor", "summarize", "", "size", "updateContainsMark", "updateDataAnchor", "anchor", "updateGroupKey", "updateGroupSize", "updateMark", "updateNodeCount", "updateParentAnchor", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SlotTableKt {
    private static final int Aux_Mask = 268435456;
    private static final int Aux_Shift = 28;
    private static final int ContainsMark_Mask = 67108864;
    private static final int DataAnchor_Offset = 4;
    private static final int GroupInfo_Offset = 1;
    private static final int Group_Fields_Size = 5;
    private static final int Key_Offset = 0;
    private static final int LIVE_EDIT_INVALID_KEY = -3;
    private static final int Mark_Mask = 134217728;
    private static final int MinGroupGrowthSize = 32;
    private static final int MinSlotsGrowthSize = 32;
    private static final int NodeBit_Mask = 1073741824;
    private static final int NodeCount_Mask = 67108863;
    private static final int ObjectKey_Mask = 536870912;
    private static final int ObjectKey_Shift = 29;
    private static final int ParentAnchor_Offset = 2;
    private static final int Size_Offset = 3;
    private static final int Slots_Shift = 28;
    private static final int parentAnchorPivot = -2;

    private static final <T> T fastLastOrNull(ArrayList<T> arrayList, Function1<? super T, Boolean> function1) {
        for (int index = arrayList.size() - 1; index >= 0; index--) {
            T t = arrayList.get(index);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    private static final <T> int fastIndexOf(ArrayList<T> arrayList, Function1<? super T, Boolean> function1) {
        int size = arrayList.size();
        for (int index = 0; index < size; index++) {
            Object value = arrayList.get(index);
            if (function1.invoke(value).booleanValue()) {
                return index;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String summarize(String $this$summarize, int size) {
        String it = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default($this$summarize, "androidx.", "a.", false, 4, (Object) null), "compose.", "c.", false, 4, (Object) null), "runtime.", "r.", false, 4, (Object) null), "internal.", "ι.", false, 4, (Object) null), "ui.", "u.", false, 4, (Object) null), "Modifier", "μ", false, 4, (Object) null), "material.", "m.", false, 4, (Object) null), "Function", "λ", false, 4, (Object) null), "OpaqueKey", "κ", false, 4, (Object) null), "MutableState", "σ", false, 4, (Object) null);
        String substring = it.substring(0, Math.min(size, it.length()));
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupInfo(int[] $this$groupInfo, int address) {
        return $this$groupInfo[(address * 5) + 1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNode(int[] $this$isNode, int address) {
        return ($this$isNode[(address * 5) + 1] & 1073741824) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeIndex(int[] $this$nodeIndex, int address) {
        return $this$nodeIndex[(address * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasObjectKey(int[] $this$hasObjectKey, int address) {
        return ($this$hasObjectKey[(address * 5) + 1] & ObjectKey_Mask) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int objectKeyIndex(int[] $this$objectKeyIndex, int address) {
        int slot = address * 5;
        return $this$objectKeyIndex[slot + 4] + countOneBits($this$objectKeyIndex[slot + 1] >> 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasAux(int[] $this$hasAux, int address) {
        return ($this$hasAux[(address * 5) + 1] & Aux_Mask) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addAux(int[] $this$addAux, int address) {
        int arrayIndex = (address * 5) + 1;
        $this$addAux[arrayIndex] = $this$addAux[arrayIndex] | Aux_Mask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasMark(int[] $this$hasMark, int address) {
        return ($this$hasMark[(address * 5) + 1] & Mark_Mask) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMark(int[] $this$updateMark, int address, boolean value) {
        int arrayIndex = (address * 5) + 1;
        if (value) {
            $this$updateMark[arrayIndex] = $this$updateMark[arrayIndex] | Mark_Mask;
        } else {
            $this$updateMark[arrayIndex] = $this$updateMark[arrayIndex] & (-134217729);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsMark(int[] $this$containsMark, int address) {
        return ($this$containsMark[(address * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateContainsMark(int[] $this$updateContainsMark, int address, boolean value) {
        int arrayIndex = (address * 5) + 1;
        if (value) {
            $this$updateContainsMark[arrayIndex] = $this$updateContainsMark[arrayIndex] | 67108864;
        } else {
            $this$updateContainsMark[arrayIndex] = $this$updateContainsMark[arrayIndex] & (-67108865);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsAnyMark(int[] $this$containsAnyMark, int address) {
        return ($this$containsAnyMark[(address * 5) + 1] & 201326592) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int auxIndex(int[] $this$auxIndex, int address) {
        int slot = address * 5;
        return slot >= $this$auxIndex.length ? $this$auxIndex.length : $this$auxIndex[slot + 4] + countOneBits($this$auxIndex[slot + 1] >> 29);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int slotAnchor(int[] $this$slotAnchor, int address) {
        int slot = address * 5;
        return $this$slotAnchor[slot + 4] + countOneBits($this$slotAnchor[slot + 1] >> 28);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int countOneBits(int value) {
        switch (value) {
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int key(int[] $this$key, int address) {
        return $this$key[address * 5];
    }

    static /* synthetic */ List keys$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return keys(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> keys(int[] $this$keys, int len) {
        return slice($this$keys, RangesKt.step(RangesKt.until(0, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeCount(int[] $this$nodeCount, int address) {
        return $this$nodeCount[(address * 5) + 1] & NodeCount_Mask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateNodeCount(int[] $this$updateNodeCount, int address, int value) {
        ComposerKt.runtimeCheck(value >= 0 && value < NodeCount_Mask);
        $this$updateNodeCount[(address * 5) + 1] = ($this$updateNodeCount[(address * 5) + 1] & (-67108864)) | value;
    }

    static /* synthetic */ List nodeCounts$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return nodeCounts(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> nodeCounts(int[] $this$nodeCounts, int len) {
        List $this$fastMap$iv = slice($this$nodeCounts, RangesKt.step(RangesKt.until(1, len), 5));
        List target$iv = new ArrayList($this$fastMap$iv.size());
        int size = $this$fastMap$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
            int it = ((Number) item$iv$iv).intValue();
            target$iv.add(Integer.valueOf(it & NodeCount_Mask));
        }
        return target$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int parentAnchor(int[] $this$parentAnchor, int address) {
        return $this$parentAnchor[(address * 5) + 2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateParentAnchor(int[] $this$updateParentAnchor, int address, int value) {
        $this$updateParentAnchor[(address * 5) + 2] = value;
    }

    static /* synthetic */ List parentAnchors$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return parentAnchors(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> parentAnchors(int[] $this$parentAnchors, int len) {
        return slice($this$parentAnchors, RangesKt.step(RangesKt.until(2, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupSize(int[] $this$groupSize, int address) {
        return $this$groupSize[(address * 5) + 3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupSize(int[] $this$updateGroupSize, int address, int value) {
        ComposerKt.runtimeCheck(value >= 0);
        $this$updateGroupSize[(address * 5) + 3] = value;
    }

    private static final List<Integer> slice(int[] $this$slice, Iterable<Integer> iterable) {
        List list = new ArrayList();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext()) {
            int index = it.next().intValue();
            list.add(Integer.valueOf($this$slice[index]));
        }
        return list;
    }

    static /* synthetic */ List groupSizes$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return groupSizes(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> groupSizes(int[] $this$groupSizes, int len) {
        return slice($this$groupSizes, RangesKt.step(RangesKt.until(3, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int dataAnchor(int[] $this$dataAnchor, int address) {
        return $this$dataAnchor[(address * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDataAnchor(int[] $this$updateDataAnchor, int address, int anchor) {
        $this$updateDataAnchor[(address * 5) + 4] = anchor;
    }

    static /* synthetic */ List dataAnchors$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = iArr.length;
        }
        return dataAnchors(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> dataAnchors(int[] $this$dataAnchors, int len) {
        return slice($this$dataAnchors, RangesKt.step(RangesKt.until(4, len), 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGroup(int[] $this$initGroup, int address, int key, boolean isNode, boolean hasDataKey, boolean hasData, int parentAnchor, int dataAnchor) {
        int nodeBit = isNode ? 1073741824 : 0;
        int dataKeyBit = hasDataKey ? ObjectKey_Mask : 0;
        int dataBit = hasData ? Aux_Mask : 0;
        int arrayIndex = address * 5;
        $this$initGroup[arrayIndex + 0] = key;
        $this$initGroup[arrayIndex + 1] = nodeBit | dataKeyBit | dataBit;
        $this$initGroup[arrayIndex + 2] = parentAnchor;
        $this$initGroup[arrayIndex + 3] = 0;
        $this$initGroup[arrayIndex + 4] = dataAnchor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupKey(int[] $this$updateGroupKey, int address, int key) {
        int arrayIndex = address * 5;
        $this$updateGroupKey[arrayIndex + 0] = key;
    }

    private static final Anchor getOrAdd(ArrayList<Anchor> arrayList, int index, int effectiveSize, Function0<Anchor> function0) {
        int location = search(arrayList, index, effectiveSize);
        if (location < 0) {
            Anchor anchor = function0.invoke();
            arrayList.add(-(location + 1), anchor);
            return anchor;
        }
        return arrayList.get(location);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Anchor find(ArrayList<Anchor> arrayList, int index, int effectiveSize) {
        int location = search(arrayList, index, effectiveSize);
        if (location >= 0) {
            return arrayList.get(location);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int search(ArrayList<Anchor> arrayList, int location, int effectiveSize) {
        int low = 0;
        int high = arrayList.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int it = arrayList.get(mid).getLocation();
            if (it < 0) {
                it = effectiveSize + it;
            }
            int cmp = Intrinsics.compare(it, location);
            if (cmp < 0) {
                low = mid + 1;
            } else {
                if (cmp <= 0) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int locationOf(ArrayList<Anchor> arrayList, int index, int effectiveSize) {
        int it = search(arrayList, index, effectiveSize);
        return it >= 0 ? it : -(it + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void add(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap, int key, int value) {
        MutableIntSet it = mutableIntObjectMap.get(key);
        if (it == null) {
            it = new MutableIntSet(0, 1, null);
            mutableIntObjectMap.set(key, it);
        }
        it.add(value);
    }
}
