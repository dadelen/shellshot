package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.SelectableTextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.SelectionController;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicText.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0084\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001az\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001ad\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001an\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010 \u001az\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010!\u001a°\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010#\u001a\u00020\u000e2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a@\u0010,\u001a\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020/\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u0001000.\u0018\u00010-2\f\u00102\u001a\b\u0012\u0004\u0012\u0002030-2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e00H\u0002\u001a\u001e\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u000207062\b\u00108\u001a\u0004\u0018\u000109H\u0002\u001aÂ\u0001\u0010:\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\u0014\u0010;\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<\u0018\u00010-2\u001c\u0010>\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010?0-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006B²\u0006\n\u0010C\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"BasicText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "inlineContent", "", "", "Landroidx/compose/foundation/text/InlineTextContent;", "BasicText-VhcvRP8", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "BasicText-RWo7tUw", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "BasicText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "BasicText-BpD7jsM", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILandroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "LayoutWithLinksAndInlineContent", "hasInlineContent", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "selectionController", "Landroidx/compose/foundation/text/modifiers/SelectionController;", "onShowTranslation", "Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "LayoutWithLinksAndInlineContent-vOo2fZY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Lkotlin/jvm/functions/Function1;ZLjava/util/Map;Landroidx/compose/ui/text/TextStyle;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "measureWithTextRangeMeasureConstraints", "", "Lkotlin/Pair;", "Landroidx/compose/ui/layout/Placeable;", "Lkotlin/Function0;", "Landroidx/compose/ui/unit/IntOffset;", "measurables", "Landroidx/compose/ui/layout/Measurable;", "shouldMeasureLinks", "selectionIdSaver", "Landroidx/compose/runtime/saveable/Saver;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "textModifier", "placeholders", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "onPlaceholderLayout", "Landroidx/compose/ui/geometry/Rect;", "textModifier-cFh6CEA", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;)Landroidx/compose/ui/Modifier;", "foundation_release", "displayedText"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextKt {
    /* JADX WARN: Removed duplicated region for block: B:79:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x044d  */
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m967BasicTextVhcvRP8(final java.lang.String r45, androidx.compose.ui.Modifier r46, androidx.compose.ui.text.TextStyle r47, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r48, int r49, boolean r50, int r51, int r52, androidx.compose.ui.graphics.ColorProducer r53, androidx.compose.runtime.Composer r54, final int r55, final int r56) {
        /*
            Method dump skipped, instructions count: 1260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m967BasicTextVhcvRP8(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02b8  */
    /* renamed from: BasicText-RWo7tUw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m965BasicTextRWo7tUw(androidx.compose.ui.text.AnnotatedString r51, androidx.compose.ui.Modifier r52, androidx.compose.ui.text.TextStyle r53, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r54, int r55, boolean r56, int r57, int r58, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r59, androidx.compose.ui.graphics.ColorProducer r60, androidx.compose.runtime.Composer r61, final int r62, final int r63) {
        /*
            Method dump skipped, instructions count: 1430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m965BasicTextRWo7tUw(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, java.util.Map, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final AnnotatedString BasicText_RWo7tUw$lambda$5(MutableState<AnnotatedString> mutableState) {
        MutableState<AnnotatedString> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-BpD7jsM, reason: not valid java name */
    public static final /* synthetic */ void m964BasicTextBpD7jsM(final String text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, Composer $composer, final int $changed, final int i) {
        String str;
        Modifier modifier2;
        TextStyle style2;
        Function1 onTextLayout2;
        int overflow2;
        boolean softWrap2;
        Modifier modifier3;
        final TextStyle style3;
        final Function1 onTextLayout3;
        final int overflow3;
        final int maxLines2;
        final boolean softWrap3;
        Composer $composer2 = $composer.startRestartGroup(1022429478);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(6,1,5,2,3:c#ui.text.style.TextOverflow,4)279@12355L234:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            str = text;
        } else if (($changed & 6) == 0) {
            str = text;
            $dirty |= $composer2.changed(str) ? 4 : 2;
        } else {
            str = text;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            style2 = style;
        } else if (($changed & 384) == 0) {
            style2 = style;
            $dirty |= $composer2.changed(style2) ? 256 : 128;
        } else {
            style2 = style;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            overflow2 = overflow;
        } else if (($changed & 24576) == 0) {
            overflow2 = overflow;
            $dirty |= $composer2.changed(overflow2) ? 16384 : 8192;
        } else {
            overflow2 = overflow;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            softWrap2 = softWrap;
        } else if ((196608 & $changed) == 0) {
            softWrap2 = softWrap;
            $dirty |= $composer2.changed(softWrap2) ? 131072 : 65536;
        } else {
            softWrap2 = softWrap;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            Function1 function1 = onTextLayout2;
            modifier3 = modifier2;
            onTextLayout3 = function1;
            maxLines2 = maxLines;
            style3 = style2;
            overflow3 = overflow2;
            softWrap3 = softWrap2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i4 != 0) {
                onTextLayout2 = null;
            }
            if (i5 != 0) {
                overflow2 = TextOverflow.INSTANCE.m6542getClipgIe3tQ8();
            }
            if (i6 != 0) {
                softWrap2 = true;
            }
            int maxLines3 = i7 != 0 ? Integer.MAX_VALUE : maxLines;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1022429478, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:278)");
            }
            TextStyle style4 = style2;
            int overflow4 = overflow2;
            Function1 onTextLayout4 = onTextLayout2;
            modifier3 = modifier4;
            m967BasicTextVhcvRP8(str, modifier3, style4, (Function1<? super TextLayoutResult, Unit>) onTextLayout4, overflow4, softWrap2, maxLines3, 1, (ColorProducer) null, $composer2, ($dirty & 14) | 12582912 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            style3 = style4;
            onTextLayout3 = onTextLayout4;
            overflow3 = overflow4;
            maxLines2 = maxLines3;
            softWrap3 = softWrap2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$4
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

                public final void invoke(Composer composer, int i8) {
                    BasicTextKt.m964BasicTextBpD7jsM(text, modifier5, style3, onTextLayout3, overflow3, softWrap3, maxLines2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    public static final /* synthetic */ void m962BasicText4YKlhWE(final AnnotatedString text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, Map inlineContent, Composer $composer, final int $changed, final int i) {
        AnnotatedString annotatedString;
        Modifier modifier2;
        TextStyle style2;
        Function1 onTextLayout2;
        int overflow2;
        boolean softWrap2;
        int i2;
        Modifier modifier3;
        final TextStyle style3;
        final Function1 onTextLayout3;
        final int overflow3;
        final boolean softWrap3;
        final int maxLines2;
        final Map inlineContent2;
        Composer $composer2 = $composer.startRestartGroup(-648605928);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(7,2,6,3,4:c#ui.text.style.TextOverflow,5,1)303@13046L273:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            annotatedString = text;
        } else if (($changed & 6) == 0) {
            annotatedString = text;
            $dirty |= $composer2.changed(annotatedString) ? 4 : 2;
        } else {
            annotatedString = text;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            style2 = style;
        } else if (($changed & 384) == 0) {
            style2 = style;
            $dirty |= $composer2.changed(style2) ? 256 : 128;
        } else {
            style2 = style;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            overflow2 = overflow;
        } else if (($changed & 24576) == 0) {
            overflow2 = overflow;
            $dirty |= $composer2.changed(overflow2) ? 16384 : 8192;
        } else {
            overflow2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            softWrap2 = softWrap;
        } else if ((196608 & $changed) == 0) {
            softWrap2 = softWrap;
            $dirty |= $composer2.changed(softWrap2) ? 131072 : 65536;
        } else {
            softWrap2 = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty |= $composer2.changedInstance(inlineContent) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            maxLines2 = maxLines;
            modifier3 = modifier2;
            style3 = style2;
            onTextLayout3 = onTextLayout2;
            overflow3 = overflow2;
            softWrap3 = softWrap2;
            inlineContent2 = inlineContent;
        } else {
            Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
            if (i4 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i5 != 0) {
                onTextLayout2 = null;
            }
            if (i6 != 0) {
                overflow2 = TextOverflow.INSTANCE.m6542getClipgIe3tQ8();
            }
            if (i7 != 0) {
                softWrap2 = true;
            }
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            Map inlineContent3 = i2 != 0 ? MapsKt.emptyMap() : inlineContent;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-648605928, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:302)");
            }
            modifier3 = modifier4;
            int maxLines4 = maxLines3;
            Function1 onTextLayout4 = onTextLayout2;
            boolean softWrap4 = softWrap2;
            int overflow4 = overflow2;
            TextStyle style4 = style2;
            m965BasicTextRWo7tUw(annotatedString, modifier3, style4, onTextLayout4, overflow4, softWrap4, maxLines4, 1, inlineContent3, null, $composer2, ($dirty & 14) | 12582912 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (($dirty << 3) & 234881024), 512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            style3 = style4;
            onTextLayout3 = onTextLayout4;
            overflow3 = overflow4;
            softWrap3 = softWrap4;
            maxLines2 = maxLines4;
            inlineContent2 = inlineContent3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$5
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

                public final void invoke(Composer composer, int i10) {
                    BasicTextKt.m962BasicText4YKlhWE(AnnotatedString.this, modifier5, style3, onTextLayout3, overflow3, softWrap3, maxLines2, inlineContent2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    public static final /* synthetic */ void m963BasicText4YKlhWE(final String text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, int minLines, Composer $composer, final int $changed, final int i) {
        String str;
        Modifier modifier2;
        TextStyle style2;
        Function1 onTextLayout2;
        int overflow2;
        boolean softWrap2;
        int i2;
        Modifier.Companion modifier3;
        int maxLines2;
        int minLines2;
        Modifier modifier4;
        final TextStyle style3;
        final Function1 onTextLayout3;
        final int overflow3;
        final boolean softWrap3;
        final int maxLines3;
        final int minLines3;
        Composer $composer2 = $composer.startRestartGroup(1542716361);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(7,2,6,3,4:c#ui.text.style.TextOverflow,5)327@13717L86:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            str = text;
        } else if (($changed & 6) == 0) {
            str = text;
            $dirty |= $composer2.changed(str) ? 4 : 2;
        } else {
            str = text;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            style2 = style;
        } else if (($changed & 384) == 0) {
            style2 = style;
            $dirty |= $composer2.changed(style2) ? 256 : 128;
        } else {
            style2 = style;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            overflow2 = overflow;
        } else if (($changed & 24576) == 0) {
            overflow2 = overflow;
            $dirty |= $composer2.changed(overflow2) ? 16384 : 8192;
        } else {
            overflow2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            softWrap2 = softWrap;
        } else if ((196608 & $changed) == 0) {
            softWrap2 = softWrap;
            $dirty |= $composer2.changed(softWrap2) ? 131072 : 65536;
        } else {
            softWrap2 = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty |= $composer2.changed(minLines) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            maxLines3 = maxLines;
            modifier4 = modifier2;
            style3 = style2;
            onTextLayout3 = onTextLayout2;
            overflow3 = overflow2;
            softWrap3 = softWrap2;
            minLines3 = minLines;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i5 != 0) {
                onTextLayout2 = null;
            }
            if (i6 != 0) {
                overflow2 = TextOverflow.INSTANCE.m6542getClipgIe3tQ8();
            }
            if (i7 != 0) {
                softWrap2 = true;
            }
            if (i8 == 0) {
                maxLines2 = maxLines;
            } else {
                maxLines2 = Integer.MAX_VALUE;
            }
            if (i2 == 0) {
                minLines2 = minLines;
            } else {
                minLines2 = 1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1542716361, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:327)");
            }
            modifier4 = modifier3;
            int maxLines4 = maxLines2;
            Function1 onTextLayout4 = onTextLayout2;
            boolean softWrap4 = softWrap2;
            int overflow4 = overflow2;
            TextStyle style4 = style2;
            m967BasicTextVhcvRP8(str, modifier4, style4, (Function1<? super TextLayoutResult, Unit>) onTextLayout4, overflow4, softWrap4, maxLines4, minLines2, (ColorProducer) null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            style3 = style4;
            onTextLayout3 = onTextLayout4;
            overflow3 = overflow4;
            softWrap3 = softWrap4;
            maxLines3 = maxLines4;
            minLines3 = minLines2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$6
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

                public final void invoke(Composer composer, int i10) {
                    BasicTextKt.m963BasicText4YKlhWE(text, modifier5, style3, onTextLayout3, overflow3, softWrap3, maxLines3, minLines3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    public static final /* synthetic */ void m966BasicTextVhcvRP8(final AnnotatedString text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, int minLines, Map inlineContent, Composer $composer, final int $changed, final int i) {
        AnnotatedString annotatedString;
        Modifier modifier2;
        TextStyle style2;
        Function1 onTextLayout2;
        int overflow2;
        boolean softWrap2;
        int i2;
        int i3;
        int minLines2;
        final TextStyle style3;
        final int overflow3;
        final boolean softWrap3;
        final int maxLines2;
        final Map inlineContent2;
        Composer $composer2 = $composer.startRestartGroup(851408699);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(8,3,7,4,5:c#ui.text.style.TextOverflow,6,1,2)341@14269L240:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            annotatedString = text;
        } else if (($changed & 6) == 0) {
            annotatedString = text;
            $dirty |= $composer2.changed(annotatedString) ? 4 : 2;
        } else {
            annotatedString = text;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            style2 = style;
        } else if (($changed & 384) == 0) {
            style2 = style;
            $dirty |= $composer2.changed(style2) ? 256 : 128;
        } else {
            style2 = style;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            overflow2 = overflow;
        } else if (($changed & 24576) == 0) {
            overflow2 = overflow;
            $dirty |= $composer2.changed(overflow2) ? 16384 : 8192;
        } else {
            overflow2 = overflow;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            softWrap2 = softWrap;
        } else if ((196608 & $changed) == 0) {
            softWrap2 = softWrap;
            $dirty |= $composer2.changed(softWrap2) ? 131072 : 65536;
        } else {
            softWrap2 = softWrap;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
            i2 = i10;
        } else if (($changed & 12582912) == 0) {
            i2 = i10;
            $dirty |= $composer2.changed(minLines) ? 8388608 : 4194304;
        } else {
            i2 = i10;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
            i3 = i11;
        } else if (($changed & 100663296) == 0) {
            i3 = i11;
            $dirty |= $composer2.changedInstance(inlineContent) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i11;
        }
        if (($dirty & 38347923) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            maxLines2 = maxLines;
            minLines2 = minLines;
            inlineContent2 = inlineContent;
            style3 = style2;
            overflow3 = overflow2;
            softWrap3 = softWrap2;
        } else {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i5 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i6 != 0) {
                onTextLayout2 = null;
            }
            if (i7 != 0) {
                overflow2 = TextOverflow.INSTANCE.m6542getClipgIe3tQ8();
            }
            if (i8 != 0) {
                softWrap2 = true;
            }
            int maxLines3 = i9 != 0 ? Integer.MAX_VALUE : maxLines;
            int minLines3 = i2 != 0 ? 1 : minLines;
            Map inlineContent3 = i3 != 0 ? MapsKt.emptyMap() : inlineContent;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(851408699, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:341)");
            }
            int maxLines4 = maxLines3;
            minLines2 = minLines3;
            TextStyle style4 = style2;
            int overflow4 = overflow2;
            boolean softWrap4 = softWrap2;
            Modifier modifier3 = modifier2;
            Function1 onTextLayout3 = onTextLayout2;
            m965BasicTextRWo7tUw(annotatedString, modifier3, style4, onTextLayout3, overflow4, softWrap4, maxLines4, minLines2, inlineContent3, null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty), 512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            style3 = style4;
            onTextLayout2 = onTextLayout3;
            overflow3 = overflow4;
            softWrap3 = softWrap4;
            maxLines2 = maxLines4;
            inlineContent2 = inlineContent3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function1 onTextLayout4 = onTextLayout2;
            final int minLines4 = minLines2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$7
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

                public final void invoke(Composer composer, int i12) {
                    BasicTextKt.m966BasicTextVhcvRP8(AnnotatedString.this, modifier4, style3, onTextLayout4, overflow3, softWrap3, maxLines2, minLines4, inlineContent2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Saver<Long, Long> selectionIdSaver(final SelectionRegistrar selectionRegistrar) {
        return SaverKt.Saver(new Function2<SaverScope, Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt$selectionIdSaver$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Long invoke(SaverScope saverScope, Long l) {
                return invoke(saverScope, l.longValue());
            }

            public final Long invoke(SaverScope $this$Saver, long it) {
                if (SelectionRegistrarKt.hasSelection(SelectionRegistrar.this, it)) {
                    return Long.valueOf(it);
                }
                return null;
            }
        }, new Function1<Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt$selectionIdSaver$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Long invoke(Long l) {
                return invoke(l.longValue());
            }

            public final Long invoke(long it) {
                return Long.valueOf(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Pair<Placeable, Function0<IntOffset>>> measureWithTextRangeMeasureConstraints(List<? extends Measurable> list, Function0<Boolean> function0) {
        if (function0.invoke().booleanValue()) {
            TextRangeLayoutMeasureScope textRangeLayoutMeasureScope = new TextRangeLayoutMeasureScope();
            List $this$fastMapIndexedNotNull$iv = list;
            int $i$f$fastMapIndexedNotNull = 0;
            ArrayList target$iv = new ArrayList($this$fastMapIndexedNotNull$iv.size());
            List $this$fastForEachIndexed$iv$iv = $this$fastMapIndexedNotNull$iv;
            int index$iv$iv = 0;
            int size = $this$fastForEachIndexed$iv$iv.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = $this$fastForEachIndexed$iv$iv.get(index$iv$iv);
                Measurable measurable = (Measurable) item$iv$iv;
                Object parentData = measurable.getParentData();
                Intrinsics.checkNotNull(parentData, "null cannot be cast to non-null type androidx.compose.foundation.text.TextRangeLayoutModifier");
                TextRangeScopeMeasurePolicy rangeMeasurePolicy = ((TextRangeLayoutModifier) parentData).getMeasurePolicy();
                TextRangeLayoutMeasureResult rangeMeasureResult = rangeMeasurePolicy.measure(textRangeLayoutMeasureScope);
                TextRangeLayoutMeasureScope textRangeLayoutMeasureScope2 = textRangeLayoutMeasureScope;
                Constraints.Companion companion = Constraints.INSTANCE;
                List $this$fastMapIndexedNotNull$iv2 = $this$fastMapIndexedNotNull$iv;
                int width = rangeMeasureResult.getWidth();
                int $i$f$fastMapIndexedNotNull2 = $i$f$fastMapIndexedNotNull;
                int $i$f$fastMapIndexedNotNull3 = rangeMeasureResult.getWidth();
                ArrayList target$iv2 = target$iv;
                Placeable placeable = measurable.mo5491measureBRTryo0(companion.m6588fitPrioritizingWidthZbe2FdA(width, $i$f$fastMapIndexedNotNull3, rangeMeasureResult.getHeight(), rangeMeasureResult.getHeight()));
                target$iv2.add(new Pair(placeable, rangeMeasureResult.getPlace()));
                index$iv$iv++;
                textRangeLayoutMeasureScope = textRangeLayoutMeasureScope2;
                $this$fastMapIndexedNotNull$iv = $this$fastMapIndexedNotNull$iv2;
                $i$f$fastMapIndexedNotNull = $i$f$fastMapIndexedNotNull2;
                target$iv = target$iv2;
                $this$fastForEachIndexed$iv$iv = $this$fastForEachIndexed$iv$iv;
            }
            return target$iv;
        }
        return null;
    }

    /* renamed from: textModifier-cFh6CEA, reason: not valid java name */
    private static final Modifier m970textModifiercFh6CEA(Modifier $this$textModifier_u2dcFh6CEA, AnnotatedString text, TextStyle style, Function1<? super TextLayoutResult, Unit> function1, int overflow, boolean softWrap, int maxLines, int minLines, FontFamily.Resolver fontFamilyResolver, List<AnnotatedString.Range<Placeholder>> list, Function1<? super List<Rect>, Unit> function12, SelectionController selectionController, ColorProducer color, Function1<? super TextAnnotatedStringNode.TextSubstitutionValue, Unit> function13) {
        if (selectionController == null) {
            TextAnnotatedStringElement staticTextModifier = new TextAnnotatedStringElement(text, style, fontFamilyResolver, function1, overflow, softWrap, maxLines, minLines, list, function12, null, color, function13, null);
            return $this$textModifier_u2dcFh6CEA.then(Modifier.INSTANCE).then(staticTextModifier);
        }
        SelectableTextAnnotatedStringElement selectableTextModifier = new SelectableTextAnnotatedStringElement(text, style, fontFamilyResolver, function1, overflow, softWrap, maxLines, minLines, list, function12, selectionController, color, null);
        return $this$textModifier_u2dcFh6CEA.then(selectionController.getModifier()).then(selectableTextModifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0616  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04cf  */
    /* renamed from: LayoutWithLinksAndInlineContent-vOo2fZY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m968LayoutWithLinksAndInlineContentvOo2fZY(final androidx.compose.ui.Modifier r45, final androidx.compose.ui.text.AnnotatedString r46, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r47, final boolean r48, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r49, final androidx.compose.ui.text.TextStyle r50, final int r51, final boolean r52, final int r53, final int r54, final androidx.compose.ui.text.font.FontFamily.Resolver r55, final androidx.compose.foundation.text.modifiers.SelectionController r56, final androidx.compose.ui.graphics.ColorProducer r57, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.TextSubstitutionValue, kotlin.Unit> r58, androidx.compose.runtime.Composer r59, final int r60, final int r61, final int r62) {
        /*
            Method dump skipped, instructions count: 1709
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m968LayoutWithLinksAndInlineContentvOo2fZY(androidx.compose.ui.Modifier, androidx.compose.ui.text.AnnotatedString, kotlin.jvm.functions.Function1, boolean, java.util.Map, androidx.compose.ui.text.TextStyle, int, boolean, int, int, androidx.compose.ui.text.font.FontFamily$Resolver, androidx.compose.foundation.text.modifiers.SelectionController, androidx.compose.ui.graphics.ColorProducer, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
