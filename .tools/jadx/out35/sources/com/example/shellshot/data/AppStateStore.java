package com.example.shellshot.data;

import androidx.compose.ui.layout.LayoutKt;
import com.example.shellshot.service.AutoShellMode;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: AppStateStore.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000eJ\u0018\u0010\u001a\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u000eJ\u001c\u0010\u001d\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u001fJ\u000e\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\fJ\u000e\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020'R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006)"}, d2 = {"Lcom/example/shellshot/data/AppStateStore;", "", "<init>", "()V", "_runtimeState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/shellshot/data/AppRuntimeState;", "runtimeState", "Lkotlinx/coroutines/flow/StateFlow;", "getRuntimeState", "()Lkotlinx/coroutines/flow/StateFlow;", "setMonitoringActive", "", "active", "", "setMonitoringPhase", "phase", "Lcom/example/shellshot/data/MonitoringPhase;", "setMode", "mode", "Lcom/example/shellshot/service/AutoShellMode;", "reason", "", "setScreenState", "screenOn", "userUnlocked", "setForegroundApp", "packageName", "isGame", "setFileWatcherState", "watchedDirectories", "", "setMediaFallbackActive", "pushLog", "logEntry", "Lcom/example/shellshot/data/LogEntry;", "clearLogs", "setLastProcessingResult", "result", "Lcom/example/shellshot/data/ProcessingResult;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AppStateStore {
    private static final int MAX_LOG_COUNT = 100;
    private final MutableStateFlow<AppRuntimeState> _runtimeState = StateFlowKt.MutableStateFlow(new AppRuntimeState(false, null, null, null, 0, false, false, null, false, false, false, false, null, null, null, LayoutKt.LargeDimension, null));
    private final StateFlow<AppRuntimeState> runtimeState = FlowKt.asStateFlow(this._runtimeState);
    public static final int $stable = 8;

    public final StateFlow<AppRuntimeState> getRuntimeState() {
        return this.runtimeState;
    }

    public final void setMonitoringActive(boolean active) {
        AppRuntimeState value;
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(value, active, null, null, null, 0L, false, false, null, false, false, false, false, null, null, null, 32766, null)));
    }

    public final void setMonitoringPhase(MonitoringPhase phase) {
        MonitoringPhase phase2 = phase;
        Intrinsics.checkNotNullParameter(phase2, "phase");
        MutableStateFlow mutableStateFlow = this._runtimeState;
        while (true) {
            AppRuntimeState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, AppRuntimeState.copy$default(value, false, phase2, null, null, 0L, false, false, null, false, false, false, false, null, null, null, 32765, null))) {
                return;
            }
            phase2 = phase;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    public final void setMode(AutoShellMode mode, String reason) {
        AutoShellMode mode2 = mode;
        Intrinsics.checkNotNullParameter(mode2, "mode");
        String reason2 = reason;
        Intrinsics.checkNotNullParameter(reason2, "reason");
        MutableStateFlow mutableStateFlow = this._runtimeState;
        while (true) {
            AppRuntimeState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            AppRuntimeState appRuntimeState = value;
            if (mutableStateFlow2.compareAndSet(value, AppRuntimeState.copy$default(appRuntimeState, false, null, mode2, reason2, appRuntimeState.getMode() != mode2 ? System.currentTimeMillis() : appRuntimeState.getModeChangedAtMillis(), false, false, null, false, false, false, false, null, null, null, 32739, null))) {
                return;
            }
            mode2 = mode;
            reason2 = reason;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    public final void setScreenState(boolean screenOn, boolean userUnlocked) {
        AppRuntimeState value;
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(value, false, null, null, null, 0L, screenOn, userUnlocked, null, false, false, false, false, null, null, null, 32671, null)));
    }

    public final void setForegroundApp(String packageName, boolean isGame) {
        AppRuntimeState value;
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(value, false, null, null, null, 0L, false, false, packageName, isGame, false, false, false, null, null, null, 32383, null)));
    }

    public final void setFileWatcherState(boolean active, List<String> watchedDirectories) {
        List<String> watchedDirectories2 = watchedDirectories;
        Intrinsics.checkNotNullParameter(watchedDirectories2, "watchedDirectories");
        MutableStateFlow mutableStateFlow = this._runtimeState;
        while (true) {
            AppRuntimeState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            AppRuntimeState appRuntimeState = value;
            if (mutableStateFlow2.compareAndSet(value, AppRuntimeState.copy$default(appRuntimeState, false, null, null, null, 0L, false, false, null, false, active, false, !active && appRuntimeState.getMediaFallbackActive(), watchedDirectories2, null, null, 26111, null))) {
                return;
            }
            watchedDirectories2 = watchedDirectories;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    public final void setMediaFallbackActive(boolean active) {
        AppRuntimeState value;
        AppRuntimeState appRuntimeState;
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
            appRuntimeState = value;
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(appRuntimeState, false, null, null, null, 0L, false, false, null, false, false, active, !appRuntimeState.getFileWatcherActive() && active, null, null, null, 29695, null)));
    }

    public final void pushLog(LogEntry logEntry) {
        AppRuntimeState value;
        AppRuntimeState appRuntimeState;
        Intrinsics.checkNotNullParameter(logEntry, "logEntry");
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
            appRuntimeState = value;
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(appRuntimeState, false, null, null, null, 0L, false, false, null, false, false, false, false, null, CollectionsKt.take(CollectionsKt.plus((Collection) CollectionsKt.listOf(logEntry), (Iterable) appRuntimeState.getLogs()), 100), null, 24575, null)));
    }

    public final void clearLogs() {
        AppRuntimeState value;
        MutableStateFlow mutableStateFlow = this._runtimeState;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, AppRuntimeState.copy$default(value, false, null, null, null, 0L, false, false, null, false, false, false, false, null, CollectionsKt.emptyList(), null, 24575, null)));
    }

    public final void setLastProcessingResult(ProcessingResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        MutableStateFlow mutableStateFlow = this._runtimeState;
        while (true) {
            AppRuntimeState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, AppRuntimeState.copy$default(value, false, null, null, null, 0L, false, false, null, false, false, false, false, null, null, result, 16383, null))) {
                return;
            } else {
                mutableStateFlow = mutableStateFlow2;
            }
        }
    }
}
