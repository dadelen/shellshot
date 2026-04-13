package com.example.shellshot;

import com.example.shellshot.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShellShotApp.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class ShellShotAppKt$ShellShotApp$4$2$4$1 extends FunctionReferenceImpl implements Function1<String, Unit> {
    ShellShotAppKt$ShellShotApp$4$2$4$1(Object obj) {
        super(1, obj, MainViewModel.class, "deleteTemplate", "deleteTemplate(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((MainViewModel) this.receiver).deleteTemplate(p0);
    }
}
