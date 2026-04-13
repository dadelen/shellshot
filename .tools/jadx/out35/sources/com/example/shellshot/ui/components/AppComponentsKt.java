package com.example.shellshot.ui.components;

import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.DeleteKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SwipeToDismissBoxValue;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.ui.theme.DesignTokensKt;
import com.example.shellshot.ui.theme.ShellShotColorTokens;
import com.example.shellshot.ui.theme.ShellShotShadowStyle;
import com.example.shellshot.ui.theme.ShellShotSpacingTokens;
import com.example.shellshot.ui.theme.ShellShotTokens;
import com.example.shellshot.ui.theme.ThemeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: AppComponents.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001aA\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a/\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0014\u001a\u001f\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0017\u001aI\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u001e\u001a?\u0010\u001f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010 \u001a\u001f\u0010!\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0017\u001a7\u0010\"\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010&\u001a7\u0010'\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010&\u001a7\u0010(\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010&\u001a'\u0010)\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010,\u001a\u0017\u0010-\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a)\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0004\b3\u00104\u001a_\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$2\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010$H\u0007¢\u0006\u0002\u0010<\u001aS\u0010=\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010?\u001a\u00020+2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010$H\u0007¢\u0006\u0002\u0010@\u001aU\u0010A\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u001a2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u001a2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010D\u001aY\u0010E\u001a\u00020\u00012\u0006\u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00112\u0006\u0010I\u001a\u00020\u001a2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00010$H\u0007¢\u0006\u0002\u0010K\u001aK\u0010L\u001a\u00020\u00012\f\u0010M\u001a\b\u0012\u0004\u0012\u00020O0N2\u0006\u0010P\u001a\u00020Q2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010S\u001a\u00020TH\u0007¢\u0006\u0002\u0010U\u001aA\u0010V\u001a\u00020\u00012\f\u0010M\u001a\b\u0012\u0004\u0012\u00020O0N2\u0006\u0010P\u001a\u00020Q2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010W\u001a\r\u0010X\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010Y\u001a\u0019\u0010Z\u001a\u000202*\u00020+2\u0006\u0010[\u001a\u00020\\H\u0002¢\u0006\u0002\u0010]\u001a\f\u0010^\u001a\u00020\u0011*\u00020+H\u0002\u001a\u001c\u0010_\u001a\u00020\u0003*\u00020\u00032\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020cH\u0002\u001a\u001f\u0010d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010e\u001a\u000202H\u0003¢\u0006\u0004\bf\u0010g\"\u000e\u0010h\u001a\u00020iX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006j²\u0006\n\u0010k\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010l\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u0010k\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010l\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u0010k\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010l\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u0010n\u001a\u00020QX\u008a\u008e\u0002²\u0006\n\u0010o\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010p\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u00101\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u0010q\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u0010r\u001a\u00020mX\u008a\u0084\u0002²\u0006\n\u0010s\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010t\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"AppBackdrop", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AppPageSection", "maxContentWidth", "Landroidx/compose/ui/unit/Dp;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "AppPageSection-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AppPageHeader", "eyebrow", "", "title", "description", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AppSectionLabel", "text", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AppSurfaceCard", "prominent", "", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AppInsetPanel", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AppMetaChip", "AppDarkButton", "onClick", "Lkotlin/Function0;", "enabled", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "AppPrimaryButton", "AppSecondaryButton", "AppStatusPill", "tone", "Lcom/example/shellshot/ui/components/StatusTone;", "(Ljava/lang/String;Lcom/example/shellshot/ui/components/StatusTone;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AppDivider", "AppIconPlate", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "tint", "Landroidx/compose/ui/graphics/Color;", "AppIconPlate-3IgeMak", "(Landroidx/compose/ui/graphics/vector/ImageVector;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "TemplateCard", HintConstants.AUTOFILL_HINT_NAME, "previewPath", "summary", "selected", "actionText", "onDelete", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "SettingActionRow", "statusText", "statusTone", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/String;Lcom/example/shellshot/ui/components/StatusTone;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "SettingToggleRow", "checked", "onCheckedChange", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LogStreamItem", "module", "timestamp", "details", "expanded", "onToggleExpanded", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/shellshot/ui/components/StatusTone;Landroidx/compose/ui/Modifier;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "LiquidGlassBottomBar", "items", "", "Lcom/example/shellshot/ui/components/GlassBottomBarItem;", "selectedIndex", "", "onSelect", "style", "Lcom/example/shellshot/ui/components/LiquidGlassBottomBarStyle;", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lcom/example/shellshot/ui/components/LiquidGlassBottomBarStyle;Landroidx/compose/runtime/Composer;II)V", "PremiumBottomBar", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "LiquidGlassBottomBarPreview", "(Landroidx/compose/runtime/Composer;I)V", "iconTint", "colors", "Lcom/example/shellshot/ui/theme/ShellShotColorTokens;", "(Lcom/example/shellshot/ui/components/StatusTone;Lcom/example/shellshot/ui/theme/ShellShotColorTokens;)J", "label", "appShadow", "shadow", "Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;", "shape", "Landroidx/compose/ui/graphics/Shape;", "BackgroundWash", "color", "BackgroundWash-RPmYEkk", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;I)V", "PremiumMotionEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "app_debug", "pressed", "scale", "", "previousSelectedIndex", "indicatorOffset", "selectionFraction", "contentAlpha", "iconScale", "labelOffset", "iconSize"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class AppComponentsKt {
    private static final CubicBezierEasing PremiumMotionEasing = new CubicBezierEasing(0.22f, 1.0f, 0.36f, 1.0f);

    /* compiled from: AppComponents.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StatusTone.values().length];
            try {
                iArr[StatusTone.Success.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StatusTone.Warning.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StatusTone.Error.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StatusTone.Info.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[StatusTone.Neutral.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit AppBackdrop$lambda$4(Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppBackdrop(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppDarkButton$lambda$25(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        AppDarkButton(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppDivider$lambda$42(Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppDivider(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppIconPlate_3IgeMak$lambda$44(ImageVector imageVector, long j, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m6926AppIconPlate3IgeMak(imageVector, j, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppInsetPanel$lambda$16(Modifier modifier, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        AppInsetPanel(modifier, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppMetaChip$lambda$18(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppMetaChip(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppPageHeader$lambda$8(String str, String str2, String str3, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppPageHeader(str, str2, str3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppPageSection_uFdPcIQ$lambda$6(Modifier modifier, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m6927AppPageSectionuFdPcIQ(modifier, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppPrimaryButton$lambda$32(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        AppPrimaryButton(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppSecondaryButton$lambda$39(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        AppSecondaryButton(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppSectionLabel$lambda$9(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppSectionLabel(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppStatusPill$lambda$41(String str, StatusTone statusTone, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AppStatusPill(str, statusTone, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AppSurfaceCard$lambda$14(Modifier modifier, boolean z, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        AppSurfaceCard(modifier, z, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BackgroundWash_RPmYEkk$lambda$116(Modifier modifier, long j, int i, Composer composer, int i2) {
        m6928BackgroundWashRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassBottomBar$lambda$112(List list, int i, Function1 function1, Modifier modifier, LiquidGlassBottomBarStyle liquidGlassBottomBarStyle, int i2, int i3, Composer composer, int i4) {
        LiquidGlassBottomBar(list, i, function1, modifier, liquidGlassBottomBarStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassBottomBarPreview$lambda$114(int i, Composer composer, int i2) {
        LiquidGlassBottomBarPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LogStreamItem$lambda$78(String str, String str2, String str3, StatusTone statusTone, Modifier modifier, String str4, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        LogStreamItem(str, str2, str3, statusTone, modifier, str4, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PremiumBottomBar$lambda$113(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        PremiumBottomBar(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit SettingActionRow$lambda$67(ImageVector imageVector, String str, String str2, Modifier modifier, String str3, StatusTone statusTone, Function0 function0, int i, int i2, Composer composer, int i3) {
        SettingActionRow(imageVector, str, str2, modifier, str3, statusTone, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SettingToggleRow$lambda$70(ImageVector imageVector, String str, String str2, boolean z, Modifier modifier, boolean z2, Function1 function1, int i, int i2, Composer composer, int i3) {
        SettingToggleRow(imageVector, str, str2, z, modifier, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TemplateCard$lambda$63(String str, String str2, String str3, boolean z, String str4, Modifier modifier, Function0 function0, Function0 function02, int i, int i2, Composer composer, int i3) {
        TemplateCard(str, str2, str3, z, str4, modifier, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0622  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppBackdrop(androidx.compose.ui.Modifier r89, androidx.compose.runtime.Composer r90, final int r91, final int r92) {
        /*
            Method dump skipped, instructions count: 1590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppBackdrop(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    static final DrawResult AppBackdrop$lambda$3$lambda$2$lambda$1(boolean $darkBackdrop, ShellShotColorTokens $colors, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        final Brush primarySweep;
        long m4161copywmQWz5c2;
        final Brush secondarySweep;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        Color[] colorArr = new Color[3];
        colorArr[0] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : $darkBackdrop ? 0.04f : 0.32f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr[1] = Color.m4153boximpl(m4161copywmQWz5c);
        colorArr[2] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        primarySweep = companion.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) colorArr), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.18f, 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.58f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion2 = Brush.INSTANCE;
        Color[] colorArr2 = new Color[3];
        colorArr2[0] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r16) : $darkBackdrop ? 0.015f : 0.05f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7056getAccentBlue0d7_KjU()) : 0.0f);
        colorArr2[1] = Color.m4153boximpl(m4161copywmQWz5c2);
        colorArr2[2] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        secondarySweep = companion2.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) colorArr2), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.48f, 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.84f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppComponentsKt.AppBackdrop$lambda$3$lambda$2$lambda$1$lambda$0(Brush.this, secondarySweep, (DrawScope) obj);
            }
        });
    }

    static final Unit AppBackdrop$lambda$3$lambda$2$lambda$1$lambda$0(Brush $primarySweep, Brush $secondarySweep, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4706drawRectAsUm42w$default(onDrawBehind, $primarySweep, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 62, null);
        DrawScope.m4706drawRectAsUm42w$default(onDrawBehind, $secondarySweep, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 62, null);
        return Unit.INSTANCE;
    }

    /* renamed from: AppPageSection-uFdPcIQ, reason: not valid java name */
    public static final void m6927AppPageSectionuFdPcIQ(Modifier modifier, final float maxContentWidth, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final float maxContentWidth2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(2050914526);
        ComposerKt.sourceInformation($composer2, "C(AppPageSection)P(2,1:c#ui.unit.Dp)305@11879L268,302@11764L383:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(maxContentWidth) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            maxContentWidth2 = maxContentWidth;
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i3 != 0) {
                maxContentWidth = Dp.m6626constructorimpl(1120);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2050914526, $dirty2, -1, "com.example.shellshot.ui.components.AppPageSection (AppComponents.kt:301)");
            }
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Alignment.INSTANCE.getCenter(), false, ComposableLambdaKt.rememberComposableLambda(1156778804, true, new Function3() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AppComponentsKt.AppPageSection_uFdPcIQ$lambda$5(maxContentWidth, content, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier;
            maxContentWidth2 = maxContentWidth;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.AppPageSection_uFdPcIQ$lambda$6(Modifier.this, maxContentWidth2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0172  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit AppPageSection_uFdPcIQ$lambda$5(float r25, kotlin.jvm.functions.Function3 r26, androidx.compose.foundation.layout.BoxWithConstraintsScope r27, androidx.compose.runtime.Composer r28, int r29) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppPageSection_uFdPcIQ$lambda$5(float, kotlin.jvm.functions.Function3, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    public static final void AppPageHeader(final String eyebrow, final String title, final String description, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier.Companion modifier3;
        Composer composer;
        Composer composer2;
        Composer $composer2;
        int $dirty;
        Modifier modifier4;
        Composer composer3;
        char c;
        final Modifier modifier5;
        Intrinsics.checkNotNullParameter(eyebrow, "eyebrow");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Composer $composer3 = $composer.startRestartGroup(1029488929);
        ComposerKt.sourceInformation($composer3, "C(AppPageHeader)P(1,3)325@12317L15,326@12372L15,328@12401L767:AppComponents.kt#e5eupx");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(eyebrow) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(title) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(description) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier5 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1029488929, $dirty2, -1, "com.example.shellshot.ui.components.AppPageHeader (AppComponents.kt:324)");
            }
            ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable).getColors();
            ShellShotSpacingTokens spacing = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable).getSpacing();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null);
            Arrangement.Vertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(spacing.m7110getSmD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m552spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3663setimpl(m3656constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl(m3656constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3656constructorimpl.getInserting() || !Intrinsics.areEqual(m3656constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3656constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3656constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3663setimpl(m3656constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1786277539, "C342@12864L10,340@12798L140:AppComponents.kt#e5eupx");
            if (StringsKt.isBlank(eyebrow)) {
                composer = $composer3;
                composer2 = $composer3;
                $composer2 = $composer3;
                $dirty = $dirty2;
                modifier4 = modifier3;
                composer3 = $composer3;
                c = 26935;
                composer2.startReplaceGroup(1773824311);
                composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1786290961);
                ComposerKt.sourceInformation($composer3, "335@12649L10,333@12573L206");
                TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelLarge();
                modifier4 = modifier3;
                $composer2 = $composer3;
                $dirty = $dirty2;
                composer3 = $composer3;
                composer = $composer3;
                TextKt.m2697Text4IGK_g(eyebrow, (Modifier) null, colors.m7071getTextSecondary0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelLarge, $composer3, ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 65498);
                composer2 = $composer3;
                composer2.endReplaceGroup();
                c = 26935;
            }
            Composer composer4 = composer2;
            TextKt.m2697Text4IGK_g(title, (Modifier) null, colors.m7070getTextPrimary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getDisplaySmall(), composer4, ($dirty >> 3) & 14, 0, 65530);
            Composer composer5 = composer4;
            if (StringsKt.isBlank(description)) {
                composer5.startReplaceGroup(1773824311);
            } else {
                composer5.startReplaceGroup(1786704222);
                ComposerKt.sourceInformation(composer5, "348@13071L10,346@12991L161");
                TextKt.m2697Text4IGK_g(description, (Modifier) null, colors.m7071getTextSecondary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getBodyLarge(), composer5, ($dirty >> 6) & 14, 0, 65530);
                composer5 = composer5;
            }
            composer5.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.AppPageHeader$lambda$8(eyebrow, title, description, modifier5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AppSectionLabel(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier.Companion modifier3;
        Composer $composer2;
        final Modifier modifier4;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(-16913906);
        ComposerKt.sourceInformation($composer3, "C(AppSectionLabel)P(1)360@13293L15,364@13406L10,361@13320L191:AppComponents.kt#e5eupx");
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
                ComposerKt.traceEventStart(-16913906, $dirty, -1, "com.example.shellshot.ui.components.AppSectionLabel (AppComponents.kt:359)");
            }
            ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable).getColors();
            TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelLarge();
            FontWeight medium = FontWeight.INSTANCE.getMedium();
            $composer2 = $composer3;
            int $dirty2 = $dirty;
            long m7072getTextTertiary0d7_KjU = colors.m7072getTextTertiary0d7_KjU();
            int i3 = ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty2 & 112);
            modifier4 = modifier3;
            TextKt.m2697Text4IGK_g(text, modifier4, m7072getTextTertiary0d7_KjU, 0L, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelLarge, $composer2, i3, 0, 65496);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.AppSectionLabel$lambda$9(text, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0382 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x033b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppSurfaceCard(androidx.compose.ui.Modifier r51, boolean r52, androidx.compose.foundation.layout.PaddingValues r53, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, androidx.compose.runtime.Composer r55, final int r56, final int r57) {
        /*
            Method dump skipped, instructions count: 1038
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppSurfaceCard(androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    static final DrawResult AppSurfaceCard$lambda$13$lambda$12$lambda$11(ShellShotColorTokens $colors, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush topGlow = Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, 0.42f * Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 10, (Object) null);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.02f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7056getAccentBlue0d7_KjU()) : 0.0f);
        final Brush sideTint = Brush.Companion.m4112horizontalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c3)}), 0.0f, 0.0f, 0, 14, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppComponentsKt.AppSurfaceCard$lambda$13$lambda$12$lambda$11$lambda$10(Brush.this, sideTint, (DrawScope) obj);
            }
        });
    }

    static final Unit AppSurfaceCard$lambda$13$lambda$12$lambda$11$lambda$10(Brush $topGlow, Brush $sideTint, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $topGlow, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $sideTint, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppInsetPanel(androidx.compose.ui.Modifier r49, androidx.compose.foundation.layout.PaddingValues r50, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppInsetPanel(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0258  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppMetaChip(java.lang.String r50, androidx.compose.ui.Modifier r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 627
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppMetaChip(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x028e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppDarkButton(final java.lang.String r56, final kotlin.jvm.functions.Function0<kotlin.Unit> r57, androidx.compose.ui.Modifier r58, boolean r59, androidx.compose.runtime.Composer r60, final int r61, final int r62) {
        /*
            Method dump skipped, instructions count: 977
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppDarkButton(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean AppDarkButton$lambda$20(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float AppDarkButton$lambda$21(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit AppDarkButton$lambda$23$lambda$22(boolean $enabled, State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(AppDarkButton$lambda$21($scale$delegate));
        graphicsLayer.setScaleY(AppDarkButton$lambda$21($scale$delegate));
        graphicsLayer.setAlpha($enabled ? 1.0f : 0.42f);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x027c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppPrimaryButton(final java.lang.String r56, final kotlin.jvm.functions.Function0<kotlin.Unit> r57, androidx.compose.ui.Modifier r58, boolean r59, androidx.compose.runtime.Composer r60, final int r61, final int r62) {
        /*
            Method dump skipped, instructions count: 954
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppPrimaryButton(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean AppPrimaryButton$lambda$27(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float AppPrimaryButton$lambda$28(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit AppPrimaryButton$lambda$30$lambda$29(boolean $enabled, State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(AppPrimaryButton$lambda$28($scale$delegate));
        graphicsLayer.setScaleY(AppPrimaryButton$lambda$28($scale$delegate));
        graphicsLayer.setAlpha($enabled ? 1.0f : 0.48f);
        return Unit.INSTANCE;
    }

    public static final void AppSecondaryButton(final String text, final Function0<Unit> onClick, Modifier modifier, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        Modifier.Companion modifier3;
        final boolean enabled2;
        Object obj;
        Object obj2;
        long m4161copywmQWz5c;
        Modifier m257clickableO2vRcR0;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled3;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(1735641453);
        ComposerKt.sourceInformation($composer3, "C(AppSecondaryButton)P(3,2,1)616@21254L15,618@21329L39,619@21406L25,620@21449L204,629@21775L128,627@21715L961:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled3 = z;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                enabled2 = z;
            } else {
                enabled2 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1735641453, $dirty, -1, "com.example.shellshot.ui.components.AppSecondaryButton (AppComponents.kt:615)");
            }
            ShellShotTokens tokens = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable);
            ShellShotColorTokens colors = tokens.getColors();
            ComposerKt.sourceInformationMarkerStart($composer3, 1504133620, "CC(remember):AppComponents.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            MutableInteractionSource interactionSource = (MutableInteractionSource) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State pressed$delegate = PressInteractionKt.collectIsPressedAsState(interactionSource, $composer3, 6);
            final State scale$delegate = AnimateAsStateKt.animateFloatAsState((AppSecondaryButton$lambda$34(pressed$delegate) && enabled2) ? 0.99f : 1.0f, AnimationSpecKt.spring$default(0.8f, 640.0f, null, 4, null), 0.0f, "app-secondary-button-scale", null, $composer3, 3120, 20);
            RoundedCornerShape shape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(tokens.getRadii().m7082getButtonD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer3, 1504147981, "CC(remember):AppComponents.kt#9igjgp");
            boolean changed = $composer3.changed(scale$delegate) | (($dirty & 7168) == 2048);
            Object rememberedValue2 = $composer3.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function1() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return AppComponentsKt.AppSecondaryButton$lambda$37$lambda$36(enabled2, scale$delegate, (GraphicsLayerScope) obj3);
                    }
                };
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier clip = ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifier3, (Function1) obj2), shape);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r30, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r30) : 0.86f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r30) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r30) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            Modifier m238borderxT4_qwU = BorderKt.m238borderxT4_qwU(BackgroundKt.m227backgroundbw27NRU$default(clip, m4161copywmQWz5c, null, 2, null), Dp.m6626constructorimpl(1), colors.m7066getCardStroke0d7_KjU(), shape);
            Modifier modifier5 = modifier3;
            int $dirty2 = $dirty;
            boolean enabled4 = enabled2;
            m257clickableO2vRcR0 = ClickableKt.m257clickableO2vRcR0(m238borderxT4_qwU, interactionSource, null, (r14 & 4) != 0 ? true : enabled4, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : null, onClick);
            Modifier m673paddingVpY3zN4 = PaddingKt.m673paddingVpY3zN4(m257clickableO2vRcR0, Dp.m6626constructorimpl(16), Dp.m6626constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m673paddingVpY3zN4);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3663setimpl(m3656constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl(m3656constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3656constructorimpl.getInserting() || !Intrinsics.areEqual(m3656constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3656constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3656constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3663setimpl(m3656constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1778956737, "C652@22512L10,649@22399L271:AppComponents.kt#e5eupx");
            $composer2 = $composer3;
            TextKt.m2697Text4IGK_g(text, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), colors.m7070getTextPrimary0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6486boximpl(TextAlign.INSTANCE.m6493getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleSmall(), $composer3, ($dirty2 & 14) | 196656, 0, 64984);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            enabled3 = enabled4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return AppComponentsKt.AppSecondaryButton$lambda$39(text, onClick, modifier4, enabled3, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final boolean AppSecondaryButton$lambda$34(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float AppSecondaryButton$lambda$35(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit AppSecondaryButton$lambda$37$lambda$36(boolean $enabled, State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(AppSecondaryButton$lambda$35($scale$delegate));
        graphicsLayer.setScaleY(AppSecondaryButton$lambda$35($scale$delegate));
        graphicsLayer.setAlpha($enabled ? 1.0f : 0.48f);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppStatusPill(final java.lang.String r56, final com.example.shellshot.ui.components.StatusTone r57, androidx.compose.ui.Modifier r58, androidx.compose.runtime.Composer r59, final int r60, final int r61) {
        /*
            Method dump skipped, instructions count: 844
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.AppStatusPill(java.lang.String, com.example.shellshot.ui.components.StatusTone, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void AppDivider(final Modifier modifier, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(-1308748735);
        ComposerKt.sourceInformation($composer2, "C(AppDivider)707@24249L15,708@24276L138:AppComponents.kt#e5eupx");
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
                ComposerKt.traceEventStart(-1308748735, $dirty, -1, "com.example.shellshot.ui.components.AppDivider (AppComponents.kt:706)");
            }
            ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer2, MaterialTheme.$stable).getColors();
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(SizeKt.m703height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Dp.m6626constructorimpl(1)), colors.m7067getDividerColor0d7_KjU(), null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.AppDivider$lambda$42(Modifier.this, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0274  */
    /* renamed from: AppIconPlate-3IgeMak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6926AppIconPlate3IgeMak(final androidx.compose.ui.graphics.vector.ImageVector r35, final long r36, androidx.compose.ui.Modifier r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instructions count: 654
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.m6926AppIconPlate3IgeMak(androidx.compose.ui.graphics.vector.ImageVector, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TemplateCard(final java.lang.String r36, final java.lang.String r37, final java.lang.String r38, final boolean r39, final java.lang.String r40, androidx.compose.ui.Modifier r41, final kotlin.jvm.functions.Function0<kotlin.Unit> r42, kotlin.jvm.functions.Function0<kotlin.Unit> r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 1087
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.TemplateCard(java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    static final boolean TemplateCard$lambda$49$lambda$48(Function0 $onDelete, SwipeToDismissBoxValue value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value != SwipeToDismissBoxValue.EndToStart || $onDelete == null) {
            return false;
        }
        $onDelete.invoke();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x077b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0570 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0402 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x05de A[LOOP:0: B:60:0x05d8->B:62:0x05de, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0629  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0720  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0765  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0aad  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x09b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit TemplateCard$lambda$58(androidx.compose.foundation.shape.RoundedCornerShape r119, boolean r120, com.example.shellshot.ui.theme.ShellShotColorTokens r121, java.lang.String r122, java.lang.String r123, java.util.List r124, androidx.compose.foundation.shape.RoundedCornerShape r125, kotlin.jvm.functions.Function0 r126, final java.lang.String r127, androidx.compose.runtime.Composer r128, int r129) {
        /*
            Method dump skipped, instructions count: 2739
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.TemplateCard$lambda$58(androidx.compose.foundation.shape.RoundedCornerShape, boolean, com.example.shellshot.ui.theme.ShellShotColorTokens, java.lang.String, java.lang.String, java.util.List, androidx.compose.foundation.shape.RoundedCornerShape, kotlin.jvm.functions.Function0, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit TemplateCard$lambda$58$lambda$57$lambda$56$lambda$55$lambda$54(String $actionText, RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C887@30995L200:AppComponents.kt#e5eupx");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1411436728, $changed, -1, "com.example.shellshot.ui.components.TemplateCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppComponents.kt:887)");
            }
            TextKt.m2697Text4IGK_g($actionText, (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateCard$lambda$60(RoundedCornerShape $shape, ShellShotColorTokens $colors, RowScope SwipeToDismissBox, Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        Intrinsics.checkNotNullParameter(SwipeToDismissBox, "$this$SwipeToDismissBox");
        ComposerKt.sourceInformation($composer, "C906@31562L763:AppComponents.kt#e5eupx");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1026077922, $changed, -1, "com.example.shellshot.ui.components.TemplateCard.<anonymous> (AppComponents.kt:906)");
            }
            Modifier clip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), $shape);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r6) : 0.88f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7060getAccentRed0d7_KjU()) : 0.0f);
            Modifier m227backgroundbw27NRU$default = BackgroundKt.m227backgroundbw27NRU$default(clip, m4161copywmQWz5c, null, 2, null);
            float m6626constructorimpl = Dp.m6626constructorimpl(1);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.22f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7060getAccentRed0d7_KjU()) : 0.0f);
            Modifier m676paddingqDBjuR0$default = PaddingKt.m676paddingqDBjuR0$default(BorderKt.m238borderxT4_qwU(m227backgroundbw27NRU$default, m6626constructorimpl, m4161copywmQWz5c2, $shape), 0.0f, 0.0f, Dp.m6626constructorimpl(22), 0.0f, 11, null);
            Alignment centerEnd = Alignment.INSTANCE.getCenterEnd();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerEnd, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, m676paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer m3656constructorimpl = Updater.m3656constructorimpl($composer);
            Updater.m3663setimpl(m3656constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl(m3656constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3656constructorimpl.getInserting() || !Intrinsics.areEqual(m3656constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3656constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3656constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3663setimpl(m3656constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -48751249, "C919@32123L184:AppComponents.kt#e5eupx");
            IconKt.m2154Iconww6aTOc(DeleteKt.getDelete(Icons.Rounded.INSTANCE), "删除模板", (Modifier) null, Color.INSTANCE.m4200getWhite0d7_KjU(), $composer, 3120, 4);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateCard$lambda$61(Function2 $cardContent, RowScope SwipeToDismissBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(SwipeToDismissBox, "$this$SwipeToDismissBox");
        ComposerKt.sourceInformation($composer, "C927@32365L13:AppComponents.kt#e5eupx");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-375058649, $changed, -1, "com.example.shellshot.ui.components.TemplateCard.<anonymous> (AppComponents.kt:927)");
            }
            $cardContent.invoke($composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0592  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SettingActionRow(final androidx.compose.ui.graphics.vector.ImageVector r77, final java.lang.String r78, final java.lang.String r79, androidx.compose.ui.Modifier r80, java.lang.String r81, com.example.shellshot.ui.components.StatusTone r82, final kotlin.jvm.functions.Function0<kotlin.Unit> r83, androidx.compose.runtime.Composer r84, final int r85, final int r86) {
        /*
            Method dump skipped, instructions count: 1460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.SettingActionRow(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, androidx.compose.ui.Modifier, java.lang.String, com.example.shellshot.ui.components.StatusTone, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x027c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SettingToggleRow(final androidx.compose.ui.graphics.vector.ImageVector r73, final java.lang.String r74, final java.lang.String r75, final boolean r76, androidx.compose.ui.Modifier r77, boolean r78, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r79, androidx.compose.runtime.Composer r80, final int r81, final int r82) {
        /*
            Method dump skipped, instructions count: 1231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.SettingToggleRow(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, boolean, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void LogStreamItem(final String module, final String timestamp, final String summary, final StatusTone tone, Modifier modifier, String details, final boolean expanded, final Function0<Unit> onToggleExpanded, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String str;
        Modifier.Companion modifier3;
        String details2;
        Object obj;
        Modifier m257clickableO2vRcR0;
        Composer $composer2;
        final Modifier modifier4;
        final String details3;
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(tone, "tone");
        Intrinsics.checkNotNullParameter(onToggleExpanded, "onToggleExpanded");
        Composer $composer3 = $composer.startRestartGroup(146700536);
        ComposerKt.sourceInformation($composer3, "C(LogStreamItem)P(3,6,5,7,2)1059@36267L15,1066@36466L39,1071@36685L2500,1062@36334L2851:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(module) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(timestamp) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(summary) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(tone.ordinal()) ? 2048 : 1024;
        }
        int i2 = i & 16;
        if (i2 != 0) {
            $dirty |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str = details;
        } else if ((196608 & $changed) == 0) {
            str = details;
            $dirty |= $composer3.changed(str) ? 131072 : 65536;
        } else {
            str = details;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(expanded) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onToggleExpanded) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            details3 = str;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                details2 = str;
            } else {
                details2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(146700536, $dirty, -1, "com.example.shellshot.ui.components.LogStreamItem (AppComponents.kt:1058)");
            }
            final ShellShotColorTokens colors = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer3, MaterialTheme.$stable).getColors();
            final long accent = iconTint(tone, colors);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -754690529, "CC(remember):AppComponents.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m257clickableO2vRcR0 = ClickableKt.m257clickableO2vRcR0(fillMaxWidth$default, (MutableInteractionSource) obj, null, (r14 & 4) != 0, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : null, onToggleExpanded);
            $composer2 = $composer3;
            Modifier modifier5 = modifier3;
            final String details4 = details2;
            AppSurfaceCard(m257clickableO2vRcR0, false, PaddingKt.m666PaddingValuesYgX7TsA(Dp.m6626constructorimpl(18), Dp.m6626constructorimpl(18)), ComposableLambdaKt.rememberComposableLambda(-2041098660, true, new Function3() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return AppComponentsKt.LogStreamItem$lambda$77(StatusTone.this, accent, colors, module, timestamp, expanded, summary, details4, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer2, 54), $composer2, 3456, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            details3 = details4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return AppComponentsKt.LogStreamItem$lambda$78(module, timestamp, summary, tone, modifier4, details3, expanded, onToggleExpanded, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LogStreamItem$lambda$77(com.example.shellshot.ui.components.StatusTone r96, long r97, final com.example.shellshot.ui.theme.ShellShotColorTokens r99, java.lang.String r100, final java.lang.String r101, final boolean r102, final java.lang.String r103, final java.lang.String r104, androidx.compose.foundation.layout.ColumnScope r105, androidx.compose.runtime.Composer r106, int r107) {
        /*
            Method dump skipped, instructions count: 1191
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.LogStreamItem$lambda$77(com.example.shellshot.ui.components.StatusTone, long, com.example.shellshot.ui.theme.ShellShotColorTokens, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit LogStreamItem$lambda$77$lambda$76$lambda$75(final ShellShotColorTokens $colors, String $timestamp, final boolean $expanded, String $summary, final String $details, ColumnScope AppInsetPanel, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AppInsetPanel, "$this$AppInsetPanel");
        ComposerKt.sourceInformation($composer, "C1102@37989L10,1100@37903L175,1107@38179L10,1105@38095L347,1117@38711L444,1113@38459L696:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(AppInsetPanel) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1915492773, $dirty, -1, "com.example.shellshot.ui.components.LogStreamItem.<anonymous>.<anonymous>.<anonymous> (AppComponents.kt:1100)");
            }
            int $dirty2 = $dirty;
            TextKt.m2697Text4IGK_g($timestamp, (Modifier) null, $colors.m7072getTextTertiary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelLarge(), $composer, 0, 0, 65530);
            TextKt.m2697Text4IGK_g($summary, (Modifier) null, $colors.m7070getTextPrimary0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6543getEllipsisgIe3tQ8(), false, $expanded ? Integer.MAX_VALUE : 2, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 48, 55258);
            String str = $details;
            AnimatedVisibilityKt.AnimatedVisibility(AppInsetPanel, !(str == null || StringsKt.isBlank(str)), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(160, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.tween$default(180, 0, null, 6, null), null, false, null, 14, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(140, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(140, 0, null, 6, null), null, false, null, 14, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-1751594189, true, new Function3() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AppComponentsKt.LogStreamItem$lambda$77$lambda$76$lambda$75$lambda$74($expanded, $details, $colors, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, ($dirty2 & 14) | 1600512, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogStreamItem$lambda$77$lambda$76$lambda$75$lambda$74(boolean $expanded, String $details, ShellShotColorTokens $colors, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C1120@38911L10,1118@38733L404:AppComponents.kt#e5eupx");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1751594189, $changed, -1, "com.example.shellshot.ui.components.LogStreamItem.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppComponents.kt:1118)");
        }
        String str = "";
        if (!$expanded) {
            String str2 = (String) SequencesKt.firstOrNull(StringsKt.lineSequence($details == null ? "" : $details));
            if (str2 != null) {
                str = str2;
            }
        } else if ($details != null) {
            str = $details;
        }
        TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall();
        TextKt.m2697Text4IGK_g(str, (Modifier) null, $colors.m7071getTextSecondary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6543getEllipsisgIe3tQ8(), false, $expanded ? Integer.MAX_VALUE : 1, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall, $composer, 0, 48, 55290);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void LiquidGlassBottomBar(final List<GlassBottomBarItem> items, final int selectedIndex, final Function1<? super Integer, Unit> onSelect, Modifier modifier, LiquidGlassBottomBarStyle style, Composer $composer, final int $changed, final int i) {
        int i2;
        Modifier modifier2;
        LiquidGlassBottomBarStyle liquidGlassBottomBarStyle;
        final LiquidGlassBottomBarStyle style2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        final Modifier modifier3;
        final LiquidGlassBottomBarStyle style3;
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        Composer $composer2 = $composer.startRestartGroup(-1827604326);
        ComposerKt.sourceInformation($composer2, "C(LiquidGlassBottomBar)P(!1,3,2)1139@39470L15,1153@40041L11,1154@40143L11,1159@40366L14080,1144@39665L14781:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(items) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            i2 = selectedIndex;
            $dirty |= $composer2.changed(i2) ? 32 : 16;
        } else {
            i2 = selectedIndex;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onSelect) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            liquidGlassBottomBarStyle = style;
        } else if (($changed & 24576) == 0) {
            liquidGlassBottomBarStyle = style;
            $dirty |= $composer2.changed(liquidGlassBottomBarStyle) ? 16384 : 8192;
        } else {
            liquidGlassBottomBarStyle = style;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            style3 = liquidGlassBottomBarStyle;
        } else {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i4 == 0) {
                style2 = liquidGlassBottomBarStyle;
            } else {
                style2 = LiquidGlassBottomBarStyleDefaults.INSTANCE.getBalanced();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1827604326, $dirty2, -1, "com.example.shellshot.ui.components.LiquidGlassBottomBar (AppComponents.kt:1138)");
            }
            ShellShotTokens tokens = DesignTokensKt.getShellShotTokens(MaterialTheme.INSTANCE, $composer2, MaterialTheme.$stable);
            final ShellShotColorTokens colors = tokens.getColors();
            final RoundedCornerShape shape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(style2.m7009getCornerRadiusD9Ej5fM());
            ShellShotShadowStyle floatingShadow = ShellShotShadowStyle.m7089copyhOCwus4$default(tokens.getShadows().getFloating(), style2.m7011getFloatingShadowD9Ej5fM(), 0L, 0L, 6, null);
            Modifier clip = ClipKt.clip(appShadow(SizeKt.m703height3ABfNKs(modifier2, style2.m7007getBarHeightD9Ej5fM()), floatingShadow, shape), shape);
            Brush.Companion companion = Brush.INSTANCE;
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : style2.getContainerAlpha() * 0.48f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : style2.getContainerAlpha() * 0.72f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface()) : 0.0f);
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : style2.getContainerAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurfaceVariant()) : 0.0f);
            Modifier background$default = BackgroundKt.background$default(clip, Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(m4161copywmQWz5c3)}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            float m7008getBorderWidthD9Ej5fM = style2.m7008getBorderWidthD9Ej5fM();
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r17) : style2.getBorderAlpha(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(colors.m7066getCardStroke0d7_KjU()) : 0.0f);
            final int i5 = i2;
            Modifier modifier4 = modifier2;
            BoxWithConstraintsKt.BoxWithConstraints(BorderKt.m238borderxT4_qwU(background$default, m7008getBorderWidthD9Ej5fM, m4161copywmQWz5c4, shape), null, false, ComposableLambdaKt.rememberComposableLambda(2028817904, true, new Function3() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AppComponentsKt.LiquidGlassBottomBar$lambda$111(items, style2, i5, shape, colors, onSelect, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            style3 = style2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.LiquidGlassBottomBar$lambda$112(items, selectedIndex, onSelect, modifier3, style3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0c25  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0cc4  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0d45  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0dac  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0de1  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0de6  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0d56 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0cd1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0c2b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0e7b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x095e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0915  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0681  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x063b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x05b7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0570  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0903  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x090f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x09c9  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0c19  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LiquidGlassBottomBar$lambda$111(java.util.List r103, final com.example.shellshot.ui.components.LiquidGlassBottomBarStyle r104, int r105, androidx.compose.foundation.shape.RoundedCornerShape r106, com.example.shellshot.ui.theme.ShellShotColorTokens r107, kotlin.jvm.functions.Function1 r108, androidx.compose.foundation.layout.BoxWithConstraintsScope r109, androidx.compose.runtime.Composer r110, int r111) {
        /*
            Method dump skipped, instructions count: 3713
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.LiquidGlassBottomBar$lambda$111(java.util.List, com.example.shellshot.ui.components.LiquidGlassBottomBarStyle, int, androidx.compose.foundation.shape.RoundedCornerShape, com.example.shellshot.ui.theme.ShellShotColorTokens, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiquidGlassBottomBar$lambda$111$lambda$80(MutableIntState $previousSelectedIndex$delegate) {
        return $previousSelectedIndex$delegate.getIntValue();
    }

    private static final float LiquidGlassBottomBar$lambda$111$lambda$83(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1776)
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1751)
        	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:141)
        	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:134)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    static final androidx.compose.ui.draw.DrawResult LiquidGlassBottomBar$lambda$111$lambda$88$lambda$87(com.example.shellshot.ui.components.LiquidGlassBottomBarStyle r21, androidx.compose.ui.draw.CacheDrawScope r22) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppComponentsKt.LiquidGlassBottomBar$lambda$111$lambda$88$lambda$87(com.example.shellshot.ui.components.LiquidGlassBottomBarStyle, androidx.compose.ui.draw.CacheDrawScope):androidx.compose.ui.draw.DrawResult");
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$88$lambda$87$lambda$86(Brush $topHighlight, Brush $bottomShade, Brush $innerEdge, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $topHighlight, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $bottomShade, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $innerEdge, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        return Unit.INSTANCE;
    }

    static final DrawResult LiquidGlassBottomBar$lambda$111$lambda$95$lambda$91$lambda$90(CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        final Brush diagonalGloss;
        long m4161copywmQWz5c3;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.24f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        diagonalGloss = companion.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.18f, 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.78f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.03f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
        final Brush lowerShadow = Brush.Companion.m4120verticalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c3)}), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.56f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 8, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppComponentsKt.LiquidGlassBottomBar$lambda$111$lambda$95$lambda$91$lambda$90$lambda$89(Brush.this, lowerShadow, (DrawScope) obj);
            }
        });
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$95$lambda$91$lambda$90$lambda$89(Brush $diagonalGloss, Brush $lowerShadow, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $diagonalGloss, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $lowerShadow, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        return Unit.INSTANCE;
    }

    static final DrawResult LiquidGlassBottomBar$lambda$111$lambda$95$lambda$94$lambda$93(CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.28f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush surfaceGlow = Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, 0.36f * Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 10, (Object) null);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush softGlassBand = Brush.Companion.m4112horizontalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppComponentsKt.LiquidGlassBottomBar$lambda$111$lambda$95$lambda$94$lambda$93$lambda$92(Brush.this, softGlassBand, (DrawScope) obj);
            }
        });
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$95$lambda$94$lambda$93$lambda$92(Brush $surfaceGlow, Brush $softGlassBand, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $surfaceGlow, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $softGlassBand, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        return Unit.INSTANCE;
    }

    private static final long LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$96(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final float LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$97(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$98(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$99(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$102$lambda$101(Function1 $onSelect, int $index) {
        $onSelect.invoke(Integer.valueOf($index));
        return Unit.INSTANCE;
    }

    private static final float LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$108$lambda$103(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$108$lambda$105$lambda$104(State $iconScale$delegate, State $contentAlpha$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$98($iconScale$delegate));
        graphicsLayer.setScaleY(LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$98($iconScale$delegate));
        graphicsLayer.setAlpha(LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$97($contentAlpha$delegate));
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$108$lambda$107$lambda$106(State $contentAlpha$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha(LiquidGlassBottomBar$lambda$111$lambda$110$lambda$109$lambda$97($contentAlpha$delegate));
        return Unit.INSTANCE;
    }

    public static final void PremiumBottomBar(final List<GlassBottomBarItem> items, final int selectedIndex, final Function1<? super Integer, Unit> onSelect, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier.Companion modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        Composer $composer3 = $composer.startRestartGroup(560670286);
        ComposerKt.sourceInformation($composer3, "C(PremiumBottomBar)P(!1,3,2)1493@54617L207:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(items) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selectedIndex) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onSelect) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(modifier) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            $composer2 = $composer3;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(560670286, $dirty2, -1, "com.example.shellshot.ui.components.PremiumBottomBar (AppComponents.kt:1492)");
            }
            LiquidGlassBottomBar(items, selectedIndex, onSelect, modifier2, LiquidGlassBottomBarStyleDefaults.INSTANCE.getBalanced(), $composer3, ($dirty2 & 14) | 24576 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.PremiumBottomBar$lambda$113(items, selectedIndex, onSelect, modifier3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void LiquidGlassBottomBarPreview(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-568039228);
        ComposerKt.sourceInformation($composer2, "C(LiquidGlassBottomBarPreview)1511@55146L1002:AppComponents.kt#e5eupx");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-568039228, $changed, -1, "com.example.shellshot.ui.components.LiquidGlassBottomBarPreview (AppComponents.kt:1510)");
            }
            ThemeKt.ShellShotTheme(false, ComposableSingletons$AppComponentsKt.INSTANCE.getLambda$1370548465$app_debug(), $composer2, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.LiquidGlassBottomBarPreview$lambda$114($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long iconTint(StatusTone $this$iconTint, ShellShotColorTokens colors) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$iconTint.ordinal()]) {
            case 1:
                return colors.m7058getAccentGreen0d7_KjU();
            case 2:
                return colors.m7054getAccentAmber0d7_KjU();
            case 3:
                return colors.m7060getAccentRed0d7_KjU();
            case 4:
                return colors.m7056getAccentBlue0d7_KjU();
            case 5:
                return colors.m7071getTextSecondary0d7_KjU();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final String label(StatusTone $this$label) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$label.ordinal()]) {
            case 1:
                return "成功";
            case 2:
                return "注意";
            case 3:
                return "错误";
            case 4:
                return "信息";
            case 5:
                return "状态";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final Modifier appShadow(Modifier $this$appShadow, ShellShotShadowStyle shadow, Shape shape) {
        return ShadowKt.m3826shadows4CzXII($this$appShadow, shadow.m7095getElevationD9Ej5fM(), shape, false, shadow.m7094getAmbientColor0d7_KjU(), shadow.m7096getSpotColor0d7_KjU());
    }

    /* renamed from: BackgroundWash-RPmYEkk, reason: not valid java name */
    private static final void m6928BackgroundWashRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        long m4161copywmQWz5c;
        Brush m4126radialGradientP_VxKs;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-757418497);
        ComposerKt.sourceInformation($composer3, "C(BackgroundWash)P(1,0:c#ui.graphics.Color)1577@57145L59,1578@57209L380:AppComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(color) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-757418497, $dirty2, -1, "com.example.shellshot.ui.components.BackgroundWash (AppComponents.kt:1576)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -2029699078, "CC(remember):AppComponents.kt#9igjgp");
            boolean z = ($dirty2 & 112) == 32;
            Object rememberedValue = $composer3.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                m4161copywmQWz5c = Color.m4161copywmQWz5c(color, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(color) : Color.m4165getAlphaimpl(color) * 0.48f, (r12 & 2) != 0 ? Color.m4169getRedimpl(color) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(color) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(color) : 0.0f);
                rememberedValue = Color.m4153boximpl(m4161copywmQWz5c);
                $composer3.updateRememberedValue(rememberedValue);
            }
            long middleColor = ((Color) rememberedValue).m4173unboximpl();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier m4323graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m4323graphicsLayerAp8cVGQ$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null);
            m4126radialGradientP_VxKs = Brush.INSTANCE.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(color), Color.m4153boximpl(middleColor), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : 0L, (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
            $composer2 = $composer3;
            BoxKt.Box(BackgroundKt.background$default(m4323graphicsLayerAp8cVGQ$default, m4126radialGradientP_VxKs, RoundedCornerShapeKt.getCircleShape(), 0.0f, 4, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppComponentsKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppComponentsKt.BackgroundWash_RPmYEkk$lambda$116(Modifier.this, color, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
