package com.example.shellshot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.shellshot.ShellShotApplication;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.utils.ServiceController;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoShellRestartReceiver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/example/shellshot/receiver/AutoShellRestartReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AutoShellRestartReceiver extends BroadcastReceiver {

    @Deprecated
    public static final String TAG = "AutoShellRestart";
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AppContainer appContainer;
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        ShellShotApplication shellShotApplication = applicationContext instanceof ShellShotApplication ? (ShellShotApplication) applicationContext : null;
        if (shellShotApplication == null || (appContainer = shellShotApplication.getAppContainer()) == null) {
            return;
        }
        appContainer.getLogger().d(TAG, "收到服务自恢复广播，准备重新拉起后台监听");
        ServiceController serviceController = appContainer.getServiceController();
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        serviceController.start(applicationContext2);
    }

    /* compiled from: AutoShellRestartReceiver.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/receiver/AutoShellRestartReceiver$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
