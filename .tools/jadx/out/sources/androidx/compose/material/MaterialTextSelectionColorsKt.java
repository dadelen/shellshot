package androidx.compose.material;

import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* compiled from: MaterialTextSelectionColors.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a2\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a*\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"DefaultSelectionBackgroundAlpha", "", "DesiredContrastRatio", "MinimumSelectionBackgroundAlpha", "binarySearchForAccessibleSelectionColorAlpha", "selectionColor", "Landroidx/compose/ui/graphics/Color;", "textColor", "backgroundColor", "binarySearchForAccessibleSelectionColorAlpha-ysEtTa8", "(JJJ)F", "calculateContrastRatio", "foreground", "background", "calculateContrastRatio--OWjLjI", "(JJ)F", "selectionColorAlpha", "calculateContrastRatio-nb2GgbA", "(JFJJ)F", "calculateSelectionBackgroundColor", "calculateSelectionBackgroundColor-ysEtTa8", "(JJJ)J", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "colors", "Landroidx/compose/material/Colors;", "(Landroidx/compose/material/Colors;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MaterialTextSelectionColorsKt {
    private static final float DefaultSelectionBackgroundAlpha = 0.4f;
    private static final float DesiredContrastRatio = 4.5f;
    private static final float MinimumSelectionBackgroundAlpha = 0.2f;

    public static final SelectionColors rememberTextSelectionColors(Colors colors, Composer $composer, int $changed) {
        long m4173unboximpl;
        long textColorWithLowestAlpha;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -721696685, "C(rememberTextSelectionColors)45@1902L6,47@1930L384:MaterialTextSelectionColors.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-721696685, $changed, -1, "androidx.compose.material.rememberTextSelectionColors (MaterialTextSelectionColors.kt:35)");
        }
        long primaryColor = colors.m1478getPrimary0d7_KjU();
        long backgroundColor = colors.m1471getBackground0d7_KjU();
        $composer.startReplaceGroup(1102762072);
        ComposerKt.sourceInformation($composer, "*43@1845L7");
        long $this$takeOrElse_u2dDxMtmZc$iv = ColorsKt.m1495contentColorFor4WTKRHQ(colors, backgroundColor);
        if ($this$takeOrElse_u2dDxMtmZc$iv != 16) {
            m4173unboximpl = $this$takeOrElse_u2dDxMtmZc$iv;
        } else {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            m4173unboximpl = ((Color) consume).m4173unboximpl();
        }
        $composer.endReplaceGroup();
        textColorWithLowestAlpha = Color.m4161copywmQWz5c(m4173unboximpl, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(m4173unboximpl) : ContentAlpha.INSTANCE.getMedium($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(m4173unboximpl) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(m4173unboximpl) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(m4173unboximpl) : 0.0f);
        ComposerKt.sourceInformationMarkerStart($composer, 1102766492, "CC(remember):MaterialTextSelectionColors.kt#9igjgp");
        boolean invalid$iv = $composer.changed(primaryColor) | $composer.changed(backgroundColor) | $composer.changed(textColorWithLowestAlpha);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new SelectionColors(colors.m1478getPrimary0d7_KjU(), m1578calculateSelectionBackgroundColorysEtTa8(primaryColor, textColorWithLowestAlpha, backgroundColor), null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SelectionColors selectionColors = (SelectionColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return selectionColors;
    }

    /* renamed from: calculateSelectionBackgroundColor-ysEtTa8, reason: not valid java name */
    public static final long m1578calculateSelectionBackgroundColorysEtTa8(long selectionColor, long textColor, long backgroundColor) {
        float alpha;
        long m4161copywmQWz5c;
        float maximumContrastRatio = m1577calculateContrastRationb2GgbA(selectionColor, 0.4f, textColor, backgroundColor);
        float minimumContrastRatio = m1577calculateContrastRationb2GgbA(selectionColor, 0.2f, textColor, backgroundColor);
        if (maximumContrastRatio >= DesiredContrastRatio) {
            alpha = 0.4f;
        } else {
            alpha = minimumContrastRatio < DesiredContrastRatio ? 0.2f : m1575binarySearchForAccessibleSelectionColorAlphaysEtTa8(selectionColor, textColor, backgroundColor);
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(selectionColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(selectionColor) : alpha, (r12 & 2) != 0 ? Color.m4169getRedimpl(selectionColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(selectionColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(selectionColor) : 0.0f);
        return m4161copywmQWz5c;
    }

    /* renamed from: binarySearchForAccessibleSelectionColorAlpha-ysEtTa8, reason: not valid java name */
    private static final float m1575binarySearchForAccessibleSelectionColorAlphaysEtTa8(long selectionColor, long textColor, long backgroundColor) {
        float lowAlpha = 0.2f;
        float alpha = 0.4f;
        float highAlpha = 0.4f;
        for (int attempts = 0; attempts < 7; attempts++) {
            float contrastRatio = m1577calculateContrastRationb2GgbA(selectionColor, alpha, textColor, backgroundColor);
            float percentageError = (contrastRatio / DesiredContrastRatio) - 1.0f;
            boolean z = false;
            if (0.0f <= percentageError && percentageError <= 0.01f) {
                z = true;
            }
            if (z) {
                break;
            }
            if (percentageError < 0.0f) {
                highAlpha = alpha;
            } else {
                lowAlpha = alpha;
            }
            alpha = (highAlpha + lowAlpha) / 2.0f;
        }
        return alpha;
    }

    /* renamed from: calculateContrastRatio-nb2GgbA, reason: not valid java name */
    private static final float m1577calculateContrastRationb2GgbA(long selectionColor, float selectionColorAlpha, long textColor, long backgroundColor) {
        long selectionColor2;
        selectionColor2 = Color.m4161copywmQWz5c(selectionColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(selectionColor) : selectionColorAlpha, (r12 & 2) != 0 ? Color.m4169getRedimpl(selectionColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(selectionColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(selectionColor) : 0.0f);
        long compositeBackground = ColorKt.m4208compositeOverOWjLjI(selectionColor2, backgroundColor);
        long compositeTextColor = ColorKt.m4208compositeOverOWjLjI(textColor, compositeBackground);
        return m1576calculateContrastRatioOWjLjI(compositeTextColor, compositeBackground);
    }

    /* renamed from: calculateContrastRatio--OWjLjI, reason: not valid java name */
    public static final float m1576calculateContrastRatioOWjLjI(long foreground, long background) {
        float foregroundLuminance = ColorKt.m4215luminance8_81llA(foreground) + 0.05f;
        float backgroundLuminance = ColorKt.m4215luminance8_81llA(background) + 0.05f;
        return Math.max(foregroundLuminance, backgroundLuminance) / Math.min(foregroundLuminance, backgroundLuminance);
    }
}
