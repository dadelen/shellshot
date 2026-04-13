package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jv\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/compose/material/SliderDefaults;", "", "()V", "DisabledActiveTrackAlpha", "", "DisabledInactiveTrackAlpha", "DisabledTickAlpha", "InactiveTrackAlpha", "TickAlpha", "colors", "Landroidx/compose/material/SliderColors;", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "disabledThumbColor", "activeTrackColor", "inactiveTrackColor", "disabledActiveTrackColor", "disabledInactiveTrackColor", "activeTickColor", "inactiveTickColor", "disabledActiveTickColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SliderColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final float DisabledActiveTrackAlpha = 0.32f;
    public static final float DisabledInactiveTrackAlpha = 0.12f;
    public static final float DisabledTickAlpha = 0.12f;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    public static final float InactiveTrackAlpha = 0.24f;
    public static final float TickAlpha = 0.54f;

    private SliderDefaults() {
    }

    /* renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m1636colorsq0g_0yA(long thumbColor, long disabledThumbColor, long activeTrackColor, long inactiveTrackColor, long disabledActiveTrackColor, long disabledInactiveTrackColor, long activeTickColor, long inactiveTickColor, long disabledActiveTickColor, long disabledInactiveTickColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledThumbColor2;
        long inactiveTrackColor2;
        long disabledActiveTrackColor2;
        long disabledInactiveTrackColor2;
        long activeTickColor2;
        long inactiveTickColor2;
        long disabledActiveTickColor2;
        long disabledInactiveTickColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        ComposerKt.sourceInformationMarkerStart($composer, 436017687, "C(colors)P(9:c#ui.graphics.Color,6:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,7:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color)485@21094L6,486@21160L6,487@21216L8,488@21267L6,489@21332L6,492@21503L6,495@21716L33:Slider.kt#jmzs0o");
        long thumbColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : thumbColor;
        if ((i & 2) != 0) {
            m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledThumbColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c8, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledThumbColor2 = disabledThumbColor;
        }
        long activeTrackColor2 = (i & 4) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : activeTrackColor;
        if ((i & 8) != 0) {
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(activeTrackColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(activeTrackColor2) : 0.24f, (r12 & 2) != 0 ? Color.m4169getRedimpl(activeTrackColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(activeTrackColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(activeTrackColor2) : 0.0f);
            inactiveTrackColor2 = m4161copywmQWz5c7;
        } else {
            inactiveTrackColor2 = inactiveTrackColor;
        }
        if ((i & 16) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.32f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledActiveTrackColor2 = m4161copywmQWz5c6;
        } else {
            disabledActiveTrackColor2 = disabledActiveTrackColor;
        }
        if ((i & 32) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(disabledActiveTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(disabledActiveTrackColor) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(disabledActiveTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(disabledActiveTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(disabledActiveTrackColor2) : 0.0f);
            disabledInactiveTrackColor2 = m4161copywmQWz5c5;
        } else {
            disabledInactiveTrackColor2 = disabledInactiveTrackColor;
        }
        if ((i & 64) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorsKt.m1496contentColorForek8zF_U(activeTrackColor2, $composer, ($changed >> 6) & 14)) : 0.0f);
            activeTickColor2 = m4161copywmQWz5c4;
        } else {
            activeTickColor2 = activeTickColor;
        }
        if ((i & 128) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(activeTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(activeTrackColor) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(activeTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(activeTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(activeTrackColor2) : 0.0f);
            inactiveTickColor2 = m4161copywmQWz5c3;
        } else {
            inactiveTickColor2 = inactiveTickColor;
        }
        if ((i & 256) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(activeTickColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(activeTickColor) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(activeTickColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(activeTickColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(activeTickColor2) : 0.0f);
            disabledActiveTickColor2 = m4161copywmQWz5c2;
        } else {
            disabledActiveTickColor2 = disabledActiveTickColor;
        }
        if ((i & 512) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(disabledInactiveTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(disabledInactiveTrackColor) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(disabledInactiveTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(disabledInactiveTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(disabledInactiveTrackColor2) : 0.0f);
            disabledInactiveTickColor2 = m4161copywmQWz5c;
        } else {
            disabledInactiveTickColor2 = disabledInactiveTickColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(436017687, $changed, $changed1, "androidx.compose.material.SliderDefaults.colors (Slider.kt:500)");
        }
        DefaultSliderColors defaultSliderColors = new DefaultSliderColors(thumbColor2, disabledThumbColor2, activeTrackColor2, inactiveTrackColor2, disabledActiveTrackColor2, disabledInactiveTrackColor2, activeTickColor2, inactiveTickColor2, disabledActiveTickColor2, disabledInactiveTickColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSliderColors;
    }
}
