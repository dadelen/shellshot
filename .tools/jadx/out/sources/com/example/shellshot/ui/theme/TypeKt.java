package com.example.shellshot.ui.theme;

import androidx.compose.material3.Typography;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Type.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"BaseFontFamily", "Landroidx/compose/ui/text/font/GenericFontFamily;", "AppTypography", "Landroidx/compose/material3/Typography;", "getAppTypography", "()Landroidx/compose/material3/Typography;", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TypeKt {
    private static final Typography AppTypography;
    private static final GenericFontFamily BaseFontFamily = FontFamily.INSTANCE.getSansSerif();

    static {
        GenericFontFamily genericFontFamily = BaseFontFamily;
        TextStyle textStyle = new TextStyle(0L, TextUnitKt.getSp(38), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, genericFontFamily, (String) null, TextUnitKt.getSp(-0.8d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(44), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null);
        GenericFontFamily genericFontFamily2 = BaseFontFamily;
        TextStyle textStyle2 = new TextStyle(0L, TextUnitKt.getSp(34), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, genericFontFamily2, (String) null, TextUnitKt.getSp(-0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(40), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null);
        GenericFontFamily genericFontFamily3 = BaseFontFamily;
        AppTypography = new Typography(null, null, textStyle, textStyle2, new TextStyle(0L, TextUnitKt.getSp(28), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, genericFontFamily3, (String) null, TextUnitKt.getSp(-0.2d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(34), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null), null, new TextStyle(0L, TextUnitKt.getSp(21), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(28), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(18), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(24), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(15), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(22), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(24), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(21), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(13), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(19), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(16), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), new TextStyle(0L, TextUnitKt.getSp(11), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, BaseFontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(15), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null), null, 16419, null);
    }

    public static final Typography getAppTypography() {
        return AppTypography;
    }
}
