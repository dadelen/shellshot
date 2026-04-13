package androidx.compose.animation.core;

import kotlin.Metadata;

/* compiled from: MonoSpline.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001cJ \u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0003J\u0018\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J8\u0010 \u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J#\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001cH\u0002¢\u0006\u0002\u0010$R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006%"}, d2 = {"Landroidx/compose/animation/core/MonoSpline;", "", "time", "", "y", "", "periodicBias", "", "([F[[FF)V", "isExtrapolate", "", "slopeTemp", "tangents", "[[F", "timePoints", "values", "diff", "h", "x", "y1", "y2", "t1", "t2", "getPos", "", "v", "Landroidx/compose/animation/core/AnimationVector;", "index", "", "t", "j", "getSlope", "interpolate", "makeFloatArray", "a", "b", "(II)[[F", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MonoSpline {
    public static final int $stable = 8;
    private final boolean isExtrapolate = true;
    private final float[] slopeTemp;
    private final float[][] tangents;
    private final float[] timePoints;
    private final float[][] values;

    public MonoSpline(float[] time, float[][] y, float periodicBias) {
        int n;
        boolean z = true;
        int n2 = time.length;
        int dim = y[0].length;
        this.slopeTemp = new float[dim];
        float[][] slope = makeFloatArray(n2 - 1, dim);
        float[][] tangent = makeFloatArray(n2, dim);
        for (int j = 0; j < dim; j++) {
            int i = n2 - 1;
            for (int i2 = 0; i2 < i; i2++) {
                float dt = time[i2 + 1] - time[i2];
                slope[i2][j] = (y[i2 + 1][j] - y[i2][j]) / dt;
                if (i2 == 0) {
                    tangent[i2][j] = slope[i2][j];
                } else {
                    tangent[i2][j] = (slope[i2 - 1][j] + slope[i2][j]) * 0.5f;
                }
            }
            int i3 = n2 - 1;
            tangent[i3][j] = slope[n2 - 2][j];
        }
        if (!Float.isNaN(periodicBias)) {
            for (int j2 = 0; j2 < dim; j2++) {
                float adjustedSlope = (slope[n2 - 2][j2] * (1.0f - periodicBias)) + (slope[0][j2] * periodicBias);
                slope[0][j2] = adjustedSlope;
                slope[n2 - 2][j2] = adjustedSlope;
                tangent[n2 - 1][j2] = adjustedSlope;
                tangent[0][j2] = adjustedSlope;
            }
        }
        int i4 = 0;
        int i5 = n2 - 1;
        while (i4 < i5) {
            int j3 = 0;
            while (j3 < dim) {
                if (slope[i4][j3] == 0.0f ? z : false) {
                    tangent[i4][j3] = 0.0f;
                    tangent[i4 + 1][j3] = 0.0f;
                    n = n2;
                } else {
                    float a = tangent[i4][j3] / slope[i4][j3];
                    float b = tangent[i4 + 1][j3] / slope[i4][j3];
                    n = n2;
                    float h = (float) Math.hypot(a, b);
                    if (h > 9.0d) {
                        float t = 3.0f / h;
                        tangent[i4][j3] = t * a * slope[i4][j3];
                        tangent[i4 + 1][j3] = t * b * slope[i4][j3];
                    }
                }
                j3++;
                n2 = n;
                z = true;
            }
            i4++;
            z = true;
        }
        this.timePoints = time;
        this.values = y;
        this.tangents = tangent;
    }

    private final float[][] makeFloatArray(int a, int b) {
        float[][] fArr = new float[a][];
        for (int i = 0; i < a; i++) {
            fArr[i] = new float[b];
        }
        return fArr;
    }

    public final float getPos(float t, int j) {
        int n = this.timePoints.length;
        boolean z = this.isExtrapolate;
        float[] fArr = this.timePoints;
        if (z) {
            if (t <= fArr[0]) {
                return this.values[0][j] + ((t - this.timePoints[0]) * getSlope(this.timePoints[0], j));
            }
            if (t >= this.timePoints[n - 1]) {
                return this.values[n - 1][j] + ((t - this.timePoints[n - 1]) * getSlope(this.timePoints[n - 1], j));
            }
        } else {
            if (t <= fArr[0]) {
                return this.values[0][j];
            }
            if (t >= this.timePoints[n - 1]) {
                return this.values[n - 1][j];
            }
        }
        int i = n - 1;
        for (int i2 = 0; i2 < i; i2++) {
            if (t == this.timePoints[i2]) {
                return this.values[i2][j];
            }
            if (t < this.timePoints[i2 + 1]) {
                float h = this.timePoints[i2 + 1] - this.timePoints[i2];
                float x = (t - this.timePoints[i2]) / h;
                float y1 = this.values[i2][j];
                float y2 = this.values[i2 + 1][j];
                float t1 = this.tangents[i2][j];
                float t2 = this.tangents[i2 + 1][j];
                return interpolate(h, x, y1, y2, t1, t2);
            }
        }
        return 0.0f;
    }

    public static /* synthetic */ void getPos$default(MonoSpline monoSpline, float f, AnimationVector animationVector, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        monoSpline.getPos(f, animationVector, i);
    }

    public final void getPos(float time, AnimationVector v, int index) {
        int n = this.timePoints.length;
        int dim = this.values[0].length;
        boolean z = this.isExtrapolate;
        float[] fArr = this.timePoints;
        if (z) {
            float f = fArr[0];
            float[] fArr2 = this.timePoints;
            if (time <= f) {
                getSlope(fArr2[0], this.slopeTemp);
                for (int j = 0; j < dim; j++) {
                    v.set$animation_core_release(j, this.values[0][j] + ((time - this.timePoints[0]) * this.slopeTemp[j]));
                }
                return;
            }
            if (time >= fArr2[n - 1]) {
                getSlope(this.timePoints[n - 1], this.slopeTemp);
                for (int j2 = 0; j2 < dim; j2++) {
                    v.set$animation_core_release(j2, this.values[n - 1][j2] + ((time - this.timePoints[n - 1]) * this.slopeTemp[j2]));
                }
                return;
            }
        } else {
            if (time <= fArr[0]) {
                for (int j3 = 0; j3 < dim; j3++) {
                    v.set$animation_core_release(j3, this.values[0][j3]);
                }
                return;
            }
            if (time >= this.timePoints[n - 1]) {
                for (int j4 = 0; j4 < dim; j4++) {
                    v.set$animation_core_release(j4, this.values[n - 1][j4]);
                }
                return;
            }
        }
        int i = n - 1;
        for (int i2 = index; i2 < i; i2++) {
            if (time == this.timePoints[i2]) {
                for (int j5 = 0; j5 < dim; j5++) {
                    v.set$animation_core_release(j5, this.values[i2][j5]);
                }
            }
            if (time < this.timePoints[i2 + 1]) {
                float h = this.timePoints[i2 + 1] - this.timePoints[i2];
                float x = (time - this.timePoints[i2]) / h;
                for (int j6 = 0; j6 < dim; j6++) {
                    float y1 = this.values[i2][j6];
                    float y2 = this.values[i2 + 1][j6];
                    float t1 = this.tangents[i2][j6];
                    float t2 = this.tangents[i2 + 1][j6];
                    v.set$animation_core_release(j6, interpolate(h, x, y1, y2, t1, t2));
                }
                return;
            }
        }
    }

    public final void getSlope(float time, float[] v) {
        float t = time;
        int n = this.timePoints.length;
        int dim = this.values[0].length;
        float f = this.timePoints[0];
        float[] fArr = this.timePoints;
        if (t <= f) {
            t = fArr[0];
        } else if (t >= fArr[n - 1]) {
            t = this.timePoints[n - 1];
        }
        int i = n - 1;
        for (int i2 = 0; i2 < i; i2++) {
            if (t <= this.timePoints[i2 + 1]) {
                float h = this.timePoints[i2 + 1] - this.timePoints[i2];
                float x = (t - this.timePoints[i2]) / h;
                for (int j = 0; j < dim; j++) {
                    float y1 = this.values[i2][j];
                    float y2 = this.values[i2 + 1][j];
                    float t1 = this.tangents[i2][j];
                    float t2 = this.tangents[i2 + 1][j];
                    v[j] = diff(h, x, y1, y2, t1, t2) / h;
                }
                return;
            }
        }
    }

    public static /* synthetic */ void getSlope$default(MonoSpline monoSpline, float f, AnimationVector animationVector, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        monoSpline.getSlope(f, animationVector, i);
    }

    public final void getSlope(float time, AnimationVector v, int index) {
        int n = this.timePoints.length;
        int dim = this.values[0].length;
        if (time <= this.timePoints[0]) {
            for (int j = 0; j < dim; j++) {
                v.set$animation_core_release(j, this.tangents[0][j]);
            }
            return;
        }
        if (time >= this.timePoints[n - 1]) {
            for (int j2 = 0; j2 < dim; j2++) {
                v.set$animation_core_release(j2, this.tangents[n - 1][j2]);
            }
            return;
        }
        int i = n - 1;
        for (int i2 = index; i2 < i; i2++) {
            if (time <= this.timePoints[i2 + 1]) {
                float h = this.timePoints[i2 + 1] - this.timePoints[i2];
                float x = (time - this.timePoints[i2]) / h;
                for (int j3 = 0; j3 < dim; j3++) {
                    float y1 = this.values[i2][j3];
                    float y2 = this.values[i2 + 1][j3];
                    float t1 = this.tangents[i2][j3];
                    float t2 = this.tangents[i2 + 1][j3];
                    v.set$animation_core_release(j3, diff(h, x, y1, y2, t1, t2) / h);
                }
                return;
            }
        }
    }

    private final float getSlope(float time, int j) {
        float t = time;
        int n = this.timePoints.length;
        float f = this.timePoints[0];
        float[] fArr = this.timePoints;
        if (t < f) {
            t = fArr[0];
        } else if (t >= fArr[n - 1]) {
            t = this.timePoints[n - 1];
        }
        int i = n - 1;
        for (int i2 = 0; i2 < i; i2++) {
            if (t <= this.timePoints[i2 + 1]) {
                float h = this.timePoints[i2 + 1] - this.timePoints[i2];
                float x = (t - this.timePoints[i2]) / h;
                float y1 = this.values[i2][j];
                float y2 = this.values[i2 + 1][j];
                float t1 = this.tangents[i2][j];
                float t2 = this.tangents[i2 + 1][j];
                return diff(h, x, y1, y2, t1, t2) / h;
            }
        }
        return 0.0f;
    }

    private final float interpolate(float h, float x, float y1, float y2, float t1, float t2) {
        float x2 = x * x;
        float x3 = x2 * x;
        return (((((((((((-2.0f) * x3) * y2) + ((3.0f * x2) * y2)) + ((2.0f * x3) * y1)) - ((3.0f * x2) * y1)) + y1) + ((h * t2) * x3)) + ((h * t1) * x3)) - ((h * t2) * x2)) - (((2.0f * h) * t1) * x2)) + (h * t1 * x);
    }

    private final float diff(float h, float x, float y1, float y2, float t1, float t2) {
        float x2 = x * x;
        return ((((((((((-6.0f) * x2) * y2) + ((6.0f * x) * y2)) + ((6.0f * x2) * y1)) - ((6.0f * x) * y1)) + (((3.0f * h) * t2) * x2)) + (((3.0f * h) * t1) * x2)) - (((2.0f * h) * t2) * x)) - (((4.0f * h) * t1) * x)) + (h * t1);
    }
}
