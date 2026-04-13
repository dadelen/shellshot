package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* compiled from: JsonTreeReader.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010\r\u001a\u00020\u000e*\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e0\u000fH\u0082@¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015H\u0082\bJ\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0006\u0010\u001a\u001a\u00020\u000eJ\b\u0010\u001b\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeReader;", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;Lkotlinx/serialization/json/internal/AbstractJsonLexer;)V", "isLenient", "", "trailingCommaAllowed", "stackDepth", "", "readObject", "Lkotlinx/serialization/json/JsonElement;", "Lkotlin/DeepRecursiveScope;", "", "(Lkotlin/DeepRecursiveScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readObjectImpl", "Lkotlinx/serialization/json/JsonObject;", "reader", "Lkotlin/Function0;", "readArray", "readValue", "Lkotlinx/serialization/json/JsonPrimitive;", "isString", "read", "readDeepRecursive", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class JsonTreeReader {
    private final boolean isLenient;
    private final JsonReader lexer;
    private int stackDepth;
    private final boolean trailingCommaAllowed;

    public JsonTreeReader(JsonConfiguration configuration, JsonReader lexer) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        this.lexer = lexer;
        this.isLenient = configuration.getIsLenient();
        this.trailingCommaAllowed = configuration.getAllowTrailingComma();
    }

    private final JsonElement readObject() {
        byte lastToken$iv = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() == 4) {
            JsonReader.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        LinkedHashMap result$iv = new LinkedHashMap();
        while (true) {
            if (!this.lexer.canConsumeValue()) {
                break;
            }
            boolean z = this.isLenient;
            JsonReader jsonReader = this.lexer;
            String key$iv = z ? jsonReader.consumeStringLenient() : jsonReader.consumeString();
            this.lexer.consumeNextToken((byte) 5);
            JsonElement element$iv = read();
            result$iv.put(key$iv, element$iv);
            lastToken$iv = this.lexer.consumeNextToken();
            if (lastToken$iv != 4) {
                if (lastToken$iv != 7) {
                    JsonReader.fail$default(this.lexer, "Expected end of the object or comma", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (lastToken$iv == 6) {
            this.lexer.consumeNextToken((byte) 7);
        } else if (lastToken$iv == 4) {
            boolean z2 = this.trailingCommaAllowed;
            JsonReader jsonReader2 = this.lexer;
            if (!z2) {
                JsonExceptionsKt.invalidTrailingComma$default(jsonReader2, null, 1, null);
                throw new KotlinNothingValueException();
            }
            jsonReader2.consumeNextToken((byte) 7);
        }
        return new JsonObject(result$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00a3 -> B:12:0x00ac). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readObject(kotlin.DeepRecursiveScope<kotlin.Unit, kotlinx.serialization.json.JsonElement> r23, kotlin.coroutines.Continuation<? super kotlinx.serialization.json.JsonElement> r24) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonTreeReader.readObject(kotlin.DeepRecursiveScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final JsonObject readObjectImpl(Function0<? extends JsonElement> reader) {
        byte lastToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() == 4) {
            JsonReader.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        LinkedHashMap result = new LinkedHashMap();
        while (true) {
            if (!this.lexer.canConsumeValue()) {
                break;
            }
            boolean z = this.isLenient;
            JsonReader jsonReader = this.lexer;
            String key = z ? jsonReader.consumeStringLenient() : jsonReader.consumeString();
            this.lexer.consumeNextToken((byte) 5);
            JsonElement element = reader.invoke();
            result.put(key, element);
            lastToken = this.lexer.consumeNextToken();
            if (lastToken != 4) {
                if (lastToken != 7) {
                    JsonReader.fail$default(this.lexer, "Expected end of the object or comma", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (lastToken == 6) {
            this.lexer.consumeNextToken((byte) 7);
        } else if (lastToken == 4) {
            boolean z2 = this.trailingCommaAllowed;
            JsonReader jsonReader2 = this.lexer;
            if (!z2) {
                JsonExceptionsKt.invalidTrailingComma$default(jsonReader2, null, 1, null);
                throw new KotlinNothingValueException();
            }
            jsonReader2.consumeNextToken((byte) 7);
        }
        return new JsonObject(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonElement readArray() {
        byte lastToken = this.lexer.consumeNextToken();
        if (this.lexer.peekNextToken() == 4) {
            JsonReader.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        ArrayList result = new ArrayList();
        while (this.lexer.canConsumeValue()) {
            JsonElement element = read();
            result.add(element);
            lastToken = this.lexer.consumeNextToken();
            if (lastToken != 4) {
                JsonReader $this$iv = this.lexer;
                boolean condition$iv = lastToken == 9;
                int position$iv = $this$iv.currentPosition;
                if (!condition$iv) {
                    JsonReader.fail$default($this$iv, "Expected end of the array or comma", position$iv, null, 4, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (lastToken == 8) {
            this.lexer.consumeNextToken((byte) 9);
        } else if (lastToken == 4) {
            boolean z = this.trailingCommaAllowed;
            JsonReader jsonReader = this.lexer;
            if (!z) {
                JsonExceptionsKt.invalidTrailingComma(jsonReader, "array");
                throw new KotlinNothingValueException();
            }
            jsonReader.consumeNextToken((byte) 9);
        }
        return new JsonArray(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonPrimitive readValue(boolean isString) {
        String consumeStringLenient;
        if (this.isLenient || !isString) {
            consumeStringLenient = this.lexer.consumeStringLenient();
        } else {
            consumeStringLenient = this.lexer.consumeString();
        }
        String string = consumeStringLenient;
        return (isString || !Intrinsics.areEqual(string, AbstractJsonLexerKt.NULL)) ? new JsonLiteral(string, isString, null, 4, null) : JsonNull.INSTANCE;
    }

    public final JsonElement read() {
        JsonElement readObject;
        byte token = this.lexer.peekNextToken();
        if (token == 1) {
            return readValue(true);
        }
        if (token == 0) {
            return readValue(false);
        }
        if (token == 6) {
            this.stackDepth++;
            if (this.stackDepth == 200) {
                readObject = readDeepRecursive();
            } else {
                readObject = readObject();
            }
            this.stackDepth--;
            return readObject;
        }
        if (token == 8) {
            return readArray();
        }
        JsonReader.fail$default(this.lexer, "Cannot read Json element because of unexpected " + AbstractJsonLexerKt.tokenDescription(token), 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final JsonElement readDeepRecursive() {
        return (JsonElement) DeepRecursiveKt.invoke(new DeepRecursiveFunction(new JsonTreeReader$readDeepRecursive$1(this, null)), Unit.INSTANCE);
    }
}
