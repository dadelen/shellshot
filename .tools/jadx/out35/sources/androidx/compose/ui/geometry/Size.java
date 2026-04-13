package androidx.compose.ui.geometry;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 32\u00020\u0001:\u00013B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u0007H\u0087\n¢\u0006\u0004\b\u0017\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\u0007H\u0087\n¢\u0006\u0004\b\u0019\u0010\u000bJ'\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0007H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020'HÖ\u0001¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\"H\u0007¢\u0006\u0004\b+\u0010,J\u001e\u0010-\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0007H\u0087\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010 J\u000f\u0010/\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\t\u001a\u0004\b\u0015\u0010\u000b\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Landroidx/compose/ui/geometry/Size;", "", "packedValue", "", "constructor-impl", "(J)J", "height", "", "getHeight$annotations", "()V", "getHeight-impl", "(J)F", "maxDimension", "getMaxDimension$annotations", "getMaxDimension-impl", "minDimension", "getMinDimension$annotations", "getMinDimension-impl", "getPackedValue$annotations", "width", "getWidth$annotations", "getWidth-impl", "component1", "component1-impl", "component2", "component2-impl", "copy", "copy-xjbvk4A", "(JFF)J", "div", "operand", "div-7Ah8Wj8", "(JF)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "isEmpty", "isEmpty-impl", "(J)Z", "times", "times-7Ah8Wj8", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes13.dex */
public final class Size {
    private final long packedValue;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Zero = m3982constructorimpl(0);
    private static final long Unspecified = m3982constructorimpl(InlineClassHelperKt.UnspecifiedPackedFloats);

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Size m3979boximpl(long j) {
        return new Size(j);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m3982constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m3986equalsimpl(long j, Object obj) {
        return (obj instanceof Size) && j == ((Size) obj).getPackedValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3987equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getHeight$annotations() {
    }

    public static /* synthetic */ void getMaxDimension$annotations() {
    }

    public static /* synthetic */ void getMinDimension$annotations() {
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    public static /* synthetic */ void getWidth$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m3992hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object obj) {
        return m3986equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m3992hashCodeimpl(this.packedValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }

    private /* synthetic */ Size(long packedValue) {
        this.packedValue = packedValue;
    }

    /* renamed from: getWidth-impl, reason: not valid java name */
    public static final float m3991getWidthimpl(long arg0) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) (arg0 >> 32);
        return Float.intBitsToFloat(bits$iv$iv);
    }

    /* renamed from: getHeight-impl, reason: not valid java name */
    public static final float m3988getHeightimpl(long arg0) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) (4294967295L & arg0);
        return Float.intBitsToFloat(bits$iv$iv);
    }

    /* renamed from: component1-impl, reason: not valid java name */
    public static final float m3980component1impl(long arg0) {
        return m3991getWidthimpl(arg0);
    }

    /* renamed from: component2-impl, reason: not valid java name */
    public static final float m3981component2impl(long arg0) {
        return m3988getHeightimpl(arg0);
    }

    /* renamed from: copy-xjbvk4A$default, reason: not valid java name */
    public static /* synthetic */ long m3984copyxjbvk4A$default(long value$iv, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            int bits$iv$iv = (int) (value$iv >> 32);
            f = Float.intBitsToFloat(bits$iv$iv);
        }
        if ((i & 2) != 0) {
            int bits$iv$iv2 = (int) (4294967295L & value$iv);
            f2 = Float.intBitsToFloat(bits$iv$iv2);
        }
        return m3983copyxjbvk4A(value$iv, f, f2);
    }

    /* renamed from: copy-xjbvk4A, reason: not valid java name */
    public static final long m3983copyxjbvk4A(long arg0, float width, float height) {
        long v1$iv = Float.floatToRawIntBits(width);
        long v2$iv = Float.floatToRawIntBits(height);
        return m3982constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* compiled from: Size.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\f"}, d2 = {"Landroidx/compose/ui/geometry/Size$Companion;", "", "()V", "Unspecified", "Landroidx/compose/ui/geometry/Size;", "getUnspecified-NH-jbRc$annotations", "getUnspecified-NH-jbRc", "()J", "J", "Zero", "getZero-NH-jbRc$annotations", "getZero-NH-jbRc", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getUnspecified-NH-jbRc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3997getUnspecifiedNHjbRc$annotations() {
        }

        /* renamed from: getZero-NH-jbRc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3998getZeroNHjbRc$annotations() {
        }

        private Companion() {
        }

        /* renamed from: getZero-NH-jbRc, reason: not valid java name */
        public final long m4000getZeroNHjbRc() {
            return Size.Zero;
        }

        /* renamed from: getUnspecified-NH-jbRc, reason: not valid java name */
        public final long m3999getUnspecifiedNHjbRc() {
            return Size.Unspecified;
        }
    }

    /* renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m3993isEmptyimpl(long arg0) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        long v = (~((((-9223372034707292160L) & arg0) >>> 31) * (-1))) & arg0;
        return ((v >>> 32) & (4294967295L & v)) == 0;
    }

    /* renamed from: times-7Ah8Wj8, reason: not valid java name */
    public static final long m3994times7Ah8Wj8(long arg0, float operand) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) (arg0 >> 32);
        float val1$iv = Float.intBitsToFloat(bits$iv$iv) * operand;
        int bits$iv$iv2 = (int) (arg0 & 4294967295L);
        float val2$iv = Float.intBitsToFloat(bits$iv$iv2) * operand;
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return m3982constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: div-7Ah8Wj8, reason: not valid java name */
    public static final long m3985div7Ah8Wj8(long arg0, float operand) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) (arg0 >> 32);
        float val1$iv = Float.intBitsToFloat(bits$iv$iv) / operand;
        int bits$iv$iv2 = (int) (arg0 & 4294967295L);
        float val2$iv = Float.intBitsToFloat(bits$iv$iv2) / operand;
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return m3982constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: getMinDimension-impl, reason: not valid java name */
    public static final float m3990getMinDimensionimpl(long arg0) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) ((arg0 >> 32) & 2147483647L);
        int bits$iv$iv2 = (int) (arg0 & 2147483647L);
        return Math.min(Float.intBitsToFloat(bits$iv$iv), Float.intBitsToFloat(bits$iv$iv2));
    }

    /* renamed from: getMaxDimension-impl, reason: not valid java name */
    public static final float m3989getMaxDimensionimpl(long arg0) {
        if (arg0 == InlineClassHelperKt.UnspecifiedPackedFloats) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        int bits$iv$iv = (int) ((arg0 >> 32) & 2147483647L);
        int bits$iv$iv2 = (int) (arg0 & 2147483647L);
        return Math.max(Float.intBitsToFloat(bits$iv$iv), Float.intBitsToFloat(bits$iv$iv2));
    }

    public String toString() {
        return m3995toStringimpl(this.packedValue);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m3995toStringimpl(long arg0) {
        if (arg0 != InlineClassHelperKt.UnspecifiedPackedFloats) {
            return "Size(" + GeometryUtilsKt.toStringAsFixed(m3991getWidthimpl(arg0), 1) + ", " + GeometryUtilsKt.toStringAsFixed(m3988getHeightimpl(arg0), 1) + ')';
        }
        return "Size.Unspecified";
    }
}
