package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J0\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.JD\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00042\b\b\u0002\u0010/\u001a\u00020\u00042\b\b\u0002\u00100\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102J0\u00103\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105J0\u00106\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00105R\u0016\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0014\u001a\u00020\u0015X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0017\u0010\rR\u0011\u0010\u0018\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\nR\u0016\u0010\u001a\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001b\u001a\u00020\u001c8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00068"}, d2 = {"Landroidx/compose/material/ButtonDefaults;", "", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "IconSize", "getIconSize-D9Ej5fM", "()F", "IconSpacing", "getIconSpacing-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedBorderOpacity", "", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "TextButtonContentPadding", "getTextButtonContentPadding", "TextButtonHorizontalPadding", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "buttonColors", "Landroidx/compose/material/ButtonColors;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledBackgroundColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "elevation", "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "pressedElevation", "disabledElevation", "elevation-yajeYGU", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "hoveredElevation", "focusedElevation", "elevation-R_JCAzs", "(FFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "outlinedButtonColors", "outlinedButtonColors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "textButtonColors", "textButtonColors-RGew2ao", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ButtonDefaults {
    public static final int $stable = 0;
    public static final float OutlinedBorderOpacity = 0.12f;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float ButtonHorizontalPadding = Dp.m6626constructorimpl(16);
    private static final float ButtonVerticalPadding = Dp.m6626constructorimpl(8);
    private static final PaddingValues ContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(ButtonHorizontalPadding, ButtonVerticalPadding, ButtonHorizontalPadding, ButtonVerticalPadding);
    private static final float MinWidth = Dp.m6626constructorimpl(64);
    private static final float MinHeight = Dp.m6626constructorimpl(36);
    private static final float IconSize = Dp.m6626constructorimpl(18);
    private static final float IconSpacing = Dp.m6626constructorimpl(8);
    private static final float OutlinedBorderSize = Dp.m6626constructorimpl(1);
    private static final float TextButtonHorizontalPadding = Dp.m6626constructorimpl(8);
    private static final PaddingValues TextButtonContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(TextButtonHorizontalPadding, ContentPadding.getTop(), TextButtonHorizontalPadding, ContentPadding.getBottom());

    private ButtonDefaults() {
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1450getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1449getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1447getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1448getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use another overload of elevation")
    /* renamed from: elevation-yajeYGU, reason: not valid java name */
    public final /* synthetic */ ButtonElevation m1446elevationyajeYGU(float defaultElevation, float pressedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float disabledElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1428576874, "C(elevation)P(0:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp)350@14616L161:Button.kt#jmzs0o");
        if ((i & 1) != 0) {
            float defaultElevation3 = Dp.m6626constructorimpl(2);
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = Dp.m6626constructorimpl(8);
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float disabledElevation3 = Dp.m6626constructorimpl(0);
            disabledElevation2 = disabledElevation3;
        } else {
            disabledElevation2 = disabledElevation;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1428576874, $changed, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:350)");
        }
        int $this$dp$iv = $changed & 14;
        ButtonElevation m1445elevationR_JCAzs = m1445elevationR_JCAzs(defaultElevation2, pressedElevation2, disabledElevation2, Dp.m6626constructorimpl(4), Dp.m6626constructorimpl(4), $composer, $this$dp$iv | 27648 | ($changed & 112) | ($changed & 896) | (($changed << 6) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1445elevationR_JCAzs;
    }

    /* renamed from: elevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1445elevationR_JCAzs(float defaultElevation, float pressedElevation, float disabledElevation, float hoveredElevation, float focusedElevation, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -737170518, "C(elevation)P(0:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,3:c#ui.unit.Dp,2:c#ui.unit.Dp)379@15748L497:Button.kt#jmzs0o");
        float defaultElevation2 = (i & 1) != 0 ? Dp.m6626constructorimpl(2) : defaultElevation;
        float pressedElevation2 = (i & 2) != 0 ? Dp.m6626constructorimpl(8) : pressedElevation;
        float disabledElevation2 = (i & 4) != 0 ? Dp.m6626constructorimpl(0) : disabledElevation;
        float hoveredElevation2 = (i & 8) != 0 ? Dp.m6626constructorimpl(4) : hoveredElevation;
        float focusedElevation2 = (i & 16) != 0 ? Dp.m6626constructorimpl(4) : focusedElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-737170518, $changed, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:378)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -511819094, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(defaultElevation2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(pressedElevation2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(disabledElevation2)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(hoveredElevation2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(focusedElevation2)) || ($changed & 24576) == 16384);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new DefaultButtonElevation(defaultElevation2, pressedElevation2, disabledElevation2, hoveredElevation2, focusedElevation2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        DefaultButtonElevation defaultButtonElevation = (DefaultButtonElevation) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonElevation;
    }

    /* renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1444buttonColorsro_MJ88(long backgroundColor, long contentColor, long disabledBackgroundColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledBackgroundColor2;
        long disabledContentColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, 1870371134, "C(buttonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)407@16814L6,408@16860L32,409@16949L6,410@17027L6,411@17096L6,412@17152L8:Button.kt#jmzs0o");
        long backgroundColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : backgroundColor;
        long contentColor2 = (i & 2) != 0 ? ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer, $changed & 14) : contentColor;
        if ((i & 4) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledBackgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c2, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledBackgroundColor2 = disabledBackgroundColor;
        }
        if ((i & 8) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1870371134, $changed, -1, "androidx.compose.material.ButtonDefaults.buttonColors (Button.kt:413)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(backgroundColor2, contentColor2, disabledBackgroundColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* renamed from: outlinedButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1452outlinedButtonColorsRGew2ao(long backgroundColor, long contentColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -2124406093, "C(outlinedButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)430@17899L6,431@17959L6,432@18027L6,433@18083L8:Button.kt#jmzs0o");
        long backgroundColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : backgroundColor;
        long contentColor2 = (i & 2) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : contentColor;
        if ((i & 4) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2124406093, $changed, -1, "androidx.compose.material.ButtonDefaults.outlinedButtonColors (Button.kt:434)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(backgroundColor2, contentColor2, backgroundColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* renamed from: textButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1453textButtonColorsRGew2ao(long backgroundColor, long contentColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContentColor2;
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, 182742216, "C(textButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)452@18850L6,453@18918L6,454@18974L8:Button.kt#jmzs0o");
        long backgroundColor2 = (i & 1) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : backgroundColor;
        long contentColor2 = (i & 2) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : contentColor;
        if ((i & 4) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(182742216, $changed, -1, "androidx.compose.material.ButtonDefaults.textButtonColors (Button.kt:455)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(backgroundColor2, contentColor2, backgroundColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1451getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    public final BorderStroke getOutlinedBorder(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -2091313033, "C478@19678L6:Button.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2091313033, $changed, -1, "androidx.compose.material.ButtonDefaults.<get-outlinedBorder> (Button.kt:477)");
        }
        float f = OutlinedBorderSize;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r2) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
        BorderStroke m254BorderStrokecXLIe8U = BorderStrokeKt.m254BorderStrokecXLIe8U(f, m4161copywmQWz5c);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m254BorderStrokecXLIe8U;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }
}
