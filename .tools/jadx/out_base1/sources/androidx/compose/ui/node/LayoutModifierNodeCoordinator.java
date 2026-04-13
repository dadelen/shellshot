package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScopeImpl;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutModifierNodeCoordinator.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 G2\u00020\u0001:\u0002GHB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020$H\u0016J\u0010\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020$H\u0016J\u001a\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020$2\u0006\u0010*\u001a\u00020$H\u0016J\u0010\u00103\u001a\u00020$2\u0006\u0010,\u001a\u00020$H\u0016J\b\u00104\u001a\u00020(H\u0002J\u001a\u00105\u001a\u00020(2\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u000109H\u0016J=\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0019\u0010?\u001a\u0015\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020(\u0018\u00010@¢\u0006\u0002\bBH\u0014ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ*\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010E\u001a\u000209H\u0014ø\u0001\u0000¢\u0006\u0004\bC\u0010FR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@TX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006I"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "Landroidx/compose/ui/node/NodeCoordinator;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "measureNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/LayoutModifierNode;)V", "approachMeasureScope", "Landroidx/compose/ui/layout/ApproachMeasureScopeImpl;", "value", "layoutModifierNode", "getLayoutModifierNode", "()Landroidx/compose/ui/node/LayoutModifierNode;", "setLayoutModifierNode$ui_release", "(Landroidx/compose/ui/node/LayoutModifierNode;)V", "lookaheadConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLookaheadConstraints-DWUhwKw$ui_release", "()Landroidx/compose/ui/unit/Constraints;", "setLookaheadConstraints-_Sx5XlM$ui_release", "(Landroidx/compose/ui/unit/Constraints;)V", "<set-?>", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrappedNonNull", "getWrappedNonNull", "()Landroidx/compose/ui/node/NodeCoordinator;", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "ensureLookaheadDelegateCreated", "", "maxIntrinsicHeight", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "onAfterPlaceAt", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "placeAt", "position", "Landroidx/compose/ui/unit/IntOffset;", "zIndex", "", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "Companion", "LookaheadDelegateForLayoutModifierNode", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Paint modifierBoundsPaint;
    private ApproachMeasureScopeImpl approachMeasureScope;
    private LayoutModifierNode layoutModifierNode;
    private Constraints lookaheadConstraints;
    private LookaheadDelegate lookaheadDelegate;

    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode measureNode) {
        super(layoutNode);
        this.layoutModifierNode = measureNode;
        ApproachMeasureScopeImpl approachMeasureScopeImpl = null;
        this.lookaheadDelegate = layoutNode.getLookaheadRoot() != null ? new LookaheadDelegateForLayoutModifierNode() : null;
        Modifier.Node this_$iv = measureNode.getNode();
        if ((this_$iv.getKindSet() & NodeKind.m5726constructorimpl(512)) != 0) {
            Intrinsics.checkNotNull(measureNode, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
            approachMeasureScopeImpl = new ApproachMeasureScopeImpl(this, (ApproachLayoutModifierNode) measureNode);
        }
        this.approachMeasureScope = approachMeasureScopeImpl;
    }

    public final LayoutModifierNode getLayoutModifierNode() {
        return this.layoutModifierNode;
    }

    public final void setLayoutModifierNode$ui_release(LayoutModifierNode value) {
        if (!Intrinsics.areEqual(value, this.layoutModifierNode)) {
            Modifier.Node this_$iv = value.getNode();
            if ((this_$iv.getKindSet() & NodeKind.m5726constructorimpl(512)) != 0) {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
                ApproachMeasureScopeImpl it = this.approachMeasureScope;
                if (it != null) {
                    it.setApproachNode((ApproachLayoutModifierNode) value);
                } else {
                    it = new ApproachMeasureScopeImpl(this, (ApproachLayoutModifierNode) value);
                }
                this.approachMeasureScope = it;
            } else {
                this.approachMeasureScope = null;
            }
        }
        this.layoutModifierNode = value;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public Modifier.Node getTail() {
        return this.layoutModifierNode.getNode();
    }

    public final NodeCoordinator getWrappedNonNull() {
        NodeCoordinator wrapped$ui_release = getWrapped();
        Intrinsics.checkNotNull(wrapped$ui_release);
        return wrapped$ui_release;
    }

    /* renamed from: getLookaheadConstraints-DWUhwKw$ui_release, reason: not valid java name and from getter */
    public final Constraints getLookaheadConstraints() {
        return this.lookaheadConstraints;
    }

    /* renamed from: setLookaheadConstraints-_Sx5XlM$ui_release, reason: not valid java name */
    public final void m5638setLookaheadConstraints_Sx5XlM$ui_release(Constraints constraints) {
        this.lookaheadConstraints = constraints;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    protected void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    /* compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0016J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForLayoutModifierNode;", "Landroidx/compose/ui/node/LookaheadDelegate;", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;)V", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "maxIntrinsicHeight", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        public LookaheadDelegateForLayoutModifierNode() {
            super(LayoutModifierNodeCoordinator.this);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public Placeable mo5491measureBRTryo0(long constraints) {
            LookaheadDelegateForLayoutModifierNode this_$iv = this;
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            this_$iv.m5554setMeasurementConstraintsBRTryo0(constraints);
            layoutModifierNodeCoordinator.m5638setLookaheadConstraints_Sx5XlM$ui_release(Constraints.m6567boximpl(constraints));
            LayoutModifierNode $this$measure_BRTryo0_u24lambda_u241_u24lambda_u240 = layoutModifierNodeCoordinator.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            this_$iv.set_measureResult($this$measure_BRTryo0_u24lambda_u241_u24lambda_u240.mo84measure3p2s80s(this, lookaheadDelegate, constraints));
            return this_$iv;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public int calculateAlignmentLine(AlignmentLine alignmentLine) {
            int it;
            it = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(it));
            return it;
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            LayoutModifierNode $this$minIntrinsicWidth_u24lambda_u243 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$minIntrinsicWidth_u24lambda_u243.minIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            LayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u244 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$maxIntrinsicWidth_u24lambda_u244.maxIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            LayoutModifierNode $this$minIntrinsicHeight_u24lambda_u245 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$minIntrinsicHeight_u24lambda_u245.minIntrinsicHeight(this, lookaheadDelegate, width);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            LayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u246 = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return $this$maxIntrinsicHeight_u24lambda_u246.maxIntrinsicHeight(this, lookaheadDelegate, width);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void ensureLookaheadDelegateCreated() {
        if (getLookaheadDelegate() == null) {
            setLookaheadDelegate(new LookaheadDelegateForLayoutModifierNode());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d0, code lost:
    
        if (r12 != null) goto L42;
     */
    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.compose.ui.layout.Placeable mo5491measureBRTryo0(long r17) {
        /*
            r16 = this;
            r0 = r16
            boolean r1 = r0.getForceMeasureWithLookaheadConstraints()
            if (r1 == 0) goto L1f
            androidx.compose.ui.unit.Constraints r1 = r0.lookaheadConstraints
            if (r1 == 0) goto L11
            long r1 = r1.getValue()
            goto L21
        L11:
            r1 = 0
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Lookahead constraints cannot be null in approach pass."
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L1f:
            r1 = r17
        L21:
            r3 = r0
            androidx.compose.ui.node.NodeCoordinator r3 = (androidx.compose.ui.node.NodeCoordinator) r3
            r4 = 0
            androidx.compose.ui.node.NodeCoordinator.m5694access$setMeasurementConstraintsBRTryo0(r3, r1)
            r5 = 0
            androidx.compose.ui.layout.ApproachMeasureScopeImpl r6 = access$getApproachMeasureScope$p(r0)
            if (r6 == 0) goto Ld2
            r7 = 0
            androidx.compose.ui.layout.ApproachLayoutModifierNode r8 = r6.getApproachNode()
            r9 = 0
            long r10 = r6.mo5466getLookaheadSizeYbymL2g()
            boolean r10 = r8.mo97isMeasurementApproachInProgressozmzZPI(r10)
            r11 = 0
            r12 = 1
            if (r10 != 0) goto L50
            androidx.compose.ui.unit.Constraints r10 = r0.getLookaheadConstraints()
            boolean r10 = androidx.compose.ui.unit.Constraints.m6571equalsimpl(r1, r10)
            if (r10 != 0) goto L4e
            goto L50
        L4e:
            r10 = r11
            goto L51
        L50:
            r10 = r12
        L51:
            r6.setApproachMeasureRequired$ui_release(r10)
            boolean r10 = r6.getApproachMeasureRequired()
            if (r10 != 0) goto L61
            androidx.compose.ui.node.NodeCoordinator r10 = r0.getWrappedNonNull()
            r10.setForceMeasureWithLookaheadConstraints$ui_release(r12)
        L61:
            r10 = r6
            androidx.compose.ui.layout.ApproachMeasureScope r10 = (androidx.compose.ui.layout.ApproachMeasureScope) r10
            androidx.compose.ui.node.NodeCoordinator r13 = r0.getWrappedNonNull()
            androidx.compose.ui.layout.Measurable r13 = (androidx.compose.ui.layout.Measurable) r13
            androidx.compose.ui.layout.MeasureResult r10 = r8.mo96approachMeasure3p2s80s(r10, r13, r1)
            androidx.compose.ui.node.NodeCoordinator r13 = r0.getWrappedNonNull()
            r13.setForceMeasureWithLookaheadConstraints$ui_release(r11)
            int r13 = r10.getWidth()
            androidx.compose.ui.node.LookaheadDelegate r14 = r0.getLookaheadDelegate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            int r14 = r14.getWidth()
            if (r13 != r14) goto L99
            int r13 = r10.getHeight()
            androidx.compose.ui.node.LookaheadDelegate r14 = r0.getLookaheadDelegate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            int r14 = r14.getHeight()
            if (r13 != r14) goto L99
            r11 = r12
            goto L9a
        L99:
        L9a:
            boolean r12 = r6.getApproachMeasureRequired()
            if (r12 != 0) goto Lcd
            androidx.compose.ui.node.NodeCoordinator r12 = r0.getWrappedNonNull()
            long r12 = r12.mo5499getSizeYbymL2g()
            androidx.compose.ui.node.NodeCoordinator r14 = r0.getWrappedNonNull()
            androidx.compose.ui.node.LookaheadDelegate r14 = r14.getLookaheadDelegate()
            if (r14 == 0) goto Lbc
            long r14 = r14.m5673getSizeYbymL2g$ui_release()
            androidx.compose.ui.unit.IntSize r14 = androidx.compose.ui.unit.IntSize.m6788boximpl(r14)
            goto Lbd
        Lbc:
            r14 = 0
        Lbd:
            boolean r12 = androidx.compose.ui.unit.IntSize.m6793equalsimpl(r12, r14)
            if (r12 == 0) goto Lcd
            if (r11 != 0) goto Lcd
            androidx.compose.ui.node.LayoutModifierNodeCoordinator$measure$1$1$1$1 r12 = new androidx.compose.ui.node.LayoutModifierNodeCoordinator$measure$1$1$1$1
            r12.<init>(r0)
            androidx.compose.ui.layout.MeasureResult r12 = (androidx.compose.ui.layout.MeasureResult) r12
            goto Lce
        Lcd:
            r12 = r10
        Lce:
            if (r12 != 0) goto Le5
        Ld2:
            androidx.compose.ui.node.LayoutModifierNode r6 = r0.getLayoutModifierNode()
            r7 = 0
            r8 = r0
            androidx.compose.ui.layout.MeasureScope r8 = (androidx.compose.ui.layout.MeasureScope) r8
            androidx.compose.ui.node.NodeCoordinator r9 = r0.getWrappedNonNull()
            androidx.compose.ui.layout.Measurable r9 = (androidx.compose.ui.layout.Measurable) r9
            androidx.compose.ui.layout.MeasureResult r12 = r6.mo84measure3p2s80s(r8, r9, r1)
        Le5:
            r0.setMeasureResult$ui_release(r12)
            r5 = r0
            androidx.compose.ui.layout.Placeable r5 = (androidx.compose.ui.layout.Placeable) r5
            r0.onMeasured()
            r3 = r0
            androidx.compose.ui.layout.Placeable r3 = (androidx.compose.ui.layout.Placeable) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutModifierNodeCoordinator.mo5491measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        ApproachMeasureScopeImpl $this$minIntrinsicWidth_u24lambda_u247 = this.approachMeasureScope;
        if ($this$minIntrinsicWidth_u24lambda_u247 != null) {
            ApproachLayoutModifierNode $this$minIntrinsicWidth_u24lambda_u247_u24lambda_u246 = $this$minIntrinsicWidth_u24lambda_u247.getApproachNode();
            return $this$minIntrinsicWidth_u24lambda_u247_u24lambda_u246.minApproachIntrinsicWidth($this$minIntrinsicWidth_u24lambda_u247, getWrappedNonNull(), height);
        }
        LayoutModifierNode $this$minIntrinsicWidth_u24lambda_u248 = this.layoutModifierNode;
        return $this$minIntrinsicWidth_u24lambda_u248.minIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        ApproachMeasureScopeImpl $this$maxIntrinsicWidth_u24lambda_u2410 = this.approachMeasureScope;
        if ($this$maxIntrinsicWidth_u24lambda_u2410 != null) {
            ApproachLayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u2410_u24lambda_u249 = $this$maxIntrinsicWidth_u24lambda_u2410.getApproachNode();
            return $this$maxIntrinsicWidth_u24lambda_u2410_u24lambda_u249.maxApproachIntrinsicWidth($this$maxIntrinsicWidth_u24lambda_u2410, getWrappedNonNull(), height);
        }
        LayoutModifierNode $this$maxIntrinsicWidth_u24lambda_u2411 = this.layoutModifierNode;
        return $this$maxIntrinsicWidth_u24lambda_u2411.maxIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        ApproachMeasureScopeImpl $this$minIntrinsicHeight_u24lambda_u2413 = this.approachMeasureScope;
        if ($this$minIntrinsicHeight_u24lambda_u2413 != null) {
            ApproachLayoutModifierNode $this$minIntrinsicHeight_u24lambda_u2413_u24lambda_u2412 = $this$minIntrinsicHeight_u24lambda_u2413.getApproachNode();
            return $this$minIntrinsicHeight_u24lambda_u2413_u24lambda_u2412.minApproachIntrinsicHeight($this$minIntrinsicHeight_u24lambda_u2413, getWrappedNonNull(), width);
        }
        LayoutModifierNode $this$minIntrinsicHeight_u24lambda_u2414 = this.layoutModifierNode;
        return $this$minIntrinsicHeight_u24lambda_u2414.minIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        ApproachMeasureScopeImpl $this$maxIntrinsicHeight_u24lambda_u2416 = this.approachMeasureScope;
        if ($this$maxIntrinsicHeight_u24lambda_u2416 != null) {
            ApproachLayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u2416_u24lambda_u2415 = $this$maxIntrinsicHeight_u24lambda_u2416.getApproachNode();
            return $this$maxIntrinsicHeight_u24lambda_u2416_u24lambda_u2415.maxApproachIntrinsicHeight($this$maxIntrinsicHeight_u24lambda_u2416, getWrappedNonNull(), width);
        }
        LayoutModifierNode $this$maxIntrinsicHeight_u24lambda_u2417 = this.layoutModifierNode;
        return $this$maxIntrinsicHeight_u24lambda_u2417.maxIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    protected void mo5552placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) {
        super.mo5552placeAtf8xVGno(position, zIndex, layer);
        onAfterPlaceAt();
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    protected void mo5492placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        super.mo5492placeAtf8xVGno(position, zIndex, layerBlock);
        onAfterPlaceAt();
    }

    private final void onAfterPlaceAt() {
        boolean approachComplete;
        if (getIsShallowPlacing()) {
            return;
        }
        onPlaced();
        ApproachMeasureScopeImpl it = this.approachMeasureScope;
        if (it != null) {
            ApproachLayoutModifierNode $this$onAfterPlaceAt_u24lambda_u2420_u24lambda_u2419 = it.getApproachNode();
            Placeable.PlacementScope $this$onAfterPlaceAt_u24lambda_u2420_u24lambda_u2419_u24lambda_u2418 = getPlacementScope();
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (!$this$onAfterPlaceAt_u24lambda_u2420_u24lambda_u2419.isPlacementApproachInProgress($this$onAfterPlaceAt_u24lambda_u2420_u24lambda_u2419_u24lambda_u2418, lookaheadDelegate.getLookaheadLayoutCoordinates()) && !it.getApproachMeasureRequired()) {
                long j = mo5499getSizeYbymL2g();
                LookaheadDelegate lookaheadDelegate2 = getLookaheadDelegate();
                if (IntSize.m6793equalsimpl(j, lookaheadDelegate2 != null ? IntSize.m6788boximpl(lookaheadDelegate2.m5673getSizeYbymL2g$ui_release()) : null)) {
                    long mo5499getSizeYbymL2g = getWrappedNonNull().mo5499getSizeYbymL2g();
                    LookaheadDelegate lookaheadDelegate3 = getWrappedNonNull().getLookaheadDelegate();
                    if (IntSize.m6793equalsimpl(mo5499getSizeYbymL2g, lookaheadDelegate3 != null ? IntSize.m6788boximpl(lookaheadDelegate3.m5673getSizeYbymL2g$ui_release()) : null)) {
                        approachComplete = true;
                        getWrappedNonNull().setForcePlaceWithLookaheadOffset$ui_release(approachComplete);
                    }
                }
            }
            approachComplete = false;
            getWrappedNonNull().setForcePlaceWithLookaheadOffset$ui_release(approachComplete);
        }
        getMeasureResult$ui_release().placeChildren();
        getWrappedNonNull().setForcePlaceWithLookaheadOffset$ui_release(false);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public int calculateAlignmentLine(AlignmentLine alignmentLine) {
        int calculateAlignmentAndPlaceChildAsNeeded;
        LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
        if (lookaheadDelegate != null) {
            return lookaheadDelegate.getCachedAlignmentLine$ui_release(alignmentLine);
        }
        calculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
        return calculateAlignmentAndPlaceChildAsNeeded;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        getWrappedNonNull().draw(canvas, graphicsLayer);
        if (LayoutNodeKt.requireOwner(getLayoutNode()).getShowLayoutBounds()) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    /* compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$Companion;", "", "()V", "modifierBoundsPaint", "Landroidx/compose/ui/graphics/Paint;", "getModifierBoundsPaint", "()Landroidx/compose/ui/graphics/Paint;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Paint getModifierBoundsPaint() {
            return LayoutModifierNodeCoordinator.modifierBoundsPaint;
        }
    }

    static {
        Paint paint = AndroidPaint_androidKt.Paint();
        paint.mo4042setColor8_81llA(Color.INSTANCE.m4190getBlue0d7_KjU());
        paint.setStrokeWidth(1.0f);
        paint.mo4046setStylek9PVt8s(PaintingStyle.INSTANCE.m4430getStrokeTiuSbCo());
        modifierBoundsPaint = paint;
    }
}
