package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: MathUtils.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u001a\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"addExactOrElse", "", "right", "defaultValue", "Lkotlin/Function0;", "distanceSquaredToClosestCornerFromOutside", "", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "distanceSquaredToClosestCornerFromOutside-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)F", "findClosestRect", "rect1", "rect2", "findClosestRect-9KIMszo", "(JLandroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;)I", "subtractExactOrElse", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MathUtilsKt {
    public static final int addExactOrElse(int $this$addExactOrElse, int right, Function0<Integer> function0) {
        int result = $this$addExactOrElse + right;
        return (($this$addExactOrElse ^ result) & (right ^ result)) < 0 ? function0.invoke().intValue() : result;
    }

    public static final int subtractExactOrElse(int $this$subtractExactOrElse, int right, Function0<Integer> function0) {
        int result = $this$subtractExactOrElse - right;
        return (($this$subtractExactOrElse ^ right) & ($this$subtractExactOrElse ^ result)) < 0 ? function0.invoke().intValue() : result;
    }

    /* renamed from: findClosestRect-9KIMszo, reason: not valid java name */
    public static final int m1162findClosestRect9KIMszo(long $this$findClosestRect_u2d9KIMszo, Rect rect1, Rect rect2) {
        float comparativeDistTo1 = m1161distanceSquaredToClosestCornerFromOutside3MmeM6k($this$findClosestRect_u2d9KIMszo, rect1);
        float comparativeDistTo2 = m1161distanceSquaredToClosestCornerFromOutside3MmeM6k($this$findClosestRect_u2d9KIMszo, rect2);
        if (comparativeDistTo1 == comparativeDistTo2) {
            return 0;
        }
        return comparativeDistTo1 < comparativeDistTo2 ? -1 : 1;
    }

    /* renamed from: distanceSquaredToClosestCornerFromOutside-3MmeM6k, reason: not valid java name */
    private static final float m1161distanceSquaredToClosestCornerFromOutside3MmeM6k(long $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k, Rect rect) {
        if (SelectionManagerKt.m1365containsInclusiveUv8p0NA(rect, $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k)) {
            return 0.0f;
        }
        float it = Offset.m3921getDistanceSquaredimpl(Offset.m3926minusMKHz9U(rect.m3957getTopLeftF1C5BW0(), $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k));
        float distance = it < Float.MAX_VALUE ? it : Float.MAX_VALUE;
        float it2 = Offset.m3921getDistanceSquaredimpl(Offset.m3926minusMKHz9U(rect.m3958getTopRightF1C5BW0(), $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k));
        if (it2 < distance) {
            distance = it2;
        }
        float it3 = Offset.m3921getDistanceSquaredimpl(Offset.m3926minusMKHz9U(rect.m3950getBottomLeftF1C5BW0(), $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k));
        if (it3 < distance) {
            distance = it3;
        }
        float it4 = Offset.m3921getDistanceSquaredimpl(Offset.m3926minusMKHz9U(rect.m3951getBottomRightF1C5BW0(), $this$distanceSquaredToClosestCornerFromOutside_u2d3MmeM6k));
        return it4 < distance ? it4 : distance;
    }
}
