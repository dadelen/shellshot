package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composer.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0000\u001a\u0010\u0010<\u001a\u00020=2\u0006\u0010:\u001a\u00020;H\u0000\u001a(\u0010>\u001a\u0004\u0018\u00010\u00042\b\u0010?\u001a\u0004\u0018\u00010\u00042\b\u0010@\u001a\u0004\u0018\u00010\u00042\b\u0010A\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\b\u0010B\u001a\u00020/H\u0007\u001a1\u0010C\u001a\u000e\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u0002HF0D\"\u0004\b\u0000\u0010E\"\b\b\u0001\u0010F*\u00020\u00042\u0006\u0010G\u001a\u00020\nH\u0002¢\u0006\u0002\u0010H\u001a\u0010\u0010I\u001a\u0002092\u0006\u0010?\u001a\u00020/H\u0000\u001a*\u0010I\u001a\u0002092\u0006\u0010?\u001a\u00020/2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020;0KH\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a\u0018\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020N2\u0006\u0010L\u001a\u00020;H\u0007\u001a\u0010\u0010O\u001a\u0002092\u0006\u0010M\u001a\u00020NH\u0007\u001a \u0010P\u001a\u0002092\u0006\u0010M\u001a\u00020N2\u0006\u0010Q\u001a\u00020\n2\u0006\u0010L\u001a\u00020;H\u0007\u001a\b\u0010R\u001a\u000209H\u0007\u001a(\u0010S\u001a\u0002092\u0006\u0010Q\u001a\u00020\n2\u0006\u0010T\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\u0006\u0010V\u001a\u00020;H\u0007\u001a\u0018\u0010S\u001a\u0002092\u0006\u0010Q\u001a\u00020\n2\u0006\u0010V\u001a\u00020;H\u0007\u001a\f\u0010W\u001a\u00020/*\u00020\nH\u0002\u001a\f\u0010X\u001a\u00020\n*\u00020/H\u0002\u001a3\u0010Y\u001a\u0002HZ\"\u0004\b\u0000\u0010Z*\u00020N2\u0006\u0010[\u001a\u00020/2\u0011\u0010\\\u001a\r\u0012\u0004\u0012\u0002HZ0K¢\u0006\u0002\b]H\u0087\b¢\u0006\u0002\u0010^\u001a\u001c\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040`*\u00020a2\u0006\u0010b\u001a\u00020cH\u0002\u001a\u0014\u0010d\u001a\u000209*\u0002022\u0006\u0010e\u001a\u00020fH\u0000\u001a\u001c\u0010g\u001a\u00020\n*\u0002002\u0006\u0010h\u001a\u00020\n2\u0006\u0010i\u001a\u00020\nH\u0002\u001a(\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00020k*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010l\u001a\u00020\n2\u0006\u0010m\u001a\u00020\nH\u0002\u001a\u001a\u0010n\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010o\u001a\u00020\nH\u0002\u001a\u001a\u0010p\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010o\u001a\u00020\nH\u0002\u001a$\u0010q\u001a\u0004\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010l\u001a\u00020\n2\u0006\u0010m\u001a\u00020\nH\u0002\u001a,\u0010r\u001a\u000209*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010o\u001a\u00020\n2\u0006\u0010s\u001a\u00020t2\b\u0010u\u001a\u0004\u0018\u00010\u0004H\u0002\u001a$\u0010v\u001a\u00020\n*\u0002002\u0006\u0010w\u001a\u00020\n2\u0006\u0010x\u001a\u00020\n2\u0006\u0010y\u001a\u00020\nH\u0002\u001a\u0014\u0010z\u001a\u000209*\u0002022\u0006\u0010e\u001a\u00020fH\u0000\u001a&\u0010{\u001a\u000209*\u0002022\u0006\u0010|\u001a\u00020\n2\u0006\u0010h\u001a\u00020\n2\b\u0010}\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u001c\u0010~\u001a\u0004\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010o\u001a\u00020\nH\u0002\u001a\"\u0010\u007f\u001a\u000209*\b\u0012\u0004\u0012\u00020\u00020k2\u0006\u0010l\u001a\u00020\n2\u0006\u0010m\u001a\u00020\nH\u0002\u001a<\u0010\u0080\u0001\u001a\u000209\"\u0005\b\u0000\u0010\u0081\u0001*\u0002022\b\u0010b\u001a\u0004\u0018\u00010c2\u001b\u0010\u0082\u0001\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0005\u0012\u0003H\u0081\u00010\u0083\u0001H\u0080\b\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0016\u0010\t\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0006\"\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0006\"\u000e\u0010\u000f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u001c\u0010\u0011\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0006\u001a\u0004\b\u0013\u0010\b\"\u0016\u0010\u0014\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0006\"\u000e\u0010\u0016\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u001c\u0010\u0017\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0006\u001a\u0004\b\u0019\u0010\b\"\u0016\u0010\u001a\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0006\"\u001c\u0010\u001c\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\b\"\u0016\u0010\u001f\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u0006\"\u001c\u0010!\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0006\u001a\u0004\b#\u0010\b\"\u0016\u0010$\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b%\u0010\u0006\"\u001c\u0010&\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\b\"\u0016\u0010)\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b*\u0010\u0006\"\u0016\u0010+\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b,\u0010\u0006\"\u000e\u0010-\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010.\u001a\u00020/*\u0002008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u00101\"\u0018\u0010.\u001a\u00020/*\u0002028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u00103\"\u0018\u00104\u001a\u00020\u0004*\u0002058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00107¨\u0006\u0084\u0001"}, d2 = {"InvalidationLocationAscending", "Ljava/util/Comparator;", "Landroidx/compose/runtime/Invalidation;", "compositionLocalMap", "", "getCompositionLocalMap$annotations", "()V", "getCompositionLocalMap", "()Ljava/lang/Object;", "compositionLocalMapKey", "", "getCompositionLocalMapKey$annotations", "compositionTracer", "Landroidx/compose/runtime/CompositionTracer;", "getCompositionTracer$annotations", "defaultsKey", "invalidGroupLocation", "invocation", "getInvocation$annotations", "getInvocation", "invocationKey", "getInvocationKey$annotations", "nodeKey", "provider", "getProvider$annotations", "getProvider", "providerKey", "getProviderKey$annotations", "providerMaps", "getProviderMaps$annotations", "getProviderMaps", "providerMapsKey", "getProviderMapsKey$annotations", "providerValues", "getProviderValues$annotations", "getProviderValues", "providerValuesKey", "getProviderValuesKey$annotations", "reference", "getReference$annotations", "getReference", "referenceKey", "getReferenceKey$annotations", "reuseKey", "getReuseKey$annotations", "rootKey", "isAfterFirstChild", "", "Landroidx/compose/runtime/SlotReader;", "(Landroidx/compose/runtime/SlotReader;)Z", "Landroidx/compose/runtime/SlotWriter;", "(Landroidx/compose/runtime/SlotWriter;)Z", "joinedKey", "Landroidx/compose/runtime/KeyInfo;", "getJoinedKey", "(Landroidx/compose/runtime/KeyInfo;)Ljava/lang/Object;", "composeImmediateRuntimeError", "", "message", "", "composeRuntimeError", "", "getKey", "value", "left", "right", "isTraceInProgress", "multiMap", "Landroidx/compose/runtime/MutableScatterMultiMap;", "K", "V", "initialCapacity", "(I)Landroidx/collection/MutableScatterMap;", "runtimeCheck", "lazyMessage", "Lkotlin/Function0;", "sourceInformation", "composer", "Landroidx/compose/runtime/Composer;", "sourceInformationMarkerEnd", "sourceInformationMarkerStart", "key", "traceEventEnd", "traceEventStart", "dirty1", "dirty2", "info", "asBool", "asInt", "cache", "T", "invalid", "block", "Landroidx/compose/runtime/DisallowComposableCalls;", "(Landroidx/compose/runtime/Composer;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "collectNodesFrom", "", "Landroidx/compose/runtime/SlotTable;", "anchor", "Landroidx/compose/runtime/Anchor;", "deactivateCurrentGroup", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "distanceFrom", "index", "root", "filterToRange", "", "start", "end", "findInsertLocation", "location", "findLocation", "firstInRange", "insertIfMissing", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "instance", "nearestCommonRootOf", "a", "b", "common", "removeCurrentGroup", "removeData", "group", "data", "removeLocation", "removeRange", "withAfterAnchorInfo", "R", "cb", "Lkotlin/Function2;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ComposerKt {
    public static final int compositionLocalMapKey = 202;
    private static CompositionTracer compositionTracer = null;
    private static final int defaultsKey = -127;
    private static final int invalidGroupLocation = -2;
    public static final int invocationKey = 200;
    private static final int nodeKey = 125;
    public static final int providerKey = 201;
    public static final int providerMapsKey = 204;
    public static final int providerValuesKey = 203;
    public static final int referenceKey = 206;
    public static final int reuseKey = 207;
    private static final int rootKey = 100;
    private static final Object invocation = new OpaqueKey("provider");
    private static final Object provider = new OpaqueKey("provider");
    private static final Object compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final Object providerValues = new OpaqueKey("providerValues");
    private static final Object providerMaps = new OpaqueKey("providers");
    private static final Object reference = new OpaqueKey("reference");
    private static final Comparator<Invalidation> InvalidationLocationAscending = new Comparator() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int compare;
            compare = Intrinsics.compare(((Invalidation) obj).getLocation(), ((Invalidation) obj2).getLocation());
            return compare;
        }
    };

    public static /* synthetic */ void getCompositionLocalMap$annotations() {
    }

    public static /* synthetic */ void getCompositionLocalMapKey$annotations() {
    }

    private static /* synthetic */ void getCompositionTracer$annotations() {
    }

    public static /* synthetic */ void getInvocation$annotations() {
    }

    public static /* synthetic */ void getInvocationKey$annotations() {
    }

    public static /* synthetic */ void getProvider$annotations() {
    }

    public static /* synthetic */ void getProviderKey$annotations() {
    }

    public static /* synthetic */ void getProviderMaps$annotations() {
    }

    public static /* synthetic */ void getProviderMapsKey$annotations() {
    }

    public static /* synthetic */ void getProviderValues$annotations() {
    }

    public static /* synthetic */ void getProviderValuesKey$annotations() {
    }

    public static /* synthetic */ void getReference$annotations() {
    }

    public static /* synthetic */ void getReferenceKey$annotations() {
    }

    public static /* synthetic */ void getReuseKey$annotations() {
    }

    @ComposeCompilerApi
    public static final <T> T cache(Composer composer, boolean z, Function0<? extends T> function0) {
        T t = (T) composer.rememberedValue();
        if (z || t == Composer.INSTANCE.getEmpty()) {
            T invoke = function0.invoke();
            composer.updateRememberedValue(invoke);
            return invoke;
        }
        return t;
    }

    @ComposeCompilerApi
    public static final void sourceInformation(Composer composer, String sourceInformation) {
        composer.sourceInformation(sourceInformation);
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerStart(Composer composer, int key, String sourceInformation) {
        composer.sourceInformationMarkerStart(key, sourceInformation);
    }

    @ComposeCompilerApi
    public static final boolean isTraceInProgress() {
        CompositionTracer it = compositionTracer;
        return it != null && it.isTraceInProgress();
    }

    @ComposeCompilerApi
    public static final void traceEventStart(int key, int dirty1, int dirty2, String info) {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventStart(key, dirty1, dirty2, info);
        }
    }

    @ComposeCompilerApi
    public static final void traceEventEnd() {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventEnd();
        }
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerEnd(Composer composer) {
        composer.sourceInformationMarkerEnd();
    }

    public static final void removeCurrentGroup(SlotWriter $this$removeCurrentGroup, RememberManager rememberManager) {
        int group$iv;
        SlotWriter this_$iv;
        int priority;
        int group$iv2 = $this$removeCurrentGroup.getCurrentGroup();
        SlotWriter this_$iv2 = $this$removeCurrentGroup;
        int $i$f$forAllData = 0;
        int address$iv = this_$iv2.groupIndexToAddress(group$iv2);
        int start$iv = this_$iv2.dataIndex(this_$iv2.groups, address$iv);
        int end$iv = this_$iv2.dataIndex(this_$iv2.groups, this_$iv2.groupIndexToAddress(this_$iv2.getCurrentGroup() + this_$iv2.groupSize(this_$iv2.getCurrentGroup())));
        int slot$iv = start$iv;
        while (slot$iv < end$iv) {
            Object slot = this_$iv2.slots[this_$iv2.dataIndexToDataAddress(slot$iv)];
            int slotIndex = slot$iv;
            if (slot instanceof ComposeNodeLifecycleCallback) {
                int endRelativeOrder = $this$removeCurrentGroup.getSlotsSize() - slotIndex;
                rememberManager.releasing((ComposeNodeLifecycleCallback) slot, endRelativeOrder, -1, -1);
            }
            if (!(slot instanceof RememberObserverHolder)) {
                group$iv = group$iv2;
                this_$iv = this_$iv2;
                priority = $i$f$forAllData;
            } else {
                int endRelativeSlotIndex = $this$removeCurrentGroup.getSlotsSize() - slotIndex;
                Anchor anchor$iv = ((RememberObserverHolder) slot).getAfter();
                int priority$iv = -1;
                int endRelativeAfter$iv = -1;
                if (anchor$iv != null && anchor$iv.getValid()) {
                    priority$iv = $this$removeCurrentGroup.anchorIndex(anchor$iv);
                    endRelativeAfter$iv = $this$removeCurrentGroup.getSlotsSize() - $this$removeCurrentGroup.slotsEndAllIndex$runtime_release(priority$iv);
                }
                int priority2 = priority$iv;
                int endRelativeAfter = endRelativeAfter$iv;
                group$iv = group$iv2;
                this_$iv = this_$iv2;
                priority = $i$f$forAllData;
                rememberManager.forgetting(((RememberObserverHolder) slot).getWrapped(), endRelativeSlotIndex, priority2, endRelativeAfter);
            }
            if (slot instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) slot).release();
            }
            slot$iv++;
            $i$f$forAllData = priority;
            this_$iv2 = this_$iv;
            group$iv2 = group$iv;
        }
        $this$removeCurrentGroup.removeGroup();
    }

    public static final <R> void withAfterAnchorInfo(SlotWriter $this$withAfterAnchorInfo, Anchor anchor, Function2<? super Integer, ? super Integer, ? extends R> function2) {
        int priority = -1;
        int endRelativeAfter = -1;
        if (anchor != null && anchor.getValid()) {
            priority = $this$withAfterAnchorInfo.anchorIndex(anchor);
            endRelativeAfter = $this$withAfterAnchorInfo.getSlotsSize() - $this$withAfterAnchorInfo.slotsEndAllIndex$runtime_release(priority);
        }
        function2.invoke(Integer.valueOf(priority), Integer.valueOf(endRelativeAfter));
    }

    public static final boolean isAfterFirstChild(SlotWriter $this$isAfterFirstChild) {
        return $this$isAfterFirstChild.getCurrentGroup() > $this$isAfterFirstChild.getParent() + 1;
    }

    public static final boolean isAfterFirstChild(SlotReader $this$isAfterFirstChild) {
        return $this$isAfterFirstChild.getCurrent() > $this$isAfterFirstChild.getParent() + 1;
    }

    public static final void deactivateCurrentGroup(SlotWriter $this$deactivateCurrentGroup, RememberManager rememberManager) {
        int start;
        int end;
        Object node;
        SlotWriter $this$withAfterAnchorInfo$iv;
        int start2 = $this$deactivateCurrentGroup.getCurrentGroup();
        int end2 = $this$deactivateCurrentGroup.getCurrentGroupEnd();
        for (int group = start2; group < end2; group++) {
            Object node2 = $this$deactivateCurrentGroup.node(group);
            if (node2 instanceof ComposeNodeLifecycleCallback) {
                int endRelativeOrder = $this$deactivateCurrentGroup.getSlotsSize() - $this$deactivateCurrentGroup.slotsStartIndex$runtime_release(group);
                rememberManager.deactivating((ComposeNodeLifecycleCallback) node2, endRelativeOrder, -1, -1);
            }
            SlotWriter this_$iv = $this$deactivateCurrentGroup;
            int address$iv = this_$iv.groupIndexToAddress(group);
            int slotsStart$iv = this_$iv.slotIndex(this_$iv.groups, address$iv);
            int slotsEnd$iv = this_$iv.dataIndex(this_$iv.groups, this_$iv.groupIndexToAddress(group + 1));
            int slot$iv = slotsStart$iv;
            while (slot$iv < slotsEnd$iv) {
                int slotIndex = slot$iv - slotsStart$iv;
                Object data = this_$iv.slots[this_$iv.dataIndexToDataAddress(slot$iv)];
                if (data instanceof RememberObserverHolder) {
                    RememberObserver wrapped = ((RememberObserverHolder) data).getWrapped();
                    start = start2;
                    if (wrapped instanceof ReusableRememberObserver) {
                        end = end2;
                        node = node2;
                        $this$withAfterAnchorInfo$iv = this_$iv;
                    } else {
                        removeData($this$deactivateCurrentGroup, group, slotIndex, data);
                        int endRelativeOrder2 = $this$deactivateCurrentGroup.getSlotsSize() - slotIndex;
                        end = end2;
                        Anchor anchor$iv = ((RememberObserverHolder) data).getAfter();
                        int priority$iv = -1;
                        int endRelativeAfter$iv = -1;
                        if (anchor$iv == null || !anchor$iv.getValid()) {
                            node = node2;
                            $this$withAfterAnchorInfo$iv = this_$iv;
                        } else {
                            node = node2;
                            $this$withAfterAnchorInfo$iv = this_$iv;
                            int priority$iv2 = $this$deactivateCurrentGroup.anchorIndex(anchor$iv);
                            int priority$iv3 = $this$deactivateCurrentGroup.getSlotsSize();
                            endRelativeAfter$iv = priority$iv3 - $this$deactivateCurrentGroup.slotsEndAllIndex$runtime_release(priority$iv2);
                            priority$iv = priority$iv2;
                        }
                        int priority = priority$iv;
                        int endRelativeAfter = endRelativeAfter$iv;
                        rememberManager.forgetting(wrapped, endRelativeOrder2, priority, endRelativeAfter);
                    }
                } else {
                    start = start2;
                    end = end2;
                    node = node2;
                    $this$withAfterAnchorInfo$iv = this_$iv;
                    if (data instanceof RecomposeScopeImpl) {
                        removeData($this$deactivateCurrentGroup, group, slotIndex, data);
                        ((RecomposeScopeImpl) data).release();
                    }
                }
                slot$iv++;
                start2 = start;
                this_$iv = $this$withAfterAnchorInfo$iv;
                end2 = end;
                node2 = node;
            }
        }
    }

    private static final void removeData(SlotWriter $this$removeData, int group, int index, Object data) {
        boolean value$iv = data == $this$removeData.set(group, index, Composer.INSTANCE.getEmpty());
        if (value$iv) {
            return;
        }
        composeImmediateRuntimeError("Slot table is out of sync");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> MutableScatterMap<K, Object> multiMap(int initialCapacity) {
        return MutableScatterMultiMap.m3637constructorimpl(new MutableScatterMap(initialCapacity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getKey(Object value, Object left, Object right) {
        Object key;
        JoinedKey it = value instanceof JoinedKey ? (JoinedKey) value : null;
        if (it == null) {
            return null;
        }
        if (Intrinsics.areEqual(it.getLeft(), left) && Intrinsics.areEqual(it.getRight(), right)) {
            key = value;
        } else {
            key = getKey(it.getLeft(), left, right);
            if (key == null) {
                key = getKey(it.getRight(), left, right);
            }
        }
        return key;
    }

    private static final int findLocation(List<Invalidation> list, int location) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Invalidation midVal = list.get(mid);
            int cmp = Intrinsics.compare(midVal.getLocation(), location);
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

    private static final int findInsertLocation(List<Invalidation> list, int location) {
        int it = findLocation(list, location);
        return it < 0 ? -(it + 1) : it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List<Invalidation> list, int location, RecomposeScopeImpl scope, Object instance) {
        int index = findLocation(list, location);
        if (index < 0) {
            list.add(-(index + 1), new Invalidation(scope, location, instance instanceof DerivedState ? instance : null));
            return;
        }
        Invalidation invalidation = list.get(index);
        if (instance instanceof DerivedState) {
            Object oldInstance = invalidation.getInstances();
            if (oldInstance != null) {
                if (oldInstance instanceof MutableScatterSet) {
                    ((MutableScatterSet) oldInstance).add(instance);
                    return;
                } else {
                    invalidation.setInstances(ScatterSetKt.mutableScatterSetOf(oldInstance, instance));
                    return;
                }
            }
            invalidation.setInstances(instance);
            return;
        }
        invalidation.setInstances(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List<Invalidation> list, int start, int end) {
        int index = findInsertLocation(list, start);
        if (index < list.size()) {
            Invalidation firstInvalidation = list.get(index);
            if (firstInvalidation.getLocation() < end) {
                return firstInvalidation;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List<Invalidation> list, int location) {
        int index = findLocation(list, location);
        if (index >= 0) {
            return list.remove(index);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List<Invalidation> list, int start, int end) {
        int index = findInsertLocation(list, start);
        while (index < list.size()) {
            Invalidation validation = list.get(index);
            if (validation.getLocation() >= end) {
                return;
            } else {
                list.remove(index);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Invalidation> filterToRange(List<Invalidation> list, int start, int end) {
        List result = new ArrayList();
        for (int index = findInsertLocation(list, start); index < list.size(); index++) {
            Invalidation invalidation = list.get(index);
            if (invalidation.getLocation() >= end) {
                break;
            }
            result.add(invalidation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int $this$asBool) {
        return $this$asBool != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable $this$collectNodesFrom, Anchor anchor) {
        List result = new ArrayList();
        SlotReader reader$iv = $this$collectNodesFrom.openReader();
        try {
            int index = $this$collectNodesFrom.anchorIndex(anchor);
            collectNodesFrom$lambda$10$collectFromGroup(reader$iv, result, index);
            Unit unit = Unit.INSTANCE;
            return result;
        } finally {
            reader$iv.close();
        }
    }

    private static final void collectNodesFrom$lambda$10$collectFromGroup(SlotReader $reader, List<Object> list, int group) {
        if ($reader.isNode(group)) {
            list.add($reader.node(group));
            return;
        }
        int current = group + 1;
        int end = $reader.groupSize(group) + group;
        while (current < end) {
            collectNodesFrom$lambda$10$collectFromGroup($reader, list, current);
            current += $reader.groupSize(current);
        }
    }

    private static final int distanceFrom(SlotReader $this$distanceFrom, int index, int root) {
        int count = 0;
        int current = index;
        while (current > 0 && current != root) {
            current = $this$distanceFrom.parent(current);
            count++;
        }
        return count;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader $this$nearestCommonRootOf, int a, int b, int common) {
        if (a == b) {
            return a;
        }
        if (a == common || b == common) {
            return common;
        }
        if ($this$nearestCommonRootOf.parent(a) == b) {
            return b;
        }
        if ($this$nearestCommonRootOf.parent(b) == a) {
            return a;
        }
        if ($this$nearestCommonRootOf.parent(a) == $this$nearestCommonRootOf.parent(b)) {
            return $this$nearestCommonRootOf.parent(a);
        }
        int currentA = a;
        int currentB = b;
        int aDistance = distanceFrom($this$nearestCommonRootOf, a, common);
        int bDistance = distanceFrom($this$nearestCommonRootOf, b, common);
        int i = aDistance - bDistance;
        for (int i2 = 0; i2 < i; i2++) {
            currentA = $this$nearestCommonRootOf.parent(currentA);
        }
        int i3 = bDistance - aDistance;
        for (int i4 = 0; i4 < i3; i4++) {
            currentB = $this$nearestCommonRootOf.parent(currentB);
        }
        while (currentA != currentB) {
            currentA = $this$nearestCommonRootOf.parent(currentA);
            currentB = $this$nearestCommonRootOf.parent(currentB);
        }
        return currentA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo $this$joinedKey) {
        return $this$joinedKey.getObjectKey() != null ? new JoinedKey(Integer.valueOf($this$joinedKey.getKey()), $this$joinedKey.getObjectKey()) : Integer.valueOf($this$joinedKey.getKey());
    }

    public static final Object getInvocation() {
        return invocation;
    }

    public static final Object getProvider() {
        return provider;
    }

    public static final Object getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static final Object getProviderValues() {
        return providerValues;
    }

    public static final Object getProviderMaps() {
        return providerMaps;
    }

    public static final Object getReference() {
        return reference;
    }

    public static final void runtimeCheck(boolean value, Function0<String> function0) {
        if (!value) {
            composeImmediateRuntimeError(function0.invoke());
        }
    }

    public static final void runtimeCheck(boolean value) {
        if (value) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }

    public static final Void composeRuntimeError(String message) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + message + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    public static final void composeImmediateRuntimeError(String message) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + message + "). Please report to Google or use https://goo.gle/compose-feedback");
    }
}
