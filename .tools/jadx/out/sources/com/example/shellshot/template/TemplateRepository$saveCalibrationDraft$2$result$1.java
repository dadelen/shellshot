package com.example.shellshot.template;

import com.example.shellshot.utils.ShellLogger;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/template/TemplateCalibrationResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$saveCalibrationDraft$2$result$1", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$saveCalibrationDraft$2$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateCalibrationResult>, Object> {
    final /* synthetic */ TemplateCalibrationDraft $draft;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$saveCalibrationDraft$2$result$1(TemplateRepository templateRepository, TemplateCalibrationDraft templateCalibrationDraft, Continuation<? super TemplateRepository$saveCalibrationDraft$2$result$1> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
        this.$draft = templateCalibrationDraft;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TemplateRepository$saveCalibrationDraft$2$result$1 templateRepository$saveCalibrationDraft$2$result$1 = new TemplateRepository$saveCalibrationDraft$2$result$1(this.this$0, this.$draft, continuation);
        templateRepository$saveCalibrationDraft$2$result$1.L$0 = obj;
        return templateRepository$saveCalibrationDraft$2$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateCalibrationResult> continuation) {
        return ((TemplateRepository$saveCalibrationDraft$2$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7216constructorimpl;
        ShellLogger shellLogger;
        TemplateImportResult persistDraft;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TemplateRepository templateRepository = this.this$0;
                TemplateCalibrationDraft templateCalibrationDraft = this.$draft;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    persistDraft = templateRepository.persistDraft(templateCalibrationDraft);
                    m7216constructorimpl = Result.m7216constructorimpl(new TemplateCalibrationResult(persistDraft.getSuccess(), persistDraft.getTemplateId(), persistDraft.getMessage()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7216constructorimpl = Result.m7216constructorimpl(ResultKt.createFailure(th));
                }
                TemplateRepository templateRepository2 = this.this$0;
                Throwable m7219exceptionOrNullimpl = Result.m7219exceptionOrNullimpl(m7216constructorimpl);
                if (m7219exceptionOrNullimpl != null) {
                    shellLogger = templateRepository2.logger;
                    shellLogger.e(TemplateRepository.TAG, "保存模板失败", m7219exceptionOrNullimpl);
                    String message = m7219exceptionOrNullimpl.getMessage();
                    return new TemplateCalibrationResult(false, null, message == null ? "保存模板失败" : message, 2, null);
                }
                return m7216constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
