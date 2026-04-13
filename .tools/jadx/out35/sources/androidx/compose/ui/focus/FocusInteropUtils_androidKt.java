package androidx.compose.ui.focus;

import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* compiled from: FocusInteropUtils.android.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a%\u0010\n\u001a\u00020\u000b*\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0002\u0010\u000f\u001a\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u0003*\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, d2 = {"toFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "androidDirection", "", "toLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "androidLayoutDirection", "calculateBoundingRect", "Landroidx/compose/ui/geometry/Rect;", "Landroid/view/View;", "requestInteropFocus", "", "direction", "rect", "Landroid/graphics/Rect;", "(Landroid/view/View;Ljava/lang/Integer;Landroid/graphics/Rect;)Z", "toAndroidFocusDirection", "toAndroidFocusDirection-3ESFkO8", "(I)Ljava/lang/Integer;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusInteropUtils_androidKt {
    public static final FocusDirection toFocusDirection(int androidDirection) {
        switch (androidDirection) {
            case 1:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3845getPreviousdhqQ8s());
            case 2:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3844getNextdhqQ8s());
            case 17:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3843getLeftdhqQ8s());
            case 33:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3847getUpdhqQ8s());
            case 66:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3846getRightdhqQ8s());
            case 130:
                return FocusDirection.m3831boximpl(FocusDirection.INSTANCE.m3840getDowndhqQ8s());
            default:
                return null;
        }
    }

    /* renamed from: toAndroidFocusDirection-3ESFkO8, reason: not valid java name */
    public static final Integer m3848toAndroidFocusDirection3ESFkO8(int $this$toAndroidFocusDirection_u2d3ESFkO8) {
        if (FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3847getUpdhqQ8s())) {
            return 33;
        }
        if (FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3840getDowndhqQ8s())) {
            return 130;
        }
        if (FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3843getLeftdhqQ8s())) {
            return 17;
        }
        if (FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3846getRightdhqQ8s())) {
            return 66;
        }
        if (FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3844getNextdhqQ8s())) {
            return 2;
        }
        return FocusDirection.m3834equalsimpl0($this$toAndroidFocusDirection_u2d3ESFkO8, FocusDirection.INSTANCE.m3845getPreviousdhqQ8s()) ? 1 : null;
    }

    public static final LayoutDirection toLayoutDirection(int androidLayoutDirection) {
        switch (androidLayoutDirection) {
            case 0:
                return LayoutDirection.Ltr;
            case 1:
                return LayoutDirection.Rtl;
            default:
                return null;
        }
    }

    public static final Rect calculateBoundingRect(View $this$calculateBoundingRect) {
        int[] focusedAndroidBounds = FocusInteropUtils.INSTANCE.getTempCoordinates();
        $this$calculateBoundingRect.getLocationInWindow(focusedAndroidBounds);
        return new Rect(focusedAndroidBounds[0], focusedAndroidBounds[1], focusedAndroidBounds[0] + $this$calculateBoundingRect.getWidth(), focusedAndroidBounds[1] + $this$calculateBoundingRect.getHeight());
    }

    public static final boolean requestInteropFocus(View $this$requestInteropFocus, Integer direction, android.graphics.Rect rect) {
        if (direction == null) {
            return $this$requestInteropFocus.requestFocus();
        }
        if (!($this$requestInteropFocus instanceof ViewGroup)) {
            return $this$requestInteropFocus.requestFocus(direction.intValue(), rect);
        }
        if (((ViewGroup) $this$requestInteropFocus).isFocused()) {
            return true;
        }
        if ((!((ViewGroup) $this$requestInteropFocus).isFocusable() || $this$requestInteropFocus.hasFocus()) && !($this$requestInteropFocus instanceof AndroidComposeView)) {
            if (rect != null) {
                View findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect((ViewGroup) $this$requestInteropFocus, rect, direction.intValue());
                return findNextFocusFromRect != null ? findNextFocusFromRect.requestFocus(direction.intValue(), rect) : $this$requestInteropFocus.requestFocus(direction.intValue(), rect);
            }
            View focusedView = $this$requestInteropFocus.hasFocus() ? $this$requestInteropFocus.findFocus() : null;
            View findNextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup) $this$requestInteropFocus, focusedView, direction.intValue());
            return findNextFocus != null ? findNextFocus.requestFocus(direction.intValue()) : $this$requestInteropFocus.requestFocus(direction.intValue());
        }
        return $this$requestInteropFocus.requestFocus(direction.intValue(), rect);
    }
}
