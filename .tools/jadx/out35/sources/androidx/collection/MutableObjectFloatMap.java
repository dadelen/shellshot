package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: ObjectFloatMap.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\n\u001a\u00020\bH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000fJ'\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00028\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0016\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00028\u0000H\u0086\n¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u0019\u001a\u00020\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0086\nJ\u001e\u0010\u0019\u001a\u00020\b2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001dH\u0086\n¢\u0006\u0002\u0010\u001eJ\u0017\u0010\u0019\u001a\u00020\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0086\nJ\u0017\u0010\u0019\u001a\u00020\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0086\nJ\u0017\u0010!\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\nJ\u001b\u0010#\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u0011¢\u0006\u0002\u0010%J#\u0010#\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0011¢\u0006\u0002\u0010'J\u0014\u0010(\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0013\u0010)\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJ\u001b\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u0011¢\u0006\u0002\u0010+J&\u0010,\u001a\u00020\b2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020*0.H\u0086\bø\u0001\u0000J\u0010\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\u0004H\u0001J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\u0004H\u0002J\u001e\u00103\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u0011H\u0086\u0002¢\u0006\u0002\u0010%J\u0006\u00104\u001a\u00020\u0004J\u0019\u00105\u001a\u00020\b2\u0006\u00100\u001a\u00020\u00042\u0006\u0010$\u001a\u000206H\u0082\bR\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00067"}, d2 = {"Landroidx/collection/MutableObjectFloatMap;", "K", "Landroidx/collection/ObjectFloatMap;", "initialCapacity", "", "(I)V", "growthLimit", "adjustStorage", "", "clear", "dropDeletes", "findFirstAvailableSlot", "hash1", "findIndex", "key", "(Ljava/lang/Object;)I", "getOrPut", "", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)F", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "(Ljava/lang/Object;)V", "keys", "Landroidx/collection/ScatterSet;", "", "([Ljava/lang/Object;)V", "", "Lkotlin/sequences/Sequence;", "plusAssign", "from", "put", "value", "(Ljava/lang/Object;F)V", "default", "(Ljava/lang/Object;FF)F", "putAll", "remove", "", "(Ljava/lang/Object;F)Z", "removeIf", "predicate", "Lkotlin/Function2;", "removeValueAt", "index", "resizeStorage", "newCapacity", "set", "trim", "writeMetadata", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MutableObjectFloatMap<K> extends ObjectFloatMap<K> {
    private int growthLimit;

    public MutableObjectFloatMap() {
        this(0, 1, null);
    }

    public /* synthetic */ MutableObjectFloatMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    public MutableObjectFloatMap(int initialCapacity) {
        super(null);
        boolean value$iv = initialCapacity >= 0;
        if (!value$iv) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(initialCapacity));
    }

    private final void initializeStorage(int initialCapacity) {
        int newCapacity;
        if (initialCapacity > 0) {
            newCapacity = Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity));
        } else {
            newCapacity = 0;
        }
        this._capacity = newCapacity;
        initializeMetadata(newCapacity);
        this.keys = new Object[newCapacity];
        this.values = new float[newCapacity];
    }

    private final void initializeMetadata(int capacity) {
        long[] jArr;
        if (capacity == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int size = ((((capacity + 1) + 7) + 7) & (-8)) >> 3;
            long[] $this$initializeMetadata_u24lambda_u241 = new long[size];
            ArraysKt.fill$default($this$initializeMetadata_u24lambda_u241, -9187201950435737472L, 0, 0, 6, (Object) null);
            jArr = $this$initializeMetadata_u24lambda_u241;
        }
        this.metadata = jArr;
        long[] data$iv = this.metadata;
        int i$iv = capacity >> 3;
        int b$iv = (capacity & 7) << 3;
        data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(get_capacity()) - this._size;
    }

    public final float getOrPut(K key, Function0<Float> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        int index = findKeyIndex(key);
        if (index >= 0) {
            return this.values[index];
        }
        float value = defaultValue.invoke().floatValue();
        set(key, value);
        return value;
    }

    public final void set(K key, float value) {
        int index = findIndex(key);
        if (index < 0) {
            index = ~index;
        }
        this.keys[index] = key;
        this.values[index] = value;
    }

    public final void put(K key, float value) {
        set(key, value);
    }

    public final float put(K key, float value, float r6) {
        int index = findIndex(key);
        float previous = r6;
        if (index < 0) {
            index = ~index;
        } else {
            previous = this.values[index];
        }
        this.keys[index] = key;
        this.values[index] = value;
        return previous;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void putAll(ObjectFloatMap<K> from) {
        int $i$f$forEach;
        Object[] k$iv;
        int i;
        int $i$f$forEach2;
        Object[] k$iv2;
        Intrinsics.checkNotNullParameter(from, "from");
        ObjectFloatMap this_$iv = from;
        int $i$f$forEach3 = 0;
        Object[] k$iv3 = this_$iv.keys;
        float[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            ObjectFloatMap this_$iv2 = this_$iv;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i2;
                        $i$f$forEach2 = $i$f$forEach3;
                        k$iv2 = k$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object key = k$iv3[index$iv$iv];
                        $i$f$forEach2 = $i$f$forEach3;
                        float value = v$iv[index$iv$iv];
                        k$iv2 = k$iv3;
                        set(key, value);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    $i$f$forEach3 = $i$f$forEach2;
                    k$iv3 = k$iv2;
                }
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv = this_$iv2;
            $i$f$forEach3 = $i$f$forEach;
            k$iv3 = k$iv;
        }
    }

    public final void plusAssign(ObjectFloatMap<K> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        putAll(from);
    }

    public final void remove(K key) {
        int index = findKeyIndex(key);
        if (index >= 0) {
            removeValueAt(index);
        }
    }

    public final boolean remove(K key, float value) {
        int index = findKeyIndex(key);
        if (index >= 0) {
            if (this.values[index] == value) {
                removeValueAt(index);
                return true;
            }
        }
        return false;
    }

    public final void removeIf(Function2<? super K, ? super Float, Boolean> predicate) {
        int $i$f$removeIf;
        int $i$f$removeIf2;
        int i;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int $i$f$removeIf3 = 0;
        MutableObjectFloatMap<K> this_$iv = this;
        long[] m$iv = this_$iv.metadata;
        int lastIndex$iv = m$iv.length - 2;
        int i$iv = 0;
        if (0 > lastIndex$iv) {
            return;
        }
        while (true) {
            long slot$iv = m$iv[i$iv];
            long $this$maskEmptyOrDeleted$iv$iv = ((~slot$iv) << 7) & slot$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv == -9187201950435737472L) {
                $i$f$removeIf = $i$f$removeIf3;
            } else {
                int i2 = 8;
                int bitCount$iv = 8 - ((~(i$iv - lastIndex$iv)) >>> 31);
                int j$iv = 0;
                while (j$iv < bitCount$iv) {
                    long value$iv$iv = 255 & slot$iv;
                    if (!(value$iv$iv < 128)) {
                        $i$f$removeIf2 = $i$f$removeIf3;
                        i = i2;
                    } else {
                        int index$iv = (i$iv << 3) + j$iv;
                        i = i2;
                        $i$f$removeIf2 = $i$f$removeIf3;
                        if (predicate.invoke(this.keys[index$iv], Float.valueOf(this.values[index$iv])).booleanValue()) {
                            removeValueAt(index$iv);
                        }
                    }
                    slot$iv >>= i;
                    j$iv++;
                    i2 = i;
                    $i$f$removeIf3 = $i$f$removeIf2;
                }
                $i$f$removeIf = $i$f$removeIf3;
                if (bitCount$iv != i2) {
                    return;
                }
            }
            if (i$iv == lastIndex$iv) {
                return;
            }
            i$iv++;
            $i$f$removeIf3 = $i$f$removeIf;
        }
    }

    public final void minusAssign(K key) {
        remove(key);
    }

    public final void minusAssign(K[] keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        for (K k : keys) {
            remove(k);
        }
    }

    public final void minusAssign(Iterable<? extends K> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Iterator<? extends K> it = keys.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    public final void minusAssign(Sequence<? extends K> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Iterator<? extends K> it = keys.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(ScatterSet<K> keys) {
        ScatterSet this_$iv;
        int i;
        ScatterSet this_$iv2;
        Intrinsics.checkNotNullParameter(keys, "keys");
        int $i$f$minusAssign = 0;
        ScatterSet this_$iv3 = keys;
        Object[] k$iv = this_$iv3.elements;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            int $i$f$minusAssign2 = $i$f$minusAssign;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                this_$iv = this_$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i2;
                        this_$iv2 = this_$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object key = k$iv[index$iv$iv];
                        this_$iv2 = this_$iv3;
                        remove(key);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$minusAssign = $i$f$minusAssign2;
            this_$iv3 = this_$iv;
        }
    }

    public final void removeValueAt(int index) {
        this._size--;
        long[] data$iv = this.metadata;
        int capacity$iv = this._capacity;
        int i$iv$iv = index >> 3;
        int b$iv$iv = (index & 7) << 3;
        data$iv[i$iv$iv] = (data$iv[i$iv$iv] & (~(255 << b$iv$iv))) | (254 << b$iv$iv);
        int $i$f$writeRawMetadata = index - 7;
        int cloneIndex$iv = ($i$f$writeRawMetadata & capacity$iv) + (capacity$iv & 7);
        data$iv[cloneIndex$iv >> 3] = data$iv[index >> 3];
        this.keys[index] = null;
    }

    public final void clear() {
        this._size = 0;
        if (this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, -9187201950435737472L, 0, 0, 6, (Object) null);
            long[] data$iv = this.metadata;
            int offset$iv = this._capacity;
            int i$iv = offset$iv >> 3;
            int b$iv = (offset$iv & 7) << 3;
            data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        }
        ArraysKt.fill(this.keys, (Object) null, 0, this._capacity);
        initializeGrowth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x008e, code lost:
    
        r9 = (((~r3) << 6) & r3) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009b, code lost:
    
        if (r9 == 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x009e, code lost:
    
        r2 = findFirstAvailableSlot(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a8, code lost:
    
        if (r24.growthLimit != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bd, code lost:
    
        if (((r24.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bf, code lost:
    
        r3 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c3, code lost:
    
        if (r3 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c5, code lost:
    
        adjustStorage();
        r2 = findFirstAvailableSlot(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c2, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00cc, code lost:
    
        r24._size++;
        r3 = r24.growthLimit;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e8, code lost:
    
        if (((r24.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 128) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ea, code lost:
    
        r17 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ef, code lost:
    
        r24.growthLimit = r3 - r17;
        r3 = r24.metadata;
        r11 = r24._capacity;
        r12 = r5;
        r17 = r2 >> 3;
        r18 = (r2 & 7) << 3;
        r3[r17] = (r3[r17] & (~(255 << r18))) | (r12 << r18);
        r9 = ((r2 - 7) & r11) + (r11 & 7);
        r3[r9 >> 3] = r3[r2 >> 3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x011f, code lost:
    
        return ~r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ed, code lost:
    
        r17 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int findIndex(K r25) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableObjectFloatMap.findIndex(java.lang.Object):int");
    }

    private final int findFirstAvailableSlot(int hash1) {
        int probeMask = this._capacity;
        int probeOffset = hash1 & probeMask;
        int probeIndex = 0;
        while (true) {
            long[] metadata$iv = this.metadata;
            int i$iv = probeOffset >> 3;
            int b$iv = (probeOffset & 7) << 3;
            long g = (metadata$iv[i$iv] >>> b$iv) | ((metadata$iv[i$iv + 1] << (64 - b$iv)) & ((-b$iv) >> 63));
            long $this$maskEmptyOrDeleted$iv = ((~g) << 7) & g & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv != 0) {
                return ((Long.numberOfTrailingZeros($this$maskEmptyOrDeleted$iv) >> 3) + probeOffset) & probeMask;
            }
            probeIndex += 8;
            probeOffset = (probeOffset + probeIndex) & probeMask;
        }
    }

    public final int trim() {
        int previousCapacity = this._capacity;
        int newCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (newCapacity < previousCapacity) {
            resizeStorage(newCapacity);
            return previousCapacity - this._capacity;
        }
        return 0;
    }

    private final void adjustStorage() {
        if (this._capacity > 8 && Long.compareUnsigned(ULong.m7386constructorimpl(ULong.m7386constructorimpl(this._size) * 32), ULong.m7386constructorimpl(ULong.m7386constructorimpl(this._capacity) * 25)) <= 0) {
            dropDeletes();
        } else {
            resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    private final void dropDeletes() {
        long[] metadata = this.metadata;
        int capacity = this._capacity;
        Object[] keys = this.keys;
        float[] values = this.values;
        ScatterMapKt.convertMetadataForCleanup(metadata, capacity);
        int swapIndex = -1;
        int index = 0;
        while (index != capacity) {
            long m = (metadata[index >> 3] >> ((index & 7) << 3)) & 255;
            if (m == 128) {
                swapIndex = index;
                index++;
            } else if (m != 254) {
                index++;
            } else {
                Object k$iv = keys[index];
                int hash$iv = (k$iv != null ? k$iv.hashCode() : 0) * ScatterMapKt.MurmurHashC1;
                int hash = hash$iv ^ (hash$iv << 16);
                int $i$f$h1 = hash >>> 7;
                int targetIndex = findFirstAvailableSlot($i$f$h1);
                int probeOffset = $i$f$h1 & capacity;
                int newProbeIndex = ((targetIndex - probeOffset) & capacity) / 8;
                int oldProbeIndex = ((index - probeOffset) & capacity) / 8;
                if (newProbeIndex != oldProbeIndex) {
                    Object[] keys2 = keys;
                    float[] values2 = values;
                    if (((metadata[targetIndex >> 3] >> ((targetIndex & 7) << 3)) & 255) == 128) {
                        int $i$f$h2 = hash & WorkQueueKt.MASK;
                        long value$iv = $i$f$h2;
                        int i$iv = targetIndex >> 3;
                        int b$iv = (targetIndex & 7) << 3;
                        metadata[i$iv] = (metadata[i$iv] & (~(255 << b$iv))) | (value$iv << b$iv);
                        int i$iv2 = index >> 3;
                        int b$iv2 = (index & 7) << 3;
                        long value$iv2 = 255 << b$iv2;
                        metadata[i$iv2] = (metadata[i$iv2] & (~value$iv2)) | (128 << b$iv2);
                        keys2[targetIndex] = keys2[index];
                        keys2[index] = null;
                        values2[targetIndex] = values2[index];
                        values2[index] = 0.0f;
                        swapIndex = index;
                    } else {
                        int $i$f$h22 = hash & WorkQueueKt.MASK;
                        long value$iv3 = $i$f$h22;
                        int i$iv3 = targetIndex >> 3;
                        int b$iv3 = (targetIndex & 7) << 3;
                        metadata[i$iv3] = (metadata[i$iv3] & (~(255 << b$iv3))) | (value$iv3 << b$iv3);
                        if (swapIndex == -1) {
                            swapIndex = ScatterMapKt.findEmptySlot(metadata, index + 1, capacity);
                        }
                        keys2[swapIndex] = keys2[targetIndex];
                        keys2[targetIndex] = keys2[index];
                        keys2[index] = keys2[swapIndex];
                        values2[swapIndex] = values2[targetIndex];
                        values2[targetIndex] = values2[index];
                        values2[index] = values2[swapIndex];
                        index--;
                    }
                    metadata[ArraysKt.getLastIndex(metadata)] = (metadata[0] & 72057594037927935L) | Long.MIN_VALUE;
                    index++;
                    keys = keys2;
                    values = values2;
                } else {
                    int $i$f$h23 = hash & WorkQueueKt.MASK;
                    Object[] keys3 = keys;
                    long value$iv4 = $i$f$h23;
                    int i$iv4 = index >> 3;
                    int b$iv4 = (index & 7) << 3;
                    long value$iv5 = 255 << b$iv4;
                    metadata[i$iv4] = (metadata[i$iv4] & (~value$iv5)) | (value$iv4 << b$iv4);
                    metadata[ArraysKt.getLastIndex(metadata)] = (metadata[0] & 72057594037927935L) | Long.MIN_VALUE;
                    index++;
                    keys = keys3;
                    values = values;
                }
            }
        }
        initializeGrowth();
    }

    private final void resizeStorage(int newCapacity) {
        long[] previousMetadata;
        MutableObjectFloatMap<K> mutableObjectFloatMap = this;
        long[] previousMetadata2 = mutableObjectFloatMap.metadata;
        Object[] previousKeys = mutableObjectFloatMap.keys;
        float[] previousValues = mutableObjectFloatMap.values;
        int previousCapacity = mutableObjectFloatMap._capacity;
        initializeStorage(newCapacity);
        long[] newMetadata = mutableObjectFloatMap.metadata;
        Object[] newKeys = mutableObjectFloatMap.keys;
        float[] newValues = mutableObjectFloatMap.values;
        int capacity = mutableObjectFloatMap._capacity;
        int i = 0;
        while (i < previousCapacity) {
            if (!(((previousMetadata2[i >> 3] >> ((i & 7) << 3)) & 255) < 128)) {
                previousMetadata = previousMetadata2;
            } else {
                Object previousKey = previousKeys[i];
                int hash$iv = (previousKey != null ? previousKey.hashCode() : 0) * ScatterMapKt.MurmurHashC1;
                int $i$f$hash = hash$iv ^ (hash$iv << 16);
                int $i$f$h1 = $i$f$hash >>> 7;
                int index = mutableObjectFloatMap.findFirstAvailableSlot($i$f$h1);
                int $i$f$h2 = $i$f$hash & WorkQueueKt.MASK;
                long value$iv = $i$f$h2;
                int i$iv$iv = index >> 3;
                int b$iv$iv = (index & 7) << 3;
                previousMetadata = previousMetadata2;
                newMetadata[i$iv$iv] = (newMetadata[i$iv$iv] & (~(255 << b$iv$iv))) | (value$iv << b$iv$iv);
                int cloneIndex$iv = ((index - 7) & capacity) + (capacity & 7);
                newMetadata[cloneIndex$iv >> 3] = newMetadata[index >> 3];
                newKeys[index] = previousKey;
                newValues[index] = previousValues[i];
            }
            i++;
            mutableObjectFloatMap = this;
            previousMetadata2 = previousMetadata;
        }
    }

    private final void writeMetadata(int index, long value) {
        long[] m = this.metadata;
        int i$iv = index >> 3;
        int b$iv = (index & 7) << 3;
        m[i$iv] = (m[i$iv] & (~(255 << b$iv))) | (value << b$iv);
        int $i$f$writeRawMetadata = this._capacity;
        int cloneIndex = ((index - 7) & $i$f$writeRawMetadata) + ($i$f$writeRawMetadata & 7);
        int i$iv2 = cloneIndex >> 3;
        int b$iv2 = (cloneIndex & 7) << 3;
        m[i$iv2] = ((~(255 << b$iv2)) & m[i$iv2]) | (value << b$iv2);
    }
}
