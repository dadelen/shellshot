package com.example.shellshot.ui;

import android.net.Uri;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.template.TemplateImportDraft;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$prepareTemplateImport$1", f = "MainViewModel.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$prepareTemplateImport$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $defaultName;
    final /* synthetic */ Uri $imageUri;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$prepareTemplateImport$1(MainViewModel mainViewModel, Uri uri, String str, Continuation<? super MainViewModel$prepareTemplateImport$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$imageUri = uri;
        this.$defaultName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$prepareTemplateImport$1(this.this$0, this.$imageUri, this.$defaultName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$prepareTemplateImport$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        MutableStateFlow mutableStateFlow3;
        String mapTemplateImportMessage;
        AppContainer appContainer;
        Object prepareTemplateImportDraft;
        MutableStateFlow mutableStateFlow4;
        MutableStateFlow mutableStateFlow5;
        MutableStateFlow mutableStateFlow6;
        MutableStateFlow mutableStateFlow7;
        String mapTemplateImportMessage2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    appContainer = this.this$0.appContainer;
                    this.label = 1;
                    prepareTemplateImportDraft = appContainer.getTemplateRepository().prepareTemplateImportDraft(this.$imageUri, this.$defaultName, this);
                    if (prepareTemplateImportDraft == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    prepareTemplateImportDraft = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            TemplateImportDraft draft = (TemplateImportDraft) prepareTemplateImportDraft;
            String validationWarning = draft.getValidationWarning();
            MainViewModel mainViewModel = this.this$0;
            if (validationWarning != null) {
                mutableStateFlow6 = mainViewModel.templateImportDraftState;
                mutableStateFlow6.setValue(null);
                mutableStateFlow7 = this.this$0.templateImportAlertState;
                mapTemplateImportMessage2 = this.this$0.mapTemplateImportMessage(validationWarning);
                mutableStateFlow7.setValue(new TemplateImportAlert("妯℃澘涓嶇\ue0c1鍚堣\ue6e6姹?", mapTemplateImportMessage2));
            } else {
                mutableStateFlow4 = mainViewModel.templateImportDraftState;
                mutableStateFlow4.setValue(draft);
                mutableStateFlow5 = this.this$0.templateImportAlertState;
                mutableStateFlow5.setValue(null);
            }
        } catch (Throwable throwable) {
            try {
                if (!(throwable instanceof CancellationException)) {
                    mutableStateFlow2 = this.this$0.templateImportDraftState;
                    mutableStateFlow2.setValue(null);
                    mutableStateFlow3 = this.this$0.templateImportAlertState;
                    mapTemplateImportMessage = this.this$0.mapTemplateImportMessage(throwable.getMessage());
                    mutableStateFlow3.setValue(new TemplateImportAlert("鏃犳硶瀵煎叆妯℃澘", mapTemplateImportMessage));
                } else {
                    throw throwable;
                }
            } finally {
                mutableStateFlow = this.this$0.templateImportInProgressState;
                mutableStateFlow.setValue(Boxing.boxBoolean(false));
            }
        }
        return Unit.INSTANCE;
    }
}
