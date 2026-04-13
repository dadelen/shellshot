package com.example.shellshot.service;

import androidx.constraintlayout.solver.widgets.Optimizer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1", f = "AutoShellForegroundService.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 3, 3}, l = {227, 238, 243, Optimizer.OPTIMIZATION_STANDARD}, m = "invokeSuspend", n = {"$this$launch", "snapshot", "$this$launch", "snapshot", "$this$launch", "snapshot", "$this$launch", "snapshot", "settings", "foregroundSnapshot"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1 autoShellForegroundService$startForegroundAppMonitorIfNeeded$1 = new AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1(this.this$0, continuation);
        autoShellForegroundService$startForegroundAppMonitorIfNeeded$1.L$0 = obj;
        return autoShellForegroundService$startForegroundAppMonitorIfNeeded$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0161, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r10, r11) == false) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x01d4 -> B:7:0x01d8). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService$startForegroundAppMonitorIfNeeded$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
