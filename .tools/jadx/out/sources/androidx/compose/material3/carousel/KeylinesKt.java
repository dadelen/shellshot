package androidx.compose.material3.carousel;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: Keylines.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001aD\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00012\b\b\u0002\u0010\u0014\u001a\u00020\u0001H\u0000\u001a(\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0000¨\u0006\u0017"}, d2 = {"calculateMediumChildSize", "", "minimumMediumSize", "largeItemSize", "remainingSpace", "createLeftAlignedKeylineList", "Landroidx/compose/material3/carousel/KeylineList;", "carouselMainAxisSize", "itemSpacing", "leftAnchorSize", "rightAnchorSize", "arrangement", "Landroidx/compose/material3/carousel/Arrangement;", "multiBrowseKeylineList", "density", "Landroidx/compose/ui/unit/Density;", "preferredItemSize", "itemCount", "", "minSmallItemSize", "maxSmallItemSize", "uncontainedKeylineList", "itemSize", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class KeylinesKt {
    public static /* synthetic */ KeylineList multiBrowseKeylineList$default(Density $this$multiBrowseKeylineList_u24lambda_u240, float f, float f2, float f3, int i, float f4, float f5, int i2, Object obj) {
        float f6;
        float f7;
        if ((i2 & 32) == 0) {
            f6 = f4;
        } else {
            f6 = $this$multiBrowseKeylineList_u24lambda_u240.mo364toPx0680j_4(CarouselDefaults.INSTANCE.m2885getMinSmallItemSizeD9Ej5fM$material3_release());
        }
        if ((i2 & 64) == 0) {
            f7 = f5;
        } else {
            f7 = $this$multiBrowseKeylineList_u24lambda_u240.mo364toPx0680j_4(CarouselDefaults.INSTANCE.m2884getMaxSmallItemSizeD9Ej5fM$material3_release());
        }
        return multiBrowseKeylineList($this$multiBrowseKeylineList_u24lambda_u240, f, f2, f3, i, f6, f7);
    }

    public static final KeylineList multiBrowseKeylineList(Density density, float carouselMainAxisSize, float preferredItemSize, float itemSpacing, int itemCount, float minSmallItemSize, float maxSmallItemSize) {
        float f;
        Arrangement arrangement;
        if (!(carouselMainAxisSize == 0.0f)) {
            if (!(preferredItemSize == 0.0f)) {
                int[] smallCounts = {1};
                int[] mediumCounts = {1, 0};
                float targetLargeSize = Math.min(preferredItemSize, carouselMainAxisSize);
                float targetSmallSize = RangesKt.coerceIn(targetLargeSize / 3.0f, minSmallItemSize, maxSmallItemSize);
                float targetMediumSize = (targetLargeSize + targetSmallSize) / 2.0f;
                if (carouselMainAxisSize < 2.0f * minSmallItemSize) {
                    smallCounts = new int[]{0};
                }
                float minAvailableLargeSpace = (carouselMainAxisSize - (ArraysKt.maxOrThrow(mediumCounts) * targetMediumSize)) - (ArraysKt.maxOrThrow(smallCounts) * maxSmallItemSize);
                int minLargeCount = Math.max(1, (int) Math.floor(minAvailableLargeSpace / targetLargeSize));
                int maxLargeCount = (int) Math.ceil(carouselMainAxisSize / targetLargeSize);
                int i = (maxLargeCount - minLargeCount) + 1;
                int[] largeCounts = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    largeCounts[i2] = maxLargeCount - i2;
                }
                float anchorSize = density.mo364toPx0680j_4(CarouselDefaults.INSTANCE.m2883getAnchorSizeD9Ej5fM$material3_release());
                Arrangement arrangement2 = Arrangement.INSTANCE.findLowestCostArrangement(carouselMainAxisSize, itemSpacing, targetSmallSize, minSmallItemSize, maxSmallItemSize, smallCounts, targetMediumSize, mediumCounts, targetLargeSize, largeCounts);
                if (arrangement2 == null || arrangement2.itemCount() <= itemCount) {
                    f = carouselMainAxisSize;
                    arrangement = arrangement2;
                } else {
                    int keylineSurplus = arrangement2.itemCount() - itemCount;
                    int smallCount = arrangement2.getSmallCount();
                    int smallCount2 = smallCount;
                    int keylineSurplus2 = arrangement2.getMediumCount();
                    for (int keylineSurplus3 = keylineSurplus; keylineSurplus3 > 0; keylineSurplus3--) {
                        if (smallCount2 > 0) {
                            smallCount2--;
                        } else if (keylineSurplus2 > 1) {
                            keylineSurplus2--;
                        }
                    }
                    int mediumCount = keylineSurplus2;
                    f = carouselMainAxisSize;
                    arrangement = Arrangement.INSTANCE.findLowestCostArrangement(f, itemSpacing, targetSmallSize, minSmallItemSize, maxSmallItemSize, new int[]{smallCount2}, targetMediumSize, new int[]{mediumCount}, targetLargeSize, largeCounts);
                }
                if (arrangement == null) {
                    return KeylineListKt.emptyKeylineList();
                }
                return createLeftAlignedKeylineList(f, itemSpacing, anchorSize, anchorSize, arrangement);
            }
        }
        return KeylineListKt.emptyKeylineList();
    }

    public static final KeylineList createLeftAlignedKeylineList(float carouselMainAxisSize, float itemSpacing, final float leftAnchorSize, final float rightAnchorSize, final Arrangement arrangement) {
        return KeylineListKt.m2891keylineListOfWNYm7Xg(carouselMainAxisSize, itemSpacing, CarouselAlignment.INSTANCE.m2882getStartNUL3oTo(), new Function1<KeylineListScope, Unit>() { // from class: androidx.compose.material3.carousel.KeylinesKt$createLeftAlignedKeylineList$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeylineListScope keylineListScope) {
                invoke2(keylineListScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeylineListScope $this$keylineListOf) {
                $this$keylineListOf.add(leftAnchorSize, true);
                int largeCount = arrangement.getLargeCount();
                Arrangement arrangement2 = arrangement;
                for (int i = 0; i < largeCount; i++) {
                    KeylineListScope.add$default($this$keylineListOf, arrangement2.getLargeSize(), false, 2, null);
                }
                int mediumCount = arrangement.getMediumCount();
                Arrangement arrangement3 = arrangement;
                for (int i2 = 0; i2 < mediumCount; i2++) {
                    KeylineListScope.add$default($this$keylineListOf, arrangement3.getMediumSize(), false, 2, null);
                }
                int smallCount = arrangement.getSmallCount();
                Arrangement arrangement4 = arrangement;
                for (int i3 = 0; i3 < smallCount; i3++) {
                    KeylineListScope.add$default($this$keylineListOf, arrangement4.getSmallSize(), false, 2, null);
                }
                $this$keylineListOf.add(rightAnchorSize, true);
            }
        });
    }

    public static final KeylineList uncontainedKeylineList(Density density, float carouselMainAxisSize, float itemSize, float itemSpacing) {
        if (!(carouselMainAxisSize == 0.0f)) {
            if (!(itemSize == 0.0f)) {
                float largeItemSize = Math.min(itemSize + itemSpacing, carouselMainAxisSize);
                int largeCount = Math.max(1, (int) Math.floor(carouselMainAxisSize / largeItemSize));
                float remainingSpace = carouselMainAxisSize - (largeCount * largeItemSize);
                int mediumCount = remainingSpace > 0.0f ? 1 : 0;
                float defaultAnchorSize = density.mo364toPx0680j_4(CarouselDefaults.INSTANCE.m2883getAnchorSizeD9Ej5fM$material3_release());
                float mediumItemSize = calculateMediumChildSize(defaultAnchorSize, largeItemSize, remainingSpace);
                Arrangement arrangement = new Arrangement(0, 0.0f, 0, mediumItemSize, mediumCount, largeItemSize, largeCount);
                float xSmallSize = Math.min(defaultAnchorSize, itemSize);
                float leftAnchorSize = Math.max(xSmallSize, 0.5f * mediumItemSize);
                return createLeftAlignedKeylineList(carouselMainAxisSize, itemSpacing, leftAnchorSize, defaultAnchorSize, arrangement);
            }
        }
        return KeylineListKt.emptyKeylineList();
    }

    private static final float calculateMediumChildSize(float minimumMediumSize, float largeItemSize, float remainingSpace) {
        float sizeWithThirdCutOff = 1.5f * remainingSpace;
        float mediumItemSize = Math.max(sizeWithThirdCutOff, minimumMediumSize);
        float largeItemThreshold = 0.85f * largeItemSize;
        if (mediumItemSize > largeItemThreshold) {
            float sizeWithFifthCutOff = 1.2f * remainingSpace;
            return Math.max(largeItemThreshold, sizeWithFifthCutOff);
        }
        return mediumItemSize;
    }
}
