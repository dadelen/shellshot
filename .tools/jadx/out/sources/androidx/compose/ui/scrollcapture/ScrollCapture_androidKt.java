package androidx.compose.ui.scrollcapture;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ScrollCapture.android.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00130\u0018H\u0002\u001a\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b*\u00020\u0002H\u0002\u001a!\u0010\u001c\u001a\u00020\u0013*\u00020\u00022\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0018H\u0082\b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0005\u0010\u0004\"E\u0010\b\u001a/\b\u0001\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\t*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"canScrollVertically", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "getCanScrollVertically", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "isVisible", "isVisible$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "scrollCaptureScrollByAction", "Lkotlin/Function2;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "offset", "Lkotlin/coroutines/Continuation;", "", "getScrollCaptureScrollByAction", "(Landroidx/compose/ui/semantics/SemanticsNode;)Lkotlin/jvm/functions/Function2;", "visitScrollCaptureCandidates", "", "fromNode", "depth", "", "onCandidate", "Lkotlin/Function1;", "Landroidx/compose/ui/scrollcapture/ScrollCaptureCandidate;", "getChildrenForSearch", "", "visitDescendants", "onNode", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ScrollCapture_androidKt {
    private static /* synthetic */ void isVisible$annotations(SemanticsNode semanticsNode) {
    }

    static /* synthetic */ void visitScrollCaptureCandidates$default(SemanticsNode semanticsNode, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        visitScrollCaptureCandidates(semanticsNode, i, function1);
    }

    private static final void visitScrollCaptureCandidates(SemanticsNode fromNode, int depth, Function1<? super ScrollCaptureCandidate, Unit> function1) {
        MutableVector nodes$iv = new MutableVector(new SemanticsNode[16], 0);
        List elements$iv$iv = getChildrenForSearch(fromNode);
        nodes$iv.addAll(nodes$iv.getSize(), elements$iv$iv);
        while (nodes$iv.isNotEmpty()) {
            boolean visitChildren$iv = true;
            SemanticsNode node$iv = (SemanticsNode) nodes$iv.removeAt(nodes$iv.getSize() - 1);
            if (!isVisible(node$iv) || node$iv.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getDisabled())) {
                visitChildren$iv = false;
            } else {
                NodeCoordinator findCoordinatorToGetBounds$ui_release = node$iv.findCoordinatorToGetBounds$ui_release();
                if (findCoordinatorToGetBounds$ui_release != null) {
                    LayoutCoordinates nodeCoordinates = findCoordinatorToGetBounds$ui_release.getCoordinates();
                    IntRect viewportBoundsInWindow = IntRectKt.roundToIntRect(LayoutCoordinatesKt.boundsInWindow(nodeCoordinates));
                    if (viewportBoundsInWindow.isEmpty()) {
                        visitChildren$iv = false;
                    } else if (getCanScrollVertically(node$iv)) {
                        int candidateDepth = depth + 1;
                        function1.invoke(new ScrollCaptureCandidate(node$iv, candidateDepth, viewportBoundsInWindow, nodeCoordinates));
                        visitScrollCaptureCandidates(node$iv, candidateDepth, function1);
                        visitChildren$iv = false;
                    }
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Expected semantics node to have a coordinator.");
                    throw new KotlinNothingValueException();
                }
            }
            if (visitChildren$iv) {
                List elements$iv$iv2 = getChildrenForSearch(node$iv);
                nodes$iv.addAll(nodes$iv.getSize(), elements$iv$iv2);
            }
        }
    }

    public static final Function2<Offset, Continuation<? super Offset>, Object> getScrollCaptureScrollByAction(SemanticsNode $this$scrollCaptureScrollByAction) {
        return (Function2) SemanticsConfigurationKt.getOrNull($this$scrollCaptureScrollByAction.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollByOffset());
    }

    private static final boolean isVisible(SemanticsNode $this$isVisible) {
        return ($this$isVisible.isTransparent$ui_release() || $this$isVisible.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser())) ? false : true;
    }

    private static final boolean getCanScrollVertically(SemanticsNode $this$canScrollVertically) {
        Function2 scrollByOffset = getScrollCaptureScrollByAction($this$canScrollVertically);
        ScrollAxisRange verticalScrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull($this$canScrollVertically.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        return (scrollByOffset == null || verticalScrollAxisRange == null || verticalScrollAxisRange.getMaxValue().invoke().floatValue() <= 0.0f) ? false : true;
    }

    private static final void visitDescendants(SemanticsNode $this$visitDescendants, Function1<? super SemanticsNode, Boolean> function1) {
        MutableVector nodes = new MutableVector(new SemanticsNode[16], 0);
        List elements$iv = getChildrenForSearch($this$visitDescendants);
        nodes.addAll(nodes.getSize(), elements$iv);
        while (nodes.isNotEmpty()) {
            SemanticsNode node = (SemanticsNode) nodes.removeAt(nodes.getSize() - 1);
            boolean visitChildren = function1.invoke(node).booleanValue();
            if (visitChildren) {
                List elements$iv2 = getChildrenForSearch(node);
                nodes.addAll(nodes.getSize(), elements$iv2);
            }
        }
    }

    private static final List<SemanticsNode> getChildrenForSearch(SemanticsNode $this$getChildrenForSearch) {
        return $this$getChildrenForSearch.getChildren$ui_release(false, false, false);
    }
}
