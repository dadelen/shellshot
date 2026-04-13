package com.example.shellshot.data;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProcessingResult.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010%\u001a\u00020\rHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jk\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014¨\u0006-"}, d2 = {"Lcom/example/shellshot/data/ProcessingResult;", "", "processedAtMillis", "", "sourcePath", "", "outputPath", "templateId", "triggerSource", "deletedOriginal", "", "deleteMessage", NotificationCompat.CATEGORY_STATUS, "Lcom/example/shellshot/data/ProcessingStatus;", "detailMessage", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lcom/example/shellshot/data/ProcessingStatus;Ljava/lang/String;)V", "getProcessedAtMillis", "()J", "getSourcePath", "()Ljava/lang/String;", "getOutputPath", "getTemplateId", "getTriggerSource", "getDeletedOriginal", "()Z", "getDeleteMessage", "getStatus", "()Lcom/example/shellshot/data/ProcessingStatus;", "getDetailMessage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class ProcessingResult {
    public static final int $stable = 0;
    private final String deleteMessage;
    private final boolean deletedOriginal;
    private final String detailMessage;
    private final String outputPath;
    private final long processedAtMillis;
    private final String sourcePath;
    private final ProcessingStatus status;
    private final String templateId;
    private final String triggerSource;

    public static /* synthetic */ ProcessingResult copy$default(ProcessingResult processingResult, long j, String str, String str2, String str3, String str4, boolean z, String str5, ProcessingStatus processingStatus, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            j = processingResult.processedAtMillis;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = processingResult.sourcePath;
        }
        String str7 = str;
        if ((i & 4) != 0) {
            str2 = processingResult.outputPath;
        }
        return processingResult.copy(j2, str7, str2, (i & 8) != 0 ? processingResult.templateId : str3, (i & 16) != 0 ? processingResult.triggerSource : str4, (i & 32) != 0 ? processingResult.deletedOriginal : z, (i & 64) != 0 ? processingResult.deleteMessage : str5, (i & 128) != 0 ? processingResult.status : processingStatus, (i & 256) != 0 ? processingResult.detailMessage : str6);
    }

    /* renamed from: component1, reason: from getter */
    public final long getProcessedAtMillis() {
        return this.processedAtMillis;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSourcePath() {
        return this.sourcePath;
    }

    /* renamed from: component3, reason: from getter */
    public final String getOutputPath() {
        return this.outputPath;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTriggerSource() {
        return this.triggerSource;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getDeletedOriginal() {
        return this.deletedOriginal;
    }

    /* renamed from: component7, reason: from getter */
    public final String getDeleteMessage() {
        return this.deleteMessage;
    }

    /* renamed from: component8, reason: from getter */
    public final ProcessingStatus getStatus() {
        return this.status;
    }

    /* renamed from: component9, reason: from getter */
    public final String getDetailMessage() {
        return this.detailMessage;
    }

    public final ProcessingResult copy(long processedAtMillis, String sourcePath, String outputPath, String templateId, String triggerSource, boolean deletedOriginal, String deleteMessage, ProcessingStatus status, String detailMessage) {
        Intrinsics.checkNotNullParameter(sourcePath, "sourcePath");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(status, "status");
        return new ProcessingResult(processedAtMillis, sourcePath, outputPath, templateId, triggerSource, deletedOriginal, deleteMessage, status, detailMessage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProcessingResult)) {
            return false;
        }
        ProcessingResult processingResult = (ProcessingResult) other;
        return this.processedAtMillis == processingResult.processedAtMillis && Intrinsics.areEqual(this.sourcePath, processingResult.sourcePath) && Intrinsics.areEqual(this.outputPath, processingResult.outputPath) && Intrinsics.areEqual(this.templateId, processingResult.templateId) && Intrinsics.areEqual(this.triggerSource, processingResult.triggerSource) && this.deletedOriginal == processingResult.deletedOriginal && Intrinsics.areEqual(this.deleteMessage, processingResult.deleteMessage) && this.status == processingResult.status && Intrinsics.areEqual(this.detailMessage, processingResult.detailMessage);
    }

    public int hashCode() {
        return (((((((((((((((Long.hashCode(this.processedAtMillis) * 31) + this.sourcePath.hashCode()) * 31) + (this.outputPath == null ? 0 : this.outputPath.hashCode())) * 31) + this.templateId.hashCode()) * 31) + (this.triggerSource == null ? 0 : this.triggerSource.hashCode())) * 31) + Boolean.hashCode(this.deletedOriginal)) * 31) + (this.deleteMessage == null ? 0 : this.deleteMessage.hashCode())) * 31) + this.status.hashCode()) * 31) + (this.detailMessage != null ? this.detailMessage.hashCode() : 0);
    }

    public String toString() {
        return "ProcessingResult(processedAtMillis=" + this.processedAtMillis + ", sourcePath=" + this.sourcePath + ", outputPath=" + this.outputPath + ", templateId=" + this.templateId + ", triggerSource=" + this.triggerSource + ", deletedOriginal=" + this.deletedOriginal + ", deleteMessage=" + this.deleteMessage + ", status=" + this.status + ", detailMessage=" + this.detailMessage + ")";
    }

    public ProcessingResult(long processedAtMillis, String sourcePath, String outputPath, String templateId, String triggerSource, boolean deletedOriginal, String deleteMessage, ProcessingStatus status, String detailMessage) {
        Intrinsics.checkNotNullParameter(sourcePath, "sourcePath");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(status, "status");
        this.processedAtMillis = processedAtMillis;
        this.sourcePath = sourcePath;
        this.outputPath = outputPath;
        this.templateId = templateId;
        this.triggerSource = triggerSource;
        this.deletedOriginal = deletedOriginal;
        this.deleteMessage = deleteMessage;
        this.status = status;
        this.detailMessage = detailMessage;
    }

    public final long getProcessedAtMillis() {
        return this.processedAtMillis;
    }

    public final String getSourcePath() {
        return this.sourcePath;
    }

    public final String getOutputPath() {
        return this.outputPath;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final String getTriggerSource() {
        return this.triggerSource;
    }

    public final boolean getDeletedOriginal() {
        return this.deletedOriginal;
    }

    public final String getDeleteMessage() {
        return this.deleteMessage;
    }

    public final ProcessingStatus getStatus() {
        return this.status;
    }

    public final String getDetailMessage() {
        return this.detailMessage;
    }
}
