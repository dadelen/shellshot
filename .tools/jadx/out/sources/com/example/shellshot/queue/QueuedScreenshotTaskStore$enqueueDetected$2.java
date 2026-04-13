package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: QueuedScreenshotTaskStore.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/queue/EnqueueTaskResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.queue.QueuedScreenshotTaskStore$enqueueDetected$2", f = "QueuedScreenshotTaskStore.kt", i = {1, 1, 1, 1}, l = {34, 319}, m = "invokeSuspend", n = {"settings", "screenshotRelativePath", "$this$withLock_u24default\\1", "$i$f$withLock\\1\\36"}, s = {"L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes5.dex */
final class QueuedScreenshotTaskStore$enqueueDetected$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EnqueueTaskResult>, Object> {
    final /* synthetic */ String $absolutePath;
    final /* synthetic */ String $changedUri;
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $relativePath;
    final /* synthetic */ String $source;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ QueuedScreenshotTaskStore this$0;

    /* compiled from: QueuedScreenshotTaskStore.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenshotTaskStatus.values().length];
            try {
                iArr[ScreenshotTaskStatus.COMPOSING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScreenshotTaskStatus.READY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QueuedScreenshotTaskStore$enqueueDetected$2(QueuedScreenshotTaskStore queuedScreenshotTaskStore, String str, String str2, String str3, String str4, String str5, Continuation<? super QueuedScreenshotTaskStore$enqueueDetected$2> continuation) {
        super(2, continuation);
        this.this$0 = queuedScreenshotTaskStore;
        this.$absolutePath = str;
        this.$displayName = str2;
        this.$relativePath = str3;
        this.$changedUri = str4;
        this.$source = str5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QueuedScreenshotTaskStore$enqueueDetected$2(this.this$0, this.$absolutePath, this.$displayName, this.$relativePath, this.$changedUri, this.$source, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EnqueueTaskResult> continuation) {
        return ((QueuedScreenshotTaskStore$enqueueDetected$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00be A[Catch: all -> 0x00d0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00d0, blocks: (B:12:0x00be, B:25:0x0106, B:34:0x012f, B:38:0x014a, B:43:0x0173, B:47:0x018f, B:53:0x01b0, B:63:0x01dc, B:65:0x01f8, B:73:0x0216, B:74:0x0222, B:75:0x0225, B:78:0x0237, B:79:0x023b, B:80:0x024d, B:83:0x025a, B:84:0x02a8, B:86:0x02ae, B:90:0x02cc, B:94:0x02d2, B:96:0x0251, B:97:0x0228, B:98:0x022d), top: B:10:0x00bc }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0209 A[LOOP:0: B:61:0x01d6->B:69:0x0209, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0212 A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r49) {
        /*
            Method dump skipped, instructions count: 918
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.queue.QueuedScreenshotTaskStore$enqueueDetected$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
