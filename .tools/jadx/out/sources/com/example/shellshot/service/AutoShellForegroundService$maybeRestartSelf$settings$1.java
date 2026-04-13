package com.example.shellshot.service;

import com.example.shellshot.data.AppContainer;
import com.example.shellshot.data.AppSettings;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/data/AppSettings;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$maybeRestartSelf$settings$1", f = "AutoShellForegroundService.kt", i = {}, l = {712}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$maybeRestartSelf$settings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AppSettings>, Object> {
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$maybeRestartSelf$settings$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$maybeRestartSelf$settings$1> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoShellForegroundService$maybeRestartSelf$settings$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AppSettings> continuation) {
        return ((AutoShellForegroundService$maybeRestartSelf$settings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AppContainer appContainer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                appContainer = this.this$0.getAppContainer();
                this.label = 1;
                Object currentSettings = appContainer.getAppPrefs().currentSettings(this);
                return currentSettings == coroutine_suspended ? coroutine_suspended : currentSettings;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
