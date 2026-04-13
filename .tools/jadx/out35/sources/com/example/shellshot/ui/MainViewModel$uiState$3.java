package com.example.shellshot.ui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/ui/MainUiState;", "state", "templateImportAlert", "Lcom/example/shellshot/ui/TemplateImportAlert;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$uiState$3", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$uiState$3 extends SuspendLambda implements Function3<MainUiState, TemplateImportAlert, Continuation<? super MainUiState>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    MainViewModel$uiState$3(Continuation<? super MainViewModel$uiState$3> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(MainUiState mainUiState, TemplateImportAlert templateImportAlert, Continuation<? super MainUiState> continuation) {
        MainViewModel$uiState$3 mainViewModel$uiState$3 = new MainViewModel$uiState$3(continuation);
        mainViewModel$uiState$3.L$0 = mainUiState;
        mainViewModel$uiState$3.L$1 = templateImportAlert;
        return mainViewModel$uiState$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MainUiState state = (MainUiState) this.L$0;
        TemplateImportAlert templateImportAlert = (TemplateImportAlert) this.L$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return MainUiState.copy$default(state, null, null, false, null, null, null, false, false, null, false, false, false, false, null, null, null, false, templateImportAlert, null, null, false, null, 4063231, null);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
