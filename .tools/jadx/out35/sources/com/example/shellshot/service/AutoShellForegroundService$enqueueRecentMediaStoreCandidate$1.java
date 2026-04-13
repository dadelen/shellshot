package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService", f = "AutoShellForegroundService.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2}, l = {667, 668, 687}, m = "enqueueRecentMediaStoreCandidate", n = {"reason", "changedUri", "reason", "changedUri", "screenshotRelativePath", "reason", "changedUri", "screenshotRelativePath", "candidates", "candidate"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$enqueueRecentMediaStoreCandidate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$enqueueRecentMediaStoreCandidate$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$enqueueRecentMediaStoreCandidate$1> continuation) {
        super(continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object enqueueRecentMediaStoreCandidate;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        enqueueRecentMediaStoreCandidate = this.this$0.enqueueRecentMediaStoreCandidate(null, null, this);
        return enqueueRecentMediaStoreCandidate;
    }
}
