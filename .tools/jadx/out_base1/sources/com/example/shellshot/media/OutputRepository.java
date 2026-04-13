package com.example.shellshot.media;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.example.shellshot.data.OutputNamingStrategy;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;

/* compiled from: OutputRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0016J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/example/shellshot/media/OutputRepository;", "", "context", "Landroid/content/Context;", "contentResolver", "Landroid/content/ContentResolver;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Landroid/content/Context;Landroid/content/ContentResolver;Lcom/example/shellshot/utils/ShellLogger;)V", "save", "Lcom/example/shellshot/media/OutputWriteResult;", "bitmap", "Landroid/graphics/Bitmap;", "sourceCandidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "namingStrategy", "Lcom/example/shellshot/data/OutputNamingStrategy;", "(Landroid/graphics/Bitmap;Lcom/example/shellshot/media/ScreenshotCandidate;Lcom/example/shellshot/data/OutputNamingStrategy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOriginal", "Lcom/example/shellshot/media/DeleteResult;", "candidate", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildUniqueOutputFile", "Ljava/io/File;", "parentDirectory", "sourceDisplayName", "", "scanFile", "Landroid/net/Uri;", "file", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class OutputRepository {

    @Deprecated
    public static final String TAG = "OutputRepository";
    private final ContentResolver contentResolver;
    private final Context context;
    private final ShellLogger logger;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public OutputRepository(Context context, ContentResolver contentResolver, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.contentResolver = contentResolver;
        this.logger = logger;
    }

    public final Object save(Bitmap bitmap, ScreenshotCandidate sourceCandidate, OutputNamingStrategy namingStrategy, Continuation<? super OutputWriteResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new OutputRepository$save$2(this, sourceCandidate, namingStrategy, bitmap, null), continuation);
    }

    public final Object deleteOriginal(ScreenshotCandidate candidate, Continuation<? super DeleteResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new OutputRepository$deleteOriginal$2(candidate, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File buildUniqueOutputFile(File parentDirectory, String sourceDisplayName, OutputNamingStrategy namingStrategy) {
        String desiredName = ScreenshotDirectories.INSTANCE.buildOutputFileName(sourceDisplayName, namingStrategy);
        File firstCandidate = new File(parentDirectory, desiredName);
        if (!firstCandidate.exists()) {
            return firstCandidate;
        }
        String baseName = StringsKt.substringBeforeLast$default(desiredName, '.', (String) null, 2, (Object) null);
        String extension = StringsKt.substringAfterLast(desiredName, '.', "png");
        int index = 2;
        while (true) {
            File candidate = new File(parentDirectory, baseName + "_" + index + "." + extension);
            if (!candidate.exists()) {
                return candidate;
            }
            index++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object scanFile(File file, Continuation<? super Uri> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        MediaScannerConnection.scanFile(this.context, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.example.shellshot.media.OutputRepository$scanFile$2$1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String str, Uri uri) {
                if (cancellableContinuationImpl2.isActive()) {
                    CancellableContinuation<Uri> cancellableContinuation = cancellableContinuationImpl2;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m7216constructorimpl(uri));
                }
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* compiled from: OutputRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/media/OutputRepository$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
