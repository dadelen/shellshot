package com.example.shellshot.template;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateOperationResult.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/example/shellshot/template/TemplateDeleteResult;", "", "success", "", "deletedTemplateId", "", "message", "<init>", "(ZLjava/lang/String;Ljava/lang/String;)V", "getSuccess", "()Z", "getDeletedTemplateId", "()Ljava/lang/String;", "getMessage", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class TemplateDeleteResult {
    public static final int $stable = 0;
    private final String deletedTemplateId;
    private final String message;
    private final boolean success;

    public static /* synthetic */ TemplateDeleteResult copy$default(TemplateDeleteResult templateDeleteResult, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = templateDeleteResult.success;
        }
        if ((i & 2) != 0) {
            str = templateDeleteResult.deletedTemplateId;
        }
        if ((i & 4) != 0) {
            str2 = templateDeleteResult.message;
        }
        return templateDeleteResult.copy(z, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeletedTemplateId() {
        return this.deletedTemplateId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final TemplateDeleteResult copy(boolean success, String deletedTemplateId, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new TemplateDeleteResult(success, deletedTemplateId, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateDeleteResult)) {
            return false;
        }
        TemplateDeleteResult templateDeleteResult = (TemplateDeleteResult) other;
        return this.success == templateDeleteResult.success && Intrinsics.areEqual(this.deletedTemplateId, templateDeleteResult.deletedTemplateId) && Intrinsics.areEqual(this.message, templateDeleteResult.message);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.success) * 31) + (this.deletedTemplateId == null ? 0 : this.deletedTemplateId.hashCode())) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "TemplateDeleteResult(success=" + this.success + ", deletedTemplateId=" + this.deletedTemplateId + ", message=" + this.message + ")";
    }

    public TemplateDeleteResult(boolean success, String deletedTemplateId, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.success = success;
        this.deletedTemplateId = deletedTemplateId;
        this.message = message;
    }

    public /* synthetic */ TemplateDeleteResult(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? null : str, str2);
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final String getDeletedTemplateId() {
        return this.deletedTemplateId;
    }

    public final String getMessage() {
        return this.message;
    }
}
