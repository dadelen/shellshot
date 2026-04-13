package com.example.shellshot.media;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotTrigger.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017JT\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u000bHÖ\u0001J\t\u0010'\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013¨\u0006*"}, d2 = {"Lcom/example/shellshot/media/ScreenshotTrigger;", "", "source", "Lcom/example/shellshot/media/ScreenshotTrigger$Source;", "changedUri", "Landroid/net/Uri;", "absolutePath", "", "displayName", "relativePath", "eventMask", "", "<init>", "(Lcom/example/shellshot/media/ScreenshotTrigger$Source;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getSource", "()Lcom/example/shellshot/media/ScreenshotTrigger$Source;", "getChangedUri", "()Landroid/net/Uri;", "getAbsolutePath", "()Ljava/lang/String;", "getDisplayName", "getRelativePath", "getEventMask", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "sourceLabel", "getSourceLabel", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/example/shellshot/media/ScreenshotTrigger$Source;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/example/shellshot/media/ScreenshotTrigger;", "equals", "", "other", "hashCode", "toString", "Source", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class ScreenshotTrigger {
    private final String absolutePath;
    private final Uri changedUri;
    private final String displayName;
    private final Integer eventMask;
    private final String relativePath;
    private final Source source;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ ScreenshotTrigger copy$default(ScreenshotTrigger screenshotTrigger, Source source, Uri uri, String str, String str2, String str3, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            source = screenshotTrigger.source;
        }
        if ((i & 2) != 0) {
            uri = screenshotTrigger.changedUri;
        }
        if ((i & 4) != 0) {
            str = screenshotTrigger.absolutePath;
        }
        if ((i & 8) != 0) {
            str2 = screenshotTrigger.displayName;
        }
        if ((i & 16) != 0) {
            str3 = screenshotTrigger.relativePath;
        }
        if ((i & 32) != 0) {
            num = screenshotTrigger.eventMask;
        }
        String str4 = str3;
        Integer num2 = num;
        return screenshotTrigger.copy(source, uri, str, str2, str4, num2);
    }

    /* renamed from: component1, reason: from getter */
    public final Source getSource() {
        return this.source;
    }

    /* renamed from: component2, reason: from getter */
    public final Uri getChangedUri() {
        return this.changedUri;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getEventMask() {
        return this.eventMask;
    }

    public final ScreenshotTrigger copy(Source source, Uri changedUri, String absolutePath, String displayName, String relativePath, Integer eventMask) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new ScreenshotTrigger(source, changedUri, absolutePath, displayName, relativePath, eventMask);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenshotTrigger)) {
            return false;
        }
        ScreenshotTrigger screenshotTrigger = (ScreenshotTrigger) other;
        return this.source == screenshotTrigger.source && Intrinsics.areEqual(this.changedUri, screenshotTrigger.changedUri) && Intrinsics.areEqual(this.absolutePath, screenshotTrigger.absolutePath) && Intrinsics.areEqual(this.displayName, screenshotTrigger.displayName) && Intrinsics.areEqual(this.relativePath, screenshotTrigger.relativePath) && Intrinsics.areEqual(this.eventMask, screenshotTrigger.eventMask);
    }

    public int hashCode() {
        return (((((((((this.source.hashCode() * 31) + (this.changedUri == null ? 0 : this.changedUri.hashCode())) * 31) + (this.absolutePath == null ? 0 : this.absolutePath.hashCode())) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.relativePath == null ? 0 : this.relativePath.hashCode())) * 31) + (this.eventMask != null ? this.eventMask.hashCode() : 0);
    }

    public String toString() {
        return "ScreenshotTrigger(source=" + this.source + ", changedUri=" + this.changedUri + ", absolutePath=" + this.absolutePath + ", displayName=" + this.displayName + ", relativePath=" + this.relativePath + ", eventMask=" + this.eventMask + ")";
    }

    public ScreenshotTrigger(Source source, Uri changedUri, String absolutePath, String displayName, String relativePath, Integer eventMask) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.changedUri = changedUri;
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        this.eventMask = eventMask;
    }

    public /* synthetic */ ScreenshotTrigger(Source source, Uri uri, String str, String str2, String str3, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(source, (i & 2) != 0 ? null : uri, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : num);
    }

    public final Source getSource() {
        return this.source;
    }

    public final Uri getChangedUri() {
        return this.changedUri;
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

    public final Integer getEventMask() {
        return this.eventMask;
    }

    public final String getSourceLabel() {
        return this.source.getLabel();
    }

    /* compiled from: ScreenshotTrigger.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/example/shellshot/media/ScreenshotTrigger$Source;", "", "label", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "FileObserver", "MediaStoreFallback", "MediaStoreRecovery", "StartupRecovery", "DirectoryRecovery", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Source {
        FileObserver("file_observer"),
        MediaStoreFallback("media_store_fallback"),
        MediaStoreRecovery("media_store_recovery"),
        StartupRecovery("startup_recovery"),
        DirectoryRecovery("directory_recovery");

        private final String label;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Source> getEntries() {
            return $ENTRIES;
        }

        Source(String label) {
            this.label = label;
        }

        public final String getLabel() {
            return this.label;
        }
    }

    /* compiled from: ScreenshotTrigger.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u0010\u001a\u00020\u0005J \u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/shellshot/media/ScreenshotTrigger$Companion;", "", "<init>", "()V", "fileObserver", "Lcom/example/shellshot/media/ScreenshotTrigger;", "absolutePath", "", "displayName", "relativePath", "eventMask", "", "mediaFallback", "changedUri", "Landroid/net/Uri;", "mediaStoreRecovery", "startupRecovery", "directoryRecovery", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScreenshotTrigger fileObserver(String absolutePath, String displayName, String relativePath, int eventMask) {
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            return new ScreenshotTrigger(Source.FileObserver, null, absolutePath, displayName, relativePath, Integer.valueOf(eventMask), 2, null);
        }

        public final ScreenshotTrigger mediaFallback(Uri changedUri) {
            return new ScreenshotTrigger(Source.MediaStoreFallback, changedUri, null, null, null, null, 60, null);
        }

        public final ScreenshotTrigger mediaStoreRecovery(Uri changedUri) {
            return new ScreenshotTrigger(Source.MediaStoreRecovery, changedUri, null, null, null, null, 60, null);
        }

        public final ScreenshotTrigger startupRecovery() {
            return new ScreenshotTrigger(Source.StartupRecovery, null, null, null, null, null, 62, null);
        }

        public final ScreenshotTrigger directoryRecovery(String absolutePath, String displayName, String relativePath) {
            Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            return new ScreenshotTrigger(Source.DirectoryRecovery, null, absolutePath, displayName, relativePath, null, 34, null);
        }
    }
}
