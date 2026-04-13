package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyStaggeredGrid.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0089\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"LazyStaggeredGrid", "", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "LazyStaggeredGrid-LJWHXA8", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/FlingBehavior;ZFFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridKt {
    /* renamed from: LazyStaggeredGrid-LJWHXA8, reason: not valid java name */
    public static final void m873LazyStaggeredGridLJWHXA8(final LazyStaggeredGridState state, final Orientation orientation, final LazyGridStaggeredGridSlotsProvider slots, Modifier modifier, PaddingValues contentPadding, boolean reverseLayout, FlingBehavior flingBehavior, boolean userScrollEnabled, float mainAxisSpacing, float crossAxisSpacing, final Function1<? super LazyStaggeredGridScope, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        PaddingValues paddingValues;
        final boolean reverseLayout2;
        int i2;
        int i3;
        FlingBehavior flingBehavior2;
        int $dirty;
        Modifier modifier3;
        float mainAxisSpacing2;
        boolean reverseLayout3;
        boolean userScrollEnabled2;
        PaddingValues contentPadding2;
        FlingBehavior flingBehavior3;
        int $dirty2;
        float crossAxisSpacing2;
        Object value$iv$iv;
        Composer $composer2;
        final boolean userScrollEnabled3;
        final FlingBehavior flingBehavior4;
        final Modifier modifier4;
        final PaddingValues contentPadding3;
        final float mainAxisSpacing3;
        final float crossAxisSpacing3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(288295126);
        ComposerKt.sourceInformation($composer3, "C(LazyStaggeredGrid)P(9,6,8,5,1,7,3,10,4:c#ui.unit.Dp,2:c#ui.unit.Dp)51@2370L15,61@2769L55,62@2850L24,63@2922L7,64@2954L266,76@3245L60,82@3456L278,90@3804L57,93@4024L7,89@3748L385,98@4194L316,78@3311L1332:LazyStaggeredGrid.kt#fzvcnm");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(orientation) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= ($changed & 512) == 0 ? $composer3.changed(slots) : $composer3.changedInstance(slots) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty3 |= 24576;
            paddingValues = contentPadding;
        } else if (($changed & 24576) == 0) {
            paddingValues = contentPadding;
            $dirty3 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        } else {
            paddingValues = contentPadding;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            reverseLayout2 = reverseLayout;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty3 |= $composer3.changed(reverseLayout2) ? 131072 : 65536;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(flingBehavior)) {
                i4 = 1048576;
                $dirty3 |= i4;
            }
            i4 = 524288;
            $dirty3 |= i4;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(userScrollEnabled) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            i2 = i9;
        } else if (($changed & 100663296) == 0) {
            i2 = i9;
            $dirty3 |= $composer3.changed(mainAxisSpacing) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i9;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty3 |= 805306368;
            i3 = i10;
        } else if (($changed & 805306368) == 0) {
            i3 = i10;
            $dirty3 |= $composer3.changed(crossAxisSpacing) ? 536870912 : 268435456;
        } else {
            i3 = i10;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        int $dirty4 = $dirty3;
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            userScrollEnabled3 = userScrollEnabled;
            mainAxisSpacing3 = mainAxisSpacing;
            $composer2 = $composer3;
            modifier4 = modifier2;
            contentPadding3 = paddingValues;
            flingBehavior4 = flingBehavior;
            crossAxisSpacing3 = crossAxisSpacing;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                PaddingValues contentPadding4 = i6 != 0 ? PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0)) : paddingValues;
                if (i7 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 64) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    $dirty = $dirty4;
                }
                boolean userScrollEnabled4 = i8 != 0 ? true : userScrollEnabled;
                float mainAxisSpacing4 = i2 != 0 ? Dp.m6626constructorimpl(0) : mainAxisSpacing;
                if (i3 != 0) {
                    modifier3 = modifier5;
                    mainAxisSpacing2 = mainAxisSpacing4;
                    reverseLayout3 = reverseLayout2;
                    userScrollEnabled2 = userScrollEnabled4;
                    flingBehavior3 = flingBehavior2;
                    $dirty2 = $dirty;
                    crossAxisSpacing2 = Dp.m6626constructorimpl(0);
                    contentPadding2 = contentPadding4;
                } else {
                    modifier3 = modifier5;
                    mainAxisSpacing2 = mainAxisSpacing4;
                    reverseLayout3 = reverseLayout2;
                    userScrollEnabled2 = userScrollEnabled4;
                    contentPadding2 = contentPadding4;
                    flingBehavior3 = flingBehavior2;
                    $dirty2 = $dirty;
                    crossAxisSpacing2 = crossAxisSpacing;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    mainAxisSpacing2 = mainAxisSpacing;
                    $dirty2 = $dirty4 & (-3670017);
                    reverseLayout3 = reverseLayout2;
                    contentPadding2 = paddingValues;
                    crossAxisSpacing2 = crossAxisSpacing;
                    modifier3 = modifier2;
                } else {
                    flingBehavior3 = flingBehavior;
                    userScrollEnabled2 = userScrollEnabled;
                    mainAxisSpacing2 = mainAxisSpacing;
                    $dirty2 = $dirty4;
                    reverseLayout3 = reverseLayout2;
                    contentPadding2 = paddingValues;
                    crossAxisSpacing2 = crossAxisSpacing;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(288295126, $dirty2, $dirty12, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:60)");
            }
            Function0 itemProviderLambda = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(state, function1, $composer3, ($dirty2 & 14) | (($dirty12 << 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            PaddingValues contentPadding5 = contentPadding2;
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localGraphicsContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            GraphicsContext graphicsContext = (GraphicsContext) consume;
            boolean reverseLayout4 = reverseLayout3;
            int $dirty5 = $dirty2;
            Function2 measurePolicy = LazyStaggeredGridMeasurePolicyKt.m884rememberStaggeredGridMeasurePolicyqKj4JfE(state, itemProviderLambda, contentPadding5, reverseLayout4, orientation, mainAxisSpacing2, crossAxisSpacing2, coroutineScope, slots, graphicsContext, $composer3, ($dirty2 & 14) | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 << 9) & 57344) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty2 << 18) & 234881024));
            float mainAxisSpacing5 = mainAxisSpacing2;
            float crossAxisSpacing4 = crossAxisSpacing2;
            LazyLayoutSemanticState semanticState = LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(state, reverseLayout4, $composer3, ($dirty5 & 14) | (($dirty5 >> 12) & 112));
            boolean userScrollEnabled5 = userScrollEnabled2;
            Modifier lazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier3.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), itemProviderLambda, semanticState, orientation, userScrollEnabled5, reverseLayout4, $composer3, (($dirty5 << 6) & 7168) | (($dirty5 >> 9) & 57344) | ($dirty5 & 458752));
            LazyLayoutBeyondBoundsState rememberLazyStaggeredGridBeyondBoundsState = LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(state, $composer3, $dirty5 & 14);
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FlingBehavior flingBehavior5 = flingBehavior3;
            LazyLayoutKt.LazyLayout((Function0<? extends LazyLayoutItemProvider>) itemProviderLambda, ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(lazyLayoutSemantics, rememberLazyStaggeredGridBeyondBoundsState, beyondBoundsInfo, reverseLayout4, (LayoutDirection) consume2, orientation, userScrollEnabled5, $composer3, (MutableVector.$stable << 6) | (($dirty5 >> 6) & 7168) | (($dirty5 << 12) & 458752) | (($dirty5 >> 3) & 3670016)).then(state.getItemAnimator$foundation_release().getModifier()), state, orientation, userScrollEnabled5, reverseLayout4, flingBehavior5, state.getMutableInteractionSource(), null, $composer3, (($dirty5 << 3) & 112) | (($dirty5 << 3) & 896) | (($dirty5 >> 12) & 7168) | (($dirty5 >> 3) & 57344) | (($dirty5 >> 3) & 458752), 64), state.getPrefetchState(), (Function2<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult>) measurePolicy, $composer3, 0, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            userScrollEnabled3 = userScrollEnabled5;
            flingBehavior4 = flingBehavior5;
            modifier4 = modifier3;
            contentPadding3 = contentPadding5;
            mainAxisSpacing3 = mainAxisSpacing5;
            crossAxisSpacing3 = crossAxisSpacing4;
            reverseLayout2 = reverseLayout4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$LazyStaggeredGrid$1
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
                    LazyStaggeredGridKt.m873LazyStaggeredGridLJWHXA8(LazyStaggeredGridState.this, orientation, slots, modifier4, contentPadding3, reverseLayout2, flingBehavior4, userScrollEnabled3, mainAxisSpacing3, crossAxisSpacing3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
