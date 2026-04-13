package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: StringJsonLexer.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J3\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u0017H\u0016J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/json/internal/StringJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "source", "", "<init>", "(Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "prefetchOrEof", "", "position", "consumeNextToken", "", "canConsumeValue", "", "skipWhitespaces", "", "expected", "", "consumeKeyString", "consumeStringChunked", "isLenient", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "stringChunk", "peekLeadingMatchingValue", "keyToMatch", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public class StringJsonLexer extends JsonReader {
    private final String source;

    public StringJsonLexer(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.JsonReader
    public String getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int prefetchOrEof(int position) {
        if (position < getSource().length()) {
            return position;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public byte consumeNextToken() {
        String source = getSource();
        int cpos = this.currentPosition;
        while (cpos != -1 && cpos < source.length()) {
            int cpos2 = cpos + 1;
            char c = source.charAt(cpos);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                cpos = cpos2;
            } else {
                this.currentPosition = cpos2;
                return AbstractJsonLexerKt.charToTokenClass(c);
            }
        }
        int cpos3 = source.length();
        this.currentPosition = cpos3;
        return (byte) 10;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public boolean canConsumeValue() {
        int current = this.currentPosition;
        if (current == -1) {
            return false;
        }
        String source = getSource();
        while (current < source.length()) {
            char c = source.charAt(current);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                current++;
            } else {
                this.currentPosition = current;
                return isValidValueStart(c);
            }
        }
        this.currentPosition = current;
        return false;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int skipWhitespaces() {
        int current = this.currentPosition;
        if (current == -1) {
            return current;
        }
        String source = getSource();
        while (current < source.length()) {
            char c = source.charAt(current);
            if (!(c == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                break;
            }
            current++;
        }
        this.currentPosition = current;
        return current;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public void consumeNextToken(char expected) {
        if (this.currentPosition == -1) {
            unexpectedToken(expected);
        }
        String source = getSource();
        int cpos = this.currentPosition;
        while (cpos < source.length()) {
            int cpos2 = cpos + 1;
            char c = source.charAt(cpos);
            if (!(c == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                this.currentPosition = cpos2;
                if (c == expected) {
                    return;
                } else {
                    unexpectedToken(expected);
                }
            }
            cpos = cpos2;
        }
        this.currentPosition = -1;
        unexpectedToken(expected);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String consumeKeyString() {
        consumeNextToken('\"');
        int current = this.currentPosition;
        int closingQuote = StringsKt.indexOf$default((CharSequence) getSource(), '\"', current, false, 4, (Object) null);
        if (closingQuote == -1) {
            consumeStringLenient();
            fail$kotlinx_serialization_json((byte) 1, false);
            throw new KotlinNothingValueException();
        }
        for (int i = current; i < closingQuote; i++) {
            if (getSource().charAt(i) == '\\') {
                return consumeString(getSource(), this.currentPosition, i);
            }
        }
        int i2 = closingQuote + 1;
        this.currentPosition = i2;
        String substring = getSource().substring(current, closingQuote);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public void consumeStringChunked(boolean isLenient, Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        Iterable $this$forEach$iv = StringsKt.chunked(isLenient ? consumeStringLenient() : consumeString(), 16384);
        for (Object element$iv : $this$forEach$iv) {
            consumeChunk.invoke(element$iv);
        }
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String peekLeadingMatchingValue(String keyToMatch, boolean isLenient) {
        Intrinsics.checkNotNullParameter(keyToMatch, "keyToMatch");
        int positionSnapshot = this.currentPosition;
        try {
            if (consumeNextToken() != 6) {
                return null;
            }
            String firstKey = peekString(isLenient);
            if (!Intrinsics.areEqual(firstKey, keyToMatch)) {
                return null;
            }
            discardPeeked();
            if (consumeNextToken() != 5) {
                return null;
            }
            return peekString(isLenient);
        } finally {
            this.currentPosition = positionSnapshot;
            discardPeeked();
        }
    }
}
