package com.example.shellshot.utils;

import com.example.shellshot.media.ScreenshotDirectories;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimeUtils.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/shellshot/utils/TimeUtils;", "", "<init>", "()V", "displayFormatter", "Ljava/text/SimpleDateFormat;", "fileNameFormatter", "formatTimestamp", "", "timestampMillis", "", "buildOutputFileName", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class TimeUtils {
    public static final TimeUtils INSTANCE = new TimeUtils();
    private static final SimpleDateFormat displayFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat fileNameFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
    public static final int $stable = 8;

    private TimeUtils() {
    }

    public final String formatTimestamp(long timestampMillis) {
        String format = displayFormatter.format(new Date(timestampMillis));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final String buildOutputFileName(long timestampMillis) {
        return ScreenshotDirectories.OUTPUT_PREFIX + fileNameFormatter.format(new Date(timestampMillis)) + ".png";
    }
}
