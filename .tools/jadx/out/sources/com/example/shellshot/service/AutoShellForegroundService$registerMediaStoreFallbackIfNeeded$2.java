package com.example.shellshot.service;

import android.net.Uri;
import com.example.shellshot.data.AppContainer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "changedUri", "Landroid/net/Uri;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2", f = "AutoShellForegroundService.kt", i = {0}, l = {452}, m = "invokeSuspend", n = {"changedUri"}, s = {"L$0"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2 extends SuspendLambda implements Function2<Uri, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2 autoShellForegroundService$registerMediaStoreFallbackIfNeeded$2 = new AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2(this.this$0, continuation);
        autoShellForegroundService$registerMediaStoreFallbackIfNeeded$2.L$0 = obj;
        return autoShellForegroundService$registerMediaStoreFallbackIfNeeded$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Uri uri, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$registerMediaStoreFallbackIfNeeded$2) create(uri, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AppContainer appContainer;
        Object enqueueRecentMediaStoreCandidate;
        AppContainer appContainer2;
        Uri changedUri = (Uri) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                appContainer = this.this$0.getAppContainer();
                AutoShellMode mode = appContainer.getAppStateStore().getRuntimeState().getValue().getMode();
                AutoShellMode autoShellMode = AutoShellMode.ACTIVE;
                AutoShellForegroundService autoShellForegroundService = this.this$0;
                if (mode != autoShellMode) {
                    appContainer2 = autoShellForegroundService.getAppContainer();
                    appContainer2.getLogger().d("AutoShellService", "跳过媒体库兜底 reason=mode_not_active");
                    return Unit.INSTANCE;
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(changedUri);
                this.label = 1;
                enqueueRecentMediaStoreCandidate = autoShellForegroundService.enqueueRecentMediaStoreCandidate("media_store_fallback", changedUri, this);
                if (enqueueRecentMediaStoreCandidate == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
