package com.example.shellshot.service;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import androidx.autofill.HintConstants;
import com.example.shellshot.utils.ShellLogger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenStateMonitor.kt */
@Metadata(d1 = {"\u0000Q\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0007*\u0001 \b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\"\u001a\u00020\u000eJ\u0006\u0010#\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R#\u0010\u0018\u001a\n \u0013*\u0004\u0018\u00010\u00190\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!¨\u0006&"}, d2 = {"Lcom/example/shellshot/service/ScreenStateMonitor;", "", "context", "Landroid/content/Context;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "onChanged", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "reason", "Lcom/example/shellshot/service/ScreenStateSnapshot;", "snapshot", "", "<init>", "(Landroid/content/Context;Lcom/example/shellshot/utils/ShellLogger;Lkotlin/jvm/functions/Function2;)V", "powerManager", "Landroid/os/PowerManager;", "kotlin.jvm.PlatformType", "getPowerManager", "()Landroid/os/PowerManager;", "powerManager$delegate", "Lkotlin/Lazy;", "keyguardManager", "Landroid/app/KeyguardManager;", "getKeyguardManager", "()Landroid/app/KeyguardManager;", "keyguardManager$delegate", "registered", "", "receiver", "com/example/shellshot/service/ScreenStateMonitor$receiver$1", "Lcom/example/shellshot/service/ScreenStateMonitor$receiver$1;", "start", "stop", "currentSnapshot", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ScreenStateMonitor {

    @Deprecated
    public static final String TAG = "ScreenState";
    private final Context context;

    /* renamed from: keyguardManager$delegate, reason: from kotlin metadata */
    private final Lazy keyguardManager;
    private final ShellLogger logger;
    private final Function2<String, ScreenStateSnapshot, Unit> onChanged;

    /* renamed from: powerManager$delegate, reason: from kotlin metadata */
    private final Lazy powerManager;
    private final ScreenStateMonitor$receiver$1 receiver;
    private boolean registered;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.example.shellshot.service.ScreenStateMonitor$receiver$1] */
    public ScreenStateMonitor(Context context, ShellLogger logger, Function2<? super String, ? super ScreenStateSnapshot, Unit> onChanged) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        this.context = context;
        this.logger = logger;
        this.onChanged = onChanged;
        this.powerManager = LazyKt.lazy(new Function0() { // from class: com.example.shellshot.service.ScreenStateMonitor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ScreenStateMonitor.powerManager_delegate$lambda$0(ScreenStateMonitor.this);
            }
        });
        this.keyguardManager = LazyKt.lazy(new Function0() { // from class: com.example.shellshot.service.ScreenStateMonitor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ScreenStateMonitor.keyguardManager_delegate$lambda$1(ScreenStateMonitor.this);
            }
        });
        this.receiver = new BroadcastReceiver() { // from class: com.example.shellshot.service.ScreenStateMonitor$receiver$1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0040  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onReceive(android.content.Context r9, android.content.Intent r10) {
                /*
                    r8 = this;
                    if (r10 == 0) goto L7
                    java.lang.String r0 = r10.getAction()
                    goto L8
                L7:
                    r0 = 0
                L8:
                    if (r0 != 0) goto Lc
                    java.lang.String r0 = ""
                Lc:
                    int r1 = r0.hashCode()
                    switch(r1) {
                        case -2128145023: goto L2b;
                        case -1454123155: goto L20;
                        case 823795052: goto L14;
                        default: goto L13;
                    }
                L13:
                    goto L37
                L14:
                    java.lang.String r1 = "android.intent.action.USER_PRESENT"
                    boolean r1 = r0.equals(r1)
                    if (r1 != 0) goto L1d
                    goto L13
                L1d:
                    java.lang.String r1 = "USER_PRESENT"
                    goto L46
                L20:
                    java.lang.String r1 = "android.intent.action.SCREEN_ON"
                    boolean r1 = r0.equals(r1)
                    if (r1 == 0) goto L13
                    java.lang.String r1 = "SCREEN_ON"
                    goto L46
                L2b:
                    java.lang.String r1 = "android.intent.action.SCREEN_OFF"
                    boolean r1 = r0.equals(r1)
                    if (r1 != 0) goto L34
                    goto L13
                L34:
                    java.lang.String r1 = "SCREEN_OFF"
                    goto L46
                L37:
                    r1 = r0
                    java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                    boolean r2 = kotlin.text.StringsKt.isBlank(r1)
                    if (r2 == 0) goto L44
                    r1 = 0
                    java.lang.String r1 = "UNKNOWN"
                L44:
                    java.lang.String r1 = (java.lang.String) r1
                L46:
                    com.example.shellshot.service.ScreenStateMonitor r2 = com.example.shellshot.service.ScreenStateMonitor.this
                    com.example.shellshot.service.ScreenStateSnapshot r2 = r2.currentSnapshot()
                    com.example.shellshot.service.ScreenStateMonitor r3 = com.example.shellshot.service.ScreenStateMonitor.this
                    com.example.shellshot.utils.ShellLogger r3 = com.example.shellshot.service.ScreenStateMonitor.access$getLogger$p(r3)
                    boolean r4 = r2.getScreenOn()
                    boolean r5 = r2.getUserUnlocked()
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r7 = "屏幕状态变更 reason="
                    java.lang.StringBuilder r6 = r6.append(r7)
                    java.lang.StringBuilder r6 = r6.append(r1)
                    java.lang.String r7 = " screenOn="
                    java.lang.StringBuilder r6 = r6.append(r7)
                    java.lang.StringBuilder r4 = r6.append(r4)
                    java.lang.String r6 = " unlocked="
                    java.lang.StringBuilder r4 = r4.append(r6)
                    java.lang.StringBuilder r4 = r4.append(r5)
                    java.lang.String r4 = r4.toString()
                    java.lang.String r5 = "ScreenState"
                    r3.d(r5, r4)
                    com.example.shellshot.service.ScreenStateMonitor r3 = com.example.shellshot.service.ScreenStateMonitor.this
                    kotlin.jvm.functions.Function2 r3 = com.example.shellshot.service.ScreenStateMonitor.access$getOnChanged$p(r3)
                    r3.invoke(r1, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.ScreenStateMonitor$receiver$1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
    }

    private final PowerManager getPowerManager() {
        return (PowerManager) this.powerManager.getValue();
    }

    static final PowerManager powerManager_delegate$lambda$0(ScreenStateMonitor this$0) {
        return (PowerManager) this$0.context.getSystemService(PowerManager.class);
    }

    private final KeyguardManager getKeyguardManager() {
        return (KeyguardManager) this.keyguardManager.getValue();
    }

    static final KeyguardManager keyguardManager_delegate$lambda$1(ScreenStateMonitor this$0) {
        return (KeyguardManager) this$0.context.getSystemService(KeyguardManager.class);
    }

    public final void start() {
        if (this.registered) {
            return;
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        this.context.registerReceiver(this.receiver, filter, 4);
        this.registered = true;
        ScreenStateSnapshot snapshot = currentSnapshot();
        this.logger.d(TAG, "屏幕状态监听已启动 screenOn=" + snapshot.getScreenOn() + " unlocked=" + snapshot.getUserUnlocked());
        this.onChanged.invoke("initial", snapshot);
    }

    public final void stop() {
        if (!this.registered) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            ScreenStateMonitor screenStateMonitor = this;
            screenStateMonitor.context.unregisterReceiver(screenStateMonitor.receiver);
            Result.m7216constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m7216constructorimpl(ResultKt.createFailure(th));
        }
        this.registered = false;
        this.logger.d(TAG, "屏幕状态监听已停止");
    }

    public final ScreenStateSnapshot currentSnapshot() {
        boolean screenOn = getPowerManager().isInteractive();
        boolean userUnlocked = !getKeyguardManager().isDeviceLocked();
        return new ScreenStateSnapshot(screenOn, userUnlocked);
    }

    /* compiled from: ScreenStateMonitor.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/service/ScreenStateMonitor$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
