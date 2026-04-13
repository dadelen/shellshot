package com.example.shellshot.media;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LatestScreenshotResolver.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/example/shellshot/media/ResolveQueuedTaskResult;", "", "candidate", "Lcom/example/shellshot/media/ScreenshotCandidate;", "source", "Lcom/example/shellshot/media/ResolutionSource;", "reason", "", "<init>", "(Lcom/example/shellshot/media/ScreenshotCandidate;Lcom/example/shellshot/media/ResolutionSource;Ljava/lang/String;)V", "getCandidate", "()Lcom/example/shellshot/media/ScreenshotCandidate;", "getSource", "()Lcom/example/shellshot/media/ResolutionSource;", "getReason", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class ResolveQueuedTaskResult {
    public static final int $stable = 8;
    private final ScreenshotCandidate candidate;
    private final String reason;
    private final ResolutionSource source;

    public static /* synthetic */ ResolveQueuedTaskResult copy$default(ResolveQueuedTaskResult resolveQueuedTaskResult, ScreenshotCandidate screenshotCandidate, ResolutionSource resolutionSource, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            screenshotCandidate = resolveQueuedTaskResult.candidate;
        }
        if ((i & 2) != 0) {
            resolutionSource = resolveQueuedTaskResult.source;
        }
        if ((i & 4) != 0) {
            str = resolveQueuedTaskResult.reason;
        }
        return resolveQueuedTaskResult.copy(screenshotCandidate, resolutionSource, str);
    }

    /* renamed from: component1, reason: from getter */
    public final ScreenshotCandidate getCandidate() {
        return this.candidate;
    }

    /* renamed from: component2, reason: from getter */
    public final ResolutionSource getSource() {
        return this.source;
    }

    /* renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    public final ResolveQueuedTaskResult copy(ScreenshotCandidate candidate, ResolutionSource source, String reason) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new ResolveQueuedTaskResult(candidate, source, reason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResolveQueuedTaskResult)) {
            return false;
        }
        ResolveQueuedTaskResult resolveQueuedTaskResult = (ResolveQueuedTaskResult) other;
        return Intrinsics.areEqual(this.candidate, resolveQueuedTaskResult.candidate) && this.source == resolveQueuedTaskResult.source && Intrinsics.areEqual(this.reason, resolveQueuedTaskResult.reason);
    }

    public int hashCode() {
        return ((((this.candidate == null ? 0 : this.candidate.hashCode()) * 31) + this.source.hashCode()) * 31) + this.reason.hashCode();
    }

    public String toString() {
        return "ResolveQueuedTaskResult(candidate=" + this.candidate + ", source=" + this.source + ", reason=" + this.reason + ")";
    }

    public ResolveQueuedTaskResult(ScreenshotCandidate candidate, ResolutionSource source, String reason) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.candidate = candidate;
        this.source = source;
        this.reason = reason;
    }

    public final ScreenshotCandidate getCandidate() {
        return this.candidate;
    }

    public final ResolutionSource getSource() {
        return this.source;
    }

    public final String getReason() {
        return this.reason;
    }
}
