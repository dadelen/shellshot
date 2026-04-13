package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReaderJsonLexer.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\b\u0010\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\rH\u0016J\u0018\u0010$\u001a\u00020 2\u0006\u0010#\u001a\u00020\r2\u0006\u0010%\u001a\u00020\rH\u0016J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\rH\u0014J\u001a\u0010)\u001a\u0004\u0018\u00010 2\u0006\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0013H\u0016J\u0006\u0010,\u001a\u00020\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\r8\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006-"}, d2 = {"Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "reader", "Lkotlinx/serialization/json/internal/InternalJsonReader;", "buffer", "", "<init>", "(Lkotlinx/serialization/json/internal/InternalJsonReader;[C)V", "getReader", "()Lkotlinx/serialization/json/internal/InternalJsonReader;", "getBuffer", "()[C", "threshold", "", "source", "Lkotlinx/serialization/json/internal/ArrayAsSequence;", "getSource", "()Lkotlinx/serialization/json/internal/ArrayAsSequence;", "canConsumeValue", "", "preload", "", "unprocessedCount", "prefetchOrEof", "position", "consumeNextToken", "", "expected", "", "skipWhitespaces", "ensureHaveChars", "consumeKeyString", "", "indexOf", "char", "startPos", "substring", "endPos", "appendRange", "fromIndex", "toIndex", "peekLeadingMatchingValue", "keyToMatch", "isLenient", "release", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public class ReaderJsonLexer extends JsonReader {
    private final char[] buffer;
    private final InternalJsonReader reader;
    private final ArrayAsSequence source;
    protected int threshold;

    public /* synthetic */ ReaderJsonLexer(InternalJsonReader internalJsonReader, char[] cArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(internalJsonReader, (i & 2) != 0 ? CharArrayPoolBatchSize.INSTANCE.take() : cArr);
    }

    public final InternalJsonReader getReader() {
        return this.reader;
    }

    public final char[] getBuffer() {
        return this.buffer;
    }

    public ReaderJsonLexer(InternalJsonReader reader, char[] buffer) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.reader = reader;
        this.buffer = buffer;
        this.threshold = 128;
        this.source = new ArrayAsSequence(this.buffer);
        preload(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.JsonReader
    public ArrayAsSequence getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public boolean canConsumeValue() {
        ensureHaveChars();
        int current = this.currentPosition;
        while (true) {
            int current2 = prefetchOrEof(current);
            if (current2 != -1) {
                char c = getSource().charAt(current2);
                if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                    current = current2 + 1;
                } else {
                    this.currentPosition = current2;
                    return isValidValueStart(c);
                }
            } else {
                this.currentPosition = current2;
                return false;
            }
        }
    }

    private final void preload(int unprocessedCount) {
        char[] buffer = getSource().getBuffer();
        if (unprocessedCount != 0) {
            ArraysKt.copyInto(buffer, buffer, 0, this.currentPosition, this.currentPosition + unprocessedCount);
        }
        int filledCount = unprocessedCount;
        int sizeTotal = getSource().length();
        while (true) {
            if (filledCount == sizeTotal) {
                break;
            }
            int actual = this.reader.read(buffer, filledCount, sizeTotal - filledCount);
            if (actual == -1) {
                getSource().trim(filledCount);
                this.threshold = -1;
                break;
            }
            filledCount += actual;
        }
        this.currentPosition = 0;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int prefetchOrEof(int position) {
        if (position < getSource().length()) {
            return position;
        }
        this.currentPosition = position;
        ensureHaveChars();
        if (this.currentPosition == 0) {
            return getSource().length() == 0 ? -1 : 0;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public byte consumeNextToken() {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                int cpos3 = cpos2 + 1;
                char ch = source.charAt(cpos2);
                byte tc = AbstractJsonLexerKt.charToTokenClass(ch);
                if (tc == 3) {
                    cpos = cpos3;
                } else {
                    this.currentPosition = cpos3;
                    return tc;
                }
            } else {
                this.currentPosition = cpos2;
                return (byte) 10;
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public void consumeNextToken(char expected) {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                int cpos3 = cpos2 + 1;
                char c = source.charAt(cpos2);
                if (!(c == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                    this.currentPosition = cpos3;
                    if (c == expected) {
                        return;
                    } else {
                        unexpectedToken(expected);
                    }
                }
                cpos = cpos3;
            } else {
                this.currentPosition = cpos2;
                unexpectedToken(expected);
                return;
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int skipWhitespaces() {
        int current;
        int current2 = this.currentPosition;
        while (true) {
            current = prefetchOrEof(current2);
            if (current != -1) {
                char c = getSource().charAt(current);
                if (!(c == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                    break;
                }
                current2 = current + 1;
            } else {
                break;
            }
        }
        this.currentPosition = current;
        return current;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public void ensureHaveChars() {
        int cur = this.currentPosition;
        int oldSize = getSource().length();
        int spaceLeft = oldSize - cur;
        if (spaceLeft > this.threshold) {
            return;
        }
        preload(spaceLeft);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String consumeKeyString() {
        consumeNextToken('\"');
        int current = this.currentPosition;
        int closingQuote = indexOf('\"', current);
        if (closingQuote == -1) {
            int current2 = prefetchOrEof(current);
            if (current2 == -1) {
                JsonReader.fail$kotlinx_serialization_json$default(this, (byte) 1, false, 2, null);
                throw new KotlinNothingValueException();
            }
            return consumeString(getSource(), this.currentPosition, current2);
        }
        for (int i = current; i < closingQuote; i++) {
            if (getSource().charAt(i) == '\\') {
                return consumeString(getSource(), this.currentPosition, i);
            }
        }
        int i2 = closingQuote + 1;
        this.currentPosition = i2;
        return substring(current, closingQuote);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int indexOf(char r5, int startPos) {
        ArrayAsSequence src = getSource();
        int length = src.length();
        for (int i = startPos; i < length; i++) {
            if (src.charAt(i) == r5) {
                return i;
            }
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String substring(int startPos, int endPos) {
        return getSource().substring(startPos, endPos);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    protected void appendRange(int fromIndex, int toIndex) {
        Intrinsics.checkNotNullExpressionValue(getEscapedString().append(getSource().getBuffer(), fromIndex, toIndex - fromIndex), "append(...)");
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String peekLeadingMatchingValue(String keyToMatch, boolean isLenient) {
        Intrinsics.checkNotNullParameter(keyToMatch, "keyToMatch");
        return null;
    }

    public final void release() {
        CharArrayPoolBatchSize.INSTANCE.release(this.buffer);
    }
}
