package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001e\u0010\u000b\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001f\u0010\u0010\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b \u0010\u0015\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m7675partition4UcCI2c(byte[] array, int left, int right) {
        int i = left;
        int j = right;
        byte pivot = UByteArray.m7292getw2LRezQ(array, (left + right) / 2);
        while (i <= j) {
            while (Intrinsics.compare(UByteArray.m7292getw2LRezQ(array, i) & UByte.MAX_VALUE, pivot & UByte.MAX_VALUE) < 0) {
                i++;
            }
            while (Intrinsics.compare(UByteArray.m7292getw2LRezQ(array, j) & UByte.MAX_VALUE, pivot & UByte.MAX_VALUE) > 0) {
                j--;
            }
            if (i <= j) {
                byte tmp = UByteArray.m7292getw2LRezQ(array, i);
                UByteArray.m7297setVurrAj0(array, i, UByteArray.m7292getw2LRezQ(array, j));
                UByteArray.m7297setVurrAj0(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m7679quickSort4UcCI2c(byte[] array, int left, int right) {
        int index = m7675partition4UcCI2c(array, left, right);
        if (left < index - 1) {
            m7679quickSort4UcCI2c(array, left, index - 1);
        }
        if (index < right) {
            m7679quickSort4UcCI2c(array, index, right);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m7676partitionAa5vz7o(short[] array, int left, int right) {
        int i = left;
        int j = right;
        short pivot = UShortArray.m7555getMh2AYeg(array, (left + right) / 2);
        while (i <= j) {
            while (Intrinsics.compare(UShortArray.m7555getMh2AYeg(array, i) & UShort.MAX_VALUE, pivot & UShort.MAX_VALUE) < 0) {
                i++;
            }
            while (Intrinsics.compare(UShortArray.m7555getMh2AYeg(array, j) & UShort.MAX_VALUE, pivot & UShort.MAX_VALUE) > 0) {
                j--;
            }
            if (i <= j) {
                short tmp = UShortArray.m7555getMh2AYeg(array, i);
                UShortArray.m7560set01HTLdE(array, i, UShortArray.m7555getMh2AYeg(array, j));
                UShortArray.m7560set01HTLdE(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m7680quickSortAa5vz7o(short[] array, int left, int right) {
        int index = m7676partitionAa5vz7o(array, left, right);
        if (left < index - 1) {
            m7680quickSortAa5vz7o(array, left, index - 1);
        }
        if (index < right) {
            m7680quickSortAa5vz7o(array, index, right);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m7677partitionoBK06Vg(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int pivot = UIntArray.m7371getpVg5ArA(array, (left + right) / 2);
        while (i <= j) {
            while (Integer.compareUnsigned(UIntArray.m7371getpVg5ArA(array, i), pivot) < 0) {
                i++;
            }
            while (Integer.compareUnsigned(UIntArray.m7371getpVg5ArA(array, j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                int tmp = UIntArray.m7371getpVg5ArA(array, i);
                UIntArray.m7376setVXSXFK8(array, i, UIntArray.m7371getpVg5ArA(array, j));
                UIntArray.m7376setVXSXFK8(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m7681quickSortoBK06Vg(int[] array, int left, int right) {
        int index = m7677partitionoBK06Vg(array, left, right);
        if (left < index - 1) {
            m7681quickSortoBK06Vg(array, left, index - 1);
        }
        if (index < right) {
            m7681quickSortoBK06Vg(array, index, right);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m7674partitionnroSd4(long[] array, int left, int right) {
        int i = left;
        int j = right;
        long pivot = ULongArray.m7450getsVKNKU(array, (left + right) / 2);
        while (i <= j) {
            while (Long.compareUnsigned(ULongArray.m7450getsVKNKU(array, i), pivot) < 0) {
                i++;
            }
            while (Long.compareUnsigned(ULongArray.m7450getsVKNKU(array, j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                long tmp = ULongArray.m7450getsVKNKU(array, i);
                ULongArray.m7455setk8EXiF4(array, i, ULongArray.m7450getsVKNKU(array, j));
                ULongArray.m7455setk8EXiF4(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m7678quickSortnroSd4(long[] array, int left, int right) {
        int index = m7674partitionnroSd4(array, left, right);
        if (left < index - 1) {
            m7678quickSortnroSd4(array, left, index - 1);
        }
        if (index < right) {
            m7678quickSortnroSd4(array, index, right);
        }
    }

    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m7683sortArray4UcCI2c(byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m7679quickSort4UcCI2c(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m7684sortArrayAa5vz7o(short[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m7680quickSortAa5vz7o(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m7685sortArrayoBK06Vg(int[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m7681quickSortoBK06Vg(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m7682sortArraynroSd4(long[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m7678quickSortnroSd4(array, fromIndex, toIndex - 1);
    }
}
