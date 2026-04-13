package com.example.shellshot;

import android.app.Application;
import com.example.shellshot.data.AppContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: ShellShotApplication.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0002R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/example/shellshot/ShellShotApplication;", "Landroid/app/Application;", "<init>", "()V", "value", "Lcom/example/shellshot/data/AppContainer;", "appContainer", "getAppContainer", "()Lcom/example/shellshot/data/AppContainer;", "onCreate", "", "restoreMonitoringIfNeeded", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ShellShotApplication extends Application {

    @Deprecated
    public static final String TAG = "ShellShotApp";
    private AppContainer appContainer;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public final AppContainer getAppContainer() {
        AppContainer appContainer = this.appContainer;
        if (appContainer != null) {
            return appContainer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appContainer");
        return null;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.appContainer = new AppContainer(this);
        restoreMonitoringIfNeeded();
    }

    private final void restoreMonitoringIfNeeded() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO())), null, null, new ShellShotApplication$restoreMonitoringIfNeeded$1(this, null), 3, null);
    }

    /* compiled from: ShellShotApplication.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/ShellShotApplication$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
