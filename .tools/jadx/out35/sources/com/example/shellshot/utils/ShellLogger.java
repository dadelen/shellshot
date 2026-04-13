package com.example.shellshot.utils;

import android.util.Log;
import com.example.shellshot.data.LogEntry;
import com.example.shellshot.data.LogLevel;
import com.example.shellshot.data.LogRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ShellLogger.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\"\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/example/shellshot/utils/ShellLogger;", "", "logRepository", "Lcom/example/shellshot/data/LogRepository;", "<init>", "(Lcom/example/shellshot/data/LogRepository;)V", "d", "", "tag", "", "message", "e", "throwable", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class ShellLogger {
    public static final int $stable = 8;
    private final LogRepository logRepository;

    public ShellLogger(LogRepository logRepository) {
        Intrinsics.checkNotNullParameter(logRepository, "logRepository");
        this.logRepository = logRepository;
    }

    public final void d(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.d(tag, message);
        this.logRepository.append(new LogEntry(System.currentTimeMillis(), LogLevel.Debug, tag, message));
    }

    public static /* synthetic */ void e$default(ShellLogger shellLogger, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        shellLogger.e(str, str2, th);
    }

    public final void e(String tag, String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.e(tag, message, throwable);
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        if (throwable != null) {
            sb.append(" | ");
            sb.append(throwable.getClass().getSimpleName());
            String message2 = throwable.getMessage();
            if (!(message2 == null || StringsKt.isBlank(message2))) {
                sb.append(": ");
                sb.append(throwable.getMessage());
            }
        }
        String renderedMessage = sb.toString();
        this.logRepository.append(new LogEntry(System.currentTimeMillis(), LogLevel.Error, tag, renderedMessage));
    }
}
