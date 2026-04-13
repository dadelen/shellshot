package com.example.shellshot.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.shellshot.ShellShotApplication
import com.example.shellshot.service.AutoShellForegroundService

object ServiceController {
    fun start(context: Context) {
        log(context, "Requesting service start ${diagnosticTrace("service_controller_start")}")
        val intent = Intent(context, AutoShellForegroundService::class.java).apply {
            action = AutoShellForegroundService.ACTION_START
        }
        ContextCompat.startForegroundService(context, intent)
    }

    fun stop(context: Context) {
        log(context, "Requesting service stop ${diagnosticTrace("service_controller_stop")}")
        val intent = Intent(context, AutoShellForegroundService::class.java).apply {
            action = AutoShellForegroundService.ACTION_STOP
            putExtra(AutoShellForegroundService.EXTRA_TRUSTED_STOP, true)
        }
        context.startService(intent)
    }

    private fun log(context: Context, message: String) {
        val logger = (context.applicationContext as? ShellShotApplication)?.appContainer?.logger
        if (logger != null) {
            logger.d(TAG, message)
        } else {
            Log.d(TAG, message)
        }
    }

    private fun diagnosticTrace(reason: String): String = buildString {
        append("reason=")
        append(reason)
        append(" thread=")
        append(Thread.currentThread().name)
        append('\n')
        append(Throwable().stackTraceToString())
    }

    private const val TAG = "ServiceController"
}
