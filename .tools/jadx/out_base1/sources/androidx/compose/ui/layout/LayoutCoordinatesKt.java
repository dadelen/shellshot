package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;

/* compiled from: LayoutCoordinates.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0002\u001a\u000f\u0010\u0006\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\t\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\n\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\u000b\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b¨\u0006\f"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRootCoordinates", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "positionOnScreen", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates $this$positionInRoot) {
        return $this$positionInRoot.mo5502localToRootMKHz9U(Offset.INSTANCE.m3938getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates $this$positionInWindow) {
        return $this$positionInWindow.mo5504localToWindowMKHz9U(Offset.INSTANCE.m3938getZeroF1C5BW0());
    }

    public static final long positionOnScreen(LayoutCoordinates $this$positionOnScreen) {
        return $this$positionOnScreen.mo5503localToScreenMKHz9U(Offset.INSTANCE.m3938getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates $this$boundsInRoot) {
        return LayoutCoordinates.localBoundingBoxOf$default(findRootCoordinates($this$boundsInRoot), $this$boundsInRoot, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates $this$boundsInWindow) {
        LayoutCoordinates root = findRootCoordinates($this$boundsInWindow);
        float rootWidth = IntSize.m6796getWidthimpl(root.mo5499getSizeYbymL2g());
        float rootHeight = IntSize.m6795getHeightimpl(root.mo5499getSizeYbymL2g());
        Rect bounds = boundsInRoot($this$boundsInWindow);
        float $this$fastCoerceIn$iv = bounds.getLeft();
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > rootWidth) {
            $this$fastCoerceAtLeast$iv$iv = rootWidth;
        }
        float $this$fastCoerceIn$iv2 = bounds.getTop();
        float $this$fastCoerceAtLeast$iv$iv2 = $this$fastCoerceIn$iv2;
        if ($this$fastCoerceAtLeast$iv$iv2 < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv2 = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > rootHeight) {
            $this$fastCoerceAtLeast$iv$iv2 = rootHeight;
        }
        float $this$fastCoerceIn$iv3 = bounds.getRight();
        float $this$fastCoerceAtLeast$iv$iv3 = $this$fastCoerceIn$iv3;
        if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv3 > rootWidth) {
            $this$fastCoerceAtLeast$iv$iv3 = rootWidth;
        }
        float $this$fastCoerceIn$iv4 = bounds.getBottom();
        float $this$fastCoerceAtLeast$iv$iv4 = $this$fastCoerceIn$iv4;
        if ($this$fastCoerceAtLeast$iv$iv4 < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv4 = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv4 > rootHeight) {
            $this$fastCoerceAtLeast$iv$iv4 = rootHeight;
        }
        if (!($this$fastCoerceAtLeast$iv$iv == $this$fastCoerceAtLeast$iv$iv3)) {
            if (!($this$fastCoerceAtLeast$iv$iv2 == $this$fastCoerceAtLeast$iv$iv4)) {
                long topLeft = root.mo5504localToWindowMKHz9U(OffsetKt.Offset($this$fastCoerceAtLeast$iv$iv, $this$fastCoerceAtLeast$iv$iv2));
                long topRight = root.mo5504localToWindowMKHz9U(OffsetKt.Offset($this$fastCoerceAtLeast$iv$iv3, $this$fastCoerceAtLeast$iv$iv2));
                long bottomRight = root.mo5504localToWindowMKHz9U(OffsetKt.Offset($this$fastCoerceAtLeast$iv$iv3, $this$fastCoerceAtLeast$iv$iv4));
                long bottomLeft = root.mo5504localToWindowMKHz9U(OffsetKt.Offset($this$fastCoerceAtLeast$iv$iv, $this$fastCoerceAtLeast$iv$iv4));
                float topLeftX = Offset.m3922getXimpl(topLeft);
                float topRightX = Offset.m3922getXimpl(topRight);
                float bottomLeftX = Offset.m3922getXimpl(bottomLeft);
                float bottomRightX = Offset.m3922getXimpl(bottomRight);
                float left = Math.min(topLeftX, Math.min(topRightX, Math.min(bottomLeftX, bottomRightX)));
                float right = Math.max(topLeftX, Math.max(topRightX, Math.max(bottomLeftX, bottomRightX)));
                float topLeftY = Offset.m3923getYimpl(topLeft);
                float topRightY = Offset.m3923getYimpl(topRight);
                float bottomLeftY = Offset.m3923getYimpl(bottomLeft);
                float bottomRightY = Offset.m3923getYimpl(bottomRight);
                float top = Math.min(topLeftY, Math.min(topRightY, Math.min(bottomLeftY, bottomRightY)));
                float boundsLeft = Math.max(bottomLeftY, bottomRightY);
                float bottom = Math.max(topLeftY, Math.max(topRightY, boundsLeft));
                return new Rect(left, top, right, bottom);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public static final long positionInParent(LayoutCoordinates $this$positionInParent) {
        LayoutCoordinates parentLayoutCoordinates = $this$positionInParent.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo5500localPositionOfR5De75A($this$positionInParent, Offset.INSTANCE.m3938getZeroF1C5BW0()) : Offset.INSTANCE.m3938getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates $this$boundsInParent) {
        Rect localBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = $this$boundsInParent.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (localBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(parentLayoutCoordinates, $this$boundsInParent, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m6796getWidthimpl($this$boundsInParent.mo5499getSizeYbymL2g()), IntSize.m6795getHeightimpl($this$boundsInParent.mo5499getSizeYbymL2g())) : localBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates $this$findRootCoordinates) {
        LayoutCoordinates root = $this$findRootCoordinates;
        LayoutCoordinates parent = root.getParentLayoutCoordinates();
        while (parent != null) {
            root = parent;
            parent = root.getParentLayoutCoordinates();
        }
        NodeCoordinator rootCoordinator = root instanceof NodeCoordinator ? (NodeCoordinator) root : null;
        if (rootCoordinator == null) {
            return root;
        }
        for (NodeCoordinator parentCoordinator = rootCoordinator.getWrappedBy(); parentCoordinator != null; parentCoordinator = parentCoordinator.getWrappedBy()) {
            rootCoordinator = parentCoordinator;
        }
        return rootCoordinator;
    }
}
