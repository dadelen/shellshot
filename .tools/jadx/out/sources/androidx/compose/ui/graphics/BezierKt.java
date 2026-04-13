package androidx.compose.ui.graphics;

import androidx.collection.FloatFloatPair;
import androidx.compose.ui.graphics.PathSegment;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;

/* compiled from: Bezier.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\t\u001a\u0011\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0082\b\u001a?\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010\u001b\u001a'\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010\u001e\u001a'\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\u0010\u001e\u001aH\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0000\u001a \u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u0018H\u0002\u001a0\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u0018H\u0000\u001a \u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u0003H\u0007\u001a0\u00103\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u0003H\u0002\u001a \u00109\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u0003H\u0002\u001a(\u0010:\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u0003H\u0002\u001a\u0018\u0010;\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0003H\u0002\u001a\u0018\u0010<\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0003H\u0007\u001a\u0018\u0010=\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\u0018H\u0002\u001a(\u0010?\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010@\u001a\u00020A2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a(\u0010B\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u0003H\u0007\u001a\u0019\u0010C\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0003H\u0082\b\u001a \u0010D\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u0003H\u0002\u001a\u0018\u0010E\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010F\u001a\u00020\u0003H\u0007\u001a+\u0010G\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0082\b\u001a2\u0010H\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0002\u001a \u0010I\u001a\u00020A2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0003H\u0002\u001a \u0010J\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003H\u0000\u001a(\u0010K\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010L\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003H\u0002\u001a0\u0010M\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010L\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u0018H\u0002\u001a\u0018\u0010N\u001a\u00020\u001a2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0018H\u0002\u001a0\u0010P\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u0018H\u0000\u001a0\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00182\u0006\u0010U\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u00182\u0006\u0010V\u001a\u00020\u001a2\u0006\u00106\u001a\u00020\u0003H\u0002\u001a \u0010W\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u0003H\u0002\u001a\u0018\u0010X\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003H\u0002\u001a \u0010Y\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a\u0015\u0010Z\u001a\u00020A*\u00020\u00012\u0006\u0010[\u001a\u00020\u0001H\u0080\b\u001a\u0015\u0010Z\u001a\u00020A*\u00020\u00032\u0006\u0010[\u001a\u00020\u0003H\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0005\u001a\u00020\u0003*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001a\u00020\u0003*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u0019\u0010\u000b\u001a\u00020\u0003*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b\"\u0019\u0010\r\u001a\u00020\u0003*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\\"}, d2 = {"Epsilon", "", "FloatEpsilon", "", "Tau", "endX", "Landroidx/compose/ui/graphics/PathSegment;", "getEndX", "(Landroidx/compose/ui/graphics/PathSegment;)F", "endY", "getEndY", "startX", "getStartX", "startY", "getStartY", "clampValidRootInUnitRange", "r", "computeCubicVerticalBounds", "Landroidx/collection/FloatFloatPair;", "p0y", "p1y", "p2y", "p3y", "roots", "", "index", "", "(FFFF[FI)J", "computeHorizontalBounds", "segment", "(Landroidx/compose/ui/graphics/PathSegment;[FI)J", "computeVerticalBounds", "cubicArea", "x0", "y0", "x1", "y1", "x2", "y2", "x3", "y3", "cubicToMonotonicCubics", "cubic", "dst", "tmpRoot", "cubicWinding", "points", "x", "y", "tmpCubics", "tmpRoots", "evaluateCubic", "p1", "p2", "t", "p0", "p3", "evaluateLine", "evaluateQuadratic", "evaluateX", "evaluateY", "findCubicExtremaY", "dstRoots", "findDerivativeRoots", "horizontal", "", "findFirstCubicRoot", "findFirstLineRoot", "findFirstQuadraticRoot", "findFirstRoot", "fraction", "findLineRoot", "findQuadraticRoots", "isQuadraticMonotonic", "lineWinding", "monotonicCubicWinding", "offset", "monotonicQuadraticWinding", "quadraticToMonotonicQuadratics", "quadratic", "quadraticWinding", "tmpQuadratics", "splitCubicAt", "", "src", "srcOffset", "dstOffset", "splitQuadraticAt", "unitDivide", "writeValidRootInUnitRange", "closeTo", "b", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class BezierKt {
    private static final double Epsilon = 1.0E-7d;
    private static final float FloatEpsilon = 8.34465E-7f;
    private static final double Tau = 6.283185307179586d;

    /* compiled from: Bezier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PathSegment.Type.values().length];
            try {
                iArr[PathSegment.Type.Move.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PathSegment.Type.Line.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PathSegment.Type.Quadratic.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PathSegment.Type.Conic.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PathSegment.Type.Cubic.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[PathSegment.Type.Close.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[PathSegment.Type.Done.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final float evaluateX(PathSegment segment, float t) {
        float[] points = segment.getPoints();
        switch (WhenMappings.$EnumSwitchMapping$0[segment.getType().ordinal()]) {
            case 1:
                return points[0];
            case 2:
                return evaluateLine(points[0], points[2], t);
            case 3:
                return evaluateQuadratic(points[0], points[2], points[4], t);
            case 4:
            case 6:
            case 7:
                return Float.NaN;
            case 5:
                return evaluateCubic(points[0], points[2], points[4], points[6], t);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final float evaluateY(PathSegment segment, float t) {
        float[] points = segment.getPoints();
        switch (WhenMappings.$EnumSwitchMapping$0[segment.getType().ordinal()]) {
            case 1:
                return points[1];
            case 2:
                return evaluateLine(points[1], points[3], t);
            case 3:
                return evaluateQuadratic(points[1], points[3], points[5], t);
            case 4:
            case 6:
            case 7:
                return Float.NaN;
            case 5:
                return evaluateCubic(points[1], points[3], points[5], points[7], t);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final float evaluateLine(float p0y, float p1y, float t) {
        return ((p1y - p0y) * t) + p0y;
    }

    private static final float evaluateQuadratic(float p0, float p1, float p2, float t) {
        float by = (p1 - p0) * 2.0f;
        float ay = (p2 - (2.0f * p1)) + p0;
        return (((ay * t) + by) * t) + p0;
    }

    private static final float evaluateCubic(float p0, float p1, float p2, float p3, float t) {
        float a = (((p1 - p2) * 3.0f) + p3) - p0;
        float b = ((p2 - (2.0f * p1)) + p0) * 3.0f;
        float c = (p1 - p0) * 3.0f;
        return (((((a * t) + b) * t) + c) * t) + p0;
    }

    public static final float evaluateCubic(float p1, float p2, float t) {
        float a = (p1 - p2) + 0.33333334f;
        float b = p2 - (2.0f * p1);
        return ((((a * t) + b) * t) + p1) * 3.0f * t;
    }

    public static final float findFirstRoot(PathSegment segment, float fraction) {
        float[] points = segment.getPoints();
        switch (WhenMappings.$EnumSwitchMapping$0[segment.getType().ordinal()]) {
            case 1:
            case 4:
            case 6:
            case 7:
                return Float.NaN;
            case 2:
                float p0$iv = points[0] - fraction;
                float p1$iv = points[2] - fraction;
                float r$iv$iv = (-p0$iv) / (p1$iv - p0$iv);
                if (r$iv$iv < 0.0f) {
                    return r$iv$iv >= -8.34465E-7f ? 0.0f : Float.NaN;
                }
                if (r$iv$iv > 1.0f) {
                    return r$iv$iv <= 1.0000008f ? 1.0f : Float.NaN;
                }
                return r$iv$iv;
            case 3:
                return findFirstQuadraticRoot(points[0] - fraction, points[2] - fraction, points[4] - fraction);
            case 5:
                return findFirstCubicRoot(points[0] - fraction, points[2] - fraction, points[4] - fraction, points[6] - fraction);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final float findFirstLineRoot(float p0, float p1) {
        float r$iv = (-p0) / (p1 - p0);
        if (r$iv < 0.0f) {
            return r$iv >= -8.34465E-7f ? 0.0f : Float.NaN;
        }
        if (r$iv > 1.0f) {
            return r$iv <= 1.0000008f ? 1.0f : Float.NaN;
        }
        return r$iv;
    }

    private static final float findFirstQuadraticRoot(float p0, float p1, float p2) {
        float root;
        double a = p0;
        double b = p1;
        double c = p2;
        double d = (a - (b * 2.0d)) + c;
        if (d == 0.0d) {
            if (b == c) {
                return Float.NaN;
            }
            float r$iv = (float) (((b * 2.0d) - c) / ((b * 2.0d) - (2.0d * c)));
            if (r$iv < 0.0f) {
                return r$iv >= -8.34465E-7f ? 0.0f : Float.NaN;
            }
            if (r$iv > 1.0f) {
                return r$iv <= 1.0000008f ? 1.0f : Float.NaN;
            }
            return r$iv;
        }
        double v1 = -Math.sqrt((b * b) - (a * c));
        double v2 = (-a) + b;
        float r$iv2 = (float) ((-(v1 + v2)) / d);
        if (r$iv2 < 0.0f) {
            root = r$iv2 >= -8.34465E-7f ? 0.0f : Float.NaN;
        } else if (r$iv2 > 1.0f) {
            root = r$iv2 <= 1.0000008f ? 1.0f : Float.NaN;
        } else {
            root = r$iv2;
        }
        if (!Float.isNaN(root)) {
            return root;
        }
        float r$iv3 = (float) ((v1 - v2) / d);
        if (r$iv3 < 0.0f) {
            return r$iv3 >= -8.34465E-7f ? 0.0f : Float.NaN;
        }
        if (r$iv3 > 1.0f) {
            return r$iv3 <= 1.0000008f ? 1.0f : Float.NaN;
        }
        return r$iv3;
    }

    public static final float findFirstCubicRoot(float p0, float p1, float p2, float p3) {
        float root;
        float root2;
        float root3;
        float root4;
        double a = ((p0 - (p1 * 2.0d)) + p2) * 3.0d;
        double b = (p1 - p0) * 3.0d;
        double c = p0;
        double d = (-p0) + ((p1 - p2) * 3.0d) + p3;
        if (Math.abs(d - 0.0d) < Epsilon) {
            int $i$f$closeTo = Math.abs(a - 0.0d) < Epsilon ? 1 : 0;
            if ($i$f$closeTo != 0) {
                if (Math.abs(b - 0.0d) < Epsilon) {
                    return Float.NaN;
                }
                float r$iv = (float) ((-c) / b);
                if (r$iv < 0.0f) {
                    return r$iv >= -8.34465E-7f ? 0.0f : Float.NaN;
                }
                if (r$iv > 1.0f) {
                    return r$iv <= 1.0000008f ? 1.0f : Float.NaN;
                }
                return r$iv;
            }
            double q = Math.sqrt((b * b) - ((4.0d * a) * c));
            double a2 = a * 2.0d;
            float r$iv2 = (float) ((q - b) / a2);
            if (r$iv2 < 0.0f) {
                root4 = r$iv2 >= -8.34465E-7f ? 0.0f : Float.NaN;
            } else if (r$iv2 > 1.0f) {
                root4 = r$iv2 <= 1.0000008f ? 1.0f : Float.NaN;
            } else {
                root4 = r$iv2;
            }
            if (!Float.isNaN(root4)) {
                return root4;
            }
            float r$iv3 = (float) (((-b) - q) / a2);
            if (r$iv3 < 0.0f) {
                return r$iv3 >= -8.34465E-7f ? 0.0f : Float.NaN;
            }
            if (r$iv3 > 1.0f) {
                return r$iv3 <= 1.0000008f ? 1.0f : Float.NaN;
            }
            return r$iv3;
        }
        double a3 = a / d;
        double b2 = b / d;
        double o3 = ((b2 * 3.0d) - (a3 * a3)) / 9.0d;
        double q2 = (((((2.0d * a3) * a3) * a3) - ((9.0d * a3) * b2)) + (27.0d * (c / d))) / 54.0d;
        double discriminant = (q2 * q2) + (o3 * o3 * o3);
        double a32 = a3 / 3.0d;
        if (discriminant >= 0.0d) {
            if (discriminant == 0.0d) {
                float u1 = -MathHelpersKt.fastCbrt((float) q2);
                float r$iv4 = (u1 * 2.0f) - ((float) a32);
                if (r$iv4 < 0.0f) {
                    root = r$iv4 >= -8.34465E-7f ? 0.0f : Float.NaN;
                } else if (r$iv4 > 1.0f) {
                    root = r$iv4 <= 1.0000008f ? 1.0f : Float.NaN;
                } else {
                    root = r$iv4;
                }
                if (!Float.isNaN(root)) {
                    return root;
                }
                float r$iv5 = (-u1) - ((float) a32);
                if (r$iv5 < 0.0f) {
                    return r$iv5 >= -8.34465E-7f ? 0.0f : Float.NaN;
                }
                if (r$iv5 > 1.0f) {
                    return r$iv5 <= 1.0000008f ? 1.0f : Float.NaN;
                }
                return r$iv5;
            }
            double sd = Math.sqrt(discriminant);
            float u12 = MathHelpersKt.fastCbrt((float) ((-q2) + sd));
            float v1 = MathHelpersKt.fastCbrt((float) (q2 + sd));
            float r$iv6 = (float) ((u12 - v1) - a32);
            if (r$iv6 < 0.0f) {
                return r$iv6 >= -8.34465E-7f ? 0.0f : Float.NaN;
            }
            if (r$iv6 > 1.0f) {
                return r$iv6 <= 1.0000008f ? 1.0f : Float.NaN;
            }
            return r$iv6;
        }
        double mp33 = -(o3 * o3 * o3);
        double r = Math.sqrt(mp33);
        double t = (-q2) / r;
        double $this$fastCoerceAtLeast$iv$iv = t;
        if ($this$fastCoerceAtLeast$iv$iv < -1.0d) {
            $this$fastCoerceAtLeast$iv$iv = -1.0d;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0d) {
            $this$fastCoerceAtLeast$iv$iv = 1.0d;
        }
        double phi = Math.acos($this$fastCoerceAtLeast$iv$iv);
        float t1 = MathHelpersKt.fastCbrt((float) r) * 2.0f;
        float r$iv7 = (float) ((t1 * Math.cos(phi / 3.0d)) - a32);
        if (r$iv7 < 0.0f) {
            root2 = r$iv7 >= -8.34465E-7f ? 0.0f : Float.NaN;
        } else if (r$iv7 > 1.0f) {
            root2 = r$iv7 <= 1.0000008f ? 1.0f : Float.NaN;
        } else {
            root2 = r$iv7;
        }
        if (!Float.isNaN(root2)) {
            return root2;
        }
        float r$iv8 = (float) ((t1 * Math.cos((phi + Tau) / 3.0d)) - a32);
        if (r$iv8 < 0.0f) {
            root3 = r$iv8 >= -8.34465E-7f ? 0.0f : Float.NaN;
        } else if (r$iv8 > 1.0f) {
            root3 = r$iv8 <= 1.0000008f ? 1.0f : Float.NaN;
        } else {
            root3 = r$iv8;
        }
        if (!Float.isNaN(root3)) {
            return root3;
        }
        float r$iv9 = (float) ((t1 * Math.cos((phi + 12.566370614359172d) / 3.0d)) - a32);
        if (r$iv9 < 0.0f) {
            return r$iv9 >= -8.34465E-7f ? 0.0f : Float.NaN;
        }
        if (r$iv9 > 1.0f) {
            return r$iv9 <= 1.0000008f ? 1.0f : Float.NaN;
        }
        return r$iv9;
    }

    static /* synthetic */ int findLineRoot$default(float p0, float p1, float[] roots, int index, int i, Object obj) {
        if ((i & 8) != 0) {
            index = 0;
        }
        return writeValidRootInUnitRange((-p0) / (p1 - p0), roots, index);
    }

    private static final int findLineRoot(float p0, float p1, float[] roots, int index) {
        return writeValidRootInUnitRange((-p0) / (p1 - p0), roots, index);
    }

    static /* synthetic */ int findQuadraticRoots$default(float f, float f2, float f3, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            i = 0;
        }
        return findQuadraticRoots(f, f2, f3, fArr, i);
    }

    private static final int findQuadraticRoots(float p0, float p1, float p2, float[] roots, int index) {
        double a = p0;
        double b = p1;
        double c = p2;
        double d = (a - (b * 2.0d)) + c;
        if (d == 0.0d) {
            if (!(b == c)) {
                return 0 + writeValidRootInUnitRange((float) (((b * 2.0d) - c) / ((b * 2.0d) - (c * 2.0d))), roots, index);
            }
            return 0;
        }
        double v1 = -Math.sqrt((b * b) - (a * c));
        double v2 = (-a) + b;
        int rootCount = 0 + writeValidRootInUnitRange((float) ((-(v1 + v2)) / d), roots, index);
        int rootCount2 = rootCount + writeValidRootInUnitRange((float) ((v1 - v2) / d), roots, index + rootCount);
        if (rootCount2 > 1) {
            float s = roots[index];
            float t = roots[index + 1];
            if (s <= t) {
                return s == t ? rootCount2 - 1 : rootCount2;
            }
            roots[index] = t;
            roots[index + 1] = s;
            return rootCount2;
        }
        return rootCount2;
    }

    private static final int findDerivativeRoots(PathSegment pathSegment, boolean z, float[] fArr, int i) {
        int i2 = !z ? 1 : 0;
        float[] points = pathSegment.getPoints();
        switch (WhenMappings.$EnumSwitchMapping$0[pathSegment.getType().ordinal()]) {
            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
                return 0;
            case 3:
                float f = (points[i2 + 2] - points[i2 + 0]) * 2.0f;
                return writeValidRootInUnitRange((-f) / ((2.0f * (points[i2 + 4] - points[i2 + 2])) - f), fArr, i);
            case 5:
                float f2 = (points[i2 + 2] - points[i2 + 0]) * 3.0f;
                float f3 = (points[i2 + 4] - points[i2 + 2]) * 3.0f;
                float f4 = (points[i2 + 6] - points[i2 + 4]) * 3.0f;
                int findQuadraticRoots = findQuadraticRoots(f2, f3, f4, fArr, i);
                float f5 = (f3 - f2) * 2.0f;
                return findQuadraticRoots + writeValidRootInUnitRange((-f5) / (((f4 - f3) * 2.0f) - f5), fArr, i + findQuadraticRoots);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ long computeHorizontalBounds$default(PathSegment pathSegment, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return computeHorizontalBounds(pathSegment, fArr, i);
    }

    public static final long computeHorizontalBounds(PathSegment segment, float[] roots, int index) {
        int count = findDerivativeRoots(segment, true, roots, index);
        float minX = Math.min(segment.getPoints()[0], getEndX(segment));
        float maxX = Math.max(segment.getPoints()[0], getEndX(segment));
        for (int i = 0; i < count; i++) {
            float t = roots[i];
            float x = evaluateX(segment, t);
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        }
        return FloatFloatPair.m6constructorimpl(minX, maxX);
    }

    public static /* synthetic */ long computeVerticalBounds$default(PathSegment pathSegment, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return computeVerticalBounds(pathSegment, fArr, i);
    }

    public static final long computeVerticalBounds(PathSegment segment, float[] roots, int index) {
        int count = findDerivativeRoots(segment, false, roots, index);
        float minY = Math.min(segment.getPoints()[1], getEndY(segment));
        float maxY = Math.max(segment.getPoints()[1], getEndY(segment));
        for (int i = 0; i < count; i++) {
            float t = roots[i];
            float x = evaluateY(segment, t);
            minY = Math.min(minY, x);
            maxY = Math.max(maxY, x);
        }
        return FloatFloatPair.m6constructorimpl(minY, maxY);
    }

    public static /* synthetic */ long computeCubicVerticalBounds$default(float f, float f2, float f3, float f4, float[] fArr, int i, int i2, Object obj) {
        int i3;
        if ((i2 & 32) == 0) {
            i3 = i;
        } else {
            i3 = 0;
        }
        return computeCubicVerticalBounds(f, f2, f3, f4, fArr, i3);
    }

    public static final long computeCubicVerticalBounds(float p0y, float p1y, float p2y, float p3y, float[] roots, int index) {
        float[] fArr = roots;
        float d0 = (p1y - p0y) * 3.0f;
        float d1 = (p2y - p1y) * 3.0f;
        float d2 = (p3y - p2y) * 3.0f;
        int count = findQuadraticRoots(d0, d1, d2, fArr, index);
        float dd0 = (d1 - d0) * 2.0f;
        float dd1 = (d2 - d1) * 2.0f;
        int index$iv = index + count;
        int count2 = count + writeValidRootInUnitRange((-dd0) / (dd1 - dd0), fArr, index$iv);
        float minY = Math.min(p0y, p3y);
        float maxY = Math.max(p0y, p3y);
        int i = 0;
        while (i < count2) {
            float t = fArr[i];
            float y = evaluateCubic(p0y, p1y, p2y, p3y, t);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
            i++;
            fArr = roots;
        }
        return FloatFloatPair.m6constructorimpl(minY, maxY);
    }

    public static final boolean closeTo(double $this$closeTo, double b) {
        return Math.abs($this$closeTo - b) < Epsilon;
    }

    public static final boolean closeTo(float $this$closeTo, float b) {
        return Math.abs($this$closeTo - b) < FloatEpsilon;
    }

    private static final float clampValidRootInUnitRange(float r) {
        if (r < 0.0f) {
            return r >= -8.34465E-7f ? 0.0f : Float.NaN;
        }
        if (r > 1.0f) {
            return r <= 1.0000008f ? 1.0f : Float.NaN;
        }
        return r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int writeValidRootInUnitRange(float f, float[] fArr, int i) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = 1.0f;
            if (f <= 1.0f) {
                f2 = f;
            } else if (f > 1.0000008f) {
                f2 = Float.NaN;
            }
        } else if (f < -8.34465E-7f) {
            f2 = Float.NaN;
        }
        fArr[i] = f2;
        return !Float.isNaN(f2) ? 1 : 0;
    }

    public static final int lineWinding(float[] points, float x, float y) {
        boolean z = false;
        float x0 = points[0];
        float y0 = points[1];
        float x1 = points[2];
        float y1 = points[3];
        float dy = y1 - y0;
        int direction = 1;
        if (y0 > y1) {
            y0 = y1;
            y1 = y0;
            direction = -1;
        }
        if (y < y0 || y >= y1) {
            return 0;
        }
        float crossProduct = ((x1 - x0) * (y - y0)) - ((x - x0) * dy);
        if (crossProduct == 0.0f) {
            z = true;
        }
        if (z || ((int) Math.signum(crossProduct)) == direction) {
            return 0;
        }
        return direction;
    }

    private static final boolean isQuadraticMonotonic(float y0, float y1, float y2) {
        return !(Math.signum(y0 - y1) + Math.signum(y1 - y2) == 0.0f);
    }

    public static final int quadraticWinding(float[] points, float x, float y, float[] tmpQuadratics, float[] tmpRoots) {
        float y0 = points[1];
        float y1 = points[3];
        float y2 = points[5];
        if (isQuadraticMonotonic(y0, y1, y2)) {
            return monotonicQuadraticWinding(points, 0, x, y, tmpRoots);
        }
        int rootCount = quadraticToMonotonicQuadratics(points, tmpQuadratics);
        int winding = monotonicQuadraticWinding(tmpQuadratics, 0, x, y, tmpRoots);
        if (rootCount > 0) {
            return winding + monotonicQuadraticWinding(tmpQuadratics, 4, x, y, tmpRoots);
        }
        return winding;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007d, code lost:
    
        if ((r17 == r1) == false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int monotonicQuadraticWinding(float[] r14, int r15, float r16, float r17, float[] r18) {
        /*
            int r0 = r15 + 1
            r0 = r14[r0]
            int r1 = r15 + 5
            r1 = r14[r1]
            r2 = 1
            int r3 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r3 <= 0) goto L11
            r3 = r1
            r1 = r0
            r0 = r3
            r2 = -1
        L11:
            int r3 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            r4 = 0
            if (r3 < 0) goto L86
            int r3 = (r17 > r1 ? 1 : (r17 == r1 ? 0 : -1))
            if (r3 < 0) goto L1c
            goto L86
        L1c:
            int r3 = r15 + 1
            r0 = r14[r3]
            int r3 = r15 + 3
            r3 = r14[r3]
            int r5 = r15 + 5
            r1 = r14[r5]
            r5 = 1073741824(0x40000000, float:2.0)
            float r6 = r3 * r5
            float r6 = r0 - r6
            float r7 = r6 + r1
            float r6 = r3 - r0
            float r8 = r6 * r5
            float r9 = r0 - r17
            r12 = 16
            r13 = 0
            r11 = 0
            r10 = r18
            int r5 = findQuadraticRoots$default(r7, r8, r9, r10, r11, r12, r13)
            r6 = 4
            r7 = 2
            if (r5 != 0) goto L4b
            int r8 = 1 - r2
            int r8 = r8 * r7
            r7 = r14[r8]
            goto L57
        L4b:
            r8 = r14[r4]
            r7 = r14[r7]
            r9 = r14[r6]
            r10 = r18[r4]
            float r7 = evaluateQuadratic(r8, r7, r9, r10)
        L57:
            r8 = r7
            r9 = 0
            float r10 = r8 - r16
            float r10 = java.lang.Math.abs(r10)
            r11 = 895483904(0x35600000, float:8.34465E-7)
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            r11 = 1
            if (r10 >= 0) goto L69
            r8 = r11
            goto L6a
        L69:
            r8 = r4
        L6a:
            if (r8 == 0) goto L80
            r6 = r14[r6]
            int r6 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r6 != 0) goto L74
            r6 = r11
            goto L75
        L74:
            r6 = r4
        L75:
            if (r6 == 0) goto L7f
            int r6 = (r17 > r1 ? 1 : (r17 == r1 ? 0 : -1))
            if (r6 != 0) goto L7c
            goto L7d
        L7c:
            r11 = r4
        L7d:
            if (r11 != 0) goto L80
        L7f:
            return r4
        L80:
            int r6 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r6 >= 0) goto L85
            r4 = r2
        L85:
            return r4
        L86:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.BezierKt.monotonicQuadraticWinding(float[], int, float, float, float[]):int");
    }

    private static final int quadraticToMonotonicQuadratics(float[] quadratic, float[] dst) {
        float y0 = quadratic[1];
        float y1 = quadratic[3];
        float y2 = quadratic[5];
        if (!isQuadraticMonotonic(y0, y1, y2)) {
            float t = unitDivide(y0 - y1, ((y0 - y1) - y1) + y2);
            if (!Float.isNaN(t)) {
                splitQuadraticAt(quadratic, dst, t);
                return 1;
            }
            y1 = Math.abs(y0 - y1) < Math.abs(y1 - y2) ? y0 : y2;
        }
        ArraysKt.copyInto(quadratic, dst, 0, 0, 6);
        dst[3] = y1;
        return 0;
    }

    private static final void splitQuadraticAt(float[] src, float[] dst, float t) {
        float p0x = src[0];
        float p0y = src[1];
        float p1x = src[2];
        float p1y = src[3];
        float p2x = src[4];
        float p2y = src[5];
        float abx = MathHelpersKt.lerp(p0x, p1x, t);
        float aby = MathHelpersKt.lerp(p0y, p1y, t);
        dst[0] = p0x;
        dst[1] = p0y;
        dst[2] = abx;
        dst[3] = aby;
        float bcx = MathHelpersKt.lerp(p1x, p2x, t);
        float bcy = MathHelpersKt.lerp(p1y, p2y, t);
        float abcx = MathHelpersKt.lerp(abx, bcx, t);
        float abcy = MathHelpersKt.lerp(aby, bcy, t);
        dst[4] = abcx;
        dst[5] = abcy;
        dst[6] = bcx;
        dst[7] = bcy;
        dst[8] = p2x;
        dst[9] = p2y;
    }

    private static final float unitDivide(float x, float y) {
        float n = x;
        float d = y;
        if (n < 0.0f) {
            n = -n;
            d = -d;
        }
        if (!(d == 0.0f)) {
            if (!(n == 0.0f) && n < d) {
                float r = n / d;
                if (r == 0.0f) {
                    return Float.NaN;
                }
                return r;
            }
        }
        return Float.NaN;
    }

    public static final int cubicWinding(float[] points, float x, float y, float[] tmpCubics, float[] tmpRoots) {
        int splits = cubicToMonotonicCubics(points, tmpCubics, tmpRoots);
        int winding = 0;
        int i = 0;
        if (0 <= splits) {
            while (true) {
                winding += monotonicCubicWinding(tmpCubics, i * 3 * 2, x, y);
                if (i == splits) {
                    break;
                }
                i++;
            }
        }
        return winding;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a0, code lost:
    
        if ((r21 == r1) == false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int monotonicCubicWinding(float[] r18, int r19, float r20, float r21) {
        /*
            int r0 = r19 + 1
            r0 = r18[r0]
            int r1 = r19 + 7
            r1 = r18[r1]
            r2 = 1
            int r3 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r3 <= 0) goto L11
            r3 = r1
            r1 = r0
            r0 = r3
            r2 = -1
        L11:
            int r3 = (r21 > r0 ? 1 : (r21 == r0 ? 0 : -1))
            r4 = 0
            if (r3 < 0) goto La9
            int r3 = (r21 > r1 ? 1 : (r21 == r1 ? 0 : -1))
            if (r3 < 0) goto L1c
            goto La9
        L1c:
            int r3 = r19 + 0
            r3 = r18[r3]
            int r5 = r19 + 2
            r5 = r18[r5]
            int r6 = r19 + 4
            r6 = r18[r6]
            int r7 = r19 + 6
            r7 = r18[r7]
            r8 = 0
            float r9 = java.lang.Math.min(r6, r7)
            float r9 = java.lang.Math.min(r5, r9)
            float r9 = java.lang.Math.min(r3, r9)
            int r8 = (r20 > r9 ? 1 : (r20 == r9 ? 0 : -1))
            if (r8 >= 0) goto L3f
            return r4
        L3f:
            r8 = 0
            float r10 = java.lang.Math.max(r6, r7)
            float r10 = java.lang.Math.max(r5, r10)
            float r10 = java.lang.Math.max(r3, r10)
            int r8 = (r20 > r10 ? 1 : (r20 == r10 ? 0 : -1))
            if (r8 <= 0) goto L52
            return r2
        L52:
            int r8 = r19 + 1
            r0 = r18[r8]
            int r8 = r19 + 3
            r8 = r18[r8]
            int r11 = r19 + 5
            r11 = r18[r11]
            int r12 = r19 + 7
            r1 = r18[r12]
            float r12 = r0 - r21
            float r13 = r8 - r21
            float r14 = r11 - r21
            float r15 = r1 - r21
            float r12 = findFirstCubicRoot(r12, r13, r14, r15)
            boolean r13 = java.lang.Float.isNaN(r12)
            if (r13 == 0) goto L75
            return r4
        L75:
            float r13 = evaluateCubic(r3, r5, r6, r7, r12)
            r14 = r13
            r15 = 0
            float r16 = r14 - r20
            float r16 = java.lang.Math.abs(r16)
            r17 = 895483904(0x35600000, float:8.34465E-7)
            int r16 = (r16 > r17 ? 1 : (r16 == r17 ? 0 : -1))
            r17 = 1
            if (r16 >= 0) goto L8c
            r14 = r17
            goto L8d
        L8c:
            r14 = r4
        L8d:
            if (r14 == 0) goto La3
            int r14 = (r20 > r7 ? 1 : (r20 == r7 ? 0 : -1))
            if (r14 != 0) goto L96
            r14 = r17
            goto L97
        L96:
            r14 = r4
        L97:
            if (r14 == 0) goto La2
            int r14 = (r21 > r1 ? 1 : (r21 == r1 ? 0 : -1))
            if (r14 != 0) goto L9e
            goto La0
        L9e:
            r17 = r4
        La0:
            if (r17 != 0) goto La3
        La2:
            return r4
        La3:
            int r14 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r14 >= 0) goto La8
            r4 = r2
        La8:
            return r4
        La9:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.BezierKt.monotonicCubicWinding(float[], int, float, float):int");
    }

    private static final int cubicToMonotonicCubics(float[] cubic, float[] dst, float[] tmpRoot) {
        int rootCount = findCubicExtremaY(cubic, tmpRoot);
        if (rootCount == 0) {
            ArraysKt.copyInto(cubic, dst, 0, 0, 8);
        } else {
            float lastT = 0.0f;
            int dstOffset = 0;
            float[] src = cubic;
            for (int i = 0; i < rootCount; i++) {
                float t = tmpRoot[i];
                float $this$fastCoerceIn$iv = (t - lastT) / (1.0f - lastT);
                float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
                if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                    $this$fastCoerceAtLeast$iv$iv = 0.0f;
                }
                if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                    $this$fastCoerceAtLeast$iv$iv = 1.0f;
                }
                lastT = $this$fastCoerceAtLeast$iv$iv;
                splitCubicAt(src, dstOffset, dst, dstOffset, $this$fastCoerceAtLeast$iv$iv);
                src = dst;
                dstOffset += 6;
            }
        }
        return rootCount;
    }

    private static final int findCubicExtremaY(float[] cubic, float[] dstRoots) {
        float a = cubic[1];
        float b = cubic[3];
        float c = cubic[5];
        float d = cubic[7];
        float A = (d - a) + ((b - c) * 3.0f);
        float B = (((a - b) - b) - c) * 2.0f;
        float C = b - a;
        return findQuadraticRoots(A, B, C, dstRoots, 0);
    }

    private static final void splitCubicAt(float[] src, int srcOffset, float[] dst, int dstOffset, float t) {
        if (t >= 1.0f) {
            ArraysKt.copyInto(src, dst, dstOffset, srcOffset, 8);
            float x = src[srcOffset + 6];
            float y = src[srcOffset + 7];
            dst[dstOffset + 8] = x;
            dst[dstOffset + 9] = y;
            dst[dstOffset + 10] = x;
            dst[dstOffset + 11] = y;
            dst[dstOffset + 12] = x;
            dst[dstOffset + 13] = y;
            return;
        }
        float p0x = src[srcOffset + 0];
        float p0y = src[srcOffset + 1];
        dst[dstOffset + 0] = p0x;
        dst[dstOffset + 1] = p0y;
        float p1x = src[srcOffset + 2];
        float p1y = src[srcOffset + 3];
        float abx = MathHelpersKt.lerp(p0x, p1x, t);
        float aby = MathHelpersKt.lerp(p0y, p1y, t);
        dst[dstOffset + 2] = abx;
        dst[dstOffset + 3] = aby;
        float p2x = src[srcOffset + 4];
        float p2y = src[srcOffset + 5];
        float bcx = MathHelpersKt.lerp(p1x, p2x, t);
        float bcy = MathHelpersKt.lerp(p1y, p2y, t);
        float abcx = MathHelpersKt.lerp(abx, bcx, t);
        float abcy = MathHelpersKt.lerp(aby, bcy, t);
        dst[dstOffset + 4] = abcx;
        dst[dstOffset + 5] = abcy;
        float p3x = src[srcOffset + 6];
        float p3y = src[srcOffset + 7];
        float cdx = MathHelpersKt.lerp(p2x, p3x, t);
        float cdy = MathHelpersKt.lerp(p2y, p3y, t);
        float bcdx = MathHelpersKt.lerp(bcx, cdx, t);
        float bcdy = MathHelpersKt.lerp(bcy, cdy, t);
        float abcdx = MathHelpersKt.lerp(abcx, bcdx, t);
        float abcdy = MathHelpersKt.lerp(abcy, bcdy, t);
        dst[dstOffset + 6] = abcdx;
        dst[dstOffset + 7] = abcdy;
        dst[dstOffset + 8] = bcdx;
        dst[dstOffset + 9] = bcdy;
        dst[dstOffset + 10] = cdx;
        dst[dstOffset + 11] = cdy;
        dst[dstOffset + 12] = p3x;
        dst[dstOffset + 13] = p3y;
    }

    public static final float cubicArea(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        return ((((((((y3 - y0) * (x1 + x2)) - ((x3 - x0) * (y1 + y2))) + ((x0 - x2) * y1)) - ((y0 - y2) * x1)) + (((x0 / 3.0f) + x2) * y3)) - (((y0 / 3.0f) + y2) * x3)) * 3.0f) / 20.0f;
    }

    private static final float getStartX(PathSegment $this$startX) {
        return $this$startX.getPoints()[0];
    }

    private static final float getEndX(PathSegment $this$endX) {
        float[] points = $this$endX.getPoints();
        char c = 4;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$endX.getType().ordinal()]) {
            case 1:
                c = 0;
                break;
            case 2:
                c = 2;
                break;
            case 3:
            case 4:
                break;
            case 5:
                c = 6;
                break;
            case 6:
                c = 0;
                break;
            case 7:
                c = 0;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return points[c];
    }

    private static final float getStartY(PathSegment $this$startY) {
        return $this$startY.getPoints()[1];
    }

    private static final float getEndY(PathSegment $this$endY) {
        float[] points = $this$endY.getPoints();
        char c = 5;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$endY.getType().ordinal()]) {
            case 1:
                c = 0;
                break;
            case 2:
                c = 3;
                break;
            case 3:
            case 4:
                break;
            case 5:
                c = 7;
                break;
            case 6:
                c = 0;
                break;
            case 7:
                c = 0;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return points[c];
    }
}
