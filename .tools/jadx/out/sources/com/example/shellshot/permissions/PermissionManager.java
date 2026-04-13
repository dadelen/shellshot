package com.example.shellshot.permissions;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import androidx.core.content.ContextCompat;
import com.example.shellshot.data.MediaAccessLevel;
import com.example.shellshot.data.PermissionSnapshot;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionManager.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\tJ\u0006\u0010\u000e\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/example/shellshot/permissions/PermissionManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "readSnapshot", "Lcom/example/shellshot/data/PermissionSnapshot;", "hasNotificationPermission", "", "mediaAccessLevel", "Lcom/example/shellshot/data/MediaAccessLevel;", "hasReadImagesPermission", "hasVisualUserSelectedPermission", "hasManageAllFilesAccess", "hasUsageStatsAccess", "hasCoreMonitoringPermissions", "canUseMediaStoreFallback", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PermissionManager {
    public static final int $stable = 8;
    private final Context context;

    public PermissionManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final PermissionSnapshot readSnapshot() {
        return new PermissionSnapshot(hasNotificationPermission(), hasManageAllFilesAccess(), hasReadImagesPermission(), hasUsageStatsAccess(), mediaAccessLevel());
    }

    public final boolean hasNotificationPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") == 0;
    }

    public final MediaAccessLevel mediaAccessLevel() {
        return hasReadImagesPermission() ? MediaAccessLevel.Full : hasVisualUserSelectedPermission() ? MediaAccessLevel.Partial : MediaAccessLevel.NotGranted;
    }

    public final boolean hasReadImagesPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.READ_MEDIA_IMAGES") == 0;
    }

    public final boolean hasVisualUserSelectedPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.READ_MEDIA_VISUAL_USER_SELECTED") == 0;
    }

    public final boolean hasManageAllFilesAccess() {
        return Environment.isExternalStorageManager();
    }

    public final boolean hasUsageStatsAccess() {
        AppOpsManager appOps = (AppOpsManager) this.context.getSystemService(AppOpsManager.class);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats", Process.myUid(), this.context.getPackageName());
        return mode == 0;
    }

    public final boolean hasCoreMonitoringPermissions() {
        return hasNotificationPermission() && hasManageAllFilesAccess();
    }

    public final boolean canUseMediaStoreFallback() {
        return hasReadImagesPermission();
    }
}
