package com.example.shellshot.ui.theme;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DesignTokens.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0010\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0011\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0013\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0015\u0010\rJ.\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\r¨\u0006 "}, d2 = {"Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;", "", "elevation", "Landroidx/compose/ui/unit/Dp;", "ambientColor", "Landroidx/compose/ui/graphics/Color;", "spotColor", "<init>", "(FJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getElevation-D9Ej5fM", "()F", "F", "getAmbientColor-0d7_KjU", "()J", "J", "getSpotColor-0d7_KjU", "component1", "component1-D9Ej5fM", "component2", "component2-0d7_KjU", "component3", "component3-0d7_KjU", "copy", "copy-hOCwus4", "(FJJ)Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class ShellShotShadowStyle {
    public static final int $stable = 0;
    private final long ambientColor;
    private final float elevation;
    private final long spotColor;

    public /* synthetic */ ShellShotShadowStyle(float f, long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, j, j2);
    }

    /* renamed from: copy-hOCwus4$default, reason: not valid java name */
    public static /* synthetic */ ShellShotShadowStyle m7093copyhOCwus4$default(ShellShotShadowStyle shellShotShadowStyle, float f, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = shellShotShadowStyle.elevation;
        }
        if ((i & 2) != 0) {
            j = shellShotShadowStyle.ambientColor;
        }
        if ((i & 4) != 0) {
            j2 = shellShotShadowStyle.spotColor;
        }
        return shellShotShadowStyle.m7097copyhOCwus4(f, j, j2);
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getElevation() {
        return this.elevation;
    }

    /* renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
    public final long getAmbientColor() {
        return this.ambientColor;
    }

    /* renamed from: component3-0d7_KjU, reason: not valid java name and from getter */
    public final long getSpotColor() {
        return this.spotColor;
    }

    /* renamed from: copy-hOCwus4, reason: not valid java name */
    public final ShellShotShadowStyle m7097copyhOCwus4(float elevation, long ambientColor, long spotColor) {
        return new ShellShotShadowStyle(elevation, ambientColor, spotColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShellShotShadowStyle)) {
            return false;
        }
        ShellShotShadowStyle shellShotShadowStyle = (ShellShotShadowStyle) other;
        return Dp.m6631equalsimpl0(this.elevation, shellShotShadowStyle.elevation) && Color.m4164equalsimpl0(this.ambientColor, shellShotShadowStyle.ambientColor) && Color.m4164equalsimpl0(this.spotColor, shellShotShadowStyle.spotColor);
    }

    public int hashCode() {
        return (((Dp.m6632hashCodeimpl(this.elevation) * 31) + Color.m4170hashCodeimpl(this.ambientColor)) * 31) + Color.m4170hashCodeimpl(this.spotColor);
    }

    public String toString() {
        return "ShellShotShadowStyle(elevation=" + Dp.m6637toStringimpl(this.elevation) + ", ambientColor=" + Color.m4171toStringimpl(this.ambientColor) + ", spotColor=" + Color.m4171toStringimpl(this.spotColor) + ")";
    }

    private ShellShotShadowStyle(float elevation, long ambientColor, long spotColor) {
        this.elevation = elevation;
        this.ambientColor = ambientColor;
        this.spotColor = spotColor;
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m7099getElevationD9Ej5fM() {
        return this.elevation;
    }

    /* renamed from: getAmbientColor-0d7_KjU, reason: not valid java name */
    public final long m7098getAmbientColor0d7_KjU() {
        return this.ambientColor;
    }

    /* renamed from: getSpotColor-0d7_KjU, reason: not valid java name */
    public final long m7100getSpotColor0d7_KjU() {
        return this.spotColor;
    }
}
