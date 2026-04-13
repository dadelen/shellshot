package com.example.shellshot.ui.components;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0083\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0010\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0012\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0014\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0016\u0010\nJ8\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\n¨\u0006!"}, d2 = {"Lcom/example/shellshot/ui/components/AppleLiquidNavBarMetrics;", "", "barHeight", "Landroidx/compose/ui/unit/Dp;", "pillHorizontalInset", "pillVerticalInset", "iconSize", "<init>", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBarHeight-D9Ej5fM", "()F", "F", "getPillHorizontalInset-D9Ej5fM", "getPillVerticalInset-D9Ej5fM", "getIconSize-D9Ej5fM", "component1", "component1-D9Ej5fM", "component2", "component2-D9Ej5fM", "component3", "component3-D9Ej5fM", "component4", "component4-D9Ej5fM", "copy", "copy-a9UjIt4", "(FFFF)Lcom/example/shellshot/ui/components/AppleLiquidNavBarMetrics;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
final /* data */ class AppleLiquidNavBarMetrics {
    private final float barHeight;
    private final float iconSize;
    private final float pillHorizontalInset;
    private final float pillVerticalInset;

    public /* synthetic */ AppleLiquidNavBarMetrics(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    /* renamed from: copy-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ AppleLiquidNavBarMetrics m6929copya9UjIt4$default(AppleLiquidNavBarMetrics appleLiquidNavBarMetrics, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = appleLiquidNavBarMetrics.barHeight;
        }
        if ((i & 2) != 0) {
            f2 = appleLiquidNavBarMetrics.pillHorizontalInset;
        }
        if ((i & 4) != 0) {
            f3 = appleLiquidNavBarMetrics.pillVerticalInset;
        }
        if ((i & 8) != 0) {
            f4 = appleLiquidNavBarMetrics.iconSize;
        }
        return appleLiquidNavBarMetrics.m6934copya9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBarHeight() {
        return this.barHeight;
    }

    /* renamed from: component2-D9Ej5fM, reason: not valid java name and from getter */
    public final float getPillHorizontalInset() {
        return this.pillHorizontalInset;
    }

    /* renamed from: component3-D9Ej5fM, reason: not valid java name and from getter */
    public final float getPillVerticalInset() {
        return this.pillVerticalInset;
    }

    /* renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIconSize() {
        return this.iconSize;
    }

    /* renamed from: copy-a9UjIt4, reason: not valid java name */
    public final AppleLiquidNavBarMetrics m6934copya9UjIt4(float barHeight, float pillHorizontalInset, float pillVerticalInset, float iconSize) {
        return new AppleLiquidNavBarMetrics(barHeight, pillHorizontalInset, pillVerticalInset, iconSize, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppleLiquidNavBarMetrics)) {
            return false;
        }
        AppleLiquidNavBarMetrics appleLiquidNavBarMetrics = (AppleLiquidNavBarMetrics) other;
        return Dp.m6631equalsimpl0(this.barHeight, appleLiquidNavBarMetrics.barHeight) && Dp.m6631equalsimpl0(this.pillHorizontalInset, appleLiquidNavBarMetrics.pillHorizontalInset) && Dp.m6631equalsimpl0(this.pillVerticalInset, appleLiquidNavBarMetrics.pillVerticalInset) && Dp.m6631equalsimpl0(this.iconSize, appleLiquidNavBarMetrics.iconSize);
    }

    public int hashCode() {
        return (((((Dp.m6632hashCodeimpl(this.barHeight) * 31) + Dp.m6632hashCodeimpl(this.pillHorizontalInset)) * 31) + Dp.m6632hashCodeimpl(this.pillVerticalInset)) * 31) + Dp.m6632hashCodeimpl(this.iconSize);
    }

    public String toString() {
        return "AppleLiquidNavBarMetrics(barHeight=" + Dp.m6637toStringimpl(this.barHeight) + ", pillHorizontalInset=" + Dp.m6637toStringimpl(this.pillHorizontalInset) + ", pillVerticalInset=" + Dp.m6637toStringimpl(this.pillVerticalInset) + ", iconSize=" + Dp.m6637toStringimpl(this.iconSize) + ")";
    }

    private AppleLiquidNavBarMetrics(float barHeight, float pillHorizontalInset, float pillVerticalInset, float iconSize) {
        this.barHeight = barHeight;
        this.pillHorizontalInset = pillHorizontalInset;
        this.pillVerticalInset = pillVerticalInset;
        this.iconSize = iconSize;
    }

    public /* synthetic */ AppleLiquidNavBarMetrics(float f, float f2, float f3, float f4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.m6626constructorimpl(84) : f, (i & 2) != 0 ? Dp.m6626constructorimpl(6) : f2, (i & 4) != 0 ? Dp.m6626constructorimpl(6) : f3, (i & 8) != 0 ? Dp.m6626constructorimpl(24) : f4, null);
    }

    /* renamed from: getBarHeight-D9Ej5fM, reason: not valid java name */
    public final float m6935getBarHeightD9Ej5fM() {
        return this.barHeight;
    }

    /* renamed from: getPillHorizontalInset-D9Ej5fM, reason: not valid java name */
    public final float m6937getPillHorizontalInsetD9Ej5fM() {
        return this.pillHorizontalInset;
    }

    /* renamed from: getPillVerticalInset-D9Ej5fM, reason: not valid java name */
    public final float m6938getPillVerticalInsetD9Ej5fM() {
        return this.pillVerticalInset;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m6936getIconSizeD9Ej5fM() {
        return this.iconSize;
    }
}
