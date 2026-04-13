package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composers.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\fH\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/ComposerForUnsignedNumbers;", "Lkotlinx/serialization/json/internal/Composer;", "writer", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "forceQuoting", "", "<init>", "(Lkotlinx/serialization/json/internal/InternalJsonWriter;Z)V", "print", "", "v", "", "", "", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class ComposerForUnsignedNumbers extends Composer {
    private final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(InternalJsonWriter writer, boolean forceQuoting) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = forceQuoting;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int v) {
        if (this.forceQuoting) {
            printQuoted(Integer.toUnsignedString(UInt.m7307constructorimpl(v)));
        } else {
            print(Integer.toUnsignedString(UInt.m7307constructorimpl(v)));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long v) {
        if (this.forceQuoting) {
            printQuoted(Long.toUnsignedString(ULong.m7386constructorimpl(v)));
        } else {
            print(Long.toUnsignedString(ULong.m7386constructorimpl(v)));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte v) {
        if (this.forceQuoting) {
            printQuoted(UByte.m7274toStringimpl(UByte.m7230constructorimpl(v)));
        } else {
            print(UByte.m7274toStringimpl(UByte.m7230constructorimpl(v)));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short v) {
        if (this.forceQuoting) {
            printQuoted(UShort.m7537toStringimpl(UShort.m7493constructorimpl(v)));
        } else {
            print(UShort.m7537toStringimpl(UShort.m7493constructorimpl(v)));
        }
    }
}
