package com.example.shellshot.ui.components;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.BlurKt;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpSize;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.ui.theme.DesignTokensKt;
import com.example.shellshot.ui.theme.ShellShotColorTokens;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: GlassComponents.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001aI\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a?\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0017\u001a7\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001a7\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001a)\u0010 \u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0004\b#\u0010$\u001a3\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00142\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\"H\u0007¢\u0006\u0004\b)\u0010*\u001a\u0017\u0010+\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010,\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00142\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010-\u001aq\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u001d2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u001d2\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\"2\b\b\u0002\u00109\u001a\u00020\"H\u0007¢\u0006\u0004\b:\u0010;\u001aq\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u001d2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u001d2\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\"2\b\b\u0002\u00109\u001a\u00020\"H\u0007¢\u0006\u0004\b?\u0010;\u001aK\u0010@\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u001d2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u001d2\u0006\u00106\u001a\u0002072\u0006\u00104\u001a\u000205H\u0003¢\u0006\u0004\bA\u0010B\u001aK\u0010C\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u001d2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u001d2\u0006\u00106\u001a\u0002072\u0006\u00104\u001a\u000205H\u0003¢\u0006\u0004\bD\u0010B\u001aK\u0010E\u001a\u00020\u00012\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010I\u001a\u00020J2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020LH\u0007¢\u0006\u0002\u0010M\u001a5\u0010N\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020P2\u0006\u0010R\u001a\u00020PH\u0003¢\u0006\u0002\u0010S\u001a,\u0010V\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020X0W2\u0006\u0010Y\u001a\u00020P2\u0006\u0010Z\u001a\u00020P2\u0006\u0010[\u001a\u00020PH\u0002\u001a#\u0010\\\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\"0GH\u0003¢\u0006\u0002\u0010^\"\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006_²\u0006\n\u0010/\u001a\u00020\u001dX\u008a\u008e\u0002²\u0006\n\u0010`\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010c\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010d\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010e\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010f\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010g\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010`\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010d\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010g\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010R\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010h\u001a\u00020PX\u008a\u0084\u0002²\u0006\n\u0010i\u001a\u00020jX\u008a\u0084\u0002²\u0006\n\u0010k\u001a\u00020\"X\u008a\u0084\u0002²\u0006\n\u0010l\u001a\u00020jX\u008a\u0084\u0002"}, d2 = {"LiquidGlassBackdrop", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "GlassPanel", "shape", "Landroidx/compose/ui/graphics/Shape;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "GlassInsetBlock", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScreenHeader", "eyebrow", "", "title", "description", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "GlassActionButton", "text", "onClick", "Lkotlin/Function0;", "emphasized", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "GlassChipButton", "StatusPill", "accent", "Landroidx/compose/ui/graphics/Color;", "StatusPill-3IgeMak", "(Ljava/lang/String;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DetailRow", "label", "value", "valueColor", "DetailRow-ww6aTOc", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "GlassDivider", "SectionLabel", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "LegacyLiquidGlassSwitch", "checked", "onCheckedChange", "enabled", "quality", "Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchQuality;", "spec", "Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;", "size", "Landroidx/compose/ui/unit/DpSize;", "activeTrackColor", "inactiveTrackColor", "LegacyLiquidGlassSwitch-d3XrlC8", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchQuality;Lcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;JJJLandroidx/compose/runtime/Composer;II)V", "LegacyStatefulLiquidGlassSwitch", "initialChecked", "onChange", "LegacyStatefulLiquidGlassSwitch-d3XrlC8", "LegacyHighFidelityLiquidGlassSwitch", "LegacyHighFidelityLiquidGlassSwitch-9LiSoMs", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZJLcom/example/shellshot/ui/components/LegacyLiquidGlassSwitchSpec;Landroidx/compose/runtime/Composer;I)V", "LegacyHighPerformanceLiquidGlassSwitch", "LegacyHighPerformanceLiquidGlassSwitch-9LiSoMs", "LegacyLiquidGlassBottomBar", "items", "", "Lcom/example/shellshot/ui/components/GlassBottomBarItem;", "selectedIndex", "", "onSelect", "Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lcom/example/shellshot/ui/components/LiquidGlassBottomBarSpec;Landroidx/compose/runtime/Composer;II)V", "GlassSpecularLayer", "highlightAngleDegrees", "", "highlightAlpha", "breathe", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;FFFLandroidx/compose/runtime/Composer;I)V", "LegacyLiquidGlassSwitchMotionEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "gradientLineForAngle", "Lkotlin/Pair;", "Landroidx/compose/ui/geometry/Offset;", "angleDegrees", "width", "height", "GlassOrb", "colors", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "app_debug", "pressed", NotificationCompat.CATEGORY_PROGRESS, "pressScale", "shellTopColor", "shellBottomColor", "liquidStartColor", "liquidEndColor", "thumbBorderColor", "shimmer", "indicatorOffset", "Landroidx/compose/ui/unit/Dp;", "tint", "itemLift"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class GlassComponentsKt {
    private static final CubicBezierEasing LegacyLiquidGlassSwitchMotionEasing = new CubicBezierEasing(0.22f, 1.0f, 0.36f, 1.0f);

    /* compiled from: GlassComponents.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LegacyLiquidGlassSwitchQuality.values().length];
            try {
                iArr[LegacyLiquidGlassSwitchQuality.HighFidelity.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LegacyLiquidGlassSwitchQuality.HighPerformance.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit DetailRow_ww6aTOc$lambda$16(String str, String str2, Modifier modifier, long j, int i, int i2, Composer composer, int i3) {
        m6943DetailRowww6aTOc(str, str2, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassActionButton$lambda$9(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        GlassActionButton(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassChipButton$lambda$12(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        GlassChipButton(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassDivider$lambda$17(Modifier modifier, int i, int i2, Composer composer, int i3) {
        GlassDivider(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassInsetBlock$lambda$4(Modifier modifier, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        GlassInsetBlock(modifier, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassOrb$lambda$75(Modifier modifier, List list, int i, Composer composer, int i2) {
        GlassOrb(modifier, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit GlassPanel$lambda$3(Modifier modifier, Shape shape, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        GlassPanel(modifier, shape, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit GlassSpecularLayer$lambda$74(Modifier modifier, Shape shape, float f, float f2, float f3, int i, Composer composer, int i2) {
        GlassSpecularLayer(modifier, shape, f, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$43(boolean z, Function1 function1, Modifier modifier, boolean z2, long j, LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec, int i, Composer composer, int i2) {
        m6944LegacyHighFidelityLiquidGlassSwitch9LiSoMs(z, function1, modifier, z2, j, legacyLiquidGlassSwitchSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$56(boolean z, Function1 function1, Modifier modifier, boolean z2, long j, LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec, int i, Composer composer, int i2) {
        m6945LegacyHighPerformanceLiquidGlassSwitch9LiSoMs(z, function1, modifier, z2, j, legacyLiquidGlassSwitchSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LegacyLiquidGlassBottomBar$lambda$70(List list, int i, Function1 function1, Modifier modifier, LiquidGlassBottomBarSpec liquidGlassBottomBarSpec, int i2, int i3, Composer composer, int i4) {
        LegacyLiquidGlassBottomBar(list, i, function1, modifier, liquidGlassBottomBarSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LegacyLiquidGlassSwitch_d3XrlC8$lambda$19(boolean z, Function1 function1, Modifier modifier, boolean z2, LegacyLiquidGlassSwitchQuality legacyLiquidGlassSwitchQuality, LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec, long j, long j2, long j3, int i, int i2, Composer composer, int i3) {
        m6946LegacyLiquidGlassSwitchd3XrlC8(z, function1, modifier, z2, legacyLiquidGlassSwitchQuality, legacyLiquidGlassSwitchSpec, j, j2, j3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$26(boolean z, Function1 function1, Modifier modifier, boolean z2, LegacyLiquidGlassSwitchQuality legacyLiquidGlassSwitchQuality, LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec, long j, long j2, long j3, int i, int i2, Composer composer, int i3) {
        m6947LegacyStatefulLiquidGlassSwitchd3XrlC8(z, function1, modifier, z2, legacyLiquidGlassSwitchQuality, legacyLiquidGlassSwitchSpec, j, j2, j3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassBackdrop$lambda$1(Modifier modifier, int i, int i2, Composer composer, int i3) {
        LiquidGlassBackdrop(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ScreenHeader$lambda$6(String str, String str2, String str3, Modifier modifier, int i, int i2, Composer composer, int i3) {
        ScreenHeader(str, str2, str3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SectionLabel$lambda$18(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        SectionLabel(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit StatusPill_3IgeMak$lambda$14(String str, long j, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m6948StatusPill3IgeMak(str, j, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0469  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LiquidGlassBackdrop(androidx.compose.ui.Modifier r46, androidx.compose.runtime.Composer r47, final int r48, final int r49) {
        /*
            Method dump skipped, instructions count: 1149
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.LiquidGlassBackdrop(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0372  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlassPanel(androidx.compose.ui.Modifier r48, androidx.compose.ui.graphics.Shape r49, androidx.compose.foundation.layout.PaddingValues r50, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 1092
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.GlassPanel(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0232  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlassInsetBlock(androidx.compose.ui.Modifier r26, androidx.compose.foundation.layout.PaddingValues r27, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instructions count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.GlassInsetBlock(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0285  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ScreenHeader(final java.lang.String r49, final java.lang.String r50, final java.lang.String r51, androidx.compose.ui.Modifier r52, androidx.compose.runtime.Composer r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.ScreenHeader(java.lang.String, java.lang.String, java.lang.String, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x036b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlassActionButton(final java.lang.String r55, final kotlin.jvm.functions.Function0<kotlin.Unit> r56, androidx.compose.ui.Modifier r57, boolean r58, androidx.compose.runtime.Composer r59, final int r60, final int r61) {
        /*
            Method dump skipped, instructions count: 905
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.GlassActionButton(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0376  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlassChipButton(final java.lang.String r54, final kotlin.jvm.functions.Function0<kotlin.Unit> r55, androidx.compose.ui.Modifier r56, boolean r57, androidx.compose.runtime.Composer r58, final int r59, final int r60) {
        /*
            Method dump skipped, instructions count: 916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.GlassChipButton(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x02ef  */
    /* renamed from: StatusPill-3IgeMak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6948StatusPill3IgeMak(final java.lang.String r52, final long r53, androidx.compose.ui.Modifier r55, androidx.compose.runtime.Composer r56, final int r57, final int r58) {
        /*
            Method dump skipped, instructions count: 779
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.m6948StatusPill3IgeMak(java.lang.String, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: DetailRow-ww6aTOc, reason: not valid java name */
    public static final void m6943DetailRowww6aTOc(final String label, final String value, Modifier modifier, long valueColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        int $dirty;
        Modifier modifier3;
        long valueColor2;
        long m4161copywmQWz5c;
        Composer $composer2;
        final long valueColor3;
        final Modifier modifier4;
        int i2;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(value, "value");
        Composer $composer3 = $composer.startRestartGroup(429164964);
        ComposerKt.sourceInformation($composer3, "C(DetailRow)P(!1,2!,3:c#ui.graphics.Color)482@16800L735:GlassComponents.kt#e5eupx");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(value) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = valueColor;
                if ($composer3.changed(j)) {
                    i2 = 2048;
                    $dirty2 |= i2;
                }
            } else {
                j = valueColor;
            }
            i2 = 1024;
            $dirty2 |= i2;
        } else {
            j = valueColor;
        }
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            valueColor3 = j;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "480@16769L11");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    Modifier modifier6 = modifier5;
                    $dirty = $dirty2 & (-7169);
                    modifier3 = modifier6;
                    valueColor2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface();
                } else {
                    Modifier modifier7 = modifier5;
                    $dirty = $dirty2;
                    modifier3 = modifier7;
                    valueColor2 = j;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                valueColor2 = j;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(429164964, $dirty, -1, "com.example.shellshot.ui.components.DetailRow (GlassComponents.kt:481)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null);
            Arrangement.Horizontal m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(12));
            Alignment.Vertical top = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m552spacedBy0680j_4, top, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer m3656constructorimpl = Updater.m3656constructorimpl($composer3);
            Updater.m3663setimpl(m3656constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl(m3656constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3656constructorimpl.getInserting() || !Intrinsics.areEqual(m3656constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3656constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3656constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3663setimpl(m3656constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i6 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -371114357, "C490@17081L10,492@17181L11,487@16969L264,497@17352L10,494@17242L287:GlassComponents.kt#e5eupx");
            Modifier m722width3ABfNKs = SizeKt.m722width3ABfNKs(Modifier.INSTANCE, Dp.m6626constructorimpl(84));
            TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall();
            FontWeight medium = FontWeight.INSTANCE.getMedium();
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r39, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r39) : 0.66f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r39) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r39) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface()) : 0.0f);
            Modifier modifier8 = modifier3;
            int $dirty3 = $dirty;
            $composer2 = $composer3;
            TextKt.m2697Text4IGK_g(label, m722width3ABfNKs, m4161copywmQWz5c, 0L, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall, $composer3, ($dirty & 14) | 196656, 0, 65496);
            long valueColor4 = valueColor2;
            TextKt.m2697Text4IGK_g(value, RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), valueColor4, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6486boximpl(TextAlign.INSTANCE.m6498getStarte0LSkKk()), 0L, TextOverflow.INSTANCE.m6543getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), $composer3, (($dirty3 >> 3) & 14) | (($dirty3 >> 3) & 896), 3120, 54776);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            valueColor3 = valueColor4;
            modifier4 = modifier8;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.DetailRow_ww6aTOc$lambda$16(label, value, modifier4, valueColor3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void GlassDivider(final Modifier modifier, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(619789451);
        ComposerKt.sourceInformation($composer2, "C(GlassDivider)510@17639L15,511@17666L84:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($dirty & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(619789451, $dirty, -1, "com.example.shellshot.ui.components.GlassDivider (GlassComponents.kt:509)");
            }
            ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer2, MaterialTheme.$stable).getColors();
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Dp.m6626constructorimpl(1)), colors.m7071getDividerColor0d7_KjU(), null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.GlassDivider$lambda$17(Modifier.this, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void SectionLabel(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier.Companion modifier3;
        Composer $composer2;
        final Modifier modifier4;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(-2032021864);
        ComposerKt.sourceInformation($composer3, "C(SectionLabel)P(1)519@17872L15,523@17985L10,520@17899L194:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2032021864, $dirty, -1, "com.example.shellshot.ui.components.SectionLabel (GlassComponents.kt:518)");
            }
            ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable).getColors();
            TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelLarge();
            FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
            $composer2 = $composer3;
            int $dirty2 = $dirty;
            long m7075getTextSecondary0d7_KjU = colors.m7075getTextSecondary0d7_KjU();
            int i3 = ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty2 & 112);
            modifier4 = modifier3;
            TextKt.m2697Text4IGK_g(text, modifier4, m7075getTextSecondary0d7_KjU, 0L, (FontStyle) null, semiBold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelLarge, $composer2, i3, 0, 65496);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.SectionLabel$lambda$18(text, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* renamed from: LegacyLiquidGlassSwitch-d3XrlC8, reason: not valid java name */
    public static final void m6946LegacyLiquidGlassSwitchd3XrlC8(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, LegacyLiquidGlassSwitchQuality quality, LegacyLiquidGlassSwitchSpec spec, long size, long activeTrackColor, long inactiveTrackColor, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        boolean enabled2;
        long j;
        int $dirty;
        long inactiveTrackColor2;
        LegacyLiquidGlassSwitchQuality quality2;
        LegacyLiquidGlassSwitchSpec spec2;
        long size2;
        long activeTrackColor2;
        long inactiveTrackColor3;
        LegacyLiquidGlassSwitchSpec spec3;
        long activeTrackColor3;
        Composer $composer2;
        final LegacyLiquidGlassSwitchQuality quality3;
        final Modifier modifier3;
        final boolean enabled3;
        final long size3;
        final LegacyLiquidGlassSwitchSpec spec4;
        final long size4;
        final long inactiveTrackColor4;
        int i2;
        int $dirty2;
        int i3;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Composer $composer3 = $composer.startRestartGroup(-169020355);
        ComposerKt.sourceInformation($composer3, "C(LegacyLiquidGlassSwitch)P(1,5,4,2,6,8,7:c#ui.unit.DpSize,0:c#ui.graphics.Color,3:c#ui.graphics.Color):GlassComponents.kt#e5eupx");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            z = checked;
            $dirty3 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = checked;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(onCheckedChange) ? 32 : 16;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(quality == null ? -1 : quality.ordinal()) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0 && $composer3.changed(spec)) {
                i5 = 131072;
                $dirty3 |= i5;
            }
            i5 = 65536;
            $dirty3 |= i5;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                j = size;
                if ($composer3.changed(j)) {
                    i4 = 1048576;
                    $dirty3 |= i4;
                }
            } else {
                j = size;
            }
            i4 = 524288;
            $dirty3 |= i4;
        } else {
            j = size;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                $dirty2 = $dirty3;
                if ($composer3.changed(activeTrackColor)) {
                    i3 = 8388608;
                    $dirty = $dirty2 | i3;
                }
            } else {
                $dirty2 = $dirty3;
            }
            i3 = 4194304;
            $dirty = $dirty2 | i3;
        } else {
            $dirty = $dirty3;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0) {
                inactiveTrackColor2 = inactiveTrackColor;
                if ($composer3.changed(inactiveTrackColor2)) {
                    i2 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    $dirty |= i2;
                }
            } else {
                inactiveTrackColor2 = inactiveTrackColor;
            }
            i2 = 33554432;
            $dirty |= i2;
        } else {
            inactiveTrackColor2 = inactiveTrackColor;
        }
        if ((38347923 & $dirty) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            quality3 = quality;
            $composer2 = $composer3;
            enabled3 = enabled2;
            size3 = j;
            size4 = activeTrackColor;
            inactiveTrackColor4 = inactiveTrackColor2;
            modifier3 = modifier2;
            spec4 = spec;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    spec3 = spec;
                    activeTrackColor3 = activeTrackColor;
                    $dirty &= -234881025;
                    inactiveTrackColor3 = inactiveTrackColor2;
                    size2 = j;
                    quality2 = quality;
                } else {
                    quality2 = quality;
                    spec3 = spec;
                    activeTrackColor3 = activeTrackColor;
                    inactiveTrackColor3 = inactiveTrackColor2;
                    size2 = j;
                }
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if (i8 == 0) {
                    quality2 = quality;
                } else {
                    quality2 = LegacyLiquidGlassSwitchQuality.HighFidelity;
                }
                if ((i & 32) == 0) {
                    spec2 = spec;
                } else {
                    if (quality2 == LegacyLiquidGlassSwitchQuality.HighFidelity) {
                        spec2 = LegacyLiquidGlassSwitchDefaults.INSTANCE.getHighFidelity();
                    } else {
                        spec2 = LegacyLiquidGlassSwitchDefaults.INSTANCE.getHighPerformance();
                    }
                    $dirty &= -458753;
                }
                if ((i & 64) == 0) {
                    size2 = j;
                } else {
                    size2 = DpKt.m6648DpSizeYgX7TsA(spec2.m6972getWidthD9Ej5fM(), spec2.m6965getHeightD9Ej5fM());
                    $dirty &= -3670017;
                }
                if ((i & 128) == 0) {
                    activeTrackColor2 = activeTrackColor;
                } else {
                    activeTrackColor2 = spec2.m6962getActiveTint0d7_KjU();
                    $dirty &= -29360129;
                }
                if ((i & 256) == 0) {
                    inactiveTrackColor3 = inactiveTrackColor2;
                    spec3 = spec2;
                    activeTrackColor3 = activeTrackColor2;
                } else {
                    inactiveTrackColor3 = spec2.m6966getInactiveTint0d7_KjU();
                    spec3 = spec2;
                    $dirty &= -234881025;
                    activeTrackColor3 = activeTrackColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-169020355, $dirty, -1, "com.example.shellshot.ui.components.LegacyLiquidGlassSwitch (GlassComponents.kt:544)");
            }
            LegacyLiquidGlassSwitchSpec tunedSpec = LegacyLiquidGlassSwitchSpec.m6949copyuAEEYxs$default(spec3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, activeTrackColor3, 0L, inactiveTrackColor3, 49151, null);
            switch (WhenMappings.$EnumSwitchMapping$0[quality2.ordinal()]) {
                case 1:
                    $composer3.startReplaceGroup(780223152);
                    ComposerKt.sourceInformation($composer3, "550@18935L243");
                    m6944LegacyHighFidelityLiquidGlassSwitch9LiSoMs(checked, onCheckedChange, modifier2, enabled2, size2, tunedSpec, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 6) & 57344));
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                    break;
                case 2:
                    $composer3.startReplaceGroup(780232851);
                    ComposerKt.sourceInformation($composer3, "559@19238L246");
                    m6945LegacyHighPerformanceLiquidGlassSwitch9LiSoMs(z, onCheckedChange, modifier2, enabled2, size2, tunedSpec, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & ($dirty >> 6)));
                    $composer3.endReplaceGroup();
                    $composer2 = $composer3;
                    break;
                default:
                    $composer3.startReplaceGroup(780221232);
                    $composer3.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            quality3 = quality2;
            modifier3 = modifier2;
            enabled3 = enabled2;
            size3 = size2;
            spec4 = spec3;
            size4 = activeTrackColor3;
            inactiveTrackColor4 = inactiveTrackColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.LegacyLiquidGlassSwitch_d3XrlC8$lambda$19(checked, onCheckedChange, modifier3, enabled3, quality3, spec4, size3, size4, inactiveTrackColor4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* renamed from: LegacyStatefulLiquidGlassSwitch-d3XrlC8, reason: not valid java name */
    public static final void m6947LegacyStatefulLiquidGlassSwitchd3XrlC8(final boolean initialChecked, final Function1<? super Boolean, Unit> onChange, Modifier modifier, boolean enabled, LegacyLiquidGlassSwitchQuality quality, LegacyLiquidGlassSwitchSpec spec, long size, long activeTrackColor, long inactiveTrackColor, Composer $composer, final int $changed, final int i) {
        boolean z;
        LegacyLiquidGlassSwitchSpec legacyLiquidGlassSwitchSpec;
        final long size2;
        long j;
        Modifier.Companion modifier2;
        LegacyLiquidGlassSwitchSpec spec2;
        long activeTrackColor2;
        boolean enabled2;
        LegacyLiquidGlassSwitchQuality quality2;
        long inactiveTrackColor2;
        long activeTrackColor3;
        int i2;
        long size3;
        LegacyLiquidGlassSwitchSpec spec3;
        Object obj;
        Object obj2;
        Composer $composer2;
        int $dirty;
        final Modifier modifier3;
        final boolean enabled3;
        final LegacyLiquidGlassSwitchQuality quality3;
        final LegacyLiquidGlassSwitchSpec spec4;
        final long activeTrackColor4;
        final long inactiveTrackColor3;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        Composer $composer3 = $composer.startRestartGroup(431051345);
        ComposerKt.sourceInformation($composer3, "C(LegacyStatefulLiquidGlassSwitch)P(3,5,4,1,6,8,7:c#ui.unit.DpSize,0:c#ui.graphics.Color,2:c#ui.graphics.Color)586@20197L34,586@20164L67,590@20315L61,588@20237L365:GlassComponents.kt#e5eupx");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(initialChecked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(onChange) ? 32 : 16;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(quality == null ? -1 : quality.ordinal()) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                legacyLiquidGlassSwitchSpec = spec;
                if ($composer3.changed(legacyLiquidGlassSwitchSpec)) {
                    i6 = 131072;
                    $dirty2 |= i6;
                }
            } else {
                legacyLiquidGlassSwitchSpec = spec;
            }
            i6 = 65536;
            $dirty2 |= i6;
        } else {
            legacyLiquidGlassSwitchSpec = spec;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                size2 = size;
                if ($composer3.changed(size2)) {
                    i5 = 1048576;
                    $dirty2 |= i5;
                }
            } else {
                size2 = size;
            }
            i5 = 524288;
            $dirty2 |= i5;
        } else {
            size2 = size;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(activeTrackColor)) {
                i4 = 8388608;
                $dirty2 |= i4;
            }
            i4 = 4194304;
            $dirty2 |= i4;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                j = inactiveTrackColor;
                if ($composer3.changed(j)) {
                    i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    $dirty2 |= i3;
                }
            } else {
                j = inactiveTrackColor;
            }
            i3 = 33554432;
            $dirty2 |= i3;
        } else {
            j = inactiveTrackColor;
        }
        if (($dirty2 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            quality3 = quality;
            $composer2 = $composer3;
            inactiveTrackColor3 = j;
            spec4 = legacyLiquidGlassSwitchSpec;
            modifier3 = modifier;
            $dirty = $dirty2;
            enabled3 = z;
            activeTrackColor4 = activeTrackColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier2 = i7 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i8 != 0 ? true : z;
                LegacyLiquidGlassSwitchQuality quality4 = i9 != 0 ? LegacyLiquidGlassSwitchQuality.HighFidelity : quality;
                if ((i & 32) != 0) {
                    spec2 = quality4 == LegacyLiquidGlassSwitchQuality.HighFidelity ? LegacyLiquidGlassSwitchDefaults.INSTANCE.getHighFidelity() : LegacyLiquidGlassSwitchDefaults.INSTANCE.getHighPerformance();
                    $dirty2 &= -458753;
                } else {
                    spec2 = legacyLiquidGlassSwitchSpec;
                }
                if ((i & 64) != 0) {
                    size2 = DpKt.m6648DpSizeYgX7TsA(spec2.m6972getWidthD9Ej5fM(), spec2.m6965getHeightD9Ej5fM());
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    activeTrackColor2 = spec2.m6962getActiveTint0d7_KjU();
                    $dirty2 &= -29360129;
                } else {
                    activeTrackColor2 = activeTrackColor;
                }
                if ((i & 256) != 0) {
                    $dirty2 = (-234881025) & $dirty2;
                    enabled2 = enabled4;
                    quality2 = quality4;
                    inactiveTrackColor2 = spec2.m6966getInactiveTint0d7_KjU();
                    activeTrackColor3 = activeTrackColor2;
                    i2 = -1;
                    size3 = size2;
                    spec3 = spec2;
                } else {
                    enabled2 = enabled4;
                    quality2 = quality4;
                    inactiveTrackColor2 = j;
                    activeTrackColor3 = activeTrackColor2;
                    i2 = -1;
                    size3 = size2;
                    spec3 = spec2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    quality2 = quality;
                    activeTrackColor3 = activeTrackColor;
                    $dirty2 &= -234881025;
                    size3 = size2;
                    enabled2 = z;
                    inactiveTrackColor2 = j;
                    spec3 = legacyLiquidGlassSwitchSpec;
                    i2 = -1;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    quality2 = quality;
                    activeTrackColor3 = activeTrackColor;
                    size3 = size2;
                    enabled2 = z;
                    inactiveTrackColor2 = j;
                    spec3 = legacyLiquidGlassSwitchSpec;
                    i2 = -1;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(431051345, $dirty2, i2, "com.example.shellshot.ui.components.LegacyStatefulLiquidGlassSwitch (GlassComponents.kt:585)");
            }
            Object[] objArr = {Boolean.valueOf(initialChecked)};
            ComposerKt.sourceInformationMarkerStart($composer3, 1923504659, "CC(remember):GlassComponents.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object rememberedValue = $composer3.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MutableState mutableStateOf$default;
                        mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(initialChecked), null, 2, null);
                        return mutableStateOf$default;
                    }
                };
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState checked$delegate = (MutableState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) null, (String) null, (Function0) obj, $composer3, 0, 6);
            boolean LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$22 = LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$22(checked$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 1923508462, "CC(remember):GlassComponents.kt#9igjgp");
            boolean changed = $composer3.changed(checked$delegate) | (($dirty2 & 112) == 32);
            Object rememberedValue2 = $composer3.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function1() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return GlassComponentsKt.LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$25$lambda$24(Function1.this, checked$delegate, ((Boolean) obj3).booleanValue());
                    }
                };
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier4 = modifier2;
            $composer2 = $composer3;
            m6946LegacyLiquidGlassSwitchd3XrlC8(LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$22, (Function1) obj2, modifier4, enabled2, quality2, spec3, size3, activeTrackColor3, inactiveTrackColor2, $composer2, ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty = $dirty2;
            modifier3 = modifier4;
            enabled3 = enabled2;
            quality3 = quality2;
            spec4 = spec3;
            size2 = size3;
            activeTrackColor4 = activeTrackColor3;
            inactiveTrackColor3 = inactiveTrackColor2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return GlassComponentsKt.LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$26(initialChecked, onChange, modifier3, enabled3, quality3, spec4, size2, activeTrackColor4, inactiveTrackColor3, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final boolean LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$22(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$23(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$25$lambda$24(Function1 $onChange, MutableState $checked$delegate, boolean it) {
        LegacyStatefulLiquidGlassSwitch_d3XrlC8$lambda$23($checked$delegate, it);
        $onChange.invoke(Boolean.valueOf(it));
        return Unit.INSTANCE;
    }

    /* renamed from: LegacyHighFidelityLiquidGlassSwitch-9LiSoMs, reason: not valid java name */
    private static final void m6944LegacyHighFidelityLiquidGlassSwitch9LiSoMs(final boolean checked, final Function1<? super Boolean, Unit> function1, final Modifier modifier, final boolean enabled, final long size, final LegacyLiquidGlassSwitchSpec spec, Composer $composer, final int $changed) {
        Object obj;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        Object obj2;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        Object obj3;
        Modifier m257clickableO2vRcR0;
        Composer $composer2 = $composer.startRestartGroup(-1801485303);
        ComposerKt.sourceInformation($composer2, "C(LegacyHighFidelityLiquidGlassSwitch)P(!1,3,2!1,4:c#ui.unit.DpSize)613@20864L39,614@20941L25,615@20987L280,623@21290L221,628@21537L315,633@21881L301,638@22211L224,643@22462L226,648@22717L273,661@23311L151,694@24495L29,696@24547L7028,657@23172L8403:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(size) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(spec) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1801485303, $dirty, -1, "com.example.shellshot.ui.components.LegacyHighFidelityLiquidGlassSwitch (GlassComponents.kt:612)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -355547600, "CC(remember):GlassComponents.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            MutableInteractionSource interactionSource = (MutableInteractionSource) obj;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            State pressed$delegate = PressInteractionKt.collectIsPressedAsState(interactionSource, $composer2, 6);
            final State progress$delegate = AnimateAsStateKt.animateFloatAsState(checked ? 1.0f : 0.0f, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), 0.0f, "liquid-glass-switch-progress", null, $composer2, 3072, 20);
            final State pressScale$delegate = AnimateAsStateKt.animateFloatAsState((LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$28(pressed$delegate) && enabled) ? spec.getPressedScale() : 1.0f, AnimationSpecKt.spring$default(0.7f, 620.0f, null, 4, null), 0.0f, "liquid-glass-switch-press-scale", null, $composer2, 3120, 20);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r27, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r27) : checked ? spec.getShellAlpha() : spec.getShellAlpha() * 0.98f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r27) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r27) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            State shellTopColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(m4161copywmQWz5c, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-shell-top", null, $composer2, 384, 8);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : checked ? 0.8f : 0.92f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(spec.m6966getInactiveTint0d7_KjU()) : 0.0f);
            State shellBottomColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(m4161copywmQWz5c2, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-shell-bottom", null, $composer2, 384, 8);
            final State liquidStartColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(spec.m6962getActiveTint0d7_KjU(), AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-liquid-start", null, $composer2, 384, 8);
            final State liquidEndColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(spec.m6963getActiveTintEdge0d7_KjU(), AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-liquid-end", null, $composer2, 384, 8);
            if (checked) {
                m4161copywmQWz5c3 = spec.m6962getActiveTint0d7_KjU();
            } else {
                m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r32, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r32) : 0.6f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r32) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r32) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            }
            final State thumbBorderColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(m4161copywmQWz5c3, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-thumb-border", null, $composer2, 384, 8);
            final RoundedCornerShape shellShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(spec.m6964getCornerRadiusD9Ej5fM());
            final RoundedCornerShape thumbShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(999));
            final float stretch = RangesKt.coerceIn(LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$29(progress$delegate) * 4.0f * (1.0f - LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$29(progress$delegate)), 0.0f, 1.0f);
            Modifier m703height3ABfNKs = SizeKt.m703height3ABfNKs(SizeKt.m722width3ABfNKs(modifier, DpSize.m6724getWidthD9Ej5fM(size)), DpSize.m6722getHeightD9Ej5fM(size));
            ComposerKt.sourceInformationMarkerStart($composer2, -355469184, "CC(remember):GlassComponents.kt#9igjgp");
            boolean changed = (($dirty & 7168) == 2048) | $composer2.changed(pressScale$delegate) | ((458752 & $dirty) == 131072);
            Object rememberedValue2 = $composer2.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function1() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return GlassComponentsKt.LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$37$lambda$36(enabled, spec, pressScale$delegate, (GraphicsLayerScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r35, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r35) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r35) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r35) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier background$default = BackgroundKt.background$default(ClipKt.clip(ShadowKt.m3826shadows4CzXII(GraphicsLayerModifierKt.graphicsLayer(m703height3ABfNKs, (Function1) obj2), spec.m6968getShadowElevationD9Ej5fM(), shellShape, false, m4161copywmQWz5c4, ColorKt.Color(402653184)), shellShape), Brush.Companion.m4120verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$31(shellTopColor$delegate)), Color.m4153boximpl(LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$32(shellBottomColor$delegate))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            float m6626constructorimpl = Dp.m6626constructorimpl(1);
            Brush.Companion companion = Brush.INSTANCE;
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r33, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r33) : spec.getStrokeAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r33) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r33) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r33, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r33) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r33) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r33) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier m240borderziNgDLE = BorderKt.m240borderziNgDLE(background$default, m6626constructorimpl, Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c5), Color.m4153boximpl(m4161copywmQWz5c6)}), 0.0f, 0.0f, 0, 14, (Object) null), shellShape);
            Role m5895boximpl = Role.m5895boximpl(Role.INSTANCE.m5907getSwitcho7Vup1c());
            ComposerKt.sourceInformationMarkerStart($composer2, -355431418, "CC(remember):GlassComponents.kt#9igjgp");
            boolean z = (($dirty & 112) == 32) | (($dirty & 14) == 4);
            Object rememberedValue3 = $composer2.rememberedValue();
            if (z || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj3 = new Function0() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return GlassComponentsKt.LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$39$lambda$38(Function1.this, checked);
                    }
                };
                $composer2.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m257clickableO2vRcR0 = ClickableKt.m257clickableO2vRcR0(m240borderziNgDLE, interactionSource, null, (r14 & 4) != 0 ? true : enabled, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : m5895boximpl, (Function0) obj3);
            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-284315277, true, new Function3() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return GlassComponentsKt.LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$42(stretch, spec, thumbShape, shellShape, progress$delegate, liquidStartColor$delegate, liquidEndColor$delegate, thumbBorderColor$delegate, (BoxWithConstraintsScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer2, 54);
            $composer2 = $composer2;
            BoxWithConstraintsKt.BoxWithConstraints(m257clickableO2vRcR0, null, false, rememberComposableLambda, $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return GlassComponentsKt.LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$43(checked, function1, modifier, enabled, size, spec, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final boolean LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$28(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$29(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$30(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final long LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$31(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$32(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$33(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$34(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$35(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    static final Unit LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$37$lambda$36(boolean $enabled, LegacyLiquidGlassSwitchSpec $spec, State $pressScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$30($pressScale$delegate));
        graphicsLayer.setScaleY(LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$30($pressScale$delegate));
        graphicsLayer.setAlpha($enabled ? 1.0f : $spec.getDisabledAlpha());
        return Unit.INSTANCE;
    }

    static final Unit LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$39$lambda$38(Function1 $onCheckedChange, boolean $checked) {
        $onCheckedChange.invoke(Boolean.valueOf(!$checked));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x06b5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0975  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x06bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$42(float r64, com.example.shellshot.ui.components.LegacyLiquidGlassSwitchSpec r65, androidx.compose.foundation.shape.RoundedCornerShape r66, androidx.compose.foundation.shape.RoundedCornerShape r67, androidx.compose.runtime.State r68, androidx.compose.runtime.State r69, androidx.compose.runtime.State r70, androidx.compose.runtime.State r71, androidx.compose.foundation.layout.BoxWithConstraintsScope r72, androidx.compose.runtime.Composer r73, int r74) {
        /*
            Method dump skipped, instructions count: 2427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.LegacyHighFidelityLiquidGlassSwitch_9LiSoMs$lambda$42(float, com.example.shellshot.ui.components.LegacyLiquidGlassSwitchSpec, androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* renamed from: LegacyHighPerformanceLiquidGlassSwitch-9LiSoMs, reason: not valid java name */
    private static final void m6945LegacyHighPerformanceLiquidGlassSwitch9LiSoMs(final boolean checked, final Function1<? super Boolean, Unit> function1, final Modifier modifier, final boolean enabled, final long size, final LegacyLiquidGlassSwitchSpec spec, Composer $composer, final int $changed) {
        Object obj;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        Object obj2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        Object obj3;
        Modifier m257clickableO2vRcR0;
        Composer $composer2 = $composer.startRestartGroup(-153002079);
        ComposerKt.sourceInformation($composer2, "C(LegacyHighPerformanceLiquidGlassSwitch)P(!1,3,2!1,4:c#ui.unit.DpSize)889@31840L39,890@31917L25,891@31963L292,899@32278L227,904@32534L307,909@32870L286,922@33477L151,953@34576L29,955@34628L2838,918@33338L4128:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(size) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(spec) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-153002079, $dirty, -1, "com.example.shellshot.ui.components.LegacyHighPerformanceLiquidGlassSwitch (GlassComponents.kt:888)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1006101464, "CC(remember):GlassComponents.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            MutableInteractionSource interactionSource = (MutableInteractionSource) obj;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            State pressed$delegate = PressInteractionKt.collectIsPressedAsState(interactionSource, $composer2, 6);
            final State progress$delegate = AnimateAsStateKt.animateFloatAsState(checked ? 1.0f : 0.0f, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), 0.0f, "liquid-glass-switch-performance-progress", null, $composer2, 3072, 20);
            final State pressScale$delegate = AnimateAsStateKt.animateFloatAsState((LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$45(pressed$delegate) && enabled) ? spec.getPressedScale() : 1.0f, AnimationSpecKt.spring$default(0.7f, 620.0f, null, 4, null), 0.0f, "liquid-glass-switch-performance-scale", null, $composer2, 3120, 20);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r27, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r27) : checked ? 0.84f : 0.92f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r27) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r27) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(spec.m6966getInactiveTint0d7_KjU()) : 0.0f);
            State shellBottomColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(m4161copywmQWz5c, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-performance-shell", null, $composer2, 384, 8);
            if (checked) {
                m4161copywmQWz5c2 = spec.m6962getActiveTint0d7_KjU();
            } else {
                m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : 0.58f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            }
            final State thumbBorderColor$delegate = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(m4161copywmQWz5c2, AnimationSpecKt.tween$default(spec.getAnimationDurationMillis(), 0, LegacyLiquidGlassSwitchMotionEasing, 2, null), "liquid-glass-switch-performance-thumb-border", null, $composer2, 384, 8);
            RoundedCornerShape shellShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(spec.m6964getCornerRadiusD9Ej5fM());
            final RoundedCornerShape thumbShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(999));
            final float stretch = RangesKt.coerceIn(LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$46(progress$delegate) * 4.0f * (1.0f - LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$46(progress$delegate)), 0.0f, 1.0f);
            Modifier m703height3ABfNKs = SizeKt.m703height3ABfNKs(SizeKt.m722width3ABfNKs(modifier, DpSize.m6724getWidthD9Ej5fM(size)), DpSize.m6722getHeightD9Ej5fM(size));
            ComposerKt.sourceInformationMarkerStart($composer2, -1006048968, "CC(remember):GlassComponents.kt#9igjgp");
            boolean changed = (($dirty & 7168) == 2048) | $composer2.changed(pressScale$delegate) | ((458752 & $dirty) == 131072);
            Object rememberedValue2 = $composer2.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function1() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return GlassComponentsKt.LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$51$lambda$50(enabled, spec, pressScale$delegate, (GraphicsLayerScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r32, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r32) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r32) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r32) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier clip = ClipKt.clip(ShadowKt.m3826shadows4CzXII(GraphicsLayerModifierKt.graphicsLayer(m703height3ABfNKs, (Function1) obj2), spec.m6968getShadowElevationD9Ej5fM(), shellShape, false, m4161copywmQWz5c3, ColorKt.Color(335544320)), shellShape);
            Brush.Companion companion = Brush.INSTANCE;
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r31, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r31) : spec.getShellAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r31) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r31) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier background$default = BackgroundKt.background$default(clip, Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c4), Color.m4153boximpl(LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$48(shellBottomColor$delegate))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            float m6626constructorimpl = Dp.m6626constructorimpl(1);
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : spec.getStrokeAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier m238borderxT4_qwU = BorderKt.m238borderxT4_qwU(background$default, m6626constructorimpl, m4161copywmQWz5c5, shellShape);
            Role m5895boximpl = Role.m5895boximpl(Role.INSTANCE.m5907getSwitcho7Vup1c());
            ComposerKt.sourceInformationMarkerStart($composer2, -1006013922, "CC(remember):GlassComponents.kt#9igjgp");
            boolean z = (($dirty & 112) == 32) | (($dirty & 14) == 4);
            Object rememberedValue3 = $composer2.rememberedValue();
            if (z || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj3 = new Function0() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return GlassComponentsKt.LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$53$lambda$52(Function1.this, checked);
                    }
                };
                $composer2.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m257clickableO2vRcR0 = ClickableKt.m257clickableO2vRcR0(m238borderxT4_qwU, interactionSource, null, (r14 & 4) != 0 ? true : enabled, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : m5895boximpl, (Function0) obj3);
            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1918386679, true, new Function3() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return GlassComponentsKt.LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$55(stretch, thumbShape, spec, progress$delegate, thumbBorderColor$delegate, (BoxWithConstraintsScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer2, 54);
            $composer2 = $composer2;
            BoxWithConstraintsKt.BoxWithConstraints(m257clickableO2vRcR0, null, false, rememberComposableLambda, $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return GlassComponentsKt.LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$56(checked, function1, modifier, enabled, size, spec, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final boolean LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$45(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$46(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$47(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final long LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$48(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$49(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    static final Unit LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$51$lambda$50(boolean $enabled, LegacyLiquidGlassSwitchSpec $spec, State $pressScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$47($pressScale$delegate));
        graphicsLayer.setScaleY(LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$47($pressScale$delegate));
        graphicsLayer.setAlpha($enabled ? 1.0f : $spec.getDisabledAlpha());
        return Unit.INSTANCE;
    }

    static final Unit LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$53$lambda$52(Function1 $onCheckedChange, boolean $checked) {
        $onCheckedChange.invoke(Boolean.valueOf(!$checked));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x043e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$55(float r49, androidx.compose.foundation.shape.RoundedCornerShape r50, com.example.shellshot.ui.components.LegacyLiquidGlassSwitchSpec r51, androidx.compose.runtime.State r52, androidx.compose.runtime.State r53, androidx.compose.foundation.layout.BoxWithConstraintsScope r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instructions count: 1092
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.LegacyHighPerformanceLiquidGlassSwitch_9LiSoMs$lambda$55(float, androidx.compose.foundation.shape.RoundedCornerShape, com.example.shellshot.ui.components.LegacyLiquidGlassSwitchSpec, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    public static final void LegacyLiquidGlassBottomBar(final List<GlassBottomBarItem> items, final int selectedIndex, final Function1<? super Integer, Unit> onSelect, Modifier modifier, LiquidGlassBottomBarSpec spec, Composer $composer, final int $changed, final int i) {
        final int i2;
        Modifier modifier2;
        LiquidGlassBottomBarSpec liquidGlassBottomBarSpec;
        Modifier.Companion modifier3;
        LiquidGlassBottomBarSpec spec2;
        Composer $composer2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        Brush m4124linearGradientmHitzGk;
        final Modifier modifier4;
        final LiquidGlassBottomBarSpec spec3;
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        Composer $composer3 = $composer.startRestartGroup(1587322044);
        ComposerKt.sourceInformation($composer3, "C(LegacyLiquidGlassBottomBar)P(!1,3,2)1040@37752L69,1041@37859L319,1050@38216L311,1092@39773L11842,1062@38629L12986:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(items) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            i2 = selectedIndex;
            $dirty |= $composer3.changed(i2) ? 32 : 16;
        } else {
            i2 = selectedIndex;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onSelect) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            liquidGlassBottomBarSpec = spec;
        } else if (($changed & 24576) == 0) {
            liquidGlassBottomBarSpec = spec;
            $dirty |= $composer3.changed(liquidGlassBottomBarSpec) ? 16384 : 8192;
        } else {
            liquidGlassBottomBarSpec = spec;
        }
        if (($dirty & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            LiquidGlassBottomBarSpec liquidGlassBottomBarSpec2 = liquidGlassBottomBarSpec;
            $composer2 = $composer3;
            spec3 = liquidGlassBottomBarSpec2;
            modifier4 = modifier2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                spec2 = liquidGlassBottomBarSpec;
            } else {
                spec2 = LiquidGlassBottomBarDefaults.INSTANCE.getAppleEnhanced();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1587322044, $dirty, -1, "com.example.shellshot.ui.components.LegacyLiquidGlassBottomBar (GlassComponents.kt:1039)");
            }
            InfiniteTransition breatheTransition = InfiniteTransitionKt.rememberInfiniteTransition("liquid-glass-bottom-bar-breathe", $composer3, 6, 0);
            final State breathe$delegate = InfiniteTransitionKt.animateFloat(breatheTransition, 0.95f, 1.08f, AnimationSpecKt.m131infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(4200, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "liquid-glass-bottom-bar-breathe-alpha", $composer3, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            final State shimmer$delegate = InfiniteTransitionKt.animateFloat(breatheTransition, -0.2f, 1.18f, AnimationSpecKt.m131infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2800, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "liquid-glass-bottom-bar-sheen", $composer3, InfiniteTransition.$stable | 24960 | (InfiniteRepeatableSpec.$stable << 9), 0);
            $composer2 = $composer3;
            final RoundedCornerShape shape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(spec2.m6988getCornerRadiusD9Ej5fM());
            final long barPrismColor = ColorKt.Color(4293327103L);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : 0.5f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(861904895)) : 0.0f);
            Modifier clip = ClipKt.clip(ShadowKt.m3826shadows4CzXII(SizeKt.m703height3ABfNKs(modifier3, spec2.m6987getBarHeightD9Ej5fM()), spec2.m6993getShadowElevationD9Ej5fM(), shape, false, m4161copywmQWz5c, m4161copywmQWz5c2), shape);
            Brush.Companion companion = Brush.INSTANCE;
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : spec2.getFillAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : spec2.getFillAlpha() * 0.64f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(spec2.m6994getWarmTint0d7_KjU()) : 0.0f);
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : spec2.getFillAlpha() * 0.58f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(spec2.m6986getActiveTint0d7_KjU()) : 0.0f);
            Modifier background$default = BackgroundKt.background$default(clip, Brush.Companion.m4112horizontalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c3), Color.m4153boximpl(m4161copywmQWz5c4), Color.m4153boximpl(m4161copywmQWz5c5)}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            float m6626constructorimpl = Dp.m6626constructorimpl(1);
            Brush.Companion companion2 = Brush.INSTANCE;
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r17) : spec2.getStrokeAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r17) : 0.2f * spec2.getStrokeAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4124linearGradientmHitzGk = companion2.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c6), Color.m4153boximpl(m4161copywmQWz5c7)}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : 0L, (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : 0L, (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
            Modifier m240borderziNgDLE = BorderKt.m240borderziNgDLE(background$default, m6626constructorimpl, m4124linearGradientmHitzGk, shape);
            Modifier modifier5 = modifier3;
            final LiquidGlassBottomBarSpec spec4 = spec2;
            BoxWithConstraintsKt.BoxWithConstraints(m240borderziNgDLE, null, false, ComposableLambdaKt.rememberComposableLambda(315341478, true, new Function3() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return GlassComponentsKt.LegacyLiquidGlassBottomBar$lambda$69(items, spec4, i2, barPrismColor, shape, breathe$delegate, shimmer$delegate, onSelect, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            spec3 = spec4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.LegacyLiquidGlassBottomBar$lambda$70(items, selectedIndex, onSelect, modifier4, spec3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float LegacyLiquidGlassBottomBar$lambda$57(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LegacyLiquidGlassBottomBar$lambda$58(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0af3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0ae1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0aed  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0ba6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0d73  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0d7f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0e2c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0e6a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0e6f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0e38  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0d85  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0efd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LegacyLiquidGlassBottomBar$lambda$69(java.util.List r91, com.example.shellshot.ui.components.LiquidGlassBottomBarSpec r92, int r93, long r94, androidx.compose.foundation.shape.RoundedCornerShape r96, androidx.compose.runtime.State r97, androidx.compose.runtime.State r98, kotlin.jvm.functions.Function1 r99, androidx.compose.foundation.layout.BoxWithConstraintsScope r100, androidx.compose.runtime.Composer r101, int r102) {
        /*
            Method dump skipped, instructions count: 3843
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.GlassComponentsKt.LegacyLiquidGlassBottomBar$lambda$69(java.util.List, com.example.shellshot.ui.components.LiquidGlassBottomBarSpec, int, long, androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.runtime.State, androidx.compose.runtime.State, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final float LegacyLiquidGlassBottomBar$lambda$69$lambda$59(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    private static final long LegacyLiquidGlassBottomBar$lambda$69$lambda$68$lambda$67$lambda$61(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final float LegacyLiquidGlassBottomBar$lambda$69$lambda$68$lambda$67$lambda$62(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    static final Unit LegacyLiquidGlassBottomBar$lambda$69$lambda$68$lambda$67$lambda$65$lambda$64(Function1 $onSelect, int $index) {
        $onSelect.invoke(Integer.valueOf($index));
        return Unit.INSTANCE;
    }

    private static final void GlassSpecularLayer(Modifier modifier, Shape shape, final float highlightAngleDegrees, final float highlightAlpha, final float breathe, Composer $composer, final int $changed) {
        Modifier modifier2;
        Shape shape2;
        Object obj;
        Composer $composer2 = $composer.startRestartGroup(-1645014309);
        ComposerKt.sourceInformation($composer2, "C(GlassSpecularLayer)P(3,4,2,1)1399@51879L1597,1396@51794L1689:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer2.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(highlightAngleDegrees) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(highlightAlpha) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(breathe) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1645014309, $dirty2, -1, "com.example.shellshot.ui.components.GlassSpecularLayer (GlassComponents.kt:1395)");
            }
            Modifier clip = ClipKt.clip(modifier, shape);
            ComposerKt.sourceInformationMarkerStart($composer2, -310776104, "CC(remember):GlassComponents.kt#9igjgp");
            boolean z = (($dirty2 & 896) == 256) | (($dirty2 & 7168) == 2048) | ((57344 & $dirty2) == 16384);
            Object rememberedValue = $composer2.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function1() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return GlassComponentsKt.GlassSpecularLayer$lambda$73$lambda$72(highlightAngleDegrees, highlightAlpha, breathe, (CacheDrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxKt.Box(DrawModifierKt.drawWithCache(clip, (Function1) obj), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final Shape shape3 = shape2;
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return GlassComponentsKt.GlassSpecularLayer$lambda$74(Modifier.this, shape3, highlightAngleDegrees, highlightAlpha, breathe, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final DrawResult GlassSpecularLayer$lambda$73$lambda$72(float $highlightAngleDegrees, final float $highlightAlpha, final float $breathe, CacheDrawScope drawWithCache) {
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Pair<Offset, Offset> gradientLineForAngle = gradientLineForAngle($highlightAngleDegrees, Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()));
        final long start = gradientLineForAngle.component1().getPackedValue();
        final long end = gradientLineForAngle.component2().getPackedValue();
        final float radius = Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) / 2.0f;
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GlassComponentsKt.GlassSpecularLayer$lambda$73$lambda$72$lambda$71($highlightAlpha, $breathe, start, end, radius, (DrawScope) obj);
            }
        });
    }

    static final Unit GlassSpecularLayer$lambda$73$lambda$72$lambda$71(float $highlightAlpha, float $breathe, long $start, long $end, float $radius, DrawScope onDrawBehind) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        Brush m4124linearGradientmHitzGk;
        long m4161copywmQWz5c3;
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : $highlightAlpha * (($breathe * 0.08f) + 0.96f), (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : $highlightAlpha * 0.26f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4124linearGradientmHitzGk = companion.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : $start, (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : $end, (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, m4124linearGradientmHitzGk, 0L, 0L, CornerRadiusKt.CornerRadius($radius, $radius), 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 118, null);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : $highlightAlpha * ((0.08f * $breathe) + 0.66f), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, Brush.Companion.m4120verticalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c3), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, Size.m3988getHeightimpl(onDrawBehind.mo4713getSizeNHjbRc()) * 0.48f, 0, 10, (Object) null), 0L, androidx.compose.ui.geometry.SizeKt.Size(Size.m3991getWidthimpl(onDrawBehind.mo4713getSizeNHjbRc()), Size.m3988getHeightimpl(onDrawBehind.mo4713getSizeNHjbRc()) * 0.48f), CornerRadiusKt.CornerRadius($radius, $radius), 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 114, null);
        return Unit.INSTANCE;
    }

    private static final Pair<Offset, Offset> gradientLineForAngle(float angleDegrees, float width, float height) {
        double radians = Math.toRadians(angleDegrees);
        float radius = ((float) Math.hypot(width, height)) / 2.0f;
        float dx = ((float) Math.cos(radians)) * radius;
        float dy = ((float) Math.sin(radians)) * radius;
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;
        return TuplesKt.to(Offset.m3911boximpl(OffsetKt.Offset(centerX - dx, centerY - dy)), Offset.m3911boximpl(OffsetKt.Offset(centerX + dx, centerY + dy)));
    }

    private static final void GlassOrb(final Modifier modifier, final List<Color> list, Composer $composer, final int $changed) {
        Brush m4126radialGradientP_VxKs;
        Composer $composer2 = $composer.startRestartGroup(-721967229);
        ComposerKt.sourceInformation($composer2, "C(GlassOrb)P(1)1458@54160L165:GlassComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-721967229, $dirty2, -1, "com.example.shellshot.ui.components.GlassOrb (GlassComponents.kt:1457)");
            }
            Modifier m3800blurF8QBwvs$default = BlurKt.m3800blurF8QBwvs$default(modifier, Dp.m6626constructorimpl(96), null, 2, null);
            m4126radialGradientP_VxKs = Brush.INSTANCE.m4126radialGradientP_VxKs((List<Color>) list, (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : 0L, (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
            BoxKt.Box(BackgroundKt.background$default(m3800blurF8QBwvs$default, m4126radialGradientP_VxKs, RoundedCornerShapeKt.getCircleShape(), 0.0f, 4, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.GlassComponentsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return GlassComponentsKt.GlassOrb$lambda$75(Modifier.this, list, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
