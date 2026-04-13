package com.example.shellshot.permissions

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.os.Process
import androidx.core.content.ContextCompat
import com.example.shellshot.data.MediaAccessLevel
import com.example.shellshot.data.PermissionSnapshot

class PermissionManager(
    private val context: Context,
) {
    fun readSnapshot(): PermissionSnapshot {
        return PermissionSnapshot(
            notificationsGranted = hasNotificationPermission(),
            allFilesGranted = hasManageAllFilesAccess(),
            readImagesGranted = hasReadImagesPermission(),
            usageStatsGranted = hasUsageStatsAccess(),
            mediaAccessLevel = mediaAccessLevel(),
        )
    }

    fun hasNotificationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun mediaAccessLevel(): MediaAccessLevel {
        return when {
            hasReadImagesPermission() -> MediaAccessLevel.Full
            hasVisualUserSelectedPermission() -> MediaAccessLevel.Partial
            else -> MediaAccessLevel.NotGranted
        }
    }

    fun hasReadImagesPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES,
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun hasVisualUserSelectedPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun hasManageAllFilesAccess(): Boolean {
        return Environment.isExternalStorageManager()
    }

    fun hasUsageStatsAccess(): Boolean {
        val appOps = context.getSystemService(AppOpsManager::class.java)
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            context.packageName,
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun hasCoreMonitoringPermissions(): Boolean {
        return hasNotificationPermission() &&
            hasManageAllFilesAccess()
    }

    fun canUseMediaStoreFallback(): Boolean {
        return hasReadImagesPermission()
    }
}
