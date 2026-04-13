package com.example.shellshot.service;

import com.example.shellshot.observer.DirectoryWatchEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoShellForegroundService.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
final /* synthetic */ class AutoShellForegroundService$registerPrimaryDirectoryWatcher$observer$1 extends FunctionReferenceImpl implements Function1<DirectoryWatchEvent, Unit> {
    AutoShellForegroundService$registerPrimaryDirectoryWatcher$observer$1(Object obj) {
        super(1, obj, AutoShellForegroundService.class, "onDirectoryEvent", "onDirectoryEvent(Lcom/example/shellshot/observer/DirectoryWatchEvent;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(DirectoryWatchEvent directoryWatchEvent) {
        invoke2(directoryWatchEvent);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(DirectoryWatchEvent p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((AutoShellForegroundService) this.receiver).onDirectoryEvent(p0);
    }
}
