package com.example.shellshot.media;

import android.net.Uri;
import com.example.shellshot.data.AppPrefs;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: LatestScreenshotResolver.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ4\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010\u0012J2\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010\u0012J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u000bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u000bH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0010\u0010!\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/example/shellshot/media/LatestScreenshotResolver;", "", "appPrefs", "Lcom/example/shellshot/data/AppPrefs;", "mediaStoreRepository", "Lcom/example/shellshot/media/MediaStoreRepository;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Lcom/example/shellshot/data/AppPrefs;Lcom/example/shellshot/media/MediaStoreRepository;Lcom/example/shellshot/utils/ShellLogger;)V", "resolveQueuedTask", "Lcom/example/shellshot/media/ScreenshotCandidate;", "absolutePath", "", "displayName", "relativePath", "changedUri", "Landroid/net/Uri;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveQueuedTaskDetailed", "Lcom/example/shellshot/media/ResolveQueuedTaskResult;", "evaluateConfiguredPathCandidate", "Lcom/example/shellshot/media/LatestScreenshotResolver$ConfiguredPathEvaluation;", "screenshotRelativePath", "shouldEnableMediaStoreFallback", "", "reason", "hasClearlyAbnormalMetadata", "candidate", "isLikelyTemporaryFile", "logConfiguredPathEvaluation", "", "evaluation", "summarizeCandidate", "logAndReturnSelection", "result", "ConfiguredPathEvaluation", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class LatestScreenshotResolver {

    @Deprecated
    public static final String REASON_CONFIGURED_FILE_MISSING = "configured_file_missing";

    @Deprecated
    public static final String REASON_CONFIGURED_FILE_UNREADABLE = "configured_file_unreadable";

    @Deprecated
    public static final String REASON_CONFIGURED_METADATA_ABNORMAL = "configured_metadata_abnormal";

    @Deprecated
    public static final String REASON_CONFIGURED_TEMPORARY_FILE = "configured_temporary_file";

    @Deprecated
    public static final String REASON_FIXED_PRIMARY_ACCEPTED = "fixed_primary_accepted";

    @Deprecated
    public static final String REASON_GENERATED_BY_APP = "generated_by_app";

    @Deprecated
    public static final String REASON_INVALID_INPUT = "invalid_input";

    @Deprecated
    public static final String REASON_MEDIASTORE_NO_MATCH = "mediastore_no_match";

    @Deprecated
    public static final String REASON_NOT_CONFIGURED_SCREENSHOT_PATH = "not_configured_screenshot_path";

    @Deprecated
    public static final String REASON_OUTPUT_DIRECTORY = "output_directory";

    @Deprecated
    public static final String REASON_UNSUPPORTED_EXTENSION = "unsupported_extension";

    @Deprecated
    public static final int RECENT_LIMIT = 4;

    @Deprecated
    public static final String TAG = "LatestShotResolver";
    private final AppPrefs appPrefs;
    private final ShellLogger logger;
    private final MediaStoreRepository mediaStoreRepository;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public LatestScreenshotResolver(AppPrefs appPrefs, MediaStoreRepository mediaStoreRepository, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(appPrefs, "appPrefs");
        Intrinsics.checkNotNullParameter(mediaStoreRepository, "mediaStoreRepository");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.appPrefs = appPrefs;
        this.mediaStoreRepository = mediaStoreRepository;
        this.logger = logger;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveQueuedTask(java.lang.String r9, java.lang.String r10, java.lang.String r11, android.net.Uri r12, kotlin.coroutines.Continuation<? super com.example.shellshot.media.ScreenshotCandidate> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTask$1
            if (r0 == 0) goto L14
            r0 = r13
            com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTask$1 r0 = (com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTask$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTask$1 r0 = new com.example.shellshot.media.LatestScreenshotResolver$resolveQueuedTask$1
            r0.<init>(r8, r13)
        L19:
            r6 = r0
            java.lang.Object r0 = r6.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L49;
                case 1: goto L2f;
                default: goto L25;
            }
        L25:
            r2 = r9
            r3 = r10
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2f:
            java.lang.Object r1 = r6.L$3
            r12 = r1
            android.net.Uri r12 = (android.net.Uri) r12
            java.lang.Object r1 = r6.L$2
            r11 = r1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r1 = r6.L$1
            r10 = r1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r1 = r6.L$0
            r9 = r1
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r9
            r9 = r0
            goto L7b
        L49:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r6.L$0 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r6.L$1 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r6.L$2 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r6.L$3 = r1
            r1 = 1
            r6.label = r1
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            java.lang.Object r9 = r1.resolveQueuedTaskDetailed(r2, r3, r4, r5, r6)
            if (r9 != r7) goto L78
            return r7
        L78:
            r10 = r3
            r11 = r4
            r12 = r5
        L7b:
            com.example.shellshot.media.ResolveQueuedTaskResult r9 = (com.example.shellshot.media.ResolveQueuedTaskResult) r9
            com.example.shellshot.media.ScreenshotCandidate r9 = r9.getCandidate()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.LatestScreenshotResolver.resolveQueuedTask(java.lang.String, java.lang.String, java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object resolveQueuedTaskDetailed(String absolutePath, String displayName, String relativePath, Uri changedUri, Continuation<? super ResolveQueuedTaskResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new LatestScreenshotResolver$resolveQueuedTaskDetailed$2(this, absolutePath, displayName, relativePath, changedUri, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConfiguredPathEvaluation evaluateConfiguredPathCandidate(String absolutePath, String displayName, String relativePath, String screenshotRelativePath) {
        if (StringsKt.isBlank(absolutePath) || StringsKt.isBlank(displayName)) {
            return new ConfiguredPathEvaluation(null, null, REASON_INVALID_INPUT, 3, null);
        }
        String resolvedRelativePath = relativePath == null ? ScreenshotDirectories.INSTANCE.relativePathFromAbsolutePath(absolutePath) : relativePath;
        if (ScreenshotDirectories.INSTANCE.isGeneratedByApp(displayName)) {
            return new ConfiguredPathEvaluation(null, null, REASON_GENERATED_BY_APP, 3, null);
        }
        ScreenshotDirectories screenshotDirectories = ScreenshotDirectories.INSTANCE;
        File parentFile = new File(absolutePath).getParentFile();
        if (screenshotDirectories.isOutputLocation(absolutePath, resolvedRelativePath, parentFile != null ? parentFile.getName() : null)) {
            return new ConfiguredPathEvaluation(null, null, REASON_OUTPUT_DIRECTORY, 3, null);
        }
        if (!ScreenshotDirectories.INSTANCE.looksLikeImageFile(displayName)) {
            return new ConfiguredPathEvaluation(null, null, REASON_UNSUPPORTED_EXTENSION, 3, null);
        }
        if (!ScreenshotDirectories.INSTANCE.isScreenshotSource(absolutePath, resolvedRelativePath, screenshotRelativePath)) {
            return new ConfiguredPathEvaluation(null, null, REASON_NOT_CONFIGURED_SCREENSHOT_PATH, 3, null);
        }
        File file = new File(absolutePath);
        Long valueOf = Long.valueOf(file.lastModified());
        if (!(valueOf.longValue() > 0)) {
            valueOf = null;
        }
        long longValue = valueOf != null ? valueOf.longValue() : System.currentTimeMillis();
        Long valueOf2 = Long.valueOf(file.length());
        Long l = valueOf2.longValue() > 0 ? valueOf2 : null;
        ScreenshotCandidate candidate = new ScreenshotCandidate(absolutePath, displayName, resolvedRelativePath, longValue, l != null ? l.longValue() : 0L, ScreenshotRules.INSTANCE.inferMimeType(file), null, null, null, null, null, false, 1728, null);
        if (!file.exists()) {
            return new ConfiguredPathEvaluation(null, candidate, REASON_CONFIGURED_FILE_MISSING, 1, null);
        }
        if (!file.canRead()) {
            return new ConfiguredPathEvaluation(null, candidate, REASON_CONFIGURED_FILE_UNREADABLE, 1, null);
        }
        if (isLikelyTemporaryFile(candidate)) {
            return new ConfiguredPathEvaluation(null, candidate, REASON_CONFIGURED_TEMPORARY_FILE, 1, null);
        }
        if (hasClearlyAbnormalMetadata(candidate)) {
            return new ConfiguredPathEvaluation(null, candidate, REASON_CONFIGURED_METADATA_ABNORMAL, 1, null);
        }
        return new ConfiguredPathEvaluation(candidate, null, REASON_FIXED_PRIMARY_ACCEPTED, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldEnableMediaStoreFallback(String reason) {
        return Intrinsics.areEqual(reason, REASON_CONFIGURED_FILE_MISSING) || Intrinsics.areEqual(reason, REASON_CONFIGURED_FILE_UNREADABLE) || Intrinsics.areEqual(reason, REASON_CONFIGURED_TEMPORARY_FILE) || Intrinsics.areEqual(reason, REASON_CONFIGURED_METADATA_ABNORMAL);
    }

    private final boolean hasClearlyAbnormalMetadata(ScreenshotCandidate candidate) {
        String relativePath = candidate.getRelativePath();
        return (relativePath == null || StringsKt.isBlank(relativePath)) || candidate.getLastModifiedMillis() <= 0 || StringsKt.isBlank(candidate.getDisplayName());
    }

    private final boolean isLikelyTemporaryFile(ScreenshotCandidate candidate) {
        String name = candidate.getDisplayName().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(name, "toLowerCase(...)");
        String path = candidate.getAbsolutePath().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(path, "toLowerCase(...)");
        return StringsKt.startsWith$default(name, ".", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "pending", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) "pending", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) ".tmp", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) ".tmp", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "temp", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) "temp", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logConfiguredPathEvaluation(ConfiguredPathEvaluation evaluation) {
        ScreenshotCandidate candidate = evaluation.getAcceptedCandidate();
        if (candidate == null) {
            candidate = evaluation.getRejectedCandidate();
        }
        if (candidate == null) {
            this.logger.d(TAG, "配置路径候选不可用 reason=" + evaluation.getReason());
            return;
        }
        File file = new File(candidate.getAbsolutePath());
        ShellLogger shellLogger = this.logger;
        String absolutePath = candidate.getAbsolutePath();
        boolean exists = file.exists();
        boolean canRead = file.canRead();
        Long valueOf = Long.valueOf(file.length());
        valueOf.longValue();
        if (!file.exists()) {
            valueOf = null;
        }
        shellLogger.d(TAG, "配置路径候选评估 path=" + absolutePath + " exists=" + exists + " readable=" + canRead + " size=" + (valueOf != null ? valueOf.longValue() : 0L) + " relativePath=" + candidate.getRelativePath() + " reason=" + evaluation.getReason());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String summarizeCandidate(ScreenshotCandidate candidate) {
        return "path=" + candidate.getAbsolutePath() + ",uri=" + candidate.getUri() + ",size=" + candidate.getSizeBytes() + ",capturedAt=" + candidate.getCapturedAtMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ResolveQueuedTaskResult logAndReturnSelection(ResolveQueuedTaskResult result) {
        ShellLogger shellLogger = this.logger;
        ResolutionSource source = result.getSource();
        String reason = result.getReason();
        ScreenshotCandidate candidate = result.getCandidate();
        String absolutePath = candidate != null ? candidate.getAbsolutePath() : null;
        ScreenshotCandidate candidate2 = result.getCandidate();
        shellLogger.d(TAG, "最终选择 source=" + source + " reason=" + reason + " path=" + absolutePath + " uri=" + (candidate2 != null ? candidate2.getUri() : null));
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LatestScreenshotResolver.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/example/shellshot/media/LatestScreenshotResolver$ConfiguredPathEvaluation;", "", "acceptedCandidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "rejectedCandidate", "reason", "", "<init>", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lcom/example/shellshot/media/ScreenshotCandidate;Ljava/lang/String;)V", "getAcceptedCandidate", "()Lcom/example/shellshot/media/ScreenshotCandidate;", "getRejectedCandidate", "getReason", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class ConfiguredPathEvaluation {
        private final ScreenshotCandidate acceptedCandidate;
        private final String reason;
        private final ScreenshotCandidate rejectedCandidate;

        public static /* synthetic */ ConfiguredPathEvaluation copy$default(ConfiguredPathEvaluation configuredPathEvaluation, ScreenshotCandidate screenshotCandidate, ScreenshotCandidate screenshotCandidate2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                screenshotCandidate = configuredPathEvaluation.acceptedCandidate;
            }
            if ((i & 2) != 0) {
                screenshotCandidate2 = configuredPathEvaluation.rejectedCandidate;
            }
            if ((i & 4) != 0) {
                str = configuredPathEvaluation.reason;
            }
            return configuredPathEvaluation.copy(screenshotCandidate, screenshotCandidate2, str);
        }

        /* renamed from: component1, reason: from getter */
        public final ScreenshotCandidate getAcceptedCandidate() {
            return this.acceptedCandidate;
        }

        /* renamed from: component2, reason: from getter */
        public final ScreenshotCandidate getRejectedCandidate() {
            return this.rejectedCandidate;
        }

        /* renamed from: component3, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final ConfiguredPathEvaluation copy(ScreenshotCandidate acceptedCandidate, ScreenshotCandidate rejectedCandidate, String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new ConfiguredPathEvaluation(acceptedCandidate, rejectedCandidate, reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConfiguredPathEvaluation)) {
                return false;
            }
            ConfiguredPathEvaluation configuredPathEvaluation = (ConfiguredPathEvaluation) other;
            return Intrinsics.areEqual(this.acceptedCandidate, configuredPathEvaluation.acceptedCandidate) && Intrinsics.areEqual(this.rejectedCandidate, configuredPathEvaluation.rejectedCandidate) && Intrinsics.areEqual(this.reason, configuredPathEvaluation.reason);
        }

        public int hashCode() {
            return ((((this.acceptedCandidate == null ? 0 : this.acceptedCandidate.hashCode()) * 31) + (this.rejectedCandidate != null ? this.rejectedCandidate.hashCode() : 0)) * 31) + this.reason.hashCode();
        }

        public String toString() {
            return "ConfiguredPathEvaluation(acceptedCandidate=" + this.acceptedCandidate + ", rejectedCandidate=" + this.rejectedCandidate + ", reason=" + this.reason + ")";
        }

        public ConfiguredPathEvaluation(ScreenshotCandidate acceptedCandidate, ScreenshotCandidate rejectedCandidate, String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.acceptedCandidate = acceptedCandidate;
            this.rejectedCandidate = rejectedCandidate;
            this.reason = reason;
        }

        public /* synthetic */ ConfiguredPathEvaluation(ScreenshotCandidate screenshotCandidate, ScreenshotCandidate screenshotCandidate2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : screenshotCandidate, (i & 2) != 0 ? null : screenshotCandidate2, str);
        }

        public final ScreenshotCandidate getAcceptedCandidate() {
            return this.acceptedCandidate;
        }

        public final ScreenshotCandidate getRejectedCandidate() {
            return this.rejectedCandidate;
        }

        public final String getReason() {
            return this.reason;
        }
    }

    /* compiled from: LatestScreenshotResolver.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/media/LatestScreenshotResolver$Companion;", "", "<init>", "()V", "TAG", "", "RECENT_LIMIT", "", "REASON_FIXED_PRIMARY_ACCEPTED", "REASON_INVALID_INPUT", "REASON_GENERATED_BY_APP", "REASON_OUTPUT_DIRECTORY", "REASON_UNSUPPORTED_EXTENSION", "REASON_NOT_CONFIGURED_SCREENSHOT_PATH", "REASON_CONFIGURED_FILE_MISSING", "REASON_CONFIGURED_FILE_UNREADABLE", "REASON_CONFIGURED_TEMPORARY_FILE", "REASON_CONFIGURED_METADATA_ABNORMAL", "REASON_MEDIASTORE_NO_MATCH", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
