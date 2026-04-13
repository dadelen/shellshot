package com.example.shellshot.template;

import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/template/TemplateDeleteResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.template.TemplateRepository$deleteUserTemplate$2$result$1", f = "TemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TemplateRepository$deleteUserTemplate$2$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateDeleteResult>, Object> {
    final /* synthetic */ String $templateId;
    int label;
    final /* synthetic */ TemplateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TemplateRepository$deleteUserTemplate$2$result$1(TemplateRepository templateRepository, String str, Continuation<? super TemplateRepository$deleteUserTemplate$2$result$1> continuation) {
        super(2, continuation);
        this.this$0 = templateRepository;
        this.$templateId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TemplateRepository$deleteUserTemplate$2$result$1(this.this$0, this.$templateId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateDeleteResult> continuation) {
        return ((TemplateRepository$deleteUserTemplate$2$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        File userTemplatesRoot;
        File userTemplatesRoot2;
        ShellLogger shellLogger;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                userTemplatesRoot = this.this$0.userTemplatesRoot();
                File targetDirectory = new File(userTemplatesRoot, this.$templateId);
                if (!targetDirectory.exists()) {
                    return new TemplateDeleteResult(false, null, "模板不存在", 2, null);
                }
                String absolutePath = targetDirectory.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                userTemplatesRoot2 = this.this$0.userTemplatesRoot();
                String absolutePath2 = userTemplatesRoot2.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                if (!StringsKt.startsWith(absolutePath, absolutePath2, true)) {
                    return new TemplateDeleteResult(false, null, "模板路径无效", 2, null);
                }
                if (FilesKt.deleteRecursively(targetDirectory)) {
                    shellLogger = this.this$0.logger;
                    shellLogger.d(TemplateRepository.TAG, "已删除用户模板 id=" + this.$templateId + " path=" + targetDirectory.getAbsolutePath());
                    return new TemplateDeleteResult(true, this.$templateId, "模板已删除");
                }
                return new TemplateDeleteResult(false, null, "删除模板失败", 2, null);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
