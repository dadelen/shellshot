package androidx.compose.animation.core;

import androidx.collection.IntList;
import androidx.collection.IntObjectMap;
import androidx.compose.animation.core.AnimationVector;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorizedMonoSplineKeyframesSpec.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bH\u0002J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bH\u0002J\u0010\u0010#\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u000bH\u0002J-\u0010$\u001a\u00028\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J-\u0010+\u001a\u00028\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J%\u0010,\u001a\u00020-2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010.R\u0014\u0010\f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0012\u0010\u0015\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u001cX\u0082.¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006/"}, d2 = {"Landroidx/compose/animation/core/VectorizedMonoSplineKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "timestamps", "Landroidx/collection/IntList;", "keyframes", "Landroidx/collection/IntObjectMap;", "Lkotlin/Pair;", "Landroidx/compose/animation/core/Easing;", "durationMillis", "", "delayMillis", "periodicBias", "", "(Landroidx/collection/IntList;Landroidx/collection/IntObjectMap;IIF)V", "getDelayMillis", "()I", "getDurationMillis", "lastInitialValue", "Landroidx/compose/animation/core/AnimationVector;", "lastTargetValue", "monoSpline", "Landroidx/compose/animation/core/MonoSpline;", "times", "", "valueVector", "values", "", "[[F", "velocityVector", "findEntryForTimeMillis", "timeMillis", "getEasedTimeFromIndex", "index", "getEasing", "getValueFromNanos", "playTimeNanos", "", "initialValue", "targetValue", "initialVelocity", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "init", "", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class VectorizedMonoSplineKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private final int delayMillis;
    private final int durationMillis;
    private final IntObjectMap<Pair<V, Easing>> keyframes;
    private V lastInitialValue;
    private V lastTargetValue;
    private MonoSpline monoSpline;
    private final float periodicBias;
    private float[] times;
    private final IntList timestamps;
    private V valueVector;
    private float[][] values;
    private V velocityVector;

    public VectorizedMonoSplineKeyframesSpec(IntList timestamps, IntObjectMap<Pair<V, Easing>> intObjectMap, int durationMillis, int delayMillis, float periodicBias) {
        this.timestamps = timestamps;
        this.keyframes = intObjectMap;
        this.durationMillis = durationMillis;
        this.delayMillis = delayMillis;
        this.periodicBias = periodicBias;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    private final void init(V initialValue, V targetValue, V initialVelocity) {
        float[] fArr;
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialVelocity);
            int size = this.timestamps.getSize();
            float[] fArr2 = new float[size];
            for (int i = 0; i < size; i++) {
                fArr2[i] = this.timestamps.get(i) / 1000.0f;
            }
            this.times = fArr2;
        }
        if (this.monoSpline == null || !Intrinsics.areEqual(this.lastInitialValue, initialValue) || !Intrinsics.areEqual(this.lastTargetValue, targetValue)) {
            boolean z = !Intrinsics.areEqual(this.lastInitialValue, initialValue);
            boolean z2 = !Intrinsics.areEqual(this.lastTargetValue, targetValue);
            this.lastInitialValue = initialValue;
            this.lastTargetValue = targetValue;
            int size2 = initialValue.getSize();
            float[][] fArr3 = null;
            if (this.values == null) {
                int size3 = this.timestamps.getSize();
                float[][] fArr4 = new float[size3][];
                for (int i2 = 0; i2 < size3; i2++) {
                    int i3 = this.timestamps.get(i2);
                    if (i3 == 0) {
                        if (!this.keyframes.contains(i3)) {
                            fArr = new float[size2];
                            for (int i4 = 0; i4 < size2; i4++) {
                                fArr[i4] = initialValue.get$animation_core_release(i4);
                            }
                        } else {
                            fArr = new float[size2];
                            Pair<V, Easing> pair = this.keyframes.get(i3);
                            Intrinsics.checkNotNull(pair);
                            V first = pair.getFirst();
                            for (int i5 = 0; i5 < size2; i5++) {
                                fArr[i5] = first.get$animation_core_release(i5);
                            }
                        }
                    } else if (i3 == getDurationMillis()) {
                        if (!this.keyframes.contains(i3)) {
                            fArr = new float[size2];
                            for (int i6 = 0; i6 < size2; i6++) {
                                fArr[i6] = targetValue.get$animation_core_release(i6);
                            }
                        } else {
                            fArr = new float[size2];
                            Pair<V, Easing> pair2 = this.keyframes.get(i3);
                            Intrinsics.checkNotNull(pair2);
                            V first2 = pair2.getFirst();
                            for (int i7 = 0; i7 < size2; i7++) {
                                fArr[i7] = first2.get$animation_core_release(i7);
                            }
                        }
                    } else {
                        fArr = new float[size2];
                        Pair<V, Easing> pair3 = this.keyframes.get(i3);
                        Intrinsics.checkNotNull(pair3);
                        V first3 = pair3.getFirst();
                        for (int i8 = 0; i8 < size2; i8++) {
                            fArr[i8] = first3.get$animation_core_release(i8);
                        }
                    }
                    fArr4[i2] = fArr;
                }
                this.values = fArr4;
            } else {
                if (z && !this.keyframes.contains(0)) {
                    float[][] fArr5 = this.values;
                    if (fArr5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("values");
                        fArr5 = null;
                    }
                    int binarySearch$default = IntListExtensionKt.binarySearch$default(this.timestamps, 0, 0, 0, 6, null);
                    float[] fArr6 = new float[size2];
                    for (int i9 = 0; i9 < size2; i9++) {
                        fArr6[i9] = initialValue.get$animation_core_release(i9);
                    }
                    fArr5[binarySearch$default] = fArr6;
                }
                if (z2 && !this.keyframes.contains(getDurationMillis())) {
                    float[][] fArr7 = this.values;
                    if (fArr7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("values");
                        fArr7 = null;
                    }
                    int binarySearch$default2 = IntListExtensionKt.binarySearch$default(this.timestamps, getDurationMillis(), 0, 0, 6, null);
                    float[] fArr8 = new float[size2];
                    for (int i10 = 0; i10 < size2; i10++) {
                        fArr8[i10] = targetValue.get$animation_core_release(i10);
                    }
                    fArr7[binarySearch$default2] = fArr8;
                }
            }
            float[] fArr9 = this.times;
            if (fArr9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("times");
                fArr9 = null;
            }
            float[][] fArr10 = this.values;
            if (fArr10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("values");
            } else {
                fArr3 = fArr10;
            }
            this.monoSpline = new MonoSpline(fArr9, fArr3, this.periodicBias);
        }
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        long playTimeMillis = playTimeNanos / AnimationKt.MillisToNanos;
        int clampedPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeMillis);
        if (this.keyframes.containsKey(clampedPlayTime)) {
            Pair<V, Easing> pair = this.keyframes.get(clampedPlayTime);
            Intrinsics.checkNotNull(pair);
            return pair.getFirst();
        }
        if (clampedPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (clampedPlayTime <= 0) {
            return initialValue;
        }
        init(initialValue, targetValue, initialVelocity);
        int index = findEntryForTimeMillis(clampedPlayTime);
        MonoSpline monoSpline = this.monoSpline;
        if (monoSpline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("monoSpline");
            monoSpline = null;
        }
        float easedTimeFromIndex = getEasedTimeFromIndex(index, clampedPlayTime);
        V v = this.valueVector;
        if (v == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            v = null;
        }
        monoSpline.getPos(easedTimeFromIndex, v, index);
        V v2 = this.valueVector;
        if (v2 != null) {
            return v2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        return null;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        long playTimeMillis = playTimeNanos / AnimationKt.MillisToNanos;
        int clampedPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeMillis);
        if (clampedPlayTime < 0) {
            return initialVelocity;
        }
        init(initialValue, targetValue, initialVelocity);
        int index = findEntryForTimeMillis(clampedPlayTime);
        MonoSpline monoSpline = this.monoSpline;
        if (monoSpline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("monoSpline");
            monoSpline = null;
        }
        float easedTimeFromIndex = getEasedTimeFromIndex(index, clampedPlayTime);
        V v = this.velocityVector;
        if (v == null) {
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            v = null;
        }
        monoSpline.getSlope(easedTimeFromIndex, v, index);
        V v2 = this.velocityVector;
        if (v2 != null) {
            return v2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        return null;
    }

    private final Easing getEasing(int index) {
        Easing second;
        int timestamp = this.timestamps.get(index);
        Pair<V, Easing> pair = this.keyframes.get(timestamp);
        return (pair == null || (second = pair.getSecond()) == null) ? EasingKt.getLinearEasing() : second;
    }

    private final float getEasedTimeFromIndex(int index, int timeMillis) {
        IntList this_$iv = this.timestamps;
        if (index >= this_$iv._size - 1) {
            return timeMillis / 1000.0f;
        }
        int timeMin = this.timestamps.get(index);
        int timeMax = this.timestamps.get(index + 1);
        if (timeMillis == timeMin) {
            return timeMin / 1000.0f;
        }
        int timeRange = timeMax - timeMin;
        Easing easing = getEasing(index);
        float rawFraction = (timeMillis - timeMin) / timeRange;
        float easedFraction = easing.transform(rawFraction);
        return ((timeRange * easedFraction) + timeMin) / 1000.0f;
    }

    private final int findEntryForTimeMillis(int timeMillis) {
        int index = IntListExtensionKt.binarySearch$default(this.timestamps, timeMillis, 0, 0, 6, null);
        return index < -1 ? -(index + 2) : index;
    }
}
