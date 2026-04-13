package androidx.compose.material3.tokens;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.GenericFontFamily;
import kotlin.Metadata;

/* compiled from: TypographyTokens.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006¨\u0006#"}, d2 = {"Landroidx/compose/material3/tokens/TypographyTokens;", "", "()V", "BodyLarge", "Landroidx/compose/ui/text/TextStyle;", "getBodyLarge", "()Landroidx/compose/ui/text/TextStyle;", "BodyMedium", "getBodyMedium", "BodySmall", "getBodySmall", "DisplayLarge", "getDisplayLarge", "DisplayMedium", "getDisplayMedium", "DisplaySmall", "getDisplaySmall", "HeadlineLarge", "getHeadlineLarge", "HeadlineMedium", "getHeadlineMedium", "HeadlineSmall", "getHeadlineSmall", "LabelLarge", "getLabelLarge", "LabelMedium", "getLabelMedium", "LabelSmall", "getLabelSmall", "TitleLarge", "getTitleLarge", "TitleMedium", "getTitleMedium", "TitleSmall", "getTitleSmall", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TypographyTokens {
    public static final int $stable = 0;
    private static final TextStyle BodyLarge;
    private static final TextStyle BodyMedium;
    private static final TextStyle BodySmall;
    private static final TextStyle DisplayLarge;
    private static final TextStyle DisplayMedium;
    private static final TextStyle DisplaySmall;
    private static final TextStyle HeadlineLarge;
    private static final TextStyle HeadlineMedium;
    private static final TextStyle HeadlineSmall;
    public static final TypographyTokens INSTANCE = new TypographyTokens();
    private static final TextStyle LabelLarge;
    private static final TextStyle LabelMedium;
    private static final TextStyle LabelSmall;
    private static final TextStyle TitleLarge;
    private static final TextStyle TitleMedium;
    private static final TextStyle TitleSmall;

    private TypographyTokens() {
    }

    public final TextStyle getBodyLarge() {
        return BodyLarge;
    }

    static {
        TextStyle defaultTextStyle = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyLargeFont = TypeScaleTokens.INSTANCE.getBodyLargeFont();
        BodyLarge = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle, 0L, TypeScaleTokens.INSTANCE.m3575getBodyLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyLargeWeight(), null, null, bodyLargeFont, null, TypeScaleTokens.INSTANCE.m3576getBodyLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3574getBodyLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle2 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyMediumFont = TypeScaleTokens.INSTANCE.getBodyMediumFont();
        BodyMedium = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle2, 0L, TypeScaleTokens.INSTANCE.m3578getBodyMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyMediumWeight(), null, null, bodyMediumFont, null, TypeScaleTokens.INSTANCE.m3579getBodyMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3577getBodyMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle3 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodySmallFont = TypeScaleTokens.INSTANCE.getBodySmallFont();
        BodySmall = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle3, 0L, TypeScaleTokens.INSTANCE.m3581getBodySmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodySmallWeight(), null, null, bodySmallFont, null, TypeScaleTokens.INSTANCE.m3582getBodySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3580getBodySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle4 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayLargeFont = TypeScaleTokens.INSTANCE.getDisplayLargeFont();
        DisplayLarge = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle4, 0L, TypeScaleTokens.INSTANCE.m3584getDisplayLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayLargeWeight(), null, null, displayLargeFont, null, TypeScaleTokens.INSTANCE.m3585getDisplayLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3583getDisplayLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle5 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayMediumFont = TypeScaleTokens.INSTANCE.getDisplayMediumFont();
        DisplayMedium = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle5, 0L, TypeScaleTokens.INSTANCE.m3587getDisplayMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayMediumWeight(), null, null, displayMediumFont, null, TypeScaleTokens.INSTANCE.m3588getDisplayMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3586getDisplayMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle6 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displaySmallFont = TypeScaleTokens.INSTANCE.getDisplaySmallFont();
        DisplaySmall = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle6, 0L, TypeScaleTokens.INSTANCE.m3590getDisplaySmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplaySmallWeight(), null, null, displaySmallFont, null, TypeScaleTokens.INSTANCE.m3591getDisplaySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3589getDisplaySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle7 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineLargeFont = TypeScaleTokens.INSTANCE.getHeadlineLargeFont();
        HeadlineLarge = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle7, 0L, TypeScaleTokens.INSTANCE.m3593getHeadlineLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineLargeWeight(), null, null, headlineLargeFont, null, TypeScaleTokens.INSTANCE.m3594getHeadlineLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3592getHeadlineLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle8 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineMediumFont = TypeScaleTokens.INSTANCE.getHeadlineMediumFont();
        HeadlineMedium = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle8, 0L, TypeScaleTokens.INSTANCE.m3596getHeadlineMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineMediumWeight(), null, null, headlineMediumFont, null, TypeScaleTokens.INSTANCE.m3597getHeadlineMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3595getHeadlineMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle9 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineSmallFont = TypeScaleTokens.INSTANCE.getHeadlineSmallFont();
        HeadlineSmall = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle9, 0L, TypeScaleTokens.INSTANCE.m3599getHeadlineSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineSmallWeight(), null, null, headlineSmallFont, null, TypeScaleTokens.INSTANCE.m3600getHeadlineSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3598getHeadlineSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle10 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelLargeFont = TypeScaleTokens.INSTANCE.getLabelLargeFont();
        LabelLarge = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle10, 0L, TypeScaleTokens.INSTANCE.m3602getLabelLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelLargeWeight(), null, null, labelLargeFont, null, TypeScaleTokens.INSTANCE.m3603getLabelLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3601getLabelLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle11 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelMediumFont = TypeScaleTokens.INSTANCE.getLabelMediumFont();
        LabelMedium = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle11, 0L, TypeScaleTokens.INSTANCE.m3605getLabelMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelMediumWeight(), null, null, labelMediumFont, null, TypeScaleTokens.INSTANCE.m3606getLabelMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3604getLabelMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle12 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelSmallFont = TypeScaleTokens.INSTANCE.getLabelSmallFont();
        LabelSmall = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle12, 0L, TypeScaleTokens.INSTANCE.m3608getLabelSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelSmallWeight(), null, null, labelSmallFont, null, TypeScaleTokens.INSTANCE.m3609getLabelSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3607getLabelSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle13 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleLargeFont = TypeScaleTokens.INSTANCE.getTitleLargeFont();
        TitleLarge = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle13, 0L, TypeScaleTokens.INSTANCE.m3611getTitleLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleLargeWeight(), null, null, titleLargeFont, null, TypeScaleTokens.INSTANCE.m3612getTitleLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3610getTitleLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle14 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleMediumFont = TypeScaleTokens.INSTANCE.getTitleMediumFont();
        TitleMedium = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle14, 0L, TypeScaleTokens.INSTANCE.m3614getTitleMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleMediumWeight(), null, null, titleMediumFont, null, TypeScaleTokens.INSTANCE.m3615getTitleMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3613getTitleMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle15 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleSmallFont = TypeScaleTokens.INSTANCE.getTitleSmallFont();
        TitleSmall = TextStyle.m6105copyp1EtxEg$default(defaultTextStyle15, 0L, TypeScaleTokens.INSTANCE.m3617getTitleSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleSmallWeight(), null, null, titleSmallFont, null, TypeScaleTokens.INSTANCE.m3618getTitleSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m3616getTitleSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
    }

    public final TextStyle getBodyMedium() {
        return BodyMedium;
    }

    public final TextStyle getBodySmall() {
        return BodySmall;
    }

    public final TextStyle getDisplayLarge() {
        return DisplayLarge;
    }

    public final TextStyle getDisplayMedium() {
        return DisplayMedium;
    }

    public final TextStyle getDisplaySmall() {
        return DisplaySmall;
    }

    public final TextStyle getHeadlineLarge() {
        return HeadlineLarge;
    }

    public final TextStyle getHeadlineMedium() {
        return HeadlineMedium;
    }

    public final TextStyle getHeadlineSmall() {
        return HeadlineSmall;
    }

    public final TextStyle getLabelLarge() {
        return LabelLarge;
    }

    public final TextStyle getLabelMedium() {
        return LabelMedium;
    }

    public final TextStyle getLabelSmall() {
        return LabelSmall;
    }

    public final TextStyle getTitleLarge() {
        return TitleLarge;
    }

    public final TextStyle getTitleMedium() {
        return TitleMedium;
    }

    public final TextStyle getTitleSmall() {
        return TitleSmall;
    }
}
