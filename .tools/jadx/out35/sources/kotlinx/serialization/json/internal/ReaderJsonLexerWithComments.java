package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CommentLexers.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\b\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001c\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00112\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0016¨\u0006\u0016"}, d2 = {"Lkotlinx/serialization/json/internal/ReaderJsonLexerWithComments;", "Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "reader", "Lkotlinx/serialization/json/internal/InternalJsonReader;", "buffer", "", "<init>", "(Lkotlinx/serialization/json/internal/InternalJsonReader;[C)V", "consumeNextToken", "", "expected", "", "canConsumeValue", "", "", "peekNextToken", "handleComment", "Lkotlin/Pair;", "", "position", "prefetchWithinThreshold", "skipWhitespaces", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class ReaderJsonLexerWithComments extends ReaderJsonLexer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReaderJsonLexerWithComments(InternalJsonReader reader, char[] buffer) {
        super(reader, buffer);
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public void consumeNextToken(char expected) {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
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

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public boolean canConsumeValue() {
        ensureHaveChars();
        int current = skipWhitespaces();
        if (current >= getSource().length() || current == -1) {
            return false;
        }
        return isValidValueStart(getSource().charAt(current));
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public byte consumeNextToken() {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int cpos = skipWhitespaces();
        if (cpos >= source.length() || cpos == -1) {
            return (byte) 10;
        }
        this.currentPosition = cpos + 1;
        return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public byte peekNextToken() {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int cpos = skipWhitespaces();
        if (cpos >= source.length() || cpos == -1) {
            return (byte) 10;
        }
        this.currentPosition = cpos;
        return AbstractJsonLexerKt.charToTokenClass(source.charAt(cpos));
    }

    private final Pair<Integer, Boolean> handleComment(int position) {
        int current = position;
        int startIndex = current + 2;
        switch (getSource().charAt(current + 1)) {
            case '*':
                boolean rareCaseHit = false;
                int startIndex2 = startIndex;
                while (current != -1) {
                    int current2 = StringsKt.indexOf$default((CharSequence) getSource(), "*/", startIndex2, false, 4, (Object) null);
                    if (current2 == -1) {
                        if (getSource().charAt(getSource().length() - 1) != '*') {
                            current = prefetchOrEof(getSource().length());
                            startIndex2 = current;
                        } else {
                            current = prefetchWithinThreshold(getSource().length() - 1);
                            if (rareCaseHit) {
                                this.currentPosition = getSource().length();
                                JsonReader.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
                                throw new KotlinNothingValueException();
                            }
                            rareCaseHit = true;
                            startIndex2 = current;
                        }
                    } else {
                        return TuplesKt.to(Integer.valueOf(current2 + 2), true);
                    }
                }
                this.currentPosition = getSource().length();
                JsonReader.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
                throw new KotlinNothingValueException();
            case '/':
                int startIndex3 = startIndex;
                while (current != -1) {
                    int current3 = StringsKt.indexOf$default((CharSequence) getSource(), '\n', startIndex3, false, 4, (Object) null);
                    if (current3 == -1) {
                        current = prefetchOrEof(getSource().length());
                        startIndex3 = current;
                    } else {
                        return TuplesKt.to(Integer.valueOf(current3 + 1), true);
                    }
                }
                return TuplesKt.to(-1, true);
            default:
                return TuplesKt.to(Integer.valueOf(current), false);
        }
    }

    private final int prefetchWithinThreshold(int position) {
        if (getSource().length() - position > this.threshold) {
            return position;
        }
        this.currentPosition = position;
        ensureHaveChars();
        if (this.currentPosition == 0) {
            return getSource().length() == 0 ? -1 : 0;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.JsonReader
    public int skipWhitespaces() {
        int current = this.currentPosition;
        while (true) {
            current = prefetchOrEof(current);
            if (current == -1) {
                break;
            }
            char c = getSource().charAt(current);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                current++;
            } else {
                if (c != '/' || current + 1 >= getSource().length()) {
                    break;
                }
                Pair<Integer, Boolean> handleComment = handleComment(current);
                int intValue = handleComment.component1().intValue();
                boolean cont = handleComment.component2().booleanValue();
                current = intValue;
                if (!cont) {
                    break;
                }
            }
        }
        this.currentPosition = current;
        return current;
    }
}
