package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.service.AutoShellForegroundService", f = "AutoShellForegroundService.kt", i = {}, l = {870}, m = "currentScreenshotRelativePath", n = {}, s = {})
/* loaded from: classes4.dex */
final class AutoShellForegroundService$currentScreenshotRelativePath$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AutoShellForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoShellForegroundService$currentScreenshotRelativePath$1(AutoShellForegroundService autoShellForegroundService, Continuation<? super AutoShellForegroundService$currentScreenshotRelativePath$1> continuation) {
        super(continuation);
        this.this$0 = autoShellForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object currentScreenshotRelativePath;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        currentScreenshotRelativePath = this.this$0.currentScreenshotRelativePath(this);
        return currentScreenshotRelativePath;
    }
}
