package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: ExpressiveNavigationBar.kt */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001aW\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-H\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u009d\u0001\u00100\u001a\u00020!2\u0006\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020!0,2\u0011\u00104\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u00105\u001a\u0002022\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u00108\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=H\u0001ø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a\u0018\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020AH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u000e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u0014\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0015\u0010\u0010\"\u0016\u0010\u0016\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0017\u0010\u0010\"\u0016\u0010\u0018\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0019\u0010\u0010\"\u0016\u0010\u001a\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001b\u0010\u0010\"\u0010\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0010\u0010\u001d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u001e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"ActiveIconColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ActiveIndicatorColor", "ActiveIndicatorShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ActiveLabelTextColor", "IconSize", "Landroidx/compose/ui/unit/Dp;", "F", "InactiveIconColor", "InactiveLabelTextColor", "LabelTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "NavigationBarHeight", "StartIconIndicatorHorizontalPadding", "getStartIconIndicatorHorizontalPadding", "()F", "StartIconIndicatorVerticalPadding", "getStartIconIndicatorVerticalPadding", "StartIconItemActiveIndicatorHeight", "StartIconToLabelPadding", "getStartIconToLabelPadding", "TopIconIndicatorHorizontalPadding", "getTopIconIndicatorHorizontalPadding", "TopIconIndicatorToLabelPadding", "getTopIconIndicatorToLabelPadding", "TopIconIndicatorVerticalPadding", "getTopIconIndicatorVerticalPadding", "TopIconItemActiveIndicatorHeight", "TopIconItemActiveIndicatorWidth", "TopIconItemVerticalPadding", "getTopIconItemVerticalPadding", "ExpressiveNavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/material3/NavigationBarArrangement;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ExpressiveNavigationBar-NiJtXQ4", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/foundation/layout/WindowInsets;ILkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ExpressiveNavigationBarItem", "selected", "", "onClick", "icon", "enabled", "label", "badge", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "colors", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "ExpressiveNavigationBarItem-pli-t6k", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "calculateCenteredContentHorizontalPadding", "", "itemsCount", "barWidth", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ExpressiveNavigationBarKt {
    private static final float StartIconIndicatorHorizontalPadding;
    private static final float StartIconIndicatorVerticalPadding;
    private static final float StartIconToLabelPadding;
    private static final float TopIconIndicatorHorizontalPadding;
    private static final float TopIconIndicatorToLabelPadding;
    private static final float TopIconIndicatorVerticalPadding;
    private static final float IconSize = Dp.m6626constructorimpl((float) 24.0d);
    private static final float TopIconItemActiveIndicatorWidth = Dp.m6626constructorimpl(56);
    private static final float TopIconItemActiveIndicatorHeight = Dp.m6626constructorimpl(32);
    private static final float StartIconItemActiveIndicatorHeight = Dp.m6626constructorimpl(40);
    private static final TypographyKeyTokens LabelTextFont = TypographyKeyTokens.LabelMedium;
    private static final ShapeKeyTokens ActiveIndicatorShape = ShapeKeyTokens.CornerFull;
    private static final ColorSchemeKeyTokens ActiveIconColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ActiveLabelTextColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ActiveIndicatorColor = ColorSchemeKeyTokens.SecondaryContainer;
    private static final ColorSchemeKeyTokens InactiveIconColor = ColorSchemeKeyTokens.OnSurfaceVariant;
    private static final ColorSchemeKeyTokens InactiveLabelTextColor = ColorSchemeKeyTokens.OnSurfaceVariant;
    private static final float NavigationBarHeight = Dp.m6626constructorimpl(64);
    private static final float TopIconItemVerticalPadding = Dp.m6626constructorimpl(6);

    /* renamed from: ExpressiveNavigationBar-NiJtXQ4, reason: not valid java name */
    public static final void m2099ExpressiveNavigationBarNiJtXQ4(Modifier modifier, long containerColor, long contentColor, WindowInsets windowInsets, int arrangement, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        final WindowInsets windowInsets2;
        int i2;
        final Modifier.Companion modifier3;
        long contentColor3;
        final int arrangement2;
        long containerColor3;
        Composer $composer2;
        final Modifier modifier4;
        final WindowInsets windowInsets3;
        final long containerColor4;
        final int arrangement3;
        final long contentColor4;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-1171105467);
        ComposerKt.sourceInformation($composer3, "C(ExpressiveNavigationBar)P(4,1:c#ui.graphics.Color,3:c#ui.graphics.Color,5,0:c#material3.NavigationBarArrangement)83@3944L14,84@4018L12,85@4097L12,92@4328L779,89@4244L863:ExpressiveNavigationBar.kt#uh7d8r");
        int $dirty = $changed;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i6 = 32;
                    $dirty |= i6;
                }
            } else {
                containerColor2 = containerColor;
            }
            i6 = 16;
            $dirty |= i6;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                contentColor2 = contentColor;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            contentColor2 = contentColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                i2 = arrangement;
                if ($composer3.changed(i2)) {
                    i3 = 16384;
                    $dirty |= i3;
                }
            } else {
                i2 = arrangement;
            }
            i3 = 8192;
            $dirty |= i3;
        } else {
            i2 = arrangement;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            containerColor4 = containerColor2;
            windowInsets3 = windowInsets2;
            contentColor4 = contentColor2;
            arrangement3 = i2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    containerColor2 = ExpressiveNavigationBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 4) != 0) {
                    contentColor2 = ExpressiveNavigationBarDefaults.INSTANCE.getContentColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    windowInsets2 = ExpressiveNavigationBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 16) != 0) {
                    arrangement2 = ExpressiveNavigationBarDefaults.INSTANCE.m2098getArrangementEbr7WPU();
                    $dirty &= -57345;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                } else {
                    contentColor3 = contentColor2;
                    arrangement2 = i2;
                    containerColor3 = containerColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier3 = modifier2;
                contentColor3 = contentColor2;
                arrangement2 = i2;
                containerColor3 = containerColor2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1171105467, $dirty, -1, "androidx.compose.material3.ExpressiveNavigationBar (ExpressiveNavigationBar.kt:88)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2547SurfaceT9BRK9s(null, null, containerColor3, contentColor3, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1573697866, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBar$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    float f;
                    CenteredContentMeasurePolicy centeredContentMeasurePolicy;
                    ComposerKt.sourceInformation($composer4, "C93@4338L763:ExpressiveNavigationBar.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1573697866, $changed2, -1, "androidx.compose.material3.ExpressiveNavigationBar.<anonymous> (ExpressiveNavigationBar.kt:93)");
                        }
                        Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(Modifier.this, windowInsets2);
                        f = ExpressiveNavigationBarKt.NavigationBarHeight;
                        Modifier selectableGroup = SelectableGroupKt.selectableGroup(SizeKt.m702defaultMinSizeVpY3zN4$default(windowInsetsPadding, 0.0f, f, 1, null));
                        int i8 = arrangement2;
                        if (NavigationBarArrangement.m2256equalsimpl0(i8, NavigationBarArrangement.INSTANCE.m2261getEqualWeightEbr7WPU())) {
                            centeredContentMeasurePolicy = new EqualWeightContentMeasurePolicy();
                        } else if (NavigationBarArrangement.m2256equalsimpl0(i8, NavigationBarArrangement.INSTANCE.m2260getCenteredEbr7WPU())) {
                            centeredContentMeasurePolicy = new CenteredContentMeasurePolicy();
                        } else {
                            throw new IllegalArgumentException("Invalid ItemsArrangement value.");
                        }
                        Function2 content$iv = function2;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer4, selectableGroup);
                        Function0 factory$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(factory$iv$iv);
                        } else {
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv = Updater.m3656constructorimpl($composer4);
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, centeredContentMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), block$iv$iv);
                        }
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        content$iv.invoke($composer4, Integer.valueOf(($changed$iv$iv >> 6) & 14));
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty << 3) & 896) | 12582912 | (($dirty << 3) & 7168), 115);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            windowInsets3 = windowInsets2;
            containerColor4 = containerColor3;
            arrangement3 = arrangement2;
            contentColor4 = contentColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBar$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    ExpressiveNavigationBarKt.m2099ExpressiveNavigationBarNiJtXQ4(Modifier.this, containerColor4, contentColor4, windowInsets3, arrangement3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: ExpressiveNavigationBarItem-pli-t6k, reason: not valid java name */
    public static final void m2100ExpressiveNavigationBarItemplit6k(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, int iconPosition, NavigationItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean enabled2;
        Function2 label;
        Function2 badge;
        int i2;
        int i3;
        int iconPosition2;
        NavigationItemColors colors2;
        int $dirty;
        MutableInteractionSource interactionSource2;
        NavigationItemColors colors3;
        Modifier modifier3;
        boolean enabled3;
        Function2 label2;
        Function2 badge2;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        final MutableInteractionSource interactionSource4;
        final NavigationItemColors colors4;
        final Modifier modifier4;
        final boolean enabled4;
        final Function2 label3;
        final Function2 badge3;
        final int iconPosition3;
        Object value$iv;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1250474866);
        ComposerKt.sourceInformation($composer3, "C(ExpressiveNavigationBarItem)P(9,8,3,7,2,6!1,4:c#material3.NavigationItemIconPosition)195@8610L8,219@9391L5,220@9444L5,215@9259L800:ExpressiveNavigationBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty2 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            label = function22;
        } else if ((196608 & $changed) == 0) {
            label = function22;
            $dirty2 |= $composer3.changedInstance(label) ? 131072 : 65536;
        } else {
            label = function22;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
            badge = function23;
        } else if ((1572864 & $changed) == 0) {
            badge = function23;
            $dirty2 |= $composer3.changedInstance(badge) ? 1048576 : 524288;
        } else {
            badge = function23;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty2 |= $composer3.changed(iconPosition) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(colors)) {
                i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty2 |= i4;
            }
            i4 = 33554432;
            $dirty2 |= i4;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty2 |= 805306368;
            i3 = i10;
        } else if (($changed & 805306368) == 0) {
            i3 = i10;
            $dirty2 |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        } else {
            i3 = i10;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            iconPosition3 = iconPosition;
            interactionSource4 = interactionSource;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            label3 = label;
            badge3 = badge;
            colors4 = colors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if (i7 != 0) {
                    label = null;
                }
                if (i8 != 0) {
                    badge = null;
                }
                iconPosition2 = i2 != 0 ? NavigationItemIconPosition.INSTANCE.m2314getTopxw1Ddg() : iconPosition;
                if ((i & 256) != 0) {
                    colors2 = ExpressiveNavigationBarItemDefaults.INSTANCE.colors($composer3, 6);
                    $dirty = $dirty3 & (-234881025);
                } else {
                    colors2 = colors;
                    $dirty = $dirty3;
                }
                if (i3 != 0) {
                    interactionSource2 = null;
                    colors3 = colors2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    label2 = label;
                    badge2 = badge;
                } else {
                    interactionSource2 = interactionSource;
                    colors3 = colors2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    label2 = label;
                    badge2 = badge;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 256) != 0) {
                    colors3 = colors;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty3 & (-234881025);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    label2 = label;
                    badge2 = badge;
                    iconPosition2 = iconPosition;
                } else {
                    iconPosition2 = iconPosition;
                    colors3 = colors;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty3;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    label2 = label;
                    badge2 = badge;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1250474866, $dirty, -1, "androidx.compose.material3.ExpressiveNavigationBarItem (ExpressiveNavigationBar.kt:197)");
            }
            $composer3.startReplaceGroup(-986536477);
            ComposerKt.sourceInformation($composer3, "199@8762L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, -986535826, "CC(remember):ExpressiveNavigationBar.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                interactionSource3 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
            }
            $composer3.endReplaceGroup();
            boolean isIconPositionTop = NavigationItemIconPosition.m2309equalsimpl0(iconPosition2, NavigationItemIconPosition.INSTANCE.m2314getTopxw1Ddg());
            float indicatorHorizontalPadding = isIconPositionTop ? TopIconIndicatorHorizontalPadding : StartIconIndicatorHorizontalPadding;
            float indicatorVerticalPadding = isIconPositionTop ? TopIconIndicatorVerticalPadding : StartIconIndicatorVerticalPadding;
            int iconPosition4 = iconPosition2;
            $composer2 = $composer3;
            NavigationItemKt.m2315NavigationItemSHbi2eg(z, function02, function24, TypographyKt.getValue(LabelTextFont, $composer3, 6), ShapesKt.getValue(ActiveIndicatorShape, $composer3, 6), TopIconItemActiveIndicatorWidth, indicatorHorizontalPadding, indicatorVerticalPadding, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, colors3, modifier3, enabled3, label2, badge2, iconPosition4, interactionSource3, $composer2, ($dirty & 14) | 906166272 | ($dirty & 112) | ($dirty & 896), 6 | (($dirty >> 21) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (3670016 & ($dirty >> 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource4 = interactionSource2;
            colors4 = colors3;
            modifier4 = modifier3;
            enabled4 = enabled3;
            label3 = label2;
            badge3 = badge2;
            iconPosition3 = iconPosition4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExpressiveNavigationBarKt$ExpressiveNavigationBarItem$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i11) {
                    ExpressiveNavigationBarKt.m2100ExpressiveNavigationBarItemplit6k(selected, function0, function2, modifier4, enabled4, label3, badge3, iconPosition3, colors4, interactionSource4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateCenteredContentHorizontalPadding(int itemsCount, int barWidth) {
        if (itemsCount > 6) {
            return 0;
        }
        float paddingPercentage = ((100 - ((itemsCount + 3) * 10)) / 2.0f) / 100.0f;
        return MathKt.roundToInt(barWidth * paddingPercentage);
    }

    static {
        float arg0$iv = TopIconItemActiveIndicatorHeight;
        float other$iv = IconSize;
        TopIconIndicatorVerticalPadding = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = TopIconItemActiveIndicatorWidth;
        float other$iv2 = IconSize;
        TopIconIndicatorHorizontalPadding = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv2 - other$iv2) / 2);
        float arg0$iv3 = StartIconItemActiveIndicatorHeight;
        float other$iv3 = IconSize;
        StartIconIndicatorVerticalPadding = Dp.m6626constructorimpl(Dp.m6626constructorimpl(arg0$iv3 - other$iv3) / 2);
        TopIconIndicatorToLabelPadding = Dp.m6626constructorimpl(4);
        StartIconIndicatorHorizontalPadding = Dp.m6626constructorimpl(16);
        StartIconToLabelPadding = Dp.m6626constructorimpl(4);
    }

    public static final float getTopIconItemVerticalPadding() {
        return TopIconItemVerticalPadding;
    }

    public static final float getTopIconIndicatorVerticalPadding() {
        return TopIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorHorizontalPadding() {
        return TopIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconIndicatorVerticalPadding() {
        return StartIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorToLabelPadding() {
        return TopIconIndicatorToLabelPadding;
    }

    public static final float getStartIconIndicatorHorizontalPadding() {
        return StartIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconToLabelPadding() {
        return StartIconToLabelPadding;
    }
}
