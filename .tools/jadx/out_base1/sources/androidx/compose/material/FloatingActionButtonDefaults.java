package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* compiled from: FloatingActionButton.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ:\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Landroidx/compose/material/FloatingActionButtonDefaults;", "", "()V", "elevation", "Landroidx/compose/material/FloatingActionButtonElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "elevation-ixp7dh8", "(FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/FloatingActionButtonElevation;", "hoveredElevation", "focusedElevation", "elevation-xZ9-QkE", "(FFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/FloatingActionButtonElevation;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FloatingActionButtonDefaults {
    public static final int $stable = 0;
    public static final FloatingActionButtonDefaults INSTANCE = new FloatingActionButtonDefaults();

    private FloatingActionButtonDefaults() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use another overload of elevation")
    /* renamed from: elevation-ixp7dh8, reason: not valid java name */
    public final /* synthetic */ FloatingActionButtonElevation m1563elevationixp7dh8(float defaultElevation, float pressedElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        ComposerKt.sourceInformationMarkerStart($composer, -654132828, "C(elevation)P(0:c#ui.unit.Dp,1:c#ui.unit.Dp)222@9683L134:FloatingActionButton.kt#jmzs0o");
        if ((i & 1) != 0) {
            float defaultElevation3 = Dp.m6626constructorimpl(6);
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = Dp.m6626constructorimpl(12);
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-654132828, $changed, -1, "androidx.compose.material.FloatingActionButtonDefaults.elevation (FloatingActionButton.kt:222)");
        }
        int $this$dp$iv = $changed & 14;
        FloatingActionButtonElevation m1564elevationxZ9QkE = m1564elevationxZ9QkE(defaultElevation2, pressedElevation2, Dp.m6626constructorimpl(8), Dp.m6626constructorimpl(8), $composer, $this$dp$iv | 3456 | ($changed & 112) | (($changed << 6) & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1564elevationxZ9QkE;
    }

    /* renamed from: elevation-xZ9-QkE, reason: not valid java name */
    public final FloatingActionButtonElevation m1564elevationxZ9QkE(float defaultElevation, float pressedElevation, float hoveredElevation, float focusedElevation, Composer $composer, int $changed, int i) {
        float defaultElevation2;
        float pressedElevation2;
        float hoveredElevation2;
        float focusedElevation2;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 380403812, "C(elevation)P(0:c#ui.unit.Dp,3:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp)249@10645L367:FloatingActionButton.kt#jmzs0o");
        if ((i & 1) != 0) {
            float defaultElevation3 = Dp.m6626constructorimpl(6);
            defaultElevation2 = defaultElevation3;
        } else {
            defaultElevation2 = defaultElevation;
        }
        if ((i & 2) != 0) {
            float pressedElevation3 = Dp.m6626constructorimpl(12);
            pressedElevation2 = pressedElevation3;
        } else {
            pressedElevation2 = pressedElevation;
        }
        if ((i & 4) != 0) {
            float hoveredElevation3 = Dp.m6626constructorimpl(8);
            hoveredElevation2 = hoveredElevation3;
        } else {
            hoveredElevation2 = hoveredElevation;
        }
        if ((i & 8) != 0) {
            float focusedElevation3 = Dp.m6626constructorimpl(8);
            focusedElevation2 = focusedElevation3;
        } else {
            focusedElevation2 = focusedElevation;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(380403812, $changed, -1, "androidx.compose.material.FloatingActionButtonDefaults.elevation (FloatingActionButton.kt:248)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1100369724, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(defaultElevation2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(pressedElevation2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(hoveredElevation2)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(focusedElevation2)) || ($changed & 3072) == 2048);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new DefaultFloatingActionButtonElevation(defaultElevation2, pressedElevation2, hoveredElevation2, focusedElevation2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        DefaultFloatingActionButtonElevation defaultFloatingActionButtonElevation = (DefaultFloatingActionButtonElevation) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFloatingActionButtonElevation;
    }
}
