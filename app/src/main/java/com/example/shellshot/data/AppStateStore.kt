package com.example.shellshot.data

import com.example.shellshot.service.AutoShellMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class MonitoringPhase {
    Disabled,
    Starting,
    Monitoring,
    CandidateDetected,
    WaitingFileStable,
    Composing,
    Saving,
    ObserverRegisterFailed,
    FileNotStable,
    ComposeFailed,
    SaveFailed,
}

data class AppRuntimeState(
    val monitoringActive: Boolean = false,
    val monitoringPhase: MonitoringPhase = MonitoringPhase.Disabled,
    val mode: AutoShellMode = AutoShellMode.USER_STOPPED,
    val modeReason: String = "用户未开启监听",
    val modeChangedAtMillis: Long = System.currentTimeMillis(),
    val screenOn: Boolean = true,
    val userUnlocked: Boolean = true,
    val currentForegroundPackage: String? = null,
    val gameForeground: Boolean = false,
    val fileWatcherActive: Boolean = false,
    val mediaFallbackActive: Boolean = false,
    val degradedMode: Boolean = false,
    val watchedDirectories: List<String> = emptyList(),
    val logs: List<LogEntry> = emptyList(),
    val lastProcessingResult: ProcessingResult? = null,
)

class AppStateStore {
    private val _runtimeState = MutableStateFlow(AppRuntimeState())
    val runtimeState: StateFlow<AppRuntimeState> = _runtimeState.asStateFlow()

    fun setMonitoringActive(active: Boolean) {
        _runtimeState.update { current ->
            current.copy(monitoringActive = active)
        }
    }

    fun setMonitoringPhase(phase: MonitoringPhase) {
        _runtimeState.update { current ->
            current.copy(monitoringPhase = phase)
        }
    }

    fun setMode(mode: AutoShellMode, reason: String) {
        _runtimeState.update { current ->
            val now = System.currentTimeMillis()
            current.copy(
                mode = mode,
                modeReason = reason,
                modeChangedAtMillis = if (current.mode != mode) now else current.modeChangedAtMillis,
            )
        }
    }

    fun setScreenState(screenOn: Boolean, userUnlocked: Boolean) {
        _runtimeState.update { current ->
            current.copy(
                screenOn = screenOn,
                userUnlocked = userUnlocked,
            )
        }
    }

    fun setForegroundApp(packageName: String?, isGame: Boolean) {
        _runtimeState.update { current ->
            current.copy(
                currentForegroundPackage = packageName,
                gameForeground = isGame,
            )
        }
    }

    fun setFileWatcherState(active: Boolean, watchedDirectories: List<String>) {
        _runtimeState.update { current ->
            current.copy(
                fileWatcherActive = active,
                watchedDirectories = watchedDirectories,
                degradedMode = !active && current.mediaFallbackActive,
            )
        }
    }

    fun setMediaFallbackActive(active: Boolean) {
        _runtimeState.update { current ->
            current.copy(
                mediaFallbackActive = active,
                degradedMode = !current.fileWatcherActive && active,
            )
        }
    }

    fun pushLog(logEntry: LogEntry) {
        _runtimeState.update { current ->
            current.copy(
                logs = (listOf(logEntry) + current.logs).take(MAX_LOG_COUNT),
            )
        }
    }

    fun clearLogs() {
        _runtimeState.update { current ->
            current.copy(logs = emptyList())
        }
    }

    fun setLastProcessingResult(result: ProcessingResult) {
        _runtimeState.update { current ->
            current.copy(lastProcessingResult = result)
        }
    }

    companion object {
        private const val MAX_LOG_COUNT = 100
    }
}
