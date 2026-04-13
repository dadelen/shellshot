package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;

/* compiled from: TimeSource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "WithComparableMarks", "Monotonic", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes14.dex */
public interface TimeSource {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        ComparableTimeMark markNow();
    }

    TimeMark markNow();

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0004\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "<init>", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Monotonic implements WithComparableMarks {
        public static final Monotonic INSTANCE = new Monotonic();

        private Monotonic() {
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
            return ValueTimeMark.m8664boximpl(m8663markNowz9LOYto());
        }

        @Override // kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ TimeMark markNow() {
            return ValueTimeMark.m8664boximpl(m8663markNowz9LOYto());
        }

        /* renamed from: markNow-z9LOYto, reason: not valid java name */
        public long m8663markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m8658markNowz9LOYto();
        }

        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        /* compiled from: TimeSource.kt */
        @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0016\u0010\u0014J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0001H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u001a\u0010\u000eJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u001cHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0004\n\u0002\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004¨\u0006$"}, d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "J", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(JJ)J", "minus", "minus-LRDsOJo", "hasPassedNow", "", "hasPassedNow-impl", "(J)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "other", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "minus-6eNON_k", "compareTo", "", "compareTo-6eNON_k", "(JJ)I", "equals", "", "hashCode", "toString", "", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @JvmInline
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            /* renamed from: box-impl, reason: not valid java name */
            public static final /* synthetic */ ValueTimeMark m8664boximpl(long j) {
                return new ValueTimeMark(j);
            }

            /* renamed from: constructor-impl, reason: not valid java name */
            public static long m8667constructorimpl(long j) {
                return j;
            }

            /* renamed from: equals-impl, reason: not valid java name */
            public static boolean m8669equalsimpl(long j, Object obj) {
                return (obj instanceof ValueTimeMark) && j == ((ValueTimeMark) obj).getReading();
            }

            /* renamed from: equals-impl0, reason: not valid java name */
            public static final boolean m8670equalsimpl0(long j, long j2) {
                return j == j2;
            }

            /* renamed from: hashCode-impl, reason: not valid java name */
            public static int m8673hashCodeimpl(long j) {
                return Long.hashCode(j);
            }

            /* renamed from: toString-impl, reason: not valid java name */
            public static String m8678toStringimpl(long j) {
                return "ValueTimeMark(reading=" + j + ')';
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object other) {
                return m8669equalsimpl(this.reading, other);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return m8673hashCodeimpl(this.reading);
            }

            public String toString() {
                return m8678toStringimpl(this.reading);
            }

            /* renamed from: unbox-impl, reason: not valid java name and from getter */
            public final /* synthetic */ long getReading() {
                return this.reading;
            }

            /* renamed from: compareTo-impl, reason: not valid java name */
            public static int m8666compareToimpl(long arg0, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m8664boximpl(arg0).compareTo(other);
            }

            @Override // java.lang.Comparable
            public int compareTo(ComparableTimeMark other) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, other);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo8542minusLRDsOJo(long duration) {
                return m8664boximpl(m8679minusLRDsOJo(duration));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo8542minusLRDsOJo(long duration) {
                return m8664boximpl(m8679minusLRDsOJo(duration));
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo8544plusLRDsOJo(long duration) {
                return m8664boximpl(m8680plusLRDsOJo(duration));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo8544plusLRDsOJo(long duration) {
                return m8664boximpl(m8680plusLRDsOJo(duration));
            }

            private /* synthetic */ ValueTimeMark(long reading) {
                this.reading = reading;
            }

            /* renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
            public static long m8668elapsedNowUwyO8pc(long arg0) {
                return MonotonicTimeSource.INSTANCE.m8657elapsedFrom6eNON_k(arg0);
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: elapsedNow-UwyO8pc */
            public long mo8541elapsedNowUwyO8pc() {
                return m8668elapsedNowUwyO8pc(this.reading);
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public static long m8677plusLRDsOJo(long arg0, long duration) {
                return MonotonicTimeSource.INSTANCE.m8655adjustReading6QKq23U(arg0, duration);
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public long m8680plusLRDsOJo(long duration) {
                return m8677plusLRDsOJo(this.reading, duration);
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public static long m8675minusLRDsOJo(long arg0, long duration) {
                return MonotonicTimeSource.INSTANCE.m8655adjustReading6QKq23U(arg0, Duration.m8595unaryMinusUwyO8pc(duration));
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public long m8679minusLRDsOJo(long duration) {
                return m8675minusLRDsOJo(this.reading, duration);
            }

            /* renamed from: hasPassedNow-impl, reason: not valid java name */
            public static boolean m8672hasPassedNowimpl(long arg0) {
                return !Duration.m8577isNegativeimpl(m8668elapsedNowUwyO8pc(arg0));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m8672hasPassedNowimpl(this.reading);
            }

            /* renamed from: hasNotPassedNow-impl, reason: not valid java name */
            public static boolean m8671hasNotPassedNowimpl(long arg0) {
                return Duration.m8577isNegativeimpl(m8668elapsedNowUwyO8pc(arg0));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m8671hasNotPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: minus-UwyO8pc */
            public long mo8543minusUwyO8pc(ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m8676minusUwyO8pc(this.reading, other);
            }

            /* renamed from: minus-UwyO8pc, reason: not valid java name */
            public static long m8676minusUwyO8pc(long arg0, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (!(other instanceof ValueTimeMark)) {
                    throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m8678toStringimpl(arg0)) + " and " + other);
                }
                return m8674minus6eNON_k(arg0, ((ValueTimeMark) other).getReading());
            }

            /* renamed from: minus-6eNON_k, reason: not valid java name */
            public static final long m8674minus6eNON_k(long arg0, long other) {
                return MonotonicTimeSource.INSTANCE.m8656differenceBetweenfRLX17w(arg0, other);
            }

            /* renamed from: compareTo-6eNON_k, reason: not valid java name */
            public static final int m8665compareTo6eNON_k(long arg0, long other) {
                return Duration.m8550compareToLRDsOJo(m8674minus6eNON_k(arg0, other), Duration.INSTANCE.m8642getZEROUwyO8pc());
            }
        }
    }

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$Companion;", "", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
