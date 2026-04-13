package com.example.shellshot.media;

import com.example.shellshot.media.ScreenshotStabilityChecker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ScreenshotStabilityChecker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/media/ScreenshotStabilityChecker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.ScreenshotStabilityChecker$awaitStableCandidate$2", f = "ScreenshotStabilityChecker.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {43}, m = "invokeSuspend", n = {"previousPositiveSize", "roundFailures", "readableStreak", "startedAtElapsedRealtime", "index", "targetOffsetMillis", "round", "sleepMillis"}, s = {"L$0", "L$1", "I$0", "J$0", "I$3", "J$1", "I$4", "J$2"})
/* loaded from: classes11.dex */
final class ScreenshotStabilityChecker$awaitStableCandidate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ScreenshotStabilityChecker.Result>, Object> {
    final /* synthetic */ ScreenshotCandidate $candidate;
    final /* synthetic */ Function0<Boolean> $shouldContinue;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScreenshotStabilityChecker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScreenshotStabilityChecker$awaitStableCandidate$2(Function0<Boolean> function0, ScreenshotStabilityChecker screenshotStabilityChecker, ScreenshotCandidate screenshotCandidate, Continuation<? super ScreenshotStabilityChecker$awaitStableCandidate$2> continuation) {
        super(2, continuation);
        this.$shouldContinue = function0;
        this.this$0 = screenshotStabilityChecker;
        this.$candidate = screenshotCandidate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ScreenshotStabilityChecker$awaitStableCandidate$2(this.$shouldContinue, this.this$0, this.$candidate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ScreenshotStabilityChecker.Result> continuation) {
        return ((ScreenshotStabilityChecker$awaitStableCandidate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0488  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x0103 -> B:7:0x010a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x0110 -> B:8:0x011d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r69) {
        /*
            Method dump skipped, instructions count: 1248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.ScreenshotStabilityChecker$awaitStableCandidate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
