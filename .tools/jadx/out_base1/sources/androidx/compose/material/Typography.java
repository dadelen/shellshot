package androidx.compose.material;

import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Typography.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0093\u0001\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012Bo\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013J\u0088\u0001\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u0006*"}, d2 = {"Landroidx/compose/material/Typography;", "", "defaultFontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "h1", "Landroidx/compose/ui/text/TextStyle;", "h2", "h3", "h4", "h5", "h6", "subtitle1", "subtitle2", "body1", "body2", "button", "caption", "overline", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "getBody1", "()Landroidx/compose/ui/text/TextStyle;", "getBody2", "getButton", "getCaption", "getH1", "getH2", "getH3", "getH4", "getH5", "getH6", "getOverline", "getSubtitle1", "getSubtitle2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Typography {
    public static final int $stable = 0;
    private final TextStyle body1;
    private final TextStyle body2;
    private final TextStyle button;
    private final TextStyle caption;
    private final TextStyle h1;
    private final TextStyle h2;
    private final TextStyle h3;
    private final TextStyle h4;
    private final TextStyle h5;
    private final TextStyle h6;
    private final TextStyle overline;
    private final TextStyle subtitle1;
    private final TextStyle subtitle2;

    public Typography(TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.h6 = h6;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.body1 = body1;
        this.body2 = body2;
        this.button = button;
        this.caption = caption;
        this.overline = overline;
    }

    public final TextStyle getH1() {
        return this.h1;
    }

    public final TextStyle getH2() {
        return this.h2;
    }

    public final TextStyle getH3() {
        return this.h3;
    }

    public final TextStyle getH4() {
        return this.h4;
    }

    public final TextStyle getH5() {
        return this.h5;
    }

    public final TextStyle getH6() {
        return this.h6;
    }

    public final TextStyle getSubtitle1() {
        return this.subtitle1;
    }

    public final TextStyle getSubtitle2() {
        return this.subtitle2;
    }

    public final TextStyle getBody1() {
        return this.body1;
    }

    public final TextStyle getBody2() {
        return this.body2;
    }

    public final TextStyle getButton() {
        return this.button;
    }

    public final TextStyle getCaption() {
        return this.caption;
    }

    public final TextStyle getOverline() {
        return this.overline;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Typography(androidx.compose.ui.text.font.FontFamily r49, androidx.compose.ui.text.TextStyle r50, androidx.compose.ui.text.TextStyle r51, androidx.compose.ui.text.TextStyle r52, androidx.compose.ui.text.TextStyle r53, androidx.compose.ui.text.TextStyle r54, androidx.compose.ui.text.TextStyle r55, androidx.compose.ui.text.TextStyle r56, androidx.compose.ui.text.TextStyle r57, androidx.compose.ui.text.TextStyle r58, androidx.compose.ui.text.TextStyle r59, androidx.compose.ui.text.TextStyle r60, androidx.compose.ui.text.TextStyle r61, androidx.compose.ui.text.TextStyle r62, int r63, kotlin.jvm.internal.DefaultConstructorMarker r64) {
        /*
            Method dump skipped, instructions count: 1200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.Typography.<init>(androidx.compose.ui.text.font.FontFamily, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Typography(androidx.compose.ui.text.font.FontFamily r17, androidx.compose.ui.text.TextStyle r18, androidx.compose.ui.text.TextStyle r19, androidx.compose.ui.text.TextStyle r20, androidx.compose.ui.text.TextStyle r21, androidx.compose.ui.text.TextStyle r22, androidx.compose.ui.text.TextStyle r23, androidx.compose.ui.text.TextStyle r24, androidx.compose.ui.text.TextStyle r25, androidx.compose.ui.text.TextStyle r26, androidx.compose.ui.text.TextStyle r27, androidx.compose.ui.text.TextStyle r28, androidx.compose.ui.text.TextStyle r29, androidx.compose.ui.text.TextStyle r30) {
        /*
            r16 = this;
            r0 = r17
            r1 = r18
            androidx.compose.ui.text.TextStyle r3 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r1, r0)
            r2 = r19
            androidx.compose.ui.text.TextStyle r4 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r2, r0)
            r5 = r20
            androidx.compose.ui.text.TextStyle r6 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r5, r0)
            r7 = r21
            r5 = r6
            androidx.compose.ui.text.TextStyle r6 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r7, r0)
            r8 = r22
            androidx.compose.ui.text.TextStyle r7 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r8, r0)
            r9 = r23
            androidx.compose.ui.text.TextStyle r8 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r9, r0)
            r10 = r24
            androidx.compose.ui.text.TextStyle r9 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r10, r0)
            r11 = r25
            androidx.compose.ui.text.TextStyle r10 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r11, r0)
            r12 = r26
            androidx.compose.ui.text.TextStyle r11 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r12, r0)
            r13 = r27
            androidx.compose.ui.text.TextStyle r12 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r13, r0)
            r14 = r28
            androidx.compose.ui.text.TextStyle r13 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r14, r0)
            r15 = r29
            androidx.compose.ui.text.TextStyle r14 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r15, r0)
            r1 = r30
            androidx.compose.ui.text.TextStyle r15 = androidx.compose.material.TypographyKt.access$withDefaultFontFamily(r1, r0)
            r2 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.Typography.<init>(androidx.compose.ui.text.font.FontFamily, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle):void");
    }

    public static /* synthetic */ Typography copy$default(Typography typography, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, int i, Object obj) {
        if ((i & 1) != 0) {
            textStyle = typography.h1;
        }
        return typography.copy(textStyle, (i & 2) != 0 ? typography.h2 : textStyle2, (i & 4) != 0 ? typography.h3 : textStyle3, (i & 8) != 0 ? typography.h4 : textStyle4, (i & 16) != 0 ? typography.h5 : textStyle5, (i & 32) != 0 ? typography.h6 : textStyle6, (i & 64) != 0 ? typography.subtitle1 : textStyle7, (i & 128) != 0 ? typography.subtitle2 : textStyle8, (i & 256) != 0 ? typography.body1 : textStyle9, (i & 512) != 0 ? typography.body2 : textStyle10, (i & 1024) != 0 ? typography.button : textStyle11, (i & 2048) != 0 ? typography.caption : textStyle12, (i & 4096) != 0 ? typography.overline : textStyle13);
    }

    public final Typography copy(TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        return new Typography(h1, h2, h3, h4, h5, h6, subtitle1, subtitle2, body1, body2, button, caption, overline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Typography) && Intrinsics.areEqual(this.h1, ((Typography) other).h1) && Intrinsics.areEqual(this.h2, ((Typography) other).h2) && Intrinsics.areEqual(this.h3, ((Typography) other).h3) && Intrinsics.areEqual(this.h4, ((Typography) other).h4) && Intrinsics.areEqual(this.h5, ((Typography) other).h5) && Intrinsics.areEqual(this.h6, ((Typography) other).h6) && Intrinsics.areEqual(this.subtitle1, ((Typography) other).subtitle1) && Intrinsics.areEqual(this.subtitle2, ((Typography) other).subtitle2) && Intrinsics.areEqual(this.body1, ((Typography) other).body1) && Intrinsics.areEqual(this.body2, ((Typography) other).body2) && Intrinsics.areEqual(this.button, ((Typography) other).button) && Intrinsics.areEqual(this.caption, ((Typography) other).caption) && Intrinsics.areEqual(this.overline, ((Typography) other).overline);
    }

    public int hashCode() {
        int result = this.h1.hashCode();
        return (((((((((((((((((((((((result * 31) + this.h2.hashCode()) * 31) + this.h3.hashCode()) * 31) + this.h4.hashCode()) * 31) + this.h5.hashCode()) * 31) + this.h6.hashCode()) * 31) + this.subtitle1.hashCode()) * 31) + this.subtitle2.hashCode()) * 31) + this.body1.hashCode()) * 31) + this.body2.hashCode()) * 31) + this.button.hashCode()) * 31) + this.caption.hashCode()) * 31) + this.overline.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Typography(h1=").append(this.h1).append(", h2=").append(this.h2).append(", h3=").append(this.h3).append(", h4=").append(this.h4).append(", h5=").append(this.h5).append(", h6=").append(this.h6).append(", subtitle1=").append(this.subtitle1).append(", subtitle2=").append(this.subtitle2).append(", body1=").append(this.body1).append(", body2=").append(this.body2).append(", button=").append(this.button).append(", caption=");
        sb.append(this.caption).append(", overline=").append(this.overline).append(')');
        return sb.toString();
    }
}
