package com.example.shellshot.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import com.example.shellshot.data.AppPrefs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b:\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B±\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010/\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b0\u0010\u001aJ\u0010\u00101\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b2\u0010\u001aJ\t\u00103\u001a\u00020\u0006HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\t\u00105\u001a\u00020\u0006HÆ\u0003J\u0010\u00106\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b7\u0010\u001aJ\u0010\u00108\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b9\u0010\u001aJ\u0010\u0010:\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b;\u0010\u001aJ\u0010\u0010<\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b=\u0010\u001aJ\u0010\u0010>\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b?\u0010\u001aJ\u0010\u0010@\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\bA\u0010\u001aJ\t\u0010B\u001a\u00020\u0006HÆ\u0003J\t\u0010C\u001a\u00020\u0011HÆ\u0003J\t\u0010D\u001a\u00020\u0006HÆ\u0003J\u0010\u0010E\u001a\u00020\u0014HÆ\u0003¢\u0006\u0004\bF\u0010+J\u0010\u0010G\u001a\u00020\u0014HÆ\u0003¢\u0006\u0004\bH\u0010+J\u0010\u0010I\u001a\u00020\u0014HÆ\u0003¢\u0006\u0004\bJ\u0010+Jº\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014HÆ\u0001¢\u0006\u0004\bL\u0010MJ\u0013\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Q\u001a\u00020\u0011HÖ\u0001J\t\u0010R\u001a\u00020SHÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b \u0010\u001aR\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b!\u0010\u001aR\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\"\u0010\u001aR\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b#\u0010\u001aR\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b$\u0010\u001aR\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0013\u0010\u0013\u001a\u00020\u0014¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0013\u0010\u0015\u001a\u00020\u0014¢\u0006\n\n\u0002\u0010,\u001a\u0004\b-\u0010+R\u0013\u0010\u0016\u001a\u00020\u0014¢\u0006\n\n\u0002\u0010,\u001a\u0004\b.\u0010+¨\u0006T"}, d2 = {"Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;", "", "shellBlur", "Landroidx/compose/ui/unit/Dp;", "liquidBlur", "shellAlpha", "", "highlightAngleDegrees", "strokeAlpha", "cornerRadius", "shadowElevation", "width", "height", "thumbDiameter", "thumbStrokeWidth", "pressedScale", "animationDurationMillis", "", "disabledAlpha", "activeTint", "Landroidx/compose/ui/graphics/Color;", "activeTintEdge", "inactiveTint", "<init>", "(FFFFFFFFFFFFIFJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getShellBlur-D9Ej5fM", "()F", "F", "getLiquidBlur-D9Ej5fM", "getShellAlpha", "getHighlightAngleDegrees", "getStrokeAlpha", "getCornerRadius-D9Ej5fM", "getShadowElevation-D9Ej5fM", "getWidth-D9Ej5fM", "getHeight-D9Ej5fM", "getThumbDiameter-D9Ej5fM", "getThumbStrokeWidth-D9Ej5fM", "getPressedScale", "getAnimationDurationMillis", "()I", "getDisabledAlpha", "getActiveTint-0d7_KjU", "()J", "J", "getActiveTintEdge-0d7_KjU", "getInactiveTint-0d7_KjU", "component1", "component1-D9Ej5fM", "component2", "component2-D9Ej5fM", "component3", "component4", "component5", "component6", "component6-D9Ej5fM", "component7", "component7-D9Ej5fM", "component8", "component8-D9Ej5fM", "component9", "component9-D9Ej5fM", "component10", "component10-D9Ej5fM", "component11", "component11-D9Ej5fM", "component12", "component13", "component14", "component15", "component15-0d7_KjU", "component16", "component16-0d7_KjU", "component17", "component17-0d7_KjU", "copy", "copy-uAEEYxs", "(FFFFFFFFFFFFIFJJJ)Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class LegacyLiquidGlassSwitchSpec {
    public static final int $stable = 0;
    private final long activeTint;
    private final long activeTintEdge;
    private final int animationDurationMillis;
    private final float cornerRadius;
    private final float disabledAlpha;
    private final float height;
    private final float highlightAngleDegrees;
    private final long inactiveTint;
    private final float liquidBlur;
    private final float pressedScale;
    private final float shadowElevation;
    private final float shellAlpha;
    private final float shellBlur;
    private final float strokeAlpha;
    private final float thumbDiameter;
    private final float thumbStrokeWidth;
    private final float width;

    public /* synthetic */ LegacyLiquidGlassSwitchSpec(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int i, float f13, long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, i, f13, j, j2, j3);
    }

    /* renamed from: copy-uAEEYxs$default, reason: not valid java name */
    public static /* synthetic */ LegacyLiquidGlassSwitchSpec m6949copyuAEEYxs$default(LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int i, float f13, long j, long j2, long j3, int i2, Object obj) {
        long j4;
        long j5;
        float f14 = (i2 & 1) != 0 ? legacyLiquidGlassSwitchSpec.shellBlur : f;
        float f15 = (i2 & 2) != 0 ? legacyLiquidGlassSwitchSpec.liquidBlur : f2;
        float f16 = (i2 & 4) != 0 ? legacyLiquidGlassSwitchSpec.shellAlpha : f3;
        float f17 = (i2 & 8) != 0 ? legacyLiquidGlassSwitchSpec.highlightAngleDegrees : f4;
        float f18 = (i2 & 16) != 0 ? legacyLiquidGlassSwitchSpec.strokeAlpha : f5;
        float f19 = (i2 & 32) != 0 ? legacyLiquidGlassSwitchSpec.cornerRadius : f6;
        float f20 = (i2 & 64) != 0 ? legacyLiquidGlassSwitchSpec.shadowElevation : f7;
        float f21 = (i2 & 128) != 0 ? legacyLiquidGlassSwitchSpec.width : f8;
        float f22 = (i2 & 256) != 0 ? legacyLiquidGlassSwitchSpec.height : f9;
        float f23 = (i2 & 512) != 0 ? legacyLiquidGlassSwitchSpec.thumbDiameter : f10;
        float f24 = (i2 & 1024) != 0 ? legacyLiquidGlassSwitchSpec.thumbStrokeWidth : f11;
        float f25 = (i2 & 2048) != 0 ? legacyLiquidGlassSwitchSpec.pressedScale : f12;
        int i3 = (i2 & 4096) != 0 ? legacyLiquidGlassSwitchSpec.animationDurationMillis : i;
        float f26 = (i2 & 8192) != 0 ? legacyLiquidGlassSwitchSpec.disabledAlpha : f13;
        float f27 = f14;
        long j6 = (i2 & 16384) != 0 ? legacyLiquidGlassSwitchSpec.activeTint : j;
        long j7 = (i2 & 32768) != 0 ? legacyLiquidGlassSwitchSpec.activeTintEdge : j2;
        if ((i2 & 65536) != 0) {
            j5 = j7;
            j4 = legacyLiquidGlassSwitchSpec.inactiveTint;
        } else {
            j4 = j3;
            j5 = j7;
        }
        return legacyLiquidGlassSwitchSpec.m6961copyuAEEYxs(f27, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24, f25, i3, f26, j6, j5, j4);
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getShellBlur() {
        return this.shellBlur;
    }

    /* renamed from: component10-D9Ej5fM, reason: not valid java name and from getter */
    public final float getThumbDiameter() {
        return this.thumbDiameter;
    }

    /* renamed from: component11-D9Ej5fM, reason: not valid java name and from getter */
    public final float getThumbStrokeWidth() {
        return this.thumbStrokeWidth;
    }

    /* renamed from: component12, reason: from getter */
    public final float getPressedScale() {
        return this.pressedScale;
    }

    /* renamed from: component13, reason: from getter */
    public final int getAnimationDurationMillis() {
        return this.animationDurationMillis;
    }

    /* renamed from: component14, reason: from getter */
    public final float getDisabledAlpha() {
        return this.disabledAlpha;
    }

    /* renamed from: component15-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTint() {
        return this.activeTint;
    }

    /* renamed from: component16-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTintEdge() {
        return this.activeTintEdge;
    }

    /* renamed from: component17-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveTint() {
        return this.inactiveTint;
    }

    /* renamed from: component2-D9Ej5fM, reason: not valid java name and from getter */
    public final float getLiquidBlur() {
        return this.liquidBlur;
    }

    /* renamed from: component3, reason: from getter */
    public final float getShellAlpha() {
        return this.shellAlpha;
    }

    /* renamed from: component4, reason: from getter */
    public final float getHighlightAngleDegrees() {
        return this.highlightAngleDegrees;
    }

    /* renamed from: component5, reason: from getter */
    public final float getStrokeAlpha() {
        return this.strokeAlpha;
    }

    /* renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: component7-D9Ej5fM, reason: not valid java name and from getter */
    public final float getShadowElevation() {
        return this.shadowElevation;
    }

    /* renamed from: component8-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* renamed from: component9-D9Ej5fM, reason: not valid java name and from getter */
    public final float getHeight() {
        return this.height;
    }

    /* renamed from: copy-uAEEYxs, reason: not valid java name */
    public final LegacyLiquidGlassSwitchSpec m6961copyuAEEYxs(float shellBlur, float liquidBlur, float shellAlpha, float highlightAngleDegrees, float strokeAlpha, float cornerRadius, float shadowElevation, float width, float height, float thumbDiameter, float thumbStrokeWidth, float pressedScale, int animationDurationMillis, float disabledAlpha, long activeTint, long activeTintEdge, long inactiveTint) {
        return new LegacyLiquidGlassSwitchSpec(shellBlur, liquidBlur, shellAlpha, highlightAngleDegrees, strokeAlpha, cornerRadius, shadowElevation, width, height, thumbDiameter, thumbStrokeWidth, pressedScale, animationDurationMillis, disabledAlpha, activeTint, activeTintEdge, inactiveTint, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LegacyLiquidGlassSwitchSpec)) {
            return false;
        }
        LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec = (LegacyLiquidGlassSwitchSpec) other;
        return Dp.m6631equalsimpl0(this.shellBlur, legacyLiquidGlassSwitchSpec.shellBlur) && Dp.m6631equalsimpl0(this.liquidBlur, legacyLiquidGlassSwitchSpec.liquidBlur) && Float.compare(this.shellAlpha, legacyLiquidGlassSwitchSpec.shellAlpha) == 0 && Float.compare(this.highlightAngleDegrees, legacyLiquidGlassSwitchSpec.highlightAngleDegrees) == 0 && Float.compare(this.strokeAlpha, legacyLiquidGlassSwitchSpec.strokeAlpha) == 0 && Dp.m6631equalsimpl0(this.cornerRadius, legacyLiquidGlassSwitchSpec.cornerRadius) && Dp.m6631equalsimpl0(this.shadowElevation, legacyLiquidGlassSwitchSpec.shadowElevation) && Dp.m6631equalsimpl0(this.width, legacyLiquidGlassSwitchSpec.width) && Dp.m6631equalsimpl0(this.height, legacyLiquidGlassSwitchSpec.height) && Dp.m6631equalsimpl0(this.thumbDiameter, legacyLiquidGlassSwitchSpec.thumbDiameter) && Dp.m6631equalsimpl0(this.thumbStrokeWidth, legacyLiquidGlassSwitchSpec.thumbStrokeWidth) && Float.compare(this.pressedScale, legacyLiquidGlassSwitchSpec.pressedScale) == 0 && this.animationDurationMillis == legacyLiquidGlassSwitchSpec.animationDurationMillis && Float.compare(this.disabledAlpha, legacyLiquidGlassSwitchSpec.disabledAlpha) == 0 && Color.m4164equalsimpl0(this.activeTint, legacyLiquidGlassSwitchSpec.activeTint) && Color.m4164equalsimpl0(this.activeTintEdge, legacyLiquidGlassSwitchSpec.activeTintEdge) && Color.m4164equalsimpl0(this.inactiveTint, legacyLiquidGlassSwitchSpec.inactiveTint);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((Dp.m6632hashCodeimpl(this.shellBlur) * 31) + Dp.m6632hashCodeimpl(this.liquidBlur)) * 31) + Float.hashCode(this.shellAlpha)) * 31) + Float.hashCode(this.highlightAngleDegrees)) * 31) + Float.hashCode(this.strokeAlpha)) * 31) + Dp.m6632hashCodeimpl(this.cornerRadius)) * 31) + Dp.m6632hashCodeimpl(this.shadowElevation)) * 31) + Dp.m6632hashCodeimpl(this.width)) * 31) + Dp.m6632hashCodeimpl(this.height)) * 31) + Dp.m6632hashCodeimpl(this.thumbDiameter)) * 31) + Dp.m6632hashCodeimpl(this.thumbStrokeWidth)) * 31) + Float.hashCode(this.pressedScale)) * 31) + Integer.hashCode(this.animationDurationMillis)) * 31) + Float.hashCode(this.disabledAlpha)) * 31) + Color.m4170hashCodeimpl(this.activeTint)) * 31) + Color.m4170hashCodeimpl(this.activeTintEdge)) * 31) + Color.m4170hashCodeimpl(this.inactiveTint);
    }

    public String toString() {
        return "LegacyLiquidGlassSwitchSpec(shellBlur=" + Dp.m6637toStringimpl(this.shellBlur) + ", liquidBlur=" + Dp.m6637toStringimpl(this.liquidBlur) + ", shellAlpha=" + this.shellAlpha + ", highlightAngleDegrees=" + this.highlightAngleDegrees + ", strokeAlpha=" + this.strokeAlpha + ", cornerRadius=" + Dp.m6637toStringimpl(this.cornerRadius) + ", shadowElevation=" + Dp.m6637toStringimpl(this.shadowElevation) + ", width=" + Dp.m6637toStringimpl(this.width) + ", height=" + Dp.m6637toStringimpl(this.height) + ", thumbDiameter=" + Dp.m6637toStringimpl(this.thumbDiameter) + ", thumbStrokeWidth=" + Dp.m6637toStringimpl(this.thumbStrokeWidth) + ", pressedScale=" + this.pressedScale + ", animationDurationMillis=" + this.animationDurationMillis + ", disabledAlpha=" + this.disabledAlpha + ", activeTint=" + Color.m4171toStringimpl(this.activeTint) + ", activeTintEdge=" + Color.m4171toStringimpl(this.activeTintEdge) + ", inactiveTint=" + Color.m4171toStringimpl(this.inactiveTint) + ")";
    }

    private LegacyLiquidGlassSwitchSpec(float shellBlur, float liquidBlur, float shellAlpha, float highlightAngleDegrees, float strokeAlpha, float cornerRadius, float shadowElevation, float width, float height, float thumbDiameter, float thumbStrokeWidth, float pressedScale, int animationDurationMillis, float disabledAlpha, long activeTint, long activeTintEdge, long inactiveTint) {
        this.shellBlur = shellBlur;
        this.liquidBlur = liquidBlur;
        this.shellAlpha = shellAlpha;
        this.highlightAngleDegrees = highlightAngleDegrees;
        this.strokeAlpha = strokeAlpha;
        this.cornerRadius = cornerRadius;
        this.shadowElevation = shadowElevation;
        this.width = width;
        this.height = height;
        this.thumbDiameter = thumbDiameter;
        this.thumbStrokeWidth = thumbStrokeWidth;
        this.pressedScale = pressedScale;
        this.animationDurationMillis = animationDurationMillis;
        this.disabledAlpha = disabledAlpha;
        this.activeTint = activeTint;
        this.activeTintEdge = activeTintEdge;
        this.inactiveTint = inactiveTint;
    }

    public /* synthetic */ LegacyLiquidGlassSwitchSpec(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int i, float f13, long j, long j2, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? Dp.m6626constructorimpl(14) : f, (i2 & 2) != 0 ? Dp.m6626constructorimpl(10) : f2, (i2 & 4) != 0 ? 0.94f : f3, (i2 & 8) != 0 ? 14.0f : f4, (i2 & 16) != 0 ? 0.92f : f5, (i2 & 32) != 0 ? Dp.m6626constructorimpl(999) : f6, (i2 & 64) != 0 ? Dp.m6626constructorimpl(4) : f7, (i2 & 128) != 0 ? Dp.m6626constructorimpl(84) : f8, (i2 & 256) != 0 ? Dp.m6626constructorimpl(50) : f9, (i2 & 512) != 0 ? Dp.m6626constructorimpl(36) : f10, (i2 & 1024) != 0 ? Dp.m6626constructorimpl(2) : f11, (i2 & 2048) != 0 ? 0.984f : f12, (i2 & 4096) != 0 ? AppPrefs.MAX_RECENT_PROCESSED_KEYS : i, (i2 & 8192) != 0 ? 0.46f : f13, (i2 & 16384) != 0 ? ColorKt.Color(4282635354L) : j, (i2 & 32768) != 0 ? ColorKt.Color(4284079474L) : j2, (i2 & 65536) != 0 ? ColorKt.Color(4293389554L) : j3, null);
    }

    /* renamed from: getShellBlur-D9Ej5fM, reason: not valid java name */
    public final float m6969getShellBlurD9Ej5fM() {
        return this.shellBlur;
    }

    /* renamed from: getLiquidBlur-D9Ej5fM, reason: not valid java name */
    public final float m6967getLiquidBlurD9Ej5fM() {
        return this.liquidBlur;
    }

    public final float getShellAlpha() {
        return this.shellAlpha;
    }

    public final float getHighlightAngleDegrees() {
        return this.highlightAngleDegrees;
    }

    public final float getStrokeAlpha() {
        return this.strokeAlpha;
    }

    /* renamed from: getCornerRadius-D9Ej5fM, reason: not valid java name */
    public final float m6964getCornerRadiusD9Ej5fM() {
        return this.cornerRadius;
    }

    /* renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m6968getShadowElevationD9Ej5fM() {
        return this.shadowElevation;
    }

    /* renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public final float m6972getWidthD9Ej5fM() {
        return this.width;
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m6965getHeightD9Ej5fM() {
        return this.height;
    }

    /* renamed from: getThumbDiameter-D9Ej5fM, reason: not valid java name */
    public final float m6970getThumbDiameterD9Ej5fM() {
        return this.thumbDiameter;
    }

    /* renamed from: getThumbStrokeWidth-D9Ej5fM, reason: not valid java name */
    public final float m6971getThumbStrokeWidthD9Ej5fM() {
        return this.thumbStrokeWidth;
    }

    public final float getPressedScale() {
        return this.pressedScale;
    }

    public final int getAnimationDurationMillis() {
        return this.animationDurationMillis;
    }

    public final float getDisabledAlpha() {
        return this.disabledAlpha;
    }

    /* renamed from: getActiveTint-0d7_KjU, reason: not valid java name */
    public final long m6962getActiveTint0d7_KjU() {
        return this.activeTint;
    }

    /* renamed from: getActiveTintEdge-0d7_KjU, reason: not valid java name */
    public final long m6963getActiveTintEdge0d7_KjU() {
        return this.activeTintEdge;
    }

    /* renamed from: getInactiveTint-0d7_KjU, reason: not valid java name */
    public final long m6966getInactiveTint0d7_KjU() {
        return this.inactiveTint;
    }
}
