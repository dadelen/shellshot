package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.ExperimentalSerializationApi;

/* compiled from: Json.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/serialization/json/DecodeSequenceMode;", "", "<init>", "(Ljava/lang/String;I)V", "WHITESPACE_SEPARATED", "ARRAY_WRAPPED", "AUTO_DETECT", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
@ExperimentalSerializationApi
/* loaded from: classes14.dex */
public enum DecodeSequenceMode {
    WHITESPACE_SEPARATED,
    ARRAY_WRAPPED,
    AUTO_DETECT;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<DecodeSequenceMode> getEntries() {
        return $ENTRIES;
    }
}
