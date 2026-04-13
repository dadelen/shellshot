package androidx.compose.ui.graphics;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathSegment.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/PathSegment;", "", "type", "Landroidx/compose/ui/graphics/PathSegment$Type;", "points", "", "weight", "", "(Landroidx/compose/ui/graphics/PathSegment$Type;[FF)V", "getPoints", "()[F", "getType", "()Landroidx/compose/ui/graphics/PathSegment$Type;", "getWeight", "()F", "equals", "", "other", "hashCode", "", "toString", "", "Type", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PathSegment {
    private final float[] points;
    private final Type type;
    private final float weight;

    /* compiled from: PathSegment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/graphics/PathSegment$Type;", "", "(Ljava/lang/String;I)V", "Move", "Line", "Quadratic", "Conic", "Cubic", "Close", "Done", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Type {
        Move,
        Line,
        Quadratic,
        Conic,
        Cubic,
        Close,
        Done
    }

    public PathSegment(Type type, float[] points, float weight) {
        this.type = type;
        this.points = points;
        this.weight = weight;
    }

    public final Type getType() {
        return this.type;
    }

    public final float[] getPoints() {
        return this.points;
    }

    public final float getWeight() {
        return this.weight;
    }

    public boolean equals(Object other) {
        boolean z;
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (this.type != ((PathSegment) other).type || !Arrays.equals(this.points, ((PathSegment) other).points)) {
            return false;
        }
        if (this.weight == ((PathSegment) other).weight) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.type.hashCode();
        return (((result * 31) + Arrays.hashCode(this.points)) * 31) + Float.hashCode(this.weight);
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("PathSegment(type=").append(this.type).append(", points=");
        String arrays = Arrays.toString(this.points);
        Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
        return append.append(arrays).append(", weight=").append(this.weight).append(')').toString();
    }
}
