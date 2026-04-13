package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CommentLexers.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0006\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/internal/StringJsonLexerWithComments;", "Lkotlinx/serialization/json/internal/StringJsonLexer;", "source", "", "<init>", "(Ljava/lang/String;)V", "consumeNextToken", "", "canConsumeValue", "", "", "expected", "", "peekNextToken", "skipWhitespaces", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class StringJsonLexerWithComments extends StringJsonLexer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringJsonLexerWithComments(String source) {
        super(source);
        Intrinsics.checkNotNullParameter(source, "source");
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public byte consumeNextToken() {
        String source = getSource();
        int cpos = skipWhitespaces();
        if (cpos >= source.length() || cpos == -1) {
            return (byte) 10;
        }
        this.currentPosition = cpos + 1;
        return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public boolean canConsumeValue() {
        int current = skipWhitespaces();
        if (current >= getSource().length() || current == -1) {
            return false;
        }
        return isValidValueStart(getSource().charAt(current));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public void consumeNextToken(char expected) {
        String source = getSource();
        int current = skipWhitespaces();
        if (current >= source.length() || current == -1) {
            this.currentPosition = -1;
            unexpectedToken(expected);
        }
        char c = source.charAt(current);
        this.currentPosition = current + 1;
        if (c == expected) {
            return;
        }
        unexpectedToken(expected);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public byte peekNextToken() {
        String source = getSource();
        int cpos = skipWhitespaces();
        if (cpos >= source.length() || cpos == -1) {
            return (byte) 10;
        }
        this.currentPosition = cpos;
        return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public int skipWhitespaces() {
        int current = this.currentPosition;
        if (current == -1) {
            return current;
        }
        String source = getSource();
        while (current < source.length()) {
            char c = source.charAt(current);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                current++;
            } else {
                if (c == '/' && current + 1 < source.length()) {
                    switch (source.charAt(current + 1)) {
                        case '*':
                            int current2 = StringsKt.indexOf$default((CharSequence) source, "*/", current + 2, false, 4, (Object) null);
                            if (current2 == -1) {
                                this.currentPosition = source.length();
                                JsonReader.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
                                throw new KotlinNothingValueException();
                            }
                            current = current2 + 2;
                            continue;
                        case '/':
                            int current3 = StringsKt.indexOf$default((CharSequence) source, '\n', current + 2, false, 4, (Object) null);
                            if (current3 == -1) {
                                current = source.length();
                                break;
                            } else {
                                current = current3 + 1;
                                continue;
                            }
                    }
                }
                this.currentPosition = current;
                return current;
            }
        }
        this.currentPosition = current;
        return current;
    }
}
