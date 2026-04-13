package androidx.compose.animation.core;

import androidx.collection.IntObjectMap;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationSpec.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u000e\u000fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0001\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec;", "T", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "config", "Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "(Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;)V", "getConfig", "()Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "vectorize", "Landroidx/compose/animation/core/VectorizedKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "KeyframeEntity", "KeyframesSpecConfig", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeyframesSpec<T> implements DurationBasedAnimationSpec<T> {
    public static final int $stable = 0;
    private final KeyframesSpecConfig<T> config;

    public KeyframesSpec(KeyframesSpecConfig<T> keyframesSpecConfig) {
        this.config = keyframesSpecConfig;
    }

    public final KeyframesSpecConfig<T> getConfig() {
        return this.config;
    }

    /* compiled from: AnimationSpec.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003*\u00028\u00012\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0004¢\u0006\u0002\u0010\fJ\"\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003*\u00028\u00012\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0096\u0004¢\u0006\u0002\u0010\u0010J+\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003*\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "T", "Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "()V", "createEntityFor", "value", "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "at", "timeStamp", "", "(Ljava/lang/Object;I)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "atFraction", "fraction", "", "(Ljava/lang/Object;F)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "using", "arcMode", "Landroidx/compose/animation/core/ArcMode;", "using-ngzHuyU", "(Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;I)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "with", "", "easing", "Landroidx/compose/animation/core/Easing;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframesSpecConfig<T> extends KeyframesSpecBaseConfig<T, KeyframeEntity<T>> {
        public static final int $stable = 0;

        public KeyframesSpecConfig() {
            super(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public /* bridge */ /* synthetic */ KeyframeBaseEntity at(Object obj, int timeStamp) {
            return at((KeyframesSpecConfig<T>) obj, timeStamp);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public /* bridge */ /* synthetic */ KeyframeBaseEntity atFraction(Object obj, float fraction) {
            return atFraction((KeyframesSpecConfig<T>) obj, fraction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public /* bridge */ /* synthetic */ KeyframeBaseEntity createEntityFor$animation_core_release(Object obj) {
            return createEntityFor$animation_core_release((KeyframesSpecConfig<T>) obj);
        }

        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> createEntityFor$animation_core_release(T value) {
            return new KeyframeEntity<>(value, null, 0, 6, null);
        }

        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> at(T t, int timeStamp) {
            KeyframeEntity it = new KeyframeEntity(t, null, 0, 6, null);
            getKeyframes$animation_core_release().set(timeStamp, it);
            return it;
        }

        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> atFraction(T t, float fraction) {
            float $this$fastRoundToInt$iv = getDurationMillis() * fraction;
            return at((KeyframesSpecConfig<T>) t, Math.round($this$fastRoundToInt$iv));
        }

        @Deprecated(message = "Use version that returns an instance of the entity so it can be re-used in other keyframe builders.", replaceWith = @ReplaceWith(expression = "this using easing", imports = {}))
        public final void with(KeyframeEntity<T> keyframeEntity, Easing easing) {
            keyframeEntity.setEasing$animation_core_release(easing);
        }

        /* renamed from: using-ngzHuyU, reason: not valid java name */
        public final KeyframeEntity<T> m148usingngzHuyU(KeyframeEntity<T> keyframeEntity, int arcMode) {
            keyframeEntity.m147setArcModeRur9ykg$animation_core_release(arcMode);
            return keyframeEntity;
        }
    }

    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    public <V extends AnimationVector> VectorizedKeyframesSpec<V> vectorize(TwoWayConverter<T, V> converter) {
        IntObjectMap this_$iv;
        int $i$f$forEach;
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap this_$iv$iv;
        IntObjectMap this_$iv2;
        int $i$f$forEach2;
        int[] k$iv2;
        Object[] v$iv2;
        IntObjectMap this_$iv$iv2;
        int i;
        MutableIntList timestamps = new MutableIntList(this.config.getKeyframes$animation_core_release().get_size() + 2);
        MutableIntObjectMap timeToInfoMap = new MutableIntObjectMap(this.config.getKeyframes$animation_core_release().get_size());
        IntObjectMap this_$iv3 = this.config.getKeyframes$animation_core_release();
        int $i$f$forEach3 = 0;
        int[] k$iv3 = this_$iv3.keys;
        Object[] v$iv3 = this_$iv3.values;
        IntObjectMap this_$iv$iv3 = this_$iv3;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long slot$iv$iv2 = slot$iv$iv;
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv2 & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            int key = k$iv3[index$iv$iv];
                            KeyframeEntity value = (KeyframeEntity) v$iv3[index$iv$iv];
                            i = i2;
                            timestamps.add(key);
                            this_$iv2 = this_$iv3;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                            timeToInfoMap.set(key, new VectorizedKeyframeSpecElementInfo(converter.getConvertToVector().invoke(value.getValue$animation_core_release()), value.getEasing(), value.getArcMode(), null));
                        }
                        slot$iv$iv2 >>= i;
                        j$iv$iv++;
                        this_$iv3 = this_$iv2;
                        i2 = i;
                        $i$f$forEach3 = $i$f$forEach2;
                        k$iv3 = k$iv2;
                        v$iv3 = v$iv2;
                        this_$iv$iv3 = this_$iv$iv2;
                    }
                    this_$iv = this_$iv3;
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv3 = this_$iv;
                $i$f$forEach3 = $i$f$forEach;
                k$iv3 = k$iv;
                v$iv3 = v$iv;
                this_$iv$iv3 = this_$iv$iv;
            }
        }
        if (!this.config.getKeyframes$animation_core_release().contains(0)) {
            timestamps.add(0, 0);
        }
        if (!this.config.getKeyframes$animation_core_release().contains(this.config.getDurationMillis())) {
            timestamps.add(this.config.getDurationMillis());
        }
        timestamps.sort();
        return new VectorizedKeyframesSpec<>(timestamps, timeToInfoMap, this.config.getDurationMillis(), this.config.getDelayMillis(), EasingKt.getLinearEasing(), ArcMode.INSTANCE.m144getArcLinear9TMq4(), null);
    }

    /* compiled from: AnimationSpec.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\"\u0010\u0006\u001a\u00020\u0007X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0014"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "T", "Landroidx/compose/animation/core/KeyframeBaseEntity;", "value", "easing", "Landroidx/compose/animation/core/Easing;", "arcMode", "Landroidx/compose/animation/core/ArcMode;", "(Ljava/lang/Object;Landroidx/compose/animation/core/Easing;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getArcMode--9T-Mq4$animation_core_release", "()I", "setArcMode-Rur9ykg$animation_core_release", "(I)V", "I", "equals", "", "other", "", "hashCode", "", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframeEntity<T> extends KeyframeBaseEntity<T> {
        public static final int $stable = 8;
        private int arcMode;

        public /* synthetic */ KeyframeEntity(Object obj, Easing easing, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, easing, i);
        }

        public /* synthetic */ KeyframeEntity(Object obj, Easing easing, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, (i2 & 2) != 0 ? EasingKt.getLinearEasing() : easing, (i2 & 4) != 0 ? ArcMode.INSTANCE.m144getArcLinear9TMq4() : i, null);
        }

        /* renamed from: getArcMode--9T-Mq4$animation_core_release, reason: not valid java name and from getter */
        public final int getArcMode() {
            return this.arcMode;
        }

        /* renamed from: setArcMode-Rur9ykg$animation_core_release, reason: not valid java name */
        public final void m147setArcModeRur9ykg$animation_core_release(int i) {
            this.arcMode = i;
        }

        private KeyframeEntity(T t, Easing easing, int arcMode) {
            super(t, easing, null);
            this.arcMode = arcMode;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other instanceof KeyframeEntity) {
                return Intrinsics.areEqual(((KeyframeEntity) other).getValue$animation_core_release(), getValue$animation_core_release()) && Intrinsics.areEqual(((KeyframeEntity) other).getEasing(), getEasing()) && ArcMode.m138equalsimpl0(((KeyframeEntity) other).arcMode, this.arcMode);
            }
            return false;
        }

        public int hashCode() {
            T value$animation_core_release = getValue$animation_core_release();
            int result = value$animation_core_release != null ? value$animation_core_release.hashCode() : 0;
            return (((result * 31) + ArcMode.m139hashCodeimpl(this.arcMode)) * 31) + getEasing().hashCode();
        }
    }
}
