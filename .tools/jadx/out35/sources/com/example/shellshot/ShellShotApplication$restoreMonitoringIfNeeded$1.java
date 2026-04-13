package com.example.shellshot;

import android.content.Context;
import com.example.shellshot.data.AppSettings;
import com.example.shellshot.utils.ServiceController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ShellShotApplication.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ShellShotApplication$restoreMonitoringIfNeeded$1", f = "ShellShotApplication.kt", i = {}, l = {22}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class ShellShotApplication$restoreMonitoringIfNeeded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ShellShotApplication this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShellShotApplication$restoreMonitoringIfNeeded$1(ShellShotApplication shellShotApplication, Continuation<? super ShellShotApplication$restoreMonitoringIfNeeded$1> continuation) {
        super(2, continuation);
        this.this$0 = shellShotApplication;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShellShotApplication$restoreMonitoringIfNeeded$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShellShotApplication$restoreMonitoringIfNeeded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object currentSettings;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                currentSettings = this.this$0.getAppContainer().getAppPrefs().currentSettings(this);
                if (currentSettings == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                currentSettings = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AppSettings settings = (AppSettings) currentSettings;
        if (settings.getMonitoringEnabled() && this.this$0.getAppContainer().getPermissionManager().hasCoreMonitoringPermissions()) {
            this.this$0.getAppContainer().getLogger().d(ShellShotApplication.TAG, "Application cold start restoring auto shell service");
            ServiceController serviceController = this.this$0.getAppContainer().getServiceController();
            Context applicationContext = this.this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            serviceController.start(applicationContext);
        }
        return Unit.INSTANCE;
    }
}
