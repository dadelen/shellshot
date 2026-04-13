package com.example.shellshot

import android.app.Application
import com.example.shellshot.data.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ShellShotApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
        restoreMonitoringIfNeeded()
    }

    private fun restoreMonitoringIfNeeded() {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            val settings = appContainer.appPrefs.currentSettings()
            if (settings.monitoringEnabled && appContainer.permissionManager.hasCoreMonitoringPermissions()) {
                appContainer.logger.d(TAG, "Application cold start restoring auto shell service")
                appContainer.serviceController.start(applicationContext)
            }
        }
    }

    private companion object {
        const val TAG = "ShellShotApp"
    }
}
