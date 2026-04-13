package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.IntervalTree;
import androidx.compose.ui.graphics.PathIterator;
import androidx.compose.ui.graphics.PathSegment;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathHitTester.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/PathHitTester;", "", "()V", "bounds", "Landroidx/compose/ui/geometry/Rect;", "curves", "", "intervals", "Landroidx/compose/ui/graphics/IntervalTree;", "Landroidx/compose/ui/graphics/PathSegment;", "path", "Landroidx/compose/ui/graphics/Path;", "roots", "tolerance", "", "contains", "", "position", "Landroidx/compose/ui/geometry/Offset;", "contains-k-4lQ0M", "(J)Z", "updatePath", "", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PathHitTester {
    private Rect bounds;
    private final float[] curves;
    private final IntervalTree<PathSegment> intervals;
    private Path path;
    private final float[] roots;
    private float tolerance;

    /* compiled from: PathHitTester.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PathSegment.Type.values().length];
            try {
                iArr[PathSegment.Type.Line.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PathSegment.Type.Quadratic.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PathSegment.Type.Cubic.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PathSegment.Type.Done.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PathHitTester() {
        Path path;
        path = PathHitTesterKt.EmptyPath;
        this.path = path;
        this.tolerance = 0.5f;
        this.bounds = Rect.INSTANCE.getZero();
        this.intervals = new IntervalTree<>();
        this.curves = new float[20];
        this.roots = new float[2];
    }

    public static /* synthetic */ void updatePath$default(PathHitTester pathHitTester, Path path, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.5f;
        }
        pathHitTester.updatePath(path, f);
    }

    public final void updatePath(Path path, float tolerance) {
        this.path = path;
        this.tolerance = tolerance;
        this.bounds = path.getBounds();
        this.intervals.clear();
        PathIterator iterator = path.iterator(PathIterator.ConicEvaluation.AsQuadratics, tolerance);
        while (iterator.hasNext()) {
            PathSegment segment = iterator.next();
            switch (WhenMappings.$EnumSwitchMapping$0[segment.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    long computeVerticalBounds$default = BezierKt.computeVerticalBounds$default(segment, this.curves, 0, 4, null);
                    int bits$iv$iv = (int) (computeVerticalBounds$default >> 32);
                    float min = Float.intBitsToFloat(bits$iv$iv);
                    int bits$iv$iv2 = (int) (computeVerticalBounds$default & 4294967295L);
                    float max = Float.intBitsToFloat(bits$iv$iv2);
                    this.intervals.addInterval(min, max, segment);
                    break;
                case 4:
                    return;
            }
        }
    }

    /* renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m4446containsk4lQ0M(long position) {
        boolean isInverse;
        boolean isInverse2 = false;
        if (!this.path.isEmpty() && this.bounds.m3948containsk4lQ0M(position)) {
            float x = Offset.m3922getXimpl(position);
            float y = Offset.m3923getYimpl(position);
            float[] curvesArray = this.curves;
            float[] rootsArray = this.roots;
            int winding = 0;
            IntervalTree $this$iv = this.intervals;
            if ($this$iv.root != $this$iv.terminator) {
                ArrayList s$iv = $this$iv.stack;
                s$iv.add($this$iv.root);
                while (s$iv.size() > 0) {
                    IntervalTree.Node node$iv = (IntervalTree.Node) CollectionsKt.removeLast(s$iv);
                    if (node$iv.overlaps(y, y)) {
                        IntervalTree.Node interval = node$iv;
                        Object data = interval.getData();
                        Intrinsics.checkNotNull(data);
                        PathSegment segment = (PathSegment) data;
                        isInverse = isInverse2;
                        float[] points = segment.getPoints();
                        switch (WhenMappings.$EnumSwitchMapping$0[segment.getType().ordinal()]) {
                            case 1:
                                winding += BezierKt.lineWinding(points, x, y);
                                break;
                            case 2:
                                winding += BezierKt.quadraticWinding(points, x, y, curvesArray, rootsArray);
                                break;
                            case 3:
                                winding += BezierKt.cubicWinding(points, x, y, curvesArray, rootsArray);
                                break;
                        }
                    } else {
                        isInverse = isInverse2;
                    }
                    if (node$iv.getLeft() != $this$iv.terminator && node$iv.getLeft().getMax() >= y) {
                        s$iv.add(node$iv.getLeft());
                    }
                    if (node$iv.getRight() != $this$iv.terminator && node$iv.getRight().getMin() <= y) {
                        s$iv.add(node$iv.getRight());
                    }
                    isInverse2 = isInverse;
                }
                s$iv.clear();
            }
            boolean isEvenOdd = PathFillType.m4440equalsimpl0(this.path.mo4054getFillTypeRgk1Os(), PathFillType.INSTANCE.m4444getEvenOddRgk1Os());
            if (isEvenOdd) {
                winding &= 1;
            }
            if (winding != 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
