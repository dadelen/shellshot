package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.TextUnit;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: Text.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\u001aæ\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\u0014\b\u0002\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00060-2\b\b\u0002\u0010/\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001aÜ\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\u0014\b\u0002\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00060-2\b\b\u0002\u0010/\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001aÆ\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020*2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00060-2\b\b\u0002\u0010/\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001aÒ\u0001\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020*2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\u0016\b\u0002\u0010,\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0006\u0018\u00010-2\b\b\u0002\u0010/\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00106\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"LocalTextStyle", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/text/TextStyle;", "getLocalTextStyle", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ProvideTextStyle", "", "value", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Text", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "letterSpacing", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "textAlign", "Landroidx/compose/ui/text/style/TextAlign;", "lineHeight", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "inlineContent", "", "", "Landroidx/compose/foundation/text/InlineTextContent;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "style", "Text-IbK3jfQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text--4IGK_g", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text-fLXpl1I", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextKt {
    private static final ProvidableCompositionLocal<TextStyle> LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<TextStyle>() { // from class: androidx.compose.material.TextKt$LocalTextStyle$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextStyle invoke() {
            return TypographyKt.getDefaultTextStyle();
        }
    });

    /* JADX WARN: Removed duplicated region for block: B:95:0x054b  */
    /* renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1721Text4IGK_g(final java.lang.String r52, androidx.compose.ui.Modifier r53, long r54, long r56, androidx.compose.ui.text.font.FontStyle r58, androidx.compose.ui.text.font.FontWeight r59, androidx.compose.ui.text.font.FontFamily r60, long r61, androidx.compose.ui.text.style.TextDecoration r63, androidx.compose.ui.text.style.TextAlign r64, long r65, int r67, boolean r68, int r69, int r70, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r71, androidx.compose.ui.text.TextStyle r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 1422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m1721Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x023c, code lost:
    
        if (r4.changed(r62) != false) goto L177;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    /* renamed from: Text-fLXpl1I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m1723TextfLXpl1I(final java.lang.String r43, androidx.compose.ui.Modifier r44, long r45, long r47, androidx.compose.ui.text.font.FontStyle r49, androidx.compose.ui.text.font.FontWeight r50, androidx.compose.ui.text.font.FontFamily r51, long r52, androidx.compose.ui.text.style.TextDecoration r54, androidx.compose.ui.text.style.TextAlign r55, long r56, int r58, boolean r59, int r60, kotlin.jvm.functions.Function1 r61, androidx.compose.ui.text.TextStyle r62, androidx.compose.runtime.Composer r63, final int r64, final int r65, final int r66) {
        /*
            Method dump skipped, instructions count: 1112
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m1723TextfLXpl1I(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:98:0x05b9  */
    /* renamed from: Text-IbK3jfQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1722TextIbK3jfQ(final androidx.compose.ui.text.AnnotatedString r52, androidx.compose.ui.Modifier r53, long r54, long r56, androidx.compose.ui.text.font.FontStyle r58, androidx.compose.ui.text.font.FontWeight r59, androidx.compose.ui.text.font.FontFamily r60, long r61, androidx.compose.ui.text.style.TextDecoration r63, androidx.compose.ui.text.style.TextAlign r64, long r65, int r67, boolean r68, int r69, int r70, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r71, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r72, androidx.compose.ui.text.TextStyle r73, androidx.compose.runtime.Composer r74, final int r75, final int r76, final int r77) {
        /*
            Method dump skipped, instructions count: 1541
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m1722TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    /* renamed from: Text--4IGK_g, reason: not valid java name */
    public static final /* synthetic */ void m1720Text4IGK_g(final AnnotatedString text, Modifier modifier, long color, long fontSize, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long letterSpacing, TextDecoration textDecoration, TextAlign textAlign, long lineHeight, int overflow, boolean softWrap, int maxLines, Map inlineContent, Function1 onTextLayout, TextStyle style, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        int i2;
        long color2;
        long fontSize2;
        FontStyle fontStyle2;
        FontWeight fontWeight2;
        int $dirty;
        int $dirty1;
        int i3;
        int i4;
        int $dirty12;
        int i5;
        boolean softWrap2;
        int $dirty13;
        TextStyle style2;
        boolean softWrap3;
        long color3;
        Function1 onTextLayout2;
        int overflow2;
        Map inlineContent2;
        FontStyle fontStyle3;
        long fontSize3;
        Modifier modifier3;
        FontWeight fontWeight3;
        FontFamily fontFamily2;
        TextDecoration textDecoration2;
        TextAlign textAlign2;
        long letterSpacing2;
        long letterSpacing3;
        int maxLines2;
        Composer $composer2;
        final Modifier modifier4;
        final long color4;
        final long fontSize4;
        final FontStyle fontStyle4;
        final FontWeight fontWeight4;
        final FontFamily fontFamily3;
        final long letterSpacing4;
        final TextDecoration textDecoration3;
        final TextAlign textAlign3;
        final long lineHeight2;
        final int overflow3;
        final boolean softWrap4;
        final int maxLines3;
        final Map inlineContent3;
        final Function1 onTextLayout3;
        final TextStyle style3;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-422393234);
        ComposerKt.sourceInformation($composer3, "C(Text)P(14,9,0:c#ui.graphics.Color,2:c#ui.unit.TextUnit,3:c#ui.text.font.FontStyle,4!1,6:c#ui.unit.TextUnit,16,15:c#ui.text.style.TextAlign,7:c#ui.unit.TextUnit,11:c#ui.text.style.TextOverflow,12,8)354@16476L7,356@16492L345:Text.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(text) ? 4 : 2;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty2 |= 384;
            i2 = i7;
            color2 = color;
        } else if (($changed & 384) == 0) {
            i2 = i7;
            color2 = color;
            $dirty2 |= $composer3.changed(color2) ? 256 : 128;
        } else {
            i2 = i7;
            color2 = color;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty2 |= 3072;
            fontSize2 = fontSize;
        } else if (($changed & 3072) == 0) {
            fontSize2 = fontSize;
            $dirty2 |= $composer3.changed(fontSize2) ? 2048 : 1024;
        } else {
            fontSize2 = fontSize;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty2 |= 24576;
            fontStyle2 = fontStyle;
        } else if (($changed & 24576) == 0) {
            fontStyle2 = fontStyle;
            $dirty2 |= $composer3.changed(fontStyle2) ? 16384 : 8192;
        } else {
            fontStyle2 = fontStyle;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fontWeight2 = fontWeight;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            fontWeight2 = fontWeight;
            $dirty2 |= $composer3.changed(fontWeight2) ? 131072 : 65536;
        } else {
            fontWeight2 = fontWeight;
        }
        int i12 = i & 64;
        if (i12 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(fontFamily) ? 1048576 : 524288;
        }
        int $dirty3 = i & 128;
        if ($dirty3 != 0) {
            $dirty = $dirty2 | 12582912;
            $dirty1 = $changed1;
        } else if (($changed & 12582912) == 0) {
            $dirty1 = $changed1;
            $dirty = $dirty2 | ($composer3.changed(letterSpacing) ? 8388608 : 4194304);
        } else {
            $dirty = $dirty2;
            $dirty1 = $changed1;
        }
        int i13 = i & 256;
        if (i13 != 0) {
            $dirty |= 100663296;
            i3 = i13;
        } else if (($changed & 100663296) == 0) {
            i3 = i13;
            $dirty |= $composer3.changed(textDecoration) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i13;
        }
        int i14 = i & 512;
        if (i14 != 0) {
            $dirty |= 805306368;
            i4 = i14;
        } else if (($changed & 805306368) == 0) {
            i4 = i14;
            $dirty |= $composer3.changed(textAlign) ? 536870912 : 268435456;
        } else {
            i4 = i14;
        }
        int $dirty4 = $dirty;
        int $dirty14 = i & 1024;
        if ($dirty14 != 0) {
            $dirty12 = $dirty1 | 6;
        } else if (($changed1 & 6) == 0) {
            $dirty12 = $dirty1 | ($composer3.changed(lineHeight) ? 4 : 2);
        } else {
            $dirty12 = $dirty1;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty12 |= 48;
            i5 = i15;
        } else if (($changed1 & 48) == 0) {
            i5 = i15;
            $dirty12 |= $composer3.changed(overflow) ? 32 : 16;
        } else {
            i5 = i15;
        }
        int $dirty15 = $dirty12;
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty15 |= 384;
            softWrap2 = softWrap;
        } else if (($changed1 & 384) == 0) {
            softWrap2 = softWrap;
            $dirty15 |= $composer3.changed(softWrap2) ? 256 : 128;
        } else {
            softWrap2 = softWrap;
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty13 = $dirty15 | 3072;
        } else {
            int $dirty16 = $dirty15;
            int $dirty17 = $changed1 & 3072;
            if ($dirty17 == 0) {
                $dirty13 = $dirty16 | ($composer3.changed(maxLines) ? 2048 : 1024);
            } else {
                $dirty13 = $dirty16;
            }
        }
        int i18 = i & 16384;
        if (i18 != 0) {
            $dirty13 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty13 |= $composer3.changedInstance(inlineContent) ? 16384 : 8192;
        }
        int i19 = i & 32768;
        if (i19 != 0) {
            $dirty13 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty13 |= $composer3.changedInstance(onTextLayout) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            if ((i & 65536) == 0 && $composer3.changed(style)) {
                i6 = 1048576;
                $dirty13 |= i6;
            }
            i6 = 524288;
            $dirty13 |= i6;
        }
        if (($dirty4 & 306783379) == 306783378 && (599187 & $dirty13) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            overflow3 = overflow;
            maxLines3 = maxLines;
            inlineContent3 = inlineContent;
            onTextLayout3 = onTextLayout;
            style3 = style;
            $composer2 = $composer3;
            softWrap4 = softWrap2;
            fontStyle4 = fontStyle2;
            color4 = color2;
            fontSize4 = fontSize2;
            modifier4 = modifier2;
            fontWeight4 = fontWeight2;
            fontFamily3 = fontFamily;
            letterSpacing4 = letterSpacing;
            textDecoration3 = textDecoration;
            textAlign3 = textAlign;
            lineHeight2 = lineHeight;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    color2 = Color.INSTANCE.m4199getUnspecified0d7_KjU();
                }
                if (i9 != 0) {
                    fontSize2 = TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE();
                }
                if (i10 != 0) {
                    fontStyle2 = null;
                }
                if (i11 != 0) {
                    fontWeight2 = null;
                }
                FontFamily fontFamily4 = i12 != 0 ? null : fontFamily;
                long letterSpacing5 = $dirty3 != 0 ? TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE() : letterSpacing;
                TextDecoration textDecoration4 = i3 != 0 ? null : textDecoration;
                TextAlign textAlign4 = i4 != 0 ? null : textAlign;
                long lineHeight3 = $dirty14 != 0 ? TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE() : lineHeight;
                int overflow4 = i5 != 0 ? TextOverflow.INSTANCE.m6542getClipgIe3tQ8() : overflow;
                if (i16 != 0) {
                    softWrap2 = true;
                }
                int maxLines4 = i17 != 0 ? Integer.MAX_VALUE : maxLines;
                Map inlineContent4 = i18 != 0 ? MapsKt.emptyMap() : inlineContent;
                TextKt$Text$8 onTextLayout4 = i19 != 0 ? new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.material.TextKt$Text$8
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                    }
                } : onTextLayout;
                if ((i & 65536) != 0) {
                    FontFamily fontFamily5 = fontFamily4;
                    ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
                    TextDecoration textDecoration5 = textDecoration4;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    style2 = (TextStyle) consume;
                    $dirty13 &= -3670017;
                    softWrap3 = softWrap2;
                    color3 = color2;
                    onTextLayout2 = onTextLayout4;
                    overflow2 = overflow4;
                    inlineContent2 = inlineContent4;
                    fontStyle3 = fontStyle2;
                    fontSize3 = fontSize2;
                    modifier3 = modifier2;
                    fontWeight3 = fontWeight2;
                    fontFamily2 = fontFamily5;
                    textDecoration2 = textDecoration5;
                    int i20 = maxLines4;
                    textAlign2 = textAlign4;
                    letterSpacing2 = letterSpacing5;
                    letterSpacing3 = lineHeight3;
                    maxLines2 = i20;
                } else {
                    style2 = style;
                    softWrap3 = softWrap2;
                    color3 = color2;
                    onTextLayout2 = onTextLayout4;
                    overflow2 = overflow4;
                    inlineContent2 = inlineContent4;
                    fontStyle3 = fontStyle2;
                    fontSize3 = fontSize2;
                    modifier3 = modifier2;
                    fontWeight3 = fontWeight2;
                    fontFamily2 = fontFamily4;
                    textDecoration2 = textDecoration4;
                    int i21 = maxLines4;
                    textAlign2 = textAlign4;
                    letterSpacing2 = letterSpacing5;
                    letterSpacing3 = lineHeight3;
                    maxLines2 = i21;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 65536) != 0) {
                    textAlign2 = textAlign;
                    letterSpacing3 = lineHeight;
                    overflow2 = overflow;
                    maxLines2 = maxLines;
                    inlineContent2 = inlineContent;
                    onTextLayout2 = onTextLayout;
                    style2 = style;
                    $dirty13 = (-3670017) & $dirty13;
                    softWrap3 = softWrap2;
                    color3 = color2;
                    modifier3 = modifier2;
                    letterSpacing2 = letterSpacing;
                    fontStyle3 = fontStyle2;
                    fontSize3 = fontSize2;
                    fontWeight3 = fontWeight2;
                    fontFamily2 = fontFamily;
                    textDecoration2 = textDecoration;
                } else {
                    textAlign2 = textAlign;
                    letterSpacing3 = lineHeight;
                    overflow2 = overflow;
                    maxLines2 = maxLines;
                    inlineContent2 = inlineContent;
                    onTextLayout2 = onTextLayout;
                    style2 = style;
                    softWrap3 = softWrap2;
                    color3 = color2;
                    modifier3 = modifier2;
                    letterSpacing2 = letterSpacing;
                    fontStyle3 = fontStyle2;
                    fontSize3 = fontSize2;
                    fontWeight3 = fontWeight2;
                    fontFamily2 = fontFamily;
                    textDecoration2 = textDecoration;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-422393234, $dirty4, $dirty13, "androidx.compose.material.Text (Text.kt:355)");
            }
            $composer2 = $composer3;
            m1722TextIbK3jfQ(text, modifier3, color3, fontSize3, fontStyle3, fontWeight3, fontFamily2, letterSpacing2, textDecoration2, textAlign2, letterSpacing3, overflow2, softWrap3, maxLines2, 1, inlineContent2, onTextLayout2, style2, $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 896) | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (3670016 & $dirty4) | (29360128 & $dirty4) | (234881024 & $dirty4) | (1879048192 & $dirty4), ($dirty13 & 14) | 24576 | ($dirty13 & 112) | ($dirty13 & 896) | ($dirty13 & 7168) | (($dirty13 << 3) & 458752) | (($dirty13 << 3) & 3670016) | (($dirty13 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            color4 = color3;
            fontSize4 = fontSize3;
            fontStyle4 = fontStyle3;
            fontWeight4 = fontWeight3;
            fontFamily3 = fontFamily2;
            letterSpacing4 = letterSpacing2;
            textDecoration3 = textDecoration2;
            textAlign3 = textAlign2;
            lineHeight2 = letterSpacing3;
            overflow3 = overflow2;
            softWrap4 = softWrap3;
            maxLines3 = maxLines2;
            inlineContent3 = inlineContent2;
            onTextLayout3 = onTextLayout2;
            style3 = style2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextKt$Text$9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i22) {
                    TextKt.m1720Text4IGK_g(AnnotatedString.this, modifier4, color4, fontSize4, fontStyle4, fontWeight4, fontFamily3, letterSpacing4, textDecoration3, textAlign3, lineHeight2, overflow3, softWrap4, maxLines3, inlineContent3, onTextLayout3, style3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<TextStyle> getLocalTextStyle() {
        return LocalTextStyle;
    }

    public static final void ProvideTextStyle(final TextStyle value, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1772272796);
        ComposerKt.sourceInformation($composer2, "C(ProvideTextStyle)P(1)397@17792L7,398@17817L80:Text.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1772272796, $dirty, -1, "androidx.compose.material.ProvideTextStyle (Text.kt:396)");
            }
            ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextStyle mergedStyle = ((TextStyle) consume).merge(value);
            CompositionLocalKt.CompositionLocalProvider(LocalTextStyle.provides(mergedStyle), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextKt$ProvideTextStyle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TextKt.ProvideTextStyle(TextStyle.this, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
