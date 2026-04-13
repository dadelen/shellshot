package com.example.shellshot.media;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: ScreenshotDeduplicator.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J*\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u0012\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\tH\u0002J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\bH\u0002J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDeduplicator;", "", "<init>", "()V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "inFlightPaths", "", "", "", "processedPaths", "processedUris", "ignorePaths", "tryBegin", "Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision;", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishProcessing", "", "markProcessed", "outputPath", "outputUri", "Landroid/net/Uri;", "(Lcom/example/shellshot/media/ScreenshotCandidate;Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupExpiredLocked", "now", "normalizePath", "path", "normalizeUri", "uri", "BeginDecision", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ScreenshotDeduplicator {

    @Deprecated
    public static final long WINDOW_MILLIS = 10000;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);
    private final Map<String, Long> inFlightPaths = new LinkedHashMap();
    private final Map<String, Long> processedPaths = new LinkedHashMap();
    private final Map<String, Long> processedUris = new LinkedHashMap();
    private final Map<String, Long> ignorePaths = new LinkedHashMap();

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0067 A[Catch: all -> 0x00d8, TryCatch #0 {all -> 0x00d8, blocks: (B:14:0x005a, B:16:0x0067, B:19:0x006c, B:21:0x007d, B:23:0x0087, B:25:0x008f, B:26:0x0099, B:28:0x00a1, B:30:0x00ad, B:32:0x00b5, B:33:0x00bf), top: B:13:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006c A[Catch: all -> 0x00d8, TryCatch #0 {all -> 0x00d8, blocks: (B:14:0x005a, B:16:0x0067, B:19:0x006c, B:21:0x007d, B:23:0x0087, B:25:0x008f, B:26:0x0099, B:28:0x00a1, B:30:0x00ad, B:32:0x00b5, B:33:0x00bf), top: B:13:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryBegin(com.example.shellshot.media.ScreenshotCandidate r12, kotlin.coroutines.Continuation<? super com.example.shellshot.media.ScreenshotDeduplicator.BeginDecision> r13) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDeduplicator.tryBegin(com.example.shellshot.media.ScreenshotCandidate, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[Catch: all -> 0x0077, TryCatch #0 {all -> 0x0077, blocks: (B:14:0x0057, B:17:0x006e, B:21:0x0062), top: B:13:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishProcessing(com.example.shellshot.media.ScreenshotCandidate r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.example.shellshot.media.ScreenshotDeduplicator$finishProcessing$1
            if (r0 == 0) goto L14
            r0 = r13
            com.example.shellshot.media.ScreenshotDeduplicator$finishProcessing$1 r0 = (com.example.shellshot.media.ScreenshotDeduplicator$finishProcessing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.media.ScreenshotDeduplicator$finishProcessing$1 r0 = new com.example.shellshot.media.ScreenshotDeduplicator$finishProcessing$1
            r0.<init>(r11, r13)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3d;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            int r2 = r0.I$0
            r3 = 0
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$0
            r12 = r6
            com.example.shellshot.media.ScreenshotCandidate r12 = (com.example.shellshot.media.ScreenshotCandidate) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L55
        L3d:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r5 = r11.mutex
            r3 = 0
            r6 = 0
            r0.L$0 = r12
            r0.L$1 = r5
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r7 = r5.lock(r3, r0)
            if (r7 != r2) goto L54
            return r2
        L54:
            r2 = r6
        L55:
            r6 = 0
            java.lang.String r7 = r12.getAbsolutePath()     // Catch: java.lang.Throwable -> L77
            java.lang.String r7 = r11.normalizePath(r7)     // Catch: java.lang.Throwable -> L77
            if (r7 != 0) goto L62
            goto L6e
        L62:
            java.util.Map<java.lang.String, java.lang.Long> r8 = r11.inFlightPaths     // Catch: java.lang.Throwable -> L77
            r8.remove(r7)     // Catch: java.lang.Throwable -> L77
            r8 = 0
            r10 = 0
            cleanupExpiredLocked$default(r11, r8, r4, r10)     // Catch: java.lang.Throwable -> L77
        L6e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L77
            r5.unlock(r3)
            return r4
        L77:
            r4 = move-exception
            r5.unlock(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDeduplicator.finishProcessing(com.example.shellshot.media.ScreenshotCandidate, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0090 A[Catch: all -> 0x00eb, TryCatch #0 {all -> 0x00eb, blocks: (B:14:0x007f, B:16:0x0090, B:17:0x00a5, B:19:0x00b0, B:20:0x00bb, B:22:0x00c2, B:23:0x00cd, B:25:0x00d4), top: B:13:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b0 A[Catch: all -> 0x00eb, TryCatch #0 {all -> 0x00eb, blocks: (B:14:0x007f, B:16:0x0090, B:17:0x00a5, B:19:0x00b0, B:20:0x00bb, B:22:0x00c2, B:23:0x00cd, B:25:0x00d4), top: B:13:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c2 A[Catch: all -> 0x00eb, TryCatch #0 {all -> 0x00eb, blocks: (B:14:0x007f, B:16:0x0090, B:17:0x00a5, B:19:0x00b0, B:20:0x00bb, B:22:0x00c2, B:23:0x00cd, B:25:0x00d4), top: B:13:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d4 A[Catch: all -> 0x00eb, TRY_LEAVE, TryCatch #0 {all -> 0x00eb, blocks: (B:14:0x007f, B:16:0x0090, B:17:0x00a5, B:19:0x00b0, B:20:0x00bb, B:22:0x00c2, B:23:0x00cd, B:25:0x00d4), top: B:13:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object markProcessed(com.example.shellshot.media.ScreenshotCandidate r18, java.lang.String r19, android.net.Uri r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDeduplicator.markProcessed(com.example.shellshot.media.ScreenshotCandidate, java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ void cleanupExpiredLocked$default(ScreenshotDeduplicator screenshotDeduplicator, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        screenshotDeduplicator.cleanupExpiredLocked(j);
    }

    private final void cleanupExpiredLocked(final long now) {
        CollectionsKt.removeAll(this.inFlightPaths.entrySet(), new Function1() { // from class: com.example.shellshot.media.ScreenshotDeduplicator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ScreenshotDeduplicator.cleanupExpiredLocked$lambda$7(now, (Map.Entry) obj));
            }
        });
        CollectionsKt.removeAll(this.processedPaths.entrySet(), new Function1() { // from class: com.example.shellshot.media.ScreenshotDeduplicator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ScreenshotDeduplicator.cleanupExpiredLocked$lambda$8(now, (Map.Entry) obj));
            }
        });
        CollectionsKt.removeAll(this.processedUris.entrySet(), new Function1() { // from class: com.example.shellshot.media.ScreenshotDeduplicator$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ScreenshotDeduplicator.cleanupExpiredLocked$lambda$9(now, (Map.Entry) obj));
            }
        });
        CollectionsKt.removeAll(this.ignorePaths.entrySet(), new Function1() { // from class: com.example.shellshot.media.ScreenshotDeduplicator$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ScreenshotDeduplicator.cleanupExpiredLocked$lambda$10(now, (Map.Entry) obj));
            }
        });
    }

    static final boolean cleanupExpiredLocked$lambda$7(long $now, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $now - ((Number) it.getValue()).longValue() > WINDOW_MILLIS;
    }

    static final boolean cleanupExpiredLocked$lambda$8(long $now, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $now - ((Number) it.getValue()).longValue() > WINDOW_MILLIS;
    }

    static final boolean cleanupExpiredLocked$lambda$9(long $now, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $now - ((Number) it.getValue()).longValue() > WINDOW_MILLIS;
    }

    static final boolean cleanupExpiredLocked$lambda$10(long $now, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $now - ((Number) it.getValue()).longValue() > WINDOW_MILLIS;
    }

    private final String normalizePath(String path) {
        return ScreenshotDirectories.INSTANCE.normalizeAbsolutePath(path);
    }

    private final String normalizeUri(Uri uri) {
        String uri2;
        String obj;
        if (uri == null || (uri2 = uri.toString()) == null || (obj = StringsKt.trim((CharSequence) uri2).toString()) == null) {
            return null;
        }
        String lowerCase = obj.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (lowerCase == null || StringsKt.isBlank(lowerCase)) {
            return null;
        }
        return lowerCase;
    }

    /* compiled from: ScreenshotDeduplicator.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision;", "", "Accepted", "Skipped", "Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision$Accepted;", "Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision$Skipped;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface BeginDecision {

        /* compiled from: ScreenshotDeduplicator.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision$Accepted;", "Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Accepted implements BeginDecision {
            public static final int $stable = 0;
            public static final Accepted INSTANCE = new Accepted();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Accepted)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -1587121189;
            }

            public String toString() {
                return "Accepted";
            }

            private Accepted() {
            }
        }

        /* compiled from: ScreenshotDeduplicator.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision$Skipped;", "Lcom/example/shellshot/media/ScreenshotDeduplicator$BeginDecision;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Skipped implements BeginDecision {
            public static final int $stable = 0;
            private final String reason;

            public static /* synthetic */ Skipped copy$default(Skipped skipped, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = skipped.reason;
                }
                return skipped.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            public final Skipped copy(String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Skipped(reason);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Skipped) && Intrinsics.areEqual(this.reason, ((Skipped) other).reason);
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            public String toString() {
                return "Skipped(reason=" + this.reason + ")";
            }

            public Skipped(String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
            }

            public final String getReason() {
                return this.reason;
            }
        }
    }

    /* compiled from: ScreenshotDeduplicator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDeduplicator$Companion;", "", "<init>", "()V", "WINDOW_MILLIS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
