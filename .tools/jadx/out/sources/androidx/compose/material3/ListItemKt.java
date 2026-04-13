package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.ListTokens;
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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a©\u0001\u0010\u0016\u001a\u00020\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001e\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010$\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001at\u0010'\u001a\u00020\u00172\u0013\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\u0013\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010,\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001aH\u0003¢\u0006\u0002\u0010-\u001a5\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001aH\u0003ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a\u001a\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u000208H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aV\u0010;\u001a\u00020<*\u00020=2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020<2\u0006\u00107\u001a\u0002082\u0006\u00106\u001a\u00020<2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001aN\u0010G\u001a\u00020<*\u00020=2\u0006\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020<2\u0006\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020<2\u0006\u0010M\u001a\u00020<2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u0014\u0010P\u001a\u00020Q*\u00020R2\u0006\u0010S\u001a\u00020<H\u0002\u001an\u0010T\u001a\u00020U*\u00020V2\u0006\u0010W\u001a\u00020<2\u0006\u0010X\u001a\u00020<2\b\u0010Y\u001a\u0004\u0018\u00010Z2\b\u0010[\u001a\u0004\u0018\u00010Z2\b\u0010\\\u001a\u0004\u0018\u00010Z2\b\u0010]\u001a\u0004\u0018\u00010Z2\b\u0010^\u001a\u0004\u0018\u00010Z2\u0006\u0010_\u001a\u00020Q2\u0006\u0010`\u001a\u00020<2\u0006\u0010a\u001a\u00020<2\u0006\u0010b\u001a\u00020<H\u0002\u001a\u0014\u0010c\u001a\u00020<*\u00020<2\u0006\u0010d\u001a\u00020<H\u0002\"\u001e\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\u0005\"\u001e\u0010\n\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\u0005\"\u001e\u0010\r\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0005\"\u001e\u0010\u0010\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0005\"\u001e\u0010\u0013\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006e"}, d2 = {"LeadingContentEndPadding", "Landroidx/compose/ui/unit/Dp;", "getLeadingContentEndPadding$annotations", "()V", "getLeadingContentEndPadding", "()F", "F", "ListItemEndPadding", "getListItemEndPadding$annotations", "getListItemEndPadding", "ListItemStartPadding", "getListItemStartPadding$annotations", "getListItemStartPadding", "ListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding$annotations", "getListItemThreeLineVerticalPadding", "ListItemVerticalPadding", "getListItemVerticalPadding$annotations", "getListItemVerticalPadding", "TrailingContentStartPadding", "getTrailingContentStartPadding$annotations", "getTrailingContentStartPadding", "ListItem", "", "headlineContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "overlineContent", "supportingContent", "leadingContent", "trailingContent", "colors", "Landroidx/compose/material3/ListItemColors;", "tonalElevation", "shadowElevation", "ListItem-HXNGIdc", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ListItemColors;FFLandroidx/compose/runtime/Composer;II)V", "ListItemLayout", "leading", "trailing", "headline", "overline", "supporting", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ProvideTextStyleFromToken", "color", "Landroidx/compose/ui/graphics/Color;", "textToken", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "content", "ProvideTextStyleFromToken-3J-VO9M", "(JLandroidx/compose/material3/tokens/TypographyKeyTokens;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "verticalPadding", "listItemType", "Landroidx/compose/material3/ListItemType;", "verticalPadding-yh95HIg", "(I)F", "calculateHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "leadingHeight", "trailingHeight", "headlineHeight", "overlineHeight", "supportingHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "calculateHeight-N4Jib3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIIJ)I", "calculateWidth", "leadingWidth", "trailingWidth", "headlineWidth", "overlineWidth", "supportingWidth", "horizontalPadding", "calculateWidth-yeHjK3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIJ)I", "isSupportingMultilineHeuristic", "", "Landroidx/compose/ui/unit/Density;", "estimatedSupportingHeight", "place", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "width", "height", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "headlinePlaceable", "overlinePlaceable", "supportingPlaceable", "isThreeLine", "startPadding", "endPadding", "topPadding", "subtractConstraintSafely", "n", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ListItemKt {
    private static final float ListItemVerticalPadding = Dp.m6626constructorimpl(8);
    private static final float ListItemThreeLineVerticalPadding = Dp.m6626constructorimpl(12);
    private static final float ListItemStartPadding = Dp.m6626constructorimpl(16);
    private static final float ListItemEndPadding = Dp.m6626constructorimpl(16);
    private static final float LeadingContentEndPadding = Dp.m6626constructorimpl(16);
    private static final float TrailingContentStartPadding = Dp.m6626constructorimpl(16);

    public static /* synthetic */ void getLeadingContentEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemStartPadding$annotations() {
    }

    public static /* synthetic */ void getListItemThreeLineVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getListItemVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getTrailingContentStartPadding$annotations() {
    }

    /* renamed from: ListItem-HXNGIdc, reason: not valid java name */
    public static final void m2199ListItemHXNGIdc(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, ListItemColors colors, float tonalElevation, float shadowElevation, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2 overlineContent;
        Function2 supportingContent;
        Function2 function26;
        int i2;
        int i3;
        float f;
        Function2 leadingContent;
        Function2 trailingContent;
        int i4;
        int i5;
        Composer $composer2;
        ListItemColors colors2;
        float shadowElevation2;
        float tonalElevation2;
        final ListItemColors colors3;
        Function2 leadingContent2;
        Function2 leadingContent3;
        final Function2 decoratedSupportingContent;
        Function2 decoratedSupportingContent2;
        final Function2 decoratedOverlineContent;
        Function2 decoratedLeadingContent;
        Function2 decoratedTrailingContent;
        final Function2 trailingContent2;
        final Function2 overlineContent2;
        final Function2 trailingContent3;
        final float tonalElevation3;
        final float shadowElevation3;
        final ListItemColors colors4;
        final Modifier modifier3;
        final Function2 leadingContent4;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-1647707763);
        ComposerKt.sourceInformation($composer3, "C(ListItem)P(1,3,4,6,2,8!1,7:c#ui.unit.Dp,5:c#ui.unit.Dp)100@4555L8,104@4735L176,157@6562L5,162@6761L288,155@6438L611:ListItem.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            overlineContent = function22;
        } else if (($changed & 384) == 0) {
            overlineContent = function22;
            $dirty |= $composer3.changedInstance(overlineContent) ? 256 : 128;
        } else {
            overlineContent = function22;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty |= 3072;
            supportingContent = function23;
        } else if (($changed & 3072) == 0) {
            supportingContent = function23;
            $dirty |= $composer3.changedInstance(supportingContent) ? 2048 : 1024;
        } else {
            supportingContent = function23;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function24) ? 16384 : 8192;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function26 = function25;
        } else if ((196608 & $changed) == 0) {
            function26 = function25;
            $dirty |= $composer3.changedInstance(function26) ? 131072 : 65536;
        } else {
            function26 = function25;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(colors)) {
                i6 = 1048576;
                $dirty |= i6;
            }
            i6 = 524288;
            $dirty |= i6;
        }
        int i12 = i & 128;
        if (i12 != 0) {
            $dirty |= 12582912;
            i2 = i12;
        } else if (($changed & 12582912) == 0) {
            i2 = i12;
            $dirty |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        } else {
            i2 = i12;
        }
        int i13 = i & 256;
        if (i13 != 0) {
            $dirty |= 100663296;
            i3 = i13;
            f = shadowElevation;
        } else if (($changed & 100663296) == 0) {
            i3 = i13;
            f = shadowElevation;
            $dirty |= $composer3.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i13;
            f = shadowElevation;
        }
        Composer $composer4 = $composer3;
        if (($dirty & 38347923) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            tonalElevation3 = tonalElevation;
            modifier3 = modifier2;
            overlineContent2 = overlineContent;
            trailingContent3 = supportingContent;
            shadowElevation3 = f;
            trailingContent2 = function26;
            leadingContent4 = function24;
            colors4 = colors;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    overlineContent = null;
                }
                if (i9 != 0) {
                    supportingContent = null;
                }
                leadingContent = i10 != 0 ? null : function24;
                trailingContent = i11 != 0 ? null : function26;
                if ((i & 64) != 0) {
                    i4 = i2;
                    i5 = i3;
                    colors2 = ListItemDefaults.INSTANCE.m2197colorsJ08w3E(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 805306368, FrameMetricsAggregator.EVERY_DURATION);
                    $composer2 = $composer4;
                    $dirty &= -3670017;
                } else {
                    i4 = i2;
                    i5 = i3;
                    $composer2 = $composer4;
                    colors2 = colors;
                }
                float tonalElevation4 = i4 != 0 ? ListItemDefaults.INSTANCE.m2198getElevationD9Ej5fM() : tonalElevation;
                if (i5 != 0) {
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = ListItemDefaults.INSTANCE.m2198getElevationD9Ej5fM();
                    colors3 = colors2;
                } else {
                    shadowElevation2 = shadowElevation;
                    tonalElevation2 = tonalElevation4;
                    colors3 = colors2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                leadingContent = function24;
                colors3 = colors;
                tonalElevation2 = tonalElevation;
                shadowElevation2 = f;
                trailingContent = function26;
                $composer2 = $composer4;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1647707763, $dirty, -1, "androidx.compose.material3.ListItem (ListItem.kt:103)");
            }
            final Function2 decoratedHeadlineContent = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                public final void invoke(Composer $composer5, int $changed2) {
                    ComposerKt.sourceInformation($composer5, "C105@4745L160:ListItem.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-403249643, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                        }
                        ListItemKt.m2200ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2192headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, $composer5, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer5.skipToGroupEnd();
                }
            }, $composer2, 54);
            $composer2.startReplaceGroup(1640970492);
            ComposerKt.sourceInformation($composer2, "*113@5021L204");
            if (supportingContent == null) {
                leadingContent2 = leadingContent;
                leadingContent3 = null;
            } else {
                final Function2 it = supportingContent;
                leadingContent2 = leadingContent;
                leadingContent3 = ComposableLambdaKt.rememberComposableLambda(-1020860251, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedSupportingContent$1$1
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

                    public final void invoke(Composer $composer5, int $changed2) {
                        ComposerKt.sourceInformation($composer5, "C114@5039L172:ListItem.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1020860251, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:114)");
                            }
                            ListItemKt.m2200ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2195supportingColor0d7_KjU$material3_release(), ListTokens.INSTANCE.getListItemSupportingTextFont(), it, $composer5, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }, $composer2, 54);
            }
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(1640980724);
            ComposerKt.sourceInformation($composer2, "*123@5341L196");
            if (overlineContent == null) {
                decoratedSupportingContent = leadingContent3;
                decoratedSupportingContent2 = null;
            } else {
                final Function2 it2 = overlineContent;
                decoratedSupportingContent = leadingContent3;
                decoratedSupportingContent2 = ComposableLambdaKt.rememberComposableLambda(-764441232, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedOverlineContent$1$1
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

                    public final void invoke(Composer $composer5, int $changed2) {
                        ComposerKt.sourceInformation($composer5, "C124@5359L164:ListItem.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-764441232, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:124)");
                            }
                            ListItemKt.m2200ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2194overlineColor0d7_KjU$material3_release(), ListTokens.INSTANCE.getListItemOverlineFont(), it2, $composer5, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }, $composer2, 54);
            }
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(1640990750);
            ComposerKt.sourceInformation($composer2, "*133@5651L302");
            if (leadingContent2 == null) {
                decoratedOverlineContent = decoratedSupportingContent2;
                decoratedLeadingContent = null;
            } else {
                final Function2 it3 = leadingContent2;
                decoratedOverlineContent = decoratedSupportingContent2;
                decoratedLeadingContent = ComposableLambdaKt.rememberComposableLambda(1400509200, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedLeadingContent$1$1
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

                    /* JADX WARN: Removed duplicated region for block: B:24:0x016d  */
                    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                        /*
                            Method dump skipped, instructions count: 369
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemKt$ListItem$decoratedLeadingContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer2, 54);
            }
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(1641004177);
            ComposerKt.sourceInformation($composer2, "*144@6069L353");
            if (trailingContent == null) {
                decoratedTrailingContent = null;
            } else {
                final Function2 it4 = trailingContent;
                decoratedTrailingContent = ComposableLambdaKt.rememberComposableLambda(1512306332, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedTrailingContent$1$1
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

                    public final void invoke(Composer $composer5, int $changed2) {
                        ComposerKt.sourceInformation($composer5, "C145@6087L321:ListItem.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1512306332, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:145)");
                            }
                            Modifier modifier$iv = PaddingKt.m676paddingqDBjuR0$default(Modifier.INSTANCE, ListItemKt.getTrailingContentStartPadding(), 0.0f, 0.0f, 0.0f, 14, null);
                            ListItemColors listItemColors = ListItemColors.this;
                            Function2<Composer, Integer, Unit> function27 = it4;
                            ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                            Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                $composer5.createNode(factory$iv$iv$iv);
                            } else {
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m3656constructorimpl($composer5);
                            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                            }
                            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i14 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i15 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 690581204, "C146@6168L222:ListItem.kt#uh7d8r");
                            ListItemKt.m2200ProvideTextStyleFromToken3JVO9M(listItemColors.m2196trailingIconColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemTrailingSupportingTextFont(), function27, $composer5, 48);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }, $composer2, 54);
            }
            $composer2.endReplaceGroup();
            final Function2 decoratedTrailingContent2 = decoratedTrailingContent;
            final Function2 decoratedLeadingContent2 = decoratedLeadingContent;
            Composer $composer5 = $composer2;
            SurfaceKt.m2547SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                }
            }).then(modifier2), ListItemDefaults.INSTANCE.getShape($composer2, 6), colors3.getContainerColor(), colors3.m2192headlineColorvNxB06k$material3_release(true), tonalElevation2, shadowElevation2, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                public final void invoke(Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C163@6771L272:ListItem.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer6.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1502590376, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                        }
                        ListItemKt.ListItemLayout(decoratedLeadingContent2, decoratedTrailingContent2, decoratedHeadlineContent, decoratedOverlineContent, decoratedSupportingContent, $composer6, 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer6.skipToGroupEnd();
                }
            }, $composer2, 54), $composer5, (($dirty >> 9) & 57344) | 12582912 | (458752 & ($dirty >> 9)), 64);
            $composer4 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trailingContent2 = trailingContent;
            overlineContent2 = overlineContent;
            trailingContent3 = supportingContent;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            colors4 = colors3;
            modifier3 = modifier2;
            leadingContent4 = leadingContent2;
        }
        ScopeUpdateScope endRestartGroup = $composer4.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$3
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

                public final void invoke(Composer composer, int i14) {
                    ListItemKt.m2199ListItemHXNGIdc(function2, modifier3, overlineContent2, trailingContent3, leadingContent4, trailingContent2, colors4, tonalElevation3, shadowElevation3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0234  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ListItemLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r26, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32) {
        /*
            Method dump skipped, instructions count: 592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemKt.ListItemLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m2206calculateWidthyeHjK3Y(IntrinsicMeasureScope $this$calculateWidth_u2dyeHjK3Y, int leadingWidth, int trailingWidth, int headlineWidth, int overlineWidth, int supportingWidth, int horizontalPadding, long constraints) {
        if (Constraints.m6575getHasBoundedWidthimpl(constraints)) {
            return Constraints.m6579getMaxWidthimpl(constraints);
        }
        int mainContentWidth = Math.max(headlineWidth, Math.max(overlineWidth, supportingWidth));
        return horizontalPadding + leadingWidth + mainContentWidth + trailingWidth;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-N4Jib3Y, reason: not valid java name */
    public static final int m2205calculateHeightN4Jib3Y(IntrinsicMeasureScope $this$calculateHeight_u2dN4Jib3Y, int leadingHeight, int trailingHeight, int headlineHeight, int overlineHeight, int supportingHeight, int listItemType, int verticalPadding, long constraints) {
        float defaultMinHeight;
        if (ListItemType.m2212equalsimpl0(listItemType, ListItemType.INSTANCE.m2217getOneLineAlXitO8())) {
            defaultMinHeight = ListTokens.INSTANCE.m3314getListItemOneLineContainerHeightD9Ej5fM();
        } else {
            defaultMinHeight = ListItemType.m2212equalsimpl0(listItemType, ListItemType.INSTANCE.m2219getTwoLineAlXitO8()) ? ListTokens.INSTANCE.m3319getListItemTwoLineContainerHeightD9Ej5fM() : ListTokens.INSTANCE.m3316getListItemThreeLineContainerHeightD9Ej5fM();
        }
        int minHeight = Math.max(Constraints.m6580getMinHeightimpl(constraints), $this$calculateHeight_u2dN4Jib3Y.mo358roundToPx0680j_4(defaultMinHeight));
        int mainContentHeight = headlineHeight + overlineHeight + supportingHeight;
        return RangesKt.coerceAtMost(Math.max(minHeight, Math.max(leadingHeight, Math.max(mainContentHeight, trailingHeight)) + verticalPadding), Constraints.m6578getMaxHeightimpl(constraints));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult place(MeasureScope $this$place, final int width, final int height, final Placeable leadingPlaceable, final Placeable trailingPlaceable, final Placeable headlinePlaceable, final Placeable overlinePlaceable, final Placeable supportingPlaceable, final boolean isThreeLine, final int startPadding, final int endPadding, final int topPadding) {
        return MeasureScope.layout$default($this$place, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ListItemKt$place$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                int totalHeight;
                Placeable it = Placeable.this;
                if (it != null) {
                    int i = startPadding;
                    boolean z = isThreeLine;
                    int i2 = topPadding;
                    int i3 = height;
                    if (!z) {
                        i2 = Alignment.INSTANCE.getCenterVertically().align(it.getHeight(), i3);
                    }
                    Placeable.PlacementScope.placeRelative$default($this$layout, it, i, i2, 0.0f, 4, null);
                }
                Placeable it2 = trailingPlaceable;
                if (it2 != null) {
                    int i4 = width;
                    int i5 = endPadding;
                    boolean z2 = isThreeLine;
                    int i6 = topPadding;
                    int i7 = height;
                    int width2 = (i4 - i5) - it2.getWidth();
                    if (!z2) {
                        i6 = Alignment.INSTANCE.getCenterVertically().align(it2.getHeight(), i7);
                    }
                    Placeable.PlacementScope.placeRelative$default($this$layout, it2, width2, i6, 0.0f, 4, null);
                }
                int mainContentX = startPadding + TextFieldImplKt.widthOrZero(Placeable.this);
                if (isThreeLine) {
                    totalHeight = topPadding;
                } else {
                    int totalHeight2 = TextFieldImplKt.heightOrZero(headlinePlaceable) + TextFieldImplKt.heightOrZero(overlinePlaceable) + TextFieldImplKt.heightOrZero(supportingPlaceable);
                    totalHeight = Alignment.INSTANCE.getCenterVertically().align(totalHeight2, height);
                }
                int currentY = totalHeight;
                Placeable placeable = overlinePlaceable;
                if (placeable != null) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, placeable, mainContentX, currentY, 0.0f, 4, null);
                }
                int currentY2 = currentY + TextFieldImplKt.heightOrZero(overlinePlaceable);
                Placeable placeable2 = headlinePlaceable;
                if (placeable2 != null) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, placeable2, mainContentX, currentY2, 0.0f, 4, null);
                }
                int currentY3 = currentY2 + TextFieldImplKt.heightOrZero(headlinePlaceable);
                Placeable placeable3 = supportingPlaceable;
                if (placeable3 != null) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, placeable3, mainContentX, currentY3, 0.0f, 4, null);
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ProvideTextStyleFromToken-3J-VO9M, reason: not valid java name */
    public static final void m2200ProvideTextStyleFromToken3JVO9M(final long color, final TypographyKeyTokens textToken, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(1133967795);
        ComposerKt.sourceInformation($composer2, "C(ProvideTextStyleFromToken)P(0:c#ui.graphics.Color,2)659@26906L5,657@26816L128:ListItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(textToken) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 256 : 128;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133967795, $dirty2, -1, "androidx.compose.material3.ProvideTextStyleFromToken (ListItem.kt:657)");
            }
            ProvideContentColorTextStyleKt.m2906ProvideContentColorTextStyle3JVO9M(color, TypographyKt.getValue(textToken, $composer2, ($dirty2 >> 3) & 14), function22, $composer2, ($dirty2 & 14) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ProvideTextStyleFromToken$1
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

                public final void invoke(Composer composer, int i) {
                    ListItemKt.m2200ProvideTextStyleFromToken3JVO9M(color, textToken, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final float getListItemVerticalPadding() {
        return ListItemVerticalPadding;
    }

    public static final float getListItemThreeLineVerticalPadding() {
        return ListItemThreeLineVerticalPadding;
    }

    public static final float getListItemStartPadding() {
        return ListItemStartPadding;
    }

    public static final float getListItemEndPadding() {
        return ListItemEndPadding;
    }

    public static final float getLeadingContentEndPadding() {
        return LeadingContentEndPadding;
    }

    public static final float getTrailingContentStartPadding() {
        return TrailingContentStartPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSupportingMultilineHeuristic(Density $this$isSupportingMultilineHeuristic, int estimatedSupportingHeight) {
        return estimatedSupportingHeight > $this$isSupportingMultilineHeuristic.mo357roundToPxR2X_6o(TextUnitKt.getSp(30));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: verticalPadding-yh95HIg, reason: not valid java name */
    public static final float m2207verticalPaddingyh95HIg(int listItemType) {
        return ListItemType.m2212equalsimpl0(listItemType, ListItemType.INSTANCE.m2218getThreeLineAlXitO8()) ? ListItemThreeLineVerticalPadding : ListItemVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int subtractConstraintSafely(int $this$subtractConstraintSafely, int n) {
        if ($this$subtractConstraintSafely == Integer.MAX_VALUE) {
            return $this$subtractConstraintSafely;
        }
        return $this$subtractConstraintSafely - n;
    }
}
