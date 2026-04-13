package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.PathSegment;
import androidx.compose.ui.graphics.vector.PathParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathSvg.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"command", "", "type", "Landroidx/compose/ui/graphics/PathSegment$Type;", "lastType", "addSvg", "", "Landroidx/compose/ui/graphics/Path;", "pathData", "toSvg", "asDocument", "", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PathSvgKt {

    /* compiled from: PathSvg.kt */
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

    public static final void addSvg(Path $this$addSvg, String pathData) {
        new PathParser().parsePathString(pathData).toPath($this$addSvg);
    }

    public static final String toSvg(Path $this$toSvg, boolean asDocument) {
        StringBuilder $this$toSvg_u24lambda_u240 = new StringBuilder();
        Rect bounds = $this$toSvg.getBounds();
        if (asDocument) {
            $this$toSvg_u24lambda_u240.append("<svg xmlns=\"http://www.w3.org/2000/svg\" ");
            StringBuilder append = $this$toSvg_u24lambda_u240.append("viewBox=\"" + bounds.getLeft() + ' ' + bounds.getTop() + ' ' + bounds.getWidth() + ' ' + bounds.getHeight() + "\">");
            Intrinsics.checkNotNullExpressionValue(append, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
        }
        PathIterator iterator = $this$toSvg.iterator();
        float[] points = new float[8];
        PathSegment.Type lastType = PathSegment.Type.Done;
        if (iterator.hasNext()) {
            if (asDocument) {
                if (PathFillType.m4440equalsimpl0($this$toSvg.mo4054getFillTypeRgk1Os(), PathFillType.INSTANCE.m4444getEvenOddRgk1Os())) {
                    $this$toSvg_u24lambda_u240.append("  <path fill-rule=\"evenodd\" d=\"");
                } else {
                    $this$toSvg_u24lambda_u240.append("  <path d=\"");
                }
            }
            while (iterator.hasNext()) {
                PathSegment.Type type = PathIterator.next$default(iterator, points, 0, 2, null);
                switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                    case 1:
                        $this$toSvg_u24lambda_u240.append(command(PathSegment.Type.Move, lastType) + points[0] + ' ' + points[1]);
                        lastType = type;
                        break;
                    case 2:
                        $this$toSvg_u24lambda_u240.append(command(PathSegment.Type.Line, lastType) + points[2] + ' ' + points[3]);
                        lastType = type;
                        break;
                    case 3:
                        $this$toSvg_u24lambda_u240.append(command(PathSegment.Type.Quadratic, lastType));
                        $this$toSvg_u24lambda_u240.append(new StringBuilder().append(points[2]).append(' ').append(points[3]).append(' ').append(points[4]).append(' ').append(points[5]).toString());
                        lastType = type;
                        break;
                    case 4:
                    case 7:
                        break;
                    case 5:
                        $this$toSvg_u24lambda_u240.append(command(PathSegment.Type.Cubic, lastType));
                        $this$toSvg_u24lambda_u240.append(new StringBuilder().append(points[2]).append(' ').append(points[3]).append(' ').toString());
                        $this$toSvg_u24lambda_u240.append(new StringBuilder().append(points[4]).append(' ').append(points[5]).append(' ').toString());
                        $this$toSvg_u24lambda_u240.append(new StringBuilder().append(points[6]).append(' ').append(points[7]).toString());
                        lastType = type;
                        break;
                    case 6:
                        $this$toSvg_u24lambda_u240.append(command(PathSegment.Type.Close, lastType));
                        lastType = type;
                        break;
                    default:
                        lastType = type;
                        break;
                }
            }
            if (asDocument) {
                StringBuilder append2 = $this$toSvg_u24lambda_u240.append("\"/>");
                Intrinsics.checkNotNullExpressionValue(append2, "append(value)");
                Intrinsics.checkNotNullExpressionValue(append2.append('\n'), "append('\\n')");
            }
        }
        if (asDocument) {
            StringBuilder append3 = $this$toSvg_u24lambda_u240.append("</svg>");
            Intrinsics.checkNotNullExpressionValue(append3, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append3.append('\n'), "append('\\n')");
        }
        String sb = $this$toSvg_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static /* synthetic */ String toSvg$default(Path path, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return toSvg(path, z);
    }

    private static final String command(PathSegment.Type type, PathSegment.Type lastType) {
        if (type != lastType) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    return "M";
                case 2:
                    return "L";
                case 3:
                    return "Q";
                case 4:
                default:
                    return "";
                case 5:
                    return "C";
                case 6:
                    return "Z";
            }
        }
        return " ";
    }
}
