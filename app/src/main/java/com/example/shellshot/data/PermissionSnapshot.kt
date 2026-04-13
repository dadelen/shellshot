package com.example.shellshot.data

enum class MediaAccessLevel {
    NotGranted,
    Partial,
    Full,
}

data class PermissionSnapshot(
    val notificationsGranted: Boolean = false,
    val allFilesGranted: Boolean = false,
    val readImagesGranted: Boolean = false,
    val usageStatsGranted: Boolean = false,
    val mediaAccessLevel: MediaAccessLevel = MediaAccessLevel.NotGranted,
) {
    val fullMediaAccess: Boolean
        get() = mediaAccessLevel == MediaAccessLevel.Full

    val partialMediaAccess: Boolean
        get() = mediaAccessLevel == MediaAccessLevel.Partial
}
