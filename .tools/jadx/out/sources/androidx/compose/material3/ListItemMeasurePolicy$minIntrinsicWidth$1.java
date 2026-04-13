package androidx.compose.material3;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: ListItem.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
/* synthetic */ class ListItemMeasurePolicy$minIntrinsicWidth$1 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
    public static final ListItemMeasurePolicy$minIntrinsicWidth$1 INSTANCE = new ListItemMeasurePolicy$minIntrinsicWidth$1();

    ListItemMeasurePolicy$minIntrinsicWidth$1() {
        super(2, IntrinsicMeasurable.class, "minIntrinsicWidth", "minIntrinsicWidth(I)I", 0);
    }

    public final Integer invoke(IntrinsicMeasurable p0, int p1) {
        return Integer.valueOf(p0.minIntrinsicWidth(p1));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
        return invoke(intrinsicMeasurable, num.intValue());
    }
}
