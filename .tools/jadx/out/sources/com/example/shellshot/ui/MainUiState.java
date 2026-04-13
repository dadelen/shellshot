package com.example.shellshot.ui;

import com.example.shellshot.data.AppSettings;
import com.example.shellshot.data.LogEntry;
import com.example.shellshot.data.MediaAccessLevel;
import com.example.shellshot.data.MonitoringPhase;
import com.example.shellshot.data.PermissionSnapshot;
import com.example.shellshot.data.ProcessingResult;
import com.example.shellshot.media.ScreenshotDirectories;
import com.example.shellshot.media.ScreenshotDirectoryRecommendation;
import com.example.shellshot.service.AutoShellMode;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateImportDraft;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainUiState.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0016\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0016\u0012\b\b\u0002\u0010\"\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0016¢\u0006\u0004\b%\u0010&J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\t\u0010\\\u001a\u00020\u0005HÆ\u0003J\t\u0010]\u001a\u00020\u0007HÆ\u0003J\t\u0010^\u001a\u00020\tHÆ\u0003J\t\u0010_\u001a\u00020\u000bHÆ\u0003J\t\u0010`\u001a\u00020\rHÆ\u0003J\t\u0010a\u001a\u00020\u0007HÆ\u0003J\t\u0010b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010d\u001a\u00020\u0007HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\t\u0010f\u001a\u00020\u0007HÆ\u0003J\t\u0010g\u001a\u00020\u0007HÆ\u0003J\u000f\u0010h\u001a\b\u0012\u0004\u0012\u00020\r0\u0016HÆ\u0003J\u000f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00180\u0016HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\t\u0010k\u001a\u00020\u0007HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\u000f\u0010n\u001a\b\u0012\u0004\u0012\u00020!0\u0016HÆ\u0003J\t\u0010o\u001a\u00020\u0007HÆ\u0003J\u000f\u0010p\u001a\b\u0012\u0004\u0012\u00020$0\u0016HÆ\u0003J\u0085\u0002\u0010q\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00162\b\b\u0002\u0010\"\u001a\u00020\u00072\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0016HÆ\u0001J\u0013\u0010r\u001a\u00020\u00072\b\u0010s\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010t\u001a\u00020uHÖ\u0001J\t\u0010v\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b3\u0010,R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b4\u0010,R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b5\u00102R\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010,R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u0010,R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b8\u0010,R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b9\u0010,R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\b\n\u0000\u001a\u0004\b<\u0010;R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0011\u0010\u001b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b?\u0010,R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0016¢\u0006\b\n\u0000\u001a\u0004\bD\u0010;R\u0011\u0010\"\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bE\u0010,R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0016¢\u0006\b\n\u0000\u001a\u0004\bF\u0010;R\u0013\u0010G\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0011\u0010J\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bK\u0010,R\u0011\u0010L\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bM\u0010,R\u0011\u0010N\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bO\u00102R\u0011\u0010P\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bQ\u00102R\u0011\u0010R\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bS\u00102R\u0017\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u00168F¢\u0006\u0006\u001a\u0004\bV\u0010;R\u0011\u0010W\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bX\u00102R\u0011\u0010Y\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bZ\u00102¨\u0006w"}, d2 = {"Lcom/example/shellshot/ui/MainUiState;", "", "settings", "Lcom/example/shellshot/data/AppSettings;", "permissionSnapshot", "Lcom/example/shellshot/data/PermissionSnapshot;", "monitoringActive", "", "monitoringPhase", "Lcom/example/shellshot/data/MonitoringPhase;", "mode", "Lcom/example/shellshot/service/AutoShellMode;", "modeReason", "", "screenOn", "userUnlocked", "currentForegroundPackage", "gameForeground", "fileWatcherActive", "mediaFallbackActive", "degradedMode", "watchedDirectories", "", "templates", "Lcom/example/shellshot/template/ShellTemplate;", "templateImportDraft", "Lcom/example/shellshot/template/TemplateImportDraft;", "templateImportInProgress", "templateImportAlert", "Lcom/example/shellshot/ui/TemplateImportAlert;", "lastProcessingResult", "Lcom/example/shellshot/data/ProcessingResult;", "recommendedScreenshotDirectories", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "detectingScreenshotDirectories", "logs", "Lcom/example/shellshot/data/LogEntry;", "<init>", "(Lcom/example/shellshot/data/AppSettings;Lcom/example/shellshot/data/PermissionSnapshot;ZLcom/example/shellshot/data/MonitoringPhase;Lcom/example/shellshot/service/AutoShellMode;Ljava/lang/String;ZZLjava/lang/String;ZZZZLjava/util/List;Ljava/util/List;Lcom/example/shellshot/template/TemplateImportDraft;ZLcom/example/shellshot/ui/TemplateImportAlert;Lcom/example/shellshot/data/ProcessingResult;Ljava/util/List;ZLjava/util/List;)V", "getSettings", "()Lcom/example/shellshot/data/AppSettings;", "getPermissionSnapshot", "()Lcom/example/shellshot/data/PermissionSnapshot;", "getMonitoringActive", "()Z", "getMonitoringPhase", "()Lcom/example/shellshot/data/MonitoringPhase;", "getMode", "()Lcom/example/shellshot/service/AutoShellMode;", "getModeReason", "()Ljava/lang/String;", "getScreenOn", "getUserUnlocked", "getCurrentForegroundPackage", "getGameForeground", "getFileWatcherActive", "getMediaFallbackActive", "getDegradedMode", "getWatchedDirectories", "()Ljava/util/List;", "getTemplates", "getTemplateImportDraft", "()Lcom/example/shellshot/template/TemplateImportDraft;", "getTemplateImportInProgress", "getTemplateImportAlert", "()Lcom/example/shellshot/ui/TemplateImportAlert;", "getLastProcessingResult", "()Lcom/example/shellshot/data/ProcessingResult;", "getRecommendedScreenshotDirectories", "getDetectingScreenshotDirectories", "getLogs", "selectedTemplate", "getSelectedTemplate", "()Lcom/example/shellshot/template/ShellTemplate;", "hasCoreStartPermissions", "getHasCoreStartPermissions", "canUseFallback", "getCanUseFallback", "screenshotDirectoryRelativePath", "getScreenshotDirectoryRelativePath", "screenshotDirectoryAbsolutePath", "getScreenshotDirectoryAbsolutePath", "defaultScreenshotDirectoryRelativePath", "getDefaultScreenshotDirectoryRelativePath", "presetScreenshotDirectories", "Lcom/example/shellshot/ui/ScreenshotDirectoryOption;", "getPresetScreenshotDirectories", "mediaAccessLabel", "getMediaAccessLabel", "phaseLabel", "getPhaseLabel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class MainUiState {
    public static final int $stable = 8;
    private final String currentForegroundPackage;
    private final boolean degradedMode;
    private final boolean detectingScreenshotDirectories;
    private final boolean fileWatcherActive;
    private final boolean gameForeground;
    private final ProcessingResult lastProcessingResult;
    private final List<LogEntry> logs;
    private final boolean mediaFallbackActive;
    private final AutoShellMode mode;
    private final String modeReason;
    private final boolean monitoringActive;
    private final MonitoringPhase monitoringPhase;
    private final PermissionSnapshot permissionSnapshot;
    private final List<ScreenshotDirectoryRecommendation> recommendedScreenshotDirectories;
    private final boolean screenOn;
    private final AppSettings settings;
    private final TemplateImportAlert templateImportAlert;
    private final TemplateImportDraft templateImportDraft;
    private final boolean templateImportInProgress;
    private final List<ShellTemplate> templates;
    private final boolean userUnlocked;
    private final List<String> watchedDirectories;

    /* compiled from: MainUiState.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[MediaAccessLevel.values().length];
            try {
                iArr[MediaAccessLevel.Full.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MediaAccessLevel.Partial.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MediaAccessLevel.NotGranted.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[MonitoringPhase.values().length];
            try {
                iArr2[MonitoringPhase.Disabled.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[MonitoringPhase.Starting.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[MonitoringPhase.Monitoring.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[MonitoringPhase.CandidateDetected.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[MonitoringPhase.WaitingFileStable.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[MonitoringPhase.Composing.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[MonitoringPhase.Saving.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[MonitoringPhase.ObserverRegisterFailed.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr2[MonitoringPhase.FileNotStable.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr2[MonitoringPhase.ComposeFailed.ordinal()] = 10;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr2[MonitoringPhase.SaveFailed.ordinal()] = 11;
            } catch (NoSuchFieldError e14) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[AutoShellMode.values().length];
            try {
                iArr3[AutoShellMode.USER_STOPPED.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr3[AutoShellMode.SUSPEND_SCREEN_OFF.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr3[AutoShellMode.SUSPEND_GAME.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr3[AutoShellMode.ACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e18) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public MainUiState() {
        this(null, null, false, null, null, null, false, false, null, false, false, false, false, null, null, null, false, null, null, null, false, null, 4194303, null);
    }

    public static /* synthetic */ MainUiState copy$default(MainUiState mainUiState, AppSettings appSettings, PermissionSnapshot permissionSnapshot, boolean z, MonitoringPhase monitoringPhase, AutoShellMode autoShellMode, String str, boolean z2, boolean z3, String str2, boolean z4, boolean z5, boolean z6, boolean z7, List list, List list2, TemplateImportDraft templateImportDraft, boolean z8, TemplateImportAlert templateImportAlert, ProcessingResult processingResult, List list3, boolean z9, List list4, int i, Object obj) {
        List list5;
        boolean z10;
        AppSettings appSettings2 = (i & 1) != 0 ? mainUiState.settings : appSettings;
        PermissionSnapshot permissionSnapshot2 = (i & 2) != 0 ? mainUiState.permissionSnapshot : permissionSnapshot;
        boolean z11 = (i & 4) != 0 ? mainUiState.monitoringActive : z;
        MonitoringPhase monitoringPhase2 = (i & 8) != 0 ? mainUiState.monitoringPhase : monitoringPhase;
        AutoShellMode autoShellMode2 = (i & 16) != 0 ? mainUiState.mode : autoShellMode;
        String str3 = (i & 32) != 0 ? mainUiState.modeReason : str;
        boolean z12 = (i & 64) != 0 ? mainUiState.screenOn : z2;
        boolean z13 = (i & 128) != 0 ? mainUiState.userUnlocked : z3;
        String str4 = (i & 256) != 0 ? mainUiState.currentForegroundPackage : str2;
        boolean z14 = (i & 512) != 0 ? mainUiState.gameForeground : z4;
        boolean z15 = (i & 1024) != 0 ? mainUiState.fileWatcherActive : z5;
        boolean z16 = (i & 2048) != 0 ? mainUiState.mediaFallbackActive : z6;
        boolean z17 = (i & 4096) != 0 ? mainUiState.degradedMode : z7;
        List list6 = (i & 8192) != 0 ? mainUiState.watchedDirectories : list;
        AppSettings appSettings3 = appSettings2;
        List list7 = (i & 16384) != 0 ? mainUiState.templates : list2;
        TemplateImportDraft templateImportDraft2 = (i & 32768) != 0 ? mainUiState.templateImportDraft : templateImportDraft;
        boolean z18 = (i & 65536) != 0 ? mainUiState.templateImportInProgress : z8;
        TemplateImportAlert templateImportAlert2 = (i & 131072) != 0 ? mainUiState.templateImportAlert : templateImportAlert;
        ProcessingResult processingResult2 = (i & 262144) != 0 ? mainUiState.lastProcessingResult : processingResult;
        List list8 = (i & 524288) != 0 ? mainUiState.recommendedScreenshotDirectories : list3;
        boolean z19 = (i & 1048576) != 0 ? mainUiState.detectingScreenshotDirectories : z9;
        if ((i & 2097152) != 0) {
            z10 = z19;
            list5 = mainUiState.logs;
        } else {
            list5 = list4;
            z10 = z19;
        }
        return mainUiState.copy(appSettings3, permissionSnapshot2, z11, monitoringPhase2, autoShellMode2, str3, z12, z13, str4, z14, z15, z16, z17, list6, list7, templateImportDraft2, z18, templateImportAlert2, processingResult2, list8, z10, list5);
    }

    /* renamed from: component1, reason: from getter */
    public final AppSettings getSettings() {
        return this.settings;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getGameForeground() {
        return this.gameForeground;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getFileWatcherActive() {
        return this.fileWatcherActive;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getMediaFallbackActive() {
        return this.mediaFallbackActive;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getDegradedMode() {
        return this.degradedMode;
    }

    public final List<String> component14() {
        return this.watchedDirectories;
    }

    public final List<ShellTemplate> component15() {
        return this.templates;
    }

    /* renamed from: component16, reason: from getter */
    public final TemplateImportDraft getTemplateImportDraft() {
        return this.templateImportDraft;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getTemplateImportInProgress() {
        return this.templateImportInProgress;
    }

    /* renamed from: component18, reason: from getter */
    public final TemplateImportAlert getTemplateImportAlert() {
        return this.templateImportAlert;
    }

    /* renamed from: component19, reason: from getter */
    public final ProcessingResult getLastProcessingResult() {
        return this.lastProcessingResult;
    }

    /* renamed from: component2, reason: from getter */
    public final PermissionSnapshot getPermissionSnapshot() {
        return this.permissionSnapshot;
    }

    public final List<ScreenshotDirectoryRecommendation> component20() {
        return this.recommendedScreenshotDirectories;
    }

    /* renamed from: component21, reason: from getter */
    public final boolean getDetectingScreenshotDirectories() {
        return this.detectingScreenshotDirectories;
    }

    public final List<LogEntry> component22() {
        return this.logs;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getMonitoringActive() {
        return this.monitoringActive;
    }

    /* renamed from: component4, reason: from getter */
    public final MonitoringPhase getMonitoringPhase() {
        return this.monitoringPhase;
    }

    /* renamed from: component5, reason: from getter */
    public final AutoShellMode getMode() {
        return this.mode;
    }

    /* renamed from: component6, reason: from getter */
    public final String getModeReason() {
        return this.modeReason;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getScreenOn() {
        return this.screenOn;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }

    /* renamed from: component9, reason: from getter */
    public final String getCurrentForegroundPackage() {
        return this.currentForegroundPackage;
    }

    public final MainUiState copy(AppSettings settings, PermissionSnapshot permissionSnapshot, boolean monitoringActive, MonitoringPhase monitoringPhase, AutoShellMode mode, String modeReason, boolean screenOn, boolean userUnlocked, String currentForegroundPackage, boolean gameForeground, boolean fileWatcherActive, boolean mediaFallbackActive, boolean degradedMode, List<String> watchedDirectories, List<ShellTemplate> templates, TemplateImportDraft templateImportDraft, boolean templateImportInProgress, TemplateImportAlert templateImportAlert, ProcessingResult lastProcessingResult, List<ScreenshotDirectoryRecommendation> recommendedScreenshotDirectories, boolean detectingScreenshotDirectories, List<LogEntry> logs) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(permissionSnapshot, "permissionSnapshot");
        Intrinsics.checkNotNullParameter(monitoringPhase, "monitoringPhase");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(modeReason, "modeReason");
        Intrinsics.checkNotNullParameter(watchedDirectories, "watchedDirectories");
        Intrinsics.checkNotNullParameter(templates, "templates");
        Intrinsics.checkNotNullParameter(recommendedScreenshotDirectories, "recommendedScreenshotDirectories");
        Intrinsics.checkNotNullParameter(logs, "logs");
        return new MainUiState(settings, permissionSnapshot, monitoringActive, monitoringPhase, mode, modeReason, screenOn, userUnlocked, currentForegroundPackage, gameForeground, fileWatcherActive, mediaFallbackActive, degradedMode, watchedDirectories, templates, templateImportDraft, templateImportInProgress, templateImportAlert, lastProcessingResult, recommendedScreenshotDirectories, detectingScreenshotDirectories, logs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MainUiState)) {
            return false;
        }
        MainUiState mainUiState = (MainUiState) other;
        return Intrinsics.areEqual(this.settings, mainUiState.settings) && Intrinsics.areEqual(this.permissionSnapshot, mainUiState.permissionSnapshot) && this.monitoringActive == mainUiState.monitoringActive && this.monitoringPhase == mainUiState.monitoringPhase && this.mode == mainUiState.mode && Intrinsics.areEqual(this.modeReason, mainUiState.modeReason) && this.screenOn == mainUiState.screenOn && this.userUnlocked == mainUiState.userUnlocked && Intrinsics.areEqual(this.currentForegroundPackage, mainUiState.currentForegroundPackage) && this.gameForeground == mainUiState.gameForeground && this.fileWatcherActive == mainUiState.fileWatcherActive && this.mediaFallbackActive == mainUiState.mediaFallbackActive && this.degradedMode == mainUiState.degradedMode && Intrinsics.areEqual(this.watchedDirectories, mainUiState.watchedDirectories) && Intrinsics.areEqual(this.templates, mainUiState.templates) && Intrinsics.areEqual(this.templateImportDraft, mainUiState.templateImportDraft) && this.templateImportInProgress == mainUiState.templateImportInProgress && Intrinsics.areEqual(this.templateImportAlert, mainUiState.templateImportAlert) && Intrinsics.areEqual(this.lastProcessingResult, mainUiState.lastProcessingResult) && Intrinsics.areEqual(this.recommendedScreenshotDirectories, mainUiState.recommendedScreenshotDirectories) && this.detectingScreenshotDirectories == mainUiState.detectingScreenshotDirectories && Intrinsics.areEqual(this.logs, mainUiState.logs);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((this.settings.hashCode() * 31) + this.permissionSnapshot.hashCode()) * 31) + Boolean.hashCode(this.monitoringActive)) * 31) + this.monitoringPhase.hashCode()) * 31) + this.mode.hashCode()) * 31) + this.modeReason.hashCode()) * 31) + Boolean.hashCode(this.screenOn)) * 31) + Boolean.hashCode(this.userUnlocked)) * 31) + (this.currentForegroundPackage == null ? 0 : this.currentForegroundPackage.hashCode())) * 31) + Boolean.hashCode(this.gameForeground)) * 31) + Boolean.hashCode(this.fileWatcherActive)) * 31) + Boolean.hashCode(this.mediaFallbackActive)) * 31) + Boolean.hashCode(this.degradedMode)) * 31) + this.watchedDirectories.hashCode()) * 31) + this.templates.hashCode()) * 31) + (this.templateImportDraft == null ? 0 : this.templateImportDraft.hashCode())) * 31) + Boolean.hashCode(this.templateImportInProgress)) * 31) + (this.templateImportAlert == null ? 0 : this.templateImportAlert.hashCode())) * 31) + (this.lastProcessingResult != null ? this.lastProcessingResult.hashCode() : 0)) * 31) + this.recommendedScreenshotDirectories.hashCode()) * 31) + Boolean.hashCode(this.detectingScreenshotDirectories)) * 31) + this.logs.hashCode();
    }

    public String toString() {
        return "MainUiState(settings=" + this.settings + ", permissionSnapshot=" + this.permissionSnapshot + ", monitoringActive=" + this.monitoringActive + ", monitoringPhase=" + this.monitoringPhase + ", mode=" + this.mode + ", modeReason=" + this.modeReason + ", screenOn=" + this.screenOn + ", userUnlocked=" + this.userUnlocked + ", currentForegroundPackage=" + this.currentForegroundPackage + ", gameForeground=" + this.gameForeground + ", fileWatcherActive=" + this.fileWatcherActive + ", mediaFallbackActive=" + this.mediaFallbackActive + ", degradedMode=" + this.degradedMode + ", watchedDirectories=" + this.watchedDirectories + ", templates=" + this.templates + ", templateImportDraft=" + this.templateImportDraft + ", templateImportInProgress=" + this.templateImportInProgress + ", templateImportAlert=" + this.templateImportAlert + ", lastProcessingResult=" + this.lastProcessingResult + ", recommendedScreenshotDirectories=" + this.recommendedScreenshotDirectories + ", detectingScreenshotDirectories=" + this.detectingScreenshotDirectories + ", logs=" + this.logs + ")";
    }

    public MainUiState(AppSettings settings, PermissionSnapshot permissionSnapshot, boolean monitoringActive, MonitoringPhase monitoringPhase, AutoShellMode mode, String modeReason, boolean screenOn, boolean userUnlocked, String currentForegroundPackage, boolean gameForeground, boolean fileWatcherActive, boolean mediaFallbackActive, boolean degradedMode, List<String> watchedDirectories, List<ShellTemplate> templates, TemplateImportDraft templateImportDraft, boolean templateImportInProgress, TemplateImportAlert templateImportAlert, ProcessingResult lastProcessingResult, List<ScreenshotDirectoryRecommendation> recommendedScreenshotDirectories, boolean detectingScreenshotDirectories, List<LogEntry> logs) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(permissionSnapshot, "permissionSnapshot");
        Intrinsics.checkNotNullParameter(monitoringPhase, "monitoringPhase");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(modeReason, "modeReason");
        Intrinsics.checkNotNullParameter(watchedDirectories, "watchedDirectories");
        Intrinsics.checkNotNullParameter(templates, "templates");
        Intrinsics.checkNotNullParameter(recommendedScreenshotDirectories, "recommendedScreenshotDirectories");
        Intrinsics.checkNotNullParameter(logs, "logs");
        this.settings = settings;
        this.permissionSnapshot = permissionSnapshot;
        this.monitoringActive = monitoringActive;
        this.monitoringPhase = monitoringPhase;
        this.mode = mode;
        this.modeReason = modeReason;
        this.screenOn = screenOn;
        this.userUnlocked = userUnlocked;
        this.currentForegroundPackage = currentForegroundPackage;
        this.gameForeground = gameForeground;
        this.fileWatcherActive = fileWatcherActive;
        this.mediaFallbackActive = mediaFallbackActive;
        this.degradedMode = degradedMode;
        this.watchedDirectories = watchedDirectories;
        this.templates = templates;
        this.templateImportDraft = templateImportDraft;
        this.templateImportInProgress = templateImportInProgress;
        this.templateImportAlert = templateImportAlert;
        this.lastProcessingResult = lastProcessingResult;
        this.recommendedScreenshotDirectories = recommendedScreenshotDirectories;
        this.detectingScreenshotDirectories = detectingScreenshotDirectories;
        this.logs = logs;
    }

    public /* synthetic */ MainUiState(AppSettings appSettings, PermissionSnapshot permissionSnapshot, boolean z, MonitoringPhase monitoringPhase, AutoShellMode autoShellMode, String str, boolean z2, boolean z3, String str2, boolean z4, boolean z5, boolean z6, boolean z7, List list, List list2, TemplateImportDraft templateImportDraft, boolean z8, TemplateImportAlert templateImportAlert, ProcessingResult processingResult, List list3, boolean z9, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AppSettings(null, null, false, false, false, false, false, null, null, null, 1023, null) : appSettings, (i & 2) != 0 ? new PermissionSnapshot(false, false, false, false, null, 31, null) : permissionSnapshot, (i & 4) != 0 ? false : z, (i & 8) != 0 ? MonitoringPhase.Disabled : monitoringPhase, (i & 16) != 0 ? AutoShellMode.USER_STOPPED : autoShellMode, (i & 32) != 0 ? "" : str, (i & 64) != 0 ? true : z2, (i & 128) == 0 ? z3 : true, (i & 256) != 0 ? null : str2, (i & 512) != 0 ? false : z4, (i & 1024) != 0 ? false : z5, (i & 2048) != 0 ? false : z6, (i & 4096) != 0 ? false : z7, (i & 8192) != 0 ? CollectionsKt.emptyList() : list, (i & 16384) != 0 ? CollectionsKt.emptyList() : list2, (i & 32768) != 0 ? null : templateImportDraft, (i & 65536) != 0 ? false : z8, (i & 131072) != 0 ? null : templateImportAlert, (i & 262144) != 0 ? null : processingResult, (i & 524288) != 0 ? CollectionsKt.emptyList() : list3, (i & 1048576) != 0 ? false : z9, (i & 2097152) != 0 ? CollectionsKt.emptyList() : list4);
    }

    public final AppSettings getSettings() {
        return this.settings;
    }

    public final PermissionSnapshot getPermissionSnapshot() {
        return this.permissionSnapshot;
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

    public final List<ShellTemplate> getTemplates() {
        return this.templates;
    }

    public final TemplateImportDraft getTemplateImportDraft() {
        return this.templateImportDraft;
    }

    public final boolean getTemplateImportInProgress() {
        return this.templateImportInProgress;
    }

    public final TemplateImportAlert getTemplateImportAlert() {
        return this.templateImportAlert;
    }

    public final ProcessingResult getLastProcessingResult() {
        return this.lastProcessingResult;
    }

    public final List<ScreenshotDirectoryRecommendation> getRecommendedScreenshotDirectories() {
        return this.recommendedScreenshotDirectories;
    }

    public final boolean getDetectingScreenshotDirectories() {
        return this.detectingScreenshotDirectories;
    }

    public final List<LogEntry> getLogs() {
        return this.logs;
    }

    public final ShellTemplate getSelectedTemplate() {
        Object obj;
        Iterator it = this.templates.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((ShellTemplate) obj).getId(), this.settings.getSelectedTemplateId())) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        ShellTemplate shellTemplate = (ShellTemplate) obj;
        return shellTemplate == null ? (ShellTemplate) CollectionsKt.firstOrNull((List) this.templates) : shellTemplate;
    }

    public final boolean getHasCoreStartPermissions() {
        return this.permissionSnapshot.getNotificationsGranted() && this.permissionSnapshot.getAllFilesGranted();
    }

    public final boolean getCanUseFallback() {
        return this.settings.getMediaStoreFallbackEnabled() && this.permissionSnapshot.getReadImagesGranted();
    }

    public final String getScreenshotDirectoryRelativePath() {
        return ScreenshotDirectories.INSTANCE.resolveScreenshotRelativePath(this.settings.getScreenshotRelativePath());
    }

    public final String getScreenshotDirectoryAbsolutePath() {
        String absolutePath = ScreenshotDirectories.INSTANCE.screenshotDirectory(getScreenshotDirectoryRelativePath()).getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    public final String getDefaultScreenshotDirectoryRelativePath() {
        return ScreenshotDirectories.DEFAULT_SCREENSHOT_RELATIVE_PATH;
    }

    public final List<ScreenshotDirectoryOption> getPresetScreenshotDirectories() {
        Iterable<String> presetScreenshotRelativePaths = ScreenshotDirectories.INSTANCE.presetScreenshotRelativePaths();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(presetScreenshotRelativePaths, 10));
        for (String str : presetScreenshotRelativePaths) {
            String absolutePath = ScreenshotDirectories.INSTANCE.screenshotDirectory(str).getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            arrayList.add(new ScreenshotDirectoryOption(str, absolutePath));
        }
        return (List) arrayList;
    }

    public final String getMediaAccessLabel() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.permissionSnapshot.getMediaAccessLevel().ordinal()]) {
            case 1:
                return "完整媒体访问";
            case 2:
                return "部分媒体访问";
            case 3:
                return "未授予媒体访问";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String getPhaseLabel() {
        switch (WhenMappings.$EnumSwitchMapping$2[this.mode.ordinal()]) {
            case 1:
                return "未开启";
            case 2:
                return "息屏暂停";
            case 3:
                return "游戏暂停";
            case 4:
                switch (WhenMappings.$EnumSwitchMapping$1[this.monitoringPhase.ordinal()]) {
                    case 1:
                        return "未开启";
                    case 2:
                        return "启动中";
                    case 3:
                        return this.degradedMode ? "降级监听中" : "监听中";
                    case 4:
                        return "已发现截图";
                    case 5:
                        return "等待文件稳定";
                    case 6:
                        return "正在合成";
                    case 7:
                        return "正在保存";
                    case 8:
                        return "监听注册失败";
                    case 9:
                        return "文件未稳定";
                    case 10:
                        return "合成失败";
                    case 11:
                        return "保存失败";
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
