package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ3\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010!J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J3\u0010\u001e\u001a\u00020\u00122\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00100J3\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00103J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u00104J\r\u0010\u0017\u001a\u00020\rH\u0007¢\u0006\u0002\u00105Jv\u0010\u0017\u001a\u00020\r2\b\b\u0002\u00106\u001a\u00020)2\b\b\u0002\u00107\u001a\u00020)2\b\b\u0002\u00108\u001a\u00020)2\b\b\u0002\u00109\u001a\u00020)2\b\b\u0002\u0010:\u001a\u00020)2\b\b\u0002\u0010;\u001a\u00020)2\b\b\u0002\u0010<\u001a\u00020)2\b\b\u0002\u0010=\u001a\u00020)2\b\b\u0002\u0010>\u001a\u00020)2\b\b\u0002\u0010?\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ2\u0010\"\u001a\u00020\u00122\u0006\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020)H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010GJÄ\u0001\u0010H\u001a\u00020\u0012*\u00020$2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\u0006\u00109\u001a\u00020)2\u0006\u00107\u001a\u00020)2\u0006\u0010:\u001a\u00020)2\u0006\u00108\u001a\u00020)2\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u001f\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2#\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\u0006\u0010Q\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ>\u0010T\u001a\u00020\u0012*\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020U2\u0006\u0010E\u001a\u00020)2\u0006\u0010V\u001a\u00020L2\u0006\u0010W\u001a\u00020LH\u0002ø\u0001\u0000¢\u0006\u0004\bX\u0010YR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\r*\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Z"}, d2 = {"Landroidx/compose/material3/SliderDefaults;", "", "()V", "TickSize", "Landroidx/compose/ui/unit/Dp;", "getTickSize-D9Ej5fM", "()F", "F", "TrackStopIndicatorSize", "getTrackStopIndicatorSize-D9Ej5fM", "trackPath", "Landroidx/compose/ui/graphics/Path;", "defaultSliderColors", "Landroidx/compose/material3/SliderColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultSliderColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SliderColors;", "Thumb", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "enabled", "", "thumbSize", "Landroidx/compose/ui/unit/DpSize;", "Thumb-9LiSoMs", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "Track", "rangeSliderState", "Landroidx/compose/material3/RangeSliderState;", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "drawTick", "Lkotlin/Function3;", "Landroidx/compose/ui/graphics/Color;", "thumbTrackGapSize", "trackInsideCornerSize", "Track-4EFweAY", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "sliderPositions", "Landroidx/compose/material3/SliderPositions;", "(Landroidx/compose/material3/SliderPositions;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "sliderState", "Landroidx/compose/material3/SliderState;", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SliderColors;", "thumbColor", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SliderColors;", "drawScope", "offset", "size", "color", "drawStopIndicator-x3O1jOs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJ)V", "drawTrack", "tickFractions", "", "activeRangeStart", "", "activeRangeEnd", "height", "startThumbWidth", "endThumbWidth", "isRangeSlider", "drawTrack-ngJ0SCU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;[FFFJJJJFFFFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Z)V", "drawTrackPath", "Landroidx/compose/ui/geometry/Size;", "startCornerRadius", "endCornerRadius", "drawTrackPath-Cx2C_VA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJJFF)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    private static final float TrackStopIndicatorSize = SliderTokens.INSTANCE.m3502getStopIndicatorSizeD9Ej5fM();
    private static final float TickSize = SliderTokens.INSTANCE.m3502getStopIndicatorSizeD9Ej5fM();
    private static final Path trackPath = AndroidPath_androidKt.Path();

    private SliderDefaults() {
    }

    public final SliderColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1376295968, "C(colors)845@36907L11:Slider.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1376295968, $changed, -1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:845)");
        }
        SliderColors defaultSliderColors$material3_release = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSliderColors$material3_release;
    }

    /* renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m2499colorsq0g_0yA(long thumbColor, long activeTrackColor, long activeTickColor, long inactiveTrackColor, long inactiveTickColor, long disabledThumbColor, long disabledActiveTrackColor, long disabledActiveTickColor, long disabledInactiveTrackColor, long disabledInactiveTickColor, Composer $composer, int $changed, int $changed1, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 885588574, "C(colors)P(9:c#ui.graphics.Color,1:c#ui.graphics.Color,0:c#ui.graphics.Color,8:c#ui.graphics.Color,7:c#ui.graphics.Color,6:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.graphics.Color,4:c#ui.graphics.Color)887@39230L11:Slider.kt#uh7d8r");
        long thumbColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : thumbColor;
        long activeTrackColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : activeTrackColor;
        long activeTickColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : activeTickColor;
        long inactiveTrackColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : inactiveTrackColor;
        long inactiveTickColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : inactiveTickColor;
        long disabledThumbColor2 = (i & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledThumbColor;
        long disabledActiveTrackColor2 = (i & 64) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledActiveTrackColor;
        long disabledActiveTickColor2 = (i & 128) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledActiveTickColor;
        long disabledInactiveTrackColor2 = (i & 256) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledInactiveTrackColor;
        long disabledInactiveTickColor2 = (i & 512) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledInactiveTickColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885588574, $changed, $changed1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:887)");
        }
        SliderColors m2477copyK518z4 = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2477copyK518z4(thumbColor2, activeTrackColor2, activeTickColor2, inactiveTrackColor2, inactiveTickColor2, disabledThumbColor2, disabledActiveTrackColor2, disabledActiveTickColor2, disabledInactiveTrackColor2, disabledInactiveTickColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2477copyK518z4;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    public final androidx.compose.material3.SliderColors getDefaultSliderColors$material3_release(androidx.compose.material3.ColorScheme r34) {
        /*
            r33 = this;
            r0 = r34
            androidx.compose.material3.SliderColors r1 = r0.getDefaultSliderColorsCached()
            if (r1 != 0) goto Lf3
            androidx.compose.material3.SliderColors r2 = new androidx.compose.material3.SliderColors
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getHandleColor()
            long r3 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getActiveTrackColor()
            long r5 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getInactiveTrackColor()
            long r7 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getInactiveTrackColor()
            long r9 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getActiveTrackColor()
            long r11 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getDisabledHandleColor()
            long r13 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            float r15 = r1.getDisabledHandleOpacity()
            r19 = 14
            r20 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            long r13 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r13, r15, r16, r17, r18, r19, r20)
            r15 = r2
            long r1 = r0.getSurface()
            long r13 = androidx.compose.ui.graphics.ColorKt.m4208compositeOverOWjLjI(r13, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r1.getDisabledActiveTrackColor()
            long r16 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            float r18 = r1.getDisabledActiveTrackOpacity()
            r22 = 14
            r23 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            long r1 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r16, r18, r19, r20, r21, r22, r23)
            androidx.compose.material3.tokens.SliderTokens r16 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            r17 = r1
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r16.getDisabledInactiveTrackColor()
            long r19 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            float r21 = r1.getDisabledInactiveTrackOpacity()
            r25 = 14
            r26 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            long r1 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r19, r21, r22, r23, r24, r25, r26)
            androidx.compose.material3.tokens.SliderTokens r16 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            r19 = r1
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r16.getDisabledInactiveTrackColor()
            long r21 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            float r23 = r1.getDisabledInactiveTrackOpacity()
            r27 = 14
            r28 = 0
            r25 = 0
            r26 = 0
            long r1 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r21, r23, r24, r25, r26, r27, r28)
            androidx.compose.material3.tokens.SliderTokens r16 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            r21 = r1
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r1 = r16.getDisabledActiveTrackColor()
            long r23 = androidx.compose.material3.ColorSchemeKt.fromToken(r0, r1)
            androidx.compose.material3.tokens.SliderTokens r1 = androidx.compose.material3.tokens.SliderTokens.INSTANCE
            float r25 = r1.getDisabledActiveTrackOpacity()
            r29 = 14
            r30 = 0
            r27 = 0
            r28 = 0
            long r1 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r23, r25, r26, r27, r28, r29, r30)
            r23 = 0
            r31 = r1
            r2 = r15
            r15 = r17
            r17 = r19
            r19 = r21
            r21 = r31
            r2.<init>(r3, r5, r7, r9, r11, r13, r15, r17, r19, r21, r23)
            r15 = r2
            r1 = r15
            r2 = 0
            r0.setDefaultSliderColorsCached$material3_release(r1)
        Lf3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.getDefaultSliderColors$material3_release(androidx.compose.material3.ColorScheme):androidx.compose.material3.SliderColors");
    }

    /* renamed from: Thumb-9LiSoMs, reason: not valid java name */
    public final void m2496Thumb9LiSoMs(final MutableInteractionSource interactionSource, Modifier modifier, SliderColors colors, boolean enabled, long thumbSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors colors2;
        boolean enabled2;
        long j;
        long thumbSize2;
        long j2;
        Object value$iv;
        SliderDefaults$Thumb$1$1 value$iv2;
        long size;
        final Modifier modifier3;
        final long thumbSize3;
        final SliderColors colors3;
        final boolean enabled3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-290277409);
        ComposerKt.sourceInformation($composer2, "C(Thumb)P(2,3!,4:c#ui.unit.DpSize)947@42562L8,951@42678L46,952@42767L658,952@42733L692,975@43824L5,971@43620L220:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            colors2 = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            j = thumbSize;
        } else if (($changed & 24576) == 0) {
            j = thumbSize;
            $dirty |= $composer2.changed(j) ? 16384 : 8192;
        } else {
            j = thumbSize;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(this) ? 131072 : 65536;
        }
        if (($dirty & 74899) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            thumbSize3 = j;
            colors3 = colors2;
            enabled3 = enabled2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    SliderColors colors4 = colors($composer2, ($dirty >> 15) & 14);
                    $dirty &= -897;
                    colors2 = colors4;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    j2 = SliderKt.ThumbSize;
                    thumbSize2 = j2;
                } else {
                    thumbSize2 = j;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                thumbSize2 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-290277409, $dirty, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:950)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1068636116, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            SnapshotStateList interactions = (SnapshotStateList) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1068632656, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SliderDefaults$Thumb$1$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty & 14);
            if (interactions.isEmpty()) {
                size = thumbSize2;
            } else {
                float arg0$iv = DpSize.m6724getWidthD9Ej5fM(thumbSize2);
                size = DpSize.m6717copyDwJknco$default(thumbSize2, Dp.m6626constructorimpl(arg0$iv / 2), 0.0f, 2, null);
            }
            SpacerKt.Spacer(BackgroundKt.m226backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m718size6HolHcs(modifier2, size), interactionSource, false, 2, null), colors2.m2488thumbColorvNxB06k$material3_release(enabled2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), $composer2, 6)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            thumbSize3 = thumbSize2;
            colors3 = colors2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Thumb$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    SliderDefaults.this.m2496Thumb9LiSoMs(interactionSource, modifier3, colors3, enabled3, thumbSize3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x018b  */
    @kotlin.Deprecated(message = "Use version that supports slider state")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Track(final androidx.compose.material3.SliderPositions r26, androidx.compose.ui.Modifier r27, androidx.compose.material3.SliderColors r28, boolean r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.Track(androidx.compose.material3.SliderPositions, androidx.compose.ui.Modifier, androidx.compose.material3.SliderColors, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacySliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(sliderState, modifier, enabled, colors, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final SliderState sliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        SliderColors colors2;
        int $dirty;
        Modifier modifier3;
        final SliderColors colors3;
        boolean enabled2;
        float f;
        float f2;
        final boolean enabled3;
        final Modifier modifier4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(593554206);
        ComposerKt.sourceInformation($composer2, "C(Track)P(3,2)1079@48295L8,1082@48353L213:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(sliderState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                if ($composer2.changed(sliderColors)) {
                    i2 = 256;
                    $dirty2 |= i2;
                }
            } else {
                sliderColors = colors;
            }
            i2 = 128;
            $dirty2 |= i2;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = sliderColors;
            enabled3 = z;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                } else {
                    colors2 = sliderColors;
                }
                if (i4 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled2 = true;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled2 = z;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                colors3 = sliderColors;
                enabled2 = z;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(593554206, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1081)");
            }
            f = SliderKt.ThumbTrackGapSize;
            f2 = SliderKt.TrackInsideCornerSize;
            m2498Track4EFweAY(sliderState, modifier3, enabled2, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled3 = enabled2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    SliderDefaults.this.Track(sliderState, modifier4, colors3, enabled3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0299  */
    /* renamed from: Track-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2498Track4EFweAY(final androidx.compose.material3.SliderState r28, androidx.compose.ui.Modifier r29, boolean r30, androidx.compose.material3.SliderColors r31, kotlin.jvm.functions.Function2<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r32, kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, ? super androidx.compose.ui.graphics.Color, kotlin.Unit> r33, float r34, float r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m2498Track4EFweAY(androidx.compose.material3.SliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacyRangeSliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(rangeSliderState, modifier, colors, enabled, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final RangeSliderState rangeSliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        SliderColors colors2;
        int $dirty;
        Modifier modifier3;
        final SliderColors colors3;
        boolean enabled2;
        float f;
        float f2;
        final boolean enabled3;
        final Modifier modifier4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-1617869097);
        ComposerKt.sourceInformation($composer2, "C(Track)P(3,2)1187@52807L8,1190@52865L218:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(rangeSliderState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                if ($composer2.changed(sliderColors)) {
                    i2 = 256;
                    $dirty2 |= i2;
                }
            } else {
                sliderColors = colors;
            }
            i2 = 128;
            $dirty2 |= i2;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = sliderColors;
            enabled3 = z;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                } else {
                    colors2 = sliderColors;
                }
                if (i4 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled2 = true;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    colors3 = colors2;
                    enabled2 = z;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                colors3 = sliderColors;
                enabled2 = z;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617869097, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1189)");
            }
            f = SliderKt.ThumbTrackGapSize;
            f2 = SliderKt.TrackInsideCornerSize;
            m2497Track4EFweAY(rangeSliderState, modifier3, enabled2, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled3 = enabled2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    SliderDefaults.this.Track(rangeSliderState, modifier4, colors3, enabled3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0299  */
    /* renamed from: Track-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2497Track4EFweAY(final androidx.compose.material3.RangeSliderState r28, androidx.compose.ui.Modifier r29, boolean r30, androidx.compose.material3.SliderColors r31, kotlin.jvm.functions.Function2<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r32, kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, ? super androidx.compose.ui.graphics.Color, kotlin.Unit> r33, float r34, float r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m2497Track4EFweAY(androidx.compose.material3.RangeSliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x01f8, code lost:
    
        if (r7 == (r46.length - 1)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x023f, code lost:
    
        if (r0.contains(java.lang.Float.valueOf(androidx.compose.ui.geometry.Offset.m3922getXimpl(r2))) == false) goto L54;
     */
    /* renamed from: drawTrack-ngJ0SCU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2494drawTrackngJ0SCU(androidx.compose.ui.graphics.drawscope.DrawScope r45, float[] r46, float r47, float r48, long r49, long r51, long r53, long r55, float r57, float r58, float r59, float r60, float r61, kotlin.jvm.functions.Function2<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r62, kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, ? super androidx.compose.ui.graphics.Color, kotlin.Unit> r63, boolean r64) {
        /*
            Method dump skipped, instructions count: 641
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m2494drawTrackngJ0SCU(androidx.compose.ui.graphics.drawscope.DrawScope, float[], float, float, long, long, long, long, float, float, float, float, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, boolean):void");
    }

    /* renamed from: drawTrackPath-Cx2C_VA, reason: not valid java name */
    private final void m2495drawTrackPathCx2C_VA(DrawScope $this$drawTrackPath_u2dCx2C_VA, long offset, long size, long color, float startCornerRadius, float endCornerRadius) {
        long startCorner = CornerRadiusKt.CornerRadius(startCornerRadius, startCornerRadius);
        long endCorner = CornerRadiusKt.CornerRadius(endCornerRadius, endCornerRadius);
        RoundRect track = RoundRectKt.m3974RoundRectZAM2FJo(RectKt.m3962Recttz77jQw(OffsetKt.Offset(Offset.m3922getXimpl(offset), 0.0f), androidx.compose.ui.geometry.SizeKt.Size(Size.m3991getWidthimpl(size), Size.m3988getHeightimpl(size))), startCorner, endCorner, endCorner, startCorner);
        Path.addRoundRect$default(trackPath, track, null, 2, null);
        DrawScope.m4703drawPathLG529CI$default($this$drawTrackPath_u2dCx2C_VA, trackPath, color, 0.0f, null, null, 0, 60, null);
        trackPath.rewind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawStopIndicator-x3O1jOs, reason: not valid java name */
    public final void m2493drawStopIndicatorx3O1jOs(DrawScope drawScope, long offset, float size, long color) {
        DrawScope.m4694drawCircleVaOC9Bg$default(drawScope, color, drawScope.mo364toPx0680j_4(size) / 2.0f, offset, 0.0f, null, null, 0, 120, null);
    }

    /* renamed from: getTrackStopIndicatorSize-D9Ej5fM, reason: not valid java name */
    public final float m2501getTrackStopIndicatorSizeD9Ej5fM() {
        return TrackStopIndicatorSize;
    }

    /* renamed from: getTickSize-D9Ej5fM, reason: not valid java name */
    public final float m2500getTickSizeD9Ej5fM() {
        return TickSize;
    }
}
