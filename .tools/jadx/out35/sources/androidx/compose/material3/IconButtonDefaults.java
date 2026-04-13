package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilledIconButtonTokens;
import androidx.compose.material3.tokens.FilledTonalIconButtonTokens;
import androidx.compose.material3.tokens.IconButtonTokens;
import androidx.compose.material3.tokens.OutlinedIconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* compiled from: IconButton.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0016\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u0017J:\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010 JN\u0010\u001f\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010!\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u0017J:\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010\u001eJ\r\u0010'\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010 JN\u0010'\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010!\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010$J\r\u0010)\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u0017J:\u0010)\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010\u001eJ\r\u0010+\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010 JN\u0010+\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010!\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010$J\u0015\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00101J\r\u00102\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u0017J:\u00102\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u0010\u001eJ\u001f\u00104\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u000200H\u0007¢\u0006\u0002\u00106J\r\u00107\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010 JN\u00107\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010!\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b8\u0010$J\u001e\u00109\u001a\u00020\n*\u00020\u000b2\u0006\u0010:\u001a\u00020\u0019H\u0000ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001e\u0010=\u001a\u00020\u000f*\u00020\u000b2\u0006\u0010:\u001a\u00020\u0019H\u0000ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001e\u0010@\u001a\u00020\n*\u00020\u000b2\u0006\u0010:\u001a\u00020\u0019H\u0000ø\u0001\u0000¢\u0006\u0004\bA\u0010<J\u001e\u0010B\u001a\u00020\u000f*\u00020\u000b2\u0006\u0010:\u001a\u00020\u0019H\u0000ø\u0001\u0000¢\u0006\u0004\bC\u0010?R\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\n*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\u0014\u001a\u00020\u000f*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults;", "", "()V", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape", "defaultFilledIconButtonColors", "Landroidx/compose/material3/IconButtonColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultFilledIconButtonColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/IconButtonColors;", "defaultFilledIconToggleButtonColors", "Landroidx/compose/material3/IconToggleButtonColors;", "getDefaultFilledIconToggleButtonColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/IconToggleButtonColors;", "defaultFilledTonalIconButtonColors", "getDefaultFilledTonalIconButtonColors$material3_release", "defaultFilledTonalIconToggleButtonColors", "getDefaultFilledTonalIconToggleButtonColors$material3_release", "filledIconButtonColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconButtonColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "filledIconButtonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconButtonColors;", "filledIconToggleButtonColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/IconToggleButtonColors;", "checkedContainerColor", "checkedContentColor", "filledIconToggleButtonColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconToggleButtonColors;", "filledTonalIconButtonColors", "filledTonalIconButtonColors-ro_MJ88", "filledTonalIconToggleButtonColors", "filledTonalIconToggleButtonColors-5tl4gsc", "iconButtonColors", "iconButtonColors-ro_MJ88", "iconToggleButtonColors", "iconToggleButtonColors-5tl4gsc", "outlinedIconButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconButtonColors", "outlinedIconButtonColors-ro_MJ88", "outlinedIconToggleButtonBorder", "checked", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconToggleButtonColors", "outlinedIconToggleButtonColors-5tl4gsc", "defaultIconButtonColors", "localContentColor", "defaultIconButtonColors-4WTKRHQ$material3_release", "(Landroidx/compose/material3/ColorScheme;J)Landroidx/compose/material3/IconButtonColors;", "defaultIconToggleButtonColors", "defaultIconToggleButtonColors-4WTKRHQ$material3_release", "(Landroidx/compose/material3/ColorScheme;J)Landroidx/compose/material3/IconToggleButtonColors;", "defaultOutlinedIconButtonColors", "defaultOutlinedIconButtonColors-4WTKRHQ$material3_release", "defaultOutlinedIconToggleButtonColors", "defaultOutlinedIconToggleButtonColors-4WTKRHQ$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class IconButtonDefaults {
    public static final int $stable = 0;
    public static final IconButtonDefaults INSTANCE = new IconButtonDefaults();

    private IconButtonDefaults() {
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1265841879, "C584@27553L5:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1265841879, $changed, -1, "androidx.compose.material3.IconButtonDefaults.<get-filledShape> (IconButton.kt:584)");
        }
        Shape value = ShapesKt.getValue(FilledIconButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1327125527, "C588@27711L5:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1327125527, $changed, -1, "androidx.compose.material3.IconButtonDefaults.<get-outlinedShape> (IconButton.kt:588)");
        }
        Shape value = ShapesKt.getValue(OutlinedIconButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final IconButtonColors iconButtonColors(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        IconButtonColors colors;
        $composer.startReplaceGroup(-1519621781);
        ComposerKt.sourceInformation($composer, "C(iconButtonColors)593@27925L7,594@27968L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1519621781, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:592)");
        }
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor = ((Color) consume).m4173unboximpl();
        IconButtonColors colors2 = m2140defaultIconButtonColors4WTKRHQ$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6), contentColor);
        if (Color.m4164equalsimpl0(colors2.getContentColor(), contentColor)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return colors2;
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor) : 0.0f);
        colors = colors2.m2135copyjRlVdoo((r18 & 1) != 0 ? colors2.containerColor : 0L, (r18 & 2) != 0 ? colors2.contentColor : contentColor, (r18 & 4) != 0 ? colors2.disabledContainerColor : 0L, (r18 & 8) != 0 ? colors2.disabledContentColor : m4161copywmQWz5c);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return colors;
    }

    /* renamed from: iconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m2148iconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long contentColor3;
        long disabledContentColor2;
        long contentColor4;
        ComposerKt.sourceInformationMarkerStart($composer, 999008085, "C(iconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)617@28950L7,622@29180L11,623@29247L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) consume).m4173unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            contentColor3 = contentColor2;
            contentColor4 = Color.m4161copywmQWz5c(contentColor3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor3) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor3) : 0.0f);
            disabledContentColor2 = contentColor4;
        } else {
            contentColor3 = contentColor2;
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(999008085, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:622)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localContentColor2);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor5 = contentColor3;
        long contentColor6 = disabledContainerColor2;
        IconButtonColors m2135copyjRlVdoo = m2140defaultIconButtonColors4WTKRHQ$material3_release(colorScheme, ((Color) consume2).m4173unboximpl()).m2135copyjRlVdoo(containerColor2, contentColor5, contentColor6, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2135copyjRlVdoo;
    }

    /* renamed from: defaultIconButtonColors-4WTKRHQ$material3_release, reason: not valid java name */
    public final IconButtonColors m2140defaultIconButtonColors4WTKRHQ$material3_release(ColorScheme $this$defaultIconButtonColors_u2d4WTKRHQ, long localContentColor) {
        long m4161copywmQWz5c;
        IconButtonColors defaultIconButtonColorsCached = $this$defaultIconButtonColors_u2d4WTKRHQ.getDefaultIconButtonColorsCached();
        if (defaultIconButtonColorsCached != null) {
            return defaultIconButtonColorsCached;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(localContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(localContentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(localContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(localContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(localContentColor) : 0.0f);
        IconButtonColors it = new IconButtonColors(m4198getTransparent0d7_KjU, localContentColor, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, null);
        $this$defaultIconButtonColors_u2d4WTKRHQ.setDefaultIconButtonColorsCached$material3_release(it);
        return it;
    }

    public final IconToggleButtonColors iconToggleButtonColors(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        IconToggleButtonColors colors;
        $composer.startReplaceGroup(-589987581);
        ComposerKt.sourceInformation($composer, "C(iconToggleButtonColors)651@30397L7,652@30440L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-589987581, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButton.kt:650)");
        }
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor = ((Color) consume).m4173unboximpl();
        IconToggleButtonColors colors2 = m2141defaultIconToggleButtonColors4WTKRHQ$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6), contentColor);
        if (Color.m4164equalsimpl0(colors2.getContentColor(), contentColor)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return colors2;
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor) : 0.0f);
        colors = colors2.m2158copytNS2XkQ((r26 & 1) != 0 ? colors2.containerColor : 0L, (r26 & 2) != 0 ? colors2.contentColor : contentColor, (r26 & 4) != 0 ? colors2.disabledContainerColor : 0L, (r26 & 8) != 0 ? colors2.disabledContentColor : m4161copywmQWz5c, (r26 & 16) != 0 ? colors2.checkedContainerColor : 0L, (r26 & 32) != 0 ? colors2.checkedContentColor : 0L);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return colors;
    }

    /* renamed from: iconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m2149iconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long contentColor3;
        long disabledContentColor2;
        long contentColor4;
        ComposerKt.sourceInformationMarkerStart($composer, -2020719549, "C(iconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)678@31629L7,685@31979L11,686@32052L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) consume).m4173unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            contentColor3 = contentColor2;
            contentColor4 = Color.m4161copywmQWz5c(contentColor3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor3) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor3) : 0.0f);
            disabledContentColor2 = contentColor4;
        } else {
            contentColor3 = contentColor2;
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2020719549, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButton.kt:685)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localContentColor2);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor5 = contentColor3;
        long contentColor6 = disabledContainerColor2;
        IconToggleButtonColors m2158copytNS2XkQ = m2141defaultIconToggleButtonColors4WTKRHQ$material3_release(colorScheme, ((Color) consume2).m4173unboximpl()).m2158copytNS2XkQ(containerColor2, contentColor5, contentColor6, disabledContentColor2, checkedContainerColor2, checkedContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2158copytNS2XkQ;
    }

    /* renamed from: defaultIconToggleButtonColors-4WTKRHQ$material3_release, reason: not valid java name */
    public final IconToggleButtonColors m2141defaultIconToggleButtonColors4WTKRHQ$material3_release(ColorScheme $this$defaultIconToggleButtonColors_u2d4WTKRHQ, long localContentColor) {
        long m4161copywmQWz5c;
        IconToggleButtonColors defaultIconToggleButtonColorsCached = $this$defaultIconToggleButtonColors_u2d4WTKRHQ.getDefaultIconToggleButtonColorsCached();
        if (defaultIconToggleButtonColorsCached != null) {
            return defaultIconToggleButtonColorsCached;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(localContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(localContentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(localContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(localContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(localContentColor) : 0.0f);
        IconToggleButtonColors it = new IconToggleButtonColors(m4198getTransparent0d7_KjU, localContentColor, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, Color.INSTANCE.m4198getTransparent0d7_KjU(), ColorSchemeKt.fromToken($this$defaultIconToggleButtonColors_u2d4WTKRHQ, IconButtonTokens.INSTANCE.getSelectedIconColor()), null);
        $this$defaultIconToggleButtonColors_u2d4WTKRHQ.setDefaultIconToggleButtonColorsCached$material3_release(it);
        return it;
    }

    public final IconButtonColors filledIconButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1857395287, "C(filledIconButtonColors)719@33486L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1857395287, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButton.kt:719)");
        }
        IconButtonColors defaultFilledIconButtonColors$material3_release = getDefaultFilledIconButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFilledIconButtonColors$material3_release;
    }

    /* renamed from: filledIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m2144filledIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -669858473, "C(filledIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)732@34130L31,736@34326L11:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-669858473, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButton.kt:736)");
        }
        IconButtonColors m2135copyjRlVdoo = getDefaultFilledIconButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2135copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2135copyjRlVdoo;
    }

    public final IconButtonColors getDefaultFilledIconButtonColors$material3_release(ColorScheme $this$defaultFilledIconButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        IconButtonColors it = $this$defaultFilledIconButtonColors.getDefaultFilledIconButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultFilledIconButtonColors, FilledIconButtonTokens.INSTANCE.getContainerColor());
            long m1933contentColorFor4WTKRHQ = ColorSchemeKt.m1933contentColorFor4WTKRHQ($this$defaultFilledIconButtonColors, ColorSchemeKt.fromToken($this$defaultFilledIconButtonColors, FilledIconButtonTokens.INSTANCE.getContainerColor()));
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : FilledIconButtonTokens.INSTANCE.getDisabledContainerOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledIconButtonColors, FilledIconButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : FilledIconButtonTokens.INSTANCE.getDisabledOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledIconButtonColors, FilledIconButtonTokens.INSTANCE.getDisabledColor())) : 0.0f);
            IconButtonColors it2 = new IconButtonColors(fromToken, m1933contentColorFor4WTKRHQ, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultFilledIconButtonColors.setDefaultFilledIconButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final IconToggleButtonColors filledIconToggleButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1554706367, "C(filledIconToggleButtonColors)766@35781L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1554706367, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButton.kt:766)");
        }
        IconToggleButtonColors defaultFilledIconToggleButtonColors$material3_release = getDefaultFilledIconToggleButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFilledIconToggleButtonColors$material3_release;
    }

    /* renamed from: filledIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m2145filledIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1887173701, "C(filledIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)788@36995L38,790@37088L11:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        long checkedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.m1934contentColorForek8zF_U(checkedContainerColor2, $composer, ($changed >> 12) & 14) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1887173701, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButton.kt:790)");
        }
        IconToggleButtonColors m2158copytNS2XkQ = getDefaultFilledIconToggleButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2158copytNS2XkQ(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2158copytNS2XkQ;
    }

    public final IconToggleButtonColors getDefaultFilledIconToggleButtonColors$material3_release(ColorScheme $this$defaultFilledIconToggleButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        IconToggleButtonColors it = $this$defaultFilledIconToggleButtonColors.getDefaultFilledIconToggleButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getUnselectedContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getToggleUnselectedColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : FilledIconButtonTokens.INSTANCE.getDisabledContainerOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : FilledIconButtonTokens.INSTANCE.getDisabledOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getDisabledColor())) : 0.0f);
            IconToggleButtonColors it2 = new IconToggleButtonColors(fromToken, fromToken2, m4161copywmQWz5c, m4161copywmQWz5c2, ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getSelectedContainerColor()), ColorSchemeKt.m1933contentColorFor4WTKRHQ($this$defaultFilledIconToggleButtonColors, ColorSchemeKt.fromToken($this$defaultFilledIconToggleButtonColors, FilledIconButtonTokens.INSTANCE.getSelectedContainerColor())), null);
            $this$defaultFilledIconToggleButtonColors.setDefaultFilledIconToggleButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final IconButtonColors filledTonalIconButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1099140437, "C(filledTonalIconButtonColors)830@39188L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1099140437, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButton.kt:830)");
        }
        IconButtonColors defaultFilledTonalIconButtonColors$material3_release = getDefaultFilledTonalIconButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFilledTonalIconButtonColors$material3_release;
    }

    /* renamed from: filledTonalIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m2146filledTonalIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -18532843, "C(filledTonalIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)844@39854L31,848@40050L11:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-18532843, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButton.kt:848)");
        }
        IconButtonColors m2135copyjRlVdoo = getDefaultFilledTonalIconButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2135copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2135copyjRlVdoo;
    }

    public final IconButtonColors getDefaultFilledTonalIconButtonColors$material3_release(ColorScheme $this$defaultFilledTonalIconButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        IconButtonColors it = $this$defaultFilledTonalIconButtonColors.getDefaultFilledTonalIconButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultFilledTonalIconButtonColors, FilledTonalIconButtonTokens.INSTANCE.getContainerColor());
            long m1933contentColorFor4WTKRHQ = ColorSchemeKt.m1933contentColorFor4WTKRHQ($this$defaultFilledTonalIconButtonColors, ColorSchemeKt.fromToken($this$defaultFilledTonalIconButtonColors, FilledTonalIconButtonTokens.INSTANCE.getContainerColor()));
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalIconButtonColors, FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : FilledTonalIconButtonTokens.INSTANCE.getDisabledOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalIconButtonColors, FilledTonalIconButtonTokens.INSTANCE.getDisabledColor())) : 0.0f);
            IconButtonColors it2 = new IconButtonColors(fromToken, m1933contentColorFor4WTKRHQ, m4161copywmQWz5c, m4161copywmQWz5c2, null);
            $this$defaultFilledTonalIconButtonColors.setDefaultFilledTonalIconButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final IconToggleButtonColors filledTonalIconToggleButtonColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 434219587, "C(filledTonalIconToggleButtonColors)878@41565L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(434219587, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButton.kt:878)");
        }
        IconToggleButtonColors defaultFilledTonalIconToggleButtonColors$material3_release = getDefaultFilledTonalIconToggleButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFilledTonalIconToggleButtonColors$material3_release;
    }

    /* renamed from: filledTonalIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m2147filledTonalIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -19426557, "C(filledTonalIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)894@42431L31,900@42747L11:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long disabledContentColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContentColor;
        long checkedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-19426557, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButton.kt:900)");
        }
        IconToggleButtonColors m2158copytNS2XkQ = getDefaultFilledTonalIconToggleButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2158copytNS2XkQ(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2158copytNS2XkQ;
    }

    public final IconToggleButtonColors getDefaultFilledTonalIconToggleButtonColors$material3_release(ColorScheme $this$defaultFilledTonalIconToggleButtonColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        IconToggleButtonColors it = $this$defaultFilledTonalIconToggleButtonColors.getDefaultFilledTonalIconToggleButtonColorsCached();
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getUnselectedContainerColor());
            long m1933contentColorFor4WTKRHQ = ColorSchemeKt.m1933contentColorFor4WTKRHQ($this$defaultFilledTonalIconToggleButtonColors, ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getUnselectedContainerColor()));
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : FilledTonalIconButtonTokens.INSTANCE.getDisabledOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getDisabledColor())) : 0.0f);
            IconToggleButtonColors it2 = new IconToggleButtonColors(fromToken, m1933contentColorFor4WTKRHQ, m4161copywmQWz5c, m4161copywmQWz5c2, ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getSelectedContainerColor()), ColorSchemeKt.fromToken($this$defaultFilledTonalIconToggleButtonColors, FilledTonalIconButtonTokens.INSTANCE.getToggleSelectedColor()), null);
            $this$defaultFilledTonalIconToggleButtonColors.setDefaultFilledTonalIconToggleButtonColorsCached$material3_release(it2);
            return it2;
        }
        return it;
    }

    public final IconButtonColors outlinedIconButtonColors(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        IconButtonColors m2135copyjRlVdoo;
        $composer.startReplaceGroup(389287465);
        ComposerKt.sourceInformation($composer, "C(outlinedIconButtonColors)940@44790L11,940@44852L7,941@44906L7:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(389287465, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButton.kt:938)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd($composer);
        IconButtonColors colors = m2142defaultOutlinedIconButtonColors4WTKRHQ$material3_release(colorScheme, ((Color) consume).m4173unboximpl());
        ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localContentColor2);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor = ((Color) consume2).m4173unboximpl();
        if (Color.m4164equalsimpl0(colors.getContentColor(), contentColor)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return colors;
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor) : 0.0f);
        m2135copyjRlVdoo = colors.m2135copyjRlVdoo((r18 & 1) != 0 ? colors.containerColor : 0L, (r18 & 2) != 0 ? colors.contentColor : contentColor, (r18 & 4) != 0 ? colors.disabledContainerColor : 0L, (r18 & 8) != 0 ? colors.disabledContentColor : m4161copywmQWz5c);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return m2135copyjRlVdoo;
    }

    /* renamed from: outlinedIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m2150outlinedIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long contentColor3;
        long disabledContentColor2;
        long contentColor4;
        ComposerKt.sourceInformationMarkerStart($composer, -1030517545, "C(outlinedIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)965@45873L7,970@46107L11,971@46182L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) consume).m4173unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            contentColor3 = contentColor2;
            contentColor4 = Color.m4161copywmQWz5c(contentColor3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor3) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor3) : 0.0f);
            disabledContentColor2 = contentColor4;
        } else {
            contentColor3 = contentColor2;
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1030517545, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButton.kt:970)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localContentColor2);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor5 = contentColor3;
        long contentColor6 = disabledContainerColor2;
        IconButtonColors m2135copyjRlVdoo = m2142defaultOutlinedIconButtonColors4WTKRHQ$material3_release(colorScheme, ((Color) consume2).m4173unboximpl()).m2135copyjRlVdoo(containerColor2, contentColor5, contentColor6, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2135copyjRlVdoo;
    }

    /* renamed from: defaultOutlinedIconButtonColors-4WTKRHQ$material3_release, reason: not valid java name */
    public final IconButtonColors m2142defaultOutlinedIconButtonColors4WTKRHQ$material3_release(ColorScheme $this$defaultOutlinedIconButtonColors_u2d4WTKRHQ, long localContentColor) {
        long m4161copywmQWz5c;
        IconButtonColors defaultOutlinedIconButtonColorsCached = $this$defaultOutlinedIconButtonColors_u2d4WTKRHQ.getDefaultOutlinedIconButtonColorsCached();
        if (defaultOutlinedIconButtonColorsCached != null) {
            return defaultOutlinedIconButtonColorsCached;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(localContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(localContentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(localContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(localContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(localContentColor) : 0.0f);
        IconButtonColors it = new IconButtonColors(m4198getTransparent0d7_KjU, localContentColor, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, null);
        $this$defaultOutlinedIconButtonColors_u2d4WTKRHQ.setDefaultOutlinedIconButtonColorsCached$material3_release(it);
        return it;
    }

    public final IconToggleButtonColors outlinedIconToggleButtonColors(Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        IconToggleButtonColors colors;
        $composer.startReplaceGroup(-779749183);
        ComposerKt.sourceInformation($composer, "C(outlinedIconToggleButtonColors)1001@47390L7,1002@47433L11:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-779749183, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButton.kt:1000)");
        }
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor = ((Color) consume).m4173unboximpl();
        IconToggleButtonColors colors2 = m2143defaultOutlinedIconToggleButtonColors4WTKRHQ$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6), contentColor);
        if (Color.m4164equalsimpl0(colors2.getContentColor(), contentColor)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return colors2;
        }
        m4161copywmQWz5c = Color.m4161copywmQWz5c(contentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor) : 0.0f);
        colors = colors2.m2158copytNS2XkQ((r26 & 1) != 0 ? colors2.containerColor : 0L, (r26 & 2) != 0 ? colors2.contentColor : contentColor, (r26 & 4) != 0 ? colors2.disabledContainerColor : 0L, (r26 & 8) != 0 ? colors2.disabledContentColor : m4161copywmQWz5c, (r26 & 16) != 0 ? colors2.checkedContainerColor : 0L, (r26 & 32) != 0 ? colors2.checkedContentColor : 0L);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return colors;
    }

    /* renamed from: outlinedIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m2151outlinedIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long contentColor3;
        long disabledContentColor2;
        long contentColor4;
        ComposerKt.sourceInformationMarkerStart($composer, 2130592709, "C(outlinedIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)1028@48650L7,1033@48932L38,1035@49025L11,1036@49106L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) consume).m4173unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            contentColor3 = contentColor2;
            contentColor4 = Color.m4161copywmQWz5c(contentColor3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(contentColor3) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(contentColor3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(contentColor3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(contentColor3) : 0.0f);
            disabledContentColor2 = contentColor4;
        } else {
            contentColor3 = contentColor2;
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.m1934contentColorForek8zF_U(checkedContainerColor2, $composer, ($changed >> 12) & 14) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2130592709, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButton.kt:1035)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localContentColor2);
        ComposerKt.sourceInformationMarkerEnd($composer);
        long contentColor5 = contentColor3;
        long contentColor6 = disabledContainerColor2;
        IconToggleButtonColors m2158copytNS2XkQ = m2143defaultOutlinedIconToggleButtonColors4WTKRHQ$material3_release(colorScheme, ((Color) consume2).m4173unboximpl()).m2158copytNS2XkQ(containerColor2, contentColor5, contentColor6, disabledContentColor2, checkedContainerColor2, checkedContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2158copytNS2XkQ;
    }

    /* renamed from: defaultOutlinedIconToggleButtonColors-4WTKRHQ$material3_release, reason: not valid java name */
    public final IconToggleButtonColors m2143defaultOutlinedIconToggleButtonColors4WTKRHQ$material3_release(ColorScheme $this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ, long localContentColor) {
        long m4161copywmQWz5c;
        IconToggleButtonColors defaultIconToggleButtonColorsCached = $this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ.getDefaultIconToggleButtonColorsCached();
        if (defaultIconToggleButtonColorsCached != null) {
            return defaultIconToggleButtonColorsCached;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(localContentColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(localContentColor) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(localContentColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(localContentColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(localContentColor) : 0.0f);
        IconToggleButtonColors it = new IconToggleButtonColors(m4198getTransparent0d7_KjU, localContentColor, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, ColorSchemeKt.fromToken($this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ, OutlinedIconButtonTokens.INSTANCE.getSelectedContainerColor()), ColorSchemeKt.m1933contentColorFor4WTKRHQ($this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ, ColorSchemeKt.fromToken($this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ, OutlinedIconButtonTokens.INSTANCE.getSelectedContainerColor())), null);
        $this$defaultOutlinedIconToggleButtonColors_u2d4WTKRHQ.setDefaultOutlinedIconToggleButtonColorsCached$material3_release(it);
        return it;
    }

    public final BorderStroke outlinedIconToggleButtonBorder(boolean enabled, boolean checked, Composer $composer, int $changed) {
        $composer.startReplaceGroup(1244729690);
        ComposerKt.sourceInformation($composer, "C(outlinedIconToggleButtonBorder)P(1)1082@51041L33:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1244729690, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonBorder (IconButton.kt:1078)");
        }
        if (checked) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return null;
        }
        BorderStroke outlinedIconButtonBorder = outlinedIconButtonBorder(enabled, $composer, ($changed & 14) | (($changed >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return outlinedIconButtonBorder;
    }

    public final BorderStroke outlinedIconButtonBorder(boolean enabled, Composer $composer, int $changed) {
        long color;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -511461558, "C(outlinedIconButtonBorder)1100@51643L108:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511461558, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonBorder (IconButton.kt:1091)");
        }
        if (enabled) {
            $composer.startReplaceGroup(1186104514);
            ComposerKt.sourceInformation($composer, "1094@51433L7");
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            color = ((Color) consume).m4173unboximpl();
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1186170420);
            ComposerKt.sourceInformation($composer, "1096@51496L7");
            ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer.consume(localContentColor2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            color = Color.m4161copywmQWz5c(r1, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r1) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r1) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r1) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(((Color) consume2).m4173unboximpl()) : 0.0f);
            $composer.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerStart($composer, 176816691, "CC(remember):IconButton.kt#9igjgp");
        boolean invalid$iv = $composer.changed(color);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = BorderStrokeKt.m254BorderStrokecXLIe8U(OutlinedIconButtonTokens.INSTANCE.m3359getUnselectedOutlineWidthD9Ej5fM(), color);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        BorderStroke borderStroke = (BorderStroke) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return borderStroke;
    }
}
