package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.ranges.RangesKt;

/* compiled from: VelocityTracker.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a(\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0015j\u0002`\u00160\u00142\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0082\b¢\u0006\u0002\u0010\u0019\u001a(\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0007H\u0002\u001a\u0011\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u0082\b\u001a2\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00012\b\b\u0002\u0010%\u001a\u00020\u0015H\u0000\u001a\u0012\u0010&\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*\u001a\u0014\u0010+\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*H\u0002\u001a\u0014\u0010,\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*H\u0002\u001a\u0014\u0010-\u001a\u00020\u0003*\u00020\u00152\u0006\u0010.\u001a\u00020\u0015H\u0002\u001a,\u0010/\u001a\u00020\u0003*\f\u0012\u0004\u0012\u00020\u00150\u0014j\u0002`02\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001H\u0082\n¢\u0006\u0002\u00103\u001a\r\u00104\u001a\u00020\u0003*\u00020\u0015H\u0082\b\u001a4\u00105\u001a\u00020'*\f\u0012\u0004\u0012\u00020\u00150\u0014j\u0002`02\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u0003H\u0082\n¢\u0006\u0002\u00107\u001a1\u00105\u001a\u00020'*\n\u0012\u0006\u0012\u0004\u0018\u0001080\u00142\u0006\u00109\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010<\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\",\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078G@GX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\",\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078G@GX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000e*\u0018\b\u0002\u0010\u0013\"\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0012\u0004\u0012\u00020\u00150\u0014*\f\b\u0002\u0010=\"\u00020\u00152\u00020\u0015¨\u0006>"}, d2 = {"AssumePointerMoveStoppedMilliseconds", "", "DefaultWeight", "", "HistorySize", "HorizonMilliseconds", "<set-?>", "", "VelocityTrackerAddPointsFix", "getVelocityTrackerAddPointsFix$annotations", "()V", "getVelocityTrackerAddPointsFix", "()Z", "setVelocityTrackerAddPointsFix", "(Z)V", "VelocityTrackerStrategyUseImpulse", "getVelocityTrackerStrategyUseImpulse$annotations", "getVelocityTrackerStrategyUseImpulse", "setVelocityTrackerStrategyUseImpulse", "Matrix", "", "", "Landroidx/compose/ui/input/pointer/util/Vector;", "rows", "cols", "(II)[[F", "calculateImpulseVelocity", "dataPoints", "time", "sampleCount", "isDataDifferential", "kineticEnergyToVelocity", "kineticEnergy", "polyFitLeastSquares", "x", "y", "degree", "coefficients", "addPointerInputChange", "", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerInputChange;", "addPointerInputChangeLegacy", "addPointerInputChangeWithFix", "dot", "a", "get", "Landroidx/compose/ui/input/pointer/util/Matrix;", "row", "col", "([[FII)F", "norm", "set", "value", "([[FIIF)V", "Landroidx/compose/ui/input/pointer/util/DataPointAtTime;", "index", "", "dataPoint", "([Landroidx/compose/ui/input/pointer/util/DataPointAtTime;IJF)V", "Vector", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static boolean VelocityTrackerAddPointsFix = true;
    private static boolean VelocityTrackerStrategyUseImpulse;

    public static /* synthetic */ void getVelocityTrackerAddPointsFix$annotations() {
    }

    public static /* synthetic */ void getVelocityTrackerStrategyUseImpulse$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void set(DataPointAtTime[] $this$set, int index, long time, float dataPoint) {
        DataPointAtTime currentEntry = $this$set[index];
        if (currentEntry == null) {
            $this$set[index] = new DataPointAtTime(time, dataPoint);
        } else {
            currentEntry.setTime(time);
            currentEntry.setDataPoint(dataPoint);
        }
    }

    public static final void addPointerInputChange(VelocityTracker $this$addPointerInputChange, PointerInputChange event) {
        if (VelocityTrackerAddPointsFix) {
            addPointerInputChangeWithFix($this$addPointerInputChange, event);
        } else {
            addPointerInputChangeLegacy($this$addPointerInputChange, event);
        }
    }

    private static final void addPointerInputChangeLegacy(VelocityTracker $this$addPointerInputChangeLegacy, PointerInputChange event) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            $this$addPointerInputChangeLegacy.m5464setCurrentPointerPositionAccumulatork4lQ0M$ui_release(event.getPosition());
            $this$addPointerInputChangeLegacy.resetTracking();
        }
        long previousPointerPosition = event.getPreviousPosition();
        List $this$fastForEach$iv = event.getHistorical();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            HistoricalChange it = (HistoricalChange) item$iv;
            long historicalDelta = Offset.m3926minusMKHz9U(it.getPosition(), previousPointerPosition);
            previousPointerPosition = it.getPosition();
            $this$addPointerInputChangeLegacy.m5464setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3927plusMKHz9U($this$addPointerInputChangeLegacy.getCurrentPointerPositionAccumulator(), historicalDelta));
            $this$addPointerInputChangeLegacy.m5460addPositionUv8p0NA(it.getUptimeMillis(), $this$addPointerInputChangeLegacy.getCurrentPointerPositionAccumulator());
        }
        long delta = Offset.m3926minusMKHz9U(event.getPosition(), previousPointerPosition);
        $this$addPointerInputChangeLegacy.m5464setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3927plusMKHz9U($this$addPointerInputChangeLegacy.getCurrentPointerPositionAccumulator(), delta));
        $this$addPointerInputChangeLegacy.m5460addPositionUv8p0NA(event.getUptimeMillis(), $this$addPointerInputChangeLegacy.getCurrentPointerPositionAccumulator());
    }

    private static final void addPointerInputChangeWithFix(VelocityTracker $this$addPointerInputChangeWithFix, PointerInputChange event) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            $this$addPointerInputChangeWithFix.resetTracking();
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(event)) {
            List $this$fastForEach$iv = event.getHistorical();
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                HistoricalChange it = (HistoricalChange) item$iv;
                $this$addPointerInputChangeWithFix.m5460addPositionUv8p0NA(it.getUptimeMillis(), it.getOriginalEventPosition());
            }
            $this$addPointerInputChangeWithFix.m5460addPositionUv8p0NA(event.getUptimeMillis(), event.getOriginalEventPosition());
        }
        if (PointerEventKt.changedToUpIgnoreConsumed(event) && event.getUptimeMillis() - $this$addPointerInputChangeWithFix.getLastMoveEventTimeStamp() > 40) {
            $this$addPointerInputChangeWithFix.resetTracking();
        }
        $this$addPointerInputChangeWithFix.setLastMoveEventTimeStamp$ui_release(event.getUptimeMillis());
    }

    public static /* synthetic */ float[] polyFitLeastSquares$default(float[] fArr, float[] fArr2, int i, int i2, float[] fArr3, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            fArr3 = new float[RangesKt.coerceAtLeast(i2 + 1, 0)];
        }
        return polyFitLeastSquares(fArr, fArr2, i, i2, fArr3);
    }

    public static final float[] polyFitLeastSquares(float[] x, float[] y, int sampleCount, int degree, float[] coefficients) {
        if (degree < 1) {
            InlineClassHelperKt.throwIllegalArgumentException("The degree must be at positive integer");
        }
        if (sampleCount == 0) {
            InlineClassHelperKt.throwIllegalArgumentException("At least one point must be provided");
        }
        int truncatedDegree = degree >= sampleCount ? sampleCount - 1 : degree;
        int n = truncatedDegree + 1;
        float[][] a = new float[n][];
        for (int i = 0; i < n; i++) {
            a[i] = new float[sampleCount];
        }
        for (int h = 0; h < sampleCount; h++) {
            a[0][h] = 1.0f;
            for (int i2 = 1; i2 < n; i2++) {
                int row$iv = i2 - 1;
                float value$iv = a[row$iv][h] * x[h];
                a[i2][h] = value$iv;
            }
        }
        float[][] q = new float[n][];
        for (int i3 = 0; i3 < n; i3++) {
            q[i3] = new float[sampleCount];
        }
        float[][] r = new float[n][];
        for (int i4 = 0; i4 < n; i4++) {
            r[i4] = new float[n];
        }
        int j = 0;
        while (j < n) {
            float[] w = q[j];
            ArraysKt.copyInto(a[j], w, 0, 0, sampleCount);
            for (int i5 = 0; i5 < j; i5++) {
                float[] z = q[i5];
                float dot = dot(w, z);
                for (int h2 = 0; h2 < sampleCount; h2++) {
                    w[h2] = w[h2] - (z[h2] * dot);
                }
            }
            float $this$fastCoerceAtLeast$iv = (float) Math.sqrt(dot(w, w));
            float minimumValue$iv = $this$fastCoerceAtLeast$iv >= 1.0E-6f ? $this$fastCoerceAtLeast$iv : 1.0E-6f;
            float inverseNorm = 1.0f / minimumValue$iv;
            for (int h3 = 0; h3 < sampleCount; h3++) {
                w[h3] = w[h3] * inverseNorm;
            }
            float[] v = r[j];
            int i6 = 0;
            while (i6 < n) {
                v[i6] = i6 < j ? 0.0f : dot(w, a[i6]);
                i6++;
            }
            j++;
        }
        for (int i7 = n - 1; -1 < i7; i7--) {
            float c = dot(q[i7], y);
            float[] ri = r[i7];
            int j2 = n - 1;
            int i8 = i7 + 1;
            if (i8 <= j2) {
                while (true) {
                    c -= ri[j2] * coefficients[j2];
                    if (j2 != i8) {
                        j2--;
                    }
                }
            }
            coefficients[i7] = c / ri[i7];
        }
        return coefficients;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateImpulseVelocity(float[] dataPoints, float[] time, int sampleCount, boolean isDataDifferential) {
        float work = 0.0f;
        int start = sampleCount - 1;
        float nextTime = time[start];
        for (int i = start; i > 0; i--) {
            float currentTime = nextTime;
            nextTime = time[i - 1];
            if (!(currentTime == nextTime)) {
                float dataPointsDelta = isDataDifferential ? -dataPoints[i - 1] : dataPoints[i] - dataPoints[i - 1];
                float vCurr = dataPointsDelta / (currentTime - nextTime);
                float vPrev = Math.signum(work) * ((float) Math.sqrt(2.0f * Math.abs(work)));
                work += (vCurr - vPrev) * Math.abs(vCurr);
                if (i == start) {
                    work *= 0.5f;
                }
            }
        }
        return Math.signum(work) * ((float) Math.sqrt(2.0f * Math.abs(work)));
    }

    private static final float kineticEnergyToVelocity(float kineticEnergy) {
        return Math.signum(kineticEnergy) * ((float) Math.sqrt(2.0f * Math.abs(kineticEnergy)));
    }

    private static final float dot(float[] $this$dot, float[] a) {
        float result = 0.0f;
        int length = $this$dot.length;
        for (int i = 0; i < length; i++) {
            result += $this$dot[i] * a[i];
        }
        return result;
    }

    private static final float norm(float[] $this$norm) {
        return (float) Math.sqrt(dot($this$norm, $this$norm));
    }

    private static final float[][] Matrix(int rows, int cols) {
        float[][] fArr = new float[rows][];
        for (int i = 0; i < rows; i++) {
            fArr[i] = new float[cols];
        }
        return fArr;
    }

    private static final float get(float[][] $this$get, int row, int col) {
        return $this$get[row][col];
    }

    private static final void set(float[][] $this$set, int row, int col, float value) {
        $this$set[row][col] = value;
    }

    public static final boolean getVelocityTrackerAddPointsFix() {
        return VelocityTrackerAddPointsFix;
    }

    public static final void setVelocityTrackerAddPointsFix(boolean z) {
        VelocityTrackerAddPointsFix = z;
    }

    public static final boolean getVelocityTrackerStrategyUseImpulse() {
        return VelocityTrackerStrategyUseImpulse;
    }

    public static final void setVelocityTrackerStrategyUseImpulse(boolean z) {
        VelocityTrackerStrategyUseImpulse = z;
    }
}
