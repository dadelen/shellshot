package com.example.shellshot.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.example.shellshot.ShellShotApplication;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.service.AutoShellForegroundService;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ServiceController.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/example/shellshot/utils/ServiceController;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "stop", "log", "message", "", "diagnosticTrace", "reason", "TAG", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class ServiceController {
    public static final int $stable = 0;
    public static final ServiceController INSTANCE = new ServiceController();
    private static final String TAG = "ServiceController";

    private ServiceController() {
    }

    public final void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        log(context, "Requesting service start " + diagnosticTrace("service_controller_start"));
        Intent intent = new Intent(context, (Class<?>) AutoShellForegroundService.class);
        intent.setAction(AutoShellForegroundService.ACTION_START);
        ContextCompat.startForegroundService(context, intent);
    }

    public final void stop(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        log(context, "Requesting service stop " + diagnosticTrace("service_controller_stop"));
        Intent intent = new Intent(context, (Class<?>) AutoShellForegroundService.class);
        intent.setAction(AutoShellForegroundService.ACTION_STOP);
        intent.putExtra(AutoShellForegroundService.EXTRA_TRUSTED_STOP, true);
        context.startService(intent);
    }

    private final void log(Context context, String message) {
        AppContainer appContainer;
        Context applicationContext = context.getApplicationContext();
        ShellLogger logger = null;
        ShellShotApplication shellShotApplication = applicationContext instanceof ShellShotApplication ? (ShellShotApplication) applicationContext : null;
        if (shellShotApplication != null && (appContainer = shellShotApplication.getAppContainer()) != null) {
            logger = appContainer.getLogger();
        }
        if (logger != null) {
            logger.d(TAG, message);
        } else {
            Log.d(TAG, message);
        }
    }

    private final String diagnosticTrace(String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("reason=");
        sb.append(reason);
        sb.append(" thread=");
        sb.append(Thread.currentThread().getName());
        sb.append('\n');
        sb.append(ExceptionsKt.stackTraceToString(new Throwable()));
        return sb.toString();
    }
}
