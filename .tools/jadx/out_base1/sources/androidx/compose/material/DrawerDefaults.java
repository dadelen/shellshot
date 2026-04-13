package androidx.compose.material;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: Drawer.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\u00020\u000f8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u000f8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u00158G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/material/DrawerDefaults;", "", "()V", "AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "getAnimationSpec", "()Landroidx/compose/animation/core/TweenSpec;", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "ScrimOpacity", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "getBackgroundColor", "(Landroidx/compose/runtime/Composer;I)J", "scrimColor", "getScrimColor", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DrawerDefaults {
    public static final int $stable = 0;
    public static final float ScrimOpacity = 0.32f;
    public static final DrawerDefaults INSTANCE = new DrawerDefaults();
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);
    private static final float Elevation = Dp.m6626constructorimpl(16);

    private DrawerDefaults() {
    }

    public final TweenSpec<Float> getAnimationSpec() {
        return AnimationSpec;
    }

    public final long getBackgroundColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -788676020, "C758@29273L6:Drawer.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-788676020, $changed, -1, "androidx.compose.material.DrawerDefaults.<get-backgroundColor> (Drawer.kt:758)");
        }
        long m1482getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1482getSurface0d7_KjU;
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1527getElevationD9Ej5fM() {
        return Elevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2041803618, "C770@29531L6:Drawer.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2041803618, $changed, -1, "androidx.compose.material.DrawerDefaults.<get-shape> (Drawer.kt:770)");
        }
        CornerBasedShape large = MaterialTheme.INSTANCE.getShapes($composer, 6).getLarge();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return large;
    }

    public final long getScrimColor(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, 617225966, "C777@29717L6:Drawer.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(617225966, $changed, -1, "androidx.compose.material.DrawerDefaults.<get-scrimColor> (Drawer.kt:777)");
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r1, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r1) : 0.32f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r1) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r1) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m4161copywmQWz5c;
    }
}
