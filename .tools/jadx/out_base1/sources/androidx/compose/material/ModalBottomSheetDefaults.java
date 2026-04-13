package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: ModalBottomSheet.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\u000e8Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Landroidx/compose/material/ModalBottomSheetDefaults;", "", "()V", "AnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "scrimColor", "Landroidx/compose/ui/graphics/Color;", "getScrimColor", "(Landroidx/compose/runtime/Composer;I)J", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ModalBottomSheetDefaults {
    public static final ModalBottomSheetDefaults INSTANCE = new ModalBottomSheetDefaults();
    private static final float Elevation = Dp.m6626constructorimpl(16);
    private static final AnimationSpec<Float> AnimationSpec = AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    public static final int $stable = 8;

    private ModalBottomSheetDefaults() {
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1580getElevationD9Ej5fM() {
        return Elevation;
    }

    public final long getScrimColor(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -112572414, "C539@21688L6:ModalBottomSheet.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-112572414, $changed, -1, "androidx.compose.material.ModalBottomSheetDefaults.<get-scrimColor> (ModalBottomSheet.kt:539)");
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r1, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r1) : 0.32f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r1) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r1) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m4161copywmQWz5c;
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return AnimationSpec;
    }
}
