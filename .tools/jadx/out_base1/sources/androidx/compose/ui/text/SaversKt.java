package androidx.compose.ui.text;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Savers.kt */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001ay\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0011\"\u0004\b\u0000\u0010R\"\b\b\u0001\u0010S*\u00020\u00032.\u0010T\u001a*\u0012\u0004\u0012\u00020V\u0012\u0013\u0012\u0011HR¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0006\u0012\u0004\u0018\u0001HS0U¢\u0006\u0002\bZ2#\u0010[\u001a\u001f\u0012\u0013\u0012\u0011HS¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0006\u0012\u0004\u0018\u0001HR0\\H\u0002\u001aL\u0010[\u001a\u0004\u0018\u0001H]\"\u0014\b\u0000\u0010^*\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0001\"\u0004\b\u0001\u0010R\"\u0004\b\u0002\u0010S\"\u0006\b\u0003\u0010]\u0018\u00012\b\u0010Y\u001a\u0004\u0018\u0001HS2\u0006\u0010_\u001a\u0002H^H\u0080\b¢\u0006\u0002\u0010`\u001a\"\u0010[\u001a\u0004\u0018\u0001H]\"\u0006\b\u0000\u0010]\u0018\u00012\b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u0080\b¢\u0006\u0002\u0010a\u001aI\u0010T\u001a\u00020\u0003\"\u0014\b\u0000\u0010^*\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0001\"\u0004\b\u0001\u0010R\"\u0004\b\u0002\u0010S2\b\u0010Y\u001a\u0004\u0018\u0001HR2\u0006\u0010_\u001a\u0002H^2\u0006\u0010b\u001a\u00020VH\u0000¢\u0006\u0002\u0010c\u001a\u001f\u0010T\u001a\u0004\u0018\u0001H^\"\u0004\b\u0000\u0010^2\b\u0010Y\u001a\u0004\u0018\u0001H^H\u0000¢\u0006\u0002\u0010a\" \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"(\u0010\u0006\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\b0\u0007\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"(\u0010\t\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\b\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000b\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0005\"\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0005\"\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0005\"\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\" \u00102\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b4\u0010\u000b\"\u001a\u00105\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0001*\u0002088@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001*\u00020;8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010<\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030\u0001*\u00020=8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010>\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00030\u0001*\u00020?8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010@\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00030\u0001*\u00020A8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010B\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00030\u0001*\u00020C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010D\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u0001*\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010F\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001*\u00020G8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010H\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00030\u0001*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010J\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u0001*\u00020K8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010L\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00030\u0001*\u00020M8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010N\"$\u00107\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00030\u0001*\u00020O8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010P¨\u0006d"}, d2 = {"AnnotatedStringSaver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/AnnotatedString;", "", "getAnnotatedStringSaver", "()Landroidx/compose/runtime/saveable/Saver;", "AnnotationRangeListSaver", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "AnnotationRangeSaver", "getAnnotationRangeSaver$annotations", "()V", "BaselineShiftSaver", "Landroidx/compose/ui/text/style/BaselineShift;", "ClickableSaver", "Landroidx/compose/ui/text/LinkAnnotation$Clickable;", "ColorSaver", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "Landroidx/compose/ui/graphics/Color;", "FontWeightSaver", "Landroidx/compose/ui/text/font/FontWeight;", "LinkSaver", "Landroidx/compose/ui/text/LinkAnnotation$Url;", "LocaleListSaver", "Landroidx/compose/ui/text/intl/LocaleList;", "LocaleSaver", "Landroidx/compose/ui/text/intl/Locale;", "OffsetSaver", "Landroidx/compose/ui/geometry/Offset;", "ParagraphStyleSaver", "Landroidx/compose/ui/text/ParagraphStyle;", "getParagraphStyleSaver", "ShadowSaver", "Landroidx/compose/ui/graphics/Shadow;", "SpanStyleSaver", "Landroidx/compose/ui/text/SpanStyle;", "getSpanStyleSaver", "TextDecorationSaver", "Landroidx/compose/ui/text/style/TextDecoration;", "TextGeometricTransformSaver", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "TextIndentSaver", "Landroidx/compose/ui/text/style/TextIndent;", "TextLinkStylesSaver", "Landroidx/compose/ui/text/TextLinkStyles;", "getTextLinkStylesSaver", "TextRangeSaver", "Landroidx/compose/ui/text/TextRange;", "TextUnitSaver", "Landroidx/compose/ui/unit/TextUnit;", "UrlAnnotationSaver", "Landroidx/compose/ui/text/UrlAnnotation;", "getUrlAnnotationSaver$annotations", "VerbatimTtsAnnotationSaver", "Landroidx/compose/ui/text/VerbatimTtsAnnotation;", "Saver", "Landroidx/compose/ui/geometry/Offset$Companion;", "getSaver", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Color$Companion;", "(Landroidx/compose/ui/graphics/Color$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Shadow$Companion;", "(Landroidx/compose/ui/graphics/Shadow$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/TextRange$Companion;", "(Landroidx/compose/ui/text/TextRange$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/font/FontWeight$Companion;", "(Landroidx/compose/ui/text/font/FontWeight$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/Locale$Companion;", "(Landroidx/compose/ui/text/intl/Locale$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/LocaleList$Companion;", "(Landroidx/compose/ui/text/intl/LocaleList$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/BaselineShift$Companion;", "(Landroidx/compose/ui/text/style/BaselineShift$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextDecoration$Companion;", "(Landroidx/compose/ui/text/style/TextDecoration$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;", "(Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextIndent$Companion;", "(Landroidx/compose/ui/text/style/TextIndent$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/unit/TextUnit$Companion;", "(Landroidx/compose/ui/unit/TextUnit$Companion;)Landroidx/compose/runtime/saveable/Saver;", "NonNullValueClassSaver", "Original", "Saveable", "save", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "Lkotlin/ExtensionFunctionType;", "restore", "Lkotlin/Function1;", "Result", "T", "saver", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;)Ljava/lang/Object;", "(Ljava/lang/Object;)Ljava/lang/Object;", "scope", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Landroidx/compose/runtime/saveable/SaverScope;)Ljava/lang/Object;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SaversKt {
    private static final Saver<AnnotatedString, Object> AnnotatedStringSaver = SaverKt.Saver(new Function2<SaverScope, AnnotatedString, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, AnnotatedString it) {
            Saver saver;
            Saver saver2;
            Saver saver3;
            Object save = SaversKt.save(it.getText());
            List<AnnotatedString.Range<SpanStyle>> spanStyles = it.getSpanStyles();
            saver = SaversKt.AnnotationRangeListSaver;
            Object save2 = SaversKt.save(spanStyles, saver, $this$Saver);
            List<AnnotatedString.Range<ParagraphStyle>> paragraphStyles = it.getParagraphStyles();
            saver2 = SaversKt.AnnotationRangeListSaver;
            Object save3 = SaversKt.save(paragraphStyles, saver2, $this$Saver);
            List<AnnotatedString.Range<? extends Object>> annotations$ui_text_release = it.getAnnotations$ui_text_release();
            saver3 = SaversKt.AnnotationRangeListSaver;
            return CollectionsKt.arrayListOf(save, save2, save3, SaversKt.save(annotations$ui_text_release, saver3, $this$Saver));
        }
    }, new Function1<Object, AnnotatedString>() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString invoke(Object it) {
            Saver saver$iv;
            List spanStylesOrNull;
            Saver saver$iv2;
            List paragraphStylesOrNull;
            List list;
            List list2;
            Saver saver$iv3;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list3 = (List) it;
            Object value$iv = list3.get(1);
            saver$iv = SaversKt.AnnotationRangeListSaver;
            List list4 = null;
            if (!Intrinsics.areEqual(value$iv, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) {
                spanStylesOrNull = value$iv != null ? (List) saver$iv.restore(value$iv) : null;
            } else {
                spanStylesOrNull = null;
            }
            Object value$iv2 = list3.get(2);
            saver$iv2 = SaversKt.AnnotationRangeListSaver;
            if (!Intrinsics.areEqual(value$iv2, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) {
                paragraphStylesOrNull = value$iv2 != null ? (List) saver$iv2.restore(value$iv2) : null;
            } else {
                paragraphStylesOrNull = null;
            }
            Object value$iv3 = list3.get(0);
            String str = value$iv3 != null ? (String) value$iv3 : null;
            Intrinsics.checkNotNull(str);
            if (spanStylesOrNull != null) {
                List list5 = spanStylesOrNull;
                if (list5.isEmpty()) {
                    list5 = null;
                }
                list = list5;
            } else {
                list = null;
            }
            if (paragraphStylesOrNull != null) {
                List list6 = paragraphStylesOrNull;
                if (list6.isEmpty()) {
                    list6 = null;
                }
                list2 = list6;
            } else {
                list2 = null;
            }
            Object value$iv4 = list3.get(3);
            saver$iv3 = SaversKt.AnnotationRangeListSaver;
            if ((!Intrinsics.areEqual(value$iv4, (Object) false) || (saver$iv3 instanceof NonNullValueClassSaver)) && value$iv4 != null) {
                list4 = (List) saver$iv3.restore(value$iv4);
            }
            return new AnnotatedString(str, list, list2, list4);
        }
    });
    private static final Saver<List<AnnotatedString.Range<? extends Object>>, Object> AnnotationRangeListSaver = SaverKt.Saver(new Function2<SaverScope, List<? extends AnnotatedString.Range<? extends Object>>, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, List<? extends AnnotatedString.Range<? extends Object>> list) {
            Saver saver;
            List target$iv = new ArrayList(list.size());
            int size = list.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                AnnotatedString.Range item$iv$iv = list.get(index$iv$iv);
                AnnotatedString.Range range = item$iv$iv;
                saver = SaversKt.AnnotationRangeSaver;
                target$iv.add(SaversKt.save(range, saver, $this$Saver));
            }
            List $this$fastMap$iv = target$iv;
            return $this$fastMap$iv;
        }
    }, new Function1<Object, List<? extends AnnotatedString.Range<? extends Object>>>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final List<? extends AnnotatedString.Range<? extends Object>> invoke(Object it) {
            Saver saver$iv;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            ArrayList target$iv = new ArrayList(list.size());
            int size = list.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = list.get(index$iv$iv);
                ArrayList arrayList = target$iv;
                saver$iv = SaversKt.AnnotationRangeSaver;
                AnnotatedString.Range range = null;
                if ((!Intrinsics.areEqual(item$iv$iv, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) && item$iv$iv != null) {
                    Object it$iv = saver$iv.restore(item$iv$iv);
                    range = (AnnotatedString.Range) it$iv;
                }
                Intrinsics.checkNotNull(range);
                AnnotatedString.Range range2 = range;
                arrayList.add(range2);
            }
            return target$iv;
        }
    });
    private static final Saver<AnnotatedString.Range<? extends Object>, Object> AnnotationRangeSaver = SaverKt.Saver(new Function2<SaverScope, AnnotatedString.Range<? extends Object>, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$1

        /* compiled from: Savers.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError e7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, AnnotatedString.Range<? extends Object> range) {
            Object item;
            Saver saver;
            Saver saver2;
            Saver saver3;
            Saver saver4;
            Object item2 = range.getItem();
            AnnotationType marker = item2 instanceof ParagraphStyle ? AnnotationType.Paragraph : item2 instanceof SpanStyle ? AnnotationType.Span : item2 instanceof VerbatimTtsAnnotation ? AnnotationType.VerbatimTts : item2 instanceof UrlAnnotation ? AnnotationType.Url : item2 instanceof LinkAnnotation.Url ? AnnotationType.Link : item2 instanceof LinkAnnotation.Clickable ? AnnotationType.Clickable : AnnotationType.String;
            switch (WhenMappings.$EnumSwitchMapping$0[marker.ordinal()]) {
                case 1:
                    Object item3 = range.getItem();
                    Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                    item = SaversKt.save((ParagraphStyle) item3, SaversKt.getParagraphStyleSaver(), $this$Saver);
                    break;
                case 2:
                    Object item4 = range.getItem();
                    Intrinsics.checkNotNull(item4, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                    item = SaversKt.save((SpanStyle) item4, SaversKt.getSpanStyleSaver(), $this$Saver);
                    break;
                case 3:
                    Object item5 = range.getItem();
                    Intrinsics.checkNotNull(item5, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                    saver = SaversKt.VerbatimTtsAnnotationSaver;
                    item = SaversKt.save((VerbatimTtsAnnotation) item5, saver, $this$Saver);
                    break;
                case 4:
                    Object item6 = range.getItem();
                    Intrinsics.checkNotNull(item6, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                    saver2 = SaversKt.UrlAnnotationSaver;
                    item = SaversKt.save((UrlAnnotation) item6, saver2, $this$Saver);
                    break;
                case 5:
                    Object item7 = range.getItem();
                    Intrinsics.checkNotNull(item7, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url");
                    saver3 = SaversKt.LinkSaver;
                    item = SaversKt.save((LinkAnnotation.Url) item7, saver3, $this$Saver);
                    break;
                case 6:
                    Object item8 = range.getItem();
                    Intrinsics.checkNotNull(item8, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable");
                    saver4 = SaversKt.ClickableSaver;
                    item = SaversKt.save((LinkAnnotation.Clickable) item8, saver4, $this$Saver);
                    break;
                case 7:
                    item = SaversKt.save(range.getItem());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return CollectionsKt.arrayListOf(SaversKt.save(marker), item, SaversKt.save(Integer.valueOf(range.getStart())), SaversKt.save(Integer.valueOf(range.getEnd())), SaversKt.save(range.getTag()));
        }
    }, new Function1<Object, AnnotatedString.Range<? extends Object>>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$2

        /* compiled from: Savers.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError e7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString.Range<? extends Object> invoke(Object it) {
            Object it$iv;
            Object it$iv2;
            String tag;
            Saver saver$iv;
            Saver saver$iv2;
            Saver saver$iv3;
            Saver saver$iv4;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Object it$iv3 = null;
            AnnotationType marker = value$iv != null ? (AnnotationType) value$iv : null;
            Intrinsics.checkNotNull(marker);
            Object value$iv2 = list.get(2);
            if (value$iv2 == null) {
                it$iv = null;
            } else {
                it$iv = (Integer) value$iv2;
            }
            Intrinsics.checkNotNull(it$iv);
            int start = ((Number) it$iv).intValue();
            Object value$iv3 = list.get(3);
            if (value$iv3 == null) {
                it$iv2 = null;
            } else {
                it$iv2 = (Integer) value$iv3;
            }
            Intrinsics.checkNotNull(it$iv2);
            int end = ((Number) it$iv2).intValue();
            Object value$iv4 = list.get(4);
            if (value$iv4 == null) {
                tag = null;
            } else {
                tag = (String) value$iv4;
            }
            Intrinsics.checkNotNull(tag);
            switch (WhenMappings.$EnumSwitchMapping$0[marker.ordinal()]) {
                case 1:
                    Object value$iv5 = list.get(1);
                    Saver saver$iv5 = SaversKt.getParagraphStyleSaver();
                    if ((!Intrinsics.areEqual(value$iv5, (Object) false) || (saver$iv5 instanceof NonNullValueClassSaver)) && value$iv5 != null) {
                        it$iv3 = (ParagraphStyle) saver$iv5.restore(value$iv5);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 2:
                    Object value$iv6 = list.get(1);
                    Saver saver$iv6 = SaversKt.getSpanStyleSaver();
                    if ((!Intrinsics.areEqual(value$iv6, (Object) false) || (saver$iv6 instanceof NonNullValueClassSaver)) && value$iv6 != null) {
                        it$iv3 = (SpanStyle) saver$iv6.restore(value$iv6);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 3:
                    Object value$iv7 = list.get(1);
                    saver$iv = SaversKt.VerbatimTtsAnnotationSaver;
                    if ((!Intrinsics.areEqual(value$iv7, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) && value$iv7 != null) {
                        it$iv3 = (VerbatimTtsAnnotation) saver$iv.restore(value$iv7);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 4:
                    Object value$iv8 = list.get(1);
                    saver$iv2 = SaversKt.UrlAnnotationSaver;
                    if ((!Intrinsics.areEqual(value$iv8, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) && value$iv8 != null) {
                        it$iv3 = (UrlAnnotation) saver$iv2.restore(value$iv8);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 5:
                    Object value$iv9 = list.get(1);
                    saver$iv3 = SaversKt.LinkSaver;
                    if ((!Intrinsics.areEqual(value$iv9, (Object) false) || (saver$iv3 instanceof NonNullValueClassSaver)) && value$iv9 != null) {
                        it$iv3 = (LinkAnnotation.Url) saver$iv3.restore(value$iv9);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 6:
                    Object value$iv10 = list.get(1);
                    saver$iv4 = SaversKt.ClickableSaver;
                    if ((!Intrinsics.areEqual(value$iv10, (Object) false) || (saver$iv4 instanceof NonNullValueClassSaver)) && value$iv10 != null) {
                        it$iv3 = (LinkAnnotation.Clickable) saver$iv4.restore(value$iv10);
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                case 7:
                    Object value$iv11 = list.get(1);
                    if (value$iv11 != null) {
                        it$iv3 = (String) value$iv11;
                    }
                    Intrinsics.checkNotNull(it$iv3);
                    return new AnnotatedString.Range<>(it$iv3, start, end, tag);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    });
    private static final Saver<VerbatimTtsAnnotation, Object> VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2<SaverScope, VerbatimTtsAnnotation, Object>() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, VerbatimTtsAnnotation it) {
            return SaversKt.save(it.getVerbatim());
        }
    }, new Function1<Object, VerbatimTtsAnnotation>() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final VerbatimTtsAnnotation invoke(Object it) {
            String str = it != null ? (String) it : null;
            Intrinsics.checkNotNull(str);
            return new VerbatimTtsAnnotation(str);
        }
    });
    private static final Saver<UrlAnnotation, Object> UrlAnnotationSaver = SaverKt.Saver(new Function2<SaverScope, UrlAnnotation, Object>() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, UrlAnnotation it) {
            return SaversKt.save(it.getUrl());
        }
    }, new Function1<Object, UrlAnnotation>() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final UrlAnnotation invoke(Object it) {
            String str = it != null ? (String) it : null;
            Intrinsics.checkNotNull(str);
            return new UrlAnnotation(str);
        }
    });
    private static final Saver<LinkAnnotation.Url, Object> LinkSaver = SaverKt.Saver(new Function2<SaverScope, LinkAnnotation.Url, Object>() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, LinkAnnotation.Url it) {
            return CollectionsKt.arrayListOf(SaversKt.save(it.getUrl()), SaversKt.save(it.getStyles(), SaversKt.getTextLinkStylesSaver(), $this$Saver));
        }
    }, new Function1<Object, LinkAnnotation.Url>() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Url invoke(Object it) {
            TextLinkStyles stylesOrNull;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) it;
            Object value$iv = list.get(0);
            String str = value$iv != null ? (String) value$iv : null;
            Intrinsics.checkNotNull(str);
            String url = str;
            Object value$iv2 = list.get(1);
            Saver saver$iv = SaversKt.getTextLinkStylesSaver();
            if (Intrinsics.areEqual(value$iv2, (Object) false) && !(saver$iv instanceof NonNullValueClassSaver)) {
                stylesOrNull = null;
            } else {
                stylesOrNull = value$iv2 != null ? saver$iv.restore(value$iv2) : null;
            }
            return new LinkAnnotation.Url(url, stylesOrNull, null, 4, null);
        }
    });
    private static final Saver<LinkAnnotation.Clickable, Object> ClickableSaver = SaverKt.Saver(new Function2<SaverScope, LinkAnnotation.Clickable, Object>() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, LinkAnnotation.Clickable it) {
            return CollectionsKt.arrayListOf(SaversKt.save(it.getTag()), SaversKt.save(it.getStyles(), SaversKt.getTextLinkStylesSaver(), $this$Saver));
        }
    }, new Function1<Object, LinkAnnotation.Clickable>() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Clickable invoke(Object it) {
            TextLinkStyles stylesOrNull;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) it;
            Object value$iv = list.get(0);
            String tag = value$iv != null ? (String) value$iv : null;
            Intrinsics.checkNotNull(tag);
            Object value$iv2 = list.get(1);
            Saver saver$iv = SaversKt.getTextLinkStylesSaver();
            if (Intrinsics.areEqual(value$iv2, (Object) false) && !(saver$iv instanceof NonNullValueClassSaver)) {
                stylesOrNull = null;
            } else {
                stylesOrNull = value$iv2 != null ? saver$iv.restore(value$iv2) : null;
            }
            return new LinkAnnotation.Clickable(tag, stylesOrNull, null);
        }
    });
    private static final Saver<ParagraphStyle, Object> ParagraphStyleSaver = SaverKt.Saver(new Function2<SaverScope, ParagraphStyle, Object>() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, ParagraphStyle it) {
            return CollectionsKt.arrayListOf(SaversKt.save(TextAlign.m6486boximpl(it.getTextAlign())), SaversKt.save(TextDirection.m6500boximpl(it.getTextDirection())), SaversKt.save(TextUnit.m6809boximpl(it.getLineHeight()), SaversKt.getSaver(TextUnit.INSTANCE), $this$Saver), SaversKt.save(it.getTextIndent(), SaversKt.getSaver(TextIndent.INSTANCE), $this$Saver));
        }
    }, new Function1<Object, ParagraphStyle>() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final ParagraphStyle invoke(Object it) {
            TextDirection textDirection;
            TextUnit restore;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) it;
            Object value$iv = list.get(0);
            TextIndent textIndent = null;
            TextAlign textAlign = value$iv != null ? (TextAlign) value$iv : null;
            Intrinsics.checkNotNull(textAlign);
            int value = textAlign.getValue();
            Object value$iv2 = list.get(1);
            if (value$iv2 == null) {
                textDirection = null;
            } else {
                textDirection = (TextDirection) value$iv2;
            }
            Intrinsics.checkNotNull(textDirection);
            int value2 = textDirection.getValue();
            Object value$iv3 = list.get(2);
            Saver saver$iv = SaversKt.getSaver(TextUnit.INSTANCE);
            if (Intrinsics.areEqual(value$iv3, (Object) false) && !(saver$iv instanceof NonNullValueClassSaver)) {
                restore = null;
            } else {
                restore = value$iv3 != null ? saver$iv.restore(value$iv3) : null;
            }
            Intrinsics.checkNotNull(restore);
            long packedValue = restore.getPackedValue();
            Object value$iv4 = list.get(3);
            Saver saver$iv2 = SaversKt.getSaver(TextIndent.INSTANCE);
            if ((!Intrinsics.areEqual(value$iv4, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) && value$iv4 != null) {
                textIndent = saver$iv2.restore(value$iv4);
            }
            return new ParagraphStyle(value, value2, packedValue, textIndent, (PlatformParagraphStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 496, (DefaultConstructorMarker) null);
        }
    });
    private static final Saver<SpanStyle, Object> SpanStyleSaver = SaverKt.Saver(new Function2<SaverScope, SpanStyle, Object>() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, SpanStyle it) {
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m4153boximpl(it.m6042getColor0d7_KjU()), SaversKt.getSaver(Color.INSTANCE), $this$Saver), SaversKt.save(TextUnit.m6809boximpl(it.getFontSize()), SaversKt.getSaver(TextUnit.INSTANCE), $this$Saver), SaversKt.save(it.getFontWeight(), SaversKt.getSaver(FontWeight.INSTANCE), $this$Saver), SaversKt.save(it.getFontStyle()), SaversKt.save(it.getFontSynthesis()), SaversKt.save(-1), SaversKt.save(it.getFontFeatureSettings()), SaversKt.save(TextUnit.m6809boximpl(it.getLetterSpacing()), SaversKt.getSaver(TextUnit.INSTANCE), $this$Saver), SaversKt.save(it.getBaselineShift(), SaversKt.getSaver(BaselineShift.INSTANCE), $this$Saver), SaversKt.save(it.getTextGeometricTransform(), SaversKt.getSaver(TextGeometricTransform.INSTANCE), $this$Saver), SaversKt.save(it.getLocaleList(), SaversKt.getSaver(LocaleList.INSTANCE), $this$Saver), SaversKt.save(Color.m4153boximpl(it.getBackground()), SaversKt.getSaver(Color.INSTANCE), $this$Saver), SaversKt.save(it.getTextDecoration(), SaversKt.getSaver(TextDecoration.INSTANCE), $this$Saver), SaversKt.save(it.getShadow(), SaversKt.getSaver(Shadow.INSTANCE), $this$Saver));
        }
    }, new Function1<Object, SpanStyle>() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 androidx.compose.ui.text.SpanStyle, still in use, count: 2, list:
              (r2v0 androidx.compose.ui.text.SpanStyle) from 0x010b: MOVE (r17v2 androidx.compose.ui.text.SpanStyle) = (r2v0 androidx.compose.ui.text.SpanStyle)
              (r2v0 androidx.compose.ui.text.SpanStyle) from 0x0103: MOVE (r17v6 androidx.compose.ui.text.SpanStyle) = (r2v0 androidx.compose.ui.text.SpanStyle)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final androidx.compose.ui.text.SpanStyle invoke(java.lang.Object r28) {
            /*
                Method dump skipped, instructions count: 581
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2.invoke(java.lang.Object):androidx.compose.ui.text.SpanStyle");
        }
    });
    private static final Saver<TextLinkStyles, Object> TextLinkStylesSaver = SaverKt.Saver(new Function2<SaverScope, TextLinkStyles, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, TextLinkStyles it) {
            return CollectionsKt.arrayListOf(SaversKt.save(it.getStyle(), SaversKt.getSpanStyleSaver(), $this$Saver), SaversKt.save(it.getFocusedStyle(), SaversKt.getSpanStyleSaver(), $this$Saver), SaversKt.save(it.getHoveredStyle(), SaversKt.getSpanStyleSaver(), $this$Saver), SaversKt.save(it.getPressedStyle(), SaversKt.getSpanStyleSaver(), $this$Saver));
        }
    }, new Function1<Object, TextLinkStyles>() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextLinkStyles invoke(Object it) {
            SpanStyle styleOrNull;
            SpanStyle focusedStyleOrNull;
            SpanStyle hoveredStyleOrNull;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Saver saver$iv = SaversKt.getSpanStyleSaver();
            SpanStyle pressedStyleOrNull = null;
            if (!Intrinsics.areEqual(value$iv, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) {
                styleOrNull = value$iv != null ? saver$iv.restore(value$iv) : null;
            } else {
                styleOrNull = null;
            }
            Object value$iv2 = list.get(1);
            Saver saver$iv2 = SaversKt.getSpanStyleSaver();
            if (!Intrinsics.areEqual(value$iv2, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) {
                focusedStyleOrNull = value$iv2 != null ? saver$iv2.restore(value$iv2) : null;
            } else {
                focusedStyleOrNull = null;
            }
            Object value$iv3 = list.get(2);
            Saver saver$iv3 = SaversKt.getSpanStyleSaver();
            if (!Intrinsics.areEqual(value$iv3, (Object) false) || (saver$iv3 instanceof NonNullValueClassSaver)) {
                hoveredStyleOrNull = value$iv3 != null ? saver$iv3.restore(value$iv3) : null;
            } else {
                hoveredStyleOrNull = null;
            }
            Object value$iv4 = list.get(3);
            Saver saver$iv4 = SaversKt.getSpanStyleSaver();
            if ((!Intrinsics.areEqual(value$iv4, (Object) false) || (saver$iv4 instanceof NonNullValueClassSaver)) && value$iv4 != null) {
                pressedStyleOrNull = saver$iv4.restore(value$iv4);
            }
            return new TextLinkStyles(styleOrNull, focusedStyleOrNull, hoveredStyleOrNull, pressedStyleOrNull);
        }
    });
    private static final Saver<TextDecoration, Object> TextDecorationSaver = SaverKt.Saver(new Function2<SaverScope, TextDecoration, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, TextDecoration it) {
            return Integer.valueOf(it.getMask());
        }
    }, new Function1<Object, TextDecoration>() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextDecoration invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
            return new TextDecoration(((Integer) it).intValue());
        }
    });
    private static final Saver<TextGeometricTransform, Object> TextGeometricTransformSaver = SaverKt.Saver(new Function2<SaverScope, TextGeometricTransform, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, TextGeometricTransform it) {
            return CollectionsKt.arrayListOf(Float.valueOf(it.getScaleX()), Float.valueOf(it.getSkewX()));
        }
    }, new Function1<Object, TextGeometricTransform>() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextGeometricTransform invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
            List list = (List) it;
            return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
        }
    });
    private static final Saver<TextIndent, Object> TextIndentSaver = SaverKt.Saver(new Function2<SaverScope, TextIndent, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, TextIndent it) {
            return CollectionsKt.arrayListOf(SaversKt.save(TextUnit.m6809boximpl(it.getFirstLine()), SaversKt.getSaver(TextUnit.INSTANCE), $this$Saver), SaversKt.save(TextUnit.m6809boximpl(it.getRestLine()), SaversKt.getSaver(TextUnit.INSTANCE), $this$Saver));
        }
    }, new Function1<Object, TextIndent>() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextIndent invoke(Object it) {
            TextUnit restore;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Saver saver$iv = SaversKt.getSaver(TextUnit.INSTANCE);
            TextUnit textUnit = null;
            if (!Intrinsics.areEqual(value$iv, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) {
                restore = value$iv != null ? saver$iv.restore(value$iv) : null;
            } else {
                restore = null;
            }
            Intrinsics.checkNotNull(restore);
            long packedValue = restore.getPackedValue();
            Object value$iv2 = list.get(1);
            Saver saver$iv2 = SaversKt.getSaver(TextUnit.INSTANCE);
            if ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) && value$iv2 != null) {
                textUnit = saver$iv2.restore(value$iv2);
            }
            Intrinsics.checkNotNull(textUnit);
            return new TextIndent(packedValue, textUnit.getPackedValue(), null);
        }
    });
    private static final Saver<FontWeight, Object> FontWeightSaver = SaverKt.Saver(new Function2<SaverScope, FontWeight, Object>() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, FontWeight it) {
            return Integer.valueOf(it.getWeight());
        }
    }, new Function1<Object, FontWeight>() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final FontWeight invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
            return new FontWeight(((Integer) it).intValue());
        }
    });
    private static final Saver<BaselineShift, Object> BaselineShiftSaver = SaverKt.Saver(new Function2<SaverScope, BaselineShift, Object>() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, BaselineShift baselineShift) {
            return m6022invoke8a2Sb4w(saverScope, baselineShift.m6383unboximpl());
        }

        /* renamed from: invoke-8a2Sb4w, reason: not valid java name */
        public final Object m6022invoke8a2Sb4w(SaverScope $this$Saver, float it) {
            return Float.valueOf(it);
        }
    }, new Function1<Object, BaselineShift>() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke-jTk7eUs, reason: not valid java name and merged with bridge method [inline-methods] */
        public final BaselineShift invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Float");
            return BaselineShift.m6377boximpl(BaselineShift.m6378constructorimpl(((Float) it).floatValue()));
        }
    });
    private static final Saver<TextRange, Object> TextRangeSaver = SaverKt.Saver(new Function2<SaverScope, TextRange, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, TextRange textRange) {
            return m6028invokeFDrldGo(saverScope, textRange.getPackedValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke-FDrldGo, reason: not valid java name */
        public final Object m6028invokeFDrldGo(SaverScope $this$Saver, long it) {
            return CollectionsKt.arrayListOf(SaversKt.save(Integer.valueOf(TextRange.m6092getStartimpl(it))), SaversKt.save(Integer.valueOf(TextRange.m6087getEndimpl(it))));
        }
    }, new Function1<Object, TextRange>() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke-VqIyPBM, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextRange invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Object it$iv = value$iv != null ? (Integer) value$iv : null;
            Intrinsics.checkNotNull(it$iv);
            int intValue = ((Number) it$iv).intValue();
            Object value$iv2 = list.get(1);
            Object it$iv2 = value$iv2 != null ? (Integer) value$iv2 : null;
            Intrinsics.checkNotNull(it$iv2);
            return TextRange.m6080boximpl(TextRangeKt.TextRange(intValue, ((Number) it$iv2).intValue()));
        }
    });
    private static final Saver<Shadow, Object> ShadowSaver = SaverKt.Saver(new Function2<SaverScope, Shadow, Object>() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, Shadow it) {
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m4153boximpl(it.getColor()), SaversKt.getSaver(Color.INSTANCE), $this$Saver), SaversKt.save(Offset.m3911boximpl(it.getOffset()), SaversKt.getSaver(Offset.INSTANCE), $this$Saver), SaversKt.save(Float.valueOf(it.getBlurRadius())));
        }
    }, new Function1<Object, Shadow>() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Shadow invoke(Object it) {
            Color restore;
            Offset restore2;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Saver saver$iv = SaversKt.getSaver(Color.INSTANCE);
            if (!Intrinsics.areEqual(value$iv, (Object) false) || (saver$iv instanceof NonNullValueClassSaver)) {
                restore = value$iv != null ? saver$iv.restore(value$iv) : null;
            } else {
                restore = null;
            }
            Intrinsics.checkNotNull(restore);
            long m4173unboximpl = restore.m4173unboximpl();
            Object value$iv2 = list.get(1);
            Saver saver$iv2 = SaversKt.getSaver(Offset.INSTANCE);
            if (!Intrinsics.areEqual(value$iv2, (Object) false) || (saver$iv2 instanceof NonNullValueClassSaver)) {
                restore2 = value$iv2 != null ? saver$iv2.restore(value$iv2) : null;
            } else {
                restore2 = null;
            }
            Intrinsics.checkNotNull(restore2);
            long packedValue = restore2.getPackedValue();
            Object value$iv3 = list.get(2);
            Object it$iv = value$iv3 != null ? (Float) value$iv3 : null;
            Intrinsics.checkNotNull(it$iv);
            return new Shadow(m4173unboximpl, packedValue, ((Number) it$iv).floatValue(), null);
        }
    });
    private static final NonNullValueClassSaver<Color, Object> ColorSaver = NonNullValueClassSaver(new Function2<SaverScope, Color, Object>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, Color color) {
            return m6024invoke4WTKRHQ(saverScope, color.m4173unboximpl());
        }

        /* renamed from: invoke-4WTKRHQ, reason: not valid java name */
        public final Object m6024invoke4WTKRHQ(SaverScope $this$NonNullValueClassSaver, long it) {
            if (it == 16) {
                return false;
            }
            return Integer.valueOf(ColorKt.m4217toArgb8_81llA(it));
        }
    }, new Function1<Object, Color>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke-ijrfgN4, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke(Object it) {
            long Color;
            if (Intrinsics.areEqual(it, (Object) false)) {
                Color = Color.INSTANCE.m4199getUnspecified0d7_KjU();
            } else {
                Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
                Color = ColorKt.Color(((Integer) it).intValue());
            }
            return Color.m4153boximpl(Color);
        }
    });
    private static final NonNullValueClassSaver<TextUnit, Object> TextUnitSaver = NonNullValueClassSaver(new Function2<SaverScope, TextUnit, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, TextUnit textUnit) {
            return m6030invokempE4wyQ(saverScope, textUnit.getPackedValue());
        }

        /* renamed from: invoke-mpE4wyQ, reason: not valid java name */
        public final Object m6030invokempE4wyQ(SaverScope $this$NonNullValueClassSaver, long it) {
            if (TextUnit.m6816equalsimpl0(it, TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE())) {
                return (Serializable) false;
            }
            return CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(TextUnit.m6819getValueimpl(it))), SaversKt.save(TextUnitType.m6844boximpl(TextUnit.m6818getTypeUIouoOA(it))));
        }
    }, new Function1<Object, TextUnit>() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke-XNhUCwk, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextUnit invoke(Object it) {
            if (Intrinsics.areEqual(it, (Object) false)) {
                return TextUnit.m6809boximpl(TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE());
            }
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Object it$iv = value$iv != null ? (Float) value$iv : null;
            Intrinsics.checkNotNull(it$iv);
            float floatValue = ((Number) it$iv).floatValue();
            Object value$iv2 = list.get(1);
            TextUnitType textUnitType = value$iv2 != null ? (TextUnitType) value$iv2 : null;
            Intrinsics.checkNotNull(textUnitType);
            return TextUnit.m6809boximpl(TextUnitKt.m6831TextUnitanM5pPY(floatValue, textUnitType.getType()));
        }
    });
    private static final NonNullValueClassSaver<Offset, Object> OffsetSaver = NonNullValueClassSaver(new Function2<SaverScope, Offset, Object>() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, Offset offset) {
            return m6026invokeUv8p0NA(saverScope, offset.getPackedValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final Object m6026invokeUv8p0NA(SaverScope $this$NonNullValueClassSaver, long it) {
            return Offset.m3919equalsimpl0(it, Offset.INSTANCE.m3937getUnspecifiedF1C5BW0()) ? (Serializable) false : CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(Offset.m3922getXimpl(it))), SaversKt.save(Float.valueOf(Offset.m3923getYimpl(it))));
        }
    }, new Function1<Object, Offset>() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke-x-9fifI, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Offset invoke(Object it) {
            if (Intrinsics.areEqual(it, (Object) false)) {
                return Offset.m3911boximpl(Offset.INSTANCE.m3937getUnspecifiedF1C5BW0());
            }
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) it;
            Object value$iv = list.get(0);
            Object it$iv = value$iv != null ? (Float) value$iv : null;
            Intrinsics.checkNotNull(it$iv);
            float floatValue = ((Number) it$iv).floatValue();
            Object value$iv2 = list.get(1);
            Object it$iv2 = value$iv2 != null ? (Float) value$iv2 : null;
            Intrinsics.checkNotNull(it$iv2);
            return Offset.m3911boximpl(OffsetKt.Offset(floatValue, ((Number) it$iv2).floatValue()));
        }
    });
    private static final Saver<LocaleList, Object> LocaleListSaver = SaverKt.Saver(new Function2<SaverScope, LocaleList, Object>() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, LocaleList it) {
            List $this$fastMap$iv = it.getLocaleList();
            List target$iv = new ArrayList($this$fastMap$iv.size());
            int size = $this$fastMap$iv.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                Locale locale = (Locale) item$iv$iv;
                target$iv.add(SaversKt.save(locale, SaversKt.getSaver(Locale.INSTANCE), $this$Saver));
            }
            return target$iv;
        }
    }, new Function1<Object, LocaleList>() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LocaleList invoke(Object it) {
            Locale locale;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) it;
            ArrayList target$iv = new ArrayList(list.size());
            int size = list.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = list.get(index$iv$iv);
                ArrayList arrayList = target$iv;
                Saver saver$iv = SaversKt.getSaver(Locale.INSTANCE);
                if (Intrinsics.areEqual(item$iv$iv, (Object) false) && !(saver$iv instanceof NonNullValueClassSaver)) {
                    locale = null;
                } else if (item$iv$iv != null) {
                    Object it$iv = saver$iv.restore(item$iv$iv);
                    locale = (Locale) it$iv;
                } else {
                    locale = null;
                }
                Intrinsics.checkNotNull(locale);
                arrayList.add(locale);
            }
            return new LocaleList(target$iv);
        }
    });
    private static final Saver<Locale, Object> LocaleSaver = SaverKt.Saver(new Function2<SaverScope, Locale, Object>() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope $this$Saver, Locale it) {
            return it.toLanguageTag();
        }
    }, new Function1<Object, Locale>() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Locale invoke(Object it) {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.String");
            return new Locale((String) it);
        }
    });

    private static /* synthetic */ void getAnnotationRangeSaver$annotations() {
    }

    private static /* synthetic */ void getUrlAnnotationSaver$annotations() {
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable> Object save(Original original, T t, SaverScope scope) {
        Object save;
        if (original == null || (save = t.save(scope, original)) == null) {
            return false;
        }
        return save;
    }

    public static final /* synthetic */ <T extends Saver<Original, Saveable>, Original, Saveable, Result> Result restore(Saveable saveable, T t) {
        if ((Intrinsics.areEqual((Object) saveable, (Object) false) && !(t instanceof NonNullValueClassSaver)) || saveable == null) {
            return null;
        }
        Result result = (Result) t.restore(saveable);
        Intrinsics.reifiedOperationMarker(1, "Result");
        return result;
    }

    private static final <Original, Saveable> NonNullValueClassSaver<Original, Saveable> NonNullValueClassSaver(final Function2<? super SaverScope, ? super Original, ? extends Saveable> function2, final Function1<? super Saveable, ? extends Original> function1) {
        return new NonNullValueClassSaver<Original, Saveable>() { // from class: androidx.compose.ui.text.SaversKt$NonNullValueClassSaver$1
            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope $this$save, Original original) {
                return function2.invoke($this$save, original);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return function1.invoke(value);
            }
        };
    }

    public static final <T> T save(T t) {
        return t;
    }

    public static final /* synthetic */ <Result> Result restore(Object obj) {
        if (obj == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(1, "Result");
        return (Result) obj;
    }

    public static final Saver<AnnotatedString, Object> getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    public static final Saver<ParagraphStyle, Object> getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final Saver<SpanStyle, Object> getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    public static final Saver<TextLinkStyles, Object> getTextLinkStylesSaver() {
        return TextLinkStylesSaver;
    }

    public static final Saver<TextDecoration, Object> getSaver(TextDecoration.Companion $this$Saver) {
        return TextDecorationSaver;
    }

    public static final Saver<TextGeometricTransform, Object> getSaver(TextGeometricTransform.Companion $this$Saver) {
        return TextGeometricTransformSaver;
    }

    public static final Saver<TextIndent, Object> getSaver(TextIndent.Companion $this$Saver) {
        return TextIndentSaver;
    }

    public static final Saver<FontWeight, Object> getSaver(FontWeight.Companion $this$Saver) {
        return FontWeightSaver;
    }

    public static final Saver<BaselineShift, Object> getSaver(BaselineShift.Companion $this$Saver) {
        return BaselineShiftSaver;
    }

    public static final Saver<TextRange, Object> getSaver(TextRange.Companion $this$Saver) {
        return TextRangeSaver;
    }

    public static final Saver<Shadow, Object> getSaver(Shadow.Companion $this$Saver) {
        return ShadowSaver;
    }

    public static final Saver<Color, Object> getSaver(Color.Companion $this$Saver) {
        return ColorSaver;
    }

    public static final Saver<TextUnit, Object> getSaver(TextUnit.Companion $this$Saver) {
        return TextUnitSaver;
    }

    public static final Saver<Offset, Object> getSaver(Offset.Companion $this$Saver) {
        return OffsetSaver;
    }

    public static final Saver<LocaleList, Object> getSaver(LocaleList.Companion $this$Saver) {
        return LocaleListSaver;
    }

    public static final Saver<Locale, Object> getSaver(Locale.Companion $this$Saver) {
        return LocaleSaver;
    }
}
