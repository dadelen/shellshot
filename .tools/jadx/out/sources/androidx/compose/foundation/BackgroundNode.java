package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Background.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010%\u001a\u00020&H\u0016J\f\u0010'\u001a\u00020&*\u00020(H\u0016J\f\u0010)\u001a\u00020&*\u00020(H\u0002J\f\u0010*\u001a\u00020&*\u00020(H\u0002J\f\u0010+\u001a\u00020\u001d*\u00020(H\u0002R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0004\u001a\u00020\u0005X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\u00020 X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0019R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006,"}, d2 = {"Landroidx/compose/foundation/BackgroundNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ObserverModifierNode;", "color", "Landroidx/compose/ui/graphics/Color;", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "(JLandroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "J", "lastLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "lastOutline", "Landroidx/compose/ui/graphics/Outline;", "lastShape", "lastSize", "Landroidx/compose/ui/geometry/Size;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "onObservedReadsChanged", "", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawOutline", "drawRect", "getOutline", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class BackgroundNode extends Modifier.Node implements DrawModifierNode, ObserverModifierNode {
    private float alpha;
    private Brush brush;
    private long color;
    private LayoutDirection lastLayoutDirection;
    private Outline lastOutline;
    private Shape lastShape;
    private long lastSize;
    private Shape shape;

    public /* synthetic */ BackgroundNode(long j, Brush brush, float f, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, brush, f, shape);
    }

    /* renamed from: getColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    /* renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m229setColor8_81llA(long j) {
        this.color = j;
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final void setBrush(Brush brush) {
        this.brush = brush;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape shape) {
        this.shape = shape;
    }

    private BackgroundNode(long color, Brush brush, float alpha, Shape shape) {
        this.color = color;
        this.brush = brush;
        this.alpha = alpha;
        this.shape = shape;
        this.lastSize = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        if (this.shape == RectangleShapeKt.getRectangleShape()) {
            drawRect($this$draw);
        } else {
            drawOutline($this$draw);
        }
        $this$draw.drawContent();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        this.lastSize = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
        this.lastLayoutDirection = null;
        this.lastOutline = null;
        this.lastShape = null;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    private final void drawRect(ContentDrawScope $this$drawRect) {
        if (!Color.m4164equalsimpl0(this.color, Color.INSTANCE.m4199getUnspecified0d7_KjU())) {
            DrawScope.m4707drawRectnJ9OG0$default($this$drawRect, this.color, 0L, 0L, 0.0f, null, null, 0, 126, null);
        }
        Brush it = this.brush;
        if (it != null) {
            DrawScope.m4706drawRectAsUm42w$default($this$drawRect, it, 0L, 0L, this.alpha, null, null, 0, 118, null);
        }
    }

    private final void drawOutline(ContentDrawScope $this$drawOutline) {
        Outline outline = getOutline($this$drawOutline);
        if (!Color.m4164equalsimpl0(this.color, Color.INSTANCE.m4199getUnspecified0d7_KjU())) {
            OutlineKt.m4420drawOutlinewDX37Ww($this$drawOutline, outline, this.color, (r16 & 4) != 0 ? 1.0f : 0.0f, (r16 & 8) != 0 ? Fill.INSTANCE : null, (r16 & 16) != 0 ? null : null, (r16 & 32) != 0 ? DrawScope.INSTANCE.m4715getDefaultBlendMode0nO6VwU() : 0);
        }
        Brush it = this.brush;
        if (it != null) {
            OutlineKt.m4418drawOutlinehn5TExg($this$drawOutline, outline, it, (r14 & 4) != 0 ? 1.0f : this.alpha, (r14 & 8) != 0 ? Fill.INSTANCE : null, (r14 & 16) != 0 ? null : null, (r14 & 32) != 0 ? DrawScope.INSTANCE.m4715getDefaultBlendMode0nO6VwU() : 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, androidx.compose.ui.graphics.Outline, java.lang.Object] */
    private final Outline getOutline(final ContentDrawScope $this$getOutline) {
        final Ref.ObjectRef outline = new Ref.ObjectRef();
        if (Size.m3987equalsimpl0($this$getOutline.mo4713getSizeNHjbRc(), this.lastSize) && $this$getOutline.getLayoutDirection() == this.lastLayoutDirection && Intrinsics.areEqual(this.lastShape, this.shape)) {
            ?? r1 = this.lastOutline;
            Intrinsics.checkNotNull(r1);
            outline.element = r1;
        } else {
            ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.foundation.BackgroundNode$getOutline$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r1v2, types: [T, androidx.compose.ui.graphics.Outline] */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    outline.element = this.getShape().mo275createOutlinePq9zytI($this$getOutline.mo4713getSizeNHjbRc(), $this$getOutline.getLayoutDirection(), $this$getOutline);
                }
            });
        }
        this.lastOutline = (Outline) outline.element;
        this.lastSize = $this$getOutline.mo4713getSizeNHjbRc();
        this.lastLayoutDirection = $this$getOutline.getLayoutDirection();
        this.lastShape = this.shape;
        T t = outline.element;
        Intrinsics.checkNotNull(t);
        return (Outline) t;
    }
}
