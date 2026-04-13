package com.example.shellshot.data;

import android.content.Context;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LogRepository.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/shellshot/data/LogRepository;", "", "context", "Landroid/content/Context;", "appStateStore", "Lcom/example/shellshot/data/AppStateStore;", "<init>", "(Landroid/content/Context;Lcom/example/shellshot/data/AppStateStore;)V", "logFile", "Ljava/io/File;", "lock", "append", "", "entry", "Lcom/example/shellshot/data/LogEntry;", "hydrateRecentLogs", "trimLogFileIfNeeded", "serialize", "", "deserialize", "raw", "encode", "value", "decode", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class LogRepository {

    @Deprecated
    public static final int MAX_LOG_COUNT = 100;

    @Deprecated
    public static final int MAX_PERSISTED_COUNT = 400;
    private final AppStateStore appStateStore;
    private final Object lock;
    private final File logFile;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public LogRepository(Context context, AppStateStore appStateStore) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appStateStore, "appStateStore");
        this.appStateStore = appStateStore;
        this.logFile = new File(context.getFilesDir(), "shellshot_logs.txt");
        this.lock = new Object();
        hydrateRecentLogs();
    }

    public final void append(LogEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        synchronized (this.lock) {
            this.appStateStore.pushLog(entry);
            File parentFile = this.logFile.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FilesKt.appendText$default(this.logFile, serialize(entry) + "\n", null, 2, null);
            trimLogFileIfNeeded();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void hydrateRecentLogs() {
        if (this.logFile.exists()) {
            synchronized (this.lock) {
                Iterable takeLast = CollectionsKt.takeLast(FilesKt.readLines$default(this.logFile, null, 1, null), 100);
                Collection arrayList = new ArrayList();
                Iterator it = takeLast.iterator();
                while (it.hasNext()) {
                    LogEntry deserialize = deserialize((String) it.next());
                    if (deserialize != null) {
                        arrayList.add(deserialize);
                    }
                }
                AppStateStore appStateStore = this.appStateStore;
                Iterator it2 = ((List) arrayList).iterator();
                while (it2.hasNext()) {
                    appStateStore.pushLog((LogEntry) it2.next());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void trimLogFileIfNeeded() {
        List lines = FilesKt.readLines$default(this.logFile, null, 1, null);
        if (lines.size() <= 400) {
            return;
        }
        FilesKt.writeText$default(this.logFile, CollectionsKt.joinToString$default(CollectionsKt.takeLast(lines, MAX_PERSISTED_COUNT), "\n", null, "\n", 0, null, null, 58, null), null, 2, null);
    }

    private final String serialize(LogEntry entry) {
        return CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{String.valueOf(entry.getTimestampMillis()), entry.getLevel().name(), encode(entry.getTag()), encode(entry.getMessage())}), "|", null, null, 0, null, null, 62, null);
    }

    private final LogEntry deserialize(String raw) {
        Object m7212constructorimpl;
        List parts = StringsKt.split$default((CharSequence) raw, new String[]{"|"}, false, 0, 6, (Object) null);
        if (parts.size() < 4) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            LogRepository logRepository = this;
            m7212constructorimpl = Result.m7212constructorimpl(new LogEntry(Long.parseLong((String) parts.get(0)), LogLevel.valueOf((String) parts.get(1)), logRepository.decode((String) parts.get(2)), logRepository.decode((String) parts.get(3))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
        }
        return (LogEntry) (Result.m7218isFailureimpl(m7212constructorimpl) ? null : m7212constructorimpl);
    }

    private final String encode(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = value.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String encodeToString = encoder.encodeToString(bytes);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
        return encodeToString;
    }

    private final String decode(String value) {
        byte[] decode = Base64.getDecoder().decode(value);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return new String(decode, UTF_8);
    }

    /* compiled from: LogRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/example/shellshot/data/LogRepository$Companion;", "", "<init>", "()V", "MAX_LOG_COUNT", "", "MAX_PERSISTED_COUNT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
