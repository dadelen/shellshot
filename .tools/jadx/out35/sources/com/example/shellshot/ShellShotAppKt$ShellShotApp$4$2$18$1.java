package com.example.shellshot;

import com.example.shellshot.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: ShellShotApp.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class ShellShotAppKt$ShellShotApp$4$2$18$1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    ShellShotAppKt$ShellShotApp$4$2$18$1(Object obj) {
        super(1, obj, MainViewModel.class, "setMediaStoreFallbackEnabled", "setMediaStoreFallbackEnabled(Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean p0) {
        ((MainViewModel) this.receiver).setMediaStoreFallbackEnabled(p0);
    }
}
