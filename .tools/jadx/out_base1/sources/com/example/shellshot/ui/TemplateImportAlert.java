package com.example.shellshot.ui;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainUiState.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/ui/TemplateImportAlert;", "", "title", "", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class TemplateImportAlert {
    public static final int $stable = 0;
    private final String message;
    private final String title;

    public static /* synthetic */ TemplateImportAlert copy$default(TemplateImportAlert templateImportAlert, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = templateImportAlert.title;
        }
        if ((i & 2) != 0) {
            str2 = templateImportAlert.message;
        }
        return templateImportAlert.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final TemplateImportAlert copy(String title, String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        return new TemplateImportAlert(title, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateImportAlert)) {
            return false;
        }
        TemplateImportAlert templateImportAlert = (TemplateImportAlert) other;
        return Intrinsics.areEqual(this.title, templateImportAlert.title) && Intrinsics.areEqual(this.message, templateImportAlert.message);
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.message.hashCode();
    }

    public String toString() {
        return "TemplateImportAlert(title=" + this.title + ", message=" + this.message + ")";
    }

    public TemplateImportAlert(String title, String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.title = title;
        this.message = message;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getMessage() {
        return this.message;
    }
}
