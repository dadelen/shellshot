package com.example.shellshot.media;

import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ScreenshotDirectoryAdvisor.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDirectoryAdvisor;", "", "mediaStoreRepository", "Lcom/example/shellshot/media/MediaStoreRepository;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Lcom/example/shellshot/media/MediaStoreRepository;Lcom/example/shellshot/utils/ShellLogger;)V", "detectRecommendations", "", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "currentRelativePath", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLikelyRecommendationCandidate", "", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "relativePath", "RecommendationAccumulator", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ScreenshotDirectoryAdvisor {

    @Deprecated
    public static final int DEFAULT_RECOMMENDATION_LIMIT = 4;

    @Deprecated
    public static final int MEDIASTORE_SCAN_LIMIT = 48;

    @Deprecated
    public static final int PRESET_SCAN_SAMPLE_LIMIT = 6;

    @Deprecated
    public static final long PRESET_SCAN_WINDOW_MILLIS = 3888000000L;

    @Deprecated
    public static final String TAG = "ScreenshotDirectoryAdvisor";
    private final ShellLogger logger;
    private final MediaStoreRepository mediaStoreRepository;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public ScreenshotDirectoryAdvisor(MediaStoreRepository mediaStoreRepository, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(mediaStoreRepository, "mediaStoreRepository");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.mediaStoreRepository = mediaStoreRepository;
        this.logger = logger;
    }

    public static /* synthetic */ Object detectRecommendations$default(ScreenshotDirectoryAdvisor screenshotDirectoryAdvisor, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 4;
        }
        return screenshotDirectoryAdvisor.detectRecommendations(str, i, continuation);
    }

    public final Object detectRecommendations(String currentRelativePath, int limit, Continuation<? super List<ScreenshotDirectoryRecommendation>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ScreenshotDirectoryAdvisor$detectRecommendations$2(currentRelativePath, limit, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLikelyRecommendationCandidate(ScreenshotCandidate candidate, String relativePath) {
        if (!ScreenshotDirectories.INSTANCE.looksLikeImageFile(candidate.getDisplayName()) || candidate.isPending() || ScreenshotDirectories.INSTANCE.isGeneratedByApp(candidate.getDisplayName()) || ScreenshotDirectories.INSTANCE.isOutputLocation(candidate.getAbsolutePath(), candidate.getRelativePath(), candidate.getBucketName())) {
            return false;
        }
        if (!ScreenshotDirectories.INSTANCE.looksLikeScreenshotRelativePath(relativePath, candidate.getBucketName()) && !ScreenshotDirectories.INSTANCE.looksLikeScreenshotFileName(candidate.getDisplayName())) {
            return false;
        }
        File directory = ScreenshotDirectories.INSTANCE.toAbsoluteDirectory(relativePath);
        return directory.exists() && directory.isDirectory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScreenshotDirectoryAdvisor.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020$J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J;\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0006HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010¨\u00060"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDirectoryAdvisor$RecommendationAccumulator;", "", "relativePath", "", "absolutePath", "presetHitCount", "", "mediaStoreHitCount", "latestCapturedAtMillis", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIJ)V", "getRelativePath", "()Ljava/lang/String;", "getAbsolutePath", "getPresetHitCount", "()I", "setPresetHitCount", "(I)V", "getMediaStoreHitCount", "setMediaStoreHitCount", "getLatestCapturedAtMillis", "()J", "setLatestCapturedAtMillis", "(J)V", "score", "getScore", "notePresetHit", "", "recentFiles", "", "Ljava/io/File;", "noteMediaStoreHit", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "toRecommendation", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class RecommendationAccumulator {
        private final String absolutePath;
        private long latestCapturedAtMillis;
        private int mediaStoreHitCount;
        private int presetHitCount;
        private final String relativePath;

        public static /* synthetic */ RecommendationAccumulator copy$default(RecommendationAccumulator recommendationAccumulator, String str, String str2, int i, int i2, long j, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = recommendationAccumulator.relativePath;
            }
            if ((i3 & 2) != 0) {
                str2 = recommendationAccumulator.absolutePath;
            }
            if ((i3 & 4) != 0) {
                i = recommendationAccumulator.presetHitCount;
            }
            if ((i3 & 8) != 0) {
                i2 = recommendationAccumulator.mediaStoreHitCount;
            }
            if ((i3 & 16) != 0) {
                j = recommendationAccumulator.latestCapturedAtMillis;
            }
            long j2 = j;
            return recommendationAccumulator.copy(str, str2, i, i2, j2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getRelativePath() {
            return this.relativePath;
        }

        /* renamed from: component2, reason: from getter */
        public final String getAbsolutePath() {
            return this.absolutePath;
        }

        /* renamed from: component3, reason: from getter */
        public final int getPresetHitCount() {
            return this.presetHitCount;
        }

        /* renamed from: component4, reason: from getter */
        public final int getMediaStoreHitCount() {
            return this.mediaStoreHitCount;
        }

        /* renamed from: component5, reason: from getter */
        public final long getLatestCapturedAtMillis() {
            return this.latestCapturedAtMillis;
        }

        public final RecommendationAccumulator copy(String relativePath, String absolutePath, int presetHitCount, int mediaStoreHitCount, long latestCapturedAtMillis) {
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            return new RecommendationAccumulator(relativePath, absolutePath, presetHitCount, mediaStoreHitCount, latestCapturedAtMillis);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommendationAccumulator)) {
                return false;
            }
            RecommendationAccumulator recommendationAccumulator = (RecommendationAccumulator) other;
            return Intrinsics.areEqual(this.relativePath, recommendationAccumulator.relativePath) && Intrinsics.areEqual(this.absolutePath, recommendationAccumulator.absolutePath) && this.presetHitCount == recommendationAccumulator.presetHitCount && this.mediaStoreHitCount == recommendationAccumulator.mediaStoreHitCount && this.latestCapturedAtMillis == recommendationAccumulator.latestCapturedAtMillis;
        }

        public int hashCode() {
            return (((((((this.relativePath.hashCode() * 31) + this.absolutePath.hashCode()) * 31) + Integer.hashCode(this.presetHitCount)) * 31) + Integer.hashCode(this.mediaStoreHitCount)) * 31) + Long.hashCode(this.latestCapturedAtMillis);
        }

        public String toString() {
            return "RecommendationAccumulator(relativePath=" + this.relativePath + ", absolutePath=" + this.absolutePath + ", presetHitCount=" + this.presetHitCount + ", mediaStoreHitCount=" + this.mediaStoreHitCount + ", latestCapturedAtMillis=" + this.latestCapturedAtMillis + ")";
        }

        public RecommendationAccumulator(String relativePath, String absolutePath, int presetHitCount, int mediaStoreHitCount, long latestCapturedAtMillis) {
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            this.relativePath = relativePath;
            this.absolutePath = absolutePath;
            this.presetHitCount = presetHitCount;
            this.mediaStoreHitCount = mediaStoreHitCount;
            this.latestCapturedAtMillis = latestCapturedAtMillis;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ RecommendationAccumulator(java.lang.String r8, java.lang.String r9, int r10, int r11, long r12, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
            /*
                r7 = this;
                r15 = r14 & 2
                if (r15 == 0) goto L15
                com.example.shellshot.media.ScreenshotDirectories r9 = com.example.shellshot.media.ScreenshotDirectories.INSTANCE
                java.io.File r9 = r9.toAbsoluteDirectory(r8)
                java.lang.String r9 = r9.getAbsolutePath()
                java.lang.String r15 = "getAbsolutePath(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r15)
                r2 = r9
                goto L16
            L15:
                r2 = r9
            L16:
                r9 = r14 & 4
                r15 = 0
                if (r9 == 0) goto L1d
                r3 = r15
                goto L1e
            L1d:
                r3 = r10
            L1e:
                r9 = r14 & 8
                if (r9 == 0) goto L24
                r4 = r15
                goto L25
            L24:
                r4 = r11
            L25:
                r9 = r14 & 16
                if (r9 == 0) goto L2d
                r12 = 0
                r5 = r12
                goto L2e
            L2d:
                r5 = r12
            L2e:
                r0 = r7
                r1 = r8
                r0.<init>(r1, r2, r3, r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDirectoryAdvisor.RecommendationAccumulator.<init>(java.lang.String, java.lang.String, int, int, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getRelativePath() {
            return this.relativePath;
        }

        public final String getAbsolutePath() {
            return this.absolutePath;
        }

        public final int getPresetHitCount() {
            return this.presetHitCount;
        }

        public final void setPresetHitCount(int i) {
            this.presetHitCount = i;
        }

        public final int getMediaStoreHitCount() {
            return this.mediaStoreHitCount;
        }

        public final void setMediaStoreHitCount(int i) {
            this.mediaStoreHitCount = i;
        }

        public final long getLatestCapturedAtMillis() {
            return this.latestCapturedAtMillis;
        }

        public final void setLatestCapturedAtMillis(long j) {
            this.latestCapturedAtMillis = j;
        }

        public final int getScore() {
            return (this.presetHitCount * 40) + (this.mediaStoreHitCount * 25);
        }

        public final void notePresetHit(List<? extends File> recentFiles) {
            Long l;
            Intrinsics.checkNotNullParameter(recentFiles, "recentFiles");
            this.presetHitCount += recentFiles.size();
            long j = this.latestCapturedAtMillis;
            Iterator<T> it = recentFiles.iterator();
            if (it.hasNext()) {
                Long valueOf = Long.valueOf(((File) it.next()).lastModified());
                while (it.hasNext()) {
                    Long valueOf2 = Long.valueOf(((File) it.next()).lastModified());
                    if (valueOf.compareTo(valueOf2) < 0) {
                        valueOf = valueOf2;
                    }
                }
                l = valueOf;
            } else {
                l = null;
            }
            Long l2 = l;
            this.latestCapturedAtMillis = Math.max(j, l2 != null ? l2.longValue() : 0L);
        }

        public final void noteMediaStoreHit(ScreenshotCandidate candidate) {
            Intrinsics.checkNotNullParameter(candidate, "candidate");
            this.mediaStoreHitCount++;
            this.latestCapturedAtMillis = Math.max(this.latestCapturedAtMillis, candidate.getCapturedAtMillis());
        }

        public final ScreenshotDirectoryRecommendation toRecommendation() {
            String sourceLabel;
            String reason;
            if (this.mediaStoreHitCount <= 0 || this.presetHitCount <= 0) {
                sourceLabel = this.mediaStoreHitCount > 0 ? "最近截图" : "常用目录";
            } else {
                sourceLabel = "综合推荐";
            }
            if (this.mediaStoreHitCount <= 0 || this.presetHitCount <= 0) {
                reason = this.mediaStoreHitCount > 0 ? "MediaStore 最近命中 " + this.mediaStoreHitCount + " 张" : "目录内发现 " + this.presetHitCount + " 个最近文件";
            } else {
                reason = "MediaStore 最近命中 " + this.mediaStoreHitCount + " 张，目录内发现 " + this.presetHitCount + " 个最近文件";
            }
            return new ScreenshotDirectoryRecommendation(this.relativePath, this.absolutePath, sourceLabel, reason);
        }
    }

    /* compiled from: ScreenshotDirectoryAdvisor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDirectoryAdvisor$Companion;", "", "<init>", "()V", "TAG", "", "MEDIASTORE_SCAN_LIMIT", "", "PRESET_SCAN_SAMPLE_LIMIT", "PRESET_SCAN_WINDOW_MILLIS", "", "DEFAULT_RECOMMENDATION_LIMIT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
