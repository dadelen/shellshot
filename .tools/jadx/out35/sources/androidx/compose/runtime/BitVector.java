package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0086\u0002J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tJ\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\rH\u0086\u0002J\u0016\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/BitVector;", "", "()V", "first", "", "others", "", "second", "size", "", "getSize", "()I", "get", "", "index", "nextClear", "nextSet", "set", "", "value", "setRange", "start", "end", "toString", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class BitVector {
    public static final int $stable = 8;
    private long first;
    private long[] others;
    private long second;

    public final int getSize() {
        long[] it = this.others;
        if (it != null) {
            return (it.length + 2) * 64;
        }
        return 128;
    }

    public final boolean get(int index) {
        int address;
        if (index < 0 || index >= getSize()) {
            throw new IllegalStateException(("Index " + index + " out of bound").toString());
        }
        if (index < 64) {
            return ((1 << index) & this.first) != 0;
        }
        if (index < 128) {
            return ((1 << (index + (-64))) & this.second) != 0;
        }
        long[] others = this.others;
        if (others == null || (index / 64) - 2 >= others.length) {
            return false;
        }
        int bit = index % 64;
        return ((1 << bit) & others[address]) != 0;
    }

    public final void set(int index, boolean value) {
        if (index < 64) {
            long mask = 1 << index;
            long j = this.first;
            this.first = value ? j | mask : j & (~mask);
            return;
        }
        if (index < 128) {
            long mask2 = 1 << (index - 64);
            long j2 = this.second;
            this.second = value ? j2 | mask2 : j2 & (~mask2);
            return;
        }
        int address = (index / 64) - 2;
        long mask3 = 1 << (index % 64);
        long[] others = this.others;
        if (others == null) {
            BitVector $this$set_u24lambda_u241 = this;
            long[] others2 = new long[address + 1];
            $this$set_u24lambda_u241.others = others2;
            others = others2;
        }
        if (address >= others.length) {
            long[] copyOf = Arrays.copyOf(others, address + 1);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            others = copyOf;
            this.others = others;
        }
        long bits = others[address];
        others[address] = value ? bits | mask3 : (~mask3) & bits;
    }

    public final int nextSet(int index) {
        int size = getSize();
        for (int bit = index; bit < size; bit++) {
            if (get(bit)) {
                return bit;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final int nextClear(int index) {
        int size = getSize();
        for (int bit = index; bit < size; bit++) {
            if (!get(bit)) {
                return bit;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final void setRange(int start, int end) {
        for (int bit = start; bit < end; bit++) {
            set(bit, true);
        }
    }

    public String toString() {
        StringBuilder $this$toString_u24lambda_u242 = new StringBuilder();
        boolean first = true;
        $this$toString_u24lambda_u242.append("BitVector [");
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (get(i)) {
                if (!first) {
                    $this$toString_u24lambda_u242.append(", ");
                }
                first = false;
                $this$toString_u24lambda_u242.append(i);
            }
        }
        $this$toString_u24lambda_u242.append(AbstractJsonLexerKt.END_LIST);
        String sb = $this$toString_u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
