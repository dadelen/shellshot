package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ExposedDropdownMenu.android.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u00128\b\u0002\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\u0002\u0010\u0011J2\u0010%\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020(H\u0016ø\u0001\u0000¢\u0006\u0004\b,\u0010-R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000RA\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "density", "Landroidx/compose/ui/unit/Density;", "topWindowInsets", "", "keyboardSignalState", "Landroidx/compose/runtime/State;", "", "verticalMargin", "onPositionCalculated", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntRect;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "anchorBounds", "menuBounds", "(Landroidx/compose/ui/unit/Density;ILandroidx/compose/runtime/State;ILkotlin/jvm/functions/Function2;)V", "bottomToAnchorTop", "Landroidx/compose/material3/internal/MenuPosition$Vertical;", "bottomToWindowBottom", "getDensity", "()Landroidx/compose/ui/unit/Density;", "endToAnchorEnd", "Landroidx/compose/material3/internal/MenuPosition$Horizontal;", "getKeyboardSignalState", "()Landroidx/compose/runtime/State;", "leftToWindowLeft", "getOnPositionCalculated", "()Lkotlin/jvm/functions/Function2;", "rightToWindowRight", "startToAnchorStart", "topToAnchorBottom", "topToWindowTop", "getTopWindowInsets", "()I", "getVerticalMargin", "calculatePosition", "Landroidx/compose/ui/unit/IntOffset;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ExposedDropdownMenuPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final MenuPosition.Vertical bottomToAnchorTop;
    private final MenuPosition.Vertical bottomToWindowBottom;
    private final Density density;
    private final MenuPosition.Horizontal endToAnchorEnd;
    private final State<Unit> keyboardSignalState;
    private final MenuPosition.Horizontal leftToWindowLeft;
    private final Function2<IntRect, IntRect, Unit> onPositionCalculated;
    private final MenuPosition.Horizontal rightToWindowRight;
    private final MenuPosition.Horizontal startToAnchorStart;
    private final MenuPosition.Vertical topToAnchorBottom;
    private final MenuPosition.Vertical topToWindowTop;
    private final int topWindowInsets;
    private final int verticalMargin;

    /* JADX WARN: Multi-variable type inference failed */
    public ExposedDropdownMenuPositionProvider(Density density, int topWindowInsets, State<Unit> state, int verticalMargin, Function2<? super IntRect, ? super IntRect, Unit> function2) {
        this.density = density;
        this.topWindowInsets = topWindowInsets;
        this.keyboardSignalState = state;
        this.verticalMargin = verticalMargin;
        this.onPositionCalculated = function2;
        this.startToAnchorStart = MenuPosition.startToAnchorStart$default(MenuPosition.INSTANCE, 0, 1, null);
        this.endToAnchorEnd = MenuPosition.endToAnchorEnd$default(MenuPosition.INSTANCE, 0, 1, null);
        this.leftToWindowLeft = MenuPosition.leftToWindowLeft$default(MenuPosition.INSTANCE, 0, 1, null);
        this.rightToWindowRight = MenuPosition.rightToWindowRight$default(MenuPosition.INSTANCE, 0, 1, null);
        this.topToAnchorBottom = MenuPosition.topToAnchorBottom$default(MenuPosition.INSTANCE, 0, 1, null);
        this.bottomToAnchorTop = MenuPosition.bottomToAnchorTop$default(MenuPosition.INSTANCE, 0, 1, null);
        this.topToWindowTop = MenuPosition.INSTANCE.topToWindowTop(this.verticalMargin);
        this.bottomToWindowBottom = MenuPosition.INSTANCE.bottomToWindowBottom(this.verticalMargin);
    }

    public /* synthetic */ ExposedDropdownMenuPositionProvider(Density $this$_init__u24lambda_u240, int i, State state, int i2, Function2 function2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this($this$_init__u24lambda_u240, i, (i3 & 4) != 0 ? null : state, (i3 & 8) != 0 ? $this$_init__u24lambda_u240.mo358roundToPx0680j_4(MenuKt.getMenuVerticalMargin()) : i2, (i3 & 16) != 0 ? new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuPositionProvider.2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                invoke2(intRect, intRect2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IntRect intRect, IntRect intRect2) {
            }
        } : function2);
    }

    public final Density getDensity() {
        return this.density;
    }

    public final int getTopWindowInsets() {
        return this.topWindowInsets;
    }

    public final State<Unit> getKeyboardSignalState() {
        return this.keyboardSignalState;
    }

    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public final Function2<IntRect, IntRect, Unit> getOnPositionCalculated() {
        return this.onPositionCalculated;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4 */
    public long mo336calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        MenuPosition.Horizontal horizontal;
        MenuPosition.Vertical vertical;
        State<Unit> state = this.keyboardSignalState;
        if (state != null) {
            state.getValue();
        }
        long windowSize2 = IntSizeKt.IntSize(IntSize.m6796getWidthimpl(windowSize), IntSize.m6795getHeightimpl(windowSize) + this.topWindowInsets);
        MenuPosition.Horizontal[] horizontalArr = new MenuPosition.Horizontal[3];
        horizontalArr[0] = this.startToAnchorStart;
        horizontalArr[1] = this.endToAnchorEnd;
        if (IntOffset.m6754getXimpl(anchorBounds.m6777getCenternOccac()) < IntSize.m6796getWidthimpl(windowSize2) / 2) {
            horizontal = this.leftToWindowLeft;
        } else {
            horizontal = this.rightToWindowRight;
        }
        horizontalArr[2] = horizontal;
        List xCandidates = CollectionsKt.listOf((Object[]) horizontalArr);
        int x = 0;
        int size = xCandidates.size();
        for (int index = 0; index < size; index++) {
            int xCandidate = ((MenuPosition.Horizontal) xCandidates.get(index)).mo2894position95KtPRI(anchorBounds, windowSize2, IntSize.m6796getWidthimpl(popupContentSize), layoutDirection);
            if (index == CollectionsKt.getLastIndex(xCandidates) || (xCandidate >= 0 && IntSize.m6796getWidthimpl(popupContentSize) + xCandidate <= IntSize.m6796getWidthimpl(windowSize2))) {
                x = xCandidate;
                break;
            }
        }
        MenuPosition.Vertical[] verticalArr = new MenuPosition.Vertical[3];
        verticalArr[0] = this.topToAnchorBottom;
        verticalArr[1] = this.bottomToAnchorTop;
        if (IntOffset.m6755getYimpl(anchorBounds.m6777getCenternOccac()) < IntSize.m6795getHeightimpl(windowSize2) / 2) {
            vertical = this.topToWindowTop;
        } else {
            vertical = this.bottomToWindowBottom;
        }
        verticalArr[2] = vertical;
        List yCandidates = CollectionsKt.listOf((Object[]) verticalArr);
        int y = 0;
        int size2 = yCandidates.size();
        for (int index2 = 0; index2 < size2; index2++) {
            int yCandidate = ((MenuPosition.Vertical) yCandidates.get(index2)).mo2895positionJVtK1S4(anchorBounds, windowSize2, IntSize.m6795getHeightimpl(popupContentSize));
            if (index2 == CollectionsKt.getLastIndex(yCandidates) || (yCandidate >= 0 && IntSize.m6795getHeightimpl(popupContentSize) + yCandidate <= IntSize.m6795getHeightimpl(windowSize2))) {
                y = yCandidate;
                break;
            }
        }
        long menuOffset = IntOffsetKt.IntOffset(x, y);
        this.onPositionCalculated.invoke(anchorBounds, IntRectKt.m6786IntRectVbeCjmY(menuOffset, popupContentSize));
        return menuOffset;
    }
}
