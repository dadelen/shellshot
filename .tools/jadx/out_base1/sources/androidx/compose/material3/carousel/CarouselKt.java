package androidx.compose.material3.carousel;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: Carousel.kt */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a»\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u001621\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0091\u0001\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a}\u0010$\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0018\u0010(\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0000\u001a\u0018\u0010+\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0001\u001a \u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020\bH\u0002\u001a\u0019\u00101\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a\u0019\u00103\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a:\u00104\u001a\u00020\u0013*\u00020\u00132\u0006\u00105\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"Carousel", "", "state", "Landroidx/compose/material3/carousel/CarouselState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "keylineList", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "availableSpace", "itemSpacing", "Landroidx/compose/material3/carousel/KeylineList;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "maxNonFocalVisibleItemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "content", "Landroidx/compose/material3/carousel/CarouselItemScope;", "itemIndex", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Carousel-V-95POc", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ILandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalMultiBrowseCarousel", "preferredItemWidth", "minSmallItemWidth", "maxSmallItemWidth", "HorizontalMultiBrowseCarousel-zCIJ0Nk", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;FFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalUncontainedCarousel", "itemWidth", "HorizontalUncontainedCarousel-9QcgTRs", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "calculateCurrentScrollOffset", "strategy", "Landroidx/compose/material3/carousel/Strategy;", "calculateMaxScrollOffset", "getProgress", "before", "Landroidx/compose/material3/carousel/Keyline;", "after", "unadjustedOffset", "calculateAfterContentPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)F", "calculateBeforeContentPadding", "carouselItem", "index", "Lkotlin/Function0;", "carouselItemInfo", "Landroidx/compose/material3/carousel/CarouselItemInfoImpl;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CarouselKt {
    /* renamed from: HorizontalMultiBrowseCarousel-zCIJ0Nk, reason: not valid java name */
    public static final void m2888HorizontalMultiBrowseCarouselzCIJ0Nk(final CarouselState state, final float preferredItemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, float minSmallItemWidth, float maxSmallItemWidth, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        float f;
        final Modifier modifier2;
        float f2;
        TargetedFlingBehavior flingBehavior2;
        float minSmallItemWidth2;
        float maxSmallItemWidth2;
        PaddingValues paddingValues;
        CarouselState carouselState;
        PaddingValues contentPadding2;
        TargetedFlingBehavior flingBehavior3;
        int $dirty;
        Modifier modifier3;
        float itemSpacing2;
        float maxSmallItemWidth3;
        Object value$iv;
        Composer $composer2;
        final PaddingValues contentPadding3;
        final float itemSpacing3;
        final TargetedFlingBehavior flingBehavior4;
        final float maxSmallItemWidth4;
        final float itemSpacing4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-1825706865);
        ComposerKt.sourceInformation($composer3, "C(HorizontalMultiBrowseCarousel)P(8,7:c#ui.unit.Dp,6,3:c#ui.unit.Dp,2,5:c#ui.unit.Dp,4:c#ui.unit.Dp,1)106@5374L41,112@5703L7,116@5816L554,113@5715L1048:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            f = preferredItemWidth;
        } else if (($changed & 48) == 0) {
            f = preferredItemWidth;
            $dirty2 |= $composer3.changed(f) ? 32 : 16;
        } else {
            f = preferredItemWidth;
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
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            f2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            f2 = itemSpacing;
            $dirty2 |= $composer3.changed(f2) ? 2048 : 1024;
        } else {
            f2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                if ($composer3.changed(flingBehavior2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                flingBehavior2 = flingBehavior;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            minSmallItemWidth2 = minSmallItemWidth;
        } else if ((196608 & $changed) == 0) {
            minSmallItemWidth2 = minSmallItemWidth;
            $dirty2 |= $composer3.changed(minSmallItemWidth2) ? 131072 : 65536;
        } else {
            minSmallItemWidth2 = minSmallItemWidth;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            maxSmallItemWidth2 = maxSmallItemWidth;
        } else if (($changed & 1572864) == 0) {
            maxSmallItemWidth2 = maxSmallItemWidth;
            $dirty2 |= $composer3.changed(maxSmallItemWidth2) ? 1048576 : 524288;
        } else {
            maxSmallItemWidth2 = maxSmallItemWidth;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
            paddingValues = contentPadding;
        } else if (($changed & 12582912) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? 8388608 : 4194304;
        } else {
            paddingValues = contentPadding;
        }
        int $dirty3 = $dirty2;
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            itemSpacing3 = f2;
            contentPadding3 = paddingValues;
            $composer2 = $composer3;
            flingBehavior4 = flingBehavior2;
            maxSmallItemWidth4 = maxSmallItemWidth2;
            itemSpacing4 = minSmallItemWidth2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                float itemSpacing5 = i4 != 0 ? Dp.m6626constructorimpl(0) : f2;
                if ((i & 16) != 0) {
                    int i8 = ($dirty3 & 14) | 384;
                    carouselState = state;
                    $dirty3 &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i8, 2);
                } else {
                    carouselState = state;
                }
                if (i5 != 0) {
                    minSmallItemWidth2 = CarouselDefaults.INSTANCE.m2885getMinSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i6 != 0) {
                    maxSmallItemWidth2 = CarouselDefaults.INSTANCE.m2884getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i7 != 0) {
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                    flingBehavior3 = flingBehavior2;
                    $dirty = $dirty3;
                    modifier3 = modifier4;
                    itemSpacing2 = itemSpacing5;
                } else {
                    contentPadding2 = paddingValues;
                    flingBehavior3 = flingBehavior2;
                    $dirty = $dirty3;
                    modifier3 = modifier4;
                    itemSpacing2 = itemSpacing5;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                float f3 = f2;
                modifier3 = modifier2;
                contentPadding2 = paddingValues;
                itemSpacing2 = f3;
                carouselState = state;
                flingBehavior3 = flingBehavior2;
                $dirty = $dirty3;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825706865, $dirty, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            int $dirty4 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) consume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer3, 1995157598, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(density) | (($dirty4 & 112) == 32) | $composer3.changedInstance(carouselState) | (($dirty4 & 458752) == 131072) | (($dirty4 & 3670016) == 1048576);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float f4 = f;
                final float maxSmallItemWidth5 = maxSmallItemWidth2;
                final float minSmallItemWidth3 = minSmallItemWidth2;
                maxSmallItemWidth3 = maxSmallItemWidth5;
                value$iv = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f5, Float f6) {
                        return invoke(f5.floatValue(), f6.floatValue());
                    }

                    public final KeylineList invoke(float availableSpace, float itemSpacingPx) {
                        Density $this$invoke_u24lambda_u240 = Density.this;
                        float f5 = f4;
                        CarouselState carouselState2 = state;
                        float f6 = minSmallItemWidth3;
                        float f7 = maxSmallItemWidth5;
                        float availableSpace2 = $this$invoke_u24lambda_u240.mo364toPx0680j_4(f5);
                        return KeylinesKt.multiBrowseKeylineList($this$invoke_u24lambda_u240, availableSpace, availableSpace2, itemSpacingPx, carouselState2.getItemCountState().getValue().invoke().intValue(), $this$invoke_u24lambda_u240.mo364toPx0680j_4(f6), $this$invoke_u24lambda_u240.mo364toPx0680j_4(f7));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
                maxSmallItemWidth3 = maxSmallItemWidth2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m2887CarouselV95POc(state, orientation, (Function2) value$iv, contentPadding2, 2, modifier3, itemSpacing2, flingBehavior3, function4, $composer3, ($dirty4 & 14) | 24624 | (($dirty4 >> 12) & 7168) | (($dirty4 << 9) & 458752) | (($dirty4 << 9) & 3670016) | (29360128 & ($dirty4 << 9)) | ($dirty4 & 234881024), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentPadding3 = contentPadding2;
            modifier2 = modifier3;
            itemSpacing3 = itemSpacing2;
            flingBehavior4 = flingBehavior3;
            maxSmallItemWidth4 = maxSmallItemWidth3;
            itemSpacing4 = minSmallItemWidth2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                public final void invoke(Composer composer, int i9) {
                    CarouselKt.m2888HorizontalMultiBrowseCarouselzCIJ0Nk(CarouselState.this, preferredItemWidth, modifier2, itemSpacing3, flingBehavior4, itemSpacing4, maxSmallItemWidth4, contentPadding3, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01f0  */
    /* renamed from: HorizontalUncontainedCarousel-9QcgTRs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2889HorizontalUncontainedCarousel9QcgTRs(final androidx.compose.material3.carousel.CarouselState r21, final float r22, androidx.compose.ui.Modifier r23, float r24, androidx.compose.foundation.gestures.TargetedFlingBehavior r25, androidx.compose.foundation.layout.PaddingValues r26, final kotlin.jvm.functions.Function4<? super androidx.compose.material3.carousel.CarouselItemScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.carousel.CarouselKt.m2889HorizontalUncontainedCarousel9QcgTRs(androidx.compose.material3.carousel.CarouselState, float, androidx.compose.ui.Modifier, float, androidx.compose.foundation.gestures.TargetedFlingBehavior, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026f  */
    /* renamed from: Carousel-V-95POc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2887CarouselV95POc(final androidx.compose.material3.carousel.CarouselState r38, final androidx.compose.foundation.gestures.Orientation r39, final kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Float, androidx.compose.material3.carousel.KeylineList> r40, final androidx.compose.foundation.layout.PaddingValues r41, final int r42, androidx.compose.ui.Modifier r43, float r44, androidx.compose.foundation.gestures.TargetedFlingBehavior r45, final kotlin.jvm.functions.Function4<? super androidx.compose.material3.carousel.CarouselItemScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, androidx.compose.runtime.Composer r47, final int r48, final int r49) {
        /*
            Method dump skipped, instructions count: 850
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.carousel.CarouselKt.m2887CarouselV95POc(androidx.compose.material3.carousel.CarouselState, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, int, androidx.compose.ui.Modifier, float, androidx.compose.foundation.gestures.TargetedFlingBehavior, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float calculateBeforeContentPadding(PaddingValues $this$calculateBeforeContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1896839347, "C(calculateBeforeContentPadding)*349@15698L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896839347, $changed, -1, "androidx.compose.material3.carousel.calculateBeforeContentPadding (Carousel.kt:341)");
        }
        $composer.startReplaceGroup(295830617);
        ComposerKt.sourceInformation($composer, "346@15649L7");
        if (orientation == Orientation.Vertical) {
            dpValue = $this$calculateBeforeContentPadding.getTop();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateStartPadding($this$calculateBeforeContentPadding, (LayoutDirection) consume);
        }
        $composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateBeforeContentPadding_u24lambda_u243 = (Density) consume2;
        float mo364toPx0680j_4 = $this$calculateBeforeContentPadding_u24lambda_u243.mo364toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return mo364toPx0680j_4;
    }

    private static final float calculateAfterContentPadding(PaddingValues $this$calculateAfterContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1018496720, "C(calculateAfterContentPadding)*361@16056L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018496720, $changed, -1, "androidx.compose.material3.carousel.calculateAfterContentPadding (Carousel.kt:353)");
        }
        $composer.startReplaceGroup(-587616383);
        ComposerKt.sourceInformation($composer, "358@16007L7");
        if (orientation == Orientation.Vertical) {
            dpValue = $this$calculateAfterContentPadding.getBottom();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateEndPadding($this$calculateAfterContentPadding, (LayoutDirection) consume);
        }
        $composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateAfterContentPadding_u24lambda_u244 = (Density) consume2;
        float mo364toPx0680j_4 = $this$calculateAfterContentPadding_u24lambda_u244.mo364toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return mo364toPx0680j_4;
    }

    public static final Modifier carouselItem(Modifier $this$carouselItem, final int index, final CarouselState state, final Function0<Strategy> function0, final CarouselItemInfoImpl carouselItemInfo, final Shape clipShape) {
        return LayoutModifierKt.layout($this$carouselItem, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.carousel.CarouselKt$carouselItem$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m2890invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m2890invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                long m6569copyZbe2FdA;
                final Strategy strategyResult = function0.invoke();
                if (!strategyResult.getIsValid()) {
                    return MeasureScope.layout$default($this$layout, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$carouselItem$1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        }
                    }, 4, null);
                }
                final boolean isVertical = state.getPagerState().getLayoutInfo().getOrientation() == Orientation.Vertical;
                final boolean isRtl = $this$layout.getLayoutDirection() == LayoutDirection.Rtl;
                float mainAxisSize = strategyResult.getItemMainAxisSize();
                if (isVertical) {
                    m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, Constraints.m6581getMinWidthimpl(constraints), Constraints.m6579getMaxWidthimpl(constraints), MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize));
                } else {
                    m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize), Constraints.m6580getMinHeightimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints));
                }
                long itemConstraints = m6569copyZbe2FdA;
                final Placeable placeable = measurable.mo5491measureBRTryo0(itemConstraints);
                int width = placeable.getWidth();
                int height = placeable.getHeight();
                final CarouselState carouselState = state;
                final int i = index;
                final CarouselItemInfoImpl carouselItemInfoImpl = carouselItemInfo;
                final Shape shape = clipShape;
                return MeasureScope.layout$default($this$layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$carouselItem$1.2
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
                    public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        Placeable placeable2 = Placeable.this;
                        final CarouselState carouselState2 = carouselState;
                        final Strategy strategy = strategyResult;
                        final int i2 = i;
                        final boolean z = isVertical;
                        final CarouselItemInfoImpl carouselItemInfoImpl2 = carouselItemInfoImpl;
                        final Shape shape2 = shape;
                        final boolean z2 = isRtl;
                        Placeable.PlacementScope.placeWithLayer$default($this$layout2, placeable2, 0, 0, 0.0f, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                invoke2(graphicsLayerScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GraphicsLayerScope $this$placeWithLayer) {
                                float progress;
                                float scrollOffset = CarouselKt.calculateCurrentScrollOffset(CarouselState.this, strategy);
                                float maxScrollOffset = CarouselKt.calculateMaxScrollOffset(CarouselState.this, strategy);
                                KeylineList keylines = Strategy.getKeylineListForScrollOffset$material3_release$default(strategy, scrollOffset, maxScrollOffset, false, 4, null);
                                KeylineList roundedKeylines = strategy.getKeylineListForScrollOffset$material3_release(scrollOffset, maxScrollOffset, true);
                                float itemSizeWithSpacing = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
                                float unadjustedCenter = ((i2 * itemSizeWithSpacing) + (strategy.getItemMainAxisSize() / 2.0f)) - scrollOffset;
                                Keyline keylineBefore = keylines.getKeylineBefore(unadjustedCenter);
                                Keyline keylineAfter = keylines.getKeylineAfter(unadjustedCenter);
                                progress = CarouselKt.getProgress(keylineBefore, keylineAfter, unadjustedCenter);
                                Keyline interpolatedKeyline = KeylineListKt.lerp(keylineBefore, keylineAfter, progress);
                                boolean isOutOfKeylineBounds = Intrinsics.areEqual(keylineBefore, keylineAfter);
                                float centerX = (z ? Size.m3988getHeightimpl($this$placeWithLayer.getSize()) : strategy.getItemMainAxisSize()) / 2.0f;
                                float centerY = (z ? strategy.getItemMainAxisSize() : Size.m3988getHeightimpl($this$placeWithLayer.getSize())) / 2.0f;
                                float halfMaskWidth = (z ? Size.m3991getWidthimpl($this$placeWithLayer.getSize()) : interpolatedKeyline.getSize()) / 2.0f;
                                float halfMaskHeight = (z ? interpolatedKeyline.getSize() : Size.m3988getHeightimpl($this$placeWithLayer.getSize())) / 2.0f;
                                Rect maskRect = new Rect(centerX - halfMaskWidth, centerY - halfMaskHeight, centerX + halfMaskWidth, centerY + halfMaskHeight);
                                carouselItemInfoImpl2.setSizeState(interpolatedKeyline.getSize());
                                CarouselItemInfoImpl carouselItemInfoImpl3 = carouselItemInfoImpl2;
                                KeylineList $this$minBy$iv = roundedKeylines;
                                int $i$f$minByOrThrow = 0;
                                Iterator iterator$iv = $this$minBy$iv.iterator();
                                if (!iterator$iv.hasNext()) {
                                    throw new NoSuchElementException();
                                }
                                Object minElem$iv = iterator$iv.next();
                                if (iterator$iv.hasNext()) {
                                    Keyline it = (Keyline) minElem$iv;
                                    float minValue$iv = it.getSize();
                                    while (true) {
                                        Object e$iv = iterator$iv.next();
                                        Keyline it2 = (Keyline) e$iv;
                                        float size = it2.getSize();
                                        int $i$f$minByOrThrow2 = $i$f$minByOrThrow;
                                        if (Float.compare(minValue$iv, size) > 0) {
                                            minElem$iv = e$iv;
                                            minValue$iv = size;
                                        }
                                        if (!iterator$iv.hasNext()) {
                                            break;
                                        } else {
                                            $i$f$minByOrThrow = $i$f$minByOrThrow2;
                                        }
                                    }
                                }
                                carouselItemInfoImpl3.setMinSizeState(((Keyline) minElem$iv).getSize());
                                carouselItemInfoImpl2.setMaxSizeState(roundedKeylines.getFirstFocal().getSize());
                                carouselItemInfoImpl2.setMaskRectState(maskRect);
                                $this$placeWithLayer.setClip(!Intrinsics.areEqual(maskRect, new Rect(0.0f, 0.0f, Size.m3991getWidthimpl($this$placeWithLayer.getSize()), Size.m3988getHeightimpl($this$placeWithLayer.getSize()))));
                                $this$placeWithLayer.setShape(shape2);
                                float translation = interpolatedKeyline.getOffset() - unadjustedCenter;
                                if (isOutOfKeylineBounds) {
                                    float outOfBoundsOffset = (unadjustedCenter - interpolatedKeyline.getUnadjustedOffset()) / interpolatedKeyline.getSize();
                                    translation += outOfBoundsOffset;
                                }
                                if (z) {
                                    $this$placeWithLayer.setTranslationY(translation);
                                } else {
                                    $this$placeWithLayer.setTranslationX(z2 ? -translation : translation);
                                }
                            }
                        }, 4, (Object) null);
                    }
                }, 4, null);
            }
        });
    }

    public static final float calculateCurrentScrollOffset(CarouselState state, Strategy strategy) {
        float itemSizeWithSpacing = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
        float currentItemScrollOffset = (state.getPagerState().getCurrentPage() * itemSizeWithSpacing) + (state.getPagerState().getCurrentPageOffsetFraction() * itemSizeWithSpacing);
        return currentItemScrollOffset - KeylineSnapPositionKt.getSnapPositionOffset(strategy, state.getPagerState().getCurrentPage(), state.getPagerState().getPageCount());
    }

    public static final float calculateMaxScrollOffset(CarouselState state, Strategy strategy) {
        float itemCount = state.getPagerState().getPageCount();
        float maxScrollPossible = (strategy.getItemMainAxisSize() * itemCount) + (strategy.getItemSpacing() * (itemCount - 1.0f));
        return RangesKt.coerceAtLeast(maxScrollPossible - strategy.getAvailableSpace(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float getProgress(Keyline before, Keyline after, float unadjustedOffset) {
        if (Intrinsics.areEqual(before, after)) {
            return 1.0f;
        }
        float total = after.getUnadjustedOffset() - before.getUnadjustedOffset();
        return (unadjustedOffset - before.getUnadjustedOffset()) / total;
    }
}
