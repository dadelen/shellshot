package com.example.shellshot.service

import com.example.shellshot.data.AppRuntimeState
import com.example.shellshot.data.AppSettings

object AutoShellModeEvaluator {
    fun evaluate(
        settings: AppSettings,
        runtimeState: AppRuntimeState,
    ): AutoShellMode {
        return when {
            !settings.monitoringEnabled -> AutoShellMode.USER_STOPPED
            !runtimeState.screenOn || !runtimeState.userUnlocked -> AutoShellMode.SUSPEND_SCREEN_OFF
            runtimeState.gameForeground -> AutoShellMode.SUSPEND_GAME
            else -> AutoShellMode.ACTIVE
        }
    }
}
