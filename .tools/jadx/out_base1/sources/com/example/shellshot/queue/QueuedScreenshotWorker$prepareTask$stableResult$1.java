package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: QueuedScreenshotWorker.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
final /* synthetic */ class QueuedScreenshotWorker$prepareTask$stableResult$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    QueuedScreenshotWorker$prepareTask$stableResult$1(Object obj) {
        super(0, obj, QueuedScreenshotWorker.class, "canProcessCurrentTask", "canProcessCurrentTask()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        boolean canProcessCurrentTask;
        canProcessCurrentTask = ((QueuedScreenshotWorker) this.receiver).canProcessCurrentTask();
        return Boolean.valueOf(canProcessCurrentTask);
    }
}
