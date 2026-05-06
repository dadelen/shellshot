package com.example.shellshot.service

import com.example.shellshot.data.AppRuntimeState
import com.example.shellshot.data.AppSettings
import org.junit.Assert.assertEquals
import org.junit.Test

class AutoShellModeEvaluatorTest {
    @Test
    fun disabledMonitoringWinsOverRuntimeState() {
        val mode = AutoShellModeEvaluator.evaluate(
            settings = AppSettings(monitoringEnabled = false),
            runtimeState = AppRuntimeState(
                screenOn = false,
                userUnlocked = false,
                gameForeground = true,
            ),
        )

        assertEquals(AutoShellMode.USER_STOPPED, mode)
    }

    @Test
    fun lockedOrScreenOffSuspendsBeforeGameDetection() {
        val mode = AutoShellModeEvaluator.evaluate(
            settings = AppSettings(monitoringEnabled = true),
            runtimeState = AppRuntimeState(
                screenOn = true,
                userUnlocked = false,
                gameForeground = true,
            ),
        )

        assertEquals(AutoShellMode.SUSPEND_SCREEN_OFF, mode)
    }

    @Test
    fun foregroundGameSuspendsWhenDeviceIsUsable() {
        val mode = AutoShellModeEvaluator.evaluate(
            settings = AppSettings(monitoringEnabled = true),
            runtimeState = AppRuntimeState(
                screenOn = true,
                userUnlocked = true,
                gameForeground = true,
            ),
        )

        assertEquals(AutoShellMode.SUSPEND_GAME, mode)
    }

    @Test
    fun activeWhenMonitoringEnabledAndNoSuspendConditionApplies() {
        val mode = AutoShellModeEvaluator.evaluate(
            settings = AppSettings(monitoringEnabled = true),
            runtimeState = AppRuntimeState(
                screenOn = true,
                userUnlocked = true,
                gameForeground = false,
            ),
        )

        assertEquals(AutoShellMode.ACTIVE, mode)
    }
}
