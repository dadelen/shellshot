package com.example.shellshot.template;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TemplateRepository.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository", f = "TemplateRepository.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {643, 78}, m = "prepareTemplateImportDraft", n = {"imageUri", "templateNameOverride", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\77", "imageUri", "templateNameOverride", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\77", "$i$a$-withLock$default-TemplateRepository$prepareTemplateImportDraft$2\\2\\645\\0"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0", "I$1"})
/* loaded from: classes9.dex */
final class TemplateRepository$prepareTemplateImportDraft$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$prepareTemplateImportDraft$1(TemplateRepository templateRepository, Continuation<? super TemplateRepository$prepareTemplateImportDraft$1> continuation) {
        super(continuation);
        this.this$0 = templateRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.prepareTemplateImportDraft(null, null, this);
    }
}
