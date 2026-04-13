package com.example.shellshot.ui;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainUiState.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/ui/ScreenshotDirectoryOption;", "", "relativePath", "", "absolutePath", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getRelativePath", "()Ljava/lang/String;", "getAbsolutePath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class ScreenshotDirectoryOption {
    public static final int $stable = 0;
    private final String absolutePath;
    private final String relativePath;

    public static /* synthetic */ ScreenshotDirectoryOption copy$default(ScreenshotDirectoryOption screenshotDirectoryOption, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = screenshotDirectoryOption.relativePath;
        }
        if ((i & 2) != 0) {
            str2 = screenshotDirectoryOption.absolutePath;
        }
        return screenshotDirectoryOption.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final ScreenshotDirectoryOption copy(String relativePath, String absolutePath) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        return new ScreenshotDirectoryOption(relativePath, absolutePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenshotDirectoryOption)) {
            return false;
        }
        ScreenshotDirectoryOption screenshotDirectoryOption = (ScreenshotDirectoryOption) other;
        return Intrinsics.areEqual(this.relativePath, screenshotDirectoryOption.relativePath) && Intrinsics.areEqual(this.absolutePath, screenshotDirectoryOption.absolutePath);
    }

    public int hashCode() {
        return (this.relativePath.hashCode() * 31) + this.absolutePath.hashCode();
    }

    public String toString() {
        return "ScreenshotDirectoryOption(relativePath=" + this.relativePath + ", absolutePath=" + this.absolutePath + ")";
    }

    public ScreenshotDirectoryOption(String relativePath, String absolutePath) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        this.relativePath = relativePath;
        this.absolutePath = absolutePath;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }
}
