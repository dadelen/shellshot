package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JsonStreams.kt */
@JsonFriendModuleApi
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/InternalJsonWriter;", "", "writeLong", "", "value", "", "writeChar", "char", "", "write", "text", "", "writeQuoted", "release", "Companion", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public interface InternalJsonWriter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void release();

    void write(String text);

    void writeChar(char r1);

    void writeLong(long value);

    void writeQuoted(String text);

    /* compiled from: JsonStreams.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Ja\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072K\u0010\b\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00050\tH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/InternalJsonWriter$Companion;", "", "<init>", "()V", "doWriteEscaping", "", "text", "", "writeImpl", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "startIndex", "endIndex", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final void doWriteEscaping(String text, Function3<? super String, ? super Integer, ? super Integer, Unit> writeImpl) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(writeImpl, "writeImpl");
            int lastPos = 0;
            int length = text.length();
            for (int i = 0; i < length; i++) {
                int c = text.charAt(i);
                if (c < StringOpsKt.getESCAPE_STRINGS().length && StringOpsKt.getESCAPE_STRINGS()[c] != null) {
                    writeImpl.invoke(text, Integer.valueOf(lastPos), Integer.valueOf(i));
                    String escape = StringOpsKt.getESCAPE_STRINGS()[c];
                    Intrinsics.checkNotNull(escape);
                    writeImpl.invoke(escape, 0, Integer.valueOf(escape.length()));
                    lastPos = i + 1;
                }
            }
            writeImpl.invoke(text, Integer.valueOf(lastPos), Integer.valueOf(text.length()));
        }
    }
}
