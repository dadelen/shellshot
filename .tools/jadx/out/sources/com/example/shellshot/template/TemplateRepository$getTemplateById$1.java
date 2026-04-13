package com.example.shellshot.template;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TemplateRepository.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository", f = "TemplateRepository.kt", i = {0}, l = {46}, m = "getTemplateById", n = {"templateId"}, s = {"L$0"})
/* loaded from: classes9.dex */
final class TemplateRepository$getTemplateById$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$getTemplateById$1(TemplateRepository templateRepository, Continuation<? super TemplateRepository$getTemplateById$1> continuation) {
        super(continuation);
        this.this$0 = templateRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getTemplateById(null, this);
    }
}
