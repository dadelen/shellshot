package androidx.compose.ui.text;

import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* compiled from: AndroidParagraph.android.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a\u001a\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001a\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\n\u001a\f\u0010\u001b\u001a\u00020\u001c*\u00020\u001cH\u0002\u001a\u0014\u0010\u001d\u001a\u00020\u0006*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u001a\u0016\u0010 \u001a\u00020\u0006*\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"shouldAttachIndentationFixSpan", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "ellipsis", "toLayoutAlign", "", "align", "Landroidx/compose/ui/text/style/TextAlign;", "toLayoutAlign-aXe7zB0", "(I)I", "toLayoutBreakStrategy", "breakStrategy", "Landroidx/compose/ui/text/style/LineBreak$Strategy;", "toLayoutBreakStrategy-xImikfE", "toLayoutHyphenationFrequency", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "toLayoutHyphenationFrequency--3fSNIE", "toLayoutLineBreakStyle", "lineBreakStrictness", "Landroidx/compose/ui/text/style/LineBreak$Strictness;", "toLayoutLineBreakStyle-hpcqdu8", "toLayoutLineBreakWordStyle", "lineBreakWordStyle", "Landroidx/compose/ui/text/style/LineBreak$WordBreak;", "toLayoutLineBreakWordStyle-wPN0Rpw", "attachIndentationFixSpan", "", "numberOfLinesThatFitMaxHeight", "Landroidx/compose/ui/text/android/TextLayout;", "maxHeight", "toLayoutTextGranularity", "Landroidx/compose/ui/text/TextGranularity;", "toLayoutTextGranularity-duNsdkg", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidParagraph_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final int m5937toLayoutAlignaXe7zB0(int align) {
        if (TextAlign.m6489equalsimpl0(align, TextAlign.INSTANCE.m6496getLefte0LSkKk())) {
            return 3;
        }
        if (TextAlign.m6489equalsimpl0(align, TextAlign.INSTANCE.m6497getRighte0LSkKk())) {
            return 4;
        }
        if (TextAlign.m6489equalsimpl0(align, TextAlign.INSTANCE.m6493getCentere0LSkKk())) {
            return 2;
        }
        return (!TextAlign.m6489equalsimpl0(align, TextAlign.INSTANCE.m6498getStarte0LSkKk()) && TextAlign.m6489equalsimpl0(align, TextAlign.INSTANCE.m6494getEnde0LSkKk())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final int m5939toLayoutHyphenationFrequency3fSNIE(int hyphens) {
        if (Hyphens.m6399equalsimpl0(hyphens, Hyphens.INSTANCE.m6403getAutovmbZdU8())) {
            return 4;
        }
        return Hyphens.m6399equalsimpl0(hyphens, Hyphens.INSTANCE.m6404getNonevmbZdU8()) ? 0 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final int m5938toLayoutBreakStrategyxImikfE(int breakStrategy) {
        if (LineBreak.Strategy.m6430equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m6436getSimplefcGXIks())) {
            return 0;
        }
        if (LineBreak.Strategy.m6430equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m6435getHighQualityfcGXIks())) {
            return 1;
        }
        return LineBreak.Strategy.m6430equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m6434getBalancedfcGXIks()) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final int m5940toLayoutLineBreakStylehpcqdu8(int lineBreakStrictness) {
        if (LineBreak.Strictness.m6441equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m6445getDefaultusljTpc())) {
            return 0;
        }
        if (LineBreak.Strictness.m6441equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m6446getLooseusljTpc())) {
            return 1;
        }
        if (LineBreak.Strictness.m6441equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m6447getNormalusljTpc())) {
            return 2;
        }
        return LineBreak.Strictness.m6441equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m6448getStrictusljTpc()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final int m5941toLayoutLineBreakWordStylewPN0Rpw(int lineBreakWordStyle) {
        return (!LineBreak.WordBreak.m6453equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m6457getDefaultjp8hJ3c()) && LineBreak.WordBreak.m6453equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m6458getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout $this$numberOfLinesThatFitMaxHeight, int maxHeight) {
        int lineCount = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
            if ($this$numberOfLinesThatFitMaxHeight.getLineBottom(lineIndex) > maxHeight) {
                return lineIndex;
            }
        }
        int lineIndex2 = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        return lineIndex2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean ellipsis) {
        return (!ellipsis || TextUnit.m6816equalsimpl0(textStyle.m6128getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m6816equalsimpl0(textStyle.m6128getLetterSpacingXSAIIZE(), TextUnit.INSTANCE.m6830getUnspecifiedXSAIIZE()) || TextAlign.m6489equalsimpl0(textStyle.m6133getTextAligne0LSkKk(), TextAlign.INSTANCE.m6499getUnspecifiede0LSkKk()) || TextAlign.m6489equalsimpl0(textStyle.m6133getTextAligne0LSkKk(), TextAlign.INSTANCE.m6498getStarte0LSkKk()) || TextAlign.m6489equalsimpl0(textStyle.m6133getTextAligne0LSkKk(), TextAlign.INSTANCE.m6495getJustifye0LSkKk())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence $this$attachIndentationFixSpan) {
        if ($this$attachIndentationFixSpan.length() == 0) {
            return $this$attachIndentationFixSpan;
        }
        SpannableString spannable = $this$attachIndentationFixSpan instanceof Spannable ? (Spannable) $this$attachIndentationFixSpan : new SpannableString($this$attachIndentationFixSpan);
        SpannableExtensions_androidKt.setSpan(spannable, new IndentationFixSpan(), spannable.length() - 1, spannable.length() - 1);
        return spannable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutTextGranularity-duNsdkg, reason: not valid java name */
    public static final int m5942toLayoutTextGranularityduNsdkg(int $this$toLayoutTextGranularity_u2dduNsdkg) {
        return (!TextGranularity.m6052equalsimpl0($this$toLayoutTextGranularity_u2dduNsdkg, TextGranularity.INSTANCE.m6056getCharacterDRrd7Zo()) && TextGranularity.m6052equalsimpl0($this$toLayoutTextGranularity_u2dduNsdkg, TextGranularity.INSTANCE.m6057getWordDRrd7Zo())) ? 1 : 0;
    }
}
