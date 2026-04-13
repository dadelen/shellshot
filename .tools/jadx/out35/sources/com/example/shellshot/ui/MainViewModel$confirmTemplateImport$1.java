package com.example.shellshot.ui;

import com.example.shellshot.data.AppContainer;
import com.example.shellshot.template.TemplateImportDraft;
import com.example.shellshot.template.TemplateImportResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$confirmTemplateImport$1", f = "MainViewModel.kt", i = {1}, l = {187, 195}, m = "invokeSuspend", n = {"result"}, s = {"L$0"})
/* loaded from: classes7.dex */
final class MainViewModel$confirmTemplateImport$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TemplateImportDraft $draft;
    final /* synthetic */ String $trimmedName;
    Object L$0;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$confirmTemplateImport$1(MainViewModel mainViewModel, TemplateImportDraft templateImportDraft, String str, Continuation<? super MainViewModel$confirmTemplateImport$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$draft = templateImportDraft;
        this.$trimmedName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$confirmTemplateImport$1(this.this$0, this.$draft, this.$trimmedName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$confirmTemplateImport$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableStateFlow mutableStateFlow;
        AppContainer appContainer;
        Object importPreparedTemplate;
        TemplateImportResult result;
        MutableStateFlow mutableStateFlow2;
        String mapTemplateImportMessage;
        MutableStateFlow mutableStateFlow3;
        MutableStateFlow mutableStateFlow4;
        AppContainer appContainer2;
        MutableStateFlow mutableStateFlow5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    appContainer = this.this$0.appContainer;
                    this.label = 1;
                    importPreparedTemplate = appContainer.getTemplateRepository().importPreparedTemplate(this.$draft.getSourceImagePath(), this.$trimmedName, this);
                    if (importPreparedTemplate == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    result = (TemplateImportResult) importPreparedTemplate;
                    if (result.getSuccess() || result.getTemplateId() == null) {
                        mutableStateFlow2 = this.this$0.templateImportAlertState;
                        mapTemplateImportMessage = this.this$0.mapTemplateImportMessage(result.getMessage());
                        mutableStateFlow2.setValue(new TemplateImportAlert("妯℃澘鐢熸垚澶辫触", mapTemplateImportMessage));
                        mutableStateFlow5 = this.this$0.templateImportInProgressState;
                        mutableStateFlow5.setValue(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    }
                    mutableStateFlow3 = this.this$0.templateImportDraftState;
                    mutableStateFlow3.setValue(null);
                    mutableStateFlow4 = this.this$0.templateImportAlertState;
                    mutableStateFlow4.setValue(null);
                    this.this$0.reloadTemplates(result.getTemplateId());
                    appContainer2 = this.this$0.appContainer;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(result);
                    this.label = 2;
                    if (appContainer2.getAppPrefs().updateSelectedTemplate(result.getTemplateId(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutableStateFlow5 = this.this$0.templateImportInProgressState;
                    mutableStateFlow5.setValue(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    importPreparedTemplate = $result;
                    result = (TemplateImportResult) importPreparedTemplate;
                    if (result.getSuccess()) {
                        break;
                    }
                    mutableStateFlow2 = this.this$0.templateImportAlertState;
                    mapTemplateImportMessage = this.this$0.mapTemplateImportMessage(result.getMessage());
                    mutableStateFlow2.setValue(new TemplateImportAlert("妯℃澘鐢熸垚澶辫触", mapTemplateImportMessage));
                    mutableStateFlow5 = this.this$0.templateImportInProgressState;
                    mutableStateFlow5.setValue(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    mutableStateFlow5 = this.this$0.templateImportInProgressState;
                    mutableStateFlow5.setValue(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            mutableStateFlow = this.this$0.templateImportInProgressState;
            mutableStateFlow.setValue(Boxing.boxBoolean(false));
            throw th;
        }
    }
}
