package androidx.compose.foundation.text;

import androidx.compose.ui.text.TextLinkStyles;
import kotlin.Metadata;

/* compiled from: TextLinkScope.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0002*\u0018\b\u0000\u0010\u0003\"\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"isNullOrEmpty", "", "Landroidx/compose/ui/text/TextLinkStyles;", "LinkRange", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/LinkAnnotation;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextLinkScopeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNullOrEmpty(TextLinkStyles $this$isNullOrEmpty) {
        return $this$isNullOrEmpty == null || ($this$isNullOrEmpty.getStyle() == null && $this$isNullOrEmpty.getFocusedStyle() == null && $this$isNullOrEmpty.getHoveredStyle() == null && $this$isNullOrEmpty.getPressedStyle() == null);
    }
}
