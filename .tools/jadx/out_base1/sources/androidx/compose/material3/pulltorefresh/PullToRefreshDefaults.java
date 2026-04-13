package androidx.compose.material3.pulltorefresh;

import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: PullToRefresh.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0017\u0010\n\u001a\u00020\u000b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u000b8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshDefaults;", "", "()V", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "PositionalThreshold", "getPositionalThreshold-D9Ej5fM", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "indicatorColor", "getIndicatorColor", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "Indicator", "", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "isRefreshing", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "threshold", "Indicator-2poqoh4", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;JJFLandroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PullToRefreshDefaults {
    public static final int $stable = 0;
    public static final PullToRefreshDefaults INSTANCE = new PullToRefreshDefaults();
    private static final Shape shape = RoundedCornerShapeKt.getCircleShape();
    private static final float PositionalThreshold = Dp.m6626constructorimpl(80);
    private static final float Elevation = ElevationTokens.INSTANCE.m3178getLevel2D9Ej5fM();

    private PullToRefreshDefaults() {
    }

    public final Shape getShape() {
        return shape;
    }

    public final long getContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1066257972, "C419@15907L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1066257972, $changed, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-containerColor> (PullToRefresh.kt:419)");
        }
        long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurfaceContainerHigh();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return surfaceContainerHigh;
    }

    public final long getIndicatorColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1441334156, "C423@16068L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1441334156, $changed, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorColor> (PullToRefresh.kt:423)");
        }
        long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getOnSurfaceVariant();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return onSurfaceVariant;
    }

    /* renamed from: getPositionalThreshold-D9Ej5fM, reason: not valid java name */
    public final float m2989getPositionalThresholdD9Ej5fM() {
        return PositionalThreshold;
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m2988getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x02d5  */
    /* renamed from: Indicator-2poqoh4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2987Indicator2poqoh4(final androidx.compose.material3.pulltorefresh.PullToRefreshState r38, final boolean r39, androidx.compose.ui.Modifier r40, long r41, long r43, float r45, androidx.compose.runtime.Composer r46, final int r47, final int r48) {
        /*
            Method dump skipped, instructions count: 760
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.m2987Indicator2poqoh4(androidx.compose.material3.pulltorefresh.PullToRefreshState, boolean, androidx.compose.ui.Modifier, long, long, float, androidx.compose.runtime.Composer, int, int):void");
    }
}
