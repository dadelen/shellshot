package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.ElevatedButtonTokens;
import androidx.compose.material3.tokens.FilledButtonTokens;
import androidx.compose.material3.tokens.FilledTonalButtonTokens;
import androidx.compose.material3.tokens.OutlinedButtonTokens;
import androidx.compose.material3.tokens.TextButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010:\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010:\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bA\u0010BJD\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\r\u0010L\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010L\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010BJD\u0010N\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bO\u0010KJ\r\u0010P\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010P\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bQ\u0010BJD\u0010R\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u00042\b\b\u0002\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u00042\b\b\u0002\u0010H\u001a\u00020\u00042\b\b\u0002\u0010I\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bS\u0010KJ\u0017\u0010#\u001a\u00020$2\b\b\u0002\u0010T\u001a\u00020UH\u0007¢\u0006\u0002\u0010VJ\r\u0010W\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010W\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bX\u0010BJ\r\u0010Y\u001a\u00020.H\u0007¢\u0006\u0002\u0010;J:\u0010Y\u001a\u00020.2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bZ\u0010BR\u0016\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\u0013\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0014\u0010\u0010R\u0019\u0010\u0015\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u0016\u0010\u0019\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\nR\u0016\u0010\u001c\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001d\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0011\u0010#\u001a\u00020$8G¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b(\u0010 R\u0011\u0010)\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b*\u0010 R\u0011\u0010+\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b,\u0010 R\u0018\u0010-\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0018\u00102\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u00101R\u0018\u00104\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00101R\u0018\u00106\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u00101R\u0018\u00108\u001a\u00020.*\u00020/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006["}, d2 = {"Landroidx/compose/material3/ButtonDefaults;", "", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ButtonWithIconContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getButtonWithIconContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "ButtonWithIconHorizontalStartPadding", "ContentPadding", "getContentPadding", "IconSize", "getIconSize-D9Ej5fM", "()F", "IconSpacing", "getIconSpacing-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "TextButtonContentPadding", "getTextButtonContentPadding", "TextButtonHorizontalPadding", "TextButtonWithIconContentPadding", "getTextButtonWithIconContentPadding", "TextButtonWithIconHorizontalEndPadding", "elevatedShape", "Landroidx/compose/ui/graphics/Shape;", "getElevatedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "filledTonalShape", "getFilledTonalShape", "outlinedButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedButtonBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedShape", "getOutlinedShape", "shape", "getShape", "textShape", "getTextShape", "defaultButtonColors", "Landroidx/compose/material3/ButtonColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultButtonColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ButtonColors;", "defaultElevatedButtonColors", "getDefaultElevatedButtonColors$material3_release", "defaultFilledTonalButtonColors", "getDefaultFilledTonalButtonColors$material3_release", "defaultOutlinedButtonColors", "getDefaultOutlinedButtonColors$material3_release", "defaultTextButtonColors", "getDefaultTextButtonColors$material3_release", "buttonColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ButtonColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ButtonColors;", "buttonElevation", "Landroidx/compose/material3/ButtonElevation;", "defaultElevation", "pressedElevation", "focusedElevation", "hoveredElevation", "disabledElevation", "buttonElevation-R_JCAzs", "(FFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ButtonElevation;", "elevatedButtonColors", "elevatedButtonColors-ro_MJ88", "elevatedButtonElevation", "elevatedButtonElevation-R_JCAzs", "filledTonalButtonColors", "filledTonalButtonColors-ro_MJ88", "filledTonalButtonElevation", "filledTonalButtonElevation-R_JCAzs", "enabled", "", "(ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "outlinedButtonColors", "outlinedButtonColors-ro_MJ88", "textButtonColors", "textButtonColors-ro_MJ88", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ButtonDefaults {
    public static final int $stable = 0;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float ButtonHorizontalPadding = Dp.m6626constructorimpl(24);
    private static final float ButtonVerticalPadding = Dp.m6626constructorimpl(8);
    private static final PaddingValues ContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(ButtonHorizontalPadding, ButtonVerticalPadding, ButtonHorizontalPadding, ButtonVerticalPadding);
    private static final float ButtonWithIconHorizontalStartPadding = Dp.m6626constructorimpl(16);
    private static final PaddingValues ButtonWithIconContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(ButtonWithIconHorizontalStartPadding, ButtonVerticalPadding, ButtonHorizontalPadding, ButtonVerticalPadding);
    private static final float TextButtonHorizontalPadding = Dp.m6626constructorimpl(12);
    private static final PaddingValues TextButtonContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(TextButtonHorizontalPadding, ContentPadding.getTop(), TextButtonHorizontalPadding, ContentPadding.getBottom());
    private static final float TextButtonWithIconHorizontalEndPadding = Dp.m6626constructorimpl(16);
    private static final PaddingValues TextButtonWithIconContentPadding = PaddingKt.m668PaddingValuesa9UjIt4(TextButtonHorizontalPadding, ContentPadding.getTop(), TextButtonWithIconHorizontalEndPadding, ContentPadding.getBottom());
    private static final float MinWidth = Dp.m6626constructorimpl(58);
    private static final float MinHeight = Dp.m6626constructorimpl(40);
    private static final float IconSize = FilledButtonTokens.INSTANCE.m3248getIconSizeD9Ej5fM();
    private static final float IconSpacing = Dp.m6626constructorimpl(8);

    private ButtonDefaults() {
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    public final PaddingValues getButtonWithIconContentPadding() {
        return ButtonWithIconContentPadding;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }

    public final PaddingValues getTextButtonWithIconContentPadding() {
        return TextButtonWithIconContentPadding;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1822getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1821getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1819getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1820getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1234923021, "C542@25460L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1234923021, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-shape> (Button.kt:542)");
        }
        Shape value = ShapesKt.getValue(FilledButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getElevatedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2143958791, "C546@25609L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143958791, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-elevatedShape> (Button.kt:546)");
        }
        Shape value = ShapesKt.getValue(ElevatedButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getFilledTonalShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -886584987, "C550@25767L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-886584987, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-filledTonalShape> (Button.kt:550)");
        }
        Shape value = ShapesKt.getValue(FilledTonalButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2045213065, "C554@25916L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2045213065, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-outlinedShape> (Button.kt:554)");
        }
        Shape value = ShapesKt.getValue(OutlinedButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getTextShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -349121587, "C558@26052L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-349121587, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-textShape> (Button.kt:558)");
        }
        Shape value = ShapesKt.getValue(TextButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final ButtonColors buttonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1449248637, "C(buttonColors)564@26242L11:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1449248637, $changed, -1, "androidx.compose.material3.ButtonDefaults.buttonColors (Button.kt:564)");
        }
        ButtonColors defaultButtonColors$material3_release = getDefaultButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors$material3_release;
    }

    /* renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1813buttonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -339300779, "C(buttonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)582@27048L11:Button.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-339300779, $changed, -1, "androidx.compose.material3.ButtonDefaults.buttonColors (Button.kt:582)");
        }
        ButtonColors m1808copyjRlVdoo = getDefaultButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1808copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1808copyjRlVdoo;
    }

    public final ButtonColors getDefaultButtonColors$material3_release(ColorScheme $this$defaultButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ButtonColors it = $this$defaultButtonColors.getDefaultButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultButtonColors, FilledButtonTokens.INSTANCE.getContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultButtonColors, FilledButtonTokens.INSTANCE.getLabelTextColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultButtonColors, FilledButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultButtonColors, FilledButtonTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            ButtonColors it2 = new ButtonColors(fromToken, fromToken2, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultButtonColors.setDefaultButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final ButtonColors elevatedButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2025043443, "C(elevatedButtonColors)609@28364L11:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2025043443, $changed, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonColors (Button.kt:609)");
        }
        ButtonColors defaultElevatedButtonColors$material3_release = getDefaultElevatedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultElevatedButtonColors$material3_release;
    }

    /* renamed from: elevatedButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1815elevatedButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1507908383, "C(elevatedButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)627@29223L11:Button.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1507908383, $changed, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonColors (Button.kt:627)");
        }
        ButtonColors m1808copyjRlVdoo = getDefaultElevatedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1808copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1808copyjRlVdoo;
    }

    public final ButtonColors getDefaultElevatedButtonColors$material3_release(ColorScheme $this$defaultElevatedButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ButtonColors it = $this$defaultElevatedButtonColors.getDefaultElevatedButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultElevatedButtonColors, ElevatedButtonTokens.INSTANCE.getContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedButtonColors, ElevatedButtonTokens.INSTANCE.getLabelTextColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : ElevatedButtonTokens.INSTANCE.getDisabledContainerOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedButtonColors, ElevatedButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : ElevatedButtonTokens.INSTANCE.getDisabledLabelTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedButtonColors, ElevatedButtonTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            ButtonColors it2 = new ButtonColors(fromToken, fromToken2, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultElevatedButtonColors.setDefaultElevatedButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final ButtonColors filledTonalButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 824987837, "C(filledTonalButtonColors)655@30593L11:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(824987837, $changed, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonColors (Button.kt:655)");
        }
        ButtonColors defaultFilledTonalButtonColors$material3_release = getDefaultFilledTonalButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFilledTonalButtonColors$material3_release;
    }

    /* renamed from: filledTonalButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1817filledTonalButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1670757653, "C(filledTonalButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)674@31482L11:Button.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1670757653, $changed, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonColors (Button.kt:674)");
        }
        ButtonColors m1808copyjRlVdoo = getDefaultFilledTonalButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1808copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1808copyjRlVdoo;
    }

    public final ButtonColors getDefaultFilledTonalButtonColors$material3_release(ColorScheme $this$defaultFilledTonalButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ButtonColors it = $this$defaultFilledTonalButtonColors.getDefaultFilledTonalButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultFilledTonalButtonColors, FilledTonalButtonTokens.INSTANCE.getContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultFilledTonalButtonColors, FilledTonalButtonTokens.INSTANCE.getLabelTextColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalButtonColors, FilledTonalButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalButtonColors, FilledTonalButtonTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            ButtonColors it2 = new ButtonColors(fromToken, fromToken2, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultFilledTonalButtonColors.setDefaultFilledTonalButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final ButtonColors outlinedButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1344886725, "C(outlinedButtonColors)701@32872L11:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1344886725, $changed, -1, "androidx.compose.material3.ButtonDefaults.outlinedButtonColors (Button.kt:701)");
        }
        ButtonColors defaultOutlinedButtonColors$material3_release = getDefaultOutlinedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultOutlinedButtonColors$material3_release;
    }

    /* renamed from: outlinedButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1823outlinedButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1778526249, "C(outlinedButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)719@33731L11:Button.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1778526249, $changed, -1, "androidx.compose.material3.ButtonDefaults.outlinedButtonColors (Button.kt:719)");
        }
        ButtonColors m1808copyjRlVdoo = getDefaultOutlinedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1808copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1808copyjRlVdoo;
    }

    public final ButtonColors getDefaultOutlinedButtonColors$material3_release(ColorScheme $this$defaultOutlinedButtonColors) {
        long m4161copywmQWz5c;
        ButtonColors it = $this$defaultOutlinedButtonColors.getDefaultOutlinedButtonColorsCached();
        if (it != null) {
            return it;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultOutlinedButtonColors, OutlinedButtonTokens.INSTANCE.getLabelTextColor());
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedButtonColors, OutlinedButtonTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        ButtonColors it2 = new ButtonColors(m4198getTransparent0d7_KjU, fromToken, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, null);
        $this$defaultOutlinedButtonColors.setDefaultOutlinedButtonColorsCached$material3_release(it2);
        return it2;
    }

    public final ButtonColors textButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1880341584, "C(textButtonColors)744@34895L11:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1880341584, $changed, -1, "androidx.compose.material3.ButtonDefaults.textButtonColors (Button.kt:744)");
        }
        ButtonColors defaultTextButtonColors$material3_release = getDefaultTextButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextButtonColors$material3_release;
    }

    /* renamed from: textButtonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1824textButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1402274782, "C(textButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)762@35725L11:Button.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1402274782, $changed, -1, "androidx.compose.material3.ButtonDefaults.textButtonColors (Button.kt:762)");
        }
        ButtonColors m1808copyjRlVdoo = getDefaultTextButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1808copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1808copyjRlVdoo;
    }

    public final ButtonColors getDefaultTextButtonColors$material3_release(ColorScheme $this$defaultTextButtonColors) {
        long m4161copywmQWz5c;
        ButtonColors it = $this$defaultTextButtonColors.getDefaultTextButtonColorsCached();
        if (it != null) {
            return it;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultTextButtonColors, TextButtonTokens.INSTANCE.getLabelTextColor());
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextButtonColors, TextButtonTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        ButtonColors it2 = new ButtonColors(m4198getTransparent0d7_KjU, fromToken, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, null);
        $this$defaultTextButtonColors.setDefaultTextButtonColorsCached$material3_release(it2);
        return it2;
    }

    /* renamed from: buttonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1814buttonElevationR_JCAzs(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1827791191, "C(buttonElevation)P(0:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,3:c#ui.unit.Dp,1:c#ui.unit.Dp):Button.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = FilledButtonTokens.INSTANCE.m3243getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = FilledButtonTokens.INSTANCE.m3249getPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = FilledButtonTokens.INSTANCE.m3246getFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        if ((i & 8) != 0) {
            float hoveredElevation3 = FilledButtonTokens.INSTANCE.m3247getHoverContainerElevationD9Ej5fM();
            hoveredElevation2 = hoveredElevation3;
        } else {
            hoveredElevation2 = hoveredElevation;
        }
        float disabledElevation2 = (i & 16) != 0 ? FilledButtonTokens.INSTANCE.m3245getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1827791191, $changed, -1, "androidx.compose.material3.ButtonDefaults.buttonElevation (Button.kt:802)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return buttonElevation;
    }

    /* renamed from: elevatedButtonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1816elevatedButtonElevationR_JCAzs(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1065482445, "C(elevatedButtonElevation)P(0:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,3:c#ui.unit.Dp,1:c#ui.unit.Dp):Button.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = ElevatedButtonTokens.INSTANCE.m3162getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = ElevatedButtonTokens.INSTANCE.m3168getPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = ElevatedButtonTokens.INSTANCE.m3165getFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        if ((i & 8) != 0) {
            float hoveredElevation3 = ElevatedButtonTokens.INSTANCE.m3166getHoverContainerElevationD9Ej5fM();
            hoveredElevation2 = hoveredElevation3;
        } else {
            hoveredElevation2 = hoveredElevation;
        }
        float disabledElevation2 = (i & 16) != 0 ? ElevatedButtonTokens.INSTANCE.m3164getDisabledContainerElevationD9Ej5fM() : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1065482445, $changed, -1, "androidx.compose.material3.ButtonDefaults.elevatedButtonElevation (Button.kt:829)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return buttonElevation;
    }

    /* renamed from: filledTonalButtonElevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1818filledTonalButtonElevationR_JCAzs(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 5982871, "C(filledTonalButtonElevation)P(0:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,3:c#ui.unit.Dp,1:c#ui.unit.Dp):Button.kt#uh7d8r");
        if ((i & 1) != 0) {
            float defaultElevation3 = FilledTonalButtonTokens.INSTANCE.m3266getContainerElevationD9Ej5fM();
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = FilledTonalButtonTokens.INSTANCE.m3272getPressedContainerElevationD9Ej5fM();
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float focusedElevation3 = FilledTonalButtonTokens.INSTANCE.m3269getFocusContainerElevationD9Ej5fM();
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        if ((i & 8) != 0) {
            float hoveredElevation3 = FilledTonalButtonTokens.INSTANCE.m3270getHoverContainerElevationD9Ej5fM();
            hoveredElevation2 = hoveredElevation3;
        } else {
            hoveredElevation2 = hoveredElevation;
        }
        float disabledElevation2 = (i & 16) != 0 ? Dp.m6626constructorimpl(0) : disabledElevation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(5982871, $changed, -1, "androidx.compose.material3.ButtonDefaults.filledTonalButtonElevation (Button.kt:859)");
        }
        ButtonElevation buttonElevation = new ButtonElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return buttonElevation;
    }

    @Deprecated(message = "Please use the version that takes an `enabled` param to get the `BorderStroke` with the correct opacity", replaceWith = @ReplaceWith(expression = "outlinedButtonBorder(enabled)", imports = {}))
    public final BorderStroke getOutlinedButtonBorder(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -563957672, "C879@41501L5:Button.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-563957672, $changed, -1, "androidx.compose.material3.ButtonDefaults.<get-outlinedButtonBorder> (Button.kt:877)");
        }
        BorderStroke m254BorderStrokecXLIe8U = BorderStrokeKt.m254BorderStrokecXLIe8U(OutlinedButtonTokens.INSTANCE.m3348getOutlineWidthD9Ej5fM(), ColorSchemeKt.getValue(OutlinedButtonTokens.INSTANCE.getOutlineColor(), $composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m254BorderStrokecXLIe8U;
    }

    public final BorderStroke outlinedButtonBorder(boolean enabled, Composer $composer, int $changed, int i) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -626854767, "C(outlinedButtonBorder):Button.kt#uh7d8r");
        if ((i & 1) != 0) {
            enabled = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-626854767, $changed, -1, "androidx.compose.material3.ButtonDefaults.outlinedButtonBorder (Button.kt:889)");
        }
        float m3348getOutlineWidthD9Ej5fM = OutlinedButtonTokens.INSTANCE.m3348getOutlineWidthD9Ej5fM();
        if (enabled) {
            $composer.startReplaceGroup(-855870548);
            ComposerKt.sourceInformation($composer, "893@41926L5");
            m4161copywmQWz5c = ColorSchemeKt.getValue(OutlinedButtonTokens.INSTANCE.getOutlineColor(), $composer, 6);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-855783004);
            ComposerKt.sourceInformation($composer, "895@42011L5");
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r2) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedButtonTokens.INSTANCE.getOutlineColor(), $composer, 6)) : 0.0f);
            $composer.endReplaceGroup();
        }
        BorderStroke m254BorderStrokecXLIe8U = BorderStrokeKt.m254BorderStrokecXLIe8U(m3348getOutlineWidthD9Ej5fM, m4161copywmQWz5c);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m254BorderStrokecXLIe8U;
    }
}
