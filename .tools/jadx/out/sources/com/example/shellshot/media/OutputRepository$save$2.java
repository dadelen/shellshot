package com.example.shellshot.media;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.shellshot.data.OutputNamingStrategy;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OutputRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/media/OutputWriteResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.OutputRepository$save$2", f = "OutputRepository.kt", i = {0, 0}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX}, m = "invokeSuspend", n = {"outputDirectory", "outputFile"}, s = {"L$0", "L$1"})
/* loaded from: classes11.dex */
final class OutputRepository$save$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super OutputWriteResult>, Object> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ OutputNamingStrategy $namingStrategy;
    final /* synthetic */ ScreenshotCandidate $sourceCandidate;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OutputRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OutputRepository$save$2(OutputRepository outputRepository, ScreenshotCandidate screenshotCandidate, OutputNamingStrategy outputNamingStrategy, Bitmap bitmap, Continuation<? super OutputRepository$save$2> continuation) {
        super(2, continuation);
        this.this$0 = outputRepository;
        this.$sourceCandidate = screenshotCandidate;
        this.$namingStrategy = outputNamingStrategy;
        this.$bitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OutputRepository$save$2(this.this$0, this.$sourceCandidate, this.$namingStrategy, this.$bitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super OutputWriteResult> continuation) {
        return ((OutputRepository$save$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        File outputFile;
        Object scanFile;
        File outputFile2;
        ShellLogger shellLogger;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                File outputDirectory = ScreenshotDirectories.INSTANCE.outputDirectory();
                if (outputDirectory.exists() || outputDirectory.mkdirs()) {
                    outputFile = this.this$0.buildUniqueOutputFile(outputDirectory, this.$sourceCandidate.getDisplayName(), this.$namingStrategy);
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                    try {
                        if (this.$bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream, null);
                            this.L$0 = SpillingKt.nullOutSpilledVariable(outputDirectory);
                            this.L$1 = outputFile;
                            this.label = 1;
                            scanFile = this.this$0.scanFile(outputFile, this);
                            if (scanFile == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            outputFile2 = outputFile;
                            break;
                        } else {
                            throw new IllegalStateException(("Bitmap compression failed for " + outputFile.getAbsolutePath()).toString());
                        }
                    } finally {
                    }
                } else {
                    throw new IllegalStateException(("Unable to create output directory: " + outputDirectory.getAbsolutePath()).toString());
                }
                break;
            case 1:
                outputFile2 = (File) this.L$1;
                ResultKt.throwOnFailure($result);
                scanFile = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Uri scannedUri = (Uri) scanFile;
        shellLogger = this.this$0.logger;
        shellLogger.d(OutputRepository.TAG, "成品已保存 source=" + this.$sourceCandidate.getAbsolutePath() + " output=" + outputFile2.getAbsolutePath() + " relativePath=Pictures/AutoShell/");
        String absolutePath = outputFile2.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return new OutputWriteResult(absolutePath, scannedUri);
    }
}
