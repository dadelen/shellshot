package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService", f = "AutoShellForegroundService.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {882, 305}, m = "evaluateModeAndApply", n = {"reason", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\304", "reason", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\304", "$i$a$-withLock$default-AutoShellForegroundService$evaluateModeAndApply$2\\2\\884\\0"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$evaluateModeAndApply$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$evaluateModeAndApply$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$evaluateModeAndApply$1> continuation) {
        super(continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object evaluateModeAndApply;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        evaluateModeAndApply = this.this$0.evaluateModeAndApply(null, this);
        return evaluateModeAndApply;
    }
}
