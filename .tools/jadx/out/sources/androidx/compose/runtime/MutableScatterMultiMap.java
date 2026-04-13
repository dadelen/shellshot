package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Composer.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0083@\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00028\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0088\u0001\u0004¨\u0006 "}, d2 = {"Landroidx/compose/runtime/MutableScatterMultiMap;", "K", "V", "", "map", "Landroidx/collection/MutableScatterMap;", "constructor-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/MutableScatterMap;", "getMap", "()Landroidx/collection/MutableScatterMap;", "equals", "", "other", "equals-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Landroidx/collection/MutableScatterMap;)I", "pop", "key", "pop-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Ljava/lang/Object;", "put", "", "value", "put-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "", "toString-impl", "(Landroidx/collection/MutableScatterMap;)Ljava/lang/String;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes13.dex */
final class MutableScatterMultiMap<K, V> {
    private final MutableScatterMap<K, Object> map;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ MutableScatterMultiMap m3636boximpl(MutableScatterMap mutableScatterMap) {
        return new MutableScatterMultiMap(mutableScatterMap);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static <K, V> MutableScatterMap<K, Object> m3637constructorimpl(MutableScatterMap<K, Object> mutableScatterMap) {
        return mutableScatterMap;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m3638equalsimpl(MutableScatterMap<K, Object> mutableScatterMap, Object obj) {
        return (obj instanceof MutableScatterMultiMap) && Intrinsics.areEqual(mutableScatterMap, ((MutableScatterMultiMap) obj).getMap());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3639equalsimpl0(MutableScatterMap<K, Object> mutableScatterMap, MutableScatterMap<K, Object> mutableScatterMap2) {
        return Intrinsics.areEqual(mutableScatterMap, mutableScatterMap2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m3640hashCodeimpl(MutableScatterMap<K, Object> mutableScatterMap) {
        return mutableScatterMap.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m3643toStringimpl(MutableScatterMap<K, Object> mutableScatterMap) {
        return "MutableScatterMultiMap(map=" + mutableScatterMap + ')';
    }

    public boolean equals(Object obj) {
        return m3638equalsimpl(this.map, obj);
    }

    public int hashCode() {
        return m3640hashCodeimpl(this.map);
    }

    public String toString() {
        return m3643toStringimpl(this.map);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableScatterMap getMap() {
        return this.map;
    }

    private /* synthetic */ MutableScatterMultiMap(MutableScatterMap map) {
        this.map = map;
    }

    public final MutableScatterMap<K, Object> getMap() {
        return this.map;
    }

    /* renamed from: put-impl, reason: not valid java name */
    public static final void m3642putimpl(MutableScatterMap<K, Object> mutableScatterMap, K k, V v) {
        Object obj;
        int findInsertIndex = mutableScatterMap.findInsertIndex(k);
        boolean z = findInsertIndex < 0;
        Object obj2 = z ? null : mutableScatterMap.values[findInsertIndex];
        if (obj2 == null) {
            obj = v;
        } else if (TypeIntrinsics.isMutableList(obj2)) {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.MutableList<V of androidx.compose.runtime.MutableScatterMultiMap.put_impl$lambda$0>");
            List asMutableList = TypeIntrinsics.asMutableList(obj2);
            asMutableList.add(v);
            obj = asMutableList;
        } else {
            obj = CollectionsKt.mutableListOf(obj2, v);
        }
        if (z) {
            int i = ~findInsertIndex;
            mutableScatterMap.keys[i] = k;
            mutableScatterMap.values[i] = obj;
            return;
        }
        mutableScatterMap.values[findInsertIndex] = obj;
    }

    /* renamed from: pop-impl, reason: not valid java name */
    public static final V m3641popimpl(MutableScatterMap<K, Object> mutableScatterMap, K k) {
        V v;
        Object obj = mutableScatterMap.get(k);
        if (obj == null) {
            return null;
        }
        if (TypeIntrinsics.isMutableList(obj)) {
            List asMutableList = TypeIntrinsics.asMutableList(obj);
            v = (V) asMutableList.remove(0);
            if (asMutableList.isEmpty()) {
                mutableScatterMap.remove(k);
            }
        } else {
            mutableScatterMap.remove(k);
            v = (V) obj;
        }
        Intrinsics.checkNotNull(v, "null cannot be cast to non-null type V of androidx.compose.runtime.MutableScatterMultiMap.pop_impl$lambda$1");
        return v;
    }
}
