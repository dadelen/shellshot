package androidx.compose.ui.unit;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: Constraints.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\u001a5\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001a\u001a\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0002\u001a-\u0010 \u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0082\b\u001a\u0011\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0082\b\u001a\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0002\u001a\u0010\u0010)\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020\u0003H\u0002\u001a\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0002\u001a\u0011\u0010,\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0082\b\u001a\u0011\u0010-\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0082\b\u001a\u001c\u0010.\u001a\u00020\u0015*\u00020\u00152\u0006\u0010/\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001e\u0010.\u001a\u000202*\u00020\u00152\u0006\u0010\u001f\u001a\u000202H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00101\u001a\u001e\u00104\u001a\u00020\u0003*\u00020\u00152\u0006\u00105\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001a\u001e\u00108\u001a\u00020\u0003*\u00020\u00152\u0006\u00109\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b:\u00107\u001a\u001e\u0010;\u001a\u00020<*\u00020\u00152\u0006\u0010\u001f\u001a\u000202H\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a*\u0010?\u001a\u00020\u0015*\u00020\u00152\b\b\u0002\u0010@\u001a\u00020\u00032\b\b\u0002\u0010A\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\bB\u0010C\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"FocusMask", "", "Infinity", "", "MaxAllowedForMaxFocusBits", "MaxAllowedForMaxNonFocusBits", "MaxAllowedForMinFocusBits", "MaxAllowedForMinNonFocusBits", "MaxFocusBits", "MaxFocusHeight", "MaxFocusMask", "MaxFocusWidth", "MaxNonFocusBits", "MaxNonFocusMask", "MinFocusBits", "MinFocusHeight", "MinFocusMask", "MinFocusWidth", "MinNonFocusBits", "MinNonFocusMask", androidx.constraintlayout.widget.Constraints.TAG, "Landroidx/compose/ui/unit/Constraints;", "minWidth", "maxWidth", "minHeight", "maxHeight", "(IIII)J", "addMaxWithMinimum", "max", "value", "bitsNeedForSizeUnchecked", "size", "createConstraints", "heightMask", "bitOffset", "indexToBitOffset", "index", "invalidConstraint", "", "widthVal", "heightVal", "invalidSize", "", "maxAllowedForSize", "minHeightOffsets", "widthMask", "constrain", "otherConstraints", "constrain-N9IONVI", "(JJ)J", "Landroidx/compose/ui/unit/IntSize;", "constrain-4WqzIAM", "constrainHeight", "height", "constrainHeight-K40F9xA", "(JI)I", "constrainWidth", "width", "constrainWidth-K40F9xA", "isSatisfiedBy", "", "isSatisfiedBy-4WqzIAM", "(JJ)Z", "offset", "horizontal", "vertical", "offset-NN6Ew-U", "(JII)J", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ConstraintsKt {
    private static final long FocusMask = 3;
    private static final int Infinity = Integer.MAX_VALUE;
    private static final int MaxAllowedForMaxFocusBits = 8190;
    private static final int MaxAllowedForMaxNonFocusBits = 262142;
    private static final int MaxAllowedForMinFocusBits = 32766;
    private static final int MaxAllowedForMinNonFocusBits = 65534;
    private static final int MaxFocusBits = 18;
    private static final int MaxFocusHeight = 0;
    private static final int MaxFocusMask = 262143;
    private static final int MaxFocusWidth = 3;
    private static final int MaxNonFocusBits = 13;
    private static final int MaxNonFocusMask = 8191;
    private static final int MinFocusBits = 16;
    private static final int MinFocusHeight = 1;
    private static final int MinFocusMask = 65535;
    private static final int MinFocusWidth = 2;
    private static final int MinNonFocusBits = 15;
    private static final int MinNonFocusMask = 32767;

    private static final void invalidConstraint(int widthVal, int heightVal) {
        throw new IllegalArgumentException("Can't represent a width of " + widthVal + " and height of " + heightVal + " in Constraints");
    }

    private static final Void invalidSize(int size) {
        throw new IllegalArgumentException("Can't represent a size of " + size + " in Constraints");
    }

    public static final long createConstraints(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        int heightVal = maxHeight == Integer.MAX_VALUE ? minHeight : maxHeight;
        int heightBits = bitsNeedForSizeUnchecked(heightVal);
        int widthVal = maxWidth == Integer.MAX_VALUE ? minWidth : maxWidth;
        int widthBits = bitsNeedForSizeUnchecked(widthVal);
        if (widthBits + heightBits > 31) {
            invalidConstraint(widthVal, heightVal);
        }
        int maxWidthValue = maxWidth + 1;
        int maxWidthValue2 = maxWidthValue & (~(maxWidthValue >> 31));
        int maxHeightValue = maxHeight + 1;
        int maxHeightValue2 = maxHeightValue & (~(maxHeightValue >> 31));
        int focus = 0;
        switch (widthBits) {
            case 15:
                focus = 1;
                break;
            case 16:
                focus = 2;
                break;
            case 18:
                focus = 3;
                break;
        }
        int bitOffset$iv = ((focus & 1) << 1) + (((focus & 2) >> 1) * 3);
        int minHeightOffset = bitOffset$iv + 15;
        int maxHeightOffset = minHeightOffset + 31;
        long value = (maxWidthValue2 << 33) | focus | (minWidth << 2) | (minHeight << minHeightOffset) | (maxHeightValue2 << maxHeightOffset);
        return Constraints.m6568constructorimpl(value);
    }

    private static final int bitsNeedForSizeUnchecked(int size) {
        if (size < MaxNonFocusMask) {
            return 13;
        }
        if (size < 32767) {
            return 15;
        }
        if (size < 65535) {
            return 16;
        }
        return size < MaxFocusMask ? 18 : 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int maxAllowedForSize(int size) {
        if (size < MaxNonFocusMask) {
            return MaxAllowedForMaxNonFocusBits;
        }
        if (size < 32767) {
            return MaxAllowedForMinNonFocusBits;
        }
        if (size < 65535) {
            return MaxAllowedForMinFocusBits;
        }
        if (size < MaxFocusMask) {
            return MaxAllowedForMaxFocusBits;
        }
        invalidSize(size);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return Constraints(i, i2, i3, i4);
    }

    public static final long Constraints(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        boolean value$iv = maxWidth >= minWidth;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth(" + maxWidth + ") must be >= than minWidth(" + minWidth + ')');
        }
        boolean value$iv2 = maxHeight >= minHeight;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("maxHeight(" + maxHeight + ") must be >= than minHeight(" + minHeight + ')');
        }
        boolean value$iv3 = minWidth >= 0 && minHeight >= 0;
        if (!value$iv3) {
            InlineClassHelperKt.throwIllegalArgumentException("minWidth(" + minWidth + ") and minHeight(" + minHeight + ") must be >= 0");
        }
        return createConstraints(minWidth, maxWidth, minHeight, maxHeight);
    }

    /* renamed from: constrain-N9IONVI, reason: not valid java name */
    public static final long m6594constrainN9IONVI(long $this$constrain_u2dN9IONVI, long otherConstraints) {
        return Constraints(RangesKt.coerceIn(Constraints.m6581getMinWidthimpl(otherConstraints), Constraints.m6581getMinWidthimpl($this$constrain_u2dN9IONVI), Constraints.m6579getMaxWidthimpl($this$constrain_u2dN9IONVI)), RangesKt.coerceIn(Constraints.m6579getMaxWidthimpl(otherConstraints), Constraints.m6581getMinWidthimpl($this$constrain_u2dN9IONVI), Constraints.m6579getMaxWidthimpl($this$constrain_u2dN9IONVI)), RangesKt.coerceIn(Constraints.m6580getMinHeightimpl(otherConstraints), Constraints.m6580getMinHeightimpl($this$constrain_u2dN9IONVI), Constraints.m6578getMaxHeightimpl($this$constrain_u2dN9IONVI)), RangesKt.coerceIn(Constraints.m6578getMaxHeightimpl(otherConstraints), Constraints.m6580getMinHeightimpl($this$constrain_u2dN9IONVI), Constraints.m6578getMaxHeightimpl($this$constrain_u2dN9IONVI)));
    }

    /* renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m6593constrain4WqzIAM(long $this$constrain_u2d4WqzIAM, long size) {
        return IntSizeKt.IntSize(RangesKt.coerceIn(IntSize.m6796getWidthimpl(size), Constraints.m6581getMinWidthimpl($this$constrain_u2d4WqzIAM), Constraints.m6579getMaxWidthimpl($this$constrain_u2d4WqzIAM)), RangesKt.coerceIn(IntSize.m6795getHeightimpl(size), Constraints.m6580getMinHeightimpl($this$constrain_u2d4WqzIAM), Constraints.m6578getMaxHeightimpl($this$constrain_u2d4WqzIAM)));
    }

    /* renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final int m6596constrainWidthK40F9xA(long $this$constrainWidth_u2dK40F9xA, int width) {
        return RangesKt.coerceIn(width, Constraints.m6581getMinWidthimpl($this$constrainWidth_u2dK40F9xA), Constraints.m6579getMaxWidthimpl($this$constrainWidth_u2dK40F9xA));
    }

    /* renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final int m6595constrainHeightK40F9xA(long $this$constrainHeight_u2dK40F9xA, int height) {
        return RangesKt.coerceIn(height, Constraints.m6580getMinHeightimpl($this$constrainHeight_u2dK40F9xA), Constraints.m6578getMaxHeightimpl($this$constrainHeight_u2dK40F9xA));
    }

    /* renamed from: isSatisfiedBy-4WqzIAM, reason: not valid java name */
    public static final boolean m6597isSatisfiedBy4WqzIAM(long $this$isSatisfiedBy_u2d4WqzIAM, long size) {
        int m6581getMinWidthimpl = Constraints.m6581getMinWidthimpl($this$isSatisfiedBy_u2d4WqzIAM);
        int m6579getMaxWidthimpl = Constraints.m6579getMaxWidthimpl($this$isSatisfiedBy_u2d4WqzIAM);
        int m6796getWidthimpl = IntSize.m6796getWidthimpl(size);
        if (m6581getMinWidthimpl <= m6796getWidthimpl && m6796getWidthimpl <= m6579getMaxWidthimpl) {
            int m6580getMinHeightimpl = Constraints.m6580getMinHeightimpl($this$isSatisfiedBy_u2d4WqzIAM);
            int m6578getMaxHeightimpl = Constraints.m6578getMaxHeightimpl($this$isSatisfiedBy_u2d4WqzIAM);
            int m6795getHeightimpl = IntSize.m6795getHeightimpl(size);
            if (m6580getMinHeightimpl <= m6795getHeightimpl && m6795getHeightimpl <= m6578getMaxHeightimpl) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: offset-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ long m6599offsetNN6EwU$default(long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m6598offsetNN6EwU(j, i, i2);
    }

    /* renamed from: offset-NN6Ew-U, reason: not valid java name */
    public static final long m6598offsetNN6EwU(long $this$offset_u2dNN6Ew_u2dU, int horizontal, int vertical) {
        return Constraints(RangesKt.coerceAtLeast(Constraints.m6581getMinWidthimpl($this$offset_u2dNN6Ew_u2dU) + horizontal, 0), addMaxWithMinimum(Constraints.m6579getMaxWidthimpl($this$offset_u2dNN6Ew_u2dU), horizontal), RangesKt.coerceAtLeast(Constraints.m6580getMinHeightimpl($this$offset_u2dNN6Ew_u2dU) + vertical, 0), addMaxWithMinimum(Constraints.m6578getMaxHeightimpl($this$offset_u2dNN6Ew_u2dU), vertical));
    }

    private static final int addMaxWithMinimum(int max, int value) {
        if (max == Integer.MAX_VALUE) {
            return max;
        }
        return RangesKt.coerceAtLeast(max + value, 0);
    }

    private static final int indexToBitOffset(int index) {
        return ((index & 1) << 1) + (((index & 2) >> 1) * 3);
    }

    private static final int minHeightOffsets(int bitOffset) {
        return bitOffset + 15;
    }

    private static final int widthMask(int bitOffset) {
        return (1 << (bitOffset + 13)) - 1;
    }

    private static final int heightMask(int bitOffset) {
        return (1 << (18 - bitOffset)) - 1;
    }
}
