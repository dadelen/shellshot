package com.example.shellshot.ui.components;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AppComponents.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b=\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BÅ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u00100\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b1\u0010\u001bJ\u0010\u00102\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b3\u0010\u001bJ\u0010\u00104\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b5\u0010\u001bJ\u0010\u00106\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b7\u0010\u001bJ\u0010\u00108\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b9\u0010\u001bJ\t\u0010:\u001a\u00020\tHÆ\u0003J\t\u0010;\u001a\u00020\tHÆ\u0003J\t\u0010<\u001a\u00020\tHÆ\u0003J\u0010\u0010=\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b>\u0010\u001bJ\u0010\u0010?\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b@\u0010\u001bJ\u0010\u0010A\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\bB\u0010\u001bJ\u0010\u0010C\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\bD\u0010\u001bJ\t\u0010E\u001a\u00020\tHÆ\u0003J\u0010\u0010F\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\bG\u0010\u001bJ\t\u0010H\u001a\u00020\u0013HÆ\u0003J\t\u0010I\u001a\u00020\tHÆ\u0003J\t\u0010J\u001a\u00020\tHÆ\u0003J\t\u0010K\u001a\u00020\tHÆ\u0003J\t\u0010L\u001a\u00020\tHÆ\u0003JÎ\u0001\u0010M\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\tHÆ\u0001¢\u0006\u0004\bN\u0010OJ\u0013\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010S\u001a\u00020\u0013HÖ\u0001J\t\u0010T\u001a\u00020UHÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001e\u0010\u001bR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001f\u0010\u001bR\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b \u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b$\u0010\u001bR\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b%\u0010\u001bR\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b&\u0010\u001bR\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b'\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b)\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001bR\u0011\u0010\u0015\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001bR\u0011\u0010\u0017\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001b¨\u0006V"}, d2 = {"Lcom/example/shellshot/ui/components/LiquidGlassBottomBarStyle;", "", "barHeight", "Landroidx/compose/ui/unit/Dp;", "cornerRadius", "horizontalInset", "verticalInset", "borderWidth", "borderAlpha", "", "containerAlpha", "highlightAlpha", "fauxBlurRadius", "floatingShadow", "indicatorCornerRadius", "indicatorShadow", "indicatorStretchFactor", "maxStretch", "motionDurationMillis", "", "unselectedScale", "selectedScale", "unselectedContentAlpha", "selectedContentAlpha", "<init>", "(FFFFFFFFFFFFFFIFFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBarHeight-D9Ej5fM", "()F", "F", "getCornerRadius-D9Ej5fM", "getHorizontalInset-D9Ej5fM", "getVerticalInset-D9Ej5fM", "getBorderWidth-D9Ej5fM", "getBorderAlpha", "getContainerAlpha", "getHighlightAlpha", "getFauxBlurRadius-D9Ej5fM", "getFloatingShadow-D9Ej5fM", "getIndicatorCornerRadius-D9Ej5fM", "getIndicatorShadow-D9Ej5fM", "getIndicatorStretchFactor", "getMaxStretch-D9Ej5fM", "getMotionDurationMillis", "()I", "getUnselectedScale", "getSelectedScale", "getUnselectedContentAlpha", "getSelectedContentAlpha", "component1", "component1-D9Ej5fM", "component2", "component2-D9Ej5fM", "component3", "component3-D9Ej5fM", "component4", "component4-D9Ej5fM", "component5", "component5-D9Ej5fM", "component6", "component7", "component8", "component9", "component9-D9Ej5fM", "component10", "component10-D9Ej5fM", "component11", "component11-D9Ej5fM", "component12", "component12-D9Ej5fM", "component13", "component14", "component14-D9Ej5fM", "component15", "component16", "component17", "component18", "component19", "copy", "copy-S5OhADA", "(FFFFFFFFFFFFFFIFFFF)Lcom/example/shellshot/ui/components/LiquidGlassBottomBarStyle;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class LiquidGlassBottomBarStyle {
    public static final int $stable = 0;
    private final float barHeight;
    private final float borderAlpha;
    private final float borderWidth;
    private final float containerAlpha;
    private final float cornerRadius;
    private final float fauxBlurRadius;
    private final float floatingShadow;
    private final float highlightAlpha;
    private final float horizontalInset;
    private final float indicatorCornerRadius;
    private final float indicatorShadow;
    private final float indicatorStretchFactor;
    private final float maxStretch;
    private final int motionDurationMillis;
    private final float selectedContentAlpha;
    private final float selectedScale;
    private final float unselectedContentAlpha;
    private final float unselectedScale;
    private final float verticalInset;

    public /* synthetic */ LiquidGlassBottomBarStyle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int i, float f15, float f16, float f17, float f18, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, i, f15, f16, f17, f18);
    }

    /* renamed from: copy-S5OhADA$default, reason: not valid java name */
    public static /* synthetic */ LiquidGlassBottomBarStyle m6995copyS5OhADA$default(LiquidGlassBottomBarStyle liquidGlassBottomBarStyle, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int i, float f15, float f16, float f17, float f18, int i2, Object obj) {
        float f19;
        float f20;
        float f21 = (i2 & 1) != 0 ? liquidGlassBottomBarStyle.barHeight : f;
        float f22 = (i2 & 2) != 0 ? liquidGlassBottomBarStyle.cornerRadius : f2;
        float f23 = (i2 & 4) != 0 ? liquidGlassBottomBarStyle.horizontalInset : f3;
        float f24 = (i2 & 8) != 0 ? liquidGlassBottomBarStyle.verticalInset : f4;
        float f25 = (i2 & 16) != 0 ? liquidGlassBottomBarStyle.borderWidth : f5;
        float f26 = (i2 & 32) != 0 ? liquidGlassBottomBarStyle.borderAlpha : f6;
        float f27 = (i2 & 64) != 0 ? liquidGlassBottomBarStyle.containerAlpha : f7;
        float f28 = (i2 & 128) != 0 ? liquidGlassBottomBarStyle.highlightAlpha : f8;
        float f29 = (i2 & 256) != 0 ? liquidGlassBottomBarStyle.fauxBlurRadius : f9;
        float f30 = (i2 & 512) != 0 ? liquidGlassBottomBarStyle.floatingShadow : f10;
        float f31 = (i2 & 1024) != 0 ? liquidGlassBottomBarStyle.indicatorCornerRadius : f11;
        float f32 = (i2 & 2048) != 0 ? liquidGlassBottomBarStyle.indicatorShadow : f12;
        float f33 = (i2 & 4096) != 0 ? liquidGlassBottomBarStyle.indicatorStretchFactor : f13;
        float f34 = (i2 & 8192) != 0 ? liquidGlassBottomBarStyle.maxStretch : f14;
        float f35 = f21;
        int i3 = (i2 & 16384) != 0 ? liquidGlassBottomBarStyle.motionDurationMillis : i;
        float f36 = (i2 & 32768) != 0 ? liquidGlassBottomBarStyle.unselectedScale : f15;
        float f37 = (i2 & 65536) != 0 ? liquidGlassBottomBarStyle.selectedScale : f16;
        float f38 = (i2 & 131072) != 0 ? liquidGlassBottomBarStyle.unselectedContentAlpha : f17;
        if ((i2 & 262144) != 0) {
            f20 = f38;
            f19 = liquidGlassBottomBarStyle.selectedContentAlpha;
        } else {
            f19 = f18;
            f20 = f38;
        }
        return liquidGlassBottomBarStyle.m7006copyS5OhADA(f35, f22, f23, f24, f25, f26, f27, f28, f29, f30, f31, f32, f33, f34, i3, f36, f37, f20, f19);
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBarHeight() {
        return this.barHeight;
    }

    /* renamed from: component10-D9Ej5fM, reason: not valid java name and from getter */
    public final float getFloatingShadow() {
        return this.floatingShadow;
    }

    /* renamed from: component11-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorCornerRadius() {
        return this.indicatorCornerRadius;
    }

    /* renamed from: component12-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorShadow() {
        return this.indicatorShadow;
    }

    /* renamed from: component13, reason: from getter */
    public final float getIndicatorStretchFactor() {
        return this.indicatorStretchFactor;
    }

    /* renamed from: component14-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxStretch() {
        return this.maxStretch;
    }

    /* renamed from: component15, reason: from getter */
    public final int getMotionDurationMillis() {
        return this.motionDurationMillis;
    }

    /* renamed from: component16, reason: from getter */
    public final float getUnselectedScale() {
        return this.unselectedScale;
    }

    /* renamed from: component17, reason: from getter */
    public final float getSelectedScale() {
        return this.selectedScale;
    }

    /* renamed from: component18, reason: from getter */
    public final float getUnselectedContentAlpha() {
        return this.unselectedContentAlpha;
    }

    /* renamed from: component19, reason: from getter */
    public final float getSelectedContentAlpha() {
        return this.selectedContentAlpha;
    }

    /* renamed from: component2-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: component3-D9Ej5fM, reason: not valid java name and from getter */
    public final float getHorizontalInset() {
        return this.horizontalInset;
    }

    /* renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    public final float getVerticalInset() {
        return this.verticalInset;
    }

    /* renamed from: component5-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBorderWidth() {
        return this.borderWidth;
    }

    /* renamed from: component6, reason: from getter */
    public final float getBorderAlpha() {
        return this.borderAlpha;
    }

    /* renamed from: component7, reason: from getter */
    public final float getContainerAlpha() {
        return this.containerAlpha;
    }

    /* renamed from: component8, reason: from getter */
    public final float getHighlightAlpha() {
        return this.highlightAlpha;
    }

    /* renamed from: component9-D9Ej5fM, reason: not valid java name and from getter */
    public final float getFauxBlurRadius() {
        return this.fauxBlurRadius;
    }

    /* renamed from: copy-S5OhADA, reason: not valid java name */
    public final LiquidGlassBottomBarStyle m7006copyS5OhADA(float barHeight, float cornerRadius, float horizontalInset, float verticalInset, float borderWidth, float borderAlpha, float containerAlpha, float highlightAlpha, float fauxBlurRadius, float floatingShadow, float indicatorCornerRadius, float indicatorShadow, float indicatorStretchFactor, float maxStretch, int motionDurationMillis, float unselectedScale, float selectedScale, float unselectedContentAlpha, float selectedContentAlpha) {
        return new LiquidGlassBottomBarStyle(barHeight, cornerRadius, horizontalInset, verticalInset, borderWidth, borderAlpha, containerAlpha, highlightAlpha, fauxBlurRadius, floatingShadow, indicatorCornerRadius, indicatorShadow, indicatorStretchFactor, maxStretch, motionDurationMillis, unselectedScale, selectedScale, unselectedContentAlpha, selectedContentAlpha, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiquidGlassBottomBarStyle)) {
            return false;
        }
        LiquidGlassBottomBarStyle liquidGlassBottomBarStyle = (LiquidGlassBottomBarStyle) other;
        return Dp.m6631equalsimpl0(this.barHeight, liquidGlassBottomBarStyle.barHeight) && Dp.m6631equalsimpl0(this.cornerRadius, liquidGlassBottomBarStyle.cornerRadius) && Dp.m6631equalsimpl0(this.horizontalInset, liquidGlassBottomBarStyle.horizontalInset) && Dp.m6631equalsimpl0(this.verticalInset, liquidGlassBottomBarStyle.verticalInset) && Dp.m6631equalsimpl0(this.borderWidth, liquidGlassBottomBarStyle.borderWidth) && Float.compare(this.borderAlpha, liquidGlassBottomBarStyle.borderAlpha) == 0 && Float.compare(this.containerAlpha, liquidGlassBottomBarStyle.containerAlpha) == 0 && Float.compare(this.highlightAlpha, liquidGlassBottomBarStyle.highlightAlpha) == 0 && Dp.m6631equalsimpl0(this.fauxBlurRadius, liquidGlassBottomBarStyle.fauxBlurRadius) && Dp.m6631equalsimpl0(this.floatingShadow, liquidGlassBottomBarStyle.floatingShadow) && Dp.m6631equalsimpl0(this.indicatorCornerRadius, liquidGlassBottomBarStyle.indicatorCornerRadius) && Dp.m6631equalsimpl0(this.indicatorShadow, liquidGlassBottomBarStyle.indicatorShadow) && Float.compare(this.indicatorStretchFactor, liquidGlassBottomBarStyle.indicatorStretchFactor) == 0 && Dp.m6631equalsimpl0(this.maxStretch, liquidGlassBottomBarStyle.maxStretch) && this.motionDurationMillis == liquidGlassBottomBarStyle.motionDurationMillis && Float.compare(this.unselectedScale, liquidGlassBottomBarStyle.unselectedScale) == 0 && Float.compare(this.selectedScale, liquidGlassBottomBarStyle.selectedScale) == 0 && Float.compare(this.unselectedContentAlpha, liquidGlassBottomBarStyle.unselectedContentAlpha) == 0 && Float.compare(this.selectedContentAlpha, liquidGlassBottomBarStyle.selectedContentAlpha) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Dp.m6632hashCodeimpl(this.barHeight) * 31) + Dp.m6632hashCodeimpl(this.cornerRadius)) * 31) + Dp.m6632hashCodeimpl(this.horizontalInset)) * 31) + Dp.m6632hashCodeimpl(this.verticalInset)) * 31) + Dp.m6632hashCodeimpl(this.borderWidth)) * 31) + Float.hashCode(this.borderAlpha)) * 31) + Float.hashCode(this.containerAlpha)) * 31) + Float.hashCode(this.highlightAlpha)) * 31) + Dp.m6632hashCodeimpl(this.fauxBlurRadius)) * 31) + Dp.m6632hashCodeimpl(this.floatingShadow)) * 31) + Dp.m6632hashCodeimpl(this.indicatorCornerRadius)) * 31) + Dp.m6632hashCodeimpl(this.indicatorShadow)) * 31) + Float.hashCode(this.indicatorStretchFactor)) * 31) + Dp.m6632hashCodeimpl(this.maxStretch)) * 31) + Integer.hashCode(this.motionDurationMillis)) * 31) + Float.hashCode(this.unselectedScale)) * 31) + Float.hashCode(this.selectedScale)) * 31) + Float.hashCode(this.unselectedContentAlpha)) * 31) + Float.hashCode(this.selectedContentAlpha);
    }

    public String toString() {
        return "LiquidGlassBottomBarStyle(barHeight=" + Dp.m6637toStringimpl(this.barHeight) + ", cornerRadius=" + Dp.m6637toStringimpl(this.cornerRadius) + ", horizontalInset=" + Dp.m6637toStringimpl(this.horizontalInset) + ", verticalInset=" + Dp.m6637toStringimpl(this.verticalInset) + ", borderWidth=" + Dp.m6637toStringimpl(this.borderWidth) + ", borderAlpha=" + this.borderAlpha + ", containerAlpha=" + this.containerAlpha + ", highlightAlpha=" + this.highlightAlpha + ", fauxBlurRadius=" + Dp.m6637toStringimpl(this.fauxBlurRadius) + ", floatingShadow=" + Dp.m6637toStringimpl(this.floatingShadow) + ", indicatorCornerRadius=" + Dp.m6637toStringimpl(this.indicatorCornerRadius) + ", indicatorShadow=" + Dp.m6637toStringimpl(this.indicatorShadow) + ", indicatorStretchFactor=" + this.indicatorStretchFactor + ", maxStretch=" + Dp.m6637toStringimpl(this.maxStretch) + ", motionDurationMillis=" + this.motionDurationMillis + ", unselectedScale=" + this.unselectedScale + ", selectedScale=" + this.selectedScale + ", unselectedContentAlpha=" + this.unselectedContentAlpha + ", selectedContentAlpha=" + this.selectedContentAlpha + ")";
    }

    private LiquidGlassBottomBarStyle(float barHeight, float cornerRadius, float horizontalInset, float verticalInset, float borderWidth, float borderAlpha, float containerAlpha, float highlightAlpha, float fauxBlurRadius, float floatingShadow, float indicatorCornerRadius, float indicatorShadow, float indicatorStretchFactor, float maxStretch, int motionDurationMillis, float unselectedScale, float selectedScale, float unselectedContentAlpha, float selectedContentAlpha) {
        this.barHeight = barHeight;
        this.cornerRadius = cornerRadius;
        this.horizontalInset = horizontalInset;
        this.verticalInset = verticalInset;
        this.borderWidth = borderWidth;
        this.borderAlpha = borderAlpha;
        this.containerAlpha = containerAlpha;
        this.highlightAlpha = highlightAlpha;
        this.fauxBlurRadius = fauxBlurRadius;
        this.floatingShadow = floatingShadow;
        this.indicatorCornerRadius = indicatorCornerRadius;
        this.indicatorShadow = indicatorShadow;
        this.indicatorStretchFactor = indicatorStretchFactor;
        this.maxStretch = maxStretch;
        this.motionDurationMillis = motionDurationMillis;
        this.unselectedScale = unselectedScale;
        this.selectedScale = selectedScale;
        this.unselectedContentAlpha = unselectedContentAlpha;
        this.selectedContentAlpha = selectedContentAlpha;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ LiquidGlassBottomBarStyle(float r21, float r22, float r23, float r24, float r25, float r26, float r27, float r28, float r29, float r30, float r31, float r32, float r33, float r34, int r35, float r36, float r37, float r38, float r39, int r40, kotlin.jvm.internal.DefaultConstructorMarker r41) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.LiquidGlassBottomBarStyle.<init>(float, float, float, float, float, float, float, float, float, float, float, float, float, float, int, float, float, float, float, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* renamed from: getBarHeight-D9Ej5fM, reason: not valid java name */
    public final float m7007getBarHeightD9Ej5fM() {
        return this.barHeight;
    }

    /* renamed from: getCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m7009getCornerRadiusD9Ej5fM() {
        return this.cornerRadius;
    }

    /* renamed from: getHorizontalInset-D9Ej5fM, reason: not valid java name */
    public final float m7012getHorizontalInsetD9Ej5fM() {
        return this.horizontalInset;
    }

    /* renamed from: getVerticalInset-D9Ej5fM, reason: not valid java name */
    public final float m7016getVerticalInsetD9Ej5fM() {
        return this.verticalInset;
    }

    /* renamed from: getBorderWidth-D9Ej5fM, reason: not valid java name */
    public final float m7008getBorderWidthD9Ej5fM() {
        return this.borderWidth;
    }

    public final float getBorderAlpha() {
        return this.borderAlpha;
    }

    public final float getContainerAlpha() {
        return this.containerAlpha;
    }

    public final float getHighlightAlpha() {
        return this.highlightAlpha;
    }

    /* renamed from: getFauxBlurRadius-D9Ej5fM, reason: not valid java name */
    public final float m7010getFauxBlurRadiusD9Ej5fM() {
        return this.fauxBlurRadius;
    }

    /* renamed from: getFloatingShadow-D9Ej5fM, reason: not valid java name */
    public final float m7011getFloatingShadowD9Ej5fM() {
        return this.floatingShadow;
    }

    /* renamed from: getIndicatorCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m7013getIndicatorCornerRadiusD9Ej5fM() {
        return this.indicatorCornerRadius;
    }

    /* renamed from: getIndicatorShadow-D9Ej5fM, reason: not valid java name */
    public final float m7014getIndicatorShadowD9Ej5fM() {
        return this.indicatorShadow;
    }

    public final float getIndicatorStretchFactor() {
        return this.indicatorStretchFactor;
    }

    /* renamed from: getMaxStretch-D9Ej5fM, reason: not valid java name */
    public final float m7015getMaxStretchD9Ej5fM() {
        return this.maxStretch;
    }

    public final int getMotionDurationMillis() {
        return this.motionDurationMillis;
    }

    public final float getUnselectedScale() {
        return this.unselectedScale;
    }

    public final float getSelectedScale() {
        return this.selectedScale;
    }

    public final float getUnselectedContentAlpha() {
        return this.unselectedContentAlpha;
    }

    public final float getSelectedContentAlpha() {
        return this.selectedContentAlpha;
    }
}
