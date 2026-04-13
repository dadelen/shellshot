package com.example.shellshot.ui.screen;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogScreen.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0011JB\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\""}, d2 = {"Lcom/example/shellshot/ui/screen/LogPresentation;", "", "module", "", "title", "summary", "badgeLabel", "accentColor", "Landroidx/compose/ui/graphics/Color;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getModule", "()Ljava/lang/String;", "getTitle", "getSummary", "getBadgeLabel", "getAccentColor-0d7_KjU", "()J", "J", "component1", "component2", "component3", "component4", "component5", "component5-0d7_KjU", "copy", "copy-xwkQ0AY", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/example/shellshot/ui/screen/LogPresentation;", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
final /* data */ class LogPresentation {
    private final long accentColor;
    private final String badgeLabel;
    private final String module;
    private final String summary;
    private final String title;

    public /* synthetic */ LogPresentation(String str, String str2, String str3, String str4, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, j);
    }

    /* renamed from: copy-xwkQ0AY$default, reason: not valid java name */
    public static /* synthetic */ LogPresentation m7029copyxwkQ0AY$default(LogPresentation logPresentation, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = logPresentation.module;
        }
        if ((i & 2) != 0) {
            str2 = logPresentation.title;
        }
        if ((i & 4) != 0) {
            str3 = logPresentation.summary;
        }
        if ((i & 8) != 0) {
            str4 = logPresentation.badgeLabel;
        }
        if ((i & 16) != 0) {
            j = logPresentation.accentColor;
        }
        long j2 = j;
        return logPresentation.m7031copyxwkQ0AY(str, str2, str3, str4, j2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getModule() {
        return this.module;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBadgeLabel() {
        return this.badgeLabel;
    }

    /* renamed from: component5-0d7_KjU, reason: not valid java name and from getter */
    public final long getAccentColor() {
        return this.accentColor;
    }

    /* renamed from: copy-xwkQ0AY, reason: not valid java name */
    public final LogPresentation m7031copyxwkQ0AY(String module, String title, String summary, String badgeLabel, long accentColor) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(badgeLabel, "badgeLabel");
        return new LogPresentation(module, title, summary, badgeLabel, accentColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogPresentation)) {
            return false;
        }
        LogPresentation logPresentation = (LogPresentation) other;
        return Intrinsics.areEqual(this.module, logPresentation.module) && Intrinsics.areEqual(this.title, logPresentation.title) && Intrinsics.areEqual(this.summary, logPresentation.summary) && Intrinsics.areEqual(this.badgeLabel, logPresentation.badgeLabel) && Color.m4164equalsimpl0(this.accentColor, logPresentation.accentColor);
    }

    public int hashCode() {
        return (((((((this.module.hashCode() * 31) + this.title.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.badgeLabel.hashCode()) * 31) + Color.m4170hashCodeimpl(this.accentColor);
    }

    public String toString() {
        return "LogPresentation(module=" + this.module + ", title=" + this.title + ", summary=" + this.summary + ", badgeLabel=" + this.badgeLabel + ", accentColor=" + Color.m4171toStringimpl(this.accentColor) + ")";
    }

    private LogPresentation(String module, String title, String summary, String badgeLabel, long accentColor) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(badgeLabel, "badgeLabel");
        this.module = module;
        this.title = title;
        this.summary = summary;
        this.badgeLabel = badgeLabel;
        this.accentColor = accentColor;
    }

    public final String getModule() {
        return this.module;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getBadgeLabel() {
        return this.badgeLabel;
    }

    /* renamed from: getAccentColor-0d7_KjU, reason: not valid java name */
    public final long m7032getAccentColor0d7_KjU() {
        return this.accentColor;
    }
}
