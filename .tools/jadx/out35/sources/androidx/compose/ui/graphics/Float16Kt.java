package androidx.compose.ui.graphics;

import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.ShortCompanionObject;

/* compiled from: Float16.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\r\u001a\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\nH\u0080\b\u001a\u0011\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0017H\u0080\b\u001a\"\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\"\u0010 \u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001f\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0017H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014\"\u0010\u0010\u0015\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Fp16Combined", "", "Fp16ExponentBias", "Fp16ExponentMask", "Fp16ExponentMax", "Fp16ExponentShift", "Fp16SignMask", "Fp16SignShift", "Fp16SignificandMask", "Fp32DenormalFloat", "", "Fp32DenormalMagic", "Fp32ExponentBias", "Fp32ExponentMask", "Fp32ExponentShift", "Fp32QNaNMask", "Fp32SignShift", "Fp32SignificandMask", "NegativeOne", "Landroidx/compose/ui/graphics/Float16;", "S", "One", "floatToHalf", "", "f", "halfToFloat", "h", "max", "x", "y", "max-AoSsdG0", "(SS)S", "min", "min-AoSsdG0", "toCompareValue", "value", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class Float16Kt {
    private static final int Fp16Combined = 32767;
    private static final int Fp16ExponentBias = 15;
    private static final int Fp16ExponentMask = 31;
    private static final int Fp16ExponentMax = 31744;
    private static final int Fp16ExponentShift = 10;
    private static final int Fp16SignMask = 32768;
    private static final int Fp16SignShift = 15;
    private static final int Fp16SignificandMask = 1023;
    private static final int Fp32ExponentBias = 127;
    private static final int Fp32ExponentMask = 255;
    private static final int Fp32ExponentShift = 23;
    private static final int Fp32QNaNMask = 4194304;
    private static final int Fp32SignShift = 31;
    private static final int Fp32SignificandMask = 8388607;
    private static final short One = Float16.m4270constructorimpl(1.0f);
    private static final short NegativeOne = Float16.m4270constructorimpl(-1.0f);
    private static final int Fp32DenormalMagic = 1056964608;
    private static final float Fp32DenormalFloat = Float.intBitsToFloat(Fp32DenormalMagic);

    private static final int toCompareValue(short value) {
        if ((value & ShortCompanionObject.MIN_VALUE) != 0) {
            return 32768 - (value & UShort.MAX_VALUE);
        }
        return value & UShort.MAX_VALUE;
    }

    public static final short floatToHalf(float f) {
        int bits = Float.floatToRawIntBits(f);
        int s = bits >>> 31;
        int e = (bits >>> 23) & 255;
        int m = Fp32SignificandMask & bits;
        int outE = 0;
        int outM = 0;
        if (e == 255) {
            outE = 31;
            outM = m != 0 ? 512 : 0;
        } else {
            int e2 = (e - 127) + 15;
            if (e2 >= 31) {
                outE = 49;
            } else if (e2 <= 0) {
                if (e2 >= -10) {
                    int m2 = (8388608 | m) >> (1 - e2);
                    if ((m2 & 4096) != 0) {
                        m2 += 8192;
                    }
                    outM = m2 >> 13;
                }
            } else {
                outE = e2;
                outM = m >> 13;
                if ((m & 4096) != 0) {
                    int out = (outE << 10) | outM;
                    return (short) ((s << 15) | (out + 1));
                }
            }
        }
        int out2 = s << 15;
        return (short) (out2 | (outE << 10) | outM);
    }

    public static final float halfToFloat(short h) {
        int bits = 65535 & h;
        int s = 32768 & bits;
        int e = (bits >>> 10) & 31;
        int m = bits & Fp16SignificandMask;
        int outE = 0;
        int outM = 0;
        if (e == 0) {
            if (m != 0) {
                int bits$iv = Fp32DenormalMagic + m;
                float o = Float.intBitsToFloat(bits$iv) - Fp32DenormalFloat;
                return s == 0 ? o : -o;
            }
        } else {
            outM = m << 13;
            if (e == 31) {
                outE = 255;
                if (outM != 0) {
                    outM |= 4194304;
                }
            } else {
                outE = (e - 15) + 127;
            }
        }
        int out = (s << 16) | (outE << 23) | outM;
        return Float.intBitsToFloat(out);
    }

    /* renamed from: min-AoSsdG0, reason: not valid java name */
    public static final short m4309minAoSsdG0(short x, short y) {
        if (Float16.m4281isNaNimpl(x) || Float16.m4281isNaNimpl(y)) {
            return Float16.Companion.m4303getNaNslo4al4();
        }
        return Float16.m4268compareTo41bOqos(x, y) <= 0 ? x : y;
    }

    /* renamed from: max-AoSsdG0, reason: not valid java name */
    public static final short m4308maxAoSsdG0(short x, short y) {
        if (Float16.m4281isNaNimpl(x) || Float16.m4281isNaNimpl(y)) {
            return Float16.Companion.m4303getNaNslo4al4();
        }
        return Float16.m4268compareTo41bOqos(x, y) >= 0 ? x : y;
    }
}
