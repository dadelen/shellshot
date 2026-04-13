package com.example.shellshot.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogEntry.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/example/shellshot/data/LogEntry;", "", "timestampMillis", "", "level", "Lcom/example/shellshot/data/LogLevel;", "tag", "", "message", "<init>", "(JLcom/example/shellshot/data/LogLevel;Ljava/lang/String;Ljava/lang/String;)V", "getTimestampMillis", "()J", "getLevel", "()Lcom/example/shellshot/data/LogLevel;", "getTag", "()Ljava/lang/String;", "getMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class LogEntry {
    public static final int $stable = 0;
    private final LogLevel level;
    private final String message;
    private final String tag;
    private final long timestampMillis;

    public static /* synthetic */ LogEntry copy$default(LogEntry logEntry, long j, LogLevel logLevel, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = logEntry.timestampMillis;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            logLevel = logEntry.level;
        }
        LogLevel logLevel2 = logLevel;
        if ((i & 4) != 0) {
            str = logEntry.tag;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = logEntry.message;
        }
        return logEntry.copy(j2, logLevel2, str3, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    /* renamed from: component2, reason: from getter */
    public final LogLevel getLevel() {
        return this.level;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final LogEntry copy(long timestampMillis, LogLevel level, String tag, String message) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        return new LogEntry(timestampMillis, level, tag, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEntry)) {
            return false;
        }
        LogEntry logEntry = (LogEntry) other;
        return this.timestampMillis == logEntry.timestampMillis && this.level == logEntry.level && Intrinsics.areEqual(this.tag, logEntry.tag) && Intrinsics.areEqual(this.message, logEntry.message);
    }

    public int hashCode() {
        return (((((Long.hashCode(this.timestampMillis) * 31) + this.level.hashCode()) * 31) + this.tag.hashCode()) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "LogEntry(timestampMillis=" + this.timestampMillis + ", level=" + this.level + ", tag=" + this.tag + ", message=" + this.message + ")";
    }

    public LogEntry(long timestampMillis, LogLevel level, String tag, String message) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        this.timestampMillis = timestampMillis;
        this.level = level;
        this.tag = tag;
        this.message = message;
    }

    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    public final LogLevel getLevel() {
        return this.level;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getMessage() {
        return this.message;
    }
}
