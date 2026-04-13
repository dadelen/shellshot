package com.example.shellshot.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import androidx.core.view.ViewCompat;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.utils.ShellLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u0000 Y2\u00020\u0001:\u0002XYB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0086@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0086@¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010\u0015J\"\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010\u001bJ\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010\u001bJ\"\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\"2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0015J\"\u0010#\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0086@¢\u0006\u0002\u0010\u001bJ\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020$H\u0086@¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010,J\u0018\u0010-\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010,J\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0082@¢\u0006\u0002\u0010\u0010J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u001c\u00101\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0002J\u001c\u00102\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u00103\u001a\u00020\u00172\u0006\u0010'\u001a\u00020$H\u0002J\u001c\u00104\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0014H\u0002J$\u00107\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00108\u001a\u0002052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020\u0014H\u0002J\u0010\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020*H\u0002J\u0018\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020AH\u0002J \u0010B\u001a\u00020*2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020AH\u0002J.\u0010G\u001a\u0010\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020D\u0018\u00010H2\u0006\u0010I\u001a\u00020J2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020DH\u0002J:\u0010N\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020D0H0\r2\u0006\u0010O\u001a\u00020D2\u0006\u0010P\u001a\u00020D2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0002J\b\u0010Q\u001a\u00020RH\u0002J\u0012\u0010S\u001a\u00020\u00142\b\u0010T\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010U\u001a\u000205H\u0002J\b\u0010V\u001a\u000205H\u0002J\b\u0010W\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/example/shellshot/template/TemplateRepository;", "", "context", "Landroid/content/Context;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Landroid/content/Context;Lcom/example/shellshot/utils/ShellLogger;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "json", "Lkotlinx/serialization/json/Json;", "cachedTemplates", "", "Lcom/example/shellshot/template/ShellTemplate;", "getTemplates", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTemplates", "getTemplateById", "templateId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importTemplateImage", "Lcom/example/shellshot/template/TemplateImportResult;", "imageUri", "Landroid/net/Uri;", "templateNameOverride", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareTemplateImportDraft", "Lcom/example/shellshot/template/TemplateImportDraft;", "importPreparedTemplate", "sourceImagePath", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUserTemplate", "Lcom/example/shellshot/template/TemplateDeleteResult;", "beginImageCalibration", "Lcom/example/shellshot/template/TemplateCalibrationDraft;", "saveCalibrationDraft", "Lcom/example/shellshot/template/TemplateCalibrationResult;", "draft", "(Lcom/example/shellshot/template/TemplateCalibrationDraft;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadFrameBitmap", "Landroid/graphics/Bitmap;", "template", "(Lcom/example/shellshot/template/ShellTemplate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadScreenMaskBitmap", "loadTemplatesInternal", "loadBuiltInTemplates", "loadUserTemplates", "createDraftFromImage", "createDraftFromStagedPath", "persistDraft", "stageSourceImage", "Ljava/io/File;", "fileNameHint", "copyImageToDraft", "draftDirectory", "decodeBitmapPath", "path", "detectScreenOpening", "Lcom/example/shellshot/template/TemplateRepository$DetectionResult;", "frameBitmap", "buildAdjustedMask", "baseMask", "finalRect", "Lcom/example/shellshot/template/ScreenRect;", "createRectMask", "width", "", "height", "rect", "findTransparentSeed", "Lkotlin/Pair;", "pixels", "", "isTransparentPixel", "", "color", "fourNeighbors", "x", "y", "purgeLegacyImportedTemplates", "", "sanitizeFileName", "raw", "draftsRoot", "userTemplatesRoot", "buildUserTemplateId", "DetectionResult", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TemplateRepository {

    @Deprecated
    public static final int DEFAULT_SHRINK_PIXELS = 1;

    @Deprecated
    public static final String LEGACY_IMPORTED_TEMPLATE_DIR = "custom_templates";

    @Deprecated
    public static final int PREVIEW_SCREENSHOT_HEIGHT = 3168;

    @Deprecated
    public static final int PREVIEW_SCREENSHOT_WIDTH = 1440;

    @Deprecated
    public static final String TAG = "TemplateRepository";

    @Deprecated
    public static final String USER_TEMPLATE_CONFIG_NAME = "template.json";

    @Deprecated
    public static final String USER_TEMPLATE_ROOT = "user_templates";
    private List<ShellTemplate> cachedTemplates;
    private final Context context;
    private final Json json;
    private final ShellLogger logger;
    private final Mutex mutex;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public TemplateRepository(Context context, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.logger = logger;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.json = JsonKt.Json$default(null, new Function1() { // from class: com.example.shellshot.template.TemplateRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TemplateRepository.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
        purgeLegacyImportedTemplates();
    }

    static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0066 A[Catch: all -> 0x003a, TryCatch #0 {all -> 0x003a, blocks: (B:13:0x0035, B:15:0x0077, B:22:0x0062, B:24:0x0066), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTemplates(kotlin.coroutines.Continuation<? super java.util.List<com.example.shellshot.template.ShellTemplate>> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.example.shellshot.template.TemplateRepository$getTemplates$1
            if (r0 == 0) goto L14
            r0 = r11
            com.example.shellshot.template.TemplateRepository$getTemplates$1 r0 = (com.example.shellshot.template.TemplateRepository$getTemplates$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.template.TemplateRepository$getTemplates$1 r0 = new com.example.shellshot.template.TemplateRepository$getTemplates$1
            r0.<init>(r10, r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L47;
                case 1: goto L3c;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            int r2 = r0.I$1
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L3a
            r7 = r1
            goto L77
        L3a:
            r2 = move-exception
            goto L87
        L3c:
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            kotlin.ResultKt.throwOnFailure(r1)
            goto L60
        L47:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r3 = r10.mutex
            r4 = 0
            r5 = 0
            r0.L$0 = r3
            r0.I$0 = r5
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r3.lock(r4, r0)
            if (r6 != r2) goto L5d
            return r2
        L5d:
            r9 = r5
            r5 = r3
            r3 = r9
        L60:
            r6 = 0
            java.util.List<com.example.shellshot.template.ShellTemplate> r7 = r10.cachedTemplates     // Catch: java.lang.Throwable -> L3a
            if (r7 != 0) goto L80
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L3a
            r0.I$0 = r3     // Catch: java.lang.Throwable -> L3a
            r0.I$1 = r6     // Catch: java.lang.Throwable -> L3a
            r7 = 2
            r0.label = r7     // Catch: java.lang.Throwable -> L3a
            java.lang.Object r7 = r10.loadTemplatesInternal(r0)     // Catch: java.lang.Throwable -> L3a
            if (r7 != r2) goto L76
            return r2
        L76:
            r2 = r6
        L77:
            r6 = r7
            java.util.List r6 = (java.util.List) r6     // Catch: java.lang.Throwable -> L3a
            r8 = 0
            r10.cachedTemplates = r6     // Catch: java.lang.Throwable -> L3a
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L3a
            r6 = r2
        L80:
            r5.unlock(r4)
            return r7
        L87:
            r5.unlock(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.getTemplates(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshTemplates(kotlin.coroutines.Continuation<? super java.util.List<com.example.shellshot.template.ShellTemplate>> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.example.shellshot.template.TemplateRepository$refreshTemplates$1
            if (r0 == 0) goto L14
            r0 = r11
            com.example.shellshot.template.TemplateRepository$refreshTemplates$1 r0 = (com.example.shellshot.template.TemplateRepository$refreshTemplates$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.template.TemplateRepository$refreshTemplates$1 r0 = new com.example.shellshot.template.TemplateRepository$refreshTemplates$1
            r0.<init>(r10, r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L47;
                case 1: goto L3c;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            int r2 = r0.I$1
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L3a
            r7 = r1
            goto L73
        L3a:
            r2 = move-exception
            goto L82
        L3c:
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            kotlin.ResultKt.throwOnFailure(r1)
            goto L60
        L47:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r3 = r10.mutex
            r4 = 0
            r5 = 0
            r0.L$0 = r3
            r0.I$0 = r5
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r3.lock(r4, r0)
            if (r6 != r2) goto L5d
            return r2
        L5d:
            r9 = r5
            r5 = r3
            r3 = r9
        L60:
            r6 = 0
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L3a
            r0.I$0 = r3     // Catch: java.lang.Throwable -> L3a
            r0.I$1 = r6     // Catch: java.lang.Throwable -> L3a
            r7 = 2
            r0.label = r7     // Catch: java.lang.Throwable -> L3a
            java.lang.Object r7 = r10.loadTemplatesInternal(r0)     // Catch: java.lang.Throwable -> L3a
            if (r7 != r2) goto L72
            return r2
        L72:
            r2 = r6
        L73:
            r6 = r7
            java.util.List r6 = (java.util.List) r6     // Catch: java.lang.Throwable -> L3a
            r8 = 0
            r10.cachedTemplates = r6     // Catch: java.lang.Throwable -> L3a
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L3a
            r5.unlock(r4)
            return r7
        L82:
            r5.unlock(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.refreshTemplates(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTemplateById(java.lang.String r11, kotlin.coroutines.Continuation<? super com.example.shellshot.template.ShellTemplate> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.example.shellshot.template.TemplateRepository$getTemplateById$1
            if (r0 == 0) goto L14
            r0 = r12
            com.example.shellshot.template.TemplateRepository$getTemplateById$1 r0 = (com.example.shellshot.template.TemplateRepository$getTemplateById$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.template.TemplateRepository$getTemplateById$1 r0 = new com.example.shellshot.template.TemplateRepository$getTemplateById$1
            r0.<init>(r10, r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$0
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L45
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r11
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r10.getTemplates(r0)
            if (r3 != r2) goto L45
            return r2
        L45:
            r2 = r3
            java.util.List r2 = (java.util.List) r2
            r3 = r2
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            java.util.Iterator r5 = r3.iterator()
        L50:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L69
            java.lang.Object r6 = r5.next()
            r7 = r6
            com.example.shellshot.template.ShellTemplate r7 = (com.example.shellshot.template.ShellTemplate) r7
            r8 = 0
            java.lang.String r9 = r7.getId()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r11)
            if (r7 == 0) goto L50
            goto L6a
        L69:
            r6 = 0
        L6a:
            com.example.shellshot.template.ShellTemplate r6 = (com.example.shellshot.template.ShellTemplate) r6
            if (r6 != 0) goto L75
            java.lang.Object r3 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            r6 = r3
            com.example.shellshot.template.ShellTemplate r6 = (com.example.shellshot.template.ShellTemplate) r6
        L75:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.getTemplateById(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object importTemplateImage$default(TemplateRepository templateRepository, Uri uri, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return templateRepository.importTemplateImage(uri, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object importTemplateImage(android.net.Uri r12, java.lang.String r13, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateImportResult> r14) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.importTemplateImage(android.net.Uri, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object prepareTemplateImportDraft$default(TemplateRepository templateRepository, Uri uri, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return templateRepository.prepareTemplateImportDraft(uri, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object prepareTemplateImportDraft(android.net.Uri r12, java.lang.String r13, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateImportDraft> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$1
            if (r0 == 0) goto L14
            r0 = r14
            com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$1 r0 = (com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$1 r0 = new com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$1
            r0.<init>(r11, r14)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L5b;
                case 1: goto L46;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            int r2 = r0.I$1
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$1
            r13 = r6
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r6 = r0.L$0
            r12 = r6
            android.net.Uri r12 = (android.net.Uri) r12
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L44
            r7 = r1
            goto La5
        L44:
            r2 = move-exception
            goto Laf
        L46:
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$1
            r13 = r6
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r6 = r0.L$0
            r12 = r6
            android.net.Uri r12 = (android.net.Uri) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L78
        L5b:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r3 = r11.mutex
            r4 = 0
            r5 = 0
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r3
            r0.I$0 = r5
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r3.lock(r4, r0)
            if (r6 != r2) goto L75
            return r2
        L75:
            r10 = r5
            r5 = r3
            r3 = r10
        L78:
            r6 = 0
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L44
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7     // Catch: java.lang.Throwable -> L44
            com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$2$1 r8 = new com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$2$1     // Catch: java.lang.Throwable -> L44
            r9 = 0
            r8.<init>(r11, r12, r13, r9)     // Catch: java.lang.Throwable -> L44
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch: java.lang.Throwable -> L44
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch: java.lang.Throwable -> L44
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L44
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch: java.lang.Throwable -> L44
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L44
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L44
            r0.I$0 = r3     // Catch: java.lang.Throwable -> L44
            r0.I$1 = r6     // Catch: java.lang.Throwable -> L44
            r9 = 2
            r0.label = r9     // Catch: java.lang.Throwable -> L44
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r0)     // Catch: java.lang.Throwable -> L44
            if (r7 != r2) goto La4
            return r2
        La4:
            r2 = r6
        La5:
            com.example.shellshot.template.TemplateImportDraft r7 = (com.example.shellshot.template.TemplateImportDraft) r7     // Catch: java.lang.Throwable -> L44
            r5.unlock(r4)
            return r7
        Laf:
            r5.unlock(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.prepareTemplateImportDraft(android.net.Uri, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object importPreparedTemplate$default(TemplateRepository templateRepository, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return templateRepository.importPreparedTemplate(str, str2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object importPreparedTemplate(java.lang.String r12, java.lang.String r13, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateImportResult> r14) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.importPreparedTemplate(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deleteUserTemplate(java.lang.String r12, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateDeleteResult> r13) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.deleteUserTemplate(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object beginImageCalibration$default(TemplateRepository templateRepository, Uri uri, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return templateRepository.beginImageCalibration(uri, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object beginImageCalibration(android.net.Uri r12, java.lang.String r13, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateCalibrationDraft> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.example.shellshot.template.TemplateRepository$beginImageCalibration$1
            if (r0 == 0) goto L14
            r0 = r14
            com.example.shellshot.template.TemplateRepository$beginImageCalibration$1 r0 = (com.example.shellshot.template.TemplateRepository$beginImageCalibration$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.shellshot.template.TemplateRepository$beginImageCalibration$1 r0 = new com.example.shellshot.template.TemplateRepository$beginImageCalibration$1
            r0.<init>(r11, r14)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L5b;
                case 1: goto L46;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            int r2 = r0.I$1
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$1
            r13 = r6
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r6 = r0.L$0
            r12 = r6
            android.net.Uri r12 = (android.net.Uri) r12
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L44
            r7 = r1
            goto La5
        L44:
            r2 = move-exception
            goto Laf
        L46:
            int r3 = r0.I$0
            r4 = 0
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$1
            r13 = r6
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r6 = r0.L$0
            r12 = r6
            android.net.Uri r12 = (android.net.Uri) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L78
        L5b:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.sync.Mutex r3 = r11.mutex
            r4 = 0
            r5 = 0
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r3
            r0.I$0 = r5
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r3.lock(r4, r0)
            if (r6 != r2) goto L75
            return r2
        L75:
            r10 = r5
            r5 = r3
            r3 = r10
        L78:
            r6 = 0
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L44
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7     // Catch: java.lang.Throwable -> L44
            com.example.shellshot.template.TemplateRepository$beginImageCalibration$2$1 r8 = new com.example.shellshot.template.TemplateRepository$beginImageCalibration$2$1     // Catch: java.lang.Throwable -> L44
            r9 = 0
            r8.<init>(r11, r12, r13, r9)     // Catch: java.lang.Throwable -> L44
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch: java.lang.Throwable -> L44
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch: java.lang.Throwable -> L44
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L44
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch: java.lang.Throwable -> L44
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L44
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L44
            r0.I$0 = r3     // Catch: java.lang.Throwable -> L44
            r0.I$1 = r6     // Catch: java.lang.Throwable -> L44
            r9 = 2
            r0.label = r9     // Catch: java.lang.Throwable -> L44
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r0)     // Catch: java.lang.Throwable -> L44
            if (r7 != r2) goto La4
            return r2
        La4:
            r2 = r6
        La5:
            com.example.shellshot.template.TemplateCalibrationDraft r7 = (com.example.shellshot.template.TemplateCalibrationDraft) r7     // Catch: java.lang.Throwable -> L44
            r5.unlock(r4)
            return r7
        Laf:
            r5.unlock(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.beginImageCalibration(android.net.Uri, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveCalibrationDraft(com.example.shellshot.template.TemplateCalibrationDraft r12, kotlin.coroutines.Continuation<? super com.example.shellshot.template.TemplateCalibrationResult> r13) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.saveCalibrationDraft(com.example.shellshot.template.TemplateCalibrationDraft, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object loadFrameBitmap(ShellTemplate template, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TemplateRepository$loadFrameBitmap$2(this, template, null), continuation);
    }

    public final Object loadScreenMaskBitmap(ShellTemplate template, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TemplateRepository$loadScreenMaskBitmap$2(template, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadTemplatesInternal(Continuation<? super List<ShellTemplate>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TemplateRepository$loadTemplatesInternal$2(this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ShellTemplate> loadBuiltInTemplates() {
        TemplateRepository templateRepository = this;
        InputStream open = templateRepository.context.getAssets().open("templates/template_list.json");
        try {
            InputStream inputStream = open;
            Json json = templateRepository.json;
            Intrinsics.checkNotNull(inputStream);
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            String readText = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
            json.getSerializersModule();
            TemplateListAsset templateList = (TemplateListAsset) json.decodeFromString(TemplateListAsset.INSTANCE.serializer(), readText);
            CloseableKt.closeFinally(open, null);
            Iterable templates = templateList.getTemplates();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(templates, 10));
            Iterator it = templates.iterator();
            while (it.hasNext()) {
                open = templateRepository.context.getAssets().open((String) it.next());
                try {
                    InputStream inputStream2 = open;
                    Json json2 = templateRepository.json;
                    Intrinsics.checkNotNull(inputStream2);
                    Reader inputStreamReader2 = new InputStreamReader(inputStream2, Charsets.UTF_8);
                    String readText2 = TextStreamsKt.readText(inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192));
                    json2.getSerializersModule();
                    TemplateConfig templateConfig = (TemplateConfig) json2.decodeFromString(TemplateConfig.INSTANCE.serializer(), readText2);
                    CloseableKt.closeFinally(open, null);
                    arrayList.add(ShellTemplate.Companion.fromConfig$default(ShellTemplate.INSTANCE, templateConfig, true, null, 4, null));
                    templateRepository = this;
                } finally {
                }
            }
            return (List) arrayList;
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x011f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.example.shellshot.template.ShellTemplate> loadUserTemplates() {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.loadUserTemplates():java.util.List");
    }

    static /* synthetic */ TemplateCalibrationDraft createDraftFromImage$default(TemplateRepository templateRepository, Uri uri, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return templateRepository.createDraftFromImage(uri, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TemplateCalibrationDraft createDraftFromImage(Uri imageUri, String templateNameOverride) {
        File sourceFile = stageSourceImage(imageUri, templateNameOverride);
        String absolutePath = sourceFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return createDraftFromStagedPath(absolutePath, templateNameOverride);
    }

    static /* synthetic */ TemplateCalibrationDraft createDraftFromStagedPath$default(TemplateRepository templateRepository, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return templateRepository.createDraftFromStagedPath(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TemplateCalibrationDraft createDraftFromStagedPath(String sourceImagePath, String templateNameOverride) {
        String str;
        String obj;
        File sourceFile = new File(sourceImagePath);
        if (!(sourceFile.exists() && sourceFile.isFile())) {
            throw new IllegalStateException("选中的模板图片已经失效，请重新上传".toString());
        }
        String absolutePath = sourceFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        Bitmap frameBitmap = decodeBitmapPath(absolutePath);
        DetectionResult detection = detectScreenOpening(frameBitmap);
        if (!frameBitmap.isRecycled()) {
            frameBitmap.recycle();
        }
        String absolutePath2 = sourceFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
        if (templateNameOverride != null && (obj = StringsKt.trim((CharSequence) templateNameOverride).toString()) != null) {
            if (StringsKt.isBlank(obj)) {
                obj = null;
            }
            if (obj != null) {
                str = obj;
                return new TemplateCalibrationDraft(absolutePath2, str, detection.getOutputWidth(), detection.getOutputHeight(), PREVIEW_SCREENSHOT_WIDTH, PREVIEW_SCREENSHOT_HEIGHT, detection.getScreenRect(), detection.getSummary(), 0, 0, 0, 0, 1, 3840, null);
            }
        }
        String nameWithoutExtension = FilesKt.getNameWithoutExtension(sourceFile);
        if (StringsKt.isBlank(nameWithoutExtension)) {
            nameWithoutExtension = "我的模板";
        }
        str = nameWithoutExtension;
        return new TemplateCalibrationDraft(absolutePath2, str, detection.getOutputWidth(), detection.getOutputHeight(), PREVIEW_SCREENSHOT_WIDTH, PREVIEW_SCREENSHOT_HEIGHT, detection.getScreenRect(), detection.getSummary(), 0, 0, 0, 0, 1, 3840, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x020f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.example.shellshot.template.TemplateImportResult persistDraft(com.example.shellshot.template.TemplateCalibrationDraft r29) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.persistDraft(com.example.shellshot.template.TemplateCalibrationDraft):com.example.shellshot.template.TemplateImportResult");
    }

    static /* synthetic */ File stageSourceImage$default(TemplateRepository templateRepository, Uri uri, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return templateRepository.stageSourceImage(uri, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File stageSourceImage(Uri imageUri, String fileNameHint) {
        FilesKt.deleteRecursively(draftsRoot());
        File draftDirectory = draftsRoot();
        draftDirectory.mkdirs();
        return copyImageToDraft(imageUri, draftDirectory, fileNameHint);
    }

    static /* synthetic */ File copyImageToDraft$default(TemplateRepository templateRepository, Uri uri, File file, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return templateRepository.copyImageToDraft(uri, file, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:
    
        if (r0 != null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.io.File copyImageToDraft(android.net.Uri r18, java.io.File r19, java.lang.String r20) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            android.content.Context r0 = r1.context
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r0 = r0.getType(r2)
            r3 = 2
            r4 = 0
            if (r0 == 0) goto L35
        L15:
            r5 = 47
            java.lang.String r0 = kotlin.text.StringsKt.substringAfter$default(r0, r5, r4, r3, r4)
            if (r0 == 0) goto L35
        L1e:
            r5 = 59
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default(r0, r5, r4, r3, r4)
            if (r0 == 0) goto L35
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r5 = kotlin.text.StringsKt.isBlank(r0)
            if (r5 == 0) goto L30
            r0 = 0
            r0 = r4
        L30:
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L35
            goto L37
        L35:
            java.lang.String r0 = "png"
        L37:
            r5 = r0
            r6 = r20
            java.lang.String r0 = r1.sanitizeFileName(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r7 = kotlin.text.StringsKt.isBlank(r0)
            if (r7 == 0) goto L4a
            r0 = 0
            java.lang.String r0 = "source"
        L4a:
            r7 = r0
            java.lang.String r7 = (java.lang.String) r7
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r7)
            java.lang.String r9 = "."
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r5)
            java.lang.String r8 = r8.toString()
            r9 = r19
            r0.<init>(r9, r8)
            r8 = r0
            android.content.Context r0 = r1.context
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.io.InputStream r0 = r0.openInputStream(r2)
            if (r0 == 0) goto Lad
            r10 = r0
            java.io.Closeable r10 = (java.io.Closeable) r10
            r0 = r10
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: java.lang.Throwable -> La5
            r11 = r0
            r12 = 0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> La5
            r0.<init>(r8)     // Catch: java.lang.Throwable -> La5
            r13 = r0
            java.io.Closeable r13 = (java.io.Closeable) r13     // Catch: java.lang.Throwable -> La5
            r0 = r13
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch: java.lang.Throwable -> L9d
            r14 = 0
            r15 = r0
            java.io.OutputStream r15 = (java.io.OutputStream) r15     // Catch: java.lang.Throwable -> L9d
            r16 = r0
            r0 = 0
            kotlin.io.ByteStreamsKt.copyTo$default(r11, r15, r0, r3, r4)     // Catch: java.lang.Throwable -> L9d
            kotlin.io.CloseableKt.closeFinally(r13, r4)     // Catch: java.lang.Throwable -> La5
            kotlin.io.CloseableKt.closeFinally(r10, r4)
            return r8
        L9d:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch: java.lang.Throwable -> La0
        La0:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r13, r3)     // Catch: java.lang.Throwable -> La5
            throw r0     // Catch: java.lang.Throwable -> La5
        La5:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch: java.lang.Throwable -> La8
        La8:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r3)
            throw r0
        Lad:
            r0 = 0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r3 = "无法读取所选图片，请重新选择"
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.template.TemplateRepository.copyImageToDraft(android.net.Uri, java.io.File, java.lang.String):java.io.File");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap decodeBitmapPath(String path) {
        InputStream open;
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        File file = new File(path);
        if (file.isAbsolute() && file.exists()) {
            open = new FileInputStream(file);
            try {
                bitmap = BitmapFactory.decodeStream(open, null, options);
                CloseableKt.closeFinally(open, null);
            } finally {
            }
        } else {
            open = this.context.getAssets().open(path);
            try {
                bitmap = BitmapFactory.decodeStream(open, null, options);
                CloseableKt.closeFinally(open, null);
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        }
        if (bitmap != null) {
            return bitmap;
        }
        throw new IllegalStateException(("无法解码模板图片: " + path).toString());
    }

    private final DetectionResult detectScreenOpening(Bitmap frameBitmap) {
        int width = frameBitmap.getWidth();
        int height = frameBitmap.getHeight();
        int[] pixels = new int[width * height];
        frameBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        Pair start = findTransparentSeed(pixels, width, height);
        int x = 1064011039;
        int y = 1063339950;
        float f = 0.12f;
        float f2 = 0.08f;
        if (start == null) {
            ScreenRect fallbackRect = new ScreenRect((int) (width * 0.12f), (int) (height * 0.08f), (int) (width * 0.88f), (int) (height * 0.92f));
            return new DetectionResult(fallbackRect, createRectMask(width, height, fallbackRect), "未识别到透明屏幕区，已使用居中保底区域", width, height);
        }
        boolean[] visited = new boolean[width * height];
        ArrayDeque queue = new ArrayDeque();
        int[] maskPixels = new int[width * height];
        queue.addLast(start);
        visited[(start.getSecond().intValue() * width) + start.getFirst().intValue()] = true;
        int minX = width;
        int maxX = 0;
        int minY = height;
        int area = 0;
        int maxY = 0;
        while (!queue.isEmpty()) {
            Pair pair = (Pair) queue.removeFirst();
            int i = x;
            int x2 = ((Number) pair.component1()).intValue();
            int i2 = y;
            int y2 = ((Number) pair.component2()).intValue();
            int offset = (y2 * width) + x2;
            maskPixels[offset] = -1;
            area++;
            if (x2 < minX) {
                minX = x2;
            }
            if (y2 < minY) {
                minY = y2;
            }
            if (x2 > maxX) {
                maxX = x2;
            }
            if (y2 > maxY) {
                maxY = y2;
            }
            for (Pair<Integer, Integer> pair2 : fourNeighbors(x2, y2, width, height)) {
                int nx = pair2.component1().intValue();
                int ny = pair2.component2().intValue();
                int nextOffset = (ny * width) + nx;
                if (!visited[nextOffset]) {
                    visited[nextOffset] = true;
                    float f3 = f;
                    if (!isTransparentPixel(pixels[nextOffset])) {
                        f = f3;
                    } else {
                        queue.addLast(TuplesKt.to(Integer.valueOf(nx), Integer.valueOf(ny)));
                        f = f3;
                        f2 = f2;
                    }
                }
            }
            y = i2;
            x = i;
        }
        int i3 = x;
        int i4 = y;
        float f4 = f;
        float f5 = f2;
        if (area < width * height * f5) {
            ScreenRect fallbackRect2 = new ScreenRect((int) (width * f4), (int) (height * f5), (int) (width * i4), (int) (height * i3));
            return new DetectionResult(fallbackRect2, createRectMask(width, height, fallbackRect2), "透明区域过小，已切换到保底区域", width, height);
        }
        ScreenRect screenRect = new ScreenRect(minX, minY, maxX + 1, maxY + 1);
        Bitmap maskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(maskBitmap, "createBitmap(...)");
        maskBitmap.setPixels(maskPixels, 0, width, 0, 0, width, height);
        return new DetectionResult(screenRect, maskBitmap, "已自动识别屏幕开口并生成精确遮罩", width, height);
    }

    private final Bitmap buildAdjustedMask(Bitmap baseMask, ScreenRect finalRect) {
        int i;
        int width = baseMask.getWidth();
        int height = baseMask.getHeight();
        int[] source = new int[width * height];
        baseMask.getPixels(source, 0, width, 0, 0, width, height);
        int[] output = new int[width * height];
        int y = 0;
        while (y < height) {
            int x = 0;
            while (x < width) {
                int offset = (y * width) + x;
                boolean insideRect = false;
                if (x < finalRect.getRight() && finalRect.getLeft() <= x) {
                    if (y < finalRect.getBottom() && finalRect.getTop() <= y) {
                        insideRect = true;
                    }
                }
                if (insideRect && Color.red(source[offset]) >= 128) {
                    i = -1;
                } else {
                    i = ViewCompat.MEASURED_STATE_MASK;
                }
                output[offset] = i;
                x++;
            }
            y++;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        createBitmap.setPixels(output, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private final Bitmap createRectMask(int width, int height, ScreenRect rect) {
        int i = width * height;
        int[] pixels = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            pixels[i2] = -16777216;
        }
        int coerceAtMost = RangesKt.coerceAtMost(rect.getBottom(), height);
        for (int y = rect.getTop(); y < coerceAtMost; y++) {
            int coerceAtMost2 = RangesKt.coerceAtMost(rect.getRight(), width);
            for (int x = rect.getLeft(); x < coerceAtMost2; x++) {
                pixels[(y * width) + x] = -1;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        createBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private final Pair<Integer, Integer> findTransparentSeed(int[] pixels, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;
        if (isTransparentPixel(pixels[(centerY * width) + centerX])) {
            return TuplesKt.to(Integer.valueOf(centerX), Integer.valueOf(centerY));
        }
        int maxRadius = Math.min(width, height) / 2;
        int radius = 1;
        if (1 > maxRadius) {
            return null;
        }
        while (true) {
            int left = RangesKt.coerceAtLeast(centerX - radius, 0);
            int right = RangesKt.coerceAtMost(centerX + radius, width - 1);
            int top = RangesKt.coerceAtLeast(centerY - radius, 0);
            int bottom = RangesKt.coerceAtMost(centerY + radius, height - 1);
            int x = left;
            if (x <= right) {
                while (!isTransparentPixel(pixels[(top * width) + x])) {
                    if (isTransparentPixel(pixels[(bottom * width) + x])) {
                        return TuplesKt.to(Integer.valueOf(x), Integer.valueOf(bottom));
                    }
                    if (x != right) {
                        x++;
                    }
                }
                return TuplesKt.to(Integer.valueOf(x), Integer.valueOf(top));
            }
            int y = top;
            if (y <= bottom) {
                while (!isTransparentPixel(pixels[(y * width) + left])) {
                    if (isTransparentPixel(pixels[(y * width) + right])) {
                        return TuplesKt.to(Integer.valueOf(right), Integer.valueOf(y));
                    }
                    if (y != bottom) {
                        y++;
                    }
                }
                return TuplesKt.to(Integer.valueOf(left), Integer.valueOf(y));
            }
            if (radius == maxRadius) {
                return null;
            }
            radius++;
        }
    }

    private final boolean isTransparentPixel(int color) {
        return Color.alpha(color) <= 10;
    }

    private final List<Pair<Integer, Integer>> fourNeighbors(int x, int y, int width, int height) {
        ArrayList result = new ArrayList(4);
        if (x > 0) {
            result.add(TuplesKt.to(Integer.valueOf(x - 1), Integer.valueOf(y)));
        }
        if (x + 1 < width) {
            result.add(TuplesKt.to(Integer.valueOf(x + 1), Integer.valueOf(y)));
        }
        if (y > 0) {
            result.add(TuplesKt.to(Integer.valueOf(x), Integer.valueOf(y - 1)));
        }
        if (y + 1 < height) {
            result.add(TuplesKt.to(Integer.valueOf(x), Integer.valueOf(y + 1)));
        }
        return result;
    }

    private final void purgeLegacyImportedTemplates() {
        FilesKt.deleteRecursively(new File(this.context.getFilesDir(), LEGACY_IMPORTED_TEMPLATE_DIR));
    }

    private final String sanitizeFileName(String raw) {
        String obj;
        if (raw != null && (obj = StringsKt.trim((CharSequence) raw).toString()) != null) {
            String replace = new Regex("[\\\\/:*?\"<>|]").replace(obj, "_");
            if (replace != null) {
                String replace2 = new Regex("\\s+").replace(replace, "_");
                if (replace2 != null) {
                    String str = replace2;
                    if (StringsKt.isBlank(str)) {
                        str = null;
                    }
                    String str2 = str;
                    if (str2 != null) {
                        return str2;
                    }
                }
            }
        }
        return "source";
    }

    private final File draftsRoot() {
        return new File(this.context.getFilesDir(), "template_calibration");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File userTemplatesRoot() {
        return new File(this.context.getFilesDir(), USER_TEMPLATE_ROOT);
    }

    private final String buildUserTemplateId() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.getDefault()).format(new Date());
        return "user_" + timestamp;
    }

    /* compiled from: TemplateRepository.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006!"}, d2 = {"Lcom/example/shellshot/template/TemplateRepository$DetectionResult;", "", "screenRect", "Lcom/example/shellshot/template/ScreenRect;", "maskBitmap", "Landroid/graphics/Bitmap;", "summary", "", "outputWidth", "", "outputHeight", "<init>", "(Lcom/example/shellshot/template/ScreenRect;Landroid/graphics/Bitmap;Ljava/lang/String;II)V", "getScreenRect", "()Lcom/example/shellshot/template/ScreenRect;", "getMaskBitmap", "()Landroid/graphics/Bitmap;", "getSummary", "()Ljava/lang/String;", "getOutputWidth", "()I", "getOutputHeight", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class DetectionResult {
        private final Bitmap maskBitmap;
        private final int outputHeight;
        private final int outputWidth;
        private final ScreenRect screenRect;
        private final String summary;

        public static /* synthetic */ DetectionResult copy$default(DetectionResult detectionResult, ScreenRect screenRect, Bitmap bitmap, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                screenRect = detectionResult.screenRect;
            }
            if ((i3 & 2) != 0) {
                bitmap = detectionResult.maskBitmap;
            }
            if ((i3 & 4) != 0) {
                str = detectionResult.summary;
            }
            if ((i3 & 8) != 0) {
                i = detectionResult.outputWidth;
            }
            if ((i3 & 16) != 0) {
                i2 = detectionResult.outputHeight;
            }
            int i4 = i2;
            String str2 = str;
            return detectionResult.copy(screenRect, bitmap, str2, i, i4);
        }

        /* renamed from: component1, reason: from getter */
        public final ScreenRect getScreenRect() {
            return this.screenRect;
        }

        /* renamed from: component2, reason: from getter */
        public final Bitmap getMaskBitmap() {
            return this.maskBitmap;
        }

        /* renamed from: component3, reason: from getter */
        public final String getSummary() {
            return this.summary;
        }

        /* renamed from: component4, reason: from getter */
        public final int getOutputWidth() {
            return this.outputWidth;
        }

        /* renamed from: component5, reason: from getter */
        public final int getOutputHeight() {
            return this.outputHeight;
        }

        public final DetectionResult copy(ScreenRect screenRect, Bitmap maskBitmap, String summary, int outputWidth, int outputHeight) {
            Intrinsics.checkNotNullParameter(screenRect, "screenRect");
            Intrinsics.checkNotNullParameter(maskBitmap, "maskBitmap");
            Intrinsics.checkNotNullParameter(summary, "summary");
            return new DetectionResult(screenRect, maskBitmap, summary, outputWidth, outputHeight);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DetectionResult)) {
                return false;
            }
            DetectionResult detectionResult = (DetectionResult) other;
            return Intrinsics.areEqual(this.screenRect, detectionResult.screenRect) && Intrinsics.areEqual(this.maskBitmap, detectionResult.maskBitmap) && Intrinsics.areEqual(this.summary, detectionResult.summary) && this.outputWidth == detectionResult.outputWidth && this.outputHeight == detectionResult.outputHeight;
        }

        public int hashCode() {
            return (((((((this.screenRect.hashCode() * 31) + this.maskBitmap.hashCode()) * 31) + this.summary.hashCode()) * 31) + Integer.hashCode(this.outputWidth)) * 31) + Integer.hashCode(this.outputHeight);
        }

        public String toString() {
            return "DetectionResult(screenRect=" + this.screenRect + ", maskBitmap=" + this.maskBitmap + ", summary=" + this.summary + ", outputWidth=" + this.outputWidth + ", outputHeight=" + this.outputHeight + ")";
        }

        public DetectionResult(ScreenRect screenRect, Bitmap maskBitmap, String summary, int outputWidth, int outputHeight) {
            Intrinsics.checkNotNullParameter(screenRect, "screenRect");
            Intrinsics.checkNotNullParameter(maskBitmap, "maskBitmap");
            Intrinsics.checkNotNullParameter(summary, "summary");
            this.screenRect = screenRect;
            this.maskBitmap = maskBitmap;
            this.summary = summary;
            this.outputWidth = outputWidth;
            this.outputHeight = outputHeight;
        }

        public final ScreenRect getScreenRect() {
            return this.screenRect;
        }

        public final Bitmap getMaskBitmap() {
            return this.maskBitmap;
        }

        public final String getSummary() {
            return this.summary;
        }

        public final int getOutputWidth() {
            return this.outputWidth;
        }

        public final int getOutputHeight() {
            return this.outputHeight;
        }
    }

    /* compiled from: TemplateRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/example/shellshot/template/TemplateRepository$Companion;", "", "<init>", "()V", "TAG", "", "USER_TEMPLATE_ROOT", "USER_TEMPLATE_CONFIG_NAME", "LEGACY_IMPORTED_TEMPLATE_DIR", "PREVIEW_SCREENSHOT_WIDTH", "", "PREVIEW_SCREENSHOT_HEIGHT", "DEFAULT_SHRINK_PIXELS", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
