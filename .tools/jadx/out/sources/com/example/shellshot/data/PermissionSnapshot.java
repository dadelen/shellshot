package com.example.shellshot.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionSnapshot.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\f¨\u0006\""}, d2 = {"Lcom/example/shellshot/data/PermissionSnapshot;", "", "notificationsGranted", "", "allFilesGranted", "readImagesGranted", "usageStatsGranted", "mediaAccessLevel", "Lcom/example/shellshot/data/MediaAccessLevel;", "<init>", "(ZZZZLcom/example/shellshot/data/MediaAccessLevel;)V", "getNotificationsGranted", "()Z", "getAllFilesGranted", "getReadImagesGranted", "getUsageStatsGranted", "getMediaAccessLevel", "()Lcom/example/shellshot/data/MediaAccessLevel;", "fullMediaAccess", "getFullMediaAccess", "partialMediaAccess", "getPartialMediaAccess", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class PermissionSnapshot {
    public static final int $stable = 0;
    private final boolean allFilesGranted;
    private final MediaAccessLevel mediaAccessLevel;
    private final boolean notificationsGranted;
    private final boolean readImagesGranted;
    private final boolean usageStatsGranted;

    public PermissionSnapshot() {
        this(false, false, false, false, null, 31, null);
    }

    public static /* synthetic */ PermissionSnapshot copy$default(PermissionSnapshot permissionSnapshot, boolean z, boolean z2, boolean z3, boolean z4, MediaAccessLevel mediaAccessLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            z = permissionSnapshot.notificationsGranted;
        }
        if ((i & 2) != 0) {
            z2 = permissionSnapshot.allFilesGranted;
        }
        if ((i & 4) != 0) {
            z3 = permissionSnapshot.readImagesGranted;
        }
        if ((i & 8) != 0) {
            z4 = permissionSnapshot.usageStatsGranted;
        }
        if ((i & 16) != 0) {
            mediaAccessLevel = permissionSnapshot.mediaAccessLevel;
        }
        MediaAccessLevel mediaAccessLevel2 = mediaAccessLevel;
        boolean z5 = z3;
        return permissionSnapshot.copy(z, z2, z5, z4, mediaAccessLevel2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getNotificationsGranted() {
        return this.notificationsGranted;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getAllFilesGranted() {
        return this.allFilesGranted;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getReadImagesGranted() {
        return this.readImagesGranted;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getUsageStatsGranted() {
        return this.usageStatsGranted;
    }

    /* renamed from: component5, reason: from getter */
    public final MediaAccessLevel getMediaAccessLevel() {
        return this.mediaAccessLevel;
    }

    public final PermissionSnapshot copy(boolean notificationsGranted, boolean allFilesGranted, boolean readImagesGranted, boolean usageStatsGranted, MediaAccessLevel mediaAccessLevel) {
        Intrinsics.checkNotNullParameter(mediaAccessLevel, "mediaAccessLevel");
        return new PermissionSnapshot(notificationsGranted, allFilesGranted, readImagesGranted, usageStatsGranted, mediaAccessLevel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermissionSnapshot)) {
            return false;
        }
        PermissionSnapshot permissionSnapshot = (PermissionSnapshot) other;
        return this.notificationsGranted == permissionSnapshot.notificationsGranted && this.allFilesGranted == permissionSnapshot.allFilesGranted && this.readImagesGranted == permissionSnapshot.readImagesGranted && this.usageStatsGranted == permissionSnapshot.usageStatsGranted && this.mediaAccessLevel == permissionSnapshot.mediaAccessLevel;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.notificationsGranted) * 31) + Boolean.hashCode(this.allFilesGranted)) * 31) + Boolean.hashCode(this.readImagesGranted)) * 31) + Boolean.hashCode(this.usageStatsGranted)) * 31) + this.mediaAccessLevel.hashCode();
    }

    public String toString() {
        return "PermissionSnapshot(notificationsGranted=" + this.notificationsGranted + ", allFilesGranted=" + this.allFilesGranted + ", readImagesGranted=" + this.readImagesGranted + ", usageStatsGranted=" + this.usageStatsGranted + ", mediaAccessLevel=" + this.mediaAccessLevel + ")";
    }

    public PermissionSnapshot(boolean notificationsGranted, boolean allFilesGranted, boolean readImagesGranted, boolean usageStatsGranted, MediaAccessLevel mediaAccessLevel) {
        Intrinsics.checkNotNullParameter(mediaAccessLevel, "mediaAccessLevel");
        this.notificationsGranted = notificationsGranted;
        this.allFilesGranted = allFilesGranted;
        this.readImagesGranted = readImagesGranted;
        this.usageStatsGranted = usageStatsGranted;
        this.mediaAccessLevel = mediaAccessLevel;
    }

    public /* synthetic */ PermissionSnapshot(boolean z, boolean z2, boolean z3, boolean z4, MediaAccessLevel mediaAccessLevel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? MediaAccessLevel.NotGranted : mediaAccessLevel);
    }

    public final boolean getNotificationsGranted() {
        return this.notificationsGranted;
    }

    public final boolean getAllFilesGranted() {
        return this.allFilesGranted;
    }

    public final boolean getReadImagesGranted() {
        return this.readImagesGranted;
    }

    public final boolean getUsageStatsGranted() {
        return this.usageStatsGranted;
    }

    public final MediaAccessLevel getMediaAccessLevel() {
        return this.mediaAccessLevel;
    }

    public final boolean getFullMediaAccess() {
        return this.mediaAccessLevel == MediaAccessLevel.Full;
    }

    public final boolean getPartialMediaAccess() {
        return this.mediaAccessLevel == MediaAccessLevel.Partial;
    }
}
