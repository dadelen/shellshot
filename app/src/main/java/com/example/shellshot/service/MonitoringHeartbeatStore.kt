package com.example.shellshot.service

import android.content.Context

object MonitoringHeartbeatStore {
    private const val PREFS_NAME = "shellshot_monitoring_heartbeat"
    private const val KEY_LAST_HEARTBEAT = "last_heartbeat_elapsed_realtime"

    fun write(context: Context, elapsedRealtimeMillis: Long) {
        context.applicationContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putLong(KEY_LAST_HEARTBEAT, elapsedRealtimeMillis)
            .apply()
    }

    fun read(context: Context): Long {
        return context.applicationContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getLong(KEY_LAST_HEARTBEAT, 0L)
    }

    fun clear(context: Context) {
        context.applicationContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_LAST_HEARTBEAT)
            .apply()
    }
}
