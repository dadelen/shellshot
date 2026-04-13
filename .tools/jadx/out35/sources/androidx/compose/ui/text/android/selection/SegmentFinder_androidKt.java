package androidx.compose.ui.text.android.selection;

import android.text.TextPaint;
import kotlin.Metadata;

/* compiled from: SegmentFinder.android.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"createGraphemeClusterSegmentFinder", "Landroidx/compose/ui/text/android/selection/SegmentFinder;", "text", "", "textPaint", "Landroid/text/TextPaint;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SegmentFinder_androidKt {
    public static final SegmentFinder createGraphemeClusterSegmentFinder(CharSequence text, TextPaint textPaint) {
        return new GraphemeClusterSegmentFinderApi29(text, textPaint);
    }
}
