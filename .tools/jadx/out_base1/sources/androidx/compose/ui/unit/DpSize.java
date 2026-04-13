package androidx.compose.ui.unit;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Dp.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0010\u001a\u00020\u0007H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000bJ\u0016\u0010\u0012\u001a\u00020\u0007H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u000bJ$\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u001cH\u0087\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b#\u0010$J\u001b\u0010%\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010'J\u001e\u0010*\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001bJ\u001e\u0010*\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u001cH\u0087\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001dJ\u000f\u0010,\u001a\u00020-H\u0017¢\u0006\u0004\b.\u0010/R \u0010\u0006\u001a\u00020\u00078FX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\tR \u0010\r\u001a\u00020\u00078FX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000b\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00061"}, d2 = {"Landroidx/compose/ui/unit/DpSize;", "", "packedValue", "", "constructor-impl", "(J)J", "height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM$annotations", "()V", "getHeight-D9Ej5fM", "(J)F", "getPackedValue$annotations", "width", "getWidth-D9Ej5fM$annotations", "getWidth-D9Ej5fM", "component1", "component1-D9Ej5fM", "component2", "component2-D9Ej5fM", "copy", "copy-DwJknco", "(JFF)J", "div", "other", "", "div-Gh9hcWk", "(JF)J", "", "(JI)J", "equals", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "(J)I", "minus", "minus-e_xh8Ic", "(JJ)J", "plus", "plus-e_xh8Ic", "times", "times-Gh9hcWk", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes13.dex */
public final class DpSize {
    private final long packedValue;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Zero = m6715constructorimpl(0);
    private static final long Unspecified = m6715constructorimpl(androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats);

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DpSize m6712boximpl(long j) {
        return new DpSize(j);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m6715constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m6720equalsimpl(long j, Object obj) {
        return (obj instanceof DpSize) && j == ((DpSize) obj).getPackedValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6721equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getHeight-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m6723getHeightD9Ej5fM$annotations() {
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    /* renamed from: getWidth-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m6725getWidthD9Ej5fM$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m6726hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object obj) {
        return m6720equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m6726hashCodeimpl(this.packedValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }

    private /* synthetic */ DpSize(long packedValue) {
        this.packedValue = packedValue;
    }

    /* renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public static final float m6724getWidthD9Ej5fM(long arg0) {
        int bits$iv$iv = (int) (arg0 >> 32);
        float $this$dp$iv = Float.intBitsToFloat(bits$iv$iv);
        return Dp.m6626constructorimpl($this$dp$iv);
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public static final float m6722getHeightD9Ej5fM(long arg0) {
        int bits$iv$iv = (int) (4294967295L & arg0);
        float $this$dp$iv = Float.intBitsToFloat(bits$iv$iv);
        return Dp.m6626constructorimpl($this$dp$iv);
    }

    /* renamed from: copy-DwJknco$default, reason: not valid java name */
    public static /* synthetic */ long m6717copyDwJknco$default(long j, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = m6724getWidthD9Ej5fM(j);
        }
        if ((i & 2) != 0) {
            f2 = m6722getHeightD9Ej5fM(j);
        }
        return m6716copyDwJknco(j, f, f2);
    }

    /* renamed from: copy-DwJknco, reason: not valid java name */
    public static final long m6716copyDwJknco(long arg0, float width, float height) {
        long v1$iv = Float.floatToRawIntBits(width);
        long v2$iv = Float.floatToRawIntBits(height);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: minus-e_xh8Ic, reason: not valid java name */
    public static final long m6727minuse_xh8Ic(long arg0, long other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float other$iv = m6724getWidthD9Ej5fM(other);
        float arg0$iv2 = Dp.m6626constructorimpl(arg0$iv - other$iv);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float other$iv2 = m6722getHeightD9Ej5fM(other);
        float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv3 - other$iv2);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: plus-e_xh8Ic, reason: not valid java name */
    public static final long m6728pluse_xh8Ic(long arg0, long other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float other$iv = m6724getWidthD9Ej5fM(other);
        float arg0$iv2 = Dp.m6626constructorimpl(arg0$iv + other$iv);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float other$iv2 = m6722getHeightD9Ej5fM(other);
        float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv3 + other$iv2);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name */
    public static final float m6713component1D9Ej5fM(long arg0) {
        return m6724getWidthD9Ej5fM(arg0);
    }

    /* renamed from: component2-D9Ej5fM, reason: not valid java name */
    public static final float m6714component2D9Ej5fM(long arg0) {
        return m6722getHeightD9Ej5fM(arg0);
    }

    /* renamed from: times-Gh9hcWk, reason: not valid java name */
    public static final long m6730timesGh9hcWk(long arg0, int other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float arg0$iv2 = Dp.m6626constructorimpl(other * arg0$iv);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float arg0$iv4 = Dp.m6626constructorimpl(other * arg0$iv3);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: times-Gh9hcWk, reason: not valid java name */
    public static final long m6729timesGh9hcWk(long arg0, float other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float arg0$iv2 = Dp.m6626constructorimpl(arg0$iv * other);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv3 * other);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: div-Gh9hcWk, reason: not valid java name */
    public static final long m6719divGh9hcWk(long arg0, int other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float arg0$iv2 = Dp.m6626constructorimpl(arg0$iv / other);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv3 / other);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: div-Gh9hcWk, reason: not valid java name */
    public static final long m6718divGh9hcWk(long arg0, float other) {
        float arg0$iv = m6724getWidthD9Ej5fM(arg0);
        float arg0$iv2 = Dp.m6626constructorimpl(arg0$iv / other);
        float arg0$iv3 = m6722getHeightD9Ej5fM(arg0);
        float arg0$iv4 = Dp.m6626constructorimpl(arg0$iv3 / other);
        long v1$iv = Float.floatToRawIntBits(arg0$iv2);
        long v2$iv = Float.floatToRawIntBits(arg0$iv4);
        return m6715constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    public String toString() {
        return m6731toStringimpl(this.packedValue);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m6731toStringimpl(long arg0) {
        if (arg0 != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return ((Object) Dp.m6637toStringimpl(m6724getWidthD9Ej5fM(arg0))) + " x " + ((Object) Dp.m6637toStringimpl(m6722getHeightD9Ej5fM(arg0)));
        }
        return "DpSize.Unspecified";
    }

    /* compiled from: Dp.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\n"}, d2 = {"Landroidx/compose/ui/unit/DpSize$Companion;", "", "()V", "Unspecified", "Landroidx/compose/ui/unit/DpSize;", "getUnspecified-MYxV2XQ", "()J", "J", "Zero", "getZero-MYxV2XQ", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getZero-MYxV2XQ, reason: not valid java name */
        public final long m6734getZeroMYxV2XQ() {
            return DpSize.Zero;
        }

        /* renamed from: getUnspecified-MYxV2XQ, reason: not valid java name */
        public final long m6733getUnspecifiedMYxV2XQ() {
            return DpSize.Unspecified;
        }
    }
}
