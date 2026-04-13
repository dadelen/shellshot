package com.example.shellshot.media;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ScreenshotScorer.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J*\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014J\"\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0014J\u0016\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bJ \u0010\u001c\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/example/shellshot/media/ScreenshotRules;", "", "<init>", "()V", "mediaStoreDisplayNamePatterns", "", "", "getMediaStoreDisplayNamePatterns", "()Ljava/util/List;", "screenshotPathKeywords", "screenshotFileKeywords", "isPotentialScreenshotFile", "", "absolutePath", "displayName", "screenshotRelativePath", "isEligibleScreenshotCandidate", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "nowMillis", "", "recentWindowMillis", "isRecentCandidate", "windowMillis", "matchReason", "inferMimeType", "file", "Ljava/io/File;", "containsKeyword", "value", "keywords", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ScreenshotRules {
    public static final ScreenshotRules INSTANCE = new ScreenshotRules();
    private static final List<String> mediaStoreDisplayNamePatterns = CollectionsKt.listOf((Object[]) new String[]{"Screenshot%", "screenshot%", "ScreenShot%", "ScreenCapture%", "screen_capture%", "screencap%", "%截屏%", "%截图%", "%截圖%"});
    private static final List<String> screenshotPathKeywords = CollectionsKt.listOf((Object[]) new String[]{"pictures/screenshots/", "dcim/screenshots/", "pictures/screencapture/", "dcim/screencapture/", "pictures/screenshots/", "dcim/screenshots/", "截屏", "截图", "截圖"});
    private static final List<String> screenshotFileKeywords = CollectionsKt.listOf((Object[]) new String[]{"screenshot", "screen_shot", "screen-shot", "screen capture", "screencapture", "screencap", "screen", "截屏", "截图", "截圖"});
    public static final int $stable = 8;

    private ScreenshotRules() {
    }

    public final List<String> getMediaStoreDisplayNamePatterns() {
        return mediaStoreDisplayNamePatterns;
    }

    public final boolean isPotentialScreenshotFile(String absolutePath, String displayName, String screenshotRelativePath) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(screenshotRelativePath, "screenshotRelativePath");
        String relativePath = ScreenshotDirectories.INSTANCE.relativePathFromAbsolutePath(absolutePath);
        if (!ScreenshotDirectories.INSTANCE.isGeneratedByApp(displayName)) {
            ScreenshotDirectories screenshotDirectories = ScreenshotDirectories.INSTANCE;
            File parentFile = new File(absolutePath).getParentFile();
            return !screenshotDirectories.isOutputLocation(absolutePath, relativePath, parentFile != null ? parentFile.getName() : null) && ScreenshotDirectories.INSTANCE.looksLikeImageFile(displayName) && ScreenshotDirectories.INSTANCE.isScreenshotSource(absolutePath, relativePath, screenshotRelativePath);
        }
        return false;
    }

    public static /* synthetic */ boolean isEligibleScreenshotCandidate$default(ScreenshotRules screenshotRules, ScreenshotCandidate screenshotCandidate, String str, long j, long j2, int i, Object obj) {
        long j3;
        long j4;
        if ((i & 4) == 0) {
            j3 = j;
        } else {
            j3 = System.currentTimeMillis();
        }
        if ((i & 8) == 0) {
            j4 = j2;
        } else {
            j4 = 30000;
        }
        return screenshotRules.isEligibleScreenshotCandidate(screenshotCandidate, str, j3, j4);
    }

    public final boolean isEligibleScreenshotCandidate(ScreenshotCandidate candidate, String screenshotRelativePath, long nowMillis, long recentWindowMillis) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(screenshotRelativePath, "screenshotRelativePath");
        return isPotentialScreenshotFile(candidate.getAbsolutePath(), candidate.getDisplayName(), screenshotRelativePath) && isRecentCandidate(candidate, nowMillis, recentWindowMillis) && candidate.getSizeBytes() > 0 && !candidate.isPending();
    }

    public static /* synthetic */ boolean isRecentCandidate$default(ScreenshotRules screenshotRules, ScreenshotCandidate screenshotCandidate, long j, long j2, int i, Object obj) {
        long j3;
        long j4;
        if ((i & 2) == 0) {
            j3 = j;
        } else {
            j3 = System.currentTimeMillis();
        }
        if ((i & 4) == 0) {
            j4 = j2;
        } else {
            j4 = 30000;
        }
        return screenshotRules.isRecentCandidate(screenshotCandidate, j3, j4);
    }

    public final boolean isRecentCandidate(ScreenshotCandidate candidate, long nowMillis, long windowMillis) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        return Math.abs(nowMillis - candidate.getCapturedAtMillis()) <= windowMillis;
    }

    public final String matchReason(ScreenshotCandidate candidate, String screenshotRelativePath) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(screenshotRelativePath, "screenshotRelativePath");
        List createListBuilder = CollectionsKt.createListBuilder();
        if (ScreenshotDirectories.INSTANCE.isScreenshotSource(candidate.getAbsolutePath(), candidate.getRelativePath(), screenshotRelativePath)) {
            createListBuilder.add("configured_screenshot_directory");
        }
        if (INSTANCE.containsKeyword(candidate.getRelativePath(), screenshotPathKeywords)) {
            createListBuilder.add("screenshot_keyword_directory");
        }
        if (INSTANCE.containsKeyword(candidate.getDisplayName(), screenshotFileKeywords)) {
            createListBuilder.add("screenshot_file_name");
        }
        if (ScreenshotDirectories.INSTANCE.isOutputLocation(candidate.getAbsolutePath(), candidate.getRelativePath(), candidate.getBucketName())) {
            createListBuilder.add(LatestScreenshotResolver.REASON_OUTPUT_DIRECTORY);
        }
        if (ScreenshotDirectories.INSTANCE.isGeneratedByApp(candidate.getDisplayName())) {
            createListBuilder.add(LatestScreenshotResolver.REASON_GENERATED_BY_APP);
        }
        List reasons = CollectionsKt.build(createListBuilder);
        String joinToString$default = CollectionsKt.joinToString$default(reasons, " + ", null, null, 0, null, null, 62, null);
        if (StringsKt.isBlank(joinToString$default)) {
            joinToString$default = "no_match";
        }
        return joinToString$default;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0046 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String inferMimeType(java.io.File r3) {
        /*
            r2 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = kotlin.io.FilesKt.getExtension(r3)
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case 105441: goto L3d;
                case 111145: goto L31;
                case 3268712: goto L28;
                case 3645340: goto L1c;
                default: goto L1b;
            }
        L1b:
            goto L49
        L1c:
            java.lang.String r1 = "webp"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L25
            goto L1b
        L25:
            java.lang.String r0 = "image/webp"
            goto L4b
        L28:
            java.lang.String r1 = "jpeg"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L1b
        L31:
            java.lang.String r1 = "png"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3a
            goto L1b
        L3a:
            java.lang.String r0 = "image/png"
            goto L4b
        L3d:
            java.lang.String r1 = "jpg"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L1b
        L46:
            java.lang.String r0 = "image/jpeg"
            goto L4b
        L49:
            java.lang.String r0 = "image/*"
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotRules.inferMimeType(java.io.File):java.lang.String");
    }

    private final boolean containsKeyword(String value, List<String> keywords) {
        String str = value;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        List<String> list = keywords;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains((CharSequence) value, (CharSequence) it.next(), true)) {
                return true;
            }
        }
        return false;
    }
}
