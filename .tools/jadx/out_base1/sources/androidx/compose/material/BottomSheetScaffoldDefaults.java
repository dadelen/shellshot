package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\r\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/BottomSheetScaffoldDefaults;", "", "()V", "AnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "SheetElevation", "Landroidx/compose/ui/unit/Dp;", "getSheetElevation-D9Ej5fM", "()F", "F", "SheetPeekHeight", "getSheetPeekHeight-D9Ej5fM", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BottomSheetScaffoldDefaults {
    public static final BottomSheetScaffoldDefaults INSTANCE = new BottomSheetScaffoldDefaults();
    private static final float SheetElevation = Dp.m6626constructorimpl(8);
    private static final float SheetPeekHeight = Dp.m6626constructorimpl(56);
    private static final AnimationSpec<Float> AnimationSpec = AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    public static final int $stable = 8;

    private BottomSheetScaffoldDefaults() {
    }

    /* renamed from: getSheetElevation-D9Ej5fM, reason: not valid java name */
    public final float m1436getSheetElevationD9Ej5fM() {
        return SheetElevation;
    }

    /* renamed from: getSheetPeekHeight-D9Ej5fM, reason: not valid java name */
    public final float m1437getSheetPeekHeightD9Ej5fM() {
        return SheetPeekHeight;
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return AnimationSpec;
    }
}
