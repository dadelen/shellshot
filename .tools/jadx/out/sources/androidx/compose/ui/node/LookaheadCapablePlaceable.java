package androidx.compose.ui.node;

import androidx.collection.MutableObjectFloatMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectFloatMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.Ruler;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.layout.VerticalRuler;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LookaheadDelegate.kt */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000 e2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001eB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010?\u001a\u00020@2\u0006\u0010!\u001a\u00020\"2\u0006\u0010A\u001a\u000205H\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH&J\u0017\u0010F\u001a\u00020@2\b\u0010G\u001a\u0004\u0018\u00010&H\u0000¢\u0006\u0002\bHJ\u0010\u0010F\u001a\u00020@2\u0006\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020\u00002\u0006\u0010A\u001a\u000205H\u0002J\u0016\u0010L\u001a\u00020M2\u0006\u0010A\u001a\u0002052\u0006\u0010N\u001a\u00020MJ\u0011\u0010O\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0086\u0002J\u0010\u0010P\u001a\u00020@2\u0006\u0010A\u001a\u000205H\u0002J`\u0010Q\u001a\u00020&2\u0006\u0010R\u001a\u00020C2\u0006\u0010S\u001a\u00020C2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020C0U2\u0019\u0010V\u001a\u0015\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020@\u0018\u00010W¢\u0006\u0002\bX2\u0017\u0010Y\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020@0W¢\u0006\u0002\bXH\u0016J&\u0010Z\u001a\u00020@2\u001c\u0010[\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\"07j\b\u0012\u0004\u0012\u00020\"`806H\u0002J\u0016\u0010\\\u001a\u00020@2\u0006\u0010A\u001a\u0002052\u0006\u0010]\u001a\u00020MJ\u0016\u0010^\u001a\u00020@2\u0006\u0010A\u001a\u0002052\u0006\u0010]\u001a\u00020MJ\r\u0010_\u001a\u00020@H ¢\u0006\u0002\b`J\f\u0010a\u001a\u00020@*\u00020bH\u0004J\u0014\u0010c\u001a\u00020\u0013*\u00020\"2\u0006\u0010d\u001a\u00020\"H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u001aR\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X \u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\rR\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0018\u0010/\u001a\u000200X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b1\u00102R2\u00103\u001a&\u0012\u0004\u0012\u000205\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\"07j\b\u0012\u0004\u0012\u00020\"`806\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00109\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0016\u0010<\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010>\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006f"}, d2 = {"Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/node/MeasureScopeWithLayoutNode;", "Landroidx/compose/ui/node/MotionReferencePlacementDelegate;", "()V", "_rulerScope", "Landroidx/compose/ui/layout/RulerScope;", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "getChild", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "hasMeasureResult", "", "getHasMeasureResult", "()Z", "isLookingAhead", "isLookingAhead$annotations", "isPlacedUnderMotionFrameOfReference", "setPlacedUnderMotionFrameOfReference", "(Z)V", "isPlacingForAlignment", "isPlacingForAlignment$ui_release", "setPlacingForAlignment$ui_release", "isShallowPlacing", "isShallowPlacing$ui_release", "setShallowPlacing$ui_release", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "getMeasureResult$ui_release", "()Landroidx/compose/ui/layout/MeasureResult;", "parent", "getParent", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "position", "Landroidx/compose/ui/unit/IntOffset;", "getPosition-nOcc-ac", "()J", "rulerReaders", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/layout/Ruler;", "Landroidx/collection/MutableScatterSet;", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/ui/node/WeakReference;", "rulerScope", "getRulerScope", "()Landroidx/compose/ui/layout/RulerScope;", "rulerValues", "Landroidx/collection/MutableObjectFloatMap;", "rulerValuesCache", "addRulerReader", "", "ruler", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "captureRulers", "result", "captureRulers$ui_release", "placeableResult", "Landroidx/compose/ui/node/PlaceableResult;", "findAncestorRulerDefiner", "findRulerValue", "", "defaultValue", "get", "invalidateChildrenOfDefiningRuler", "layout", "width", "height", "alignmentLines", "", "rulers", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "placementBlock", "notifyRulerValueChange", "layoutNodes", "provideRelativeRulerValue", "value", "provideRulerValue", "replace", "replace$ui_release", "invalidateAlignmentLinesFromPositionChange", "Landroidx/compose/ui/node/NodeCoordinator;", "isLayoutNodeAncestor", "ancestor", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public abstract class LookaheadCapablePlaceable extends Placeable implements MeasureScopeWithLayoutNode, MotionReferencePlacementDelegate {
    public static final int $stable = 0;
    private static final Function1<PlaceableResult, Unit> onCommitAffectingRuler = new Function1<PlaceableResult, Unit>() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$Companion$onCommitAffectingRuler$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PlaceableResult placeableResult) {
            invoke2(placeableResult);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PlaceableResult result) {
            if (result.isValidOwnerScope()) {
                result.getPlaceable().captureRulers(result);
            }
        }
    };
    private RulerScope _rulerScope;
    private boolean isPlacedUnderMotionFrameOfReference;
    private boolean isPlacingForAlignment;
    private boolean isShallowPlacing;
    private final Placeable.PlacementScope placementScope = PlaceableKt.PlacementScope(this);
    private MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> rulerReaders;
    private MutableObjectFloatMap<Ruler> rulerValues;
    private MutableObjectFloatMap<Ruler> rulerValuesCache;

    public static /* synthetic */ void isLookingAhead$annotations() {
    }

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    public abstract AlignmentLinesOwner getAlignmentLinesOwner();

    public abstract LookaheadCapablePlaceable getChild();

    public abstract LayoutCoordinates getCoordinates();

    public abstract boolean getHasMeasureResult();

    @Override // androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public abstract LayoutNode getLayoutNode();

    public abstract MeasureResult getMeasureResult$ui_release();

    public abstract LookaheadCapablePlaceable getParent();

    /* renamed from: getPosition-nOcc-ac, reason: not valid java name */
    public abstract long getPosition();

    public abstract void replace$ui_release();

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    /* renamed from: isPlacedUnderMotionFrameOfReference, reason: from getter */
    public boolean getIsPlacedUnderMotionFrameOfReference() {
        return this.isPlacedUnderMotionFrameOfReference;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }

    public final RulerScope getRulerScope() {
        RulerScope rulerScope = this._rulerScope;
        return rulerScope == null ? new RulerScope() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$rulerScope$1
            @Override // androidx.compose.ui.layout.RulerScope
            public LayoutCoordinates getCoordinates() {
                LookaheadCapablePlaceable.this.getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
                return LookaheadCapablePlaceable.this.getCoordinates();
            }

            @Override // androidx.compose.ui.layout.RulerScope
            public void provides(Ruler $this$provides, float value) {
                LookaheadCapablePlaceable.this.provideRulerValue($this$provides, value);
            }

            @Override // androidx.compose.ui.layout.RulerScope
            public void providesRelative(VerticalRuler $this$providesRelative, float value) {
                LookaheadCapablePlaceable.this.provideRelativeRulerValue($this$providesRelative, value);
            }

            @Override // androidx.compose.ui.unit.Density
            public float getDensity() {
                return LookaheadCapablePlaceable.this.getDensity();
            }

            @Override // androidx.compose.ui.unit.FontScaling
            public float getFontScale() {
                return LookaheadCapablePlaceable.this.getFontScale();
            }
        } : rulerScope;
    }

    @Override // androidx.compose.ui.layout.Measured
    public final int get(AlignmentLine alignmentLine) {
        int measuredPosition;
        int m6755getYimpl;
        if (!getHasMeasureResult() || (measuredPosition = calculateAlignmentLine(alignmentLine)) == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (alignmentLine instanceof VerticalAlignmentLine) {
            m6755getYimpl = IntOffset.m6754getXimpl(getApparentToRealOffset());
        } else {
            m6755getYimpl = IntOffset.m6755getYimpl(getApparentToRealOffset());
        }
        return m6755getYimpl + measuredPosition;
    }

    /* renamed from: isShallowPlacing$ui_release, reason: from getter */
    public final boolean getIsShallowPlacing() {
        return this.isShallowPlacing;
    }

    public final void setShallowPlacing$ui_release(boolean z) {
        this.isShallowPlacing = z;
    }

    /* renamed from: isPlacingForAlignment$ui_release, reason: from getter */
    public final boolean getIsPlacingForAlignment() {
        return this.isPlacingForAlignment;
    }

    public final void setPlacingForAlignment$ui_release(boolean z) {
        this.isPlacingForAlignment = z;
    }

    public final Placeable.PlacementScope getPlacementScope() {
        return this.placementScope;
    }

    protected final void invalidateAlignmentLinesFromPositionChange(NodeCoordinator $this$invalidateAlignmentLinesFromPositionChange) {
        AlignmentLines alignmentLines;
        NodeCoordinator wrapped = $this$invalidateAlignmentLinesFromPositionChange.getWrapped();
        if (!Intrinsics.areEqual(wrapped != null ? wrapped.getLayoutNode() : null, $this$invalidateAlignmentLinesFromPositionChange.getLayoutNode())) {
            $this$invalidateAlignmentLinesFromPositionChange.getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            return;
        }
        AlignmentLinesOwner parentAlignmentLinesOwner = $this$invalidateAlignmentLinesFromPositionChange.getAlignmentLinesOwner().getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null || (alignmentLines = parentAlignmentLinesOwner.getAlignmentLines()) == null) {
            return;
        }
        alignmentLines.onAlignmentsChanged();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }

    public final float findRulerValue(Ruler ruler, float defaultValue) {
        if (this.isPlacingForAlignment) {
            return defaultValue;
        }
        LookaheadCapablePlaceable p = this;
        while (true) {
            MutableObjectFloatMap<Ruler> mutableObjectFloatMap = p.rulerValues;
            float rulerValue = mutableObjectFloatMap != null ? mutableObjectFloatMap.getOrDefault(ruler, Float.NaN) : Float.NaN;
            if (!Float.isNaN(rulerValue)) {
                p.addRulerReader(getLayoutNode(), ruler);
                return ruler.calculateCoordinate$ui_release(rulerValue, p.getCoordinates(), getCoordinates());
            }
            LookaheadCapablePlaceable parent = p.getParent();
            if (parent == null) {
                p.addRulerReader(getLayoutNode(), ruler);
                return defaultValue;
            }
            p = parent;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addRulerReader(androidx.compose.ui.node.LayoutNode r48, androidx.compose.ui.layout.Ruler r49) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LookaheadCapablePlaceable.addRulerReader(androidx.compose.ui.node.LayoutNode, androidx.compose.ui.layout.Ruler):void");
    }

    private final LookaheadCapablePlaceable findAncestorRulerDefiner(Ruler ruler) {
        LookaheadCapablePlaceable p = this;
        while (true) {
            MutableObjectFloatMap<Ruler> mutableObjectFloatMap = p.rulerValues;
            boolean z = false;
            if (mutableObjectFloatMap != null && mutableObjectFloatMap.contains(ruler)) {
                z = true;
            }
            if (z) {
                return p;
            }
            LookaheadCapablePlaceable parent = p.getParent();
            if (parent == null) {
                return p;
            }
            p = parent;
        }
    }

    private final boolean isLayoutNodeAncestor(LayoutNode $this$isLayoutNodeAncestor, LayoutNode ancestor) {
        if ($this$isLayoutNodeAncestor == ancestor) {
            return true;
        }
        LayoutNode parent$ui_release = $this$isLayoutNodeAncestor.getParent$ui_release();
        if (parent$ui_release != null) {
            return isLayoutNodeAncestor(parent$ui_release, ancestor);
        }
        return false;
    }

    private final void invalidateChildrenOfDefiningRuler(Ruler ruler) {
        LookaheadCapablePlaceable definer = findAncestorRulerDefiner(ruler);
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = definer.rulerReaders;
        MutableScatterSet readers = mutableScatterMap != null ? mutableScatterMap.remove(ruler) : null;
        if (readers != null) {
            notifyRulerValueChange(readers);
        }
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final Function1<? super RulerScope, Unit> rulers, final Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        boolean value$iv$iv = (width & ViewCompat.MEASURED_STATE_MASK) == 0 && ((-16777216) & height) == 0;
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$layout$1
            @Override // androidx.compose.ui.layout.MeasureResult
            /* renamed from: getWidth, reason: from getter */
            public int get$width() {
                return width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* renamed from: getHeight, reason: from getter */
            public int get$height() {
                return height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                placementBlock.invoke(this.getPlacementScope());
            }
        };
    }

    public final void captureRulers$ui_release(MeasureResult result) {
        int i;
        if (result != null) {
            captureRulers(new PlaceableResult(result, this));
            return;
        }
        ScatterMap scatterMap = this.rulerReaders;
        if (scatterMap != null) {
            ScatterMap this_$iv = scatterMap;
            Object[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    ScatterMap this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i2;
                                MutableScatterSet it = (MutableScatterSet) v$iv[index$iv$iv];
                                notifyRulerValueChange(it);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                        }
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv = this_$iv2;
                }
            }
        }
        MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> mutableScatterMap = this.rulerReaders;
        if (mutableScatterMap != null) {
            mutableScatterMap.clear();
        }
        MutableObjectFloatMap<Ruler> mutableObjectFloatMap = this.rulerValues;
        if (mutableObjectFloatMap != null) {
            mutableObjectFloatMap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void captureRulers(final PlaceableResult placeableResult) {
        int $i$f$forEachKey;
        int j$iv$iv;
        int $i$f$forEachKey2;
        int j$iv$iv2;
        MutableScatterSet<WeakReference<LayoutNode>> readers;
        OwnerSnapshotObserver snapshotObserver;
        if (this.isPlacingForAlignment) {
            return;
        }
        Function1 rulerLambda = placeableResult.getResult().getRulers();
        MutableScatterMap rulerReaders = this.rulerReaders;
        int i = 8;
        if (rulerLambda != null) {
            MutableObjectFloatMap it = this.rulerValuesCache;
            if (it == null) {
                it = new MutableObjectFloatMap(0, 1, null);
                this.rulerValuesCache = it;
            }
            MutableObjectFloatMap it2 = this.rulerValues;
            if (it2 == null) {
                it2 = new MutableObjectFloatMap(0, 1, null);
                this.rulerValues = it2;
            }
            it.putAll(it2);
            it2.clear();
            Owner owner = getLayoutNode().getOwner();
            if (owner != null && (snapshotObserver = owner.getSnapshotObserver()) != null) {
                snapshotObserver.observeReads$ui_release(placeableResult, onCommitAffectingRuler, new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$captureRulers$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Function1<RulerScope, Unit> rulers = PlaceableResult.this.getResult().getRulers();
                        if (rulers != null) {
                            rulers.invoke(this.getRulerScope());
                        }
                    }
                });
            }
            if (rulerReaders != null) {
                ObjectFloatMap this_$iv = it;
                Object[] k$iv = this_$iv.keys;
                float[] v$iv = this_$iv.values;
                ObjectFloatMap this_$iv$iv = this_$iv;
                long[] m$iv$iv = this_$iv$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        int i2 = i;
                        ObjectFloatMap this_$iv$iv2 = this_$iv$iv;
                        Function1 rulerLambda2 = rulerLambda;
                        if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            int j$iv$iv3 = 0;
                            while (j$iv$iv3 < bitCount$iv$iv) {
                                long value$iv$iv$iv = slot$iv$iv & 255;
                                int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull == 0) {
                                    j$iv$iv2 = j$iv$iv3;
                                } else {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv3;
                                    Object obj = k$iv[index$iv$iv];
                                    float oldValue = v$iv[index$iv$iv];
                                    Ruler ruler = (Ruler) obj;
                                    j$iv$iv2 = j$iv$iv3;
                                    float newValue = it2.getOrDefault(ruler, Float.NaN);
                                    if (!(newValue == oldValue) && (readers = rulerReaders.remove(ruler)) != null) {
                                        notifyRulerValueChange(readers);
                                    }
                                }
                                slot$iv$iv >>= i2;
                                j$iv$iv3 = j$iv$iv2 + 1;
                            }
                            if (bitCount$iv$iv != i2) {
                                break;
                            }
                        }
                        if (i$iv$iv == lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        rulerLambda = rulerLambda2;
                        this_$iv$iv = this_$iv$iv2;
                        i = 8;
                    }
                }
            }
            ObjectFloatMap this_$iv2 = it2;
            int $i$f$forEachKey3 = 0;
            Object[] k$iv2 = this_$iv2.keys;
            long[] m$iv$iv2 = this_$iv2.metadata;
            int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
            int i$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv2) {
                while (true) {
                    long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                    ObjectFloatMap this_$iv3 = this_$iv2;
                    if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                        $i$f$forEachKey = $i$f$forEachKey3;
                    } else {
                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                        int j$iv$iv4 = 0;
                        while (j$iv$iv4 < bitCount$iv$iv2) {
                            long value$iv$iv$iv2 = slot$iv$iv2 & 255;
                            if (!(value$iv$iv$iv2 < 128)) {
                                j$iv$iv = j$iv$iv4;
                                $i$f$forEachKey2 = $i$f$forEachKey3;
                            } else {
                                j$iv$iv = j$iv$iv4;
                                Ruler ruler2 = (Ruler) k$iv2[(i$iv$iv2 << 3) + j$iv$iv4];
                                if (it.contains(ruler2)) {
                                    $i$f$forEachKey2 = $i$f$forEachKey3;
                                } else {
                                    $i$f$forEachKey2 = $i$f$forEachKey3;
                                    LookaheadCapablePlaceable parent = getParent();
                                    if (parent != null) {
                                        parent.invalidateChildrenOfDefiningRuler(ruler2);
                                    }
                                }
                            }
                            slot$iv$iv2 >>= 8;
                            j$iv$iv4 = j$iv$iv + 1;
                            $i$f$forEachKey3 = $i$f$forEachKey2;
                        }
                        $i$f$forEachKey = $i$f$forEachKey3;
                        if (bitCount$iv$iv2 != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv2 == lastIndex$iv$iv2) {
                        break;
                    }
                    i$iv$iv2++;
                    this_$iv2 = this_$iv3;
                    $i$f$forEachKey3 = $i$f$forEachKey;
                }
            }
            it.clear();
            return;
        }
        if (rulerReaders != null) {
            MutableScatterMap this_$iv4 = rulerReaders;
            Object[] v$iv2 = this_$iv4.values;
            long[] m$iv$iv3 = this_$iv4.metadata;
            int lastIndex$iv$iv3 = m$iv$iv3.length - 2;
            int i$iv$iv3 = 0;
            if (0 <= lastIndex$iv$iv3) {
                while (true) {
                    long slot$iv$iv3 = m$iv$iv3[i$iv$iv3];
                    ScatterMap this_$iv5 = this_$iv4;
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv3) << 7) & slot$iv$iv3 & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                        int bitCount$iv$iv3 = 8 - ((~(i$iv$iv3 - lastIndex$iv$iv3)) >>> 31);
                        for (int j$iv$iv5 = 0; j$iv$iv5 < bitCount$iv$iv3; j$iv$iv5++) {
                            long value$iv$iv$iv3 = slot$iv$iv3 & 255;
                            if (value$iv$iv$iv3 < 128) {
                                notifyRulerValueChange((MutableScatterSet) v$iv2[(i$iv$iv3 << 3) + j$iv$iv5]);
                            }
                            slot$iv$iv3 >>= 8;
                        }
                        if (bitCount$iv$iv3 != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv3 == lastIndex$iv$iv3) {
                        break;
                    }
                    i$iv$iv3++;
                    this_$iv4 = this_$iv5;
                }
            }
            rulerReaders.clear();
        }
    }

    private final void notifyRulerValueChange(MutableScatterSet<WeakReference<LayoutNode>> layoutNodes) {
        ScatterSet this_$iv;
        ScatterSet this_$iv2;
        MutableScatterSet<WeakReference<LayoutNode>> this_$iv3 = layoutNodes;
        Object[] k$iv = this_$iv3.elements;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
            } else {
                int i = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    int i2 = i;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        WeakReference layoutNodeRef = (WeakReference) k$iv[index$iv$iv];
                        LayoutNode layoutNode = (LayoutNode) layoutNodeRef.get();
                        if (layoutNode == null) {
                            this_$iv2 = this_$iv3;
                        } else if (isLookingAhead()) {
                            this_$iv2 = this_$iv3;
                            layoutNode.requestLookaheadRelayout$ui_release(false);
                        } else {
                            this_$iv2 = this_$iv3;
                            layoutNode.requestRelayout$ui_release(false);
                        }
                    }
                    slot$iv$iv >>= i2;
                    j$iv$iv++;
                    i = i2;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                if (bitCount$iv$iv != i) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
        }
    }

    public final void provideRulerValue(Ruler ruler, float value) {
        MutableObjectFloatMap it = this.rulerValues;
        if (it == null) {
            it = new MutableObjectFloatMap(0, 1, null);
            this.rulerValues = it;
        }
        it.set(ruler, value);
    }

    public final void provideRelativeRulerValue(Ruler ruler, float value) {
        float width;
        MutableObjectFloatMap it = this.rulerValues;
        if (it == null) {
            it = new MutableObjectFloatMap(0, 1, null);
            this.rulerValues = it;
        }
        if (getLayoutDirection() == LayoutDirection.Ltr) {
            width = value;
        } else {
            width = getWidth() - value;
        }
        it.set(ruler, width);
    }
}
