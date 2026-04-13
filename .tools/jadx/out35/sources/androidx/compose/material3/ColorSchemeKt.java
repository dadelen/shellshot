package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorDarkTokens;
import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.PaletteTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: ColorScheme.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001a\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a´\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001aø\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\b\u0010<\u001a\u00020\u0004H\u0000\u001a´\u0002\u0010=\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b>\u00102\u001aø\u0002\u0010=\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u000b2\b\b\u0002\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010&\u001a\u00020\u000b2\b\b\u0002\u0010'\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\b\b\u0002\u0010+\u001a\u00020\u000b2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000b2\b\b\u0002\u00100\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\b\b\u0002\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u00108\u001a\u00020\u000b2\b\b\u0002\u00109\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b?\u0010;\u001a&\u0010@\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020BH\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a\u001e\u0010\u000f\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a\u0019\u0010G\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\n\u001a\u00020\fH\u0001¢\u0006\u0002\u0010H\u001a\u001e\u0010I\u001a\u00020\u000b*\u00020\u00042\u0006\u0010A\u001a\u00020BH\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010K\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0018\u0010\n\u001a\u00020\u000b*\u00020\f8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"DisabledAlpha", "", "LocalColorScheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/ColorScheme;", "getLocalColorScheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalTonalElevationEnabled", "", "getLocalTonalElevationEnabled", "value", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getValue", "(Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;Landroidx/compose/runtime/Composer;I)J", "contentColorFor", "backgroundColor", "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "darkColorScheme", "primary", "onPrimary", "primaryContainer", "onPrimaryContainer", "inversePrimary", "secondary", "onSecondary", "secondaryContainer", "onSecondaryContainer", "tertiary", "onTertiary", "tertiaryContainer", "onTertiaryContainer", "background", "onBackground", "surface", "onSurface", "surfaceVariant", "onSurfaceVariant", "surfaceTint", "inverseSurface", "inverseOnSurface", "error", "onError", "errorContainer", "onErrorContainer", "outline", "outlineVariant", "scrim", "darkColorScheme-G1PFc-w", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "surfaceBright", "surfaceContainer", "surfaceContainerHigh", "surfaceContainerHighest", "surfaceContainerLow", "surfaceContainerLowest", "surfaceDim", "darkColorScheme-C-Xl9yA", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "expressiveLightColorScheme", "lightColorScheme", "lightColorScheme-G1PFc-w", "lightColorScheme-C-Xl9yA", "applyTonalElevation", "elevation", "Landroidx/compose/ui/unit/Dp;", "applyTonalElevation-RFCenO8", "(Landroidx/compose/material3/ColorScheme;JFLandroidx/compose/runtime/Composer;I)J", "contentColorFor-4WTKRHQ", "(Landroidx/compose/material3/ColorScheme;J)J", "fromToken", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;)J", "surfaceColorAtElevation", "surfaceColorAtElevation-3ABfNKs", "(Landroidx/compose/material3/ColorScheme;F)J", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ColorSchemeKt {
    public static final float DisabledAlpha = 0.38f;
    private static final ProvidableCompositionLocal<ColorScheme> LocalColorScheme = CompositionLocalKt.staticCompositionLocalOf(new Function0<ColorScheme>() { // from class: androidx.compose.material3.ColorSchemeKt$LocalColorScheme$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ColorScheme invoke() {
            return ColorSchemeKt.m1940lightColorSchemeCXl9yA$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -1, 15, null);
        }
    });
    private static final ProvidableCompositionLocal<Boolean> LocalTonalElevationEnabled = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.material3.ColorSchemeKt$LocalTonalElevationEnabled$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return true;
        }
    });

    /* compiled from: ColorScheme.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ColorSchemeKeyTokens.values().length];
            try {
                iArr[ColorSchemeKeyTokens.Background.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Error.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ColorSchemeKeyTokens.ErrorContainer.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseOnSurface.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InversePrimary.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseSurface.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnBackground.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnError.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnErrorContainer.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimary.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimaryContainer.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondary.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondaryContainer.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurface.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurfaceVariant.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceTint.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiary.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiaryContainer.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Outline.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OutlineVariant.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Primary.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[ColorSchemeKeyTokens.PrimaryContainer.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Scrim.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Secondary.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SecondaryContainer.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Surface.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceVariant.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceBright.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainer.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHigh.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHighest.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLow.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLowest.ordinal()] = 33;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceDim.ordinal()] = 34;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Tertiary.ordinal()] = 35;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[ColorSchemeKeyTokens.TertiaryContainer.ordinal()] = 36;
            } catch (NoSuchFieldError e36) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* renamed from: lightColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1940lightColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long m3114getPrimary0d7_KjU = (i & 1) != 0 ? ColorLightTokens.INSTANCE.m3114getPrimary0d7_KjU() : j;
        long j37 = m3114getPrimary0d7_KjU;
        return m1939lightColorSchemeCXl9yA(j37, (i & 2) != 0 ? ColorLightTokens.INSTANCE.m3098getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorLightTokens.INSTANCE.m3115getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorLightTokens.INSTANCE.m3099getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorLightTokens.INSTANCE.m3093getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorLightTokens.INSTANCE.m3119getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorLightTokens.INSTANCE.m3102getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorLightTokens.INSTANCE.m3120getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorLightTokens.INSTANCE.m3103getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorLightTokens.INSTANCE.m3133getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorLightTokens.INSTANCE.m3108getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorLightTokens.INSTANCE.m3134getTertiaryContainer0d7_KjU() : j12, (i & 4096) != 0 ? ColorLightTokens.INSTANCE.m3109getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorLightTokens.INSTANCE.m3089getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorLightTokens.INSTANCE.m3095getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorLightTokens.INSTANCE.m3123getSurface0d7_KjU() : j16, (i & 65536) != 0 ? ColorLightTokens.INSTANCE.m3106getOnSurface0d7_KjU() : j17, (i & 131072) != 0 ? ColorLightTokens.INSTANCE.m3132getSurfaceVariant0d7_KjU() : j18, (i & 262144) != 0 ? ColorLightTokens.INSTANCE.m3107getOnSurfaceVariant0d7_KjU() : j19, (i & 524288) != 0 ? j37 : j20, (i & 1048576) != 0 ? ColorLightTokens.INSTANCE.m3094getInverseSurface0d7_KjU() : j21, (i & 2097152) != 0 ? ColorLightTokens.INSTANCE.m3092getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorLightTokens.INSTANCE.m3090getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorLightTokens.INSTANCE.m3096getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorLightTokens.INSTANCE.m3091getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorLightTokens.INSTANCE.m3097getOnErrorContainer0d7_KjU() : j26, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? ColorLightTokens.INSTANCE.m3112getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorLightTokens.INSTANCE.m3113getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorLightTokens.INSTANCE.m3118getScrim0d7_KjU() : j29, (i & 536870912) != 0 ? ColorLightTokens.INSTANCE.m3124getSurfaceBright0d7_KjU() : j30, (i & BasicMeasure.EXACTLY) != 0 ? ColorLightTokens.INSTANCE.m3125getSurfaceContainer0d7_KjU() : j31, (i & Integer.MIN_VALUE) != 0 ? ColorLightTokens.INSTANCE.m3126getSurfaceContainerHigh0d7_KjU() : j32, (i2 & 1) != 0 ? ColorLightTokens.INSTANCE.m3127getSurfaceContainerHighest0d7_KjU() : j33, (i2 & 2) != 0 ? ColorLightTokens.INSTANCE.m3128getSurfaceContainerLow0d7_KjU() : j34, (i2 & 4) != 0 ? ColorLightTokens.INSTANCE.m3129getSurfaceContainerLowest0d7_KjU() : j35, (i2 & 8) != 0 ? ColorLightTokens.INSTANCE.m3130getSurfaceDim0d7_KjU() : j36);
    }

    /* renamed from: lightColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1939lightColorSchemeCXl9yA(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim, long surfaceBright, long surfaceContainer, long surfaceContainerHigh, long surfaceContainerHighest, long surfaceContainerLow, long surfaceContainerLowest, long surfaceDim) {
        return new ColorScheme(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, surfaceBright, surfaceDim, surfaceContainer, surfaceContainerHigh, surfaceContainerHighest, surfaceContainerLow, surfaceContainerLowest, null);
    }

    /* renamed from: lightColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1942lightColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, int i, Object obj) {
        long m3114getPrimary0d7_KjU = (i & 1) != 0 ? ColorLightTokens.INSTANCE.m3114getPrimary0d7_KjU() : j;
        long j30 = m3114getPrimary0d7_KjU;
        return m1941lightColorSchemeG1PFcw(j30, (i & 2) != 0 ? ColorLightTokens.INSTANCE.m3098getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorLightTokens.INSTANCE.m3115getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorLightTokens.INSTANCE.m3099getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorLightTokens.INSTANCE.m3093getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorLightTokens.INSTANCE.m3119getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorLightTokens.INSTANCE.m3102getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorLightTokens.INSTANCE.m3120getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorLightTokens.INSTANCE.m3103getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorLightTokens.INSTANCE.m3133getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorLightTokens.INSTANCE.m3108getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorLightTokens.INSTANCE.m3134getTertiaryContainer0d7_KjU() : j12, (i & 4096) != 0 ? ColorLightTokens.INSTANCE.m3109getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorLightTokens.INSTANCE.m3089getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorLightTokens.INSTANCE.m3095getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorLightTokens.INSTANCE.m3123getSurface0d7_KjU() : j16, (i & 65536) != 0 ? ColorLightTokens.INSTANCE.m3106getOnSurface0d7_KjU() : j17, (i & 131072) != 0 ? ColorLightTokens.INSTANCE.m3132getSurfaceVariant0d7_KjU() : j18, (i & 262144) != 0 ? ColorLightTokens.INSTANCE.m3107getOnSurfaceVariant0d7_KjU() : j19, (i & 524288) != 0 ? j30 : j20, (i & 1048576) != 0 ? ColorLightTokens.INSTANCE.m3094getInverseSurface0d7_KjU() : j21, (i & 2097152) != 0 ? ColorLightTokens.INSTANCE.m3092getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorLightTokens.INSTANCE.m3090getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorLightTokens.INSTANCE.m3096getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorLightTokens.INSTANCE.m3091getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorLightTokens.INSTANCE.m3097getOnErrorContainer0d7_KjU() : j26, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? ColorLightTokens.INSTANCE.m3112getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorLightTokens.INSTANCE.m3113getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorLightTokens.INSTANCE.m3118getScrim0d7_KjU() : j29);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional surface roles instead")
    /* renamed from: lightColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1941lightColorSchemeG1PFcw(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim) {
        return m1940lightColorSchemeCXl9yA$default(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* renamed from: darkColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1936darkColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long m3066getPrimary0d7_KjU = (i & 1) != 0 ? ColorDarkTokens.INSTANCE.m3066getPrimary0d7_KjU() : j;
        long j37 = m3066getPrimary0d7_KjU;
        return m1935darkColorSchemeCXl9yA(j37, (i & 2) != 0 ? ColorDarkTokens.INSTANCE.m3050getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorDarkTokens.INSTANCE.m3067getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorDarkTokens.INSTANCE.m3051getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorDarkTokens.INSTANCE.m3045getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorDarkTokens.INSTANCE.m3071getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorDarkTokens.INSTANCE.m3054getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorDarkTokens.INSTANCE.m3072getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorDarkTokens.INSTANCE.m3055getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorDarkTokens.INSTANCE.m3085getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorDarkTokens.INSTANCE.m3060getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorDarkTokens.INSTANCE.m3086getTertiaryContainer0d7_KjU() : j12, (i & 4096) != 0 ? ColorDarkTokens.INSTANCE.m3061getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorDarkTokens.INSTANCE.m3041getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorDarkTokens.INSTANCE.m3047getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorDarkTokens.INSTANCE.m3075getSurface0d7_KjU() : j16, (i & 65536) != 0 ? ColorDarkTokens.INSTANCE.m3058getOnSurface0d7_KjU() : j17, (i & 131072) != 0 ? ColorDarkTokens.INSTANCE.m3084getSurfaceVariant0d7_KjU() : j18, (i & 262144) != 0 ? ColorDarkTokens.INSTANCE.m3059getOnSurfaceVariant0d7_KjU() : j19, (i & 524288) != 0 ? j37 : j20, (i & 1048576) != 0 ? ColorDarkTokens.INSTANCE.m3046getInverseSurface0d7_KjU() : j21, (i & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m3044getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m3042getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m3048getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m3043getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m3049getOnErrorContainer0d7_KjU() : j26, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? ColorDarkTokens.INSTANCE.m3064getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorDarkTokens.INSTANCE.m3065getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorDarkTokens.INSTANCE.m3070getScrim0d7_KjU() : j29, (i & 536870912) != 0 ? ColorDarkTokens.INSTANCE.m3076getSurfaceBright0d7_KjU() : j30, (i & BasicMeasure.EXACTLY) != 0 ? ColorDarkTokens.INSTANCE.m3077getSurfaceContainer0d7_KjU() : j31, (i & Integer.MIN_VALUE) != 0 ? ColorDarkTokens.INSTANCE.m3078getSurfaceContainerHigh0d7_KjU() : j32, (i2 & 1) != 0 ? ColorDarkTokens.INSTANCE.m3079getSurfaceContainerHighest0d7_KjU() : j33, (i2 & 2) != 0 ? ColorDarkTokens.INSTANCE.m3080getSurfaceContainerLow0d7_KjU() : j34, (i2 & 4) != 0 ? ColorDarkTokens.INSTANCE.m3081getSurfaceContainerLowest0d7_KjU() : j35, (i2 & 8) != 0 ? ColorDarkTokens.INSTANCE.m3082getSurfaceDim0d7_KjU() : j36);
    }

    /* renamed from: darkColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m1935darkColorSchemeCXl9yA(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim, long surfaceBright, long surfaceContainer, long surfaceContainerHigh, long surfaceContainerHighest, long surfaceContainerLow, long surfaceContainerLowest, long surfaceDim) {
        return new ColorScheme(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, surfaceBright, surfaceDim, surfaceContainer, surfaceContainerHigh, surfaceContainerHighest, surfaceContainerLow, surfaceContainerLowest, null);
    }

    /* renamed from: darkColorScheme-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m1938darkColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, int i, Object obj) {
        long m3066getPrimary0d7_KjU = (i & 1) != 0 ? ColorDarkTokens.INSTANCE.m3066getPrimary0d7_KjU() : j;
        long j30 = m3066getPrimary0d7_KjU;
        return m1937darkColorSchemeG1PFcw(j30, (i & 2) != 0 ? ColorDarkTokens.INSTANCE.m3050getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorDarkTokens.INSTANCE.m3067getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorDarkTokens.INSTANCE.m3051getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorDarkTokens.INSTANCE.m3045getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorDarkTokens.INSTANCE.m3071getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorDarkTokens.INSTANCE.m3054getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorDarkTokens.INSTANCE.m3072getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorDarkTokens.INSTANCE.m3055getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorDarkTokens.INSTANCE.m3085getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorDarkTokens.INSTANCE.m3060getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorDarkTokens.INSTANCE.m3086getTertiaryContainer0d7_KjU() : j12, (i & 4096) != 0 ? ColorDarkTokens.INSTANCE.m3061getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorDarkTokens.INSTANCE.m3041getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorDarkTokens.INSTANCE.m3047getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorDarkTokens.INSTANCE.m3075getSurface0d7_KjU() : j16, (i & 65536) != 0 ? ColorDarkTokens.INSTANCE.m3058getOnSurface0d7_KjU() : j17, (i & 131072) != 0 ? ColorDarkTokens.INSTANCE.m3084getSurfaceVariant0d7_KjU() : j18, (i & 262144) != 0 ? ColorDarkTokens.INSTANCE.m3059getOnSurfaceVariant0d7_KjU() : j19, (i & 524288) != 0 ? j30 : j20, (i & 1048576) != 0 ? ColorDarkTokens.INSTANCE.m3046getInverseSurface0d7_KjU() : j21, (i & 2097152) != 0 ? ColorDarkTokens.INSTANCE.m3044getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m3042getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m3048getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m3043getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m3049getOnErrorContainer0d7_KjU() : j26, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? ColorDarkTokens.INSTANCE.m3064getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorDarkTokens.INSTANCE.m3065getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorDarkTokens.INSTANCE.m3070getScrim0d7_KjU() : j29);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional surface roles instead")
    /* renamed from: darkColorScheme-G1PFc-w, reason: not valid java name */
    public static final /* synthetic */ ColorScheme m1937darkColorSchemeG1PFcw(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim) {
        return m1936darkColorSchemeCXl9yA$default(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, 15, null);
    }

    /* renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m1933contentColorFor4WTKRHQ(ColorScheme $this$contentColorFor_u2d4WTKRHQ, long backgroundColor) {
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getPrimary())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnPrimary();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSecondary())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnSecondary();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getTertiary())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnTertiary();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getBackground())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnBackground();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getError())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnError();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getPrimaryContainer())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnPrimaryContainer();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSecondaryContainer())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnSecondaryContainer();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getTertiaryContainer())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnTertiaryContainer();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getErrorContainer())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnErrorContainer();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getInverseSurface())) {
            return $this$contentColorFor_u2d4WTKRHQ.getInverseOnSurface();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurface())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnSurface();
        }
        if (Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceVariant())) {
            return $this$contentColorFor_u2d4WTKRHQ.getOnSurfaceVariant();
        }
        if (!Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceBright()) && !Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceContainer()) && !Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceContainerHigh()) && !Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceContainerHighest()) && !Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceContainerLow()) && !Color.m4164equalsimpl0(backgroundColor, $this$contentColorFor_u2d4WTKRHQ.getSurfaceContainerLowest())) {
            return Color.INSTANCE.m4199getUnspecified0d7_KjU();
        }
        return $this$contentColorFor_u2d4WTKRHQ.getOnSurface();
    }

    /* renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m1934contentColorForek8zF_U(long backgroundColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 509589638, "C(contentColorFor)P(0:c#ui.graphics.Color):ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(509589638, $changed, -1, "androidx.compose.material3.contentColorFor (ColorScheme.kt:878)");
        }
        $composer.startReplaceGroup(-1680936624);
        ComposerKt.sourceInformation($composer, "*878@39525L11,879@39609L7");
        long $this$takeOrElse_u2dDxMtmZc$iv = m1933contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme($composer, 6), backgroundColor);
        if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $this$takeOrElse_u2dDxMtmZc$iv = ((Color) consume).m4173unboximpl();
        }
        $composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return $this$takeOrElse_u2dDxMtmZc$iv;
    }

    /* renamed from: applyTonalElevation-RFCenO8, reason: not valid java name */
    public static final long m1932applyTonalElevationRFCenO8(ColorScheme $this$applyTonalElevation_u2dRFCenO8, long backgroundColor, float elevation, Composer $composer, int $changed) {
        long j;
        ComposerKt.sourceInformationMarkerStart($composer, -1610977682, "C(applyTonalElevation)P(0:c#ui.graphics.Color,1:c#ui.unit.Dp)896@40465L7:ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1610977682, $changed, -1, "androidx.compose.material3.applyTonalElevation (ColorScheme.kt:895)");
        }
        ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalTonalElevationEnabled;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean tonalElevationEnabled = ((Boolean) consume).booleanValue();
        if (Color.m4164equalsimpl0(backgroundColor, $this$applyTonalElevation_u2dRFCenO8.getSurface()) && tonalElevationEnabled) {
            j = m1943surfaceColorAtElevation3ABfNKs($this$applyTonalElevation_u2dRFCenO8, elevation);
        } else {
            j = backgroundColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return j;
    }

    /* renamed from: surfaceColorAtElevation-3ABfNKs, reason: not valid java name */
    public static final long m1943surfaceColorAtElevation3ABfNKs(ColorScheme $this$surfaceColorAtElevation_u2d3ABfNKs, float elevation) {
        long m4161copywmQWz5c;
        if (Dp.m6631equalsimpl0(elevation, Dp.m6626constructorimpl(0))) {
            return $this$surfaceColorAtElevation_u2d3ABfNKs.getSurface();
        }
        float alpha = ((((float) Math.log(1.0f + elevation)) * 4.5f) + 2.0f) / 100.0f;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r2) : alpha, (r12 & 2) != 0 ? Color.m4169getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($this$surfaceColorAtElevation_u2d3ABfNKs.getSurfaceTint()) : 0.0f);
        return ColorKt.m4208compositeOverOWjLjI(m4161copywmQWz5c, $this$surfaceColorAtElevation_u2d3ABfNKs.getSurface());
    }

    public static final ColorScheme expressiveLightColorScheme() {
        return m1940lightColorSchemeCXl9yA$default(0L, 0L, 0L, PaletteTokens.INSTANCE.m3425getPrimary300d7_KjU(), 0L, 0L, 0L, 0L, PaletteTokens.INSTANCE.m3438getSecondary300d7_KjU(), 0L, 0L, 0L, PaletteTokens.INSTANCE.m3438getSecondary300d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -4361, 15, null);
    }

    public static final long fromToken(ColorScheme $this$fromToken, ColorSchemeKeyTokens value) {
        switch (WhenMappings.$EnumSwitchMapping$0[value.ordinal()]) {
            case 1:
                return $this$fromToken.getBackground();
            case 2:
                return $this$fromToken.getError();
            case 3:
                return $this$fromToken.getErrorContainer();
            case 4:
                return $this$fromToken.getInverseOnSurface();
            case 5:
                return $this$fromToken.getInversePrimary();
            case 6:
                return $this$fromToken.getInverseSurface();
            case 7:
                return $this$fromToken.getOnBackground();
            case 8:
                return $this$fromToken.getOnError();
            case 9:
                return $this$fromToken.getOnErrorContainer();
            case 10:
                return $this$fromToken.getOnPrimary();
            case 11:
                return $this$fromToken.getOnPrimaryContainer();
            case 12:
                return $this$fromToken.getOnSecondary();
            case 13:
                return $this$fromToken.getOnSecondaryContainer();
            case 14:
                return $this$fromToken.getOnSurface();
            case 15:
                return $this$fromToken.getOnSurfaceVariant();
            case 16:
                return $this$fromToken.getSurfaceTint();
            case 17:
                return $this$fromToken.getOnTertiary();
            case 18:
                return $this$fromToken.getOnTertiaryContainer();
            case 19:
                return $this$fromToken.getOutline();
            case 20:
                return $this$fromToken.getOutlineVariant();
            case 21:
                return $this$fromToken.getPrimary();
            case 22:
                return $this$fromToken.getPrimaryContainer();
            case 23:
                return $this$fromToken.getScrim();
            case 24:
                return $this$fromToken.getSecondary();
            case 25:
                return $this$fromToken.getSecondaryContainer();
            case 26:
                return $this$fromToken.getSurface();
            case 27:
                return $this$fromToken.getSurfaceVariant();
            case 28:
                return $this$fromToken.getSurfaceBright();
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                return $this$fromToken.getSurfaceContainer();
            case 30:
                return $this$fromToken.getSurfaceContainerHigh();
            case 31:
                return $this$fromToken.getSurfaceContainerHighest();
            case 32:
                return $this$fromToken.getSurfaceContainerLow();
            case 33:
                return $this$fromToken.getSurfaceContainerLowest();
            case 34:
                return $this$fromToken.getSurfaceDim();
            case 35:
                return $this$fromToken.getTertiary();
            case 36:
                return $this$fromToken.getTertiaryContainer();
            default:
                return Color.INSTANCE.m4199getUnspecified0d7_KjU();
        }
    }

    public static final ProvidableCompositionLocal<ColorScheme> getLocalColorScheme() {
        return LocalColorScheme;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalTonalElevationEnabled() {
        return LocalTonalElevationEnabled;
    }

    public static final long getValue(ColorSchemeKeyTokens $this$value, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -810780884, "C1009@45379L11:ColorScheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-810780884, $changed, -1, "androidx.compose.material3.<get-value> (ColorScheme.kt:1009)");
        }
        long fromToken = fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $this$value);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fromToken;
    }
}
