package com.example.shellshot.media;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotDirectoryAdvisor.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "", "relativePath", "", "absolutePath", "sourceLabel", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRelativePath", "()Ljava/lang/String;", "getAbsolutePath", "getSourceLabel", "getReason", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class ScreenshotDirectoryRecommendation {
    public static final int $stable = 0;
    private final String absolutePath;
    private final String reason;
    private final String relativePath;
    private final String sourceLabel;

    public static /* synthetic */ ScreenshotDirectoryRecommendation copy$default(ScreenshotDirectoryRecommendation screenshotDirectoryRecommendation, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = screenshotDirectoryRecommendation.relativePath;
        }
        if ((i & 2) != 0) {
            str2 = screenshotDirectoryRecommendation.absolutePath;
        }
        if ((i & 4) != 0) {
            str3 = screenshotDirectoryRecommendation.sourceLabel;
        }
        if ((i & 8) != 0) {
            str4 = screenshotDirectoryRecommendation.reason;
        }
        return screenshotDirectoryRecommendation.copy(str, str2, str3, str4);
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
    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    /* renamed from: component4, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    public final ScreenshotDirectoryRecommendation copy(String relativePath, String absolutePath, String sourceLabel, String reason) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(sourceLabel, "sourceLabel");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new ScreenshotDirectoryRecommendation(relativePath, absolutePath, sourceLabel, reason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenshotDirectoryRecommendation)) {
            return false;
        }
        ScreenshotDirectoryRecommendation screenshotDirectoryRecommendation = (ScreenshotDirectoryRecommendation) other;
        return Intrinsics.areEqual(this.relativePath, screenshotDirectoryRecommendation.relativePath) && Intrinsics.areEqual(this.absolutePath, screenshotDirectoryRecommendation.absolutePath) && Intrinsics.areEqual(this.sourceLabel, screenshotDirectoryRecommendation.sourceLabel) && Intrinsics.areEqual(this.reason, screenshotDirectoryRecommendation.reason);
    }

    public int hashCode() {
        return (((((this.relativePath.hashCode() * 31) + this.absolutePath.hashCode()) * 31) + this.sourceLabel.hashCode()) * 31) + this.reason.hashCode();
    }

    public String toString() {
        return "ScreenshotDirectoryRecommendation(relativePath=" + this.relativePath + ", absolutePath=" + this.absolutePath + ", sourceLabel=" + this.sourceLabel + ", reason=" + this.reason + ")";
    }

    public ScreenshotDirectoryRecommendation(String relativePath, String absolutePath, String sourceLabel, String reason) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(sourceLabel, "sourceLabel");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.relativePath = relativePath;
        this.absolutePath = absolutePath;
        this.sourceLabel = sourceLabel;
        this.reason = reason;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    public final String getReason() {
        return this.reason;
    }
}
