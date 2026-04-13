package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JN\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 Jl\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010#\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010$\u001a\u00020\u00192\b\b\u0002\u0010%\u001a\u00020\u00192\b\b\u0002\u0010&\u001a\u00020\u00192\b\b\u0002\u0010'\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)JN\u0010*\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010 Jl\u0010,\u001a\u00020\"2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010#\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010$\u001a\u00020\u00192\b\b\u0002\u0010%\u001a\u00020\u00192\b\b\u0002\u0010&\u001a\u00020\u00192\b\b\u0002\u0010'\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010)R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0019\u0010\u000b\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000f\u0010\tR\u0019\u0010\u0010\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0012\u001a\u00020\u00138G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006."}, d2 = {"Landroidx/compose/material/ChipDefaults;", "", "()V", "ContentOpacity", "", "LeadingIconOpacity", "LeadingIconSize", "Landroidx/compose/ui/unit/Dp;", "getLeadingIconSize-D9Ej5fM", "()F", "F", "MinHeight", "getMinHeight-D9Ej5fM", "OutlinedBorderOpacity", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "SelectedIconSize", "getSelectedIconSize-D9Ej5fM", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "chipColors", "Landroidx/compose/material/ChipColors;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "leadingIconContentColor", "disabledBackgroundColor", "disabledContentColor", "disabledLeadingIconContentColor", "chipColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ChipColors;", "filterChipColors", "Landroidx/compose/material/SelectableChipColors;", "leadingIconColor", "disabledLeadingIconColor", "selectedBackgroundColor", "selectedContentColor", "selectedLeadingIconColor", "filterChipColors-J08w3-E", "(JJJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/SelectableChipColors;", "outlinedChipColors", "outlinedChipColors-5tl4gsc", "outlinedFilterChipColors", "outlinedFilterChipColors-J08w3-E", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ChipDefaults {
    public static final int $stable = 0;
    public static final float ContentOpacity = 0.87f;
    public static final float LeadingIconOpacity = 0.54f;
    public static final float OutlinedBorderOpacity = 0.12f;
    public static final ChipDefaults INSTANCE = new ChipDefaults();
    private static final float MinHeight = Dp.m6626constructorimpl(32);
    private static final float OutlinedBorderSize = Dp.m6626constructorimpl(1);
    private static final float LeadingIconSize = Dp.m6626constructorimpl(20);
    private static final float SelectedIconSize = Dp.m6626constructorimpl(18);

    private ChipDefaults() {
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1464getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: chipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1461chipColors5tl4gsc(long backgroundColor, long contentColor, long leadingIconContentColor, long disabledBackgroundColor, long disabledContentColor, long disabledLeadingIconContentColor, Composer $composer, int $changed, int i) {
        long backgroundColor2;
        long contentColor2;
        long leadingIconContentColor2;
        long disabledBackgroundColor2;
        long disabledContentColor2;
        long disabledLeadingIconContentColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        ComposerKt.sourceInformationMarkerStart($composer, 1838505436, "C(chipColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)391@17141L6,392@17235L6,393@17296L6,396@17498L6,397@17558L8,398@17633L6,400@17740L8,403@17888L8:Chip.kt#jmzs0o");
        if ((i & 1) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            backgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c6, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            backgroundColor2 = backgroundColor;
        }
        if ((i & 2) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            contentColor2 = m4161copywmQWz5c5;
        } else {
            contentColor2 = contentColor;
        }
        if ((i & 4) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(contentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor2) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            leadingIconContentColor2 = m4161copywmQWz5c4;
        } else {
            leadingIconContentColor2 = leadingIconContentColor;
        }
        if ((i & 8) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledBackgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c3, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledBackgroundColor2 = disabledBackgroundColor;
        }
        if ((i & 16) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c2;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if ((i & 32) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(leadingIconContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconContentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconContentColor2) : 0.0f);
            disabledLeadingIconContentColor2 = m4161copywmQWz5c;
        } else {
            disabledLeadingIconContentColor2 = disabledLeadingIconContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1838505436, $changed, -1, "androidx.compose.material.ChipDefaults.chipColors (Chip.kt:404)");
        }
        DefaultChipColors defaultChipColors = new DefaultChipColors(backgroundColor2, contentColor2, leadingIconContentColor2, disabledBackgroundColor2, disabledContentColor2, disabledLeadingIconContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultChipColors;
    }

    /* renamed from: outlinedChipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1467outlinedChipColors5tl4gsc(long backgroundColor, long contentColor, long leadingIconContentColor, long disabledBackgroundColor, long disabledContentColor, long disabledLeadingIconContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledLeadingIconContentColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, -1763922662, "C(outlinedChipColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)427@19084L6,428@19144L6,432@19427L8,435@19575L8,436@19627L342:Chip.kt#jmzs0o");
        long backgroundColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : backgroundColor;
        if ((i & 2) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r6) : 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            contentColor2 = m4161copywmQWz5c2;
        } else {
            contentColor2 = contentColor;
        }
        long leadingIconContentColor2 = (i & 4) != 0 ? Color.m4161copywmQWz5c(contentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor2) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f) : leadingIconContentColor;
        long disabledBackgroundColor2 = (i & 8) != 0 ? backgroundColor2 : disabledBackgroundColor;
        long disabledContentColor2 = (i & 16) != 0 ? Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f) : disabledContentColor;
        if ((i & 32) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(leadingIconContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconContentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconContentColor2) : 0.0f);
            disabledLeadingIconContentColor2 = m4161copywmQWz5c;
        } else {
            disabledLeadingIconContentColor2 = disabledLeadingIconContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1763922662, $changed, -1, "androidx.compose.material.ChipDefaults.outlinedChipColors (Chip.kt:436)");
        }
        long backgroundColor3 = backgroundColor2;
        long backgroundColor4 = contentColor2;
        ChipColors m1461chipColors5tl4gsc = m1461chipColors5tl4gsc(backgroundColor3, backgroundColor4, leadingIconContentColor2, disabledBackgroundColor2, disabledContentColor2, disabledLeadingIconContentColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m1461chipColors5tl4gsc;
    }

    /* renamed from: filterChipColors-J08w3-E, reason: not valid java name */
    public final SelectableChipColors m1462filterChipColorsJ08w3E(long backgroundColor, long contentColor, long leadingIconColor, long disabledBackgroundColor, long disabledContentColor, long disabledLeadingIconColor, long selectedBackgroundColor, long selectedContentColor, long selectedLeadingIconColor, Composer $composer, int $changed, int i) {
        long backgroundColor2;
        long contentColor2;
        long leadingIconColor2;
        long disabledBackgroundColor2;
        long disabledContentColor2;
        long disabledLeadingIconColor2;
        long selectedBackgroundColor2;
        long selectedContentColor2;
        long selectedLeadingIconColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        long m4161copywmQWz5c9;
        ComposerKt.sourceInformationMarkerStart($composer, 830140629, "C(filterChipColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,8:c#ui.graphics.Color)461@20966L6,462@21060L6,463@21121L6,466@21308L6,467@21368L8,468@21443L6,470@21550L8,473@21685L8,475@21781L6,478@21940L6,481@22101L6:Chip.kt#jmzs0o");
        if ((i & 1) != 0) {
            m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            backgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c9, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            backgroundColor2 = backgroundColor;
        }
        if ((i & 2) != 0) {
            m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r10) : 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            contentColor2 = m4161copywmQWz5c8;
        } else {
            contentColor2 = contentColor;
        }
        if ((i & 4) != 0) {
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(contentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor2) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            leadingIconColor2 = m4161copywmQWz5c7;
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        if ((i & 8) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            disabledBackgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c6, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU());
        } else {
            disabledBackgroundColor2 = disabledBackgroundColor;
        }
        if ((i & 16) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c5;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if ((i & 32) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(leadingIconColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconColor2) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c4;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((i & 64) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedBackgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c3, backgroundColor2);
        } else {
            selectedBackgroundColor2 = selectedBackgroundColor;
        }
        if ((i & 128) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedContentColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c2, contentColor2);
        } else {
            selectedContentColor2 = selectedContentColor;
        }
        if ((i & 256) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedLeadingIconColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c, leadingIconColor2);
        } else {
            selectedLeadingIconColor2 = selectedLeadingIconColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(830140629, $changed, -1, "androidx.compose.material.ChipDefaults.filterChipColors (Chip.kt:484)");
        }
        DefaultSelectableChipColors defaultSelectableChipColors = new DefaultSelectableChipColors(backgroundColor2, contentColor2, leadingIconColor2, disabledBackgroundColor2, disabledContentColor2, disabledLeadingIconColor2, selectedBackgroundColor2, selectedContentColor2, selectedLeadingIconColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSelectableChipColors;
    }

    /* renamed from: outlinedFilterChipColors-J08w3-E, reason: not valid java name */
    public final SelectableChipColors m1468outlinedFilterChipColorsJ08w3E(long backgroundColor, long contentColor, long leadingIconColor, long disabledBackgroundColor, long disabledContentColor, long disabledLeadingIconColor, long selectedBackgroundColor, long selectedContentColor, long selectedLeadingIconColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long leadingIconColor2;
        long disabledContentColor2;
        long disabledLeadingIconColor2;
        long selectedBackgroundColor2;
        long selectedContentColor2;
        long selectedLeadingIconColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        ComposerKt.sourceInformationMarkerStart($composer, 346878099, "C(outlinedFilterChipColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,5:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,8:c#ui.graphics.Color)512@23751L6,513@23811L6,517@24071L8,520@24206L8,522@24302L6,525@24462L6,528@24623L6:Chip.kt#jmzs0o");
        long backgroundColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : backgroundColor;
        if ((i & 2) != 0) {
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r10) : 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            contentColor2 = m4161copywmQWz5c7;
        } else {
            contentColor2 = contentColor;
        }
        if ((i & 4) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(contentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor2) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            leadingIconColor2 = m4161copywmQWz5c6;
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        long disabledBackgroundColor2 = (i & 8) != 0 ? backgroundColor2 : disabledBackgroundColor;
        if ((i & 16) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor2) : 0.0f);
            disabledContentColor2 = m4161copywmQWz5c5;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if ((i & 32) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(leadingIconColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconColor2) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c4;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((i & 64) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedBackgroundColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c3, backgroundColor2);
        } else {
            selectedBackgroundColor2 = selectedBackgroundColor;
        }
        if ((i & 128) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedContentColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c2, contentColor2);
        } else {
            selectedContentColor2 = selectedContentColor;
        }
        if ((i & 256) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            selectedLeadingIconColor2 = ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c, leadingIconColor2);
        } else {
            selectedLeadingIconColor2 = selectedLeadingIconColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(346878099, $changed, -1, "androidx.compose.material.ChipDefaults.outlinedFilterChipColors (Chip.kt:531)");
        }
        DefaultSelectableChipColors defaultSelectableChipColors = new DefaultSelectableChipColors(backgroundColor2, contentColor2, leadingIconColor2, disabledBackgroundColor2, disabledContentColor2, disabledLeadingIconColor2, selectedBackgroundColor2, selectedContentColor2, selectedLeadingIconColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSelectableChipColors;
    }

    public final BorderStroke getOutlinedBorder(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -1650225597, "C549@25470L6:Chip.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1650225597, $changed, -1, "androidx.compose.material.ChipDefaults.<get-outlinedBorder> (Chip.kt:548)");
        }
        float f = OutlinedBorderSize;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r2) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
        BorderStroke m254BorderStrokecXLIe8U = BorderStrokeKt.m254BorderStrokecXLIe8U(f, m4161copywmQWz5c);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m254BorderStrokecXLIe8U;
    }

    /* renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1465getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    /* renamed from: getLeadingIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1463getLeadingIconSizeD9Ej5fM() {
        return LeadingIconSize;
    }

    /* renamed from: getSelectedIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1466getSelectedIconSizeD9Ej5fM() {
        return SelectedIconSize;
    }
}
