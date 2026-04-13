package com.example.shellshot.service;

import com.example.shellshot.data.AppContainer;
import com.example.shellshot.observer.DirectoryWatchEvent;
import com.example.shellshot.queue.EnqueueTaskResult;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$onDirectoryEvent$1", f = "AutoShellForegroundService.kt", i = {}, l = {484}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$onDirectoryEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DirectoryWatchEvent $event;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$onDirectoryEvent$1(AutoShellForegroundService autoShellForegroundService, DirectoryWatchEvent directoryWatchEvent, Continuation<? super AutoShellForegroundService$onDirectoryEvent$1> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
        this.$event = directoryWatchEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoShellForegroundService$onDirectoryEvent$1(this.this$0, this.$event, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$onDirectoryEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AppContainer appContainer;
        Object enqueueDetected;
        AppContainer appContainer2;
        AppContainer appContainer3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                appContainer = this.this$0.getAppContainer();
                this.label = 1;
                enqueueDetected = appContainer.getQueuedTaskStore().enqueueDetected(this.$event.getAbsolutePath(), this.$event.getFileName(), this.$event.getRelativePath(), null, "file_observer", this);
                if (enqueueDetected == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                enqueueDetected = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        EnqueueTaskResult result = (EnqueueTaskResult) enqueueDetected;
        boolean accepted = result.getAccepted();
        AutoShellForegroundService autoShellForegroundService = this.this$0;
        if (accepted) {
            appContainer3 = autoShellForegroundService.getAppContainer();
            appContainer3.getLogger().d("AutoShellService", "候选任务入队 source=file_observer path=" + this.$event.getAbsolutePath());
        } else {
            appContainer2 = autoShellForegroundService.getAppContainer();
            appContainer2.getLogger().d("AutoShellService", "跳过入队 reason=" + result.getReason() + " path=" + this.$event.getAbsolutePath());
        }
        return Unit.INSTANCE;
    }
}
