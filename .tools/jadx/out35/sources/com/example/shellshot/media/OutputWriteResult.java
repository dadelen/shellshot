package com.example.shellshot.media;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutputRepository.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/example/shellshot/media/OutputWriteResult;", "", "absolutePath", "", "scannedUri", "Landroid/net/Uri;", "<init>", "(Ljava/lang/String;Landroid/net/Uri;)V", "getAbsolutePath", "()Ljava/lang/String;", "getScannedUri", "()Landroid/net/Uri;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class OutputWriteResult {
    public static final int $stable = 8;
    private final String absolutePath;
    private final Uri scannedUri;

    public static /* synthetic */ OutputWriteResult copy$default(OutputWriteResult outputWriteResult, String str, Uri uri, int i, Object obj) {
        if ((i & 1) != 0) {
            str = outputWriteResult.absolutePath;
        }
        if ((i & 2) != 0) {
            uri = outputWriteResult.scannedUri;
        }
        return outputWriteResult.copy(str, uri);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    /* renamed from: component2, reason: from getter */
    public final Uri getScannedUri() {
        return this.scannedUri;
    }

    public final OutputWriteResult copy(String absolutePath, Uri scannedUri) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        return new OutputWriteResult(absolutePath, scannedUri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OutputWriteResult)) {
            return false;
        }
        OutputWriteResult outputWriteResult = (OutputWriteResult) other;
        return Intrinsics.areEqual(this.absolutePath, outputWriteResult.absolutePath) && Intrinsics.areEqual(this.scannedUri, outputWriteResult.scannedUri);
    }

    public int hashCode() {
        return (this.absolutePath.hashCode() * 31) + (this.scannedUri == null ? 0 : this.scannedUri.hashCode());
    }

    public String toString() {
        return "OutputWriteResult(absolutePath=" + this.absolutePath + ", scannedUri=" + this.scannedUri + ")";
    }

    public OutputWriteResult(String absolutePath, Uri scannedUri) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        this.absolutePath = absolutePath;
        this.scannedUri = scannedUri;
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final Uri getScannedUri() {
        return this.scannedUri;
    }
}
