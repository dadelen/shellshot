package com.example.shellshot.data;

import androidx.compose.material3.internal.TextFieldImplKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/shellshot/data/OutputNamingStrategy;", "", "<init>", "(Ljava/lang/String;I)V", TextFieldImplKt.PrefixId, TextFieldImplKt.SuffixId, "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public enum OutputNamingStrategy {
    Prefix,
    Suffix;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<OutputNamingStrategy> getEntries() {
        return $ENTRIES;
    }

    /* compiled from: AppPrefs.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/example/shellshot/data/OutputNamingStrategy$Companion;", "", "<init>", "()V", "fromRaw", "Lcom/example/shellshot/data/OutputNamingStrategy;", "value", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OutputNamingStrategy fromRaw(String value) {
            Object obj;
            Iterator it = OutputNamingStrategy.getEntries().iterator();
            while (true) {
                if (it.hasNext()) {
                    obj = it.next();
                    if (StringsKt.equals(((OutputNamingStrategy) obj).name(), value, true)) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            OutputNamingStrategy outputNamingStrategy = (OutputNamingStrategy) obj;
            return outputNamingStrategy == null ? OutputNamingStrategy.Prefix : outputNamingStrategy;
        }
    }
}
