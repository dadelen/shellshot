package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: SpringEstimation.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007\u001a8\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007\u001a0\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\fH\u0007\u001a(\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u001a8\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u001a0\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u001a(\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u001a9\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001aH\u0082\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\u0004H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"MAX_LONG_MILLIS", "", "estimateAnimationDurationMillis", "stiffness", "", "dampingRatio", "initialVelocity", "initialDisplacement", "delta", "springConstant", "dampingCoefficient", "mass", "", "estimateCriticallyDamped", "firstRoot", "Landroidx/compose/animation/core/ComplexDouble;", "p0", "v0", "estimateDurationInternal", "secondRoot", "initialPosition", "estimateOverDamped", "estimateUnderDamped", "iterateNewtonsMethod", "x", "fn", "Lkotlin/Function1;", "fnPrime", "isNotFinite", "", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SpringEstimationKt {
    private static final long MAX_LONG_MILLIS = 9223372036854L;

    public static final long estimateAnimationDurationMillis(float stiffness, float dampingRatio, float initialVelocity, float initialDisplacement, float delta) {
        if (dampingRatio == 0.0f) {
            return MAX_LONG_MILLIS;
        }
        return estimateAnimationDurationMillis(stiffness, dampingRatio, initialVelocity, initialDisplacement, delta);
    }

    public static final long estimateAnimationDurationMillis(double stiffness, double dampingRatio, double initialVelocity, double initialDisplacement, double delta) {
        double dampingCoefficient = 2.0d * dampingRatio * Math.sqrt(stiffness);
        double partialRoot = (dampingCoefficient * dampingCoefficient) - (4.0d * stiffness);
        double $this$plus$iv = -dampingCoefficient;
        ComplexDouble other$iv = ComplexDoubleKt.complexSqrt(partialRoot);
        other$iv._real += $this$plus$iv;
        other$iv._real *= 0.5d;
        other$iv._imaginary *= 0.5d;
        double $this$minus$iv = -dampingCoefficient;
        ComplexDouble other$iv2 = ComplexDoubleKt.complexSqrt(partialRoot);
        other$iv2._real *= -1.0d;
        other$iv2._imaginary *= -1.0d;
        other$iv2._real += $this$minus$iv;
        other$iv2._real *= 0.5d;
        other$iv2._imaginary *= 0.5d;
        return estimateDurationInternal(other$iv, other$iv2, dampingRatio, initialVelocity, initialDisplacement, delta);
    }

    public static final long estimateAnimationDurationMillis(double springConstant, double dampingCoefficient, double mass, double initialVelocity, double initialDisplacement, double delta) {
        double criticalDamping = Math.sqrt(springConstant * mass) * 2.0d;
        double dampingRatio = dampingCoefficient / criticalDamping;
        double partialRoot = (dampingCoefficient * dampingCoefficient) - ((4.0d * mass) * springConstant);
        double divisor = 1.0d / (2.0d * mass);
        double $this$plus$iv = -dampingCoefficient;
        ComplexDouble other$iv = ComplexDoubleKt.complexSqrt(partialRoot);
        other$iv._real += $this$plus$iv;
        other$iv._real *= divisor;
        other$iv._imaginary *= divisor;
        double $this$minus$iv = -dampingCoefficient;
        ComplexDouble other$iv2 = ComplexDoubleKt.complexSqrt(partialRoot);
        other$iv2._real *= -1.0d;
        other$iv2._imaginary *= -1.0d;
        double $this$plus$iv$iv = other$iv2._real + $this$minus$iv;
        other$iv2._real = $this$plus$iv$iv;
        other$iv2._real *= divisor;
        other$iv2._imaginary *= divisor;
        return estimateDurationInternal(other$iv, other$iv2, dampingRatio, initialVelocity, initialDisplacement, delta);
    }

    private static final double estimateUnderDamped(ComplexDouble firstRoot, double p0, double v0, double delta) {
        double r = firstRoot.getReal();
        double c2 = (v0 - (r * p0)) / firstRoot.getImaginary();
        double c = Math.sqrt((p0 * p0) + (c2 * c2));
        return Math.log(delta / c) / r;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final double estimateCriticallyDamped(androidx.compose.animation.core.ComplexDouble r35, double r36, double r38, double r40) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SpringEstimationKt.estimateCriticallyDamped(androidx.compose.animation.core.ComplexDouble, double, double, double):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00e6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final double estimateOverDamped(androidx.compose.animation.core.ComplexDouble r35, androidx.compose.animation.core.ComplexDouble r36, double r37, double r39, double r41) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SpringEstimationKt.estimateOverDamped(androidx.compose.animation.core.ComplexDouble, androidx.compose.animation.core.ComplexDouble, double, double, double):double");
    }

    private static final double estimateOverDamped$xInflection(double c1, double r1, double tInflection, double c2, double r2) {
        return (Math.exp(r1 * tInflection) * c1) + (Math.exp(r2 * tInflection) * c2);
    }

    private static final long estimateDurationInternal(ComplexDouble firstRoot, ComplexDouble secondRoot, double dampingRatio, double initialVelocity, double initialPosition, double delta) {
        double estimateCriticallyDamped;
        if (initialPosition == 0.0d) {
            if (initialVelocity == 0.0d) {
                return 0L;
            }
        }
        double v0 = initialPosition < 0.0d ? -initialVelocity : initialVelocity;
        double p0 = Math.abs(initialPosition);
        if (dampingRatio > 1.0d) {
            estimateCriticallyDamped = estimateOverDamped(firstRoot, secondRoot, p0, v0, delta);
        } else if (dampingRatio < 1.0d) {
            estimateCriticallyDamped = estimateUnderDamped(firstRoot, p0, v0, delta);
        } else {
            estimateCriticallyDamped = estimateCriticallyDamped(firstRoot, p0, v0, delta);
        }
        return (long) (estimateCriticallyDamped * 1000.0d);
    }

    private static final double iterateNewtonsMethod(double x, Function1<? super Double, Double> function1, Function1<? super Double, Double> function12) {
        return x - (function1.invoke(Double.valueOf(x)).doubleValue() / function12.invoke(Double.valueOf(x)).doubleValue());
    }

    private static final boolean isNotFinite(double $this$isNotFinite) {
        return !((Double.isInfinite($this$isNotFinite) || Double.isNaN($this$isNotFinite)) ? false : true);
    }
}
