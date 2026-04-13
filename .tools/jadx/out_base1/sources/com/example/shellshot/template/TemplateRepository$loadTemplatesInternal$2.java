package com.example.shellshot.template;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/shellshot/template/ShellTemplate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$loadTemplatesInternal$2", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$loadTemplatesInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ShellTemplate>>, Object> {
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$loadTemplatesInternal$2(TemplateRepository templateRepository, Continuation<? super TemplateRepository$loadTemplatesInternal$2> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateRepository$loadTemplatesInternal$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ShellTemplate>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<ShellTemplate>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<ShellTemplate>> continuation) {
        return ((TemplateRepository$loadTemplatesInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        List loadBuiltInTemplates;
        List loadUserTemplates;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                loadBuiltInTemplates = this.this$0.loadBuiltInTemplates();
                loadUserTemplates = this.this$0.loadUserTemplates();
                return CollectionsKt.plus((Collection) loadBuiltInTemplates, (Iterable) loadUserTemplates);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
