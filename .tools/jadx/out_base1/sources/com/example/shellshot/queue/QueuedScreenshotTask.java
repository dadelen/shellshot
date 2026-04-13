package com.example.shellshot.queue;

import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: QueuedScreenshotTask.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 U2\u00020\u0001:\u0002TUB«\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0019\u0010\u001aB¹\u0001\b\u0010\u0012\u0006\u0010\u001b\u001a\u00020\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u0019\u0010\u001eJ\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\nHÆ\u0003J\t\u0010=\u001a\u00020\fHÆ\u0003J\t\u0010>\u001a\u00020\fHÆ\u0003J\t\u0010?\u001a\u00020\fHÆ\u0003J\t\u0010@\u001a\u00020\u0010HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0016HÆ\u0003J\t\u0010E\u001a\u00020\u0016HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¿\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010H\u001a\u00020\u00162\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020\u0010HÖ\u0001J\t\u0010K\u001a\u00020\u0003HÖ\u0001J%\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u00002\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0001¢\u0006\u0002\bSR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0017\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010 ¨\u0006V"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotTask;", "", "id", "", "absolutePath", "displayName", "relativePath", "changedUri", "source", NotificationCompat.CATEGORY_STATUS, "Lcom/example/shellshot/queue/ScreenshotTaskStatus;", "detectedAtMillis", "", "updatedAtMillis", "nextAttemptAtMillis", "retryCount", "", "lastError", "preparedCandidate", "Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "outputPath", "deleteAttempted", "", "deleteSucceeded", "deleteMessage", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/queue/ScreenshotTaskStatus;JJJILjava/lang/String;Lcom/example/shellshot/queue/PreparedScreenshotCandidate;Ljava/lang/String;ZZLjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/queue/ScreenshotTaskStatus;JJJILjava/lang/String;Lcom/example/shellshot/queue/PreparedScreenshotCandidate;Ljava/lang/String;ZZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getAbsolutePath", "getDisplayName", "getRelativePath", "getChangedUri", "getSource", "getStatus", "()Lcom/example/shellshot/queue/ScreenshotTaskStatus;", "getDetectedAtMillis", "()J", "getUpdatedAtMillis", "getNextAttemptAtMillis", "getRetryCount", "()I", "getLastError", "getPreparedCandidate", "()Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "getOutputPath", "getDeleteAttempted", "()Z", "getDeleteSucceeded", "getDeleteMessage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
/* loaded from: classes5.dex */
public final /* data */ class QueuedScreenshotTask {
    public static final int $stable = 0;
    private final String absolutePath;
    private final String changedUri;
    private final boolean deleteAttempted;
    private final String deleteMessage;
    private final boolean deleteSucceeded;
    private final long detectedAtMillis;
    private final String displayName;
    private final String id;
    private final String lastError;
    private final long nextAttemptAtMillis;
    private final String outputPath;
    private final PreparedScreenshotCandidate preparedCandidate;
    private final String relativePath;
    private final int retryCount;
    private final String source;
    private final ScreenshotTaskStatus status;
    private final long updatedAtMillis;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.example.shellshot.queue.QueuedScreenshotTask$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            KSerializer _childSerializers$_anonymous_;
            _childSerializers$_anonymous_ = QueuedScreenshotTask._childSerializers$_anonymous_();
            return _childSerializers$_anonymous_;
        }
    }), null, null, null, null, null, null, null, null, null, null};

    public static /* synthetic */ QueuedScreenshotTask copy$default(QueuedScreenshotTask queuedScreenshotTask, String str, String str2, String str3, String str4, String str5, String str6, ScreenshotTaskStatus screenshotTaskStatus, long j, long j2, long j3, int i, String str7, PreparedScreenshotCandidate preparedScreenshotCandidate, String str8, boolean z, boolean z2, String str9, int i2, Object obj) {
        String str10;
        boolean z3;
        String str11 = (i2 & 1) != 0 ? queuedScreenshotTask.id : str;
        String str12 = (i2 & 2) != 0 ? queuedScreenshotTask.absolutePath : str2;
        String str13 = (i2 & 4) != 0 ? queuedScreenshotTask.displayName : str3;
        String str14 = (i2 & 8) != 0 ? queuedScreenshotTask.relativePath : str4;
        String str15 = (i2 & 16) != 0 ? queuedScreenshotTask.changedUri : str5;
        String str16 = (i2 & 32) != 0 ? queuedScreenshotTask.source : str6;
        ScreenshotTaskStatus screenshotTaskStatus2 = (i2 & 64) != 0 ? queuedScreenshotTask.status : screenshotTaskStatus;
        long j4 = (i2 & 128) != 0 ? queuedScreenshotTask.detectedAtMillis : j;
        long j5 = (i2 & 256) != 0 ? queuedScreenshotTask.updatedAtMillis : j2;
        long j6 = (i2 & 512) != 0 ? queuedScreenshotTask.nextAttemptAtMillis : j3;
        int i3 = (i2 & 1024) != 0 ? queuedScreenshotTask.retryCount : i;
        String str17 = str11;
        String str18 = (i2 & 2048) != 0 ? queuedScreenshotTask.lastError : str7;
        PreparedScreenshotCandidate preparedScreenshotCandidate2 = (i2 & 4096) != 0 ? queuedScreenshotTask.preparedCandidate : preparedScreenshotCandidate;
        String str19 = (i2 & 8192) != 0 ? queuedScreenshotTask.outputPath : str8;
        boolean z4 = (i2 & 16384) != 0 ? queuedScreenshotTask.deleteAttempted : z;
        boolean z5 = (i2 & 32768) != 0 ? queuedScreenshotTask.deleteSucceeded : z2;
        if ((i2 & 65536) != 0) {
            z3 = z5;
            str10 = queuedScreenshotTask.deleteMessage;
        } else {
            str10 = str9;
            z3 = z5;
        }
        return queuedScreenshotTask.copy(str17, str12, str13, str14, str15, str16, screenshotTaskStatus2, j4, j5, j6, i3, str18, preparedScreenshotCandidate2, str19, z4, z3, str10);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final long getNextAttemptAtMillis() {
        return this.nextAttemptAtMillis;
    }

    /* renamed from: component11, reason: from getter */
    public final int getRetryCount() {
        return this.retryCount;
    }

    /* renamed from: component12, reason: from getter */
    public final String getLastError() {
        return this.lastError;
    }

    /* renamed from: component13, reason: from getter */
    public final PreparedScreenshotCandidate getPreparedCandidate() {
        return this.preparedCandidate;
    }

    /* renamed from: component14, reason: from getter */
    public final String getOutputPath() {
        return this.outputPath;
    }

    /* renamed from: component15, reason: from getter */
    public final boolean getDeleteAttempted() {
        return this.deleteAttempted;
    }

    /* renamed from: component16, reason: from getter */
    public final boolean getDeleteSucceeded() {
        return this.deleteSucceeded;
    }

    /* renamed from: component17, reason: from getter */
    public final String getDeleteMessage() {
        return this.deleteMessage;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component5, reason: from getter */
    public final String getChangedUri() {
        return this.changedUri;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* renamed from: component7, reason: from getter */
    public final ScreenshotTaskStatus getStatus() {
        return this.status;
    }

    /* renamed from: component8, reason: from getter */
    public final long getDetectedAtMillis() {
        return this.detectedAtMillis;
    }

    /* renamed from: component9, reason: from getter */
    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    public final QueuedScreenshotTask copy(String id, String absolutePath, String displayName, String relativePath, String changedUri, String source, ScreenshotTaskStatus status, long detectedAtMillis, long updatedAtMillis, long nextAttemptAtMillis, int retryCount, String lastError, PreparedScreenshotCandidate preparedCandidate, String outputPath, boolean deleteAttempted, boolean deleteSucceeded, String deleteMessage) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(status, "status");
        return new QueuedScreenshotTask(id, absolutePath, displayName, relativePath, changedUri, source, status, detectedAtMillis, updatedAtMillis, nextAttemptAtMillis, retryCount, lastError, preparedCandidate, outputPath, deleteAttempted, deleteSucceeded, deleteMessage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueuedScreenshotTask)) {
            return false;
        }
        QueuedScreenshotTask queuedScreenshotTask = (QueuedScreenshotTask) other;
        return Intrinsics.areEqual(this.id, queuedScreenshotTask.id) && Intrinsics.areEqual(this.absolutePath, queuedScreenshotTask.absolutePath) && Intrinsics.areEqual(this.displayName, queuedScreenshotTask.displayName) && Intrinsics.areEqual(this.relativePath, queuedScreenshotTask.relativePath) && Intrinsics.areEqual(this.changedUri, queuedScreenshotTask.changedUri) && Intrinsics.areEqual(this.source, queuedScreenshotTask.source) && this.status == queuedScreenshotTask.status && this.detectedAtMillis == queuedScreenshotTask.detectedAtMillis && this.updatedAtMillis == queuedScreenshotTask.updatedAtMillis && this.nextAttemptAtMillis == queuedScreenshotTask.nextAttemptAtMillis && this.retryCount == queuedScreenshotTask.retryCount && Intrinsics.areEqual(this.lastError, queuedScreenshotTask.lastError) && Intrinsics.areEqual(this.preparedCandidate, queuedScreenshotTask.preparedCandidate) && Intrinsics.areEqual(this.outputPath, queuedScreenshotTask.outputPath) && this.deleteAttempted == queuedScreenshotTask.deleteAttempted && this.deleteSucceeded == queuedScreenshotTask.deleteSucceeded && Intrinsics.areEqual(this.deleteMessage, queuedScreenshotTask.deleteMessage);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.absolutePath.hashCode()) * 31) + this.displayName.hashCode()) * 31) + (this.relativePath == null ? 0 : this.relativePath.hashCode())) * 31) + (this.changedUri == null ? 0 : this.changedUri.hashCode())) * 31) + this.source.hashCode()) * 31) + this.status.hashCode()) * 31) + Long.hashCode(this.detectedAtMillis)) * 31) + Long.hashCode(this.updatedAtMillis)) * 31) + Long.hashCode(this.nextAttemptAtMillis)) * 31) + Integer.hashCode(this.retryCount)) * 31) + (this.lastError == null ? 0 : this.lastError.hashCode())) * 31) + (this.preparedCandidate == null ? 0 : this.preparedCandidate.hashCode())) * 31) + (this.outputPath == null ? 0 : this.outputPath.hashCode())) * 31) + Boolean.hashCode(this.deleteAttempted)) * 31) + Boolean.hashCode(this.deleteSucceeded)) * 31) + (this.deleteMessage != null ? this.deleteMessage.hashCode() : 0);
    }

    public String toString() {
        return "QueuedScreenshotTask(id=" + this.id + ", absolutePath=" + this.absolutePath + ", displayName=" + this.displayName + ", relativePath=" + this.relativePath + ", changedUri=" + this.changedUri + ", source=" + this.source + ", status=" + this.status + ", detectedAtMillis=" + this.detectedAtMillis + ", updatedAtMillis=" + this.updatedAtMillis + ", nextAttemptAtMillis=" + this.nextAttemptAtMillis + ", retryCount=" + this.retryCount + ", lastError=" + this.lastError + ", preparedCandidate=" + this.preparedCandidate + ", outputPath=" + this.outputPath + ", deleteAttempted=" + this.deleteAttempted + ", deleteSucceeded=" + this.deleteSucceeded + ", deleteMessage=" + this.deleteMessage + ")";
    }

    /* compiled from: QueuedScreenshotTask.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/shellshot/queue/QueuedScreenshotTask$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<QueuedScreenshotTask> serializer() {
            return QueuedScreenshotTask$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ QueuedScreenshotTask(int seen0, String id, String absolutePath, String displayName, String relativePath, String changedUri, String source, ScreenshotTaskStatus status, long detectedAtMillis, long updatedAtMillis, long nextAttemptAtMillis, int retryCount, String lastError, PreparedScreenshotCandidate preparedCandidate, String outputPath, boolean deleteAttempted, boolean deleteSucceeded, String deleteMessage, SerializationConstructorMarker serializationConstructorMarker) {
        if (1007 != (seen0 & PointerIconCompat.TYPE_CROSSHAIR)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, PointerIconCompat.TYPE_CROSSHAIR, QueuedScreenshotTask$$serializer.INSTANCE.getDescriptor());
        }
        this.id = id;
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        if ((seen0 & 16) == 0) {
            this.changedUri = null;
        } else {
            this.changedUri = changedUri;
        }
        this.source = source;
        this.status = status;
        this.detectedAtMillis = detectedAtMillis;
        this.updatedAtMillis = updatedAtMillis;
        this.nextAttemptAtMillis = nextAttemptAtMillis;
        if ((seen0 & 1024) == 0) {
            this.retryCount = 0;
        } else {
            this.retryCount = retryCount;
        }
        if ((seen0 & 2048) == 0) {
            this.lastError = null;
        } else {
            this.lastError = lastError;
        }
        if ((seen0 & 4096) == 0) {
            this.preparedCandidate = null;
        } else {
            this.preparedCandidate = preparedCandidate;
        }
        if ((seen0 & 8192) == 0) {
            this.outputPath = null;
        } else {
            this.outputPath = outputPath;
        }
        if ((seen0 & 16384) == 0) {
            this.deleteAttempted = false;
        } else {
            this.deleteAttempted = deleteAttempted;
        }
        if ((seen0 & 32768) == 0) {
            this.deleteSucceeded = false;
        } else {
            this.deleteSucceeded = deleteSucceeded;
        }
        if ((seen0 & 65536) == 0) {
            this.deleteMessage = null;
        } else {
            this.deleteMessage = deleteMessage;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return ScreenshotTaskStatus.INSTANCE.serializer();
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(QueuedScreenshotTask self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeStringElement(serialDesc, 1, self.absolutePath);
        output.encodeStringElement(serialDesc, 2, self.displayName);
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.relativePath);
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.changedUri != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.changedUri);
        }
        output.encodeStringElement(serialDesc, 5, self.source);
        output.encodeSerializableElement(serialDesc, 6, lazyArr[6].getValue(), self.status);
        output.encodeLongElement(serialDesc, 7, self.detectedAtMillis);
        output.encodeLongElement(serialDesc, 8, self.updatedAtMillis);
        output.encodeLongElement(serialDesc, 9, self.nextAttemptAtMillis);
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.retryCount != 0) {
            output.encodeIntElement(serialDesc, 10, self.retryCount);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.lastError != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.lastError);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.preparedCandidate != null) {
            output.encodeNullableSerializableElement(serialDesc, 12, PreparedScreenshotCandidate$$serializer.INSTANCE, self.preparedCandidate);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.outputPath != null) {
            output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.outputPath);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.deleteAttempted) {
            output.encodeBooleanElement(serialDesc, 14, self.deleteAttempted);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || self.deleteSucceeded) {
            output.encodeBooleanElement(serialDesc, 15, self.deleteSucceeded);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 16) || self.deleteMessage != null) {
            output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.deleteMessage);
        }
    }

    public QueuedScreenshotTask(String id, String absolutePath, String displayName, String relativePath, String changedUri, String source, ScreenshotTaskStatus status, long detectedAtMillis, long updatedAtMillis, long nextAttemptAtMillis, int retryCount, String lastError, PreparedScreenshotCandidate preparedCandidate, String outputPath, boolean deleteAttempted, boolean deleteSucceeded, String deleteMessage) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id;
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        this.changedUri = changedUri;
        this.source = source;
        this.status = status;
        this.detectedAtMillis = detectedAtMillis;
        this.updatedAtMillis = updatedAtMillis;
        this.nextAttemptAtMillis = nextAttemptAtMillis;
        this.retryCount = retryCount;
        this.lastError = lastError;
        this.preparedCandidate = preparedCandidate;
        this.outputPath = outputPath;
        this.deleteAttempted = deleteAttempted;
        this.deleteSucceeded = deleteSucceeded;
        this.deleteMessage = deleteMessage;
    }

    public /* synthetic */ QueuedScreenshotTask(String str, String str2, String str3, String str4, String str5, String str6, ScreenshotTaskStatus screenshotTaskStatus, long j, long j2, long j3, int i, String str7, PreparedScreenshotCandidate preparedScreenshotCandidate, String str8, boolean z, boolean z2, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i2 & 16) != 0 ? null : str5, str6, screenshotTaskStatus, j, j2, j3, (i2 & 1024) != 0 ? 0 : i, (i2 & 2048) != 0 ? null : str7, (i2 & 4096) != 0 ? null : preparedScreenshotCandidate, (i2 & 8192) != 0 ? null : str8, (i2 & 16384) != 0 ? false : z, (32768 & i2) != 0 ? false : z2, (i2 & 65536) != 0 ? null : str9);
    }

    public final String getId() {
        return this.id;
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getChangedUri() {
        return this.changedUri;
    }

    public final String getSource() {
        return this.source;
    }

    public final ScreenshotTaskStatus getStatus() {
        return this.status;
    }

    public final long getDetectedAtMillis() {
        return this.detectedAtMillis;
    }

    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    public final long getNextAttemptAtMillis() {
        return this.nextAttemptAtMillis;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final String getLastError() {
        return this.lastError;
    }

    public final PreparedScreenshotCandidate getPreparedCandidate() {
        return this.preparedCandidate;
    }

    public final String getOutputPath() {
        return this.outputPath;
    }

    public final boolean getDeleteAttempted() {
        return this.deleteAttempted;
    }

    public final boolean getDeleteSucceeded() {
        return this.deleteSucceeded;
    }

    public final String getDeleteMessage() {
        return this.deleteMessage;
    }
}
