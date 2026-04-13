package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathParser.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ8\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bJ\u0011\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082\bJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\nJ\u0010\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathParser;", "", "()V", "nodeData", "", "nodes", "Ljava/util/ArrayList;", "Landroidx/compose/ui/graphics/vector/PathNode;", "Lkotlin/collections/ArrayList;", "addPathNodes", "", "clear", "", "parsePathString", "pathData", "", "pathStringToNodes", "resizeNodeData", "dataCount", "", "toNodes", "toPath", "Landroidx/compose/ui/graphics/Path;", "target", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PathParser {
    private float[] nodeData = new float[64];
    private ArrayList<PathNode> nodes;

    public final void clear() {
        ArrayList<PathNode> arrayList = this.nodes;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public final PathParser parsePathString(String pathData) {
        ArrayList dstNodes = this.nodes;
        if (dstNodes == null) {
            dstNodes = new ArrayList();
            this.nodes = dstNodes;
        } else {
            dstNodes.clear();
        }
        pathStringToNodes(pathData, dstNodes);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArrayList pathStringToNodes$default(PathParser pathParser, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 2) != 0) {
            arrayList = new ArrayList();
        }
        return pathParser.pathStringToNodes(str, arrayList);
    }

    public final ArrayList<PathNode> pathStringToNodes(String pathData, ArrayList<PathNode> nodes) {
        int i;
        int index;
        int start;
        int dataCount;
        int start2 = 0;
        int end = pathData.length();
        int dataCount2 = 0;
        while (true) {
            i = 32;
            if (start2 >= end || Intrinsics.compare((int) pathData.charAt(start2), 32) > 0) {
                break;
            }
            start2++;
        }
        while (end > start2 && Intrinsics.compare((int) pathData.charAt(end - 1), 32) <= 0) {
            end--;
        }
        int index2 = start2;
        while (index2 < end) {
            char command = 0;
            while (true) {
                index = index2 + 1;
                char c = pathData.charAt(index2);
                int lowerChar = c | ' ';
                if ((lowerChar - 97) * (lowerChar - 122) <= 0 && lowerChar != 101) {
                    command = c;
                    break;
                }
                if (index >= end) {
                    break;
                }
                index2 = index;
                i = 32;
            }
            if (command != 0) {
                if ((command | ' ') == 122) {
                    start = start2;
                } else {
                    int dataCount3 = 0;
                    while (true) {
                        if (index >= end || Intrinsics.compare((int) pathData.charAt(index), i) > 0) {
                            long result = FastFloatParserKt.nextFloat(pathData, index, end);
                            int index3 = (int) (result >>> i);
                            int dataCount4 = dataCount3;
                            int bits$iv$iv = (int) (result & 4294967295L);
                            float value = Float.intBitsToFloat(bits$iv$iv);
                            if (Float.isNaN(value)) {
                                start = start2;
                                dataCount = dataCount4;
                                index = index3;
                            } else {
                                dataCount = dataCount4 + 1;
                                this.nodeData[dataCount4] = value;
                                if (dataCount < this.nodeData.length) {
                                    start = start2;
                                } else {
                                    float[] src$iv = this.nodeData;
                                    this.nodeData = new float[dataCount * 2];
                                    start = start2;
                                    ArraysKt.copyInto(src$iv, this.nodeData, 0, 0, src$iv.length);
                                }
                                index = index3;
                            }
                            while (index < end && pathData.charAt(index) == ',') {
                                index++;
                            }
                            if (index >= end || Float.isNaN(value)) {
                                break;
                            }
                            dataCount3 = dataCount;
                            start2 = start;
                            i = 32;
                        } else {
                            index++;
                        }
                    }
                    dataCount2 = dataCount;
                }
                index2 = index;
                PathNodeKt.addPathNodes(command, nodes, this.nodeData, dataCount2);
                start2 = start;
                i = 32;
            } else {
                index2 = index;
                i = 32;
            }
        }
        return nodes;
    }

    private final void resizeNodeData(int dataCount) {
        if (dataCount >= this.nodeData.length) {
            float[] src = this.nodeData;
            this.nodeData = new float[dataCount * 2];
            ArraysKt.copyInto(src, this.nodeData, 0, 0, src.length);
        }
    }

    public final PathParser addPathNodes(List<? extends PathNode> nodes) {
        ArrayList dstNodes = this.nodes;
        if (dstNodes == null) {
            dstNodes = new ArrayList();
            this.nodes = dstNodes;
        }
        dstNodes.addAll(nodes);
        return this;
    }

    public final List<PathNode> toNodes() {
        ArrayList<PathNode> arrayList = this.nodes;
        return arrayList != null ? arrayList : CollectionsKt.emptyList();
    }

    public static /* synthetic */ Path toPath$default(PathParser pathParser, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return pathParser.toPath(path);
    }

    public final Path toPath(Path target) {
        Path path;
        ArrayList<PathNode> arrayList = this.nodes;
        return (arrayList == null || (path = PathParserKt.toPath(arrayList, target)) == null) ? AndroidPath_androidKt.Path() : path;
    }
}
