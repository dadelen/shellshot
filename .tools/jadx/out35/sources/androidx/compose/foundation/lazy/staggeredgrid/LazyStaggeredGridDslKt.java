package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;

/* compiled from: LazyStaggeredGridDsl.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0083\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a%\u0010 \u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010\"\u001a%\u0010#\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010$\u001aÐ\u0001\u0010%\u001a\u00020\u0001\"\u0004\b\u0000\u0010&*\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H&0'2%\b\n\u0010(\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,\u0018\u00010\u00142%\b\u0006\u0010-\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010,0\u00142%\b\n\u0010.\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020/\u0018\u00010\u001423\b\u0004\u00100\u001a-\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u000101¢\u0006\u0002\b3¢\u0006\u0002\b\u0016H\u0086\b¢\u0006\u0002\u00104\u001aÐ\u0001\u0010%\u001a\u00020\u0001\"\u0004\b\u0000\u0010&*\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H&052%\b\n\u0010(\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,\u0018\u00010\u00142%\b\u0006\u0010-\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010,0\u00142%\b\n\u0010.\u001a\u001f\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020/\u0018\u00010\u001423\b\u0004\u00100\u001a-\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u000101¢\u0006\u0002\b3¢\u0006\u0002\b\u0016H\u0086\b¢\u0006\u0002\u00106\u001a¤\u0002\u00107\u001a\u00020\u0001\"\u0004\b\u0000\u0010&*\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H&0'2:\b\n\u0010(\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,\u0018\u0001012:\b\u0006\u0010-\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010,012:\b\n\u0010.\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020/\u0018\u0001012H\b\u0004\u00100\u001aB\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00010:¢\u0006\u0002\b3¢\u0006\u0002\b\u0016H\u0086\b¢\u0006\u0002\u0010;\u001a¤\u0002\u00107\u001a\u00020\u0001\"\u0004\b\u0000\u0010&*\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H&052:\b\n\u0010(\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,\u0018\u0001012:\b\u0006\u0010-\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010,012:\b\n\u0010.\u001a4\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020/\u0018\u0001012H\b\u0004\u00100\u001aB\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u001108¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0013\u0012\u0011H&¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00010:¢\u0006\u0002\b3¢\u0006\u0002\b\u0016H\u0086\b¢\u0006\u0002\u0010<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"LazyHorizontalStaggeredGrid", "", "rows", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalItemSpacing", "Landroidx/compose/ui/unit/Dp;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "LazyHorizontalStaggeredGrid-cJHQLPU", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalStaggeredGrid", "columns", "verticalItemSpacing", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "LazyVerticalStaggeredGrid-zadm560", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/layout/PaddingValues;ZFLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberColumnSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "rememberRowSlots", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "items", "T", "", "key", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "item", "", "contentType", "span", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridDslKt {
    /* renamed from: LazyVerticalStaggeredGrid-zadm560, reason: not valid java name */
    public static final void m868LazyVerticalStaggeredGridzadm560(final StaggeredGridCells columns, Modifier modifier, LazyStaggeredGridState state, PaddingValues contentPadding, boolean reverseLayout, float verticalItemSpacing, Arrangement.Horizontal horizontalArrangement, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyStaggeredGridScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyStaggeredGridState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        float verticalItemSpacing2;
        int i2;
        int i3;
        Arrangement.HorizontalOrVertical horizontalArrangement2;
        FlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        FlingBehavior flingBehavior3;
        LazyStaggeredGridState state3;
        boolean reverseLayout3;
        float verticalItemSpacing3;
        Composer $composer2;
        final LazyStaggeredGridState state4;
        final Arrangement.Horizontal horizontalArrangement3;
        final Modifier modifier3;
        final PaddingValues contentPadding3;
        final boolean reverseLayout4;
        final FlingBehavior flingBehavior4;
        final boolean userScrollEnabled3;
        final float verticalItemSpacing4;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1695323794);
        ComposerKt.sourceInformation($composer3, "C(LazyVerticalStaggeredGrid)P(!1,5,7,2,6,9:c#ui.unit.Dp,4,3,8)64@3068L32,69@3365L15,83@3878L67,73@3476L502:LazyStaggeredGridDsl.kt#fzvcnm");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(columns) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                state2 = state;
                if ($composer3.changedInstance(state2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                state2 = state;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            state2 = state;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 3072) == 0) {
            contentPadding2 = contentPadding;
            $dirty |= $composer3.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 24576) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty |= $composer3.changed(reverseLayout2) ? 16384 : 8192;
        } else {
            reverseLayout2 = reverseLayout;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            verticalItemSpacing2 = verticalItemSpacing;
        } else if ((196608 & $changed) == 0) {
            verticalItemSpacing2 = verticalItemSpacing;
            $dirty |= $composer3.changed(verticalItemSpacing2) ? 131072 : 65536;
        } else {
            verticalItemSpacing2 = verticalItemSpacing;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(horizontalArrangement) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(flingBehavior)) {
                i4 = 8388608;
                $dirty |= i4;
            }
            i4 = 4194304;
            $dirty |= i4;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i11;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            horizontalArrangement3 = horizontalArrangement;
            userScrollEnabled3 = userScrollEnabled;
            $composer2 = $composer3;
            modifier3 = modifier2;
            state4 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            verticalItemSpacing4 = verticalItemSpacing2;
            flingBehavior4 = flingBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    i3 = -29360129;
                    $dirty &= -897;
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer3, 0, 3);
                } else {
                    i3 = -29360129;
                }
                if (i7 != 0) {
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                }
                if (i8 != 0) {
                    reverseLayout2 = false;
                }
                if (i9 != 0) {
                    verticalItemSpacing2 = Dp.m6626constructorimpl(0);
                }
                horizontalArrangement2 = i10 != 0 ? Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(0)) : horizontalArrangement;
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty &= i3;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i2 != 0) {
                    flingBehavior3 = flingBehavior2;
                    state3 = state2;
                    userScrollEnabled2 = true;
                    reverseLayout3 = reverseLayout2;
                    verticalItemSpacing3 = verticalItemSpacing2;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                    flingBehavior3 = flingBehavior2;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalItemSpacing3 = verticalItemSpacing2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 128) != 0) {
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty &= -29360129;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalItemSpacing3 = verticalItemSpacing2;
                    horizontalArrangement2 = horizontalArrangement;
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalItemSpacing3 = verticalItemSpacing2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1695323794, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid (LazyStaggeredGridDsl.kt:72)");
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            PaddingValues contentPadding4 = contentPadding2;
            LazyStaggeredGridKt.m873LazyStaggeredGridLJWHXA8(state3, Orientation.Vertical, rememberColumnSlots(columns, horizontalArrangement2, contentPadding2, $composer3, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), modifier4, contentPadding4, reverseLayout3, flingBehavior3, userScrollEnabled2, verticalItemSpacing3, horizontalArrangement2.getSpacing(), function1, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 6) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            state4 = state3;
            horizontalArrangement3 = horizontalArrangement2;
            modifier3 = modifier4;
            contentPadding3 = contentPadding4;
            reverseLayout4 = reverseLayout3;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            verticalItemSpacing4 = verticalItemSpacing3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyVerticalStaggeredGrid$1
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

                public final void invoke(Composer composer, int i12) {
                    LazyStaggeredGridDslKt.m868LazyVerticalStaggeredGridzadm560(StaggeredGridCells.this, modifier3, state4, contentPadding3, reverseLayout4, verticalItemSpacing4, horizontalArrangement3, flingBehavior4, userScrollEnabled3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final LazyGridStaggeredGridSlotsProvider rememberColumnSlots(final StaggeredGridCells columns, final Arrangement.Horizontal horizontalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        LazyStaggeredGridSlotCache value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1267076841, "C(rememberColumnSlots)P(!1,2)94@4216L1114:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1267076841, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberColumnSlots (LazyStaggeredGridDsl.kt:94)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 463564400, "CC(remember):LazyStaggeredGridDsl.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(columns)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(horizontalArrangement)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(contentPadding)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyStaggeredGridSlotCache(new Function2<Density, Constraints, LazyStaggeredGridSlots>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberColumnSlots$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridSlots invoke(Density density, Constraints constraints) {
                    return m869invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridSlots m869invoke0kLqBqw(Density $this$$receiver, long constraints) {
                    if (!(Constraints.m6579getMaxWidthimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyVerticalStaggeredGrid's width should be bound by parent.".toString());
                    }
                    float arg0$iv = PaddingKt.calculateStartPadding(PaddingValues.this, LayoutDirection.Ltr);
                    float other$iv = PaddingKt.calculateEndPadding(PaddingValues.this, LayoutDirection.Ltr);
                    int gridWidth = Constraints.m6579getMaxWidthimpl(constraints) - $this$$receiver.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv + other$iv));
                    StaggeredGridCells $this$invoke_0kLqBqw_u24lambda_u243 = columns;
                    Arrangement.Horizontal $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = horizontalArrangement;
                    int[] sizes = $this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($this$$receiver, gridWidth, $this$$receiver.mo358roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing()));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($this$$receiver, gridWidth, sizes, LayoutDirection.Ltr, positions);
                    return new LazyStaggeredGridSlots(positions, sizes);
                }
            });
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider = (LazyGridStaggeredGridSlotsProvider) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyGridStaggeredGridSlotsProvider;
    }

    /* renamed from: LazyHorizontalStaggeredGrid-cJHQLPU, reason: not valid java name */
    public static final void m867LazyHorizontalStaggeredGridcJHQLPU(final StaggeredGridCells rows, Modifier modifier, LazyStaggeredGridState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Vertical verticalArrangement, float horizontalItemSpacing, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyStaggeredGridScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyStaggeredGridState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Vertical verticalArrangement2;
        int i2;
        int i3;
        FlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        float horizontalItemSpacing2;
        FlingBehavior flingBehavior3;
        LazyStaggeredGridState state3;
        boolean reverseLayout3;
        Arrangement.Vertical verticalArrangement3;
        Composer $composer2;
        final LazyStaggeredGridState state4;
        final Arrangement.Vertical verticalArrangement4;
        final Modifier modifier3;
        final PaddingValues contentPadding3;
        final boolean reverseLayout4;
        final FlingBehavior flingBehavior4;
        final boolean userScrollEnabled3;
        final float horizontalItemSpacing3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-8666074);
        ComposerKt.sourceInformation($composer3, "C(LazyHorizontalStaggeredGrid)P(6,4,7,1,5,9,3:c#ui.unit.Dp,2,8)154@6993L32,159@7288L15,173@7803L59,163@7399L496:LazyStaggeredGridDsl.kt#fzvcnm");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(rows) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                state2 = state;
                if ($composer3.changedInstance(state2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                state2 = state;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            state2 = state;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 3072) == 0) {
            contentPadding2 = contentPadding;
            $dirty |= $composer3.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 24576) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty |= $composer3.changed(reverseLayout2) ? 16384 : 8192;
        } else {
            reverseLayout2 = reverseLayout;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            verticalArrangement2 = verticalArrangement;
        } else if ((196608 & $changed) == 0) {
            verticalArrangement2 = verticalArrangement;
            $dirty |= $composer3.changed(verticalArrangement2) ? 131072 : 65536;
        } else {
            verticalArrangement2 = verticalArrangement;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(horizontalItemSpacing) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(flingBehavior)) {
                i4 = 8388608;
                $dirty |= i4;
            }
            i4 = 4194304;
            $dirty |= i4;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i11;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            horizontalItemSpacing3 = horizontalItemSpacing;
            userScrollEnabled3 = userScrollEnabled;
            $composer2 = $composer3;
            modifier3 = modifier2;
            state4 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            verticalArrangement4 = verticalArrangement2;
            flingBehavior4 = flingBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    i3 = -29360129;
                    $dirty &= -897;
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer3, 0, 3);
                } else {
                    i3 = -29360129;
                }
                if (i7 != 0) {
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                }
                if (i8 != 0) {
                    reverseLayout2 = false;
                }
                if (i9 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(0));
                }
                float horizontalItemSpacing4 = i10 != 0 ? Dp.m6626constructorimpl(0) : horizontalItemSpacing;
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty &= i3;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i2 != 0) {
                    horizontalItemSpacing2 = horizontalItemSpacing4;
                    flingBehavior3 = flingBehavior2;
                    state3 = state2;
                    userScrollEnabled2 = true;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                    horizontalItemSpacing2 = horizontalItemSpacing4;
                    flingBehavior3 = flingBehavior2;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 128) != 0) {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty &= -29360129;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                } else {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-8666074, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid (LazyStaggeredGridDsl.kt:162)");
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            PaddingValues contentPadding4 = contentPadding2;
            LazyStaggeredGridKt.m873LazyStaggeredGridLJWHXA8(state3, Orientation.Horizontal, rememberRowSlots(rows, verticalArrangement3, contentPadding2, $composer3, ($dirty & 14) | (($dirty >> 12) & 112) | (($dirty >> 3) & 896)), modifier4, contentPadding4, reverseLayout3, flingBehavior3, userScrollEnabled2, horizontalItemSpacing2, verticalArrangement3.getSpacing(), function1, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 6) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            state4 = state3;
            verticalArrangement4 = verticalArrangement3;
            modifier3 = modifier4;
            contentPadding3 = contentPadding4;
            reverseLayout4 = reverseLayout3;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            horizontalItemSpacing3 = horizontalItemSpacing2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyHorizontalStaggeredGrid$1
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

                public final void invoke(Composer composer, int i12) {
                    LazyStaggeredGridDslKt.m867LazyHorizontalStaggeredGridcJHQLPU(StaggeredGridCells.this, modifier3, state4, contentPadding3, reverseLayout4, verticalArrangement4, horizontalItemSpacing3, flingBehavior4, userScrollEnabled3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final LazyGridStaggeredGridSlotsProvider rememberRowSlots(final StaggeredGridCells rows, final Arrangement.Vertical verticalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        LazyStaggeredGridSlotCache value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1532383053, "C(rememberRowSlots)P(1,2)184@8120L940:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1532383053, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberRowSlots (LazyStaggeredGridDsl.kt:184)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1011137904, "CC(remember):LazyStaggeredGridDsl.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(rows)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(verticalArrangement)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(contentPadding)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new LazyStaggeredGridSlotCache(new Function2<Density, Constraints, LazyStaggeredGridSlots>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberRowSlots$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridSlots invoke(Density density, Constraints constraints) {
                    return m870invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridSlots m870invoke0kLqBqw(Density $this$$receiver, long constraints) {
                    if (!(Constraints.m6578getMaxHeightimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyHorizontalStaggeredGrid's height should be bound by parent.".toString());
                    }
                    float arg0$iv = PaddingValues.this.getTop();
                    float other$iv = PaddingValues.this.getBottom();
                    int gridHeight = Constraints.m6578getMaxHeightimpl(constraints) - $this$$receiver.mo358roundToPx0680j_4(Dp.m6626constructorimpl(arg0$iv + other$iv));
                    StaggeredGridCells $this$invoke_0kLqBqw_u24lambda_u243 = rows;
                    Arrangement.Vertical $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = verticalArrangement;
                    int[] sizes = $this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($this$$receiver, gridHeight, $this$$receiver.mo358roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing()));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($this$$receiver, gridHeight, sizes, positions);
                    return new LazyStaggeredGridSlots(positions, sizes);
                }
            });
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider = (LazyGridStaggeredGridSlotsProvider) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyGridStaggeredGridSlotsProvider;
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope $this$items_u24default, List items, Function1 key, Function1 contentType, Function1 span, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((LazyStaggeredGridDslKt$items$1) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        int size = items.size();
        Function1 it = null;
        LazyStaggeredGridDslKt$items$2$1 lazyStaggeredGridDslKt$items$2$1 = key != null ? new LazyStaggeredGridDslKt$items$2$1(key, items) : null;
        LazyStaggeredGridDslKt$items$3 lazyStaggeredGridDslKt$items$3 = new LazyStaggeredGridDslKt$items$3(contentType, items);
        if (span != null) {
            Function1 it2 = new LazyStaggeredGridDslKt$items$4$1(span, items);
            it = it2;
        }
        $this$items_u24default.items(size, lazyStaggeredGridDslKt$items$2$1, lazyStaggeredGridDslKt$items$3, it, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new LazyStaggeredGridDslKt$items$5(itemContent, items)));
    }

    public static final <T> void items(LazyStaggeredGridScope $this$items, List<? extends T> list, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function1<? super T, StaggeredGridItemSpan> function13, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        Function1 it;
        int size = list.size();
        Function1 it2 = null;
        if (function1 == null) {
            it = null;
        } else {
            it = new LazyStaggeredGridDslKt$items$2$1(function1, list);
        }
        LazyStaggeredGridDslKt$items$3 lazyStaggeredGridDslKt$items$3 = new LazyStaggeredGridDslKt$items$3(function12, list);
        if (function13 != null) {
            Function1 it3 = new LazyStaggeredGridDslKt$items$4$1(function13, list);
            it2 = it3;
        }
        $this$items.items(size, it, lazyStaggeredGridDslKt$items$3, it2, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new LazyStaggeredGridDslKt$items$5(function4, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope $this$itemsIndexed_u24default, List items, Function2 key, Function2 contentType, Function2 span, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), (int) p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        $this$itemsIndexed_u24default.items(items.size(), key != null ? new LazyStaggeredGridDslKt$itemsIndexed$2$1(key, items) : null, new LazyStaggeredGridDslKt$itemsIndexed$3(contentType, items), span != null ? new LazyStaggeredGridDslKt$itemsIndexed$4$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(284833944, true, new LazyStaggeredGridDslKt$itemsIndexed$5(itemContent, items)));
    }

    public static final <T> void itemsIndexed(LazyStaggeredGridScope $this$itemsIndexed, List<? extends T> list, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function23, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        LazyStaggeredGridDslKt$itemsIndexed$2$1 lazyStaggeredGridDslKt$itemsIndexed$2$1;
        int size = list.size();
        LazyStaggeredGridDslKt$itemsIndexed$4$1 lazyStaggeredGridDslKt$itemsIndexed$4$1 = null;
        if (function2 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$2$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$2$1 = new LazyStaggeredGridDslKt$itemsIndexed$2$1(function2, list);
        }
        LazyStaggeredGridDslKt$itemsIndexed$3 lazyStaggeredGridDslKt$itemsIndexed$3 = new LazyStaggeredGridDslKt$itemsIndexed$3(function22, list);
        if (function23 != null) {
            lazyStaggeredGridDslKt$itemsIndexed$4$1 = new LazyStaggeredGridDslKt$itemsIndexed$4$1(function23, list);
        }
        $this$itemsIndexed.items(size, lazyStaggeredGridDslKt$itemsIndexed$2$1, lazyStaggeredGridDslKt$itemsIndexed$3, lazyStaggeredGridDslKt$itemsIndexed$4$1, ComposableLambdaKt.composableLambdaInstance(284833944, true, new LazyStaggeredGridDslKt$itemsIndexed$5(function5, list)));
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope $this$items_u24default, Object[] items, Function1 key, Function1 contentType, Function1 span, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$6
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((LazyStaggeredGridDslKt$items$6) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        int length = items.length;
        Function1 it = null;
        LazyStaggeredGridDslKt$items$7$1 lazyStaggeredGridDslKt$items$7$1 = key != null ? new LazyStaggeredGridDslKt$items$7$1(key, items) : null;
        LazyStaggeredGridDslKt$items$8 lazyStaggeredGridDslKt$items$8 = new LazyStaggeredGridDslKt$items$8(contentType, items);
        if (span != null) {
            Function1 it2 = new LazyStaggeredGridDslKt$items$9$1(span, items);
            it = it2;
        }
        $this$items_u24default.items(length, lazyStaggeredGridDslKt$items$7$1, lazyStaggeredGridDslKt$items$8, it, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new LazyStaggeredGridDslKt$items$10(itemContent, items)));
    }

    public static final <T> void items(LazyStaggeredGridScope $this$items, T[] tArr, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function1<? super T, StaggeredGridItemSpan> function13, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        Function1 it;
        int length = tArr.length;
        Function1 it2 = null;
        if (function1 == null) {
            it = null;
        } else {
            it = new LazyStaggeredGridDslKt$items$7$1(function1, tArr);
        }
        LazyStaggeredGridDslKt$items$8 lazyStaggeredGridDslKt$items$8 = new LazyStaggeredGridDslKt$items$8(function12, tArr);
        if (function13 != null) {
            Function1 it3 = new LazyStaggeredGridDslKt$items$9$1(function13, tArr);
            it2 = it3;
        }
        $this$items.items(length, it, lazyStaggeredGridDslKt$items$8, it2, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new LazyStaggeredGridDslKt$items$10(function4, tArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope $this$itemsIndexed_u24default, Object[] items, Function2 key, Function2 contentType, Function2 span, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), (int) p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        $this$itemsIndexed_u24default.items(items.length, key != null ? new LazyStaggeredGridDslKt$itemsIndexed$7$1(key, items) : null, new LazyStaggeredGridDslKt$itemsIndexed$8(contentType, items), span != null ? new LazyStaggeredGridDslKt$itemsIndexed$9$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new LazyStaggeredGridDslKt$itemsIndexed$10(itemContent, items)));
    }

    public static final <T> void itemsIndexed(LazyStaggeredGridScope $this$itemsIndexed, T[] tArr, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function23, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        LazyStaggeredGridDslKt$itemsIndexed$7$1 lazyStaggeredGridDslKt$itemsIndexed$7$1;
        int length = tArr.length;
        LazyStaggeredGridDslKt$itemsIndexed$9$1 lazyStaggeredGridDslKt$itemsIndexed$9$1 = null;
        if (function2 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$7$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$7$1 = new LazyStaggeredGridDslKt$itemsIndexed$7$1(function2, tArr);
        }
        LazyStaggeredGridDslKt$itemsIndexed$8 lazyStaggeredGridDslKt$itemsIndexed$8 = new LazyStaggeredGridDslKt$itemsIndexed$8(function22, tArr);
        if (function23 != null) {
            lazyStaggeredGridDslKt$itemsIndexed$9$1 = new LazyStaggeredGridDslKt$itemsIndexed$9$1(function23, tArr);
        }
        $this$itemsIndexed.items(length, lazyStaggeredGridDslKt$itemsIndexed$7$1, lazyStaggeredGridDslKt$itemsIndexed$8, lazyStaggeredGridDslKt$itemsIndexed$9$1, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new LazyStaggeredGridDslKt$itemsIndexed$10(function5, tArr)));
    }
}
