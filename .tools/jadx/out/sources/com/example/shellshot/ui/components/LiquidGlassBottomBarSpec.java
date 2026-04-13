package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010'\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b(\u0010\u0016J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\u0010\u0010,\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b-\u0010\u0016J\u0010\u0010.\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b/\u0010\u0016J\t\u00100\u001a\u00020\u0005HÆ\u0003J\u0010\u00101\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b2\u0010\u0016J\u0010\u00103\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b4\u0010\u0016J\u0010\u00105\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b6\u0010\u0016J\u0010\u00107\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b8\u0010\"J\u0010\u00109\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b:\u0010\"J\u0010\u0010;\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b<\u0010\"J\u0010\u0010=\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b>\u0010\"J\u009c\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000fHÆ\u0001¢\u0006\u0004\b@\u0010AJ\u0013\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020FHÖ\u0001J\t\u0010G\u001a\u00020HHÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b \u0010\u0016R\u0013\u0010\u000e\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010#\u001a\u0004\b$\u0010\"R\u0013\u0010\u0011\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010#\u001a\u0004\b%\u0010\"R\u0013\u0010\u0012\u001a\u00020\u000f¢\u0006\n\n\u0002\u0010#\u001a\u0004\b&\u0010\"¨\u0006I"}, d2 = {"Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;", "", "fauxBackdropBlur", "Landroidx/compose/ui/unit/Dp;", "fillAlpha", "", "highlightAngleDegrees", "strokeAlpha", "cornerRadius", "shadowElevation", "indicatorGlowAlpha", "indicatorInset", "indicatorCornerRadius", "barHeight", "activeContentColor", "Landroidx/compose/ui/graphics/Color;", "inactiveContentColor", "activeTint", "warmTint", "<init>", "(FFFFFFFFFFJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getFauxBackdropBlur-D9Ej5fM", "()F", "F", "getFillAlpha", "getHighlightAngleDegrees", "getStrokeAlpha", "getCornerRadius-D9Ej5fM", "getShadowElevation-D9Ej5fM", "getIndicatorGlowAlpha", "getIndicatorInset-D9Ej5fM", "getIndicatorCornerRadius-D9Ej5fM", "getBarHeight-D9Ej5fM", "getActiveContentColor-0d7_KjU", "()J", "J", "getInactiveContentColor-0d7_KjU", "getActiveTint-0d7_KjU", "getWarmTint-0d7_KjU", "component1", "component1-D9Ej5fM", "component2", "component3", "component4", "component5", "component5-D9Ej5fM", "component6", "component6-D9Ej5fM", "component7", "component8", "component8-D9Ej5fM", "component9", "component9-D9Ej5fM", "component10", "component10-D9Ej5fM", "component11", "component11-0d7_KjU", "component12", "component12-0d7_KjU", "component13", "component13-0d7_KjU", "component14", "component14-0d7_KjU", "copy", "copy-ZradBxE", "(FFFFFFFFFFJJJJ)Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class LiquidGlassBottomBarSpec {
    public static final int $stable = 0;
    private final long activeContentColor;
    private final long activeTint;
    private final float barHeight;
    private final float cornerRadius;
    private final float fauxBackdropBlur;
    private final float fillAlpha;
    private final float highlightAngleDegrees;
    private final long inactiveContentColor;
    private final float indicatorCornerRadius;
    private final float indicatorGlowAlpha;
    private final float indicatorInset;
    private final float shadowElevation;
    private final float strokeAlpha;
    private final long warmTint;

    public /* synthetic */ LiquidGlassBottomBarSpec(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, j, j2, j3, j4);
    }

    /* renamed from: copy-ZradBxE$default, reason: not valid java name */
    public static /* synthetic */ LiquidGlassBottomBarSpec m6973copyZradBxE$default(LiquidGlassBottomBarSpec liquidGlassBottomBarSpec, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, long j2, long j3, long j4, int i, Object obj) {
        long j5;
        long j6;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        long j7;
        long j8;
        float f20 = (i & 1) != 0 ? liquidGlassBottomBarSpec.fauxBackdropBlur : f;
        float f21 = (i & 2) != 0 ? liquidGlassBottomBarSpec.fillAlpha : f2;
        float f22 = (i & 4) != 0 ? liquidGlassBottomBarSpec.highlightAngleDegrees : f3;
        float f23 = (i & 8) != 0 ? liquidGlassBottomBarSpec.strokeAlpha : f4;
        float f24 = (i & 16) != 0 ? liquidGlassBottomBarSpec.cornerRadius : f5;
        float f25 = (i & 32) != 0 ? liquidGlassBottomBarSpec.shadowElevation : f6;
        float f26 = (i & 64) != 0 ? liquidGlassBottomBarSpec.indicatorGlowAlpha : f7;
        float f27 = (i & 128) != 0 ? liquidGlassBottomBarSpec.indicatorInset : f8;
        float f28 = (i & 256) != 0 ? liquidGlassBottomBarSpec.indicatorCornerRadius : f9;
        float f29 = (i & 512) != 0 ? liquidGlassBottomBarSpec.barHeight : f10;
        long j9 = (i & 1024) != 0 ? liquidGlassBottomBarSpec.activeContentColor : j;
        long j10 = (i & 2048) != 0 ? liquidGlassBottomBarSpec.inactiveContentColor : j2;
        float f30 = f20;
        float f31 = f21;
        long j11 = (i & 4096) != 0 ? liquidGlassBottomBarSpec.activeTint : j3;
        if ((i & 8192) != 0) {
            j6 = j11;
            j5 = liquidGlassBottomBarSpec.warmTint;
            f12 = f22;
            f13 = f23;
            f14 = f24;
            f15 = f25;
            f16 = f26;
            f17 = f27;
            f18 = f28;
            f19 = f29;
            j7 = j9;
            j8 = j10;
            f11 = f31;
        } else {
            j5 = j4;
            j6 = j11;
            f11 = f31;
            f12 = f22;
            f13 = f23;
            f14 = f24;
            f15 = f25;
            f16 = f26;
            f17 = f27;
            f18 = f28;
            f19 = f29;
            j7 = j9;
            j8 = j10;
        }
        return liquidGlassBottomBarSpec.m6984copyZradBxE(f30, f11, f12, f13, f14, f15, f16, f17, f18, f19, j7, j8, j6, j5);
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getFauxBackdropBlur() {
        return this.fauxBackdropBlur;
    }

    /* renamed from: component10-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBarHeight() {
        return this.barHeight;
    }

    /* renamed from: component11-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContentColor() {
        return this.activeContentColor;
    }

    /* renamed from: component12-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContentColor() {
        return this.inactiveContentColor;
    }

    /* renamed from: component13-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTint() {
        return this.activeTint;
    }

    /* renamed from: component14-0d7_KjU, reason: not valid java name and from getter */
    public final long getWarmTint() {
        return this.warmTint;
    }

    /* renamed from: component2, reason: from getter */
    public final float getFillAlpha() {
        return this.fillAlpha;
    }

    /* renamed from: component3, reason: from getter */
    public final float getHighlightAngleDegrees() {
        return this.highlightAngleDegrees;
    }

    /* renamed from: component4, reason: from getter */
    public final float getStrokeAlpha() {
        return this.strokeAlpha;
    }

    /* renamed from: component5-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    public final float getShadowElevation() {
        return this.shadowElevation;
    }

    /* renamed from: component7, reason: from getter */
    public final float getIndicatorGlowAlpha() {
        return this.indicatorGlowAlpha;
    }

    /* renamed from: component8-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorInset() {
        return this.indicatorInset;
    }

    /* renamed from: component9-D9Ej5fM, reason: not valid java name and from getter */
    public final float getIndicatorCornerRadius() {
        return this.indicatorCornerRadius;
    }

    /* renamed from: copy-ZradBxE, reason: not valid java name */
    public final LiquidGlassBottomBarSpec m6984copyZradBxE(float fauxBackdropBlur, float fillAlpha, float highlightAngleDegrees, float strokeAlpha, float cornerRadius, float shadowElevation, float indicatorGlowAlpha, float indicatorInset, float indicatorCornerRadius, float barHeight, long activeContentColor, long inactiveContentColor, long activeTint, long warmTint) {
        return new LiquidGlassBottomBarSpec(fauxBackdropBlur, fillAlpha, highlightAngleDegrees, strokeAlpha, cornerRadius, shadowElevation, indicatorGlowAlpha, indicatorInset, indicatorCornerRadius, barHeight, activeContentColor, inactiveContentColor, activeTint, warmTint, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiquidGlassBottomBarSpec)) {
            return false;
        }
        LiquidGlassBottomBarSpec liquidGlassBottomBarSpec = (LiquidGlassBottomBarSpec) other;
        return Dp.m6631equalsimpl0(this.fauxBackdropBlur, liquidGlassBottomBarSpec.fauxBackdropBlur) && Float.compare(this.fillAlpha, liquidGlassBottomBarSpec.fillAlpha) == 0 && Float.compare(this.highlightAngleDegrees, liquidGlassBottomBarSpec.highlightAngleDegrees) == 0 && Float.compare(this.strokeAlpha, liquidGlassBottomBarSpec.strokeAlpha) == 0 && Dp.m6631equalsimpl0(this.cornerRadius, liquidGlassBottomBarSpec.cornerRadius) && Dp.m6631equalsimpl0(this.shadowElevation, liquidGlassBottomBarSpec.shadowElevation) && Float.compare(this.indicatorGlowAlpha, liquidGlassBottomBarSpec.indicatorGlowAlpha) == 0 && Dp.m6631equalsimpl0(this.indicatorInset, liquidGlassBottomBarSpec.indicatorInset) && Dp.m6631equalsimpl0(this.indicatorCornerRadius, liquidGlassBottomBarSpec.indicatorCornerRadius) && Dp.m6631equalsimpl0(this.barHeight, liquidGlassBottomBarSpec.barHeight) && Color.m4164equalsimpl0(this.activeContentColor, liquidGlassBottomBarSpec.activeContentColor) && Color.m4164equalsimpl0(this.inactiveContentColor, liquidGlassBottomBarSpec.inactiveContentColor) && Color.m4164equalsimpl0(this.activeTint, liquidGlassBottomBarSpec.activeTint) && Color.m4164equalsimpl0(this.warmTint, liquidGlassBottomBarSpec.warmTint);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((Dp.m6632hashCodeimpl(this.fauxBackdropBlur) * 31) + Float.hashCode(this.fillAlpha)) * 31) + Float.hashCode(this.highlightAngleDegrees)) * 31) + Float.hashCode(this.strokeAlpha)) * 31) + Dp.m6632hashCodeimpl(this.cornerRadius)) * 31) + Dp.m6632hashCodeimpl(this.shadowElevation)) * 31) + Float.hashCode(this.indicatorGlowAlpha)) * 31) + Dp.m6632hashCodeimpl(this.indicatorInset)) * 31) + Dp.m6632hashCodeimpl(this.indicatorCornerRadius)) * 31) + Dp.m6632hashCodeimpl(this.barHeight)) * 31) + Color.m4170hashCodeimpl(this.activeContentColor)) * 31) + Color.m4170hashCodeimpl(this.inactiveContentColor)) * 31) + Color.m4170hashCodeimpl(this.activeTint)) * 31) + Color.m4170hashCodeimpl(this.warmTint);
    }

    public String toString() {
        return "LiquidGlassBottomBarSpec(fauxBackdropBlur=" + Dp.m6637toStringimpl(this.fauxBackdropBlur) + ", fillAlpha=" + this.fillAlpha + ", highlightAngleDegrees=" + this.highlightAngleDegrees + ", strokeAlpha=" + this.strokeAlpha + ", cornerRadius=" + Dp.m6637toStringimpl(this.cornerRadius) + ", shadowElevation=" + Dp.m6637toStringimpl(this.shadowElevation) + ", indicatorGlowAlpha=" + this.indicatorGlowAlpha + ", indicatorInset=" + Dp.m6637toStringimpl(this.indicatorInset) + ", indicatorCornerRadius=" + Dp.m6637toStringimpl(this.indicatorCornerRadius) + ", barHeight=" + Dp.m6637toStringimpl(this.barHeight) + ", activeContentColor=" + Color.m4171toStringimpl(this.activeContentColor) + ", inactiveContentColor=" + Color.m4171toStringimpl(this.inactiveContentColor) + ", activeTint=" + Color.m4171toStringimpl(this.activeTint) + ", warmTint=" + Color.m4171toStringimpl(this.warmTint) + ")";
    }

    private LiquidGlassBottomBarSpec(float fauxBackdropBlur, float fillAlpha, float highlightAngleDegrees, float strokeAlpha, float cornerRadius, float shadowElevation, float indicatorGlowAlpha, float indicatorInset, float indicatorCornerRadius, float barHeight, long activeContentColor, long inactiveContentColor, long activeTint, long warmTint) {
        this.fauxBackdropBlur = fauxBackdropBlur;
        this.fillAlpha = fillAlpha;
        this.highlightAngleDegrees = highlightAngleDegrees;
        this.strokeAlpha = strokeAlpha;
        this.cornerRadius = cornerRadius;
        this.shadowElevation = shadowElevation;
        this.indicatorGlowAlpha = indicatorGlowAlpha;
        this.indicatorInset = indicatorInset;
        this.indicatorCornerRadius = indicatorCornerRadius;
        this.barHeight = barHeight;
        this.activeContentColor = activeContentColor;
        this.inactiveContentColor = inactiveContentColor;
        this.activeTint = activeTint;
        this.warmTint = warmTint;
    }

    public /* synthetic */ LiquidGlassBottomBarSpec(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, long j2, long j3, long j4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.m6626constructorimpl(24) : f, (i & 2) != 0 ? 0.82f : f2, (i & 4) != 0 ? 14.0f : f3, (i & 8) != 0 ? 0.88f : f4, (i & 16) != 0 ? Dp.m6626constructorimpl(34) : f5, (i & 32) != 0 ? Dp.m6626constructorimpl(12) : f6, (i & 64) != 0 ? 0.3f : f7, (i & 128) != 0 ? Dp.m6626constructorimpl(6) : f8, (i & 256) != 0 ? Dp.m6626constructorimpl(28) : f9, (i & 512) != 0 ? Dp.m6626constructorimpl(82) : f10, (i & 1024) != 0 ? ColorKt.Color(4280165687L) : j, (i & 2048) != 0 ? ColorKt.Color(4284706951L) : j2, (i & 4096) != 0 ? ColorKt.Color(4291817471L) : j3, (i & 8192) != 0 ? ColorKt.Color(4294438370L) : j4, null);
    }

    /* renamed from: getFauxBackdropBlur-D9Ej5fM, reason: not valid java name */
    public final float m6989getFauxBackdropBlurD9Ej5fM() {
        return this.fauxBackdropBlur;
    }

    public final float getFillAlpha() {
        return this.fillAlpha;
    }

    public final float getHighlightAngleDegrees() {
        return this.highlightAngleDegrees;
    }

    public final float getStrokeAlpha() {
        return this.strokeAlpha;
    }

    /* renamed from: getCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m6988getCornerRadiusD9Ej5fM() {
        return this.cornerRadius;
    }

    /* renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m6993getShadowElevationD9Ej5fM() {
        return this.shadowElevation;
    }

    public final float getIndicatorGlowAlpha() {
        return this.indicatorGlowAlpha;
    }

    /* renamed from: getIndicatorInset-D9Ej5fM, reason: not valid java name */
    public final float m6992getIndicatorInsetD9Ej5fM() {
        return this.indicatorInset;
    }

    /* renamed from: getIndicatorCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m6991getIndicatorCornerRadiusD9Ej5fM() {
        return this.indicatorCornerRadius;
    }

    /* renamed from: getBarHeight-D9Ej5fM, reason: not valid java name */
    public final float m6987getBarHeightD9Ej5fM() {
        return this.barHeight;
    }

    /* renamed from: getActiveContentColor-0d7_KjU, reason: not valid java name */
    public final long m6985getActiveContentColor0d7_KjU() {
        return this.activeContentColor;
    }

    /* renamed from: getInactiveContentColor-0d7_KjU, reason: not valid java name */
    public final long m6990getInactiveContentColor0d7_KjU() {
        return this.inactiveContentColor;
    }

    /* renamed from: getActiveTint-0d7_KjU, reason: not valid java name */
    public final long m6986getActiveTint0d7_KjU() {
        return this.activeTint;
    }

    /* renamed from: getWarmTint-0d7_KjU, reason: not valid java name */
    public final long m6994getWarmTint0d7_KjU() {
        return this.warmTint;
    }
}
