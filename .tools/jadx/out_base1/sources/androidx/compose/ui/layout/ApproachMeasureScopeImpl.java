package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.ViewCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApproachMeasureScope.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ`\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020/022\u0019\u00104\u001a\u0015\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000207\u0018\u000105¢\u0006\u0002\b82\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020705¢\u0006\u0002\b8H\u0016JH\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0014\b\u0002\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020/022\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020705¢\u0006\u0002\b8H\u0096\u0001J\u0017\u0010:\u001a\u00020/*\u00020;H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\u0017\u0010:\u001a\u00020/*\u00020>H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020;*\u00020>H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bB\u0010CJ\u001a\u0010A\u001a\u00020;*\u00020\u0016H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001a\u0010A\u001a\u00020;*\u00020/H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010FJ\u0017\u0010G\u001a\u00020H*\u00020IH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\f\u0010L\u001a\u00020(*\u00020(H\u0016J\u0017\u0010M\u001a\u00020\u0016*\u00020;H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bN\u0010EJ\u0017\u0010M\u001a\u00020\u0016*\u00020>H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010CJ\r\u0010P\u001a\u00020Q*\u00020RH\u0097\u0001J\u0017\u0010S\u001a\u00020I*\u00020HH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010KJ\u0017\u0010U\u001a\u00020>*\u00020;H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ\u001a\u0010U\u001a\u00020>*\u00020\u0016H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bX\u0010WJ\u001a\u0010U\u001a\u00020>*\u00020/H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bX\u0010YR\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\fR\u0012\u0010\u001c\u001a\u00020\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b&\u0010#R\u0018\u0010'\u001a\u00020(*\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Z"}, d2 = {"Landroidx/compose/ui/layout/ApproachMeasureScopeImpl;", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "coordinator", "Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "approachNode", "Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;Landroidx/compose/ui/layout/ApproachLayoutModifierNode;)V", "approachMeasureRequired", "", "getApproachMeasureRequired$ui_release", "()Z", "setApproachMeasureRequired$ui_release", "(Z)V", "getApproachNode", "()Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "setApproachNode", "(Landroidx/compose/ui/layout/ApproachLayoutModifierNode;)V", "getCoordinator", "()Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "isLookingAhead", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "lookaheadConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLookaheadConstraints-msEJaDk", "()J", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "getLookaheadSize-YbymL2g", "lookaheadScopeCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "layout", "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "rulers", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "placementBlock", "roundToPx", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toLookaheadCoordinates", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ApproachMeasureScopeImpl implements ApproachMeasureScope, MeasureScope, LookaheadScope {
    public static final int $stable = 0;
    private boolean approachMeasureRequired;
    private ApproachLayoutModifierNode approachNode;
    private final LayoutModifierNodeCoordinator coordinator;

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.coordinator.getLayoutDirection();
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        return this.coordinator.layout(width, height, alignmentLines, placementBlock);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx--R2X_6o */
    public int mo357roundToPxR2X_6o(long j) {
        return this.coordinator.mo357roundToPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public int mo358roundToPx0680j_4(float f) {
        return this.coordinator.mo358roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toDp-GaN1DYA */
    public float mo359toDpGaN1DYA(long j) {
        return this.coordinator.mo359toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo360toDpu2uoSUM(float f) {
        return this.coordinator.mo360toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo361toDpu2uoSUM(int i) {
        return this.coordinator.mo361toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDpSize-k-rfVVM */
    public long mo362toDpSizekrfVVM(long j) {
        return this.coordinator.mo362toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public float mo363toPxR2X_6o(long j) {
        return this.coordinator.mo363toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public float mo364toPx0680j_4(float f) {
        return this.coordinator.mo364toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        return this.coordinator.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public long mo365toSizeXkaWNTQ(long j) {
        return this.coordinator.mo365toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toSp-0xMU5do */
    public long mo366toSp0xMU5do(float f) {
        return this.coordinator.mo366toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo367toSpkPz2Gy4(float f) {
        return this.coordinator.mo367toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo368toSpkPz2Gy4(int i) {
        return this.coordinator.mo368toSpkPz2Gy4(i);
    }

    public ApproachMeasureScopeImpl(LayoutModifierNodeCoordinator coordinator, ApproachLayoutModifierNode approachNode) {
        this.coordinator = coordinator;
        this.approachNode = approachNode;
    }

    public final LayoutModifierNodeCoordinator getCoordinator() {
        return this.coordinator;
    }

    public final ApproachLayoutModifierNode getApproachNode() {
        return this.approachNode;
    }

    public final void setApproachNode(ApproachLayoutModifierNode approachLayoutModifierNode) {
        this.approachNode = approachLayoutModifierNode;
    }

    @Override // androidx.compose.ui.layout.ApproachIntrinsicMeasureScope
    /* renamed from: getLookaheadConstraints-msEJaDk */
    public long mo5465getLookaheadConstraintsmsEJaDk() {
        Constraints lookaheadConstraints = this.coordinator.getLookaheadConstraints();
        if (lookaheadConstraints != null) {
            return lookaheadConstraints.getValue();
        }
        throw new IllegalArgumentException("Error: Lookahead constraints requested before lookahead measure.".toString());
    }

    @Override // androidx.compose.ui.layout.ApproachIntrinsicMeasureScope
    /* renamed from: getLookaheadSize-YbymL2g */
    public long mo5466getLookaheadSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        MeasureResult it = lookaheadDelegate.getMeasureResult$ui_release();
        return IntSizeKt.IntSize(it.getWidth(), it.getHeight());
    }

    /* renamed from: getApproachMeasureRequired$ui_release, reason: from getter */
    public final boolean getApproachMeasureRequired() {
        return this.approachMeasureRequired;
    }

    public final void setApproachMeasureRequired$ui_release(boolean z) {
        this.approachMeasureRequired = z;
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates $this$toLookaheadCoordinates) {
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates;
        if ($this$toLookaheadCoordinates instanceof LookaheadLayoutCoordinates) {
            return $this$toLookaheadCoordinates;
        }
        if ($this$toLookaheadCoordinates instanceof NodeCoordinator) {
            LookaheadDelegate lookaheadDelegate = ((NodeCoordinator) $this$toLookaheadCoordinates).getLookaheadDelegate();
            return (lookaheadDelegate == null || (lookaheadLayoutCoordinates = lookaheadDelegate.getLookaheadLayoutCoordinates()) == null) ? $this$toLookaheadCoordinates : lookaheadLayoutCoordinates;
        }
        throw new IllegalArgumentException("Unsupported LayoutCoordinates: " + $this$toLookaheadCoordinates);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope $this$lookaheadScopeCoordinates) {
        NodeCoordinator outerCoordinator$ui_release;
        LayoutNode lookaheadRoot = this.coordinator.getLayoutNode().getLookaheadRoot();
        if (lookaheadRoot == null) {
            throw new IllegalArgumentException("Error: Requesting LookaheadScopeCoordinates is not permitted from outside of a LookaheadScope.".toString());
        }
        if (lookaheadRoot.getIsVirtualLookaheadRoot()) {
            LayoutNode parent$ui_release = lookaheadRoot.getParent$ui_release();
            if (parent$ui_release == null || (outerCoordinator$ui_release = parent$ui_release.getInnerCoordinator$ui_release()) == null) {
                outerCoordinator$ui_release = lookaheadRoot.getChildren$ui_release().get(0).getOuterCoordinator$ui_release();
            }
            return outerCoordinator$ui_release;
        }
        return lookaheadRoot.getOuterCoordinator$ui_release();
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super RulerScope, Unit> rulers, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        boolean value$iv$iv = (width & ViewCompat.MEASURED_STATE_MASK) == 0 && ((-16777216) & height) == 0;
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult(width, height, alignmentLines, rulers, placementBlock, this) { // from class: androidx.compose.ui.layout.ApproachMeasureScopeImpl$layout$1
            final /* synthetic */ Function1<Placeable.PlacementScope, Unit> $placementBlock;
            private final Map<AlignmentLine, Integer> alignmentLines;
            private final int height;
            private final Function1<RulerScope, Unit> rulers;
            final /* synthetic */ ApproachMeasureScopeImpl this$0;
            private final int width;

            public static /* synthetic */ void getAlignmentLines$annotations() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$placementBlock = placementBlock;
                this.this$0 = this;
                this.width = width;
                this.height = height;
                this.alignmentLines = alignmentLines;
                this.rulers = rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return this.rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                this.$placementBlock.invoke(this.this$0.getCoordinator().getPlacementScope());
            }
        };
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }
}
