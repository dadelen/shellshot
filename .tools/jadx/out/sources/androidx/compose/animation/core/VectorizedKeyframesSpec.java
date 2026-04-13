package androidx.compose.animation.core;

import androidx.collection.IntList;
import androidx.collection.IntObjectMap;
import androidx.compose.animation.core.AnimationVector;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorizedAnimationSpec.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B9\b\u0016\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u00070\u0005\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bBC\b\u0000\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000e\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010&\u001a\u00020\u0006H\u0002J \u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010+\u001a\u00020,H\u0002J-\u0010-\u001a\u00028\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00028\u00002\u0006\u00101\u001a\u00028\u00002\u0006\u00102\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00103J-\u00104\u001a\u00028\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00028\u00002\u0006\u00101\u001a\u00028\u00002\u0006\u00102\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00103J%\u00105\u001a\u0002062\u0006\u00100\u001a\u00028\u00002\u0006\u00101\u001a\u00028\u00002\u0006\u00102\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00107R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\t\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0016\u0010\u0011\u001a\u00020\u0012X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0019R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010$\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00068"}, d2 = {"Landroidx/compose/animation/core/VectorizedKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "keyframes", "", "", "Lkotlin/Pair;", "Landroidx/compose/animation/core/Easing;", "durationMillis", "delayMillis", "(Ljava/util/Map;II)V", "timestamps", "Landroidx/collection/IntList;", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/animation/core/VectorizedKeyframeSpecElementInfo;", "defaultEasing", "initialArcMode", "Landroidx/compose/animation/core/ArcMode;", "(Landroidx/collection/IntList;Landroidx/collection/IntObjectMap;IILandroidx/compose/animation/core/Easing;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "arcSpline", "Landroidx/compose/animation/core/ArcSpline;", "getDelayMillis", "()I", "getDurationMillis", "I", "lastInitialValue", "Landroidx/compose/animation/core/AnimationVector;", "lastTargetValue", "modes", "", "posArray", "", "slopeArray", "times", "valueVector", "velocityVector", "findEntryForTimeMillis", "timeMillis", "getEasedTime", "", "getEasedTimeFromIndex", "index", "asFraction", "", "getValueFromNanos", "playTimeNanos", "", "initialValue", "targetValue", "initialVelocity", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "init", "", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class VectorizedKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private ArcSpline arcSpline;
    private final Easing defaultEasing;
    private final int delayMillis;
    private final int durationMillis;
    private final int initialArcMode;
    private final IntObjectMap<VectorizedKeyframeSpecElementInfo<V>> keyframes;
    private V lastInitialValue;
    private V lastTargetValue;
    private int[] modes;
    private float[] posArray;
    private float[] slopeArray;
    private float[] times;
    private final IntList timestamps;
    private V valueVector;
    private V velocityVector;

    public /* synthetic */ VectorizedKeyframesSpec(IntList intList, IntObjectMap intObjectMap, int i, int i2, Easing easing, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(intList, intObjectMap, i, i2, easing, i3);
    }

    private VectorizedKeyframesSpec(IntList timestamps, IntObjectMap<VectorizedKeyframeSpecElementInfo<V>> intObjectMap, int durationMillis, int delayMillis, Easing defaultEasing, int initialArcMode) {
        this.timestamps = timestamps;
        this.keyframes = intObjectMap;
        this.durationMillis = durationMillis;
        this.delayMillis = delayMillis;
        this.defaultEasing = defaultEasing;
        this.initialArcMode = initialArcMode;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    public /* synthetic */ VectorizedKeyframesSpec(Map map, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, i, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public VectorizedKeyframesSpec(java.util.Map<java.lang.Integer, ? extends kotlin.Pair<? extends V, ? extends androidx.compose.animation.core.Easing>> r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r18
            r1 = 0
            androidx.collection.MutableIntList r2 = new androidx.collection.MutableIntList
            int r3 = r0.size()
            int r3 = r3 + 2
            r2.<init>(r3)
            r3 = r18
            r4 = 0
            java.util.Set r5 = r3.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L19:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L36
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            r7 = 0
            java.lang.Object r8 = r6.getKey()
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r2.add(r8)
            goto L19
        L36:
            r3 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            boolean r4 = r0.containsKey(r4)
            if (r4 != 0) goto L45
            r2.add(r3, r3)
        L45:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            boolean r4 = r0.containsKey(r4)
            if (r4 != 0) goto L55
            r8 = r19
            r2.add(r8)
            goto L57
        L55:
            r8 = r19
        L57:
            r2.sort()
            r6 = r2
            androidx.collection.IntList r6 = (androidx.collection.IntList) r6
            r1 = 0
            androidx.collection.MutableIntObjectMap r2 = new androidx.collection.MutableIntObjectMap
            r4 = 1
            r5 = 0
            r2.<init>(r3, r4, r5)
            r3 = r18
            r4 = 0
            java.util.Set r7 = r3.entrySet()
            java.util.Iterator r7 = r7.iterator()
        L71:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto Lac
            java.lang.Object r9 = r7.next()
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9
            r10 = 0
            java.lang.Object r11 = r9.getKey()
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            java.lang.Object r12 = r9.getValue()
            kotlin.Pair r12 = (kotlin.Pair) r12
            androidx.compose.animation.core.VectorizedKeyframeSpecElementInfo r13 = new androidx.compose.animation.core.VectorizedKeyframeSpecElementInfo
            java.lang.Object r14 = r12.getFirst()
            androidx.compose.animation.core.AnimationVector r14 = (androidx.compose.animation.core.AnimationVector) r14
            java.lang.Object r15 = r12.getSecond()
            androidx.compose.animation.core.Easing r15 = (androidx.compose.animation.core.Easing) r15
            androidx.compose.animation.core.ArcMode$Companion r16 = androidx.compose.animation.core.ArcMode.INSTANCE
            int r0 = r16.m144getArcLinear9TMq4()
            r13.<init>(r14, r15, r0, r5)
            r2.set(r11, r13)
            r0 = r18
            goto L71
        Lac:
            r7 = r2
            androidx.collection.IntObjectMap r7 = (androidx.collection.IntObjectMap) r7
            androidx.compose.animation.core.Easing r10 = androidx.compose.animation.core.EasingKt.getLinearEasing()
            androidx.compose.animation.core.ArcMode$Companion r0 = androidx.compose.animation.core.ArcMode.INSTANCE
            int r11 = r0.m144getArcLinear9TMq4()
            r12 = 0
            r5 = r17
            r9 = r20
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.VectorizedKeyframesSpec.<init>(java.util.Map, int, int):void");
    }

    private final void init(V initialValue, V targetValue, V initialVelocity) {
        float[] fArr;
        boolean z = this.arcSpline != null;
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialVelocity);
            int size = this.timestamps.getSize();
            float[] fArr2 = new float[size];
            for (int i = 0; i < size; i++) {
                fArr2[i] = this.timestamps.get(i) / 1000.0f;
            }
            this.times = fArr2;
            int size2 = this.timestamps.getSize();
            int[] iArr = new int[size2];
            for (int i2 = 0; i2 < size2; i2++) {
                VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = this.keyframes.get(this.timestamps.get(i2));
                int m197getArcMode9TMq4 = vectorizedKeyframeSpecElementInfo != null ? vectorizedKeyframeSpecElementInfo.m197getArcMode9TMq4() : this.initialArcMode;
                if (!ArcMode.m138equalsimpl0(m197getArcMode9TMq4, ArcMode.INSTANCE.m144getArcLinear9TMq4())) {
                    z = true;
                }
                iArr[i2] = m197getArcMode9TMq4;
            }
            this.modes = iArr;
        }
        if (!z) {
            return;
        }
        float[] fArr3 = null;
        if (this.arcSpline != null) {
            V v = this.lastInitialValue;
            if (v == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lastInitialValue");
                v = null;
            }
            if (Intrinsics.areEqual(v, initialValue)) {
                V v2 = this.lastTargetValue;
                if (v2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lastTargetValue");
                    v2 = null;
                }
                if (Intrinsics.areEqual(v2, targetValue)) {
                    return;
                }
            }
        }
        this.lastInitialValue = initialValue;
        this.lastTargetValue = targetValue;
        int size3 = (initialValue.getSize() % 2) + initialValue.getSize();
        this.posArray = new float[size3];
        this.slopeArray = new float[size3];
        int size4 = this.timestamps.getSize();
        float[][] fArr4 = new float[size4][];
        for (int i3 = 0; i3 < size4; i3++) {
            int i4 = this.timestamps.get(i3);
            if (i4 == 0) {
                if (!this.keyframes.contains(i4)) {
                    fArr = new float[size3];
                    for (int i5 = 0; i5 < size3; i5++) {
                        fArr[i5] = initialValue.get$animation_core_release(i5);
                    }
                } else {
                    fArr = new float[size3];
                    VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo2 = this.keyframes.get(i4);
                    Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo2);
                    V vectorValue = vectorizedKeyframeSpecElementInfo2.getVectorValue();
                    for (int i6 = 0; i6 < size3; i6++) {
                        fArr[i6] = vectorValue.get$animation_core_release(i6);
                    }
                }
            } else if (i4 == getDurationMillis()) {
                if (!this.keyframes.contains(i4)) {
                    fArr = new float[size3];
                    for (int i7 = 0; i7 < size3; i7++) {
                        fArr[i7] = targetValue.get$animation_core_release(i7);
                    }
                } else {
                    fArr = new float[size3];
                    VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo3 = this.keyframes.get(i4);
                    Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo3);
                    V vectorValue2 = vectorizedKeyframeSpecElementInfo3.getVectorValue();
                    for (int i8 = 0; i8 < size3; i8++) {
                        fArr[i8] = vectorValue2.get$animation_core_release(i8);
                    }
                }
            } else {
                fArr = new float[size3];
                VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo4 = this.keyframes.get(i4);
                Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo4);
                V vectorValue3 = vectorizedKeyframeSpecElementInfo4.getVectorValue();
                for (int i9 = 0; i9 < size3; i9++) {
                    fArr[i9] = vectorValue3.get$animation_core_release(i9);
                }
            }
            fArr4[i3] = fArr;
        }
        int[] iArr2 = this.modes;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("modes");
            iArr2 = null;
        }
        float[] fArr5 = this.times;
        if (fArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("times");
        } else {
            fArr3 = fArr5;
        }
        this.arcSpline = new ArcSpline(iArr2, fArr3, fArr4);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        AnimationVector startValue;
        AnimationVector endValue;
        V v;
        V v2;
        VectorizedKeyframesSpec<V> vectorizedKeyframesSpec = this;
        long playTimeMillis = playTimeNanos / AnimationKt.MillisToNanos;
        int clampedPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(vectorizedKeyframesSpec, playTimeMillis);
        if (vectorizedKeyframesSpec.keyframes.contains(clampedPlayTime)) {
            VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = vectorizedKeyframesSpec.keyframes.get(clampedPlayTime);
            Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo);
            return vectorizedKeyframeSpecElementInfo.getVectorValue();
        }
        if (clampedPlayTime >= vectorizedKeyframesSpec.getDurationMillis()) {
            return targetValue;
        }
        if (clampedPlayTime <= 0) {
            return initialValue;
        }
        vectorizedKeyframesSpec.init(initialValue, targetValue, initialVelocity);
        if (vectorizedKeyframesSpec.arcSpline != null) {
            float easedTime = vectorizedKeyframesSpec.getEasedTime(clampedPlayTime);
            ArcSpline arcSpline = vectorizedKeyframesSpec.arcSpline;
            if (arcSpline == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arcSpline");
                arcSpline = null;
            }
            float[] fArr = vectorizedKeyframesSpec.posArray;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("posArray");
                fArr = null;
            }
            arcSpline.getPos(easedTime, fArr);
            int i = 0;
            float[] fArr2 = vectorizedKeyframesSpec.posArray;
            if (fArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("posArray");
                fArr2 = null;
            }
            int length = fArr2.length;
            while (true) {
                v2 = vectorizedKeyframesSpec.valueVector;
                if (i >= length) {
                    break;
                }
                if (v2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                    v2 = null;
                }
                float[] fArr3 = vectorizedKeyframesSpec.posArray;
                if (fArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("posArray");
                    fArr3 = null;
                }
                v2.set$animation_core_release(i, fArr3[i]);
                i++;
            }
            if (v2 != null) {
                return v2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            return null;
        }
        int index = vectorizedKeyframesSpec.findEntryForTimeMillis(clampedPlayTime);
        float easedTime2 = vectorizedKeyframesSpec.getEasedTimeFromIndex(index, clampedPlayTime, true);
        int timestampStart = vectorizedKeyframesSpec.timestamps.get(index);
        if (vectorizedKeyframesSpec.keyframes.contains(timestampStart)) {
            VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo2 = vectorizedKeyframesSpec.keyframes.get(timestampStart);
            Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo2);
            startValue = vectorizedKeyframeSpecElementInfo2.getVectorValue();
        } else {
            startValue = initialValue;
        }
        int timestampEnd = vectorizedKeyframesSpec.timestamps.get(index + 1);
        if (vectorizedKeyframesSpec.keyframes.contains(timestampEnd)) {
            VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo3 = vectorizedKeyframesSpec.keyframes.get(timestampEnd);
            Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo3);
            endValue = vectorizedKeyframeSpecElementInfo3.getVectorValue();
        } else {
            endValue = targetValue;
        }
        int i2 = 0;
        V v3 = vectorizedKeyframesSpec.valueVector;
        if (v3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            v3 = null;
        }
        int size = v3.getSize();
        while (true) {
            v = vectorizedKeyframesSpec.valueVector;
            if (i2 >= size) {
                break;
            }
            if (v == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                v = null;
            }
            v.set$animation_core_release(i2, VectorConvertersKt.lerp(startValue.get$animation_core_release(i2), endValue.get$animation_core_release(i2), easedTime2));
            i2++;
            vectorizedKeyframesSpec = this;
        }
        if (v != null) {
            return v;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        return null;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        V v;
        V v2;
        long playTimeMillis = playTimeNanos / AnimationKt.MillisToNanos;
        long clampedPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, playTimeMillis);
        if (clampedPlayTime < 0) {
            return initialVelocity;
        }
        init(initialValue, targetValue, initialVelocity);
        if (this.arcSpline != null) {
            float easedTime = getEasedTime((int) clampedPlayTime);
            ArcSpline arcSpline = this.arcSpline;
            if (arcSpline == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arcSpline");
                arcSpline = null;
            }
            float[] fArr = this.slopeArray;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                fArr = null;
            }
            arcSpline.getSlope(easedTime, fArr);
            int i = 0;
            float[] fArr2 = this.slopeArray;
            if (fArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                fArr2 = null;
            }
            int length = fArr2.length;
            while (true) {
                v2 = this.velocityVector;
                if (i >= length) {
                    break;
                }
                if (v2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                    v2 = null;
                }
                float[] fArr3 = this.slopeArray;
                if (fArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                    fArr3 = null;
                }
                v2.set$animation_core_release(i, fArr3[i]);
                i++;
            }
            if (v2 != null) {
                return v2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            return null;
        }
        AnimationVector startNum = VectorizedAnimationSpecKt.getValueFromMillis(this, clampedPlayTime - 1, initialValue, targetValue, initialVelocity);
        AnimationVector endNum = VectorizedAnimationSpecKt.getValueFromMillis(this, clampedPlayTime, initialValue, targetValue, initialVelocity);
        int i2 = 0;
        int size = startNum.getSize();
        while (true) {
            v = this.velocityVector;
            if (i2 >= size) {
                break;
            }
            if (v == null) {
                Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                v = null;
            }
            v.set$animation_core_release(i2, (startNum.get$animation_core_release(i2) - endNum.get$animation_core_release(i2)) * 1000.0f);
            i2++;
        }
        if (v != null) {
            return v;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        return null;
    }

    private final float getEasedTime(int timeMillis) {
        int index = findEntryForTimeMillis(timeMillis);
        return getEasedTimeFromIndex(index, timeMillis, false);
    }

    private final float getEasedTimeFromIndex(int index, int timeMillis, boolean asFraction) {
        Easing easing;
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
        VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = this.keyframes.get(timeMin);
        if (vectorizedKeyframeSpecElementInfo == null || (easing = vectorizedKeyframeSpecElementInfo.getEasing()) == null) {
            easing = this.defaultEasing;
        }
        float rawFraction = (timeMillis - timeMin) / timeRange;
        float easedFraction = easing.transform(rawFraction);
        if (!asFraction) {
            return ((timeRange * easedFraction) + timeMin) / 1000.0f;
        }
        return easedFraction;
    }

    private final int findEntryForTimeMillis(int timeMillis) {
        int index = IntListExtensionKt.binarySearch$default(this.timestamps, timeMillis, 0, 0, 6, null);
        return index < -1 ? -(index + 2) : index;
    }
}
