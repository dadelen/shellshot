package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonNamingStrategy;

/* compiled from: JsonNamingStrategy.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bç\u0080\u0001\u0018\u0000 \t2\u00020\u0001:\u0001\tJ \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lkotlinx/serialization/json/JsonNamingStrategy;", "", "serialNameForJson", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "elementIndex", "", "serialName", "Builtins", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
@ExperimentalSerializationApi
/* loaded from: classes14.dex */
public interface JsonNamingStrategy {

    /* renamed from: Builtins, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    String serialNameForJson(SerialDescriptor descriptor, int elementIndex, String serialName);

    /* compiled from: JsonNamingStrategy.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/json/JsonNamingStrategy$Builtins;", "", "<init>", "()V", "SnakeCase", "Lkotlinx/serialization/json/JsonNamingStrategy;", "getSnakeCase$annotations", "getSnakeCase", "()Lkotlinx/serialization/json/JsonNamingStrategy;", "KebabCase", "getKebabCase$annotations", "getKebabCase", "convertCamelCase", "", "serialName", "delimiter", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @ExperimentalSerializationApi
    /* renamed from: kotlinx.serialization.json.JsonNamingStrategy$Builtins, reason: from kotlin metadata */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final JsonNamingStrategy SnakeCase = new JsonNamingStrategy() { // from class: kotlinx.serialization.json.JsonNamingStrategy$Builtins$SnakeCase$1
            @Override // kotlinx.serialization.json.JsonNamingStrategy
            public String serialNameForJson(SerialDescriptor descriptor, int elementIndex, String serialName) {
                String convertCamelCase;
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                Intrinsics.checkNotNullParameter(serialName, "serialName");
                convertCamelCase = JsonNamingStrategy.Companion.$$INSTANCE.convertCamelCase(serialName, '_');
                return convertCamelCase;
            }

            public String toString() {
                return "kotlinx.serialization.json.JsonNamingStrategy.SnakeCase";
            }
        };
        private static final JsonNamingStrategy KebabCase = new JsonNamingStrategy() { // from class: kotlinx.serialization.json.JsonNamingStrategy$Builtins$KebabCase$1
            @Override // kotlinx.serialization.json.JsonNamingStrategy
            public String serialNameForJson(SerialDescriptor descriptor, int elementIndex, String serialName) {
                String convertCamelCase;
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                Intrinsics.checkNotNullParameter(serialName, "serialName");
                convertCamelCase = JsonNamingStrategy.Companion.$$INSTANCE.convertCamelCase(serialName, '-');
                return convertCamelCase;
            }

            public String toString() {
                return "kotlinx.serialization.json.JsonNamingStrategy.KebabCase";
            }
        };

        @ExperimentalSerializationApi
        public static /* synthetic */ void getKebabCase$annotations() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getSnakeCase$annotations() {
        }

        private Companion() {
        }

        public final JsonNamingStrategy getSnakeCase() {
            return SnakeCase;
        }

        public final JsonNamingStrategy getKebabCase() {
            return KebabCase;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String convertCamelCase(String serialName, char delimiter) {
            StringBuilder $this$convertCamelCase_u24lambda_u241 = new StringBuilder(serialName.length() * 2);
            Character ch = null;
            int previousUpperCharsCount = 0;
            String $this$forEach$iv = serialName;
            for (int i = 0; i < $this$forEach$iv.length(); i++) {
                char element$iv = $this$forEach$iv.charAt(i);
                if (Character.isUpperCase(element$iv)) {
                    if (previousUpperCharsCount == 0) {
                        if (($this$convertCamelCase_u24lambda_u241.length() > 0) && StringsKt.last($this$convertCamelCase_u24lambda_u241) != delimiter) {
                            $this$convertCamelCase_u24lambda_u241.append(delimiter);
                        }
                    }
                    if (ch != null) {
                        char p0 = ch.charValue();
                        $this$convertCamelCase_u24lambda_u241.append(p0);
                    }
                    previousUpperCharsCount++;
                    ch = Character.valueOf(Character.toLowerCase(element$iv));
                } else {
                    if (ch != null) {
                        if (previousUpperCharsCount > 1 && Character.isLetter(element$iv)) {
                            $this$convertCamelCase_u24lambda_u241.append(delimiter);
                        }
                        $this$convertCamelCase_u24lambda_u241.append(ch.charValue());
                        previousUpperCharsCount = 0;
                        ch = null;
                    }
                    $this$convertCamelCase_u24lambda_u241.append(element$iv);
                }
            }
            if (ch != null) {
                $this$convertCamelCase_u24lambda_u241.append(ch.charValue());
            }
            String sb = $this$convertCamelCase_u24lambda_u241.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return sb;
        }
    }
}
