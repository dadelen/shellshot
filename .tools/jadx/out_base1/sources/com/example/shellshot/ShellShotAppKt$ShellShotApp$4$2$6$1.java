package com.example.shellshot;

import com.example.shellshot.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: ShellShotApp.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class ShellShotAppKt$ShellShotApp$4$2$6$1 extends FunctionReferenceImpl implements Function0<Unit> {
    ShellShotAppKt$ShellShotApp$4$2$6$1(Object obj) {
        super(0, obj, MainViewModel.class, "confirmTemplateImport", "confirmTemplateImport()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((MainViewModel) this.receiver).confirmTemplateImport();
    }
}
