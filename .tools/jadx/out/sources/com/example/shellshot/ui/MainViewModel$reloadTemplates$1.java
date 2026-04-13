package com.example.shellshot.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$reloadTemplates$1", f = "MainViewModel.kt", i = {1, 2, 2, 2}, l = {296, 299, 308}, m = "invokeSuspend", n = {"templates", "templates", "settings", "templateToSelect"}, s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes7.dex */
final class MainViewModel$reloadTemplates$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $preferredTemplateId;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$reloadTemplates$1(MainViewModel mainViewModel, String str, Continuation<? super MainViewModel$reloadTemplates$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$preferredTemplateId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$reloadTemplates$1(this.this$0, this.$preferredTemplateId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$reloadTemplates$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x006d A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.MainViewModel$reloadTemplates$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
