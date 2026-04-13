package com.example.shellshot.template;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/template/TemplateImportDraft;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$prepareTemplateImportDraft$2$1", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$prepareTemplateImportDraft$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateImportDraft>, Object> {
    final /* synthetic */ Uri $imageUri;
    final /* synthetic */ String $templateNameOverride;
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$prepareTemplateImportDraft$2$1(TemplateRepository templateRepository, Uri uri, String str, Continuation<? super TemplateRepository$prepareTemplateImportDraft$2$1> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
        this.$imageUri = uri;
        this.$templateNameOverride = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateRepository$prepareTemplateImportDraft$2$1(this.this$0, this.$imageUri, this.$templateNameOverride, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateImportDraft> continuation) {
        return ((TemplateRepository$prepareTemplateImportDraft$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        File sourceFile;
        TemplateCalibrationDraft calibrationDraft;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sourceFile = this.this$0.stageSourceImage(this.$imageUri, this.$templateNameOverride);
                TemplateRepository templateRepository = this.this$0;
                String absolutePath = sourceFile.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                calibrationDraft = templateRepository.createDraftFromStagedPath(absolutePath, this.$templateNameOverride);
                String absolutePath2 = sourceFile.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                String templateName = calibrationDraft.getTemplateName();
                String detectionSummary = calibrationDraft.getDetectionSummary();
                if (!(StringsKt.contains$default((CharSequence) detectionSummary, (CharSequence) "保底", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) detectionSummary, (CharSequence) "未识别", false, 2, (Object) null))) {
                    detectionSummary = null;
                }
                return new TemplateImportDraft(absolutePath2, templateName, detectionSummary);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
