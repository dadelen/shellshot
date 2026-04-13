package com.example.shellshot.media;

import android.os.Environment;
import com.example.shellshot.data.OutputNamingStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: ScreenshotDirectories.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\u0006\u0010\u0010\u001a\u00020\u0005J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\b\u0010\u0013\u001a\u00020\u000fH\u0007J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bH\u0007J\u0006\u0010\u0014\u001a\u00020\u000fJ\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0017\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0005J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005J\u001c\u0010!\u001a\u00020\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005J\u0010\u0010#\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005J\u0010\u0010$\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005J\u0010\u0010%\u001a\u00020\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005J\u0010\u0010&\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005J&\u0010'\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005J\u0018\u0010(\u001a\u00020\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u0018\u0010)\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\"\u0010*\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.J?\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\u0006\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001042\b\b\u0002\u00106\u001a\u000204¢\u0006\u0002\u00107J\u0012\u00108\u001a\u00020\u001f2\b\u00109\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010:\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/example/shellshot/media/ScreenshotDirectories;", "", "<init>", "()V", "OUTPUT_PREFIX", "", "LEGACY_OUTPUT_PREFIX", "OUTPUT_SUFFIX", "OUTPUT_RELATIVE_PATH", "DEFAULT_SCREENSHOT_RELATIVE_PATH", "generatedPrefixes", "", "presetRelativePaths", "screenshotPathKeywords", "screenshotDirectory", "Ljava/io/File;", "configuredRelativePath", "candidateDirectories", "presetScreenshotRelativePaths", "primaryScreenshotDirectory", "outputDirectory", "normalizeRelativePath", "relativePath", "resolveScreenshotRelativePath", "relativePathFromAbsolutePath", "absolutePath", "toAbsoluteDirectory", "toAbsolutePath", "displayName", "normalizeAbsolutePath", "looksLikeImageFile", "", "fileName", "looksLikeScreenshotRelativePath", "bucketName", "looksLikeScreenshotFileName", "isGeneratedByApp", "isOutputRelativePath", "isOutputAbsolutePath", "isOutputLocation", "isScreenshotRelativePath", "isScreenshotAbsolutePath", "isScreenshotSource", "buildOutputFileName", "sourceDisplayName", "namingStrategy", "Lcom/example/shellshot/data/OutputNamingStrategy;", "recentScreenshotFiles", "screenshotRelativePath", "limit", "", "recentWindowMillis", "", "minLastModifiedMillis", "nowMillis", "(Ljava/lang/String;IJLjava/lang/Long;J)Ljava/util/List;", "containsScreenshotKeyword", "value", "externalRoot", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ScreenshotDirectories {
    public static final String OUTPUT_RELATIVE_PATH = "Pictures/AutoShell/";
    public static final String OUTPUT_SUFFIX = "_shell";
    public static final ScreenshotDirectories INSTANCE = new ScreenshotDirectories();
    public static final String OUTPUT_PREFIX = "shellshot_";
    public static final String LEGACY_OUTPUT_PREFIX = "autoshell_";
    private static final List<String> generatedPrefixes = CollectionsKt.listOf((Object[]) new String[]{OUTPUT_PREFIX, LEGACY_OUTPUT_PREFIX});
    public static final String DEFAULT_SCREENSHOT_RELATIVE_PATH = "Pictures/Screenshots/";
    private static final List<String> presetRelativePaths = CollectionsKt.listOf((Object[]) new String[]{DEFAULT_SCREENSHOT_RELATIVE_PATH, "DCIM/Screenshots/", "Pictures/ScreenShots/", "DCIM/ScreenShots/", "Pictures/Screenshot/", "DCIM/Screenshot/"});
    private static final List<String> screenshotPathKeywords = CollectionsKt.listOf((Object[]) new String[]{"screenshot", "screen_shot", "screen-shot", "screencapture", "screen capture", "screencap", "截屏", "截图", "截圖"});
    public static final int $stable = 8;

    /* compiled from: ScreenshotDirectories.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OutputNamingStrategy.values().length];
            try {
                iArr[OutputNamingStrategy.Prefix.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[OutputNamingStrategy.Suffix.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ScreenshotDirectories() {
    }

    public final File screenshotDirectory(String configuredRelativePath) {
        Intrinsics.checkNotNullParameter(configuredRelativePath, "configuredRelativePath");
        return toAbsoluteDirectory(resolveScreenshotRelativePath(configuredRelativePath));
    }

    public final List<File> candidateDirectories(String configuredRelativePath) {
        Intrinsics.checkNotNullParameter(configuredRelativePath, "configuredRelativePath");
        return CollectionsKt.listOf(screenshotDirectory(configuredRelativePath));
    }

    public final List<String> presetScreenshotRelativePaths() {
        Iterable iterable = presetRelativePaths;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(resolveScreenshotRelativePath((String) it.next()));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : (List) arrayList) {
            String lowerCase = ((String) obj).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (hashSet.add(lowerCase)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    @Deprecated(message = "Use screenshotDirectory(configuredRelativePath)")
    public final File primaryScreenshotDirectory() {
        return screenshotDirectory(DEFAULT_SCREENSHOT_RELATIVE_PATH);
    }

    @Deprecated(message = "Use candidateDirectories(configuredRelativePath)")
    public final List<File> candidateDirectories() {
        return candidateDirectories(DEFAULT_SCREENSHOT_RELATIVE_PATH);
    }

    public final File outputDirectory() {
        return toAbsoluteDirectory(OUTPUT_RELATIVE_PATH);
    }

    public final String normalizeRelativePath(String relativePath) {
        String str = relativePath;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String obj = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.replace$default(relativePath, AbstractJsonLexerKt.STRING_ESC, '/', false, 4, (Object) null), (CharSequence) "/")).toString();
        if (StringsKt.isBlank(obj)) {
            obj = null;
        }
        if (obj != null) {
            return StringsKt.endsWith$default(obj, "/", false, 2, (Object) null) ? obj : obj + "/";
        }
        return null;
    }

    public final String resolveScreenshotRelativePath(String relativePath) {
        String normalizeRelativePath = normalizeRelativePath(relativePath);
        return normalizeRelativePath == null ? DEFAULT_SCREENSHOT_RELATIVE_PATH : normalizeRelativePath;
    }

    public final String relativePathFromAbsolutePath(String absolutePath) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        String absolutePath2 = externalRoot().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
        String rootPath = StringsKt.removeSuffix(StringsKt.replace$default(absolutePath2, AbstractJsonLexerKt.STRING_ESC, '/', false, 4, (Object) null), (CharSequence) "/");
        String normalizedPath = StringsKt.replace$default(absolutePath, AbstractJsonLexerKt.STRING_ESC, '/', false, 4, (Object) null);
        if (!StringsKt.startsWith(normalizedPath, rootPath, true)) {
            return null;
        }
        return normalizeRelativePath(StringsKt.substringBeforeLast(StringsKt.removePrefix(StringsKt.removePrefix(normalizedPath, (CharSequence) rootPath), (CharSequence) "/"), "/", ""));
    }

    public final File toAbsoluteDirectory(String relativePath) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        return new File(externalRoot(), StringsKt.removeSuffix(relativePath, (CharSequence) "/"));
    }

    public final String toAbsolutePath(String relativePath, String displayName) {
        String normalizedPath = normalizeRelativePath(relativePath);
        if (normalizedPath != null && displayName != null) {
            String safeDisplayName = !StringsKt.isBlank(displayName) ? displayName : null;
            if (safeDisplayName != null) {
                return new File(externalRoot(), normalizedPath + safeDisplayName).getAbsolutePath();
            }
        }
        return null;
    }

    public final String normalizeAbsolutePath(String absolutePath) {
        String absolutePath2;
        String obj;
        if (absolutePath == null || (absolutePath2 = StringsKt.replace$default(absolutePath, AbstractJsonLexerKt.STRING_ESC, '/', false, 4, (Object) null)) == null || (obj = StringsKt.trim((CharSequence) absolutePath2).toString()) == null) {
            return null;
        }
        if (StringsKt.isBlank(obj)) {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        String lowerCase = obj.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public final boolean looksLikeImageFile(String fileName) {
        String str = fileName;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String lowerCaseName = fileName.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCaseName, "toLowerCase(...)");
        return StringsKt.endsWith$default(lowerCaseName, ".png", false, 2, (Object) null) || StringsKt.endsWith$default(lowerCaseName, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(lowerCaseName, ".jpeg", false, 2, (Object) null) || StringsKt.endsWith$default(lowerCaseName, ".webp", false, 2, (Object) null);
    }

    public static /* synthetic */ boolean looksLikeScreenshotRelativePath$default(ScreenshotDirectories screenshotDirectories, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return screenshotDirectories.looksLikeScreenshotRelativePath(str, str2);
    }

    public final boolean looksLikeScreenshotRelativePath(String relativePath, String bucketName) {
        String normalizedPath = normalizeRelativePath(relativePath);
        return containsScreenshotKeyword(normalizedPath) || containsScreenshotKeyword(bucketName);
    }

    public final boolean looksLikeScreenshotFileName(String fileName) {
        return containsScreenshotKeyword(fileName);
    }

    public final boolean isGeneratedByApp(String fileName) {
        Iterable iterable;
        String str = fileName;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        Iterable iterable2 = generatedPrefixes;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            Iterator it = iterable2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (StringsKt.startsWith(fileName, (String) it.next(), true)) {
                        iterable = 1;
                        break;
                    }
                } else {
                    iterable = null;
                    break;
                }
            }
        } else {
            iterable = null;
        }
        return iterable != null || StringsKt.endsWith(StringsKt.substringBeforeLast$default(fileName, '.', (String) null, 2, (Object) null), OUTPUT_SUFFIX, true);
    }

    public final boolean isOutputRelativePath(String relativePath) {
        String normalizedPath = normalizeRelativePath(relativePath);
        if (normalizedPath == null) {
            return false;
        }
        return StringsKt.startsWith(normalizedPath, OUTPUT_RELATIVE_PATH, true);
    }

    public final boolean isOutputAbsolutePath(String absolutePath) {
        String outputRoot;
        String normalizedPath = normalizeAbsolutePath(absolutePath);
        if (normalizedPath == null || (outputRoot = normalizeAbsolutePath(outputDirectory().getAbsolutePath())) == null) {
            return false;
        }
        return StringsKt.startsWith$default(normalizedPath, outputRoot, false, 2, (Object) null);
    }

    public static /* synthetic */ boolean isOutputLocation$default(ScreenshotDirectories screenshotDirectories, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return screenshotDirectories.isOutputLocation(str, str2, str3);
    }

    public final boolean isOutputLocation(String absolutePath, String relativePath, String bucketName) {
        if (isOutputRelativePath(relativePath) || isOutputAbsolutePath(absolutePath)) {
            return true;
        }
        return StringsKt.equals(bucketName, outputDirectory().getName(), true);
    }

    public final boolean isScreenshotRelativePath(String relativePath, String configuredRelativePath) {
        Intrinsics.checkNotNullParameter(configuredRelativePath, "configuredRelativePath");
        String normalizedPath = normalizeRelativePath(relativePath);
        if (normalizedPath == null) {
            return false;
        }
        return StringsKt.equals(normalizedPath, resolveScreenshotRelativePath(configuredRelativePath), true);
    }

    public final boolean isScreenshotAbsolutePath(String absolutePath, String configuredRelativePath) {
        String screenshotRoot;
        Intrinsics.checkNotNullParameter(configuredRelativePath, "configuredRelativePath");
        String normalizedPath = normalizeAbsolutePath(absolutePath);
        if (normalizedPath == null || (screenshotRoot = normalizeAbsolutePath(screenshotDirectory(configuredRelativePath).getAbsolutePath())) == null) {
            return false;
        }
        return StringsKt.startsWith$default(normalizedPath, screenshotRoot, false, 2, (Object) null);
    }

    public final boolean isScreenshotSource(String absolutePath, String relativePath, String configuredRelativePath) {
        Intrinsics.checkNotNullParameter(configuredRelativePath, "configuredRelativePath");
        return isScreenshotRelativePath(relativePath, configuredRelativePath) || isScreenshotAbsolutePath(absolutePath, configuredRelativePath);
    }

    public final String buildOutputFileName(String sourceDisplayName, OutputNamingStrategy namingStrategy) {
        Intrinsics.checkNotNullParameter(sourceDisplayName, "sourceDisplayName");
        Intrinsics.checkNotNullParameter(namingStrategy, "namingStrategy");
        String substringBeforeLast$default = StringsKt.substringBeforeLast$default(sourceDisplayName, '.', (String) null, 2, (Object) null);
        if (StringsKt.isBlank(substringBeforeLast$default)) {
            substringBeforeLast$default = "screenshot";
        }
        String baseName = substringBeforeLast$default;
        switch (WhenMappings.$EnumSwitchMapping$0[namingStrategy.ordinal()]) {
            case 1:
                return OUTPUT_PREFIX + baseName + ".png";
            case 2:
                return baseName + "_shell.png";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ List recentScreenshotFiles$default(ScreenshotDirectories screenshotDirectories, String str, int i, long j, Long l, long j2, int i2, Object obj) {
        Long l2;
        long j3;
        if ((i2 & 8) == 0) {
            l2 = l;
        } else {
            l2 = null;
        }
        if ((i2 & 16) == 0) {
            j3 = j2;
        } else {
            j3 = System.currentTimeMillis();
        }
        return screenshotDirectories.recentScreenshotFiles(str, i, j, l2, j3);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<java.io.File> recentScreenshotFiles(java.lang.String r9, int r10, final long r11, final java.lang.Long r13, final long r14) {
        /*
            r8 = this;
            java.lang.String r0 = "screenshotRelativePath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.io.File r0 = r8.screenshotDirectory(r9)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L74
            boolean r1 = r0.isDirectory()
            if (r1 != 0) goto L1a
            r5 = r11
            r7 = r13
            r3 = r14
            goto L77
        L1a:
            java.io.File[] r1 = r0.listFiles()
            if (r1 == 0) goto L69
        L27:
            kotlin.sequences.Sequence r1 = kotlin.collections.ArraysKt.asSequence(r1)
            if (r1 == 0) goto L65
        L2e:
            com.example.shellshot.media.ScreenshotDirectories$$ExternalSyntheticLambda0 r2 = new com.example.shellshot.media.ScreenshotDirectories$$ExternalSyntheticLambda0
            r5 = r11
            r7 = r13
            r3 = r14
            r2.<init>()
            kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.filter(r1, r2)
            if (r11 == 0) goto L6c
        L3e:
            r12 = 0
            com.example.shellshot.media.ScreenshotDirectories$recentScreenshotFiles$$inlined$sortedByDescending$1 r13 = new com.example.shellshot.media.ScreenshotDirectories$recentScreenshotFiles$$inlined$sortedByDescending$1
            r13.<init>()
            java.util.Comparator r13 = (java.util.Comparator) r13
            kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.sortedWith(r11, r13)
            if (r11 == 0) goto L6c
            com.example.shellshot.media.ScreenshotDirectories$$ExternalSyntheticLambda1 r12 = new com.example.shellshot.media.ScreenshotDirectories$$ExternalSyntheticLambda1
            r12.<init>()
            kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.distinctBy(r11, r12)
            if (r11 == 0) goto L6c
        L59:
            kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.take(r11, r10)
            if (r11 == 0) goto L6c
        L60:
            java.util.List r11 = kotlin.sequences.SequencesKt.toList(r11)
            goto L6d
        L65:
            r5 = r11
            r7 = r13
            r3 = r14
            goto L6c
        L69:
            r5 = r11
            r7 = r13
            r3 = r14
        L6c:
            r11 = 0
        L6d:
            if (r11 != 0) goto L73
            java.util.List r11 = kotlin.collections.CollectionsKt.emptyList()
        L73:
            return r11
        L74:
            r5 = r11
            r7 = r13
            r3 = r14
        L77:
            java.util.List r11 = kotlin.collections.CollectionsKt.emptyList()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotDirectories.recentScreenshotFiles(java.lang.String, int, long, java.lang.Long, long):java.util.List");
    }

    static final boolean recentScreenshotFiles$lambda$7(long $nowMillis, long $recentWindowMillis, Long $minLastModifiedMillis, File file) {
        long lastModified = file.lastModified();
        if (!file.isFile() || !INSTANCE.looksLikeImageFile(file.getName()) || INSTANCE.isGeneratedByApp(file.getName())) {
            return false;
        }
        long j = $nowMillis - lastModified;
        if (0 <= j && j <= $recentWindowMillis) {
            return $minLastModifiedMillis == null || lastModified >= $minLastModifiedMillis.longValue();
        }
        return false;
    }

    private final boolean containsScreenshotKeyword(String value) {
        String str = value;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        Iterable iterable = screenshotPathKeywords;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains((CharSequence) value, (CharSequence) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    private final File externalRoot() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkNotNullExpressionValue(externalStorageDirectory, "getExternalStorageDirectory(...)");
        return externalStorageDirectory;
    }
}
