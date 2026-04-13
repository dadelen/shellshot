package com.example.shellshot.observer;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiDirectoryFileObserver.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/example/shellshot/observer/DirectoryWatchEvent;", "", "directory", "Ljava/io/File;", "relativePath", "", "fileName", "eventMask", "", "absolutePath", "<init>", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDirectory", "()Ljava/io/File;", "getRelativePath", "()Ljava/lang/String;", "getFileName", "getEventMask", "()I", "getAbsolutePath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class DirectoryWatchEvent {
    public static final int $stable = 8;
    private final String absolutePath;
    private final File directory;
    private final int eventMask;
    private final String fileName;
    private final String relativePath;

    public static /* synthetic */ DirectoryWatchEvent copy$default(DirectoryWatchEvent directoryWatchEvent, File file, String str, String str2, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            file = directoryWatchEvent.directory;
        }
        if ((i2 & 2) != 0) {
            str = directoryWatchEvent.relativePath;
        }
        if ((i2 & 4) != 0) {
            str2 = directoryWatchEvent.fileName;
        }
        if ((i2 & 8) != 0) {
            i = directoryWatchEvent.eventMask;
        }
        if ((i2 & 16) != 0) {
            str3 = directoryWatchEvent.absolutePath;
        }
        String str4 = str3;
        String str5 = str2;
        return directoryWatchEvent.copy(file, str, str5, i, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final File getDirectory() {
        return this.directory;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component4, reason: from getter */
    public final int getEventMask() {
        return this.eventMask;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final DirectoryWatchEvent copy(File directory, String relativePath, String fileName, int eventMask, String absolutePath) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        return new DirectoryWatchEvent(directory, relativePath, fileName, eventMask, absolutePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DirectoryWatchEvent)) {
            return false;
        }
        DirectoryWatchEvent directoryWatchEvent = (DirectoryWatchEvent) other;
        return Intrinsics.areEqual(this.directory, directoryWatchEvent.directory) && Intrinsics.areEqual(this.relativePath, directoryWatchEvent.relativePath) && Intrinsics.areEqual(this.fileName, directoryWatchEvent.fileName) && this.eventMask == directoryWatchEvent.eventMask && Intrinsics.areEqual(this.absolutePath, directoryWatchEvent.absolutePath);
    }

    public int hashCode() {
        return (((((((this.directory.hashCode() * 31) + (this.relativePath == null ? 0 : this.relativePath.hashCode())) * 31) + this.fileName.hashCode()) * 31) + Integer.hashCode(this.eventMask)) * 31) + this.absolutePath.hashCode();
    }

    public String toString() {
        return "DirectoryWatchEvent(directory=" + this.directory + ", relativePath=" + this.relativePath + ", fileName=" + this.fileName + ", eventMask=" + this.eventMask + ", absolutePath=" + this.absolutePath + ")";
    }

    public DirectoryWatchEvent(File directory, String relativePath, String fileName, int eventMask, String absolutePath) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        this.directory = directory;
        this.relativePath = relativePath;
        this.fileName = fileName;
        this.eventMask = eventMask;
        this.absolutePath = absolutePath;
    }

    public final File getDirectory() {
        return this.directory;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final int getEventMask() {
        return this.eventMask;
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }
}
