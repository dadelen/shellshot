package kotlinx.serialization.json.internal;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CharsetReader.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011J \u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/serialization/json/internal/CharsetReader;", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "<init>", "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V", "decoder", "Ljava/nio/charset/CharsetDecoder;", "byteBuffer", "Ljava/nio/ByteBuffer;", "hasLeftoverPotentiallySurrogateChar", "", "leftoverChar", "", "read", "", "array", "", "offset", "length", "doRead", "fillByteBuffer", "oneShotReadSlowPath", "release", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class CharsetReader {
    private final ByteBuffer byteBuffer;
    private final Charset charset;
    private final CharsetDecoder decoder;
    private boolean hasLeftoverPotentiallySurrogateChar;
    private final InputStream inputStream;
    private char leftoverChar;

    public CharsetReader(InputStream inputStream, Charset charset) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.inputStream = inputStream;
        this.charset = charset;
        this.decoder = this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        this.byteBuffer = ByteBuffer.wrap(ByteArrayPool8k.INSTANCE.take());
        this.byteBuffer.flip();
    }

    public final int read(char[] array, int offset, int length) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(array, "array");
        if (length == 0) {
            return 0;
        }
        if (offset < 0 || offset >= array.length) {
            z = false;
        } else {
            z = true;
        }
        if (!z || length < 0 || offset + length > array.length) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!z2) {
            throw new IllegalArgumentException(("Unexpected arguments: " + offset + ", " + length + ", " + array.length).toString());
        }
        int offset2 = offset;
        int length2 = length;
        int bytesRead = 0;
        if (this.hasLeftoverPotentiallySurrogateChar) {
            array[offset2] = this.leftoverChar;
            offset2++;
            length2--;
            this.hasLeftoverPotentiallySurrogateChar = false;
            bytesRead = 1;
            if (length2 == 0) {
                return 1;
            }
        }
        if (length2 == 1) {
            int c = oneShotReadSlowPath();
            if (c != -1) {
                array[offset2] = (char) c;
                return bytesRead + 1;
            }
            if (bytesRead == 0) {
                return -1;
            }
            return bytesRead;
        }
        return doRead(array, offset2, length2) + bytesRead;
    }

    private final int doRead(char[] array, int offset, int length) {
        CharBuffer charBuffer = CharBuffer.wrap(array, offset, length);
        if (charBuffer.position() != 0) {
            charBuffer = charBuffer.slice();
        }
        boolean isEof = false;
        while (true) {
            CoderResult cr = this.decoder.decode(this.byteBuffer, charBuffer, isEof);
            if (cr.isUnderflow()) {
                if (isEof || !charBuffer.hasRemaining()) {
                    break;
                }
                int n = fillByteBuffer();
                if (n < 0) {
                    isEof = true;
                    if (charBuffer.position() == 0 && !this.byteBuffer.hasRemaining()) {
                        break;
                    }
                    this.decoder.reset();
                } else {
                    continue;
                }
            } else if (cr.isOverflow()) {
                if (!(charBuffer.position() > 0)) {
                    throw new AssertionError("Assertion failed");
                }
            } else {
                cr.throwException();
            }
        }
        if (isEof) {
            this.decoder.reset();
        }
        if (charBuffer.position() == 0) {
            return -1;
        }
        return charBuffer.position();
    }

    private final int fillByteBuffer() {
        this.byteBuffer.compact();
        try {
            int limit = this.byteBuffer.limit();
            int position = this.byteBuffer.position();
            int remaining = position <= limit ? limit - position : 0;
            int bytesRead = this.inputStream.read(this.byteBuffer.array(), this.byteBuffer.arrayOffset() + position, remaining);
            ByteBuffer byteBuffer = this.byteBuffer;
            if (bytesRead >= 0) {
                Intrinsics.checkNotNull(byteBuffer, "null cannot be cast to non-null type java.nio.Buffer");
                byteBuffer.position(position + bytesRead);
                this.byteBuffer.flip();
                return this.byteBuffer.remaining();
            }
            byteBuffer.flip();
            return bytesRead;
        } catch (Throwable th) {
            this.byteBuffer.flip();
            throw th;
        }
    }

    private final int oneShotReadSlowPath() {
        if (this.hasLeftoverPotentiallySurrogateChar) {
            this.hasLeftoverPotentiallySurrogateChar = false;
            return this.leftoverChar;
        }
        char[] array = new char[2];
        int bytesRead = read(array, 0, 2);
        switch (bytesRead) {
            case -1:
                return -1;
            case 0:
            default:
                throw new IllegalStateException(("Unreachable state: " + bytesRead).toString());
            case 1:
                return array[0];
            case 2:
                this.leftoverChar = array[1];
                this.hasLeftoverPotentiallySurrogateChar = true;
                return array[0];
        }
    }

    public final void release() {
        ByteArrayPool8k byteArrayPool8k = ByteArrayPool8k.INSTANCE;
        byte[] array = this.byteBuffer.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        byteArrayPool8k.release(array);
    }
}
