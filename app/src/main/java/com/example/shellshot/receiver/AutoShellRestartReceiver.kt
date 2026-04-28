package com.example.shellshot.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.example.shellshot.ShellShotApplication
import com.example.shellshot.service.AutoShellForegroundService
import com.example.shellshot.service.MonitoringHeartbeatStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AutoShellRestartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val appContainer = (context.applicationContext as? ShellShotApplication)?.appContainer ?: return
        val pendingResult = goAsync()
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                when (intent?.action) {
                    AutoShellForegroundService.ACTION_SUPERVISE -> {
                        scheduleNextSupervisorCheck(context)
                        val settings = appContainer.appPrefs.currentSettings()
                        if (!settings.monitoringEnabled || !appContainer.permissionManager.hasCoreMonitoringPermissions()) {
                            appContainer.logger.d(TAG, "监听心跳自检跳过 reason=monitoring_disabled_or_permission_missing")
                            return@launch
                        }

                        val lastHeartbeat = MonitoringHeartbeatStore.read(context)
                        val heartbeatAge = SystemClock.elapsedRealtime() - lastHeartbeat
                        if (lastHeartbeat <= 0L || heartbeatAge > AutoShellForegroundService.HEARTBEAT_STALE_MILLIS) {
                            appContainer.logger.d(
                                TAG,
                                "监听心跳过期，准备拉起服务 lastHeartbeat=$lastHeartbeat ageMs=$heartbeatAge",
                            )
                            appContainer.serviceController.start(context.applicationContext)
                        } else {
                            appContainer.logger.d(TAG, "监听心跳正常 ageMs=$heartbeatAge")
                        }
                    }

                    else -> {
                        appContainer.logger.d(TAG, "收到服务自恢复广播，准备重新拉起后台监听")
                        appContainer.serviceController.start(context.applicationContext)
                    }
                }
            } finally {
                pendingResult.finish()
            }
        }
    }

    private fun scheduleNextSupervisorCheck(context: Context) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + AutoShellForegroundService.SUPERVISOR_INTERVAL_MILLIS,
            supervisorPendingIntent(context),
        )
    }

    private fun supervisorPendingIntent(context: Context): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            AutoShellForegroundService.SUPERVISOR_REQUEST_CODE,
            Intent(context, AutoShellRestartReceiver::class.java).apply {
                action = AutoShellForegroundService.ACTION_SUPERVISE
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
    }

    private companion object {
        const val TAG = "AutoShellRestart"
    }
}
