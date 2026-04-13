package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/example/shellshot/ui/components/NavItem;", "", "id", "", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "visible", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Z)V", "getId", "()Ljava/lang/String;", "getLabel", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getVisible", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class NavItem {
    public static final int $stable = 0;
    private final ImageVector icon;
    private final String id;
    private final String label;
    private final boolean visible;

    public static /* synthetic */ NavItem copy$default(NavItem navItem, String str, String str2, ImageVector imageVector, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = navItem.id;
        }
        if ((i & 2) != 0) {
            str2 = navItem.label;
        }
        if ((i & 4) != 0) {
            imageVector = navItem.icon;
        }
        if ((i & 8) != 0) {
            z = navItem.visible;
        }
        return navItem.copy(str, str2, imageVector, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component3, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getVisible() {
        return this.visible;
    }

    public final NavItem copy(String id, String label, ImageVector icon, boolean visible) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new NavItem(id, label, icon, visible);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NavItem)) {
            return false;
        }
        NavItem navItem = (NavItem) other;
        return Intrinsics.areEqual(this.id, navItem.id) && Intrinsics.areEqual(this.label, navItem.label) && Intrinsics.areEqual(this.icon, navItem.icon) && this.visible == navItem.visible;
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.label.hashCode()) * 31) + this.icon.hashCode()) * 31) + Boolean.hashCode(this.visible);
    }

    public String toString() {
        return "NavItem(id=" + this.id + ", label=" + this.label + ", icon=" + this.icon + ", visible=" + this.visible + ")";
    }

    public NavItem(String id, String label, ImageVector icon, boolean visible) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.id = id;
        this.label = label;
        this.icon = icon;
        this.visible = visible;
    }

    public /* synthetic */ NavItem(String str, String str2, ImageVector imageVector, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, imageVector, (i & 8) != 0 ? true : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final boolean getVisible() {
        return this.visible;
    }
}
