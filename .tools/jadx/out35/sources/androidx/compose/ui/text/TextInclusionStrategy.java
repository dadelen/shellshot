package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextInclusionStrategy;
import kotlin.Metadata;

/* compiled from: TextInclusionStrategy.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/TextInclusionStrategy;", "", "isIncluded", "", "textBounds", "Landroidx/compose/ui/geometry/Rect;", "rect", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public interface TextInclusionStrategy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    boolean isIncluded(Rect textBounds, Rect rect);

    /* compiled from: TextInclusionStrategy.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/TextInclusionStrategy$Companion;", "", "()V", "AnyOverlap", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getAnyOverlap", "()Landroidx/compose/ui/text/TextInclusionStrategy;", "ContainsAll", "getContainsAll", "ContainsCenter", "getContainsCenter", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final TextInclusionStrategy AnyOverlap = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                boolean overlaps;
                overlaps = rect.overlaps(rect2);
                return overlaps;
            }
        };
        private static final TextInclusionStrategy ContainsAll = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda1
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                return TextInclusionStrategy.Companion.ContainsAll$lambda$1(rect, rect2);
            }
        };
        private static final TextInclusionStrategy ContainsCenter = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda2
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                boolean m3948containsk4lQ0M;
                m3948containsk4lQ0M = rect2.m3948containsk4lQ0M(rect.m3952getCenterF1C5BW0());
                return m3948containsk4lQ0M;
            }
        };

        private Companion() {
        }

        public final TextInclusionStrategy getAnyOverlap() {
            return AnyOverlap;
        }

        public final TextInclusionStrategy getContainsAll() {
            return ContainsAll;
        }

        static final boolean ContainsAll$lambda$1(Rect textBounds, Rect rect) {
            return !rect.isEmpty() && textBounds.getLeft() >= rect.getLeft() && textBounds.getRight() <= rect.getRight() && textBounds.getTop() >= rect.getTop() && textBounds.getBottom() <= rect.getBottom();
        }

        public final TextInclusionStrategy getContainsCenter() {
            return ContainsCenter;
        }
    }
}
