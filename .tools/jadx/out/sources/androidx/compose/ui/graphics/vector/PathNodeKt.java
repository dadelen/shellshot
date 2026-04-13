package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.ui.graphics.vector.PathNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* compiled from: PathNode.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\bH\u0002\u001ai\u0010'\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b28\b\u0004\u0010)\u001a2\u0012\u0013\u0012\u00110%¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110\b¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020#0*H\u0082\b\u001a&\u0010/\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\bH\u0002\u001a4\u00100\u001a\u00020 *\u00020\u00012\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#01j\b\u0012\u0004\u0012\u00020#`22\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"ArcToKey", "", "CloseKey", "CurveToKey", "HorizontalToKey", "LineToKey", "MoveToKey", "NUM_ARC_TO_ARGS", "", "NUM_CURVE_TO_ARGS", "NUM_HORIZONTAL_TO_ARGS", "NUM_LINE_TO_ARGS", "NUM_MOVE_TO_ARGS", "NUM_QUAD_TO_ARGS", "NUM_REFLECTIVE_CURVE_TO_ARGS", "NUM_REFLECTIVE_QUAD_TO_ARGS", "NUM_VERTICAL_TO_ARGS", "QuadToKey", "ReflectiveCurveToKey", "ReflectiveQuadToKey", "RelativeArcToKey", "RelativeCloseKey", "RelativeCurveToKey", "RelativeHorizontalToKey", "RelativeLineToKey", "RelativeMoveToKey", "RelativeQuadToKey", "RelativeReflectiveCurveToKey", "RelativeReflectiveQuadToKey", "RelativeVerticalToKey", "VerticalToKey", "pathMoveNodeFromArgs", "", "nodes", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "args", "", "count", "pathNodesFromArgs", "numArgs", "nodeFor", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "subArray", "start", "pathRelativeMoveNodeFromArgs", "addPathNodes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PathNodeKt {
    private static final char ArcToKey = 'A';
    private static final char CloseKey = 'Z';
    private static final char CurveToKey = 'C';
    private static final char HorizontalToKey = 'H';
    private static final char LineToKey = 'L';
    private static final char MoveToKey = 'M';
    private static final int NUM_ARC_TO_ARGS = 7;
    private static final int NUM_CURVE_TO_ARGS = 6;
    private static final int NUM_HORIZONTAL_TO_ARGS = 1;
    private static final int NUM_LINE_TO_ARGS = 2;
    private static final int NUM_MOVE_TO_ARGS = 2;
    private static final int NUM_QUAD_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_CURVE_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_QUAD_TO_ARGS = 2;
    private static final int NUM_VERTICAL_TO_ARGS = 1;
    private static final char QuadToKey = 'Q';
    private static final char ReflectiveCurveToKey = 'S';
    private static final char ReflectiveQuadToKey = 'T';
    private static final char RelativeArcToKey = 'a';
    private static final char RelativeCloseKey = 'z';
    private static final char RelativeCurveToKey = 'c';
    private static final char RelativeHorizontalToKey = 'h';
    private static final char RelativeLineToKey = 'l';
    private static final char RelativeMoveToKey = 'm';
    private static final char RelativeQuadToKey = 'q';
    private static final char RelativeReflectiveCurveToKey = 's';
    private static final char RelativeReflectiveQuadToKey = 't';
    private static final char RelativeVerticalToKey = 'v';
    private static final char VerticalToKey = 'V';

    public static final void addPathNodes(char $this$addPathNodes, ArrayList<PathNode> arrayList, float[] args, int count) {
        ArrayList<PathNode> arrayList2 = arrayList;
        if (!($this$addPathNodes == 'z' || $this$addPathNodes == 'Z')) {
            if ($this$addPathNodes != 'm') {
                if ($this$addPathNodes != 'M') {
                    if ($this$addPathNodes != 'l') {
                        if ($this$addPathNodes != 'L') {
                            if ($this$addPathNodes != 'h') {
                                if ($this$addPathNodes != 'H') {
                                    if ($this$addPathNodes != 'v') {
                                        if ($this$addPathNodes != 'V') {
                                            if ($this$addPathNodes != 'c') {
                                                if ($this$addPathNodes != 'C') {
                                                    if ($this$addPathNodes != 's') {
                                                        if ($this$addPathNodes != 'S') {
                                                            if ($this$addPathNodes != 'q') {
                                                                if ($this$addPathNodes != 'Q') {
                                                                    if ($this$addPathNodes != 't') {
                                                                        if ($this$addPathNodes != 'T') {
                                                                            if ($this$addPathNodes != 'a') {
                                                                                if ($this$addPathNodes != 'A') {
                                                                                    throw new IllegalArgumentException("Unknown command for: " + $this$addPathNodes);
                                                                                }
                                                                                int end$iv = count - 7;
                                                                                for (int index$iv = 0; index$iv <= end$iv; index$iv += 7) {
                                                                                    int start = index$iv;
                                                                                    arrayList.add(new PathNode.ArcTo(args[start], args[start + 1], args[start + 2], Float.compare(args[start + 3], 0.0f) != 0, Float.compare(args[start + 4], 0.0f) != 0, args[start + 5], args[start + 6]));
                                                                                }
                                                                                return;
                                                                            }
                                                                            int end$iv2 = count - 7;
                                                                            for (int index$iv2 = 0; index$iv2 <= end$iv2; index$iv2 += 7) {
                                                                                int start2 = index$iv2;
                                                                                arrayList.add(new PathNode.RelativeArcTo(args[start2], args[start2 + 1], args[start2 + 2], Float.compare(args[start2 + 3], 0.0f) != 0, Float.compare(args[start2 + 4], 0.0f) != 0, args[start2 + 5], args[start2 + 6]));
                                                                            }
                                                                            return;
                                                                        }
                                                                        int end$iv3 = count - 2;
                                                                        for (int index$iv3 = 0; index$iv3 <= end$iv3; index$iv3 += 2) {
                                                                            int start3 = index$iv3;
                                                                            arrayList.add(new PathNode.ReflectiveQuadTo(args[start3], args[start3 + 1]));
                                                                        }
                                                                        return;
                                                                    }
                                                                    int end$iv4 = count - 2;
                                                                    for (int index$iv4 = 0; index$iv4 <= end$iv4; index$iv4 += 2) {
                                                                        int start4 = index$iv4;
                                                                        arrayList.add(new PathNode.RelativeReflectiveQuadTo(args[start4], args[start4 + 1]));
                                                                    }
                                                                    return;
                                                                }
                                                                int end$iv5 = count - 4;
                                                                for (int index$iv5 = 0; index$iv5 <= end$iv5; index$iv5 += 4) {
                                                                    int start5 = index$iv5;
                                                                    arrayList.add(new PathNode.QuadTo(args[start5], args[start5 + 1], args[start5 + 2], args[start5 + 3]));
                                                                }
                                                                return;
                                                            }
                                                            int end$iv6 = count - 4;
                                                            for (int index$iv6 = 0; index$iv6 <= end$iv6; index$iv6 += 4) {
                                                                int start6 = index$iv6;
                                                                arrayList.add(new PathNode.RelativeQuadTo(args[start6], args[start6 + 1], args[start6 + 2], args[start6 + 3]));
                                                            }
                                                            return;
                                                        }
                                                        int end$iv7 = count - 4;
                                                        for (int index$iv7 = 0; index$iv7 <= end$iv7; index$iv7 += 4) {
                                                            int start7 = index$iv7;
                                                            arrayList.add(new PathNode.ReflectiveCurveTo(args[start7], args[start7 + 1], args[start7 + 2], args[start7 + 3]));
                                                        }
                                                        return;
                                                    }
                                                    int end$iv8 = count - 4;
                                                    int index$iv8 = 0;
                                                    while (index$iv8 <= end$iv8) {
                                                        int start8 = index$iv8;
                                                        arrayList2.add(new PathNode.RelativeReflectiveCurveTo(args[start8], args[start8 + 1], args[start8 + 2], args[start8 + 3]));
                                                        index$iv8 += 4;
                                                        arrayList2 = arrayList;
                                                    }
                                                    return;
                                                }
                                                int end$iv9 = count - 6;
                                                for (int index$iv9 = 0; index$iv9 <= end$iv9; index$iv9 += 6) {
                                                    int start9 = index$iv9;
                                                    arrayList2.add(new PathNode.CurveTo(args[start9], args[start9 + 1], args[start9 + 2], args[start9 + 3], args[start9 + 4], args[start9 + 5]));
                                                }
                                                return;
                                            }
                                            int end$iv10 = count - 6;
                                            for (int index$iv10 = 0; index$iv10 <= end$iv10; index$iv10 += 6) {
                                                int start10 = index$iv10;
                                                arrayList2.add(new PathNode.RelativeCurveTo(args[start10], args[start10 + 1], args[start10 + 2], args[start10 + 3], args[start10 + 4], args[start10 + 5]));
                                            }
                                            return;
                                        }
                                        int end$iv11 = count - 1;
                                        for (int index$iv11 = 0; index$iv11 <= end$iv11; index$iv11++) {
                                            arrayList2.add(new PathNode.VerticalTo(args[index$iv11]));
                                        }
                                        return;
                                    }
                                    int end$iv12 = count - 1;
                                    for (int index$iv12 = 0; index$iv12 <= end$iv12; index$iv12++) {
                                        arrayList2.add(new PathNode.RelativeVerticalTo(args[index$iv12]));
                                    }
                                    return;
                                }
                                int end$iv13 = count - 1;
                                for (int index$iv13 = 0; index$iv13 <= end$iv13; index$iv13++) {
                                    arrayList2.add(new PathNode.HorizontalTo(args[index$iv13]));
                                }
                                return;
                            }
                            int end$iv14 = count - 1;
                            for (int index$iv14 = 0; index$iv14 <= end$iv14; index$iv14++) {
                                arrayList2.add(new PathNode.RelativeHorizontalTo(args[index$iv14]));
                            }
                            return;
                        }
                        int end$iv15 = count - 2;
                        for (int index$iv15 = 0; index$iv15 <= end$iv15; index$iv15 += 2) {
                            int start11 = index$iv15;
                            arrayList2.add(new PathNode.LineTo(args[start11], args[start11 + 1]));
                        }
                        return;
                    }
                    int end$iv16 = count - 2;
                    for (int index$iv16 = 0; index$iv16 <= end$iv16; index$iv16 += 2) {
                        int start12 = index$iv16;
                        arrayList2.add(new PathNode.RelativeLineTo(args[start12], args[start12 + 1]));
                    }
                    return;
                }
                pathMoveNodeFromArgs(arrayList2, args, count);
                return;
            }
            pathRelativeMoveNodeFromArgs(arrayList2, args, count);
            return;
        }
        arrayList2.add(PathNode.Close.INSTANCE);
    }

    private static final void pathNodesFromArgs(List<PathNode> list, float[] args, int count, int numArgs, Function2<? super float[], ? super Integer, ? extends PathNode> function2) {
        int end = count - numArgs;
        int index = 0;
        while (index <= end) {
            list.add(function2.invoke(args, Integer.valueOf(index)));
            index += numArgs;
        }
    }

    private static final void pathMoveNodeFromArgs(List<PathNode> list, float[] args, int count) {
        int end = count - 2;
        if (end >= 0) {
            list.add(new PathNode.MoveTo(args[0], args[1]));
            for (int index = 2; index <= end; index += 2) {
                list.add(new PathNode.LineTo(args[index], args[index + 1]));
            }
        }
    }

    private static final void pathRelativeMoveNodeFromArgs(List<PathNode> list, float[] args, int count) {
        int end = count - 2;
        if (end >= 0) {
            list.add(new PathNode.RelativeMoveTo(args[0], args[1]));
            for (int index = 2; index <= end; index += 2) {
                list.add(new PathNode.RelativeLineTo(args[index], args[index + 1]));
            }
        }
    }
}
