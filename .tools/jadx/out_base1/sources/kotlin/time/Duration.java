package kotlin.time;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: Duration.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0006\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u0089\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0089\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\f\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u000fJ\u0010\u0010\u0016\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0017\u0010\u0005J\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010!\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\"\u0010\u001bJ\u0018\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\tH\u0086\u0002¢\u0006\u0004\b%\u0010&J\u0018\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020'H\u0086\u0002¢\u0006\u0004\b%\u0010(J\u0018\u0010)\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\tH\u0086\u0002¢\u0006\u0004\b*\u0010&J\u0018\u0010)\u001a\u00020\u00002\u0006\u0010$\u001a\u00020'H\u0086\u0002¢\u0006\u0004\b*\u0010(J\u0018\u0010)\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0013H\u0000¢\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020\r¢\u0006\u0004\b2\u0010\u000fJ\r\u00103\u001a\u00020\r¢\u0006\u0004\b4\u0010\u000fJ\r\u00105\u001a\u00020\r¢\u0006\u0004\b6\u0010\u000fJ\r\u00107\u001a\u00020\r¢\u0006\u0004\b8\u0010\u000fJ\u0018\u0010;\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b<\u0010=J\u009d\u0001\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2u\u0010@\u001aq\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0AH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010JJ\u0088\u0001\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2`\u0010@\u001a\\\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0KH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010LJs\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2K\u0010@\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0MH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010NJ^\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?26\u0010@\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0OH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010PJ\u0015\u0010^\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0013¢\u0006\u0004\b_\u0010`J\u0015\u0010a\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u0013¢\u0006\u0004\bb\u00100J\u0015\u0010c\u001a\u00020\t2\u0006\u0010.\u001a\u00020\u0013¢\u0006\u0004\bd\u0010eJ\u000f\u0010t\u001a\u00020uH\u0016¢\u0006\u0004\bv\u0010wJA\u0010x\u001a\u00020y*\u00060zj\u0002`{2\u0006\u0010|\u001a\u00020\t2\u0006\u0010}\u001a\u00020\t2\u0006\u0010~\u001a\u00020\t2\u0006\u0010.\u001a\u00020u2\u0006\u0010\u007f\u001a\u00020\rH\u0002¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J!\u0010t\u001a\u00020u2\u0006\u0010.\u001a\u00020\u00132\t\b\u0002\u0010\u0082\u0001\u001a\u00020\t¢\u0006\u0005\bv\u0010\u0083\u0001J\u000f\u0010\u0084\u0001\u001a\u00020u¢\u0006\u0005\b\u0085\u0001\u0010wJ\u0015\u0010\u0086\u0001\u001a\u00020\r2\t\u0010\u0019\u001a\u0005\u0018\u00010\u0087\u0001HÖ\u0003J\n\u0010\u0088\u0001\u001a\u00020\tHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0015\u0010\b\u001a\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u00109\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b:\u0010\u0005R\u001a\u0010Q\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\bR\u0010S\u001a\u0004\bT\u0010\u000bR\u001a\u0010U\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\bV\u0010S\u001a\u0004\bW\u0010\u000bR\u001a\u0010X\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\bY\u0010S\u001a\u0004\bZ\u0010\u000bR\u001a\u0010[\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\\\u0010S\u001a\u0004\b]\u0010\u000bR\u0011\u0010f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bg\u0010\u0005R\u0011\u0010h\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bi\u0010\u0005R\u0011\u0010j\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bk\u0010\u0005R\u0011\u0010l\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bm\u0010\u0005R\u0011\u0010n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bo\u0010\u0005R\u0011\u0010p\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bq\u0010\u0005R\u0011\u0010r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bs\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u008a\u0001"}, d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "value", "getValue-impl", "unitDiscriminator", "", "getUnitDiscriminator-impl", "(J)I", "isInNanos", "", "isInNanos-impl", "(J)Z", "isInMillis", "isInMillis-impl", "storageUnit", "Lkotlin/time/DurationUnit;", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "unaryMinus", "unaryMinus-UwyO8pc", "plus", "other", "plus-LRDsOJo", "(JJ)J", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "minus", "minus-LRDsOJo", "times", "scale", "times-UwyO8pc", "(JI)J", "", "(JD)J", "div", "div-UwyO8pc", "div-LRDsOJo", "(JJ)D", "truncateTo", "unit", "truncateTo-UwyO8pc$kotlin_stdlib", "(JLkotlin/time/DurationUnit;)J", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "isInfinite", "isInfinite-impl", "isFinite", "isFinite-impl", "absoluteValue", "getAbsoluteValue-UwyO8pc", "compareTo", "compareTo-LRDsOJo", "(JJ)I", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "hoursComponent", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "toDouble", "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toLong", "toLong-impl", "toInt", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "inWholeDays", "getInWholeDays-impl", "inWholeHours", "getInWholeHours-impl", "inWholeMinutes", "getInWholeMinutes-impl", "inWholeSeconds", "getInWholeSeconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds-impl", "inWholeMicroseconds", "getInWholeMicroseconds-impl", "inWholeNanoseconds", "getInWholeNanoseconds-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "appendFractional", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "toIsoString", "toIsoString-impl", "equals", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
/* loaded from: classes14.dex */
public final class Duration implements Comparable<Duration> {
    private static final long INFINITE;
    private static final long NEG_INFINITE;
    private final long rawValue;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long ZERO = m8555constructorimpl(0);

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m8553boximpl(long j) {
        return new Duration(j);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m8559equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).getRawValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8560equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m8576hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object other) {
        return m8559equalsimpl(this.rawValue, other);
    }

    public int hashCode() {
        return m8576hashCodeimpl(this.rawValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getRawValue() {
        return this.rawValue;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m8600compareToLRDsOJo(duration.getRawValue());
    }

    private /* synthetic */ Duration(long rawValue) {
        this.rawValue = rawValue;
    }

    /* renamed from: getValue-impl, reason: not valid java name */
    private static final long m8575getValueimpl(long arg0) {
        return arg0 >> 1;
    }

    /* renamed from: getUnitDiscriminator-impl, reason: not valid java name */
    private static final int m8574getUnitDiscriminatorimpl(long arg0) {
        return ((int) arg0) & 1;
    }

    /* renamed from: isInNanos-impl, reason: not valid java name */
    private static final boolean m8579isInNanosimpl(long arg0) {
        return (((int) arg0) & 1) == 0;
    }

    /* renamed from: isInMillis-impl, reason: not valid java name */
    private static final boolean m8578isInMillisimpl(long arg0) {
        return (((int) arg0) & 1) == 1;
    }

    /* renamed from: getStorageUnit-impl, reason: not valid java name */
    private static final DurationUnit m8573getStorageUnitimpl(long arg0) {
        return m8579isInNanosimpl(arg0) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m8555constructorimpl(long rawValue) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            if (m8579isInNanosimpl(rawValue)) {
                long m8575getValueimpl = m8575getValueimpl(rawValue);
                if (!(-4611686018426999999L <= m8575getValueimpl && m8575getValueimpl < 4611686018427000000L)) {
                    throw new AssertionError(m8575getValueimpl(rawValue) + " ns is out of nanoseconds range");
                }
            } else {
                long m8575getValueimpl2 = m8575getValueimpl(rawValue);
                if (!(-4611686018427387903L <= m8575getValueimpl2 && m8575getValueimpl2 < 4611686018427387904L)) {
                    throw new AssertionError(m8575getValueimpl(rawValue) + " ms is out of milliseconds range");
                }
                long m8575getValueimpl3 = m8575getValueimpl(rawValue);
                if (-4611686018426L <= m8575getValueimpl3 && m8575getValueimpl3 < 4611686018427L) {
                    throw new AssertionError(m8575getValueimpl(rawValue) + " ms is denormalized");
                }
            }
        }
        return rawValue;
    }

    /* compiled from: Duration.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\u0015\u00100\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u000201¢\u0006\u0004\b2\u00103J\u0015\u00104\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u000201¢\u0006\u0004\b5\u00103J\u0015\u00106\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u000201¢\u0006\u0002\b7J\u0015\u00108\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u000201¢\u0006\u0002\b9R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0016\u0010\u000b\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u001f\u0010\u0013\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0013\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u001a\u001a\u0004\b\u0017\u0010\u001bR\u001f\u0010\u0013\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u001c\u001a\u0004\b\u0017\u0010\u001dR\u001f\u0010\u001e\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018R\u001f\u0010\u001e\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001bR\u001f\u0010\u001e\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001dR\u001f\u0010!\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u0016\u001a\u0004\b#\u0010\u0018R\u001f\u0010!\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u001a\u001a\u0004\b#\u0010\u001bR\u001f\u0010!\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u001dR\u001f\u0010$\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u0018R\u001f\u0010$\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u001a\u001a\u0004\b&\u0010\u001bR\u001f\u0010$\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001dR\u001f\u0010'\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\u0016\u001a\u0004\b)\u0010\u0018R\u001f\u0010'\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\u001a\u001a\u0004\b)\u0010\u001bR\u001f\u0010'\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\u001c\u001a\u0004\b)\u0010\u001dR\u001f\u0010*\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u0018R\u001f\u0010*\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b+\u0010\u001a\u001a\u0004\b,\u0010\u001bR\u001f\u0010*\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b+\u0010\u001c\u001a\u0004\b,\u0010\u001dR\u001f\u0010-\u001a\u00020\u0005*\u00020\u00148Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b.\u0010\u0016\u001a\u0004\b/\u0010\u0018R\u001f\u0010-\u001a\u00020\u0005*\u00020\u00198Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b.\u0010\u001a\u001a\u0004\b/\u0010\u001bR\u001f\u0010-\u001a\u00020\u0005*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b.\u0010\u001c\u001a\u0004\b/\u0010\u001d¨\u0006:"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "<init>", "()V", "ZERO", "Lkotlin/time/Duration;", "getZERO-UwyO8pc", "()J", "J", "INFINITE", "getINFINITE-UwyO8pc", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "convert", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "nanoseconds", "", "getNanoseconds-UwyO8pc$annotations", "(I)V", "getNanoseconds-UwyO8pc", "(I)J", "", "(J)V", "(J)J", "(D)V", "(D)J", "microseconds", "getMicroseconds-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "milliseconds", "getMilliseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "seconds", "getSeconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "minutes", "getMinutes-UwyO8pc$annotations", "getMinutes-UwyO8pc", "hours", "getHours-UwyO8pc$annotations", "getHours-UwyO8pc", "days", "getDays-UwyO8pc$annotations", "getDays-UwyO8pc", "parse", "", "parse-UwyO8pc", "(Ljava/lang/String;)J", "parseIsoString", "parseIsoString-UwyO8pc", "parseOrNull", "parseOrNull-FghU774", "parseIsoStringOrNull", "parseIsoStringOrNull-FghU774", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8605getDaysUwyO8pc$annotations(double d) {
        }

        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8606getDaysUwyO8pc$annotations(int i) {
        }

        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8607getDaysUwyO8pc$annotations(long j) {
        }

        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8611getHoursUwyO8pc$annotations(double d) {
        }

        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8612getHoursUwyO8pc$annotations(int i) {
        }

        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8613getHoursUwyO8pc$annotations(long j) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8617getMicrosecondsUwyO8pc$annotations(double d) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8618getMicrosecondsUwyO8pc$annotations(int i) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8619getMicrosecondsUwyO8pc$annotations(long j) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8623getMillisecondsUwyO8pc$annotations(double d) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8624getMillisecondsUwyO8pc$annotations(int i) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8625getMillisecondsUwyO8pc$annotations(long j) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8629getMinutesUwyO8pc$annotations(double d) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8630getMinutesUwyO8pc$annotations(int i) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8631getMinutesUwyO8pc$annotations(long j) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8635getNanosecondsUwyO8pc$annotations(double d) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8636getNanosecondsUwyO8pc$annotations(int i) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8637getNanosecondsUwyO8pc$annotations(long j) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8641getSecondsUwyO8pc$annotations(double d) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8642getSecondsUwyO8pc$annotations(int i) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m8643getSecondsUwyO8pc$annotations(long j) {
        }

        private Companion() {
        }

        /* renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final long m8646getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final long m8644getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m8645getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        public final double convert(double value, DurationUnit sourceUnit, DurationUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m8633getNanosecondsUwyO8pc(int $this$nanoseconds) {
            return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m8634getNanosecondsUwyO8pc(long $this$nanoseconds) {
            return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m8632getNanosecondsUwyO8pc(double $this$nanoseconds) {
            return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m8615getMicrosecondsUwyO8pc(int $this$microseconds) {
            return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m8616getMicrosecondsUwyO8pc(long $this$microseconds) {
            return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m8614getMicrosecondsUwyO8pc(double $this$microseconds) {
            return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m8621getMillisecondsUwyO8pc(int $this$milliseconds) {
            return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m8622getMillisecondsUwyO8pc(long $this$milliseconds) {
            return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m8620getMillisecondsUwyO8pc(double $this$milliseconds) {
            return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m8639getSecondsUwyO8pc(int $this$seconds) {
            return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m8640getSecondsUwyO8pc(long $this$seconds) {
            return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m8638getSecondsUwyO8pc(double $this$seconds) {
            return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m8627getMinutesUwyO8pc(int $this$minutes) {
            return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m8628getMinutesUwyO8pc(long $this$minutes) {
            return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m8626getMinutesUwyO8pc(double $this$minutes) {
            return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m8609getHoursUwyO8pc(int $this$hours) {
            return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m8610getHoursUwyO8pc(long $this$hours) {
            return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m8608getHoursUwyO8pc(double $this$hours) {
            return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m8603getDaysUwyO8pc(int $this$days) {
            return DurationKt.toDuration($this$days, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m8604getDaysUwyO8pc(long $this$days) {
            return DurationKt.toDuration($this$days, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m8602getDaysUwyO8pc(double $this$days) {
            return DurationKt.toDuration($this$days, DurationUnit.DAYS);
        }

        /* renamed from: parse-UwyO8pc, reason: not valid java name */
        public final long m8647parseUwyO8pc(String value) {
            long parseDuration;
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                parseDuration = DurationKt.parseDuration(value, false);
                return parseDuration;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid duration string format: '" + value + "'.", e);
            }
        }

        /* renamed from: parseIsoString-UwyO8pc, reason: not valid java name */
        public final long m8648parseIsoStringUwyO8pc(String value) {
            long parseDuration;
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                parseDuration = DurationKt.parseDuration(value, true);
                return parseDuration;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", e);
            }
        }

        /* renamed from: parseOrNull-FghU774, reason: not valid java name */
        public final Duration m8650parseOrNullFghU774(String value) {
            long parseDuration;
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                parseDuration = DurationKt.parseDuration(value, false);
                return Duration.m8553boximpl(parseDuration);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        /* renamed from: parseIsoStringOrNull-FghU774, reason: not valid java name */
        public final Duration m8649parseIsoStringOrNullFghU774(String value) {
            long parseDuration;
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                parseDuration = DurationKt.parseDuration(value, true);
                return Duration.m8553boximpl(parseDuration);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    static {
        long durationOfMillis;
        long durationOfMillis2;
        durationOfMillis = DurationKt.durationOfMillis(DurationKt.MAX_MILLIS);
        INFINITE = durationOfMillis;
        durationOfMillis2 = DurationKt.durationOfMillis(-4611686018427387903L);
        NEG_INFINITE = durationOfMillis2;
    }

    /* renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final long m8599unaryMinusUwyO8pc(long arg0) {
        long durationOf;
        durationOf = DurationKt.durationOf(-m8575getValueimpl(arg0), ((int) arg0) & 1);
        return durationOf;
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m8584plusLRDsOJo(long arg0, long other) {
        long durationOfMillisNormalized;
        long durationOfNanosNormalized;
        if (m8580isInfiniteimpl(arg0)) {
            if (m8577isFiniteimpl(other) || (arg0 ^ other) >= 0) {
                return arg0;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (m8580isInfiniteimpl(other)) {
            return other;
        }
        if ((((int) arg0) & 1) == (((int) other) & 1)) {
            long result = m8575getValueimpl(arg0) + m8575getValueimpl(other);
            if (m8579isInNanosimpl(arg0)) {
                durationOfNanosNormalized = DurationKt.durationOfNanosNormalized(result);
                return durationOfNanosNormalized;
            }
            durationOfMillisNormalized = DurationKt.durationOfMillisNormalized(result);
            return durationOfMillisNormalized;
        }
        if (m8578isInMillisimpl(arg0)) {
            return m8551addValuesMixedRangesUwyO8pc(arg0, m8575getValueimpl(arg0), m8575getValueimpl(other));
        }
        return m8551addValuesMixedRangesUwyO8pc(arg0, m8575getValueimpl(other), m8575getValueimpl(arg0));
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    private static final long m8551addValuesMixedRangesUwyO8pc(long arg0, long thisMillis, long otherNanos) {
        long otherMillis;
        long durationOfMillis;
        long millisToNanos;
        long millisToNanos2;
        long otherNanoRemainder;
        otherMillis = DurationKt.nanosToMillis(otherNanos);
        long resultMillis = thisMillis + otherMillis;
        boolean z = false;
        if (-4611686018426L <= resultMillis && resultMillis < 4611686018427L) {
            z = true;
        }
        if (z) {
            millisToNanos = DurationKt.millisToNanos(otherMillis);
            long otherNanoRemainder2 = otherNanos - millisToNanos;
            millisToNanos2 = DurationKt.millisToNanos(resultMillis);
            otherNanoRemainder = DurationKt.durationOfNanos(millisToNanos2 + otherNanoRemainder2);
            return otherNanoRemainder;
        }
        durationOfMillis = DurationKt.durationOfMillis(RangesKt.coerceIn(resultMillis, -4611686018427387903L, DurationKt.MAX_MILLIS));
        return durationOfMillis;
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final long m8583minusLRDsOJo(long arg0, long other) {
        return m8584plusLRDsOJo(arg0, m8599unaryMinusUwyO8pc(other));
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m8586timesUwyO8pc(long arg0, int scale) {
        long durationOfMillis;
        long millis;
        long millisToNanos;
        long nanosToMillis;
        long durationOfMillis2;
        long durationOfNanosNormalized;
        long durationOfNanos;
        if (m8580isInfiniteimpl(arg0)) {
            if (scale != 0) {
                return scale > 0 ? arg0 : m8599unaryMinusUwyO8pc(arg0);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        }
        if (scale == 0) {
            return ZERO;
        }
        long value = m8575getValueimpl(arg0);
        long result = scale * value;
        if (!m8579isInNanosimpl(arg0)) {
            if (result / scale != value) {
                return MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE;
            }
            durationOfMillis = DurationKt.durationOfMillis(RangesKt.coerceIn(result, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            return durationOfMillis;
        }
        boolean z = false;
        if (-2147483647L <= value && value < 2147483648L) {
            z = true;
        }
        if (z) {
            durationOfNanos = DurationKt.durationOfNanos(result);
            return durationOfNanos;
        }
        if (result / scale == value) {
            durationOfNanosNormalized = DurationKt.durationOfNanosNormalized(result);
            return durationOfNanosNormalized;
        }
        millis = DurationKt.nanosToMillis(value);
        millisToNanos = DurationKt.millisToNanos(millis);
        long remNanos = value - millisToNanos;
        long resultMillis = scale * millis;
        nanosToMillis = DurationKt.nanosToMillis(scale * remNanos);
        long totalMillis = nanosToMillis + resultMillis;
        if (resultMillis / scale != millis || (totalMillis ^ resultMillis) < 0) {
            return MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE;
        }
        durationOfMillis2 = DurationKt.durationOfMillis(RangesKt.coerceIn(totalMillis, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
        return durationOfMillis2;
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m8585timesUwyO8pc(long arg0, double scale) {
        int intScale = MathKt.roundToInt(scale);
        if (((double) intScale) == scale) {
            return m8586timesUwyO8pc(arg0, intScale);
        }
        DurationUnit unit = m8573getStorageUnitimpl(arg0);
        double result = m8591toDoubleimpl(arg0, unit) * scale;
        return DurationKt.toDuration(result, unit);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m8558divUwyO8pc(long arg0, int scale) {
        long rem;
        long millisToNanos;
        long millisToNanos2;
        long durationOfNanos;
        long durationOfNanos2;
        if (scale == 0) {
            if (m8582isPositiveimpl(arg0)) {
                return INFINITE;
            }
            if (m8581isNegativeimpl(arg0)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        }
        if (m8579isInNanosimpl(arg0)) {
            durationOfNanos2 = DurationKt.durationOfNanos(m8575getValueimpl(arg0) / scale);
            return durationOfNanos2;
        }
        if (m8580isInfiniteimpl(arg0)) {
            return m8586timesUwyO8pc(arg0, MathKt.getSign(scale));
        }
        long result = m8575getValueimpl(arg0) / scale;
        boolean z = false;
        if (-4611686018426L <= result && result < 4611686018427L) {
            z = true;
        }
        if (z) {
            millisToNanos = DurationKt.millisToNanos(m8575getValueimpl(arg0) - (scale * result));
            long rem2 = millisToNanos / scale;
            millisToNanos2 = DurationKt.millisToNanos(result);
            durationOfNanos = DurationKt.durationOfNanos(millisToNanos2 + rem2);
            return durationOfNanos;
        }
        rem = DurationKt.durationOfMillis(result);
        return rem;
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m8557divUwyO8pc(long arg0, double scale) {
        int intScale = MathKt.roundToInt(scale);
        if ((((double) intScale) == scale) && intScale != 0) {
            return m8558divUwyO8pc(arg0, intScale);
        }
        DurationUnit unit = m8573getStorageUnitimpl(arg0);
        double result = m8591toDoubleimpl(arg0, unit) / scale;
        return DurationKt.toDuration(result, unit);
    }

    /* renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m8556divLRDsOJo(long arg0, long other) {
        DurationUnit coarserUnit = (DurationUnit) ComparisonsKt.maxOf(m8573getStorageUnitimpl(arg0), m8573getStorageUnitimpl(other));
        return m8591toDoubleimpl(arg0, coarserUnit) / m8591toDoubleimpl(other, coarserUnit);
    }

    /* renamed from: truncateTo-UwyO8pc$kotlin_stdlib, reason: not valid java name */
    public static final long m8598truncateToUwyO8pc$kotlin_stdlib(long arg0, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        DurationUnit storageUnit = m8573getStorageUnitimpl(arg0);
        if (unit.compareTo(storageUnit) <= 0 || m8580isInfiniteimpl(arg0)) {
            return arg0;
        }
        long scale = DurationUnitKt.convertDurationUnit(1L, unit, storageUnit);
        long result = m8575getValueimpl(arg0) - (m8575getValueimpl(arg0) % scale);
        return DurationKt.toDuration(result, storageUnit);
    }

    /* renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m8581isNegativeimpl(long arg0) {
        return arg0 < 0;
    }

    /* renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m8582isPositiveimpl(long arg0) {
        return arg0 > 0;
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m8580isInfiniteimpl(long arg0) {
        return arg0 == INFINITE || arg0 == NEG_INFINITE;
    }

    /* renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m8577isFiniteimpl(long arg0) {
        return !m8580isInfiniteimpl(arg0);
    }

    /* renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final long m8561getAbsoluteValueUwyO8pc(long arg0) {
        return m8581isNegativeimpl(arg0) ? m8599unaryMinusUwyO8pc(arg0) : arg0;
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m8600compareToLRDsOJo(long other) {
        return m8554compareToLRDsOJo(this.rawValue, other);
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m8554compareToLRDsOJo(long arg0, long other) {
        long compareBits = arg0 ^ other;
        if (compareBits < 0 || (((int) compareBits) & 1) == 0) {
            return Intrinsics.compare(arg0, other);
        }
        int r = (((int) arg0) & 1) - (((int) other) & 1);
        return m8581isNegativeimpl(arg0) ? -r : r;
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m8590toComponentsimpl(long arg0, Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m8563getInWholeDaysimpl(arg0)), Integer.valueOf(m8562getHoursComponentimpl(arg0)), Integer.valueOf(m8570getMinutesComponentimpl(arg0)), Integer.valueOf(m8572getSecondsComponentimpl(arg0)), Integer.valueOf(m8571getNanosecondsComponentimpl(arg0)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m8589toComponentsimpl(long arg0, Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m8564getInWholeHoursimpl(arg0)), Integer.valueOf(m8570getMinutesComponentimpl(arg0)), Integer.valueOf(m8572getSecondsComponentimpl(arg0)), Integer.valueOf(m8571getNanosecondsComponentimpl(arg0)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m8588toComponentsimpl(long arg0, Function3<? super Long, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m8567getInWholeMinutesimpl(arg0)), Integer.valueOf(m8572getSecondsComponentimpl(arg0)), Integer.valueOf(m8571getNanosecondsComponentimpl(arg0)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m8587toComponentsimpl(long arg0, Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m8569getInWholeSecondsimpl(arg0)), Integer.valueOf(m8571getNanosecondsComponentimpl(arg0)));
    }

    /* renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m8562getHoursComponentimpl(long arg0) {
        if (m8580isInfiniteimpl(arg0)) {
            return 0;
        }
        return (int) (m8564getInWholeHoursimpl(arg0) % 24);
    }

    /* renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m8570getMinutesComponentimpl(long arg0) {
        if (m8580isInfiniteimpl(arg0)) {
            return 0;
        }
        return (int) (m8567getInWholeMinutesimpl(arg0) % 60);
    }

    /* renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m8572getSecondsComponentimpl(long arg0) {
        if (m8580isInfiniteimpl(arg0)) {
            return 0;
        }
        return (int) (m8569getInWholeSecondsimpl(arg0) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m8571getNanosecondsComponentimpl(long arg0) {
        long millisToNanos;
        if (m8580isInfiniteimpl(arg0)) {
            return 0;
        }
        if (m8578isInMillisimpl(arg0)) {
            millisToNanos = DurationKt.millisToNanos(m8575getValueimpl(arg0) % 1000);
            return (int) millisToNanos;
        }
        return (int) (m8575getValueimpl(arg0) % 1000000000);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m8591toDoubleimpl(long arg0, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (arg0 == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (arg0 == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt.convertDurationUnit(m8575getValueimpl(arg0), m8573getStorageUnitimpl(arg0), unit);
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m8594toLongimpl(long arg0, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (arg0 == INFINITE) {
            return Long.MAX_VALUE;
        }
        if (arg0 == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt.convertDurationUnit(m8575getValueimpl(arg0), m8573getStorageUnitimpl(arg0), unit);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    public static final int m8592toIntimpl(long arg0, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) RangesKt.coerceIn(m8594toLongimpl(arg0, unit), -2147483648L, 2147483647L);
    }

    /* renamed from: getInWholeDays-impl, reason: not valid java name */
    public static final long m8563getInWholeDaysimpl(long arg0) {
        return m8594toLongimpl(arg0, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl, reason: not valid java name */
    public static final long m8564getInWholeHoursimpl(long arg0) {
        return m8594toLongimpl(arg0, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMinutes-impl, reason: not valid java name */
    public static final long m8567getInWholeMinutesimpl(long arg0) {
        return m8594toLongimpl(arg0, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeSeconds-impl, reason: not valid java name */
    public static final long m8569getInWholeSecondsimpl(long arg0) {
        return m8594toLongimpl(arg0, DurationUnit.SECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl, reason: not valid java name */
    public static final long m8566getInWholeMillisecondsimpl(long arg0) {
        return (m8578isInMillisimpl(arg0) && m8577isFiniteimpl(arg0)) ? m8575getValueimpl(arg0) : m8594toLongimpl(arg0, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMicroseconds-impl, reason: not valid java name */
    public static final long m8565getInWholeMicrosecondsimpl(long arg0) {
        return m8594toLongimpl(arg0, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeNanoseconds-impl, reason: not valid java name */
    public static final long m8568getInWholeNanosecondsimpl(long arg0) {
        long millisToNanos;
        long value = m8575getValueimpl(arg0);
        if (m8579isInNanosimpl(arg0)) {
            return value;
        }
        if (value > 9223372036854L) {
            return Long.MAX_VALUE;
        }
        if (value < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        millisToNanos = DurationKt.millisToNanos(value);
        return millisToNanos;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m8595toStringimpl(long arg0) {
        boolean hasDays;
        int i;
        int components;
        int components2;
        if (arg0 == 0) {
            return "0s";
        }
        if (arg0 == INFINITE) {
            return "Infinity";
        }
        if (arg0 == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean isNegative = m8581isNegativeimpl(arg0);
        StringBuilder $this$toString_impl_u24lambda_u241 = new StringBuilder();
        if (isNegative) {
            $this$toString_impl_u24lambda_u241.append('-');
        }
        long arg0$iv = m8561getAbsoluteValueUwyO8pc(arg0);
        long days = m8563getInWholeDaysimpl(arg0$iv);
        int hours = m8562getHoursComponentimpl(arg0$iv);
        int minutes = m8570getMinutesComponentimpl(arg0$iv);
        int seconds = m8572getSecondsComponentimpl(arg0$iv);
        int nanoseconds = m8571getNanosecondsComponentimpl(arg0$iv);
        boolean hasDays2 = days != 0;
        boolean hasHours = hours != 0;
        boolean hasMinutes = minutes != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        int components3 = 0;
        if (!hasDays2) {
            hasDays = hasDays2;
        } else {
            hasDays = hasDays2;
            $this$toString_impl_u24lambda_u241.append(days).append('d');
            components3 = 0 + 1;
        }
        if (hasHours || (hasDays && (hasMinutes || hasSeconds))) {
            int components4 = components3 + 1;
            if (components3 > 0) {
                $this$toString_impl_u24lambda_u241.append(' ');
            }
            $this$toString_impl_u24lambda_u241.append(hours).append('h');
            components3 = components4;
        }
        if (hasMinutes || (hasSeconds && (hasHours || hasDays))) {
            int components5 = components3 + 1;
            if (components3 > 0) {
                $this$toString_impl_u24lambda_u241.append(' ');
            }
            $this$toString_impl_u24lambda_u241.append(minutes).append('m');
            components3 = components5;
        }
        if (hasSeconds) {
            int components6 = components3 + 1;
            if (components3 > 0) {
                $this$toString_impl_u24lambda_u241.append(' ');
            }
            if (seconds != 0 || hasDays || hasHours) {
                components = components6;
                components2 = nanoseconds;
                i = 1;
            } else if (hasMinutes) {
                components = components6;
                components2 = nanoseconds;
                i = 1;
            } else {
                if (nanoseconds >= 1000000) {
                    components = components6;
                    i = 1;
                    m8552appendFractionalimpl(arg0, $this$toString_impl_u24lambda_u241, nanoseconds / DurationKt.NANOS_IN_MILLIS, nanoseconds % DurationKt.NANOS_IN_MILLIS, 6, "ms", false);
                } else {
                    components = components6;
                    i = 1;
                    if (nanoseconds >= 1000) {
                        m8552appendFractionalimpl(arg0, $this$toString_impl_u24lambda_u241, nanoseconds / 1000, nanoseconds % 1000, 3, "us", false);
                    } else {
                        $this$toString_impl_u24lambda_u241.append(nanoseconds).append("ns");
                    }
                }
                components3 = components;
            }
            m8552appendFractionalimpl(arg0, $this$toString_impl_u24lambda_u241, seconds, components2, 9, "s", false);
            components3 = components;
        } else {
            i = 1;
        }
        if (isNegative && components3 > i) {
            $this$toString_impl_u24lambda_u241.insert(i, '(').append(')');
        }
        return $this$toString_impl_u24lambda_u241.toString();
    }

    public String toString() {
        return m8595toStringimpl(this.rawValue);
    }

    /* renamed from: appendFractional-impl, reason: not valid java name */
    private static final void m8552appendFractionalimpl(long arg0, StringBuilder $this$appendFractional, int whole, int fractional, int fractionalSize, String unit, boolean isoZeroes) {
        $this$appendFractional.append(whole);
        if (fractional != 0) {
            $this$appendFractional.append('.');
            CharSequence fracString = StringsKt.padStart(String.valueOf(fractional), fractionalSize, '0');
            CharSequence $this$indexOfLast$iv = fracString;
            int i = -1;
            int length = $this$indexOfLast$iv.length() - 1;
            if (length >= 0) {
                while (true) {
                    int index$iv = length;
                    length--;
                    char it = $this$indexOfLast$iv.charAt(index$iv);
                    char it2 = it != '0' ? (char) 1 : (char) 0;
                    if (it2 == 0) {
                        if (length < 0) {
                            break;
                        }
                    } else {
                        i = index$iv;
                        break;
                    }
                }
            }
            int nonZeroDigits = i + 1;
            if (isoZeroes || nonZeroDigits >= 3) {
                Intrinsics.checkNotNullExpressionValue($this$appendFractional.append(fracString, 0, ((nonZeroDigits + 2) / 3) * 3), "append(...)");
            } else {
                Intrinsics.checkNotNullExpressionValue($this$appendFractional.append(fracString, 0, nonZeroDigits), "append(...)");
            }
        }
        $this$appendFractional.append(unit);
    }

    /* renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m8597toStringimpl$default(long j, DurationUnit durationUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m8596toStringimpl(j, durationUnit, i);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static final String m8596toStringimpl(long arg0, DurationUnit unit, int decimals) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(decimals >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + decimals).toString());
        }
        double number = m8591toDoubleimpl(arg0, unit);
        return Double.isInfinite(number) ? String.valueOf(number) : DurationJvmKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12)) + DurationUnitKt.shortName(unit);
    }

    /* renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m8593toIsoStringimpl(long arg0) {
        StringBuilder $this$toIsoString_impl_u24lambda_u245 = new StringBuilder();
        if (m8581isNegativeimpl(arg0)) {
            $this$toIsoString_impl_u24lambda_u245.append('-');
        }
        $this$toIsoString_impl_u24lambda_u245.append("PT");
        long arg0$iv = m8561getAbsoluteValueUwyO8pc(arg0);
        long hours = m8564getInWholeHoursimpl(arg0$iv);
        int minutes = m8570getMinutesComponentimpl(arg0$iv);
        int seconds = m8572getSecondsComponentimpl(arg0$iv);
        int nanoseconds = m8571getNanosecondsComponentimpl(arg0$iv);
        if (m8580isInfiniteimpl(arg0)) {
            hours = 9999999999999L;
        }
        boolean z = true;
        boolean hasHours = hours != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        if (minutes == 0 && (!hasSeconds || !hasHours)) {
            z = false;
        }
        boolean hasMinutes = z;
        if (hasHours) {
            $this$toIsoString_impl_u24lambda_u245.append(hours).append('H');
        }
        if (hasMinutes) {
            $this$toIsoString_impl_u24lambda_u245.append(minutes).append('M');
        }
        if (hasSeconds || (!hasHours && !hasMinutes)) {
            m8552appendFractionalimpl(arg0, $this$toIsoString_impl_u24lambda_u245, seconds, nanoseconds, 9, "S", true);
        }
        return $this$toIsoString_impl_u24lambda_u245.toString();
    }
}
