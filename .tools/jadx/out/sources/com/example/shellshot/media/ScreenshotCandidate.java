package com.example.shellshot.media;

import android.net.Uri;
import java.io.File;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotCandidate.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b1\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0001BB\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00107\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00108\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010$J\u0010\u0010:\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010$J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\u0094\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010=J\u0013\u0010>\u001a\u00020\u00122\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u000bHÖ\u0001J\t\u0010A\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010%\u001a\u0004\b&\u0010$R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010'R\u0011\u0010(\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b)\u0010\u0016R\u0011\u0010*\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b+\u0010\u0016R\u0011\u0010,\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b-\u0010\u001aR\u0011\u0010.\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b/\u0010\u0016¨\u0006C"}, d2 = {"Lcom/example/shellshot/media/ScreenshotCandidate;", "", "absolutePath", "", "displayName", "relativePath", "lastModifiedMillis", "", "sizeBytes", "mimeType", "width", "", "height", "uri", "Landroid/net/Uri;", "dateAddedSec", "dateTakenMs", "isPending", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/net/Uri;Ljava/lang/Long;Ljava/lang/Long;Z)V", "getAbsolutePath", "()Ljava/lang/String;", "getDisplayName", "getRelativePath", "getLastModifiedMillis", "()J", "getSizeBytes", "getMimeType", "getWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getUri", "()Landroid/net/Uri;", "getDateAddedSec", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDateTakenMs", "()Z", "parentDirectory", "getParentDirectory", "bucketName", "getBucketName", "capturedAtMillis", "getCapturedAtMillis", "dedupeKey", "getDedupeKey", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/net/Uri;Ljava/lang/Long;Ljava/lang/Long;Z)Lcom/example/shellshot/media/ScreenshotCandidate;", "equals", "other", "hashCode", "toString", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class ScreenshotCandidate {
    private final String absolutePath;
    private final Long dateAddedSec;
    private final Long dateTakenMs;
    private final String displayName;
    private final Integer height;
    private final boolean isPending;
    private final long lastModifiedMillis;
    private final String mimeType;
    private final String relativePath;
    private final long sizeBytes;
    private final Uri uri;
    private final Integer width;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    /* renamed from: component10, reason: from getter */
    public final Long getDateAddedSec() {
        return this.dateAddedSec;
    }

    /* renamed from: component11, reason: from getter */
    public final Long getDateTakenMs() {
        return this.dateTakenMs;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getIsPending() {
        return this.isPending;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component4, reason: from getter */
    public final long getLastModifiedMillis() {
        return this.lastModifiedMillis;
    }

    /* renamed from: component5, reason: from getter */
    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    /* renamed from: component6, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    /* renamed from: component9, reason: from getter */
    public final Uri getUri() {
        return this.uri;
    }

    public final ScreenshotCandidate copy(String absolutePath, String displayName, String relativePath, long lastModifiedMillis, long sizeBytes, String mimeType, Integer width, Integer height, Uri uri, Long dateAddedSec, Long dateTakenMs, boolean isPending) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        return new ScreenshotCandidate(absolutePath, displayName, relativePath, lastModifiedMillis, sizeBytes, mimeType, width, height, uri, dateAddedSec, dateTakenMs, isPending);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenshotCandidate)) {
            return false;
        }
        ScreenshotCandidate screenshotCandidate = (ScreenshotCandidate) other;
        return Intrinsics.areEqual(this.absolutePath, screenshotCandidate.absolutePath) && Intrinsics.areEqual(this.displayName, screenshotCandidate.displayName) && Intrinsics.areEqual(this.relativePath, screenshotCandidate.relativePath) && this.lastModifiedMillis == screenshotCandidate.lastModifiedMillis && this.sizeBytes == screenshotCandidate.sizeBytes && Intrinsics.areEqual(this.mimeType, screenshotCandidate.mimeType) && Intrinsics.areEqual(this.width, screenshotCandidate.width) && Intrinsics.areEqual(this.height, screenshotCandidate.height) && Intrinsics.areEqual(this.uri, screenshotCandidate.uri) && Intrinsics.areEqual(this.dateAddedSec, screenshotCandidate.dateAddedSec) && Intrinsics.areEqual(this.dateTakenMs, screenshotCandidate.dateTakenMs) && this.isPending == screenshotCandidate.isPending;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.absolutePath.hashCode() * 31) + this.displayName.hashCode()) * 31) + (this.relativePath == null ? 0 : this.relativePath.hashCode())) * 31) + Long.hashCode(this.lastModifiedMillis)) * 31) + Long.hashCode(this.sizeBytes)) * 31) + (this.mimeType == null ? 0 : this.mimeType.hashCode())) * 31) + (this.width == null ? 0 : this.width.hashCode())) * 31) + (this.height == null ? 0 : this.height.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + (this.dateAddedSec == null ? 0 : this.dateAddedSec.hashCode())) * 31) + (this.dateTakenMs != null ? this.dateTakenMs.hashCode() : 0)) * 31) + Boolean.hashCode(this.isPending);
    }

    public String toString() {
        return "ScreenshotCandidate(absolutePath=" + this.absolutePath + ", displayName=" + this.displayName + ", relativePath=" + this.relativePath + ", lastModifiedMillis=" + this.lastModifiedMillis + ", sizeBytes=" + this.sizeBytes + ", mimeType=" + this.mimeType + ", width=" + this.width + ", height=" + this.height + ", uri=" + this.uri + ", dateAddedSec=" + this.dateAddedSec + ", dateTakenMs=" + this.dateTakenMs + ", isPending=" + this.isPending + ")";
    }

    public ScreenshotCandidate(String absolutePath, String displayName, String relativePath, long lastModifiedMillis, long sizeBytes, String mimeType, Integer width, Integer height, Uri uri, Long dateAddedSec, Long dateTakenMs, boolean isPending) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        this.lastModifiedMillis = lastModifiedMillis;
        this.sizeBytes = sizeBytes;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.uri = uri;
        this.dateAddedSec = dateAddedSec;
        this.dateTakenMs = dateTakenMs;
        this.isPending = isPending;
    }

    public /* synthetic */ ScreenshotCandidate(String str, String str2, String str3, long j, long j2, String str4, Integer num, Integer num2, Uri uri, Long l, Long l2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, j, j2, str4, (i & 64) != 0 ? null : num, (i & 128) != 0 ? null : num2, (i & 256) != 0 ? null : uri, (i & 512) != 0 ? null : l, (i & 1024) != 0 ? null : l2, (i & 2048) != 0 ? false : z);
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final long getLastModifiedMillis() {
        return this.lastModifiedMillis;
    }

    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final Long getDateAddedSec() {
        return this.dateAddedSec;
    }

    public final Long getDateTakenMs() {
        return this.dateTakenMs;
    }

    public final boolean isPending() {
        return this.isPending;
    }

    public final String getParentDirectory() {
        String parent = new File(this.absolutePath).getParent();
        return parent == null ? "" : parent;
    }

    public final String getBucketName() {
        String name = new File(getParentDirectory()).getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    public final long getCapturedAtMillis() {
        Long l = this.dateTakenMs;
        if (l != null) {
            if (!(l.longValue() > 0)) {
                l = null;
            }
            if (l != null) {
                return l.longValue();
            }
        }
        return this.lastModifiedMillis;
    }

    public final String getDedupeKey() {
        return INSTANCE.buildDedupeKey(this.absolutePath, this.displayName, this.sizeBytes, this.lastModifiedMillis);
    }

    /* compiled from: ScreenshotCandidate.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005J&\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¨\u0006\f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotCandidate$Companion;", "", "<init>", "()V", "buildProcessedPathKey", "", "absolutePath", "buildDedupeKey", "displayName", "sizeBytes", "", "lastModifiedMillis", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String buildProcessedPathKey(String absolutePath) {
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            String normalizedPath = ScreenshotDirectories.INSTANCE.normalizeAbsolutePath(absolutePath);
            if (normalizedPath == null) {
                return null;
            }
            return "path:" + normalizedPath;
        }

        public final String buildDedupeKey(String absolutePath, String displayName, long sizeBytes, long lastModifiedMillis) {
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            StringBuilder sb = new StringBuilder();
            String lowerCase = absolutePath.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            sb.append(lowerCase);
            sb.append('|');
            String lowerCase2 = displayName.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            sb.append(lowerCase2);
            sb.append('|');
            sb.append(sizeBytes);
            sb.append('|');
            sb.append(lastModifiedMillis);
            return sb.toString();
        }
    }
}
