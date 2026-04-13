package com.example.shellshot.data;

import androidx.compose.ui.layout.LayoutKt;
import com.example.shellshot.service.AutoShellMode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppStateStore.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0014\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\tHÆ\u0003J\t\u00105\u001a\u00020\u000bHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\t0\u0014HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00160\u0014HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0018HÆ\u0003J¯\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001J\u0013\u0010A\u001a\u00020\u00032\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020DHÖ\u0001J\t\u0010E\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001cR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006F"}, d2 = {"Lcom/example/shellshot/data/AppRuntimeState;", "", "monitoringActive", "", "monitoringPhase", "Lcom/example/shellshot/data/MonitoringPhase;", "mode", "Lcom/example/shellshot/service/AutoShellMode;", "modeReason", "", "modeChangedAtMillis", "", "screenOn", "userUnlocked", "currentForegroundPackage", "gameForeground", "fileWatcherActive", "mediaFallbackActive", "degradedMode", "watchedDirectories", "", "logs", "Lcom/example/shellshot/data/LogEntry;", "lastProcessingResult", "Lcom/example/shellshot/data/ProcessingResult;", "<init>", "(ZLcom/example/shellshot/data/MonitoringPhase;Lcom/example/shellshot/service/AutoShellMode;Ljava/lang/String;JZZLjava/lang/String;ZZZZLjava/util/List;Ljava/util/List;Lcom/example/shellshot/data/ProcessingResult;)V", "getMonitoringActive", "()Z", "getMonitoringPhase", "()Lcom/example/shellshot/data/MonitoringPhase;", "getMode", "()Lcom/example/shellshot/service/AutoShellMode;", "getModeReason", "()Ljava/lang/String;", "getModeChangedAtMillis", "()J", "getScreenOn", "getUserUnlocked", "getCurrentForegroundPackage", "getGameForeground", "getFileWatcherActive", "getMediaFallbackActive", "getDegradedMode", "getWatchedDirectories", "()Ljava/util/List;", "getLogs", "getLastProcessingResult", "()Lcom/example/shellshot/data/ProcessingResult;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class AppRuntimeState {
    public static final int $stable = 8;
    private final String currentForegroundPackage;
    private final boolean degradedMode;
    private final boolean fileWatcherActive;
    private final boolean gameForeground;
    private final ProcessingResult lastProcessingResult;
    private final List<LogEntry> logs;
    private final boolean mediaFallbackActive;
    private final AutoShellMode mode;
    private final long modeChangedAtMillis;
    private final String modeReason;
    private final boolean monitoringActive;
    private final MonitoringPhase monitoringPhase;
    private final boolean screenOn;
    private final boolean userUnlocked;
    private final List<String> watchedDirectories;

    public AppRuntimeState() {
        this(false, null, null, null, 0L, false, false, null, false, false, false, false, null, null, null, LayoutKt.LargeDimension, null);
    }

    public static /* synthetic */ AppRuntimeState copy$default(AppRuntimeState appRuntimeState, boolean z, MonitoringPhase monitoringPhase, AutoShellMode autoShellMode, String str, long j, boolean z2, boolean z3, String str2, boolean z4, boolean z5, boolean z6, boolean z7, List list, List list2, ProcessingResult processingResult, int i, Object obj) {
        boolean z8 = (i & 1) != 0 ? appRuntimeState.monitoringActive : z;
        return appRuntimeState.copy(z8, (i & 2) != 0 ? appRuntimeState.monitoringPhase : monitoringPhase, (i & 4) != 0 ? appRuntimeState.mode : autoShellMode, (i & 8) != 0 ? appRuntimeState.modeReason : str, (i & 16) != 0 ? appRuntimeState.modeChangedAtMillis : j, (i & 32) != 0 ? appRuntimeState.screenOn : z2, (i & 64) != 0 ? appRuntimeState.userUnlocked : z3, (i & 128) != 0 ? appRuntimeState.currentForegroundPackage : str2, (i & 256) != 0 ? appRuntimeState.gameForeground : z4, (i & 512) != 0 ? appRuntimeState.fileWatcherActive : z5, (i & 1024) != 0 ? appRuntimeState.mediaFallbackActive : z6, (i & 2048) != 0 ? appRuntimeState.degradedMode : z7, (i & 4096) != 0 ? appRuntimeState.watchedDirectories : list, (i & 8192) != 0 ? appRuntimeState.logs : list2, (i & 16384) != 0 ? appRuntimeState.lastProcessingResult : processingResult);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getMonitoringActive() {
        return this.monitoringActive;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getFileWatcherActive() {
        return this.fileWatcherActive;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getMediaFallbackActive() {
        return this.mediaFallbackActive;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getDegradedMode() {
        return this.degradedMode;
    }

    public final List<String> component13() {
        return this.watchedDirectories;
    }

    public final List<LogEntry> component14() {
        return this.logs;
    }

    /* renamed from: component15, reason: from getter */
    public final ProcessingResult getLastProcessingResult() {
        return this.lastProcessingResult;
    }

    /* renamed from: component2, reason: from getter */
    public final MonitoringPhase getMonitoringPhase() {
        return this.monitoringPhase;
    }

    /* renamed from: component3, reason: from getter */
    public final AutoShellMode getMode() {
        return this.mode;
    }

    /* renamed from: component4, reason: from getter */
    public final String getModeReason() {
        return this.modeReason;
    }

    /* renamed from: component5, reason: from getter */
    public final long getModeChangedAtMillis() {
        return this.modeChangedAtMillis;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getScreenOn() {
        return this.screenOn;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCurrentForegroundPackage() {
        return this.currentForegroundPackage;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getGameForeground() {
        return this.gameForeground;
    }

    public final AppRuntimeState copy(boolean monitoringActive, MonitoringPhase monitoringPhase, AutoShellMode mode, String modeReason, long modeChangedAtMillis, boolean screenOn, boolean userUnlocked, String currentForegroundPackage, boolean gameForeground, boolean fileWatcherActive, boolean mediaFallbackActive, boolean degradedMode, List<String> watchedDirectories, List<LogEntry> logs, ProcessingResult lastProcessingResult) {
        Intrinsics.checkNotNullParameter(monitoringPhase, "monitoringPhase");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(modeReason, "modeReason");
        Intrinsics.checkNotNullParameter(watchedDirectories, "watchedDirectories");
        Intrinsics.checkNotNullParameter(logs, "logs");
        return new AppRuntimeState(monitoringActive, monitoringPhase, mode, modeReason, modeChangedAtMillis, screenOn, userUnlocked, currentForegroundPackage, gameForeground, fileWatcherActive, mediaFallbackActive, degradedMode, watchedDirectories, logs, lastProcessingResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppRuntimeState)) {
            return false;
        }
        AppRuntimeState appRuntimeState = (AppRuntimeState) other;
        return this.monitoringActive == appRuntimeState.monitoringActive && this.monitoringPhase == appRuntimeState.monitoringPhase && this.mode == appRuntimeState.mode && Intrinsics.areEqual(this.modeReason, appRuntimeState.modeReason) && this.modeChangedAtMillis == appRuntimeState.modeChangedAtMillis && this.screenOn == appRuntimeState.screenOn && this.userUnlocked == appRuntimeState.userUnlocked && Intrinsics.areEqual(this.currentForegroundPackage, appRuntimeState.currentForegroundPackage) && this.gameForeground == appRuntimeState.gameForeground && this.fileWatcherActive == appRuntimeState.fileWatcherActive && this.mediaFallbackActive == appRuntimeState.mediaFallbackActive && this.degradedMode == appRuntimeState.degradedMode && Intrinsics.areEqual(this.watchedDirectories, appRuntimeState.watchedDirectories) && Intrinsics.areEqual(this.logs, appRuntimeState.logs) && Intrinsics.areEqual(this.lastProcessingResult, appRuntimeState.lastProcessingResult);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((Boolean.hashCode(this.monitoringActive) * 31) + this.monitoringPhase.hashCode()) * 31) + this.mode.hashCode()) * 31) + this.modeReason.hashCode()) * 31) + Long.hashCode(this.modeChangedAtMillis)) * 31) + Boolean.hashCode(this.screenOn)) * 31) + Boolean.hashCode(this.userUnlocked)) * 31) + (this.currentForegroundPackage == null ? 0 : this.currentForegroundPackage.hashCode())) * 31) + Boolean.hashCode(this.gameForeground)) * 31) + Boolean.hashCode(this.fileWatcherActive)) * 31) + Boolean.hashCode(this.mediaFallbackActive)) * 31) + Boolean.hashCode(this.degradedMode)) * 31) + this.watchedDirectories.hashCode()) * 31) + this.logs.hashCode()) * 31) + (this.lastProcessingResult != null ? this.lastProcessingResult.hashCode() : 0);
    }

    public String toString() {
        return "AppRuntimeState(monitoringActive=" + this.monitoringActive + ", monitoringPhase=" + this.monitoringPhase + ", mode=" + this.mode + ", modeReason=" + this.modeReason + ", modeChangedAtMillis=" + this.modeChangedAtMillis + ", screenOn=" + this.screenOn + ", userUnlocked=" + this.userUnlocked + ", currentForegroundPackage=" + this.currentForegroundPackage + ", gameForeground=" + this.gameForeground + ", fileWatcherActive=" + this.fileWatcherActive + ", mediaFallbackActive=" + this.mediaFallbackActive + ", degradedMode=" + this.degradedMode + ", watchedDirectories=" + this.watchedDirectories + ", logs=" + this.logs + ", lastProcessingResult=" + this.lastProcessingResult + ")";
    }

    public AppRuntimeState(boolean monitoringActive, MonitoringPhase monitoringPhase, AutoShellMode mode, String modeReason, long modeChangedAtMillis, boolean screenOn, boolean userUnlocked, String currentForegroundPackage, boolean gameForeground, boolean fileWatcherActive, boolean mediaFallbackActive, boolean degradedMode, List<String> watchedDirectories, List<LogEntry> logs, ProcessingResult lastProcessingResult) {
        Intrinsics.checkNotNullParameter(monitoringPhase, "monitoringPhase");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(modeReason, "modeReason");
        Intrinsics.checkNotNullParameter(watchedDirectories, "watchedDirectories");
        Intrinsics.checkNotNullParameter(logs, "logs");
        this.monitoringActive = monitoringActive;
        this.monitoringPhase = monitoringPhase;
        this.mode = mode;
        this.modeReason = modeReason;
        this.modeChangedAtMillis = modeChangedAtMillis;
        this.screenOn = screenOn;
        this.userUnlocked = userUnlocked;
        this.currentForegroundPackage = currentForegroundPackage;
        this.gameForeground = gameForeground;
        this.fileWatcherActive = fileWatcherActive;
        this.mediaFallbackActive = mediaFallbackActive;
        this.degradedMode = degradedMode;
        this.watchedDirectories = watchedDirectories;
        this.logs = logs;
        this.lastProcessingResult = lastProcessingResult;
    }

    public /* synthetic */ AppRuntimeState(boolean z, MonitoringPhase monitoringPhase, AutoShellMode autoShellMode, String str, long j, boolean z2, boolean z3, String str2, boolean z4, boolean z5, boolean z6, boolean z7, List list, List list2, ProcessingResult processingResult, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? MonitoringPhase.Disabled : monitoringPhase, (i & 4) != 0 ? AutoShellMode.USER_STOPPED : autoShellMode, (i & 8) != 0 ? "用户未开启监听" : str, (i & 16) != 0 ? System.currentTimeMillis() : j, (i & 32) != 0 ? true : z2, (i & 64) == 0 ? z3 : true, (i & 128) != 0 ? null : str2, (i & 256) != 0 ? false : z4, (i & 512) != 0 ? false : z5, (i & 1024) != 0 ? false : z6, (i & 2048) == 0 ? z7 : false, (i & 4096) != 0 ? CollectionsKt.emptyList() : list, (i & 8192) != 0 ? CollectionsKt.emptyList() : list2, (i & 16384) != 0 ? null : processingResult);
    }

    public final boolean getMonitoringActive() {
        return this.monitoringActive;
    }

    public final MonitoringPhase getMonitoringPhase() {
        return this.monitoringPhase;
    }

    public final AutoShellMode getMode() {
        return this.mode;
    }

    public final String getModeReason() {
        return this.modeReason;
    }

    public final long getModeChangedAtMillis() {
        return this.modeChangedAtMillis;
    }

    public final boolean getScreenOn() {
        return this.screenOn;
    }

    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }

    public final String getCurrentForegroundPackage() {
        return this.currentForegroundPackage;
    }

    public final boolean getGameForeground() {
        return this.gameForeground;
    }

    public final boolean getFileWatcherActive() {
        return this.fileWatcherActive;
    }

    public final boolean getMediaFallbackActive() {
        return this.mediaFallbackActive;
    }

    public final boolean getDegradedMode() {
        return this.degradedMode;
    }

    public final List<String> getWatchedDirectories() {
        return this.watchedDirectories;
    }

    public final List<LogEntry> getLogs() {
        return this.logs;
    }

    public final ProcessingResult getLastProcessingResult() {
        return this.lastProcessingResult;
    }
}
