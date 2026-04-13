package com.example.shellshot.template;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateImportDraft.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/example/shellshot/template/TemplateImportDraft;", "", "sourceImagePath", "", "templateName", "validationWarning", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSourceImagePath", "()Ljava/lang/String;", "getTemplateName", "getValidationWarning", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class TemplateImportDraft {
    public static final int $stable = 0;
    private final String sourceImagePath;
    private final String templateName;
    private final String validationWarning;

    public static /* synthetic */ TemplateImportDraft copy$default(TemplateImportDraft templateImportDraft, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = templateImportDraft.sourceImagePath;
        }
        if ((i & 2) != 0) {
            str2 = templateImportDraft.templateName;
        }
        if ((i & 4) != 0) {
            str3 = templateImportDraft.validationWarning;
        }
        return templateImportDraft.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSourceImagePath() {
        return this.sourceImagePath;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTemplateName() {
        return this.templateName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getValidationWarning() {
        return this.validationWarning;
    }

    public final TemplateImportDraft copy(String sourceImagePath, String templateName, String validationWarning) {
        Intrinsics.checkNotNullParameter(sourceImagePath, "sourceImagePath");
        Intrinsics.checkNotNullParameter(templateName, "templateName");
        return new TemplateImportDraft(sourceImagePath, templateName, validationWarning);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateImportDraft)) {
            return false;
        }
        TemplateImportDraft templateImportDraft = (TemplateImportDraft) other;
        return Intrinsics.areEqual(this.sourceImagePath, templateImportDraft.sourceImagePath) && Intrinsics.areEqual(this.templateName, templateImportDraft.templateName) && Intrinsics.areEqual(this.validationWarning, templateImportDraft.validationWarning);
    }

    public int hashCode() {
        return (((this.sourceImagePath.hashCode() * 31) + this.templateName.hashCode()) * 31) + (this.validationWarning == null ? 0 : this.validationWarning.hashCode());
    }

    public String toString() {
        return "TemplateImportDraft(sourceImagePath=" + this.sourceImagePath + ", templateName=" + this.templateName + ", validationWarning=" + this.validationWarning + ")";
    }

    public TemplateImportDraft(String sourceImagePath, String templateName, String validationWarning) {
        Intrinsics.checkNotNullParameter(sourceImagePath, "sourceImagePath");
        Intrinsics.checkNotNullParameter(templateName, "templateName");
        this.sourceImagePath = sourceImagePath;
        this.templateName = templateName;
        this.validationWarning = validationWarning;
    }

    public /* synthetic */ TemplateImportDraft(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3);
    }

    public final String getSourceImagePath() {
        return this.sourceImagePath;
    }

    public final String getTemplateName() {
        return this.templateName;
    }

    public final String getValidationWarning() {
        return this.validationWarning;
    }
}
