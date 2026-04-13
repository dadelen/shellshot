package com.example.shellshot.service;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$startQueueWorkerIfNeeded$1", f = "AutoShellForegroundService.kt", i = {0, 1, 2, 2}, l = {507, FrameMetricsAggregator.EVERY_DURATION, InputDeviceCompat.SOURCE_DPAD}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch", "didWork"}, s = {"L$0", "L$0", "L$0", "Z$0"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$startQueueWorkerIfNeeded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$startQueueWorkerIfNeeded$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$startQueueWorkerIfNeeded$1> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AutoShellForegroundService$startQueueWorkerIfNeeded$1 autoShellForegroundService$startQueueWorkerIfNeeded$1 = new AutoShellForegroundService$startQueueWorkerIfNeeded$1(this.this$0, continuation);
        autoShellForegroundService$startQueueWorkerIfNeeded$1.L$0 = obj;
        return autoShellForegroundService$startQueueWorkerIfNeeded$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$startQueueWorkerIfNeeded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ad  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a6 -> B:7:0x00ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00ad -> B:8:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L29;
                case 2: goto L20;
                case 3: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            boolean r2 = r8.Z$0
            kotlin.ResultKt.throwOnFailure(r9)
            r3 = r1
            r1 = r0
            r0 = r9
            r9 = r8
            goto Lab
        L20:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r0
            r3 = r1
            r0 = r8
            r1 = r9
            goto L8b
        L29:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r8
            goto L6b
        L31:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r8
        L38:
            boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
            if (r3 == 0) goto Lb2
            com.example.shellshot.service.AutoShellForegroundService r3 = r9.this$0
            com.example.shellshot.data.AppContainer r3 = com.example.shellshot.service.AutoShellForegroundService.access$getAppContainer(r3)
            com.example.shellshot.data.AppStateStore r3 = r3.getAppStateStore()
            kotlinx.coroutines.flow.StateFlow r3 = r3.getRuntimeState()
            java.lang.Object r3 = r3.getValue()
            com.example.shellshot.data.AppRuntimeState r3 = (com.example.shellshot.data.AppRuntimeState) r3
            com.example.shellshot.service.AutoShellMode r3 = r3.getMode()
            com.example.shellshot.service.AutoShellMode r4 = com.example.shellshot.service.AutoShellMode.ACTIVE
            if (r3 == r4) goto L6c
            r3 = r9
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r9.L$0 = r1
            r4 = 1
            r9.label = r4
            r4 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r4, r3)
            if (r3 != r2) goto L6b
            return r2
        L6b:
            goto L38
        L6c:
            com.example.shellshot.service.AutoShellForegroundService r3 = r9.this$0
            com.example.shellshot.data.AppContainer r3 = com.example.shellshot.service.AutoShellForegroundService.access$getAppContainer(r3)
            com.example.shellshot.queue.QueuedScreenshotWorker r3 = r3.getQueuedScreenshotWorker()
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.L$0 = r1
            r5 = 2
            r9.label = r5
            java.lang.Object r3 = r3.runNextTask(r4)
            if (r3 != r2) goto L85
            return r2
        L85:
            r7 = r0
            r0 = r9
            r9 = r3
            r3 = r2
            r2 = r1
            r1 = r7
        L8b:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto Lad
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r0.L$0 = r2
            r0.Z$0 = r9
            r5 = 3
            r0.label = r5
            r5 = 350(0x15e, double:1.73E-321)
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r5, r4)
            if (r4 != r3) goto La6
            return r3
        La6:
            r7 = r2
            r2 = r9
            r9 = r0
            r0 = r1
            r1 = r7
        Lab:
            r2 = r3
            goto L38
        Lad:
            r9 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L38
        Lb2:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService$startQueueWorkerIfNeeded$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
