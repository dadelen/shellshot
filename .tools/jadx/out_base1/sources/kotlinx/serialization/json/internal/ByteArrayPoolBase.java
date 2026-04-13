package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ArrayPools.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0004R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/ByteArrayPoolBase;", "", "<init>", "()V", "arrays", "Lkotlin/collections/ArrayDeque;", "", "bytesTotal", "", "take", "size", "releaseImpl", "", "array", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public class ByteArrayPoolBase {
    private final ArrayDeque<byte[]> arrays = new ArrayDeque<>();
    private int bytesTotal;

    protected final byte[] take(int size) {
        byte[] candidate;
        synchronized (this) {
            candidate = this.arrays.removeLastOrNull();
            if (candidate != null) {
                this.bytesTotal -= candidate.length / 2;
            } else {
                candidate = null;
            }
        }
        return candidate == null ? new byte[size] : candidate;
    }

    protected final void releaseImpl(byte[] array) {
        int i;
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            int length = this.bytesTotal + array.length;
            i = ArrayPoolsKt.MAX_CHARS_IN_POOL;
            if (length < i) {
                this.bytesTotal += array.length / 2;
                this.arrays.addLast(array);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
