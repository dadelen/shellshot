package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1", f = "AutoShellForegroundService.kt", i = {1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4}, l = {525, 526, 531, 537, 548}, m = "invokeSuspend", n = {"recovered", "recovered", "recovered", "recovered", "screenshotRelativePath", "recentFiles", "$this$forEach\\1", "element\\1", "file\\2", "$i$f$forEach\\1\\547", "$i$a$-forEach-AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1$1\\2\\878\\0"}, s = {"L$0", "L$0", "L$0", "L$0", "L$1", "L$2", "L$3", "L$6", "L$7", "I$0", "I$1"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $reason;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1(AutoShellForegroundService autoShellForegroundService, String str, Continuation<? super AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1> continuation) {
        super(2, continuation);
        this.this$0 = autoShellForegroundService;
        this.$reason = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1(this.this$0, this.$reason, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00eb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0213  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0202 -> B:7:0x020b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService$scheduleStartupRecoveryIfNeeded$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
