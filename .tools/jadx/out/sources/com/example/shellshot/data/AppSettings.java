package com.example.shellshot.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\fHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003Jy\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0001J\u0013\u0010+\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001e¨\u00060"}, d2 = {"Lcom/example/shellshot/data/AppSettings;", "", "selectedTemplateId", "", "screenshotRelativePath", "screenshotDirectoryRecommendationsInitialized", "", "autoDeleteOriginal", "debugModeEnabled", "monitoringEnabled", "mediaStoreFallbackEnabled", "outputNamingStrategy", "Lcom/example/shellshot/data/OutputNamingStrategy;", "recentProcessedKeys", "", "gamePackageRules", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZZZZLcom/example/shellshot/data/OutputNamingStrategy;Ljava/util/List;Ljava/util/List;)V", "getSelectedTemplateId", "()Ljava/lang/String;", "getScreenshotRelativePath", "getScreenshotDirectoryRecommendationsInitialized", "()Z", "getAutoDeleteOriginal", "getDebugModeEnabled", "getMonitoringEnabled", "getMediaStoreFallbackEnabled", "getOutputNamingStrategy", "()Lcom/example/shellshot/data/OutputNamingStrategy;", "getRecentProcessedKeys", "()Ljava/util/List;", "getGamePackageRules", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class AppSettings {
    public static final int $stable = 8;
    private final boolean autoDeleteOriginal;
    private final boolean debugModeEnabled;
    private final List<String> gamePackageRules;
    private final boolean mediaStoreFallbackEnabled;
    private final boolean monitoringEnabled;
    private final OutputNamingStrategy outputNamingStrategy;
    private final List<String> recentProcessedKeys;
    private final boolean screenshotDirectoryRecommendationsInitialized;
    private final String screenshotRelativePath;
    private final String selectedTemplateId;

    public AppSettings() {
        this(null, null, false, false, false, false, false, null, null, null, 1023, null);
    }

    public static /* synthetic */ AppSettings copy$default(AppSettings appSettings, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, OutputNamingStrategy outputNamingStrategy, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appSettings.selectedTemplateId;
        }
        if ((i & 2) != 0) {
            str2 = appSettings.screenshotRelativePath;
        }
        if ((i & 4) != 0) {
            z = appSettings.screenshotDirectoryRecommendationsInitialized;
        }
        if ((i & 8) != 0) {
            z2 = appSettings.autoDeleteOriginal;
        }
        if ((i & 16) != 0) {
            z3 = appSettings.debugModeEnabled;
        }
        if ((i & 32) != 0) {
            z4 = appSettings.monitoringEnabled;
        }
        if ((i & 64) != 0) {
            z5 = appSettings.mediaStoreFallbackEnabled;
        }
        if ((i & 128) != 0) {
            outputNamingStrategy = appSettings.outputNamingStrategy;
        }
        if ((i & 256) != 0) {
            list = appSettings.recentProcessedKeys;
        }
        if ((i & 512) != 0) {
            list2 = appSettings.gamePackageRules;
        }
        List list3 = list;
        List list4 = list2;
        boolean z6 = z5;
        OutputNamingStrategy outputNamingStrategy2 = outputNamingStrategy;
        boolean z7 = z3;
        boolean z8 = z4;
        return appSettings.copy(str, str2, z, z2, z7, z8, z6, outputNamingStrategy2, list3, list4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSelectedTemplateId() {
        return this.selectedTemplateId;
    }

    public final List<String> component10() {
        return this.gamePackageRules;
    }

    /* renamed from: component2, reason: from getter */
    public final String getScreenshotRelativePath() {
        return this.screenshotRelativePath;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getScreenshotDirectoryRecommendationsInitialized() {
        return this.screenshotDirectoryRecommendationsInitialized;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getAutoDeleteOriginal() {
        return this.autoDeleteOriginal;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getDebugModeEnabled() {
        return this.debugModeEnabled;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getMonitoringEnabled() {
        return this.monitoringEnabled;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getMediaStoreFallbackEnabled() {
        return this.mediaStoreFallbackEnabled;
    }

    /* renamed from: component8, reason: from getter */
    public final OutputNamingStrategy getOutputNamingStrategy() {
        return this.outputNamingStrategy;
    }

    public final List<String> component9() {
        return this.recentProcessedKeys;
    }

    public final AppSettings copy(String selectedTemplateId, String screenshotRelativePath, boolean screenshotDirectoryRecommendationsInitialized, boolean autoDeleteOriginal, boolean debugModeEnabled, boolean monitoringEnabled, boolean mediaStoreFallbackEnabled, OutputNamingStrategy outputNamingStrategy, List<String> recentProcessedKeys, List<String> gamePackageRules) {
        Intrinsics.checkNotNullParameter(selectedTemplateId, "selectedTemplateId");
        Intrinsics.checkNotNullParameter(screenshotRelativePath, "screenshotRelativePath");
        Intrinsics.checkNotNullParameter(outputNamingStrategy, "outputNamingStrategy");
        Intrinsics.checkNotNullParameter(recentProcessedKeys, "recentProcessedKeys");
        Intrinsics.checkNotNullParameter(gamePackageRules, "gamePackageRules");
        return new AppSettings(selectedTemplateId, screenshotRelativePath, screenshotDirectoryRecommendationsInitialized, autoDeleteOriginal, debugModeEnabled, monitoringEnabled, mediaStoreFallbackEnabled, outputNamingStrategy, recentProcessedKeys, gamePackageRules);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppSettings)) {
            return false;
        }
        AppSettings appSettings = (AppSettings) other;
        return Intrinsics.areEqual(this.selectedTemplateId, appSettings.selectedTemplateId) && Intrinsics.areEqual(this.screenshotRelativePath, appSettings.screenshotRelativePath) && this.screenshotDirectoryRecommendationsInitialized == appSettings.screenshotDirectoryRecommendationsInitialized && this.autoDeleteOriginal == appSettings.autoDeleteOriginal && this.debugModeEnabled == appSettings.debugModeEnabled && this.monitoringEnabled == appSettings.monitoringEnabled && this.mediaStoreFallbackEnabled == appSettings.mediaStoreFallbackEnabled && this.outputNamingStrategy == appSettings.outputNamingStrategy && Intrinsics.areEqual(this.recentProcessedKeys, appSettings.recentProcessedKeys) && Intrinsics.areEqual(this.gamePackageRules, appSettings.gamePackageRules);
    }

    public int hashCode() {
        return (((((((((((((((((this.selectedTemplateId.hashCode() * 31) + this.screenshotRelativePath.hashCode()) * 31) + Boolean.hashCode(this.screenshotDirectoryRecommendationsInitialized)) * 31) + Boolean.hashCode(this.autoDeleteOriginal)) * 31) + Boolean.hashCode(this.debugModeEnabled)) * 31) + Boolean.hashCode(this.monitoringEnabled)) * 31) + Boolean.hashCode(this.mediaStoreFallbackEnabled)) * 31) + this.outputNamingStrategy.hashCode()) * 31) + this.recentProcessedKeys.hashCode()) * 31) + this.gamePackageRules.hashCode();
    }

    public String toString() {
        return "AppSettings(selectedTemplateId=" + this.selectedTemplateId + ", screenshotRelativePath=" + this.screenshotRelativePath + ", screenshotDirectoryRecommendationsInitialized=" + this.screenshotDirectoryRecommendationsInitialized + ", autoDeleteOriginal=" + this.autoDeleteOriginal + ", debugModeEnabled=" + this.debugModeEnabled + ", monitoringEnabled=" + this.monitoringEnabled + ", mediaStoreFallbackEnabled=" + this.mediaStoreFallbackEnabled + ", outputNamingStrategy=" + this.outputNamingStrategy + ", recentProcessedKeys=" + this.recentProcessedKeys + ", gamePackageRules=" + this.gamePackageRules + ")";
    }

    public AppSettings(String selectedTemplateId, String screenshotRelativePath, boolean screenshotDirectoryRecommendationsInitialized, boolean autoDeleteOriginal, boolean debugModeEnabled, boolean monitoringEnabled, boolean mediaStoreFallbackEnabled, OutputNamingStrategy outputNamingStrategy, List<String> recentProcessedKeys, List<String> gamePackageRules) {
        Intrinsics.checkNotNullParameter(selectedTemplateId, "selectedTemplateId");
        Intrinsics.checkNotNullParameter(screenshotRelativePath, "screenshotRelativePath");
        Intrinsics.checkNotNullParameter(outputNamingStrategy, "outputNamingStrategy");
        Intrinsics.checkNotNullParameter(recentProcessedKeys, "recentProcessedKeys");
        Intrinsics.checkNotNullParameter(gamePackageRules, "gamePackageRules");
        this.selectedTemplateId = selectedTemplateId;
        this.screenshotRelativePath = screenshotRelativePath;
        this.screenshotDirectoryRecommendationsInitialized = screenshotDirectoryRecommendationsInitialized;
        this.autoDeleteOriginal = autoDeleteOriginal;
        this.debugModeEnabled = debugModeEnabled;
        this.monitoringEnabled = monitoringEnabled;
        this.mediaStoreFallbackEnabled = mediaStoreFallbackEnabled;
        this.outputNamingStrategy = outputNamingStrategy;
        this.recentProcessedKeys = recentProcessedKeys;
        this.gamePackageRules = gamePackageRules;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ AppSettings(java.lang.String r3, java.lang.String r4, boolean r5, boolean r6, boolean r7, boolean r8, boolean r9, com.example.shellshot.data.OutputNamingStrategy r10, java.util.List r11, java.util.List r12, int r13, kotlin.jvm.internal.DefaultConstructorMarker r14) {
        /*
            r2 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L6
            java.lang.String r3 = ""
        L6:
            r14 = r13 & 2
            if (r14 == 0) goto Lc
            java.lang.String r4 = "Pictures/Screenshots/"
        Lc:
            r14 = r13 & 4
            r0 = 0
            if (r14 == 0) goto L12
            r5 = r0
        L12:
            r14 = r13 & 8
            r1 = 1
            if (r14 == 0) goto L18
            r6 = r1
        L18:
            r14 = r13 & 16
            if (r14 == 0) goto L1d
            r7 = r0
        L1d:
            r14 = r13 & 32
            if (r14 == 0) goto L22
            r8 = r0
        L22:
            r14 = r13 & 64
            if (r14 == 0) goto L27
            r9 = r1
        L27:
            r14 = r13 & 128(0x80, float:1.8E-43)
            if (r14 == 0) goto L2d
            com.example.shellshot.data.OutputNamingStrategy r10 = com.example.shellshot.data.OutputNamingStrategy.Prefix
        L2d:
            r14 = r13 & 256(0x100, float:3.59E-43)
            if (r14 == 0) goto L35
            java.util.List r11 = kotlin.collections.CollectionsKt.emptyList()
        L35:
            r13 = r13 & 512(0x200, float:7.17E-43)
            if (r13 == 0) goto L3f
            java.util.List r12 = com.example.shellshot.data.AppPrefsKt.access$getDefaultGamePackageRules$p()
            r14 = r12
            goto L40
        L3f:
            r14 = r12
        L40:
            r12 = r10
            r13 = r11
            r10 = r8
            r11 = r9
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.data.AppSettings.<init>(java.lang.String, java.lang.String, boolean, boolean, boolean, boolean, boolean, com.example.shellshot.data.OutputNamingStrategy, java.util.List, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getSelectedTemplateId() {
        return this.selectedTemplateId;
    }

    public final String getScreenshotRelativePath() {
        return this.screenshotRelativePath;
    }

    public final boolean getScreenshotDirectoryRecommendationsInitialized() {
        return this.screenshotDirectoryRecommendationsInitialized;
    }

    public final boolean getAutoDeleteOriginal() {
        return this.autoDeleteOriginal;
    }

    public final boolean getDebugModeEnabled() {
        return this.debugModeEnabled;
    }

    public final boolean getMonitoringEnabled() {
        return this.monitoringEnabled;
    }

    public final boolean getMediaStoreFallbackEnabled() {
        return this.mediaStoreFallbackEnabled;
    }

    public final OutputNamingStrategy getOutputNamingStrategy() {
        return this.outputNamingStrategy;
    }

    public final List<String> getRecentProcessedKeys() {
        return this.recentProcessedKeys;
    }

    public final List<String> getGamePackageRules() {
        return this.gamePackageRules;
    }
}
