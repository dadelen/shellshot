package com.example.shellshot.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.shellshot.ShellShotApplication

class AutoShellRestartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val appContainer = (context.applicationContext as? ShellShotApplication)?.appContainer ?: return
        appContainer.logger.d(TAG, "收到服务自恢复广播，准备重新拉起后台监听")
        appContainer.serviceController.start(context.applicationContext)
    }

    private companion object {
        const val TAG = "AutoShellRestart"
    }
}
