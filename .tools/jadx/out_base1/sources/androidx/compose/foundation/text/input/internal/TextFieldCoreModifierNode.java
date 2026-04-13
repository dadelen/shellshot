package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextHighlightType;
import androidx.compose.foundation.text.input.internal.selection.AndroidTextFieldMagnifier_androidKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNode;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: TextFieldCoreModifier.kt */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006BM\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\"\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\b\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020-H\u0002JN\u00102\u001a\u00020-2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\f\u00103\u001a\u00020-*\u000204H\u0016J\f\u00105\u001a\u00020-*\u000206H\u0016J\f\u00107\u001a\u00020-*\u000208H\u0002J(\u00109\u001a\u00020-*\u0002082\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u001f0;2\u0006\u0010=\u001a\u00020>H\u0002J&\u0010?\u001a\u00020-*\u0002082\u0006\u0010@\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020>H\u0002ø\u0001\u0000¢\u0006\u0004\bA\u0010BJ\u0014\u0010C\u001a\u00020-*\u0002082\u0006\u0010=\u001a\u00020>H\u0002J&\u0010D\u001a\u00020E*\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0016ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ&\u0010M\u001a\u00020E*\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010LJ&\u0010O\u001a\u00020E*\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0002ø\u0001\u0000¢\u0006\u0004\bP\u0010LJ6\u0010Q\u001a\u00020-*\u00020R2\u0006\u0010S\u001a\u00020!2\u0006\u0010T\u001a\u00020!2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020VH\u0002ø\u0001\u0000¢\u0006\u0004\bW\u0010XR\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldCoreModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "isFocused", "", "isDragHovered", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "writeable", "scrollState", "Landroidx/compose/foundation/ScrollState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(ZZLandroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;)V", "changeObserverJob", "Lkotlinx/coroutines/Job;", "cursorAnimation", "Landroidx/compose/foundation/text/input/internal/CursorAnimationState;", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "previousSelection", "Landroidx/compose/ui/text/TextRange;", "previousTextLayoutSize", "", "showCursor", "getShowCursor", "()Z", "textFieldMagnifierNode", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldMagnifierNode;", "calculateOffsetToFollow", "currSelection", "currTextLayoutSize", "calculateOffsetToFollow-72CqOWE", "(JI)I", "onAttach", "", "onGloballyPositioned", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "startCursorJob", "updateNode", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawCursor", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawHighlight", "highlight", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/input/TextHighlightType;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawSelection", "selection", "drawSelection-Sb-Bc2M", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/text/TextLayoutResult;)V", "drawText", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measureHorizontalScroll", "measureHorizontalScroll-3p2s80s", "measureVerticalScroll", "measureVerticalScroll-3p2s80s", "updateScrollState", "Landroidx/compose/ui/unit/Density;", "containerSize", "textLayoutSize", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "updateScrollState-tIlFzwE", "(Landroidx/compose/ui/unit/Density;IIJLandroidx/compose/ui/unit/LayoutDirection;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldCoreModifierNode extends DelegatingNode implements LayoutModifierNode, DrawModifierNode, CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private Job changeObserverJob;
    private Brush cursorBrush;
    private boolean isDragHovered;
    private boolean isFocused;
    private Orientation orientation;
    private TextRange previousSelection;
    private int previousTextLayoutSize;
    private ScrollState scrollState;
    private final TextFieldMagnifierNode textFieldMagnifierNode;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private boolean writeable;
    private final CursorAnimationState cursorAnimation = new CursorAnimationState();
    private Rect previousCursorRect = new Rect(-1.0f, -1.0f, -1.0f, -1.0f);

    public TextFieldCoreModifierNode(boolean isFocused, boolean isDragHovered, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation) {
        this.isFocused = isFocused;
        this.isDragHovered = isDragHovered;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode = (TextFieldMagnifierNode) delegate(AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode(this.textFieldState, this.textFieldSelectionState, this.textLayoutState, this.isFocused || this.isDragHovered));
    }

    private final boolean getShowCursor() {
        boolean isSpecified;
        if (this.writeable && (this.isFocused || this.isDragHovered)) {
            isSpecified = TextFieldCoreModifierKt.isSpecified(this.cursorBrush);
            if (isSpecified) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        if (this.isFocused && getShowCursor()) {
            startCursorJob();
        }
    }

    public final void updateNode(boolean isFocused, boolean isDragHovered, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation) {
        boolean previousShowCursor = getShowCursor();
        boolean wasFocused = this.isFocused;
        TransformedTextFieldState previousTextFieldState = this.textFieldState;
        TextLayoutState previousTextLayoutState = this.textLayoutState;
        TextFieldSelectionState previousTextFieldSelectionState = this.textFieldSelectionState;
        ScrollState previousScrollState = this.scrollState;
        this.isFocused = isFocused;
        this.isDragHovered = isDragHovered;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode.update(textFieldState, textFieldSelectionState, textLayoutState, isFocused || isDragHovered);
        if (!getShowCursor()) {
            Job job = this.changeObserverJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.changeObserverJob = null;
            this.cursorAnimation.cancelAndHide();
        } else if (!wasFocused || !Intrinsics.areEqual(previousTextFieldState, textFieldState) || !previousShowCursor) {
            startCursorJob();
        }
        if (!Intrinsics.areEqual(previousTextFieldState, textFieldState) || !Intrinsics.areEqual(previousTextLayoutState, textLayoutState) || !Intrinsics.areEqual(previousTextFieldSelectionState, textFieldSelectionState) || !Intrinsics.areEqual(previousScrollState, scrollState)) {
            LayoutModifierNodeKt.invalidateMeasurement(this);
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo84measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        if (this.orientation == Orientation.Vertical) {
            return m1184measureVerticalScroll3p2s80s($this$measure_u2d3p2s80s, measurable, constraints);
        }
        return m1183measureHorizontalScroll3p2s80s($this$measure_u2d3p2s80s, measurable, constraints);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        $this$draw.drawContent();
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        TextLayoutResult textLayoutResult = this.textLayoutState.getLayoutResult();
        if (textLayoutResult == null) {
            return;
        }
        Pair it = value.getHighlight();
        if (it != null) {
            drawHighlight($this$draw, it, textLayoutResult);
        }
        if (TextRange.m6086getCollapsedimpl(value.getSelection())) {
            drawText($this$draw, textLayoutResult);
            if (value.shouldShowSelection()) {
                drawCursor($this$draw);
            }
        } else {
            if (value.shouldShowSelection()) {
                m1182drawSelectionSbBc2M($this$draw, value.getSelection(), textLayoutResult);
            }
            drawText($this$draw, textLayoutResult);
        }
        TextFieldMagnifierNode $this$draw_u24lambda_u241 = this.textFieldMagnifierNode;
        $this$draw_u24lambda_u241.draw($this$draw);
    }

    /* renamed from: measureVerticalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1184measureVerticalScroll3p2s80s(final MeasureScope $this$measureVerticalScroll_u2d3p2s80s, Measurable measurable, long constraints) {
        long childConstraints;
        childConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : Integer.MAX_VALUE);
        final Placeable placeable = measurable.mo5491measureBRTryo0(childConstraints);
        final int height = Math.min(placeable.getHeight(), Constraints.m6578getMaxHeightimpl(constraints));
        return MeasureScope.layout$default($this$measureVerticalScroll_u2d3p2s80s, placeable.getWidth(), height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$measureVerticalScroll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                TransformedTextFieldState transformedTextFieldState;
                ScrollState scrollState;
                TextFieldCoreModifierNode textFieldCoreModifierNode = TextFieldCoreModifierNode.this;
                MeasureScope measureScope = $this$measureVerticalScroll_u2d3p2s80s;
                int i = height;
                int height2 = placeable.getHeight();
                transformedTextFieldState = TextFieldCoreModifierNode.this.textFieldState;
                textFieldCoreModifierNode.m1185updateScrollStatetIlFzwE(measureScope, i, height2, transformedTextFieldState.getVisualText().getSelection(), $this$measureVerticalScroll_u2d3p2s80s.getLayoutDirection());
                Placeable placeable2 = placeable;
                scrollState = TextFieldCoreModifierNode.this.scrollState;
                Placeable.PlacementScope.placeRelative$default($this$layout, placeable2, 0, -scrollState.getValue(), 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* renamed from: measureHorizontalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1183measureHorizontalScroll3p2s80s(final MeasureScope $this$measureHorizontalScroll_u2d3p2s80s, Measurable measurable, long constraints) {
        long constraints2;
        constraints2 = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : Integer.MAX_VALUE, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
        final Placeable placeable = measurable.mo5491measureBRTryo0(constraints2);
        final int width = Math.min(placeable.getWidth(), Constraints.m6579getMaxWidthimpl(constraints));
        return MeasureScope.layout$default($this$measureHorizontalScroll_u2d3p2s80s, width, placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$measureHorizontalScroll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                TransformedTextFieldState transformedTextFieldState;
                ScrollState scrollState;
                TextFieldCoreModifierNode textFieldCoreModifierNode = TextFieldCoreModifierNode.this;
                MeasureScope measureScope = $this$measureHorizontalScroll_u2d3p2s80s;
                int i = width;
                int width2 = placeable.getWidth();
                transformedTextFieldState = TextFieldCoreModifierNode.this.textFieldState;
                textFieldCoreModifierNode.m1185updateScrollStatetIlFzwE(measureScope, i, width2, transformedTextFieldState.getVisualText().getSelection(), $this$measureHorizontalScroll_u2d3p2s80s.getLayoutDirection());
                Placeable placeable2 = placeable;
                scrollState = TextFieldCoreModifierNode.this.scrollState;
                Placeable.PlacementScope.placeRelative$default($this$layout, placeable2, -scrollState.getValue(), 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* renamed from: calculateOffsetToFollow-72CqOWE, reason: not valid java name */
    private final int m1181calculateOffsetToFollow72CqOWE(long currSelection, int currTextLayoutSize) {
        TextRange textRange = this.previousSelection;
        if (!(textRange != null && TextRange.m6087getEndimpl(currSelection) == TextRange.m6087getEndimpl(textRange.getPackedValue()))) {
            return TextRange.m6087getEndimpl(currSelection);
        }
        TextRange textRange2 = this.previousSelection;
        if ((textRange2 != null && TextRange.m6092getStartimpl(currSelection) == TextRange.m6092getStartimpl(textRange2.getPackedValue())) && currTextLayoutSize == this.previousTextLayoutSize) {
            return -1;
        }
        return TextRange.m6092getStartimpl(currSelection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateScrollState-tIlFzwE, reason: not valid java name */
    public final void m1185updateScrollStatetIlFzwE(Density $this$updateScrollState_u2dtIlFzwE, int containerSize, int textLayoutSize, long currSelection, LayoutDirection layoutDirection) {
        TextLayoutResult layoutResult;
        Rect cursorRect;
        float offsetDifference;
        this.scrollState.setMaxValue$foundation_release(textLayoutSize - containerSize);
        int offsetToFollow = m1181calculateOffsetToFollow72CqOWE(currSelection, textLayoutSize);
        if (offsetToFollow < 0 || !getShowCursor() || (layoutResult = this.textLayoutState.getLayoutResult()) == null) {
            return;
        }
        Rect rawCursorRect = layoutResult.getCursorRect(RangesKt.coerceIn(offsetToFollow, (ClosedRange<Integer>) new IntRange(0, layoutResult.getLayoutInput().getText().length())));
        cursorRect = TextFieldCoreModifierKt.getCursorRectInScroller($this$updateScrollState_u2dtIlFzwE, rawCursorRect, layoutDirection == LayoutDirection.Rtl, textLayoutSize);
        if (cursorRect.getLeft() == this.previousCursorRect.getLeft()) {
            if ((cursorRect.getTop() == this.previousCursorRect.getTop()) && textLayoutSize == this.previousTextLayoutSize) {
                return;
            }
        }
        boolean vertical = this.orientation == Orientation.Vertical;
        float cursorStart = vertical ? cursorRect.getTop() : cursorRect.getLeft();
        float cursorEnd = vertical ? cursorRect.getBottom() : cursorRect.getRight();
        int startVisibleBound = this.scrollState.getValue();
        int difference = startVisibleBound + containerSize;
        if (cursorEnd > difference) {
            offsetDifference = cursorEnd - difference;
        } else if (cursorStart < startVisibleBound && cursorEnd - cursorStart > containerSize) {
            offsetDifference = cursorEnd - difference;
        } else if (cursorStart < startVisibleBound && cursorEnd - cursorStart <= containerSize) {
            offsetDifference = cursorStart - startVisibleBound;
        } else {
            offsetDifference = 0.0f;
        }
        this.previousSelection = TextRange.m6080boximpl(currSelection);
        this.previousCursorRect = cursorRect;
        this.previousTextLayoutSize = textLayoutSize;
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new TextFieldCoreModifierNode$updateScrollState$1(this, offsetDifference, rawCursorRect, null), 1, null);
    }

    /* renamed from: drawSelection-Sb-Bc2M, reason: not valid java name */
    private final void m1182drawSelectionSbBc2M(DrawScope $this$drawSelection_u2dSb_u2dBc2M, long selection, TextLayoutResult textLayoutResult) {
        int start = TextRange.m6090getMinimpl(selection);
        int end = TextRange.m6089getMaximpl(selection);
        if (start != end) {
            long selectionBackgroundColor = ((SelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())).getSelectionBackgroundColor();
            Path selectionPath = textLayoutResult.getPathForRange(start, end);
            DrawScope.m4703drawPathLG529CI$default($this$drawSelection_u2dSb_u2dBc2M, selectionPath, selectionBackgroundColor, 0.0f, null, null, 0, 60, null);
        }
    }

    private final void drawHighlight(DrawScope $this$drawHighlight, Pair<TextHighlightType, TextRange> pair, TextLayoutResult textLayoutResult) {
        long highlightBackgroundColor;
        int type = pair.component1().getValue();
        long range = pair.component2().getPackedValue();
        if (TextRange.m6086getCollapsedimpl(range)) {
            return;
        }
        Path highlightPath = textLayoutResult.getPathForRange(TextRange.m6090getMinimpl(range), TextRange.m6089getMaximpl(range));
        if (TextHighlightType.m1106equalsimpl0(type, TextHighlightType.INSTANCE.m1110getHandwritingDeletePreviewsxJuwY())) {
            Brush brush = textLayoutResult.getLayoutInput().getStyle().getBrush();
            if (brush != null) {
                DrawScope.m4702drawPathGBMwjPU$default($this$drawHighlight, highlightPath, brush, 0.2f, null, null, 0, 56, null);
                return;
            }
            long $this$takeOrElse_u2dDxMtmZc$iv = textLayoutResult.getLayoutInput().getStyle().m6122getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = Color.INSTANCE.m4189getBlack0d7_KjU();
            }
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            highlightBackgroundColor = Color.m4161copywmQWz5c(textColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(textColor) : Color.m4165getAlphaimpl(textColor) * 0.2f, (r12 & 2) != 0 ? Color.m4169getRedimpl(textColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(textColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(textColor) : 0.0f);
            DrawScope.m4703drawPathLG529CI$default($this$drawHighlight, highlightPath, highlightBackgroundColor, 0.0f, null, null, 0, 60, null);
            return;
        }
        long highlightBackgroundColor2 = ((SelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())).getSelectionBackgroundColor();
        DrawScope.m4703drawPathLG529CI$default($this$drawHighlight, highlightPath, highlightBackgroundColor2, 0.0f, null, null, 0, 60, null);
    }

    private final void drawText(DrawScope $this$drawText, TextLayoutResult textLayoutResult) {
        Canvas canvas = $this$drawText.getDrawContext().getCanvas();
        TextPainter.INSTANCE.paint(canvas, textLayoutResult);
    }

    private final void drawCursor(DrawScope $this$drawCursor) {
        float cursorAlphaValue = this.cursorAnimation.getCursorAlpha();
        if ((cursorAlphaValue == 0.0f) || !getShowCursor()) {
            return;
        }
        Rect cursorRect = this.textFieldSelectionState.getCursorRect();
        DrawScope.m4698drawLine1RTmtNc$default($this$drawCursor, this.cursorBrush, cursorRect.m3956getTopCenterF1C5BW0(), cursorRect.m3949getBottomCenterF1C5BW0(), cursorRect.getWidth(), 0, null, cursorAlphaValue, null, 0, 432, null);
    }

    private final void startCursorJob() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new TextFieldCoreModifierNode$startCursorJob$1(this, null), 3, null);
        this.changeObserverJob = launch$default;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setCoreNodeCoordinates(coordinates);
        this.textFieldMagnifierNode.onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        TextFieldMagnifierNode $this$applySemantics_u24lambda_u244 = this.textFieldMagnifierNode;
        $this$applySemantics_u24lambda_u244.applySemantics($this$applySemantics);
    }
}
