package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import androidx.core.location.LocationRequestCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AbstractJsonLexer.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\n\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\f\u001a\u00020\r*\u00020\u000eH\u0084\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\rJ\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH&J\b\u0010\u0014\u001a\u00020\rH&J\b\u0010\u0015\u001a\u00020\u0016H&J\u0006\u0010\u0017\u001a\u00020\rJ\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u000eH\u0004J\u0006\u0010\u001a\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u000eH&J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u000eH\u0004J\u001f\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00162\b\b\u0002\u0010)\u001a\u00020\rH\u0000¢\u0006\u0002\b*J\b\u0010+\u001a\u00020\u0016H\u0016J\u0010\u0010,\u001a\u00020\r2\b\b\u0002\u0010-\u001a\u00020\rJ\b\u0010.\u001a\u00020\tH&J\u001a\u0010/\u001a\u0004\u0018\u00010\u001c2\u0006\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\rH&J\u0010\u00102\u001a\u0004\u0018\u00010\u001c2\u0006\u00101\u001a\u00020\rJ\u0006\u00103\u001a\u00020\u0010J\u0018\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\tH\u0016J\u0018\u00107\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0016J\b\u00109\u001a\u00020\u001cH&J\u0018\u0010:\u001a\u00020\r2\u0006\u00101\u001a\u00020\r2\u0006\u00105\u001a\u00020\u000eH\u0002J3\u0010;\u001a\u00020\u00102\u0006\u00101\u001a\u00020\r2!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020\u00100=H\u0016JC\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\r2!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020\u00100=H\u0002J\u0006\u0010E\u001a\u00020\u001cJ \u0010E\u001a\u00020\u001c2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\tH\u0005J\u0018\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\t2\u0006\u0010G\u001a\u00020\tH\u0002J\u0018\u0010J\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010K\u001a\u00020\u001cH\u0002J\u0006\u0010L\u001a\u00020\u001cJ\b\u0010M\u001a\u00020\rH\u0002J\u0006\u0010N\u001a\u00020\u001cJ\u0018\u0010O\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\tH\u0014J\u0010\u0010P\u001a\u00020\t2\u0006\u0010F\u001a\u00020\tH\u0002J\u0018\u0010Q\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00106\u001a\u00020\tH\u0002J1\u0010R\u001a\u00020\u00102\u0006\u0010S\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\t2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u001c0UH\u0080\bø\u0001\u0000¢\u0006\u0002\bVJ\u0018\u0010W\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\rJ\b\u0010Z\u001a\u00020\u001cH\u0016J\u000e\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u001cJ\"\u0010&\u001a\u00020'2\u0006\u0010T\u001a\u00020\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010]\u001a\u00020\u001cJ\u0006\u0010^\u001a\u00020_J\u0006\u0010`\u001a\u00020\rJ\u0006\u0010a\u001a\u00020\rJ\u0010\u0010`\u001a\u00020\r2\u0006\u0010b\u001a\u00020\tH\u0003J\u0018\u0010c\u001a\u00020\u00102\u0006\u0010d\u001a\u00020\u001c2\u0006\u0010G\u001a\u00020\tH\u0002J\"\u0010e\u001a\u0002Hf\"\u0004\b\u0000\u0010f2\f\u0010g\u001a\b\u0012\u0004\u0012\u0002Hf0UH\u0082\b¢\u0006\u0002\u0010hR\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006i"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "", "<init>", "()V", "source", "", "getSource", "()Ljava/lang/CharSequence;", "currentPosition", "", "path", "Lkotlinx/serialization/json/internal/JsonPath;", "isWs", "", "", "ensureHaveChars", "", "isNotEof", "prefetchOrEof", "position", "canConsumeValue", "consumeNextToken", "", "tryConsumeComma", "isValidValueStart", "c", "expectEof", "peekedString", "", "escapedString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "expected", "unexpectedToken", "fail", "", "expectedToken", "wasConsumed", "fail$kotlinx_serialization_json", "peekNextToken", "tryConsumeNull", "doConsume", "skipWhitespaces", "peekLeadingMatchingValue", "keyToMatch", "isLenient", "peekString", "discardPeeked", "indexOf", "char", "startPos", "substring", "endPos", "consumeKeyString", "insideString", "consumeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "stringChunk", "writeRange", "fromIndex", "toIndex", "currentChunkHasEscape", "consumeString", "startPosition", "current", "appendEscape", "lastPosition", "decodedString", "takePeeked", "consumeStringLenientNotNull", "wasUnquotedString", "consumeStringLenient", "appendRange", "appendEsc", "appendHex", "require", "condition", "message", "Lkotlin/Function0;", "require$kotlinx_serialization_json", "fromHexChar", "skipElement", "allowLenientStrings", "toString", "failOnUnknownKey", "key", "hint", "consumeNumericLiteral", "", "consumeBoolean", "consumeBooleanLenient", "start", "consumeBooleanLiteral", "literalSuffix", "withPositionRollback", "T", "action", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* renamed from: kotlinx.serialization.json.internal.AbstractJsonLexer, reason: from toString */
/* loaded from: classes14.dex */
public abstract class JsonReader {
    public int currentPosition;
    private String peekedString;
    public final JsonPath path = new JsonPath();
    private StringBuilder escapedString = new StringBuilder();

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public abstract void consumeNextToken(char expected);

    protected abstract CharSequence getSource();

    public abstract String peekLeadingMatchingValue(String keyToMatch, boolean isLenient);

    public abstract int prefetchOrEof(int position);

    public abstract int skipWhitespaces();

    protected final boolean isWs(char $this$isWs) {
        return $this$isWs == ' ' || $this$isWs == '\n' || $this$isWs == '\r' || $this$isWs == '\t';
    }

    public void ensureHaveChars() {
    }

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final boolean tryConsumeComma() {
        int current = skipWhitespaces();
        CharSequence source = getSource();
        if (current >= source.length() || current == -1 || source.charAt(current) != ',') {
            return false;
        }
        this.currentPosition++;
        return true;
    }

    protected final boolean isValidValueStart(char c) {
        switch (c) {
            case ',':
            case ':':
            case ']':
            case '}':
                return false;
            default:
                return true;
        }
    }

    public final void expectEof() {
        byte nextToken = consumeNextToken();
        if (nextToken != 10) {
            fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    protected final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    protected final void setEscapedString(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    public final byte consumeNextToken(byte expected) {
        byte token = consumeNextToken();
        if (token != expected) {
            fail$kotlinx_serialization_json$default(this, expected, false, 2, null);
            throw new KotlinNothingValueException();
        }
        return token;
    }

    protected final void unexpectedToken(char expected) {
        if (this.currentPosition > 0 && expected == '\"') {
            int snapshot$iv = this.currentPosition;
            try {
                this.currentPosition--;
                String inputLiteral = consumeStringLenient();
                this.currentPosition = snapshot$iv;
                if (Intrinsics.areEqual(inputLiteral, AbstractJsonLexerKt.NULL)) {
                    fail("Expected string literal but 'null' literal was found", this.currentPosition - 1, AbstractJsonLexerKt.coerceInputValuesHint);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th) {
                this.currentPosition = snapshot$iv;
                throw th;
            }
        }
        fail$kotlinx_serialization_json$default(this, AbstractJsonLexerKt.charToTokenClass(expected), false, 2, null);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$kotlinx_serialization_json$default(JsonReader jsonReader, byte b, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return jsonReader.fail$kotlinx_serialization_json(b, z);
    }

    public final Void fail$kotlinx_serialization_json(byte expectedToken, boolean wasConsumed) {
        String expected = AbstractJsonLexerKt.tokenDescription(expectedToken);
        int i = this.currentPosition;
        if (wasConsumed) {
            i--;
        }
        int position = i;
        String s = (this.currentPosition == getSource().length() || position < 0) ? "EOF" : String.valueOf(getSource().charAt(position));
        fail$default(this, "Expected " + expected + ", but had '" + s + "' instead", position, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public byte peekNextToken() {
        CharSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                char ch = source.charAt(cpos2);
                switch (ch) {
                    case '\t':
                    case '\n':
                    case '\r':
                    case ' ':
                        cpos = cpos2 + 1;
                    default:
                        this.currentPosition = cpos2;
                        return AbstractJsonLexerKt.charToTokenClass(ch);
                }
            } else {
                this.currentPosition = cpos2;
                return (byte) 10;
            }
        }
    }

    public static /* synthetic */ boolean tryConsumeNull$default(JsonReader jsonReader, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryConsumeNull");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return jsonReader.tryConsumeNull(z);
    }

    public final boolean tryConsumeNull(boolean doConsume) {
        int current = prefetchOrEof(skipWhitespaces());
        int len = getSource().length() - current;
        if (len < 4 || current == -1) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (AbstractJsonLexerKt.NULL.charAt(i) != getSource().charAt(current + i)) {
                return false;
            }
        }
        if (len > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current + 4)) == 0) {
            return false;
        }
        if (doConsume) {
            this.currentPosition = current + 4;
            return true;
        }
        return true;
    }

    public final String peekString(boolean isLenient) {
        String string;
        byte token = peekNextToken();
        if (isLenient) {
            if (token != 1 && token != 0) {
                return null;
            }
            string = consumeStringLenient();
        } else {
            if (token != 1) {
                return null;
            }
            string = consumeString();
        }
        this.peekedString = string;
        return string;
    }

    public final void discardPeeked() {
        this.peekedString = null;
    }

    public int indexOf(char r7, int startPos) {
        return StringsKt.indexOf$default(getSource(), r7, startPos, false, 4, (Object) null);
    }

    public String substring(int startPos, int endPos) {
        return getSource().subSequence(startPos, endPos).toString();
    }

    private final boolean insideString(boolean isLenient, char r5) {
        return isLenient ? AbstractJsonLexerKt.charToTokenClass(r5) == 0 : r5 != '\"';
    }

    public void consumeStringChunked(boolean isLenient, Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        byte nextToken = peekNextToken();
        if (!isLenient || nextToken == 0) {
            if (!isLenient) {
                consumeNextToken('\"');
            }
            int currentPosition = this.currentPosition;
            int lastPosition = currentPosition;
            char charAt = getSource().charAt(currentPosition);
            boolean usedAppend = false;
            while (insideString(isLenient, charAt)) {
                if (!isLenient && charAt == '\\') {
                    usedAppend = true;
                    currentPosition = prefetchOrEof(appendEscape(lastPosition, currentPosition));
                    lastPosition = currentPosition;
                } else {
                    currentPosition++;
                }
                if (currentPosition >= getSource().length()) {
                    writeRange(lastPosition, currentPosition, usedAppend, consumeChunk);
                    usedAppend = false;
                    int currentPosition2 = prefetchOrEof(currentPosition);
                    if (currentPosition2 == -1) {
                        fail$default(this, "EOF", currentPosition2, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                    currentPosition = currentPosition2;
                    lastPosition = currentPosition;
                }
                charAt = getSource().charAt(currentPosition);
            }
            writeRange(lastPosition, currentPosition, usedAppend, consumeChunk);
            this.currentPosition = currentPosition;
            if (!isLenient) {
                consumeNextToken('\"');
            }
        }
    }

    private final void writeRange(int fromIndex, int toIndex, boolean currentChunkHasEscape, Function1<? super String, Unit> consumeChunk) {
        if (currentChunkHasEscape) {
            consumeChunk.invoke(decodedString(fromIndex, toIndex));
        } else {
            consumeChunk.invoke(substring(fromIndex, toIndex));
        }
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    protected final String consumeString(CharSequence source, int startPosition, int current) {
        String string;
        Intrinsics.checkNotNullParameter(source, "source");
        int currentPosition = current;
        boolean usedAppend = false;
        int lastPosition = startPosition;
        char charAt = source.charAt(currentPosition);
        while (charAt != '\"') {
            if (charAt == '\\') {
                int currentPosition2 = prefetchOrEof(appendEscape(lastPosition, currentPosition));
                if (currentPosition2 == -1) {
                    fail$default(this, "Unexpected EOF", currentPosition2, null, 4, null);
                    throw new KotlinNothingValueException();
                }
                currentPosition = currentPosition2;
                lastPosition = currentPosition;
                usedAppend = true;
            } else {
                currentPosition++;
                if (currentPosition >= source.length()) {
                    appendRange(lastPosition, currentPosition);
                    int currentPosition3 = prefetchOrEof(currentPosition);
                    if (currentPosition3 == -1) {
                        fail$default(this, "Unexpected EOF", currentPosition3, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                    currentPosition = currentPosition3;
                    lastPosition = currentPosition;
                    usedAppend = true;
                } else {
                    continue;
                }
            }
            charAt = source.charAt(currentPosition);
        }
        if (!usedAppend) {
            string = substring(lastPosition, currentPosition);
        } else {
            string = decodedString(lastPosition, currentPosition);
        }
        this.currentPosition = currentPosition + 1;
        return string;
    }

    private final int appendEscape(int lastPosition, int current) {
        appendRange(lastPosition, current);
        return appendEsc(current + 1);
    }

    private final String decodedString(int lastPosition, int currentPosition) {
        appendRange(lastPosition, currentPosition);
        String result = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(result, "toString(...)");
        this.escapedString.setLength(0);
        return result;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String result = consumeStringLenient();
        if (Intrinsics.areEqual(result, AbstractJsonLexerKt.NULL) && wasUnquotedString()) {
            fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return result;
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String result;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int current = skipWhitespaces();
        if (current >= getSource().length() || current == -1) {
            fail$default(this, "EOF", current, null, 4, null);
            throw new KotlinNothingValueException();
        }
        byte token = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current));
        if (token == 1) {
            return consumeString();
        }
        if (token != 0) {
            fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(current), 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        boolean usedAppend = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current)) == 0) {
            current++;
            if (current >= getSource().length()) {
                usedAppend = true;
                appendRange(this.currentPosition, current);
                int eof = prefetchOrEof(current);
                if (eof == -1) {
                    this.currentPosition = current;
                    return decodedString(0, 0);
                }
                current = eof;
            }
        }
        int i = this.currentPosition;
        if (!usedAppend) {
            result = substring(i, current);
        } else {
            result = decodedString(i, current);
        }
        this.currentPosition = current;
        return result;
    }

    protected void appendRange(int fromIndex, int toIndex) {
        this.escapedString.append(getSource(), fromIndex, toIndex);
    }

    private final int appendEsc(int startPosition) {
        int currentPosition = prefetchOrEof(startPosition);
        if (currentPosition == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int currentPosition2 = currentPosition + 1;
        char currentChar = getSource().charAt(currentPosition);
        if (currentChar == 'u') {
            return appendHex(getSource(), currentPosition2);
        }
        char c = AbstractJsonLexerKt.escapeToChar(currentChar);
        if (c == 0) {
            fail$default(this, "Invalid escaped char '" + currentChar + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append(c);
        return currentPosition2;
    }

    private final int appendHex(CharSequence source, int startPos) {
        if (startPos + 4 < source.length()) {
            this.escapedString.append((char) ((fromHexChar(source, startPos) << 12) + (fromHexChar(source, startPos + 1) << 8) + (fromHexChar(source, startPos + 2) << 4) + fromHexChar(source, startPos + 3)));
            return startPos + 4;
        }
        this.currentPosition = startPos;
        ensureHaveChars();
        if (this.currentPosition + 4 < source.length()) {
            return appendHex(source, this.currentPosition);
        }
        fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(JsonReader $this, boolean condition, int position, Function0 message, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i & 2) != 0) {
            position = $this.currentPosition;
        }
        int position2 = position;
        Intrinsics.checkNotNullParameter(message, "message");
        if (!condition) {
            fail$default($this, (String) message.invoke(), position2, null, 4, null);
            throw new KotlinNothingValueException();
        }
    }

    public final void require$kotlinx_serialization_json(boolean condition, int position, Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (!condition) {
            fail$default(this, message.invoke(), position, null, 4, null);
            throw new KotlinNothingValueException();
        }
    }

    private final int fromHexChar(CharSequence source, int currentPosition) {
        char character = source.charAt(currentPosition);
        if ('0' <= character && character < ':') {
            return character - '0';
        }
        if ('a' <= character && character < 'g') {
            return (character - 'a') + 10;
        }
        if ('A' <= character && character < 'G') {
            return (character - 'A') + 10;
        }
        fail$default(this, "Invalid toHexChar char '" + character + "' in unicode escape", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean allowLenientStrings) {
        List tokenStack = new ArrayList();
        byte lastToken = peekNextToken();
        if (lastToken != 8 && lastToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte lastToken2 = peekNextToken();
            if (lastToken2 == 1) {
                if (allowLenientStrings) {
                    consumeStringLenient();
                } else {
                    consumeKeyString();
                }
            } else {
                if (lastToken2 == 8 || lastToken2 == 6) {
                    tokenStack.add(Byte.valueOf(lastToken2));
                } else if (lastToken2 == 9) {
                    if (((Number) CollectionsKt.last(tokenStack)).byteValue() != 8) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of } at path: " + this.path, getSource());
                    }
                    CollectionsKt.removeLast(tokenStack);
                } else if (lastToken2 == 7) {
                    if (((Number) CollectionsKt.last(tokenStack)).byteValue() != 6) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ] at path: " + this.path, getSource());
                    }
                    CollectionsKt.removeLast(tokenStack);
                } else if (lastToken2 == 10) {
                    fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                    throw new KotlinNothingValueException();
                }
                consumeNextToken();
                if (tokenStack.size() == 0) {
                    return;
                }
            }
        }
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + ')';
    }

    public final void failOnUnknownKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        String processed = substring(0, this.currentPosition);
        int lastIndexOf = StringsKt.lastIndexOf$default((CharSequence) processed, key, 0, false, 6, (Object) null);
        fail("Encountered an unknown key '" + key + '\'', lastIndexOf, AbstractJsonLexerKt.ignoreUnknownKeysHint);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(JsonReader jsonReader, String str, int i, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = jsonReader.currentPosition;
        }
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        return jsonReader.fail(str, i, str2);
    }

    public final Void fail(String message, int position, String hint) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(hint, "hint");
        String hintMessage = hint.length() == 0 ? "" : '\n' + hint;
        throw JsonExceptionsKt.JsonDecodingException(position, message + " at path: " + this.path.getPath() + hintMessage, getSource());
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0244, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0174, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0170, code lost:
    
        if (r10 == r12) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0172, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0175, code lost:
    
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0176, code lost:
    
        if (r12 == r10) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0178, code lost:
    
        if (r16 == 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x017c, code lost:
    
        if (r12 == (r10 - 1)) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x017e, code lost:
    
        if (r7 == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0180, code lost:
    
        if (r6 == false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x018a, code lost:
    
        if (getSource().charAt(r10) != '\"') goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x018c, code lost:
    
        r10 = r10 + 1;
        r0 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01b5, code lost:
    
        r0.currentPosition = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01b7, code lost:
    
        if (r15 == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01b9, code lost:
    
        r2 = r8 * consumeNumericLiteral$calculateExponent(r13, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01c4, code lost:
    
        if (r2 > 9.223372036854776E18d) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ca, code lost:
    
        if (r2 < (-9.223372036854776E18d)) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01d2, code lost:
    
        if (java.lang.Math.floor(r2) != r2) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01d4, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01d7, code lost:
    
        if (r1 == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01d9, code lost:
    
        r8 = (long) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01db, code lost:
    
        fail$default(r0, "Can't convert " + r2 + " to Long", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0202, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d6, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0203, code lost:
    
        fail$default(r20, "Numeric value overflow", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0215, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0217, code lost:
    
        if (r16 == 0) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:?, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x021f, code lost:
    
        if (r8 == Long.MIN_VALUE) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0222, code lost:
    
        return -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0223, code lost:
    
        fail$default(r20, "Numeric value overflow", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0233, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0191, code lost:
    
        fail$default(r20, "Expected closing quotation mark", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01a1, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01a2, code lost:
    
        fail$default(r20, "EOF", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01b2, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b3, code lost:
    
        r0 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0234, code lost:
    
        fail$default(r20, "Expected numeric literal", 0, null, 6, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long consumeNumericLiteral() {
        /*
            Method dump skipped, instructions count: 598
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonReader.consumeNumericLiteral():long");
    }

    private static final double consumeNumericLiteral$calculateExponent(long exponentAccumulator, boolean isExponentPositive) {
        if (!isExponentPositive) {
            return Math.pow(10.0d, -exponentAccumulator);
        }
        if (!isExponentPositive) {
            throw new NoWhenBranchMatchedException();
        }
        return Math.pow(10.0d, exponentAccumulator);
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int current = skipWhitespaces();
        if (current == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(current) == '\"') {
            current++;
            z = true;
        } else {
            z = false;
        }
        boolean hasQuotation = z;
        boolean result = consumeBoolean(current);
        if (hasQuotation) {
            if (this.currentPosition == getSource().length()) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(this.currentPosition) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            this.currentPosition++;
        }
        return result;
    }

    private final boolean consumeBoolean(int start) {
        int current = prefetchOrEof(start);
        if (current >= getSource().length() || current == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int current2 = current + 1;
        switch (getSource().charAt(current) | ' ') {
            case LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY /* 102 */:
                consumeBooleanLiteral("alse", current2);
                return false;
            case 116:
                consumeBooleanLiteral("rue", current2);
                return true;
            default:
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
                throw new KotlinNothingValueException();
        }
    }

    private final void consumeBooleanLiteral(String literalSuffix, int current) {
        if (getSource().length() - current < literalSuffix.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int length = literalSuffix.length();
        for (int i = 0; i < length; i++) {
            char expected = literalSuffix.charAt(i);
            char actual = getSource().charAt(current + i);
            if (expected != (actual | ' ')) {
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
        }
        int i2 = literalSuffix.length();
        this.currentPosition = current + i2;
    }

    private final <T> T withPositionRollback(Function0<? extends T> action) {
        int snapshot = this.currentPosition;
        try {
            return action.invoke();
        } finally {
            this.currentPosition = snapshot;
        }
    }
}
