package com.example.shellshot.template;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/template/TemplateCalibrationDraft;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$beginImageCalibration$2$1", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$beginImageCalibration$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateCalibrationDraft>, Object> {
    final /* synthetic */ Uri $imageUri;
    final /* synthetic */ String $templateNameOverride;
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$beginImageCalibration$2$1(TemplateRepository templateRepository, Uri uri, String str, Continuation<? super TemplateRepository$beginImageCalibration$2$1> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
        this.$imageUri = uri;
        this.$templateNameOverride = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateRepository$beginImageCalibration$2$1(this.this$0, this.$imageUri, this.$templateNameOverride, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateCalibrationDraft> continuation) {
        return ((TemplateRepository$beginImageCalibration$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        TemplateCalibrationDraft createDraftFromImage;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                createDraftFromImage = this.this$0.createDraftFromImage(this.$imageUri, this.$templateNameOverride);
                return createDraftFromImage;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
