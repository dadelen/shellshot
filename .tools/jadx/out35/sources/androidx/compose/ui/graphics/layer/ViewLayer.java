package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GraphicsViewLayer.android.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u0000 22\u00020\u0001:\u00012B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$H\u0014J\b\u0010%\u001a\u00020\u0016H\u0016J\b\u0010&\u001a\u00020\tH\u0016J\b\u0010'\u001a\u00020\u0016H\u0016J0\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020+H\u0014J9\u0010/\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014¢\u0006\u0002\b\u0017J\u0010\u00100\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u00010\u001bR$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014¢\u0006\u0002\b\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Landroidx/compose/ui/graphics/layer/ViewLayer;", "Landroid/view/View;", "ownerView", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "(Landroid/view/View;Landroidx/compose/ui/graphics/CanvasHolder;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "value", "", "canUseCompositingLayer", "getCanUseCompositingLayer$ui_graphics_release", "()Z", "setCanUseCompositingLayer$ui_graphics_release", "(Z)V", "getCanvasHolder", "()Landroidx/compose/ui/graphics/CanvasHolder;", "density", "Landroidx/compose/ui/unit/Density;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "isInvalidated", "setInvalidated", "layerOutline", "Landroid/graphics/Outline;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getOwnerView", "()Landroid/view/View;", "parentLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "forceLayout", "hasOverlappingRendering", "invalidate", "onLayout", "changed", "l", "", "t", "r", "b", "setDrawParams", "setLayerOutline", "outline", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ViewLayer extends View {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ViewOutlineProvider LayerOutlineProvider = new ViewOutlineProvider() { // from class: androidx.compose.ui.graphics.layer.ViewLayer$Companion$LayerOutlineProvider$1
        /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
        
            r0 = ((androidx.compose.ui.graphics.layer.ViewLayer) r3).layerOutline;
         */
        @Override // android.view.ViewOutlineProvider
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void getOutline(android.view.View r3, android.graphics.Outline r4) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof androidx.compose.ui.graphics.layer.ViewLayer
                if (r0 == 0) goto L13
                r0 = r3
                androidx.compose.ui.graphics.layer.ViewLayer r0 = (androidx.compose.ui.graphics.layer.ViewLayer) r0
                android.graphics.Outline r0 = androidx.compose.ui.graphics.layer.ViewLayer.access$getLayerOutline$p(r0)
                if (r0 == 0) goto L13
                r1 = 0
                r4.set(r0)
            L13:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.ViewLayer$Companion$LayerOutlineProvider$1.getOutline(android.view.View, android.graphics.Outline):void");
        }
    };
    private boolean canUseCompositingLayer;
    private final CanvasDrawScope canvasDrawScope;
    private final CanvasHolder canvasHolder;
    private Density density;
    private Function1<? super DrawScope, Unit> drawBlock;
    private boolean isInvalidated;
    private Outline layerOutline;
    private LayoutDirection layoutDirection;
    private final View ownerView;
    private GraphicsLayer parentLayer;

    public /* synthetic */ ViewLayer(View view, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, (i & 2) != 0 ? new CanvasHolder() : canvasHolder, (i & 4) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    public final View getOwnerView() {
        return this.ownerView;
    }

    public final CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }

    public ViewLayer(View ownerView, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope) {
        super(ownerView.getContext());
        this.ownerView = ownerView;
        this.canvasHolder = canvasHolder;
        this.canvasDrawScope = canvasDrawScope;
        setOutlineProvider(LayerOutlineProvider);
        this.canUseCompositingLayer = true;
        this.density = DrawContextKt.getDefaultDensity();
        this.layoutDirection = LayoutDirection.Ltr;
        this.drawBlock = GraphicsLayerImpl.INSTANCE.getDefaultDrawBlock();
        setWillNotDraw(false);
        setClipBounds(null);
    }

    /* renamed from: isInvalidated, reason: from getter */
    public final boolean getIsInvalidated() {
        return this.isInvalidated;
    }

    public final void setInvalidated(boolean z) {
        this.isInvalidated = z;
    }

    public final boolean setLayerOutline(Outline outline) {
        this.layerOutline = outline;
        return OutlineUtils.INSTANCE.rebuildOutline(this);
    }

    /* renamed from: getCanUseCompositingLayer$ui_graphics_release, reason: from getter */
    public final boolean getCanUseCompositingLayer() {
        return this.canUseCompositingLayer;
    }

    public final void setCanUseCompositingLayer$ui_graphics_release(boolean value) {
        if (this.canUseCompositingLayer != value) {
            this.canUseCompositingLayer = value;
            invalidate();
        }
    }

    public final void setDrawParams(Density density, LayoutDirection layoutDirection, GraphicsLayer parentLayer, Function1<? super DrawScope, Unit> drawBlock) {
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = drawBlock;
        this.parentLayer = parentLayer;
    }

    @Override // android.view.View
    public void invalidate() {
        if (!this.isInvalidated) {
            this.isInvalidated = true;
            super.invalidate();
        }
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.canUseCompositingLayer;
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        CanvasHolder this_$iv = this.canvasHolder;
        Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
        this_$iv.getAndroidCanvas().setInternalCanvas(canvas);
        androidx.compose.ui.graphics.Canvas $this$dispatchDraw_u24lambda_u240 = this_$iv.getAndroidCanvas();
        DrawScope $this$draw_u2dymL40Pk$iv = this.canvasDrawScope;
        Density density$iv = this.density;
        LayoutDirection layoutDirection$iv = this.layoutDirection;
        long size$iv = SizeKt.Size(getWidth(), getHeight());
        GraphicsLayer graphicsLayer$iv = this.parentLayer;
        Function1 block$iv = this.drawBlock;
        Density prevDensity$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getDensity();
        LayoutDirection prevLayoutDirection$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getLayoutDirection();
        androidx.compose.ui.graphics.Canvas prevCanvas$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getCanvas();
        long prevSize$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().mo4634getSizeNHjbRc();
        GraphicsLayer prevLayer$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getGraphicsLayer();
        DrawContext $this$draw_ymL40Pk_u24lambda_u247$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
        $this$draw_ymL40Pk_u24lambda_u247$iv.setDensity(density$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setLayoutDirection(layoutDirection$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setCanvas($this$dispatchDraw_u24lambda_u240);
        $this$draw_ymL40Pk_u24lambda_u247$iv.mo4635setSizeuvyYCjk(size$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setGraphicsLayer(graphicsLayer$iv);
        $this$dispatchDraw_u24lambda_u240.save();
        try {
            block$iv.invoke($this$draw_u2dymL40Pk$iv);
            $this$dispatchDraw_u24lambda_u240.restore();
            DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
            $this$draw_ymL40Pk_u24lambda_u248$iv.setDensity(prevDensity$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setLayoutDirection(prevLayoutDirection$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setCanvas(prevCanvas$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.mo4635setSizeuvyYCjk(prevSize$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setGraphicsLayer(prevLayer$iv);
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
            this.isInvalidated = false;
        } catch (Throwable th) {
            $this$dispatchDraw_u24lambda_u240.restore();
            DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv2 = $this$draw_u2dymL40Pk$iv.getDrawContext();
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setDensity(prevDensity$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setLayoutDirection(prevLayoutDirection$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setCanvas(prevCanvas$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.mo4635setSizeuvyYCjk(prevSize$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setGraphicsLayer(prevLayer$iv);
            throw th;
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    @Override // android.view.View
    public void forceLayout() {
    }

    /* compiled from: GraphicsViewLayer.android.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/graphics/layer/ViewLayer$Companion;", "", "()V", "LayerOutlineProvider", "Landroid/view/ViewOutlineProvider;", "getLayerOutlineProvider$ui_graphics_release", "()Landroid/view/ViewOutlineProvider;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ViewOutlineProvider getLayerOutlineProvider$ui_graphics_release() {
            return ViewLayer.LayerOutlineProvider;
        }
    }
}
