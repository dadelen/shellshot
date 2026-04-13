package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001ac\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "elevation", "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "Card-LPr_se0", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Card-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CardKt {
    /* renamed from: Card-F-jzlyU, reason: not valid java name */
    public static final void m1454CardFjzlyU(Modifier modifier, Shape shape, long backgroundColor, long contentColor, BorderStroke border, float elevation, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed, int i) {
        Shape shape2;
        long backgroundColor2;
        long contentColor2;
        BorderStroke border2;
        float elevation2;
        ComposerKt.sourceInformationMarkerStart($composer, 1956755640, "C(Card)P(5,6,0:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp)56@2436L6,57@2494L6,58@2536L32,63@2674L218:Card.kt#jmzs0o");
        if ((i & 1) != 0) {
            Modifier modifier2 = Modifier.INSTANCE;
            modifier = modifier2;
        }
        if ((i & 2) == 0) {
            shape2 = shape;
        } else {
            Shape shape3 = MaterialTheme.INSTANCE.getShapes($composer, 6).getMedium();
            shape2 = shape3;
        }
        if ((i & 4) == 0) {
            backgroundColor2 = backgroundColor;
        } else {
            backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU();
        }
        if ((i & 8) == 0) {
            contentColor2 = contentColor;
        } else {
            contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer, ($changed >> 6) & 14);
        }
        if ((i & 16) == 0) {
            border2 = border;
        } else {
            border2 = null;
        }
        if ((i & 32) == 0) {
            elevation2 = elevation;
        } else {
            elevation2 = Dp.m6626constructorimpl(1);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1956755640, $changed, -1, "androidx.compose.material.Card (Card.kt:62)");
        }
        SurfaceKt.m1660SurfaceFjzlyU(modifier, shape2, backgroundColor2, contentColor2, border2, elevation2, function2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* renamed from: Card-LPr_se0, reason: not valid java name */
    public static final void m1455CardLPr_se0(Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, long backgroundColor, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 778538979, "C(Card)P(8,7,5,9,0:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)107@4567L6,108@4625L6,109@4667L32,115@4862L319:Card.kt#jmzs0o");
        Modifier.Companion modifier2 = (i & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean enabled2 = (i & 4) != 0 ? true : enabled;
        CornerBasedShape shape2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getShapes($composer, 6).getMedium() : shape;
        long backgroundColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : backgroundColor;
        long contentColor2 = (i & 32) != 0 ? ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer, ($changed >> 12) & 14) : contentColor;
        BorderStroke border2 = (i & 64) != 0 ? null : border;
        float elevation2 = (i & 128) != 0 ? Dp.m6626constructorimpl(1) : elevation;
        MutableInteractionSource interactionSource2 = (i & 256) != 0 ? null : interactionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(778538979, $changed, -1, "androidx.compose.material.Card (Card.kt:114)");
        }
        SurfaceKt.m1661SurfaceLPr_se0(function0, modifier2, enabled2, shape2, backgroundColor2, contentColor2, border2, elevation2, interactionSource2, function2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }
}
