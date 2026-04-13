package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* compiled from: Switch.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jv\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, d2 = {"Landroidx/compose/material/SwitchDefaults;", "", "()V", "colors", "Landroidx/compose/material/SwitchColors;", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedTrackAlpha", "", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedTrackAlpha", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "colors-SQMK_m0", "(JJFJJFJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SwitchColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SwitchDefaults {
    public static final int $stable = 0;
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();

    private SwitchDefaults() {
    }

    /* renamed from: colors-SQMK_m0, reason: not valid java name */
    public final SwitchColors m1672colorsSQMK_m0(long checkedThumbColor, long checkedTrackColor, float checkedTrackAlpha, long uncheckedThumbColor, long uncheckedTrackColor, float uncheckedTrackAlpha, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, Composer $composer, int $changed, int $changed1, int i) {
        float checkedTrackAlpha2;
        long checkedThumbColor2;
        long disabledCheckedThumbColor2;
        long disabledCheckedThumbColor3;
        long disabledCheckedTrackColor2;
        long disabledCheckedTrackColor3;
        long disabledUncheckedThumbColor2;
        long disabledUncheckedTrackColor2;
        long disabledUncheckedTrackColor3;
        long checkedTrackColor2;
        long uncheckedTrackColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long checkedThumbColor3;
        ComposerKt.sourceInformationMarkerStart($composer, -1032127534, "C(colors)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color!1,7:c#ui.graphics.Color,9:c#ui.graphics.Color,8,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color)326@13193L6,329@13365L6,330@13432L6,333@13594L8,334@13645L6,336@13762L8,337@13813L6,339@13934L8,340@13985L6,342@14106L8,343@14157L6:Switch.kt#jmzs0o");
        long checkedThumbColor4 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1481getSecondaryVariant0d7_KjU() : checkedThumbColor;
        long checkedTrackColor3 = (i & 2) != 0 ? checkedThumbColor4 : checkedTrackColor;
        float checkedTrackAlpha3 = (i & 4) != 0 ? 0.54f : checkedTrackAlpha;
        long uncheckedThumbColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : uncheckedThumbColor;
        long uncheckedTrackColor3 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU() : uncheckedTrackColor;
        float uncheckedTrackAlpha2 = (i & 32) != 0 ? 0.38f : uncheckedTrackAlpha;
        if ((i & 64) != 0) {
            long checkedThumbColor5 = checkedThumbColor4;
            checkedThumbColor3 = Color.m4161copywmQWz5c(checkedThumbColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkedThumbColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(checkedThumbColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkedThumbColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkedThumbColor5) : 0.0f);
            checkedThumbColor2 = checkedThumbColor5;
            checkedTrackAlpha2 = checkedTrackAlpha3;
            disabledCheckedThumbColor2 = ColorKt.m4208compositeOverOWjLjI(checkedThumbColor3, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            checkedTrackAlpha2 = checkedTrackAlpha3;
            checkedThumbColor2 = checkedThumbColor4;
            disabledCheckedThumbColor2 = disabledCheckedThumbColor;
        }
        if ((i & 128) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(checkedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkedTrackColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(checkedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkedTrackColor3) : 0.0f);
            disabledCheckedThumbColor3 = disabledCheckedThumbColor2;
            disabledCheckedTrackColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c5, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledCheckedThumbColor3 = disabledCheckedThumbColor2;
            disabledCheckedTrackColor2 = disabledCheckedTrackColor;
        }
        if ((i & 256) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(uncheckedThumbColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(uncheckedThumbColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(uncheckedThumbColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(uncheckedThumbColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(uncheckedThumbColor2) : 0.0f);
            disabledCheckedTrackColor3 = disabledCheckedTrackColor2;
            disabledUncheckedThumbColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c4, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledCheckedTrackColor3 = disabledCheckedTrackColor2;
            disabledUncheckedThumbColor2 = disabledUncheckedThumbColor;
        }
        if ((i & 512) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(uncheckedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(uncheckedTrackColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(uncheckedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(uncheckedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(uncheckedTrackColor3) : 0.0f);
            disabledUncheckedTrackColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c3, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledUncheckedTrackColor2 = disabledUncheckedTrackColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            disabledUncheckedTrackColor3 = disabledUncheckedTrackColor2;
            ComposerKt.traceEventStart(-1032127534, $changed, $changed1, "androidx.compose.material.SwitchDefaults.colors (Switch.kt:344)");
        } else {
            disabledUncheckedTrackColor3 = disabledUncheckedTrackColor2;
        }
        float checkedTrackAlpha4 = checkedTrackAlpha2;
        checkedTrackColor2 = Color.m4161copywmQWz5c(checkedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkedTrackColor) : checkedTrackAlpha4, (r12 & 2) != 0 ? Color.m4169getRedimpl(checkedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkedTrackColor3) : 0.0f);
        uncheckedTrackColor2 = Color.m4161copywmQWz5c(uncheckedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(uncheckedTrackColor) : uncheckedTrackAlpha2, (r12 & 2) != 0 ? Color.m4169getRedimpl(uncheckedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(uncheckedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(uncheckedTrackColor3) : 0.0f);
        m4161copywmQWz5c = Color.m4161copywmQWz5c(disabledCheckedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(disabledCheckedTrackColor) : checkedTrackAlpha4, (r12 & 2) != 0 ? Color.m4169getRedimpl(disabledCheckedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(disabledCheckedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(disabledCheckedTrackColor3) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(disabledUncheckedTrackColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(disabledUncheckedTrackColor) : uncheckedTrackAlpha2, (r12 & 2) != 0 ? Color.m4169getRedimpl(disabledUncheckedTrackColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(disabledUncheckedTrackColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(disabledUncheckedTrackColor3) : 0.0f);
        DefaultSwitchColors defaultSwitchColors = new DefaultSwitchColors(checkedThumbColor2, checkedTrackColor2, uncheckedThumbColor2, uncheckedTrackColor2, disabledCheckedThumbColor3, m4161copywmQWz5c, disabledUncheckedThumbColor2, m4161copywmQWz5c2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSwitchColors;
    }
}
