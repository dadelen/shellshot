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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/template/TemplateImportResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$importPreparedTemplate$2$result$1", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$importPreparedTemplate$2$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateImportResult>, Object> {
    final /* synthetic */ String $sourceImagePath;
    final /* synthetic */ String $templateNameOverride;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$importPreparedTemplate$2$result$1(TemplateRepository templateRepository, String str, String str2, Continuation<? super TemplateRepository$importPreparedTemplate$2$result$1> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
        this.$sourceImagePath = str;
        this.$templateNameOverride = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TemplateRepository$importPreparedTemplate$2$result$1 templateRepository$importPreparedTemplate$2$result$1 = new TemplateRepository$importPreparedTemplate$2$result$1(this.this$0, this.$sourceImagePath, this.$templateNameOverride, continuation);
        templateRepository$importPreparedTemplate$2$result$1.L$0 = obj;
        return templateRepository$importPreparedTemplate$2$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateImportResult> continuation) {
        return ((TemplateRepository$importPreparedTemplate$2$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7212constructorimpl;
        ShellLogger shellLogger;
        TemplateCalibrationDraft createDraftFromStagedPath;
        TemplateImportResult persistDraft;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TemplateRepository templateRepository = this.this$0;
                String str = this.$sourceImagePath;
                String str2 = this.$templateNameOverride;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    createDraftFromStagedPath = templateRepository.createDraftFromStagedPath(str, str2);
                    persistDraft = templateRepository.persistDraft(createDraftFromStagedPath);
                    m7212constructorimpl = Result.m7212constructorimpl(persistDraft);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
                }
                TemplateRepository templateRepository2 = this.this$0;
                Throwable m7215exceptionOrNullimpl = Result.m7215exceptionOrNullimpl(m7212constructorimpl);
                if (m7215exceptionOrNullimpl != null) {
                    shellLogger = templateRepository2.logger;
                    shellLogger.e(TemplateRepository.TAG, "自动导入模板失败", m7215exceptionOrNullimpl);
                    String message = m7215exceptionOrNullimpl.getMessage();
                    return new TemplateImportResult(false, null, message == null ? "自动导入模板失败" : message, 2, null);
                }
                return m7212constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
