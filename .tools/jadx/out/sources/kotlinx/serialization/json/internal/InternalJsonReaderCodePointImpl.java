package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: JsonStreams.kt */
@JsonFriendModuleApi
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/internal/InternalJsonReaderCodePointImpl;", "Lkotlinx/serialization/json/internal/InternalJsonReader;", "<init>", "()V", "exhausted", "", "nextCodePoint", "", "bufferedChar", "", "Ljava/lang/Character;", "read", "buffer", "", "bufferOffset", "count", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public abstract class InternalJsonReaderCodePointImpl implements InternalJsonReader {
    private Character bufferedChar;

    public abstract boolean exhausted();

    public abstract int nextCodePoint();

    @Override // kotlinx.serialization.json.internal.InternalJsonReader
    public final int read(char[] buffer, int bufferOffset, int count) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int i = 0;
        if (this.bufferedChar != null) {
            Character ch = this.bufferedChar;
            Intrinsics.checkNotNull(ch);
            buffer[bufferOffset + 0] = ch.charValue();
            i = 0 + 1;
            this.bufferedChar = null;
        }
        while (i < count && !exhausted()) {
            int codePoint = nextCodePoint();
            if (codePoint <= 65535) {
                buffer[bufferOffset + i] = (char) codePoint;
                i++;
            } else {
                char upChar = (char) ((codePoint >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                char lowChar = (char) ((codePoint & 1023) + Utf8.LOG_SURROGATE_HEADER);
                buffer[bufferOffset + i] = upChar;
                i++;
                if (i < count) {
                    buffer[bufferOffset + i] = lowChar;
                    i++;
                } else {
                    this.bufferedChar = Character.valueOf(lowChar);
                }
            }
        }
        if (i > 0) {
            return i;
        }
        return -1;
    }
}
