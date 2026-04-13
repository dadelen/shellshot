package com.example.shellshot.media;

import android.graphics.BitmapFactory;
import android.os.SystemClock;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ScreenshotStabilityChecker.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 !2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086@¢\u0006\u0002\u0010\rJL\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J\u001c\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker;", "", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Lcom/example/shellshot/utils/ShellLogger;)V", "awaitStableCandidate", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "shouldContinue", "Lkotlin/Function0;", "", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildFailureReason", "", "exists", "readable", "size", "", "sizeStable", "bounds", "Lkotlin/Pair;", "", "boundsValid", "readableStreak", "decodeBounds", "file", "Ljava/io/File;", "remainingDelayMillis", "startedAtElapsedRealtime", "targetOffsetMillis", "Result", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ScreenshotStabilityChecker {

    @Deprecated
    public static final String PASS_REASON_READABLE_TWICE_AND_BOUNDS = "readable_twice_and_bounds";

    @Deprecated
    public static final String PASS_REASON_SIZE_STABLE_AND_BOUNDS = "size_stable_and_bounds";

    @Deprecated
    public static final String TAG = "ShotStability";
    private final ShellLogger logger;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = ShellLogger.$stable;
    private static final long[] ATTEMPT_SCHEDULE_MILLIS = {0, 150, 350, 700, 1200, 2000};

    public ScreenshotStabilityChecker(ShellLogger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
    }

    /* compiled from: ScreenshotStabilityChecker.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "", "Stable", "Suspended", "Unstable", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Stable;", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Suspended;", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Unstable;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Result {

        /* compiled from: ScreenshotStabilityChecker.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Stable;", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "<init>", "(Lcom/example/shellshot/media/ScreenshotCandidate;)V", "getCandidate", "()Lcom/example/shellshot/media/ScreenshotCandidate;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Stable implements Result {
            public static final int $stable = 8;
            private final ScreenshotCandidate candidate;

            public static /* synthetic */ Stable copy$default(Stable stable, ScreenshotCandidate screenshotCandidate, int i, Object obj) {
                if ((i & 1) != 0) {
                    screenshotCandidate = stable.candidate;
                }
                return stable.copy(screenshotCandidate);
            }

            /* renamed from: component1, reason: from getter */
            public final ScreenshotCandidate getCandidate() {
                return this.candidate;
            }

            public final Stable copy(ScreenshotCandidate candidate) {
                Intrinsics.checkNotNullParameter(candidate, "candidate");
                return new Stable(candidate);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Stable) && Intrinsics.areEqual(this.candidate, ((Stable) other).candidate);
            }

            public int hashCode() {
                return this.candidate.hashCode();
            }

            public String toString() {
                return "Stable(candidate=" + this.candidate + ")";
            }

            public Stable(ScreenshotCandidate candidate) {
                Intrinsics.checkNotNullParameter(candidate, "candidate");
                this.candidate = candidate;
            }

            public final ScreenshotCandidate getCandidate() {
                return this.candidate;
            }
        }

        /* compiled from: ScreenshotStabilityChecker.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Suspended;", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Suspended implements Result {
            public static final int $stable = 0;
            public static final Suspended INSTANCE = new Suspended();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Suspended)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return 734812935;
            }

            public String toString() {
                return "Suspended";
            }

            private Suspended() {
            }
        }

        /* compiled from: ScreenshotStabilityChecker.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result$Unstable;", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Unstable implements Result {
            public static final int $stable = 0;
            public static final Unstable INSTANCE = new Unstable();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Unstable)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -1452711832;
            }

            public String toString() {
                return "Unstable";
            }

            private Unstable() {
            }
        }
    }

    public final Object awaitStableCandidate(ScreenshotCandidate candidate, Function0<Boolean> function0, Continuation<? super Result> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ScreenshotStabilityChecker$awaitStableCandidate$2(function0, this, candidate, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildFailureReason(boolean exists, boolean readable, long size, boolean sizeStable, Pair<Integer, Integer> bounds, boolean boundsValid, int readableStreak) {
        List createListBuilder = CollectionsKt.createListBuilder();
        if (!exists) {
            createListBuilder.add("missing");
        }
        if (!readable) {
            createListBuilder.add("not_readable");
        }
        if (size <= 0) {
            createListBuilder.add("size_zero");
        }
        if (!sizeStable) {
            createListBuilder.add("size_not_stable");
        }
        if (!boundsValid) {
            createListBuilder.add("bounds_invalid_" + bounds.getFirst() + "x" + bounds.getSecond());
        }
        if (readableStreak < 2) {
            createListBuilder.add("readable_streak_" + readableStreak);
        }
        return CollectionsKt.joinToString$default(CollectionsKt.build(createListBuilder), ",", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, Integer> decodeBounds(File file) {
        Object m7212constructorimpl;
        if (!file.exists() || !file.canRead()) {
            return TuplesKt.to(0, 0);
        }
        try {
            Result.Companion companion = kotlin.Result.INSTANCE;
            ScreenshotStabilityChecker screenshotStabilityChecker = this;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            m7212constructorimpl = kotlin.Result.m7212constructorimpl(TuplesKt.to(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight)));
        } catch (Throwable th) {
            Result.Companion companion2 = kotlin.Result.INSTANCE;
            m7212constructorimpl = kotlin.Result.m7212constructorimpl(ResultKt.createFailure(th));
        }
        Pair pair = TuplesKt.to(0, 0);
        if (kotlin.Result.m7218isFailureimpl(m7212constructorimpl)) {
            m7212constructorimpl = pair;
        }
        return (Pair) m7212constructorimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long remainingDelayMillis(long startedAtElapsedRealtime, long targetOffsetMillis) {
        long elapsedMillis = SystemClock.elapsedRealtime() - startedAtElapsedRealtime;
        return RangesKt.coerceAtLeast(targetOffsetMillis - elapsedMillis, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScreenshotStabilityChecker.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotStabilityChecker$Companion;", "", "<init>", "()V", "TAG", "", "PASS_REASON_SIZE_STABLE_AND_BOUNDS", "PASS_REASON_READABLE_TWICE_AND_BOUNDS", "ATTEMPT_SCHEDULE_MILLIS", "", "getATTEMPT_SCHEDULE_MILLIS", "()[J", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long[] getATTEMPT_SCHEDULE_MILLIS() {
            return ScreenshotStabilityChecker.ATTEMPT_SCHEDULE_MILLIS;
        }
    }
}
