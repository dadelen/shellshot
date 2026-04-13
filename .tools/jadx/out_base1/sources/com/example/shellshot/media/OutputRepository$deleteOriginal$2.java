package com.example.shellshot.media;

import android.content.ContentResolver;
import android.net.Uri;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
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

/* compiled from: OutputRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/media/DeleteResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.OutputRepository$deleteOriginal$2", f = "OutputRepository.kt", i = {0, 0, 0, 0}, l = {65}, m = "invokeSuspend", n = {"$this$withContext", "sourceFile", "$this$invokeSuspend_u24lambda_u240\\1", "$i$a$-runCatching-OutputRepository$deleteOriginal$2$1\\1\\63\\0"}, s = {"L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes11.dex */
final class OutputRepository$deleteOriginal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DeleteResult>, Object> {
    final /* synthetic */ ScreenshotCandidate $candidate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OutputRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OutputRepository$deleteOriginal$2(ScreenshotCandidate screenshotCandidate, OutputRepository outputRepository, Continuation<? super OutputRepository$deleteOriginal$2> continuation) {
        super(2, continuation);
        this.$candidate = screenshotCandidate;
        this.this$0 = outputRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OutputRepository$deleteOriginal$2 outputRepository$deleteOriginal$2 = new OutputRepository$deleteOriginal$2(this.$candidate, this.this$0, continuation);
        outputRepository$deleteOriginal$2.L$0 = obj;
        return outputRepository$deleteOriginal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DeleteResult> continuation) {
        return ((OutputRepository$deleteOriginal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7216constructorimpl;
        Object m7216constructorimpl2;
        ShellLogger shellLogger;
        ContentResolver contentResolver;
        ShellLogger shellLogger2;
        Object scanFile;
        CoroutineScope $this$withContext = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m7216constructorimpl = Result.m7216constructorimpl(ResultKt.createFailure(th));
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                File sourceFile = new File(this.$candidate.getAbsolutePath());
                OutputRepository outputRepository = this.this$0;
                Result.Companion companion2 = Result.INSTANCE;
                if (!sourceFile.exists() || !sourceFile.delete()) {
                    m7216constructorimpl = Result.m7216constructorimpl(Unit.INSTANCE);
                    OutputRepository outputRepository2 = this.this$0;
                    ScreenshotCandidate screenshotCandidate = this.$candidate;
                    Throwable m7219exceptionOrNullimpl = Result.m7219exceptionOrNullimpl(m7216constructorimpl);
                    if (m7219exceptionOrNullimpl != null) {
                        shellLogger2 = outputRepository2.logger;
                        shellLogger2.e(OutputRepository.TAG, "文件系统删除原图失败 path=" + screenshotCandidate.getAbsolutePath(), m7219exceptionOrNullimpl);
                    }
                    Uri uri = this.$candidate.getUri();
                    if (uri != null) {
                        OutputRepository outputRepository3 = this.this$0;
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            contentResolver = outputRepository3.contentResolver;
                            m7216constructorimpl2 = Result.m7216constructorimpl(Boxing.boxInt(contentResolver.delete(uri, null, null)));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            m7216constructorimpl2 = Result.m7216constructorimpl(ResultKt.createFailure(th2));
                        }
                        Throwable m7219exceptionOrNullimpl2 = Result.m7219exceptionOrNullimpl(m7216constructorimpl2);
                        if (m7219exceptionOrNullimpl2 != null) {
                            shellLogger = outputRepository3.logger;
                            shellLogger.e(OutputRepository.TAG, "MediaStore 删除原图失败 uri=" + uri, m7219exceptionOrNullimpl2);
                        }
                        Integer boxInt = Boxing.boxInt(0);
                        if (Result.m7222isFailureimpl(m7216constructorimpl2)) {
                            m7216constructorimpl2 = boxInt;
                        }
                        if (((Number) m7216constructorimpl2).intValue() > 0) {
                            return new DeleteResult(true, "原图已通过 MediaStore 删除");
                        }
                    }
                    return new DeleteResult(false, "原图未删除");
                }
                this.L$0 = $this$withContext;
                this.L$1 = SpillingKt.nullOutSpilledVariable(sourceFile);
                this.L$2 = SpillingKt.nullOutSpilledVariable($this$withContext);
                this.I$0 = 0;
                this.label = 1;
                scanFile = outputRepository.scanFile(sourceFile, this);
                if (scanFile == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return new DeleteResult(true, "原图已通过文件系统删除");
            case 1:
                int i = this.I$0;
                ResultKt.throwOnFailure($result);
                return new DeleteResult(true, "原图已通过文件系统删除");
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
