package com.example.shellshot.service;

import com.example.shellshot.service.AutoShellForegroundService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService$scheduleResumeRecovery$1", f = "AutoShellForegroundService.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {590, 610, 626}, m = "invokeSuspend", n = {"burstStartedAtElapsedRealtime", "resumeStartedAtWallClockMillis", "minLastModifiedMillis", "index", "targetOffsetMillis", "sleepMillis", "runtimeState", "stopReason", "burstStartedAtElapsedRealtime", "resumeStartedAtWallClockMillis", "minLastModifiedMillis", "index", "targetOffsetMillis", "sleepMillis", "roundNowMillis", "runtimeState", "stopReason", "screenshotRelativePath", "recentFiles", "acceptedAny", "acceptedFreshEnough", "$this$forEach\\1", "element\\1", "file\\2", "burstStartedAtElapsedRealtime", "resumeStartedAtWallClockMillis", "minLastModifiedMillis", "index", "targetOffsetMillis", "sleepMillis", "roundNowMillis", "$i$f$forEach\\1\\625", "$i$a$-forEach-AutoShellForegroundService$scheduleResumeRecovery$1$1\\2\\878\\0"}, s = {"J$0", "J$1", "J$2", "I$2", "J$3", "J$4", "L$1", "L$2", "J$0", "J$1", "J$2", "I$2", "J$3", "J$4", "J$5", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$11", "L$12", "J$0", "J$1", "J$2", "I$2", "J$3", "J$4", "J$5", "I$3", "I$4"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$scheduleResumeRecovery$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AutoShellForegroundService.ResumeRecoveryPlan $plan;
    final /* synthetic */ String $reason;
    final /* synthetic */ AutoShellForegroundService.ResumeRecoveryTrigger $trigger;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    long J$0;
    long J$1;
    long J$2;
    long J$3;
    long J$4;
    long J$5;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$scheduleResumeRecovery$1(AutoShellForegroundService.ResumeRecoveryPlan resumeRecoveryPlan, AutoShellForegroundService autoShellForegroundService, AutoShellForegroundService.ResumeRecoveryTrigger resumeRecoveryTrigger, String str, Continuation<? super AutoShellForegroundService$scheduleResumeRecovery$1> continuation) {
        super(2, continuation);
        this.$plan = resumeRecoveryPlan;
        this.this$0 = autoShellForegroundService;
        this.$trigger = resumeRecoveryTrigger;
        this.$reason = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoShellForegroundService$scheduleResumeRecovery$1(this.$plan, this.this$0, this.$trigger, this.$reason, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoShellForegroundService$scheduleResumeRecovery$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x068d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0515  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x04ed -> B:7:0x0505). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r60) {
        /*
            Method dump skipped, instructions count: 1738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.service.AutoShellForegroundService$scheduleResumeRecovery$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
