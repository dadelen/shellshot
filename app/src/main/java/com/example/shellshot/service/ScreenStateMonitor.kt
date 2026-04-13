package com.example.shellshot.service

import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager
import com.example.shellshot.utils.ShellLogger

data class ScreenStateSnapshot(
    val screenOn: Boolean,
    val userUnlocked: Boolean,
)

class ScreenStateMonitor(
    private val context: Context,
    private val logger: ShellLogger,
    private val onChanged: (reason: String, snapshot: ScreenStateSnapshot) -> Unit,
) {
    private val powerManager by lazy { context.getSystemService(PowerManager::class.java) }
    private val keyguardManager by lazy { context.getSystemService(KeyguardManager::class.java) }

    private var registered = false

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action.orEmpty()
            val reason = when (action) {
                Intent.ACTION_SCREEN_ON -> "SCREEN_ON"
                Intent.ACTION_SCREEN_OFF -> "SCREEN_OFF"
                Intent.ACTION_USER_PRESENT -> "USER_PRESENT"
                else -> action.ifBlank { "UNKNOWN" }
            }
            val snapshot = currentSnapshot()
            logger.d(
                TAG,
                "屏幕状态变更 reason=$reason screenOn=${snapshot.screenOn} unlocked=${snapshot.userUnlocked}",
            )
            onChanged(reason, snapshot)
        }
    }

    fun start() {
        if (registered) {
            return
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_USER_PRESENT)
        }
        context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        registered = true
        val snapshot = currentSnapshot()
        logger.d(
            TAG,
            "屏幕状态监听已启动 screenOn=${snapshot.screenOn} unlocked=${snapshot.userUnlocked}",
        )
        onChanged("initial", snapshot)
    }

    fun stop() {
        if (!registered) {
            return
        }

        runCatching { context.unregisterReceiver(receiver) }
        registered = false
        logger.d(TAG, "屏幕状态监听已停止")
    }

    fun currentSnapshot(): ScreenStateSnapshot {
        val screenOn = powerManager.isInteractive
        val userUnlocked = !keyguardManager.isDeviceLocked
        return ScreenStateSnapshot(
            screenOn = screenOn,
            userUnlocked = userUnlocked,
        )
    }

    private companion object {
        const val TAG = "ScreenState"
    }
}
