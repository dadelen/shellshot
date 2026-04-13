package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueuedScreenshotTask.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/example/shellshot/queue/EnqueueTaskResult;", "", "accepted", "", "reason", "", "task", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "<init>", "(ZLjava/lang/String;Lcom/example/shellshot/queue/QueuedScreenshotTask;)V", "getAccepted", "()Z", "getReason", "()Ljava/lang/String;", "getTask", "()Lcom/example/shellshot/queue/QueuedScreenshotTask;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class EnqueueTaskResult {
    public static final int $stable = 0;
    private final boolean accepted;
    private final String reason;
    private final QueuedScreenshotTask task;

    public static /* synthetic */ EnqueueTaskResult copy$default(EnqueueTaskResult enqueueTaskResult, boolean z, String str, QueuedScreenshotTask queuedScreenshotTask, int i, Object obj) {
        if ((i & 1) != 0) {
            z = enqueueTaskResult.accepted;
        }
        if ((i & 2) != 0) {
            str = enqueueTaskResult.reason;
        }
        if ((i & 4) != 0) {
            queuedScreenshotTask = enqueueTaskResult.task;
        }
        return enqueueTaskResult.copy(z, str, queuedScreenshotTask);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getAccepted() {
        return this.accepted;
    }

    /* renamed from: component2, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* renamed from: component3, reason: from getter */
    public final QueuedScreenshotTask getTask() {
        return this.task;
    }

    public final EnqueueTaskResult copy(boolean accepted, String reason, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new EnqueueTaskResult(accepted, reason, task);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnqueueTaskResult)) {
            return false;
        }
        EnqueueTaskResult enqueueTaskResult = (EnqueueTaskResult) other;
        return this.accepted == enqueueTaskResult.accepted && Intrinsics.areEqual(this.reason, enqueueTaskResult.reason) && Intrinsics.areEqual(this.task, enqueueTaskResult.task);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.accepted) * 31) + this.reason.hashCode()) * 31) + (this.task == null ? 0 : this.task.hashCode());
    }

    public String toString() {
        return "EnqueueTaskResult(accepted=" + this.accepted + ", reason=" + this.reason + ", task=" + this.task + ")";
    }

    public EnqueueTaskResult(boolean accepted, String reason, QueuedScreenshotTask task) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.accepted = accepted;
        this.reason = reason;
        this.task = task;
    }

    public /* synthetic */ EnqueueTaskResult(boolean z, String str, QueuedScreenshotTask queuedScreenshotTask, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, (i & 4) != 0 ? null : queuedScreenshotTask);
    }

    public final boolean getAccepted() {
        return this.accepted;
    }

    public final String getReason() {
        return this.reason;
    }

    public final QueuedScreenshotTask getTask() {
        return this.task;
    }
}
