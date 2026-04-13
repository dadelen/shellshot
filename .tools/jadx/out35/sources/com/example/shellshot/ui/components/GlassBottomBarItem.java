package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/example/shellshot/ui/components/GlassBottomBarItem;", "", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;)V", "getLabel", "()Ljava/lang/String;", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class GlassBottomBarItem {
    public static final int $stable = 0;
    private final ImageVector icon;
    private final String label;

    public static /* synthetic */ GlassBottomBarItem copy$default(GlassBottomBarItem glassBottomBarItem, String str, ImageVector imageVector, int i, Object obj) {
        if ((i & 1) != 0) {
            str = glassBottomBarItem.label;
        }
        if ((i & 2) != 0) {
            imageVector = glassBottomBarItem.icon;
        }
        return glassBottomBarItem.copy(str, imageVector);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component2, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    public final GlassBottomBarItem copy(String label, ImageVector icon) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new GlassBottomBarItem(label, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GlassBottomBarItem)) {
            return false;
        }
        GlassBottomBarItem glassBottomBarItem = (GlassBottomBarItem) other;
        return Intrinsics.areEqual(this.label, glassBottomBarItem.label) && Intrinsics.areEqual(this.icon, glassBottomBarItem.icon);
    }

    public int hashCode() {
        return (this.label.hashCode() * 31) + this.icon.hashCode();
    }

    public String toString() {
        return "GlassBottomBarItem(label=" + this.label + ", icon=" + this.icon + ")";
    }

    public GlassBottomBarItem(String label, ImageVector icon) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.label = label;
        this.icon = icon;
    }

    public final String getLabel() {
        return this.label;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }
}
