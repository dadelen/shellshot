package androidx.compose.ui.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextLinkStyles.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/TextLinkStyles;", "", "style", "Landroidx/compose/ui/text/SpanStyle;", "focusedStyle", "hoveredStyle", "pressedStyle", "(Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/SpanStyle;)V", "getFocusedStyle", "()Landroidx/compose/ui/text/SpanStyle;", "getHoveredStyle", "getPressedStyle", "getStyle", "equals", "", "other", "hashCode", "", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TextLinkStyles {
    public static final int $stable = 0;
    private final SpanStyle focusedStyle;
    private final SpanStyle hoveredStyle;
    private final SpanStyle pressedStyle;
    private final SpanStyle style;

    public TextLinkStyles() {
        this(null, null, null, null, 15, null);
    }

    public TextLinkStyles(SpanStyle style, SpanStyle focusedStyle, SpanStyle hoveredStyle, SpanStyle pressedStyle) {
        this.style = style;
        this.focusedStyle = focusedStyle;
        this.hoveredStyle = hoveredStyle;
        this.pressedStyle = pressedStyle;
    }

    public /* synthetic */ TextLinkStyles(SpanStyle spanStyle, SpanStyle spanStyle2, SpanStyle spanStyle3, SpanStyle spanStyle4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : spanStyle, (i & 2) != 0 ? null : spanStyle2, (i & 4) != 0 ? null : spanStyle3, (i & 8) != 0 ? null : spanStyle4);
    }

    public final SpanStyle getStyle() {
        return this.style;
    }

    public final SpanStyle getFocusedStyle() {
        return this.focusedStyle;
    }

    public final SpanStyle getHoveredStyle() {
        return this.hoveredStyle;
    }

    public final SpanStyle getPressedStyle() {
        return this.pressedStyle;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof TextLinkStyles) && Intrinsics.areEqual(this.style, ((TextLinkStyles) other).style) && Intrinsics.areEqual(this.focusedStyle, ((TextLinkStyles) other).focusedStyle) && Intrinsics.areEqual(this.hoveredStyle, ((TextLinkStyles) other).hoveredStyle) && Intrinsics.areEqual(this.pressedStyle, ((TextLinkStyles) other).pressedStyle)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        SpanStyle spanStyle = this.style;
        int result = spanStyle != null ? spanStyle.hashCode() : 0;
        int i = result * 31;
        SpanStyle spanStyle2 = this.focusedStyle;
        int result2 = i + (spanStyle2 != null ? spanStyle2.hashCode() : 0);
        int result3 = result2 * 31;
        SpanStyle spanStyle3 = this.hoveredStyle;
        int result4 = (result3 + (spanStyle3 != null ? spanStyle3.hashCode() : 0)) * 31;
        SpanStyle spanStyle4 = this.pressedStyle;
        return result4 + (spanStyle4 != null ? spanStyle4.hashCode() : 0);
    }
}
