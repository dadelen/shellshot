package com.example.shellshot.permissions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialAccessNavigator.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, d2 = {"Lcom/example/shellshot/permissions/SpecialAccessNavigator;", "", "<init>", "()V", "openNotificationSettings", "", "context", "Landroid/content/Context;", "openAppDetailsSettings", "openManageAllFilesSettings", "safeStart", "intent", "Landroid/content/Intent;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class SpecialAccessNavigator {
    public static final int $stable = 0;
    public static final SpecialAccessNavigator INSTANCE = new SpecialAccessNavigator();

    private SpecialAccessNavigator() {
    }

    public final void openNotificationSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        intent.addFlags(268435456);
        safeStart(context, intent);
    }

    public final void openAppDetailsSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        safeStart(context, intent);
    }

    public final void openManageAllFilesSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        safeStart(context, intent);
    }

    private final void safeStart(Context context, Intent intent) {
        Intent targetIntent;
        Intent fallbackIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        fallbackIntent.setData(Uri.parse("package:" + context.getPackageName()));
        fallbackIntent.addFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            targetIntent = intent;
        } else {
            targetIntent = fallbackIntent;
        }
        context.startActivity(targetIntent);
    }
}
